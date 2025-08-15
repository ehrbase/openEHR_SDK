/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.serialisation.jsonencoding;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.changecontrol.Contribution;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datastructures.ItemTree;
import com.nedap.archie.rm.datavalues.DataValue;
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
import org.ehrbase.openehr.sdk.serialisation.MarshalOption;
import org.ehrbase.openehr.sdk.serialisation.RMDataFormat;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

class CanonicalJsonTest {

    private static RMDataFormat rmDataFormat() {
        return RMDataFormat.canonicalJSON();
    }

    private static <T extends RMObject> T unmarshal(String path, Class<T> rmObjectClass) {
        String value = null;
        try {
            value = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            fail(e);
        }
        assertThat(value).isNotNull();
        return RMDataFormat.canonicalJSON().unmarshal(value, rmObjectClass);
    }

    @Test
    void unmarshalMultimedia() {

        assertThat(unmarshal("src/test/resources/sample_data/multimedia.json", DvMultimedia.class))
                .satisfies(this::assertDvMultimedia);
    }

    @Test
    void unmarshalMultimediaElement() {

        assertThat(unmarshal("src/test/resources/sample_data/element_multimedia.json", Element.class))
                .satisfies(element -> assertDvMultimedia(element.getValue()));
    }

    @Test
    void unmarshalMultimediaItemTree() {

        assertThat(unmarshal("src/test/resources/sample_data/item_tree_with_multimedia.json", ItemTree.class))
                .satisfies(itemTree -> assertThat(itemTree.getItems().get(0))
                        .isInstanceOf(Cluster.class)
                        .satisfies(clusterValue -> {
                            Cluster cluster = (Cluster) clusterValue;
                            assertThat(cluster.getItems().get(0))
                                    .isInstanceOf(Element.class)
                                    .satisfies(itemValue -> assertDvMultimedia(((Element) itemValue).getValue()));
                        }));
    }

    @Test
    void unmarshalPartialDateTime() {

        assertThat(unmarshal("src/test/resources/sample_data/partialdvdatetime.json", DvDateTime.class))
                .satisfies(dvDateTime1 ->
                        // NB. partial time (e.g. '10') is defaulted to '10:00' due to Java API handling of time values
                        assertThat(dvDateTime1.getValue()).hasToString("2020-08-01T10:00"));
    }

    private void assertDvMultimedia(DataValue dataValue) {

        assertThat(dataValue).isInstanceOf(DvMultimedia.class).satisfies(elementValue -> {
            var dvMultimedia = (DvMultimedia) elementValue;
            assertThat(dvMultimedia.getMediaType()).satisfies(mediaType -> {
                assertThat(mediaType.getTerminologyId().getValue()).isEqualTo("IANA_media-types");
                assertThat(mediaType.getCodeString()).isEqualTo("text/plain");
            });
            assertThat(dvMultimedia.getSize()).isEqualTo(23);
            assertThat(dvMultimedia.getData())
                    .satisfies(bytes -> assertThat(new String(bytes)).isEqualTo("Shall Be Base64 encoded"));
        });
    }

    @Test
    void unmarshalPartialDate() {

        assertThat(unmarshal("src/test/resources/sample_data/partialdvdate.json", DvDate.class))
                .satisfies(dvDate -> assertThat(dvDate.getValue()).hasToString("2020-08"));
    }

    @Test
    void marshalDefaultPrettyPrint() {

        Element element = new Element("at0042", new DvText("pretty print"), null);
        assertThat(rmDataFormat().marshal(element))
                .isEqualTo(
                        """
                       {
                         "_type" : "ELEMENT",
                         "name" : {
                           "_type" : "DV_TEXT",
                           "value" : "pretty print"
                         },
                         "archetype_node_id" : "at0042"
                       }""");
    }

    @Test
    void marshalWithOptionsNoPrettyPrint() {

        Element element = new Element("at0042", new DvText("default no pretty print"), null);
        assertThat(rmDataFormat().marshalWithOptions(element))
                .isEqualTo(
                        """
                       {"_type":"ELEMENT","name":{"_type":"DV_TEXT","value":"default no pretty print"},"archetype_node_id":"at0042"}""");
    }

    @Test
    void marshalWithOptionsPrettyPrint() {

        Element element = new Element("at0042", new DvText("pretty print"), null);
        assertThat(rmDataFormat().marshalWithOptions(element, MarshalOption.PRETTY_PRINT))
                .isEqualTo(
                        """
                       {
                         "_type" : "ELEMENT",
                         "name" : {
                           "_type" : "DV_TEXT",
                           "value" : "pretty print"
                         },
                         "archetype_node_id" : "at0042"
                       }""");
    }

    @Test
    void marshalDuration() {

        JSONAssert.assertEquals(
                rmDataFormat().marshal(new DvDuration(Duration.ofDays(30L))),
                """
                        {"_type":"DV_DURATION","value":"PT720H"}""",
                true);
    }

    @Test
    void marshalEmptyDvText() {

        JSONAssert.assertEquals(
                rmDataFormat().marshal(new DvText("")),
                """
                        {"_type":"DV_TEXT","value":""}""",
                true);
    }

    // check that dot is not converted into a comma!
    @Test
    void marshalDvDateTime() {

        JSONAssert.assertEquals(
                rmDataFormat().marshal(new DvDateTime("2021-10-01T10:32:51.543+07:00")),
                """
                        {"_type":"DV_DATE_TIME","value":"2021-10-01T10:32:51.543+07:00"}""",
                true);
    }

    @Test
    void marshalDvDateTimeWithZero() {

        JSONAssert.assertEquals(
                rmDataFormat().marshal(new DvDateTime("2022-02-25T10:55:41.400Z")),
                """
                        {"_type":"DV_DATE_TIME","value":"2022-02-25T10:55:41.4Z"}""",
                true);
    }

    @Test
    void marshalDvTimeWithZero() {

        JSONAssert.assertEquals(
                rmDataFormat().marshal(new DvTime("10:55:41.400Z")),
                """
                        {"_type":"DV_TIME","value":"10:55:41.4Z"}""",
                true);
    }

    @Test
    void marshalEmptyContent() {

        ItemTree itemTree = new ItemTree();
        itemTree.setNameAsString("test");
        JSONAssert.assertEquals(
                rmDataFormat().marshal(itemTree),
                """
                        {"_type":"ITEM_TREE","name":{"_type":"DV_TEXT","value":"test"}}""",
                true);
    }

    @Test
    void marshallContribution() {

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
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void marshallEhr() {

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
        assertThat(actual).isEqualTo(expected);
    }
}
