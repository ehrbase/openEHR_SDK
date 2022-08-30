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
package org.ehrbase.aql.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class CharSequenceHelperTest {

    @Test
    public void removeStart() {
        assertEquals("TEST", CharSequenceHelper.removeStart("TEST", "X").toString());
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
}
