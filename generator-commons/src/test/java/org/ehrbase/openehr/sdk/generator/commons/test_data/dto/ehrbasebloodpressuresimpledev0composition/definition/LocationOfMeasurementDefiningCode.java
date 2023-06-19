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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasebloodpressuresimpledev0composition.definition;

import org.ehrbase.openehr.sdk.generator.commons.interfaces.EnumValueSet;

public enum LocationOfMeasurementDefiningCode implements EnumValueSet {
    LEFT_THIGH("Left thigh", "The left thigh of the person", "local", "at0028"),

    INTRA_ARTERIAL("Intra-arterial", "Blood pressure monitored via an intra-arterial line", "local", "at0032"),

    FINGER("Finger", "A finger of the person", "local", "at1026"),

    RIGHT_ANKLE("Right ankle", "The right ankle of the person", "local", "at1031"),

    LEFT_WRIST("Left wrist", "The left wrist of the person", "local", "at1021"),

    RIGHT_WRIST("Right wrist", "The right wrist of the person", "local", "at1020"),

    LEFT_ARM("Left arm", "The left arm of the person", "local", "at0026"),

    LEFT_ANKLE("Left ankle", "The left ankle of the person", "local", "at1032"),

    RIGHT_THIGH("Right thigh", "The right thigh of the person", "local", "at0027"),

    RIGHT_ARM("Right arm", "The right arm of the person", "local", "at0025");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    LocationOfMeasurementDefiningCode(String value, String description, String terminologyId, String code) {
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
