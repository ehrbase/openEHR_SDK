/*
 * Copyright (c) 2021 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.webtemplate.model;

import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

public enum ProportionType {
    RATIO(0, "ratio"),
    UNITARY(1, "unitary"),
    PERCENT(2, "percent"),
    FRACTION(3, "fraction"),
    INTEGER_FRACTION(4, "integer_fraction");

    private final int id;
    private final String webTemplateName;

    ProportionType(int id, String webTemplateName) {
        this.id = id;
        this.webTemplateName = webTemplateName;
    }

    public int getId() {
        return id;
    }

    @JsonValue
    public String getWebTemplateName() {
        return webTemplateName;
    }

    public Optional<WebTemplateValidation> getDenominatorValidator() {
        if (this.equals(ProportionType.UNITARY)) {
            return Optional.of(buildConstantValidation(1));
        } else if (this.equals(ProportionType.PERCENT)) {
            return Optional.of(buildConstantValidation(100.0));
        }
        return Optional.empty();
    }

    private <T extends Serializable> WebTemplateValidation buildConstantValidation(T value) {
        WebTemplateValidation validation = new WebTemplateValidation();
        WebTemplateInterval<T> range = new WebTemplateInterval<>();
        range.setMax(value);
        range.setMaxOp(WebTemplateComparisonSymbol.LT_EQ);
        range.setMin(value);
        range.setMinOp(WebTemplateComparisonSymbol.GT_EQ);
        validation.setRange(range);
        return validation;
    }

    public static ProportionType findById(int id) {
        return Arrays.stream(values()).filter(v -> v.getId() == id).findAny().orElse(null);
    }
}
