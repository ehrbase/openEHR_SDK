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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasebloodpressuresimpledev0composition.definition;

import org.ehrbase.openehr.sdk.generator.commons.interfaces.EnumValueSet;

public enum CuffSizeDefiningCode implements EnumValueSet {
    PAEDIATRIC_CHILD(
            "Paediatric/Child",
            "A cuff that is appropriate for a child or thin arm - bladder approx 8cm x 21\n"
                    + "                        cm\n"
                    + "                    ",
            "local",
            "at0017"),

    ADULT_THIGH("Adult Thigh", "A cuff used for an adult thigh - bladder approx 20cm x 42 cm", "local", "at1008"),

    LARGE_ADULT("Large Adult", "A cuff for adults with larger arms - bladder approx 16cm x 38cm", "local", "at0016"),

    ADULT("Adult", "A cuff that is standard for an adult - bladder approx 13cm x 30cm", "local", "at0015"),

    INFANT("Infant", "A cuff used for infants - bladder approx 5cm x 15cm", "local", "at1018"),

    NEONATAL("Neonatal", "A cuff used for a new born - bladder approx 3cm x 6cm", "local", "at1009"),

    SMALL_ADULT("Small Adult", "A cuff used for a small adult - bladder approx 10cm x 24 cm", "local", "at1019");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    CuffSizeDefiningCode(String value, String description, String terminologyId, String code) {
        this.value = value;
        this.description = description;
        this.terminologyId = terminologyId;
        this.code = code;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTerminologyId() {
        return this.terminologyId;
    }

    public String getCode() {
        return this.code;
    }
}
