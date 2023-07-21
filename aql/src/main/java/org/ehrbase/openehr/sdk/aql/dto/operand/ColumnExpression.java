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
package org.ehrbase.openehr.sdk.aql.dto.operand;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author Stefan Spiska
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "_type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = AggregateFunction.class, name = "AggregateFunction"),
    @JsonSubTypes.Type(value = IdentifiedPath.class, name = "IdentifiedPath"),
    @JsonSubTypes.Type(value = BooleanPrimitive.class, name = "BooleanPrimitive"),
    @JsonSubTypes.Type(value = CountDistinctAggregateFunction.class, name = "CountDistinctAggregateFunction"),
    @JsonSubTypes.Type(value = DoublePrimitive.class, name = "DoublePrimitive"),
    @JsonSubTypes.Type(value = LongPrimitive.class, name = "LongPrimitive"),
    @JsonSubTypes.Type(value = NullPrimitive.class, name = "NullPrimitive"),
    @JsonSubTypes.Type(value = SingleRowFunction.class, name = "SingleRowFunction"),
    @JsonSubTypes.Type(value = StringPrimitive.class, name = "StringPrimitive"),
    @JsonSubTypes.Type(value = TemporalPrimitive.class, name = "TemporalPrimitive"),
    @JsonSubTypes.Type(value = TerminologyFunction.class, name = "TerminologyFunction")
})
public interface ColumnExpression {}
