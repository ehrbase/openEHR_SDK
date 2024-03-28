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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import com.nedap.archie.rm.ehr.EhrStatus;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class DefaultRestAdminEhrEndpointIT extends SdkClientTestIT {

    @Test
    void testDeleteEhr() {
        ehr = openEhrClient.ehrEndpoint().createEhr();
        assertThat(ehr).isNotNull();
        // delete the EHR
        openEhrClient.adminEhrEndpoint().delete(ehr);
        // check it's gone
        Optional<EhrStatus> ehrStatus = openEhrClient.ehrEndpoint().getEhrStatus(ehr);
        assertFalse(ehrStatus.isPresent());
    }

    @Override
    @AfterEach
    public void tearDown() {
        // NOP
    }
}
