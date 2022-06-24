/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Assert;
import org.junit.Test;

public class AqlPathTest {

    @Test
    public void testSplitt() {

        String cut = "at001, name/value = 'dfd' or name/value= 'fdf'";

        CharSequence[] ands = AqlPath.split2(cut, null, "and", "or", ",");
        System.out.println(ands);
    }

    @Test
    public void testParse() {
        String path = "/other_context[at0001]/items[at0006]";
        AqlPath cut = AqlPath.parse(path);
        assertThat(cut).isNotNull();
        assertThat(cut.getBaseNode().getAtCode()).isEqualTo("at0001");
        assertThat(cut.getBaseNode().getName()).isEqualTo("other_context");
        assertThat(cut).hasToString(path);
    }

    @Test
    public void testParseWithAndInName() {
        String path =
                "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1 and name/value='Einsenderstandort']/protocol[at0004]/items[at0094]/items[openEHR-EHR-CLUSTER.location.v1]";
        AqlPath cut = AqlPath.parse(path);
        assertThat(cut).isNotNull();
        assertThat(cut.getBaseNode().getAtCode()).isEqualTo("openEHR-EHR-OBSERVATION.laboratory_test_result.v1");
        assertThat(cut.getBaseNode().getName()).isEqualTo("content");
        assertThat(cut).hasToString(path);
        assertThat(cut.format(AqlPath.OtherPredicatesFormat.SHORTED, true))
                .isEqualTo(
                        "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1,'Einsenderstandort']/protocol[at0004]/items[at0094]/items[openEHR-EHR-CLUSTER.location.v1]");

        AqlPath path2 = AqlPath.parse(path, "foo");
        assertThat(path2)
                .hasToString(
                        "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1 and name/value='Einsenderstandort']/protocol[at0004]/items[at0094]/items[openEHR-EHR-CLUSTER.location.v1 and name/value='foo']");
    }

    @Test
    public void testParseWithAndInArchetypeId() {
        String path = "/content[openEHR-EHR-OBSERVATION.wordwithandin.v1]";
        AqlPath cut = AqlPath.parse(path);
        assertThat(cut).isNotNull();
        assertThat(cut.getBaseNode().getAtCode()).isEqualTo("openEHR-EHR-OBSERVATION.wordwithandin.v1");
        assertThat(cut).hasToString(path);
    }

    @Test
    public void testParseWithAttribute() {
        String path = "/context/end_time|value";
        AqlPath cut = AqlPath.parse(path);
        assertThat(cut).isNotNull();
        assertThat(cut.getBaseNode().getAtCode()).isNull();
        assertThat(cut.getBaseNode().getName()).isEqualTo("context");
        assertThat(cut.getAttributeName()).isEqualTo("value");
        assertThat(cut).hasToString(path);
    }

    @Test
    public void testNodeWithAtCode() {
        AqlPath path1 = AqlPath.parse("/context/end_time|value");
        AqlPath path2 = AqlPath.parse("/context/end_time[at0001]|value");
        AqlPath path3 = AqlPath.parse("/context/end_time[at0002]|value");
        assertThat(path1.replaceLastNode(n -> n.withAtCode("at0001"))).isEqualTo(path2);
        assertThat(path2.replaceLastNode(n -> n.withAtCode("at0001"))).isEqualTo(path2);
        assertThat(path2.replaceLastNode(n -> n.withAtCode("at0002"))).isEqualTo(path3);
        assertThat(path2.replaceLastNode(n -> n.withAtCode(null))).isEqualTo(path1);
    }

    @Test
    public void testNodeWithNameValue() {
        AqlPath path1 = AqlPath.parse("/context/end_time[at0001]|value");
        AqlPath path2 = AqlPath.parse("/context/end_time[at0001 and name/value='foo']|value");
        assertThat(path1.replaceLastNode(n -> n.withNameValue("foo"))).isEqualTo(path2);
        assertThat(path2.replaceLastNode(n -> n.withNameValue("foo"))).isEqualTo(path2);
        assertThat(path2.replaceLastNode(n -> n.withNameValue(null))).isEqualTo(path1);
    }

    @Test
    public void testNodeClearOtherPredicates() {
        AqlPath path1 = AqlPath.parse("/context/end_time[at0001]|value");
        AqlPath path2 = AqlPath.parse("/context/end_time[at0001 and name/value='foo' and foo='bar']|value");
        assertThat(path1.replaceLastNode(AqlPath.AqlNode::clearOtherPredicates)).isEqualTo(path1);
        assertThat(path2.replaceLastNode(AqlPath.AqlNode::clearOtherPredicates)).isEqualTo(path1);
    }

