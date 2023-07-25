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

public enum N999FlagDefiningCode implements EnumValueSet {
    N_I_FEEL_I_MIGHT_DIE("‘I feel I might die’", "The patient stated ‘I feel I might die’.", "local", "at0041"),

    EXTREME_SHIVERING_OR_MUSCLE_PAIN(
            "Extreme shivering or muscle pain", "The patient has extreme shivering or muscle pain.", "local", "at0038"),

    SEVERE_BREATHLESSNESS("Severe breathlessness", "The patient has severe breathlessness.", "local", "at0040"),

    SKIN_MOTTLED_ASHEN_BLUE_OR_VERY_PALE(
            "Skin mottled, ashen, blue or very pale",
            "The patient has skin which is mottled, ashen, blue or very pale.",
            "local",
            "at0042"),

    SLURRED_SPEECH_OR_CONFUSION(
            "Slurred speech or confusion", "The patient has slurred speech or confusion.", "local", "at0037"),

    PASSING_NO_URINE_IN_A_DAY(
            "Passing no urine (in a day)", "The patient is passing no urine (in a day).", "local", "at0039");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    N999FlagDefiningCode(String value, String description, String terminologyId, String code) {
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
