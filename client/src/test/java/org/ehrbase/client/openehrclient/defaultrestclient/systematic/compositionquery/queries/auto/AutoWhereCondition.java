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

import static org.ehrbase.client.openehrclient.defaultrestclient.systematic.CanonicalUtil.attributeValueAt;

import com.nedap.archie.rm.RMObject;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.SpecialCase;

public class AutoWhereCondition {

    private final String rootPath;
    private final String attributePath;
    private final RMObject referenceNode;

    public AutoWhereCondition(String rootPath, String attributePath, RMObject referenceNode) {
        this.rootPath = rootPath;
        this.attributePath = attributePath;
        this.referenceNode = referenceNode;
    }

    public String condition() {

        Object reference = attributeValueAt(referenceNode, attributePath);

        StringBuilder autoWhereCondition = new StringBuilder();
        autoWhereCondition.append(rootPath + "/" + attributePath);
        autoWhereCondition.append(" = ");
        Object filterObject = new SpecialCase().transform(reference);
        autoWhereCondition.append(filterObject instanceof String ? "'" + filterObject + "'" : filterObject);

        return autoWhereCondition.toString();
    }
}
