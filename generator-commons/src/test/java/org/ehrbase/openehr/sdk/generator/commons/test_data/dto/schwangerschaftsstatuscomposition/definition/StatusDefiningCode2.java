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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.schwangerschaftsstatuscomposition.definition;

import org.ehrbase.openehr.sdk.generator.commons.interfaces.EnumValueSet;

public enum StatusDefiningCode2 implements EnumValueSet {
    SCHWANGER("Schwanger", "Die Person ist schwanger.", "local", "at0012"),

    NICHT_SCHWANGER("Nicht schwanger", "Die Person ist nicht schwanger.", "local", "at0013"),

    UNBEKANNT("Unbekannt", "Es ist nicht bekannt, ob die Person schwanger ist oder nicht.", "local", "at0014");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    StatusDefiningCode2(String value, String description, String terminologyId, String code) {
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
