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

import care.better.platform.web.template.TimeZoneTest;
import com.google.common.collect.ImmutableMap;
import com.nedap.archie.rm.composition.Composition;
import org.ehrbase.serialisation.RMDataFormat;
import org.ehrbase.serialisation.flatencoding.FlatFormat;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ehrbase.conformance_test.extern.Helper.getFlatJson;
import static org.ehrbase.conformance_test.extern.tests.StatusesTestOverwrite.OBJECT_MAPPER;

public class TimeZoneTestOverwrite extends TimeZoneTest {

  @Override
  @Test
  public void timeZone() throws Exception {
    String template = this.getFileContent("/res/Demo Vitals.opt");
    Map<Object, Object> flatComposition =
        ImmutableMap.builder()
            .put("ctx/time", "2015-01-01T10:00:00.000+05:00")
            .put("vitals/vitals/body_temperature:0/any_event:0/temperature|magnitude", "37.7")
            .put("vitals/vitals/body_temperature:0/any_event:0/temperature|unit", "°C")
            .build();

    RMDataFormat flatJson = getFlatJson(template, FlatFormat.SIM_SDT);
    Composition composition = flatJson.unmarshal(OBJECT_MAPPER.writeValueAsString(flatComposition));
    assertThat(composition.getContext().getStartTime().getValue())
        .hasToString("2015-01-01T10:00+05:00");
  }
}
