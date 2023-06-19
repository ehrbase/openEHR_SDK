/*
 * Copyright (c) 2023 vitasystems GmbH and Hannover Medical School.
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

import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.directory.Folder;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.UUID;
import org.ehrbase.openehr.sdk.client.Integration;
import org.ehrbase.openehr.sdk.client.openehrclient.DirectoryCrudEndpoint;
import org.ehrbase.openehr.sdk.client.openehrclient.OpenEhrClient;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * @author Stefan Spiska
 */
@Category(Integration.class)
public class DefaultDirectoryCrudEndpointTestIT {

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
    public void testCreate() {

        ehr = openEhrClient.ehrEndpoint().createEhr();
        Folder folder = new Folder();
        folder.setName(new DvText("root"));
        folder.setArchetypeNodeId("openEHR-EHR-FOLDER.generic.v1");

        DirectoryCrudEndpoint directoryCrudEndpoint = openEhrClient.directoryCrudEndpoint(ehr);
        ObjectVersionId version = directoryCrudEndpoint.createDirectory(folder);

        assertThat(version).isNotNull();
        assertThat(version.getVersionTreeId().getValue()).isEqualTo("1");
    }

    @Test
    public void testGet() {

        ehr = openEhrClient.ehrEndpoint().createEhr();
        Folder folder = new Folder();
        folder.setName(new DvText("root"));
        folder.setArchetypeNodeId("openEHR-EHR-FOLDER.generic.v1");

        DirectoryCrudEndpoint directoryCrudEndpoint = openEhrClient.directoryCrudEndpoint(ehr);
        ObjectVersionId version = directoryCrudEndpoint.createDirectory(folder);

        assertThat(version).isNotNull();
        assertThat(version.getVersionTreeId().getValue()).isEqualTo("1");

        Optional<Folder> directory = directoryCrudEndpoint.getDirectory();

        assertThat(directory).isPresent();

        Folder oldFolder = directory.get();

        assertThat(((ObjectVersionId) oldFolder.getUid()).getVersionTreeId().getValue())
                .hasToString("1");
    }

    @Test
    public void testUpdate() {

        ehr = openEhrClient.ehrEndpoint().createEhr();
        Folder folder = new Folder();
        folder.setName(new DvText("root"));
        folder.setArchetypeNodeId("openEHR-EHR-FOLDER.generic.v1");

        DirectoryCrudEndpoint directoryCrudEndpoint = openEhrClient.directoryCrudEndpoint(ehr);
        ObjectVersionId version = directoryCrudEndpoint.createDirectory(folder);

        assertThat(version).isNotNull();
        assertThat(version.getVersionTreeId().getValue()).isEqualTo("1");

        Optional<Folder> directory = directoryCrudEndpoint.getDirectory();

        assertThat(directory).isPresent();

        Folder oldFolder = directory.get();

        assertThat(((ObjectVersionId) oldFolder.getUid()).getVersionTreeId().getValue())
                .hasToString("1");

        Folder newFolder = new Folder();
        newFolder.setName(new DvText("folder1"));
        newFolder.setArchetypeNodeId("openEHR-EHR-FOLDER.generic.v1");

        oldFolder.addFolder(newFolder);

        ObjectVersionId newVersionUid = directoryCrudEndpoint.updateDirectory(oldFolder);

        assertThat(newVersionUid.getVersionTreeId().getValue()).isEqualTo("2");

        Optional<Folder> newDirectory = directoryCrudEndpoint.getDirectory();

        assertThat(newDirectory).isPresent();

        Optional<Folder> folder1 = DirectoryCrudEndpoint.find(newDirectory.get(), "folder1");

        assertThat(folder1).isPresent();
    }
}
