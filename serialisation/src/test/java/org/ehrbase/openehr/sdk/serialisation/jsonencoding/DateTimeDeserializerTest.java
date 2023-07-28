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
package org.ehrbase.openehr.sdk.serialisation.jsonencoding;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rm.datavalues.quantity.ReferenceRange;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.ehrbase.openehr.sdk.util.OpenEHRDateTimeParseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

class DateTimeDeserializerTest {

    @ParameterizedTest
    @EnumSource(DateTimeDeserializerTestData.class)
    void deserialize(DateTimeDeserializerTestData data) throws IOException {
        ObjectMapper om = ArchieObjectMapperProvider.getObjectMapper().copy();
        DateTimeDeserializer cut = Mockito.spy(new DateTimeDeserializer());
        SimpleModule module = new SimpleModule();
        module.addDeserializer(DvDateTime.class, cut);
        om.registerModule(module);

        if (data.shouldThrowException) {
            Exception exception = Assertions.assertThrows(
                    data.expectedExceptionType, () -> om.readValue(data.json, DvDateTime.class));
            if (data.expectedExceptionMessage != null) {
                Assertions.assertEquals(data.expectedExceptionMessage, exception.getMessage());
            }
        } else {
            Assertions.assertEquals(data.pojo, om.readValue(data.json, DvDateTime.class));
        }
        // Make sure the deserializer is used
        Mockito.verify(cut, Mockito.times(data.invocationCount))
                .deserialize(ArgumentMatchers.any(), ArgumentMatchers.any());
    }

    @Test
    void withFailOnUnknownProperty() throws IOException {
        ObjectMapper om = ArchieObjectMapperProvider.getObjectMapper().copy();
        om.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        DateTimeDeserializer cut = Mockito.spy(new DateTimeDeserializer());
        SimpleModule module = new SimpleModule();
        module.addDeserializer(DvDateTime.class, cut);
        om.registerModule(module);

        Assertions.assertEquals(
                "Property \"unknown\" is not part of DvDateTime (through reference chain: com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime[\"unknown\"])",
                Assertions.assertThrows(
                                JsonMappingException.class,
                                () -> om.readValue(
                                        "{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023-01-02\",\"unknown\":\"foobar\"}",
                                        DvDateTime.class))
                        .getMessage());

        Mockito.verify(cut).deserialize(ArgumentMatchers.any(), ArgumentMatchers.any());
    }

    @Test
    void createInstance() {
        DateTimeDeserializer cut = new DateTimeDeserializer();
        Assertions.assertEquals(new DvDateTime(), cut.createInstance());
    }

