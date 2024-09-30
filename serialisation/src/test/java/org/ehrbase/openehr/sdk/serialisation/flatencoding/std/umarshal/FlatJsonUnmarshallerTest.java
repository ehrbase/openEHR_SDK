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
package org.ehrbase.openehr.sdk.serialisation.flatencoding.std.umarshal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.Observation;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia;
import com.nedap.archie.rm.datavalues.quantity.DvQuantity;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDate;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvTime;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.HierObjectId;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.ehrbase.openehr.sdk.serialisation.exception.UnmarshalException;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.ArchieObjectMapperProvider;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataConformanceSDTJson;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataSimSDTJson;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataSimSDTJsonInterface;
import org.ehrbase.openehr.sdk.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplate;
import org.ehrbase.openehr.sdk.webtemplate.parser.OPTParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

class FlatJsonUnmarshallerTest {

    private final ObjectMapper mapper = ArchieObjectMapperProvider.getObjectMapper();

    @Test
    void unmarshal() {

        Composition composition = unmarshallComposition(
                CompositionTestDataSimSDTJson.CORONA, OperationalTemplateTestData.CORONA_ANAMNESE);
        Observation observation = (Observation) composition.itemAtPath("/content[openEHR-EHR-OBSERVATION.story.v1]");
        assertThat(observation.getData().getOrigin().getValue()).hasToString("2020-05-11T22:53:12.039139+02:00");
        assertThat(observation.getSubject()).isNotNull();
        assertThat(observation.getSubject().getClass()).isEqualTo(PartySelf.class);
    }

    @Test
    void unmarshalUid() {

        Composition composition = unmarshallComposition(
                CompositionTestDataConformanceSDTJson.EHRBASE_CONFORMANCE_OBSERVATION,
                OperationalTemplateTestData.CONFORMANCE);
        Observation observation = (Observation)
                composition.itemAtPath(
                        "/content[openEHR-EHR-SECTION.conformance_section.v0]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0]");
        assertThat(observation.getUid()).hasToString("9fcc1c70-9349-444d-b9cb-8fa817697f5e");
        assertThat(observation.getData().getUid()).isNull();
    }

    @Test
    void unmarshalHierObjectId() throws Exception {

        WebTemplate webTemplate = webTemplateFromOTP(OperationalTemplateTestData.CONFORMANCE);
        Map<String, String> data =
                flatCompostionMap(CompositionTestDataConformanceSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_DATE_TIME);

        data.put("conformance-ehrbase.de.v0/_uid", "0e8c2c01-b92d-453e-828e-d33eb721b5a7");
        String flatJson = mapper.writeValueAsString(data);

        Composition composition = unmarshallComposition(flatJson, webTemplate);
        assertThat(composition.getUid())
                .isNotNull()
                .isInstanceOf(HierObjectId.class)
                .hasToString("0e8c2c01-b92d-453e-828e-d33eb721b5a7");
    }

    @Test
    void unmarshalObjectVersionId() throws Exception {

        WebTemplate webTemplate = webTemplateFromOTP(OperationalTemplateTestData.CONFORMANCE);
        Map<String, String> data =
                flatCompostionMap(CompositionTestDataConformanceSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_DATE_TIME);

        data.put("conformance-ehrbase.de.v0/_uid", "0e8c2c01-b92d-453e-828e-d33eb721b5a7::test.ehrbase::42");
        String flatJson = mapper.writeValueAsString(data);

        Composition composition = unmarshallComposition(flatJson, webTemplate);
        assertThat(composition.getUid())
                .isNotNull()
                .isInstanceOf(ObjectVersionId.class)
                .hasToString("0e8c2c01-b92d-453e-828e-d33eb721b5a7::test.ehrbase::42");
    }

    @Test
    void unmarshalMulti() {

        Composition composition = unmarshallComposition(
                CompositionTestDataSimSDTJson.MULTI_OCCURRENCE, OperationalTemplateTestData.MULTI_OCCURRENCE);
        assertThat(composition).isNotNull();
    }

    @Test
    void unmarshalAlt() {

        Composition composition = unmarshallComposition(
                CompositionTestDataSimSDTJson.ALTERNATIVE_EVENTS, OperationalTemplateTestData.ALT_EVENTS);
        assertThat(composition).isNotNull();
    }

