/*
 * Copyright (c) 2019 Vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project EHRbase
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehrbase.serialisation.dbencoding;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datastructures.ItemStructure;
import com.nedap.archie.rm.datastructures.ItemTree;
import org.apache.commons.io.IOUtils;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.xmlencoding.CanonicalXML;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalXML;
import org.ehrbase.test_data.item_structure.ItemStruktureTestDataCanonicalJson;
import org.junit.Test;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class RawJsonTest {

    @Test
    public void marshal() throws IOException {

        String value = IOUtils.toString(CompositionTestDataCanonicalXML.DIADEM.getStream(), UTF_8);

        CanonicalXML canonicalXML = new CanonicalXML();

        Composition composition = canonicalXML.unmarshal(value, Composition.class);

        RawJson cut = new RawJson();

        String marshal = cut.marshal(composition);

        assertThat(marshal).isNotEmpty();
    }

    @Test
    public void testMarshalItemStructure() throws IOException {

        String value = IOUtils.toString(ItemStruktureTestDataCanonicalJson.SIMPLE_EHR_OTHER_Details.getStream(), UTF_8);

        CanonicalJson canonicalJson = new CanonicalJson();

        ItemStructure itemTree = canonicalJson.unmarshal(value, ItemStructure.class);

        RawJson cut = new RawJson();

        String marshal = cut.marshal(itemTree);

        assertThat(marshal).isNotEmpty();
    }

    @Test
    public void testUnmarshalItemStructure() throws IOException {

        String value = IOUtils.toString(ItemStruktureTestDataCanonicalJson.SIMPLE_EHR_OTHER_Details.getStream(), UTF_8);

        CanonicalJson canonicalJson = new CanonicalJson();

        ItemTree itemTree = canonicalJson.unmarshal(value, ItemTree.class);

        RawJson cut = new RawJson();

        String marshal = cut.marshal(itemTree);

        ItemTree actual = cut.unmarshal(marshal, ItemTree.class);

        assertThat(actual).isNotNull();
        assertThat(actual.getItems()).size().isEqualTo(3);
    }

    @Test
    public void testUnmarshalItemStructureWithItemTable() throws IOException {

        //This test fails as ITEM_TABLE is not yet supported.
//        String value = new String(Files.readAllBytes(Paths.get("src/test/resources/sample_data/item_table_sample.json")));
//
//        CanonicalJson canonicalJson = new CanonicalJson();
//
//        ItemTable itemTable = canonicalJson.unmarshal(value, ItemTable.class);
//
//        RawJson cut = new RawJson();
//
//        String marshal = cut.marshal(itemTable);
//
//        ItemTable actual = cut.unmarshal(marshal, ItemTable.class);
//
//        assertThat(actual).isNotNull();
//        assertThat(actual.getItems()).size().isEqualTo(3);
    }

    @Test
    public void unmarshal() throws IOException {

        String value = IOUtils.toString(CompositionTestDataCanonicalXML.DIADEM.getStream(), UTF_8);

        CanonicalXML canonicalXML = new CanonicalXML();

        Composition composition = canonicalXML.unmarshal(value, Composition.class);

        RawJson cut = new RawJson();

        String marshal = cut.marshal(composition);

        Composition actual = cut.unmarshal(marshal, Composition.class);

        assertThat(actual).isNotNull();
        assertThat(composition.getName().getValue()).isEqualTo("DiADeM Assessment");
    }

    @Test
    public void unmarshal2() throws IOException {

        String value = IOUtils.toString(CompositionTestDataCanonicalXML.ALL_TYPES_FIXED.getStream(), UTF_8);

        CanonicalXML canonicalXML = new CanonicalXML();

        Composition composition = canonicalXML.unmarshal(value, Composition.class);

        RawJson cut = new RawJson();

        String marshal = cut.marshal(composition);

        Composition actual = cut.unmarshal(marshal, Composition.class);

        assertThat(actual).isNotNull();
        assertThat(composition.getName().getValue()).isEqualTo("Test all types");
    }

    @Test
    public void marshallEmptyState() throws Exception {
        String json = IOUtils.toString(CompositionTestDataCanonicalJson.GECCO_LABORBEFUND.getStream(), UTF_8);
        Composition composition = new CanonicalJson().unmarshal(json, Composition.class);

        RawJson rawJson = new RawJson();
        String actual = rawJson.marshal(composition);

        assertThat(actual).isNotNull();
    }
}