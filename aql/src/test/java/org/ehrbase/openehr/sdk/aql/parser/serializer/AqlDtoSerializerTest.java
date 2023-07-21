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
package org.ehrbase.openehr.sdk.aql.parser.serializer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ehrbase.openehr.sdk.aql.dto.AqlQuery;
import org.ehrbase.openehr.sdk.aql.parser.AqlQueryParser;
import org.ehrbase.openehr.sdk.aql.render.AqlRenderer;
import org.junit.jupiter.api.Test;

/**
 * @author Stefan Spiska
 */
class AqlDtoSerializerTest {

    @Test
    void to() throws JsonProcessingException {
        ObjectMapper cut = AqlDtoSerializer.getObjectMapper();

        String aql =
                "SELECT o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude AS Systolic__magnitude, e/ehr_id/value AS ehr_id FROM EHR e CONTAINS COMPOSITION CONTAINS OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1] WHERE (o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude >= $magnitude AND o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude < 1.1) ORDER BY o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude ASC";

        AqlQuery parse = AqlQueryParser.parse(aql);

        String value = cut.writerWithDefaultPrettyPrinter().writeValueAsString(parse);

        AqlQuery read = cut.readValue(value, AqlQuery.class);

        String actual = AqlRenderer.render(read);

        assertThat(actual).isEqualTo(aql);
    }
}
