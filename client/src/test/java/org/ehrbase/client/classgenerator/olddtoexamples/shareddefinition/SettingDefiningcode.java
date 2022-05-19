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
package org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum SettingDefiningcode implements EnumValueSet {
    COMPLEMENTARY_HEALTH_CARE("complementary health care", "complementary health care", "openehr", "235"),

    HOME("home", "home", "openehr", "225"),

    SECONDARY_NURSING_CARE("secondary nursing care", "secondary nursing care", "openehr", "233"),

    DENTAL_CARE("dental care", "dental care", "openehr", "236"),

    OTHER_CARE("other care", "other care", "openehr", "238"),

    NURSING_HOME_CARE("nursing home care", "nursing home care", "openehr", "237"),

    SECONDARY_ALLIED_HEALTH_CARE("secondary allied health care", "secondary allied health care", "openehr", "234"),

    EMERGENCY_CARE("emergency care", "emergency care", "openehr", "227"),

    PRIMARY_NURSING_CARE("primary nursing care", "primary nursing care", "openehr", "229"),

    PRIMARY_MEDICAL_CARE("primary medical care", "primary medical care", "openehr", "228"),

    PRIMARY_ALLIED_HEALTH_CARE("primary allied health care", "primary allied health care", "openehr", "230"),

    SECONDARY_MEDICAL_CARE("secondary medical care", "secondary medical care", "openehr", "232"),

    MIDWIFERY_CARE("midwifery care", "midwifery care", "openehr", "231");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    SettingDefiningcode(String value, String description, String terminologyId, String code) {
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
