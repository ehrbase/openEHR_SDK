/*
 * Copyright (c) 2019 vitasystems GmbH and Hannover Medical School.
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

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.ehrbase.openehr.sdk.generator.commons.test_data.dto.TestData.buildProxyEhrbaseBloodPressureSimpleDeV0Composition;
import static org.ehrbase.openehr.sdk.test_data.contribution.ContributionTestDataCanonicalJson.ONE_ENTRY_COMPOSITION_LATEST;
import static org.ehrbase.openehr.sdk.test_data.contribution.ContributionTestDataCanonicalJson.ONE_ENTRY_COMPOSITION_MODIFICATION_LATEST;
import static org.junit.jupiter.api.Assertions.*;

import com.nedap.archie.rm.changecontrol.Contribution;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.directory.Folder;
import com.nedap.archie.rm.generic.AuditDetails;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.support.identification.ObjectRef;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import com.nedap.archie.rm.support.identification.TerminologyId;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;
import org.apache.commons.io.IOUtils;
import org.ehrbase.openehr.sdk.client.openehrclient.ContributionEndpoint;
import org.ehrbase.openehr.sdk.client.openehrclient.builder.ContributionBuilder;
import org.ehrbase.openehr.sdk.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ProxyEhrbaseBloodPressureSimpleDeV0Composition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.minimalevaluationenv1composition.MinimalEvaluationEnV1Composition;
import org.ehrbase.openehr.sdk.response.dto.ContributionCreateDto;
import org.ehrbase.openehr.sdk.serialisation.dto.GeneratedDtoToRmConverter;
import org.ehrbase.openehr.sdk.serialisation.dto.RmToGeneratedDtoConverter;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataCanonicalJson;
import org.ehrbase.openehr.sdk.test_data.folder.FolderTestDataCanonicalJson;
import org.junit.jupiter.api.Test;

public class DefaultRestContributionEndpointIT extends SdkClientTestIT {

    @Test
    public void testSaveAndGetContribution() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();

        String contribution = IOUtils.toString(ONE_ENTRY_COMPOSITION_LATEST.getStream(), StandardCharsets.UTF_8);
        ContributionCreateDto contributionDto =
                new CanonicalJson().unmarshal(contribution, ContributionCreateDto.class);

        contributionDto.setUid(null);
        contributionDto.getAudit().setTimeCommitted(null);
        contributionDto.getVersions().stream().forEach(v -> {
            v.getCommitAudit().setTimeCommitted(null);
            v.setContribution(null);
        });

        UUID contributionEntity = openEhrClient.contributionEndpoint(ehr).saveContribution(contributionDto);

        Optional<Contribution> remoteContribution =
                openEhrClient.contributionEndpoint(ehr).find(contributionEntity);

        assertTrue(remoteContribution.isPresent());
    }

    @Test
    public void testSaveContributionWithCompositionsCreationModification() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();

        // Save first composition
        GeneratedDtoToRmConverter generatedDtoToRmConverter =
                new GeneratedDtoToRmConverter(new TestDataTemplateProvider());
        MinimalEvaluationEnV1Composition minimalEvaluationEnV1Composition = mergeMinimalEvaluationEnV1Composition();
        Composition composition = (Composition) generatedDtoToRmConverter.toRMObject(minimalEvaluationEnV1Composition);
        composition.setUid(null);
        Composition compositionWithId =
                (Composition) generatedDtoToRmConverter.toRMObject(minimalEvaluationEnV1Composition);

        GeneratedDtoToRmConverter cut = new GeneratedDtoToRmConverter(new TestDataTemplateProvider());

        ProxyEhrbaseBloodPressureSimpleDeV0Composition proxyDto = buildProxyEhrbaseBloodPressureSimpleDeV0Composition();

        // Save second composition
        ProxyEhrbaseBloodPressureSimpleDeV0Composition proxyComposition =
                openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(proxyDto);

        Composition unflattenSecondComposition = (Composition) cut.toRMObject(proxyComposition);

        // Create contribution
        ContributionCreateDto contribution = ContributionBuilder.builder(createAuditDetails())
                .addCompositionCreation(composition)
                .addCompositionCreation(composition)
                .addCompositionCreation(composition)
                .addCompositionModification(compositionWithId)
                .addCompositionModification(
                        unflattenSecondComposition,
                        proxyComposition.getVersionUid().toString())
                .build();

        // Save Contribution
        UUID versionUid = openEhrClient.contributionEndpoint(ehr).saveContribution(contribution);

        // Find Contribution
        Optional<Contribution> remoteContribution =
                openEhrClient.contributionEndpoint(ehr).find(versionUid);

        long expectedCompositionsCreatedTimes = 3L;
        long expectedCompositionsModifiedTimes = 2L;

        assertTrue(remoteContribution.isPresent());
        assertEquals(
                expectedCompositionsCreatedTimes,
                countNumberOfChangedLocatableObjectByVersion(remoteContribution.get(), "1"));
        assertEquals(
                expectedCompositionsModifiedTimes,
                countNumberOfChangedLocatableObjectByVersion(remoteContribution.get(), "2"));
    }

    private static long countNumberOfChangedLocatableObjectByVersion(Contribution remoteContribution, String version) {
        return remoteContribution.getVersions().stream()
                .filter(objectRef -> version.equals(objectRef
                        .getId()
                        .getValue()
                        .substring(objectRef.getId().getValue().lastIndexOf("::") + 2)))
                .count();
    }

    @Test
    public void testSaveContributionWithCompositionCreationModificationDeletion() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();

        // Save composition
        GeneratedDtoToRmConverter generatedDtoToRmConverter =
                new GeneratedDtoToRmConverter(new TestDataTemplateProvider());
        Composition composition =
                (Composition) generatedDtoToRmConverter.toRMObject(mergeMinimalEvaluationEnV1Composition());
        AuditDetails audit = createAuditDetails();

        // 2 Create contribution with composition modification
        ContributionCreateDto contribution = ContributionBuilder.builder(audit)
                .addCompositionModification(composition)
                .build();

        // 3 Save Contribution
        UUID versionUid = openEhrClient.contributionEndpoint(ehr).saveContribution(contribution);

        // 4 Find Contribution
        Optional<Contribution> remoteContribution =
                openEhrClient.contributionEndpoint(ehr).find(versionUid);
        assertTrue(remoteContribution.isPresent());

        assertEquals("2", getCompositionVersion(remoteContribution.get()));

        // 5 Get previous composition version id
        String compositionPrecedingVersionUid =
                remoteContribution.get().getVersions().get(0).getId().getValue();

        // 6 Create contribution with composition deletion
        ContributionCreateDto contributionWithCompositionDeletion = ContributionBuilder.builder(audit)
                .addCompositionDeletion(compositionPrecedingVersionUid)
                .build();

        // 7 Save Contribution
        UUID versionUid1 =
                openEhrClient.contributionEndpoint(ehr).saveContribution(contributionWithCompositionDeletion);

        // 8 Find Contribution
        Optional<Contribution> remoteContribution1 =
                openEhrClient.contributionEndpoint(ehr).find(versionUid1);

        assertTrue(remoteContribution1.isPresent());
        assertEquals("3", getCompositionVersion(remoteContribution1.get()));
    }

    @Test
    public void testSaveContributionWithCompositionCreationModificationDeletion1() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();

        // 1 Save composition
        GeneratedDtoToRmConverter generatedDtoToRmConverter =
                new GeneratedDtoToRmConverter(new TestDataTemplateProvider());
        Composition composition =
                (Composition) generatedDtoToRmConverter.toRMObject(mergeMinimalEvaluationEnV1Composition());
        composition.setUid(null);
        AuditDetails audit = createAuditDetails();

        // 2 Create contribution with composition modification
        ContributionCreateDto contribution = ContributionBuilder.builder(audit)
                .addCompositionCreation(composition)
                .build();

        // 3 Save Contribution
        UUID versionUid = openEhrClient.contributionEndpoint(ehr).saveContribution(contribution);

        // 4 Find Contribution
        Optional<Contribution> remoteContribution_ =
                openEhrClient.contributionEndpoint(ehr).find(versionUid);
        assertTrue(remoteContribution_.isPresent());

        assertEquals("1", getCompositionVersion(remoteContribution_.get()));

        // 5 Get previous composition id
        String compositionPrecedingVersionUid =
                remoteContribution_.get().getVersions().get(0).getId().getValue();

        // 6 Create contribution with composition modification
        ContributionCreateDto contributionCompositionModification = ContributionBuilder.builder(audit)
                .addCompositionModification(composition, compositionPrecedingVersionUid)
                .build();

        // 7 Save Contribution
        UUID versionUid1 =
                openEhrClient.contributionEndpoint(ehr).saveContribution(contributionCompositionModification);

        // 8 Find Contribution
        Optional<Contribution> remoteContribution_1 =
                openEhrClient.contributionEndpoint(ehr).find(versionUid1);
        assertTrue(remoteContribution_1.isPresent());

        assertEquals("2", getCompositionVersion(remoteContribution_1.get()));

        // 9 Get previous composition id
        String compositionPrecedingVersionUid1 =
                remoteContribution_1.get().getVersions().get(0).getId().getValue();

        // 10 Create contribution with composition deletion
        ContributionCreateDto contributionWithCompositionDeletion1 = ContributionBuilder.builder(audit)
                .addCompositionDeletion(compositionPrecedingVersionUid1)
                .build();

        // 11 Save Contribution
        UUID versionUid12 =
                openEhrClient.contributionEndpoint(ehr).saveContribution(contributionWithCompositionDeletion1);

        // 12 Find Contribution
        Optional<Contribution> remoteContribution =
                openEhrClient.contributionEndpoint(ehr).find(versionUid12);

        assertTrue(remoteContribution.isPresent());
        assertEquals("3", getCompositionVersion(remoteContribution.get()));
    }

    @Test
    public void testSaveDefaultContributionWithRMCompositionCreationModificationDeletion() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();

        // 1 Save composition
        GeneratedDtoToRmConverter generatedDtoToRmConverter =
                new GeneratedDtoToRmConverter(new TestDataTemplateProvider());
        Composition rmCompositionResponse =
                (Composition) generatedDtoToRmConverter.toRMObject(mergeMinimalEvaluationEnV1Composition());
        AuditDetails audit = createAuditDetails();

        // 2 Create contribution with composition modification
        ContributionCreateDto contributionWithCompositionModified = ContributionBuilder.builder(audit)
                .addCompositionModification(rmCompositionResponse)
                .build();

        // 3 Save Contribution
        UUID versionUid = openEhrClient.contributionEndpoint(ehr).saveContribution(contributionWithCompositionModified);

        // 4 Find Contribution
        Optional<Contribution> remoteContribution_ =
                openEhrClient.contributionEndpoint(ehr).find(versionUid);
        assertTrue(remoteContribution_.isPresent());

        assertEquals("2", getCompositionVersion(remoteContribution_.get()));

        // 5 Get previous version composition uid
        String compositionPrecedingVersionUid =
                remoteContribution_.get().getVersions().get(0).getId().getValue();

        // 6 Create contribution with composition deletion
        ContributionCreateDto contributionWithCompositionDeletion = ContributionBuilder.builder(audit)
                .addCompositionDeletion(compositionPrecedingVersionUid)
                .build();

        // 7 Save Contribution
        UUID versionUid1 =
                openEhrClient.contributionEndpoint(ehr).saveContribution(contributionWithCompositionDeletion);

        // 8 Confirm that composition no longer exist
        Optional<Composition> composition =
                openEhrClient.compositionEndpoint(ehr).findRaw(getCompositionUuid(rmCompositionResponse));
        assertFalse(composition.isPresent());

        // 9 Find Contribution
        Optional<Contribution> remoteContribution =
                openEhrClient.contributionEndpoint(ehr).find(versionUid1);

        assertTrue(remoteContribution.isPresent());
        assertEquals("3", getCompositionVersion(remoteContribution.get()));
    }

    @Test
    public void testSaveContributionWithRMCompositionCreationModificationDeletion() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();
        String compositionPrecedingVersionUid;

        // 1 Save composition
        GeneratedDtoToRmConverter generatedDtoToRmConverter =
                new GeneratedDtoToRmConverter(new TestDataTemplateProvider());
        Composition rmCompositionResponse =
                (Composition) generatedDtoToRmConverter.toRMObject(mergeMinimalEvaluationEnV1Composition());
        AuditDetails audit = createAuditDetails();

        compositionPrecedingVersionUid = rmCompositionResponse.getUid().getValue();

        // 2 Create contribution with composition modification
        ContributionCreateDto contributionWithCompositionModified = ContributionBuilder.builder(audit)
                .addCompositionModification(rmCompositionResponse, compositionPrecedingVersionUid)
                .build();

        // 3 Save Contribution
        UUID versionUid = openEhrClient.contributionEndpoint(ehr).saveContribution(contributionWithCompositionModified);

        // 4 Find Contribution
        Optional<Contribution> remoteContribution_ =
                openEhrClient.contributionEndpoint(ehr).find(versionUid);
        assertTrue(remoteContribution_.isPresent());

        assertEquals("2", getCompositionVersion(remoteContribution_.get()));

        // 5 Get previous composition id
        compositionPrecedingVersionUid =
                remoteContribution_.get().getVersions().get(0).getId().getValue();

        // 6 Create contribution with composition deletion
        ContributionCreateDto contributionWithCompositionDeletion = ContributionBuilder.builder(audit)
                .addCompositionDeletion(compositionPrecedingVersionUid)
                .build();

        // 7 Save Contribution
        UUID versionUid1 =
                openEhrClient.contributionEndpoint(ehr).saveContribution(contributionWithCompositionDeletion);

        // 8 Find Contribution
        Optional<Contribution> remoteContribution =
                openEhrClient.contributionEndpoint(ehr).find(versionUid1);

        assertTrue(remoteContribution.isPresent());
        assertEquals("3", getCompositionVersion(remoteContribution.get()));
    }

    @Test
    public void testSaveContributionWithCompositionsCreation() throws IOException {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        GeneratedDtoToRmConverter cut = new GeneratedDtoToRmConverter(new TestDataTemplateProvider());

        ProxyEhrbaseBloodPressureSimpleDeV0Composition proxyDto = buildProxyEhrbaseBloodPressureSimpleDeV0Composition();

        Composition proxyComposition = (Composition) cut.toRMObject(proxyDto);

        String contributionJson =
                IOUtils.toString(ONE_ENTRY_COMPOSITION_MODIFICATION_LATEST.getStream(), StandardCharsets.UTF_8);
        ContributionCreateDto contributionDto =
                new CanonicalJson().unmarshal(contributionJson, ContributionCreateDto.class);

        ContributionCreateDto contribution = ContributionBuilder.builder(contributionDto.getAudit())
                .addCompositionCreation(proxyComposition)
                .build();

        ContributionEndpoint contributionEndpoint = openEhrClient.contributionEndpoint(ehr);
        UUID versionUid = contributionEndpoint.saveContribution(contribution);
        Optional<Contribution> remoteContribution = contributionEndpoint.find(versionUid);

        assertTrue(remoteContribution.isPresent());
    }

    @Test
    public void testWhenNotProvidedCompositionOrCompositionDeletionPrecedentId() throws IOException {
        ContributionBuilder builder = ContributionBuilder.builder(createAuditDetails());
        Exception exception = assertThrows(IllegalArgumentException.class, builder::build);

        String expectedMessage = "Invalid Contribution, must have at least one Version object.";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testSaveContributionWithFolderCreationModification() throws IOException {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        String value = IOUtils.toString(FolderTestDataCanonicalJson.SIMPLE_EMPTY_FOLDER.getStream(), UTF_8);
        CanonicalJson canonicalJson = new CanonicalJson();
        Folder folder = canonicalJson.unmarshal(value, Folder.class);

        String contributionJson =
                IOUtils.toString(ONE_ENTRY_COMPOSITION_MODIFICATION_LATEST.getStream(), StandardCharsets.UTF_8);
        ContributionCreateDto contributionDto =
                new CanonicalJson().unmarshal(contributionJson, ContributionCreateDto.class);

        ContributionCreateDto contribution = ContributionBuilder.builder(contributionDto.getAudit())
                .addFolderCreation(folder)
                .build();

        ContributionEndpoint contributionEndpoint = openEhrClient.contributionEndpoint(ehr);
        UUID versionUid = contributionEndpoint.saveContribution(contribution);
        Optional<Contribution> remoteContribution = contributionEndpoint.find(versionUid);

        assertTrue(remoteContribution.isPresent());

        Folder newFolder = new Folder();
        DvText name = new DvText();
        name.setValue("test");
        newFolder.setName(name);
        newFolder.setUid(folder.getUid());
        folder.addFolder(newFolder);

        ContributionCreateDto modifiedContribution = ContributionBuilder.builder(contributionDto.getAudit())
                .addFolderModification(
                        folder,
                        String.valueOf(
                                remoteContribution.get().getVersions().get(0).getId()))
                .build();

        UUID secondVersionUid = contributionEndpoint.saveContribution(modifiedContribution);
        Optional<Contribution> remoteModifiedContribution = contributionEndpoint.find(secondVersionUid);

        // 5 Get previous version composition uid
        String folderPrecedingVersionUid =
                remoteModifiedContribution.get().getVersions().get(0).getId().getValue();

        // 6 Create contribution with composition deletion
        ContributionCreateDto contributionWithFolderDeletion = ContributionBuilder.builder(contributionDto.getAudit())
                .addFolderDeletion(folder, folderPrecedingVersionUid)
                .build();

        // 7 Save Contribution
        openEhrClient.contributionEndpoint(ehr).saveContribution(contributionWithFolderDeletion);
        // 8 Confirm that composition no longer exist
    }

    @Test
    public void testSaveContributionWithFolderModification() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();

        Folder rootFolder = new Folder();
        // Create root folder
        ObjectVersionId directory = openEhrClient.directoryCrudEndpoint(ehr).createDirectory(rootFolder);

        rootFolder.setUid(directory);

        // Prepare first composition
        GeneratedDtoToRmConverter generatedDtoToRmConverter =
                new GeneratedDtoToRmConverter(new TestDataTemplateProvider());
        Composition composition =
                (Composition) generatedDtoToRmConverter.toRMObject(mergeMinimalEvaluationEnV1Composition());
        composition.setUid(null);
        Composition compositionWithId =
                (Composition) generatedDtoToRmConverter.toRMObject(mergeMinimalEvaluationEnV1Composition());

        // Add first composition to folder
        rootFolder
                .getItems()
                .add(new ObjectRef<>(
                        new ObjectVersionId((compositionWithId.getUid().toString())), "local", "Composition"));

        // Prepare second composition
        GeneratedDtoToRmConverter cut = new GeneratedDtoToRmConverter(new TestDataTemplateProvider());
        ProxyEhrbaseBloodPressureSimpleDeV0Composition proxyDto = buildProxyEhrbaseBloodPressureSimpleDeV0Composition();
        ProxyEhrbaseBloodPressureSimpleDeV0Composition proxyComposition =
                openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(proxyDto);
        Composition unflattenSecondComposition = (Composition) cut.toRMObject(proxyComposition);
        unflattenSecondComposition.setUid(new ObjectVersionId(UUID.randomUUID().toString(), "local.ehrbase.org", "1"));

        // Add second composition

        rootFolder
                .getItems()
                .add(new ObjectRef<>(
                        new ObjectVersionId((unflattenSecondComposition.getUid().toString())), "local", "Composition"));

        // Create contribution
        ContributionCreateDto contribution = ContributionBuilder.builder(createAuditDetails())
                .addCompositionCreation(composition)
                .addCompositionModification(compositionWithId)
                .addCompositionCreation(unflattenSecondComposition)
                .addCompositionModification(
                        unflattenSecondComposition,
                        proxyComposition.getVersionUid().toString())
                .addFolderModification(rootFolder, rootFolder.getUid().getValue())
                .build();

        // Save Contribution
        UUID versionUid = openEhrClient.contributionEndpoint(ehr).saveContribution(contribution);

        // Find Contribution
        Optional<Contribution> remoteContribution =
                openEhrClient.contributionEndpoint(ehr).find(versionUid);

        long expectedCompositionsCreatedTimes = 2L;
        long expectedCompositionsModifiedTimes = 2L;
        long expectedFolderModifiedTimes = 1L;

        assertTrue(remoteContribution.isPresent());
        assertEquals(
                expectedCompositionsCreatedTimes,
                countNumberOfChangedLocatableObjectByVersion(remoteContribution.get(), "1"));
        assertEquals(
                expectedCompositionsModifiedTimes + expectedFolderModifiedTimes,
                countNumberOfChangedLocatableObjectByVersion(remoteContribution.get(), "2"));
    }

    private static String getCompositionVersion(Contribution remoteContribution) {
        String compositionId = remoteContribution.getVersions().get(0).getId().getValue();

        return compositionId.substring(compositionId.lastIndexOf("::") + 2);
    }

    private static UUID getCompositionUuid(Composition composition) {
        String compositionId = composition.getUid().getValue();

        return UUID.fromString(compositionId.substring(0, compositionId.indexOf("::")));
    }

    private static AuditDetails createAuditDetails() {

        AuditDetails auditDetails = new AuditDetails();
        auditDetails.setChangeType(
                new DvCodedText("modification", new CodePhrase(new TerminologyId("openehr"), "251")));
        auditDetails.setCommitter(new PartyIdentified(null, "Dr. Yamamoto", null));
        auditDetails.setSystemId("ehrbase");
        return auditDetails;
    }

    private MinimalEvaluationEnV1Composition mergeMinimalEvaluationEnV1Composition() throws IOException {
        var aComposition = new CanonicalJson()
                .unmarshal(
                        IOUtils.toString(
                                CompositionTestDataCanonicalJson.MINIMAL_EVAL.getStream(), StandardCharsets.UTF_8),
                        Composition.class);

        RmToGeneratedDtoConverter rmToGeneratedDtoConverter =
                new RmToGeneratedDtoConverter(new TestDataTemplateProvider());
        MinimalEvaluationEnV1Composition minimalEvaluationEnV1Composition =
                rmToGeneratedDtoConverter.toGeneratedDto(aComposition, MinimalEvaluationEnV1Composition.class);

        return openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(minimalEvaluationEnV1Composition);
    }
}
