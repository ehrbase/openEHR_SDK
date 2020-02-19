/*
 *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  This file is part of Project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.client.introspect;

import org.apache.xmlbeans.XmlException;
import org.assertj.core.groups.Tuple;
import org.ehrbase.client.introspect.node.*;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class TemplateIntrospectTest {

    @Test
    public void introspect() throws IOException, XmlException {
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(OperationalTemplateTestData.BLOOD_PRESSURE_SIMPLE.getStream()).getTemplate();
        TemplateIntrospect cut = new TemplateIntrospect(template);

        Map<String, Node> actual = cut.getRoot().getChildren();

        assertThat(actual).isNotEmpty();
        assertThat(actual.keySet())
                .containsExactlyInAnyOrder(
                        "/context/end_time",
                        "/language",
                        "/context/health_care_facility",
                        "/composer",
                        "/context/setting",
                        "/territory",
                        "/content[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]",
                        "/context/other_context[at0001]/items[at0006]/items[openEHR-EHR-CLUSTER.sample_device.v1]",
                        "/context/location",
                        "/category",
                        "/context/start_time"
                );

        Map<Class, Long> classes = findAll(actual).stream()
                .collect(Collectors.groupingBy(EndNode::getClazz, Collectors.counting()));

        assertThat(classes.entrySet())
                .extracting(e -> e.getKey().getSimpleName(), Map.Entry::getValue)
                .containsExactlyInAnyOrder(
                        new Tuple("PartyProxy", 2L),
                        new Tuple("DvCodedText", 6L),
                        new Tuple("CodePhrase", 3L),
                        new Tuple("PartyIdentified", 1L),
                        new Tuple("DvDateTime", 6L),
                        new Tuple("DvQuantity", 5L),
                        new Tuple("DvText", 7L),
                        new Tuple("Cluster", 3L),
                        new Tuple("String", 1L)
                );

        assertThat(countNodes(actual, ArchetypeNode.class)).isEqualTo(2l);
        assertThat(countNodes(actual, EndNode.class)).isEqualTo(34l);
        assertThat(countNodes(actual, SlotNode.class)).isEqualTo(3l);
        assertThat(countNodes(actual, ChoiceNode.class)).isEqualTo(0l);
    }

    @Test
    public void introspectMultiOccurrence() throws IOException, XmlException {
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(OperationalTemplateTestData.MULTI_OCCURRENCE.getStream()).getTemplate();
        TemplateIntrospect cut = new TemplateIntrospect(template);

        Map<String, Node> actual = cut.getRoot().getChildren();

        assertThat(actual).isNotEmpty();
        assertThat(actual.keySet())
                .containsExactlyInAnyOrder(
                        "/context/end_time",
                        "/language",
                        "/context/health_care_facility",
                        "/composer",
                        "/context/setting",
                        "/territory",
                        "/content[openEHR-EHR-OBSERVATION.body_temperature.v2]",
                        "/context/other_context[at0001]/items[at0002]",
                        "/context/location",
                        "/category",
                        "/context/start_time"
                );
        Map<Class, Long> classes = findAll(actual).stream()
                .collect(Collectors.groupingBy(EndNode::getClazz, Collectors.counting()));

        assertThat(classes.entrySet())
                .extracting(e -> e.getKey().getSimpleName(), Map.Entry::getValue)
                .containsExactlyInAnyOrder(
                        new Tuple("PartyProxy", 2L),
                        new Tuple("DvCodedText", 3L),
                        new Tuple("CodePhrase", 3L),
                        new Tuple("PartyIdentified", 1L),
                        new Tuple("DvDateTime", 3L),
                        new Tuple("DvText", 1L),
                        new Tuple("Cluster", 4L),
                        new Tuple("String", 1L)
                );

        assertThat(countNodes(actual, ArchetypeNode.class)).isEqualTo(1l);
        assertThat(countNodes(actual, EndNode.class)).isEqualTo(18l);
        assertThat(countNodes(actual, SlotNode.class)).isEqualTo(6l);
        assertThat(countNodes(actual, ChoiceNode.class)).isEqualTo(1l);
    }

    @Test
    public void introspectAllTypes() throws IOException, XmlException {
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(OperationalTemplateTestData.ALL_TYPES.getStream()).getTemplate();
        TemplateIntrospect cut = new TemplateIntrospect(template);

        Map<String, Node> actual = cut.getRoot().getChildren();

        assertThat(actual).isNotEmpty();
        assertThat(actual.keySet())
                .containsExactlyInAnyOrder(
                        "/content[openEHR-EHR-EVALUATION.test_all_types.v1]",
                        "/context/end_time",
                        "/content[openEHR-EHR-SECTION.test_all_types.v1]",
                        "/language",
                        "/context/health_care_facility",
                        "/composer",
                        "/context/setting",
                        "/territory",
                        "/context/other_context[at0004]/item[at0005]/value",
                        "/content[openEHR-EHR-OBSERVATION.test_all_types.v1]",
                        "/context/location",
                        "/category",
                        "/context/start_time"
                );
        assertThat(((ArchetypeNode) actual.get("/content[openEHR-EHR-SECTION.test_all_types.v1]")).getChildren().keySet())
                .containsExactlyInAnyOrder(
                        "/items[at0001]/items[at0002]/items[openEHR-EHR-ACTION.test_all_types.v1]",
                        "/items[at0001]/items[at0002]/items[openEHR-EHR-INSTRUCTION.test_all_types.v1]",
                        "/items[at0001]/items[openEHR-EHR-ADMIN_ENTRY.test_all_types.v1]"
                );

        Map<Class, Long> classes = findAll(actual).stream()
                .collect(Collectors.groupingBy(EndNode::getClazz, Collectors.counting()));

        assertThat(classes.entrySet())
                .extracting(e -> e.getKey().getSimpleName(), Map.Entry::getValue)
                .containsExactlyInAnyOrder(
                        new Tuple("PartyProxy", 4L),
                        new Tuple("DvDate", 2L),
                        new Tuple("DvMultimedia", 1L),
                        new Tuple("DvCodedText", 14L),
                        new Tuple("DvURI", 1L),
                        new Tuple("CodePhrase", 6L),
                        new Tuple("DvParsable", 1L),
                        new Tuple("DvOrdinal", 1L),
                        new Tuple("DvCount", 3L),
                        new Tuple("DvTime", 1L),
                        new Tuple("PartyIdentified", 1L),
                        new Tuple("DvDateTime", 8L),
                        new Tuple("DvQuantity", 2L),
                        new Tuple("DvProportion", 1L),
                        new Tuple("DvInterval", 3L),
                        new Tuple("DvText", 3L),
                        new Tuple("DvDuration", 1L),
                        new Tuple("String", 2L),
                        new Tuple("DvBoolean", 2L),
                        new Tuple("DvIdentifier", 1L),
                        new Tuple("ItemStructure", 1L)
                );

        assertThat(countNodes(actual, ArchetypeNode.class)).isEqualTo(7l);
        assertThat(countNodes(actual, EndNode.class)).isEqualTo(59l);
        assertThat(countNodes(actual, SlotNode.class)).isEqualTo(2l);
        assertThat(countNodes(actual, ChoiceNode.class)).isEqualTo(1l);
    }

    private List<EndNode> findAll(Map<String, Node> nodeMap) {
        List<EndNode> nodes = new ArrayList<>();
        for (Node node : nodeMap.values()) {
            if (EndNode.class.isAssignableFrom(node.getClass())) {
                nodes.add((EndNode) node);
            } else if (ArchetypeNode.class.isAssignableFrom(node.getClass())) {
                nodes.addAll(findAll(((ArchetypeNode) node).getChildren()));
            } else if (ChoiceNode.class.isAssignableFrom(node.getClass())) {
                nodes.addAll(((ChoiceNode) node).getNodes());
            }
        }
        return nodes;
    }

    private long countNodes(Map<String, Node> nodeMap, Class nodeClass) {
        Long collect = nodeMap.values().stream().filter(n -> nodeClass.isAssignableFrom(n.getClass())).count();

        for (Node node : nodeMap.values()) {
            if (ArchetypeNode.class.isAssignableFrom(node.getClass())) {
                collect += countNodes(((ArchetypeNode) node).getChildren(), nodeClass);
            } else if (ChoiceNode.class.isAssignableFrom(node.getClass()) && EndNode.class.isAssignableFrom(nodeClass)) {
                collect += ((ChoiceNode) node).getNodes().size();
            }
        }

        return collect;
    }
}