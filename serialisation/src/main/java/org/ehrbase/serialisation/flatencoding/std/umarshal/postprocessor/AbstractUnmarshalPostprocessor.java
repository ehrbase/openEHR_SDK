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

package org.ehrbase.serialisation.flatencoding.std.umarshal.postprocessor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.RMObject;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.serialisation.flatencoding.std.umarshal.StdToCompositionWalker;
import org.ehrbase.serialisation.flatencoding.std.umarshal.rmunmarshaller.RMUnmarshaller;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import static org.ehrbase.serialisation.walker.FlatHelper.buildDummyChild;
import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

public abstract class AbstractUnmarshalPostprocessor<T extends RMObject>
    implements UnmarshalPostprocessor<T> {

  private static final ObjectMapper OBJECT_MAPPER = JacksonUtil.getObjectMapper();

  private final Logger log = LoggerFactory.getLogger(getClass());

  /**
   * Sets the {@code consumer} to the value in {@code values} corresponding to {@code term} and
   * {@code propertyName}
   *
   * @param <S>
   * @param term
   * @param propertyName
   * @param values
   * @param consumer
   * @param clazz
   * @param consumedPaths
   */
  protected <S> void setValue(
      String term,
      String propertyName,
      Map<FlatPathDto, String> values,
      Consumer<S> consumer,
      Class<S> clazz,
      Set<String> consumedPaths) {
    String key = propertyName != null ? term + "|" + propertyName : term;
    Map.Entry<FlatPathDto, String> entry = FlatPathDto.get(values, key);
    String jasonValue = entry.getValue();
    if (StringUtils.isNotBlank(jasonValue)) {
      try {
        S value = OBJECT_MAPPER.readValue(jasonValue, clazz);
        consumer.accept(value);
        consumedPaths.add(entry.getKey().format());
      } catch (JsonProcessingException e) {
        log.error(e.getMessage());
      }
    } else {

    }
  }

  protected void handleRmAttribute(
      String currentTerm,
      RMObject rmObject,
      Map<FlatPathDto, String> values,
      Set<String> consumedPaths,
      Context<Map<FlatPathDto, String>> context,
      String attributeId) {

    callUnmarshal(
        currentTerm,
        "_" + attributeId,
        rmObject,
        values,
        consumedPaths,
        context,
        context
            .getNodeDeque()
            .peek()
            .findChildById(attributeId)
            .orElse(buildDummyChild(attributeId, context.getNodeDeque().peek())));

    calPostProcess(
        currentTerm,
        "_" + attributeId,
        rmObject,
        values,
        consumedPaths,
        context,
        context
            .getNodeDeque()
            .peek()
            .findChildById(attributeId)
            .orElse(buildDummyChild(attributeId, context.getNodeDeque().peek())));
  }

  protected void callUnmarshal(
      String term,
      String subTerm,
      RMObject rmObject,
      Map<FlatPathDto, String> values,
      Set<String> consumedPaths,
      Context<Map<FlatPathDto, String>> context,
      WebTemplateNode subNode) {
    if (subNode != null) {
      context.getNodeDeque().push(subNode);
    }
    String newTerm = term + PATH_DIVIDER + subTerm;
    Map<FlatPathDto, String> subValues = FlatHelper.filter(values, newTerm, false);
    if (!subValues.isEmpty()) {
      ((RMUnmarshaller<RMObject>) StdToCompositionWalker.findRMUnmarshaller(rmObject.getClass()))
          .handle(newTerm, rmObject, subValues, context, consumedPaths);
    }

    if (subNode != null) {
      context.getNodeDeque().poll();
    }
  }

  protected void calPostProcess(
      String term,
      String subTerm,
      RMObject rmObject,
      Map<FlatPathDto, String> values,
      Set<String> consumedPaths,
      Context<Map<FlatPathDto, String>> context,
      WebTemplateNode subNode) {
    if (subNode != null) {
      context.getNodeDeque().push(subNode);
    }
    String newTerm = term + PATH_DIVIDER + subTerm;
    Map<FlatPathDto, String> subValues = FlatHelper.filter(values, newTerm, false);
    if (!subValues.isEmpty()) {
      StdToCompositionWalker.findUnmarshalPostprocessors(rmObject.getClass())
          .forEach(
              p ->
                  ((UnmarshalPostprocessor) p)
                      .process(newTerm, rmObject, subValues, consumedPaths, context));
    }

    if (subNode != null) {
      context.getNodeDeque().poll();
    }
  }
}
