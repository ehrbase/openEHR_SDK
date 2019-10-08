/*
 *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  This file is part of Project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.client.classgenerator;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.squareup.javapoet.*;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.text.CaseUtils;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.classgenerator.config.RmClassGeneratorConfig;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.introspect.TemplateIntrospect;
import org.ehrbase.client.introspect.node.ArchetypeNode;
import org.ehrbase.client.introspect.node.EndNode;
import org.ehrbase.client.introspect.node.Node;
import org.ehrbase.client.introspect.node.TemplateNode;
import org.ehrbase.ehr.encode.wrappers.SnakeCase;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;
import org.reflections.Reflections;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ClassGenerator {

    public static final Options OPTIONS = new Options();
    private final Map<Class, RmClassGeneratorConfig> configMap;
    private Map<String, Integer> curentFieldNameMap;

    public ClassGenerator() {
        configMap = buildConfigMap();
    }

    private Map<Class, RmClassGeneratorConfig> buildConfigMap() {
        Reflections reflections = new Reflections(RmClassGeneratorConfig.class.getPackage().getName());
        Set<Class<? extends RmClassGeneratorConfig>> configs = reflections.getSubTypesOf(RmClassGeneratorConfig.class);

        return configs.stream().map(c -> {
            try {
                return c.getConstructor().newInstance();
            } catch (Exception e) {
                throw new ClientException(e.getMessage(), e);
            }
        }).collect(Collectors.toMap(RmClassGeneratorConfig::getRMClass, c -> c));
    }

    public TypeSpec generate(OPERATIONALTEMPLATE operationalTemplate) {
        ArchetypeNode root = new TemplateIntrospect(operationalTemplate).getRoot();
        String templateId = operationalTemplate.getTemplateId().getValue();
        TemplateNode templateNode = new TemplateNode(templateId, root.getArchetypeId(), root.getChildren(), templateId);
        return build(templateNode);

    }

    private TypeSpec build(ArchetypeNode archetypeNode) {
        curentFieldNameMap = new HashMap<>();
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(normalise(archetypeNode.getName(), true));
        classBuilder.addModifiers(Modifier.PUBLIC);

        AnnotationSpec archetypeAnnotation = AnnotationSpec.builder(Archetype.class).addMember(Archetype.VALUE, "$S", archetypeNode.getArchetypeId()).build();
        classBuilder.addAnnotation(archetypeAnnotation);

        if (archetypeNode instanceof TemplateNode) {
            AnnotationSpec templateAnnotation = AnnotationSpec.builder(Template.class).addMember(Template.VALUE, "$S", ((TemplateNode) archetypeNode).getTemplateId()).build();
            classBuilder.addAnnotation(templateAnnotation);
        } else {
            classBuilder.addModifiers(Modifier.STATIC);
        }

        for (Map.Entry<String, Node> entry : archetypeNode.getChildren().entrySet()) {
            if (entry.getValue() instanceof EndNode) {
                EndNode endNode = (EndNode) entry.getValue();
                addSimpleField(classBuilder, entry.getKey(), endNode);
            } else if (entry.getValue() instanceof ArchetypeNode) {
                addComplexField(classBuilder, entry.getKey(), (ArchetypeNode) entry.getValue());
            }
        }

        return classBuilder.build();
    }

    private void addSimpleField(TypeSpec.Builder classBuilder, String path, EndNode endNode) {
        RmClassGeneratorConfig classGeneratorConfig = configMap.get(endNode.getClazz());
        if (CodePhrase.class.equals(endNode.getClazz()) && !endNode.getValuset().isEmpty()) {

            TypeSpec build = buildEnumValueSet(endNode);
            classBuilder.addType(build);
            addField(classBuilder, path, endNode.getName(), ClassName.get("", build.name));
        } else if (classGeneratorConfig == null || !classGeneratorConfig.idExpandField()) {
            addField(classBuilder, path, endNode.getName(), ClassName.get(Optional.ofNullable(endNode.getClazz()).orElse(Object.class)));
        } else {
            Map<String, Field> fieldMap = Arrays.stream(FieldUtils.getAllFields(endNode.getClazz())).collect(Collectors.toMap(Field::getName, f -> f));
            classGeneratorConfig.getExpandFields().forEach(fieldName -> addField(classBuilder, path + "|" + new SnakeCase(fieldName).camelToSnake(), endNode.getName() + "_" + fieldName, ClassName.get(fieldMap.get(fieldName).getType())));
        }
    }

    private void addComplexField(TypeSpec.Builder classBuilder, String path, ArchetypeNode node) {
        TypeSpec subSpec = build(node);
        classBuilder.addType(subSpec);

        TypeName className = ClassName.get("", subSpec.name);
        if ((node).isMulti()) {
            className = ParameterizedTypeName.get(ClassName.get(List.class), className);
        }
        addField(classBuilder, path, node.getName(), className);
    }

    private TypeSpec buildEnumValueSet(EndNode endNode) {
        TypeSpec.Builder enumBuilder = TypeSpec
                .enumBuilder(normalise(extractSubName(endNode.getName()), true))
                .addSuperinterface(EnumValueSet.class)
                .addModifiers(Modifier.PUBLIC);
        FieldSpec fieldSpec1 = FieldSpec.builder(ClassName.get(String.class), "value").addModifiers(Modifier.PRIVATE).build();
        enumBuilder.addField(fieldSpec1);
        FieldSpec fieldSpec2 = FieldSpec.builder(ClassName.get(String.class), "description").addModifiers(Modifier.PRIVATE).build();
        enumBuilder.addField(fieldSpec2);
        FieldSpec fieldSpec3 = FieldSpec.builder(ClassName.get(String.class), "terminologyId").addModifiers(Modifier.PRIVATE).build();
        enumBuilder.addField(fieldSpec3);
        FieldSpec fieldSpec4 = FieldSpec.builder(ClassName.get(String.class), "code").addModifiers(Modifier.PRIVATE).build();
        enumBuilder.addField(fieldSpec4);

        MethodSpec constructor = buildConstructor(fieldSpec1, fieldSpec2, fieldSpec3, fieldSpec4);
        enumBuilder.addMethod(constructor);
        endNode.getValuset().forEach(t -> {
            String fieldName = extractSubName(t.getValue());
            enumBuilder.addEnumConstant(normalise(fieldName, false).toUpperCase(), TypeSpec.anonymousClassBuilder("$S, $S, $S, $S", t.getValue(), t.getDescription(), "local", t.getCode()).build());
        });

        enumBuilder.addMethod(buildGetter(fieldSpec1));
        enumBuilder.addMethod(buildGetter(fieldSpec2));
        enumBuilder.addMethod(buildGetter(fieldSpec3));
        enumBuilder.addMethod(buildGetter(fieldSpec4));
        return enumBuilder.build();
    }

    private MethodSpec buildConstructor(FieldSpec... fieldSpecs) {
        MethodSpec.Builder builder = MethodSpec.constructorBuilder();
        for (FieldSpec fieldSpec : fieldSpecs) {
            builder.addParameter(fieldSpec.type, fieldSpec.name)
                    .addStatement("this.$N = $N", fieldSpec.name, fieldSpec.name);
        }
        return builder.build();
    }

    private void addField(TypeSpec.Builder classBuilder, String path, String name, TypeName className) {
        AnnotationSpec annotationSpec = AnnotationSpec.builder(Path.class).addMember(Path.VALUE, "$S", path).build();
        String fieldName = buildFieldName(name);
        FieldSpec fieldSpec = FieldSpec.builder(className, fieldName)
                .addAnnotation(annotationSpec)
                .addModifiers(Modifier.PRIVATE)
                .build();
        classBuilder.addField(fieldSpec);

        classBuilder.addMethod(buildSetter(fieldSpec));
        classBuilder.addMethod(buildGetter(fieldSpec));
    }

    private String buildFieldName(String name) {
        String[] strings = name.split(TemplateIntrospect.TERM_DIVIDER);

        String fieldName = "";
        for (int i = 0; i < strings.length; i++) {
            fieldName = normalise(fieldName + "_" + strings[strings.length - 1], false);
            if (!curentFieldNameMap.containsKey(fieldName)) {
                break;
            }
        }

        if (curentFieldNameMap.containsKey(fieldName)) {
            curentFieldNameMap.put(fieldName, curentFieldNameMap.get(fieldName) + 1);
            fieldName = fieldName + curentFieldNameMap.get(fieldName);
        } else {
            curentFieldNameMap.put(fieldName, 1);
        }
        return fieldName;
    }

    private MethodSpec buildSetter(FieldSpec fieldSpec) {

        return MethodSpec.methodBuilder("set" + StringUtils.capitalize(fieldSpec.name)).addModifiers(Modifier.PUBLIC)
                .addStatement(" this.$N = $N", fieldSpec.name, fieldSpec.name)
                .addParameter(fieldSpec.type, fieldSpec.name).build();
    }

    private MethodSpec buildGetter(FieldSpec fieldSpec) {
        String prefix;
        if (Boolean.class.getTypeName().equals(fieldSpec.type.toString())) {
            prefix = "is";
        } else {
            prefix = "get";
        }
        return MethodSpec.methodBuilder(prefix + StringUtils.capitalize(fieldSpec.name)).addModifiers(Modifier.PUBLIC)
                .addStatement(" return this.$N ", fieldSpec.name)
                .returns(fieldSpec.type).build();
    }


    private String extractSubName(String name) {
        String[] strings = name.split(TemplateIntrospect.TERM_DIVIDER);
        return strings[strings.length - 1];
    }

    private static String normalise(String name, boolean capitalizeFirstLetter) {
        if (StringUtils.isBlank(name) || name.equals("_")) {
            return RandomStringUtils.randomAlphabetic(10);
        }
        String normalisedString = name.replaceAll("[^A-Za-z0-9]", "_");
        return CaseUtils.toCamelCase(normalisedString, capitalizeFirstLetter, '_');
    }

    public static void main(String[] args) throws IOException, XmlException {


        OPTIONS.addOption("opt", true, "path to opt file");
        OPTIONS.addOption("package", true, "package name");
        OPTIONS.addOption("out", true, "path to output directory");
        OPTIONS.addOption("h", false, "show help");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(OPTIONS, args);
        } catch (ParseException e) {
            System.out.println(String.format("Error parsing commandline %s", e.getMessage()));
            showHelp();
        }

        if (cmd.hasOption("h")) {
            showHelp();
        }

        if (!cmd.hasOption("opt") || !cmd.hasOption("package") || !cmd.hasOption("out")) {
            showHelp();
        }
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(Paths.get(cmd.getOptionValue("opt")).toFile()).getTemplate();
        ClassGenerator cut = new ClassGenerator();
        TypeSpec generate = cut.generate(template);
        JavaFile javaFile = JavaFile.builder(cmd.getOptionValue("package"), generate)
                .build();
        java.nio.file.Path fsRoot = Paths.get(cmd.getOptionValue("out"));

        javaFile.writeTo(fsRoot);
    }

    private static void showHelp() {

        HelpFormatter formatter = new HelpFormatter();

        formatter.printHelp("java -jar client-library.jar ", OPTIONS);

        System.exit(0);

    }
}
