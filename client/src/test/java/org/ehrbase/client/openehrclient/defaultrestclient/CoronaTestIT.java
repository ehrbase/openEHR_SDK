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

package org.ehrbase.client.openehrclient.defaultrestclient;

import com.nedap.archie.rm.composition.Composition;
import org.apache.commons.io.IOUtils;
import org.assertj.core.groups.Tuple;
import org.ehrbase.client.TestData;
import org.ehrbase.client.aql.condition.Condition;
import org.ehrbase.client.aql.containment.ContainmentExpression;
import org.ehrbase.client.aql.field.EhrFields;
import org.ehrbase.client.aql.parameter.Parameter;
import org.ehrbase.client.aql.query.EntityQuery;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record3;
import org.ehrbase.client.aql.record.Record4;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.CoronaAnamneseComposition;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.CoronaAnamneseCompositionContainment;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.*;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CoronaTestIT {

    private static OpenEhrClient openEhrClient;

    @BeforeClass
    public static void setup() throws URISyntaxException {
        openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    }

    @Test
    public void testCorona() throws IOException {

        UUID ehr = openEhrClient.ehrEndpoint().createEhr();

        Composition composition = new CanonicalJson().unmarshal(IOUtils.toString(CompositionTestDataCanonicalJson.CORONA.getStream(), StandardCharsets.UTF_8), Composition.class);
        Flattener flattener = new Flattener();
        CoronaAnamneseComposition coronaAnamneseComposition = flattener.flatten(composition, CoronaAnamneseComposition.class);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(coronaAnamneseComposition);

        assertThat(openEhrClient.compositionEndpoint(ehr).find(coronaAnamneseComposition.getVersionUid().getUuid(), CoronaAnamneseComposition.class)).isNotNull();

        CoronaAnamneseCompositionContainment coronaAnamneseCompositionContainment = CoronaAnamneseCompositionContainment.getInstance();
        SymptomeSectionContainment symptomeSectionContainment = SymptomeSectionContainment.getInstance();


        HustenObservationContainment hustenObservationContainment = HustenObservationContainment.getInstance();
        FieberOderErhohteKorpertemperaturObservationContainment fieberOderErhohteKorpertemperaturObservationContainment = FieberOderErhohteKorpertemperaturObservationContainment.getInstance();
        HeiserkeitObservationContainment heiserkeitObservationContainment = HeiserkeitObservationContainment.getInstance();

        ContainmentExpression containmentExpression = coronaAnamneseCompositionContainment
                .contains(symptomeSectionContainment)
                .contains(
                        hustenObservationContainment
                                .and(fieberOderErhohteKorpertemperaturObservationContainment)
                                .and(heiserkeitObservationContainment)

                );

        EntityQuery<Record4<VorhandenDefiningcode, VorhandenDefiningcode, VorhandenDefiningcode, Language>> entityQuery = Query.buildEntityQuery(
                containmentExpression,
                hustenObservationContainment.VORHANDEN_DEFININGCODE,
                fieberOderErhohteKorpertemperaturObservationContainment.VORHANDEN_DEFININGCODE,
                heiserkeitObservationContainment.VORHANDEN_DEFININGCODE,
                coronaAnamneseCompositionContainment.LANGUAGE
        );
        Parameter<UUID> ehrIdParameter = entityQuery.buildParameter();
        entityQuery.where(Condition.equal(EhrFields.EHR_ID(), ehrIdParameter));
        /*
        Select
              o0/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value/defining_code as F0,
              o1/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value/defining_code as F1,
              o2/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value/defining_code as F2,
              c3/language as F3
        from EHR e
              contains ( COMPOSITION c3[openEHR-EHR-COMPOSITION.report.v1] contains
               SECTION s4[openEHR-EHR-SECTION.adhoc.v1] contains
                (
                (OBSERVATION o0[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]
                and
                OBSERVATION o1[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0])
                and
                OBSERVATION o2[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0])
                )
        where e/ehr_id/value = $parm0
         */
        List<Record4<VorhandenDefiningcode, VorhandenDefiningcode, VorhandenDefiningcode, Language>> actual = openEhrClient.aqlEndpoint().execute(entityQuery, ehrIdParameter.setValue(ehr));

        assertThat(actual).extracting(Record4::value1, Record4::value2, Record4::value3, Record4::value4)
                .containsExactlyInAnyOrder(new Tuple(VorhandenDefiningcode.VORHANDEN, VorhandenDefiningcode.VORHANDEN, null, Language.DE));


    }

    @Test
    @Ignore
    public void testCoronaWithJoin() throws IOException {

        UUID ehr = openEhrClient.ehrEndpoint().createEhr();

        Composition composition = new CanonicalJson().unmarshal(IOUtils.toString(CompositionTestDataCanonicalJson.CORONA.getStream(), StandardCharsets.UTF_8), Composition.class);
        Flattener flattener = new Flattener();
        CoronaAnamneseComposition coronaAnamneseComposition = flattener.flatten(composition, CoronaAnamneseComposition.class);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(coronaAnamneseComposition);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        assertThat(openEhrClient.compositionEndpoint(ehr).find(coronaAnamneseComposition.getVersionUid().getUuid(), CoronaAnamneseComposition.class)).isNotNull();


        CoronaAnamneseCompositionContainment coronaAnamneseCompositionContainment = CoronaAnamneseCompositionContainment.getInstance();
        SymptomeSectionContainment symptomeSectionContainment = SymptomeSectionContainment.getInstance();


        HustenObservationContainment hustenObservationContainment = HustenObservationContainment.getInstance();
        FieberOderErhohteKorpertemperaturObservationContainment fieberOderErhohteKorpertemperaturObservationContainment = FieberOderErhohteKorpertemperaturObservationContainment.getInstance();
        HeiserkeitObservationContainment heiserkeitObservationContainment = HeiserkeitObservationContainment.getInstance();

        RisikogebietSectionContainment risikogebietSectionContainment = RisikogebietSectionContainment.getInstance();
        ReisefallObservationContainment reisefallObservationContainment = ReisefallObservationContainment.getInstance();

        // @formatter:off
        ContainmentExpression containmentExpression =
                coronaAnamneseCompositionContainment
                        .contains(
                                symptomeSectionContainment
                                        .contains(hustenObservationContainment)
                                 .and(
                                 risikogebietSectionContainment
                                        .contains(reisefallObservationContainment)
                                )
                        );

        // @formatter:on

        EntityQuery<Record3<VorhandenDefiningcode, Language, Language>> entityQuery = Query.buildEntityQuery(
                containmentExpression,
                hustenObservationContainment.VORHANDEN_DEFININGCODE,
                coronaAnamneseCompositionContainment.LANGUAGE,
                reisefallObservationContainment.LANGUAGE
        );
        Parameter<UUID> ehrIdParameter = entityQuery.buildParameter();
        entityQuery.where(Condition.equal(EhrFields.EHR_ID(), ehrIdParameter));

        /*
        Select
              o0/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value/defining_code as F0,
              c1/language as F1,
              o2/language as F2
              from EHR e
              contains COMPOSITION c1[openEHR-EHR-COMPOSITION.report.v1] contains (
                                                                                    (SECTION s3[openEHR-EHR-SECTION.adhoc.v1] contains
                                                                                                                                    OBSERVATION o0[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0] )
                                                                                    and
                                                                                    ( SECTION s4[openEHR-EHR-SECTION.adhoc.v1] contains
                                                                                                                                    OBSERVATION o2[openEHR-EHR-OBSERVATION.travel_event.v0] )
                                                                                   )
             where e/ehr_id/value = $parm0
         */
        List<Record3<VorhandenDefiningcode, Language, Language>> actual = openEhrClient.aqlEndpoint().execute(entityQuery, ehrIdParameter.setValue(ehr));

        assertThat(actual).extracting(Record3::value1, Record3::value2, Record3::value3)
                .containsExactlyInAnyOrder(new Tuple(VorhandenDefiningcode.VORHANDEN, Language.DE, Language.DE));


    }


}
