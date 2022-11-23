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
package org.ehrbase.client.openehrclient.defaultrestclient;

import static org.ehrbase.client.TestData.buildProxyEhrbaseBloodPressureSimpleDeV0Composition;
import static org.ehrbase.test_data.contribution.ContributionTestDataCanonicalJson.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.changecontrol.Contribution;
import com.nedap.archie.rm.changecontrol.OriginalVersion;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.ehr.EhrStatus;
import com.nedap.archie.rm.generic.AuditDetails;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.apache.commons.io.IOUtils;
import org.ehrbase.client.Integration;
import org.ehrbase.client.classgenerator.examples.minimalevaluationenv1composition.MinimalEvaluationEnV1Composition;
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.client.flattener.Unflattener;
import org.ehrbase.client.openehrclient.ContributionEndpoint;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.client.openehrclient.builder.ContributionBuilder;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.CanonicalCompoAllTypeQueryIT;
import org.ehrbase.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.response.openehr.ContributionCreateDto;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Integration.class)
public class DefaultRestContributionEndpointIT extends CanonicalCompoAllTypeQueryIT {

    private static OpenEhrClient openEhrClient;
    private UUID ehr;

    @BeforeClass
    public static void setup() throws URISyntaxException {
        openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    }

    @After
    public void tearDown() {
        // delete the created EHR using the admin endpoint
        openEhrClient.adminEhrEndpoint().delete(ehr);
    }

    @Test
    public void testSaveAndGetContribution() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();

        String contribution = IOUtils.toString(ONE_ENTRY_COMPOSITION_LATEST.getStream(), StandardCharsets.UTF_8);
        ContributionCreateDto contributionDto =
                new CanonicalJson().unmarshal(contribution, ContributionCreateDto.class);

        VersionUid contributionEntity =
                openEhrClient.contributionEndpoint(ehr).mergeContributionEntity(contributionDto);

        Optional<Contribution> remoteContribution =
                openEhrClient.contributionEndpoint(ehr).find(ehr, contributionEntity.getUuid());

