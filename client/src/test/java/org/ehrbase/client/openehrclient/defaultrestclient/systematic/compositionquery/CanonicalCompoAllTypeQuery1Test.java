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
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import org.apache.commons.io.IOUtils;
import org.ehrbase.client.Integration;
import org.ehrbase.client.aql.parameter.ParameterValue;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record1;
import org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.TestAllTypesEnV1Composition;
import org.ehrbase.client.exception.WrongStatusCodeException;
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.client.openehrclient.CompositionEndpoint;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClientTestHelper;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.CanonicalUtil;
import org.ehrbase.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.response.openehr.QueryResponseData;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParser;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParserSettings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@Category(Integration.class)
//@Ignore
public class CanonicalCompoAllTypeQuery1Test extends CanonicalUtil {
    private static OpenEhrClient openEhrClient;
    private static final String dirPath = "src/test/resources/testsets";
    private CompositionEndpoint compositionEndpoint;
    private Composition allTypesComposition;

    private UUID ehrUUID;
    private UUID compositionUUID;
    
    private DvDateTime actualDvDateTime;

    @BeforeClass
    public static void setup() throws URISyntaxException {
        openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    }

    @Before
    public void setUp() throws IOException {
        //manual test use
//        ehrUUID = UUID.fromString("d329cab4-df42-4d3c-8eed-5b6953c49eed");
//        compositionUUID = UUID.fromString("582358a6-afff-419f-bb2c-0e4c2853ad9d");

        actualDvDateTime = new DvDateTime(OffsetDateTime.now());
        allTypesComposition = new CanonicalJson().unmarshal(IOUtils.toString(CompositionTestDataCanonicalJson.ALL_TYPES_SYSTEMATIC_TESTS.getStream(), StandardCharsets.UTF_8), Composition.class);
// normal test run
        ehrUUID = openEhrClient.ehrEndpoint().createEhr();
        compositionEndpoint = openEhrClient.compositionEndpoint(ehrUUID);
        Flattener flattener = new Flattener(new TestDataTemplateProvider());
        TestAllTypesEnV1Composition testAllTypesEnV1Composition = flattener.flatten(allTypesComposition, TestAllTypesEnV1Composition.class);
//        create the composition
        TestAllTypesEnV1Composition comp = compositionEndpoint.mergeCompositionEntity(testAllTypesEnV1Composition);
        compositionUUID  = comp.getVersionUid().getUuid();

    }

