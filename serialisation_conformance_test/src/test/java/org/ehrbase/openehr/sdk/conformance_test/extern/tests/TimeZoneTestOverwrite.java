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
package org.ehrbase.openehr.sdk.conformance_test.extern.tests;

import static org.assertj.core.api.Assertions.assertThat;

import care.better.platform.web.template.TimeZoneTest;
import com.google.common.collect.ImmutableMap;
import com.nedap.archie.rm.composition.Composition;
import java.util.Map;
import org.ehrbase.openehr.sdk.conformance_test.extern.Helper;
import org.ehrbase.openehr.sdk.serialisation.RMDataFormat;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.FlatFormat;
import org.junit.Test;

public class TimeZoneTestOverwrite extends TimeZoneTest {

    @Override
    @Test
    public void timeZone() throws Exception {
        String template = this.getFileContent("/res/Demo Vitals.opt");
        Map<Object, Object> flatComposition = ImmutableMap.builder()
                .put("ctx/time", "2015-01-01T10:00:00.000+05:00")
                .put("vitals/vitals/body_temperature:0/any_event:0/temperature|magnitude", "37.7")
                .put("vitals/vitals/body_temperature:0/any_event:0/temperature|unit", "°C")
                .build();

        RMDataFormat flatJson = Helper.getFlatJson(template, FlatFormat.SIM_SDT);
        Composition composition =
                flatJson.unmarshal(StatusesTestOverwrite.OBJECT_MAPPER.writeValueAsString(flatComposition));
        assertThat(composition.getContext().getStartTime().getValue()).hasToString("2015-01-01T10:00+05:00");
    }
}
