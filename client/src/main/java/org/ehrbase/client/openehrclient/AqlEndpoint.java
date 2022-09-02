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
package org.ehrbase.client.openehrclient;

import java.util.List;
import org.ehrbase.client.aql.parameter.ParameterValue;
import org.ehrbase.client.aql.parameter.StoredQueryParameter;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record;
import org.ehrbase.response.openehr.QueryResponseData;
import org.ehrbase.response.openehr.StoredQueryResponseData;

public interface AqlEndpoint {

    <T extends Record> List<T> execute(Query<T> query, ParameterValue... parameterValues);

    QueryResponseData executeRaw(Query query, ParameterValue... parameterValues);

    QueryResponseData executeStoredQuery(StoredQueryParameter queryParameter);

    StoredQueryResponseData getStoredAqlQuery(StoredQueryParameter queryParameter);

    void storeAqlQuery(Query query, StoredQueryParameter queryParameter);
}
