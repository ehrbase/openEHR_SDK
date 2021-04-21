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

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import org.apache.commons.io.IOUtils;
import org.ehrbase.client.Integration;
import org.ehrbase.client.classgenerator.examples.laborbefundcomposition.LaborbefundComposition;
import org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.TestAllTypesEnV1Composition;
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.arbitrary.ArbitraryQuery;
import org.ehrbase.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Category(Integration.class)
public class FeederAuditQueryIT extends CanonicalCompoAllTypeQueryIT {

    protected ArbitraryQuery arbitraryQuery;



    @Before
    public void setUp() throws IOException {
        super.setUp(CompositionTestDataCanonicalJson.FEEDER_AUDIT_DETAILS);
        arbitraryQuery = new ArbitraryQuery(ehrUUID, openEhrClient);
    }

    @Test
    public void testArbitrary1() throws IOException {
        String csvTestSet = dirPath+"/arbitrary/feeder_audit_tests.csv";

        assertThat(arbitraryQuery.testItemPaths(dirPath+"/arbitrary", csvTestSet)).isTrue();
    }

}
