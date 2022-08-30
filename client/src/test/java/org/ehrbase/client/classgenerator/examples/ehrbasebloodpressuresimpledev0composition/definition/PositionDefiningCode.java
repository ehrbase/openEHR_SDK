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
package org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum PositionDefiningCode implements EnumValueSet {
    STANDING("Standing", "Standing at the time of blood pressure measurement", "local", "at1000"),

    LYING("Lying", "Lying flat at the time of blood pressure measurement", "local", "at1003"),

    RECLINING("Reclining", "Reclining at the time of blood pressure measurement", "local", "at1002"),

    TRENDELENBURG(
            "Trendelenburg",
            "Lying flat on the back (supine position) with the feet higher than the head\n"
                    + "                        at the time of blood pressure measurement\n"
                    + "                    ",
            "local",
            "at1013"),

    LEFT_LATERAL("Left Lateral", "Lying on the left side at the time of blood pressure measurement", "local", "at1014"),

    SITTING(
            "Sitting",
            "Sitting (for example on bed or chair) at the time of blood pressure\n"
                    + "                        measurement\n"
                    + "                    ",
            "local",
            "at1001");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    PositionDefiningCode(String value, String description, String terminologyId, String code) {
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
