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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasebloodpressuresimpledev0composition.definition;

import org.ehrbase.openehr.sdk.generator.commons.interfaces.EnumValueSet;

public enum KorotkoffSoundsDefiningCode implements EnumValueSet {
    FIFTH_SOUND(
            "Fifth sound",
            "The fifth Korotkoff sound is identified by absence of sounds as the cuff\n"
                    + "                        pressure drops below the diastolic blood pressure\n"
                    + "                    ",
            "local",
            "at1012"),

    FOURTH_SOUND(
            "Fourth sound",
            "The fourth Korotkoff sound is identified as an abrupt muffling of sounds\n" + "                    ",
            "local",
            "at1011");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    KorotkoffSoundsDefiningCode(String value, String description, String terminologyId, String code) {
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
