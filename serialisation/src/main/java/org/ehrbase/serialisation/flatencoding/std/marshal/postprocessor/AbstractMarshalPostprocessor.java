/*
 *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *
 *  This file is part of Project EHRbase
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.ehrbase.serialisation.flatencoding.std.marshal.postprocessor;

import com.nedap.archie.rm.RMObject;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.serialisation.flatencoding.std.marshal.StdFromCompositionWalker;
import org.ehrbase.serialisation.flatencoding.std.marshal.config.StdConfig;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.webtemplate.model.WebTemplateNode;

import java.util.Map;

import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

public abstract class AbstractMarshalPostprocessor<T extends RMObject>
    implements MarshalPostprocessor<T> {

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

  protected void handleRmAttribute(
      String currentTerm,
      RMObject rmObject,
      Map<String, Object> values,
      Context<Map<String, Object>> context,
      String attributeId) {

    callMarshal(
        currentTerm,
        "_" + attributeId,
        rmObject,
        values,
        context,
        context.getNodeDeque().peek().findChildById(attributeId).orElse(null));
    callPostprocess(
        currentTerm,
        "_" + attributeId,
        rmObject,
        values,
        context,
        context.getNodeDeque().peek().findChildById(attributeId).orElse(null));
  }

  protected void callMarshal(
      String term,
      String subTerm,
      RMObject rmObject,
      Map<String, Object> values,
      Context<Map<String, Object>> context,
      WebTemplateNode subNode) {

    if (subNode != null) {
      context.getNodeDeque().push(subNode);
    }
    String newTerm = term + PATH_DIVIDER + subTerm;

    values.putAll(
        ((StdConfig<RMObject>) StdFromCompositionWalker.findStdConfig(rmObject.getClass()))
            .buildChildValues(newTerm, rmObject, context));

    if (subNode != null) {
      context.getNodeDeque().poll();
    }
  }

  protected void callPostprocess(
      String term,
      String subTerm,
      RMObject rmObject,
      Map<String, Object> values,
      Context<Map<String, Object>> context,
      WebTemplateNode subNode) {

    if (subNode != null) {
      context.getNodeDeque().push(subNode);
    }

    final String newTerm;
    if (subTerm != null) {
      newTerm = term + PATH_DIVIDER + subTerm;
    } else {
      newTerm = term;
    }

    StdFromCompositionWalker.findPostprocessors(rmObject.getClass())
        .forEach(
            p -> ((MarshalPostprocessor<RMObject>) p).process(newTerm, rmObject, values, context));

    if (subNode != null) {
      context.getNodeDeque().poll();
    }
  }
}
