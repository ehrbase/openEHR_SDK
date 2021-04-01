/*
 *
 *  *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
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

package org.ehrbase.serialisation.walker.defaultvalues;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.fasterxml.jackson.databind.JsonNode;
import com.nedap.archie.rm.composition.Composition;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.serialisation.flatencoding.std.umarshal.FlatJsonUnmarshaller;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.ehrbase.test_data.composition.CompositionTestDataSimSDTJson;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.webtemplate.filter.Filter;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.parser.OPTParser;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

public class DefaultValuesTest {

  @Test
  public void test() throws IOException {
    Map<String, String> currentValues = new HashMap<>();
    for (Iterator<Map.Entry<String, JsonNode>> it =
            JacksonUtil.getObjectMapper()
                .readTree(
                    IOUtils.toString(
                        CompositionTestDataSimSDTJson.CORONA_WITH_CONTEXT.getStream(),
                        StandardCharsets.UTF_8))
                .fields();
        it.hasNext(); ) {
      Map.Entry<String, JsonNode> e = it.next();
      currentValues.put(e.getKey(), e.getValue().toString());
    }

    DefaultValues cut = new DefaultValues(currentValues);

    assertThat(cut).isNotNull();
  }

  @Test
  public void unmarshal() throws IOException, XmlException {

    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.CORONA_ANAMNESE.getStream())
            .getTemplate();
    WebTemplate webTemplate = new Filter().filter(new OPTParser(template).parse());

    FlatJsonUnmarshaller cut = new FlatJsonUnmarshaller();

    String flat =
        IOUtils.toString(
            CompositionTestDataSimSDTJson.CORONA_WITH_CONTEXT.getStream(), StandardCharsets.UTF_8);

    Composition actual = cut.unmarshal(flat, webTemplate, template);

    assertThat(actual).isNotNull();

    assertThat(actual.getLanguage()).isNotNull();
    assertThat(actual.getLanguage().getCodeString()).isEqualTo(Language.DE.getCode());
  }
}
