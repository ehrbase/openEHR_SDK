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

import com.nedap.archie.rm.composition.Observation;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.History;
import com.nedap.archie.rm.datastructures.ItemList;
import com.nedap.archie.rm.datavalues.quantity.DvQuantity;
import org.ehrbase.client.Integration;
import org.ehrbase.client.TestData;
import org.ehrbase.client.aql.parameter.ParameterValue;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record2;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.EhrbaseBloodPressureSimpleDeV0Composition;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

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

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

        Query<Record2<UUID, Double>> query = Query.buildNativeQuery(
                "select e/ehr_id/value,o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude  " +
                        "from EHR e[ehr_id/value = $ehr_id]  " +
                        "contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]" +
                        "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1"
                , UUID.class, Double.class
        );

        List<Record2<UUID, Double>> result = openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull();
        assertThat(result).size().isEqualTo(2);


    }

    @Test
    public void testExecute2() {

        UUID ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

        Query<Record2<UUID, DvQuantity>> query = Query.buildNativeQuery(
                "select e/ehr_id/value,o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value  " +
                        "from EHR e[ehr_id/value = $ehr_id]  " +
                        "contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]" +
                        "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1"
                , UUID.class, DvQuantity.class
        );

        List<Record2<UUID, DvQuantity>> result = openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull();
        assertThat(result).size().isEqualTo(2);


    }

    @Test
    public void testExecute3() {

        UUID ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

        Query<Record2<UUID, Element>> query = Query.buildNativeQuery(
                "select e/ehr_id/value,o/data[at0001]/events[at0002]/data[at0003]/items[at0004]  " +
                        "from EHR e[ehr_id/value = $ehr_id]  " +
                        "contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]" +
                        "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1"
                , UUID.class, Element.class
        );

        List<Record2<UUID, Element>> result = openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull();
        assertThat(result).size().isEqualTo(2);


    }

    @Test
    public void testExecute4() {

        UUID ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

        Query<Record2<UUID, ItemList>> query = Query.buildNativeQuery(
                "select e/ehr_id/value,o/data[at0001]/events[at0002]/data[at0003]  " +
                        "from EHR e[ehr_id/value = $ehr_id]  " +
                        "contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]" +
                        "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1"
                , UUID.class, ItemList.class
        );

        List<Record2<UUID, ItemList>> result = openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull();
        assertThat(result).size().isEqualTo(2);


    }

    @Test
    public void testExecute5() {

        UUID ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

        Query<Record2<UUID, Event>> query = Query.buildNativeQuery(
                "select e/ehr_id/value,o/data[at0001]/events[at0002]  " +
                        "from EHR e[ehr_id/value = $ehr_id]  " +
                        "contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]" +
                        "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1"
                , UUID.class, Event.class
        );

        List<Record2<UUID, Event>> result = openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull();
        assertThat(result).size().isEqualTo(2);


    }

    @Test
    public void testExecute6() {

        UUID ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

        Query<Record2<UUID, History>> query = Query.buildNativeQuery(
                "select e/ehr_id/value,o/data[at0001]  " +
                        "from EHR e[ehr_id/value = $ehr_id]  " +
                        "contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]" +
                        "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1"
                , UUID.class, History.class
        );

        List<Record2<UUID, History>> result = openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull();
        assertThat(result).size().isEqualTo(2);


    }

    @Test
    public void testExecute7() {

        UUID ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

        Query<Record2<UUID, Observation>> query = Query.buildNativeQuery(
                "select e/ehr_id/value,o  " +
                        "from EHR e[ehr_id/value = $ehr_id]  " +
                        "contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]" +
                        "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1"
                , UUID.class, Observation.class
        );

        List<Record2<UUID, Observation>> result = openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull();
        assertThat(result).size().isEqualTo(2);


    }

    @Test
    public void testExecute8() {

        UUID ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

        Query<Record2<UUID, Double>> query = Query.buildNativeQuery(
                "select e/ehr_id/value,o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude  " +
                        "from EHR e[ehr_id/value = $ehr_id]  " +
                        "contains COMPOSITION a contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]" +
                        "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1"
                , UUID.class, Double.class
        );

        List<Record2<UUID, Double>> result = openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull();
        assertThat(result).size().isEqualTo(2);


    }

    @Test
    public void testExecute9() {

        UUID ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

        Query<Record2<UUID, Double>> query = Query.buildNativeQuery(
                "select e/ehr_id/value,o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude  " +
                        "from EHR e[ehr_id/value = $ehr_id]  " +
                        "contains Observation o[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]" +
                        "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1"
                , UUID.class, Double.class
        );

        List<Record2<UUID, Double>> result = openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull();
        assertThat(result).size().isEqualTo(2);


    }

    @Test
    public void testExecute10() {

        UUID ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple1 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple1.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple1);

        EhrbaseBloodPressureSimpleDeV0Composition pressureSimple2 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        pressureSimple2.getBloodPressureTrainingSample().get(0).setSystolicMagnitude(1.1);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(pressureSimple2);

        Query<Record2<UUID, Double>> query = Query.buildNativeQuery(
                "select e/ehr_id/value,o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude  " +
                        "from EHR e[ehr_id/value = $ehr_id]  " +
                        "contains Observation o " +
                        "where o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1"
                , UUID.class, Double.class
        );

        List<Record2<UUID, Double>> result = openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull();
        assertThat(result).size().isEqualTo(2);


    }

}
