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
package org.ehrbase.openehr.sdk.serialisation.walker;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.ehrbase.openehr.sdk.webtemplate.path.flat.FlatPathDto;
import org.junit.jupiter.api.Test;

class FlatHelperTest {

    @Test
    void isDvCodedText() {

        Map<FlatPathDto, String> codedTextValues = Map.of(
                new FlatPathDto(
                        "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_text|value"),
                "Radial styloid tenosynovitis",
                new FlatPathDto(
                        "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_text|code"),
                "21794005",
                new FlatPathDto(
                        "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_text|terminology"),
                "SNOMED-CT");

        assertThat(FlatHelper.isExactlyDvCodedText(
                        codedTextValues,
                        "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_text"))
                .isTrue();
    }

    @Test
    void isDvCodedTextDvText() {

        Map<FlatPathDto, String> codedTextValues = Map.of(
                new FlatPathDto(
                        "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_text|value"),
                "Radial styloid tenosynovitis");

        assertThat(FlatHelper.isExactlyDvCodedText(
                        codedTextValues,
                        "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_text"))
                .isFalse();
    }

    @Test
    void isDvCodedTextDvTextShort() {

        Map<FlatPathDto, String> codedTextValues = Map.of(
                new FlatPathDto(
                        "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_text"),
                "Radial styloid tenosynovitis");

        assertThat(FlatHelper.isExactlyDvCodedText(
                        codedTextValues,
                        "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_text"))
                .isFalse();
    }

    @Test
    void isPartyRelated() {

        Map<FlatPathDto, String> codedTextValues = Map.of(
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|function"),
                "requester",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|mode"),
                "face-to-face communication",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|name"),
                "Dr. Marcus Johnson",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|id"),
                "199",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|id_scheme"),
                "HOSPITAL-NS",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|id_namespace"),
                "HOSPITAL-NS",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1/relationship|code"),
                "10",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1/relationship|value"),
                "mother",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1/relationship|terminology"),
                "openehr");

        assertThat(FlatHelper.isExactlyPartySelf(
                        codedTextValues, "conformance-ehrbase.de.v0/context/_participation:1", null))
                .isFalse();
        assertThat(FlatHelper.isExactlyPartyIdentified(
                        codedTextValues, "conformance-ehrbase.de.v0/context/_participation:1", null))
                .isFalse();
        assertThat(FlatHelper.isExactlyPartyRelated(
                        codedTextValues, "conformance-ehrbase.de.v0/context/_participation:1", null))
                .isTrue();
    }

    @Test
    void isPartyIdentified() {

        Map<FlatPathDto, String> codedTextValues = Map.of(
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|function"),
                "requester",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|mode"),
                "face-to-face communication",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|name"),
                "Dr. Marcus Johnson",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|id"),
                "199",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|id_scheme"),
                "HOSPITAL-NS",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|id_namespace"),
                "HOSPITAL-NS");

        assertThat(FlatHelper.isExactlyPartySelf(
                        codedTextValues, "conformance-ehrbase.de.v0/context/_participation:1", null))
                .isFalse();
        assertThat(FlatHelper.isExactlyPartyIdentified(
                        codedTextValues, "conformance-ehrbase.de.v0/context/_participation:1", null))
                .isTrue();
        assertThat(FlatHelper.isExactlyPartyRelated(
                        codedTextValues, "conformance-ehrbase.de.v0/context/_participation:1", null))
                .isFalse();
    }

    @Test
    void isPartySelfWithId() {

        Map<FlatPathDto, String> codedTextValues = Map.of(
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|function"),
                "requester",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|mode"),
                "face-to-face communication",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|_type"),
                "PARTY_SELF",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|id"),
                "199",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|id_scheme"),
                "HOSPITAL-NS",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|id_namespace"),
                "HOSPITAL-NS");

        assertThat(FlatHelper.isExactlyPartySelf(
                        codedTextValues, "conformance-ehrbase.de.v0/context/_participation:1", null))
                .isTrue();
        assertThat(FlatHelper.isExactlyPartyIdentified(
                        codedTextValues, "conformance-ehrbase.de.v0/context/_participation:1", null))
                .isFalse();
        assertThat(FlatHelper.isExactlyPartyRelated(
                        codedTextValues, "conformance-ehrbase.de.v0/context/_participation:1", null))
                .isFalse();
    }

