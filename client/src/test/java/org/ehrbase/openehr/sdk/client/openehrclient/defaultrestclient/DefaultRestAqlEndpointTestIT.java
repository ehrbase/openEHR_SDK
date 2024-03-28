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
package org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient;

import static org.assertj.core.api.Assertions.assertThat;

import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import com.nedap.archie.rm.support.identification.UID;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.assertj.core.groups.Tuple;
import org.ehrbase.openehr.sdk.generator.commons.aql.condition.Condition;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.EhrFields;
import org.ehrbase.openehr.sdk.generator.commons.aql.orderby.OrderByExpression;
import org.ehrbase.openehr.sdk.generator.commons.aql.parameter.Parameter;
import org.ehrbase.openehr.sdk.generator.commons.aql.parameter.ParameterValue;
import org.ehrbase.openehr.sdk.generator.commons.aql.query.EntityQuery;
import org.ehrbase.openehr.sdk.generator.commons.aql.query.Query;
import org.ehrbase.openehr.sdk.generator.commons.aql.record.Record1;
import org.ehrbase.openehr.sdk.generator.commons.aql.record.Record2;
import org.ehrbase.openehr.sdk.generator.commons.aql.record.Record3;
import org.ehrbase.openehr.sdk.generator.commons.aql.top.TopExpresion;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.BloodPressureTrainingSampleObservationProxy;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.TestData;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasebloodpressuresimpledev0composition.EhrbaseBloodPressureSimpleDeV0Composition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasebloodpressuresimpledev0composition.EhrbaseBloodPressureSimpleDeV0CompositionContainment;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasebloodpressuresimpledev0composition.definition.BloodPressureTrainingSampleObservation;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasebloodpressuresimpledev0composition.definition.BloodPressureTrainingSampleObservationContainment;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasebloodpressuresimpledev0composition.definition.CuffSizeDefiningCode;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasebloodpressuresimpledev0composition.definition.KorotkoffSoundsDefiningCode;
import org.junit.jupiter.api.Test;

class DefaultRestAqlEndpointTestIT extends SdkClientTestIT {

    @Test
    void testExecute() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        Query<Record2<String, DvDateTime>> query = Query.buildNativeQuery(
                "select  a/template_id, a/context/start_time from EHR e[ehr_id/value = $ehr_id]  contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1]",
                String.class,
                DvDateTime.class);

