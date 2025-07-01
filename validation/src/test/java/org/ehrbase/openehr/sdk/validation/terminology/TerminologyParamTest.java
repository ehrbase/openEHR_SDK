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
package org.ehrbase.openehr.sdk.validation.terminology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TerminologyParamTest {

    @Test
    void createTerminologyParam0() {
        TerminologyParam tp =
                TerminologyParam.ofFhir("//fhir.hl7.org/CodeSystem/$expand?url=http://hl7.org/fhir/observation-status");

        Assertions.assertEquals("//fhir.hl7.org", tp.getServiceApi().get());
        Assertions.assertTrue(tp.isUseCodeSystem());
        Assertions.assertEquals("$expand", tp.getOperation().get());
        Assertions.assertEquals(
                "url=http://hl7.org/fhir/observation-status", tp.getParameter().get());
    }

    @Test
    void createTerminologyParam1() {
        TerminologyParam tp =
                TerminologyParam.ofFhir("//fhir.hl7.org/CodeSystem?url=http://hl7.org/fhir/observation-status");

        Assertions.assertEquals("//fhir.hl7.org", tp.getServiceApi().get());
        Assertions.assertTrue(tp.isUseCodeSystem());
        Assertions.assertTrue(tp.getOperation().isEmpty());
        Assertions.assertEquals(
                "url=http://hl7.org/fhir/observation-status", tp.getParameter().get());
    }

    @Test
    void createTerminologyParam2() {
        TerminologyParam tp = TerminologyParam.ofFhir("//fhir.hl7.org/CodeSystem");

        Assertions.assertEquals("//fhir.hl7.org", tp.getServiceApi().get());
        Assertions.assertTrue(tp.isUseCodeSystem());
        Assertions.assertTrue(tp.getOperation().isEmpty());
        Assertions.assertTrue(tp.getParameter().isEmpty());
    }
}