    @Test
    void isPartyIdentifiedIdentifier() {

        Map<FlatPathDto, String> codedTextValues = Map.of(
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|function"),
                "requester",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|mode"),
                "face-to-face communication",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1/_identifier:0|id"),
                "199",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1/_identifier:0|issuer"),
                "HOSPITAL-NS",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1/_identifier:0|assigner"),
                "HOSPITAL-NS",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1/_identifier:0|type"),
                "type");

        assertThat(FlatHelper.isExactlyPartySelf(
                        codedTextValues, "conformance-ehrbase.de.v0/context/_participation:1", null))
                .isFalse();
        assertThat(FlatHelper.isExactlyPartyIdentified(
                        codedTextValues, "conformance-ehrbase.de.v0/context/_participation:1", null))
                .isTrue();
        assertThat(FlatHelper.isExactlyPartyRelated(
                        codedTextValues, "conformance-ehrbase.de.v0/context/_participation:1", null))
                .isFalse();
    }

    @Test
    void isPartyIdentifiedName() {

        Map<FlatPathDto, String> codedTextValues = Map.of(
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|function"),
                "requester",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|mode"),
                "face-to-face communication",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|name"),
                "Dr. Marcus Johnson");

        assertThat(FlatHelper.isExactlyPartySelf(
                        codedTextValues, "conformance-ehrbase.de.v0/context/_participation:1", null))
                .isFalse();
        assertThat(FlatHelper.isExactlyPartyIdentified(
                        codedTextValues, "conformance-ehrbase.de.v0/context/_participation:1", null))
                .isTrue();
        assertThat(FlatHelper.isExactlyPartyRelated(
                        codedTextValues, "conformance-ehrbase.de.v0/context/_participation:1", null))
                .isFalse();
    }

    @Test
    void isPartyIdentifiedId() {

        Map<FlatPathDto, String> codedTextValues = Map.of(
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|function"),
                "requester",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|mode"),
                "face-to-face communication",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|id"),
                "199",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|id_scheme"),
                "HOSPITAL-NS",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|id_namespace"),
                "HOSPITAL-NS");

        assertThat(FlatHelper.isExactlyPartySelf(
                        codedTextValues, "conformance-ehrbase.de.v0/context/_participation:1", null))
                .isFalse();
        assertThat(FlatHelper.isExactlyPartyIdentified(
                        codedTextValues, "conformance-ehrbase.de.v0/context/_participation:1", null))
                .isTrue();
        assertThat(FlatHelper.isExactlyPartyRelated(
                        codedTextValues, "conformance-ehrbase.de.v0/context/_participation:1", null))
                .isFalse();
    }

    @Test
    void isPartySelf() {

        Map<FlatPathDto, String> codedTextValues = Map.of(
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|function"),
                "requester",
                new FlatPathDto("conformance-ehrbase.de.v0/context/_participation:1|mode"),
                "face-to-face communication");

        assertThat(FlatHelper.isExactlyPartySelf(
                        codedTextValues, "conformance-ehrbase.de.v0/context/_participation:1", null))
                .isTrue();
        assertThat(FlatHelper.isExactlyPartyIdentified(
                        codedTextValues, "conformance-ehrbase.de.v0/context/_participation:1", null))
                .isFalse();
        assertThat(FlatHelper.isExactlyPartyRelated(
                        codedTextValues, "conformance-ehrbase.de.v0/context/_participation:1", null))
                .isFalse();
    }

    @Test
    void isIntervalEvent() {

        Map<FlatPathDto, String> codedTextValues = Map.of(
                new FlatPathDto(
                                "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity|magnitude"),
                        "65.9",
                new FlatPathDto(
                                "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity|unit"),
                        "unit",
                new FlatPathDto(
                                "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0|sample_count"),
                        "5",
                new FlatPathDto(
                                "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/width"),
                        "P30D",
                new FlatPathDto(
                                "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/math_function|code"),
                        "146",
                new FlatPathDto(
                                "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/math_function|value"),
                        "mean",
                new FlatPathDto(
                                "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/math_function|terminology"),
                        "openehr",
                new FlatPathDto(
                                "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/time"),
                        "2021-12-21T16:02:58.0094262+01:00");

        assertThat(FlatHelper.isExactlyIntervalEvent(
                        codedTextValues,
                        "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0"))
                .isTrue();
    }

    @Test
    void isIntervalEventPointEvent() {

        Map<FlatPathDto, String> codedTextValues = Map.of(
                new FlatPathDto(
                                "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity|magnitude"),
                        "65.9",
                new FlatPathDto(
                                "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity|unit"),
                        "unit",
                new FlatPathDto(
                                "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/time"),
                        "2021-12-21T16:02:58.0094262+01:00");

        assertThat(FlatHelper.isExactlyIntervalEvent(
                        codedTextValues,
                        "conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0"))
                .isFalse();
    }
}