    enum DateTimeDeserializerTestData {
        EMPTY(new DvDateTime(), "{\"_type\":\"DV_DATE_TIME\"}", 1),
        TIME_ONLY_RESOLUTION_FULL_WITH_OFFSET(
                new DvDateTime(OpenEHRDateTimeParseUtils.parseDateTime("2023-01-02T20:15:10.123456789+01:00")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023-01-02T20:15:10.123456789+01:00\"}",
                1),
        TIME_ONLY_RESOLUTION_FULL(
                new DvDateTime(OpenEHRDateTimeParseUtils.parseDateTime("2023-01-02T20:15:10.123456789")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023-01-02T20:15:10.123456789\"}",
                1),
        TIME_ONLY_RESOLUTION_MILLISECONDS(
                new DvDateTime(OpenEHRDateTimeParseUtils.parseDateTime("2023-01-02T20:15:10.123")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023-01-02T20:15:10.123\"}",
                1),
        TIME_ONLY_RESOLUTION_SECONDS(
                new DvDateTime(OpenEHRDateTimeParseUtils.parseDateTime("2023-01-02T20:15:10")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023-01-02T20:15:10\"}",
                1),
        TIME_ONLY_RESOLUTION_MINUTES(
                new DvDateTime(OpenEHRDateTimeParseUtils.parseDateTime("2023-01-02T20:15")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023-01-02T20:15\"}",
                1),
        TIME_ONLY_RESOLUTION_HOURS(
                new DvDateTime(OpenEHRDateTimeParseUtils.parseDateTime("2023-01-02T20")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023-01-02T20\"}",
                1),
        TIME_ONLY_RESOLUTION_DAY(
                new DvDateTime(OpenEHRDateTimeParseUtils.parseDateTime("2023-01-02")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023-01-02\"}",
                1),
        TIME_ONLY_RESOLUTION_MONTH(
                new DvDateTime(OpenEHRDateTimeParseUtils.parseDateTime("2023-01")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023-01\"}",
                1),
        TIME_ONLY_RESOLUTION_YEAR(
                new DvDateTime(OpenEHRDateTimeParseUtils.parseDateTime("2023")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023\"}",
                1),
        TIME_ONLY_RESOLUTION_FULL_WITH_OFFSET_COMPACT(
                new DvDateTime(OpenEHRDateTimeParseUtils.parseDateTime("2023-01-02T20:15:10.123456789+01:00")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"20230102T201510.123456789+0100\"}",
                1),
        TIME_ONLY_RESOLUTION_FULL_COMPACT(
                new DvDateTime(OpenEHRDateTimeParseUtils.parseDateTime("2023-01-02T20:15:10.123456789")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"20230102T201510.123456789\"}",
                1),
        TIME_ONLY_RESOLUTION_MILLISECONDS_COMPACT(
                new DvDateTime(OpenEHRDateTimeParseUtils.parseDateTime("2023-01-02T20:15:10.123")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"20230102T201510.123\"}",
                1),
        TIME_ONLY_RESOLUTION_SECONDS_COMPACT(
                new DvDateTime(OpenEHRDateTimeParseUtils.parseDateTime("2023-01-02T20:15:10")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"20230102T201510\"}",
                1),
        TIME_ONLY_RESOLUTION_MINUTES_COMPACT(
                new DvDateTime(OpenEHRDateTimeParseUtils.parseDateTime("2023-01-02T20:15")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"20230102T2015\"}",
                1),
        TIME_ONLY_RESOLUTION_HOURS_COMPACT(
                new DvDateTime(OpenEHRDateTimeParseUtils.parseDateTime("2023-01-02T20")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"20230102T20\"}",
                1),
        TIME_ONLY_RESOLUTION_DAY_COMPACT(
                new DvDateTime(OpenEHRDateTimeParseUtils.parseDateTime("2023-01-02")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"20230102\"}",
                1),
        TIME_ONLY_RESOLUTION_MONTH_COMPACT(
                new DvDateTime(OpenEHRDateTimeParseUtils.parseDateTime("2023-01")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"202301\"}",
                1),
        TIME_ONLY_RESOLUTION_YEAR_COMPACT(
                new DvDateTime(OpenEHRDateTimeParseUtils.parseDateTime("2023")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023\"}",
                1),
        ALL_ATTRIBUTES_SET_FULL_RESOLUTION_WITH_OFFSET(
                new DvDateTime(
                        List.of(new ReferenceRange<>(new DvText("meaning"), new DvInterval<DvDateTime>(null, null))),
                        new DvInterval<>(new DvDateTime(LocalDate.of(2023, 1, 1)), null),
                        new CodePhrase(),
                        "magStat",
                        new DvDuration(),
                        OpenEHRDateTimeParseUtils.parseDateTime("2023-01-02T20:15:10.123456789+01:00")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023-01-02T20:15:10.123456789+01:00\",\"normal_status\":{\"_type\":\"CODE_PHRASE\"},\"normal_range\":{\"_type\":\"DV_INTERVAL\",\"lower\":{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023-01-01\"},\"upper_unbounded\":true,\"lower_unbounded\":false,\"lower_included\":true,\"upper_included\":false},\"other_reference_ranges\":[{\"range\":{\"_type\":\"DV_INTERVAL\",\"upper_unbounded\":true,\"lower_unbounded\":true,\"lower_included\":false,\"upper_included\":false},\"meaning\":{\"_type\":\"DV_TEXT\",\"value\":\"meaning\"}}],\"magnitude_status\":\"magStat\",\"accuracy\":{\"_type\":\"DV_DURATION\"}}",
                2),

        INVALID(
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023-01-00T20:15:10.123456789+01:00\"}",
                JsonMappingException.class,
                "Text '2023-01-00T20:15:10.123456789+01:00' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): 0:2023-01-00T20:15:10.123456789+01:00 (through reference chain: com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime[\"value\"])",
                1),

        INVALID_COMPACT(
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"20230100T201510.123456789+0100\"}",
                JsonMappingException.class,
                "Text '20230100T201510.123456789+0100' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): 0:20230100T201510.123456789+0100 (through reference chain: com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime[\"value\"])",
                1);

        private final DvDateTime pojo;
        private final String json;
        private final boolean shouldThrowException;
        private final Class<? extends Exception> expectedExceptionType;
        private final String expectedExceptionMessage;
        private final int invocationCount;

        DateTimeDeserializerTestData(DvDateTime pojo, String json, int invocationCount) {
            this.pojo = pojo;
            this.json = json;
            this.invocationCount = invocationCount;
            this.expectedExceptionMessage = null;
            this.expectedExceptionType = null;
            this.shouldThrowException = false;
        }

        DateTimeDeserializerTestData(
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
