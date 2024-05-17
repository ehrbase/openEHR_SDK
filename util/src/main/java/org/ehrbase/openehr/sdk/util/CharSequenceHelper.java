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

import java.nio.CharBuffer;
import org.apache.commons.lang3.StringUtils;

/**
 * For optimizing performance of string based operations
 */
public class CharSequenceHelper {

    private CharSequenceHelper() {
        // NOOP
    }

    public static CharSequence subSequence(CharSequence str, int beginIndex) {
        return subSequence(str, beginIndex, str.length());
    }

    public static CharSequence subSequence(CharSequence str, int beginIndex, int endIndex) {
        if (beginIndex == endIndex) {
            return "";
        } else if (beginIndex == 0 && endIndex == str.length()) {
            return str;
        } else if (str.getClass() == String.class) {
            return CharBuffer.wrap(str, beginIndex, endIndex);
        } else {
            // CharBuffer does not have to be wrapped
            return str.subSequence(beginIndex, endIndex);
        }
    }

    public static CharSequence removeStart(final CharSequence str, final String remove) {
        if (str == null) {
            return null;
        }
        int strLen = str.length();
        if (strLen == 0) {
            return str;
        }
        int removeLen = remove.length();
        if (strLen < removeLen) {
            return str;
        }

        if (0 == compareSubsequence(remove, 0, removeLen, str, 0, removeLen)) {
            return subSequence(str, removeLen, strLen);
        } else {
            return str;
        }
    }

    public static CharSequence removeEnd(final CharSequence str, final String remove) {
        if (str == null) {
            return null;
        }
        int strLen = str.length();
        if (strLen == 0) {
            return str;
        }
        int removeLen = remove.length();
        if (strLen < removeLen) {
            return str;
        }

        int len = strLen - removeLen;
        if (0 == compareSubsequence(remove, 0, removeLen, str, len, strLen)) {
            return subSequence(str, 0, len);
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
            int strLen = str.length();
            if (pos == 0) {
                // leading separator
                if (strLen == 1) {
                    return new CharSequence[0];
                } else {
                    return new CharSequence[] {subSequence(str, 1, strLen)};
                }
            } else if (pos + 1 == strLen) {
                // trailing separator
                return new CharSequence[] {subSequence(str, 0, pos)};
            } else {
                return new CharSequence[] {subSequence(str, 0, pos), subSequence(str, pos + 1, strLen)};
            }
        }
    }

    public static int compareSubsequence(
            CharSequence cs1, int start1, int end1, CharSequence cs2, int start2, int end2) {

        if (cs1 == cs2 && start1 == start2) {
            return end1 - end2;
        }

        int len1 = end1 - start1;
        int len2 = end2 - start2;
        int len = Math.min(len1, len2);

        if ((start1 & start2) == 0 && len == cs1.length() && len == cs2.length()) {
            return CharSequence.compare(cs1, cs2);
        }

        for (int p1 = start1, p2 = start2, e1 = start1 + len; p1 < e1; p1++, p2++) {
            char a = cs1.charAt(p1);
            char b = cs2.charAt(p2);
            if (a != b) {
                return a - b;
            }
        }

        return len1 - len2;
    }

    public static CharSequence trim(CharSequence value) {
        int length = value.length();
        int len = length;
        int st = 0;
        while (st < len && value.charAt(st) <= ' ') {
            st++;
        }
        while (st < len && value.charAt(len - 1) <= ' ') {
            len--;
        }
        return ((st > 0) || (len < length)) ? subSequence(value, st, len) : value;
    }
}
