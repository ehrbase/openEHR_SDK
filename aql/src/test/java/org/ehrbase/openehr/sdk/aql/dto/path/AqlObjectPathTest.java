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
 *
 */

package org.ehrbase.openehr.sdk.aql.dto.path;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AqlObjectPathTest {

    @Test
    void roundTrip() {
        String path = "content[name/value='1234']/data[name[value=\"1234\"]/value=1234]/events[at001]";
        assertEquals(path.replace('"', '\''), AqlObjectPath.parse(path).render());
    }

    @Test
    void deepClone() {
        String pathStr = "content[name/value='1234']/data[name[value='1234']/value=1234]/events[at001]";
        AqlObjectPath aqlObjectPath = AqlObjectPath.parse(pathStr);
        AqlObjectPath clone = aqlObjectPath.clone();

        assertEquals(aqlObjectPath, clone);
        assertEquals(pathStr, clone.render());
    }
}