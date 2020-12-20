/*
 * Copyright (c) 2020 Christian Chevalley (Hannover Medical School) and Vitasystems GmbH
 *
 * This file is part of project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package org.ehrbase.serialisation.attributes;

import com.nedap.archie.rm.archetyped.FeederAuditDetails;
import java.util.Map;
import org.ehrbase.serialisation.dbencoding.PathMap;

public class FeederAuditDetailsAttributes {

  private final FeederAuditDetails feederAuditDetails;

  public FeederAuditDetailsAttributes(FeederAuditDetails feederAuditDetails) {
    this.feederAuditDetails = feederAuditDetails;
  }

  /**
   * encode the attributes lower snake case to comply with UML conventions and make is queryable
   *
   * @return
   */
  public Map<String, Object> toMap() {
    Map<String, Object> valuemap = PathMap.getInstance();

    if (feederAuditDetails == null) return null;

    if (feederAuditDetails.getLocation() != null) {
      valuemap.put("location", feederAuditDetails.getLocation());
    }
    if (feederAuditDetails.getProvider() != null) {
      valuemap.put("provider", feederAuditDetails.getProvider());
    }
    if (feederAuditDetails.getSubject() != null) {
      valuemap.put("subject", feederAuditDetails.getSubject());
    }
    if (feederAuditDetails.getSystemId() != null) {
      valuemap.put("system_id", feederAuditDetails.getSystemId());
    }
    if (feederAuditDetails.getTime() != null) {
      valuemap.put("time", feederAuditDetails.getTime());
    }
    return valuemap;
  }
}
