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

package org.ehrbase.client.std.marshal.config;

import com.nedap.archie.rm.RMObject;
import org.ehrbase.client.classgenerator.config.RmClassGeneratorConfig;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.reflection.ReflectionHelper;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public abstract class AbstractsStdConfig<T extends RMObject> implements StdConfig<T> {
    private static final Map<Class<?>, RmClassGeneratorConfig> configMap = ReflectionHelper.buildMap(RmClassGeneratorConfig.class);

    @Override
    /**
     * {@inheritDoc}
     */
    public Map<String, Object> buildChildValues(String currentTerm, T rmObject) {
        Map<String, Object> result = new HashMap<>();
        RmClassGeneratorConfig rmClassGeneratorConfig = configMap.get(rmObject.getClass());
        if (rmClassGeneratorConfig != null && rmClassGeneratorConfig.isExpandField()) {

            Set<String> expandFields = rmClassGeneratorConfig.getExpandFields();
            if (expandFields.size() == 1 && expandFields.contains("value")) {

                try {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor("value", rmObject.getClass());
                    Object property = propertyDescriptor.getReadMethod().invoke(rmObject);
                    result.put(currentTerm, property);
                } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
                    throw new ClientException(e.getMessage(), e);
                }

            } else {
                for (String propertyName : expandFields) {
                    try {
                        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, rmObject.getClass());
                        Object property = propertyDescriptor.getReadMethod().invoke(rmObject);
                        result.put(currentTerm + "|" + propertyName, property);
                    } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
                        throw new ClientException(e.getMessage(), e);
                    }
                }
            }

        } else {
            result.put(currentTerm, rmObject);
        }
        return result;
    }


    protected void addValue(Map<String, Object> result, String termLoop, String propertyName, String value) {
        if (value != null) {
            result.put(termLoop + "|" + propertyName, value);
        }
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public List<Integer> valueCount(Class<T> clazz) {
        RmClassGeneratorConfig rmClassGeneratorConfig = configMap.get(clazz);
        return Collections.singletonList(Optional.ofNullable(rmClassGeneratorConfig).map(RmClassGeneratorConfig::getExpandFields).map(Set::size).orElse(1));
    }
}
