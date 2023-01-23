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
package org.ehrbase.serialisation.jsonencoding;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nedap.archie.datetime.DateTimeParsers;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rm.datavalues.quantity.ReferenceRange;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import org.ehrbase.serialisation.exception.MarshalException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class DateTimeSerializerTest {

    @ParameterizedTest
    @EnumSource(DateTimeSerializerTestData.class)
    void serialize(DateTimeSerializerTestData data) throws IOException {
        StringWriter stringWriter = new StringWriter();
        JsonGenerator generator = JsonFactory.builder().build().createGenerator(stringWriter);
        generator.setCodec(CanonicalJson.MARSHAL_OM
                .copy()
                .configure(SerializationFeature.INDENT_OUTPUT, false)
                .setDefaultPrettyPrinter(null));
        DateTimeSerializer cut = new DateTimeSerializer();
        if (data.shouldThrowException) {
            Assertions.assertEquals(
                    data.expectedExceptionMessage,
                    Assertions.assertThrows(
                                    data.expectedExceptionType, () -> cut.serialize(data.testData, generator, null))
                            .getMessage());
        } else {
            Assertions.assertDoesNotThrow(() -> cut.serialize(data.testData, generator, null));
            generator.flush();
            assertThat(stringWriter.toString()).isEqualToIgnoringNewLines(data.expectedJson);
        }
    }

    @ParameterizedTest
    @EnumSource(DateTimeSerializerTestData.class)
    void serializeWithType(DateTimeSerializerTestData data) throws IOException {
        StringWriter stringWriter = new StringWriter();
        JsonGenerator generator = JsonFactory.builder().build().createGenerator(stringWriter);
        generator.setCodec(CanonicalJson.MARSHAL_OM
                .copy()
                .configure(SerializationFeature.INDENT_OUTPUT, false)
                .setDefaultPrettyPrinter(null));
        DateTimeSerializer cut = new DateTimeSerializer();
        if (data.shouldThrowException) {
            Assertions.assertEquals(
                    data.expectedExceptionMessage,
                    Assertions.assertThrows(
                                    data.expectedExceptionType,
                                    () -> cut.serializeWithType(data.testData, generator, null, null))
                            .getMessage());
        } else {
            Assertions.assertDoesNotThrow(() -> cut.serializeWithType(data.testData, generator, null, null));
            generator.flush();
            assertThat(stringWriter.toString()).isEqualToIgnoringNewLines(data.expectedJson);
        }
    }

    enum DateTimeSerializerTestData {
        EMPTY(new DvDateTime(), MarshalException.class, "DV_DATE_TIME is missing the mandatory time attribute"),
        TIME_ONLY_RESOLUTION_FULL_WITH_OFFSET(
                new DvDateTime(DateTimeParsers.parseDateTimeValue("2023-01-02T20:15:10.123456789+01:00")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023-01-02T20:15:10.123456789+01:00\"}"),
        TIME_ONLY_RESOLUTION_FULL(
                new DvDateTime(DateTimeParsers.parseDateTimeValue("2023-01-02T20:15:10.123456789")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023-01-02T20:15:10.123456789\"}"),
        TIME_ONLY_RESOLUTION_SECONDS(
                new DvDateTime(DateTimeParsers.parseDateTimeValue("2023-01-02T20:15:10")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023-01-02T20:15:10\"}"),
        TIME_ONLY_RESOLUTION_MINUTES(
                new DvDateTime(DateTimeParsers.parseDateTimeValue("2023-01-02T20:15")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023-01-02T20:15:00\"}"),
        TIME_ONLY_RESOLUTION_HOURS(
                new DvDateTime(DateTimeParsers.parseDateTimeValue("2023-01-02T20")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023-01-02T20:00:00\"}"),
        TIME_ONLY_RESOLUTION_DAY(
                new DvDateTime(DateTimeParsers.parseDateTimeValue("2023-01-02")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023-01-02\"}"),
        TIME_ONLY_RESOLUTION_MONTH(
                new DvDateTime(DateTimeParsers.parseDateTimeValue("2023-01")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023-01\"}"),
        TIME_ONLY_RESOLUTION_YEAR(
                new DvDateTime(DateTimeParsers.parseDateTimeValue("2023")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023\"}"),
        TIME_MISSING(new DvDateTime(), MarshalException.class, "DV_DATE_TIME is missing the mandatory time attribute"),
        ALL_ATTRIBUTES_SET_FULL_RESOLUTION_WITH_OFFSET(
                new DvDateTime(
                        List.of(new ReferenceRange<>(new DvText("meaning"), new DvInterval<DvDateTime>(null, null))),
                        new DvInterval<>(),
                        new CodePhrase(),
                        "magStat",
                        new DvDuration(),
                        DateTimeParsers.parseDateTimeValue("2023-01-02T20:15:10.123456789+01:00")),
                "{\"_type\":\"DV_DATE_TIME\",\"value\":\"2023-01-02T20:15:10.123456789+01:00\",\"normal_status\":{\"_type\":\"CODE_PHRASE\"},\"normal_range\":{\"_type\":\"DV_INTERVAL\",\"upper_unbounded\":false,\"lower_unbounded\":false,\"lower_included\":true,\"upper_included\":true},\"other_reference_ranges\":[{\"range\":{\"_type\":\"DV_INTERVAL\",\"upper_unbounded\":true,\"lower_unbounded\":true,\"lower_included\":false,\"upper_included\":false},\"meaning\":{\"_type\":\"DV_TEXT\",\"value\":\"meaning\"}}],\"magnitude_status\":\"magStat\",\"accuracy\":{\"_type\":\"DV_DURATION\"}}");

        private final DvDateTime testData;
        private final String expectedJson;
        private final boolean shouldThrowException;
        private final Class<? extends Exception> expectedExceptionType;
        private final String expectedExceptionMessage;

        DateTimeSerializerTestData(DvDateTime testData, String expectedJson) {
            this.testData = testData;
            this.expectedJson = expectedJson;
            this.expectedExceptionMessage = null;
            this.expectedExceptionType = null;
            this.shouldThrowException = false;
        }

        DateTimeSerializerTestData(
                DvDateTime testData,
                Class<? extends Exception> expectedExceptionType,
                String expectedExceptionMessage) {
            this.expectedExceptionType = expectedExceptionType;
            this.expectedExceptionMessage = expectedExceptionMessage;
            this.testData = testData;
            this.expectedJson = null;
            this.shouldThrowException = true;
        }
    }
}