    @Test
    public void testParseWithPredicate() {
        String path = "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']";
        AqlPath cut = AqlPath.parse(path);
        assertThat(cut).isNotNull();
        assertThat(cut.getBaseNode().getAtCode()).isEqualTo("openEHR-EHR-SECTION.adhoc.v1");
        assertThat(cut.getBaseNode().getName()).isEqualTo("content");
        assertThat(cut.getBaseNode().findOtherPredicate("name/value")).isEqualTo("Allgemeine Angaben");
        assertThat(cut).hasToString(path);
        assertThat(cut.format(false)).isEqualTo("/content[openEHR-EHR-SECTION.adhoc.v1]");
    }

    @Test
    public void testParseWithPredicateAndInName() {
        String path =
                "/items[openEHR-EHR-SECTION.adhoc.v1 and name/value='Details of other relevant care planning documents and \\' \\\\ [] = where to find them']";
        AqlPath cut = AqlPath.parse(path);
        assertThat(cut).isNotNull();
        assertThat(cut.getBaseNode().getAtCode()).isEqualTo("openEHR-EHR-SECTION.adhoc.v1");
        assertThat(cut.getBaseNode().getName()).isEqualTo("items");
        assertThat(cut.getBaseNode().findOtherPredicate("name/value"))
                .isEqualTo("Details of other relevant care planning documents and \\' \\\\ [] = where to find them");
        assertThat(cut).hasToString(path);
        assertThat(cut.format(false)).isEqualTo("/items[openEHR-EHR-SECTION.adhoc.v1]");
    }

    @Test
    public void testParseWithPredicateInShortForm() {
        String path = "/content[openEHR-EHR-SECTION.adhoc.v1,'Symptome']";
        AqlPath cut = AqlPath.parse(path);
        assertThat(cut).isNotNull();
        assertThat(cut.getBaseNode().getAtCode()).isEqualTo("openEHR-EHR-SECTION.adhoc.v1");
        assertThat(cut.getBaseNode().getName()).isEqualTo("content");
        assertThat(cut.getBaseNode().findOtherPredicate("name/value")).isEqualTo("Symptome");
        assertThat(cut).hasToString("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']");
        assertThat(cut.format(false)).isEqualTo("/content[openEHR-EHR-SECTION.adhoc.v1]");
    }

    @Test
    public void testParseEmpty() {
        String path = "/";
        AqlPath cut = AqlPath.parse(path);
        assertThat(cut).isNotNull();
        assertThat(cut.getBaseNode().getAtCode()).isNull();
        assertThat(cut.getBaseNode().getName()).isEmpty();
        assertThat(cut).hasToString(path);
    }

    @Test
    public void testParseOnlyAttribute() {
        String path = "|value";
        AqlPath cut = AqlPath.parse(path);
        assertThat(cut).isNotNull();
        assertThat(cut.getBaseNode().getAtCode()).isNull();
        assertThat(cut.getBaseNode().getName()).isEmpty();
        assertThat(cut).hasToString(path);
    }

    @Test
    public void testParseAttributeAndAtCode() {
        String path = "/data[at0001]/events[at0002]/data[at0003]/items[at0004]|magnitude";
        AqlPath cut = AqlPath.parse(path);
        assertThat(cut).isNotNull();
        assertThat(cut.getBaseNode().getAtCode()).isEqualTo("at0001");
        assertThat(cut.getBaseNode().getName()).isEqualTo("data");
        assertThat(cut.getPath()).isEqualTo("/data[at0001]/events[at0002]/data[at0003]/items[at0004]");
        assertThat(cut).hasToString(path);
    }

    @Test
    public void testGetLast() {
        String path = "/data[at0001]/events[at0002]/data[at0003]/items[at0004]|magnitude";
        AqlPath cut = AqlPath.parse(path).getEnd(1);
        assertThat(cut).isNotNull();
        assertThat(cut.getBaseNode().getAtCode()).isEqualTo("at0004");
        assertThat(cut.getBaseNode().getName()).isEqualTo("items");
        assertThat(cut).hasToString("/items[at0004]|magnitude");
    }

    @Test
    public void testRemoveStartAqlPath() {
        AqlPath path1 = AqlPath.parse("/data[at0001]/events[at0002]/data[at0003]/items[at0004]|magnitude");
        AqlPath path2 = AqlPath.parse("/data[at0001]/events[at0002]");

        assertThat(path1.removeStart(path2)).hasToString("/data[at0003]/items[at0004]|magnitude");

        assertThat(path2.removeStart(path1)).isSameAs(path2);
        assertThat(path1.removeStart(AqlPath.ROOT_PATH)).isSameAs(path1);
        assertThat(path1.removeStart(path1)).isEqualTo(AqlPath.parse("|magnitude"));
    }

