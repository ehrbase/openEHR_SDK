/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.client.aql.funtion;

import java.util.Collections;
import org.ehrbase.aql.dto.select.AQLFunction;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.SelectAqlField;

/**
 * @author Stefan Spiska
 */
public class Count extends AbstractFunction<Integer> {

    protected Count(SelectAqlField<?> parameters, String name) {
        super(Collections.singletonList(parameters), AQLFunction.COUNT, name);
    }

    @Override
    public Class<Integer> getValueClass() {
        return Integer.class;
    }

    @Override
    public boolean isMultiValued() {
        return false;
    }

    @Override
    public Containment getContainment() {
        return null;
    }

    @Override
    public String getPath() {
        return AQLFunction.COUNT.name();
    }

    @Override
    public Class<?> getEntityClass() {
        return null;
    }
}
