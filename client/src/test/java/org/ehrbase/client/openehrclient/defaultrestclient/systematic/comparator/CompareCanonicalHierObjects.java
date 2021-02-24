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

package org.ehrbase.client.openehrclient.defaultrestclient.systematic.comparator;

import com.nedap.archie.rm.support.identification.HierObjectId;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.CanonicalUtil;

import static org.assertj.core.api.Assertions.assertThat;

public class CompareCanonicalHierObjects extends CanonicalUtil {

    private static String canonicalJsonTemplate = "{\n" +
            "  \"_type\" : \"HIER_OBJECT_ID\",\n" +
            "  \"value\" : \"%s\"\n" +
            "}\n";

    private HierObjectId hierObjectId;

    public CompareCanonicalHierObjects(HierObjectId hierObjectId) {
        this.hierObjectId = hierObjectId;
    }

    public void isExpectedEqualToCanonicalUsing(String idValue){
        String jsonTransformed = toRmJson(hierObjectId);
        String localJsonReference = String.format(canonicalJsonTemplate, idValue);
        assertThat(jsonTransformed).isEqualToIgnoringNewLines(localJsonReference);
    }
}


