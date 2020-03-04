/*
 *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  This file is part of Project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.client.openehrclient.defaultrestclient;

import com.nedap.archie.rm.datastructures.ItemTree;
import com.nedap.archie.rm.ehr.EhrStatus;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.HierObjectId;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.apache.commons.io.IOUtils;
import org.ehrbase.client.Integration;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.serialisation.CanonicalJson;
import org.ehrbase.test_data.item_structure.ItemStruktureTestDataCanonicalJson;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@Category(Integration.class)
public class DefaultRestEhrEndpointIT {
    private static OpenEhrClient openEhrClient;

    @BeforeClass
    public static void setup() throws URISyntaxException {
        openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    }


    @Test
    public void testCreateEhr() {

        UUID ehr = openEhrClient.ehrEndpoint().createEhr();
        assertThat(ehr).isNotNull();
    }

    @Test
    public void testGetEhrStatus() {

        UUID ehrId = openEhrClient.ehrEndpoint().createEhr();
        Optional<EhrStatus> ehrStatus = openEhrClient.ehrEndpoint().getEhrStatus(ehrId);
        assertTrue(ehrStatus.isPresent());
    }

    @Test
    public void testUpdateEhrStatus() throws IOException {

        UUID ehrId = openEhrClient.ehrEndpoint().createEhr();

        EhrStatus ehrStatus = openEhrClient.ehrEndpoint().getEhrStatus(ehrId).get();
        ehrStatus.setQueryable(false);
        ehrStatus.setModifiable(false);
        HierObjectId subjectId = new HierObjectId("6ee110de-08f8-4fac-8372-820650f150a9");
        ehrStatus.setSubject(new PartySelf(new PartyRef(subjectId, "default", null)));

        String value = IOUtils.toString(ItemStruktureTestDataCanonicalJson.SIMPLE_EHR_OTHER_Details.getStream(), UTF_8);
        ehrStatus.setOtherDetails(new CanonicalJson().unmarshal(value, ItemTree.class));


        openEhrClient.ehrEndpoint().updateEhrStatus(ehrId, ehrStatus);
        EhrStatus actual = openEhrClient.ehrEndpoint().getEhrStatus(ehrId).get();

        assertThat(actual.getSubject().getExternalRef().getId()).isEqualTo(subjectId);
        assertThat(actual.isModifiable()).isEqualTo(ehrStatus.isModifiable());
        assertThat(actual.isQueryable()).isEqualTo(ehrStatus.isQueryable());

        assertThat(actual.getOtherDetails()).isNotNull();
        assertThat(actual.getOtherDetails().getItems()).size().isEqualTo(ehrStatus.getOtherDetails().getItems().size());
    }

}