/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.serialisation.flatencoding.std.umarshal;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.JsonNode;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.Observation;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datavalues.quantity.DvQuantity;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDate;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvTime;
import com.nedap.archie.rm.generic.PartySelf;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlException;
import org.assertj.core.api.Assertions;
import org.ehrbase.serialisation.exception.UnmarshalException;
import org.ehrbase.serialisation.jsonencoding.ArchieObjectMapperProvider;
import org.ehrbase.test_data.composition.CompositionTestDataConformanceSDTJson;
import org.ehrbase.test_data.composition.CompositionTestDataSimSDTJson;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.parser.OPTParser;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

public class FlatJsonUnmarshallerTest {

    private Object thing;

    @Test
    public void unmarshal() throws IOException, XmlException {
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(
                        OperationalTemplateTestData.CORONA_ANAMNESE.getStream())
                .getTemplate();
        WebTemplate webTemplate = new OPTParser(template).parse();

        FlatJsonUnmarshaller cut = new FlatJsonUnmarshaller();

        String flat = IOUtils.toString(CompositionTestDataSimSDTJson.CORONA.getStream(), StandardCharsets.UTF_8);

        Composition actual = cut.unmarshal(flat, webTemplate);

        assertThat(actual).isNotNull();

        Observation observation = (Observation) actual.itemAtPath("/content[openEHR-EHR-OBSERVATION.story.v1]");
        assertThat(observation.getData().getOrigin().getValue()).hasToString("2020-05-11T22:53:12.039139+02:00");
        assertThat(observation.getSubject()).isNotNull();
        assertThat(observation.getSubject().getClass()).isEqualTo(PartySelf.class);
    }

    @Test
    public void unmarshalUuid() throws IOException, XmlException {
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(
                        OperationalTemplateTestData.CONFORMANCE.getStream())
                .getTemplate();
        WebTemplate webTemplate = new OPTParser(template).parse();

        FlatJsonUnmarshaller cut = new FlatJsonUnmarshaller();

        String flat = IOUtils.toString(
                CompositionTestDataConformanceSDTJson.EHRBASE_CONFORMANCE_OBSERVATION.getStream(),
                StandardCharsets.UTF_8);

        Composition actual = cut.unmarshal(flat, webTemplate);

        assertThat(actual).isNotNull();

        Observation observation = (Observation)
                actual.itemAtPath(
                        "/content[openEHR-EHR-SECTION.conformance_section.v0]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0]");
        assertThat(observation.getUid().getValue()).hasToString("9fcc1c70-9349-444d-b9cb-8fa817697f5e");
        assertThat(observation.getData().getUid()).isNull();
    }

    @Test
    public void unmarshalMulti() throws IOException, XmlException {
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(
                        OperationalTemplateTestData.MULTI_OCCURRENCE.getStream())
                .getTemplate();
        WebTemplate webTemplate = new OPTParser(template).parse();

        FlatJsonUnmarshaller cut = new FlatJsonUnmarshaller();

        String flat =
                IOUtils.toString(CompositionTestDataSimSDTJson.MULTI_OCCURRENCE.getStream(), StandardCharsets.UTF_8);

        Composition actual = cut.unmarshal(flat, webTemplate);

        assertThat(actual).isNotNull();
    }

    @Test
    public void unmarshalAlt() throws IOException, XmlException {
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(
                        OperationalTemplateTestData.ALT_EVENTS.getStream())
                .getTemplate();
        WebTemplate webTemplate = new OPTParser(template).parse();

        FlatJsonUnmarshaller cut = new FlatJsonUnmarshaller();

        String flat =
                IOUtils.toString(CompositionTestDataSimSDTJson.ALTERNATIVE_EVENTS.getStream(), StandardCharsets.UTF_8);

        Composition actual = cut.unmarshal(flat, webTemplate);

        assertThat(actual).isNotNull();
    }

