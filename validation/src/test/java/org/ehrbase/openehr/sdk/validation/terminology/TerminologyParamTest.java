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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TerminologyParamTest {

    @Test
    void createTerminologyParam0() {
        TerminologyParam tp = TerminologyParam.ofFhir(
                "//fhir.hl7.org/CodeSystem/$expand?url=http://hl7.org/fhir/observation-status", null);

        Assertions.assertEquals("//fhir.hl7.org", tp.serviceApi());
        Assertions.assertFalse(tp.useValueSet());
        Assertions.assertEquals("$expand", tp.operation());
        Assertions.assertEquals("url=http://hl7.org/fhir/observation-status", tp.parameter());
    }

    @Test
    void createTerminologyParam1() {
        TerminologyParam tp =
                TerminologyParam.ofFhir("//fhir.hl7.org/CodeSystem?url=http://hl7.org/fhir/observation-status", null);

        Assertions.assertEquals("//fhir.hl7.org", tp.serviceApi());
        Assertions.assertFalse(tp.useValueSet());
        Assertions.assertNull(tp.operation());
        Assertions.assertEquals("url=http://hl7.org/fhir/observation-status", tp.parameter());
    }

    @Test
    void createTerminologyParam2() {
        TerminologyParam tp = TerminologyParam.ofFhir("//fhir.hl7.org/CodeSystem", null);

        Assertions.assertEquals("//fhir.hl7.org", tp.serviceApi());
        Assertions.assertFalse(tp.useValueSet());
        Assertions.assertNull(tp.operation());
        Assertions.assertNull(tp.parameter());
    }
}
