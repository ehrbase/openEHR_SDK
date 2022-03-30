/*
 *  Copyright (c) 2022  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
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

package org.ehrbase.webtemplate.parser;

import junit.framework.TestCase;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class InputHandlerTest extends TestCase {

  public void testBuildDurationConstrains() {

    assertThat(new InputHandler(null).buildDurationConstrains("PT5M"))
        .containsExactlyInAnyOrderEntriesOf(Map.of("MT", 5));
    assertThat(new InputHandler(null).buildDurationConstrains("P5M"))
        .containsExactlyInAnyOrderEntriesOf(Map.of("M", 5));
  }
}
