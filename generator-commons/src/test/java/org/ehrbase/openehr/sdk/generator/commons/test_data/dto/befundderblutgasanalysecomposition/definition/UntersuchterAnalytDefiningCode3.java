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

public enum UntersuchterAnalytDefiningCode3 implements EnumValueSet {
    PH_OF_VENOUS_BLOOD("pH of Venous blood", "pH of Venous blood", "LOINC", "2746-6"),

    PH_OF_CAPILLARY_BLOOD("pH of Capillary blood", "pH of Capillary blood", "LOINC", "2745-8"),

    PH_OF_ARTERIAL_BLOOD("pH of Arterial blood", "pH of Arterial blood", "LOINC", "2744-1"),

    PH_OF_MIXED_VENOUS_BLOOD("pH of Mixed venous blood", "pH of Mixed venous blood", "LOINC", "19213-8"),

    PH_OF_SERUM_OR_PLASMA("pH of Serum or Plasma", "pH of Serum or Plasma", "LOINC", "2753-2"),

    PH_OF_BLOOD("pH of Blood", "pH of Blood", "LOINC", "11558-4");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    UntersuchterAnalytDefiningCode3(String value, String description, String terminologyId, String code) {
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
