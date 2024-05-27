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
package org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.JsonNode;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.Observation;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.support.identification.GenericId;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlException;
import org.assertj.core.groups.Tuple;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.std.umarshal.FlatJsonUnmarshaller;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.ArchieObjectMapperProvider;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataSimSDTJson;
import org.ehrbase.openehr.sdk.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplate;
import org.ehrbase.openehr.sdk.webtemplate.parser.OPTParser;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

public class DefaultValuesTest {

    @Test
    public void test() throws IOException {
        Map<String, String> currentValues = new HashMap<>();
        for (Iterator<Map.Entry<String, JsonNode>> it = ArchieObjectMapperProvider.getObjectMapper()
                        .readTree(IOUtils.toString(
                                CompositionTestDataSimSDTJson.CORONA_WITH_CONTEXT.getStream(), StandardCharsets.UTF_8))
                        .fields();
                it.hasNext(); ) {
            Map.Entry<String, JsonNode> e = it.next();
            currentValues.put(e.getKey(), e.getValue().toString());
        }

        DefaultValues cut = new DefaultValues(currentValues);

        assertThat(cut).isNotNull();
        assertThat(cut.getDefaultValue(DefaultValuePath.LANGUAGE)).isEqualTo(Language.DE);
        assertThat(cut.getDefaultValue(DefaultValuePath.COMPOSER_NAME)).isEqualTo("Silvia Blake");
        assertThat(cut.getDefaultValue(DefaultValuePath.TIME))
                .isEqualTo(OffsetDateTime.of(2021, 4, 1, 12, 40, 31, 418954000, ZoneOffset.ofHours(2)));
        assertThat(cut.getDefaultValue(DefaultValuePath.HEALTHCARE_FACILITY_NAME))
                .isEqualTo("Hospital");
        assertThat(cut.getDefaultValue(DefaultValuePath.PARTICIPATION)).isNotNull();
        assertThat(cut.getDefaultValue(DefaultValuePath.PARTICIPATION))
                .extracting(
                        p -> p.getFunction().getValue(),
                        p -> ((PartyIdentified) p.getPerformer()).getName(),
                        p -> p.getPerformer().getExternalRef().getNamespace())
                .containsExactlyInAnyOrder(
                        new Tuple("requester", "Dr. Marcus Johnson", "HOSPITAL-NS"),
                        new Tuple("performer", "Lara Markham", "HOSPITAL-NS"));

        assertThat(cut.getDefaultValue(DefaultValuePath.PARTICIPATION))
                .extracting(p -> ((PartyIdentified) p.getPerformer()))
                .flatExtracting(p -> p.getIdentifiers())
                .extracting(
                        DvIdentifier::getAssigner, DvIdentifier::getId, DvIdentifier::getIssuer, DvIdentifier::getType)
                .containsExactlyInAnyOrder(
                        new Tuple("assigner1", "id1", "issuer1", "PERSON"),
                        new Tuple("assigner2", "id2", "issuer2", "PERSON"),
                        new Tuple("assigner3", "id3", "issuer3", "PERSON"),
                        new Tuple("assigner4", "id4", "issuer4", "PERSON"));

        assertThat(cut.getDefaultValue(DefaultValuePath.WORKFLOW_ID)).isNotNull();
        assertThat(cut.getDefaultValue(DefaultValuePath.WORKFLOW_ID).getNamespace())
                .isEqualTo("HOSPITAL-NS");
        assertThat(cut.getDefaultValue(DefaultValuePath.WORKFLOW_ID).getId().getValue())
                .isEqualTo("567");
        assertThat(cut.getDefaultValue(DefaultValuePath.WORKFLOW_ID).getType()).isEqualTo("ORGANISATION");
    }

