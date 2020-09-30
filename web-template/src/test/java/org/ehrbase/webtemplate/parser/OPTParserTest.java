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

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.test_data.webtemplate.WebTemplateTestData;
import org.ehrbase.webtemplate.filter.Filter;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class OPTParserTest {

    @Test
    public void parse() throws IOException, XmlException {
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(OperationalTemplateTestData.CORONA_ANAMMNESE.getStream()).getTemplate();

        OPTParser cut = new OPTParser(template);
        WebTemplate actual = cut.parse();
        actual = new Filter().filter(actual);
        assertThat(actual).isNotNull();
        assertThat(actual.getTree().getChildren()).size().isEqualTo(6);

        ObjectMapper objectMapper = new ObjectMapper();
        WebTemplate expected = objectMapper.readValue(IOUtils.toString(WebTemplateTestData.CORONA.getStream(), StandardCharsets.UTF_8), WebTemplate.class);


        List<String> errors = compareWebTemplate(actual, expected);

        assertThat(errors).containsExactlyInAnyOrder(
                "Missing Node id=dv_text aql=/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1 and name/value='Problem/Diganose Coronovirus']/data[at0001]/items[at0073]/value",
                "Missing Node id=event_context aql=/context",
                "Missing Node id=aufenthalt_in_den_letzten_14_tage_in_einem_der_risikogebiete_für_coronainfektion_oder_kontakt_zu_menschen_die_dort_waren aql=/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Risikogebiet']/items[openEHR-EHR-OBSERVATION.travel_history.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0111 and name/value='Aufenthalt in den letzten 14 Tage in einem der Risikogebiete für Coronainfektion oder Kontakt zu Menschen, die dort waren']/value"
        );

    }

    public List<String> compareWebTemplate(WebTemplate actual, WebTemplate expected) {
        List<String> errors = new ArrayList<>();
        errors.addAll(compareNode(actual.getTree(), expected.getTree()));
        return errors;
    }

    public List<String> compareNodes(List<WebTemplateNode> actual, List<WebTemplateNode> expected) {
        List<String> errors = new ArrayList<>();
        Map<ImmutablePair<String, String>, WebTemplateNode> actualMap = actual.stream().collect(Collectors.toMap(n -> new ImmutablePair<>(n.getAqlPath(true), n.getId()), Function.identity()));
        Map<ImmutablePair<String, String>, WebTemplateNode> expectedMap = expected.stream().collect(Collectors.toMap(n -> new ImmutablePair<>(n.getAqlPath(true), n.getId()), Function.identity()));

        for (ImmutablePair<String, String> pair : actualMap.keySet()) {
            if (expectedMap.containsKey(pair)) {
                errors.addAll(compareNode(actualMap.get(pair), expectedMap.get(pair)));
            } else {
                errors.add(String.format("Missing Node id=%s aql=%s", pair.getRight(), pair.getLeft()));
            }
        }
        return errors;
    }

    public List<String> compareNode(WebTemplateNode actual, WebTemplateNode expected) {

        List<String> errors = new ArrayList<>();
        errors.addAll(compareNodes(actual.getChildren(), expected.getChildren()));
        return errors;
    }


}