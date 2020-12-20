/*
 * Copyright (c) 2020 Christian Chevalley (Hannover Medical School) and Vitasystems GmbH
 *
 * This file is part of project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package org.ehrbase.client.aql.containment;

import static org.junit.Assert.assertNotNull;

import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.PatientenaufenthaltCompositionContainment;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.StandortClusterContainment;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.VersorgungsortAdminEntryContainment;
import org.junit.Test;

/**
 * test AQL containments as expressed in section 'Smart Infection Control AQL Analysis' The tests
 * are numbered according to the items of the analysis
 */
public class ContainmentNUMResearchTest {

  @Test
  public void test_1() {
    PatientenaufenthaltCompositionContainment patientenaufenthaltCompositionContainment =
        PatientenaufenthaltCompositionContainment.getInstance();

    // build AQL expression
    VersorgungsortAdminEntryContainment versorgungsortAdminEntryContainment =
        VersorgungsortAdminEntryContainment.getInstance();
    StandortClusterContainment standortClusterContainment =
        StandortClusterContainment.getInstance();
    versorgungsortAdminEntryContainment.setContains(standortClusterContainment);
    patientenaufenthaltCompositionContainment.setContains(versorgungsortAdminEntryContainment);

    Query.buildEntityQuery(patientenaufenthaltCompositionContainment);

    String aql = patientenaufenthaltCompositionContainment.buildAQL();

    assertNotNull(aql);
  }
}
