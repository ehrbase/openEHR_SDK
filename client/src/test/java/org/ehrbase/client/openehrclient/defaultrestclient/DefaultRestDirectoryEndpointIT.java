/*
 *
 *  *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
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

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;
import org.ehrbase.client.Integration;
import org.ehrbase.client.TestData;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.EhrbaseBloodPressureSimpleDeV0Composition;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.EhrbaseMultiOccurrenceDeV1Composition;
import org.ehrbase.client.openehrclient.FolderDAO;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Integration.class)
public class DefaultRestDirectoryEndpointIT {
  private static OpenEhrClient openEhrClient;

  @BeforeClass
  public static void setup() throws URISyntaxException {
    openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
  }

  @Test
  public void testSetName() {
    UUID ehr = openEhrClient.ehrEndpoint().createEhr();
    FolderDAO root = openEhrClient.folder(ehr, "");
    assertThat(root.getName()).isEqualTo("root");
    root.setName("case1");
    assertThat(root.getName()).isEqualTo("case1");
  }

  @Test
  public void testGetSubFolder() {
    UUID ehr = openEhrClient.ehrEndpoint().createEhr();
    FolderDAO root = openEhrClient.folder(ehr, "");
    FolderDAO visit = root.getSubFolder("case1/visit1");
    assertThat(visit.getName()).isEqualTo("visit1");
  }

  @Test
  public void testSaveEntity() {
    UUID ehr = openEhrClient.ehrEndpoint().createEhr();

    FolderDAO root = openEhrClient.folder(ehr, "");

    FolderDAO visit = root.getSubFolder("case1/visit1");

    EhrbaseBloodPressureSimpleDeV0Composition bloodPressureSimpleDeV01 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    visit.addCompositionEntity(bloodPressureSimpleDeV01);

    EhrbaseBloodPressureSimpleDeV0Composition bloodPressureSimpleDeV02 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    visit.addCompositionEntity(bloodPressureSimpleDeV02);

    EhrbaseMultiOccurrenceDeV1Composition ehrbaseMultiOccurrenceDeV1 =
        TestData.buildEhrbaseMultiOccurrenceDeV1();
    visit.addCompositionEntity(ehrbaseMultiOccurrenceDeV1);

    List<EhrbaseBloodPressureSimpleDeV0Composition> actual =
        visit.find(EhrbaseBloodPressureSimpleDeV0Composition.class);
    assertThat(actual).size().isEqualTo(2);

    List<EhrbaseMultiOccurrenceDeV1Composition> actual2 =
        visit.find(EhrbaseMultiOccurrenceDeV1Composition.class);
    assertThat(actual2).size().isEqualTo(1);
  }

  @Test
  public void testListSubFolderNames() {
    UUID ehr = openEhrClient.ehrEndpoint().createEhr();

    FolderDAO root = openEhrClient.folder(ehr, "");
    root.getSubFolder("case1");
    root.getSubFolder("case2");
    root.getSubFolder("case3/visit1");
    root.getSubFolder("case3/visit2");

    assertThat(root.listSubFolderNames()).containsExactlyInAnyOrder("case1", "case2", "case3");

    assertThat(root.getSubFolder("case3").listSubFolderNames())
        .containsExactlyInAnyOrder("visit1", "visit2");
  }
}
