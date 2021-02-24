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

package org.ehrbase.client.openehrclient.defaultrestclient.systematic.ehrquery;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.ehr.EhrStatus;
import org.apache.commons.io.IOUtils;
import org.ehrbase.client.aql.parameter.ParameterValue;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record1;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClientTestHelper;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.CanonicalUtil;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.comparator.EhrComparator;
import org.ehrbase.response.openehr.QueryResponseData;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.ehrbase.test_data.ehr.EhrTestDataCanonicalJson;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CanonicalEhrQuery2Test extends CanonicalUtil {
    private static OpenEhrClient openEhrClient;

    private UUID ehrUUID;
    private EhrStatus referenceEhrStatus = null;

    @BeforeClass
    public static void setup() throws URISyntaxException {
        openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    }

    @Before
    public void setUp(){
        try {
            referenceEhrStatus = new CanonicalJson().unmarshal(IOUtils.toString(EhrTestDataCanonicalJson.EHR_STATUS_SUBJECT_EXTERNAL_REF.getStream(), StandardCharsets.UTF_8), EhrStatus.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ehrUUID = openEhrClient.ehrEndpoint().createEhr(referenceEhrStatus);
    }

     @Test
    public void testEhrAttributesDrillDown(){

        String rootPath = "e/ehr_status";
        RMObject referenceNode = referenceEhrStatus;

         String[] attributePaths = {
                 "archetype_node_id",
                 "archetype_details",
                 "archetype_details/archetype_id",
                 "archetype_details/archetype_id/value",
                 "archetype_details/template_id",
                 "archetype_details/template_id/value",
                 "subject",
                 //TODO: https://github.com/ehrbase/project_management/issues/481
//                 "subject/external_ref"
                 "subject/external_ref/id",
                 "subject/external_ref/id/value",
                 "subject/external_ref/id/scheme",
                 "subject/external_ref/namespace",
                 "subject/external_ref/type",
                 "is_queryable",
                 "is_modifiable"
         };

        for (String attributePath: attributePaths) {
            String aqlSelect = rootPath+"/"+attributePath;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("select ");
            stringBuilder.append(aqlSelect);
            stringBuilder.append(" from EHR e[ehr_id/value = $ehr_id]");
            Query<Record1<Map>> query = Query.buildNativeQuery(
                    stringBuilder.toString()
                    , Map.class
            );

            QueryResponseData result = openEhrClient.aqlEndpoint().executeRaw(query, new ParameterValue("ehr_id", ehrUUID));

            List<Object> objectList = result.getRows().get(0);

            assertThat(valueObject(objectList.get(0)))
                    .as(aqlSelect)
                    .isEqualTo(attributeValueAt(referenceNode, attributePath));
        }
    }

}