    @Test
    public void testCompositionAttributeQuery() throws IOException {
        String rootPath = "c";
        RMObject referenceNode = allTypesComposition;

        //reads in the test set
        BufferedReader inputCSVSetReader = new BufferedReader(new FileReader("src/test/resources/testsets/testCompositionAttributeQuery.csv"));

        String csvParams = null;
        CsvParser csvParser = new CsvParser(new CsvParserSettings());

        while ((csvParams = inputCSVSetReader.readLine()) != null) {
            String[] params = csvParser.parseLine(csvParams);
            String attributePath = params[0];
            if (params.length == 1) { //conventionally, if params[1] exists, this means skip the test
                QueryResponseData result = performQuery(rootPath, attributePath, "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]");

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
        RMObject referenceNode = allTypesComposition.getContext();
        String contains = "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]";
        String csvTestSet = dirPath+"/testContextAttributesDrillDown.csv";
        
        assertThat(testItemPaths(csvTestSet, rootPath, contains, referenceNode)).isTrue();
    }

    @Test
    public void testObservationDrillDown() throws IOException {

        String rootPath = "o";
        RMObject referenceNode = (RMObject) allTypesComposition.itemsAtPath("/content[openEHR-EHR-OBSERVATION.test_all_types.v1]").get(0);
        String contains = "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                " contains observation o[openEHR-EHR-OBSERVATION.test_all_types.v1]";
        String csvTestSet = dirPath+"/testObservationDrillDown.csv";

        assertThat(testItemPaths(csvTestSet, rootPath, contains, referenceNode)).isTrue();
    }

    @Test
    public void testEvaluationDrillDown() throws IOException {

        String rootPath = "eval";
        RMObject referenceNode = (RMObject) allTypesComposition.itemsAtPath("/content[openEHR-EHR-EVALUATION.test_all_types.v1]").get(0);
        String contains = "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                " contains evaluation eval[openEHR-EHR-EVALUATION.test_all_types.v1]";
        String csvTestSet = dirPath+"/testEvaluationDrillDown.csv";

        assertThat(testItemPaths(csvTestSet, rootPath, contains, referenceNode)).isTrue();
    }

    @Test
    public void testSectionDrillDown() throws IOException {

        String rootPath = "s";
        RMObject referenceNode = (RMObject) allTypesComposition.itemsAtPath("/content[openEHR-EHR-SECTION.test_all_types.v1]").get(0);
        String contains = "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                " contains section s[openEHR-EHR-SECTION.test_all_types.v1]";
        String csvTestSet = dirPath+"/testSectionDrillDown.csv";

        assertThat(testItemPaths(csvTestSet, rootPath, contains, referenceNode)).isTrue();
    }

    @Test
    public void testInstructionDrillDown() throws IOException {

        String rootPath = "i";
        RMObject referenceNode = (RMObject) allTypesComposition
                .itemsAtPath("/content[openEHR-EHR-SECTION.test_all_types.v1]/items[at0001]/items[at0002]/items[openEHR-EHR-INSTRUCTION.test_all_types.v1]").get(0);
        String contains = "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                " contains section s[openEHR-EHR-SECTION.test_all_types.v1]" +
                " contains instruction i[openEHR-EHR-INSTRUCTION.test_all_types.v1]";
        String csvTestSet = dirPath+"/testInstructionDrillDown.csv";

        assertThat(testItemPaths(csvTestSet, rootPath, contains, referenceNode)).isTrue();
    }

    @Test
    public void testActionDrillDown() throws IOException {

        String rootPath = "a";
        RMObject referenceNode = (RMObject) allTypesComposition
                .itemsAtPath("/content[openEHR-EHR-SECTION.test_all_types.v1]/items[at0001]/items[at0002]/items[openEHR-EHR-ACTION.test_all_types.v1]").get(0);
        String contains = "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                " contains section s[openEHR-EHR-SECTION.test_all_types.v1]" +
                " contains action a[openEHR-EHR-ACTION.test_all_types.v1]";
        String csvTestSet = dirPath+"/testActionDrillDown.csv";

        assertThat(testItemPaths(csvTestSet, rootPath, contains, referenceNode)).isTrue();
    }

    @Test
    public void testAdminEntryDrillDown() throws IOException {

        String rootPath = "a";
        RMObject referenceNode = (RMObject) allTypesComposition
                .itemsAtPath("/content[openEHR-EHR-SECTION.test_all_types.v1]/items[at0001]/items[openEHR-EHR-ADMIN_ENTRY.test_all_types.v1]").get(0);
        String contains = "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                " contains section s[openEHR-EHR-SECTION.test_all_types.v1]" +
                " contains ADMIN_ENTRY a[openEHR-EHR-ADMIN_ENTRY.test_all_types.v1]";
        String csvTestSet = dirPath+"/testAdminEntryDrillDown.csv";

        assertThat(testItemPaths(csvTestSet, rootPath, contains, referenceNode)).isTrue();
    }

    private String buildAqlCompositionQuery(String rootPath, String attributePath, String containment){
        String aqlSelect = rootPath+"/"+attributePath;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select ");
        stringBuilder.append(aqlSelect);
        stringBuilder.append(" from EHR e[ehr_id/value = $ehr_id]");
        stringBuilder.append(" contains ");
        stringBuilder.append(containment);
        stringBuilder.append(" WHERE c/uid/value = $comp_uuid");

        return stringBuilder.toString();
    }

    private QueryResponseData performQuery(String rootPath, String attributePath, String containment){
        Query<Record1<Map>> query = Query.buildNativeQuery(
                buildAqlCompositionQuery(rootPath, attributePath, containment)
                , Map.class
        );
        try {
            return openEhrClient.aqlEndpoint().executeRaw(query,
                    new ParameterValue("ehr_id", ehrUUID),
                    new ParameterValue("comp_uuid", compositionUUID));
        }
        catch (WrongStatusCodeException e){
            fail("path:"+rootPath+"/"+attributePath+", error"+e.getMessage());
        }
        return null;
    }

    private boolean testItemPaths(String csvTestSet, String rootPath, String contains, RMObject referenceNode) throws IOException {
        BufferedReader inputCSVSetReader = new BufferedReader(new FileReader(csvTestSet));

        String csvParams = null;
        CsvParser csvParser = new CsvParser(new CsvParserSettings());

        while ((csvParams = inputCSVSetReader.readLine()) != null) {
            String[] params = csvParser.parseLine(csvParams);
            String attributePath = params[0];
            if (params.length == 1 || (params.length > 1 && params[1] == null)) { //conventionally, if params[1] exists, this means skip the test
                QueryResponseData result = performQuery(rootPath, attributePath, contains);

                List<Object> objectList = result.getRows().get(0);

                assertThat(valueObject(objectList.get(0)))
                        .as(rootPath + "/" + attributePath)
                        .isEqualTo(attributeValueAt(referenceNode, attributePath));
            }
        }

        return true;
    }
}