    @Test
    public void unmarshal() throws IOException, XmlException {

        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(
                        OperationalTemplateTestData.CORONA_ANAMNESE.getStream())
                .getTemplate();
        WebTemplate webTemplate = new OPTParser(template).parse();

        FlatJsonUnmarshaller cut = new FlatJsonUnmarshaller();

        String flat =
                IOUtils.toString(CompositionTestDataSimSDTJson.CORONA_WITH_CONTEXT.getStream(), StandardCharsets.UTF_8);

        Composition actual = cut.unmarshal(flat, webTemplate);

        assertThat(actual).isNotNull();
        assertThat(actual.getCategory()).isNotNull();
        assertThat(actual.getCategory().getValue()).isEqualTo("event");

        assertThat(actual.getLanguage()).isNotNull();
        assertThat(actual.getLanguage().getCodeString()).isEqualTo(Language.DE.getCode());
        assertThat(actual.getComposer()).isNotNull();
        assertThat(actual.getComposer()).getClass().isAssignableFrom(PartyIdentified.class);
        assertThat(((PartyIdentified) actual.getComposer()).getName()).isEqualTo("Silvia Blake");
        assertThat(actual.getComposer().getExternalRef().getNamespace()).isEqualTo("HOSPITAL-NS");
        assertThat(((GenericId) actual.getComposer().getExternalRef().getId()).getScheme())
                .isEqualTo("HOSPITAL-NS");
        assertThat(((GenericId) actual.getComposer().getExternalRef().getId()).getValue())
                .isEqualTo("123");

        assertThat(actual.getContext().getHealthCareFacility().getName()).isEqualTo("Hospital");
        assertThat(actual.getContext().getStartTime()).isNotNull();
        assertThat(actual.getContext().getStartTime().getValue())
                .isEqualTo(OffsetDateTime.of(2021, 4, 1, 12, 40, 31, 418954000, ZoneOffset.ofHours(2)));
        assertThat(actual.getContext().getParticipations())
                .extracting(p -> ((PartyIdentified) p.getPerformer()))
                .flatExtracting(p -> p.getIdentifiers())
                .extracting(
                        DvIdentifier::getAssigner, DvIdentifier::getId, DvIdentifier::getIssuer, DvIdentifier::getType)
                .containsExactlyInAnyOrder(
                        new Tuple("assigner1", "id1", "issuer1", "PERSON"),
                        new Tuple("assigner2", "id2", "issuer2", "PERSON"),
                        new Tuple("assigner3", "id3", "issuer3", "PERSON"),
                        new Tuple("assigner4", "id4", "issuer4", "PERSON"));

        Observation observation = actual.getContent().stream()
                .filter(c -> Observation.class.isAssignableFrom(c.getClass()))
                .map(Observation.class::cast)
                .findAny()
                .orElse(null);
        assertThat(observation).isNotNull();
        assertThat(observation.getData()).isNotNull();
        assertThat(observation.getData().getOrigin().getValue())
                .isEqualTo(OffsetDateTime.of(2021, 4, 1, 12, 40, 31, 418954000, ZoneOffset.ofHours(2)));
        assertThat(observation.getData().getEvents().get(0).getTime().getValue())
                .isEqualTo(OffsetDateTime.of(2021, 4, 1, 12, 40, 31, 418954000, ZoneOffset.ofHours(2)));

        assertThat(observation.getOtherParticipations())
                .extracting(
                        p -> p.getFunction().getValue(),
                        p -> ((PartyIdentified) p.getPerformer()).getName(),
                        p -> p.getPerformer().getExternalRef().getNamespace())
                .containsExactlyInAnyOrder(
                        new Tuple("requester", "Dr. Marcus Johnson", "HOSPITAL-NS"),
                        new Tuple("performer", "Lara Markham", "HOSPITAL-NS"));

        assertThat(observation.getOtherParticipations())
                .extracting(p -> ((PartyIdentified) p.getPerformer()))
                .flatExtracting(p -> p.getIdentifiers())
                .extracting(
                        DvIdentifier::getAssigner, DvIdentifier::getId, DvIdentifier::getIssuer, DvIdentifier::getType)
                .containsExactlyInAnyOrder(
                        new Tuple("assigner1", "id1", "issuer1", "PERSON"),
                        new Tuple("assigner2", "id2", "issuer2", "PERSON"),
                        new Tuple("assigner3", "id3", "issuer3", "PERSON"),
                        new Tuple("assigner4", "id4", "issuer4", "PERSON"));

        assertThat(observation.getWorkflowId()).isNotNull();
        assertThat(observation.getWorkflowId().getNamespace()).isEqualTo("HOSPITAL-NS");
        assertThat(observation.getWorkflowId().getId().getValue()).isEqualTo("567");
        assertThat(observation.getWorkflowId().getType()).isEqualTo("ORGANISATION");
    }
}
