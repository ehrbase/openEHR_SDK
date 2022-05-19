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

package org.ehrbase.client.openehrclient.defaultrestclient;

import com.nedap.archie.rm.ehr.EhrStatus;
import org.ehrbase.client.Integration;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.net.URISyntaxException;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

@Category(Integration.class)
public class DefaultRestAdminEhrEndpointIT {

    private static OpenEhrClient openEhrClient;
    private UUID ehr;

    @BeforeClass
    public static void setup() throws URISyntaxException {
        openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    }

    @Test
    public void testDeleteEhr(){
        ehr = openEhrClient.ehrEndpoint().createEhr();
        assertThat(ehr).isNotNull();
        //delete the EHR
        openEhrClient.adminEhrEndpoint().delete(ehr);
        //check it's gone
        Optional<EhrStatus> ehrStatus = openEhrClient.ehrEndpoint().getEhrStatus(ehr);
        assertFalse(ehrStatus.isPresent());
    }

}