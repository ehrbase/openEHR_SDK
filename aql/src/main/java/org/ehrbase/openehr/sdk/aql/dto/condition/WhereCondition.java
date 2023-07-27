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
package org.ehrbase.openehr.sdk.aql.dto.condition;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "_type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ComparisonOperatorCondition.class, name = "ComparisonOperator"),
    @JsonSubTypes.Type(value = LogicalOperatorCondition.class, name = "LogicalOperator"),
    @JsonSubTypes.Type(value = ExistsCondition.class, name = "Exists"),
    @JsonSubTypes.Type(value = LikeCondition.class, name = "Like"),
    @JsonSubTypes.Type(value = MatchesCondition.class, name = "Matches"),
    @JsonSubTypes.Type(value = NotCondition.class, name = "Not")
})
public interface WhereCondition {}
