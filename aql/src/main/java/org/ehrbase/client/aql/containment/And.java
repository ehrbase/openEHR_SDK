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
package org.ehrbase.client.aql.containment;

public class And extends BinaryLogicalOperator {

    protected And(ContainmentExpression containmentExpression1, ContainmentExpression containmentExpression2) {
        super(containmentExpression1, containmentExpression2);
    }

    @Override
    protected String getSymbol() {
        return "and";
    }

    @Override
    public ContainmentExpression and(ContainmentExpression containmentExpression) {
        containmentExpressionList.add(containmentExpression);
        return this;
    }
}
