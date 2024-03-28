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

import static org.junit.jupiter.api.Assertions.*;

import com.nedap.archie.rm.changecontrol.OriginalVersion;
import com.nedap.archie.rm.ehr.VersionedComposition;
import com.nedap.archie.rm.generic.RevisionHistoryItem;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.TestData;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.alternativeeventscomposition.AlternativeEventsComposition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasebloodpressuresimpledev0composition.EhrbaseBloodPressureSimpleDeV0Composition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasemultioccurrencedev1composition.EhrbaseMultiOccurrenceDeV1Composition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.episodeofcarecomposition.EpisodeOfCareComposition;
import org.junit.jupiter.api.Test;

public class DefaultRestVersionedCompositionEndpointIT extends SdkClientTestIT {

    @Test
    public void testFindValid() {
        ehr = openEhrClient.ehrEndpoint().createEhr();
        EhrbaseBloodPressureSimpleDeV0Composition composition = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(composition);

        Optional<VersionedComposition> versionedComposition = openEhrClient
                .versionedCompositionEndpoint(ehr)
                .find(UUID.fromString(composition.getVersionUid().getObjectId().getValue()));

        assertTrue(versionedComposition.isPresent());
        assertEquals(
                composition.getVersionUid().getObjectId().getValue(),
                versionedComposition.get().getUid().getValue());
        assertEquals(
                ehr.toString(), versionedComposition.get().getOwnerId().getId().getValue());
    }

    @Test
    void testFindWrongId() {
        ehr = openEhrClient.ehrEndpoint().createEhr();

        Optional<VersionedComposition> versionedComposition =
                openEhrClient.versionedCompositionEndpoint(ehr).find(UUID.randomUUID());

        assertTrue(versionedComposition.isEmpty());
    }

    @Test
    void testFindRevisionHistoryValid() {
        ehr = openEhrClient.ehrEndpoint().createEhr();
        EhrbaseMultiOccurrenceDeV1Composition composition = TestData.buildEhrbaseMultiOccurrenceDeV1();

        ObjectVersionId v1 = openEhrClient
                .compositionEndpoint(ehr)
                .mergeCompositionEntity(composition)
                .getVersionUid();
        ObjectVersionId v2 = openEhrClient
                .compositionEndpoint(ehr)
                .mergeCompositionEntity(composition)
                .getVersionUid();
        ObjectVersionId v3 = openEhrClient
                .compositionEndpoint(ehr)
                .mergeCompositionEntity(composition)
                .getVersionUid();

        List<RevisionHistoryItem> revisionHistory = openEhrClient
                .versionedCompositionEndpoint(ehr)
                .findRevisionHistory(UUID.fromString(
                        composition.getVersionUid().getObjectId().getValue()));
        assertEquals(3, revisionHistory.size());
        assertEquals(v1.toString(), revisionHistory.get(0).getVersionId().getValue());
        assertEquals(v2.toString(), revisionHistory.get(1).getVersionId().getValue());
        assertEquals(v3.toString(), revisionHistory.get(2).getVersionId().getValue());
    }

    @Test
    void testFindRevisionHistoryWrongId() {
        ehr = openEhrClient.ehrEndpoint().createEhr();

        List<RevisionHistoryItem> revisionHistory =
                openEhrClient.versionedCompositionEndpoint(ehr).findRevisionHistory(UUID.randomUUID());

        assertTrue(revisionHistory.isEmpty());
    }

    @Test
    void testFindVersionById() {
        ehr = openEhrClient.ehrEndpoint().createEhr();
        EpisodeOfCareComposition composition = TestData.buildEpisodeOfCareComposition();
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(composition);
        ObjectVersionId v2 = openEhrClient
                .compositionEndpoint(ehr)
                .mergeCompositionEntity(composition)
                .getVersionUid();
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(composition);

        Optional<OriginalVersion<EpisodeOfCareComposition>> originalVersion = openEhrClient
                .versionedCompositionEndpoint(ehr)
                .findVersionById(UUID.fromString(v2.getObjectId().getValue()), v2, EpisodeOfCareComposition.class);

        assertTrue(originalVersion.isPresent());
        assertEquals(v2.toString(), originalVersion.get().getUid().getValue());
        assertEquals(composition.getLanguage(), originalVersion.get().getData().getLanguage());
    }

