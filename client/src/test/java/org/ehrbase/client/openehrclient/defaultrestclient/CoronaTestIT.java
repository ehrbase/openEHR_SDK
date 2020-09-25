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
import org.ehrbase.client.aql.record.Record2;
import org.ehrbase.client.aql.record.Record3;
import org.ehrbase.client.aql.record.Record4;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.CoronaAnamneseComposition;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.CoronaAnamneseCompositionContainment;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.FieberOderErhohteKorpertemperaturObservationContainment;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.HeiserkeitObservationContainment;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.HustenObservationContainment;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.ReisefallObservationContainment;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.RisikogebietSectionContainment;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.SymptomeSectionContainment;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.VorhandenDefiningcode;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.PatientenaufenthaltCompositionContainment;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.StandortClusterContainment;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.StandortschlusselDefiningcode;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.VersorgungsortAdminEntryContainment;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.VirologischerBefundComposition;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.VirologischerBefundCompositionContainment;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition.KulturClusterContainment;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition.ProVirusClusterContainment;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition.ProbeClusterContainment;
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.client.flattener.Unflattener;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.templateprovider.TestDataTemplateProvider;
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
    @Ignore("see https://github.com/ehrbase/project_management/issues/376")
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
                .containsExactlyInAnyOrder(
                        new Tuple(VorhandenDefiningcode.VORHANDEN, VorhandenDefiningcode.VORHANDEN, VorhandenDefiningcode.NICHT_VORHANDEN, Language.DE)
                );


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

    /**
     * see https://wiki.vitagroup.ag/display/NUM/Research+Repository
     * containment test:
     *     contains COMPOSITION c[openEHR-EHR-COMPOSITION.event_summary.v0]
     *         contains ADMIN_ENTRY m[openEHR-EHR-ADMIN_ENTRY.hospitalization.v0]
     *              contains CLUSTER k[openEHR-EHR-CLUSTER.location.v1]
     */
    @Test
    public void testNUMResearchCase_1_2(){
        UUID ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildTestPatientenaufenthaltComposition());

        //build AQL
        PatientenaufenthaltCompositionContainment patientenaufenthaltCompositionContainment = PatientenaufenthaltCompositionContainment.getInstance();

        VersorgungsortAdminEntryContainment versorgungsortAdminEntryContainment = VersorgungsortAdminEntryContainment.getInstance();
        StandortClusterContainment standortClusterContainment = StandortClusterContainment.getInstance();
        versorgungsortAdminEntryContainment.setContains(standortClusterContainment);
        patientenaufenthaltCompositionContainment.setContains(versorgungsortAdminEntryContainment);

        //select set values from test data
        EntityQuery<Record3<String, String, StandortschlusselDefiningcode>> entityQuery = Query.buildEntityQuery(
                patientenaufenthaltCompositionContainment,
                standortClusterContainment.STANDORTTYP_VALUE,
                standortClusterContainment.STANDORTBESCHREIBUNG_VALUE,
                standortClusterContainment.STANDORTSCHLUSSEL_DEFININGCODE);

        Parameter<UUID> ehrIdParameter = entityQuery.buildParameter();
        entityQuery.where(Condition.equal(EhrFields.EHR_ID(), ehrIdParameter));

        List<Record3<String, String, StandortschlusselDefiningcode>> actual = openEhrClient.aqlEndpoint().execute(entityQuery, ehrIdParameter.setValue(ehr));

        assertThat(actual).extracting(Record3::value1, Record3::value2, Record3::value3)
                .containsExactlyInAnyOrder(new Tuple("Test", "Beschreibung", StandortschlusselDefiningcode.ANGIOLOGIE));

    }

    /**
     * see https://wiki.vitagroup.ag/display/NUM/Research+Repository
     *
     * Containment test:
     *
     * contains COMPOSITION c[openEHR-EHR-COMPOSITION.report-result.v1]
     * contains (
     * CLUSTER f[openEHR-EHR-CLUSTER.case_identification.v0] and
     * CLUSTER z[openEHR-EHR-CLUSTER.specimen.v1] and
     * CLUSTER j[openEHR-EHR-CLUSTER.laboratory_test_panel.v0]
     * contains CLUSTER g[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1])
     */
    @Test
    public void testNUMResearchCase_3() throws IOException {
//        Should use: TestData.buildTestVirologischerBefundComposition();

        Composition composition = new CanonicalJson().unmarshal(IOUtils.toString(CompositionTestDataCanonicalJson.VIROLOGY_FINDING_WITH_SPECIMEN.getStream(), StandardCharsets.UTF_8), Composition.class);

        assertThat(composition.itemsAtPath("/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1]/data[at0001]/events[at0002]/data[at0003]")).isNotNull();

        Flattener flattener = new Flattener();

        VirologischerBefundComposition virologischerBefundComposition = flattener.flatten(composition, VirologischerBefundComposition.class);
        assertThat(virologischerBefundComposition.getBefund()).isNotNull();

        //with the test data
        virologischerBefundComposition = TestData.buildTestVirologischerBefundComposition();
        assertThat(virologischerBefundComposition.getBefund()).isNotNull();

        UUID ehr = openEhrClient.ehrEndpoint().createEhr();
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(virologischerBefundComposition);

//        //build AQL expression
//        VirologischerBefundCompositionContainment virologischerBefundCompositionContainment = VirologischerBefundCompositionContainment.getInstance();
//        ProbeClusterContainment probeClusterContainment = ProbeClusterContainment.getInstance();
//        KulturClusterContainment kulturClusterContainment = KulturClusterContainment.getInstance();
//        ProVirusClusterContainment proVirusClusterContainment = ProVirusClusterContainment.getInstance();
//
////        contains COMPOSITION c[openEHR-EHR-COMPOSITION.report-result.v1]
////        contains (
////                CLUSTER z[openEHR-EHR-CLUSTER.specimen.v1] and
////                CLUSTER j[openEHR-EHR-CLUSTER.laboratory_test_panel.v0]
////                contains CLUSTER g[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1])
//
//        ContainmentExpression containmentExpression =
//                virologischerBefundCompositionContainment.contains(
//                        probeClusterContainment.and(
//                                kulturClusterContainment.contains(proVirusClusterContainment))
//                );
//
//        EntityQuery<Record2<String, Long>> entityQuery = Query.buildEntityQuery(
//                containmentExpression,
//                proVirusClusterContainment.VIRUS_VALUE,
//                proVirusClusterContainment.ANALYSEERGEBNIS_REIHENFOLGE_MAGNITUDE);
//
//        Parameter<UUID> ehrIdParameter = entityQuery.buildParameter();
//        entityQuery.where(Condition.equal(EhrFields.EHR_ID(), ehrIdParameter));
//
//        List<Record2<String, Long>> actual = openEhrClient.aqlEndpoint().execute(entityQuery, ehrIdParameter.setValue(ehr));
//
//        assertThat(actual).extracting(Record2::value1, Record2::value2)
//                .containsExactlyInAnyOrder(new Tuple("SARS-Cov-2", Long.valueOf(32)));

    }
}
