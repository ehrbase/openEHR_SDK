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

import static org.assertj.core.api.Assertions.assertThat;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.UUID;
import org.apache.commons.io.IOUtils;
import org.assertj.core.groups.Tuple;
import org.ehrbase.client.TestData;
import org.ehrbase.client.aql.condition.Condition;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.containment.ContainmentExpression;
import org.ehrbase.client.aql.field.EhrFields;
import org.ehrbase.client.aql.field.NativeSelectAqlField;
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
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.VorhandenDefiningCode;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.PatientenaufenthaltCompositionContainment;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.AbteilungsfallClusterContainment;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.StandortClusterContainment;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.StandortschlusselDefiningCode;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.VersorgungsortAdminEntryContainment;
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.StationarerVersorgungsfallCompositionContainment;
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition.AufnahmedatenAdminEntryContainment;
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition.EntlassungsdatenAdminEntryContainment;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.VirologischerBefundComposition;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.VirologischerBefundCompositionContainment;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition.BefundObservationContainment;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition.KulturClusterContainment;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition.ProVirusClusterContainment;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition.ProbeClusterContainment;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

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

    Composition composition =
        new CanonicalJson()
            .unmarshal(
                IOUtils.toString(
                    CompositionTestDataCanonicalJson.CORONA.getStream(), StandardCharsets.UTF_8),
                Composition.class);
    Flattener flattener = new Flattener(new TestDataTemplateProvider());
    CoronaAnamneseComposition coronaAnamneseComposition =
        flattener.flatten(composition, CoronaAnamneseComposition.class);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(coronaAnamneseComposition);

    assertThat(
            openEhrClient
                .compositionEndpoint(ehr)
                .find(
                    coronaAnamneseComposition.getVersionUid().getUuid(),
                    CoronaAnamneseComposition.class))
        .isNotNull();

    CoronaAnamneseCompositionContainment coronaAnamneseCompositionContainment =
        CoronaAnamneseCompositionContainment.getInstance();
    SymptomeSectionContainment symptomeSectionContainment =
        SymptomeSectionContainment.getInstance();

    HustenObservationContainment hustenObservationContainment =
        HustenObservationContainment.getInstance();
    FieberOderErhohteKorpertemperaturObservationContainment
        fieberOderErhohteKorpertemperaturObservationContainment =
            FieberOderErhohteKorpertemperaturObservationContainment.getInstance();
    HeiserkeitObservationContainment heiserkeitObservationContainment =
        HeiserkeitObservationContainment.getInstance();

    ContainmentExpression containmentExpression =
        coronaAnamneseCompositionContainment
            .contains(symptomeSectionContainment)
            .contains(
                hustenObservationContainment
                    .and(fieberOderErhohteKorpertemperaturObservationContainment)
                    .and(heiserkeitObservationContainment));

    EntityQuery<
            Record4<VorhandenDefiningCode, VorhandenDefiningCode, VorhandenDefiningCode, Language>>
        entityQuery =
            Query.buildEntityQuery(
                containmentExpression,
                hustenObservationContainment.VORHANDEN_DEFINING_CODE,
                fieberOderErhohteKorpertemperaturObservationContainment.VORHANDEN_DEFINING_CODE,
                heiserkeitObservationContainment.VORHANDEN_DEFINING_CODE,
                coronaAnamneseCompositionContainment.LANGUAGE);
    Parameter<UUID> ehrIdParameter = entityQuery.buildParameter();
    entityQuery.where(Condition.equal(EhrFields.EHR_ID(), ehrIdParameter));
    /*
    Select
          o0/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value/defining_Code as F0,
          o1/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value/defining_Code as F1,
          o2/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value/defining_Code as F2,
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
    List<Record4<VorhandenDefiningCode, VorhandenDefiningCode, VorhandenDefiningCode, Language>>
        actual = openEhrClient.aqlEndpoint().execute(entityQuery, ehrIdParameter.setValue(ehr));

    assertThat(actual)
        .extracting(Record4::value1, Record4::value2, Record4::value3, Record4::value4)
        .containsExactlyInAnyOrder(
            new Tuple(
                VorhandenDefiningCode.VORHANDEN,
                VorhandenDefiningCode.VORHANDEN,
                VorhandenDefiningCode.NICHT_VORHANDEN,
                Language.DE));
  }

  @Test
  @Ignore
  public void testCoronaWithJoin() throws IOException {

    UUID ehr = openEhrClient.ehrEndpoint().createEhr();

    Composition composition =
        new CanonicalJson()
            .unmarshal(
                IOUtils.toString(
                    CompositionTestDataCanonicalJson.CORONA.getStream(), StandardCharsets.UTF_8),
                Composition.class);
    Flattener flattener = new Flattener(new TestDataTemplateProvider());
    CoronaAnamneseComposition coronaAnamneseComposition =
        flattener.flatten(composition, CoronaAnamneseComposition.class);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(coronaAnamneseComposition);
    openEhrClient
        .compositionEndpoint(ehr)
        .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

    assertThat(
            openEhrClient
                .compositionEndpoint(ehr)
                .find(
                    coronaAnamneseComposition.getVersionUid().getUuid(),
                    CoronaAnamneseComposition.class))
        .isNotNull();

    CoronaAnamneseCompositionContainment coronaAnamneseCompositionContainment =
        CoronaAnamneseCompositionContainment.getInstance();
    SymptomeSectionContainment symptomeSectionContainment =
        SymptomeSectionContainment.getInstance();

    HustenObservationContainment hustenObservationContainment =
        HustenObservationContainment.getInstance();
    FieberOderErhohteKorpertemperaturObservationContainment
        fieberOderErhohteKorpertemperaturObservationContainment =
            FieberOderErhohteKorpertemperaturObservationContainment.getInstance();
    HeiserkeitObservationContainment heiserkeitObservationContainment =
        HeiserkeitObservationContainment.getInstance();

    RisikogebietSectionContainment risikogebietSectionContainment =
        RisikogebietSectionContainment.getInstance();
    ReisefallObservationContainment reisefallObservationContainment =
        ReisefallObservationContainment.getInstance();

    // @formatter:off
    ContainmentExpression containmentExpression =
        coronaAnamneseCompositionContainment.contains(
            symptomeSectionContainment
                .contains(hustenObservationContainment)
                .and(risikogebietSectionContainment.contains(reisefallObservationContainment)));

    // @formatter:on

    EntityQuery<Record3<VorhandenDefiningCode, Language, Language>> entityQuery =
        Query.buildEntityQuery(
            containmentExpression,
            hustenObservationContainment.VORHANDEN_DEFINING_CODE,
            coronaAnamneseCompositionContainment.LANGUAGE,
            reisefallObservationContainment.LANGUAGE);
    Parameter<UUID> ehrIdParameter = entityQuery.buildParameter();
    entityQuery.where(Condition.equal(EhrFields.EHR_ID(), ehrIdParameter));

    /*
    Select
          o0/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value/defining_Code as F0,
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
    List<Record3<VorhandenDefiningCode, Language, Language>> actual =
        openEhrClient.aqlEndpoint().execute(entityQuery, ehrIdParameter.setValue(ehr));

    assertThat(actual)
        .extracting(Record3::value1, Record3::value2, Record3::value3)
        .containsExactlyInAnyOrder(
            new Tuple(VorhandenDefiningCode.VORHANDEN, Language.DE, Language.DE));
  }

  /**
   * see https://wiki.vitagroup.ag/display/NUM/Research+Repository containment test: contains
   * COMPOSITION c[openEHR-EHR-COMPOSITION.event_summary.v0] contains ADMIN_ENTRY
   * m[openEHR-EHR-ADMIN_ENTRY.hospitalization.v0] contains CLUSTER
   * k[openEHR-EHR-CLUSTER.location.v1]
   */
  @Test
  public void testNUMResearchCase_1_2() {
    UUID ehr = openEhrClient.ehrEndpoint().createEhr();

    openEhrClient
        .compositionEndpoint(ehr)
        .mergeCompositionEntity(TestData.buildTestPatientenaufenthaltComposition());

    // build AQL
    PatientenaufenthaltCompositionContainment patientenaufenthaltCompositionContainment =
        PatientenaufenthaltCompositionContainment.getInstance();

    VersorgungsortAdminEntryContainment versorgungsortAdminEntryContainment =
        VersorgungsortAdminEntryContainment.getInstance();
    StandortClusterContainment standortClusterContainment =
        StandortClusterContainment.getInstance();
    versorgungsortAdminEntryContainment.setContains(standortClusterContainment);
    patientenaufenthaltCompositionContainment.setContains(versorgungsortAdminEntryContainment);

    // select set values from test data
    EntityQuery<Record3<String, String, StandortschlusselDefiningCode>> entityQuery =
        Query.buildEntityQuery(
            patientenaufenthaltCompositionContainment,
            standortClusterContainment.STANDORTTYP_VALUE,
            standortClusterContainment.STANDORTBESCHREIBUNG_VALUE,
            standortClusterContainment.STANDORTSCHLUSSEL_DEFINING_CODE);

    Parameter<UUID> ehrIdParameter = entityQuery.buildParameter();
    entityQuery.where(Condition.equal(EhrFields.EHR_ID(), ehrIdParameter));

    List<Record3<String, String, StandortschlusselDefiningCode>> actual =
        openEhrClient.aqlEndpoint().execute(entityQuery, ehrIdParameter.setValue(ehr));

    assertThat(actual)
        .extracting(Record3::value1, Record3::value2, Record3::value3)
        .containsExactlyInAnyOrder(
            new Tuple("Test", "Beschreibung", StandortschlusselDefiningCode.ANGIOLOGIE));
  }

  /**
   * see https://wiki.vitagroup.ag/display/NUM/Research+Repository
   *
   * <p>Containment test:
   *
   * <p>contains COMPOSITION c[openEHR-EHR-COMPOSITION.report-result.v1] contains ( CLUSTER
   * f[openEHR-EHR-CLUSTER.case_identification.v0] and CLUSTER z[openEHR-EHR-CLUSTER.specimen.v1]
   * and CLUSTER j[openEHR-EHR-CLUSTER.laboratory_test_panel.v0] contains CLUSTER
   * g[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1])
   */
  @Test
  public void testNUMResearchCase_3() throws IOException {
    //        Should use: TestData.buildTestVirologischerBefundComposition();
    // with the test data
    VirologischerBefundComposition virologischerBefundComposition =
        TestData.buildTestVirologischerBefundComposition();
    assertThat(virologischerBefundComposition.getBefund()).isNotNull();

    UUID ehr = openEhrClient.ehrEndpoint().createEhr();
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(virologischerBefundComposition);

    // build AQL expression
    VirologischerBefundCompositionContainment virologischerBefundCompositionContainment =
        VirologischerBefundCompositionContainment.getInstance();
    ProbeClusterContainment probeClusterContainment = ProbeClusterContainment.getInstance();
    KulturClusterContainment kulturClusterContainment = KulturClusterContainment.getInstance();
    ProVirusClusterContainment proVirusClusterContainment =
        ProVirusClusterContainment.getInstance();

    //        contains COMPOSITION c[openEHR-EHR-COMPOSITION.report-result.v1]
    //        contains (
    //                CLUSTER z[openEHR-EHR-CLUSTER.specimen.v1] and
    //                CLUSTER j[openEHR-EHR-CLUSTER.laboratory_test_panel.v0]
    //                contains CLUSTER g[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1])

    ContainmentExpression containmentExpression =
        virologischerBefundCompositionContainment.contains(
            probeClusterContainment.and(
                kulturClusterContainment.contains(proVirusClusterContainment)));

    EntityQuery<Record2<String, Long>> entityQuery =
        Query.buildEntityQuery(
            containmentExpression,
            proVirusClusterContainment.VIRUS_VALUE,
            proVirusClusterContainment.ANALYSEERGEBNIS_REIHENFOLGE_MAGNITUDE);

    Parameter<UUID> ehrIdParameter = entityQuery.buildParameter();
    entityQuery.where(Condition.equal(EhrFields.EHR_ID(), ehrIdParameter));

    List<Record2<String, Long>> actual =
        openEhrClient.aqlEndpoint().execute(entityQuery, ehrIdParameter.setValue(ehr));

    assertThat(actual)
        .extracting(Record2::value1, Record2::value2)
        .containsExactlyInAnyOrder(new Tuple("SARS-Cov-2", 32L), new Tuple("SARS-Cov-2", 34L));
  }

  /**
   * see https://wiki.vitagroup.ag/display/NUM/Research+Repository
   *
   * <p>Containment test:
   *
   * <p>contains COMPOSITION c[openEHR-EHR-COMPOSITION.event_summary.v0] contains (CLUSTER
   * n[openEHR-EHR-CLUSTER.case_identification.v0] and ADMIN_ENTRY
   * u[openEHR-EHR-ADMIN_ENTRY.hospitalization.v0] contains (CLUSTER
   * a[openEHR-EHR-CLUSTER.location.v1]))
   */
  @Test
  public void testNUMResearchCase_5() {
    UUID ehr = openEhrClient.ehrEndpoint().createEhr();

    openEhrClient
        .compositionEndpoint(ehr)
        .mergeCompositionEntity(TestData.buildTestPatientenaufenthaltComposition());

    // build AQL
    PatientenaufenthaltCompositionContainment patientenaufenthaltCompositionContainment =
        PatientenaufenthaltCompositionContainment.getInstance();
    AbteilungsfallClusterContainment abteilungsfallClusterContainment =
        AbteilungsfallClusterContainment.getInstance();
    VersorgungsortAdminEntryContainment versorgungsortAdminEntryContainment =
        VersorgungsortAdminEntryContainment.getInstance();
    StandortClusterContainment standortClusterContainment =
        StandortClusterContainment.getInstance();

    ContainmentExpression containmentExpression =
        patientenaufenthaltCompositionContainment.contains(
            abteilungsfallClusterContainment.and(
                versorgungsortAdminEntryContainment.contains(standortClusterContainment)));

    // select set values from test data
    EntityQuery<Record3<TemporalAccessor, TemporalAccessor, String>> entityQuery =
        Query.buildEntityQuery(
            containmentExpression,
            versorgungsortAdminEntryContainment.BEGINN_VALUE,
            versorgungsortAdminEntryContainment.ENDE_VALUE,
            versorgungsortAdminEntryContainment.GRUND_DES_AUFENTHALTES_VALUE);

    Parameter<UUID> ehrIdParameter = entityQuery.buildParameter();
    entityQuery.where(Condition.equal(EhrFields.EHR_ID(), ehrIdParameter));

    List<Record3<TemporalAccessor, TemporalAccessor, String>> actual =
        openEhrClient.aqlEndpoint().execute(entityQuery, ehrIdParameter.setValue(ehr));

    assertThat(actual)
        .extracting(Record3::value1, Record3::value2, Record3::value3)
        .containsExactlyInAnyOrder(
            new Tuple(
                new DvDateTime("2020-01-01T10:00Z").getValue(),
                new DvDateTime("2020-01-01T12:00Z").getValue(),
                "test value"));
  }

  /**
   * see https://wiki.vitagroup.ag/display/NUM/Research+Repository
   *
   * <p>Containment test:
   *
   * <p>contains COMPOSITION c[openEHR-EHR-COMPOSITION.fall.v0] contains ( ADMIN_ENTRY
   * p[openEHR-EHR-ADMIN_ENTRY.admission.v0] and ADMIN_ENTRY
   * b[openEHR-EHR-ADMIN_ENTRY.discharge_summary.v0])
   */
  @Test
  public void testNUMResearchCase_6() {
    UUID ehr = openEhrClient.ehrEndpoint().createEhr();

    openEhrClient
        .compositionEndpoint(ehr)
        .mergeCompositionEntity(TestData.buildTestStationarerVersorgungsfallComposition());

    // build AQL
    StationarerVersorgungsfallCompositionContainment
        stationarerVersorgungsfallCompositionContainment =
            StationarerVersorgungsfallCompositionContainment.getInstance();
    AufnahmedatenAdminEntryContainment aufnahmedatenAdminEntryContainment =
        AufnahmedatenAdminEntryContainment.getInstance();
    EntlassungsdatenAdminEntryContainment entlassungsdatenAdminEntryContainment =
        EntlassungsdatenAdminEntryContainment.getInstance();

    ContainmentExpression containmentExpression =
        stationarerVersorgungsfallCompositionContainment.contains(
            aufnahmedatenAdminEntryContainment.and(entlassungsdatenAdminEntryContainment));

    // select set values from test data
    EntityQuery<Record3<TemporalAccessor, TemporalAccessor, String>> entityQuery =
        Query.buildEntityQuery(
            containmentExpression,
            aufnahmedatenAdminEntryContainment.DATUM_UHRZEIT_DER_AUFNAHME_VALUE,
            entlassungsdatenAdminEntryContainment.DATUM_UHRZEIT_DER_ENTLASSUNG_VALUE,
            stationarerVersorgungsfallCompositionContainment.FALL_KENNUNG_VALUE);

    Parameter<UUID> ehrIdParameter = entityQuery.buildParameter();
    entityQuery.where(Condition.equal(EhrFields.EHR_ID(), ehrIdParameter));

    List<Record3<TemporalAccessor, TemporalAccessor, String>> actual =
        openEhrClient.aqlEndpoint().execute(entityQuery, ehrIdParameter.setValue(ehr));

    assertThat(actual)
        .extracting(Record3::value1, Record3::value2, Record3::value3)
        .containsExactlyInAnyOrder(
            new Tuple(
                new DvDateTime("2020-04-02T12:00:00Z").getValue(),
                new DvDateTime("2020-04-02T12:00:00Z").getValue(),
                "45657678"));
  }

  /**
   * see https://wiki.vitagroup.ag/display/NUM/Research+Repository
   *
   * <p>Containment test UC 8:
   *
   * <p>contains COMPOSITION c contains OBSERVATION
   * v[openEHR-EHR-OBSERVATION.laboratory_test_result.v1] contains ( CLUSTER
   * h[openEHR-EHR-CLUSTER.laboratory_test_panel.v0] and CLUSTER x[openEHR-EHR-CLUSTER.specimen.v1]
   * and CLUSTER q[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1])
   */
  @Test
  public void testNUMResearchCase_8() {

    VirologischerBefundComposition virologischerBefundComposition =
        TestData.buildTestVirologischerBefundComposition();
    assertThat(virologischerBefundComposition.getBefund()).isNotNull();

    UUID ehr = openEhrClient.ehrEndpoint().createEhr();
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(virologischerBefundComposition);

    // build AQL expression
    Containment compositionContainment = new Containment("COMPOSITION");
    BefundObservationContainment befundObservationContainment =
        BefundObservationContainment.getInstance();
    ProbeClusterContainment probeClusterContainment = ProbeClusterContainment.getInstance();
    KulturClusterContainment kulturClusterContainment = KulturClusterContainment.getInstance();
    ProVirusClusterContainment proVirusClusterContainment =
        ProVirusClusterContainment.getInstance();

    ContainmentExpression containmentExpression =
        compositionContainment.contains(
            befundObservationContainment.contains(
                kulturClusterContainment.and(
                    probeClusterContainment.and(proVirusClusterContainment))));

    EntityQuery<Record2<String, Long>> entityQuery =
        Query.buildEntityQuery(
            containmentExpression,
            proVirusClusterContainment.VIRUS_VALUE,
            proVirusClusterContainment.ANALYSEERGEBNIS_REIHENFOLGE_MAGNITUDE);

    Parameter<UUID> ehrIdParameter = entityQuery.buildParameter();
    entityQuery.where(
        Condition.equal(EhrFields.EHR_ID(), ehrIdParameter)
            .and(
                Condition.equal(
                    new NativeSelectAqlField<>(compositionContainment, "/name/value", String.class),
                    "Virologischer Befund")));

    List<Record2<String, Long>> actual =
        openEhrClient.aqlEndpoint().execute(entityQuery, ehrIdParameter.setValue(ehr));

    assertThat(actual)
        .extracting(Record2::value1, Record2::value2)
        .containsExactlyInAnyOrder(new Tuple("SARS-Cov-2", 32L), new Tuple("SARS-Cov-2", 34L));
  }
}
