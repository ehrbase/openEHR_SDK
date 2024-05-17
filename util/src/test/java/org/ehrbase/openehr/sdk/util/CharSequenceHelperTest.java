/*
 * Copyright (c) 2022 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.nio.CharBuffer;
import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CharSequenceHelperTest {

    @Test
    public void removeStart() {
        Assertions.assertEquals(
                "TEST", CharSequenceHelper.removeStart("TEST", "X").toString());
        assertEquals("TEXTX", CharSequenceHelper.removeStart("TEXTX", "X").toString());
        assertEquals("TEXT", CharSequenceHelper.removeStart("XTEXT", "X").toString());
        assertEquals("XTEXT", CharSequenceHelper.removeStart("XXTEXT", "X").toString());
        assertEquals("", CharSequenceHelper.removeStart("", "X").toString());

        assertEquals("TEXT", CharSequenceHelper.removeStart("XYTEXT", "XY").toString());
    }

    @Test
    public void removeEnd() {
        assertEquals("TEST", CharSequenceHelper.removeEnd("TEST", "X").toString());
        assertEquals("XTEXT", CharSequenceHelper.removeEnd("XTEXT", "X").toString());
        assertEquals("TEXT", CharSequenceHelper.removeEnd("TEXTX", "X").toString());
        assertEquals("TEXTX", CharSequenceHelper.removeEnd("TEXTXX", "X").toString());
        assertEquals("", CharSequenceHelper.removeEnd("", "X").toString());

        assertEquals("TEXT", CharSequenceHelper.removeEnd("TEXTXY", "XY").toString());
    }

    @Test
    public void splitFirst() {
        {
            var split = CharSequenceHelper.splitFirst("", ':');
            assertEquals(0, split.length);
        }
        {
            var split = CharSequenceHelper.splitFirst(":", ':');
            assertEquals(0, split.length);
        }

        {
            var split = CharSequenceHelper.splitFirst("TEXT", ':');
            assertEquals(1, split.length);
            assertEquals("TEXT", split[0].toString());
        }
        {
            var split = CharSequenceHelper.splitFirst(":TEXT", ':');
            assertEquals(1, split.length);
            assertEquals("TEXT", split[0].toString());
        }
        {
            var split = CharSequenceHelper.splitFirst("TEXT:", ':');
            assertEquals(1, split.length);
            assertEquals("TEXT", split[0].toString());
        }
        {
            var split = CharSequenceHelper.splitFirst(":TEXT:", ':');
            assertEquals(1, split.length);
            assertEquals("TEXT:", split[0].toString());
        }

        {
            var split = CharSequenceHelper.splitFirst("FOO:BAR", ':');
            assertEquals(2, split.length);
            assertEquals("FOO", split[0].toString());
            assertEquals("BAR", split[1].toString());
        }
    }

    @Test
    public void trim() {
        // no trimming
        Stream.of("546gnjkmlg hjh g- zjh ukgg", "", CharBuffer.wrap("", 0, 0), CharBuffer.wrap(" tt fs ", 1, 6))
                .forEach(v -> assertSame(v, CharSequenceHelper.trim(v)));

        // trim to empty
        Stream.of(" ", "  \t \r\n ", CharBuffer.wrap("  \t \r\n "))
                .forEach(v -> assertEquals("", CharSequenceHelper.trim(v)));

        Stream.of(
                        Pair.of(" test ", CharBuffer.wrap("test")),
                        Pair.of(
                                " \t 546g\tnjkmlg hjh g- zjh ukgg  \r\n",
                                CharBuffer.wrap("546g\tnjkmlg hjh g- zjh ukgg")))
                .forEach(p -> assertEquals(p.getRight(), CharSequenceHelper.trim(p.getLeft())));
    }
}
