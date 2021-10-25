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

import com.nedap.archie.rm.archetyped.FeederAuditDetails;
import org.ehrbase.serialisation.walker.Context;

import java.util.HashMap;
import java.util.Map;

public class FeederAudiDetailsStdConfig extends AbstractsStdConfig<FeederAuditDetails> {

  @Override
  /** {@inheritDoc} */
  public Map<String, Object> buildChildValues(
      String currentTerm, FeederAuditDetails rmObject, Context<Map<String, Object>> context) {
    Map<String, Object> result = new HashMap<>();

    addValue(result, currentTerm, "system_id", rmObject.getSystemId());
    addValue(result, currentTerm, "version_id", rmObject.getVersionId());

    return result;
  }

  @Override
  /** {@inheritDoc} */
  public Class<FeederAuditDetails> getAssociatedClass() {
    return FeederAuditDetails.class;
  }
}