        assertTrue(remoteContribution.isPresent());
    }

    @Test
    public void testSaveContributionEhrStatusModification() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();

        Optional<EhrStatus> ehrStatus = openEhrClient.ehrEndpoint().getEhrStatus(ehr);

        assertTrue(ehrStatus.isPresent());

        ContributionCreateDto contributionDto = createContributionWithEhrStatus(ehrStatus.get());

        VersionUid versionUid = openEhrClient.contributionEndpoint(ehr).mergeContributionEntity(contributionDto);

        Optional<Contribution> remoteContribution =
                openEhrClient.contributionEndpoint(ehr).find(ehr, versionUid.getUuid());

        assertTrue(remoteContribution.isPresent());
    }

    @Test
    public void testSaveContributionWithCompositionsCreationModification() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();

        // Save first composition
        Unflattener unflattener = new Unflattener(new TestDataTemplateProvider());
        Composition rmCompositionResponse =
                (Composition) unflattener.unflatten(mergeMinimalEvaluationEnV1Composition());
        LinkedHashMap<String, Object> composition = getCanonicalComposition(rmCompositionResponse);

        Unflattener cut = new Unflattener(new TestDataTemplateProvider());

        ProxyEhrbaseBloodPressureSimpleDeV0Composition proxyDto = buildProxyEhrbaseBloodPressureSimpleDeV0Composition();

        // Save second composition
        ProxyEhrbaseBloodPressureSimpleDeV0Composition proxyComposition =
                openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(proxyDto);

        Composition unflattenSecondComposition = (Composition) cut.unflatten(proxyComposition);
        // Create contribution
        ContributionBuilder contributionBuilder = ContributionBuilder.builder(createAuditDetails())
                .addCompositionCreation(composition)
                .addCompositionCreation(composition)
                .addCompositionCreation(composition)
                //                .addCompositionModification(composition) // can't modify same composition twice in one
                // request because version is chaning in each operation
                .addCompositionModification(composition)
                .addCompositionModification(
                        unflattenSecondComposition,
                        proxyComposition.getVersionUid().toString())
                .build();

        // Save Contribution
        VersionUid versionUid =
                openEhrClient.contributionEndpoint(ehr).mergeContributionEntity(contributionBuilder.getContribution());

        // Find Contribution
        Optional<Contribution> remoteContribution =
                openEhrClient.contributionEndpoint(ehr).find(ehr, versionUid.getUuid());

        long expectedCompositionsCreatedTimes = 3L;
        long expectedCompositionsModifiedTimes = 2L;

        assertTrue(remoteContribution.isPresent());
        assertEquals(
                expectedCompositionsCreatedTimes,
                countNumberOfChangedCompositionsByVersion(remoteContribution.get(), "1"));
        assertEquals(
                expectedCompositionsModifiedTimes,
                countNumberOfChangedCompositionsByVersion(remoteContribution.get(), "2"));
    }

    private static long countNumberOfChangedCompositionsByVersion(Contribution remoteContribution, String version) {
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
        Unflattener unflattener = new Unflattener(new TestDataTemplateProvider());
        Composition rmCompositionResponse =
                (Composition) unflattener.unflatten(mergeMinimalEvaluationEnV1Composition());
        LinkedHashMap<String, Object> composition = getCanonicalComposition(rmCompositionResponse);
        AuditDetails audit = createAuditDetails();

        // 2 Create contribution with composition modification
        ContributionBuilder contributionBuilderCompositionModification = ContributionBuilder.builder(audit)
                .addCompositionModification(composition)
                .build();

        // 3 Save Contribution
        VersionUid versionUid = openEhrClient
                .contributionEndpoint(ehr)
                .mergeContributionEntity(contributionBuilderCompositionModification.getContribution());

        // 4 Find Contribution
        Optional<Contribution> remoteContribution_ =
                openEhrClient.contributionEndpoint(ehr).find(ehr, versionUid.getUuid());
        assertTrue(remoteContribution_.isPresent());

        assertEquals(1L, getContributionVersion(versionUid));
        assertEquals("2", getCompositionVersion(remoteContribution_.get()));

        // 5 Replace composition version
        changeToLatestCompositionVersion(composition, remoteContribution_.get());

        // 6 Create contribution with composition deletion
        ContributionBuilder contributionBuilderCompositionDeletion = ContributionBuilder.builder(audit)
                .addCompositionDeletion(composition)
                .build();

        // 7 Save Contribution
        VersionUid versionUid1 = openEhrClient
                .contributionEndpoint(ehr)
                .mergeContributionEntity(contributionBuilderCompositionDeletion.getContribution());

        // 8 Find Contribution
        Optional<Contribution> remoteContribution =
                openEhrClient.contributionEndpoint(ehr).find(ehr, versionUid1.getUuid());

        assertTrue(remoteContribution.isPresent());
        assertEquals(1L, getContributionVersion(versionUid));
        assertEquals("3", getCompositionVersion(remoteContribution.get()));
    }

    @Test
    public void testSaveContributionWithCompositionCreationModificationDeletion1() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();

        // 1 Save composition
        Unflattener unflattener = new Unflattener(new TestDataTemplateProvider());
        Composition rmCompositionResponse =
                (Composition) unflattener.unflatten(mergeMinimalEvaluationEnV1Composition());
        LinkedHashMap<String, Object> composition = getCanonicalComposition(rmCompositionResponse);
        AuditDetails audit = createAuditDetails();

        // 2 Create contribution with composition modification
        ContributionBuilder contributionBuilderCompositionModification = ContributionBuilder.builder(audit)
                .addCompositionCreation(composition)
                .build();

        // 3 Save Contribution
        VersionUid versionUid = openEhrClient
                .contributionEndpoint(ehr)
                .mergeContributionEntity(contributionBuilderCompositionModification.getContribution());

        // 4 Find Contribution
        Optional<Contribution> remoteContribution_ =
                openEhrClient.contributionEndpoint(ehr).find(ehr, versionUid.getUuid());
        assertTrue(remoteContribution_.isPresent());

        assertEquals(1L, getContributionVersion(versionUid));
        assertEquals("1", getCompositionVersion(remoteContribution_.get()));

        // 5 Get previous composition id
        String compositionPrecedingVersionUid =
                remoteContribution_.get().getVersions().get(0).getId().getValue();

        // 6 Create contribution with composition modification
        ContributionBuilder contributionBuilderCompositionModifiation = ContributionBuilder.builder(audit)
                .addCompositionModification(composition, compositionPrecedingVersionUid)
                .build();

        // 7 Save Contribution
        VersionUid versionUid1 = openEhrClient
                .contributionEndpoint(ehr)
                .mergeContributionEntity(contributionBuilderCompositionModifiation.getContribution());

        // 8 Find Contribution
        Optional<Contribution> remoteContribution_1 =
                openEhrClient.contributionEndpoint(ehr).find(ehr, versionUid1.getUuid());
        assertTrue(remoteContribution_1.isPresent());

        assertEquals(1L, getContributionVersion(versionUid));
        assertEquals("2", getCompositionVersion(remoteContribution_1.get()));

        // 9 Get previous composition id
        String compositionPrecedingVersionUid1 =
                remoteContribution_1.get().getVersions().get(0).getId().getValue();

        // 10 Create contribution with composition deletion
        ContributionBuilder contributionBuilderCompositionDeletion1 = ContributionBuilder.builder(audit)
                .addCompositionDeletion(composition, compositionPrecedingVersionUid1)
                .build();

        // 11 Save Contribution
        VersionUid versionUid12 = openEhrClient
                .contributionEndpoint(ehr)
                .mergeContributionEntity(contributionBuilderCompositionDeletion1.getContribution());

        // 12 Find Contribution
        Optional<Contribution> remoteContribution =
                openEhrClient.contributionEndpoint(ehr).find(ehr, versionUid12.getUuid());

        assertTrue(remoteContribution.isPresent());
        assertEquals(1L, getContributionVersion(versionUid));
        assertEquals("3", getCompositionVersion(remoteContribution.get()));
    }

    @Test
    public void testSaveDefaultContributionWithRMCompositionCreationModificationDeletion() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();

        // 1 Save composition
        Unflattener unflattener = new Unflattener(new TestDataTemplateProvider());
        Composition rmCompositionResponse =
                (Composition) unflattener.unflatten(mergeMinimalEvaluationEnV1Composition());
        AuditDetails audit = createAuditDetails();

        // 2 Create contribution with composition modification
        ContributionBuilder contributionCompositionModified = ContributionBuilder.builder(audit)
                .addCompositionModification(rmCompositionResponse)
                .build();

        // 3 Save Contribution
        VersionUid versionUid = openEhrClient
                .contributionEndpoint(ehr)
                .mergeContributionEntity(contributionCompositionModified.getContribution());

        // 4 Find Contribution
        Optional<Contribution> remoteContribution_ =
                openEhrClient.contributionEndpoint(ehr).find(ehr, versionUid.getUuid());
        assertTrue(remoteContribution_.isPresent());

        assertEquals(1L, getContributionVersion(versionUid));
        assertEquals("2", getCompositionVersion(remoteContribution_.get()));

        // 5 Replace composition version
        String compositionPrecedingVersionUid =
                remoteContribution_.get().getVersions().get(0).getId().getValue();
        rmCompositionResponse.setUid(new ObjectVersionId(compositionPrecedingVersionUid));

        // 6 Create contribution with composition deletion
        ContributionBuilder contributionBuilderCompositionDeletion = ContributionBuilder.builder(audit)
                .addCompositionDeletion(rmCompositionResponse)
                .build();

        // 7 Save Contribution
        VersionUid versionUid1 = openEhrClient
                .contributionEndpoint(ehr)
                .mergeContributionEntity(contributionBuilderCompositionDeletion.getContribution());

        // 8 Find Contribution
        Optional<Contribution> remoteContribution =
                openEhrClient.contributionEndpoint(ehr).find(ehr, versionUid1.getUuid());

        assertTrue(remoteContribution.isPresent());
        assertEquals(1L, getContributionVersion(versionUid));
        assertEquals("3", getCompositionVersion(remoteContribution.get()));
    }

    @Test
    public void testSaveContributionWithRMCompositionCreationModificationDeletion() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();
        String compositionPrecedingVersionUid;

        // 1 Save composition
        Unflattener unflattener = new Unflattener(new TestDataTemplateProvider());
        Composition rmCompositionResponse =
                (Composition) unflattener.unflatten(mergeMinimalEvaluationEnV1Composition());
        AuditDetails audit = createAuditDetails();

        compositionPrecedingVersionUid = rmCompositionResponse.getUid().getValue();

        // 2 Create contribution with composition modification
        ContributionBuilder contributionCompositionModified = ContributionBuilder.builder(audit)
                .addCompositionModification(rmCompositionResponse, compositionPrecedingVersionUid)
                .build();

        // 3 Save Contribution
        VersionUid versionUid = openEhrClient
                .contributionEndpoint(ehr)
                .mergeContributionEntity(contributionCompositionModified.getContribution());

        // 4 Find Contribution
        Optional<Contribution> remoteContribution_ =
                openEhrClient.contributionEndpoint(ehr).find(ehr, versionUid.getUuid());
        assertTrue(remoteContribution_.isPresent());

        assertEquals(1L, getContributionVersion(versionUid));
        assertEquals("2", getCompositionVersion(remoteContribution_.get()));

        // 5 Get previous composition id
        compositionPrecedingVersionUid =
                remoteContribution_.get().getVersions().get(0).getId().getValue();

        // 6 Create contribution with composition deletion
        ContributionBuilder contributionBuilderCompositionDeletion = ContributionBuilder.builder(audit)
                .addCompositionDeletion(rmCompositionResponse, compositionPrecedingVersionUid)
                .build();

        // 7 Save Contribution
        VersionUid versionUid1 = openEhrClient
                .contributionEndpoint(ehr)
                .mergeContributionEntity(contributionBuilderCompositionDeletion.getContribution());

        // 8 Find Contribution
        Optional<Contribution> remoteContribution =
                openEhrClient.contributionEndpoint(ehr).find(ehr, versionUid1.getUuid());

        assertTrue(remoteContribution.isPresent());
        assertEquals(1L, getContributionVersion(versionUid));
        assertEquals("3", getCompositionVersion(remoteContribution.get()));
    }

    @Test
    public void testSaveContributionWithCompositionsCreation() throws IOException {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        Unflattener cut = new Unflattener(new TestDataTemplateProvider());

        ProxyEhrbaseBloodPressureSimpleDeV0Composition proxyDto = buildProxyEhrbaseBloodPressureSimpleDeV0Composition();

        Composition proxyComposition = (Composition) cut.unflatten(proxyDto);

        String composition = new CanonicalJson().marshal(proxyComposition);

        LinkedHashMap<String, Object> secondComposition =
                (LinkedHashMap<String, Object>) new CanonicalJson().unmarshalToMap(composition);

        OriginalVersion<Map<String, Object>> originalVersion = new OriginalVersion<>();
        originalVersion.setData(secondComposition);

        String contributionJson =
                IOUtils.toString(ONE_ENTRY_COMPOSITION_MODIFICATION_LATEST.getStream(), StandardCharsets.UTF_8);
        ContributionCreateDto contributionDto =
                new CanonicalJson().unmarshal(contributionJson, ContributionCreateDto.class);

        ContributionBuilder contributionBuilder = ContributionBuilder.builder(contributionDto.getAudit())
                .addCompositionCreation(proxyComposition)
                .addOriginalVersionCreation(originalVersion)
                .build();

        ContributionEndpoint contributionEndpoint = openEhrClient.contributionEndpoint(ehr);
        VersionUid versionUid = contributionEndpoint.mergeContributionEntity(contributionBuilder.getContribution());
        Optional<Contribution> remoteContribution = contributionEndpoint.find(ehr, versionUid.getUuid());

        assertTrue(remoteContribution.isPresent());
    }

    @SuppressWarnings("unchecked")
    private static void changeToLatestCompositionVersion(
            LinkedHashMap<String, Object> composition, Contribution contribution) {
        String compositionPrecedingVersionUid =
                contribution.getVersions().get(0).getId().getValue();
        ((LinkedHashMap<String, Object>) composition.get("uid")).put("value", compositionPrecedingVersionUid);
    }

    private static long getContributionVersion(VersionUid versionUid) {
        return versionUid.getVersion();
    }

    private static String getCompositionVersion(Contribution remoteContribution) {
        String compositionId = remoteContribution.getVersions().get(0).getId().getValue();

        return compositionId.substring(compositionId.lastIndexOf("::") + 2);
    }

    private static AuditDetails createAuditDetails() throws IOException {
        String contributionModificationJson =
                IOUtils.toString(ONE_ENTRY_COMPOSITION_MODIFICATION_LATEST.getStream(), StandardCharsets.UTF_8);
        ContributionCreateDto contributionDto =
                new CanonicalJson().unmarshal(contributionModificationJson, ContributionCreateDto.class);

        return contributionDto.getVersions().get(0).getCommitAudit();
    }

    private MinimalEvaluationEnV1Composition mergeMinimalEvaluationEnV1Composition() throws IOException {
        aComposition = new CanonicalJson()
                .unmarshal(
                        IOUtils.toString(
                                CompositionTestDataCanonicalJson.MINIMAL_EVAL.getStream(), StandardCharsets.UTF_8),
                        Composition.class);

        Flattener flattener = new Flattener(new TestDataTemplateProvider());
        MinimalEvaluationEnV1Composition minimalEvaluationEnV1Composition =
                flattener.flatten(aComposition, MinimalEvaluationEnV1Composition.class);

        return openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(minimalEvaluationEnV1Composition);
    }

    private static LinkedHashMap<String, Object> getCanonicalComposition(RMObject composition) {
        String compositionJson = new CanonicalJson().marshal(composition);

        return (LinkedHashMap<String, Object>) new CanonicalJson().unmarshalToMap(compositionJson);
    }

    private static ContributionCreateDto createContributionWithEhrStatus(EhrStatus ehrStatus) throws IOException {
        String contribution = IOUtils.toString(STATUS_COMPOITION_MODIFICATION.getStream(), StandardCharsets.UTF_8);
        ContributionCreateDto contributionDto =
                new CanonicalJson().unmarshal(contribution, ContributionCreateDto.class);
        contributionDto
                .getVersions()
                .get(0)
                .getPrecedingVersionUid()
                .setValue(ehrStatus.getUid().getValue());

        return contributionDto;
    }
}
