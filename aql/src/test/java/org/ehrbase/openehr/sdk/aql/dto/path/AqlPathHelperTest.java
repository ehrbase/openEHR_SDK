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
package org.ehrbase.openehr.sdk.aql.dto.path;

import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AqlPathHelperTest {

    @Test
    void testSplitPredicates() {

        String cut = "at001, name/value = 'dfd' or name/value= 'fdf'";

        List<CharSequence> ands =
                AqlPathHelper.split(cut, 0, -1, true, AqlPathHelper.PrefixMatcher.forStrings(" and ", " or ", ","));

        Assertions.assertThat(ands.stream().map(CharSequence::toString).collect(Collectors.toList()))
                .containsExactly("at001", ",", " name/value = 'dfd'", " or ", "name/value= 'fdf'");
    }

    @Test
    void testSplitPath() {

        String cut = "/foo/bar/baz";

        List<CharSequence> elements = AqlPathHelper.split(cut, 1, -1, false, AqlPathHelper.PrefixMatcher.forChar('/'));

        Assertions.assertThat(elements.stream().map(CharSequence::toString).collect(Collectors.toList()))
                .containsExactly("foo", "bar", "baz");
    }
}
