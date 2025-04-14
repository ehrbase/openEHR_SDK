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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.optimizersettingsections.definition;

import org.ehrbase.openehr.sdk.generator.commons.interfaces.EnumValueSet;

public enum MedikamentInVerwendungDefiningCode implements EnumValueSet {
    NICHT_IN_VERWENDUNG(
            "Nicht in Verwendung",
            "Die Person verwendet das Medikament zum Zeitpunkt des Ergebnis oder währendessen nicht oder hat es nicht verwendet.",
            "local",
            "at0032"),

    UNBEKANNT(
            "Unbekannt",
            "Es ist unbekannt, ob die Person das Medikament zum Zeitpunkt des Ergebnis oder währendessen verwendet oder es verwendet hat.",
            "local",
            "at0033"),

    IN_VERWENDUNG(
            "In Verwendung",
            "Die Person verwendet das Medikament zum Zeitpunkt des Ergebnis oder währendessen oder hat es verwendet.",
            "local",
            "at0031");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    MedikamentInVerwendungDefiningCode(String value, String description, String terminologyId, String code) {
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
