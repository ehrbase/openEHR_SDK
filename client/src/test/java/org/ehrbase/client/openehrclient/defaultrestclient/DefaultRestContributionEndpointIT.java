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
import static org.ehrbase.test_data.contribution.ContributionTestDataCanonicalJson.ONE_ENTRY_COMPOSITION_LATEST;
import static org.ehrbase.test_data.contribution.ContributionTestDataCanonicalJson.STATUS_COMPOITION_MODIFICATION;
import static org.junit.Assert.assertTrue;

import com.nedap.archie.rm.changecontrol.Contribution;
import com.nedap.archie.rm.changecontrol.OriginalVersion;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.ehr.EhrStatus;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.UUID;
import org.apache.commons.io.IOUtils;
import org.ehrbase.client.Integration;
import org.ehrbase.client.flattener.Unflattener;
import org.ehrbase.client.openehrclient.ContributionEndpoint;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.client.openehrclient.builder.ContributionBuilder;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.CanonicalCompoAllTypeQueryIT;
import org.ehrbase.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.response.openehr.ContributionCreateDto;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
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

        ContributionEndpoint contributionEndpoint = openEhrClient.contributionEndpoint(ehr);
        Optional<Contribution> remoteContribution = contributionEndpoint.find(
                ehr,
                contributionEndpoint.mergeContributionEntity(contributionDto).getUuid());

        assertTrue(remoteContribution.isPresent());
    }

    @Test
    public void testSaveContributionCreatedWithCanonicalComposition() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();

        String contributionJson = IOUtils.toString(ONE_ENTRY_COMPOSITION_LATEST.getStream(), StandardCharsets.UTF_8);
        ContributionCreateDto contributionDto =
                new CanonicalJson().unmarshal(contributionJson, ContributionCreateDto.class);
        OriginalVersion<LinkedHashMap<String, Object>> originalVersion =
                (OriginalVersion<LinkedHashMap<String, Object>>)
                        contributionDto.getVersions().get(0);

        LinkedHashMap<String, Object> composition = originalVersion.getData();

        ContributionBuilder contributionBuilder =
                ContributionBuilder.builder().addComposition(composition).build();

        ContributionEndpoint contributionEndpoint = openEhrClient.contributionEndpoint(ehr);
        VersionUid versionUid =
                contributionEndpoint.mergeContributionEntity(contributionBuilder.getContributionCreateDto());

        Optional<Contribution> remoteContribution = contributionEndpoint.find(ehr, versionUid.getUuid());

        assertTrue(remoteContribution.isPresent());
    }

    @Test
    public void testSaveContributionCreatedWithRMObjectComposition() throws IOException {

        ehr = openEhrClient.ehrEndpoint().createEhr();
        Unflattener cut = new Unflattener(new TestDataTemplateProvider());

        ProxyEhrbaseBloodPressureSimpleDeV0Composition proxyDto = buildProxyEhrbaseBloodPressureSimpleDeV0Composition();

        Composition proxyComposition = (Composition) cut.unflatten(proxyDto);

        ContributionBuilder contributionBuilder =
                ContributionBuilder.builder().addComposition(proxyComposition).build();

        ContributionEndpoint contributionEndpoint = openEhrClient.contributionEndpoint(ehr);
        VersionUid versionUid =
                contributionEndpoint.mergeContributionEntity(contributionBuilder.getContributionCreateDto());
        Optional<Contribution> remoteContribution = contributionEndpoint.find(ehr, versionUid.getUuid());

        assertTrue(remoteContribution.isPresent());
    }

    @Test
    public void testSaveDefaultContributionEntityCreatedByBuilderWithCanonicalCompositionWithName() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();

        String contributionJson = IOUtils.toString(ONE_ENTRY_COMPOSITION_LATEST.getStream(), StandardCharsets.UTF_8);
        ContributionCreateDto contributionDto =
                new CanonicalJson().unmarshal(contributionJson, ContributionCreateDto.class);
        OriginalVersion<LinkedHashMap<String, Object>> originalVersion =
                (OriginalVersion<LinkedHashMap<String, Object>>)
                        contributionDto.getVersions().get(0);

        LinkedHashMap<String, Object> composition = originalVersion.getData();

        ContributionBuilder contributionBuilder = ContributionBuilder.builder()
                .addComposition(composition, "<optional name of the committer>")
                .build();

        ContributionEndpoint contributionEndpoint = openEhrClient.contributionEndpoint(ehr);
        VersionUid versionUid =
                contributionEndpoint.mergeContributionEntity(contributionBuilder.getContributionCreateDto());

        Optional<Contribution> remoteContribution = contributionEndpoint.find(ehr, versionUid.getUuid());

        assertTrue(remoteContribution.isPresent());
    }

    @Test
    public void testSaveContributionEntitySuccess() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();

        String contributionJson = IOUtils.toString(ONE_ENTRY_COMPOSITION_LATEST.getStream(), StandardCharsets.UTF_8);
        ContributionCreateDto contributionDto =
                new CanonicalJson().unmarshal(contributionJson, ContributionCreateDto.class);

        LinkedHashMap<String, Object> composition = (LinkedHashMap<String, Object>)
                contributionDto.getVersions().get(0).getData();

        ContributionBuilder contributionBuilder = ContributionBuilder.builder()
                .addComposition(composition, "test")
                .addHierObjectId(contributionDto.getUid())
                .addContributionAuditDetails(contributionDto.getAudit())
                .build();

        ContributionEndpoint contributionEndpoint = openEhrClient.contributionEndpoint(ehr);
        VersionUid versionUid =
                contributionEndpoint.mergeContributionEntity(contributionBuilder.getContributionCreateDto());

        Optional<Contribution> remoteContribution = contributionEndpoint.find(ehr, versionUid.getUuid());

        assertTrue(remoteContribution.isPresent());
    }

    @Test
    public void testSaveContributionCanonicalEntityCreatedByBuilder() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();

        String contributionJson = IOUtils.toString(ONE_ENTRY_COMPOSITION_LATEST.getStream(), StandardCharsets.UTF_8);
        ContributionCreateDto contributionDto =
                new CanonicalJson().unmarshal(contributionJson, ContributionCreateDto.class);

        OriginalVersion<LinkedHashMap<String, Object>> originalVersion =
                (OriginalVersion<LinkedHashMap<String, Object>>)
                        contributionDto.getVersions().get(0);

        ContributionBuilder contributionBuilder = ContributionBuilder.builder()
                .addOriginalVersion(originalVersion)
                .addHierObjectId(contributionDto.getUid())
                .addContributionAuditDetails(contributionDto.getAudit())
                .build();

        ContributionEndpoint contributionEndpoint = openEhrClient.contributionEndpoint(ehr);
        VersionUid versionUid =
                contributionEndpoint.mergeContributionEntity(contributionBuilder.getContributionCreateDto());

        Optional<Contribution> remoteContribution = contributionEndpoint.find(ehr, versionUid.getUuid());

        assertTrue(remoteContribution.isPresent());
    }

    @Test
    public void removeME_addEverythingJustForTestPurposes() throws IOException {

        ehr = openEhrClient.ehrEndpoint().createEhr();
        Unflattener cut = new Unflattener(new TestDataTemplateProvider());

        ProxyEhrbaseBloodPressureSimpleDeV0Composition proxyDto = buildProxyEhrbaseBloodPressureSimpleDeV0Composition();

        Composition proxyComposition = (Composition) cut.unflatten(proxyDto);

        String composition = new CanonicalJson().marshal(proxyComposition);

        LinkedHashMap<String, Object> secondComposition =
                (LinkedHashMap<String, Object>) new CanonicalJson().unmarshalToMap(composition);

        OriginalVersion<LinkedHashMap<String, Object>> originalVersion = new OriginalVersion<>();
        originalVersion.setData(secondComposition);

        String contributionJson = IOUtils.toString(ONE_ENTRY_COMPOSITION_LATEST.getStream(), StandardCharsets.UTF_8);
        ContributionCreateDto contributionDto =
                new CanonicalJson().unmarshal(contributionJson, ContributionCreateDto.class);

        ContributionBuilder contributionBuilder = ContributionBuilder.builder()
                .addComposition(proxyComposition)
                .addOriginalVersion(originalVersion)
                .addHierObjectId(contributionDto.getUid())
                .addContributionAuditDetails(contributionDto.getAudit())
                .addComposition(
                        (LinkedHashMap<String, Object>)
                                contributionDto.getVersions().get(0).getData(),
                        "<optional name of the committer>")
                .addContributionCommitterName("<optional name of the committer>")
                .build();

        ContributionEndpoint contributionEndpoint = openEhrClient.contributionEndpoint(ehr);
        VersionUid versionUid =
                contributionEndpoint.mergeContributionEntity(contributionBuilder.getContributionCreateDto());
        Optional<Contribution> remoteContribution = contributionEndpoint.find(ehr, versionUid.getUuid());

        assertTrue(remoteContribution.isPresent());
    }

    @Test
    public void testSaveEhrStatusContributionModification() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();
        Optional<EhrStatus> ehrStatus = openEhrClient.ehrEndpoint().getEhrStatus(ehr);

        assertTrue(ehrStatus.isPresent());

        ContributionCreateDto contributionDto = createContributionWithEhrStatus(ehrStatus.get());
        ContributionEndpoint contributionEndpoint = openEhrClient.contributionEndpoint(ehr);
        VersionUid versionUid = contributionEndpoint.mergeContributionEntity(contributionDto);
        Optional<Contribution> remoteContribution = contributionEndpoint.find(ehr, versionUid.getUuid());

        assertTrue(remoteContribution.isPresent());
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