    @Test
    public void unmarshalAllTypes() throws IOException, XmlException {
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(OperationalTemplateTestData.ALL_TYPES.getStream())
                .getTemplate();
        WebTemplate webTemplate = new OPTParser(template).parse();

        FlatJsonUnmarshaller cut = new FlatJsonUnmarshaller();

        String flat = IOUtils.toString(CompositionTestDataSimSDTJson.ALL_TYPES.getStream(), StandardCharsets.UTF_8);

        Composition actual = cut.unmarshal(flat, webTemplate);

        assertThat(actual).isNotNull();

        // Choice Node was correctly parsed
        Object choice = actual.itemAtPath(
                "/content[openEHR-EHR-EVALUATION.test_all_types.v1]/data[at0001]/items[at0009]/value");
        assertThat(choice).isNotNull();
        assertThat(choice.getClass()).isEqualTo(DvQuantity.class);
        assertThat(((DvQuantity) choice).getMagnitude()).isEqualTo(148.01210165023804d);
        assertThat(((DvQuantity) choice).getUnits()).isEqualTo("mm[H20]");
    }

    @Test
    public void unmarshalDeterioriationAssessment() throws IOException, XmlException {
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(
                        OperationalTemplateTestData.DETERIORIATION_ASSESSMENT.getStream())
                .getTemplate();
        WebTemplate webTemplate = new OPTParser(template).parse();

        FlatJsonUnmarshaller cut = new FlatJsonUnmarshaller();

        String flat = IOUtils.toString(
                CompositionTestDataSimSDTJson.DETERIORIATION_ASSESSMENT.getStream(), StandardCharsets.UTF_8);

        Composition actual = cut.unmarshal(flat, webTemplate);

        assertThat(actual).isNotNull();
    }

    @Test
    public void unmarshallNestedComposition() throws Exception {
        var optTemplate = TemplateDocument.Factory.parse(OperationalTemplateTestData.NESTED.getStream())
                .getTemplate();
        var webTemplate = new OPTParser(optTemplate).parse();

        var json = IOUtils.toString(CompositionTestDataSimSDTJson.NESTED.getStream(), StandardCharsets.UTF_8);

        var composition = new FlatJsonUnmarshaller().unmarshal(json, webTemplate);

        assertThat(composition).isNotNull();
    }

    @Test
    public void unmarshallIpsComposition() throws Exception {
        var optTemplate = TemplateDocument.Factory.parse(OperationalTemplateTestData.IPS.getStream())
                .getTemplate();
        var webTemplate = new OPTParser(optTemplate).parse();

        var json = IOUtils.toString(CompositionTestDataSimSDTJson.IPS.getStream(), StandardCharsets.UTF_8);

        var composition = new FlatJsonUnmarshaller().unmarshal(json, webTemplate);

        assertThat(composition).isNotNull();
    }

    @Test
    public void DvDateTimeTest() throws Exception {
        var optTemplate = TemplateDocument.Factory.parse(OperationalTemplateTestData.CONFORMANCE.getStream())
                .getTemplate();
        var webTemplate = new OPTParser(optTemplate).parse();
        Map<String, String> flat = getKeyValueFromFlatJson(
                CompositionTestDataConformanceSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_DATE_TIME.getStream());

        String key = "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_date_time";
        FlatJsonUnmarshaller cut = new FlatJsonUnmarshaller();

        for (TestCase testCase : Arrays.asList(
                new TestCase("2022-11-01", "2022-11-01"),
                new TestCase("2022-11", "2022-11"),
                new TestCase("2022", "2022"),
                new TestCase("2022-11-01T13", "2022-11-01T13:00"),
                new TestCase("2022-11-01T13+02:00", "2022-11-01T13:00+02:00"))) {
            flat.put(key, testCase.input);
            String json = ArchieObjectMapperProvider.getObjectMapper().writeValueAsString(flat);
            Composition actual = cut.unmarshal(json, webTemplate);
            assertThat(actual).isNotNull();
            Element element = (Element)
                    actual.itemAtPath(
                            "/content[openEHR-EHR-SECTION.conformance_section.v0]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0011]");
            assertThat(element).isNotNull();
            DvDateTime dateTime = (DvDateTime) element.getValue();
            assertThat(dateTime.getValue().toString()).isEqualTo(testCase.expected);
        }
    }

