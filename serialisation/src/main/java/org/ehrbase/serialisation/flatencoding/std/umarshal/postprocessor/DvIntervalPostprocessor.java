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

import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.Map;
import java.util.Set;

public class DvIntervalPostprocessor extends AbstractUnmarshalPostprocessor<DvInterval> {

  /** {@inheritDoc} */
  @Override
  public void process(
      String term,
      DvInterval rmObject,
      Map<FlatPathDto, String> values,
      Set<String> consumedPaths,
      Context<Map<FlatPathDto, String>> context) {

    setValue(
        term, "lower_unbounded", values, rmObject::setLowerUnbounded, Boolean.class, consumedPaths);

    setValue(
        term, "upper_unbounded", values, rmObject::setUpperUnbounded, Boolean.class, consumedPaths);

    setValue(
        term, "lower_included", values, rmObject::setLowerIncluded, Boolean.class, consumedPaths);
    setValue(
        term, "upper_included", values, rmObject::setUpperIncluded, Boolean.class, consumedPaths);
  }

  /** {@inheritDoc} */
  @Override
  public Class<DvInterval> getAssociatedClass() {
    return DvInterval.class;
  }
}
