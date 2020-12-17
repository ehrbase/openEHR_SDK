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

import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.Entry;
import com.nedap.archie.rm.datastructures.IntervalEvent;
import com.nedap.archie.rm.datastructures.PointEvent;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.processing.Generated;
import javax.lang.model.element.Modifier;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.classgenerator.config.RmClassGeneratorConfig;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.interfaces.IntervalEventEntity;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.interfaces.PointEventEntity;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.MathFunction;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.classgenerator.shareddefinition.Transition;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.serialisation.util.SnakeCase;
import org.ehrbase.serialisation.walker.Walker;
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

public class ClassGenerator {

  private static final ArchieRMInfoLookup RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();
  public static final String ABBREV_MARKER = "_";
  public static final int CLASS_NAME_MAX_WIDTH = 80;
  private static final Map<Class<?>, RmClassGeneratorConfig> configMap =
      ReflectionHelper.buildMap(RmClassGeneratorConfig.class);
  public static final String DEFINITION_PACKAGE = ".definition";

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final WebTemplateFilter filter;
  private final NamingStrategy defaultNamingStrategy;

  public ClassGenerator(WebTemplateFilter filter, NamingStrategy defaultNamingStrategy) {
    this.filter = filter;
    this.defaultNamingStrategy = defaultNamingStrategy;
  }

  public ClassGenerator(ClassGeneratorConfig config) {

    this(new FlattFilter(config), new DefaultNamingStrategy(config));
  }

  public ClassGeneratorResult generate(String packageName, WebTemplate webTemplate) {

    ClassGeneratorContext context = new ClassGeneratorContext();

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
    classBuilder.addMethod(buildGetter(versionUid, false));
    classBuilder.addMethod(buildSetter(versionUid, false));
  }

  private TypeSpec.Builder build(ClassGeneratorContext context, WebTemplateNode next) {

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

    AnnotationSpec generatedAnnotation = buildGeneratedAnnotation();
    classBuilder.addAnnotation(generatedAnnotation);

    classBuilder.addSuperinterface(findRMInterface(next));

    if (next.getChildren().stream().anyMatch(n -> n.getRmType().equals("EVENT"))) {
      WebTemplateNode event =
          next.getChildren().stream()
              .filter(n -> n.getRmType().equals("EVENT"))
              .findAny()
              .orElseThrow();

      Walker.EventHelper eventHelper = new Walker.EventHelper(event).invoke();
      WebTemplateNode pointEvent = eventHelper.getPointEvent();
      WebTemplateNode intervalEvent = eventHelper.getIntervalEvent();

      next.getChildren().add(intervalEvent);
      next.getChildren().add(pointEvent);
      next.getChildren().remove(event);
    }
    Map<String, List<WebTemplateNode>> choices = next.getChoicesInChildren();
    List<WebTemplateNode> children =
        next.getChildren().stream()
            .filter(
                c -> choices.values().stream().flatMap(List::stream).noneMatch(l -> l.equals(c)))
            .collect(Collectors.toList());
    for (WebTemplateNode child : children) {

      Deque<WebTemplateNode> filtersNodes = pushToUnfiltered(context, child);

      String relativPath = context.nodeDeque.peek().buildRelativPath(child);
      if (child.getChildren().isEmpty() && !choices.containsKey(child.getAqlPath())) {

        addSimpleField(context, classBuilder, relativPath, child);
      } else if (!choices.containsKey(child.getAqlPath())) {
        addComplexField(context, classBuilder, relativPath, child);
      }
      if (!CollectionUtils.isEmpty(filtersNodes)) {
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
                + DEFINITION_PACKAGE;
        context.classes.put(interfacePackage, interfaceSpec);

        interfaceClassName = ClassName.get(interfacePackage, interfaceSpec.name);
      } else {

        List<Pair<TypeSpec.Builder, WebTemplateNode>> builders = new ArrayList<>();
        for (WebTemplateNode child : choice) {
          TypeSpec.Builder build = build(context, child);
          builders.add(new ImmutablePair<>(build, child));
        }

        TypeSpec.Builder interfaceBuilder =
            TypeSpec.interfaceBuilder(
                    defaultNamingStrategy.buildClassName(context, choice.get(0), true, false))
                .addModifiers(Modifier.PUBLIC);

        interfaceBuilder.addAnnotation(buildGeneratedAnnotation());

        Set<FieldSpec> cowmenField = null;
        for (Set<FieldSpec> fields :
            builders.stream()
                .map(Pair::getLeft)
                .map(s -> s.fieldSpecs)
                .map(HashSet::new)
                .collect(Collectors.toList())) {
          if (cowmenField == null) {
            cowmenField = fields;
          } else {
            cowmenField = SetUtils.intersection(cowmenField, fields);
          }
        }
        if (cowmenField == null) {
          cowmenField = Collections.emptySet();
        }
        cowmenField.forEach(
            f -> {
              interfaceBuilder.addMethod(buildGetter(f, true));

              interfaceBuilder.addMethod(buildSetter(f, true));
            });

        interfaceSpec = interfaceBuilder.build();
        context.currentTypeSpec.put(relativeNode, interfaceSpec);

        String interfacePackage =
            context.currentPackageName
                + "."
                + context.currentMainClass.toLowerCase()
                + DEFINITION_PACKAGE;
        context.classes.put(interfacePackage, interfaceSpec);
        interfaceClassName = ClassName.get(interfacePackage, interfaceSpec.name);

        TypeName finalInterfaceClassName = interfaceClassName;
        builders.forEach(
            pair -> {
              TypeSpec.Builder builder =
                  pair.getKey()
                      .addSuperinterface(finalInterfaceClassName)
                      .addAnnotation(
                          AnnotationSpec.builder(OptionFor.class)
                              .addMember(OptionFor.VALUE, "$S", pair.getRight().getRmType())
                              .build());
              context.classes.put(interfacePackage, builder.build());
            });
      }
      if (choice.stream().anyMatch(WebTemplateNode::isMulti)) {
        interfaceClassName =
            ParameterizedTypeName.get(ClassName.get(List.class), interfaceClassName);
      }
      String relativPath =
          FlatPath.removeStart(new FlatPath(node.getAqlPath()), new FlatPath(next.getAqlPath()))
              .toString();
      addField(
          context,
          classBuilder,
          relativPath,
          node,
          interfaceClassName,
          new ValueSet(ValueSet.LOCAL, ValueSet.LOCAL, Collections.emptySet()),
          true);

      if (!CollectionUtils.isEmpty(filtersNodes)) {
        filtersNodes.forEach(n -> context.unFilteredNodeDeque.poll());
      }
    }
    if (next.isArchetype()) {
      context.currentArchetypeName.poll();
    }

    if (children.isEmpty() && choices.isEmpty()) {
      addSimpleField(context, classBuilder, "", next);
    }

    context.currentFieldNameMap.poll();
    context.nodeDeque.poll();

    context.unFilteredNodeDeque.poll();
    return classBuilder;
  }

