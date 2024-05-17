/*
 * Copyright (c) 2021 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.conformance_test.extern.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ehrbase.openehr.sdk.conformance_test.extern.Helper.getFlatJson;

import care.better.platform.web.template.StatusesTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datavalues.quantity.DvCount;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import com.nedap.archie.rm.datavalues.quantity.DvQuantity;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDate;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvTime;
import java.util.Map;
import org.ehrbase.openehr.sdk.serialisation.RMDataFormat;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.FlatFormat;
import org.junit.Test;

public class StatusesTestOverwrite extends StatusesTest {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    @Test
    public void ordinal() throws Exception {
        String template = this.getFileContent("/res/test_statuses.opt");
        Map<String, Object> flatComposition = Map.of(
                "ctx/language",
                "sl",
                "ctx/territory",
                "SI",
                "ctx/id_scheme",
                "ispek",
                "ctx/id_namespace",
                "ispek",
                "ctx/composer_name",
                "George Orwell",
                "test_statuses/test_statuses:0/ordinal",
                "at0006",
                "test_statuses/test_statuses:0/ordinal|normal_status",
                "L");

        RMDataFormat flatJson = getFlatJson(template, FlatFormat.SIM_SDT);
        Composition composition = flatJson.unmarshal(OBJECT_MAPPER.writeValueAsString(flatComposition));
        DvOrdinal ordinal = (DvOrdinal)
                composition.itemAtPath(
                        "/content[openEHR-EHR-OBSERVATION.test_statuses.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value");

        assertThat(ordinal.getNormalStatus()).isNotNull();
        assertThat(ordinal.getNormalStatus().getTerminologyId().getValue()).isEqualTo("openehr_normal_statuses");
        assertThat(ordinal.getNormalStatus().getCodeString()).isEqualTo("L");

        Map<String, Object> value = OBJECT_MAPPER.readValue(flatJson.marshal(composition), Map.class);
        assertThat(value).containsEntry("test_statuses/test_statuses:0/ordinal|normal_status", "L");
    }

    @Override
    @Test
    public void ordinalN() throws Exception {
        String template = this.getFileContent("/res/test_statuses.opt");
        Map<String, Object> flatComposition = Map.of(
                "ctx/language",
                "sl",
                "ctx/territory",
                "SI",
                "ctx/id_scheme",
                "ispek",
                "ctx/id_namespace",
                "ispek",
                "ctx/composer_name",
                "George Orwell",
                "test_statuses/test_statuses:0/ordinal",
                "at0006",
                "test_statuses/test_statuses:0/ordinal|normal_status",
                "N");

        RMDataFormat flatJson = getFlatJson(template, FlatFormat.SIM_SDT);
        Composition composition = flatJson.unmarshal(OBJECT_MAPPER.writeValueAsString(flatComposition));
        DvOrdinal ordinal = (DvOrdinal)
                composition.itemAtPath(
                        "/content[openEHR-EHR-OBSERVATION.test_statuses.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value");

        assertThat(ordinal.getNormalStatus()).isNotNull();
        assertThat(ordinal.getNormalStatus().getTerminologyId().getValue()).isEqualTo("openehr_normal_statuses");
        assertThat(ordinal.getNormalStatus().getCodeString()).isEqualTo("N");

        Map<String, Object> value = OBJECT_MAPPER.readValue(flatJson.marshal(composition), Map.class);
        assertThat(value).containsEntry("test_statuses/test_statuses:0/ordinal|normal_status", "N");
    }

    @Override
    @Test
    public void duration() throws Exception {
        String template = this.getFileContent("/res/test_statuses.opt");
        Map<String, Object> flatComposition = Map.of(
                "ctx/language",
                "sl",
                "ctx/territory",
                "SI",
                "ctx/id_scheme",
                "ispek",
                "ctx/id_namespace",
                "ispek",
                "ctx/composer_name",
                "George Orwell",
                "test_statuses/test_statuses:0/duration",
                "P1Y",
                "test_statuses/test_statuses:0/duration|normal_status",
                "L",
                "test_statuses/test_statuses:0/duration|magnitude_status",
                ">=",
                "test_statuses/test_statuses:0/duration|accuracy",
                10.0,
                "test_statuses/test_statuses:0/duration|accuracy_is_percent",
                true);

        RMDataFormat flatJson = getFlatJson(template, FlatFormat.SIM_SDT);
        Composition composition = flatJson.unmarshal(OBJECT_MAPPER.writeValueAsString(flatComposition));
        DvDuration rmObject = (DvDuration)
                composition.itemAtPath(
                        "/content[openEHR-EHR-OBSERVATION.test_statuses.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0009]/value");

        assertThat(rmObject.getNormalStatus()).isNotNull();
        assertThat(rmObject.getNormalStatus().getTerminologyId().getValue()).isEqualTo("openehr_normal_statuses");
        assertThat(rmObject.getNormalStatus().getCodeString()).isEqualTo("L");
        assertThat(rmObject.getMagnitudeStatus()).isEqualTo(">=");
        assertThat(rmObject.getAccuracy()).isEqualTo(10);
        assertThat(rmObject.getAccuracyIsPercent()).isTrue();

        Map<String, Object> value = OBJECT_MAPPER.readValue(flatJson.marshal(composition), Map.class);
        assertThat(value)
                .containsEntry("test_statuses/test_statuses:0/duration|normal_status", "L")
                .containsEntry("test_statuses/test_statuses:0/duration|magnitude_status", ">=")
                .containsEntry("test_statuses/test_statuses:0/duration|accuracy", 10.0)
                .containsEntry("test_statuses/test_statuses:0/duration|accuracy_is_percent", true);
    }

    @Override
    @Test
    public void quantity() throws Exception {
        String template = this.getFileContent("/res/test_statuses.opt");
        Map<String, Object> flatComposition = Map.of(
                "ctx/language",
                "sl",
                "ctx/territory",
                "SI",
                "ctx/composer_name",
                "George Orwell",
                "test_statuses/test_statuses:0/quantity|unit",
                "m",
                "test_statuses/test_statuses:0/quantity|magnitude",
                1,
                "test_statuses/test_statuses:0/quantity|normal_status",
                "L",
                "test_statuses/test_statuses:0/quantity|magnitude_status",
                ">=",
                "test_statuses/test_statuses:0/quantity|accuracy",
                10.0,
                "test_statuses/test_statuses:0/quantity|accuracy_is_percent",
                true);

        RMDataFormat flatJson = getFlatJson(template, FlatFormat.SIM_SDT);
        Composition composition = flatJson.unmarshal(OBJECT_MAPPER.writeValueAsString(flatComposition));
        DvQuantity rmObject = (DvQuantity)
                composition.itemAtPath(
                        "/content[openEHR-EHR-OBSERVATION.test_statuses.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0010]/value");

        assertThat(rmObject.getNormalStatus()).isNotNull();
        assertThat(rmObject.getNormalStatus().getTerminologyId().getValue()).isEqualTo("openehr_normal_statuses");
        assertThat(rmObject.getNormalStatus().getCodeString()).isEqualTo("L");
        assertThat(rmObject.getMagnitudeStatus()).isEqualTo(">=");
        assertThat(rmObject.getAccuracy()).isEqualTo(10);
        assertThat(rmObject.getAccuracyIsPercent()).isTrue();

        Map<String, Object> value = OBJECT_MAPPER.readValue(flatJson.marshal(composition), Map.class);
        assertThat(value)
                .containsEntry("test_statuses/test_statuses:0/quantity|normal_status", "L")
                .containsEntry("test_statuses/test_statuses:0/quantity|magnitude_status", ">=")
                .containsEntry("test_statuses/test_statuses:0/quantity|accuracy", 10.0)
                .containsEntry("test_statuses/test_statuses:0/quantity|accuracy_is_percent", true);
    }

    @Override
    @Test
    public void date() throws Exception {
        String template = this.getFileContent("/res/test_statuses.opt");
        Map<String, Object> flatComposition = Map.of(
                "ctx/language",
                "sl",
                "ctx/territory",
                "SI",
                "ctx/composer_name",
                "George Orwell",
                "test_statuses/test_statuses:0/date",
                "2017-10-01",
                "test_statuses/test_statuses:0/date|normal_status",
                "L",
                "test_statuses/test_statuses:0/date|magnitude_status",
                ">=",
                "test_statuses/test_statuses:0/date/_accuracy",
                "P1Y");

        RMDataFormat flatJson = getFlatJson(template, FlatFormat.SIM_SDT);
        Composition composition = flatJson.unmarshal(OBJECT_MAPPER.writeValueAsString(flatComposition));
        DvDate rmObject = (DvDate)
                composition.itemAtPath(
                        "/content[openEHR-EHR-OBSERVATION.test_statuses.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0011]/value");

        assertThat(rmObject.getNormalStatus()).isNotNull();
        assertThat(rmObject.getNormalStatus().getTerminologyId().getValue()).isEqualTo("openehr_normal_statuses");
        assertThat(rmObject.getNormalStatus().getCodeString()).isEqualTo("L");
        assertThat(rmObject.getMagnitudeStatus()).isEqualTo(">=");
        assertThat(rmObject.getAccuracy())
                .isNotNull()
                .extracting(DvDuration::getValue)
                .extracting(Object::toString)
                .isEqualTo("P1Y");

        Map<String, Object> value = OBJECT_MAPPER.readValue(flatJson.marshal(composition), Map.class);
        assertThat(value)
                .containsEntry("test_statuses/test_statuses:0/date|normal_status", "L")
                .containsEntry("test_statuses/test_statuses:0/date|magnitude_status", ">=")
                .containsEntry("test_statuses/test_statuses:0/date/_accuracy", "P1Y");
    }

    @Override
    @Test
    public void time() throws Exception {
        String template = this.getFileContent("/res/test_statuses.opt");
        Map<String, Object> flatComposition = Map.of(
                "ctx/language",
                "sl",
                "ctx/territory",
                "SI",
                "ctx/composer_name",
                "George Orwell",
                "test_statuses/test_statuses:0/time",
                "13:20",
                "test_statuses/test_statuses:0/time|normal_status",
                "L",
                "test_statuses/test_statuses:0/time|magnitude_status",
                ">=",
                "test_statuses/test_statuses:0/time/_accuracy",
                "P1Y");

        RMDataFormat flatJson = getFlatJson(template, FlatFormat.SIM_SDT);
        Composition composition = flatJson.unmarshal(OBJECT_MAPPER.writeValueAsString(flatComposition));
        DvTime rmObject = (DvTime)
                composition.itemAtPath(
                        "/content[openEHR-EHR-OBSERVATION.test_statuses.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0012]/value");

        assertThat(rmObject.getNormalStatus()).isNotNull();
        assertThat(rmObject.getNormalStatus().getTerminologyId().getValue()).isEqualTo("openehr_normal_statuses");
        assertThat(rmObject.getNormalStatus().getCodeString()).isEqualTo("L");
        assertThat(rmObject.getMagnitudeStatus()).isEqualTo(">=");
        assertThat(rmObject.getAccuracy())
                .isNotNull()
                .extracting(DvDuration::getValue)
                .extracting(Object::toString)
                .isEqualTo("P1Y");

        Map<String, Object> value = OBJECT_MAPPER.readValue(flatJson.marshal(composition), Map.class);
        assertThat(value)
                .containsEntry("test_statuses/test_statuses:0/time|normal_status", "L")
                .containsEntry("test_statuses/test_statuses:0/time|magnitude_status", ">=")
                .containsEntry("test_statuses/test_statuses:0/time/_accuracy", "P1Y");
    }

    @Override
    @Test
    public void dateTime() throws Exception {
        String template = this.getFileContent("/res/test_statuses.opt");
        Map<String, Object> flatComposition = Map.of(
                "ctx/language",
                "sl",
                "ctx/territory",
                "SI",
                "ctx/composer_name",
                "George Orwell",
                "test_statuses/test_statuses:0/datetime",
                "2017-10-01T13:20:00Z",
                "test_statuses/test_statuses:0/datetime|normal_status",
                "L",
                "test_statuses/test_statuses:0/datetime|magnitude_status",
                ">=",
                "test_statuses/test_statuses:0/datetime/_accuracy",
                "P1Y");

        RMDataFormat flatJson = getFlatJson(template, FlatFormat.SIM_SDT);
        Composition composition = flatJson.unmarshal(OBJECT_MAPPER.writeValueAsString(flatComposition));
        DvDateTime rmObject = (DvDateTime)
                composition.itemAtPath(
                        "/content[openEHR-EHR-OBSERVATION.test_statuses.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0013]/value");

        assertThat(rmObject.getNormalStatus()).isNotNull();
        assertThat(rmObject.getNormalStatus().getTerminologyId().getValue()).isEqualTo("openehr_normal_statuses");
        assertThat(rmObject.getNormalStatus().getCodeString()).isEqualTo("L");
        assertThat(rmObject.getMagnitudeStatus()).isEqualTo(">=");
        assertThat(rmObject.getAccuracy())
                .isNotNull()
                .extracting(DvDuration::getValue)
                .extracting(Object::toString)
                .isEqualTo("P1Y");

        Map<String, Object> value = OBJECT_MAPPER.readValue(flatJson.marshal(composition), Map.class);
        assertThat(value)
                .containsEntry("test_statuses/test_statuses:0/datetime|normal_status", "L")
                .containsEntry("test_statuses/test_statuses:0/datetime|magnitude_status", ">=")
                .containsEntry("test_statuses/test_statuses:0/datetime/_accuracy", "P1Y");
    }

    @Override
    @Test
    public void count() throws Exception {
        String template = this.getFileContent("/res/test_statuses.opt");
        Map<String, Object> flatComposition = Map.of(
                "ctx/language",
                "sl",
                "ctx/territory",
                "SI",
                "ctx/composer_name",
                "George Orwell",
                "test_statuses/test_statuses:0/count",
                1,
                "test_statuses/test_statuses:0/count|normal_status",
                "L",
                "test_statuses/test_statuses:0/count|magnitude_status",
                ">=",
                "test_statuses/test_statuses:0/count|accuracy",
                10.0,
                "test_statuses/test_statuses:0/count|accuracy_is_percent",
                true);

        RMDataFormat flatJson = getFlatJson(template, FlatFormat.SIM_SDT);
        Composition composition = flatJson.unmarshal(OBJECT_MAPPER.writeValueAsString(flatComposition));
        DvCount rmObject = (DvCount)
                composition.itemAtPath(
                        "/content[openEHR-EHR-OBSERVATION.test_statuses.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0014]/value");

        assertThat(rmObject.getNormalStatus()).isNotNull();
        assertThat(rmObject.getNormalStatus().getTerminologyId().getValue()).isEqualTo("openehr_normal_statuses");
        assertThat(rmObject.getNormalStatus().getCodeString()).isEqualTo("L");
        assertThat(rmObject.getMagnitudeStatus()).isEqualTo(">=");
        assertThat(rmObject.getAccuracy()).isEqualTo(10);
        assertThat(rmObject.getAccuracyIsPercent()).isTrue();

        Map<String, Object> value = OBJECT_MAPPER.readValue(flatJson.marshal(composition), Map.class);
        assertThat(value)
                .containsEntry("test_statuses/test_statuses:0/count|normal_status", "L")
                .containsEntry("test_statuses/test_statuses:0/count|magnitude_status", ">=")
                .containsEntry("test_statuses/test_statuses:0/count|accuracy", 10.0)
                .containsEntry("test_statuses/test_statuses:0/count|accuracy_is_percent", true);
    }

    @Override
    @Test
    public void proportion() throws Exception {
        String template = this.getFileContent("/res/test_statuses.opt");
        Map<String, Object> flatComposition = Map.of(
                "ctx/language",
                "sl",
                "ctx/territory",
                "SI",
                "ctx/composer_name",
                "George Orwell",
                "test_statuses/test_statuses:0/proportion|numerator",
                17,
                "test_statuses/test_statuses:0/proportion|denominator",
                33,
                "test_statuses/test_statuses:0/proportion|normal_status",
                "L",
                "test_statuses/test_statuses:0/proportion|magnitude_status",
                ">=",
                "test_statuses/test_statuses:0/proportion|accuracy",
                10.0,
                "test_statuses/test_statuses:0/proportion|accuracy_is_percent",
                true);

        RMDataFormat flatJson = getFlatJson(template, FlatFormat.SIM_SDT);
        Composition composition = flatJson.unmarshal(OBJECT_MAPPER.writeValueAsString(flatComposition));
        DvProportion rmObject = (DvProportion)
                composition.itemAtPath(
                        "/content[openEHR-EHR-OBSERVATION.test_statuses.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0015]/value");

        assertThat(rmObject.getNormalStatus()).isNotNull();
        assertThat(rmObject.getNormalStatus().getTerminologyId().getValue()).isEqualTo("openehr_normal_statuses");
        assertThat(rmObject.getNormalStatus().getCodeString()).isEqualTo("L");
        assertThat(rmObject.getMagnitudeStatus()).isEqualTo(">=");
        assertThat(rmObject.getAccuracy()).isEqualTo(10);
        assertThat(rmObject.getAccuracyIsPercent()).isTrue();

        Map<String, Object> value = OBJECT_MAPPER.readValue(flatJson.marshal(composition), Map.class);
        assertThat(value)
                .containsEntry("test_statuses/test_statuses:0/proportion|normal_status", "L")
                .containsEntry("test_statuses/test_statuses:0/proportion|magnitude_status", ">=")
                .containsEntry("test_statuses/test_statuses:0/proportion|accuracy", 10.0)
                .containsEntry("test_statuses/test_statuses:0/proportion|accuracy_is_percent", true);
    }

    @Override
    /*
    Test error behavior which is not part of the spec
     */
    public void ordinalInvalid() throws Exception {}

    @Override
    /*
    Test error behavior which is not part of the spec
     */
    public void durationInvalid() throws Exception {}

    @Override
    /*
    Test error behavior which is not part of the spec
     */
    public void quantityInvalid() throws Exception {}

    @Override
    /*
    Test error behavior which is not part of the spec
     */
    public void dateInvalid() throws Exception {}

    @Override
    /*
    Test error behavior which is not part of the spec
     */
    public void timeInvalid() throws Exception {}

    @Override
    /*
    Test error behavior which is not part of the spec
     */
    public void dateTimeInvalid() throws Exception {}

    @Override
    /*
    Test error behavior which is not part of the spec
     */
    public void countInvalid() throws Exception {}

    @Override
    /*
    Test error behavior which is not part of the spec
     */
    public void proportionInvalid() throws Exception {}
}