    @Test
    public void DvTimeTest() throws Exception {
        var optTemplate = TemplateDocument.Factory.parse(OperationalTemplateTestData.CONFORMANCE.getStream())
                .getTemplate();
        var webTemplate = new OPTParser(optTemplate).parse();
        Map<String, String> flat = getKeyValueFromFlatJson(
                CompositionTestDataConformanceSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_DATE_TIME.getStream());

        String key = "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_time";
        FlatJsonUnmarshaller cut = new FlatJsonUnmarshaller();

        for (TestCase testCase : Arrays.asList(
                new TestCase("13:12:11", "13:12:11"),
                new TestCase("13:12", "13:12"),
                new TestCase("13", "13:00"),
                new TestCase("13+02:00", "13:00+02:00"),
                new TestCase("2022-11", null),
                new TestCase("2022-11-01", null),
                new TestCase("2022-11-01T13", null))) {
            flat.put(key, testCase.input);
            String json = ArchieObjectMapperProvider.getObjectMapper().writeValueAsString(flat);
            Composition actual;
            try {
                actual = cut.unmarshal(json, webTemplate);
            } catch (UnmarshalException ex) {
                if (testCase.expected != null) {
                    Assertions.fail("should not have failed", ex);
                }
                return;
            }
            if (testCase.expected == null) {
                Assertions.fail(testCase.input + " should have failed");
                return;
            }
            assertThat(actual).isNotNull();
            Element element = (Element)
                    actual.itemAtPath(
                            "/content[openEHR-EHR-SECTION.conformance_section.v0]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0012]");
            assertThat(element).isNotNull();
            DvTime time = (DvTime) element.getValue();
            assertThat(time.getValue().toString()).isEqualTo(testCase.expected);
        }
    }

    @Test
    public void DvDateTest() throws Exception {
        var optTemplate = TemplateDocument.Factory.parse(OperationalTemplateTestData.CONFORMANCE.getStream())
                .getTemplate();
        var webTemplate = new OPTParser(optTemplate).parse();
        Map<String, String> flat = getKeyValueFromFlatJson(
                CompositionTestDataConformanceSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_DATE_TIME.getStream());

        String key = "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_date";
        FlatJsonUnmarshaller cut = new FlatJsonUnmarshaller();

        for (TestCase testCase : Arrays.asList(
                new TestCase("2022-11-01", "2022-11-01"),
                new TestCase("2022-11", "2022-11"),
                new TestCase("2022", "2022"),
                new TestCase("2022-11-01T13", null),
                new TestCase("13:00:00", null),
                new TestCase("T13", null))) {
            flat.put(key, testCase.input);
            String json = ArchieObjectMapperProvider.getObjectMapper().writeValueAsString(flat);
            Composition actual;
            try {
                actual = cut.unmarshal(json, webTemplate);
            } catch (UnmarshalException ex) {
                if (testCase.expected != null) {
                    Assertions.fail("should not have failed", ex);
                }
                return;
            }
            if (testCase.expected == null) {
                Assertions.fail(testCase.input + " should have failed");
                return;
            }
            assertThat(actual).isNotNull();
            Element element = (Element)
                    actual.itemAtPath(
                            "/content[openEHR-EHR-SECTION.conformance_section.v0]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0013]");
            assertThat(element).isNotNull();
            DvDate date = (DvDate) element.getValue();
            assertThat(date.getValue().toString()).isEqualTo(testCase.expected);
        }
    }

    private static class TestCase {
        public final String input;
        public final String expected;

        public TestCase(String input, String expected) {
            this.input = input;
            this.expected = expected;
        }
    }

    private Map<String, String> getKeyValueFromFlatJson(InputStream in) throws IOException {
        Map<String, String> currentValues = new HashMap<>();
        for (Iterator<Map.Entry<String, JsonNode>> it = ArchieObjectMapperProvider.getObjectMapper()
                        .readTree(IOUtils.toString(in, StandardCharsets.UTF_8))
                        .fields();
                it.hasNext(); ) {
            Map.Entry<String, JsonNode> e = it.next();
            currentValues.put(e.getKey(), e.getValue().textValue());
        }
        return currentValues;
    }
}
