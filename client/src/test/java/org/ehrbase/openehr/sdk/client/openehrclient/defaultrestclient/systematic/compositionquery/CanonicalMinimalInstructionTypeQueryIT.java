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

import com.nedap.archie.datetime.DateTimeParsers;
import com.nedap.archie.rm.composition.Composition;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.apache.commons.io.IOUtils;
import org.ehrbase.openehr.sdk.client.openehrclient.CompositionEndpoint;
import org.ehrbase.openehr.sdk.client.openehrclient.OpenEhrClient;
import org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient.DefaultRestClientTestHelper;
import org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient.systematic.CanonicalUtil;
import org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.arbitrary.ArbitraryQuery;
import org.ehrbase.openehr.sdk.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.minimalinstructionenv1composition.MinimalInstructionEnV1Composition;
import org.ehrbase.openehr.sdk.serialisation.dto.RmToGeneratedDtoConverter;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CanonicalMinimalInstructionTypeQueryIT extends CanonicalUtil {
    protected static OpenEhrClient openEhrClient;
    protected static final String dirPath = "src/test/resources/testsets";
    protected CompositionEndpoint compositionEndpoint;
    protected Composition aComposition;

    protected UUID ehrUUID;
    protected UUID compositionUUID;

    private ArbitraryQuery arbitraryQuery;
    private RmToGeneratedDtoConverter rmToGeneratedDtoConverter;

    @BeforeClass
    public static void before() throws URISyntaxException {
        openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    }

    @Before
    public void setUp() throws IOException {
        ehrUUID = openEhrClient.ehrEndpoint().createEhr();
        compositionEndpoint = openEhrClient.compositionEndpoint(ehrUUID);
        arbitraryQuery = new ArbitraryQuery(ehrUUID, openEhrClient);
        rmToGeneratedDtoConverter = new RmToGeneratedDtoConverter(new TestDataTemplateProvider());

        aComposition = new CanonicalJson()
                .unmarshal(
                        IOUtils.toString(
                                CompositionTestDataCanonicalJson.MINIMAL_INSTRUCTION.getStream(),
                                StandardCharsets.UTF_8),
                        Composition.class);
    }

    @After
    public void tearDown() {
        // delete the created EHR and all compositions using the admin endpoint
        openEhrClient.adminEhrEndpoint().delete(ehrUUID);
    }

    @Test
    public void testArbitrary1() throws IOException {
        String csvTestSet = dirPath + "/arbitrary/dv_duration_where_tests1.csv";

        MinimalInstructionEnV1Composition minimalInstructionEnV1Composition =
                rmToGeneratedDtoConverter.toGeneratedDto(aComposition, MinimalInstructionEnV1Composition.class);
        //        create the composition
        MinimalInstructionEnV1Composition comp =
                compositionEndpoint.mergeCompositionEntity(minimalInstructionEnV1Composition);
        compositionUUID = UUID.fromString(comp.getVersionUid().getObjectId().getValue());
        arbitraryQuery.setCompositionUUID(compositionUUID);

        assertThat(arbitraryQuery.testItemPaths(dirPath + "/arbitrary", csvTestSet))
                .isTrue();
    }

    @Test
    public void testArbitrary2() throws IOException {
        String csvTestSet = dirPath + "/arbitrary/dv_duration_where_tests2.csv";

        MinimalInstructionEnV1Composition minimalInstructionEnV1Composition =
                rmToGeneratedDtoConverter.toGeneratedDto(aComposition, MinimalInstructionEnV1Composition.class);

        minimalInstructionEnV1Composition
                .getMinimal()
                .get(0)
                .setDurationValue(DateTimeParsers.parseDurationValue("P65Y5M12D"));

        //        create the composition
        MinimalInstructionEnV1Composition comp =
                compositionEndpoint.mergeCompositionEntity(minimalInstructionEnV1Composition);
        compositionUUID = UUID.fromString(comp.getVersionUid().getObjectId().getValue());
        arbitraryQuery.setCompositionUUID(compositionUUID);

        assertThat(arbitraryQuery.testItemPaths(dirPath + "/arbitrary", csvTestSet))
                .isTrue();
    }

    @Test
    public void testArbitrary3() throws IOException {
        String csvTestSet = dirPath + "/arbitrary/dv_duration_where_tests3.csv";

        MinimalInstructionEnV1Composition minimalInstructionEnV1Composition =
                rmToGeneratedDtoConverter.toGeneratedDto(aComposition, MinimalInstructionEnV1Composition.class);

        minimalInstructionEnV1Composition
                .getMinimal()
                .get(0)
                .setDurationValue(DateTimeParsers.parseDurationValue("P101Y"));

        //        create the composition
        MinimalInstructionEnV1Composition comp =
                compositionEndpoint.mergeCompositionEntity(minimalInstructionEnV1Composition);
        compositionUUID = UUID.fromString(comp.getVersionUid().getObjectId().getValue());
        arbitraryQuery.setCompositionUUID(compositionUUID);

        assertThat(arbitraryQuery.testItemPaths(dirPath + "/arbitrary", csvTestSet))
                .isTrue();
    }

    /**
     * this test helps at understanding the duration conversions between Y, M and D.
     * @throws IOException
     */
    @Test
    public void testArbitrary4() throws IOException {
        String csvTestSet = dirPath + "/arbitrary/dv_duration_where_tests4.csv";

        MinimalInstructionEnV1Composition minimalInstructionEnV1Composition =
                rmToGeneratedDtoConverter.toGeneratedDto(aComposition, MinimalInstructionEnV1Composition.class);

        minimalInstructionEnV1Composition
                .getMinimal()
                .get(0)
                .setDurationValue(DateTimeParsers.parseDurationValue("P1Y"));

        //        create the composition
        MinimalInstructionEnV1Composition comp =
                compositionEndpoint.mergeCompositionEntity(minimalInstructionEnV1Composition);
        compositionUUID = UUID.fromString(comp.getVersionUid().getObjectId().getValue());
        arbitraryQuery.setCompositionUUID(compositionUUID);

        assertThat(arbitraryQuery.testItemPaths(dirPath + "/arbitrary", csvTestSet))
                .isTrue();
    }
}
