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
import org.apache.commons.io.IOUtils;
import org.ehrbase.client.Integration;
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

import static org.assertj.core.api.Assertions.assertThat;

@Category(Integration.class)
public class ArbitraryQueryFeederAuditIT extends CanonicalCompoAllTypeQueryIT {

    protected ArbitraryQuery arbitraryQuery;

    @Before
    public void setUp() throws IOException {
        ehrUUID = openEhrClient.ehrEndpoint().createEhr();
        compositionEndpoint = openEhrClient.compositionEndpoint(ehrUUID);

        aComposition = new CanonicalJson().unmarshal(IOUtils.toString(CompositionTestDataCanonicalJson.FEEDER_AUDIT_DETAILS.getStream(), StandardCharsets.UTF_8), Composition.class);
        Flattener flattener = new Flattener(new TestDataTemplateProvider());
        TestAllTypesEnV1Composition testAllTypesEnV1Composition = flattener.flatten(aComposition, TestAllTypesEnV1Composition.class);
//        create the composition
        TestAllTypesEnV1Composition comp = compositionEndpoint.mergeCompositionEntity(testAllTypesEnV1Composition);
        compositionUUID = comp.getVersionUid().getUuid();
        arbitraryQuery = new ArbitraryQuery(ehrUUID, openEhrClient);
    }

    @Test
    @Ignore("fix it with another CR")
    public void testArbitraryFeederAudit() throws IOException {
        String csvTestSet = dirPath+"/arbitrary/arbitrary_feeder_audit.csv";

        assertThat(arbitraryQuery.testItemPaths(dirPath+"/arbitrary", csvTestSet)).isTrue();
    }
}
