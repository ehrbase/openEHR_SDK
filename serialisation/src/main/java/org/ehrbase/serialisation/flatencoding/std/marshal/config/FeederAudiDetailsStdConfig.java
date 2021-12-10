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
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import org.ehrbase.serialisation.walker.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FeederAudiDetailsStdConfig extends AbstractsStdConfig<FeederAuditDetails> {

  private static final PartyIdentifiedStdConfig PARTY_IDENTIFIED_STD_CONFIG =
      new PartyIdentifiedStdConfig();

  @Override
  /** {@inheritDoc} */
  public Map<String, Object> buildChildValues(
      String currentTerm, FeederAuditDetails rmObject, Context<Map<String, Object>> context) {
    Map<String, Object> result = new HashMap<>();

    addValue(
        result,
        currentTerm,
        "time",
        Optional.ofNullable(rmObject.getTime()).map(DvDateTime::getValue).orElse(null));
    addValue(result, currentTerm, "system_id", rmObject.getSystemId());
    addValue(result, currentTerm, "version_id", rmObject.getVersionId());

    if (rmObject.getLocation() != null) {
      result.putAll(
          PARTY_IDENTIFIED_STD_CONFIG.buildChildValues(
              currentTerm + "/location", rmObject.getLocation(), context));
    }
    if (rmObject.getProvider() != null) {
      result.putAll(
          PARTY_IDENTIFIED_STD_CONFIG.buildChildValues(
              currentTerm + "/provider", rmObject.getProvider(), context));
    }
    return result;
  }

  @Override
  /** {@inheritDoc} */
  public Class<FeederAuditDetails> getAssociatedClass() {
    return FeederAuditDetails.class;
  }
}
