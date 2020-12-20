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

import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import java.util.HashMap;
import java.util.Map;
import org.ehrbase.serialisation.walker.Context;

public class DVProportionConfig extends AbstractsStdConfig<DvProportion> {

  /** {@inheritDoc} */
  @Override
  public Class<DvProportion> getAssociatedClass() {
    return DvProportion.class;
  }

  /** {@inheritDoc} */
  @Override
  public Map<String, Object> buildChildValues(
      String currentTerm, DvProportion rmObject, Context<Map<String, Object>> context) {
    Map<String, Object> result = new HashMap<>();
    addValue(result, currentTerm, "numerator", rmObject.getNumerator());
    addValue(result, currentTerm, "denominator", rmObject.getDenominator());
    addValue(result, currentTerm, "type", rmObject.getType());
    if (rmObject.getNumerator() != null && rmObject.getDenominator() != null) {
      addValue(result, currentTerm, null, rmObject.getNumerator() / rmObject.getDenominator());
    }

    return result;
  }
}
