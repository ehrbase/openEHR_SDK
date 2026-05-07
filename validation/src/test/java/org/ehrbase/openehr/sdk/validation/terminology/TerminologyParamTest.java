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
package org.ehrbase.openehr.sdk.validation.terminology;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TerminologyParamTest {

    @Test
    void createValueSet() {
        TerminologyParam tp = TerminologyParam.ofFhir(
                "//fhir.hl7.org/ValueSet/$expand?url=http://hl7.org/fhir/observation-status", null);

        assertThat(tp.serviceApi()).isEqualTo("//fhir.hl7.org");
        assertThat(tp.resouceType()).isEqualTo(TerminologyParam.ResouceType.VALUE_SET);
        assertThat(tp.operation()).isEqualTo("$expand");
        assertThat(tp.parameter()).isEqualTo("url=http://hl7.org/fhir/observation-status");
    }

    @Test
    void createCodeSystem() {
        TerminologyParam tp =
                TerminologyParam.ofFhir("//fhir.hl7.org/CodeSystem?url=http://hl7.org/fhir/observation-status", null);

        assertThat(tp.serviceApi()).isEqualTo("//fhir.hl7.org");
        assertThat(tp.resouceType()).isEqualTo(TerminologyParam.ResouceType.CODE_SYSTEM);
        assertThat(tp.operation()).isNull();
        assertThat(tp.parameter()).isEqualTo("url=http://hl7.org/fhir/observation-status");
    }

    @Test
    void createWithoutUrlParameter() {
        TerminologyParam tp = TerminologyParam.ofFhir("//fhir.hl7.org/CodeSystem", null);

        assertThat(tp.serviceApi()).isEqualTo("//fhir.hl7.org");
        assertThat(tp.resouceType()).isEqualTo(TerminologyParam.ResouceType.CODE_SYSTEM);
        assertThat(tp.operation()).isNull();
        assertThat(tp.parameter()).isNull();
    }

    @Test
    void createInvalid() {
        TerminologyParam tp = TerminologyParam.ofFhir("//fhir.hl7.org/Observation", null);
        assertThat(tp).isNull();
    }
}
