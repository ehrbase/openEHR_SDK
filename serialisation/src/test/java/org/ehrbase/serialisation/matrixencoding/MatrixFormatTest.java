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
package org.ehrbase.serialisation.matrixencoding;

import static org.assertj.core.api.Assertions.assertThat;

import com.nedap.archie.rm.composition.Composition;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.templateprovider.TestDataTemplateProvider;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.jupiter.api.Test;

/**
 * @author Stefan Spiska
 */
class MatrixFormatTest {

    @Test
    void toMatrix() throws IOException {

        String corona =
                IOUtils.toString(CompositionTestDataCanonicalJson.MULTI_OCCURRENCE.getStream(), StandardCharsets.UTF_8);

        MatrixFormat cut = new MatrixFormat(new TestDataTemplateProvider());

        String actual = cut.marshal(new CanonicalJson().unmarshal(corona));

        String expected = IOUtils.toString(
                MatrixFormat.class.getResourceAsStream("/csv/MULTI_OCCURRENCE.csv"), StandardCharsets.UTF_8);

        assertThat(actual).isEqualToNormalizingNewlines(expected);
    }

    @Test
    void toMatrixCorona() throws IOException {

        String corona = IOUtils.toString(CompositionTestDataCanonicalJson.CORONA.getStream(), StandardCharsets.UTF_8);

        MatrixFormat cut = new MatrixFormat(new TestDataTemplateProvider());

        String actual = cut.marshal(new CanonicalJson().unmarshal(corona));

        String expected =
                IOUtils.toString(MatrixFormat.class.getResourceAsStream("/csv/CORONA.csv"), StandardCharsets.UTF_8);

        assertThat(actual).isEqualToNormalizingNewlines(expected);
    }

    @Test
    void toMatrixIPS() throws IOException {

        String corona = IOUtils.toString(CompositionTestDataCanonicalJson.IPS.getStream(), StandardCharsets.UTF_8);

        MatrixFormat cut = new MatrixFormat(new TestDataTemplateProvider());

        String actual = cut.marshal(new CanonicalJson().unmarshal(corona));

        String expected =
                IOUtils.toString(MatrixFormat.class.getResourceAsStream("/csv/IPS.csv"), StandardCharsets.UTF_8);

        assertThat(actual).isEqualToNormalizingNewlines(expected);
    }

    @Test
    void fromMatrix() throws IOException {
        MatrixFormat cut = new MatrixFormat(new TestDataTemplateProvider());
        Composition actual = cut.unmarshal(
                IOUtils.toString(MatrixFormat.class.getResourceAsStream("/csv/IPS.csv"), StandardCharsets.UTF_8));

        assertThat(actual.getContent()).size().isEqualTo(14);
    }
}
