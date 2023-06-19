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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.openereactcarecomposition.definition;

import org.ehrbase.openehr.sdk.generator.commons.interfaces.EnumValueSet;

public enum LikelySourceOfInfectionDefiningCode implements EnumValueSet {
    SKIN_JOINT_WOUND(
            "Skin / joint/ wound", "The likely source of infection is skin, joint or wound.", "local", "at0026"),

    URINE("Urine", "The likely source of infection is urine.", "local", "at0014"),

    INDWELLING_DEVICE(
            "Indwelling device", "The likely source of infection is an indwelling device.", "local", "at0027"),

    RESPIRATORY("Respiratory", "The likely source of infection is respiratory.", "local", "at0012"),

    SURGICAL("Surgical", "The likely source of infection is surgical.", "local", "at0015"),

    BRAIN("Brain", "The likely source of infection is brain.", "local", "at0013");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    LikelySourceOfInfectionDefiningCode(String value, String description, String terminologyId, String code) {
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
