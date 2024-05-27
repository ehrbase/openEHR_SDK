/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.serialisation;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

import com.nedap.archie.rm.directory.Folder;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.openehr.sdk.serialisation.xmlencoding.CanonicalXML;
import org.ehrbase.openehr.sdk.test_data.folder.FolderTestDataCanonicalJson;
import org.ehrbase.openehr.sdk.test_data.folder.FolderTestDataCanonicalXML;
import org.junit.Ignore;
import org.junit.Test;

public class FolderSerializerTest {

    @Test
    public void marshalBasicJsonFolder() throws IOException {

        String value = IOUtils.toString(FolderTestDataCanonicalJson.SIMPLE_EMPTY_FOLDER.getStream(), UTF_8);
        CanonicalJson canonicalJson = new CanonicalJson();
        Folder folder = canonicalJson.unmarshal(value, Folder.class);

        String marshalled = canonicalJson.marshal(folder);

        assertThat(marshalled).isNotEmpty();
        assertThat(marshalled).containsSubsequence("\"_type\"", "\"FOLDER\"");
        assertThat(marshalled)
                .containsSubsequence(
                        "\"name\"", "{", "\"_type\"", "\"DV_TEXT\"", "\"value\"", "\"Simple empty folder\"");
    }

    @Test
    public void unmarshalBasicJsonFolder() throws IOException {

        String value = IOUtils.toString(FolderTestDataCanonicalJson.SIMPLE_EMPTY_FOLDER.getStream(), UTF_8);

        CanonicalJson canonicalJson = new CanonicalJson();
        Folder folder = canonicalJson.unmarshal(value, Folder.class);

        assertThat(folder.getNameAsString()).isEqualTo("Simple empty folder");
    }

    @Test
    @Ignore("Possible bug at Archie with missing XMLRootElement annotation for folders.")
    public void marshalBasicXmlFolder() throws IOException {

        String value = IOUtils.toString(FolderTestDataCanonicalXML.SIMPLE_EMPTY_FOLDER.getStrean(), UTF_8);

        CanonicalXML canonicalXML = new CanonicalXML();
        Folder folder = canonicalXML.unmarshal(value, Folder.class);

        String marshalled = canonicalXML.marshal(folder);

        assertThat(marshalled).containsSubsequence("<name>", "<value>", "Simple empty folder", "</value>", "</name>");
    }
}
