/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.generator.commons.aql.condition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;

public abstract class BinaryLogicalOperator implements Condition {

    protected final List<Condition> conditionList = new ArrayList<>();

    BinaryLogicalOperator(Condition condition1, Condition condition2) {
        conditionList.add(condition1);
        conditionList.add(condition2);
    }

    @Override
    public String buildAql(Containment ehrContainment) {

        return "("
                + conditionList.stream()
                        .map(c -> c.buildAql(ehrContainment))
                        .collect(Collectors.joining(" " + getSymbol() + " "))
                + ")";
    }

    protected abstract String getSymbol();
}
