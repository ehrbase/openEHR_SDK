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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.stationarerversorgungsfallcomposition.definition;

import org.ehrbase.openehr.sdk.generator.commons.interfaces.EnumValueSet;

public enum KlinischerZustandDesPatientenDefiningCode implements EnumValueSet {
    IDENTISCHER_ZUSTAND(
            "Identischer Zustand",
            "Der Gesundheitszustand des Patienten ist identisch, wie bei der Aufnahme.",
            "local",
            "at0005"),

    UNBESTIMMT("Unbestimmt", "Unbestimmt.", "local", "at0008"),

    GEHEILT("Geheilt", "Der Patient ist geheilt.", "local", "at0003"),

    SONSTIGE("Sonstige", "Sonstige", "local", "at0068"),

    SCHLECHTER(
            "Schlechter",
            "Der Gesundheitszustand des Patienten ist schlechter, als bei der Aufnahme.",
            "local",
            "at0006"),

    VERBESSERT("Verbessert", "Der Gesundheitszustand des Patienten hat sich verbessert.", "local", "at0004"),

    VERSTORBEN("Verstorben", "Der Patient verstarb während des Krankenhausaufenthaltes.", "local", "at0007");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    KlinischerZustandDesPatientenDefiningCode(String value, String description, String terminologyId, String code) {
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
