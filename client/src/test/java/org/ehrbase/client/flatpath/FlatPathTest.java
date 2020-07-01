/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.client.flatpath;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class FlatPathTest {

    @Test
    public void testFlatPath() {
        String path = "/other_context[at0001]/items[at0006]";
        FlatPath cut = new FlatPath(path);
        assertThat(cut).isNotNull();
        assertThat(cut.getAtCode()).isEqualTo("at0001");
        assertThat(cut.getName()).isEqualTo("other_context");
        assertThat(cut.toString()).isEqualTo(path);
    }

    @Test
    public void testFlatPathWithAttribute() {
        String path = "/context/end_time|value";
        FlatPath cut = new FlatPath(path);
        assertThat(cut).isNotNull();
        assertThat(cut.getAtCode()).isNull();
        assertThat(cut.getName()).isEqualTo("context");
        assertThat(cut.getChild().getAttributeName()).isEqualTo("value");
        assertThat(cut.toString()).isEqualTo(path);
    }

    @Test
    public void testFlatPathWithPredicate() {
        String path = "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']";
        FlatPath cut = new FlatPath(path);
        assertThat(cut).isNotNull();
        assertThat(cut.getAtCode()).isEqualTo("openEHR-EHR-SECTION.adhoc.v1");
        assertThat(cut.getName()).isEqualTo("content");
        assertThat(cut.findOtherPredicate("name/value")).isEqualTo("Allgemeine Angaben");
        assertThat(cut.toString()).isEqualTo(path);
        assertThat(cut.format(false)).isEqualTo("/content[openEHR-EHR-SECTION.adhoc.v1]");
    }

    @Test
    public void testFlatPathEmpty() {
        String path = "/";
        FlatPath cut = new FlatPath(path);
        assertThat(cut).isNotNull();
        assertThat(cut.getAtCode()).isNull();
        assertThat(cut.getName()).isEqualTo("");
        assertThat(cut.toString()).isEqualTo(path);
    }

    @Test
    public void testFlatPathOnlyAttribute() {
        String path = "|value";
        FlatPath cut = new FlatPath(path);
        assertThat(cut).isNotNull();
        assertThat(cut.getAtCode()).isNull();
        assertThat(cut.getName()).isEqualTo("");
        assertThat(cut.toString()).isEqualTo(path);
    }


    @Test
    public void testFlatPathAttributeAndAtCode() {
        String path = "/data[at0001]/events[at0002]/data[at0003]/items[at0004]|magnitude";
        FlatPath cut = new FlatPath(path);
        assertThat(cut).isNotNull();
        assertThat(cut.getAtCode()).isEqualTo("at0001");
        assertThat(cut.getName()).isEqualTo("data");
        assertThat(cut.toString()).isEqualTo(path);
    }
}