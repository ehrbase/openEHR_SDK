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
package org.ehrbase.openehr.sdk.aql.dto.containment;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;
import org.ehrbase.openehr.sdk.aql.dto.LogicalOperator;

public final class ContainmentSetOperator
        implements Containment, LogicalOperator<ContainmentSetOperatorSymbol, Containment> {
    ContainmentSetOperatorSymbol symbol;
    List<Containment> values;

    @Override
    @JsonProperty(index = 10)
    public ContainmentSetOperatorSymbol getSymbol() {
        return this.symbol;
    }

    public void setSymbol(ContainmentSetOperatorSymbol symbol) {
        this.symbol = symbol;
    }

    @Override
    @JsonProperty(index = 20)
    public List<Containment> getValues() {
        return this.values;
    }

    public void setValues(List<Containment> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContainmentSetOperator that = (ContainmentSetOperator) o;
        return symbol == that.symbol && Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, values);
    }

    @Override
    public String toString() {
        return "ContainmentSetOperator{" + "symbol=" + symbol + ", values=" + values + '}';
    }
}