  private AnnotationSpec buildGeneratedAnnotation() {
    return AnnotationSpec.builder(Generated.class)
        .addMember("value", "$S", getClass().getName())
        .addMember("date", "$S", OffsetDateTime.now().toString())
        .addMember(
            "comments",
            "$S",
            "https://github.com/ehrbase/openEHR_SDK Version: "
                + getClass().getPackage().getImplementationVersion())
        .build();
  }

  private Type findRMInterface(WebTemplateNode next) {

    Class<?> classToBeCreated = RM_INFO_LOOKUP.getClassToBeCreated(next.getRmType());

    if (Composition.class.isAssignableFrom(classToBeCreated)) {
      return CompositionEntity.class;
    } else if (Entry.class.isAssignableFrom(classToBeCreated)) {
      return EntryEntity.class;
    } else if (IntervalEvent.class.isAssignableFrom(classToBeCreated)) {
      return IntervalEventEntity.class;
    } else if (PointEvent.class.isAssignableFrom(classToBeCreated)) {
      return PointEventEntity.class;
    } else if (Locatable.class.isAssignableFrom(classToBeCreated)) {
      return LocatableEntity.class;
    } else {

      return RMEntity.class;
    }
  }

  private Deque<WebTemplateNode> pushToUnfiltered(
      ClassGeneratorContext context, WebTemplateNode node) {

    Deque<WebTemplateNode> filtersNodes = context.webTemplate.findFiltersNodes(node);
    if (!CollectionUtils.isEmpty(filtersNodes)) {
      filtersNodes.descendingIterator().forEachRemaining(context.unFilteredNodeDeque::push);
    }
    return filtersNodes;
  }

