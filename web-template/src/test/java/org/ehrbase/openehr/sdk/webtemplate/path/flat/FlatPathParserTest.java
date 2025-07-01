/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.webtemplate.path.flat;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class FlatPathParserTest {

    @Test
    public void parse() {

        String path = "context/end_time|value";
        FlatPathDto cut = FlatPathParser.parse(path);
        assertThat(cut).isNotNull();
        assertThat(cut.getName()).isEqualTo("context");
        assertThat(cut.getChild().getAttributeName()).isEqualTo("value");
        assertThat(cut.format()).isEqualTo(path);
    }

    @Test
    public void parseWithCount() {

        String path = "context/end_time:3";
        FlatPathDto cut = FlatPathParser.parse(path);
        assertThat(cut).isNotNull();
        assertThat(cut.getName()).isEqualTo("context");
        assertThat(cut.getChild().getAttributeName()).isNull();
        assertThat(cut.getChild().getCount()).isEqualTo(3);
        assertThat(cut.format()).isEqualTo(path);
    }

    @Test
    public void parseComplex() {

        String path =
                "bericht/risikogebiet/reisefall:0/beliebiges_intervallereignis:0/bestimmte_reise:0/bestimmtes_reiseziel:0/land";
        FlatPathDto cut = FlatPathParser.parse(path);
        assertThat(cut.format()).isEqualTo(path);
    }

    @Test
    public void parseContext() {

        String path = "ctx/participation_identifiers:1|assigner:1";
        FlatPathDto cut = FlatPathParser.parse(path);
        assertThat(cut.format()).isEqualTo(path);
    }
}
