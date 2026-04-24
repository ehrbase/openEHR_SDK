/*
 * Copyright (c) 2026 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.terminology.openehr;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Tests for {@link OpenEHRTerminologyGroupIdentifiers} and {@link OpenEHRCodeSetIdentifiers}
 */
class OpenEHRTerminologyIdentifiersTest {

    @Test
    void checkAgainstArchieXml() {
        var archieContainers = listArchieOpenehrTerminologyContainers();

        var archieByContainerType = archieContainers.stream()
                .collect(Collectors.groupingBy(Pair::getValue, Collectors.toMap(Pair::getKey, Pair::getValue)));

        for (OpenEHRCodeSetIdentifiers value : OpenEHRCodeSetIdentifiers.values()) {
            assertThat(archieByContainerType.get(ContainerType.CODESET)).containsKey(value.getValue());
        }
        for (OpenEHRTerminologyGroupIdentifiers value : OpenEHRTerminologyGroupIdentifiers.values()) {
            assertThat(archieByContainerType.get(ContainerType.GROUP)).containsKey(value.getValue());
        }

        var codesetIds = Arrays.stream(OpenEHRCodeSetIdentifiers.values()).map(OpenEHRCodeSetIdentifiers::getValue);
        var groupIds = Arrays.stream(OpenEHRTerminologyGroupIdentifiers.values())
                .map(OpenEHRTerminologyGroupIdentifiers::getValue);

        assertThat(codesetIds)
                .containsExactlyInAnyOrderElementsOf(
                        archieByContainerType.get(ContainerType.CODESET).keySet());

        // XXX CDR-2273 Missing: "MultiMedia", "extract update trigger event type", "extract content type", "extract
        // action type"
        assertThat(groupIds)
                .containsExactlyInAnyOrderElementsOf(
                        archieByContainerType.get(ContainerType.GROUP).keySet());
    }

    static List<Pair<String, ContainerType>> listArchieOpenehrTerminologyContainers() {
        List<Pair<String, ContainerType>> containers = new ArrayList<>();
        // see OpenEHRTerminologyAccess
        addTerminologyContainers(containers, "/openEHR_RM/en/openehr_terminology.xml");
        addTerminologyContainers(containers, "/openEHR_RM/openehr_external_terminologies.xml");
        return containers;
    }

    static void addTerminologyContainers(List<Pair<String, ContainerType>> containers, String resource) {
        try (InputStream is = OpenEHRTerminologyIdentifiersTest.class.getResourceAsStream(resource)) {
            if (is == null) {
                throw new IllegalStateException("Resource not found: " + resource);
            }
            SAXParserFactory.newInstance().newSAXParser().parse(is, new DefaultHandler() {
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    if ("codeset".equals(qName)) {
                        containers.add(Pair.of(attributes.getValue("openehr_id"), ContainerType.CODESET));
                    } else if ("group".equals(qName)) {
                        containers.add(Pair.of(attributes.getValue("id"), ContainerType.GROUP));
                    }
                }
            });
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