    @Test
    public void testFormatRemoveStartNodes() {
        AqlPath path = AqlPath.parse("/data[at0001]/events[at0002]/data[at0003]/items[at0004]|magnitude");

        assertThat(path.removeStart(0)).isSameAs(path);
        assertThat(path.removeStart(1))
                .isEqualTo(AqlPath.parse("/events[at0002]/data[at0003]/items[at0004]|magnitude"));
        assertThat(path.removeStart(2)).isEqualTo(AqlPath.parse("/data[at0003]/items[at0004]|magnitude"));
        assertThat(path.removeStart(3)).isEqualTo(AqlPath.parse("items[at0004]|magnitude"));
        assertThat(path.removeStart(4)).isEqualTo(AqlPath.parse("|magnitude"));

        assertThat(AqlPath.parse("items[at0004]").removeStart(1)).isEqualTo(AqlPath.EMPTY_PATH);
    }

    @Test
    public void testRemoveEndAqlPath() {
        String path = "/data[at0001]/events[at0002]/data[at0003]/items[at0004]|magnitude";
        AqlPath path1 = AqlPath.parse(path);
        AqlPath path2 = path1.getEnd(1);
        AqlPath actual = path1.removeEnd(path2);
        assertThat(actual).isNotNull();
        assertThat(actual.getBaseNode().getAtCode()).isEqualTo("at0001");
        assertThat(actual.getBaseNode().getName()).isEqualTo("data");
        assertThat(actual).hasToString("/data[at0001]/events[at0002]/data[at0003]");

        assertThat(path2.removeEnd(path1)).isSameAs(path2);

        // attribute mismatch
        assertThat(path1.removeEnd(AqlPath.parse("/items[at0004]"))).isSameAs(path1);
        // node mismatch
        assertThat(path1.removeEnd(AqlPath.parse("/items[at0003]|magnitude"))).isSameAs(path1);
    }

    @Test
    public void testFormatRemoveEndNodes() {
        AqlPath path = AqlPath.parse("/data[at0001]/events[at0002]/data[at0003]/items[at0004]|magnitude");

        assertThat(path.removeEnd(0)).isSameAs(path);
        assertThat(path.removeEnd(1)).isEqualTo(AqlPath.parse("/data[at0001]/events[at0002]/data[at0003]"));
        assertThat(path.removeEnd(2)).isEqualTo(AqlPath.parse("/data[at0001]/events[at0002]"));
        assertThat(path.removeEnd(3)).isEqualTo(AqlPath.parse("/data[at0001]"));
        assertThat(path.removeEnd(4)).isEqualTo(AqlPath.ROOT_PATH);
    }

    @Test
    public void testAddEndAqlPath() {
        AqlPath path1 = AqlPath.parse("/data[at0001]/events[at0002]/data[at0003]");
        AqlPath path2 = AqlPath.parse("/items[at0004 and name/value='Name']|magnitude");
        AqlPath actual = path1.addEnd(path2);
        assertThat(actual).isNotNull();
        assertThat(actual.getBaseNode().getAtCode()).isEqualTo("at0001");
        assertThat(actual.getBaseNode().getName()).isEqualTo("data");
        assertThat(actual)
                .hasToString("/data[at0001]/events[at0002]/data[at0003]/items[at0004 and name/value='Name']|magnitude");
        assertThat(AqlPath.EMPTY_PATH.addEnd(path2)).isSameAs(path2);
    }

    @Test
    public void testAddEndStrings() {

        AqlPath path = AqlPath.parse("/data[at0001]|foo");
        assertThat(path.addEnd("|foo")).isSameAs(path);
        assertThat(path.addEnd("")).isEqualTo(AqlPath.parse("/data[at0001]"));
        assertThat(path.addEnd("events[at0002]", "", "/", "/data[at0003]/items[at0004]|magnitude"))
                .isEqualTo(AqlPath.parse("/data[at0001]/events[at0002]/data[at0003]/items[at0004]|magnitude"));

        assertThat(path.addEnd(new String[0])).isSameAs(path);
    }

