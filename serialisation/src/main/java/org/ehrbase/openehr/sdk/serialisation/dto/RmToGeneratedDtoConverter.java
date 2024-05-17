/*
 * Copyright (c) 2019 vitasystems GmbH.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.serialisation.dto;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Id;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Template;
import org.ehrbase.openehr.sdk.util.exception.ClientException;
import org.ehrbase.openehr.sdk.util.exception.SdkException;
import org.ehrbase.openehr.sdk.util.reflection.ReflectionHelper;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;
import org.ehrbase.openehr.sdk.webtemplate.templateprovider.TemplateProvider;

public class RmToGeneratedDtoConverter {

    private ScanResult classGraph;

    private final TemplateProvider templateProvider;

    public RmToGeneratedDtoConverter(TemplateProvider templateProvider) {
        this.templateProvider = templateProvider;
    }

    private static <T> T createInstance(Class<T> aClass) {
        try {
            return aClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException
                | NoSuchMethodException
                | InvocationTargetException
                | IllegalAccessException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    public <T> T toGeneratedDto(RMObject locatable, Class<T> clazz) {
        try {

            T dto = createInstance(clazz);
            Class<?> rootC = ReflectionHelper.findRootClass(clazz);
            String packageNames = StringUtils.removeEnd(rootC.getPackageName(), ".definition");
            classGraph = createClassGraph(packageNames).scan();

            String templateId = classGraph
                    .getClassesWithAnnotation(Template.class.getName())
                    .loadClasses()
                    .get(0)
                    .getAnnotation(Template.class)
                    .value();

            String archetypeValue = clazz.getAnnotation(Archetype.class).value();
            WebTemplateNode root = WebTemplateNode.streamSubtree(
                            templateProvider
                                    .buildIntrospect(templateId)
                                    .orElseThrow(() ->
                                            new SdkException(String.format("Can not find Template: %s", templateId)))
                                    .getTree(),
                            false)
                    .filter(n -> Objects.equals(n.getNodeId(), archetypeValue))
                    .findFirst()
                    .orElseThrow();
            new DtoFromCompositionWalker()
                    .walk(
                            locatable,
                            new DtoWithMatchingFields(
                                    dto, DtoFromCompositionWalker.buildFieldByPathMap(dto.getClass())),
                            root,
                            templateId);
            if (locatable instanceof Composition && ((Composition) locatable).getUid() != null) {
                addVersion(
                        dto,
                        new ObjectVersionId(((Composition) locatable).getUid().toString()));
            }
            return dto;
        } finally {
            if (classGraph != null) {
                classGraph.close();
            }
        }
    }

    protected ClassGraph createClassGraph(String packageNames) {
        return new ClassGraph().enableClassInfo().enableAnnotationInfo().acceptPackages(packageNames);
    }

    public static <T> void addVersion(T entity, ObjectVersionId versionUid) {
        Optional<Field> idField = Arrays.stream(FieldUtils.getAllFields(entity.getClass()))
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findAny();
        if (idField.isPresent()) {
            try {
                PropertyDescriptor propertyDescriptor =
                        new PropertyDescriptor(idField.get().getName(), entity.getClass());
                propertyDescriptor.getWriteMethod().invoke(entity, versionUid);
            } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
                throw new ClientException(e.getMessage(), e);
            }
        }
    }
}
