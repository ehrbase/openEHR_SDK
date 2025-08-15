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
package org.ehrbase.openehr.sdk.serialisation.xmlencoding;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.directory.Folder;
import com.nedap.archie.rm.support.identification.PartyRef;
import java.io.IOException;
import javax.xml.namespace.QName;
import org.apache.commons.io.IOUtils;
import org.ehrbase.openehr.sdk.serialisation.MarshalOption;
import org.ehrbase.openehr.sdk.serialisation.RMDataFormat;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataCanonicalXML;
import org.junit.jupiter.api.Test;

class CanonicalXMLTest {

    private static Composition unmarshallComposition(CompositionTestDataCanonicalXML compositionTestDataCanonicalXML) {

        String value = null;
        try {
            value = IOUtils.toString(compositionTestDataCanonicalXML.getStream(), UTF_8);
        } catch (IOException e) {
            fail(e);
        }
        assertThat(value).isNotNull();
        return RMDataFormat.canonicalXML().unmarshal(value);
    }

    @Test
    void unmarshal() {

        Composition composition = unmarshallComposition(CompositionTestDataCanonicalXML.ALL_TYPES);

        assertThat(composition).isNotNull();
        assertThat(composition.getArchetypeDetails().getTemplateId())
                .satisfies(templateId -> assertThat(templateId.getValue()).isEqualTo("test_all_types.en.v1"));
    }

    @Test
    void unmarshalWithDefaultSchema() {

        Composition composition = unmarshallComposition(CompositionTestDataCanonicalXML.DIADEM_DEFAULT_SCHEMA);

        assertThat(composition).isNotNull();
        assertThat(composition.getArchetypeDetails()).isNull();
    }

    @Test
    void unmarshalWithDuplicatedSections() {

        Composition composition = unmarshallComposition(CompositionTestDataCanonicalXML.REGISTRO_DE_ATENDIMENTO);

        assertThat(composition).isNotNull();
        assertThat(composition.getArchetypeDetails().getTemplateId())
                .satisfies(templateId ->
                        assertThat(templateId.getValue()).isEqualTo("Registro de Atendimento Clínico v1.0"));
    }

    @Test
    void marshalDefaultPrettyPrint() {

        Composition composition = new Composition();
        composition.setName(new DvText("pretty print test"));

        String marshal = RMDataFormat.canonicalXML().marshal(composition);

        assertThat(marshal)
                .startsWith(
                        """
              <composition xmlns:ns2="http://schemas.openehr.org/v1">
                  <name>
                      <value>pretty print test</value>
                  </name>
              </composition>""");
    }

    @Test
    void marshalWithOptionsDefaultNoPrettyPrint() {

        Composition composition = unmarshallComposition(CompositionTestDataCanonicalXML.ALL_TYPES);

        String marshal = RMDataFormat.canonicalXML().marshalWithOptions(composition);
        assertThat(marshal)
                .startsWith(
                        """
                <composition archetype_node_id="openEHR-EHR-COMPOSITION.test_all_types.v1" xmlns:ns2="http://schemas.openehr.org/v1"><name><value>Test all types</value></name><archetype_details><archetype_id>""");
    }

    @Test
    void marshalWithOptionsPrettyPrint() {

        Composition composition = new Composition();
        composition.setName(new DvText("pretty print test"));

        String marshal = RMDataFormat.canonicalXML().marshalWithOptions(composition, MarshalOption.PRETTY_PRINT);

        assertThat(marshal)
                .startsWith(
                        """
              <composition xmlns:ns2="http://schemas.openehr.org/v1">
                  <name>
                      <value>pretty print test</value>
                  </name>
              </composition>""");
    }

    @Test
    void marshalInline() {

        Folder folder = new Folder();
        folder.setName(new DvText("folder name"));
        folder.addItem(new PartyRef());

        String inline = new CanonicalXML().marshalInline(folder, new QName(null, "folder"));
        assertThat(inline)
                .isEqualTo(
                        """
                <name><value>folder name</value></name><items xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="PARTY_REF"/>""");
    }
}
