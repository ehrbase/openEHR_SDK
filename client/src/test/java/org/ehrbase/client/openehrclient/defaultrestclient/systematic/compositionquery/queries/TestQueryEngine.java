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
import org.ehrbase.response.openehr.QueryResponseData;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParser;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParserSettings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public abstract class TestQueryEngine extends CanonicalUtil {

    private final UUID ehrUUID;
    private final UUID compositionUUID;
    private final OpenEhrClient openEhrClient;

    protected final CsvParser csvParser = new CsvParser(new CsvParserSettings());

    public TestQueryEngine(UUID ehrUUID, UUID compositionUUID, OpenEhrClient openEhrClient) {
        this.ehrUUID = ehrUUID;
        this.compositionUUID = compositionUUID;
        this.openEhrClient = openEhrClient;
    }


    public boolean testItemPaths(String csvTestSet, String rootPath, String contains, RMObject referenceNode) throws IOException {
        return false;
    }


    protected void checkSimpleQuery(String csvParams, String rootPath, String contains, RMObject referenceNode){
        String[] params = csvParser.parseLine(csvParams);
        String attributePath = params[0];

        if (params.length == 1 || (params.length > 1 && params[1] == null)) { //conventionally, if params[1] exists, this means skip the test

            QueryResponseData result = performQuery(rootPath, attributePath, contains);

            try {
                List<Object> objectList = result.getRows().get(0);

                assertThat(valueObject(objectList.get(0)))
                        .as(rootPath + "/" + attributePath)
                        .isEqualTo(attributeValueAt(referenceNode, attributePath));
            } catch (Exception e){
                fail(e.getMessage());
            }
        }
    }

    public QueryResponseData performQuery(String rootPath, String attributePath, String containment){
        Query<Record1<Map>> query = Query.buildNativeQuery(
                buildAqlCompositionQuery(rootPath, attributePath, containment)
                , Map.class
        );

        return execute(query, rootPath, attributePath);
    }


    protected String buildAqlCompositionQuery(String rootPath, String attributePath, String containment){
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

    private String buildAqlCompositionQueryWithWhere(String rootPath, String attributePath, String containment, String whereCondition){
        String aqlSelect = rootPath+"/"+attributePath;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select ");
        stringBuilder.append(aqlSelect);
        stringBuilder.append(" from EHR e[ehr_id/value = $ehr_id]");
        stringBuilder.append(" contains ");
        stringBuilder.append(containment);
        stringBuilder.append(" WHERE c/uid/value = $comp_uuid");
        stringBuilder.append(" AND "+whereCondition);

        return stringBuilder.toString();
    }

    /**
     * automatically build a where clause using the reference node value item for equality condition
     * @param rootPath
     * @param attributePath
     * @param containment
     * @return
     */
    private QueryResponseData performAutoWhereQuery(String rootPath, String attributePath, String containment, RMObject referenceNode){
        Object reference = attributeValueAt(referenceNode, attributePath);

        StringBuilder autoWhereCondition = new StringBuilder();
        autoWhereCondition.append(rootPath+"/"+attributePath);
        autoWhereCondition.append(" = ");
        autoWhereCondition.append(reference instanceof String ? "'"+reference+"'" : reference);

        return performQueryWithWhere(rootPath, attributePath, containment, autoWhereCondition.toString());
    }

    private QueryResponseData performQueryWithWhere(String rootPath, String attributePath, String containment, String whereCondition){
        Query<Record1<Map>> query = Query.buildNativeQuery(
                buildAqlCompositionQueryWithWhere(rootPath, attributePath, containment, whereCondition)
                , Map.class
        );

        return execute(query, rootPath, attributePath);
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

    protected void checkAutoWhereQuery(String csvParams, String rootPath, String contains, RMObject referenceNode){
        String[] params = csvParser.parseLine(csvParams);
        String attributePath = params[0];

        if (params.length == 1 || (params.length > 1 && params[1] == null)) { //conventionally, if params[1] exists, this means skip the test

            QueryResponseData result = performAutoWhereQuery(rootPath, attributePath, contains, referenceNode);

            List<Object> objectList = result.getRows().get(0);

            assertThat(valueObject(objectList.get(0)))
                    .as(rootPath + "/" + attributePath)
                    .isEqualTo(attributeValueAt(referenceNode, attributePath));
        }
    }
}
