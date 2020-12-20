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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.ehrbase.client.aql.parameter.Parameter;
import org.ehrbase.client.aql.query.EntityQuery;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.BloodPressureTrainingSampleObservationContainment;
import org.junit.Test;

public class ConditionTest {

  @Test
  public void testBuildAql() {

    BloodPressureTrainingSampleObservationContainment containmentObservation =
        BloodPressureTrainingSampleObservationContainment.getInstance();
    EntityQuery query = mock(EntityQuery.class);
    when(query.buildVariabelName(any())).thenReturn("v");
    containmentObservation.bindQuery(query);
    Condition condition1 =
        Condition.greaterOrEqual(containmentObservation.DIASTOLIC_MAGNITUDE, 13d);
    Condition condition2 =
        Condition.notEqual(containmentObservation.MEAN_ARTERIAL_PRESSURE_UNITS, "mh");
    Condition condition3 =
        Condition.lessThan(
            containmentObservation.TIME_VALUE,
            OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC));

    Condition cut = condition1.and(condition2.or(condition3));

    assertThat(cut.buildAql())
        .isEqualTo(
            "(v/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value/magnitude >= 13.0 and "
                + "(v/data[at0001]/events[at0002]/data[at0003]/items[at1006]/value/units != 'mh' or v/data[at0001]/events[at0002]/time/value < '2019-04-03T22:00:00Z')"
                + ")");
  }

  @Test
  public void testBuildAqlFielCompare() {

    BloodPressureTrainingSampleObservationContainment containmentObservation =
        BloodPressureTrainingSampleObservationContainment.getInstance();
    EntityQuery query = mock(EntityQuery.class);
    when(query.buildVariabelName(any())).thenReturn("v");
    containmentObservation.bindQuery(query);
    Condition condition1 =
        Condition.greaterOrEqual(
            containmentObservation.DIASTOLIC_MAGNITUDE, containmentObservation.SYSTOLIC_MAGNITUDE);
    Condition condition2 =
        Condition.notEqual(
            containmentObservation.MEAN_ARTERIAL_PRESSURE_UNITS,
            containmentObservation.SYSTOLIC_UNITS);

    Condition cut = condition1.and(condition2);

    assertThat(cut.buildAql())
        .isEqualTo(
            "(v/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value/magnitude >= v/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude "
                + "and v/data[at0001]/events[at0002]/data[at0003]/items[at1006]/value/units != v/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/units)");
  }

  @Test
  public void testBuildAqlNot() {

    BloodPressureTrainingSampleObservationContainment containmentObservation =
        BloodPressureTrainingSampleObservationContainment.getInstance();
    EntityQuery query = mock(EntityQuery.class);
    when(query.buildVariabelName(any())).thenReturn("v");
    containmentObservation.bindQuery(query);
    Condition condition1 =
        Condition.greaterOrEqual(containmentObservation.DIASTOLIC_MAGNITUDE, 13d);
    Condition condition2 =
        Condition.notEqual(containmentObservation.MEAN_ARTERIAL_PRESSURE_UNITS, "mh");
    Condition condition3 =
        Condition.lessThan(
            containmentObservation.TIME_VALUE,
            OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC));

    Condition cut = condition1.and(condition2.or(condition3)).not();

    assertThat(cut.buildAql())
        .isEqualTo(
            "NOT (v/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value/magnitude >= 13.0 and (v/data[at0001]/events[at0002]/data[at0003]/items[at1006]/value/units != 'mh' or v/data[at0001]/events[at0002]/time/value < '2019-04-03T22:00:00Z'))");
  }

  @Test
  public void testBuildAqlNot2() {

    BloodPressureTrainingSampleObservationContainment containmentObservation =
        BloodPressureTrainingSampleObservationContainment.getInstance();
    EntityQuery query = mock(EntityQuery.class);
    when(query.buildVariabelName(any())).thenReturn("v");
    containmentObservation.bindQuery(query);
    Condition condition1 =
        Condition.greaterOrEqual(containmentObservation.DIASTOLIC_MAGNITUDE, 13d);
    Condition condition2 =
        Condition.notEqual(containmentObservation.MEAN_ARTERIAL_PRESSURE_UNITS, "mh");
    Condition condition3 =
        Condition.lessThan(
            containmentObservation.TIME_VALUE,
            OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC));

    Condition cut = condition1.and(condition2).and(condition3).not();

    assertThat(cut.buildAql())
        .isEqualTo(
            "NOT (v/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value/magnitude >= 13.0 and v/data[at0001]/events[at0002]/data[at0003]/items[at1006]/value/units != 'mh' and v/data[at0001]/events[at0002]/time/value < '2019-04-03T22:00:00Z')");
  }

  @Test
  public void testBuildExist() {

    BloodPressureTrainingSampleObservationContainment containmentObservation =
        BloodPressureTrainingSampleObservationContainment.getInstance();
    EntityQuery query = mock(EntityQuery.class);
    when(query.buildVariabelName(any())).thenReturn("v");
    containmentObservation.bindQuery(query);

    Condition cut = Condition.exists(containmentObservation.DIASTOLIC_MAGNITUDE);

    assertThat(cut.buildAql())
        .isEqualTo(
            "EXISTS v/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value/magnitude");
  }

  @Test
  public void testBuildAqlMatches() {

    BloodPressureTrainingSampleObservationContainment containmentObservation =
        BloodPressureTrainingSampleObservationContainment.getInstance();
    EntityQuery query = mock(EntityQuery.class);
    when(query.buildVariabelName(any())).thenReturn("v");
    containmentObservation.bindQuery(query);
    Condition condition1 = Condition.matches(containmentObservation.DIASTOLIC_MAGNITUDE, 13d, 22d);
    Condition condition2 =
        Condition.matches(
            containmentObservation.MEAN_ARTERIAL_PRESSURE_UNITS, new Parameter<>("parm+1"));

    Condition cut = condition1.and(condition2);

    assertThat(cut.buildAql())
        .isEqualTo(
            "(v/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value/magnitude matches {13.0,22.0} and "
                + "v/data[at0001]/events[at0002]/data[at0003]/items[at1006]/value/units matches {$parm+1})");
  }
}
