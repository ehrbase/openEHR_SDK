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
import org.ehrbase.openehr.sdk.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.testalltypesenv1composition.TestAllTypesEnV1Composition;
import org.ehrbase.openehr.sdk.serialisation.dto.RmToGeneratedDtoConverter;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Integration.class)
public class ArbitraryQueryFeederAuditIT extends CanonicalCompoAllTypeQueryIT {

    protected ArbitraryQuery arbitraryQuery;

    @Before
    public void setUp() throws IOException {
        ehrUUID = openEhrClient.ehrEndpoint().createEhr();
        compositionEndpoint = openEhrClient.compositionEndpoint(ehrUUID);

        aComposition = new CanonicalJson()
                .unmarshal(
                        IOUtils.toString(
                                CompositionTestDataCanonicalJson.FEEDER_AUDIT_DETAILS.getStream(),
                                StandardCharsets.UTF_8),
                        Composition.class);
        RmToGeneratedDtoConverter rmToGeneratedDtoConverter =
                new RmToGeneratedDtoConverter(new TestDataTemplateProvider());
        TestAllTypesEnV1Composition testAllTypesEnV1Composition =
                rmToGeneratedDtoConverter.toGeneratedDto(aComposition, TestAllTypesEnV1Composition.class);
        //        create the composition
        TestAllTypesEnV1Composition comp = compositionEndpoint.mergeCompositionEntity(testAllTypesEnV1Composition);
        compositionUUID = UUID.fromString(comp.getVersionUid().getObjectId().getValue());
        arbitraryQuery = new ArbitraryQuery(ehrUUID, openEhrClient);
    }

    @Test
    @Ignore("fix it with another CR")
    public void testArbitraryFeederAudit() throws IOException {
        String csvTestSet = dirPath + "/arbitrary/arbitrary_feeder_audit.csv";

        assertThat(arbitraryQuery.testItemPaths(dirPath + "/arbitrary", csvTestSet))
                .isTrue();
    }
}
