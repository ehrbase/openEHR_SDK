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

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.util.List;
import javax.lang.model.element.Modifier;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.serialisation.util.SnakeCase;
import org.ehrbase.webtemplate.parser.FlatPath;

public class FieldGenerator {

  public static final String NEW_FIELD_DEFINITION = "new $T<$T>($T.class, $S, $S, $T.class, this)";

  public ClassGeneratorResult generate(List<JavaFile> files) {
    ClassGeneratorResult result = new ClassGeneratorResult();

    files.stream()
        .filter(javaFile -> containsAnnotation(javaFile.typeSpec.annotations, Archetype.class))
        .forEach(c -> result.addClass(c.packageName, buildFieldClass(c.typeSpec)));
    return result;
  }

  private String extractAnnotationValue(List<AnnotationSpec> annotationSpecs, Class<?> annotation) {
    return annotationSpecs.stream()
        .filter(a -> a.type.toString().equals(annotation.getName()))
        .map(a -> a.members.get("value"))
        .map(s -> s.get(0))
        .map(Object::toString)
        .map(s -> StringUtils.substringBetween(s, "\""))
        .findAny()
        .orElse("");
  }

  private boolean containsAnnotation(List<AnnotationSpec> annotationSpecs, Class<?> annotation) {
    return annotationSpecs.stream()
        .map(a -> a.type)
        .map(TypeName::toString)
        .anyMatch(s -> s.equals(annotation.getName()));
  }

  private TypeSpec buildFieldClass(TypeSpec c) {
    TypeSpec.Builder builder = TypeSpec.classBuilder(c.name + "Containment");
    builder.superclass(ClassName.get(Containment.class)).addModifiers(Modifier.PUBLIC);
    MethodSpec constructor =
        MethodSpec.constructorBuilder()
            .addModifiers(Modifier.PRIVATE)
            .addStatement("super($S)", extractAnnotationValue(c.annotations, Archetype.class))
            .build();
    builder.addMethod(constructor);
    builder.addMethod(
        MethodSpec.methodBuilder("getInstance")
            .addModifiers(Modifier.PUBLIC)
            .addModifiers(Modifier.STATIC)
            .returns(ClassName.get("", c.name + "Containment"))
            .addStatement("return new $T()", ClassName.get("", c.name + "Containment"))
            .build());
    FieldSpec.Builder selfField;

    ClassName baseClassName = ClassName.get("", c.name);
    selfField =
        FieldSpec.builder(
            ParameterizedTypeName.get(ClassName.get(SelectAqlField.class), baseClassName),
            new SnakeCase(c.name).camelToUpperSnake(),
            Modifier.PUBLIC);
    selfField.initializer(
        NEW_FIELD_DEFINITION,
        ClassName.get(AqlFieldImp.class),
        baseClassName,
        baseClassName,
        "",
        c.name,
        baseClassName);
    builder.addField(selfField.build());

    c.fieldSpecs.stream()
        .filter(javaFile -> containsAnnotation(javaFile.annotations, Path.class))
        .forEach(
            f ->
                builder.addField(
                    buildAQLField(
                        baseClassName,
                        f.name,
                        new FlatPath(extractAnnotationValue(f.annotations, Path.class))
                            .format(false),
                        f.type)));

    return builder.build();
  }

  private FieldSpec buildAQLField(ClassName baseClass, String name, String path, TypeName type) {
    final FieldSpec.Builder builder;

    if (ParameterizedTypeName.class.isAssignableFrom(type.getClass())) {
      TypeName typeName =
          ParameterizedTypeName.get(
              ClassName.get(ListSelectAqlField.class), unwarap((ParameterizedTypeName) type));
      builder =
          FieldSpec.builder(typeName, new SnakeCase(name).camelToUpperSnake(), Modifier.PUBLIC);
      builder.initializer(
          NEW_FIELD_DEFINITION,
          ClassName.get(ListAqlFieldImp.class),
          unwarap((ParameterizedTypeName) type),
          baseClass,
          path,
          name,
          unwarap((ParameterizedTypeName) type));
    } else {
      TypeName typeName = ParameterizedTypeName.get(ClassName.get(SelectAqlField.class), type);
      builder =
          FieldSpec.builder(typeName, new SnakeCase(name).camelToUpperSnake(), Modifier.PUBLIC);
      builder.initializer(
          NEW_FIELD_DEFINITION,
          ClassName.get(AqlFieldImp.class),
          type,
          baseClass,
          path,
          name,
          type);
    }
    return builder.build();
  }

  private TypeName unwarap(ParameterizedTypeName field) {
    return field.typeArguments.get(0);
  }
}
