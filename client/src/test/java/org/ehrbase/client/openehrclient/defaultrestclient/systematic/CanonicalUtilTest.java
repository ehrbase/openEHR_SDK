/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.client.openehrclient.defaultrestclient.systematic;

import static org.assertj.core.api.Assertions.assertThat;

import com.nedap.archie.rm.ehr.EhrStatus;
import org.junit.Test;

public class CanonicalUtilTest extends CanonicalUtil {

    private final String ehrStatusTest = "{\n" + "  \"_type\": \"EHR_STATUS\",\n"
            + "  \"archetype_node_id\": \"openEHR-EHR-EHR_STATUS.generic.v1\",\n"
            + "  \"name\": {\n"
            + "    \"value\": \"EHR Status\"\n"
            + "  },\n"
            + "  \"subject\": {\n"
            + "    \"external_ref\": {\n"
            + "      \"_type\": \"PARTY_REF\",\n"
            + "      \"id\": {\n"
            + "        \"_type\": \"GENERIC_ID\",\n"
            + "        \"value\": \"10101010-1010-1010-1010-101010101010\",\n"
            + "        \"scheme\": \"local\"\n"
            + "      },\n"
            + "      \"namespace\": \"patients\",\n"
            + "      \"type\": \"PERSON\"\n"
            + "    }\n"
            + "  },\n"
            + "  \"is_modifiable\": true,\n"
            + "  \"is_queryable\": true\n"
            + "}";

    @Test
    public void testGetAttributeInRmObject() {

        EhrStatus ehrStatus = (EhrStatus) toRmObject(ehrStatusTest, EhrStatus.class);

        assertThat(attributeValueAt(ehrStatus, "name/value")).isEqualTo("EHR Status");
        assertThat(attributeValueAt(ehrStatus, "archetype_node_id")).isEqualTo("openEHR-EHR-EHR_STATUS.generic.v1");
        assertThat(attributeValueAt(ehrStatus, "is_modifiable")).isEqualTo(true);
        assertThat(attributeValueAt(ehrStatus, "subject/external_ref/id/value"))
                .isEqualTo("10101010-1010-1010-1010-101010101010");
    }
}
