/*
 *  Copyright (c) 2022  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *
 *  This file is part of Project EHRbase
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.ehrbase.conformance_test.extern.tests;

import care.better.platform.web.template.EndTimeTest;
import com.google.common.collect.ImmutableMap;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.Observation;
import com.nedap.archie.rm.composition.Section;
import org.ehrbase.serialisation.RMDataFormat;
import org.ehrbase.serialisation.flatencoding.FlatFormat;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ehrbase.conformance_test.extern.Helper.getFlatJson;
import static org.ehrbase.conformance_test.extern.tests.StatusesTestOverwrite.OBJECT_MAPPER;

public class EndTimeTestOverwrite extends EndTimeTest {

  @Override
  @Test
  public void endTimeTest() throws Exception {
    String template = this.getFileContent("/res/Demo Vitals.opt");
    OffsetDateTime dateTime =
        ZonedDateTime.of(2015, 1, 1, 10, 31, 16, 0, ZoneId.systemDefault()).toOffsetDateTime();
    Map<Object, Object> flatComposition =
        ImmutableMap.builder()
            .put("ctx/language", "sl")
            .put("ctx/territory", "SI")
            .put("ctx/composer_name", "Composer")
            .put("ctx/id_scheme", "ispek")
            .put("ctx/id_namespace", "ispek")
            .put("ctx/end_time", "2016-01-01T12:30:30Z")
            .put(
                "vitals/vitals/haemoglobin_a1c/history_origin",
                DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(dateTime))
            .put("vitals/vitals/haemoglobin_a1c/any_event/test_status|terminology", "local")
            .put("vitals/vitals/haemoglobin_a1c/any_event/test_status|code", "at0037")
            .build();

    RMDataFormat flatJson = getFlatJson(template, FlatFormat.SIM_SDT);
    Composition composition = flatJson.unmarshal(OBJECT_MAPPER.writeValueAsString(flatComposition));

    Observation observation =
        (Observation) ((Section) composition.getContent().get(0)).getItems().get(0);

    assertThat(observation.getData().getOrigin().getValue()).isEqualTo(dateTime);
    assertThat(composition.getContext().getEndTime().getValue())
        .hasToString("2016-01-01T12:30:30Z");
  }
}
