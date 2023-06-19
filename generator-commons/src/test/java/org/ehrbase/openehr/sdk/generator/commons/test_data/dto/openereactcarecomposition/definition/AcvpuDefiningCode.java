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

public enum AcvpuDefiningCode implements EnumValueSet {
    CONFUSION(
            "Confusion",
            "A new onset or worsening confusion, delirium or any other altered mentation.",
            "local",
            "at0015"),

    UNRESPONSIVE("Unresponsive", "No response to voice or pain stimuli.", "local", "at0008"),

    VOICE("Voice", "Any verbal, motor or eye response to a voice stimulus.", "local", "at0006"),

    PAIN("Pain", "Any verbal, motor or eye response to a pain stimulus.", "local", "at0007"),

    ALERT(
            "Alert",
            "Fully awake. Spontaneous opening of the eyes, responds to voice and have motor function.",
            "local",
            "at0005");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    AcvpuDefiningCode(String value, String description, String terminologyId, String code) {
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
