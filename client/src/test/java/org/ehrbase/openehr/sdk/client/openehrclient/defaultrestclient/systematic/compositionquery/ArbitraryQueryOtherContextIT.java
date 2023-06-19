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
package org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient.systematic.compositionquery;

import static org.assertj.core.api.Assertions.assertThat;

import com.nedap.archie.rm.composition.Composition;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.apache.commons.io.IOUtils;
import org.ehrbase.openehr.sdk.client.Integration;
import org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.arbitrary.ArbitraryQuery;
import org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.simple.SimpleSelectQuery;
import org.ehrbase.openehr.sdk.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.virologischerbefundcomposition.VirologischerBefundComposition;
import org.ehrbase.openehr.sdk.serialisation.dto.RmToGeneratedDtoConverter;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Integration.class)
public class ArbitraryQueryOtherContextIT extends CanonicalCompoAllTypeQueryIT {

    protected ArbitraryQuery arbitraryQuery;
    protected SimpleSelectQuery simpleSelectQueryEngine;

    @Before
    public void setUp() throws IOException {
        // normal test run
        ehrUUID = openEhrClient.ehrEndpoint().createEhr();
        compositionEndpoint = openEhrClient.compositionEndpoint(ehrUUID);

        aComposition = new CanonicalJson()
                .unmarshal(
                        IOUtils.toString(
                                CompositionTestDataCanonicalJson.VIROLOGY_FINDING_WITH_SPECIMEN_NO_UPDATE.getStream(),
                                StandardCharsets.UTF_8),
                        Composition.class);
        RmToGeneratedDtoConverter rmToGeneratedDtoConverter =
                new RmToGeneratedDtoConverter(new TestDataTemplateProvider());
        VirologischerBefundComposition virologischerBefundComposition =
                rmToGeneratedDtoConverter.toGeneratedDto(aComposition, VirologischerBefundComposition.class);
        //        create the composition
        VirologischerBefundComposition comp =
                compositionEndpoint.mergeCompositionEntity(virologischerBefundComposition);
        compositionUUID = UUID.fromString(comp.getVersionUid().getObjectId().getValue());

        arbitraryQuery = new ArbitraryQuery(ehrUUID, openEhrClient);
        simpleSelectQueryEngine = new SimpleSelectQuery(ehrUUID, compositionUUID, openEhrClient);
    }

    @After
    public void tearDown() {
        // delete the created EHR using the admin endpoint
        openEhrClient.adminEhrEndpoint().delete(ehrUUID);
    }

    @Test
    public void testArbitraryOtherContext() throws IOException {
        String csvTestSet = dirPath + "/arbitrary/arbitrary_other_context.csv";

        assertThat(arbitraryQuery.testItemPaths(dirPath + "/arbitrary", csvTestSet))
                .isTrue();
    }
}
