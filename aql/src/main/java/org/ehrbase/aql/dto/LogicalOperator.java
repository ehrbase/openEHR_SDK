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
package org.ehrbase.aql.dto;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Stefan Spiska
 */
public interface LogicalOperator<S, T> {
    S getSymbol();

    List<T> getValues();

    /**
     * Adds the values from the stream.
     * Depending on the implementation, the original object may or may not be modified.
     *
     * @param valuesStream
     * @return
     */
    default LogicalOperator<S, T> addValues(Stream<T> valuesStream) {
        valuesStream.forEach(getValues()::add);
        return this;
    }
}
