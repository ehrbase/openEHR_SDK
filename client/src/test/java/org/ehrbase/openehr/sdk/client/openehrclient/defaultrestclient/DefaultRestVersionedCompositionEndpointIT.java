/*
 * Copyright (c) 2021 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient;

import com.nedap.archie.rm.changecontrol.OriginalVersion;
import com.nedap.archie.rm.ehr.VersionedComposition;
import com.nedap.archie.rm.generic.RevisionHistoryItem;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.ehrbase.openehr.sdk.client.Integration;
import org.ehrbase.openehr.sdk.client.openehrclient.OpenEhrClient;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.TestData;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.alternativeeventscomposition.AlternativeEventsComposition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasebloodpressuresimpledev0composition.EhrbaseBloodPressureSimpleDeV0Composition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasemultioccurrencedev1composition.EhrbaseMultiOccurrenceDeV1Composition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.episodeofcarecomposition.EpisodeOfCareComposition;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Integration.class)
public class DefaultRestVersionedCompositionEndpointIT {

    private static OpenEhrClient openEhrClient;
    private UUID ehrId;

    @BeforeClass
    public static void setup() throws URISyntaxException {
        openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    }

    @After
    public void tearDown() {
        // delete the created EHR using the admin endpoint
        openEhrClient.adminEhrEndpoint().delete(ehrId);
    }

    @Test
    public void testFindValid() {
        ehrId = openEhrClient.ehrEndpoint().createEhr();
        EhrbaseBloodPressureSimpleDeV0Composition composition = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        openEhrClient.compositionEndpoint(ehrId).mergeCompositionEntity(composition);

        Optional<VersionedComposition> versionedComposition = openEhrClient
                .versionedCompositionEndpoint(ehrId)
                .find(UUID.fromString(composition.getVersionUid().getObjectId().getValue()));

        Assert.assertTrue(versionedComposition.isPresent());
        Assert.assertEquals(
                composition.getVersionUid().getObjectId().getValue(),
                versionedComposition.get().getUid().getValue());
        Assert.assertEquals(
                ehrId.toString(),
                versionedComposition.get().getOwnerId().getId().getValue());
    }

    @Test
    public void testFindWrongId() {
        ehrId = openEhrClient.ehrEndpoint().createEhr();

        Optional<VersionedComposition> versionedComposition =
                openEhrClient.versionedCompositionEndpoint(ehrId).find(UUID.randomUUID());

        Assert.assertTrue(versionedComposition.isEmpty());
    }

    @Test
    public void testFindRevisionHistoryValid() {
        ehrId = openEhrClient.ehrEndpoint().createEhr();
        EhrbaseMultiOccurrenceDeV1Composition composition = TestData.buildEhrbaseMultiOccurrenceDeV1();

        ObjectVersionId v1 = openEhrClient
                .compositionEndpoint(ehrId)
                .mergeCompositionEntity(composition)
                .getVersionUid();
        ObjectVersionId v2 = openEhrClient
                .compositionEndpoint(ehrId)
                .mergeCompositionEntity(composition)
                .getVersionUid();
        ObjectVersionId v3 = openEhrClient
                .compositionEndpoint(ehrId)
                .mergeCompositionEntity(composition)
                .getVersionUid();

        List<RevisionHistoryItem> revisionHistory = openEhrClient
                .versionedCompositionEndpoint(ehrId)
                .findRevisionHistory(UUID.fromString(
                        composition.getVersionUid().getObjectId().getValue()));
        Assert.assertEquals(3, revisionHistory.size());
        Assert.assertEquals(v1.toString(), revisionHistory.get(0).getVersionId().getValue());
        Assert.assertEquals(v2.toString(), revisionHistory.get(1).getVersionId().getValue());
        Assert.assertEquals(v3.toString(), revisionHistory.get(2).getVersionId().getValue());
    }

    @Test
    public void testFindRevisionHistoryWrongId() {
        ehrId = openEhrClient.ehrEndpoint().createEhr();

        List<RevisionHistoryItem> revisionHistory =
                openEhrClient.versionedCompositionEndpoint(ehrId).findRevisionHistory(UUID.randomUUID());

        Assert.assertTrue(revisionHistory.isEmpty());
    }

    @Test
    public void testFindVersionById() {
        ehrId = openEhrClient.ehrEndpoint().createEhr();
        EpisodeOfCareComposition composition = TestData.buildEpisodeOfCareComposition();
        openEhrClient.compositionEndpoint(ehrId).mergeCompositionEntity(composition);
        ObjectVersionId v2 = openEhrClient
                .compositionEndpoint(ehrId)
                .mergeCompositionEntity(composition)
                .getVersionUid();
        openEhrClient.compositionEndpoint(ehrId).mergeCompositionEntity(composition);

        Optional<OriginalVersion<EpisodeOfCareComposition>> originalVersion = openEhrClient
                .versionedCompositionEndpoint(ehrId)
                .findVersionById(UUID.fromString(v2.getObjectId().getValue()), v2, EpisodeOfCareComposition.class);

        Assert.assertTrue(originalVersion.isPresent());
        Assert.assertEquals(v2.toString(), originalVersion.get().getUid().getValue());
        Assert.assertEquals(
                composition.getLanguage(), originalVersion.get().getData().getLanguage());
    }

    @Test
    public void testFindVersionByIdWrongVersionedObjectUid() {
        ehrId = openEhrClient.ehrEndpoint().createEhr();
        EpisodeOfCareComposition composition = TestData.buildEpisodeOfCareComposition();
        openEhrClient.compositionEndpoint(ehrId).mergeCompositionEntity(composition);

        Optional<OriginalVersion<EpisodeOfCareComposition>> originalVersion = openEhrClient
                .versionedCompositionEndpoint(ehrId)
                .findVersionById(UUID.randomUUID(), composition.getVersionUid(), EpisodeOfCareComposition.class);

        Assert.assertTrue(originalVersion.isEmpty());
    }

    @Test
    public void testFindVersionByIdWrongVersionId() {
        ehrId = openEhrClient.ehrEndpoint().createEhr();
        EpisodeOfCareComposition composition = TestData.buildEpisodeOfCareComposition();
        openEhrClient.compositionEndpoint(ehrId).mergeCompositionEntity(composition);

        ObjectVersionId dummyVersionId = new ObjectVersionId(
                composition.getVersionUid().getObjectId().getValue(),
                composition.getVersionUid().getCreatingSystemId().getValue(),
                "5");

        Optional<OriginalVersion<EpisodeOfCareComposition>> originalVersion = openEhrClient
                .versionedCompositionEndpoint(ehrId)
                .findVersionById(
                        UUID.fromString(
                                composition.getVersionUid().getObjectId().getValue()),
                        dummyVersionId,
                        EpisodeOfCareComposition.class);

        Assert.assertTrue(originalVersion.isEmpty());
    }

    @Test
    public void testFindVersionAtTime() {
        ehrId = openEhrClient.ehrEndpoint().createEhr();
        LocalDateTime versionAtTime;
        Optional<OriginalVersion<AlternativeEventsComposition>> result;

        // Before
        versionAtTime = LocalDateTime.now();
        AlternativeEventsComposition composition1 = TestData.buildAlternativeEventsComposition();
        openEhrClient.compositionEndpoint(ehrId).mergeCompositionEntity(composition1);

        result = openEhrClient
                .versionedCompositionEndpoint(ehrId)
                .findVersionAtTime(
                        UUID.fromString(
                                composition1.getVersionUid().getObjectId().getValue()),
                        versionAtTime,
                        AlternativeEventsComposition.class);
        Assert.assertTrue(result.isEmpty());

        // Between
        AlternativeEventsComposition composition2 = TestData.buildAlternativeEventsComposition();
        openEhrClient.compositionEndpoint(ehrId).mergeCompositionEntity(composition2);
        ObjectVersionId v2 = openEhrClient
                .compositionEndpoint(ehrId)
                .mergeCompositionEntity(composition2)
                .getVersionUid();
        versionAtTime = LocalDateTime.now();
        openEhrClient.compositionEndpoint(ehrId).mergeCompositionEntity(composition2);
        openEhrClient.compositionEndpoint(ehrId).mergeCompositionEntity(composition2);

        result = openEhrClient
                .versionedCompositionEndpoint(ehrId)
                .findVersionAtTime(
                        UUID.fromString(
                                composition2.getVersionUid().getObjectId().getValue()),
                        versionAtTime,
                        AlternativeEventsComposition.class);
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(v2.toString(), result.get().getUid().getValue());

        // Last
        AlternativeEventsComposition composition3 = TestData.buildAlternativeEventsComposition();
        openEhrClient.compositionEndpoint(ehrId).mergeCompositionEntity(composition3);
        openEhrClient.compositionEndpoint(ehrId).mergeCompositionEntity(composition3);
        openEhrClient.compositionEndpoint(ehrId).mergeCompositionEntity(composition3);
        versionAtTime = LocalDateTime.now();

        result = openEhrClient
                .versionedCompositionEndpoint(ehrId)
                .findVersionAtTime(
                        UUID.fromString(
                                composition3.getVersionUid().getObjectId().getValue()),
                        versionAtTime,
                        AlternativeEventsComposition.class);
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(
                composition3.getVersionUid().toString(), result.get().getUid().getValue());
    }

    @Test
    public void testFindVersionAtTimeNull() {
        ehrId = openEhrClient.ehrEndpoint().createEhr();
        EpisodeOfCareComposition composition = TestData.buildEpisodeOfCareComposition();
        openEhrClient.compositionEndpoint(ehrId).mergeCompositionEntity(composition);
        openEhrClient.compositionEndpoint(ehrId).mergeCompositionEntity(composition);
        openEhrClient.compositionEndpoint(ehrId).mergeCompositionEntity(composition);

        Optional<OriginalVersion<EpisodeOfCareComposition>> originalVersion = openEhrClient
                .versionedCompositionEndpoint(ehrId)
                .findVersionAtTime(
                        UUID.fromString(
                                composition.getVersionUid().getObjectId().getValue()),
                        null,
                        EpisodeOfCareComposition.class);

        Assert.assertTrue(originalVersion.isPresent());
        Assert.assertEquals(
                composition.getVersionUid().toString(),
                originalVersion.get().getUid().getValue());
        Assert.assertEquals(
                composition.getLanguage(), originalVersion.get().getData().getLanguage());
    }

    @Test
    public void testFindVersionAtTimeInvalidIId() {
        LocalDateTime versionAtTime = LocalDateTime.now();

        ehrId = openEhrClient.ehrEndpoint().createEhr();
        EpisodeOfCareComposition composition = TestData.buildEpisodeOfCareComposition();
        openEhrClient.compositionEndpoint(ehrId).mergeCompositionEntity(composition);

        UUID uuid = UUID.randomUUID();

        Optional<OriginalVersion<EpisodeOfCareComposition>> result;

        result = openEhrClient
                .versionedCompositionEndpoint(ehrId)
                .findVersionAtTime(uuid, versionAtTime, EpisodeOfCareComposition.class);
        Assert.assertTrue(result.isEmpty());

        result = openEhrClient
                .versionedCompositionEndpoint(ehrId)
                .findVersionAtTime(uuid, null, EpisodeOfCareComposition.class);
        Assert.assertTrue(result.isEmpty());
    }
}
