/*
 * Copyright (c) 2023 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.aql.dto.select;

import org.ehrbase.aql.dto.condition.SimpleValue;

/**
 * @author Stefan Spiska
 */
public class SelectPrimitiveDto implements SelectStatementDto {

    private String name;

    private SimpleValue simpleValue;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public SimpleValue getSimpleValue() {
        return simpleValue;
    }

    public void setSimpleValue(SimpleValue simpleValue) {
        this.simpleValue = simpleValue;
    }
}
