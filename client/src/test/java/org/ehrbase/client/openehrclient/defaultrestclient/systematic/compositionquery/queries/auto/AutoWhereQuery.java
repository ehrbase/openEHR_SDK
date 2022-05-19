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
package org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.auto;

import com.nedap.archie.rm.RMObject;
import java.io.IOException;
import java.util.UUID;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.TestQueryEngine;

public class AutoWhereQuery extends TestQueryEngine {

    public AutoWhereQuery(UUID ehrUUID, UUID compositionUUID, OpenEhrClient openEhrClient) {
        super(ehrUUID, compositionUUID, openEhrClient);
    }

    public boolean testItemPaths(String csvTestSet, String rootPath, String contains, RMObject referenceNode)
            throws IOException {
        checkAutoWhereQuery(csvTestSet, rootPath, contains, referenceNode);
        return true;
    }
}
