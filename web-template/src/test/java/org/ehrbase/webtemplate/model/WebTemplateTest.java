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

package org.ehrbase.webtemplate.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlException;
import org.assertj.core.api.Assertions;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.test_data.webtemplate.WebTemplateTestData;
import org.ehrbase.webtemplate.parser.NodeId;
import org.ehrbase.webtemplate.parser.OPTParser;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

public class WebTemplateTest {

  @Test
  public void testCanParseFromFile() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    WebTemplate actual =
        objectMapper.readValue(
            IOUtils.toString(WebTemplateTestData.CORONA.getStream(), StandardCharsets.UTF_8),
            WebTemplate.class);
    assertThat(actual).isNotNull();
  }

  @Test
  public void testFindByAqlPath() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.CORONA_ANAMNESE.getStream())
            .getTemplate();

    OPTParser cut = new OPTParser(template);
    WebTemplate actual = cut.parse();

    Assertions.assertThat(
            actual
                .findByAqlPath("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']")
                .isPresent())
        .isTrue();
    Assertions.assertThat(
            actual.findByAqlPath("/content[openEHR-EHR-SECTION.adhoc.v1]").isPresent())
        .isTrue();
  }

  @Test
  public void testQueryUpperUnbounded() throws IOException, XmlException {
    OPERATIONALTEMPLATE operationaltemplate =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.IDCR_PROBLEM_LIST.getStream())
            .getTemplate();
    List<WebTemplateNode> result = new OPTParser(operationaltemplate).parse().upperNotBounded();

    assertNotNull(result);

    assertEquals(21, result.size());
  }

  @Test
  public void testQueryUpperUnbounded2() throws IOException, XmlException {
    OPERATIONALTEMPLATE operationaltemplate =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.IDCR_LABORATORY_TEST.getStream())
            .getTemplate();
    List<WebTemplateNode> result = new OPTParser(operationaltemplate).parse().upperNotBounded();

    assertNotNull(result);

    assertEquals(53, result.size());
  }

  @Test
  public void testMultiValued() throws IOException, XmlException {
    OPERATIONALTEMPLATE operationaltemplate =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.CORONA_ANAMNESE.getStream())
            .getTemplate();
    List<WebTemplateNode> result = new OPTParser(operationaltemplate).parse().multiValued();

    assertNotNull(result);

    assertEquals(266, result.size());
  }

  @Test
  public void findAllContainmentCombinations() throws IOException, XmlException {
    OPERATIONALTEMPLATE operationaltemplate =
        TemplateDocument.Factory.parse(
                OperationalTemplateTestData.BLOOD_PRESSURE_SIMPLE.getStream())
            .getTemplate();
    Set<Set<NodeId>> actual =
        new OPTParser(operationaltemplate).parse().findAllContainmentCombinations();

    Assertions.assertThat(actual).size().isEqualTo(5);
  }
}
