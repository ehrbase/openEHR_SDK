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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.coronaanamnesecomposition.definition;

import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class KontaktSection {
    @Path(
            "/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Aufenthalt in Gesundheitseinrichtung']")
    private AufenthaltInGesundheitseinrichtungObservation aufenthaltInGesundheitseinrichtung;

    @Path("/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Personenkontakt']")
    private PersonenkontaktObservation personenkontakt;

    public void setAufenthaltInGesundheitseinrichtung(
            AufenthaltInGesundheitseinrichtungObservation aufenthaltInGesundheitseinrichtung) {
        this.aufenthaltInGesundheitseinrichtung = aufenthaltInGesundheitseinrichtung;
    }

    public AufenthaltInGesundheitseinrichtungObservation getAufenthaltInGesundheitseinrichtung() {
        return this.aufenthaltInGesundheitseinrichtung;
    }

    public void setPersonenkontakt(PersonenkontaktObservation personenkontakt) {
        this.personenkontakt = personenkontakt;
    }

    public PersonenkontaktObservation getPersonenkontakt() {
        return this.personenkontakt;
    }
}
