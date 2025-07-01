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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.coronaanamnesecomposition.definition;

import org.ehrbase.openehr.sdk.generator.commons.interfaces.EnumValueSet;

public enum VorhandenseinDefiningCode2 implements EnumValueSet {
    VORHANDEN("Vorhanden", "Der Risikofaktor wurde bei der Person identifiziert.", "local", "at0018"),

    UNBESTIMMT(
            "Unbestimmt",
            "Es ist nicht möglich festzustellen, ob der Risikofaktor vorhanden oder nicht vorhanden ist.",
            "local",
            "at0026"),

    NICHT_VORHANDEN("Nicht vorhanden", "Der Risikofaktor wurde bei der Person nicht festgestellt.", "local", "at0019");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    VorhandenseinDefiningCode2(String value, String description, String terminologyId, String code) {
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
