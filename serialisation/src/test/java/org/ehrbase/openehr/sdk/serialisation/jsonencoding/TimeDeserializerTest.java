/*
 * Copyright (c) 2023 vitasystems GmbH.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.serialisation.jsonencoding;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rm.datavalues.quantity.ReferenceRange;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvTime;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import org.ehrbase.openehr.sdk.util.OpenEHRDateTimeParseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

class TimeDeserializerTest {

    @ParameterizedTest
    @EnumSource(TimeDeserializerTestData.class)
    void deserialize(TimeDeserializerTestData data) throws IOException {
        ObjectMapper om = ArchieObjectMapperProvider.getObjectMapper().copy();
        TimeDeserializer cut = Mockito.spy(new TimeDeserializer());
        SimpleModule module = new SimpleModule();
        module.addDeserializer(DvTime.class, cut);
        om.registerModule(module);

        if (data.shouldThrowException) {
            Exception exception =
                    Assertions.assertThrows(data.expectedExceptionType, () -> om.readValue(data.json, DvTime.class));
            if (data.expectedExceptionMessage != null) {
                Assertions.assertEquals(data.expectedExceptionMessage, exception.getMessage());
            }
        } else {
            Assertions.assertEquals(data.pojo, om.readValue(data.json, DvTime.class));
        }
        // Make sure the deserializer is used
        Mockito.verify(cut, Mockito.times(data.invocationCount))
                .deserialize(ArgumentMatchers.any(), ArgumentMatchers.any());
    }

    @Test
    void withFailOnUnknownProperty() throws IOException {
        ObjectMapper om = ArchieObjectMapperProvider.getObjectMapper().copy();
        om.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        TimeDeserializer cut = Mockito.spy(new TimeDeserializer());
        SimpleModule module = new SimpleModule();
        module.addDeserializer(DvTime.class, cut);
        om.registerModule(module);

        Assertions.assertEquals(
                "Property \"unknown\" is not part of DvTime (through reference chain: com.nedap.archie.rm.datavalues.quantity.datetime.DvTime[\"unknown\"])",
                Assertions.assertThrows(
                                JsonMappingException.class,
                                () -> om.readValue(
                                        "{\"_type\":\"DV_TIME\",\"value\":\"10:15\",\"unknown\":\"foobar\"}",
                                        DvTime.class))
                        .getMessage());

        Mockito.verify(cut).deserialize(ArgumentMatchers.any(), ArgumentMatchers.any());
    }

    @Test
    void createInstance() {
        TimeDeserializer cut = new TimeDeserializer();
        Assertions.assertEquals(new DvTime(), cut.createInstance());
    }

    enum TimeDeserializerTestData {
        EMPTY(new DvTime(), "{\"_type\":\"DV_TIME\"}", 1),
        TIME_ONLY_RESOLUTION_FULL_WITH_OFFSET(
                new DvTime(OpenEHRDateTimeParseUtils.parseTime("20:15:10.123456789+01:00")),
                "{\"_type\":\"DV_TIME\",\"value\":\"20:15:10.123456789+01:00\"}",
                1),
        TIME_ONLY_RESOLUTION_FULL(
                new DvTime(OpenEHRDateTimeParseUtils.parseTime("20:15:10.123456789")),
                "{\"_type\":\"DV_TIME\",\"value\":\"20:15:10.123456789\"}",
                1),
        TIME_ONLY_RESOLUTION_MILLISECONDS(
                new DvTime(OpenEHRDateTimeParseUtils.parseTime("20:15:10.123")),
                "{\"_type\":\"DV_TIME\",\"value\":\"20:15:10.123\"}",
                1),
        TIME_ONLY_RESOLUTION_SECONDS(
                new DvTime(OpenEHRDateTimeParseUtils.parseTime("20:15:10")),
                "{\"_type\":\"DV_TIME\",\"value\":\"20:15:10\"}",
                1),
        TIME_ONLY_RESOLUTION_MINUTES(
                new DvTime(OpenEHRDateTimeParseUtils.parseTime("20:15")),
                "{\"_type\":\"DV_TIME\",\"value\":\"20:15\"}",
                1),
        TIME_ONLY_RESOLUTION_HOURS(
                new DvTime(OpenEHRDateTimeParseUtils.parseTime("20")), "{\"_type\":\"DV_TIME\",\"value\":\"20\"}", 1),
        TIME_ONLY_RESOLUTION_FULL_WITH_OFFSET_COMPACT(
                new DvTime(OpenEHRDateTimeParseUtils.parseTime("20:15:10.123456789+01:00")),
                "{\"_type\":\"DV_TIME\",\"value\":\"201510.123456789+0100\"}",
                1),
        TIME_ONLY_RESOLUTION_FULL_COMPACT(
                new DvTime(OpenEHRDateTimeParseUtils.parseTime("20:15:10.123456789")),
                "{\"_type\":\"DV_TIME\",\"value\":\"201510.123456789\"}",
                1),
        TIME_ONLY_RESOLUTION_MILLISECONDS_COMPACT(
                new DvTime(OpenEHRDateTimeParseUtils.parseTime("20:15:10.123")),
                "{\"_type\":\"DV_TIME\",\"value\":\"201510.123\"}",
                1),
        TIME_ONLY_RESOLUTION_SECONDS_COMPACT(
                new DvTime(OpenEHRDateTimeParseUtils.parseTime("20:15:10")),
                "{\"_type\":\"DV_TIME\",\"value\":\"201510\"}",
                1),
        TIME_ONLY_RESOLUTION_MINUTES_COMPACT(
                new DvTime(OpenEHRDateTimeParseUtils.parseTime("20:15")),
                "{\"_type\":\"DV_TIME\",\"value\":\"2015\"}",
                1),
        TIME_ONLY_RESOLUTION_HOURS_COMPACT(
                new DvTime(OpenEHRDateTimeParseUtils.parseTime("20")), "{\"_type\":\"DV_TIME\",\"value\":\"20\"}", 1),
        ALL_ATTRIBUTES_SET_FULL_RESOLUTION_WITH_OFFSET(
                new DvTime(
                        List.of(new ReferenceRange<>(new DvText("meaning"), new DvInterval<DvTime>(null, null))),
                        new DvInterval<>(new DvTime(LocalTime.of(20, 0)), null),
                        new CodePhrase(),
                        "magStat",
                        new DvDuration(),
                        OpenEHRDateTimeParseUtils.parseTime("20:15:10.123456789+00:00")),
                "{\"_type\":\"DV_TIME\",\"value\":\"20:15:10.123456789+00:00\",\"normal_status\":{\"_type\":\"CODE_PHRASE\"},\"normal_range\":{\"_type\":\"DV_INTERVAL\",\"lower\":{\"_type\":\"DV_TIME\",\"value\":\"20:00\"},\"upper_unbounded\":true,\"lower_unbounded\":false,\"lower_included\":true,\"upper_included\":false},\"other_reference_ranges\":[{\"range\":{\"_type\":\"DV_INTERVAL\",\"upper_unbounded\":true,\"lower_unbounded\":true,\"lower_included\":false,\"upper_included\":false},\"meaning\":{\"_type\":\"DV_TEXT\",\"value\":\"meaning\"}}],\"magnitude_status\":\"magStat\",\"accuracy\":{\"_type\":\"DV_DURATION\"}}",
                2),
        INVALID(
                "{\"_type\":\"DV_TIME\",\"value\":\"24:00\"}",
                JsonMappingException.class,
                "Text '24:00' could not be parsed: Invalid value for HourOfDay (valid values 0 - 23): 24:24:00 (through reference chain: com.nedap.archie.rm.datavalues.quantity.datetime.DvTime[\"value\"])",
                1),
        INVALID_COMPACT(
                "{\"_type\":\"DV_TIME\",\"value\":\"2400\"}",
                JsonMappingException.class,
                "Text '2400' could not be parsed: Invalid value for HourOfDay (valid values 0 - 23): 24:2400 (through reference chain: com.nedap.archie.rm.datavalues.quantity.datetime.DvTime[\"value\"])",
                1);

        private final DvTime pojo;
        private final String json;
        private final boolean shouldThrowException;
        private final Class<? extends Exception> expectedExceptionType;
        private final String expectedExceptionMessage;
        private final int invocationCount;

        TimeDeserializerTestData(DvTime pojo, String json, int invocationCount) {
            this.pojo = pojo;
            this.json = json;
            this.invocationCount = invocationCount;
            this.expectedExceptionMessage = null;
            this.expectedExceptionType = null;
            this.shouldThrowException = false;
        }

        TimeDeserializerTestData(
                String json,
                Class<? extends Exception> expectedExceptionType,
                String expectedExceptionMessage,
                int invocationCount) {
            this.expectedExceptionType = expectedExceptionType;
            this.expectedExceptionMessage = expectedExceptionMessage;
            this.json = json;
            this.invocationCount = invocationCount;
            this.pojo = null;
            this.shouldThrowException = true;
        }
    }
}
