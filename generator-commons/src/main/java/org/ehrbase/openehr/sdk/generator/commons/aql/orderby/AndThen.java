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
package org.ehrbase.openehr.sdk.generator.commons.aql.orderby;

import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;

public class AndThen implements OrderByExpression {

    private final OrderByExpression first;
    private final OrderByExpression second;

    protected AndThen(OrderByExpression first, OrderByExpression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String buildAql(Containment ehrContainment) {

        StringBuilder sb = new StringBuilder();
        return sb.append(first.buildAql(ehrContainment))
                .append(", ")
                .append(second.buildAql(ehrContainment))
                .toString();
    }
}
