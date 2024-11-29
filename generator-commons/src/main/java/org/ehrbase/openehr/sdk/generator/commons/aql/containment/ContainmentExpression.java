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
package org.ehrbase.openehr.sdk.generator.commons.aql.containment;

import org.ehrbase.openehr.sdk.generator.commons.aql.query.EntityQuery;

public interface ContainmentExpression {

    String buildAQL();

    void bindQuery(EntityQuery<?> query);

    default ContainmentExpression and(ContainmentExpression containmentExpression) {
        return and(this, containmentExpression);
    }

    default ContainmentExpression or(ContainmentExpression containmentExpression) {
        return or(this, containmentExpression);
    }

    static ContainmentExpression and(
            ContainmentExpression containmentExpression1, ContainmentExpression containmentExpression2) {
        return new And(containmentExpression1, containmentExpression2);
    }

    static ContainmentExpression or(
            ContainmentExpression containmentExpression1, ContainmentExpression containmentExpression2) {
        return new Or(containmentExpression1, containmentExpression2);
    }
}
