/*
 * Copyright (c) 2024 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.response.dto.util;

import java.util.function.Consumer;
import javax.annotation.Nullable;
import org.ehrbase.openehr.sdk.response.dto.ehrscape.QueryResultDto;

public class DTOFixtures {

    public static QueryResultDto fixtureQueryResultResultDto() {
        return fixtureQueryResultResultDto(null);
    }

    public static QueryResultDto fixtureQueryResultResultDto(@Nullable Consumer<QueryResultDto> customize) {

        QueryResultDto queryResultDto = new QueryResultDto();
        queryResultDto.setExecutedAQL("SELECT e/ehr_id/value FROM EHR e LIMIT 100 OFFSET 1");

        if (null != customize) {
            customize.accept(queryResultDto);
        }

        return queryResultDto;
    }
}
