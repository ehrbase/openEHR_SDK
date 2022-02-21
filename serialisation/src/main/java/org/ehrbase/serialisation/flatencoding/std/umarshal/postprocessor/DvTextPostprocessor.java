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

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.TermMapping;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.Map;
import java.util.Set;

import static org.ehrbase.serialisation.walker.FlatHelper.buildDummyChild;

public class DvTextPostprocessor extends AbstractUnmarshalPostprocessor<DvText> {

  /** {@inheritDoc} */
  @Override
  public void process(
      String term,
      DvText rmObject,
      Map<FlatPathDto, String> values,
      Set<String> consumedPaths,
      Context<Map<FlatPathDto, String>> context) {

    FlatHelper.extractMultiValued(term, "_mapping", values).entrySet().stream()
        .map(
            e -> {
              TermMapping termMapping = new TermMapping();
              callUnmarshal(
                  term,
                  "/_mapping:" + e.getKey(),
                  termMapping,
                  e.getValue(),
                  consumedPaths,
                  context,
                  context
                      .getNodeDeque()
                      .peek()
                      .findChildById("mappings")
                      .orElse(buildDummyChild("mappings", context.getNodeDeque().peek())));
              callPostProcess(
                  term,
                  "/_mapping:" + e.getKey(),
                  termMapping,
                  e.getValue(),
                  consumedPaths,
                  context,
                  context
                      .getNodeDeque()
                      .peek()
                      .findChildById("mappings")
                      .orElse(buildDummyChild("mappings", context.getNodeDeque().peek())));

              return termMapping;
            })
        .forEach(rmObject::addMapping);

    FlatHelper.consumeAllMatching(term + "/_mapping", values, consumedPaths, false);

    Map<FlatPathDto, String> language = FlatHelper.filter(values, term + "/_language", false);

    if (!language.isEmpty()) {
      rmObject.setLanguage(new CodePhrase());
      handleRmAttribute(term, rmObject.getLanguage(), language, consumedPaths, context, "language");
    }

    Map<FlatPathDto, String> encoding = FlatHelper.filter(values, term + "/_encoding", false);

    if (!encoding.isEmpty()) {
      rmObject.setEncoding(new CodePhrase());
      handleRmAttribute(term, rmObject.getEncoding(), encoding, consumedPaths, context, "encoding");
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<DvText> getAssociatedClass() {
    return DvText.class;
  }
}
