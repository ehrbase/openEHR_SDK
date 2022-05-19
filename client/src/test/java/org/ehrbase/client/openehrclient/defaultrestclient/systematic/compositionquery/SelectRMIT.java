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
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.simple.SimpleSelectQuery;
import org.ehrbase.response.openehr.QueryResponseData;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParser;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParserSettings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Category(Integration.class)
public class SelectRMIT extends CanonicalCompoAllTypeQueryIT {

    protected SimpleSelectQuery simpleSelectQueryEngine;

    @Before
    public void setUp() throws IOException {
        super.setUp(CompositionTestDataCanonicalJson.ALL_TYPES_SYSTEMATIC_TESTS);
        simpleSelectQueryEngine = new SimpleSelectQuery(ehrUUID, compositionUUID, openEhrClient);
    }

    @Test
    public void testCompositionAttributeQuery() throws IOException {
        String rootPath = "c";
        RMObject referenceNode = aComposition;

        //reads in the test set
        BufferedReader inputCSVSetReader = new BufferedReader(new FileReader("src/test/resources/testsets/testCompositionAttributeQuery.csv"));

        String csvParams;
        CsvParser csvParser = new CsvParser(new CsvParserSettings());

        while ((csvParams = inputCSVSetReader.readLine()) != null) {
            String[] params = csvParser.parseLine(csvParams);
            String attributePath = params[0];
            if (params.length == 1) { //conventionally, if params[1] exists, this means skip the test
                QueryResponseData result = simpleSelectQueryEngine.performQuery(rootPath, attributePath, "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]");

                List<Object> objectList = result.getRows().get(0);


                //special case, c/uid is actually completed after db insertion, hence cannot be compared with the initial
                //null value
                if (attributePath.contains("uid")) {
                    assertThat(objectList.get(0)).isNotNull();
                } else
                    assertThat(valueObject(objectList.get(0)))
                            .as(rootPath + "/" + attributePath)
                            .isEqualTo(attributeValueAt(referenceNode, attributePath));
            }
        }
    }

    @Test
    public void testContextAttributesDrillDown() throws IOException {

        String rootPath = "c/context";
        RMObject referenceNode = aComposition.getContext();
        String contains = "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]";
        String csvTestSet = dirPath+"/testContextAttributesDrillDown.csv";

        assertThat(simpleSelectQueryEngine.testItemPaths(csvTestSet, rootPath, contains, referenceNode)).isTrue();
    }

    @Test
    public void testObservationDrillDown() throws IOException {

        String rootPath = "o";
        RMObject referenceNode = (RMObject) aComposition.itemsAtPath("/content[openEHR-EHR-OBSERVATION.test_all_types.v1]").get(0);
        String contains = "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                " contains observation o[openEHR-EHR-OBSERVATION.test_all_types.v1]";
        String csvTestSet = dirPath+"/testObservationDrillDown.csv";

        assertThat(simpleSelectQueryEngine.testItemPaths(csvTestSet, rootPath, contains, referenceNode)).isTrue();
    }

    @Test
    public void testEvaluationDrillDown() throws IOException {

        String rootPath = "eval";
        RMObject referenceNode = (RMObject) aComposition.itemsAtPath("/content[openEHR-EHR-EVALUATION.test_all_types.v1]").get(0);
        String contains = "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                " contains evaluation eval[openEHR-EHR-EVALUATION.test_all_types.v1]";
        String csvTestSet = dirPath+"/testEvaluationDrillDown.csv";

        assertThat(simpleSelectQueryEngine.testItemPaths(csvTestSet, rootPath, contains, referenceNode)).isTrue();
    }

    @Test
    public void testSectionDrillDown() throws IOException {

        String rootPath = "s";
        RMObject referenceNode = (RMObject) aComposition.itemsAtPath("/content[openEHR-EHR-SECTION.test_all_types.v1]").get(0);
        String contains = "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                " contains section s[openEHR-EHR-SECTION.test_all_types.v1]";
        String csvTestSet = dirPath+"/testSectionDrillDown.csv";

        assertThat(simpleSelectQueryEngine.testItemPaths(csvTestSet, rootPath, contains, referenceNode)).isTrue();
    }

    @Test
    public void testInstructionDrillDown() throws IOException {

        String rootPath = "i";
        RMObject referenceNode = (RMObject) aComposition
                .itemsAtPath("/content[openEHR-EHR-SECTION.test_all_types.v1]/items[at0001]/items[at0002]/items[openEHR-EHR-INSTRUCTION.test_all_types.v1]").get(0);
        String contains = "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                " contains section s[openEHR-EHR-SECTION.test_all_types.v1]" +
                " contains instruction i[openEHR-EHR-INSTRUCTION.test_all_types.v1]";
        String csvTestSet = dirPath+"/testInstructionDrillDown.csv";

        assertThat(simpleSelectQueryEngine.testItemPaths(csvTestSet, rootPath, contains, referenceNode)).isTrue();
    }

    @Test
    public void testActionDrillDown() throws IOException {

        String rootPath = "a";
        RMObject referenceNode = (RMObject) aComposition
                .itemsAtPath("/content[openEHR-EHR-SECTION.test_all_types.v1]/items[at0001]/items[at0002]/items[openEHR-EHR-ACTION.test_all_types.v1]").get(0);
        String contains = "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                " contains section s[openEHR-EHR-SECTION.test_all_types.v1]" +
                " contains action a[openEHR-EHR-ACTION.test_all_types.v1]";
        String csvTestSet = dirPath+"/testActionDrillDown.csv";

        assertThat(simpleSelectQueryEngine.testItemPaths(csvTestSet, rootPath, contains, referenceNode)).isTrue();
    }

    @Test
    public void testAdminEntryDrillDown() throws IOException {

        String rootPath = "a";
        RMObject referenceNode = (RMObject) aComposition
                .itemsAtPath("/content[openEHR-EHR-SECTION.test_all_types.v1]/items[at0001]/items[openEHR-EHR-ADMIN_ENTRY.test_all_types.v1]").get(0);
        String contains = "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                " contains section s[openEHR-EHR-SECTION.test_all_types.v1]" +
                " contains ADMIN_ENTRY a[openEHR-EHR-ADMIN_ENTRY.test_all_types.v1]";
        String csvTestSet = dirPath+"/testAdminEntryDrillDown.csv";

        assertThat(simpleSelectQueryEngine.testItemPaths(csvTestSet, rootPath, contains, referenceNode)).isTrue();
    }

}
