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
package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum RiskFactorsForSepsisDefiningCode implements EnumValueSet {
    AGE_OVER75("Age over 75", "Patient is over 75.", "local", "at0007"),

    IMPAIRED_IMMUNITY_E_G_DIABETES_STEROIDS_CHEMOTHERAPY(
            "Impaired immunity (e.g diabetes, steroids, chemotherapy)",
            "Patient has impaired immunity.",
            "local",
            "at0008"),

    INDWELLING_LINES_IVDU_BROKEN_SKIN(
            "Indwelling lines / IVDU / broken skin",
            "Patient has indwelling lines, IVDU or broken skin.",
            "local",
            "at0010"),

    RECENT_TRAUMA_SURGERY_INVASIVE_PROCEDURE(
            "Recent trauma / surgery / invasive procedure",
            "Patient has had recent trauma, surgery or an invasive procedure.",
            "local",
            "at0009");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    RiskFactorsForSepsisDefiningCode(String value, String description, String terminologyId, String code) {
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