    @Test
    void unmarshalAllTypes() {

        Composition composition =
                unmarshallComposition(CompositionTestDataSimSDTJson.ALL_TYPES, OperationalTemplateTestData.ALL_TYPES);

        // Choice Node was correctly parsed
        DvQuantity choice = (DvQuantity) composition.itemAtPath(
                "/content[openEHR-EHR-EVALUATION.test_all_types.v1]/data[at0001]/items[at0009]/value");
        assertThat(choice.getMagnitude()).isEqualTo(148.01210165023804d);
        assertThat(choice.getUnits()).isEqualTo("mm[H20]");
    }

    @Test
    void unmarshalDeterioriationAssessment() {

        Composition composition = unmarshallComposition(
                CompositionTestDataSimSDTJson.DETERIORIATION_ASSESSMENT,
                OperationalTemplateTestData.DETERIORIATION_ASSESSMENT);
        assertThat(composition).isNotNull();
    }

    @Test
    void unmarshallNestedComposition() {

        Composition composition =
                unmarshallComposition(CompositionTestDataSimSDTJson.NESTED, OperationalTemplateTestData.NESTED);
        assertThat(composition).isNotNull();
    }

    @Test
    void unmarshallIpsComposition() {

        Composition composition =
                unmarshallComposition(CompositionTestDataSimSDTJson.IPS, OperationalTemplateTestData.IPS);
        assertThat(composition).isNotNull();
    }

