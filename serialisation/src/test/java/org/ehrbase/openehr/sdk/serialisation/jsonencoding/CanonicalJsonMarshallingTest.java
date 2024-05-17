/*
 * Copyright (c) 2022 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.serialisation.jsonencoding;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.nedap.archie.rm.changecontrol.Contribution;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datastructures.ItemTree;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDate;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvTime;
import com.nedap.archie.rm.ehr.Ehr;
import com.nedap.archie.rm.support.identification.HierObjectId;
import com.nedap.archie.rm.support.identification.ObjectId;
import com.nedap.archie.rm.support.identification.ObjectRef;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class CanonicalJsonMarshallingTest {

    @Test
    public void UnmarshalMultimedia() throws IOException {

        String value = new String(Files.readAllBytes(Paths.get("src/test/resources/sample_data/multimedia.json")));

        CanonicalJson cut = new CanonicalJson();
        DvMultimedia dvMultimedia = cut.unmarshal(value, DvMultimedia.class);

        assertNotNull(dvMultimedia);
    }

    @Test
    public void UnmarshalMultimediaElement() throws IOException {

        String value =
                new String(Files.readAllBytes(Paths.get("src/test/resources/sample_data/element_multimedia.json")));

        CanonicalJson cut = new CanonicalJson();
        Element element = cut.unmarshal(value, Element.class);

        assertNotNull(element);
    }

    @Test
    public void UnmarshalItemTree() throws IOException {

        String value = new String(
                Files.readAllBytes(Paths.get("src/test/resources/sample_data/item_tree_with_multimedia.json")));

        CanonicalJson cut = new CanonicalJson();
        ItemTree itemTree = cut.unmarshal(value, ItemTree.class);

        assertNotNull(itemTree);
    }

    @Test
    public void UnmarshalPartialDate() throws IOException {

        String value = new String(Files.readAllBytes(Paths.get("src/test/resources/sample_data/partialdvdate.json")));

        CanonicalJson cut = new CanonicalJson();
        DvDate dvDate = cut.unmarshal(value, DvDate.class);

        assertNotNull(dvDate);

        assertEquals("2020-08", dvDate.getValue().toString());
    }

    @Test
    public void MarshalDuration() {
        DvDuration duration = new DvDuration(Duration.ofDays(30L));
        CanonicalJson cut = new CanonicalJson();
        String actual = cut.marshal(duration);
        assertThat(actual)
                .isEqualToIgnoringWhitespace("{" + "  \"_type\" : \"DV_DURATION\"," + "  \"value\" : \"PT720H\"" + "}");
    }

    @Test
    public void MarshalEmptyDvText() {
        DvText dvText = new DvText("");
        CanonicalJson cut = new CanonicalJson();
        String actual = cut.marshal(dvText);
        assertThat(actual)
                .isEqualToIgnoringWhitespace("{\n" + "  \"_type\" : \"DV_TEXT\",\n" + "  \"value\" : \"\"\n" + "}");
    }

    @Test
    public void MarshalDvDateTimeWithZero() {
        DvDateTime dvDateTime = new DvDateTime("2022-02-25T10:55:41.400Z");
        CanonicalJson cut = new CanonicalJson();
        String actual = cut.marshal(dvDateTime);
        assertThat(actual)
                .isEqualToIgnoringWhitespace("{\n"
                        + "  \"_type\" : \"DV_DATE_TIME\",\n"
                        + "  \"value\" : \"2022-02-25T10:55:41.4Z\"\n"
                        + "}");
    }

    @Test
    public void MarshalDvTimeWithZero() {
        DvTime dvTime = new DvTime("10:55:41.400Z");
        CanonicalJson cut = new CanonicalJson();
        String actual = cut.marshal(dvTime);
        assertThat(actual)
                .isEqualToIgnoringWhitespace(
                        "{\n" + "  \"_type\" : \"DV_TIME\",\n" + "  \"value\" : \"10:55:41.4Z\"\n" + "}");
    }

    @Test
    public void MarshalEmptyContent() {
        ItemTree itemTree = new ItemTree();
        itemTree.setNameAsString("test");
        CanonicalJson cut = new CanonicalJson();
        String actual = cut.marshal(itemTree);
        assertThat(actual)
                .isEqualToIgnoringWhitespace("{\n"
                        + "  \"_type\" : \"ITEM_TREE\",\n"
                        + "  \"name\" : {\n"
                        + "    \"_type\" : \"DV_TEXT\",\n"
                        + "    \"value\" : \"test\"\n"
                        + "  }\n"
                        + "}");
    }

    @Test
    public void UnmarshalPartialDateTime() throws IOException {

        String value =
                new String(Files.readAllBytes(Paths.get("src/test/resources/sample_data/partialdvdatetime.json")));

        CanonicalJson cut = new CanonicalJson();
        DvDateTime dvDateTime = cut.unmarshal(value, DvDateTime.class);

        assertNotNull(dvDateTime);

        // NB. partial time (e.g. '10') is defaulted to '10:00' due to Java API handling of time values
        assertEquals("2020-08-01T10:00", dvDateTime.getValue().toString());
    }

    @Test
    public void marshallContribution() {
        List<ObjectRef<? extends ObjectId>> versions = new ArrayList<>();
        versions.add(new ObjectRef<>(
                new HierObjectId("COMPOSITION"),
                "local",
                "b5c4aaed-2adc-4c56-9005-e21ff3cca62a::local.ehrbase.org::2"));

        Contribution expected = new Contribution();
        expected.setUid(new HierObjectId("bbf60d27-9200-4995-a950-279f889d1050"));
        expected.setVersions(versions);

        CanonicalJson cut = new CanonicalJson();
        String json = cut.marshal(expected);

        Contribution actual = cut.unmarshal(json, Contribution.class);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void marshallEhr() {
        List<ObjectRef<? extends ObjectId>> compositions = new ArrayList<>();
        compositions.add(new ObjectRef<>(
                new HierObjectId("COMPOSITION"),
                "local",
                "b5c4aaed-2adc-4c56-9005-e21ff3cca62a::local.ehrbase.org::2"));
        List<ObjectRef<? extends ObjectId>> contributions = new ArrayList<>();
        contributions.add(new ObjectRef<>(
                new HierObjectId("COMPOSITION"),
                "local",
                "b5c4aaed-2adc-4c56-9005-e21ff3cca62a::local.ehrbase.org::2"));
        List<ObjectRef<? extends ObjectId>> folders = new ArrayList<>();
        folders.add(new ObjectRef<>(
                new HierObjectId("COMPOSITION"),
                "local",
                "b5c4aaed-2adc-4c56-9005-e21ff3cca62a::local.ehrbase.org::2"));

        Ehr expected = new Ehr();
        expected.setCompositions(compositions);
        expected.setContributions(contributions);
        expected.setFolders(folders);

        CanonicalJson cut = new CanonicalJson();
        String json = cut.marshal(expected);

        Ehr actual = cut.unmarshal(json, Ehr.class);
        Assert.assertEquals(actual, expected);
    }

    // check that dot is not converted into a comma!
    @Test
    public void MarshalDvDateTime() {
        DvDateTime dvDateTime = new DvDateTime("2021-10-01T10:32:51.543+07:00");
        CanonicalJson cut = new CanonicalJson();
        String actual = cut.marshal(dvDateTime);
        assertThat(actual)
                .isEqualToIgnoringWhitespace("{" + "  \"_type\" : \"DV_DATE_TIME\","
                        + "  \"value\" : \"2021-10-01T10:32:51.543+07:00\""
                        + "}");
    }
}
