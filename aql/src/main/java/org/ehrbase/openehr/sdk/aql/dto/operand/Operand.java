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
    @JsonSubTypes.Type(value = IdentifiedPath.class, name = "IdentifiedPath"),
    @JsonSubTypes.Type(value = QueryParameter.class, name = "QueryParameter"),
    @JsonSubTypes.Type(value = BooleanPrimitive.class, name = "Boolean"),
    @JsonSubTypes.Type(value = DoublePrimitive.class, name = "Double"),
    @JsonSubTypes.Type(value = LongPrimitive.class, name = "Long"),
    @JsonSubTypes.Type(value = NullPrimitive.class, name = "Null"),
    @JsonSubTypes.Type(value = SingleRowFunction.class, name = "SingleRowFunction"),
    @JsonSubTypes.Type(value = StringPrimitive.class, name = "String"),
    @JsonSubTypes.Type(value = TemporalPrimitive.class, name = "Temporal"),
    @JsonSubTypes.Type(value = TerminologyFunction.class, name = "TerminologyFunction")
})
public sealed interface Operand permits FunctionCall, IdentifiedPath, Primitive, QueryParameter {}