    @Test
    void testFindVersionByIdWrongVersionedObjectUid() {
        ehr = openEhrClient.ehrEndpoint().createEhr();
        EpisodeOfCareComposition composition = TestData.buildEpisodeOfCareComposition();
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(composition);

        Optional<OriginalVersion<EpisodeOfCareComposition>> originalVersion = openEhrClient
                .versionedCompositionEndpoint(ehr)
                .findVersionById(UUID.randomUUID(), composition.getVersionUid(), EpisodeOfCareComposition.class);

        assertTrue(originalVersion.isEmpty());
    }

    @Test
    void testFindVersionByIdWrongVersionId() {
        ehr = openEhrClient.ehrEndpoint().createEhr();
        EpisodeOfCareComposition composition = TestData.buildEpisodeOfCareComposition();
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(composition);

        ObjectVersionId dummyVersionId = new ObjectVersionId(
                composition.getVersionUid().getObjectId().getValue(),
                composition.getVersionUid().getCreatingSystemId().getValue(),
                "5");

        Optional<OriginalVersion<EpisodeOfCareComposition>> originalVersion = openEhrClient
                .versionedCompositionEndpoint(ehr)
                .findVersionById(
                        UUID.fromString(
                                composition.getVersionUid().getObjectId().getValue()),
                        dummyVersionId,
                        EpisodeOfCareComposition.class);

        assertTrue(originalVersion.isEmpty());
    }

    @Test
    void testFindVersionAtTime() {
        ehr = openEhrClient.ehrEndpoint().createEhr();
        LocalDateTime versionAtTime;
        Optional<OriginalVersion<AlternativeEventsComposition>> result;

        AlternativeEventsComposition composition1 = TestData.buildAlternativeEventsComposition();
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(composition1);

        result = openEhrClient
                .versionedCompositionEndpoint(ehr)
                .findVersionAtTime(
                        UUID.fromString(
                                composition1.getVersionUid().getObjectId().getValue()),
                        LocalDateTime.now().plusMonths(1),
                        AlternativeEventsComposition.class);
        assertFalse(result.isEmpty());
    }

    @Test
    void testFindVersionAtTimeNull() {
        ehr = openEhrClient.ehrEndpoint().createEhr();
        EpisodeOfCareComposition composition = TestData.buildEpisodeOfCareComposition();
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(composition);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(composition);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(composition);

        Optional<OriginalVersion<EpisodeOfCareComposition>> originalVersion = openEhrClient
                .versionedCompositionEndpoint(ehr)
                .findVersionAtTime(
                        UUID.fromString(
                                composition.getVersionUid().getObjectId().getValue()),
                        null,
                        EpisodeOfCareComposition.class);

        assertTrue(originalVersion.isPresent());
        assertEquals(
                composition.getVersionUid().toString(),
                originalVersion.get().getUid().getValue());
        assertEquals(composition.getLanguage(), originalVersion.get().getData().getLanguage());
    }

    @Test
    void testFindVersionAtTimeInvalidIId() {
        LocalDateTime versionAtTime = LocalDateTime.now();

        ehr = openEhrClient.ehrEndpoint().createEhr();
        EpisodeOfCareComposition composition = TestData.buildEpisodeOfCareComposition();
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(composition);

        UUID uuid = UUID.randomUUID();

        Optional<OriginalVersion<EpisodeOfCareComposition>> result;

        result = openEhrClient
                .versionedCompositionEndpoint(ehr)
                .findVersionAtTime(uuid, versionAtTime, EpisodeOfCareComposition.class);
        assertTrue(result.isEmpty());

        result = openEhrClient
                .versionedCompositionEndpoint(ehr)
                .findVersionAtTime(uuid, null, EpisodeOfCareComposition.class);
        assertTrue(result.isEmpty());
    }
}
