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

package org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.queries;

import com.nedap.archie.rm.RMObject;
import org.ehrbase.client.aql.parameter.ParameterValue;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record1;
import org.ehrbase.client.exception.WrongStatusCodeException;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.CanonicalUtil;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.arbitrary.ArbitraryExpression;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.arbitrary.ArbitraryExpressionSettings;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.arbitrary.NumericExpression;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.arbitrary.NumericExpressionSettings;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.auto.AutoWhereCondition;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.simple.PathExpression;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.simple.SimplePathExpressionSettings;
import org.ehrbase.response.openehr.QueryResponseData;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public abstract class TestQueryEngine extends CanonicalUtil {

    private static final String FAIL_EXPECTED = "*FAIL*";

    private final UUID ehrUUID;
    protected UUID compositionUUID;
    protected OpenEhrClient openEhrClient;

    public TestQueryEngine(UUID ehrUUID, UUID compositionUUID, OpenEhrClient openEhrClient) {
        this.ehrUUID = ehrUUID;
        this.compositionUUID = compositionUUID;
        this.openEhrClient = openEhrClient;
    }

    public TestQueryEngine(UUID ehrUUID, OpenEhrClient openEhrClient) {
        this.ehrUUID = ehrUUID;
        this.openEhrClient = openEhrClient;
    }


    public boolean testItemPaths(String csvTestSet, String rootPath, String contains, RMObject referenceNode) throws IOException {
        return false;
    }


    protected void checkSimpleQuery(String csvPath, String rootPath, String contains, RMObject referenceNode) throws FileNotFoundException {
        SimplePathExpressionSettings simplePathExpressionSettings = new SimplePathExpressionSettings();
        CsvParser csvParser = new CsvParser(simplePathExpressionSettings.settings());
        csvParser.parse(new FileReader(csvPath));
        List<PathExpression> attributeDefinitions = simplePathExpressionSettings.getPathExpressionRow().getBeans();

        for (PathExpression pathExpression: attributeDefinitions) {
            if (pathExpression.getComment() == null) {
                String attributePath = pathExpression.getPath();
                QueryResponseData result = performQuery(rootPath, attributePath, contains);
                try {
                    List<Object> objectList = result.getRows().get(0);

                    assertThat(valueObject(objectList.get(0)))
                            .as(rootPath + "/" + attributePath)
                            .isEqualTo(attributeValueAt(referenceNode, attributePath));
                } catch (Exception e) {
                    fail(e.getMessage());
                }
            }
        }
    }

    protected void checkAutoWhereQuery(String csvPath, String rootPath, String contains, RMObject referenceNode) throws FileNotFoundException {
        SimplePathExpressionSettings simplePathExpressionSettings = new SimplePathExpressionSettings();
        CsvParser csvParser = new CsvParser(simplePathExpressionSettings.settings());
        csvParser.parse(new FileReader(csvPath));
        List<PathExpression> attributeDefinitions = simplePathExpressionSettings.getPathExpressionRow().getBeans();

        for (PathExpression pathExpression: attributeDefinitions) {
            if (pathExpression.getComment() == null) {  //conventionally, if params[1] exists, this means skip the test
                String attributePath = pathExpression.getPath();

                QueryResponseData result = performQueryWithWhere(rootPath, attributePath, contains, new AutoWhereCondition(rootPath, attributePath, referenceNode).condition());

                if (result.getRows().isEmpty())
                    fail(rootPath + "/" + attributePath + ": no result");

                List<Object> objectList = result.getRows().get(0);

                assertThat(valueObject(objectList.get(0)))
                        .as(rootPath + "/" + attributePath)
                        .isEqualTo(attributeValueAt(referenceNode, attributePath));
            }
        }
    }

    protected void checkAutoEhrStatusWhereQuery(String csvPath, String rootPath, RMObject referenceNode) throws FileNotFoundException {
        SimplePathExpressionSettings simplePathExpressionSettings = new SimplePathExpressionSettings();
        CsvParser csvParser = new CsvParser(simplePathExpressionSettings.settings());
        csvParser.parse(new FileReader(csvPath));
        List<PathExpression> attributeDefinitions = simplePathExpressionSettings.getPathExpressionRow().getBeans();

        for (PathExpression pathExpression: attributeDefinitions) {
            if (pathExpression.getComment() == null) {
                String attributePath = pathExpression.getPath();

                QueryResponseData result = performEhrStatusQueryWithAutoWhere(rootPath, attributePath, referenceNode);

                if (result.getRows().isEmpty())
                    fail(rootPath + "/" + attributePath + ": no result");

                List<Object> objectList = result.getRows().get(0);

                assertThat(valueObject(objectList.get(0)))
                        .as(rootPath + "/" + attributePath)
                        .isEqualTo(attributeValueAt(referenceNode, attributePath));
            }
        }
    }


    protected void checkArbitraryQuery(String dirPath, String csvPath) throws IOException {

        ArbitraryExpressionSettings arbitraryExpressionSettings = new ArbitraryExpressionSettings();
        CsvParser csvParser = new CsvParser(arbitraryExpressionSettings.settings());
        csvParser.parse(new FileReader(csvPath));
        List<ArbitraryExpression> attributeDefinitions = arbitraryExpressionSettings.getArbitraryExpressionRow().getBeans();

        for (ArbitraryExpression arbitraryExpression: attributeDefinitions) {
            if (arbitraryExpression.getOptionalComment() == null) {

                String aql = arbitraryExpression.getRightSideExpression();

                if (arbitraryExpression.getLeftSideExpressionPath() != null){
                    String leftSide = Files.readString(Paths.get(dirPath+"/"+arbitraryExpression.getLeftSideExpressionPath()));
                    aql = leftSide+" WHERE "+ aql;
                }

                boolean shouldFail = false;

                if (arbitraryExpression.getExpectedResult() != null)
                    shouldFail = arbitraryExpression.getExpectedResult().equals(FAIL_EXPECTED) ? true : false;

                QueryResponseData result = performAqlQuery(aql, shouldFail);

                if (shouldFail)
                    continue;

                if (result.getRows().isEmpty()) {
                    if (arbitraryExpression.getExpectedResult() == null)
                        continue;
                    else
                        fail(arbitraryExpression.getRightSideExpression() + ": no result");
                }

                //TODO: iterate on result
                List<Object> objectList = result.getRows().get(0);

                assertThat(valueObject(objectList.get(0)))
                        .as(arbitraryExpression.getRightSideExpression())
                        .isEqualTo(valueObject(arbitraryExpression.getExpectedResult()));
            }
        }

    }

    protected void checkNumericQuery(String dirPath, String csvPath) throws IOException {

        NumericExpressionSettings numericLongExpressionSettings = new NumericExpressionSettings();
        CsvParser csvParser = new CsvParser(numericLongExpressionSettings.settings());
        csvParser.parse(new FileReader(csvPath));
        List<NumericExpression> attributeDefinitions = numericLongExpressionSettings.getNumericExpressionRow().getBeans();

        for (NumericExpression numericExpression : attributeDefinitions) {
            if (numericExpression.getOptionalComment() == null) {

                String leftSide = "";
                if (numericExpression.getLeftSideExpression() != null)
                    leftSide = Files.readString(Paths.get(dirPath+"/"+ numericExpression.getLeftSideExpression()));

                String aql;
                if (!leftSide.isEmpty())
                    aql = leftSide+" WHERE "+ numericExpression.getRightSideExpression();
                else
                    aql = numericExpression.getRightSideExpression();

                boolean shouldFail = false;

                if (numericExpression.getExpectedResult() != null)
                    shouldFail = numericExpression.getExpectedResult().equals(-1L) ? true : false;

                QueryResponseData result = performAqlQuery(aql, shouldFail);

                if (shouldFail)
                    continue;

                if (result.getRows().isEmpty()) {
                    if (numericExpression.getExpectedResult() == null)
                        continue;
                    else
                        fail(numericExpression.getRightSideExpression() + ": no result");
                }

                Object expectedResult = numericExpression.getExpectedResult();
                try {
                    if (numericExpression.getJavaType() != null) {
                        Class clazz = Class.forName(numericExpression.getJavaType());
                        Method valueOf = clazz.getMethod("valueOf", String.class);
                        expectedResult = valueOf.invoke(null, numericExpression.getExpectedResult());
                    }
                }
                catch (Exception e){
                    throw new IllegalArgumentException("Invalid data type:"+numericExpression.getJavaType());
                }

                //TODO: iterate on result
                List<Object> objectList = result.getRows().get(0);

                assertThat(valueObject(objectList.get(0)))
                        .as(numericExpression.getRightSideExpression())
                        .isEqualTo(expectedResult);
            }
        }

    }

    public QueryResponseData performQuery(String rootPath, String attributePath, String containment){
        Query<Record1<Map>> query = Query.buildNativeQuery(
                new AqlExpressionBuilder(rootPath, attributePath, containment).composition()
                , Map.class
        );

        return execute(query, rootPath, attributePath);
    }

    private QueryResponseData performQueryWithWhere(String rootPath, String attributePath, String containment, String whereCondition){
        Query<Record1<Map>> query = Query.buildNativeQuery(
                new AqlExpressionBuilder(rootPath, attributePath, containment).composition(whereCondition)
                , Map.class
        );

        return execute(query, rootPath, attributePath);
    }



    private QueryResponseData performAqlQuery(String aql, boolean shoudFail){
        Query<Record1<Map>> query = Query.buildNativeQuery(
                aql
                , Map.class
        );
        return execute(query, aql, shoudFail);
    }

    private QueryResponseData performEhrStatusQueryWithAutoWhere(String rootPath, String attributePath, RMObject referenceNode){

        String whereCondition = new AutoWhereCondition(rootPath, attributePath, referenceNode).condition();

        Query<Record1<Map>> query = Query.buildNativeQuery(
                new AqlExpressionBuilder(rootPath, attributePath).ehrStatus(whereCondition)
                , Map.class
        );

        try {
            return openEhrClient.aqlEndpoint().executeRaw(query,
                    new ParameterValue("ehr_id", ehrUUID));
        }
        catch (WrongStatusCodeException e){
            fail("path:"+rootPath+"/"+attributePath+", error"+e.getMessage());
        }
        return null;
    }

    protected QueryResponseData execute(Query<Record1<Map>> query, String rootPath, String attributePath){
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

    protected QueryResponseData execute(Query<Record1<Map>> query, String aql, boolean shouldFail){
        try {
            if (compositionUUID != null) {
                return openEhrClient.aqlEndpoint().executeRaw(query,
                        new ParameterValue("ehr_id", ehrUUID),
                        new ParameterValue("comp_uuid", compositionUUID));
            }
            else {
                return openEhrClient.aqlEndpoint().executeRaw(query,
                        new ParameterValue("ehr_id", ehrUUID));
            }
        }
        catch (WrongStatusCodeException e){
            if (!shouldFail)
                fail("Query is not successful, path:"+aql+", error"+e.getMessage());
        }
        return null;
    }

}
