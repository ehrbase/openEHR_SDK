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

package org.ehrbase.webtemplate;

import org.apache.xmlbeans.XmlException;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.junit.jupiter.api.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

class OPTParserTest {

    @Test
    void parse() throws IOException, XmlException {
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(OperationalTemplateTestData.CORONA_ANAMMNESE.getStream()).getTemplate();

        OPTParser cut = new OPTParser(template);
        WebTemplate actual = cut.parse();
    }

    @Test
    public void testQueryUpperUnbounded() throws IOException, XmlException {
        OPERATIONALTEMPLATE operationaltemplate = TemplateDocument.Factory.parse(OperationalTemplateTestData.IDCR_PROBLEM_LIST.getStream()).getTemplate();
        List<WebTemplateNode> result = new OPTParser(operationaltemplate).parse().upperNotBounded();

        assertNotNull(result);

        assertEquals(3, result.size());
    }

    @Test
    public void testQueryUpperUnbounded2() throws IOException, XmlException {
        OPERATIONALTEMPLATE operationaltemplate = TemplateDocument.Factory.parse(OperationalTemplateTestData.IDCR_LABORATORY_TEST.getStream()).getTemplate();
        List<WebTemplateNode> result = new OPTParser(operationaltemplate).parse().upperNotBounded();

        assertNotNull(result);

        assertEquals(15, result.size());
    }

    @Test
    public void testQueryType() throws IOException, XmlException {

        OPERATIONALTEMPLATE operationaltemplate = TemplateDocument.Factory.parse(OperationalTemplateTestData.IDCR_PROBLEM_LIST.getStream()).getTemplate();

        String result = new OPTParser(operationaltemplate).parse().type("/content[openEHR-EHR-SECTION.problems_issues_rcp.v1]/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1]/data[at0001]/items[at0012]");

        assertEquals("DV_TEXT", result);
    }

    @Test
    public void testQueryType2() throws IOException, XmlException {

        OPERATIONALTEMPLATE operationaltemplate = TemplateDocument.Factory.parse(OperationalTemplateTestData.BLOOD_PRESSURE_SIMPLE.getStream()).getTemplate();

        String result = new OPTParser(operationaltemplate).parse().type("/content[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]/data[at0001]/events[at0002]/data[at0003]/items[at0004]");

        assertEquals("DV_QUANTITY", result);
    }
}