/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.serialisation.walker;

import static org.assertj.core.api.Assertions.assertThat;

import com.nedap.archie.rm.composition.Composition;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.std.umarshal.FlatJsonUnmarshaller;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataSimSDTJson;
import org.ehrbase.openehr.sdk.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.openehr.sdk.webtemplate.filter.Filter;
import org.ehrbase.openehr.sdk.webtemplate.model.FilteredWebTemplate;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplate;
import org.ehrbase.openehr.sdk.webtemplate.parser.OPTParser;
import org.junit.jupiter.api.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

class WalkerTest {

    @Test
    void testClone() throws IOException, XmlException {

        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(
                        OperationalTemplateTestData.CORONA_ANAMNESE.getStream())
                .getTemplate();
        WebTemplate webTemplate = new OPTParser(template).parse();

        FilteredWebTemplate filteredWebTemplate = new Filter().filter(webTemplate);

        assertThat(filteredWebTemplate).isNotNull();

        FlatJsonUnmarshaller cut = new FlatJsonUnmarshaller();

        String flat = IOUtils.toString(CompositionTestDataSimSDTJson.CORONA.getStream(), StandardCharsets.UTF_8);

        Composition actual = cut.unmarshal(flat, webTemplate);

        assertThat(actual).isNotNull();

        FilteredWebTemplate filteredWebTemplateAfter = new Filter().filter(webTemplate);
        assertThat(filteredWebTemplateAfter).isNotNull();
    }
}
