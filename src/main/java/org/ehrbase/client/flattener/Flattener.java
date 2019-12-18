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

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.support.identification.ObjectId;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMTypeInfo;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.EnumValueSet;
import org.ehrbase.client.exception.ClientException;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
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
    private Reflections reflections;


    public <T> T flatten(Locatable locatable, Class<T> clazz) {
        reflections = new Reflections(clazz.getPackage().getName());
        T dto = createInstance(clazz);
        mapEntityToDto(locatable, dto);
        return dto;
    }

    private <T> void mapEntityToDto(RMObject locatable, T dto) {
        Map<String, Field> fieldMap = buildFieldMap(dto);
        fieldMap.forEach((key, value) -> setFieldFromPath(dto, locatable, key, value));
    }

    private void setFieldFromPath(Object dto, RMObject locatable, String path, Field field) {

        ItemExtractor itemExtractor = new ItemExtractor(locatable, path);
        Object child = itemExtractor.getChild();

        if (List.class.isAssignableFrom(field.getType())) {

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
            Class<?> aClass = ReflectionUtils.forName(actualTypeArgument.getTypeName(), this.getClass().getClassLoader());
            for (Object childItem : childList) {

                if (aClass.isAnnotationPresent(Entity.class)) {

                    Object dtoItem = createInstance(aClass);
                    mapEntityToDto((RMObject) childItem, dtoItem);
                    dtoList.add(dtoItem);
                } else if (aClass.isAssignableFrom(child.getClass())) {

                    dtoList.add(child);
                } else {
                    logger.warn("Incompatible Typ {} {}", aClass, child.getClass());
                }

                writeField(field, dto, dtoList);
            }
        } else {
            handleSingleField(dto, field, child);
        }

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
            Class<?> type = reflections.getSubTypesOf(fieldType)
                    .stream()
                    .filter(c -> c.isAnnotationPresent(OptionFor.class))
                    .filter(c -> c.getAnnotation(OptionFor.class).value().equals(simpleName))
                    .findAny()
                    .orElse(null);
            if (type != null) {
                fieldType = type;
            } else {
                logger.warn("No implementation of {} for {}", fieldType, simpleName);
            }
        }

        if (fieldType.isAnnotationPresent(Entity.class)) {

            Object subDto = createInstance(fieldType);
            mapEntityToDto((RMObject) child, subDto);
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


    private static <T> T createInstance(Class<T> aClass) {
        try {
            return aClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

}