    @Test
    public void testAddEndNodes() {
        AqlPath path = AqlPath.parse("/data[at0001]|foo");
        var n1 = AqlPath.parse("events[at0002]").getLastNode();
        var n2 = AqlPath.parse("data[at0003]").getLastNode();
        var n3 = AqlPath.parse("items[at0004]").getLastNode();

        assertThat(path.addEnd(AqlPath.EMPTY_PATH)).isSameAs(path);
        assertThat(path.addEnd(n1)).isEqualTo(AqlPath.parse("/data[at0001]/events[at0002]"));
        assertThat(path.addEnd(n1, n2, n3))
                .isEqualTo(AqlPath.parse("/data[at0001]/events[at0002]/data[at0003]/items[at0004]"));
    }

    @Test
    public void testGetEnd() {
        assertThat(AqlPath.EMPTY_PATH.getEnd(100)).isSameAs(AqlPath.EMPTY_PATH);
        AqlPath path = AqlPath.parse("/data[at0001]/events[at0002]/data[at0003]/items[at0004]|magnitude");

        assertThat(path.getEnd(0)).isEqualTo(AqlPath.parse("|magnitude"));
        assertThat(path.getEnd(1)).isEqualTo(AqlPath.parse("/items[at0004]|magnitude"));
        assertThat(path.getEnd(2)).isEqualTo(AqlPath.parse("/data[at0003]/items[at0004]|magnitude"));
        assertThat(path.getEnd(3)).isEqualTo(AqlPath.parse("/events[at0002]/data[at0003]/items[at0004]|magnitude"));
        assertThat(path.getEnd(4)).isSameAs(path);
    }

    @Test
    public void testReplaceNodeAtPos() {
        AqlPath path = AqlPath.parse("/data[at0001]/events[at0002]/data[at0003]/items[at0004]|magnitude");
        var node = AqlPath.parse("foo[at0666]").getLastNode();

        assertThat(path.replaceNode(2, node))
                .isEqualTo(AqlPath.parse("/data[at0001]/events[at0002]/foo[at0666]/items[at0004]|magnitude"));
        assertThat(path.replaceNode(3, node))
                .isEqualTo(AqlPath.parse("/data[at0001]/events[at0002]/data[at0003]/foo[at0666]|magnitude"));
    }

    @Test
    public void testReplaceLastNode() {
        AqlPath path = AqlPath.parse("/data[at0001]/events[at0002]/data[at0003]/items[at0004]|magnitude");
        var node = AqlPath.parse("foo[at0666]").getLastNode();

        assertThat(path.replaceLastNode(n -> node))
                .isEqualTo(AqlPath.parse("/data[at0001]/events[at0002]/data[at0003]/foo[at0666]|magnitude"));
        assertThat(path.replaceLastNode(n -> n)).isSameAs(path);
        assertThat(path.replaceLastNode(n -> null))
                .isEqualTo(AqlPath.parse("/data[at0001]/events[at0002]/data[at0003]"));

        assertThat(AqlPath.ROOT_PATH.replaceLastNode(n -> {
                    Assert.fail("Unexpected node replacement");
                    return null;
                }))
                .isSameAs(AqlPath.ROOT_PATH);
    }

    @Test
    public void testWithAttributeName() {
        AqlPath path = AqlPath.parse("/data[at0001]/events[at0002]/data[at0003]/items[at0004]|magnitude");
        assertThat(path.withAttributeName("foo"))
                .isEqualTo(AqlPath.parse("/data[at0001]/events[at0002]/data[at0003]/items[at0004]|foo"));
        assertThat(path.withAttributeName(null))
                .isEqualTo(AqlPath.parse("/data[at0001]/events[at0002]/data[at0003]/items[at0004]"));
    }

    @Test
    public void testIsEmpty() {
        assertThat(AqlPath.EMPTY_PATH.isEmpty()).isTrue();
        assertThat(AqlPath.parse("|magnitude").isEmpty()).isTrue();
        assertThat(AqlPath.parse("/").isEmpty()).isFalse();
        assertThat(AqlPath.parse("/data[at0001]/events[at0002]/data[at0003]/items[at0004]|magnitude")
                        .isEmpty())
                .isFalse();
    }

    @Test
    public void testStartsWith() {
        AqlPath path1 = AqlPath.parse("/data[at0001]/events[at0002]/data[at0003]/items[at0004]");
        AqlPath path2 = AqlPath.parse("/data[at0001]/events[at0002]");
        AqlPath path3 = AqlPath.parse("/data[at0001]/events[at0066]");
        AqlPath path4 = AqlPath.parse("/foo[at0001]/events[at0002]");

        assertThat(path1.startsWith(path1)).isTrue();
        assertThat(path1.startsWith(path2)).isTrue();
        assertThat(path2.startsWith(path1)).isFalse();

        assertThat(path1.startsWith(path3)).isFalse();
        assertThat(path2.startsWith(path3)).isFalse();
        assertThat(path1.startsWith(path4)).isFalse();
        assertThat(path2.startsWith(path4)).isFalse();

        assertThat(AqlPath.EMPTY_PATH.startsWith(path1)).isFalse();
        assertThat(path1.startsWith(AqlPath.EMPTY_PATH)).isTrue();

        assertThat(AqlPath.ROOT_PATH.startsWith(path1)).isFalse();
        assertThat(path1.startsWith(AqlPath.ROOT_PATH)).isTrue();
    }