  private void addComplexField(
      ClassGeneratorContext context,
      TypeSpec.Builder classBuilder,
      String path,
      WebTemplateNode node) {

    final TypeSpec subSpec;

    WebTemplateNode relativeNode = buildRelativeNode(context, node);

    if (context.currentTypeSpec.containsKey(relativeNode)) {
      subSpec = context.currentTypeSpec.get(relativeNode);
    } else {
      subSpec = build(context, node).build();
      context.currentTypeSpec.put(relativeNode, subSpec);
    }
    String subSpecPackage =
        context.currentPackageName
            + "."
            + context.currentMainClass.toLowerCase()
            + DEFINITION_PACKAGE;

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

  private WebTemplateNode buildRelativeNode(ClassGeneratorContext context, WebTemplateNode node) {
    WebTemplateNode relativeNode = new WebTemplateNode(node);

    List<WebTemplateNode> matching = relativeNode.findMatching(n -> true);
    matching.add(relativeNode);
    matching.forEach(n -> n.setAqlPath(context.nodeDeque.peek().buildRelativPath(n)));
    return relativeNode;
  }

  private void addSimpleField(
      ClassGeneratorContext context,
      TypeSpec.Builder classBuilder,
      String path,
      WebTemplateNode endNode) {

    Class<?> clazz = extractClass(endNode);
    if (clazz == null) {
      logger.warn("No class for path {} ", path);
      return;
    }

    ValueSet valueSet = buildValueSet(endNode);

    RmClassGeneratorConfig classGeneratorConfig = configMap.get(clazz);
    if (classGeneratorConfig == null && !clazz.getName().contains("java.lang")) {
      logger.debug("No ClassGenerator for {}", clazz);
    }
    boolean expand = classGeneratorConfig != null && classGeneratorConfig.isExpandField();

    if (endNode.getRmType().equals("DV_CODED_TEXT")
        && !List.of(
                "transition",
                "language",
                "setting",
                "category",
                "territory",
                "math_function",
                "null_flavour")
            .contains(endNode.getId(false))) {
      expand =
          expand
              && endNode.getInputs().stream()
                  .filter(i -> i.getType().equals("CODED_TEXT"))
                  .map(WebTemplateInput::getList)
                  .flatMap(List::stream)
                  .findAny()
                  .isPresent();
    }

    if (!expand) {
      TypeName className =
          Optional.ofNullable(clazz).map(ClassName::get).orElse(ClassName.get(Object.class));
      if (endNode.isMulti() && !context.nodeDeque.peek().getRmType().equals("ELEMENT")) {
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
        return Integer.class;
      case "LONG":
        return Long.class;
      default:
        return RM_INFO_LOOKUP.getClass(endNode.getRmType());
    }
  }

  private ValueSet buildValueSet(WebTemplateNode endNode) {

    Optional<WebTemplateInput> input =
        endNode.getInputs().stream().filter(i -> i.getType().equals("CODED_TEXT")).findAny();
    if (input.isPresent()) {

      return new ValueSet(
          input.get().getTerminology(),
          "local",
          input.get().getList().stream().map(this::toTerm).collect(Collectors.toSet()));
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
      ClassGeneratorContext context,
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
        case "transition":
          className = ClassName.get(Transition.class);
          break;
        case "null_flavour":
          className = ClassName.get(NullFlavour.class);
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
                    + DEFINITION_PACKAGE;

            context.classes.put(enumPackage, enumValueSet);
            className = ClassName.get(enumPackage, enumValueSet.name);
          }
      }

    String fieldName = defaultNamingStrategy.buildFieldName(context, path, node);
    FieldSpec.Builder builder =
        FieldSpec.builder(className, fieldName)
            .addAnnotation(
                AnnotationSpec.builder(Path.class).addMember(Path.VALUE, "$S", path).build())
            .addModifiers(Modifier.PRIVATE)
            .addJavadoc(defaultNamingStrategy.buildFieldJavadoc(context, node));

    if (addChoiceAnnotation) {
      builder.addAnnotation(Choice.class);
    }

    FieldSpec fieldSpec = builder.build();
    classBuilder.addField(fieldSpec);

    classBuilder.addMethod(buildSetter(fieldSpec, false));
    classBuilder.addMethod(buildGetter(fieldSpec, false));
  }

  private TypeSpec buildEnumValueSet(
      ClassGeneratorContext context, WebTemplateNode node, ValueSet valueSet) {
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
            t ->
                enumBuilder.addEnumConstant(
                    defaultNamingStrategy.buildEnumConstantName(context, node, t.getValue()),
                    TypeSpec.anonymousClassBuilder(
                            "$S, $S, $S, $S",
                            t.getValue(),
                            t.getDescription(),
                            valueSet.getTerminologyId(),
                            t.getCode())
                        .build()));

    enumBuilder.addMethod(buildGetter(fieldSpec1, false));
    enumBuilder.addMethod(buildGetter(fieldSpec2, false));
    enumBuilder.addMethod(buildGetter(fieldSpec3, false));
    enumBuilder.addMethod(buildGetter(fieldSpec4, false));
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

  private MethodSpec buildSetter(FieldSpec fieldSpec, boolean isAbstract) {

    MethodSpec.Builder builder =
        MethodSpec.methodBuilder("set" + StringUtils.capitalize(fieldSpec.name));

    builder.addModifiers(Modifier.PUBLIC);
    if (isAbstract) {
      builder.addModifiers(Modifier.ABSTRACT);
    } else {
      builder.addStatement(" this.$N = $N", fieldSpec.name, fieldSpec.name);
    }
    builder.addParameter(fieldSpec.type, fieldSpec.name).build();
    return builder.build();
  }

  private MethodSpec buildGetter(FieldSpec fieldSpec, boolean isAbbstract) {
    String prefix;
    if (Boolean.class.getTypeName().equals(fieldSpec.type.toString())) {
      prefix = "is";
    } else {
      prefix = "get";
    }

    MethodSpec.Builder builder =
        MethodSpec.methodBuilder(prefix + StringUtils.capitalize(fieldSpec.name));

    builder.addModifiers(Modifier.PUBLIC);
    if (isAbbstract) {
      builder.addModifiers(Modifier.ABSTRACT);
    } else {
      builder.addStatement(" return this.$N ", fieldSpec.name);
    }
    builder.returns(fieldSpec.type);
    return builder.build();
  }
}
