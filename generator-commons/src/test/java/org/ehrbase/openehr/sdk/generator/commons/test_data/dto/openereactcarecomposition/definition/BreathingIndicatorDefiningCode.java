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

public enum BreathingIndicatorDefiningCode implements EnumValueSet {
    UNABLE_TO_SPEAK_FULL_SENTENCES(
            "Unable to speak full sentences", "Unable to speak in full sentences.", "local", "at0069"),

    NOISY_BREATHING("Noisy breathing", "Noisy breathing.", "local", "at0067"),

    SHORT_OF_BREATH("Short of breath", "Short of breath.", "local", "at0068"),

    USE_ACCESSORY_MUSCLES("Use accessory muscles", "Use accessory muscles.", "local", "at0070");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    BreathingIndicatorDefiningCode(String value, String description, String terminologyId, String code) {
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
