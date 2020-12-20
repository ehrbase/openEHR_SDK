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
import com.nedap.archie.rm.composition.Observation;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.History;
import com.nedap.archie.rm.datastructures.ItemList;
import com.nedap.archie.rm.datavalues.quantity.DvQuantity;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import org.apache.commons.io.IOUtils;
import org.assertj.core.groups.Tuple;
import org.ehrbase.client.Integration;
import org.ehrbase.client.TestData;
import org.ehrbase.client.aql.condition.Condition;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.EhrFields;
import org.ehrbase.client.aql.field.NativeSelectAqlField;
import org.ehrbase.client.aql.parameter.Parameter;
import org.ehrbase.client.aql.parameter.ParameterValue;
import org.ehrbase.client.aql.query.EntityQuery;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record2;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.CoronaAnamneseComposition;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.EhrbaseBloodPressureSimpleDeV0Composition;
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Integration.class)
public class AqlTestIT {

  private static OpenEhrClient openEhrClient;

  @BeforeClass
  public static void setup() throws URISyntaxException {
    openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
  }

  @Test
  public void testExecute() {

    UUID ehr = openEhrClient.ehrEndpoint().createEhr();

    openEhrClient
        .compositionEndpoint(ehr)
        .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

    EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

    EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

    Query<Record2<UUID, Double>> query =
        Query.buildNativeQuery(
            "select e/ehr_id/value,o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude  "
                + "from EHR e[ehr_id/value = $ehr_id]  "
                + "contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                + "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1",
            UUID.class,
            Double.class);

    List<Record2<UUID, Double>> result =
        openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
    assertThat(result).isNotNull();
    assertThat(result).size().isEqualTo(2);
  }

  @Test
  public void testExecute2() {

    UUID ehr = openEhrClient.ehrEndpoint().createEhr();

    openEhrClient
        .compositionEndpoint(ehr)
        .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

    EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

    EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

    Query<Record2<UUID, DvQuantity>> query =
        Query.buildNativeQuery(
            "select e/ehr_id/value,o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value  "
                + "from EHR e[ehr_id/value = $ehr_id]  "
                + "contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                + "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1",
            UUID.class,
            DvQuantity.class);

    List<Record2<UUID, DvQuantity>> result =
        openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
    assertThat(result).isNotNull();
    assertThat(result).size().isEqualTo(2);
  }

  @Test
  public void testExecute3() {

    UUID ehr = openEhrClient.ehrEndpoint().createEhr();

    openEhrClient
        .compositionEndpoint(ehr)
        .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

    EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

    EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

    Query<Record2<UUID, Element>> query =
        Query.buildNativeQuery(
            "select e/ehr_id/value,o/data[at0001]/events[at0002]/data[at0003]/items[at0004]  "
                + "from EHR e[ehr_id/value = $ehr_id]  "
                + "contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                + "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1",
            UUID.class,
            Element.class);

    List<Record2<UUID, Element>> result =
        openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
    assertThat(result).isNotNull();
    assertThat(result).size().isEqualTo(2);
  }

  @Test
  public void testExecute4() {

    UUID ehr = openEhrClient.ehrEndpoint().createEhr();

    openEhrClient
        .compositionEndpoint(ehr)
        .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

    EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

    EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

    Query<Record2<UUID, ItemList>> query =
        Query.buildNativeQuery(
            "select e/ehr_id/value,o/data[at0001]/events[at0002]/data[at0003]  "
                + "from EHR e[ehr_id/value = $ehr_id]  "
                + "contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                + "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1",
            UUID.class,
            ItemList.class);

    List<Record2<UUID, ItemList>> result =
        openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
    assertThat(result).isNotNull();
    assertThat(result).size().isEqualTo(2);
  }

  @Test
  public void testExecute5() {

    UUID ehr = openEhrClient.ehrEndpoint().createEhr();

    openEhrClient
        .compositionEndpoint(ehr)
        .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

    EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

    EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

    Query<Record2<UUID, Event>> query =
        Query.buildNativeQuery(
            "select e/ehr_id/value,o/data[at0001]/events[at0002]  "
                + "from EHR e[ehr_id/value = $ehr_id]  "
                + "contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                + "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1",
            UUID.class,
            Event.class);

    List<Record2<UUID, Event>> result =
        openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
    assertThat(result).isNotNull();
    assertThat(result).size().isEqualTo(2);
  }

  @Test
  public void testExecute6() {

    UUID ehr = openEhrClient.ehrEndpoint().createEhr();

    openEhrClient
        .compositionEndpoint(ehr)
        .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

    EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

    EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

    Query<Record2<UUID, History>> query =
        Query.buildNativeQuery(
            "select e/ehr_id/value,o/data[at0001]  "
                + "from EHR e[ehr_id/value = $ehr_id]  "
                + "contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                + "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1",
            UUID.class,
            History.class);

    List<Record2<UUID, History>> result =
        openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
    assertThat(result).isNotNull();
    assertThat(result).size().isEqualTo(2);
  }

