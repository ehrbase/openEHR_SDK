/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient.systematic.compositionquery.queries;

public class AqlExpressionBuilder {
    private final String rootPath;
    private final String attributePath;
    private String containment;

    public AqlExpressionBuilder(String rootPath, String attributePath) {
        this.rootPath = rootPath;
        this.attributePath = attributePath;
    }

    public AqlExpressionBuilder(String rootPath, String attributePath, String containment) {
        this.rootPath = rootPath;
        this.attributePath = attributePath;
        this.containment = containment;
    }

    public String composition() {
        String aqlSelect = rootPath + "/" + attributePath;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select ");
        stringBuilder.append(aqlSelect);
        stringBuilder.append(" from EHR e[ehr_id/value = $ehr_id]");
        stringBuilder.append(" contains ");
        stringBuilder.append(containment);
        stringBuilder.append(" WHERE c/uid/value = $comp_uuid");

        return stringBuilder.toString();
    }

    public String composition(String whereCondition) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(composition());
        stringBuilder.append(" AND " + whereCondition);
        return stringBuilder.toString();
    }

    public String ehrStatus() {
        String aqlSelect = rootPath + "/" + attributePath;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select ");
        stringBuilder.append(aqlSelect);
        stringBuilder.append(" from EHR e[ehr_id/value = $ehr_id]");

        return stringBuilder.toString();
    }

    public String ehrStatus(String whereCondition) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ehrStatus());
        stringBuilder.append(" WHERE " + whereCondition);
        return stringBuilder.toString();
    }
}
