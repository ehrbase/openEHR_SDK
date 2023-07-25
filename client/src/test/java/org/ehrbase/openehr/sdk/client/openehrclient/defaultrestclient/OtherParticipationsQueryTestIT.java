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
package org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient;

import static org.junit.Assert.assertTrue;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.ehrbase.openehr.sdk.client.Integration;
import org.ehrbase.openehr.sdk.client.openehrclient.OpenEhrClient;
import org.ehrbase.openehr.sdk.generator.commons.aql.parameter.ParameterValue;
import org.ehrbase.openehr.sdk.generator.commons.aql.query.Query;
import org.ehrbase.openehr.sdk.response.dto.QueryResponseData;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Integration.class)
public class OtherParticipationsQueryTestIT {

    private static OpenEhrClient openEhrClient;
    private UUID ehr;

    @BeforeClass
    public static void setup() throws URISyntaxException {
        openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    }

    @After
    public void tearDown() {
        // delete the created EHR using the admin endpoint
        openEhrClient.adminEhrEndpoint().delete(ehr);
    }

    @Test
    public void testQueryOtherParticipations() throws IOException {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        Composition composition = new CanonicalJson()
                .unmarshal(
                        IOUtils.toString(
                                CompositionTestDataCanonicalJson.OTHER_PARTICIPATIONS.getStream(),
                                StandardCharsets.UTF_8),
                        Composition.class);

        ObjectVersionId versionUid = openEhrClient.compositionEndpoint(ehr).mergeRaw(composition);

        Query query = Query.buildNativeQuery(
                "Select c0/content[openEHR-EHR-ACTION.minimal.v1]/other_participations/performer/name as performer_name"
                        + " from EHR e[ehr_id/value = $ehr_id] contains (COMPOSITION c0[openEHR-EHR-COMPOSITION.minimal.v1])"
                        + " WHERE c0/uid/value = $comp_uuid ");

        QueryResponseData result = openEhrClient
                .aqlEndpoint()
                .executeRaw(
                        query,
                        new ParameterValue("ehr_id", ehr),
                        new ParameterValue("comp_uuid", versionUid.getObjectId().getValue()));

        List expectedResults = Arrays.asList(List.of("Frederick Wolfstein"));

        assertTrue(CollectionUtils.isEqualCollection(result.getRows(), expectedResults));
    }
}
