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
package org.ehrbase.client.openehrclient.defaultrestclient.systematic.ehrquery;

import static org.assertj.core.api.Assertions.assertThat;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.ehr.EhrStatus;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.io.IOUtils;
import org.ehrbase.client.Integration;
import org.ehrbase.client.aql.parameter.ParameterValue;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record1;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClientTestHelper;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.CanonicalCompoAllTypeQueryIT;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.auto.AutoEhrStatusWhereQuery;
import org.ehrbase.response.openehr.QueryResponseData;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.test_data.ehr.EhrTestDataCanonicalJson;
import org.junit.*;
import org.junit.experimental.categories.Category;

@Category(Integration.class)
public class CanonicalEhrQuery3IT extends CanonicalCompoAllTypeQueryIT {
    private static OpenEhrClient openEhrClient;

    private UUID ehrUUID;
    private EhrStatus referenceEhrStatus = null;

    @BeforeClass
    public static void setup() throws URISyntaxException {
        openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    }

    @Before
    public void setUp() {
        try {
            referenceEhrStatus = new CanonicalJson()
                    .unmarshal(
                            IOUtils.toString(
                                    EhrTestDataCanonicalJson.EHR_STATUS_SUBJECT_EXTERNAL_REF_OTHER_DETAILS.getStream(),
                                    StandardCharsets.UTF_8),
                            EhrStatus.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ehrUUID = openEhrClient.ehrEndpoint().createEhr(referenceEhrStatus);
    }

    @After
    public void tearDown() {
        // delete the created EHR using the admin endpoint
        openEhrClient.adminEhrEndpoint().delete(ehrUUID);
    }

    @Test
    public void testEhrAttributesDrillDown() {

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
            "subject/external_ref",
            "subject/external_ref/id",
            "subject/external_ref/id/value",
            "subject/external_ref/id/scheme",
            "subject/external_ref/namespace",
            "subject/external_ref/type",
            "other_details",
            "other_details/name",
            "other_details/name/value",
            "other_details/items[at0001]",
            "other_details/items[at0001]/archetype_node_id",
            "other_details/items[at0001]/name",
            "other_details/items[at0001]/name/value",
            "other_details/items[at0001]/value",
            "other_details/items[at0001]/value/id",
            "other_details/items[at0001]/value/type",
            "other_details/items[at0001]/value/issuer",
            "other_details/items[at0001]/value/assigner",
            "is_queryable",
            "is_modifiable"
        };

        for (String attributePath : attributePaths) {
            String aqlSelect = rootPath + "/" + attributePath;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("select ");
            stringBuilder.append(aqlSelect);
            stringBuilder.append(" from EHR e[ehr_id/value = $ehr_id]");
            Query<Record1<Map>> query = Query.buildNativeQuery(stringBuilder.toString(), Map.class);

            QueryResponseData result =
                    openEhrClient.aqlEndpoint().executeRaw(query, new ParameterValue("ehr_id", ehrUUID));

            List<Object> objectList = result.getRows().get(0);

            Object actual = valueObject(objectList.get(0)); // Mapped object(s) from JSON

            if (actual instanceof List) {
                Object expected = attributeArrayValueAt(referenceNode, attributePath); // RMObject(s)

                assertThat(toRmObjectList((List<Object>) actual).toArray())
                        .as(aqlSelect)
                        .containsExactlyInAnyOrder(((List<?>) expected).toArray());
            } else {
                assertThat(valueObject(objectList.get(0)))
                        .as(aqlSelect)
                        .isEqualTo(attributeValueAt(referenceNode, attributePath));
            }
        }
    }

    @Test
    public void testEhrAutoWhere() throws IOException {
        EhrStatus referenceEhrStatus = new CanonicalJson()
                .unmarshal(
                        IOUtils.toString(
                                EhrTestDataCanonicalJson.EHR_STATUS_SUBJECT_EXTERNAL_REF_OTHER_DETAILS.getStream(),
                                StandardCharsets.UTF_8),
                        EhrStatus.class);

        String rootPath = "e/ehr_status";
        RMObject referenceNode = referenceEhrStatus;
        String csvTestSet = dirPath + "/testEhrStatusWhere.csv";

        assertThat(new AutoEhrStatusWhereQuery(ehrUUID, openEhrClient)
                        .testItemPaths(csvTestSet, rootPath, referenceNode))
                .isTrue();
    }
}
