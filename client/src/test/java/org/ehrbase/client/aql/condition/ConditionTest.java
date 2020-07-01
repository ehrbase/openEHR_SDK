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

package org.ehrbase.client.aql.condition;

import org.ehrbase.client.aql.query.EntityQuery;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.BloodPressureTrainingSampleObservationContainment;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConditionTest {

    @Test
    public void testBuildAql() {

        BloodPressureTrainingSampleObservationContainment containmentObservation = BloodPressureTrainingSampleObservationContainment.getInstance();
        EntityQuery query = mock(EntityQuery.class);
        when(query.buildVariabelName(any())).thenReturn("v");
        containmentObservation.bindQuery(query);
        Condition condition1 = Condition.greaterOrEqual(containmentObservation.DIASTOLIC_MAGNITUDE, 13d);
        Condition condition2 = Condition.notEqual(containmentObservation.MEAN_ARTERIAL_PRESSURE_UNITS, "mh");
        Condition condition3 = Condition.lessThen(containmentObservation.TIME_VALUE, OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC));

        Condition cut = Condition.and(condition1, Condition.or(condition2, condition3));

        assertThat(cut.buildAql()).isEqualTo("(v/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value/magnitude >= 13.0 and " +
                "(v/data[at0001]/events[at0002]/data[at0003]/items[at1006]/value/units != 'mh' or v/data[at0001]/events[at0002]/time/value < '2019-04-03T22:00:00Z')" +
                ")");

    }
}