    @Test
    public void testGetPath() {
        AqlPath path = AqlPath.parse("/data[at0001]/events[at0002]/data[at0003]/items[at0004]|magnitude");
        assertThat(path.getPath()).isEqualTo("/data[at0001]/events[at0002]/data[at0003]/items[at0004]");
        assertThat(AqlPath.parse("|magnitude").getPath()).isEmpty();
        assertThat(AqlPath.EMPTY_PATH.getPath()).isEmpty();
        assertThat(AqlPath.ROOT_PATH.getPath()).isEqualTo("/");
    }

    @Test
    public void testFormatBoolean() {
        AqlPath path = AqlPath.parse(
                "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1 and name/value='Einsenderstandort']/protocol[at0004]/items[at0094]|foo");
        assertThat(path.format(true))
                .isEqualTo(
                        "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1 and name/value='Einsenderstandort']/protocol[at0004]/items[at0094]|foo");
        assertThat(path.format(false))
                .isEqualTo(
                        "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1]/protocol[at0004]/items[at0094]|foo");
    }

    @Test
    public void testFormatEnum() {
        AqlPath path = AqlPath.parse(
                "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1 and name/value='Einsenderstandort']/protocol[at0004]/items[at0094]|foo");
        assertThat(path.format(AqlPath.OtherPredicatesFormat.FULL, true))
                .isEqualTo(
                        "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1 and name/value='Einsenderstandort']/protocol[at0004]/items[at0094]|foo");
        assertThat(path.format(AqlPath.OtherPredicatesFormat.FULL, false))
                .isEqualTo(
                        "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1 and name/value='Einsenderstandort']/protocol[at0004]/items[at0094]");
        assertThat(path.format(AqlPath.OtherPredicatesFormat.SHORTED, true))
                .isEqualTo(
                        "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1,'Einsenderstandort']/protocol[at0004]/items[at0094]|foo");
        assertThat(path.format(AqlPath.OtherPredicatesFormat.SHORTED, false))
                .isEqualTo(
                        "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1,'Einsenderstandort']/protocol[at0004]/items[at0094]");
        assertThat(path.format(AqlPath.OtherPredicatesFormat.NONE, true))
                .isEqualTo(
                        "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1]/protocol[at0004]/items[at0094]|foo");
        assertThat(path.format(AqlPath.OtherPredicatesFormat.NONE, false))
                .isEqualTo(
                        "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1]/protocol[at0004]/items[at0094]");
    }

    @Test
    public void testParseSpace() {
        AqlPath path;

        path = AqlPath.parse(
                "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1,'Einsenderstandort']/protocol[at0004]/items[at0094]/items[openEHR-EHR-CLUSTER.location.v1]");
        assertThat(path.format(AqlPath.OtherPredicatesFormat.FULL, true))
                .isEqualTo(
                        "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1 and name/value='Einsenderstandort']/protocol[at0004]/items[at0094]/items[openEHR-EHR-CLUSTER.location.v1]");
        assertThat(path.format(AqlPath.OtherPredicatesFormat.SHORTED, true))
                .isEqualTo(
                        "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1,'Einsenderstandort']/protocol[at0004]/items[at0094]/items[openEHR-EHR-CLUSTER.location.v1]");
        assertThat(path.format(AqlPath.OtherPredicatesFormat.NONE, true))
                .isEqualTo(
                        "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1]/protocol[at0004]/items[at0094]/items[openEHR-EHR-CLUSTER.location.v1]");

        path = AqlPath.parse(
                "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1 and name/value = 'Einsenderstandort']/protocol[at0004]/items[at0094]|foo");
        assertThat(path.format(AqlPath.OtherPredicatesFormat.FULL, true))
                .isEqualTo(
                        "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1 and name/value='Einsenderstandort']/protocol[at0004]/items[at0094]|foo");
        assertThat(path.format(AqlPath.OtherPredicatesFormat.SHORTED, true))
                .isEqualTo(
                        "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1,'Einsenderstandort']/protocol[at0004]/items[at0094]|foo");
    }
}
