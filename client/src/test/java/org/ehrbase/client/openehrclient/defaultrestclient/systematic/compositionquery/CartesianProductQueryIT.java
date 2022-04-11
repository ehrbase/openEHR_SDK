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
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record3;
import org.ehrbase.client.classgenerator.examples.minimalaction3env1composition.MinimalAction3EnV1Composition;
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.client.openehrclient.CompositionEndpoint;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClientTestHelper;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.CanonicalUtil;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.arbitrary.ArbitraryQuery;
import org.ehrbase.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CartesianProductQueryIT extends CanonicalUtil {
    protected static OpenEhrClient openEhrClient;
    protected static final String dirPath = "src/test/resources/testsets";
    protected CompositionEndpoint compositionEndpoint;
    protected Composition aComposition;

    protected UUID ehrUUID;
    protected UUID compositionUUID;

    private ArbitraryQuery arbitraryQuery;
    private  Flattener flattener;

    @BeforeClass
    public static void before() throws URISyntaxException {
        openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    }

    @Before
    public void setUp() throws IOException {
        ehrUUID = openEhrClient.ehrEndpoint().createEhr();
        compositionEndpoint = openEhrClient.compositionEndpoint(ehrUUID);
        arbitraryQuery = new ArbitraryQuery(ehrUUID, openEhrClient);
        flattener = new Flattener(new TestDataTemplateProvider());
    }

    @After
    public void tearDown(){
        //delete the created EHR and all compositions using the admin endpoint
        openEhrClient.adminEhrEndpoint().delete(ehrUUID);
    }

    @Test
    public void testCartesianWithOptionalAttribute() throws IOException {

        Composition compoWithAttribute = new CanonicalJson().unmarshal(IOUtils.toString(CompositionTestDataCanonicalJson.MINIMAL_WITH_OPTIONAL_ATTRIBUTE.getStream(), StandardCharsets.UTF_8), Composition.class);
        Composition compoWithoutAttribute = new CanonicalJson().unmarshal(IOUtils.toString(CompositionTestDataCanonicalJson.MINIMAL_WITHOUT_OPTIONAL_ATTRIBUTE.getStream(), StandardCharsets.UTF_8), Composition.class);

        MinimalAction3EnV1Composition minimalAction3EnV1CompositionWithAttribute = flattener.flatten(compoWithAttribute, MinimalAction3EnV1Composition.class);
        MinimalAction3EnV1Composition minimalAction3EnV1CompositionWithoutAttribute = flattener.flatten(compoWithoutAttribute, MinimalAction3EnV1Composition.class);

        //        create the compositions
        compositionEndpoint.mergeCompositionEntity(minimalAction3EnV1CompositionWithAttribute);
        compositionEndpoint.mergeCompositionEntity(minimalAction3EnV1CompositionWithoutAttribute);

        Query<Record3<String, String, Integer>> query = Query.buildNativeQuery(
                "Select" +
                        " a/uid/value as composition_uid," +
                        " a0/description[at0001]/items[at0002]/value/alternate_text as alternate_text," +
                        " a0/description[at0001]/items[at0002]/value/size as size," +
                        " a0/language/code_string as language" +
                        " from EHR e" +
                        " contains COMPOSITION a" +
                        " contains ACTION a0[openEHR-EHR-ACTION.minimal.v1]"
                , String.class, String.class, Integer.class
        );

        List<Record3<String, String, Integer>> result = openEhrClient.aqlEndpoint().execute(query);

        assertThat(result.size()).as("Should have 2 rows resulting from cartesian product").isEqualTo(2);
    }
}
