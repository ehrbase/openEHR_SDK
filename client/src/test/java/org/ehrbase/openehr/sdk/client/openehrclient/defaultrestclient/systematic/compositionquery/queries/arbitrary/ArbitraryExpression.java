/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.arbitrary;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Parsed;

public class ArbitraryExpression {

    // accept substitution f.e. $ehr_id or $composition_id
    @Parsed(index = 0)
    private String leftSideExpressionPath;

    @Parsed(index = 1)
    private String rightSideExpression;

    // accept substitution f.e. $ehr_id or $composition_id
    @Parsed(index = 2)
    private String expectedResult;

    @Parsed(index = 3)
    private String optionalComment;

    public String getLeftSideExpressionPath() {
        return leftSideExpressionPath;
    }

    public String getRightSideExpression() {
        return rightSideExpression;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public String getOptionalComment() {
        return optionalComment;
    }
}