  @Test
  public void testExecute7() {

    UUID ehr = openEhrClient.ehrEndpoint().createEhr();

    openEhrClient
        .compositionEndpoint(ehr)
        .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

    EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

    EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

    Query<Record2<UUID, Observation>> query =
        Query.buildNativeQuery(
            "select e/ehr_id/value,o  "
                + "from EHR e[ehr_id/value = $ehr_id]  "
                + "contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                + "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1",
            UUID.class,
            Observation.class);

    List<Record2<UUID, Observation>> result =
        openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
    assertThat(result).isNotNull();
    assertThat(result).size().isEqualTo(2);
  }

  @Test
  public void testExecute8() {

    UUID ehr = openEhrClient.ehrEndpoint().createEhr();

    openEhrClient
        .compositionEndpoint(ehr)
        .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

    EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

    EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

    Query<Record2<UUID, Double>> query =
        Query.buildNativeQuery(
            "select e/ehr_id/value,o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude  "
                + "from EHR e[ehr_id/value = $ehr_id]  "
                + "contains COMPOSITION a contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                + "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1",
            UUID.class,
            Double.class);

    List<Record2<UUID, Double>> result =
        openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
    assertThat(result).isNotNull();
    assertThat(result).size().isEqualTo(2);
  }

  @Test
  public void testExecute9() {

    UUID ehr = openEhrClient.ehrEndpoint().createEhr();

    openEhrClient
        .compositionEndpoint(ehr)
        .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

    EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

    EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

    Query<Record2<UUID, Double>> query =
        Query.buildNativeQuery(
            "select e/ehr_id/value,o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude  "
                + "from EHR e[ehr_id/value = $ehr_id]  "
                + "contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                + "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1",
            UUID.class,
            Double.class);

    List<Record2<UUID, Double>> result =
        openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
    assertThat(result).isNotNull();
    assertThat(result).size().isEqualTo(2);
  }

  @Test
  public void testExecute10() {

    UUID ehr = openEhrClient.ehrEndpoint().createEhr();

    openEhrClient
        .compositionEndpoint(ehr)
        .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

    EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

    EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

    Query<Record2<UUID, Double>> query =
        Query.buildNativeQuery(
            "select e/ehr_id/value,o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude  "
                + "from EHR e[ehr_id/value = $ehr_id]  "
                + "contains Observation o "
                + "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1",
            UUID.class,
            Double.class);

    List<Record2<UUID, Double>> result =
        openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
    assertThat(result).isNotNull();
    assertThat(result).size().isEqualTo(2);
  }

  @Test
  public void testExecute11() throws IOException {

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
    coronaAnamneseComposition.setVersionUid(null);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(coronaAnamneseComposition);

    Query<Record2<String, String>> query =
        Query.buildNativeQuery(
            "Select o/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value/value, o/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value/value "
                + "from EHR e[ehr_id/value = $ehr_id] "
                + "contains COMPOSITION c3[openEHR-EHR-COMPOSITION.report.v1] contains SECTION s4[openEHR-EHR-SECTION.adhoc.v1] contains OBSERVATION o[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]",
            String.class,
            String.class);

    List<Record2<String, String>> result =
        openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Record2::value1, Record2::value2)
        .containsExactlyInAnyOrder(
            new Tuple("Vorhanden", "Husten"),
            new Tuple("Vorhanden", "Schnupfen"),
            new Tuple("Nicht vorhanden", "Heiserkeit"),
            new Tuple("Vorhanden", "Fieber oder erhöhte Körpertemperatur"),
            new Tuple("Nicht vorhanden", "gestörter Geruchssinn"),
            new Tuple("Nicht vorhanden", "gestörter Geschmackssinn"),
            new Tuple("Nicht vorhanden", "Durchfall"));
  }

  @Test
  public void testExecute12() {

    UUID ehr = openEhrClient.ehrEndpoint().createEhr();

    openEhrClient
        .compositionEndpoint(ehr)
        .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

    EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

    EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

    Containment observationContainment = new Containment("OBSERVATION");

    NativeSelectAqlField<Double> magnitudeField =
        new NativeSelectAqlField<>(
            observationContainment,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude",
            Double.class);
    EntityQuery<Record2<UUID, Double>> entityQuery =
        Query.buildEntityQuery(observationContainment, EhrFields.EHR_ID(), magnitudeField);

    Parameter<UUID> ehrIdParameter = entityQuery.buildParameter();
    entityQuery.where(
        Condition.equal(EhrFields.EHR_ID(), ehrIdParameter)
            .and(Condition.equal(magnitudeField, 1.1d)));

    List<Record2<UUID, Double>> result =
        openEhrClient.aqlEndpoint().execute(entityQuery, ehrIdParameter.setValue(ehr));
    assertThat(result).isNotNull().size().isEqualTo(2);
  }
}
