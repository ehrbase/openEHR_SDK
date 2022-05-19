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

import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import org.apache.commons.lang3.BooleanUtils;
import org.ehrbase.serialisation.walker.Context;

import java.util.Map;

public class DvIntervalPostprocessor extends AbstractMarshalPostprocessor<DvInterval> {

  /** {@inheritDoc} Adds the encoding information */
  @Override
  public void process(
      String term,
      DvInterval rmObject,
      Map<String, Object> values,
      Context<Map<String, Object>> context) {

    if (rmObject.getInterval() != null) {
      if (BooleanUtils.isTrue(rmObject.getInterval().isUpperUnbounded())) {
        addValue(values, term, "upper_unbounded", rmObject.getInterval().isUpperUnbounded());
      }
      if (BooleanUtils.isTrue(rmObject.getInterval().isLowerUnbounded())) {
        addValue(values, term, "lower_unbounded", rmObject.getInterval().isLowerUnbounded());
      }
      if (BooleanUtils.isFalse(rmObject.getInterval().isLowerIncluded())) {
        addValue(values, term, "lower_included", rmObject.getInterval().isLowerIncluded());
      }
      if (BooleanUtils.isFalse(rmObject.getInterval().isUpperIncluded())) {
        addValue(values, term, "upper_included", rmObject.getInterval().isUpperIncluded());
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<DvInterval> getAssociatedClass() {
    return DvInterval.class;
  }
}