    @ParameterizedTest
    @CsvSource(
            textBlock =
                    """
                        2022-11-01|2022-11-01
                        2022-11|2022-11
                        2022|2022
                        2022-11-01T13|2022-11-01T13:00
                        2022-11-01T13+02:00|2022-11-01T13:00+02:00
                    """,
            delimiterString = "|")
    void validDvDateTime(String input, String expected) throws Exception {

        WebTemplate webTemplate = webTemplateFromOTP(OperationalTemplateTestData.CONFORMANCE);
        Map<String, String> flat =
                flatCompostionMap(CompositionTestDataConformanceSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_DATE_TIME);

        String key = "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_date_time";

        flat.put(key, input);
        String json = mapper.writeValueAsString(flat);

        Composition composition = new FlatJsonUnmarshaller().unmarshal(json, webTemplate);
        Element element = (Element)
                composition.itemAtPath(
                        "/content[openEHR-EHR-SECTION.conformance_section.v0]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0011]");
        assertThat(element).isNotNull();
        DvDateTime dateTime = (DvDateTime) element.getValue();
        assertThat(dateTime.getValue()).hasToString(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2022-11", "2022-11-01", "2022-11-01T13"})
    void invalidDvTimeTest(String input) throws Exception {

        WebTemplate webTemplate = webTemplateFromOTP(OperationalTemplateTestData.CONFORMANCE);
        Map<String, String> flat =
                flatCompostionMap(CompositionTestDataConformanceSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_DATE_TIME);

        String key = "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_time";

        flat.put(key, input);
        String json = mapper.writeValueAsString(flat);

        FlatJsonUnmarshaller unmarshaller = new FlatJsonUnmarshaller();
        assertThatThrownBy(() -> unmarshaller.unmarshal(json, webTemplate))
                .isInstanceOf(UnmarshalException.class)
                .hasMessageContaining("Text '%s' could not be parsed, unparsed text found".formatted(input));
    }

    @ParameterizedTest
    @CsvSource(
            textBlock =
                    """
                13:12:11|13:12:11
                13:12|13:12
                13|13:00
                13+02:00|13:00+02:00
            """,
            delimiterString = "|")
    void validDvTimeTest(String input, String expected) throws Exception {

        WebTemplate webTemplate = webTemplateFromOTP(OperationalTemplateTestData.CONFORMANCE);
        Map<String, String> flat =
                flatCompostionMap(CompositionTestDataConformanceSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_DATE_TIME);

        String key = "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_time";

        flat.put(key, input);
        String json = mapper.writeValueAsString(flat);

        Composition composition = new FlatJsonUnmarshaller().unmarshal(json, webTemplate);
        DvTime time = (DvTime)
                composition.itemAtPath(
                        "/content[openEHR-EHR-SECTION.conformance_section.v0]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0012]/value");
        assertThat(time.getValue()).hasToString(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2022-11-01T13", "13:00:00", "T13"})
    void invalidDvDateTest(String input) throws Exception {

        WebTemplate webTemplate = webTemplateFromOTP(OperationalTemplateTestData.CONFORMANCE);
        Map<String, String> flat =
                flatCompostionMap(CompositionTestDataConformanceSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_DATE_TIME);

        String key = "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_date";

        flat.put(key, input);
        String json = mapper.writeValueAsString(flat);

        FlatJsonUnmarshaller unmarshaller = new FlatJsonUnmarshaller();
        assertThatThrownBy(() -> unmarshaller.unmarshal(json, webTemplate))
                .isInstanceOf(UnmarshalException.class)
                .hasMessageContaining("Text '%s' could not be parsed".formatted(input));
    }

    @ParameterizedTest
    @CsvSource(
            textBlock =
                    """
                2022-11-01|2022-11-01
                2022-11|2022-11
                2022|2022
            """,
            delimiterString = "|")
    void validDvDateTest(String input, String expected) throws Exception {

        WebTemplate webTemplate = webTemplateFromOTP(OperationalTemplateTestData.CONFORMANCE);
        Map<String, String> flat =
                flatCompostionMap(CompositionTestDataConformanceSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_DATE_TIME);

        String key = "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_date";

        flat.put(key, input);
        String json = mapper.writeValueAsString(flat);

        Composition composition = new FlatJsonUnmarshaller().unmarshal(json, webTemplate);
        DvDate date = (DvDate)
                composition.itemAtPath(
                        "/content[openEHR-EHR-SECTION.conformance_section.v0]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0013]/value");
        assertThat(date.getValue()).hasToString(expected);
    }

    @Test
    void dvMultimediaTestDataBase64Decoded() {

        Composition composition = unmarshallComposition(
                CompositionTestDataSimSDTJson.EHRN_ABDM_OP_CONSULT_RECORD,
                OperationalTemplateTestData.EHRN_ABDM_OP_CONSULT_RECORD);

        Object object = composition.itemAtPath(
                "/content[openEHR-EHR-ADMIN_ENTRY.document_attachment.v0]/data[at0003]/items[openEHR-EHR-CLUSTER.media_file.v1]/items[at0001]/value");
        assertThat(object).isInstanceOf(DvMultimedia.class).satisfies(obj -> {
            var dvMultimedia = (DvMultimedia) obj;
            assertThat(dvMultimedia.getData()).isNotNull().satisfies(bytes -> assertThat(new String(bytes))
                    .isEqualTo("Shall Be Base64 encoded"));
        });
    }

    // -- Helper --

    private static WebTemplate webTemplateFromOTP(OperationalTemplateTestData data) {
        WebTemplate webTemplate = null;
        try (var in = data.getStream()) {
            OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(in).getTemplate();
            webTemplate = new OPTParser(template).parse();
            assertThat(webTemplate).isNotNull();
        } catch (Exception e) {
            fail(e);
        }
        return webTemplate;
    }

    private static Composition unmarshallComposition(
            CompositionTestDataSimSDTJsonInterface data, OperationalTemplateTestData templateData) {
        WebTemplate webTemplate = webTemplateFromOTP(templateData);
        return unmarshallComposition(data, webTemplate);
    }

    private static Composition unmarshallComposition(
            CompositionTestDataSimSDTJsonInterface data, WebTemplate webTemplate) {
        Composition composition = null;
        try (var in = data.getStream()) {
            String content = IOUtils.toString(in, StandardCharsets.UTF_8);
            composition = unmarshallComposition(content, webTemplate);
        } catch (Exception e) {
            fail(e);
        }
        return composition;
    }

    private static Composition unmarshallComposition(String flatJson, WebTemplate webTemplate) {

        return new FlatJsonUnmarshaller().unmarshal(flatJson, webTemplate);
    }

    private Map<String, String> flatCompostionMap(CompositionTestDataSimSDTJsonInterface data) {
        Map<String, String> currentValues = new HashMap<>();
        try (var in = data.getStream()) {
            for (Iterator<Map.Entry<String, JsonNode>> it = ArchieObjectMapperProvider.getObjectMapper()
                            .readTree(IOUtils.toString(in, StandardCharsets.UTF_8))
                            .fields();
                    it.hasNext(); ) {
                Map.Entry<String, JsonNode> e = it.next();
                currentValues.put(e.getKey(), e.getValue().textValue());
            }
        } catch (Throwable e) {
            fail(e);
        }
        return currentValues;
    }
}
