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

import static org.assertj.core.api.Assertions.assertThat;
import static org.ehrbase.client.TestData.buildProxyEhrbaseBloodPressureSimpleDeV0Composition;
import static org.ehrbase.test_data.contribution.ContributionTestDataCanonicalJson.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.nedap.archie.rm.changecontrol.Contribution;
import com.nedap.archie.rm.changecontrol.OriginalVersion;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.ehr.EhrStatus;
import com.nedap.archie.rm.generic.AuditDetails;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
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

/**
 * The type Default rest contribution endpoint it.
 */
@Category(Integration.class)
public class DefaultRestContributionEndpointIT extends CanonicalCompoAllTypeQueryIT {

    private static OpenEhrClient openEhrClient;
    private UUID ehr;

    /**
     * Sets .
     *
     * @throws URISyntaxException the uri syntax exception
     */
    @BeforeClass
    public static void setup() throws URISyntaxException {
        openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    }

    @After
    public void tearDown() {
        // delete the created EHR using the admin endpoint
        openEhrClient.adminEhrEndpoint().delete(ehr);
    }

    /**
     * Test save and get contribution.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testSaveAndGetContribution() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();

        String contribution = IOUtils.toString(ONE_ENTRY_COMPOSITION_LATEST.getStream(), StandardCharsets.UTF_8);
        ContributionCreateDto contributionDto =
                new CanonicalJson().unmarshal(contribution, ContributionCreateDto.class);

        ContributionEndpoint contributionEndpoint = openEhrClient.contributionEndpoint(ehr);
        Optional<Contribution> remoteContribution = contributionEndpoint.find(
                ehr,
                contributionEndpoint.mergeContributionEntity(contributionDto).getUuid());

        assertTrue(remoteContribution.isPresent());
    }

    /**
     * Test save ehr status contribution modification.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testSaveContributionEhrStatusModification() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();
        Optional<EhrStatus> ehrStatus = openEhrClient.ehrEndpoint().getEhrStatus(ehr);

        assertTrue(ehrStatus.isPresent());

        ContributionCreateDto contributionDto = createContributionWithEhrStatus(ehrStatus.get());
        ContributionEndpoint contributionEndpoint = openEhrClient.contributionEndpoint(ehr);
        VersionUid versionUid = contributionEndpoint.mergeContributionEntity(contributionDto);
        Optional<Contribution> remoteContribution = contributionEndpoint.find(ehr, versionUid.getUuid());

        assertTrue(remoteContribution.isPresent());
    }

    /**
     * Test save contribution with canonical composition modification.
     *
     * @throws IOException the io exception
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testSaveContributionWithCompositionModification() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();
        String compositionPrecedingVersionUid = getCompositionPrecedingVersionUid(ehr);

        String contributionModificationJson =
                IOUtils.toString(ONE_ENTRY_COMPOSITION_MODIFICATION_LATEST.getStream(), StandardCharsets.UTF_8);
        ContributionCreateDto contributionDto =
                new CanonicalJson().unmarshal(contributionModificationJson, ContributionCreateDto.class);
        OriginalVersion<LinkedHashMap<String, Object>> originalVersion =
                (OriginalVersion<LinkedHashMap<String, Object>>)
                        contributionDto.getVersions().get(0);

        LinkedHashMap<String, Object> composition = originalVersion.getData();

        AuditDetails compositionModificationAudit =
                contributionDto.getVersions().get(0).getCommitAudit();

        ContributionBuilder contributionBuilder = ContributionBuilder.builder(compositionModificationAudit)
                .addCompositionModification(composition, compositionPrecedingVersionUid)
                .build();

        ContributionEndpoint contributionEndpoint = openEhrClient.contributionEndpoint(ehr);
        VersionUid versionUid =
                contributionEndpoint.mergeContributionEntity(contributionBuilder.getContributionCreateDto());

        assertThat(versionUid.getVersion()).isEqualTo(1L);

        Optional<Contribution> remoteContribution = contributionEndpoint.find(ehr, versionUid.getUuid());

        assertTrue(remoteContribution.isPresent());
    }

    /**
     * Test save contribution with canonical composition deletion.
     *
     * @throws IOException the io exception
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testSaveContributionWithCompositionDeletion() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();
        String compositionPrecedingVersionUid = getContributionCompositionModified(ehr);

        String contributionModificationJson =
                IOUtils.toString(ONE_ENTRY_COMPOSITION_DELETION_LATEST.getStream(), StandardCharsets.UTF_8);
        ContributionCreateDto contributionDto =
                new CanonicalJson().unmarshal(contributionModificationJson, ContributionCreateDto.class);
        OriginalVersion<LinkedHashMap<String, Object>> originalVersion =
                (OriginalVersion<LinkedHashMap<String, Object>>)
                        contributionDto.getVersions().get(0);

        LinkedHashMap<String, Object> composition = originalVersion.getData();

        AuditDetails compositionModificationAudit =
                contributionDto.getVersions().get(0).getCommitAudit();

        ContributionBuilder contributionBuilder = ContributionBuilder.builder(compositionModificationAudit)
                .addCompositionDeletion(composition, compositionPrecedingVersionUid)
                .build();

        ContributionEndpoint contributionEndpoint = openEhrClient.contributionEndpoint(ehr);
        VersionUid versionUid =
                contributionEndpoint.mergeContributionEntity(contributionBuilder.getContributionCreateDto());

        assertThat(versionUid.getVersion()).isEqualTo(1L);

        Optional<Contribution> remoteContribution = contributionEndpoint.find(ehr, versionUid.getUuid());

        assertTrue(remoteContribution.isPresent());
        String version = remoteContribution.get().getVersions().get(0).getId().getValue();
        String updatedVersion = version.substring(version.lastIndexOf("::") + 2);

        assertEquals("3", updatedVersion);
    }

    /**
     * Test save contribution created with rm object composition.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testSaveContributionCreatedWithRMObjectComposition() throws IOException {

        ehr = openEhrClient.ehrEndpoint().createEhr();
        Unflattener cut = new Unflattener(new TestDataTemplateProvider());

        ProxyEhrbaseBloodPressureSimpleDeV0Composition proxyDto = buildProxyEhrbaseBloodPressureSimpleDeV0Composition();

        ProxyEhrbaseBloodPressureSimpleDeV0Composition comp =
                openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(proxyDto);

        assertThat(comp.getVersionUid()).isNotNull();

        AuditDetails auditDetails = getAuditDetails();

        ContributionBuilder contributionBuilder = ContributionBuilder.builder(auditDetails)
                .addCompositionCreation(cut.unflatten(comp))
                .build();

        ContributionEndpoint contributionEndpoint = openEhrClient.contributionEndpoint(ehr);
        VersionUid versionUid =
                contributionEndpoint.mergeContributionEntity(contributionBuilder.getContributionCreateDto());
        Optional<Contribution> remoteContribution = contributionEndpoint.find(ehr, versionUid.getUuid());

        assertTrue(remoteContribution.isPresent());
    }

    /**
     * Test save contribution canonical entity created by builder.
     *
     * @throws IOException the io exception
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testSaveContributionWithOriginalVersion() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();

        String contributionJson = IOUtils.toString(ONE_ENTRY_COMPOSITION_LATEST.getStream(), StandardCharsets.UTF_8);
        ContributionCreateDto contributionDto =
                new CanonicalJson().unmarshal(contributionJson, ContributionCreateDto.class);

        OriginalVersion<LinkedHashMap<String, Object>> originalVersion =
                (OriginalVersion<LinkedHashMap<String, Object>>)
                        contributionDto.getVersions().get(0);

        ContributionBuilder contributionBuilder = ContributionBuilder.builder(contributionDto.getAudit())
                .addOriginalVersionCreation(originalVersion)
                .build();

        ContributionEndpoint contributionEndpoint = openEhrClient.contributionEndpoint(ehr);
        VersionUid versionUid =
                contributionEndpoint.mergeContributionEntity(contributionBuilder.getContributionCreateDto());

        Optional<Contribution> remoteContribution = contributionEndpoint.find(ehr, versionUid.getUuid());

        assertTrue(remoteContribution.isPresent());
    }

    /**
     * Test save contribution with compositions creation.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testSaveContributionWithCompositionsCreation() throws IOException {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        Unflattener cut = new Unflattener(new TestDataTemplateProvider());

        ProxyEhrbaseBloodPressureSimpleDeV0Composition proxyDto = buildProxyEhrbaseBloodPressureSimpleDeV0Composition();

        Composition proxyComposition = (Composition) cut.unflatten(proxyDto);

        String composition = new CanonicalJson().marshal(proxyComposition);

        LinkedHashMap<String, Object> secondComposition =
                (LinkedHashMap<String, Object>) new CanonicalJson().unmarshalToMap(composition);

        OriginalVersion<LinkedHashMap<String, Object>> originalVersion = new OriginalVersion<>();
        originalVersion.setData(secondComposition);

        String contributionJson =
                IOUtils.toString(ONE_ENTRY_COMPOSITION_MODIFICATION_LATEST.getStream(), StandardCharsets.UTF_8);
        ContributionCreateDto contributionDto =
                new CanonicalJson().unmarshal(contributionJson, ContributionCreateDto.class);

        ContributionBuilder contributionBuilder = ContributionBuilder.builder(contributionDto.getAudit())
                .addCompositionCreation(proxyComposition)
                .addCompositionCreation(proxyComposition)
                .addCompositionCreation(proxyComposition)
                .addCompositionCreation(proxyComposition)
                .addOriginalVersionCreation(originalVersion)
                .addOriginalVersionCreation(originalVersion)
                .addOriginalVersionCreation(originalVersion)
                .addOriginalVersionCreation(originalVersion)
                .build();

        ContributionEndpoint contributionEndpoint = openEhrClient.contributionEndpoint(ehr);
        VersionUid versionUid =
                contributionEndpoint.mergeContributionEntity(contributionBuilder.getContributionCreateDto());
        Optional<Contribution> remoteContribution = contributionEndpoint.find(ehr, versionUid.getUuid());

        assertTrue(remoteContribution.isPresent());
    }
    /**
     * Test save contribution with canonical composition different compositions.
     *
     * @throws IOException the io exception
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testSaveContributionWithCompositionsCreationAndModification() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();
        String compositionPrecedingVersionUid = getCompositionPrecedingVersionUid(ehr);

        String contributionModificationJson =
                IOUtils.toString(ONE_ENTRY_COMPOSITION_MODIFICATION_LATEST.getStream(), StandardCharsets.UTF_8);
        ContributionCreateDto contributionDto =
                new CanonicalJson().unmarshal(contributionModificationJson, ContributionCreateDto.class);
        OriginalVersion<LinkedHashMap<String, Object>> originalVersion =
                (OriginalVersion<LinkedHashMap<String, Object>>)
                        contributionDto.getVersions().get(0);

        String contributionJson =
                IOUtils.toString(ONE_ENTRY_COMPOSITION_MODIFICATION_LATEST.getStream(), StandardCharsets.UTF_8);
        ContributionCreateDto contributionDtoCreation =
                new CanonicalJson().unmarshal(contributionJson, ContributionCreateDto.class);

        AuditDetails compositionModificationAudit =
                contributionDto.getVersions().get(0).getCommitAudit();
        originalVersion.setCommitAudit(compositionModificationAudit);

        Unflattener cut = new Unflattener(new TestDataTemplateProvider());

        ProxyEhrbaseBloodPressureSimpleDeV0Composition proxyDto = buildProxyEhrbaseBloodPressureSimpleDeV0Composition();

        Composition proxyComposition = (Composition) cut.unflatten(proxyDto);

        ContributionBuilder contributionBuilder = ContributionBuilder.builder(contributionDtoCreation.getAudit())
                .addCompositionCreation(proxyComposition)
                .addOriginalVersionModification(originalVersion, compositionPrecedingVersionUid)
                .build();

        ContributionEndpoint contributionEndpoint = openEhrClient.contributionEndpoint(ehr);
        VersionUid versionUid =
                contributionEndpoint.mergeContributionEntity(contributionBuilder.getContributionCreateDto());

        assertThat(versionUid.getVersion()).isEqualTo(1L);

        Optional<Contribution> remoteContribution = contributionEndpoint.find(ehr, versionUid.getUuid());

        assertTrue(remoteContribution.isPresent());
    }

    /**
     * Gets contribution composition modified.
     *
     * @param ehr the ehr
     * @return the contribution composition modified
     * @throws IOException the io exception
     */
    @SuppressWarnings("unchecked")
    public String getContributionCompositionModified(UUID ehr) throws IOException {
        String compositionPrecedingVersionUid = getCompositionPrecedingVersionUid(ehr);

        String contributionModificationJson =
                IOUtils.toString(ONE_ENTRY_COMPOSITION_MODIFICATION_LATEST.getStream(), StandardCharsets.UTF_8);
        ContributionCreateDto contributionDto =
                new CanonicalJson().unmarshal(contributionModificationJson, ContributionCreateDto.class);
        OriginalVersion<LinkedHashMap<String, Object>> originalVersion =
                (OriginalVersion<LinkedHashMap<String, Object>>)
                        contributionDto.getVersions().get(0);

        LinkedHashMap<String, Object> composition = originalVersion.getData();

        AuditDetails compositionModificationAudit =
                contributionDto.getVersions().get(0).getCommitAudit();

        ContributionBuilder contributionBuilder = ContributionBuilder.builder(compositionModificationAudit)
                .addCompositionModification(composition, compositionPrecedingVersionUid)
                .build();

        ContributionEndpoint contributionEndpoint = openEhrClient.contributionEndpoint(this.ehr);
        VersionUid versionUid =
                contributionEndpoint.mergeContributionEntity(contributionBuilder.getContributionCreateDto());

        assertThat(versionUid.getVersion()).isEqualTo(1L);

        Optional<Contribution> remoteContribution = contributionEndpoint.find(this.ehr, versionUid.getUuid());

        assertTrue(remoteContribution.isPresent());

        String version = remoteContribution.get().getVersions().get(0).getId().getValue();
        String updatedVersion = version.substring(version.lastIndexOf("::") + 2);

        assertEquals("2", updatedVersion);

        return version;
    }

    private String getCompositionPrecedingVersionUid(UUID ehr) throws IOException {
        aComposition = new CanonicalJson()
                .unmarshal(
                        IOUtils.toString(
                                CompositionTestDataCanonicalJson.MINIMAL_EVAL.getStream(), StandardCharsets.UTF_8),
                        Composition.class);

        Flattener flattener = new Flattener(new TestDataTemplateProvider());
        MinimalEvaluationEnV1Composition minimalEvaluationEnV1Composition =
                flattener.flatten(aComposition, MinimalEvaluationEnV1Composition.class);

        MinimalEvaluationEnV1Composition comp =
                openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(minimalEvaluationEnV1Composition);

        assertThat(comp.getVersionUid()).isNotNull();

        return comp.getVersionUid().toString();
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

    private static AuditDetails getAuditDetails() throws IOException {
        String contributionCreateJson =
                IOUtils.toString(ONE_ENTRY_COMPOSITION_LATEST.getStream(), StandardCharsets.UTF_8);
        ContributionCreateDto contributionDto =
                new CanonicalJson().unmarshal(contributionCreateJson, ContributionCreateDto.class);

        return contributionDto.getVersions().get(0).getCommitAudit();
    }
}
