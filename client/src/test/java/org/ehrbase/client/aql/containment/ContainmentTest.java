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

package org.ehrbase.client.aql.containment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nedap.archie.rm.composition.AdminEntry;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.Observation;
import java.util.ArrayList;
import java.util.List;
import org.ehrbase.client.aql.query.EntityQuery;
import org.junit.Test;

public class ContainmentTest {

  @Test
  public void testContainment() {
    List<TestCase> testCases = new ArrayList<>();

    testCases.add(
        new TestCase(
            1,
            "openEHR-EHR-COMPOSITION.sample_encounter.v1",
            Composition.class,
            "COMPOSITION v[openEHR-EHR-COMPOSITION.sample_encounter.v1]"));

    testCases.add(
        new TestCase(
            2,
            "openEHR-EHR-OBSERVATION.sample_blood_pressure.v1",
            Observation.class,
            "OBSERVATION v[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"));
    testCases.add(
        new TestCase(
            3,
            "openEHR-EHR-ADMIN_ENTRY.discharge.v1",
            AdminEntry.class,
            "ADMIN_ENTRY v[openEHR-EHR-ADMIN_ENTRY.discharge.v1]"));

    EntityQuery query = mock(EntityQuery.class);
    when(query.buildVariabelName(any())).thenReturn("v");
    testCases.forEach(
        t -> {
          Containment cut = new Containment(t.archetype);
          cut.bindQuery(query);
          assertThat(cut.getType()).as(String.format("Test Case %d", t.id)).isEqualTo(t.type);
          assertThat(cut.buildAQL()).as(String.format("Test Case %d", t.id)).isEqualTo(t.aql);
        });
  }

  private class TestCase {
    final int id;
    final String archetype;
    final Class<?> type;
    final String aql;

    private TestCase(int id, String archetype, Class<?> type, String aql) {
      this.id = id;
      this.archetype = archetype;
      this.type = type;
      this.aql = aql;
    }
  }
}
