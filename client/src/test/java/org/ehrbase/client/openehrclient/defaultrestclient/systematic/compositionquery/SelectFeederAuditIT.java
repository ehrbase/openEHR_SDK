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
import org.junit.Ignore;
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

public class SelectFeederAuditIT extends CanonicalCompoAllTypeQueryIT {

    protected SimpleSelectQuery simpleSelectQueryEngine;

    @Before
    public void setUp() throws IOException {
        super.setUp(CompositionTestDataCanonicalJson.FEEDER_AUDIT_DETAILS);
        simpleSelectQueryEngine = new SimpleSelectQuery(ehrUUID, compositionUUID, openEhrClient);
    }

    @Test
    public void testCompositionFeederAuditSelect() throws IOException {
        assertThat(simpleSelectQueryEngine.testItemPaths(
                dirPath + "/testCompositionFeederAuditQuery.csv",
                "c/feeder_audit",
                "composition c",
                aComposition.getFeederAudit()
                )).isTrue();

        assertThat(simpleSelectQueryEngine.testItemPaths(
                dirPath + "/testCompositionFeederAuditOtherDetailsQuery.csv",
                "c/feeder_audit/feeder_system_audit/other_details[openEHR-EHR-ITEM_TREE.generic.v1]",
                "composition c",
                aComposition.getFeederAudit().getFeederSystemAudit().getOtherDetails()
                )).isTrue();

        assertThat(simpleSelectQueryEngine.testItemPaths(
                dirPath + "/testCompositionFeederAuditQuery.csv",
                "o/feeder_audit",
                "composition c contains OBSERVATION o[openEHR-EHR-OBSERVATION.test_all_types.v1]",
                (RMObject) aComposition.itemsAtPath("/content[openEHR-EHR-OBSERVATION.test_all_types.v1]/feeder_audit").get(0)
        )).isTrue();

        assertThat(simpleSelectQueryEngine.testItemPaths(
                dirPath + "/testCompositionFeederAuditOtherDetailsQuery.csv",
                "o/feeder_audit/feeder_system_audit/other_details[openEHR-EHR-ITEM_TREE.generic.v1]",
                "composition c contains OBSERVATION o[openEHR-EHR-OBSERVATION.test_all_types.v1]",
                (RMObject) aComposition.itemsAtPath("/content[openEHR-EHR-OBSERVATION.test_all_types.v1]/feeder_audit/feeder_system_audit/other_details[openEHR-EHR-ITEM_TREE.generic.v1]").get(0)
        )).isTrue();
    }
}