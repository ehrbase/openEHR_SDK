/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.client.classgenerator.examples.shareddefinition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum MathFunctionDefiningcode implements EnumValueSet {
    MEDIAN("median", "median", "openehr", "268"),

    DECREASE("decrease", "decrease", "openehr", "521"),

    MINIMUM("minimum", "minimum", "openehr", "145"),

    VARIATION("variation", "variation", "openehr", "149"),

    INCREASE("increase", "increase", "openehr", "522"),

    MAXIMUM("maximum", "maximum", "openehr", "144"),

    MODE("mode", "mode", "openehr", "267"),

    MEAN("mean", "mean", "openehr", "146"),

    ACTUAL("actual", "actual", "openehr", "640"),

    CHANGE("change", "change", "openehr", "147"),

    TOTAL("total", "total", "openehr", "148");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    MathFunctionDefiningcode(String value, String description, String terminologyId, String code) {
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
