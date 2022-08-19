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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author Stefan Spiska
 */
public class EncoderHelper {

    private static List<String> code = List.of(
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "y", "z", "1",
            "2", "3", "4", "5", "6", "7", "8", "9", "0");

    int current = 0;

    public String next() {

        String collect = Arrays.stream(split(current)).map(code::get).collect(Collectors.joining());
        current++;
        return collect;
    }

    private Integer[] split(int i) {

        Integer[] result = new Integer[1];

        result[0] = i % code.size();

        if (i / code.size() > 0) {
            return ArrayUtils.addAll(split(i / code.size()), result);
        }

        return result;
    }
}