        List<Record2<String, DvDateTime>> result =
                openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull().hasSize(2);
    }

    @Test
    void testExecuteProxy() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        Query<Record2<String, BloodPressureTrainingSampleObservationProxy>> query = Query.buildNativeQuery(
                "select  a/template_id, o from EHR e[ehr_id/value = $ehr_id]  contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1] contains OBSERVATION o ",
                String.class,
                BloodPressureTrainingSampleObservationProxy.class);

        List<Record2<String, BloodPressureTrainingSampleObservationProxy>> result =
                openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull().hasSize(2);

        assertThat(result.get(0).value2().dummy).isNull();
    }

    @Test
    void testExecuteValue() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        EhrbaseBloodPressureSimpleDeV0Composition comp1 = openEhrClient
                .compositionEndpoint(ehr)
                .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());
        EhrbaseBloodPressureSimpleDeV0Composition comp2 = openEhrClient
                .compositionEndpoint(ehr)
                .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        Query<Record2<Double, OffsetDateTime>> query = Query.buildNativeQuery(
                "select  a/content[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude, a/context/start_time/value from EHR e[ehr_id/value = $ehr_id]  contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1]",
                Double.class,
                OffsetDateTime.class);

        List<Record2<Double, OffsetDateTime>> result =
                openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull().hasSize(2);
        assertThat(result)
                .extracting(
                        objectVersionIdOffsetDateTimeRecord2 -> objectVersionIdOffsetDateTimeRecord2.value1(),
                        Record2::value2)
                .containsExactlyInAnyOrder(
                        new Tuple(22d, OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC)),
                        new Tuple(22d, OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC)));
    }

    @Test
    void testExecuteCustomConverters() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        EhrbaseBloodPressureSimpleDeV0Composition comp1 = openEhrClient
                .compositionEndpoint(ehr)
                .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());
        EhrbaseBloodPressureSimpleDeV0Composition comp2 = openEhrClient
                .compositionEndpoint(ehr)
                .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        Query<Record2<ObjectVersionId, TemporalAccessor>> query = Query.buildNativeQuery(
                "select  a/uid/value, a/context/start_time/value from EHR e[ehr_id/value = $ehr_id]  contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1]",
                ObjectVersionId.class,
                TemporalAccessor.class);

        List<Record2<ObjectVersionId, TemporalAccessor>> result =
                openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull().hasSize(2);
        assertThat(result)
                .extracting(
                        objectVersionIdOffsetDateTimeRecord2 ->
                                objectVersionIdOffsetDateTimeRecord2.value1().toString(),
                        Record2::value2)
                .containsExactlyInAnyOrder(
                        new Tuple(
                                comp1.getVersionUid().toString(),
                                OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC)),
                        new Tuple(
                                comp2.getVersionUid().toString(),
                                OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC)));
    }

    @Test
    void testExecuteEntityQuery() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        EhrbaseBloodPressureSimpleDeV0Composition comp1 = openEhrClient
                .compositionEndpoint(ehr)
                .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());
        EhrbaseBloodPressureSimpleDeV0Composition comp2 = openEhrClient
                .compositionEndpoint(ehr)
                .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0CompositionContainment containmentComposition =
                EhrbaseBloodPressureSimpleDeV0CompositionContainment.getInstance();

        BloodPressureTrainingSampleObservationContainment containmentObservation =
                BloodPressureTrainingSampleObservationContainment.getInstance();

        containmentComposition.setContains(containmentObservation);

        EntityQuery<Record3<TemporalAccessor, BloodPressureTrainingSampleObservation, CuffSizeDefiningCode>>
                entityQuery = Query.buildEntityQuery(
                        containmentComposition,
                        containmentComposition.START_TIME_VALUE,
                        containmentObservation.BLOOD_PRESSURE_TRAINING_SAMPLE_OBSERVATION,
                        containmentObservation.CUFF_SIZE_DEFINING_CODE);
        Parameter<UUID> ehrIdParameter = entityQuery.buildParameter();
        entityQuery.where(Condition.equal(EhrFields.EHR_ID(), ehrIdParameter));

        List<Record3<TemporalAccessor, BloodPressureTrainingSampleObservation, CuffSizeDefiningCode>> actual =
                openEhrClient.aqlEndpoint().execute(entityQuery, ehrIdParameter.setValue(ehr));
        assertThat(actual).size().isEqualTo(2);

        Record3<TemporalAccessor, BloodPressureTrainingSampleObservation, CuffSizeDefiningCode> record1 = actual.get(0);
        assertThat(record1.value1()).isEqualTo(OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC));
        assertThat(record1.value2().getKorotkoffSoundsDefiningCode())
                .isEqualTo(KorotkoffSoundsDefiningCode.FIFTH_SOUND);
        assertThat(record1.value3()).isEqualTo(CuffSizeDefiningCode.ADULT);
    }

    @Test
    void testExecuteEntityQueryWhere() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        EhrbaseBloodPressureSimpleDeV0Composition comp1 = openEhrClient
                .compositionEndpoint(ehr)
                .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition ehrbaseBloodPressureSimpleDeV0Composition =
                TestData.buildEhrbaseBloodPressureSimpleDeV0();
        ehrbaseBloodPressureSimpleDeV0Composition
                .getBloodPressureTrainingSample()
                .get(0)
                .setSystolicMagnitude(44d);
        EhrbaseBloodPressureSimpleDeV0Composition comp2 = openEhrClient
                .compositionEndpoint(ehr)
                .mergeCompositionEntity(ehrbaseBloodPressureSimpleDeV0Composition);

        EhrbaseBloodPressureSimpleDeV0CompositionContainment containmentComposition =
                EhrbaseBloodPressureSimpleDeV0CompositionContainment.getInstance();

        BloodPressureTrainingSampleObservationContainment containmentObservation =
                BloodPressureTrainingSampleObservationContainment.getInstance();

        containmentComposition.setContains(containmentObservation);

        class TestCase {
            int id;
            Condition otherCondition;
            UUID[] uuids;

            TestCase(int id, Condition otherCondition, UUID... uuids) {
                this.id = id;
                this.otherCondition = otherCondition;
                this.uuids = uuids;
            }
        }

        List<TestCase> testCases = new ArrayList<>();

        testCases.add(new TestCase(
                1,
                Condition.greaterOrEqual(containmentObservation.SYSTOLIC_MAGNITUDE, 30d),
                UUID.fromString(comp2.getVersionUid().getObjectId().getValue())));

        testCases.add(new TestCase(
                2,
                Condition.matches(containmentObservation.SYSTOLIC_MAGNITUDE, 22d, 44d),
                UUID.fromString(comp1.getVersionUid().getObjectId().getValue()),
                UUID.fromString(comp2.getVersionUid().getObjectId().getValue())));

        testCases.add(new TestCase(
                3,
                Condition.exists(containmentObservation.DEVICE).not(),
                UUID.fromString(comp1.getVersionUid().getObjectId().getValue()),
                UUID.fromString(comp2.getVersionUid().getObjectId().getValue())));

        testCases.forEach(t -> {
            EntityQuery<Record1<EhrbaseBloodPressureSimpleDeV0Composition>> entityQuery = Query.buildEntityQuery(
                    containmentComposition, containmentComposition.EHRBASE_BLOOD_PRESSURE_SIMPLE_DE_V0_COMPOSITION);
            Parameter<UUID> ehrIdParameter = entityQuery.buildParameter();

            Condition where =
                    Condition.equal(EhrFields.EHR_ID(), ehrIdParameter).and(t.otherCondition);
            entityQuery.where(where);

            assertThat(openEhrClient.aqlEndpoint().execute(entityQuery, ehrIdParameter.setValue(ehr)))
                    .extracting(Record1::value1)
                    .extracting(EhrbaseBloodPressureSimpleDeV0Composition::getVersionUid)
                    .extracting(ObjectVersionId::getObjectId)
                    .extracting(UID::getValue)
                    .extracting(UUID::fromString)
                    .as("TestCase %s", t.id)
                    .containsExactlyInAnyOrder(t.uuids);
        });
    }

    @Test
    void testExecuteEntityQueryOrderBY() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        EhrbaseBloodPressureSimpleDeV0Composition comp1 = openEhrClient
                .compositionEndpoint(ehr)
                .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition ehrbaseBloodPressureSimpleDeV0Composition =
                TestData.buildEhrbaseBloodPressureSimpleDeV0();
        ehrbaseBloodPressureSimpleDeV0Composition
                .getBloodPressureTrainingSample()
                .get(0)
                .setSystolicMagnitude(44d);
        EhrbaseBloodPressureSimpleDeV0Composition comp2 = openEhrClient
                .compositionEndpoint(ehr)
                .mergeCompositionEntity(ehrbaseBloodPressureSimpleDeV0Composition);

        EhrbaseBloodPressureSimpleDeV0Composition ehrbaseBloodPressureSimpleDeV0Composition2 =
                TestData.buildEhrbaseBloodPressureSimpleDeV0();
        ehrbaseBloodPressureSimpleDeV0Composition2
                .getBloodPressureTrainingSample()
                .get(0)
                .setSystolicMagnitude(44d);
        ehrbaseBloodPressureSimpleDeV0Composition2
                .getBloodPressureTrainingSample()
                .get(0)
                .setDiastolicMagnitude(44d);
        EhrbaseBloodPressureSimpleDeV0Composition comp3 = openEhrClient
                .compositionEndpoint(ehr)
                .mergeCompositionEntity(ehrbaseBloodPressureSimpleDeV0Composition2);

        EhrbaseBloodPressureSimpleDeV0CompositionContainment containmentComposition =
                EhrbaseBloodPressureSimpleDeV0CompositionContainment.getInstance();

        BloodPressureTrainingSampleObservationContainment containmentObservation =
                BloodPressureTrainingSampleObservationContainment.getInstance();

        containmentComposition.setContains(containmentObservation);

        class TestCase {
            int id;
            OrderByExpression orderBy;
            UUID[] uuids;

            TestCase(int id, OrderByExpression orderBy, UUID... uuids) {
                this.id = id;
                this.orderBy = orderBy;
                this.uuids = uuids;
            }
        }

        List<TestCase> testCases = new ArrayList<>();

        testCases.add(new TestCase(
                1,
                OrderByExpression.descending(containmentObservation.SYSTOLIC_MAGNITUDE)
                        .andThenDescending(containmentObservation.DIASTOLIC_MAGNITUDE),
                UUID.fromString(comp3.getVersionUid().getObjectId().getValue()),
                UUID.fromString(comp2.getVersionUid().getObjectId().getValue()),
                UUID.fromString(comp1.getVersionUid().getObjectId().getValue())));

        testCases.add(new TestCase(
                2,
                OrderByExpression.descending(containmentObservation.SYSTOLIC_MAGNITUDE)
                        .andThenAscending(containmentObservation.DIASTOLIC_MAGNITUDE),
                UUID.fromString(comp2.getVersionUid().getObjectId().getValue()),
                UUID.fromString(comp3.getVersionUid().getObjectId().getValue()),
                UUID.fromString(comp1.getVersionUid().getObjectId().getValue())));

        testCases.add(new TestCase(
                3,
                OrderByExpression.ascending(containmentObservation.SYSTOLIC_MAGNITUDE)
                        .andThenAscending(containmentObservation.DIASTOLIC_MAGNITUDE),
                UUID.fromString(comp1.getVersionUid().getObjectId().getValue()),
                UUID.fromString(comp2.getVersionUid().getObjectId().getValue()),
                UUID.fromString(comp3.getVersionUid().getObjectId().getValue())));

        testCases.forEach(t -> {
            EntityQuery<Record1<EhrbaseBloodPressureSimpleDeV0Composition>> entityQuery = Query.buildEntityQuery(
                    containmentComposition, containmentComposition.EHRBASE_BLOOD_PRESSURE_SIMPLE_DE_V0_COMPOSITION);
            Parameter<UUID> ehrIdParameter = entityQuery.buildParameter();

            Condition where = Condition.equal(EhrFields.EHR_ID(), ehrIdParameter);
            entityQuery.where(where).orderBy(t.orderBy);

            assertThat(openEhrClient.aqlEndpoint().execute(entityQuery, ehrIdParameter.setValue(ehr)))
                    .extracting(Record1::value1)
                    .extracting(EhrbaseBloodPressureSimpleDeV0Composition::getVersionUid)
                    .extracting(ObjectVersionId::getObjectId)
                    .extracting(UID::getValue)
                    .extracting(UUID::fromString)
                    .as("TestCase %s", t.id)
                    .containsExactly(t.uuids);
        });
    }

    @Test
    void testExecuteEntityTOP() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        EhrbaseBloodPressureSimpleDeV0Composition comp1 = openEhrClient
                .compositionEndpoint(ehr)
                .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0Composition ehrbaseBloodPressureSimpleDeV0Composition =
                TestData.buildEhrbaseBloodPressureSimpleDeV0();
        ehrbaseBloodPressureSimpleDeV0Composition
                .getBloodPressureTrainingSample()
                .get(0)
                .setSystolicMagnitude(44d);
        EhrbaseBloodPressureSimpleDeV0Composition comp2 = openEhrClient
                .compositionEndpoint(ehr)
                .mergeCompositionEntity(ehrbaseBloodPressureSimpleDeV0Composition);

        EhrbaseBloodPressureSimpleDeV0Composition ehrbaseBloodPressureSimpleDeV0Composition2 =
                TestData.buildEhrbaseBloodPressureSimpleDeV0();
        ehrbaseBloodPressureSimpleDeV0Composition2
                .getBloodPressureTrainingSample()
                .get(0)
                .setSystolicMagnitude(44d);
        ehrbaseBloodPressureSimpleDeV0Composition2
                .getBloodPressureTrainingSample()
                .get(0)
                .setDiastolicMagnitude(44d);
        EhrbaseBloodPressureSimpleDeV0Composition comp3 = openEhrClient
                .compositionEndpoint(ehr)
                .mergeCompositionEntity(ehrbaseBloodPressureSimpleDeV0Composition2);

        EhrbaseBloodPressureSimpleDeV0CompositionContainment containmentComposition =
                EhrbaseBloodPressureSimpleDeV0CompositionContainment.getInstance();

        BloodPressureTrainingSampleObservationContainment containmentObservation =
                BloodPressureTrainingSampleObservationContainment.getInstance();

        containmentComposition.setContains(containmentObservation);

        class TestCase {
            int id;
            TopExpresion topExpresion;
            UUID[] uuids;

            TestCase(int id, TopExpresion topExpresion, UUID... uuids) {
                this.id = id;
                this.topExpresion = topExpresion;
                this.uuids = uuids;
            }
        }

        List<TestCase> testCases = new ArrayList<>();

        testCases.add(new TestCase(
                1,
                TopExpresion.forward(1),
                UUID.fromString(comp3.getVersionUid().getObjectId().getValue())));

        /* TODO: Direction is ignored in ehrbase. See https://github.com/ehrbase/ehrbase/issues/265
        testCases.add(new TestCase(2,
                TopExpresion.backward(1),
                (UUID.fromString(comp1.getVersionUid().getObjectId().getValue())));
        */
        testCases.forEach(t -> {
            EntityQuery<Record1<EhrbaseBloodPressureSimpleDeV0Composition>> entityQuery = Query.buildEntityQuery(
                    containmentComposition, containmentComposition.EHRBASE_BLOOD_PRESSURE_SIMPLE_DE_V0_COMPOSITION);

            Parameter<UUID> ehrIdParameter = entityQuery.buildParameter();

            Condition where = Condition.equal(EhrFields.EHR_ID(), ehrIdParameter);
            entityQuery
                    .top(t.topExpresion)
                    .where(where)
                    .orderBy(OrderByExpression.descending(containmentObservation.SYSTOLIC_MAGNITUDE)
                            .andThenDescending(containmentObservation.DIASTOLIC_MAGNITUDE));

            assertThat(openEhrClient.aqlEndpoint().execute(entityQuery, ehrIdParameter.setValue(ehr)))
                    .extracting(Record1::value1)
                    .extracting(EhrbaseBloodPressureSimpleDeV0Composition::getVersionUid)
                    .extracting(ObjectVersionId::getObjectId)
                    .extracting(UID::getValue)
                    .extracting(UUID::fromString)
                    .as("TestCase %s", t.id)
                    .containsExactly(t.uuids);
        });
    }

    @Test
    void testExecuteEntityQueryWithList() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        EhrbaseBloodPressureSimpleDeV0Composition comp1 = openEhrClient
                .compositionEndpoint(ehr)
                .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());
        EhrbaseBloodPressureSimpleDeV0Composition comp2 = openEhrClient
                .compositionEndpoint(ehr)
                .mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        EhrbaseBloodPressureSimpleDeV0CompositionContainment containmentComposition =
                EhrbaseBloodPressureSimpleDeV0CompositionContainment.getInstance();

        EntityQuery<Record2<TemporalAccessor, List<BloodPressureTrainingSampleObservation>>> entityQuery =
                Query.buildEntityQuery(
                        containmentComposition,
                        containmentComposition.START_TIME_VALUE,
                        containmentComposition.BLOOD_PRESSURE_TRAINING_SAMPLE);
        Parameter<UUID> ehrIdParameter = entityQuery.buildParameter();
        entityQuery.where(Condition.equal(EhrFields.EHR_ID(), ehrIdParameter));

        List<Record2<TemporalAccessor, List<BloodPressureTrainingSampleObservation>>> actual =
                openEhrClient.aqlEndpoint().execute(entityQuery, ehrIdParameter.setValue(ehr));
        assertThat(actual).size().isEqualTo(2);
    }

    @Test
    void testQueryCount() {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        Query<Record1<Integer>> query = Query.buildNativeQuery(
                "select  count(e/ehr_id/value) from EHR e contains composition c", Integer.class);

        List<Record1<Integer>> result = openEhrClient.aqlEndpoint().execute(query);
        assertThat(result).isNotNull();

        query = Query.buildNativeQuery("select  count(c/uid/value) from EHR e contains composition c", Integer.class);

        result = openEhrClient.aqlEndpoint().execute(query);
        assertThat(result).isNotNull().hasSize(1);
    }
}
