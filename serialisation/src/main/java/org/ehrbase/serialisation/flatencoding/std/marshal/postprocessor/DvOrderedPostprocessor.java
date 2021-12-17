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

package org.ehrbase.serialisation.flatencoding.std.marshal.postprocessor;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rm.datavalues.quantity.DvOrdered;
import com.nedap.archie.rm.datavalues.quantity.ReferenceRange;
import org.ehrbase.serialisation.flatencoding.std.marshal.config.StdConfig;
import org.ehrbase.serialisation.walker.Context;

import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.ehrbase.serialisation.flatencoding.std.marshal.StdFromCompositionWalker.findStdConfig;

public class DvOrderedPostprocessor extends AbstractMarshalPostprocessor<DvOrdered> {

  /** {@inheritDoc} Adds the encoding information */
  @Override
  public void process(
      String term,
      DvOrdered rmObject,
      Map<String, Object> values,
      Context<Map<String, Object>> context) {

    if (rmObject.getNormalRange() != null) {
      handleRange(term + "/_normal_range", values, rmObject.getNormalRange(), context);
    }

    IntStream.range(0, rmObject.getOtherReferenceRanges().size())
        .forEach(
            i -> {
              ReferenceRange referenceRange =
                  (ReferenceRange) rmObject.getOtherReferenceRanges().get(i);

              if (referenceRange.getMeaning() != null) {

                callMarshal(
                    term,
                    "_other_reference_ranges:" + i + "/meaning",
                    referenceRange.getMeaning(),
                    values,
                    context,
                    context.getNodeDeque().peek().findChildById("meaning").orElse(null));
                callPostprocess(
                    term,
                    "_other_reference_ranges:" + i + "/meaning",
                    referenceRange.getMeaning(),
                    values,
                    context,
                    context.getNodeDeque().peek().findChildById("meaning").orElse(null));
              }

              if (referenceRange.getRange() != null) {
                handleRange(
                    term + "/_other_reference_ranges:" + i,
                    values,
                    referenceRange.getRange(),
                    context);
              }
            });

    addValue(
        values,
        term,
        "normal_status",
        Optional.ofNullable(rmObject.getNormalStatus())
            .map(CodePhrase::getCodeString)
            .orElse(null));
  }

  private void handleRange(
      String rangeTerm,
      Map<String, Object> values,
      DvInterval range,
      Context<Map<String, Object>> context) {
    if (range.getLower() != null) {
      DvOrdered lower = range.getLower();

      values.putAll(
          ((StdConfig) findStdConfig(lower.getClass()))
              .buildChildValues(rangeTerm + "/lower", lower, context));
    }

    if (range.getUpper() != null) {
      DvOrdered lower = range.getUpper();

      values.putAll(
          ((StdConfig) findStdConfig(lower.getClass()))
              .buildChildValues(rangeTerm + "/upper", lower, context));
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<DvOrdered> getAssociatedClass() {
    return DvOrdered.class;
  }
}
