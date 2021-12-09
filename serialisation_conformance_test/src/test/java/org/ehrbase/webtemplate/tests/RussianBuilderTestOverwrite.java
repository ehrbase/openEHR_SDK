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

package org.ehrbase.webtemplate.tests;

import care.better.platform.web.template.RussianBuilderTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.composition.Composition;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlException;
import org.assertj.core.api.Assertions;
import org.ehrbase.serialisation.RMDataFormat;
import org.ehrbase.serialisation.flatencoding.FlatFormat;
import org.ehrbase.serialisation.flatencoding.FlatJasonProvider;
import org.junit.Test;
import org.openehr.schemas.v1.TemplateDocument;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

public class RussianBuilderTestOverwrite extends RussianBuilderTest {

  @Override
  /*
  contains null values https://jira.vitagroup.ag/browse/CDR-12
  */
  public void testCardioligistExaminationValidation() throws Exception {}

  @Override
  /*
  see https://jira.vitagroup.ag/browse/PEM-492
  */
  public void testPrevaccinalExaminationValidation() throws Exception {}

  @Override
  /*
  see https://jira.vitagroup.ag/browse/PEM-492
  */
  public void missingInstruction() throws Exception {}

  @Override
  @Test
  public void testVaccinationCardYear() throws Exception {

    String template = this.getFileContent("/res/openEHR-EHR-COMPOSITION.vaccination_card.opt");
    String structuredJson =
        this.getFileContent("/res/ru-composition.json")
            .replace(",\"end_time\":[\"2019-05-06T14:56:57.670+03:00\"]", "");

    Composition marshal = getFlatJson(template, FlatFormat.STRUCTURED).unmarshal(structuredJson);

    String flatJson = getFlatJson(template, FlatFormat.SIM_SDT).marshal(marshal);

    Map<String, Object> retrieve = new ObjectMapper().readValue(flatJson, Map.class);
    Assertions.assertThat(retrieve)
        .contains(
            Assertions.entry(
                "карта_профилактических_прививок/туберкулезные_пробы/заготовка_заголовка:0/результат_иммунодиагностики/дата",
                "2013"));
  }

  @Override
  public void testTherapistExaminationContent() throws Exception {}

  private RMDataFormat getFlatJson(String template, FlatFormat flatFormat)
      throws XmlException, IOException {
    TemplateDocument templateDocument =
        TemplateDocument.Factory.parse(IOUtils.toInputStream(template, StandardCharsets.UTF_8));

    RMDataFormat flatJson =
        new FlatJasonProvider(t -> Optional.ofNullable(templateDocument.getTemplate()))
            .buildFlatJson(flatFormat, templateDocument.getTemplate().getTemplateId().getValue());
    return flatJson;
  }
}
