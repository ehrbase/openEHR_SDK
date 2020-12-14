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

package org.ehrbase.client.flattener;

import com.google.common.reflect.TypeToken;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.support.identification.ObjectId;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.text.CaseUtils;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.EnumValueSet;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FromCompositionWalker;
import org.ehrbase.serialisation.walker.RmPrimitive;
import org.ehrbase.util.exception.SdkException;
import org.ehrbase.util.reflection.ReflectionHelper;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.parser.FlatPath;
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
      RMObject composition, DtoWithMatchingFields object, WebTemplateNode webTemplate) {
    dtoClassList = ReflectionHelper.findAll(object.getDto().getClass().getPackageName());
    super.walk(composition, object, webTemplate);
  }

  static Map<String, Field> buildFieldByPathMap(Class<?> clazz) {
    return Arrays.stream(clazz.getDeclaredFields())
        .filter(f -> f.isAnnotationPresent(Path.class))
        .collect(Collectors.toMap(f -> f.getAnnotation(Path.class).value(), Function.identity()));
  }

  @Override
  protected DtoWithMatchingFields extract(
      Context<DtoWithMatchingFields> context, WebTemplateNode child, boolean isChoice, Integer i) {
    Map<String, Field> subValues =
        context.getObjectDeque().peek().getFieldByPath().entrySet().stream()
            .map(
                e -> new ImmutablePair<>(PATH_MATCHER.matchesPath(context, child, e), e.getValue()))
            .filter(p -> p.getLeft() != null)
            .collect(Collectors.toMap(ImmutablePair::getLeft, ImmutablePair::getRight));

    if (subValues.isEmpty()) {
      return null;
    } else if (subValues.size() > 1) {
      if (isChoice && child.getRmType().equals("INTERVAL_EVENT")) {
        logger.warn("Path {} is choice but missing OptionFor", child.getAqlPath());
        return null;
      }
      return new DtoWithMatchingFields(context.getObjectDeque().peek().getDto(), subValues);
    } else {
      Field field = subValues.values().stream().findAny().orElseThrow();
      String path = subValues.keySet().stream().findAny().orElseThrow();
      Class<?> type = field.getType();
      if (List.class.isAssignableFrom(type)) {
        type =
            TypeToken.of(((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0])
                .getRawType();
      }
      if (isChoice) {
        type = findActual(type, child.getRmType()).orElseThrow();
      }
      if (type.isAnnotationPresent(Entity.class) && StringUtils.isBlank(path)) {
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
      PropertyDescriptor propertyDescriptor =
          new PropertyDescriptor(field.getName(), dto.getClass());
      Object dtoList = value;
      if (EnumValueSet.class.isAssignableFrom(field.getType())
          && value != null
          && CodePhrase.class.isAssignableFrom(value.getClass())) {
        CodePhrase codePhrase = (CodePhrase) value;
        EnumValueSet enumValueSet =
            Arrays.stream(field.getType().getEnumConstants())
                .map(o -> (EnumValueSet) o)
                .filter(
                    v -> {
                      String terminologyId =
                          Optional.ofNullable(codePhrase.getTerminologyId())
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
      PropertyDescriptor propertyDescriptor = new PropertyDescriptor(attributeName, dto.getClass());

      return propertyDescriptor.getReadMethod().invoke(dto);
    } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
      throw new ClientException(e.getMessage(), e);
    }
  }

  private Optional<? extends Class<?>> findActual(Class<?> actualTypeArgument, String simpleName) {
    return dtoClassList.stream()
        .filter(actualTypeArgument::isAssignableFrom)
        .filter(c -> c.isAnnotationPresent(OptionFor.class))
        .filter(c -> c.getAnnotation(OptionFor.class).value().equals(simpleName))
        .findAny();
  }

  @Override
  protected void preHandle(Context<DtoWithMatchingFields> context) {
    Map<String, Field> fieldByPath = context.getObjectDeque().peek().getFieldByPath();

    for (Map.Entry<String, Field> entry : fieldByPath.entrySet()) {
      FlatPath path = new FlatPath(entry.getKey());
      if (StringUtils.isBlank(path.getName())) {
        if (StringUtils.isNotBlank(path.getAttributeName())) {
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
