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

package org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.arbitrary;

import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.TestQueryEngine;

import java.io.IOException;
import java.util.UUID;

public class ArbitraryQuery extends TestQueryEngine {

    public ArbitraryQuery(UUID ehrUUID, OpenEhrClient openEhrClient) {
        super(ehrUUID, openEhrClient);
    }

    public boolean testItemPaths(String dirPath, String csvTestSet) throws IOException {
        checkArbitraryQuery(dirPath, csvTestSet);
        return true;
    }

    public void setCompositionUUID(UUID compositionUUID){
        this.compositionUUID = compositionUUID;
    }

}
