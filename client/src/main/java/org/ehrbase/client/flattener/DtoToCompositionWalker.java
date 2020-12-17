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

import com.nedap.archie.creation.RMObjectCreator;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.support.identification.TerminologyId;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMAttributeInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.EnumValueSet;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.ToCompositionWalker;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.parser.FlatPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DtoToCompositionWalker extends ToCompositionWalker<Map<String, Object>> {

  public static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

  private static final RMObjectCreator RM_OBJECT_CREATOR =
      new RMObjectCreator(ARCHIE_RM_INFO_LOOKUP);

  private static final PathMatcher PATH_MATCHER = new PathMatcher();

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  protected Map<String, Object> extract(
      Context<Map<String, Object>> context, WebTemplateNode child, boolean isChoice, Integer i) {

    Map<String, Object> subValues = filterValues(context, child);

    if (subValues.isEmpty()) {
      return null;
    } else if (subValues.size() > 1) {
      if (isChoice && child.getRmType().equals("INTERVAL_EVENT")) {
        logger.warn("Path {} is choice but missing OptionFor", child.getAqlPath());
        return null;
      }
      return subValues;
    } else {
      Object value = subValues.values().stream().findAny().orElseThrow();
      if (value instanceof List && i != null) {

        value = ((List<?>) value).size() > i ? ((List<?>) value).get(i) : null;
      }

      if (isChoice && value != null) {
        Optional<String> optionFor =
            Optional.of(value)
                .map(Object::getClass)
                .map(c -> c.getAnnotation(OptionFor.class))
                .map(OptionFor::value);
        if (optionFor.isEmpty()) {
          logger.warn(
              "Path {} is choice but {} is missing OptionFor",
              child.getAqlPath(),
              value.getClass().getSimpleName());
          if (child.getRmType().equals("INTERVAL_EVENT")) {
            value = null;
          }
        } else if (optionFor.filter(s -> s.equals(child.getRmType())).isEmpty()) {
          value = null;
        }
      }

      if (value == null) {
        return null;
      } else {
        String path = subValues.keySet().stream().findAny().orElseThrow();
        if (value.getClass().isAnnotationPresent(Entity.class)
            && new FlatPath(path).getPath().equals("")) {

          Map<String, Object> newValues =
              findEntity(value).entrySet().stream()
                  .collect(Collectors.toMap(e -> path + e.getKey(), Map.Entry::getValue));
          return !newValues.isEmpty() ? newValues : null;
        } else {
          return Map.of(path, value);
        }
      }
    }
  }

  private Map<String, Object> filterValues(
      Context<Map<String, Object>> context, WebTemplateNode child) {
    return context.getObjectDeque().peek().entrySet().stream()
        .map(e -> new ImmutablePair<>(PATH_MATCHER.matchesPath(context, child, e), e.getValue()))
        .filter(p -> p.getLeft() != null)
        .collect(Collectors.toMap(ImmutablePair::getLeft, ImmutablePair::getRight));
  }

  @Override
  protected void preHandle(Context<Map<String, Object>> context) {

    Map<String, Object> values = context.getObjectDeque().peek();

    for (Map.Entry<String, Object> objectEntry : values.entrySet()) {

      FlatPath flatPath = new FlatPath(objectEntry.getKey());
      if (StringUtils.isBlank(flatPath.getPath())) {
        if ("uuid".equals(flatPath.getAttributeName())) {
          // NOP
        } else if (StringUtils.isNotBlank(flatPath.getAttributeName())) {
          handleSingleValue(
              objectEntry.getValue(),
              flatPath.getAttributeName(),
              null,
              context.getRmObjectDeque().peek());
        } else {
          RMObject child = context.getRmObjectDeque().poll();
          handleSingleValue(
              objectEntry.getValue(),
              new FlatPath(context.getNodeDeque().peek().getAqlPath()).getLast().getName(),
              child,
              context.getRmObjectDeque().peek());
          context.getRmObjectDeque().push(child);
        }
      }
    }
  }

  @Override
  protected void postHandle(Context<Map<String, Object>> context) {
    // NOP
  }

  @Override
  protected int calculateSize(Context<Map<String, Object>> context, WebTemplateNode childNode) {

    Map<String, Object> values = filterValues(context, childNode);
    if (values.size() == 1) {
      Object value = values.values().stream().findAny().orElseThrow();
      if (value instanceof List) {
        return ((List<?>) value).size();
      } else {
        return 1;
      }
    }
    return 0;
  }

  static Map<String, Object> findEntity(Object dto) {

    return Arrays.stream(dto.getClass().getDeclaredFields())
        .filter(m -> m.isAnnotationPresent(Path.class))
        .filter(m -> readField(m, dto) != null)
        .collect(
            Collectors.toMap(m -> m.getAnnotation(Path.class).value(), m -> readField(m, dto)));
  }

  private static Object readField(Field field, Object dto) {
    try {
      PropertyDescriptor propertyDescriptor =
          new PropertyDescriptor(field.getName(), dto.getClass());
      return propertyDescriptor.getReadMethod().invoke(dto);
    } catch (InvocationTargetException | IllegalAccessException | IntrospectionException e) {
      throw new ClientException(e.getMessage(), e);
    }
  }

  private void handleSingleValue(Object value, String childName, Object child, Object parent) {

    if (value == null) {
      // NOP
    } else if (EnumValueSet.class.isAssignableFrom(value.getClass())
        && DvCodedText.class.isAssignableFrom(parent.getClass())) {
      EnumValueSet valueSet = (EnumValueSet) value;
      DvCodedText dvCodedText = (DvCodedText) parent;
      dvCodedText.setValue(valueSet.getValue());
      dvCodedText.setDefiningCode(
          new CodePhrase(new TerminologyId(valueSet.getTerminologyId()), valueSet.getCode()));
    } else if (EnumValueSet.class.isAssignableFrom(value.getClass())
        && DvCodedText.class.isAssignableFrom(
            ARCHIE_RM_INFO_LOOKUP.getAttributeInfo(parent.getClass(), childName).getType())) {
      EnumValueSet valueSet = (EnumValueSet) value;
      DvCodedText dvCodedText = new DvCodedText();
      dvCodedText.setValue(valueSet.getValue());
      dvCodedText.setDefiningCode(
          new CodePhrase(new TerminologyId(valueSet.getTerminologyId()), valueSet.getCode()));
      RM_OBJECT_CREATOR.set(parent, childName, Collections.singletonList(dvCodedText));
    } else if (EnumValueSet.class.isAssignableFrom(value.getClass())) {
      EnumValueSet valueSet = (EnumValueSet) value;
      CodePhrase codePhrase =
          new CodePhrase(new TerminologyId(valueSet.getTerminologyId()), valueSet.getCode());
      RM_OBJECT_CREATOR.set(parent, childName, Collections.singletonList(codePhrase));
    } else if (ARCHIE_RM_INFO_LOOKUP
        .getAttributeInfo(parent.getClass(), childName)
        .getTypeInCollection()
        .isAssignableFrom(value.getClass())) {
      RMAttributeInfo attributeInfo =
          ARCHIE_RM_INFO_LOOKUP.getAttributeInfo(parent.getClass(), childName);
      if (attributeInfo.isMultipleValued()) {
        try {
          Object invoke = attributeInfo.getGetMethod().invoke(parent);
          if (Collection.class.isAssignableFrom(invoke.getClass()) && child != null) {
            ((Collection) invoke).remove(child);
          }
        } catch (IllegalAccessException | InvocationTargetException e) {
          logger.warn(e.getMessage(), e);
        }
      }
      RM_OBJECT_CREATOR.addElementToListOrSetSingleValues(
          parent, childName, Collections.singletonList(value));
    } else {
      logger.warn("Unhandled child {} in {}", childName, parent);
    }
  }
}
