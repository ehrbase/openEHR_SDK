/*
 * Copyright (c) 2019 vitasystems GmbH and Hannover Medical School.
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

import com.nedap.archie.rm.composition.Composition;
import java.net.URISyntaxException;
import java.util.List;
import org.ehrbase.openehr.sdk.client.openehrclient.FolderDAO;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.TestData;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasebloodpressuresimpledev0composition.EhrbaseBloodPressureSimpleDeV0Composition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasemultioccurrencedev1composition.EhrbaseMultiOccurrenceDeV1Composition;
import org.ehrbase.openehr.sdk.serialisation.dto.GeneratedDtoToRmConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DefaultRestDirectoryEndpointIT extends SdkClientTestIT {

    private static DefaultRestClient defaultRestClient;

    @BeforeAll
    public static void setup() throws URISyntaxException {

        SdkClientTestIT.setup();
        defaultRestClient = SdkClientTestIT.setupRestClientWithDefaultTemplateProvider();
    }

    @Test
    void testSetName() {
        ehr = openEhrClient.ehrEndpoint().createEhr();
        FolderDAO root = openEhrClient.folder(ehr, "");
        assertThat(root.getName()).isEqualTo("root");
        root.setName("case1");
        assertThat(root.getName()).isEqualTo("case1");
    }

    @Test
    void testGetSubFolder() {
        ehr = openEhrClient.ehrEndpoint().createEhr();
        FolderDAO root = openEhrClient.folder(ehr, "");
        FolderDAO visit = root.getSubFolder("case1/visit1");
        assertThat(visit.getName()).isEqualTo("visit1");
    }

    @Test
    void testSaveEntity() {
        ehr = openEhrClient.ehrEndpoint().createEhr();

        FolderDAO root = openEhrClient.folder(ehr, "");

        FolderDAO visit = root.getSubFolder("case1/visit1");

        EhrbaseBloodPressureSimpleDeV0Composition bloodPressureSimpleDeV01 =
                TestData.buildEhrbaseBloodPressureSimpleDeV0();
        visit.addCompositionEntity(bloodPressureSimpleDeV01);

        EhrbaseBloodPressureSimpleDeV0Composition bloodPressureSimpleDeV02 =
                TestData.buildEhrbaseBloodPressureSimpleDeV0();
        visit.addCompositionEntity(bloodPressureSimpleDeV02);

        EhrbaseMultiOccurrenceDeV1Composition ehrbaseMultiOccurrenceDeV1 = TestData.buildEhrbaseMultiOccurrenceDeV1();
        visit.addCompositionEntity(ehrbaseMultiOccurrenceDeV1);

        List<EhrbaseBloodPressureSimpleDeV0Composition> actual =
                visit.find(EhrbaseBloodPressureSimpleDeV0Composition.class);
        assertThat(actual).size().isEqualTo(2);

        List<EhrbaseMultiOccurrenceDeV1Composition> actual2 = visit.find(EhrbaseMultiOccurrenceDeV1Composition.class);
        assertThat(actual2).size().isEqualTo(1);
    }

    @Test
    void testSaveRaw() {
        ehr = openEhrClient.ehrEndpoint().createEhr();

        FolderDAO root = openEhrClient.folder(ehr, "");

        FolderDAO visit = root.getSubFolder("case1/visit1");

        Composition composition = (Composition) new GeneratedDtoToRmConverter(
                        defaultRestClient.getTemplateProvider(), defaultRestClient.getDefaultValuesProvider())
                .toRMObject(TestData.buildEhrbaseBloodPressureSimpleDeV0());
        visit.addRaw(composition);

        List<EhrbaseBloodPressureSimpleDeV0Composition> actual =
                visit.find(EhrbaseBloodPressureSimpleDeV0Composition.class);
        assertThat(actual).size().isEqualTo(1);
    }

    @Test
    void testListSubFolderNames() {
        ehr = openEhrClient.ehrEndpoint().createEhr();

        FolderDAO root = openEhrClient.folder(ehr, "");
        root.getSubFolder("case1");
        root.getSubFolder("case2");
        root.getSubFolder("case3/visit1");
        root.getSubFolder("case3/visit2");

        assertThat(root.listSubFolderNames()).containsExactlyInAnyOrder("case1", "case2", "case3");

        assertThat(root.getSubFolder("case3").listSubFolderNames()).containsExactlyInAnyOrder("visit1", "visit2");
    }
}
