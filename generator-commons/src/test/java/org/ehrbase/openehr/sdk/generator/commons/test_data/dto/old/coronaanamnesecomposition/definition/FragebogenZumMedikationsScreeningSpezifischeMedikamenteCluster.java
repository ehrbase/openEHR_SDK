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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.coronaanamnesecomposition.definition;

import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;

@Entity
public class FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster {
    @Path("/items[at0021]/value|value")
    private String nameDesMedikamentsValue;

    @Path("/items[at0024]/value|defining_code")
    private MedikamentInVerwendungDefiningcode medikamentInVerwendungDefiningcode;

    public void setNameDesMedikamentsValue(String nameDesMedikamentsValue) {
        this.nameDesMedikamentsValue = nameDesMedikamentsValue;
    }

    public String getNameDesMedikamentsValue() {
        return this.nameDesMedikamentsValue;
    }

    public void setMedikamentInVerwendungDefiningcode(
            MedikamentInVerwendungDefiningcode medikamentInVerwendungDefiningcode) {
        this.medikamentInVerwendungDefiningcode = medikamentInVerwendungDefiningcode;
    }

    public MedikamentInVerwendungDefiningcode getMedikamentInVerwendungDefiningcode() {
        return this.medikamentInVerwendungDefiningcode;
    }
}
