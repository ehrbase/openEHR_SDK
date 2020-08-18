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

package org.ehrbase.client.aql.condition;

public abstract class BinaryLogicalOperator implements Condition {

    private final Condition condition1;
    private final Condition condition2;

    BinaryLogicalOperator(Condition condition1, Condition condition2) {
        this.condition1 = condition1;
        this.condition2 = condition2;
    }

    @Override
    public String buildAql() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("(")
                .append(condition1.buildAql())
                .append(" ")
                .append(getSymbol())
                .append(" ")
                .append(condition2.buildAql())
                .append(")");

        return sb.toString();
    }

    protected abstract String getSymbol();

    protected Condition getCondition1() {
        return condition1;
    }

    protected Condition getCondition2() {
        return condition2;
    }
}
