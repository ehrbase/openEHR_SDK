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

import org.ehrbase.client.Integration;
import org.ehrbase.client.openehrclient.FolderDAO;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.net.URISyntaxException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

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
}