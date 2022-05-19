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

package org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery;

import com.nedap.archie.rm.RMObject;
import org.ehrbase.client.Integration;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.auto.AutoWhereQuery;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@Category(Integration.class)
public class AutoWhereIT extends CanonicalCompoAllTypeQueryIT {
    protected AutoWhereQuery autoWhereQuery;

    @Before
    public void setUp() throws IOException {
        super.setUp(CompositionTestDataCanonicalJson.ALL_TYPES_SYSTEMATIC_TESTS);
        autoWhereQuery = new AutoWhereQuery(ehrUUID, compositionUUID, openEhrClient);
    }



    @Test
    public void testActionAutoWhere() throws IOException {

        String rootPath = "a";
        RMObject referenceNode = (RMObject) aComposition
                .itemsAtPath("/content[openEHR-EHR-SECTION.test_all_types.v1]/items[at0001]/items[at0002]/items[openEHR-EHR-ACTION.test_all_types.v1]").get(0);
        String contains = "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                " contains section s[openEHR-EHR-SECTION.test_all_types.v1]" +
                " contains action a[openEHR-EHR-ACTION.test_all_types.v1]";
        String csvTestSet = dirPath+"/testActionWhere.csv";

        assertThat(autoWhereQuery.testItemPaths(csvTestSet, rootPath, contains, referenceNode)).isTrue();
    }

    @Test
    public void testCompositionAutoWhere() throws IOException {
        String rootPath = "c";
        RMObject referenceNode = aComposition;
        String contains = "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]";
        String csvTestSet = dirPath+"/testCompositionWhere.csv";

        assertThat(autoWhereQuery.testItemPaths(csvTestSet, rootPath, contains, referenceNode)).isTrue();
    }

    @Test
    public void testObservationAutoWhere() throws IOException {
        String rootPath = "o";
        RMObject referenceNode = (RMObject) aComposition.itemsAtPath("/content[openEHR-EHR-OBSERVATION.test_all_types.v1]").get(0);
        String contains = "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                " contains observation o[openEHR-EHR-OBSERVATION.test_all_types.v1]";
        String csvTestSet = dirPath+"/testObservationWhere.csv";

        assertThat(autoWhereQuery.testItemPaths(csvTestSet, rootPath, contains, referenceNode)).isTrue();
    }

    @Test
    public void testContextAutoWhere() throws IOException {

        String rootPath = "c/context";
        RMObject referenceNode = aComposition.getContext();
        String contains = "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]";
        String csvTestSet = dirPath+"/testContextAttributesWhere.csv";

        assertThat(autoWhereQuery.testItemPaths(csvTestSet, rootPath, contains, referenceNode)).isTrue();
    }

    @Test
    public void testEvaluationAutoWhere() throws IOException {

        String rootPath = "eval";
        RMObject referenceNode = (RMObject) aComposition.itemsAtPath("/content[openEHR-EHR-EVALUATION.test_all_types.v1]").get(0);
        String contains = "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                " contains evaluation eval[openEHR-EHR-EVALUATION.test_all_types.v1]";
        String csvTestSet = dirPath+"/testEvaluationWhere.csv";

        assertThat(autoWhereQuery.testItemPaths(csvTestSet, rootPath, contains, referenceNode)).isTrue();
    }

    @Test
    public void testInstructionAutoWhere() throws IOException {

        String rootPath = "i";
        RMObject referenceNode = (RMObject) aComposition
                .itemsAtPath("/content[openEHR-EHR-SECTION.test_all_types.v1]/items[at0001]/items[at0002]/items[openEHR-EHR-INSTRUCTION.test_all_types.v1]").get(0);
        String contains = "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                " contains section s[openEHR-EHR-SECTION.test_all_types.v1]" +
                " contains instruction i[openEHR-EHR-INSTRUCTION.test_all_types.v1]";
        String csvTestSet = dirPath+"/testInstructionWhere.csv";

        assertThat(autoWhereQuery.testItemPaths(csvTestSet, rootPath, contains, referenceNode)).isTrue();
    }

    @Test
    public void testSectionAutoWhere() throws IOException {

        String rootPath = "s";
        RMObject referenceNode = (RMObject) aComposition.itemsAtPath("/content[openEHR-EHR-SECTION.test_all_types.v1]").get(0);
        String contains = "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                " contains section s[openEHR-EHR-SECTION.test_all_types.v1]";
        String csvTestSet = dirPath+"/testSectionWhere.csv";

        assertThat(autoWhereQuery.testItemPaths(csvTestSet, rootPath, contains, referenceNode)).isTrue();
    }

}
