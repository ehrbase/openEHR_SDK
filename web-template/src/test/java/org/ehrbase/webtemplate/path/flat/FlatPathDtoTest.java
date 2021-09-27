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
    public void startsWith2() {
        FlatPathDto cut = new FlatPathDto("vitals/vitals/haemoglobin_a1c/_link:1|type");

        assertThat(cut.startsWith("vitals/vitals/haemoglobin_a1c/_link")).isTrue();
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

    @Test
    public void removeEnd() {

        String path = "bericht/risikogebiet/reisefall:0/beliebiges_intervallereignis:0/bestimmte_reise:0/bestimmtes_reiseziel:0/land";
        FlatPathDto cut =  FlatPathParser.parse(path);
        FlatPathDto actual = FlatPathDto.removeEnd(cut, cut.getLast());
        assertThat(actual.format()).isEqualTo("bericht/risikogebiet/reisefall:0/beliebiges_intervallereignis:0/bestimmte_reise:0/bestimmtes_reiseziel:0");
    }

    @Test
    public void removeEndMissingCount() {

        String path = "bericht/risikogebiet/reisefall:0/beliebiges_intervallereignis:0/bestimmte_reise:0/bestimmtes_reiseziel:0/land";
        FlatPathDto cut =  FlatPathParser.parse(path);
        FlatPathDto actual = FlatPathDto.removeEnd(cut, new FlatPathDto("bestimmtes_reiseziel/land"));
        assertThat(actual.format()).isEqualTo("bericht/risikogebiet/reisefall:0/beliebiges_intervallereignis:0/bestimmte_reise:0");
    }
    @Test
    public void removeEndMissingCount2() {

        String path = "bericht/risikogebiet/reisefall:0/beliebiges_intervallereignis:0/bestimmte_reise:0/bestimmtes_reiseziel:0/land";
        FlatPathDto cut =  FlatPathParser.parse(path);
        FlatPathDto actual = FlatPathDto.removeEnd(cut, new FlatPathDto("bestimmtes_reiseziel/land"));
        assertThat(actual.format()).isEqualTo("bericht/risikogebiet/reisefall:0/beliebiges_intervallereignis:0/bestimmte_reise:0");
    }


    @Test
    public void removeStart() {

        String path = "bericht/risikogebiet/reisefall:0/beliebiges_intervallereignis:0/bestimmte_reise:0/bestimmtes_reiseziel:0/land";
        FlatPathDto cut =  FlatPathParser.parse(path);
        FlatPathDto actual = FlatPathDto.removeStart(cut, new FlatPathDto("bericht/risikogebiet/reisefall:0/beliebiges_intervallereignis:0/bestimmte_reise:0/bestimmtes_reiseziel:0"));
        assertThat(actual.format()).isEqualTo("land");
    }

    @Test
    public void removeStartNonMatching() {

        String path = "encounter/body_temperature:1/any_event/temperature2|magnitude";
        FlatPathDto cut =  FlatPathParser.parse(path);
    FlatPathDto actual =
        FlatPathDto.removeStart(
            cut, new FlatPathDto("encounter/body_temperature:1/any_event/temperature|magnitude"));
        assertThat(actual.format()).isEqualTo("encounter/body_temperature:1/any_event/temperature2|magnitude");
    }

    @Test
    public void removeStartMissingCount() {

        String path = "bericht/risikogebiet/reisefall/beliebiges_intervallereignis/bestimmte_reise:0/bestimmtes_reiseziel:0/land";
        FlatPathDto cut =  FlatPathParser.parse(path);
        FlatPathDto actual = FlatPathDto.removeStart(cut, new FlatPathDto("bericht/risikogebiet/reisefall:0/beliebiges_intervallereignis:0/bestimmte_reise:0/bestimmtes_reiseziel:0"));
        assertThat(actual.format()).isEqualTo("land");
    }

    @Test
    public void removeStartMissingCount2() {

        String path = "bericht/risikogebiet/reisefall:0/beliebiges_intervallereignis:0/bestimmte_reise:0/bestimmtes_reiseziel:0/land";
        FlatPathDto cut =  FlatPathParser.parse(path);
        FlatPathDto actual = FlatPathDto.removeStart(cut, new FlatPathDto("bericht/risikogebiet/reisefall/beliebiges_intervallereignis/bestimmte_reise:0/bestimmtes_reiseziel:0"));
        assertThat(actual.format()).isEqualTo("land");
    }

    @Test
    public void addEnd() {

        String path = "bericht/risikogebiet/reisefall:0/beliebiges_intervallereignis:0/bestimmte_reise:0/bestimmtes_reiseziel:0";
        FlatPathDto cut =  FlatPathParser.parse(path);
        FlatPathDto actual = FlatPathDto.addEnd(cut, new FlatPathDto("land"));
        assertThat(actual.format()).isEqualTo("bericht/risikogebiet/reisefall:0/beliebiges_intervallereignis:0/bestimmte_reise:0/bestimmtes_reiseziel:0/land");
    }
}