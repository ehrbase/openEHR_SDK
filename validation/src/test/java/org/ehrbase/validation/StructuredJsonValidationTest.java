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

package org.ehrbase.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nedap.archie.rm.composition.Composition;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.ehrbase.serialisation.RMDataFormat;
import org.ehrbase.serialisation.flatencoding.FlatFormat;
import org.ehrbase.serialisation.flatencoding.FlatJasonProvider;
import org.ehrbase.test_data.composition.CompositionTestDataStructuredJson;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.validation.webtemplate.TestDataTemplateProvider;
import org.junit.jupiter.api.Test;

class StructuredJsonValidationTest {

  private static final TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();

  private final CompositionValidator validator = new CompositionValidator();

  @Test
  void testRoundTrip() throws IOException {
    CompositionTestDataStructuredJson testData = CompositionTestDataStructuredJson.MULTI_LIST;
    String templateId = OperationalTemplateTestData.MULTI_LIST.getTemplateId();

    test(testData, templateId);
  }

  @Test
  void testRoundTripCorona() throws IOException {
    CompositionTestDataStructuredJson testData = CompositionTestDataStructuredJson.CORONA;
    String templateId = OperationalTemplateTestData.CORONA_ANAMNESE.getTemplateId();

    test(testData, templateId);
  }

  private void test(CompositionTestDataStructuredJson testData, String templateId)
      throws IOException {
    RMDataFormat cut =
        new FlatJasonProvider(templateProvider).buildFlatJson(FlatFormat.STRUCTURED, templateId);

    String flat = IOUtils.toString(testData.getStream(), StandardCharsets.UTF_8);
    Composition unmarshal = cut.unmarshal(flat);

    var existingTemplate = templateProvider.find(templateId);
    assertTrue(existingTemplate.isPresent());

    var result = validator.validate(unmarshal, existingTemplate.get());
    assertTrue(result.isEmpty());
  }
}