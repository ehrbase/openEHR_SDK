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
package org.ehrbase.aql.dto.path.predicate;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.Serializable;
import org.ehrbase.aql.dto.condition.ParameterValue;

/**
 * @author Stefan Spiska
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "_type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = PredicateLogicalOrOperation.class, name = "Or"),
    @JsonSubTypes.Type(value = PredicateLogicalAndOperation.class, name = "And"),
    @JsonSubTypes.Type(value = ParameterValue.class, name = "Parameter"),
    @JsonSubTypes.Type(value = PredicateComparisonOperatorDto.class, name = "Comparison")
})
public interface PredicateDto extends Serializable {}
