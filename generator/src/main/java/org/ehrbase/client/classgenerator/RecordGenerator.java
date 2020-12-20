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

import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import com.squareup.javapoet.WildcardTypeName;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import javax.lang.model.element.Modifier;
import org.ehrbase.client.aql.containment.ContainmentExpression;
import org.ehrbase.client.aql.field.AqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.aql.query.EntityQuery;
import org.ehrbase.client.aql.query.NativeQuery;
import org.ehrbase.client.aql.record.Record;

public class RecordGenerator {

  public static final int MAX_RECORD_SIZE = 21;
  public static final Path DIRECTORY = Paths.get(".", "client/src/main/java/");
  public static final String RECORD_PACKAGE_NAME = "org.ehrbase.client.aql.record";
  public static final String QUERY_PACKAGE_NAME = "org.ehrbase.client.aql.query";

  private RecordGenerator() {}

  private void generate() throws IOException {
    HashMap<Integer, TypeSpec> typeSpecHashMap = new HashMap<>();

    for (int n = 1; n <= MAX_RECORD_SIZE; n++) {
      TypeSpec typeSpec = buildRecordN(n);
      typeSpecHashMap.put(n, typeSpec);
    }

    buildAbstractRecordImp(typeSpecHashMap);

    buildQuery(typeSpecHashMap);
  }

