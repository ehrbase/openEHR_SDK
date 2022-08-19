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
package org.ehrbase.serialisation.matrix;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.IOUtils;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.templateprovider.TestDataTemplateProvider;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * @author Stefan Spiska
 */
class EncoderToolTest {

    @Test
    @Disabled
    void createTotalMap() throws IOException {

        List<Row> rowList = new ArrayList<>();

        rowList.addAll(getRows(CompositionTestDataCanonicalJson.CORONA));
        rowList.addAll(getRows(CompositionTestDataCanonicalJson.IPS));
        // rowList.addAll( getRows(CompositionTestDataCanonicalJson.ALL_TYPES));

        EncoderTool encoderTool = new EncoderTool();

        Map<String, String> stringStringMap = encoderTool.buildTotalEncodingMap(rowList);

        StringBuilder appendable = new StringBuilder();
        try (CSVPrinter cv = new CSVPrinter(
                appendable,
                CSVFormat.DEFAULT.builder().setHeader("path", "code").build())) {

            stringStringMap.forEach((k, v) -> {
                try {
                    cv.printRecord(k, v);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        System.out.println(appendable.toString());
    }

    @Test
    @Disabled
    void createPathMap() throws IOException {

        List<Row> rowList = new ArrayList<>();

        rowList.addAll(getRows(CompositionTestDataCanonicalJson.CORONA));
        rowList.addAll(getRows(CompositionTestDataCanonicalJson.IPS));
        // rowList.addAll( getRows(CompositionTestDataCanonicalJson.ALL_TYPES));

        EncoderTool encoderTool = new EncoderTool();

        Map<String, String> stringStringMap = encoderTool.buildPathEncodingMap(rowList);

        StringBuilder appendable = new StringBuilder();
        try (CSVPrinter cv = new CSVPrinter(
                appendable,
                CSVFormat.DEFAULT.builder().setHeader("path", "code").build())) {

            stringStringMap.forEach((k, v) -> {
                try {
                    cv.printRecord(k, v);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        System.out.println(appendable.toString());
    }

    private static List<Row> getRows(CompositionTestDataCanonicalJson dataCanonicalJson) throws IOException {
        String corona = IOUtils.toString(dataCanonicalJson.getStream(), StandardCharsets.UTF_8);

        MatrixFormat cut = new MatrixFormat(new TestDataTemplateProvider(), false);

        return cut.toTable(new CanonicalJson().unmarshal(corona));
    }
}
