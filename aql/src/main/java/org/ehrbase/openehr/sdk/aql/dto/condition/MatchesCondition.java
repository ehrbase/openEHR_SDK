/*
 * Copyright (c) 2021 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.aql.dto.condition;

import java.util.List;
import java.util.Objects;
import org.ehrbase.openehr.sdk.aql.dto.operand.IdentifiedPath;
import org.ehrbase.openehr.sdk.aql.dto.operand.MatchesOperand;

public class MatchesCondition implements WhereCondition {

    private IdentifiedPath statement;
    private List<MatchesOperand> values;

    public IdentifiedPath getStatement() {
        return statement;
    }

    public void setStatement(IdentifiedPath statement) {
        this.statement = statement;
    }

    public List<MatchesOperand> getValues() {
        return values;
    }

    public void setValues(List<MatchesOperand> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchesCondition that = (MatchesCondition) o;
        return Objects.equals(statement, that.statement) && Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statement, values);
    }

    @Override
    public String toString() {
        return "MatchesCondition{" + "statement=" + statement + ", values=" + values + '}';
    }
}
