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
package org.ehrbase.client.aql.condition;

import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.aql.parameter.Parameter;

public class NotEqual<T> extends ComparisonOperator<T> {

    protected NotEqual(SelectAqlField<T> field, T value) {
        super(field, value);
    }

    protected NotEqual(SelectAqlField<T> field, Parameter<T> parameter) {
        super(field, parameter);
    }

    public NotEqual(SelectAqlField<T> field, SelectAqlField<T> compereField) {
        super(field, compereField);
    }

    @Override
    protected String getSymbol() {
        return "!=";
    }
}
