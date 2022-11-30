/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.client.openehrclient.defaultrestclient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

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
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.apache.commons.collections4.CollectionUtils;
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
import org.ehrbase.client.aql.parameter.StoredQueryParameter;
import org.ehrbase.client.aql.query.EntityQuery;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record2;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.CoronaAnamneseComposition;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.EhrbaseBloodPressureSimpleDeV0Composition;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.exception.WrongStatusCodeException;
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.response.openehr.QueryResponseData;
import org.ehrbase.response.openehr.StoredQueryResponseData;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Integration.class)
public class AqlTestIT {

    private static OpenEhrClient openEhrClient;
    private UUID ehr;

    @BeforeClass
    public static void setup() throws URISyntaxException {
        openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    }

    @After
    public void tearDown() {
        // delete the created EHR using the admin endpoint
        openEhrClient.adminEhrEndpoint().delete(ehr);
    }

    @Test
    public void testExecute() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

        Query<Record2<UUID, Double>> query = Query.buildNativeQuery(
                "select e/ehr_id/value,o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude  "
                        + "from EHR e[ehr_id/value = $ehr_id]  "
                        + "contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1",
                UUID.class,
                Double.class);

        List<Record2<UUID, Double>> result =
                openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull().hasSize(2);
    }

    @Test
    public void testExecute2() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

        Query<Record2<UUID, DvQuantity>> query = Query.buildNativeQuery(
                "select e/ehr_id/value,o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value  "
                        + "from EHR e[ehr_id/value = $ehr_id]  "
                        + "contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1",
                UUID.class,
                DvQuantity.class);

        List<Record2<UUID, DvQuantity>> result =
                openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull().hasSize(2);
    }

    @Test
    public void testExecute3() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

        Query<Record2<UUID, Element>> query = Query.buildNativeQuery(
                "select e/ehr_id/value,o/data[at0001]/events[at0002]/data[at0003]/items[at0004]  "
                        + "from EHR e[ehr_id/value = $ehr_id]  "
                        + "contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1",
                UUID.class,
                Element.class);

        List<Record2<UUID, Element>> result =
                openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull().hasSize(2);
    }

    @Test
    public void testExecute4() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        pressureSimple1.getBloodPressureTrainingSample().get(0).setCommentValue("test");
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        pressureSimple2.getBloodPressureTrainingSample().get(0).setCommentValue("tes");
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

        Query<Record2<UUID, ItemList>> query = Query.buildNativeQuery(
                "select e/ehr_id/value,o/data[at0001]/events[at0002]/data[at0003]  "
                        + "from EHR e[ehr_id/value = $ehr_id]  "
                        + "contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1"
                        + " AND o/data[at0001]/events[at0002]/data[at0003]/items[at0033]/value/value LIKE $description",
                UUID.class,
                ItemList.class);

        List<Record2<UUID, ItemList>> result = openEhrClient
                .aqlEndpoint()
                .execute(query, new ParameterValue("ehr_id", ehr), new ParameterValue("description", "tes%"));
        assertThat(result).isNotNull().hasSize(2);
    }

    @Test
    public void testExecute5() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

        Query<Record2<UUID, Event>> query = Query.buildNativeQuery(
                "select e/ehr_id/value,o/data[at0001]/events[at0002]  " + "from EHR e[ehr_id/value = $ehr_id]  "
                        + "contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1",
                UUID.class,
                Event.class);

        List<Record2<UUID, Event>> result =
                openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull().hasSize(2);
    }

    @Test
    public void testExecute6() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

        Query<Record2<UUID, History>> query = Query.buildNativeQuery(
                "select e/ehr_id/value,o/data[at0001]  " + "from EHR e[ehr_id/value = $ehr_id]  "
                        + "contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1",
                UUID.class,
                History.class);

        List<Record2<UUID, History>> result =
                openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull().hasSize(2);
    }

    @Test
    public void testExecute7() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

        Query<Record2<UUID, Observation>> query = Query.buildNativeQuery(
                "select e/ehr_id/value,o  " + "from EHR e[ehr_id/value = $ehr_id]  "
                        + "contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1",
                UUID.class,
                Observation.class);

        List<Record2<UUID, Observation>> result =
                openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull().hasSize(2);
    }

    @Test
    public void testExecute8() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

        Query<Record2<UUID, Double>> query = Query.buildNativeQuery(
                "select e/ehr_id/value,o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude  "
                        + "from EHR e[ehr_id/value = $ehr_id]  "
                        + "contains COMPOSITION a contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1",
                UUID.class,
                Double.class);

        List<Record2<UUID, Double>> result =
                openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull().hasSize(2);
    }

    @Test
    public void testExecute9() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

        Query<Record2<UUID, Double>> query = Query.buildNativeQuery(
                "select e/ehr_id/value,o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude  "
                        + "from EHR e[ehr_id/value = $ehr_id]  "
                        + "contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1",
                UUID.class,
                Double.class);

        List<Record2<UUID, Double>> result =
                openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull().hasSize(2);
    }

    @Test
    public void testExecute10() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

        Query<Record2<UUID, Double>> query = Query.buildNativeQuery(
                "select e/ehr_id/value,o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude  "
                        + "from EHR e[ehr_id/value = $ehr_id]  "
                        + "contains Observation o "
                        + "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1",
                UUID.class,
                Double.class);

        List<Record2<UUID, Double>> result =
                openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull().hasSize(2);
    }

    @Test
    public void testExecute11() throws IOException {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        Composition composition = new CanonicalJson()
                .unmarshal(
                        IOUtils.toString(CompositionTestDataCanonicalJson.CORONA.getStream(), StandardCharsets.UTF_8),
                        Composition.class);
        Flattener flattener = new Flattener(new TestDataTemplateProvider());
        CoronaAnamneseComposition coronaAnamneseComposition =
                flattener.flatten(composition, CoronaAnamneseComposition.class);
        coronaAnamneseComposition.setVersionUid(null);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(coronaAnamneseComposition);

        Query<Record2<String, String>> query = Query.buildNativeQuery(
                "Select "
                        + " o/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value/value as var1,"
                        + " o/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value/value as var2"
                        + " from EHR e[ehr_id/value = $ehr_id] "
                        + " contains COMPOSITION c3[openEHR-EHR-COMPOSITION.report.v1]"
                        + " contains SECTION s4[openEHR-EHR-SECTION.adhoc.v1]"
                        + " contains OBSERVATION o[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0] "
                        + " WHERE"
                        + "  o/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value/value is not null "
                        + "  AND"
                        + "  o/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value/value is not null",
                String.class,
                String.class);

        List<Record2<String, String>> result =
                openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull();
        assertThat(result)
                .extracting(Record2::value1, Record2::value2)
                .containsExactlyInAnyOrder(
                        new Tuple("Nicht vorhanden", "Durchfall"),
                        new Tuple("Nicht vorhanden", "gestörter Geruchssinn"),
                        new Tuple("Nicht vorhanden", "gestörter Geschmackssinn"),
                        new Tuple("Nicht vorhanden", "Heiserkeit"),
                        new Tuple("Vorhanden", "Fieber oder erhöhte Körpertemperatur"),
                        new Tuple("Vorhanden", "Husten"),
                        new Tuple("Vorhanden", "Schnupfen"));
    }

    @Test
    public void testExecute12() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

        Containment observationContainment = new Containment("OBSERVATION");

        NativeSelectAqlField<Double> magnitudeField = new NativeSelectAqlField<>(
                observationContainment,
                "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude",
                Double.class);
        EntityQuery<Record2<UUID, Double>> entityQuery =
                Query.buildEntityQuery(observationContainment, EhrFields.EHR_ID(), magnitudeField);

        Parameter<UUID> ehrIdParameter = entityQuery.buildParameter();
        entityQuery.where(
                Condition.equal(EhrFields.EHR_ID(), ehrIdParameter).and(Condition.equal(magnitudeField, 1.1d)));

        List<Record2<UUID, Double>> result =
                openEhrClient.aqlEndpoint().execute(entityQuery, ehrIdParameter.setValue(ehr));
        assertThat(result).isNotNull().size().isEqualTo(2);
    }

    @Test
    public void testExecute13() throws IOException {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        Composition composition = new CanonicalJson()
                .unmarshal(
                        IOUtils.toString(CompositionTestDataCanonicalJson.CORONA.getStream(), StandardCharsets.UTF_8),
                        Composition.class);
        Flattener flattener = new Flattener(new TestDataTemplateProvider());
        CoronaAnamneseComposition coronaAnamneseComposition =
                flattener.flatten(composition, CoronaAnamneseComposition.class);
        coronaAnamneseComposition.setVersionUid(null);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(coronaAnamneseComposition);

        Query query = Query.buildNativeQuery("Select DISTINCT "
                + " o/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value/value as var1, o/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value/value as var2 "
                + " from EHR e[ehr_id/value = $ehr_id] "
                + " contains COMPOSITION c3[openEHR-EHR-COMPOSITION.report.v1]"
                + " contains SECTION s4[openEHR-EHR-SECTION.adhoc.v1]"
                + " contains OBSERVATION o[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]"
                + " WHERE"
                + "  o/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value/value is not null "
                + "  AND"
                + "  o/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value/value is not null");

        QueryResponseData result = openEhrClient.aqlEndpoint().executeRaw(query, new ParameterValue("ehr_id", ehr));

        assertNotNull(result);
        assertNotNull(result.getQuery());
        assertNotNull(result.getRows());
        assertNotNull(result.getColumns());
        assertEquals(7, result.getRows().size());
        assertEquals(2, result.getColumns().size());

        List expectedResults = Arrays.asList(
                List.of("Nicht vorhanden", "Durchfall"),
                List.of("Nicht vorhanden", "gestörter Geruchssinn"),
                List.of("Nicht vorhanden", "gestörter Geschmackssinn"),
                List.of("Nicht vorhanden", "Heiserkeit"),
                List.of("Vorhanden", "Fieber oder erhöhte Körpertemperatur"),
                List.of("Vorhanden", "Husten"),
                List.of("Vorhanden", "Schnupfen"));

        assertTrue(CollectionUtils.isEqualCollection(result.getRows(), expectedResults));
    }

    @Test
    public void testExecute14() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        Query query = Query.buildNativeQuery("Invalid aql query");

        Exception exception = assertThrows(
                WrongStatusCodeException.class,
                () -> openEhrClient.aqlEndpoint().executeRaw(query, new ParameterValue("ehr_id", ehr)));

        String expectedMessage = "AQL Parse exception: line 1: char 0 mismatched input 'Invalid'";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testExecute15() {

        Query query = Query.buildNativeQuery(
                "Select o/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value/value, o/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value/value "
                        + "from EHR e[ehr_id/value = $ehr_id] "
                        + "contains COMPOSITION c3[openEHR-EHR-COMPOSITION.report.v1] contains SECTION s4[openEHR-EHR-SECTION.adhoc.v1] contains OBSERVATION o[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]");

        Exception exception = assertThrows(
                ClientException.class, () -> openEhrClient.aqlEndpoint().executeRaw(query, null));

        String expectedMessage = "Invalid parameters";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testExecute16() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        Exception exception = assertThrows(
                ClientException.class,
                () -> openEhrClient.aqlEndpoint().executeRaw(null, new ParameterValue("ehr_id", ehr)));

        String expectedMessage = "Invalid query";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testExecuteStoredAqlQueryInvalid() {
        performInvalidExecuteStoredQuery(null);
        performInvalidExecuteStoredQuery(new StoredQueryParameter(null, null));
        performInvalidExecuteStoredQuery(new StoredQueryParameter("queryName", null));
        performInvalidExecuteStoredQuery(new StoredQueryParameter(null, "version"));
        performInvalidExecuteStoredQuery(new StoredQueryParameter("org.openehr::blablabla", null));
    }

    private void performInvalidExecuteStoredQuery(StoredQueryParameter requestParam) {
        Exception exception = assertThrows(
                ClientException.class, () -> openEhrClient.aqlEndpoint().executeStoredQuery(requestParam));

        String expectedMessage = "Invalid query";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testStoreAqlQueryInvalid1() {
        performInvalidStoreAqlQuery(null, null, "Invalid query");
        performInvalidStoreAqlQuery(null, new StoredQueryParameter(null, null), "Invalid query");
        performInvalidStoreAqlQuery(null, new StoredQueryParameter("queryName", null), "Invalid query");
        performInvalidStoreAqlQuery(null, new StoredQueryParameter(null, "version"), "Invalid query");

        Query<Record2<UUID, Double>> query = Query.buildNativeQuery(
                "select e/ehr_id/value,o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude  "
                        + "from EHR e[ehr_id/value = $ehr_id]  "
                        + "contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1",
                UUID.class,
                Double.class);

        performInvalidStoreAqlQuery(query, null, "Invalid parameters");
        performInvalidStoreAqlQuery(query, new StoredQueryParameter(null, null), "Invalid parameters");
        performInvalidStoreAqlQuery(query, new StoredQueryParameter("queryName", null), "Invalid parameters");
        performInvalidStoreAqlQuery(query, new StoredQueryParameter(null, "version"), "Invalid parameters");
    }

    private void performInvalidStoreAqlQuery(Query query, StoredQueryParameter requestParam, String expectedMessage) {
        Exception exception = assertThrows(
                ClientException.class, () -> openEhrClient.aqlEndpoint().storeAqlQuery(query, requestParam));

        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testGetAqlQueryInvalid() {
        checkInvalidStoredAqlQuery(null, null);
        checkInvalidStoredAqlQuery(null, "");
        checkInvalidStoredAqlQuery("", null);
        checkInvalidStoredAqlQuery("", "");
    }

    private void checkInvalidStoredAqlQuery(String qualifiedQueryName, String version) {
        StoredQueryParameter requestParam = new StoredQueryParameter(qualifiedQueryName, version);

        Exception exception = assertThrows(
                ClientException.class, () -> openEhrClient.aqlEndpoint().getStoredAqlQuery(requestParam));

        String expectedMessage = "Invalid query";

        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testStoreAndRetrieveAqlQuery() {

        Query<Record2<UUID, Double>> query = Query.buildNativeQuery(
                "SELECT c " + "FROM EHR e[ehr_id/value=$ehr_id] "
                        + "CONTAINS COMPOSITION c[openEHR-EHR-COMPOSITION.encounter.v1] "
                        + "CONTAINS OBSERVATION obs[openEHR-EHR-OBSERVATION.blood_pressure.v1] "
                        + "WHERE obs/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude >= 100",
                UUID.class,
                Double.class);

        String qualifiedQueryName = "org.openehr::storedQueryName";
        int patchVersion = 0;
        String version = "1.0.";

        boolean successful = false;

        do {
            // try to store the query
            try {
                openEhrClient
                        .aqlEndpoint()
                        .storeAqlQuery(query, new StoredQueryParameter(qualifiedQueryName, version + patchVersion));
                successful = true;
            } catch (Exception e) {
                patchVersion++;
            }
        } while (!successful && patchVersion < 1000);

        String fullVersion = version + patchVersion;

        StoredQueryResponseData storedAqlQuery = openEhrClient
                .aqlEndpoint()
                .getStoredAqlQuery(new StoredQueryParameter(qualifiedQueryName, fullVersion));

        // TODO: the qualified query name should not have the version number included
        String expectedName = qualifiedQueryName + "/" + version + patchVersion;
        assertEquals(expectedName, storedAqlQuery.getName());

        // TODO: version is currently included within the qualified name and the version field is not filled
        // this seems to not be conform to the specification
        // https://specifications.openehr.org/releases/ITS-REST/Release-1.0.0/definitions.html#definitions-stored-query-get-1 (for Release 1.0.0)
        // https://specifications.openehr.org/releases/ITS-REST/Release-1.0.2/definitions.html#definitions-stored-query-get-1 (for currently valid Release 1.0.2)
        // assertEquals(fullVersion,storedAqlQuery.getVersion());
        assertEquals(query.buildAql(), storedAqlQuery.getAqlQuery());
    }

    @Test
    public void testExecuteStoredAqlQuery() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        Query<Record2<UUID, Double>> query = Query.buildNativeQuery(
                "SELECT c " + "FROM EHR e[ehr_id/value=$ehr_id] "
                        + "CONTAINS COMPOSITION c[openEHR-EHR-COMPOSITION.encounter.v1] "
                        + "CONTAINS OBSERVATION obs[openEHR-EHR-OBSERVATION.blood_pressure.v1] "
                        + "WHERE obs/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude >= 100",
                UUID.class,
                Double.class);

        String qualifiedQueryName = "org.openehr::storedQueryName";
        int patchVersion = 0;
        String version = "1.0.";

        boolean successful = false;

        do {
            // try to store the query
            try {
                openEhrClient
                        .aqlEndpoint()
                        .storeAqlQuery(query, new StoredQueryParameter(qualifiedQueryName, version + patchVersion));
                successful = true;
            } catch (Exception e) {
                patchVersion++;
            }
        } while (!successful && patchVersion < 1000);

        String fullVersion = version + patchVersion;

        QueryResponseData queryResponse = openEhrClient
                .aqlEndpoint()
                .executeStoredQuery(new StoredQueryParameter(qualifiedQueryName, fullVersion));

        // TODO: the qualified query name should not have the version number included
        String expectedName = qualifiedQueryName + "/" + version + patchVersion;
        assertEquals(expectedName, queryResponse.getName());

        assertEquals(query.buildAql(), queryResponse.getQuery());

        assertTrue(queryResponse.getColumns().size() > 0);
    }

    @Test
    public void testExecuteStoredAqlQueryFullParams() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        Query<Record2<UUID, Double>> query = Query.buildNativeQuery(
                "SELECT c " + "FROM EHR e[ehr_id/value=$ehr_id] "
                        + "CONTAINS COMPOSITION c[openEHR-EHR-COMPOSITION.encounter.v1] "
                        + "CONTAINS OBSERVATION obs[openEHR-EHR-OBSERVATION.blood_pressure.v1] "
                        + "WHERE obs/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude >= 100",
                UUID.class,
                Double.class);

        String qualifiedQueryName = "org.openehr::storedQueryName";
        int patchVersion = 0;
        String version = "1.0.";

        boolean successful = false;

        do {
            // try to store the query
            try {
                openEhrClient
                        .aqlEndpoint()
                        .storeAqlQuery(query, new StoredQueryParameter(qualifiedQueryName, version + patchVersion));
                successful = true;
            } catch (Exception e) {
                patchVersion++;
            }
        } while (!successful && patchVersion < 1000);

        String fullVersion = version + patchVersion;

        int offsetValue = 1;
        int fetchValue = 2;
        StoredQueryParameter queryParameter = new StoredQueryParameter(qualifiedQueryName, fullVersion);
        queryParameter.offset(offsetValue).fetch(fetchValue).addQueryParam("ehr_id", ehr.toString());

        QueryResponseData queryResponse = openEhrClient.aqlEndpoint().executeStoredQuery(queryParameter);

        // TODO: the qualified query name should not have the version number included
        String expectedName = qualifiedQueryName + "/" + version + patchVersion;
        assertEquals(expectedName, queryResponse.getName());

        assertEquals(query.buildAql() + " LIMIT " + fetchValue + " OFFSET " + offsetValue, queryResponse.getQuery());

        assertTrue(queryResponse.getColumns().size() > 0);
    }
}
