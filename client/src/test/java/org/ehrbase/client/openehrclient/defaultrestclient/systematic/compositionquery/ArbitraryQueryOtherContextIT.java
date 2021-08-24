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
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.VirologischerBefundComposition;
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.arbitrary.ArbitraryQuery;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.simple.SimpleSelectQuery;
import org.ehrbase.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@Category(Integration.class)
public class ArbitraryQueryOtherContextIT extends CanonicalCompoAllTypeQueryIT {

    protected ArbitraryQuery arbitraryQuery;
    protected SimpleSelectQuery simpleSelectQueryEngine;

    @Before
    public void setUp() throws IOException {
        // normal test run
        ehrUUID = openEhrClient.ehrEndpoint().createEhr();
        compositionEndpoint = openEhrClient.compositionEndpoint(ehrUUID);

        aComposition = new CanonicalJson().unmarshal(IOUtils.toString(CompositionTestDataCanonicalJson.VIROLOGY_FINDING_WITH_SPECIMEN_NO_UPDATE.getStream(), StandardCharsets.UTF_8), Composition.class);
        Flattener flattener = new Flattener(new TestDataTemplateProvider());
        VirologischerBefundComposition virologischerBefundComposition = flattener.flatten(aComposition, VirologischerBefundComposition.class);
//        create the composition
        VirologischerBefundComposition comp = compositionEndpoint.mergeCompositionEntity(virologischerBefundComposition);
        compositionUUID = comp.getVersionUid().getUuid();


        arbitraryQuery = new ArbitraryQuery(ehrUUID, openEhrClient);
        simpleSelectQueryEngine = new SimpleSelectQuery(ehrUUID, compositionUUID, openEhrClient);
    }

    @After
    public void tearDown(){
        //delete the created EHR using the admin endpoint
        openEhrClient.adminEhrEndpoint().delete(ehrUUID);
    }

    @Test
    public void testArbitraryOtherContext() throws IOException {
        String csvTestSet = dirPath+"/arbitrary/arbitrary_other_context.csv";

        assertThat(arbitraryQuery.testItemPaths(dirPath+"/arbitrary", csvTestSet)).isTrue();
    }
}
