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

package org.ehrbase.webtemplate.parser;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

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
  public void testFlatPathWithAndInName() {
    String path =
        "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1 and name/value='Einsenderstandort']/protocol[at0004]/items[at0094]/items[openEHR-EHR-CLUSTER.location.v1]";
    FlatPath cut = new FlatPath(path);
    assertThat(cut).isNotNull();
    assertThat(cut.getAtCode()).isEqualTo("openEHR-EHR-OBSERVATION.laboratory_test_result.v1");
    assertThat(cut.getName()).isEqualTo("content");
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
  public void testFlatPathWithPredicateInShortForm() {
    String path = "/content[openEHR-EHR-SECTION.adhoc.v1,'Symptome']";
    FlatPath cut = new FlatPath(path);
    assertThat(cut).isNotNull();
    assertThat(cut.getAtCode()).isEqualTo("openEHR-EHR-SECTION.adhoc.v1");
    assertThat(cut.getName()).isEqualTo("content");
    assertThat(cut.findOtherPredicate("name/value")).isEqualTo("Symptome");
    assertThat(cut.toString())
        .isEqualTo("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']");
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
    assertThat(cut.getPath()).isEqualTo("/data[at0001]/events[at0002]/data[at0003]/items[at0004]");
    assertThat(cut.toString()).isEqualTo(path);
  }

  @Test
  public void testGetLast() {
    String path = "/data[at0001]/events[at0002]/data[at0003]/items[at0004]|magnitude";
    FlatPath cut = new FlatPath(path).getLast();
    assertThat(cut).isNotNull();
    assertThat(cut.getAtCode()).isEqualTo("at0004");
    assertThat(cut.getName()).isEqualTo("items");
    assertThat(cut.toString()).isEqualTo("/items[at0004]|magnitude");
  }

  @Test
  public void testRemoveEnd() {
    String path = "/data[at0001]/events[at0002]/data[at0003]/items[at0004]|magnitude";
    FlatPath path1 = new FlatPath(path);
    FlatPath path2 = path1.getLast();
    FlatPath actual = FlatPath.removeEnd(path1, path2);
    assertThat(actual).isNotNull();
    assertThat(actual.getAtCode()).isEqualTo("at0001");
    assertThat(actual.getName()).isEqualTo("data");
    assertThat(actual.toString()).isEqualTo("/data[at0001]/events[at0002]/data[at0003]");
  }

  @Test
  public void testAddEnd() {
    FlatPath path1 = new FlatPath("/data[at0001]/events[at0002]/data[at0003]");
    FlatPath path2 = new FlatPath("/items[at0004 and name/value='Name']|magnitude");
    FlatPath actual = FlatPath.addEnd(path1, path2);
    assertThat(actual).isNotNull();
    assertThat(actual.getAtCode()).isEqualTo("at0001");
    assertThat(actual.getName()).isEqualTo("data");
    assertThat(actual.toString())
        .isEqualTo(
            "/data[at0001]/events[at0002]/data[at0003]/items[at0004 and name/value='Name']|magnitude");
  }
}
