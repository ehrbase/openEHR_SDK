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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.befundderblutgasanalysecomposition.definition;

import org.ehrbase.openehr.sdk.generator.commons.interfaces.EnumValueSet;

public enum LabortestBezeichnungDefiningCode implements EnumValueSet {
    GAS_PANEL_CAPILLARY_BLOOD("Gas panel - Capillary blood", "Gas panel - Capillary blood", "LOINC", "24337-8"),

    GAS_PANEL_ARTERIAL_BLOOD("Gas panel - Arterial blood", "Gas panel - Arterial blood", "LOINC", "24336-0"),

    GAS_PANEL_BLOOD("Gas panel - Blood", "Gas panel - Blood", "LOINC", "24338-6");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    LabortestBezeichnungDefiningCode(String value, String description, String terminologyId, String code) {
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
