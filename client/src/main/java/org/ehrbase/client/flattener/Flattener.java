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

package org.ehrbase.client.flattener;

import com.google.common.reflect.TypeToken;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.support.identification.ObjectId;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMTypeInfo;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.EnumValueSet;
import org.ehrbase.client.exception.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class Flattener {

    private static final ArchieRMInfoLookup RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ScanResult classgraph;

    private static <T> T createInstance(Class<T> aClass) {
        try {
            return aClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    public <T> T flatten(RMObject locatable, Class<T> clazz) {
        try {
            classgraph = new ClassGraph().enableAllInfo().whitelistPackages(clazz.getPackageName()).scan();

            T dto = createInstance(clazz);
            mapEntityToDto(locatable, dto);
            return dto;
        } finally {
            classgraph.close();
        }
    }

    private <T> void mapEntityToDto(RMObject locatable, T dto) {
        Map<String, Field> fieldMap = buildFieldMap(dto);
        fieldMap.forEach((key, value) -> setFieldFromPath(dto, locatable, key, value));
    }

    private void setFieldFromPath(Object dto, RMObject locatable, String path, Field field) {
        boolean multi = List.class.isAssignableFrom(field.getType());
        ItemExtractor itemExtractor = new ItemExtractor(locatable, path, multi);
        Object child = itemExtractor.getChild();


        if (multi) {

            final List<Object> childList;

            if (child == null) {
                childList = Collections.emptyList();
            } else if (List.class.isAssignableFrom(child.getClass())) {
                childList = (List<Object>) child;
            } else {
                childList = Collections.singletonList(child);
            }

            List<Object> dtoList = new ArrayList<>();

            Type actualTypeArgument = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];

            Class<?> aClass = TypeToken.of(actualTypeArgument).getRawType();

            for (Object childItem : childList) {

                if (aClass.isAnnotationPresent(Entity.class)) {

                    Object dtoItem = createInstance(aClass);
                    mapEntityToDto((RMObject) childItem, dtoItem);
                    dtoList.add(dtoItem);
                } else if (field.isAnnotationPresent(Choice.class)) {
                    String simpleName = Optional.ofNullable(childItem).map(Object::getClass).map(RM_INFO_LOOKUP::getTypeInfo).map(RMTypeInfo::getRmName).orElse("");
                    Class<?> type = findActual((Class<?>) actualTypeArgument, simpleName)
                            .orElseThrow(() -> new ClientException(String.format("No Option for %s ", simpleName)));
                    Object dtoItem = createInstance(type);
                    mapEntityToDto((RMObject) childItem, dtoItem);
                    dtoList.add(dtoItem);
                } else if (aClass.isAssignableFrom(childItem.getClass())) {

                    dtoList.add(childItem);
                } else {
                    logger.warn("Incompatible Typ {} {}", aClass, child != null ? child.getClass() : "null");
                }

            }
            writeField(field, dto, dtoList);
        } else {
            if (child == null)  // field is done
                return;
            handleSingleField(dto, field, child);
        }

    }


    private Optional<? extends Class<?>> findActual(Class<?> actualTypeArgument, String simpleName) {
        return classgraph.getClassesImplementing(actualTypeArgument.getName())
                .stream()
                .map(ClassInfo::loadClass)
                .map(c -> (Class<?>) c)
                .filter(c -> c.isAnnotationPresent(OptionFor.class))
                .filter(c -> c.getAnnotation(OptionFor.class).value().equals(simpleName))
                .findAny();
    }

    private void writeField(Field field, Object dto, Object dtoList) {

        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), dto.getClass());
            propertyDescriptor.getWriteMethod().invoke(dto, dtoList);
        } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    private void handleSingleField(Object dto, Field field, Object child) {

        Class<?> fieldType = field.getType();

        if (field.isAnnotationPresent(Choice.class)) {
            String simpleName = Optional.ofNullable(child).map(Object::getClass).map(RM_INFO_LOOKUP::getTypeInfo).map(RMTypeInfo::getRmName).orElse("");
            Class<?> type = findActual(fieldType, simpleName)
                    .orElse(null);
            if (type != null) {
                fieldType = type;
            } else {
                logger.warn("No implementation of {} for {}", fieldType, simpleName);
            }
        }

        if (fieldType.isAnnotationPresent(Entity.class)) {

            Object subDto = createInstance(fieldType);
            try {

                mapEntityToDto((RMObject) child, subDto);
            } catch (RuntimeException e) {
                throw e;
            }
            writeField(field, dto, subDto);

        } else if (EnumValueSet.class.isAssignableFrom(fieldType) && child != null && CodePhrase.class.isAssignableFrom(child.getClass())) {
            CodePhrase codePhrase = (CodePhrase) child;
            EnumValueSet enumValueSet = Arrays.stream(fieldType.getEnumConstants())
                    .map(o -> (EnumValueSet) o)
                    .filter(v -> {
                        String terminologyId = Optional.ofNullable(codePhrase.getTerminologyId())
                                .map(ObjectId::getValue)
                                .orElse(null);
                        return v.getTerminologyId().equals(terminologyId);
                    })
                    .filter(v -> v.getCode().equals(codePhrase.getCodeString()))
                    .findAny()
                    .orElse(null);
            writeField(field, dto, enumValueSet);
        } else if (child == null || fieldType.isAssignableFrom(child.getClass())) {

            writeField(field, dto, child);
        } else {
            logger.warn("Incompatible Typ {} {}", fieldType, child.getClass());
        }

    }

    private Map<String, Field> buildFieldMap(Object dto) {
        Map<String, Field> fieldMap = new HashMap<>();

        for (Field field : dto.getClass().getDeclaredFields()) {
            Path path = field.getAnnotation(Path.class);
            if (path != null) {
                fieldMap.put(path.value(), field);
            }
        }
        return fieldMap;
    }

}
