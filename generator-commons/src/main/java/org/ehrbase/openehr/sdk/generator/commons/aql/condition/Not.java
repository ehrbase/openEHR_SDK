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

import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;

public class Not implements Condition {

    private final Condition condition;

    Not(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String buildAql(Containment ehrContainment) {
        StringBuilder builder = new StringBuilder();
        return builder.append("NOT ").append(condition.buildAql(ehrContainment)).toString();
    }
}
