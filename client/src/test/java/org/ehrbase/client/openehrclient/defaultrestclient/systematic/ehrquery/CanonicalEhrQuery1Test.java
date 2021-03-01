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
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.ehr.EhrStatus;
import org.ehrbase.client.Integration;
import org.ehrbase.client.aql.parameter.ParameterValue;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record1;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClientTestHelper;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.CanonicalUtil;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.comparator.EhrComparator;
import org.ehrbase.response.openehr.QueryResponseData;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.net.URISyntaxException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Category(Integration.class)
@Ignore
public class CanonicalEhrQuery1Test extends CanonicalUtil {
    private static OpenEhrClient openEhrClient;

    private UUID ehrUUID;
    private DvDateTime actualDvDateTime;
    private EhrStatus referenceEhrStatus;

    @BeforeClass
    public static void setup() throws URISyntaxException {
        openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    }

    @Before
    public void setUp(){
        actualDvDateTime = new DvDateTime(OffsetDateTime.now());
        ehrUUID = openEhrClient.ehrEndpoint().createEhr();
        //ehr_status
        referenceEhrStatus = openEhrClient.ehrEndpoint().getEhrStatus(ehrUUID).get();
    }

    /**
     * create a basic ehr and check attributes
     */
    @Test
    @Ignore("CR https://github.com/ehrbase/project_management/issues/478")
    public void testEhrAttributes(){

        //see issue CR #... (cannot retrieve time_created via a get using the SDK)
        //for the time being just compare date and time zone


        Query<Record1<Map>> query = Query.buildNativeQuery(
                "select e " +
                        "from EHR e[ehr_id/value = $ehr_id]"
                , Map.class
        );

        List<Record1<Map>> result = openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehrUUID));

        Map<String, Object> valueMap = result.get(0).value1();

        assertThat(new EhrComparator(referenceEhrStatus, ehrUUID, actualDvDateTime).compare(valueMap)).isNull();
    }

    @Test
    public void testEhrAttributesDrillDown1(){

        //see issue CR #... (cannot retrieve time_created via a get using the SDK)
        //for the time being just compare date and time zone


        Query<Record1<Map>> query = Query.buildNativeQuery(
                "select e/ehr_status/name " +
                        "from EHR e[ehr_id/value = $ehr_id]"
                , Map.class
        );

        List<Record1<Map>> result = openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehrUUID));

        Map<String, Object> valueMap = result.get(0).value1();

        assertThat(compareArbitraryRmClass(valueMap, DvText.class, referenceEhrStatus.getName())).isNull();
    }

    @Test
    public void testEhrAttributesDrillDown11(){

        //see issue CR #... (cannot retrieve time_created via a get using the SDK)
        //for the time being just compare date and time zone


        Query<Record1<Map>> query = Query.buildNativeQuery(
                "select e/ehr_status/archetype_node_id " +
                        "from EHR e[ehr_id/value = $ehr_id]"
                , Map.class
        );

        QueryResponseData result = openEhrClient.aqlEndpoint().executeRaw(query, new ParameterValue("ehr_id", ehrUUID));

        List<Object> objectList = result.getRows().get(0);

        assertThat(objectList.get(0)).isEqualTo(referenceEhrStatus.getArchetypeNodeId());
    }

    @Test
    @Ignore("CR https://github.com/ehrbase/project_management/issues/480")
    public void testEhrAttributesDrillDown2(){
        Query<Record1<Map>> query = Query.buildNativeQuery(
                "select e/ehr_status/name/value " +
                        "from EHR e[ehr_id/value = $ehr_id]"
                , Map.class
        );

        List<Record1<Map>> result = openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehrUUID));

        assertThat(result.get(0).value(0)).as("e/ehr_status/name/value").isEqualTo(attributeValueAt(referenceEhrStatus.getName(), "value"));
    }

    @Test
    public void testEhrAttributesDrillDown3(){
        
        String rootPath = "e/ehr_status";
        RMObject referenceNode = referenceEhrStatus;
        
        String[] attributePaths = {
                 "archetype_node_id",
                 "archetype_details",
                 "archetype_details/archetype_id",
                 "archetype_details/archetype_id/value",
                 "archetype_details/template_id",
                 "archetype_details/template_id/value",
                "archetype_details/rm_version",
                 "subject",
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
