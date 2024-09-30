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
package org.ehrbase.openehr.sdk.generator.commons.aql.query;

import java.time.temporal.TemporalAccessor;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.ehrbase.openehr.sdk.generator.commons.aql.condition.Condition;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.ContainmentExpression;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.ContainmentPath;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.EhrFields;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.NativeSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.orderby.OrderByExpression;
import org.ehrbase.openehr.sdk.generator.commons.aql.parameter.Parameter;
import org.ehrbase.openehr.sdk.generator.commons.aql.record.Record3;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasebloodpressuresimpledev0composition.EhrbaseBloodPressureSimpleDeV0CompositionContainment;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasebloodpressuresimpledev0composition.definition.BloodPressureTrainingSampleObservation;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasebloodpressuresimpledev0composition.definition.BloodPressureTrainingSampleObservationContainment;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasebloodpressuresimpledev0composition.definition.CuffSizeDefiningCode;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.virologischerbefundcomposition.definition.BefundObservationContainment;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.virologischerbefundcomposition.definition.ProVirusClusterContainment;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.virologischerbefundcomposition.definition.ProbeClusterContainment;
import org.junit.jupiter.api.Test;

public class EntityQueryTest {

    @Test
    public void buildAql() {

        EhrbaseBloodPressureSimpleDeV0CompositionContainment containmentComposition =
                EhrbaseBloodPressureSimpleDeV0CompositionContainment.getInstance();

        BloodPressureTrainingSampleObservationContainment containmentObservation =
                BloodPressureTrainingSampleObservationContainment.getInstance();

        ContainmentPath contains = containmentComposition.contains(containmentObservation);

        EntityQuery<Record3<TemporalAccessor, BloodPressureTrainingSampleObservation, CuffSizeDefiningCode>>
                entityQuery = Query.buildEntityQuery(
                        contains,
                        containmentComposition.START_TIME_VALUE,
                        containmentObservation.BLOOD_PRESSURE_TRAINING_SAMPLE_OBSERVATION,
                        containmentObservation.CUFF_SIZE_DEFINING_CODE);
        Parameter<UUID> ehrIdParameter = entityQuery.buildParameter();
        entityQuery
                .where(Condition.equal(EhrFields.EHR_ID(), ehrIdParameter)
                        .and(Condition.greaterOrEqual(containmentObservation.SYSTOLIC_MAGNITUDE, 22d)))
                .orderBy(OrderByExpression.ascending(containmentComposition.START_TIME_VALUE)
                        .andThenDescending(containmentObservation.TIME_VALUE));

        Assertions.assertThat(entityQuery.buildAql())
                .isEqualTo(
                        "Select c0/context/start_time/value as startTimeValue, o1 as BloodPressureTrainingSampleObservation, o1/protocol[at0011]/items[at0013]/value/defining_code as cuffSizeDefiningCode "
                                + "from EHR e "
                                + "contains COMPOSITION c0[openEHR-EHR-COMPOSITION.sample_encounter.v1] contains OBSERVATION o1[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1] "
                                + "where (e/ehr_id/value = $parm0 and o1/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude >= 22.0) "
                                + "order by c0/context/start_time/value ASCENDING, o1/data[at0001]/events[at0002]/time/value DESCENDING");
    }

    @Test
    public void buildAqlWithNativePath() {
        BefundObservationContainment befundObservationContainment = BefundObservationContainment.getInstance();
        ProVirusClusterContainment proVirusClusterContainment = ProVirusClusterContainment.getInstance();
        ProbeClusterContainment probeClusterContainment = ProbeClusterContainment.getInstance();

        Containment compositionContainment = new Containment("COMPOSITION");
        ContainmentExpression containment = compositionContainment
                .contains(befundObservationContainment)
                .contains(proVirusClusterContainment.and(probeClusterContainment));
        EntityQuery<Record3<String, TemporalAccessor, UUID>> entityQuery = Query.buildEntityQuery(
                containment,
                proVirusClusterContainment.VIRUS_VALUE,
                probeClusterContainment.ZEITPUNKT_DER_PROBENENTNAHME_VALUE,
                new NativeSelectAqlField<>(compositionContainment, "/uid/value", "uid", UUID.class));

        entityQuery.where(Condition.equal(
                new NativeSelectAqlField<>(compositionContainment, "/name/value", String.class),
                "Mikrobiologischer Befund"));

        Assertions.assertThat(entityQuery.buildAql())
                .isEqualTo(
                        "Select c0/items[at0024]/value/value as virusValue, c1/items[at0015]/value/value as zeitpunktDerProbenentnahmeValue, c2/uid/value as uid "
                                + "from EHR e "
                                + "contains COMPOSITION c2 "
                                + "contains OBSERVATION o3[openEHR-EHR-OBSERVATION.laboratory_test_result.v1] "
                                + "contains (CLUSTER c0[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1] and CLUSTER c1[openEHR-EHR-CLUSTER.specimen.v1]) "
                                + "where c2/name/value = 'Mikrobiologischer Befund'");
    }
}
