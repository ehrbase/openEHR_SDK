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
import org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.TestAllTypesEnV1Composition;
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.client.openehrclient.CompositionEndpoint;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClientTestHelper;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.CanonicalUtil;
import org.ehrbase.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.After;
import org.junit.BeforeClass;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.UUID;

public abstract class CanonicalCompoAllTypeQueryIT extends CanonicalUtil {
    protected static OpenEhrClient openEhrClient;
    protected static final String dirPath = "src/test/resources/testsets";
    protected CompositionEndpoint compositionEndpoint;
    protected Composition aComposition;

    protected UUID ehrUUID;
    protected UUID compositionUUID;

    private DvDateTime actualDvDateTime;

    @BeforeClass
    public static void before() throws URISyntaxException {
        openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    }

    public void setUp(CompositionTestDataCanonicalJson testComposition) throws IOException {
        //manual test use
//        ehrUUID = UUID.fromString("ecc0de4d-eb29-40c2-ad7a-e2ab8d66a9f8");
//        compositionUUID = UUID.fromString("a9c22c37-8002-4486-932a-f3e1729efe57");

        actualDvDateTime = new DvDateTime(OffsetDateTime.now());
 // normal test run
        ehrUUID = openEhrClient.ehrEndpoint().createEhr();
        compositionEndpoint = openEhrClient.compositionEndpoint(ehrUUID);

        if (testComposition != null) {
            aComposition = new CanonicalJson().unmarshal(IOUtils.toString(testComposition.getStream(), StandardCharsets.UTF_8), Composition.class);
            Flattener flattener = new Flattener(new TestDataTemplateProvider());
            TestAllTypesEnV1Composition testAllTypesEnV1Composition = flattener.flatten(aComposition, TestAllTypesEnV1Composition.class);
//        create the composition
            TestAllTypesEnV1Composition comp = compositionEndpoint.mergeCompositionEntity(testAllTypesEnV1Composition);
            compositionUUID = comp.getVersionUid().getUuid();
        }
    }

    @After
    public void tearDown(){
        //delete the created EHR and all compositions using the admin endpoint
        openEhrClient.adminEhrEndpoint().delete(ehrUUID);
    }

}
