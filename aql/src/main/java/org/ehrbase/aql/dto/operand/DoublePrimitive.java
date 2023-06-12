/*
 * Copyright (c) 2023 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.aql.dto.operand;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Stefan Spiska
 */
public class DoublePrimitive extends Primitive<Double> {

    private String stringRepresentation;

    public DoublePrimitive() {}

    public DoublePrimitive(Double value) {
        super(checkValue(value));
    }

    public DoublePrimitive(String stringRepresentation) {
        super(checkValue(parse(stringRepresentation)));
        this.stringRepresentation = stringRepresentation;
    }

    private static Double checkValue(Double d) throws NumberFormatException {
        if (d != null && (d.isInfinite() || d.isNaN())) {
            throw new NumberFormatException("%s is not supported".formatted(d));
        }
        return d;
    }

    private static Double parse(String s) {
        if (s == null) {
            return null;
        }

        if (!StringUtils.containsOnly(s, "-0123456789eE.")) {
            throw new NumberFormatException("%s does not match REAL, SCI_REAL or SCI_INTEGER definition".formatted(s));
        }

        return Double.valueOf(s);
    }

    @Override
    public void setValue(Double value) {
        super.setValue(checkValue(value));
        this.stringRepresentation = null;
    }

    public String getStringRepresentation() {
        if (stringRepresentation == null && getValue() != null) {
            this.stringRepresentation = getValue().toString();
        }
        return stringRepresentation;
    }

    public void setStringRepresentation(String stringRepresentation) {
        setValue(checkValue(parse(stringRepresentation)));
        this.stringRepresentation = stringRepresentation;
    }
}
