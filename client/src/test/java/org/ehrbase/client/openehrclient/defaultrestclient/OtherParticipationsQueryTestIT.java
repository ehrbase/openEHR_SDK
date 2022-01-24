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

package org.ehrbase.client.openehrclient.defaultrestclient;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.support.identification.GenericId;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.ehrbase.client.Integration;
import org.ehrbase.client.aql.parameter.ParameterValue;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.exception.WrongStatusCodeException;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.response.openehr.QueryResponseData;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertTrue;

@Category(Integration.class)
public class OtherParticipationsQueryTestIT {

    private static OpenEhrClient openEhrClient;
    private UUID ehr;

    @BeforeClass
    public static void setup() throws URISyntaxException {
        openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    }

    @After
    public void tearDown(){
        //delete the created EHR using the admin endpoint
        openEhrClient.adminEhrEndpoint().delete(ehr);
    }

    @Test
    public void testQueryOtherParticipations() throws IOException {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        Composition composition = new CanonicalJson().unmarshal(IOUtils.toString(CompositionTestDataCanonicalJson.OTHER_PARTICIPATIONS.getStream(), StandardCharsets.UTF_8), Composition.class);

        VersionUid versionUid = openEhrClient.compositionEndpoint(ehr).mergeRaw(composition);

        Query query = Query.buildNativeQuery(
                "Select c0/content[openEHR-EHR-ACTION.minimal.v1]/other_participations/performer/name as performer_name" +
                        " from EHR e[ehr_id/value = $ehr_id] contains (COMPOSITION c0[openEHR-EHR-COMPOSITION.minimal.v1])" +
                        " WHERE c0/uid/value = $comp_uuid "
        );

        QueryResponseData result = openEhrClient.aqlEndpoint()
                .executeRaw(query,
                        new ParameterValue("ehr_id", ehr),
                        new ParameterValue("comp_uuid", versionUid.getUuid()));

        List expectedResults = Arrays.asList(
                List.of("Frederick Wolfstein")
        );

        assertTrue(CollectionUtils.isEqualCollection(result.getRows(), expectedResults));
    }
}
