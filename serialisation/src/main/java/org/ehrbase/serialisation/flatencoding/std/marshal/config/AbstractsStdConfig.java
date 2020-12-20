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

package org.ehrbase.serialisation.flatencoding.std.marshal.config;

import com.nedap.archie.rm.RMObject;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.util.exception.SdkException;
import org.ehrbase.util.reflection.ReflectionHelper;
import org.ehrbase.webtemplate.parser.config.RmIntrospectConfig;

public abstract class AbstractsStdConfig<T extends RMObject> implements StdConfig<T> {
  private static final Map<Class<?>, RmIntrospectConfig> configMap =
      ReflectionHelper.buildMap(RmIntrospectConfig.class);

  @Override
  /** {@inheritDoc} */
  public Map<String, Object> buildChildValues(
      String currentTerm, T rmObject, Context<Map<String, Object>> context) {
    Map<String, Object> result = new HashMap<>();

    Set<String> expandFields =
        Optional.ofNullable(configMap.get(rmObject.getClass()))
            .map(RmIntrospectConfig::getNonTemplateFields)
            .orElse(Collections.emptySet());
    if (!expandFields.isEmpty()) {

      if (expandFields.size() == 1 && expandFields.contains("value")) {

        try {
          PropertyDescriptor propertyDescriptor =
              new PropertyDescriptor("value", rmObject.getClass());
          Object property = propertyDescriptor.getReadMethod().invoke(rmObject);
          result.put(currentTerm, property);
        } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
          throw new SdkException(e.getMessage(), e);
        }

      } else {
        for (String propertyName : expandFields) {
          try {
            PropertyDescriptor propertyDescriptor =
                new PropertyDescriptor(propertyName, rmObject.getClass());
            Object property = propertyDescriptor.getReadMethod().invoke(rmObject);
            result.put(currentTerm + "|" + propertyName, property);
          } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
            throw new SdkException(e.getMessage(), e);
          }
        }
      }

    } else {
      result.put(currentTerm, rmObject);
    }
    return result;
  }

  protected void addValue(
      Map<String, Object> result, String termLoop, String propertyName, Object value) {
    if (value != null) {
      String key;
      if (StringUtils.isNotBlank(propertyName)) {
        key = termLoop + "|" + propertyName;
      } else {
        key = termLoop;
      }
      result.put(key, value);
    }
  }
}
