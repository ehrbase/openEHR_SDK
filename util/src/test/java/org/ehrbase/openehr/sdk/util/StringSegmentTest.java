/*
 * Copyright (c) 2025 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.IOException;
import java.io.Reader;
import org.junit.jupiter.api.Test;

class StringSegmentTest {

    @Test
    void stringSegmentWrap() {
        String str = "TEST";
        StringSegment wrapped = StringSegment.wrap(str);
        assertThat(wrapped).hasToString(str);
        assertThat(wrapped).isNotEqualTo(str);
        assertThat(wrapped.length()).isEqualTo(str.length());
    }

    @Test
    void stringSegmentSubSequence() {
        String str = "TEST";
        StringSegment wrapper = StringSegment.wrap(str);
        StringSegment sub = wrapper.subSequence(1, 3);
        assertThat(sub).hasToString("ES");
        assertThat(sub.length()).isEqualTo(2);

        assertThat(sub.charAt(1)).isEqualTo('S');

        assertThatThrownBy(() -> StringSegment.subSequence("TEST", 0, 10))
                .isInstanceOf(StringIndexOutOfBoundsException.class);
    }

    @Test
    void stringSegmentEquals() {
        String str1 = "TEST";
        StringSegment s1 = StringSegment.wrap(str1).subSequence(1, 3);
        assertThat(s1).isEqualTo(s1);
        StringSegment s2 = StringSegment.wrap(str1).subSequence(1, 3);
        assertThat(s1).isEqualTo(s2);
        assertThat(s1).hasSameHashCodeAs(s2);
        StringSegment s3 = StringSegment.wrap("MY_TEST").subSequence(4, 6);
        assertThat(s1).isEqualTo(s3);
        assertThat(s1).hasSameHashCodeAs(s3);
        assertThat(s1).isNotEqualTo(StringSegment.wrap("TEAM").subSequence(1, 3));
    }

    @Test
    void stringSegmentReaderRead() throws IOException {
        Reader rd = StringSegment.wrap("MY_TEST").reader();

        assertThat(rd.read()).isEqualTo('M');
        assertThat(rd.read()).isEqualTo('Y');
        assertThat(rd.skip(2)).isEqualTo(2);
        assertThat(rd.read()).isEqualTo('E');
        assertThat(rd.read()).isEqualTo('S');
        assertThat(rd.read()).isEqualTo('T');
        assertThat(rd.read()).isEqualTo(-1);
        assertThat(rd.read()).isEqualTo(-1);
    }

    @Test
    void stringSegmentReaderReadArray() throws IOException {
        Reader rd = StringSegment.wrap("MY_TEST").reader();

        char[] buf = new char[100];

        assertThat(rd.read(buf, 0, 2)).isEqualTo(2);
        assertThat(rd.skip(1)).isEqualTo(1);
        assertThat(rd.read(buf, 2, 100 - 2)).isEqualTo(4);
        assertThat(rd.read(buf, 0, 0)).isZero();
        assertThat(String.valueOf(buf, 0, 6)).isEqualTo("MYTEST");
    }

    @Test
    void stringSegmentReaderClose() throws IOException {
        Reader rd = StringSegment.wrap("MY_TEST").reader();
        assertThat(rd.read()).isEqualTo('M');
        assertThat(rd.ready()).isTrue();
        rd.close();
        assertThatThrownBy(rd::read).isInstanceOf(IOException.class);
        assertThatThrownBy(rd::reset).isInstanceOf(IOException.class);
        assertThatThrownBy(rd::ready).isInstanceOf(IOException.class);
    }

    @Test
    void stringSegmentReaderMark() throws IOException {
        Reader rd = StringSegment.wrap("MY_TEST").reader();
        assertThat(rd.markSupported()).isTrue();
        assertThat(rd.read()).isEqualTo('M');
        assertThatThrownBy(() -> rd.mark(-1)).isInstanceOf(IllegalArgumentException.class);
        rd.mark(10);
        assertThat(rd.read()).isEqualTo('Y');
        assertThat(rd.read()).isEqualTo('_');
        assertThat(rd.read()).isEqualTo('T');
        rd.reset();
        assertThat(rd.read()).isEqualTo('Y');
        rd.mark(10);
        assertThat(rd.read()).isEqualTo('_');
        assertThat(rd.read()).isEqualTo('T');
        rd.reset();
        assertThat(rd.read()).isEqualTo('_');
        assertThat(rd.read()).isEqualTo('T');
    }
}
