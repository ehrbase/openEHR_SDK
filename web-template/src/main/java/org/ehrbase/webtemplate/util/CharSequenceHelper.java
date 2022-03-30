/*
 *  Copyright (c) 2022  Holger Reise (Vitasystems GmbH) and Hannover Medical School
 *
 *  This file is part of Project EHRbase
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.ehrbase.webtemplate.util;

import org.apache.commons.lang3.StringUtils;

import java.nio.CharBuffer;

/**
 * For optimizing performance of string based operations
 */
public class CharSequenceHelper {

    private CharSequenceHelper() {
        //NOOP
    }

    public static CharBuffer subSequence(CharSequence str, int beginIndex, int endIndex) {
        return CharBuffer.wrap(str, beginIndex, endIndex);
    }

    public static CharSequence removeStart(final CharSequence str, final String remove) {
        if (StringUtils.isEmpty(str) || str.length() < remove.length()) {
            return str;
        }

        if (0 == CharSequence.compare(remove, CharBuffer.wrap(str, 0, remove.length()))) {
            return CharBuffer.wrap(str, remove.length(), str.length());
        } else {
            return str;
        }
    }

    public static CharSequence removeEnd(final CharSequence str, final String remove) {
        if (StringUtils.isEmpty(str) || str.length() < remove.length()) {
            return str;
        }

        if (0 == CharSequence.compare(remove, CharBuffer.wrap(str, str.length() - remove.length(), str.length()))) {
            return CharBuffer.wrap(str, 0, str.length() - remove.length());
        } else {
            return str;
        }
    }

    public static CharSequence[] splitFirst(final CharSequence str, char separator) {
        if (StringUtils.isEmpty(str)) {
            return new CharSequence[0];
        }

        int pos = StringUtils.indexOf(str, separator);
        if (pos < 0) {
            return new CharSequence[] {str};

        } else {
            if (pos == 0) {
                if (str.length() == 1) {
                    return new CharSequence[0];
                } else {
                    return new CharSequence[] {CharBuffer.wrap(str, 1, str.length())};
                }
            } else if (pos + 1 == str.length()) {
                return new CharSequence[] {CharBuffer.wrap(str, 0, pos)};
            } else {
                return new CharSequence[] {
                    CharBuffer.wrap(str, 0, pos),
                    CharBuffer.wrap(str, pos + 1, str.length())
                };
            }
        }
    }
}
