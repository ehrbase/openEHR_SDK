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

import com.nedap.archie.rm.datavalues.DvEHRURI;
import org.ehrbase.serialisation.walker.Context;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DVEHRUriConfig extends AbstractsStdConfig<DvEHRURI> {

  /** {@inheritDoc} */
  @Override
  public Class<DvEHRURI> getAssociatedClass() {
    return DvEHRURI.class;
  }

  /** {@inheritDoc} */
  @Override
  public Map<String, Object> buildChildValues(
      String currentTerm, DvEHRURI rmObject, Context<Map<String, Object>> context) {
    Map<String, Object> result = new HashMap<>();

    addValue(
        result,
        currentTerm,
        null,
        Optional.of(rmObject).map(DvEHRURI::getValue).map(URI::toString).orElse(null));

    return result;
  }
}
