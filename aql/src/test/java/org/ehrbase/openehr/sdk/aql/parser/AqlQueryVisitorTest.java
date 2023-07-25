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
package org.ehrbase.openehr.sdk.aql.parser;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/*
 * Copyright (c) 2022. vitasystems GmbH and Hannover Medical School.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

class AqlQueryVisitorTest {

    @Test
    void isOctal() {
        assertThat(AqlQueryVisitor.isOctal('0')).isTrue();
        assertThat(AqlQueryVisitor.isOctal('1')).isTrue();
        assertThat(AqlQueryVisitor.isOctal('7')).isTrue();

        assertThat(AqlQueryVisitor.isOctal('8')).isFalse();
        assertThat(AqlQueryVisitor.isOctal('9')).isFalse();

        assertThat(AqlQueryVisitor.isOctal((char) 7)).isFalse();
        assertThat(AqlQueryVisitor.isOctal('a')).isFalse();
        assertThat(AqlQueryVisitor.isOctal(' ')).isFalse();
    }

    @Test
    void countOctals() {
        assertThat(AqlQueryVisitor.countOctals("", 0, 10)).isEqualTo(0);
        assertThat(AqlQueryVisitor.countOctals("foo", 0, 10)).isEqualTo(0);

        String f00 = "f00";
        assertThat(AqlQueryVisitor.countOctals(f00, 0, 10)).isEqualTo(0);
        assertThat(AqlQueryVisitor.countOctals(f00, 1, 10)).isEqualTo(2);
        assertThat(AqlQueryVisitor.countOctals(f00, 1, 10)).isEqualTo(2);

        String longText = "f1234567890f1234567890";
        assertThat(AqlQueryVisitor.countOctals(longText, 1, 10)).isEqualTo(7);
        assertThat(AqlQueryVisitor.countOctals(longText, 1, 3)).isEqualTo(3);
        assertThat(AqlQueryVisitor.countOctals(longText, 6, 10)).isEqualTo(2);
        assertThat(AqlQueryVisitor.countOctals(longText, 3, 2)).isEqualTo(2);
    }

    @Test
    void decodeUtf() {
        assertThat(AqlQueryVisitor.decodeUtf(16, "0061")).isEqualTo("a");
        assertThat(AqlQueryVisitor.decodeUtf(16, "00e4")).isEqualTo("ä");

        assertThat(AqlQueryVisitor.decodeUtf(8, "0141")).isEqualTo("a");
        assertThat(AqlQueryVisitor.decodeUtf(8, "344")).isEqualTo("ä");

        assertThat(AqlQueryVisitor.decodeUtf(16, "20AC")).isEqualTo("€");

        assertThat(AqlQueryVisitor.decodeUtf(16, "262E")).isEqualTo("☮");
    }
}
