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
package org.ehrbase.aql.dto.path;

import static org.ehrbase.aql.util.CharSequenceHelper.subSequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.ehrbase.aql.util.CharSequenceHelper;

public class AqlPathHelper {
    private AqlPathHelper() {
        // NOOP
    }

    private static String findPrefix(CharSequence fullPath, int startPos, String... search) {
        int pathLength = fullPath.length() - startPos;
        for (String s : search) {
            int len = s.length();

            if (len <= pathLength
                    && 0 == CharSequenceHelper.compareSubsequence(s, 0, len, fullPath, startPos, startPos + len)) {
                return s;
            }
        }
        return null;
    }

    public interface PrefixMatcher {
        static PrefixMatcher forStrings(String... search) {
            // use first char to determine remaining search options
            int[] firstChars = Arrays.stream(search)
                    .mapToInt(s -> s.charAt(0))
                    .distinct()
                    .sorted()
                    .toArray();
            int minChar = firstChars[0];
            int maxChar = firstChars[firstChars.length - 1];

            // Are the first characters of the search terms numerically close together?
            if (maxChar - minChar > 255) {
                // charMatches array would become rather large: lookup table optimization
                return (firstChar, fullString, firstPos) -> {
                    if (ArrayUtils.contains(firstChars, (int) firstChar)) {
                        return AqlPathHelper.findPrefix(fullString, firstPos, search);
                    } else {
                        return null;
                    }
                };
            }

            // lookup table with search terms be first character
            String[][] charMatches = new String[maxChar - minChar + 1][];
            for (int i = 0; i < firstChars.length; i++) {
                int ii = i;
                charMatches[firstChars[i] - minChar] = Arrays.stream(search)
                        .filter(s -> s.charAt(0) == firstChars[ii])
                        .toArray(String[]::new);
            }

            return (firstChar, fullString, firstPos) -> {
                if (firstChar >= minChar && firstChar <= maxChar) {
                    String[] searchTerms = charMatches[firstChar - minChar];
                    if (searchTerms == null) {
                        return null;
                    } else {
                        return AqlPathHelper.findPrefix(fullString, firstPos, searchTerms);
                    }
                } else {
                    return null;
                }
            };
        }

        static PrefixMatcher forChar(char search) {
            String str = Character.toString(search);
            return (firstChar, fullString, firstPos) -> {
                if (firstChar == search) {
                    return str;
                } else {
                    return null;
                }
            };
        }

        String findPrefix(char firstChar, CharSequence fullString, int firstPos);
    }

    public static List<CharSequence> split(
            CharSequence path, int startPos, int max, boolean addSearch, PrefixMatcher matcher) {
        List<CharSequence> strings = null;

        boolean inBrackets = false;
        boolean inQuotes = false;
        boolean escape = false;

        final int pathLength = path.length();
        int last = startPos;
        for (int i = startPos, l = pathLength; i < l; i++) {
            char ch = path.charAt(i);
            if (!inQuotes && ch == '[') {
                inBrackets = true;
                escape = false;
            } else if (!inQuotes && ch == ']') {
                inBrackets = false;
                escape = false;
            } else if (!escape && ch == '\'') {
                inQuotes = !inQuotes;
            } else if (!escape && ch == '\\') {
                escape = true;
            } else if (inBrackets || inQuotes) {
                escape = false;
            } else {
                String prefix = matcher.findPrefix(ch, path, i);
                if (prefix == null) {
                    escape = false;
                } else {
                    if (strings == null) {
                        // lazy initialization
                        strings = new ArrayList<>(max > 0 && max < 10 ? max : 10);
                    }
                    strings.add(subSequence(path, last, i));
                    if (addSearch) {
                        strings.add(prefix);
                    }
                    last = prefix.length() + i;
                    if (max > 0 && strings.size() == max - 1) {
                        strings.add(subSequence(path, last, pathLength));
                        break;
                    }
                }
            }
        }

        if (strings == null) {
            strings = List.of(startPos == 0 ? path : subSequence(path, startPos, pathLength));
        } else if (max <= 0 && last < pathLength) {
            strings.add(subSequence(path, last, pathLength));
        }
        return strings;
    }
}
