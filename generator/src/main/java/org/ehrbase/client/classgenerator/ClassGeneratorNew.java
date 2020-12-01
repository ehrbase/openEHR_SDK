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
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.classgenerator.config.RmClassGeneratorConfig;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.MathFunction;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.serialisation.util.SnakeCase;
import org.ehrbase.terminology.client.terminology.TermDefinition;
import org.ehrbase.terminology.client.terminology.ValueSet;
import org.ehrbase.util.reflection.ReflectionHelper;
import org.ehrbase.webtemplate.filter.WebTemplateFilter;
import org.ehrbase.webtemplate.model.FilteredWebTemplate;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateInputValue;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.parser.FlatPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
  private final DefaultNamingStrategy defaultNamingStrategy = new DefaultNamingStrategy();

  static class Context {
    final MultiValuedMap<String, TypeSpec> classes = new ArrayListValuedHashMap<>();
    final Deque<WebTemplateNode> nodeDeque = new ArrayDeque<>();
    final Deque<WebTemplateNode> unFilteredNodeDeque = new ArrayDeque<>();
    final Map<WebTemplateNode, TypeSpec> currentTypeSpec = new HashMap<>();
    String currentMainClass;
    final Deque<String> currentArchetypeName = new ArrayDeque<>();
    final Map<String, Integer> currentClassNameMap = new HashMap<>();
    String currentPackageName;
    final Map<ValueSet, TypeSpec> currentEnums = new HashMap<>();
    final Deque<Map<String, Integer>> currentFieldNameMap = new ArrayDeque<>();
    FilteredWebTemplate webTemplate;
  }

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  private final WebTemplateFilter filter;

  public ClassGeneratorNew(ClassGeneratorConfig config) {

    filter = new FlattFilter();
  }

  public ClassGeneratorResult generate(String packageName, WebTemplate webTemplate) {

    Context context = new Context();

    context.currentPackageName = packageName;

    FilteredWebTemplate filteredWebTemplate = this.filter.filter(webTemplate);
    context.webTemplate = filteredWebTemplate;
    TypeSpec.Builder builder = build(context, filteredWebTemplate.getTree());
    AnnotationSpec templateAnnotation =
        AnnotationSpec.builder(Template.class)
            .addMember(Template.VALUE, "$S", webTemplate.getTemplateId())
            .build();
    builder.addAnnotation(templateAnnotation);
    addVersionUid(builder);

    context.classes.put(
        packageName + "." + context.currentMainClass.toLowerCase(), builder.build());

    ClassGeneratorResult generatorResult = new ClassGeneratorResult();
    context.classes.entries().forEach(e -> generatorResult.addClass(e.getKey(), e.getValue()));
    return generatorResult;
  }

  private void addVersionUid(TypeSpec.Builder classBuilder) {
    FieldSpec versionUid =
        FieldSpec.builder(VersionUid.class, "versionUid", Modifier.PRIVATE)
            .addAnnotation(Id.class)
            .build();
    classBuilder.addField(versionUid);
    classBuilder.addMethod(buildGetter(versionUid));
    classBuilder.addMethod(buildSetter(versionUid));
  }

  private TypeSpec.Builder build(Context context, WebTemplateNode next) {


    String className = defaultNamingStrategy.buildClassName(context, next, false, false);

    context.currentFieldNameMap.push(new HashMap<>());

    context.nodeDeque.push(next);
    context.unFilteredNodeDeque.push(next);

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



    if (next.getChildren().stream().anyMatch(n -> n.getRmType().equals("EVENT"))) {
      WebTemplateNode event =
              next.getChildren().stream().filter(n -> n.getRmType().equals("EVENT")).findAny().get();
      WebTemplateNode pointEvent = new WebTemplateNode(event);
      WebTemplateNode intervalEvent = new WebTemplateNode(event);
      pointEvent.setRmType("POINT_EVENT");
      intervalEvent.setRmType("INTERVAL_EVENT");
      WebTemplateNode width = new WebTemplateNode();
      width.setId("width");
      width.setName("width");
      width.setRmType("DV_DURATION");
      width.setMax(1);
      width.setAqlPath(event.getAqlPath() + "/width");
      intervalEvent.getChildren().add(width);
      WebTemplateNode math = new WebTemplateNode();
      math.setId("math_function");
      math.setName("math_function");
      math.setRmType("DV_CODED_TEXT");
      math.setMax(1);
      math.setAqlPath(event.getAqlPath() + "/math_function");
      intervalEvent.getChildren().add(math);
      next.getChildren().add(intervalEvent);
      next.getChildren().add(pointEvent);
      next.getChildren().remove(event);
    }
    Map<String, List<WebTemplateNode>> choices = next.getChoicesInChildren();
    List<WebTemplateNode> children =
            next.getChildren().stream()
                    .filter(c -> choices.values().stream().flatMap(List::stream).noneMatch(l -> l.equals(c)))
                    .collect(Collectors.toList());
    for (WebTemplateNode child : children) {

      Deque<WebTemplateNode> filtersNodes = pushToUnfiltered(context, child);

      String relativPath =
          FlatPath.removeStart(new FlatPath(child.getAqlPath()), new FlatPath(next.getAqlPath()))
              .toString();
      if (child.getChildren().isEmpty() && !choices.containsKey(child.getAqlPath())) {

        addSimpleField(context, classBuilder, relativPath, child);
      } else if (!choices.containsKey(child.getAqlPath())) {
        addComplexField(context, classBuilder, relativPath, child);
      }
      if (!CollectionUtils.isEmpty(filtersNodes)){
        filtersNodes.forEach(n -> context.unFilteredNodeDeque.poll());
      }
    }

    for (List<WebTemplateNode> choice : choices.values()) {



      WebTemplateNode node = choice.get(0);
      WebTemplateNode relativeNode = buildRelativeNode(context, node);
      Deque<WebTemplateNode> filtersNodes = pushToUnfiltered(context, node);
      TypeSpec interfaceSpec;
      TypeName interfaceClassName;
      if (context.currentTypeSpec.containsKey(relativeNode)) {
        interfaceSpec = context.currentTypeSpec.get(relativeNode);
        String interfacePackage =
                context.currentPackageName
                        + "."
                        + context.currentMainClass.toLowerCase()
                        + ".definition";
        context.classes.put(interfacePackage, interfaceSpec);

        interfaceClassName = ClassName.get(interfacePackage, interfaceSpec.name);
      } else {
        interfaceSpec =
            TypeSpec.interfaceBuilder(defaultNamingStrategy.buildClassName(context, choice.get(0), true, false))
                .addModifiers(Modifier.PUBLIC)
                .build();
        context.currentTypeSpec.put(relativeNode, interfaceSpec);

        String interfacePackage =
            context.currentPackageName
                + "."
                + context.currentMainClass.toLowerCase()
                + ".definition";
        context.classes.put(interfacePackage, interfaceSpec);
         interfaceClassName = ClassName.get(interfacePackage, interfaceSpec.name);

        for (WebTemplateNode child : choice) {
          TypeSpec.Builder build = build(context, child);
          build
              .addSuperinterface(interfaceClassName)
              .addAnnotation(
                  AnnotationSpec.builder(OptionFor.class)
                      .addMember(OptionFor.VALUE, "$S", child.getRmType())
                      .build());
          context.classes.put(interfacePackage, build.build());
        }
      }
      if (choice.stream().anyMatch(WebTemplateNode::isMulti)) {
        interfaceClassName =
            ParameterizedTypeName.get(ClassName.get(List.class), interfaceClassName);
      }
      String relativPath =
          FlatPath.removeStart(
                  new FlatPath(node.getAqlPath()), new FlatPath(next.getAqlPath()))
              .toString();
      addField(
          context,
          classBuilder,
          relativPath,
          node,
          interfaceClassName,
          new ValueSet(ValueSet.LOCAL, ValueSet.LOCAL, Collections.emptySet()),
          true);

      if (!CollectionUtils.isEmpty(filtersNodes)){
        filtersNodes.forEach(n -> context.unFilteredNodeDeque.poll());
      }
    }
    if (next.isArchetype()) {
      context.currentArchetypeName.poll();
    }

    if (children.isEmpty() && choices.isEmpty()){
      addSimpleField(context, classBuilder, "", next);
    }


    context.currentFieldNameMap.poll();
    context.nodeDeque.poll();

    context.unFilteredNodeDeque.poll();
    return classBuilder;
  }

  private Deque<WebTemplateNode> pushToUnfiltered(Context context, WebTemplateNode node) {
    Deque<WebTemplateNode> filtersNodes = context.webTemplate.findFiltersNodes(node);
    if (!CollectionUtils.isEmpty(filtersNodes)) {
     filtersNodes.descendingIterator().forEachRemaining(context.unFilteredNodeDeque::push);
    }
    return filtersNodes;
  }


  private void addComplexField(
      Context context, TypeSpec.Builder classBuilder, String path, WebTemplateNode node) {

    final TypeSpec subSpec;

    WebTemplateNode relativeNode = buildRelativeNode(context, node);

    if (context.currentTypeSpec.containsKey(relativeNode)) {
      subSpec = context.currentTypeSpec.get(relativeNode);
    } else {
      subSpec = build(context, node).build();
      context.currentTypeSpec.put(relativeNode, subSpec);
    }
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
        node,
        className,
        new ValueSet(ValueSet.LOCAL, ValueSet.LOCAL, Collections.emptySet()),
        false);
  }

  private WebTemplateNode buildRelativeNode(Context context, WebTemplateNode node) {
    WebTemplateNode relativeNode = new WebTemplateNode(node);

    List<WebTemplateNode> matching = relativeNode.findMatching(n -> true);
    matching.add(relativeNode);
    matching.forEach(
        n -> {
          String relativPath =
              FlatPath.removeStart(
                      new FlatPath(n.getAqlPath()),
                      new FlatPath(context.nodeDeque.peek().getAqlPath()))
                  .toString();
          n.setAqlPath(relativPath);
        });
    return relativeNode;
  }

  private void addSimpleField(
      Context context, TypeSpec.Builder classBuilder, String path, WebTemplateNode endNode) {

    Class clazz = extractClass(endNode);
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

      addField(context, classBuilder, path, endNode, className, valueSet, false);
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
                  endNode,
                  ClassName.get(fieldMap.get(fieldName).getType()),
                  valueSet,
                  false));
    }
  }

  private Class<?> extractClass(WebTemplateNode endNode) {
    switch (endNode.getRmType()) {
      case "STRING":
        return String.class;
      case "BOOLEAN":
        return Boolean.class;
      case "INTEGER":
        return Long.class;
      default:
        return RM_INFO_LOOKUP.getClass(endNode.getRmType());
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
      WebTemplateNode node,
      TypeName className,
      ValueSet valueSet,
      boolean addChoiceAnnotation) {

    if (CodePhrase.class.getName().equals(className.toString()))
      switch (node.getName()) {
        case "language":
          className = ClassName.get(Language.class);
          break;
        case "setting":
          className = ClassName.get(Setting.class);
          break;
        case "category":
          className = ClassName.get(Category.class);
          break;
        case "territory":
          className = ClassName.get(Territory.class);
          break;
        case "math_function":
          className = ClassName.get(MathFunction.class);
          break;
        default:
          if (CollectionUtils.isNotEmpty(valueSet.getTherms())) {

            final TypeSpec enumValueSet =
                context.currentEnums.computeIfAbsent(
                    valueSet, vs -> buildEnumValueSet(context, node, vs));

            String enumPackage =
                context.currentPackageName
                    + "."
                    + context.currentMainClass.toLowerCase()
                    + ".definition";

            context.classes.put(enumPackage, enumValueSet);
            className = ClassName.get(enumPackage, enumValueSet.name);
          }
      }

     String fieldName =
            defaultNamingStrategy.buildFieldName(
                    context,
                    path, node);
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

  private TypeSpec buildEnumValueSet(Context context, WebTemplateNode node, ValueSet valueSet) {
    TypeSpec.Builder enumBuilder =
        TypeSpec.enumBuilder(defaultNamingStrategy.buildClassName(context, node, false, true))
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
              enumBuilder.addEnumConstant(
                      defaultNamingStrategy.toEnumName(t.getValue()),
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
