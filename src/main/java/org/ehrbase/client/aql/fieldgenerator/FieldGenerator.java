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

package org.ehrbase.client.aql.fieldgenerator;

import com.squareup.javapoet.*;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.ClassGeneratorResult;
import org.ehrbase.ehr.encode.wrappers.SnakeCase;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;

import javax.lang.model.element.Modifier;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class FieldGenerator {

    public ClassGeneratorResult generate(String rootPackage) {
        ClassGeneratorResult result = new ClassGeneratorResult();

        Reflections reflections = new Reflections(rootPackage, new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner());
        reflections.getSubTypesOf(Object.class)
                .stream()
                .filter(c -> c.isAnnotationPresent(Archetype.class))
                .forEach(c -> result.addClass(c.getPackageName(), buildFieldClass(c)));
        return result;
    }

    private TypeSpec buildFieldClass(Class<?> c) {
        TypeSpec.Builder builder = TypeSpec.classBuilder(c.getSimpleName() + "Containment");
        builder
                .superclass(ClassName.get(Containment.class))
                .addModifiers(Modifier.PUBLIC);
        MethodSpec constructor = MethodSpec
                .constructorBuilder()
                .addModifiers(Modifier.PRIVATE)
                .addStatement("super($S)", c.getAnnotation(Archetype.class).value())
                .build();
        builder.addMethod(constructor);
        builder.addMethod(MethodSpec.methodBuilder("getInstance")
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.STATIC)
                .returns(ClassName.get("", c.getSimpleName() + "Containment"))
                .addStatement("return new $T()", ClassName.get("", c.getSimpleName() + "Containment"))
                .build());
        FieldSpec.Builder selfField;


        selfField = FieldSpec.builder(ParameterizedTypeName.get(ClassName.get(SelectAqlField.class), ClassName.get(c)), new SnakeCase(c.getSimpleName()).camelToUpperSnake(), Modifier.PUBLIC);
        selfField.initializer("new $T<$T>($T.class, $S, $S, $T.class, this)", ClassName.get(AqlFieldImp.class), ClassName.get(c), ClassName.get(c), "", c.getSimpleName(), ClassName.get(c));
        builder.addField(selfField.build());

        Arrays.stream(c.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Path.class))
                .forEach(f -> builder.addField(buildField(f, c)));

        return builder.build();
    }

    private FieldSpec buildField(Field f, Class<?> baseClass) {
        final FieldSpec.Builder builder;

        if (List.class.isAssignableFrom(f.getType())) {
            TypeName typeName = ParameterizedTypeName.get(ClassName.get(ListSelectAqlField.class), ClassName.get(unwarap(f)));
            builder = FieldSpec.builder(typeName, new SnakeCase(f.getName()).camelToUpperSnake(), Modifier.PUBLIC);
            builder.initializer("new $T<$T>($T.class, $S, $S, $T.class, this)", ClassName.get(ListAqlFieldImp.class), ClassName.get(unwarap(f)), ClassName.get(baseClass), f.getAnnotation(Path.class).value(), f.getName(), ClassName.get(unwarap(f)));
        } else {
            TypeName typeName = ParameterizedTypeName.get(ClassName.get(SelectAqlField.class), ClassName.get(f.getType()));
            builder = FieldSpec.builder(typeName, new SnakeCase(f.getName()).camelToUpperSnake(), Modifier.PUBLIC);
            builder.initializer("new $T<$T>($T.class, $S, $S, $T.class, this)", ClassName.get(AqlFieldImp.class), ClassName.get(f.getType()), ClassName.get(baseClass), f.getAnnotation(Path.class).value(), f.getName(), ClassName.get(f.getType()));
        }
        return builder.build();
    }


    public Class<?> unwarap(Field field) {
        try {
            if (List.class.isAssignableFrom(field.getType())) {
                Type actualTypeArgument = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];

                Class<?> aClass = ReflectionUtils.forName(actualTypeArgument.getTypeName(), this.getClass().getClassLoader());
                if (aClass != null) {
                    return aClass;
                } else {
                    return null;
                }

            } else {
                return field.getType();
            }
        } catch (Throwable e) {
            return null;
        }
    }

}
