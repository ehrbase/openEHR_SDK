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
package org.ehrbase.openehr.sdk.webtemplate.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.ehrbase.openehr.sdk.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.openehr.sdk.test_data.webtemplate.WebTemplateTestData;
import org.ehrbase.openehr.sdk.webtemplate.parser.NodeId;
import org.ehrbase.openehr.sdk.webtemplate.parser.OPTParser;
import org.junit.Test;

public class WebTemplateTest {

    @Test
    public void testCanParseFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        WebTemplate actual = objectMapper.readValue(
                IOUtils.toString(WebTemplateTestData.CORONA.getStream(), StandardCharsets.UTF_8), WebTemplate.class);
        AssertionsForClassTypes.assertThat(actual).isNotNull();
    }

    @Test
    public void testFindByAqlPath() throws IOException, XmlException {
        WebTemplate actual = OPTParser.parse(OperationalTemplateTestData.CORONA_ANAMNESE.getStream());

        assertThat(actual.findByAqlPath("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']"))
                .isPresent();
        assertThat(actual.findByAqlPath("/content[openEHR-EHR-SECTION.adhoc.v1]"))
                .isPresent();
    }

    @Test
    public void isRelativePathNameDependent() throws IOException, XmlException {
        WebTemplate actual = OPTParser.parse(OperationalTemplateTestData.CORONA_ANAMNESE.getStream());

        WebTemplateNode node =
                actual.findByAqlPath("/content[openEHR-EHR-SECTION.adhoc.v1]").get();

        //
        assertThat(actual.getTree().isRelativePathNameDependent(node)).isTrue();

        assertThat(node.isRelativePathNameDependent(node.findChildById("uid").get()))
                .isFalse();
    }

    @Test
    public void testQueryUpperUnbounded() throws IOException, XmlException {
        List<WebTemplateNode> result = OPTParser.parse(OperationalTemplateTestData.IDCR_PROBLEM_LIST.getStream())
                .upperNotBounded();

        assertThat(result).hasSize(20);
    }

    @Test
    public void testQueryUpperUnbounded2() throws IOException, XmlException {
        List<WebTemplateNode> result = OPTParser.parse(OperationalTemplateTestData.IDCR_LABORATORY_TEST.getStream())
                .upperNotBounded();

        assertThat(result).hasSize(45);
    }

    @Test
    public void testMultiValued() throws IOException, XmlException {
        List<WebTemplateNode> result = OPTParser.parse(OperationalTemplateTestData.CORONA_ANAMNESE.getStream())
                .multiValued();

        assertThat(result).hasSize(262);
    }

    @Test
    public void findAllContainmentCombinations() throws IOException, XmlException {
        Set<Set<NodeId>> actual = OPTParser.parse(OperationalTemplateTestData.BLOOD_PRESSURE_SIMPLE.getStream())
                .findAllContainmentCombinations();

        assertThat(actual).hasSize(5);
    }
}
