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
package org.ehrbase.client.aql.orderby;

import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.SelectAqlField;

abstract class AbstractOrderBy implements OrderByExpression {
    protected final SelectAqlField<?> field;

    AbstractOrderBy(SelectAqlField<?> field) {
        this.field = field;
    }

    @Override
    public String buildAql(Containment ehrContainment) {
        StringBuilder sb = new StringBuilder();

        sb.append(field.buildAQL(ehrContainment)).append(" ").append(getSymbole());
        return sb.toString();
    }

    public abstract String getSymbole();
}
