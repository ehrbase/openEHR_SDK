/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.serialisation.dto;

import com.google.common.reflect.TypeToken;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.support.identification.ObjectId;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.text.CaseUtils;
import org.ehrbase.openehr.sdk.aql.webtemplatepath.AqlPath;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.OptionFor;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.EnumValueSet;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;
import org.ehrbase.openehr.sdk.serialisation.walker.FromCompositionWalker;
import org.ehrbase.openehr.sdk.serialisation.walker.RmPrimitive;
import org.ehrbase.openehr.sdk.util.exception.ClientException;
import org.ehrbase.openehr.sdk.util.exception.SdkException;
import org.ehrbase.openehr.sdk.util.reflection.ReflectionHelper;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DtoFromCompositionWalker extends FromCompositionWalker<DtoWithMatchingFields> {

    private static final PathMatcher PATH_MATCHER = new PathMatcher();

    private Logger logger = LoggerFactory.getLogger(getClass());

    static <T> T create(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            throw new SdkException(e.getMessage(), e);
        }
    }

    private List<Class<?>> dtoClassList;

    @Override
    public void walk(
            RMObject composition, DtoWithMatchingFields object, WebTemplateNode webTemplate, String templateId) {
        dtoClassList = ReflectionHelper.findAll(
                ReflectionHelper.findRootClass(object.getDto().getClass()).getPackageName());
        super.walk(composition, object, webTemplate, templateId);
    }

    static Map<AqlPath, Field> buildFieldByPathMap(Class<?> clazz) {
        return Arrays.stream(FieldUtils.getAllFields(clazz))
                .filter(f -> f.isAnnotationPresent(Path.class))
                .collect(Collectors.toMap(
                        f -> AqlPath.parse(f.getAnnotation(Path.class).value()), Function.identity()));
    }

    @Override
    protected DtoWithMatchingFields extract(
            Context<DtoWithMatchingFields> context, WebTemplateNode child, boolean isChoice, Integer i) {
        Map<AqlPath, Field> subValues = context.getObjectDeque().peek().getFieldByPath().entrySet().stream()
                .map(e -> ImmutablePair.of(PATH_MATCHER.matchesPath(context, child, e), e.getValue()))
                .filter(p -> Objects.nonNull(p.getKey()))
                .collect(Collectors.toMap(ImmutablePair::getKey, ImmutablePair::getValue));

        if (subValues.isEmpty()) {
            boolean isKnownProperty = Set.of(
                            "name",
                            "archetype_node_id",
                            "encoding",
                            "archetype_details",
                            "uid",
                            "lower_unbounded",
                            "upper_unbounded")
                    .contains(child.getAqlPathDto().getLastNode().getName());
            if (!isKnownProperty) {
                logger.warn(
                        "No Field in dto {} for path {}",
                        context.getObjectDeque().peek().getDto().getClass().getSimpleName(),
                        child);
            }
            return null;
        } else if (subValues.size() > 1) {
            if (isChoice && child.getRmType().equals("INTERVAL_EVENT")) {
                logger.warn(
                        "Path {} is choice but missing OptionFor: Transforming INTERVAL_EVENT to POINT_EVENT ",
                        child.getAqlPath());
            }
            return new DtoWithMatchingFields(context.getObjectDeque().peek().getDto(), subValues);
        } else {
            Field field = subValues.values().stream().findAny().orElseThrow();
            AqlPath path = subValues.keySet().stream().findAny().orElseThrow();
            Class<?> type = field.getType();
            if (List.class.isAssignableFrom(type)) {
                type = TypeToken.of(((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0])
                        .getRawType();
            }
            if (isChoice) {
                type = findActual(type, child.getRmType()).orElseThrow();
            }
            if (type.isAnnotationPresent(Entity.class) && path.isEmpty()) {
                Object dto = create(type);

                writeField(field, context.getObjectDeque().peek().getDto(), dto);
                return new DtoWithMatchingFields(dto, buildFieldByPathMap(type));
            } else {
                return new DtoWithMatchingFields(context.getObjectDeque().peek().getDto(), subValues);
            }
        }
    }

    private void writeField(Field field, Object dto, Object value) {

        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), dto.getClass());
            Object dtoList = value;
            if (EnumValueSet.class.isAssignableFrom(field.getType())
                    && value != null
                    && CodePhrase.class.isAssignableFrom(value.getClass())) {
                CodePhrase codePhrase = (CodePhrase) value;
                EnumValueSet enumValueSet = Arrays.stream(field.getType().getEnumConstants())
                        .map(EnumValueSet.class::cast)
                        .filter(v -> {
                            String terminologyId = Optional.ofNullable(codePhrase.getTerminologyId())
                                    .map(ObjectId::getValue)
                                    .orElse(null);
                            return v.getTerminologyId().equals(terminologyId);
                        })
                        .filter(v -> v.getCode().equals(codePhrase.getCodeString()))
                        .findAny()
                        .orElse(null);
                dtoList = enumValueSet;
            }
            if (dtoList instanceof RmPrimitive) {
                dtoList = ((RmPrimitive<?>) dtoList).getValue();
            }
            if (List.class.isAssignableFrom(field.getType())) {
                dtoList = propertyDescriptor.getReadMethod().invoke(dto);
                if (dtoList == null) {
                    dtoList = new ArrayList<>();
                }
                ((List) dtoList).add(value);
            }
            propertyDescriptor.getWriteMethod().invoke(dto, dtoList);
        } catch (IllegalAccessException
                | InvocationTargetException
                | IntrospectionException
                | IllegalArgumentException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    private Object extractAttribute(Object dto, String attributeName) {
        try {
            for (PropertyDescriptor p : Introspector.getBeanInfo(dto.getClass()).getPropertyDescriptors()) {
                if (p.getName().equals(attributeName)) {
                    return p.getReadMethod().invoke(dto);
                }
            }
            throw new ClientException("Unknown property %s for type %s".formatted(attributeName, dto.getClass()));
        } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    private Optional<Class<?>> findActual(Class<?> actualTypeArgument, String simpleName) {
        Optional<Class<?>> aClass = dtoClassList.stream()
                .filter(actualTypeArgument::isAssignableFrom)
                .filter(c -> c.isAnnotationPresent(OptionFor.class))
                .filter(c -> c.getAnnotation(OptionFor.class).value().equals(simpleName))
                .findAny();
        if (aClass.isEmpty() && !actualTypeArgument.isInterface()) {
            return Optional.of(actualTypeArgument);
        }
        return aClass;
    }

    @Override
    protected void preHandle(Context<DtoWithMatchingFields> context) {
        Map<AqlPath, Field> fieldByPath = context.getObjectDeque().peek().getFieldByPath();

        for (Map.Entry<AqlPath, Field> entry : fieldByPath.entrySet()) {
            AqlPath path = entry.getKey();
            if (!path.hasPath()) {
                if (path.getAttributeName() != null) {
                    writeField(
                            entry.getValue(),
                            context.getObjectDeque().peek().getDto(),
                            extractAttribute(
                                    context.getRmObjectDeque().peek(),
                                    CaseUtils.toCamelCase(path.getAttributeName(), false, '_')));
                } else {
                    writeField(
                            entry.getValue(),
                            context.getObjectDeque().peek().getDto(),
                            context.getRmObjectDeque().peek());
                }
            }
        }
    }

    @Override
    protected void postHandle(Context<DtoWithMatchingFields> context) {
        // NOP
    }
}
