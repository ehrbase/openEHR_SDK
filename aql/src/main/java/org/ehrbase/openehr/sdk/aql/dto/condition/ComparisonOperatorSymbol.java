/*
 * Copyright (c) 2020 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.aql.dto.condition;

import java.util.NoSuchElementException;

public enum ComparisonOperatorSymbol {
    EQ("="),
    NEQ("!="),
    GT_EQ(">="),
    GT(">"),
    LT_EQ("<="),
    LT("<");
    private final String symbol;

    ComparisonOperatorSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static ComparisonOperatorSymbol fromSymbol(CharSequence symbol) {
        for (ComparisonOperatorSymbol s : values()) {
            if (s.getSymbol().contentEquals(symbol)) {
                return s;
            }
        }
        throw new NoSuchElementException(symbol.toString());
    }
}
