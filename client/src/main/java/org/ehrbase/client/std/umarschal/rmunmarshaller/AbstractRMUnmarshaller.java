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

package org.ehrbase.client.std.umarschal.rmunmarshaller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.RMObject;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.client.classgenerator.config.RmClassGeneratorConfig;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class AbstractRMUnmarshaller<T extends RMObject> implements RMUnmarshaller<T> {

    private static final ObjectMapper OBJECT_MAPPER = JacksonUtil.getObjectMapper();
    private static final Map<Class, RmClassGeneratorConfig> configMap = buildConfigMap();
    private final Logger log = LoggerFactory.getLogger(getClass());

    protected final Set<String> consumedPath = new HashSet<>();


    private static Map<Class, RmClassGeneratorConfig> buildConfigMap() {
        Reflections reflections = new Reflections(RmClassGeneratorConfig.class.getPackage().getName());
        Set<Class<? extends RmClassGeneratorConfig>> configs = reflections.getSubTypesOf(RmClassGeneratorConfig.class);

        return configs.stream().map(c -> {
            try {
                return c.getConstructor().newInstance();
            } catch (Exception e) {
                throw new ClientException(e.getMessage(), e);
            }
        }).collect(Collectors.toMap(RmClassGeneratorConfig::getRMClass, c -> c));
    }

    @Override
    public void handle(String termLoop, T child, Map<String, String> values) {

        RmClassGeneratorConfig rmClassGeneratorConfig = configMap.get(child.getClass());
        if (rmClassGeneratorConfig != null && rmClassGeneratorConfig.isExpandField()) {

            Set<String> expandFields = rmClassGeneratorConfig.getExpandFields();
            if (expandFields.size() == 1 && expandFields.contains("value")) {

                try {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor("value", child.getClass());

                    setValue(termLoop, null, values, s -> {
                        try {
                            propertyDescriptor.getWriteMethod().invoke(child, s);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new ClientException(e.getMessage(), e);
                        }
                    }, propertyDescriptor.getPropertyType());
                } catch (IntrospectionException e) {
                    throw new ClientException(e.getMessage(), e);
                }

            } else {
                for (String propertyName : expandFields) {
                    try {
                        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, child.getClass());
                        setValue(termLoop, propertyName, values, s -> {
                            try {
                                propertyDescriptor.getWriteMethod().invoke(child, s);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                throw new ClientException(e.getMessage(), e);
                            }
                        }, propertyDescriptor.getPropertyType());
                    } catch (IntrospectionException e) {
                        throw new ClientException(e.getMessage(), e);
                    }
                }
            }

        }
    }

    protected <S> void setValue(String term, String propertyName, Map<String, String> values, Consumer<S> consumer, Class<S> clazz) {
        String key = propertyName != null ? term + "|" + propertyName : term;
        String jasonValue = values.get(key);
        if (StringUtils.isNotBlank(jasonValue)) {
            try {
                S value = OBJECT_MAPPER.readValue(jasonValue, clazz);
                consumer.accept(value);
                consumedPath.add(key);
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
            }
        } else {
            consumer.accept(null);
            consumedPath.add(key);
        }
    }

    @Override
    public Set<String> getConsumedPaths() {
        return consumedPath;
    }
}
