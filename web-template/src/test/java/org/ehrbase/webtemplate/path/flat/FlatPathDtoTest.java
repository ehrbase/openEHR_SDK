/*
 *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
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

package org.ehrbase.webtemplate.path.flat;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FlatPathDtoTest {

    @Test
    public void startsWith() {
        FlatPathDto cut = new FlatPathDto("encounter/body_temperature:1/any_event:0/temperature|magnitude");

       assertThat(cut.startsWith("encounter/body_temperature:1/any_event:0/temperature")).isTrue();
    }

    @Test
    public void startsWithMissingCount() {
        FlatPathDto cut = new FlatPathDto("encounter/body_temperature:1/any_event/temperature|magnitude");

        assertThat(cut.startsWith("encounter/body_temperature:1/any_event:0/temperature")).isTrue();
    }

    @Test
    public void isEqualTo(){
        FlatPathDto cut = new FlatPathDto("encounter/body_temperature:1/any_event:0/temperature|magnitude");

        assertThat(cut.isEqualTo("encounter/body_temperature:1/any_event:0/temperature|magnitude")).isTrue();
    }

    @Test
    public void isEqualToMissingCount(){
        FlatPathDto cut = new FlatPathDto("encounter/body_temperature:1/any_event/temperature|magnitude");

        assertThat(cut.isEqualTo("encounter/body_temperature:1/any_event:0/temperature|magnitude")).isTrue();
    }
}