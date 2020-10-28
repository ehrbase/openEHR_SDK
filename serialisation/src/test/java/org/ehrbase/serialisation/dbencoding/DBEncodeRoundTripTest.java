/*
 * Copyright (c) 2019 Vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project EHRbase
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehrbase.serialisation.dbencoding;

import com.nedap.archie.rm.composition.Composition;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.SoftAssertions;
import org.ehrbase.serialisation.dbencoding.rawjson.LightRawJsonEncoder;
import org.ehrbase.serialisation.flatencoding.FlatFormat;
import org.ehrbase.serialisation.flatencoding.FlatJasonProvider;
import org.ehrbase.serialisation.flatencoding.FlatJson;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.templateprovider.TestDataTemplateProvider;
import org.ehrbase.test_data.composition.CompositionTestDataSimSDTJson;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.ehrbase.serialisation.flatencoding.std.marshal.FlatJsonMarshallerTest.compere;
import static org.junit.Assert.assertNotNull;

@Ignore("see https://github.com/ehrbase/openEHR_SDK/issues/117")
public class DBEncodeRoundTripTest {

    class TestCase {
        long id;
        CompositionTestDataSimSDTJson simSDTJson;
        String templateId;
        String[] missing;
        String[] extra;

        public TestCase(long id, CompositionTestDataSimSDTJson simSDTJson, String templateId, String[] missing, String[] extra) {
            this.id = id;
            this.simSDTJson = simSDTJson;
            this.templateId = templateId;
            this.missing = missing;
            this.extra = extra;
        }
    }

    @Test
    public void testRoundTrip() throws IOException {

        List<TestCase> testCaseList = new ArrayList<>();

        testCaseList.add(new TestCase(
                1,
                CompositionTestDataSimSDTJson.ALL_TYPES,
                "test_all_types.en.v1",
                new String[]{
                        "Missing path: test_all_types/test_all_types:0/identifier|id, value: 55175056",
                        "Missing path: test_all_types/test_all_types:0/proportion_any|type, value: 1"
                },
                new String[]{
                        "Extra path: test_all_types/test_all_types:0/identifier, value: 55175056",
                        "Extra path: test_all_types/test_all_types:0/proportion_any|type, value: 1.0"
                }));

        testCaseList.add(new TestCase(
                2,
                CompositionTestDataSimSDTJson.CORONA,
                "Corona_Anamnese",
                new String[]{
                },
                new String[]{
                }));

        testCaseList.add(new TestCase(
                3,
                CompositionTestDataSimSDTJson.ALTERNATIVE_EVENTS,
                "AlternativeEvents",
                new String[]{
                        "Missing path: bericht/körpergewicht:0/birth_en/gewicht|magnitude, value: 30.0",
                        "Missing path: bericht/körpergewicht:0/any_event_en:1/gewicht|magnitude, value: 60.0",
                        "Missing path: bericht/körpergewicht:0/any_event_en:2/gewicht|magnitude, value: 61.0",
                        "Missing path: bericht/körpergewicht:0/any_event_en:0/gewicht|magnitude, value: 55.0",
                        "Missing path: bericht/körpergewicht:0/any_event_en:3/gewicht|magnitude, value: 62.0"
                },
                new String[]{
                        "Extra path: bericht/körpergewicht:0/any_event_en:0/gewicht|magnitude, value: 55",
                        "Extra path: bericht/körpergewicht:0/any_event_en:1/gewicht|magnitude, value: 60",
                        "Extra path: bericht/körpergewicht:0/any_event_en:2/gewicht|magnitude, value: 61",
                        "Extra path: bericht/körpergewicht:0/any_event_en:3/gewicht|magnitude, value: 62",
                        "Extra path: bericht/körpergewicht:0/birth_en/gewicht|magnitude, value: 30"

                }));


        testCaseList.add(new TestCase(
                4,
                CompositionTestDataSimSDTJson.MULTI_OCCURRENCE,
                "ehrbase_multi_occurrence.de.v1",
                new String[]{
                        "Missing path: encounter/body_temperature:0/any_event:0/temperature|magnitude, value: 22.0",
                        "Missing path: encounter/body_temperature:0/any_event:1/temperature|magnitude, value: 11.0",
                        "Missing path: encounter/body_temperature:1/any_event:0/temperature|magnitude, value: 22.0",
                        "Missing path: encounter/body_temperature:1/any_event:1/temperature|magnitude, value: 11.0"
                },
                new String[]{
                        "Extra path: encounter/context/_end_time, value: 2020-10-06T13:30:34.317875+02:00",
                        "Extra path: encounter/body_temperature:0/any_event:0/temperature|magnitude, value: 22",
                        "Extra path: encounter/body_temperature:0/any_event:1/temperature|magnitude, value: 11",
                        "Extra path: encounter/body_temperature:1/any_event:0/temperature|magnitude, value: 22",
                        "Extra path: encounter/body_temperature:1/any_event:1/temperature|magnitude, value: 11"

                }));

        SoftAssertions softly = new SoftAssertions();

        for (TestCase testCase : testCaseList) {
            checkTestCase(testCase, softly);
        }

        softly.assertAll();

    }


    public void checkTestCase(TestCase testCase, SoftAssertions softly) throws IOException {

        String value = IOUtils.toString(testCase.simSDTJson.getStream(), UTF_8);
        FlatJson flatJson = new FlatJasonProvider(new TestDataTemplateProvider()).buildFlatJson(FlatFormat.SIM_SDT, testCase.templateId);

        Composition composition = flatJson.unmarshal(value);
        assertThat(composition).isNotNull();

        CompositionSerializer compositionSerializerRawJson = new CompositionSerializer();

        String db_encoded = compositionSerializerRawJson.dbEncode(composition);

        assertNotNull(db_encoded);

        String converted = new LightRawJsonEncoder(db_encoded).encodeCompositionAsString();

        assertNotNull(converted);

        Composition actual = new CanonicalJson().unmarshal(converted, Composition.class);

        String actualFlat = flatJson.marshal(actual);

        List<String> errors = compere(actualFlat, value);

        softly.assertThat(errors)
                .filteredOn(s -> s.startsWith("Missing"))
                .as("Test Case %s", testCase.id)
                .containsExactlyInAnyOrder(
                        testCase.missing
                );

        String[] extra = {"Extra path: test_all_types/test_all_types:0/identifier, value: 55175056",
                "Extra path: test_all_types/test_all_types:0/proportion_any|type, value: 1.0"};
        softly.assertThat(errors)
                .filteredOn(s -> s.startsWith("Extra"))
                .as("Test Case %s", testCase.id)
                .containsExactlyInAnyOrder(
                        testCase.extra
                );
    }
}
