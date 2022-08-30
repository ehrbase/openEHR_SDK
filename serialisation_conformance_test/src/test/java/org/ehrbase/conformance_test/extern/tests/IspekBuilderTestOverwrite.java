/*
 * Copyright (c) 2021 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.conformance_test.extern.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ehrbase.conformance_test.extern.Helper.getFlatJson;
import static org.ehrbase.conformance_test.extern.tests.StatusesTestOverwrite.OBJECT_MAPPER;

import care.better.platform.web.template.IspekBuilderTest;
import com.google.common.collect.ImmutableMap;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.History;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import org.assertj.core.groups.Tuple;
import org.ehrbase.serialisation.RMDataFormat;
import org.ehrbase.serialisation.flatencoding.FlatFormat;
import org.junit.jupiter.api.Test;

public class IspekBuilderTestOverwrite extends IspekBuilderTest {

    @Override
    /* updating a raw composition is not supported */
    public void compositionUpdate() throws Exception {}

    @Override
    /* updating a raw composition is not supported */
    public void compositionUpdateFailed() throws Exception {}

    @Override
    /* updating a raw composition is not supported */
    public void compositionWithInstructionsAndActionsUpdateFailed() throws Exception {}

    @Override
    /*
    Contains calculation of type and Numerator in a way which is not clear to implement see  https://jira.vitagroup.ag/browse/PEM-526
    */
    public void vitalsSingle() throws Exception {}

    @Override
    /*
    Contains calculation of type and Numerator in a way which is not clear to implement  see https://jira.vitagroup.ag/browse/PEM-526
    */
    public void vitalsSingle1() throws Exception {}

    @Override
    /* Test stuff which is not path of the spec. */
    public void vitalsSingle2() throws Exception {}

    @Override
    /*
    Test count of validation error messages with is not part of the spec
    */
    public void vitalsFixedflatComposition() throws Exception {
        super.vitalsFixedflatComposition();
    }

    @Override
    @Test
    public void perinatal() throws Exception {

        // template with constrained offset
        String template = this.getFileContent("/res/MED - Perinatal history Summary.opt");

        OffsetDateTime historyOrigin =
                OffsetDateTime.of(LocalDateTime.of(2022, Month.APRIL, 2, 12, 30), ZoneOffset.UTC);
        Map<Object, Object> flatComposition = ImmutableMap.builder()
                .put("ctx/time", historyOrigin.minusMinutes(10).toString())
                .put("ctx/category", "persistent")
                .put("ctx/history_origin", historyOrigin.toString())
                .put("perinatal_history/perinatal_history/apgar_score/a1_minute/total", "3")
                .put("perinatal_history/perinatal_history/apgar_score/a10_minute/total", "5")
                .put(
                        "perinatal_history/perinatal_history/maternal_pregnancy/labour_or_delivery/duration_of_labour|day",
                        "1")
                .put(
                        "perinatal_history/perinatal_history/maternal_pregnancy/labour_or_delivery/duration_of_labour|hour",
                        "2")
                .build();
        RMDataFormat flatJson = getFlatJson(template, FlatFormat.SIM_SDT);
        Composition composition = flatJson.unmarshal(OBJECT_MAPPER.writeValueAsString(flatComposition));

        History history = (History) composition.itemAtPath(
                "/content[openEHR-EHR-SECTION.ispek_dialog.v1]/items[openEHR-EHR-OBSERVATION.apgar.v1]/data");

        assertThat(history.getOrigin().getValue()).isEqualTo(historyOrigin);

        assertThat(history.getEvents())
                .extracting(e -> ((Event) e).getTime().getValue(), e -> ((Event) e).getNameAsString())
                .containsExactlyInAnyOrder(
                        new Tuple(historyOrigin.plusMinutes(1L), "1 minute"),
                        new Tuple(historyOrigin.plusMinutes(10L), "10 minute"));
    }
}