  private void buildQuery(HashMap<Integer, TypeSpec> typeSpecHashMap) throws IOException {
    TypeSpec.Builder builder = TypeSpec.interfaceBuilder("Query");
    builder.addTypeVariable(TypeVariableName.get("T", Record.class));
    builder.addModifiers(Modifier.PUBLIC);
    builder.addMethod(
        MethodSpec.methodBuilder("buildAql")
            .addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC)
            .returns(String.class)
            .build());
    builder.addMethod(
        MethodSpec.methodBuilder("fields")
            .addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC)
            .returns(
                ArrayTypeName.of(
                    ParameterizedTypeName.get(ClassName.get(AqlField.class), TypeName.OBJECT)))
            .build());

    builder.addMethod(
        MethodSpec.methodBuilder("buildEntityQuery")
            .addModifiers(Modifier.STATIC, Modifier.PUBLIC)
            .addParameter(ContainmentExpression.class, "containment")
            .addParameter(
                ArrayTypeName.of(
                    ParameterizedTypeName.get(
                        ClassName.get(SelectAqlField.class),
                        WildcardTypeName.subtypeOf(Object.class))),
                "selectFields")
            .addCode("return new EntityQuery<>(containment, selectFields);")
            .varargs(true)
            .returns(ParameterizedTypeName.get(EntityQuery.class, Record.class))
            .build());

    builder.addMethod(
        MethodSpec.methodBuilder("buildNativeQuery")
            .addModifiers(Modifier.STATIC, Modifier.PUBLIC)
            .addParameter(String.class, "aql")
            .addParameter(
                ArrayTypeName.of(
                    ParameterizedTypeName.get(
                        ClassName.get(Class.class), WildcardTypeName.subtypeOf(Object.class))),
                "expected")
            .addCode(
                CodeBlock.builder()
                    .addStatement(
                        "return new NativeQuery<>(aql, $T.stream(expected).map(AqlField::create).toArray($T<?>[]::new))",
                        ClassName.get(Arrays.class),
                        ClassName.get(AqlField.class))
                    .build())
            .varargs(true)
            .returns(ParameterizedTypeName.get(NativeQuery.class, Record.class))
            .build());

    for (int n = 1; n <= MAX_RECORD_SIZE; n++) {
      TypeSpec recordNTypeSpec = typeSpecHashMap.get(n);
      builder.addMethod(buildEntityQueryMethod(recordNTypeSpec));
      builder.addMethod(buildNativeQueryMethod(recordNTypeSpec));
    }

    TypeSpec typeSpec = builder.build();

    JavaFile javaFile = JavaFile.builder(QUERY_PACKAGE_NAME, typeSpec).build();
    javaFile.writeTo(DIRECTORY);
  }

  private MethodSpec buildEntityQueryMethod(TypeSpec recordNTypeSpec) {
    MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("buildEntityQuery");
    methodBuilder.addModifiers(Modifier.PUBLIC, Modifier.STATIC);
    recordNTypeSpec.typeVariables.forEach(methodBuilder::addTypeVariable);
    methodBuilder.returns(
        ParameterizedTypeName.get(
            ClassName.get(EntityQuery.class),
            ParameterizedTypeName.get(
                ClassName.get(RECORD_PACKAGE_NAME, recordNTypeSpec.name),
                recordNTypeSpec.typeVariables.toArray(TypeName[]::new))));
    methodBuilder.addParameter(ContainmentExpression.class, "containment");
    recordNTypeSpec.typeVariables.forEach(
        t ->
            methodBuilder.addParameter(
                ParameterizedTypeName.get(ClassName.get(SelectAqlField.class), t),
                "selectField" + t.name.replace("T", "")));

    String vars =
        recordNTypeSpec.typeVariables.stream()
            .map(t -> "selectField" + t.name.replace("T", ""))
            .collect(Collectors.joining(","));

    methodBuilder.addCode("return new EntityQuery<>(containment," + vars + ");");

    return methodBuilder.build();
  }

  private MethodSpec buildNativeQueryMethod(TypeSpec recordNTypeSpec) {
    MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("buildNativeQuery");
    methodBuilder.addModifiers(Modifier.PUBLIC, Modifier.STATIC);
    recordNTypeSpec.typeVariables.forEach(methodBuilder::addTypeVariable);
    methodBuilder.returns(
        ParameterizedTypeName.get(
            ClassName.get(NativeQuery.class),
            ParameterizedTypeName.get(
                ClassName.get(RECORD_PACKAGE_NAME, recordNTypeSpec.name),
                recordNTypeSpec.typeVariables.toArray(TypeName[]::new))));
    methodBuilder.addParameter(String.class, "aql");
    recordNTypeSpec.typeVariables.forEach(
        t ->
            methodBuilder.addParameter(
                ParameterizedTypeName.get(ClassName.get(Class.class), t),
                "expected" + t.name.replace("T", "")));

    String vars =
        recordNTypeSpec.typeVariables.stream()
            .map(t -> "AqlField.create(expected" + t.name.replace("T", "") + ")")
            .collect(Collectors.joining(","));

    methodBuilder.addCode("return new NativeQuery<>(aql," + vars + ");");

    return methodBuilder.build();
  }

  private void buildAbstractRecordImp(HashMap<Integer, TypeSpec> typeSpecHashMap)
      throws IOException {
    TypeSpec.Builder builder = TypeSpec.classBuilder("AbstractRecordImp");
    builder.addModifiers(Modifier.ABSTRACT);
    for (int n = 1; n <= MAX_RECORD_SIZE; n++) {
      TypeVariableName typeVariable = TypeVariableName.get("T" + n);
      builder.addTypeVariable(typeVariable);
      TypeSpec recordNTypeSpec = typeSpecHashMap.get(n);
      builder.addSuperinterface(
          ParameterizedTypeName.get(
              ClassName.get(RECORD_PACKAGE_NAME, recordNTypeSpec.name),
              recordNTypeSpec.typeVariables.toArray(TypeName[]::new)));

      builder.addMethod(
          MethodSpec.methodBuilder("value" + n)
              .addModifiers(Modifier.PUBLIC)
              .addCode(
                  CodeBlock.builder()
                      .addStatement("return ($T) value(" + (n - 1) + ")", typeVariable)
                      .build())
              .returns(typeVariable)
              .build());
      builder.addMethod(
          MethodSpec.methodBuilder("field" + n)
              .addModifiers(Modifier.PUBLIC)
              .addCode(
                  CodeBlock.builder()
                      .addStatement(
                          "return ($T) field(" + (n - 1) + ")",
                          ParameterizedTypeName.get(ClassName.get(AqlField.class), typeVariable))
                      .build())
              .returns(ParameterizedTypeName.get(ClassName.get(AqlField.class), typeVariable))
              .build());
    }
    TypeSpec typeSpec = builder.build();

    JavaFile javaFile = JavaFile.builder(RECORD_PACKAGE_NAME, typeSpec).build();
    javaFile.writeTo(DIRECTORY);
  }

  private TypeSpec buildRecordN(int n) throws IOException {
    TypeSpec.Builder builder = TypeSpec.interfaceBuilder("Record" + n);
    builder.addSuperinterface(Record.class);
    builder.addModifiers(Modifier.PUBLIC);

    for (int i = 1; i <= n; i++) {
      TypeVariableName typeVariable = TypeVariableName.get("T" + i);
      builder.addTypeVariable(typeVariable);
      builder.addMethod(
          MethodSpec.methodBuilder("value" + i)
              .addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC)
              .returns(typeVariable)
              .build());
      builder.addMethod(
          MethodSpec.methodBuilder("field" + i)
              .addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC)
              .returns(ParameterizedTypeName.get(ClassName.get(AqlField.class), typeVariable))
              .build());
    }

    TypeSpec typeSpec = builder.build();

    JavaFile javaFile = JavaFile.builder(RECORD_PACKAGE_NAME, typeSpec).build();
    javaFile.writeTo(DIRECTORY);
    return typeSpec;
  }

  public static void main(String[] args) throws IOException {
    new RecordGenerator().generate();
  }
}
