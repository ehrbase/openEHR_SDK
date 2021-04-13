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
import org.ehrbase.serialisation.dbencoding.CompositionSerializer;
import org.ehrbase.serialisation.dbencoding.PathMap;

import java.util.Map;
import org.ehrbase.serialisation.dbencoding.rawjson.LightRawJsonEncoder;

public class FeederAuditDetailsAttributes {

    private final FeederAuditDetails feederAuditDetails;

    public FeederAuditDetailsAttributes(FeederAuditDetails feederAuditDetails) {
        this.feederAuditDetails = feederAuditDetails;
    }

    /**
     * encode the attributes lower snake case to comply with UML conventions and make is queryable
     * @return
     */
    public Map<String, Object> toMap() {
        Map<String, Object> valuemap = PathMap.getInstance();

        if (feederAuditDetails == null)
            return null;

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
        if (feederAuditDetails.getVersionId() != null) {
            valuemap.put("version_id", feederAuditDetails.getVersionId());
        }
        if (feederAuditDetails.getOtherDetails() != null) {
            String dbEncoded = new CompositionSerializer().dbEncode(feederAuditDetails.getOtherDetails());
            Map<String, Object> asMap = new LightRawJsonEncoder(dbEncoded).encodeOtherDetailsAsMap();
            String nodeId = asMap.get("/archetype_node_id").toString();
            // make sure node id is wrapped in [ and ] and throw errors if invalid input
            if (!nodeId.startsWith("[")) {
                if (nodeId.endsWith("]"))
                    throw new IllegalArgumentException("Invalid archetype node id");
                nodeId = "[" + nodeId + "]";
            } else if (!nodeId.endsWith("]")) {
                throw new IllegalArgumentException("Invalid archetype node id");
            }
            valuemap.put("other_details" + nodeId, asMap);
        }
        return valuemap;
    }
}
