/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.client.classgenerator;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.text.CaseUtils;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.classgenerator.config.RmClassGeneratorConfig;
import org.ehrbase.client.flattener.PathExtractor;
import org.ehrbase.client.introspect.TemplateIntrospect;
import org.ehrbase.serialisation.util.SnakeCase;
import org.ehrbase.terminology.client.terminology.TermDefinition;
import org.ehrbase.terminology.client.terminology.ValueSet;
import org.ehrbase.util.reflection.ReflectionHelper;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateInputValue;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.parser.FlatPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassGeneratorNew {

  private static final ArchieRMInfoLookup RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();
  public static final String ABBREV_MARKER = "_";
  public static final int CLASS_NAME_MAX_WIDTH = 80;
  private static final Map<Class<?>, RmClassGeneratorConfig> configMap =
      ReflectionHelper.buildMap(RmClassGeneratorConfig.class);

  private static class Context {
    final MultiValuedMap<String, TypeSpec> classes = new ArrayListValuedHashMap<>();
    final Deque<WebTemplateNode> nodeDeque = new ArrayDeque<>();
    String currentMainClass;
    final Deque<String> currentArchetypeName = new ArrayDeque<>();
    final Map<String, Integer> currentClassNameMap = new HashMap<>();
    String currentPackageName;
    final Map<ValueSet, TypeSpec> currentEnums = new HashMap<>();
    final Deque<Map<String, Integer>> currentFieldNameMap = new ArrayDeque<>();
    String templateId;
  }

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  public ClassGeneratorResult generate(String packageName, WebTemplate webTemplate) {

    Context context = new Context();
    context.templateId = webTemplate.getTemplateId();
    context.currentPackageName = packageName;

    TypeSpec.Builder builder = build(context, webTemplate.getTree());
    AnnotationSpec templateAnnotation =
        AnnotationSpec.builder(Template.class)
            .addMember(Template.VALUE, "$S", webTemplate.getTemplateId())
            .build();
    builder.addAnnotation(templateAnnotation);

    context.classes.put(
        packageName + "." + context.currentMainClass.toLowerCase(), builder.build());

    ClassGeneratorResult generatorResult = new ClassGeneratorResult();
    context.classes.entries().forEach(e -> generatorResult.addClass(e.getKey(), e.getValue()));
    return generatorResult;
  }

  private TypeSpec.Builder build(Context context, WebTemplateNode next) {

    String name;
    String className;
    if (context.currentArchetypeName.isEmpty()) {
      name = new SnakeCase(context.templateId).camelToSnake() + "_" + next.getRmType();
      className = buildClassName(context, name, false);
    } else {
      name = new SnakeCase(next.getName()).camelToSnake() + "_" + next.getRmType();
      className = buildClassName(context, name, false);
    }
    if (next.isArchetype()) {
      context.currentArchetypeName.push(next.getName());
    }

    context.currentFieldNameMap.push(new HashMap<>());


    TypeSpec.Builder classBuilder = TypeSpec.classBuilder(className);
    if (StringUtils.isBlank(context.currentMainClass)) {
      context.currentMainClass = className;
    }

    classBuilder.addModifiers(Modifier.PUBLIC);
    classBuilder.addAnnotation(AnnotationSpec.builder(Entity.class).build());
    if (next.isArchetype()) {
      AnnotationSpec archetypeAnnotation =
          AnnotationSpec.builder(Archetype.class)
              .addMember(Archetype.VALUE, "$S", next.getNodeId())
              .build();
      classBuilder.addAnnotation(archetypeAnnotation);
    }

    Map<String, List<WebTemplateNode>> choices = next.getChoicesInChildren();
    for (WebTemplateNode child : next.getChildren()) {
      String relativPath =
          FlatPath.removeStart(new FlatPath(child.getAqlPath()), new FlatPath(next.getAqlPath()))
              .toString();
      if (child.getChildren().isEmpty() && !choices.containsKey(child.getAqlPath())) {

        addSimpleField(context, classBuilder, relativPath, child);
      } else if (!choices.containsKey(child.getAqlPath())) {
        addComplexField(context, classBuilder, relativPath, child);
      }
    }
    if (next.isArchetype()) {
      context.currentArchetypeName.poll();
    }
    context.currentFieldNameMap.poll();
    context.nodeDeque.poll();
    return classBuilder;
  }

  private static String normalise(String name, boolean capitalizeFirstLetter) {
    if (StringUtils.isBlank(name) || name.equals("_")) {
      return RandomStringUtils.randomAlphabetic(10);
    }
    String normalisedString =
        StringUtils.strip(
            StringUtils.stripAccents(name).replace("ß", "ss").replaceAll("[^A-Za-z0-9]", "_"), "_");
    return CaseUtils.toCamelCase(normalisedString, capitalizeFirstLetter, '_');
  }

  String buildClassName(Context context, String name, boolean addArchetypeName) {
    String[] strings =
        Arrays.stream(name.split(TemplateIntrospect.TERM_DIVIDER))
            .filter(StringUtils::isNotBlank)
            .toArray(String[]::new);
    String fieldName = "";
    String nonNormalized = "";
    for (int i = 0; i < strings.length; i++) {
      nonNormalized = nonNormalized + "_" + strings[strings.length - (i + 1)];
      if (addArchetypeName) {
        nonNormalized =
            new SnakeCase(context.currentArchetypeName.peek()).camelToSnake() + "_" + nonNormalized;
      }
      fieldName =
          StringUtils.abbreviate(
              normalise(nonNormalized, true), ABBREV_MARKER, CLASS_NAME_MAX_WIDTH);
      if (!context.currentClassNameMap.containsKey(fieldName) && SourceVersion.isName(fieldName)) {
        break;
      }
    }

    if (context.currentClassNameMap.containsKey(fieldName)) {
      context.currentClassNameMap.put(fieldName, context.currentClassNameMap.get(fieldName) + 1);
      fieldName = fieldName + context.currentClassNameMap.get(fieldName);
    } else {
      context.currentClassNameMap.put(fieldName, 1);
    }
    fieldName = sanitizeNumber(fieldName);
    return fieldName;
  }

  private String sanitizeNumber(String fieldName) {
    if (!Character.isAlphabetic(fieldName.charAt(0))) {
      if (Character.isLowerCase(fieldName.charAt(0))) {
        fieldName = "n" + fieldName;
      } else {
        fieldName = "N" + fieldName;
      }
    }
    return fieldName;
  }

  private void addComplexField(
      Context context, TypeSpec.Builder classBuilder, String path, WebTemplateNode node) {

    TypeSpec subSpec = build(context, node).build();
    String subSpecPackage =
        context.currentPackageName + "." + context.currentMainClass.toLowerCase() + ".definition";

    context.classes.put(subSpecPackage, subSpec);
    TypeName className = ClassName.get(subSpecPackage, subSpec.name);
    if (node.isMulti()) {
      className = ParameterizedTypeName.get(ClassName.get(List.class), className);
    }
    addField(
        context,
        classBuilder,
        path,
        node.getName(),
        className,
        new ValueSet(ValueSet.LOCAL, ValueSet.LOCAL, Collections.emptySet()),
        false);
  }

  private void addSimpleField(
      Context context, TypeSpec.Builder classBuilder, String path, WebTemplateNode endNode) {

    Class clazz = RM_INFO_LOOKUP.getClass(endNode.getRmType());
    if (clazz == null) {
      logger.warn("No class for path {} ", path);
      return;
    }

    ValueSet valueSet = buildValueSet(endNode);

    RmClassGeneratorConfig classGeneratorConfig = configMap.get(clazz);
    if (classGeneratorConfig == null && !clazz.getName().contains("java.lang")) {
      logger.debug("No ClassGenerator for {}", clazz);
    }
    if (classGeneratorConfig == null || !classGeneratorConfig.isExpandField()) {

      TypeName className = ClassName.get(Optional.ofNullable(clazz).orElse(Object.class));
      if (endNode.getMax() != 1) {
        className = ParameterizedTypeName.get(ClassName.get(List.class), className);
      }

      addField(context, classBuilder, path, endNode.getName(), className, valueSet, false);
    } else {
      Map<String, Field> fieldMap =
          Arrays.stream(FieldUtils.getAllFields(clazz))
              .filter(f -> !f.isSynthetic())
              .collect(Collectors.toMap(Field::getName, f -> f));
      Set<String> expandFields = classGeneratorConfig.getExpandFields();
      expandFields.forEach(
          fieldName ->
              addField(
                  context,
                  classBuilder,
                  path + "|" + new SnakeCase(fieldName).camelToSnake(),
                  endNode.getName() + "_" + fieldName,
                  ClassName.get(fieldMap.get(fieldName).getType()),
                  valueSet,
                  false));
    }
  }

  private ValueSet buildValueSet(WebTemplateNode endNode) {

    Optional<WebTemplateInput> input =
        endNode.getInputs().stream().filter(i -> i.getType().equals("CODED_TEXT")).findAny();
    if (input.isPresent()) {

      ValueSet valueSet =
          new ValueSet(
              input.get().getTerminology(),
              "local",
              input.get().getList().stream().map(t -> toTerm(t)).collect(Collectors.toSet()));
      return valueSet;
    }

    return ValueSet.EMPTY_VALUE_SET;
  }

  private TermDefinition toTerm(WebTemplateInputValue t) {
    return new TermDefinition(
        t.getValue(),
        t.getLabel(),
        t.getLocalizedDescriptions().values().stream().findAny().orElse(t.getLabel()));
  }

  private void addField(
      Context context,
      TypeSpec.Builder classBuilder,
      String path,
      String name,
      TypeName className,
      ValueSet valueSet,
      boolean addChoiceAnnotation) {

    if (CodePhrase.class.getName().equals(className.toString())
        && CollectionUtils.isNotEmpty(valueSet.getTherms())) {

      final TypeSpec enumValueSet =
          context.currentEnums.computeIfAbsent(
              valueSet, vs -> buildEnumValueSet(context, name, vs));

      String enumPackage;
      if (valueSet.getId().equals("local") || valueSet.getTerminologyId().equals("local")) {
        enumPackage =
            context.currentPackageName
                + "."
                + context.currentMainClass.toLowerCase()
                + ".definition";
      } else {
        enumPackage = context.currentPackageName + ".shareddefinition";
      }
      context.classes.put(enumPackage, enumValueSet);
      className = ClassName.get(enumPackage, enumValueSet.name);
    }

    PathExtractor pathExtractor = new PathExtractor(path);
    String parentPath = pathExtractor.getParentPath();
    String childPath = pathExtractor.getChildPath();
    String fieldName =
        buildFieldName(
            context,
            parentPath
                + TemplateIntrospect.TERM_DIVIDER
                + childPath
                + TemplateIntrospect.TERM_DIVIDER
                + name);
    FieldSpec.Builder builder =
        FieldSpec.builder(className, fieldName)
            .addAnnotation(
                AnnotationSpec.builder(Path.class).addMember(Path.VALUE, "$S", path).build())
            .addModifiers(Modifier.PRIVATE);

    if (addChoiceAnnotation) {
      builder.addAnnotation(Choice.class);
    }

    FieldSpec fieldSpec = builder.build();
    classBuilder.addField(fieldSpec);

    classBuilder.addMethod(buildSetter(fieldSpec));
    classBuilder.addMethod(buildGetter(fieldSpec));
  }

  private TypeSpec buildEnumValueSet(Context context, String name, ValueSet valueSet) {
    TypeSpec.Builder enumBuilder =
        TypeSpec.enumBuilder(buildClassName(context, name, false))
            .addSuperinterface(EnumValueSet.class)
            .addModifiers(Modifier.PUBLIC);
    FieldSpec fieldSpec1 =
        FieldSpec.builder(ClassName.get(String.class), "value")
            .addModifiers(Modifier.PRIVATE)
            .build();
    enumBuilder.addField(fieldSpec1);
    FieldSpec fieldSpec2 =
        FieldSpec.builder(ClassName.get(String.class), "description")
            .addModifiers(Modifier.PRIVATE)
            .build();
    enumBuilder.addField(fieldSpec2);
    FieldSpec fieldSpec3 =
        FieldSpec.builder(ClassName.get(String.class), "terminologyId")
            .addModifiers(Modifier.PRIVATE)
            .build();
    enumBuilder.addField(fieldSpec3);
    FieldSpec fieldSpec4 =
        FieldSpec.builder(ClassName.get(String.class), "code")
            .addModifiers(Modifier.PRIVATE)
            .build();
    enumBuilder.addField(fieldSpec4);

    MethodSpec constructor = buildConstructor(fieldSpec1, fieldSpec2, fieldSpec3, fieldSpec4);
    enumBuilder.addMethod(constructor);
    valueSet
        .getTherms()
        .forEach(
            t -> {
              String fieldName = extractSubName(t.getValue());
              enumBuilder.addEnumConstant(
                  toEnumName(fieldName),
                  TypeSpec.anonymousClassBuilder(
                          "$S, $S, $S, $S",
                          t.getValue(),
                          t.getDescription(),
                          valueSet.getTerminologyId(),
                          t.getCode())
                      .build());
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
      builder
          .addParameter(fieldSpec.type, fieldSpec.name)
          .addStatement("this.$N = $N", fieldSpec.name, fieldSpec.name);
    }
    return builder.build();
  }

  private String extractSubName(String name) {
    String[] strings = name.split(TemplateIntrospect.TERM_DIVIDER);
    return strings[strings.length - 1];
  }

  /**
   * Manipulate the fieldName to remove or replace illegal characters
   *
   * @param fieldName
   * @return normalized fieldName for Java naming convention
   */
  private String toEnumName(String fieldName) {
    fieldName = sanitizeNumber(fieldName);
    return new SnakeCase(normalise(fieldName, false)).camelToUpperSnake();
  }

  private String buildFieldName(Context context, String name) {
    String[] strings =
        Arrays.stream(name.split(TemplateIntrospect.TERM_DIVIDER))
            .filter(StringUtils::isNotBlank)
            .toArray(String[]::new);

    String fieldName = "";
    String nonNormalized = "";
    for (int i = 0; i < strings.length; i++) {
      nonNormalized = nonNormalized + "_" + strings[strings.length - (i + 1)];
      fieldName = normalise(nonNormalized, false);
      if (!context.currentFieldNameMap.peek().containsKey(fieldName)
          && SourceVersion.isName(fieldName)) {
        break;
      }
    }

    if (context.currentFieldNameMap.peek().containsKey(fieldName)) {
      context
          .currentFieldNameMap
          .peek()
          .put(fieldName, context.currentFieldNameMap.peek().get(fieldName) + 1);
      fieldName = fieldName + context.currentFieldNameMap.peek().get(fieldName);
    } else {
      context.currentFieldNameMap.peek().put(fieldName, 1);
    }
    fieldName = sanitizeNumber(fieldName);
    return fieldName;
  }

  private MethodSpec buildSetter(FieldSpec fieldSpec) {

    return MethodSpec.methodBuilder("set" + StringUtils.capitalize(fieldSpec.name))
        .addModifiers(Modifier.PUBLIC)
        .addStatement(" this.$N = $N", fieldSpec.name, fieldSpec.name)
        .addParameter(fieldSpec.type, fieldSpec.name)
        .build();
  }

  private MethodSpec buildGetter(FieldSpec fieldSpec) {
    String prefix;
    if (Boolean.class.getTypeName().equals(fieldSpec.type.toString())) {
      prefix = "is";
    } else {
      prefix = "get";
    }
    return MethodSpec.methodBuilder(prefix + StringUtils.capitalize(fieldSpec.name))
        .addModifiers(Modifier.PUBLIC)
        .addStatement(" return this.$N ", fieldSpec.name)
        .returns(fieldSpec.type)
        .build();
  }
}
