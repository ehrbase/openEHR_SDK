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

import static org.ehrbase.client.TestData.buildEhrbaseBloodPressureSimpleDeV0;
import static org.ehrbase.test_data.contribution.ContributionTestDataCanonicalJson.ONE_ENTRY_COMPOSITION_LATEST;
import static org.ehrbase.test_data.contribution.ContributionTestDataCanonicalJson.STATUS_COMPOITION_MODIFICATION;
import static org.junit.Assert.assertTrue;

import com.nedap.archie.rm.changecontrol.Contribution;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.ehr.EhrStatus;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import org.apache.commons.io.IOUtils;
import org.ehrbase.client.Integration;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.EhrbaseBloodPressureSimpleDeV0Composition;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.ContributionEndpoint;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery.CanonicalCompoAllTypeQueryIT;
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

    @Test
    public void testSaveContribution() throws IOException {
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
    public void testSaveContributionWithCompositionEntity() throws IOException {
        ehr = openEhrClient.ehrEndpoint().createEhr();
        ProxyEhrbaseBloodPressureSimpleDeV0Composition proxy =
                getProxyEhrbaseBloodPressureSimpleDeV0Composition(buildEhrbaseBloodPressureSimpleDeV0());

        String contribution = IOUtils.toString(ONE_ENTRY_COMPOSITION_LATEST.getStream(), StandardCharsets.UTF_8);
        ContributionCreateDto contributionDto =
                new CanonicalJson().unmarshal(contribution, ContributionCreateDto.class);

        ContributionEndpoint contributionEndpoint = openEhrClient.contributionEndpoint(ehr);
        Optional<Contribution> remoteContribution = contributionEndpoint.find(
                ehr,
                contributionEndpoint
                        .mergeContributionEntity(contributionDto, proxy)
                        .getUuid());

        assertTrue(remoteContribution.isPresent());
    }

    private static ProxyEhrbaseBloodPressureSimpleDeV0Composition getProxyEhrbaseBloodPressureSimpleDeV0Composition(
            EhrbaseBloodPressureSimpleDeV0Composition bloodPressureSimpleDeV0) {
        ProxyEhrbaseBloodPressureSimpleDeV0Composition proxy = new ProxyEhrbaseBloodPressureSimpleDeV0Composition();

        proxy.dummy = "dummy";

        proxy.setStartTimeValue(OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC));
        proxy.setEndTimeValue(OffsetDateTime.now());
        proxy.setBloodPressureTrainingSample(new ArrayList<>());
        proxy.setLanguage(Language.DE);
        proxy.setTerritory(Territory.DE);
        proxy.setCategoryDefiningCode(org.ehrbase.client.classgenerator.shareddefinition.Category.EVENT);
        proxy.setSettingDefiningCode(Setting.NURSING_HOME_CARE);
        proxy.setComposer(new PartyIdentified(null, "Test", null));
        proxy.setParticipations(new ArrayList<>());
        proxy.getParticipations()
                .add(new Participation(new PartyIdentified(null, "Test", null), new DvText("Pos1"), null, null));
        proxy.getParticipations()
                .add(new Participation(new PartyIdentified(null, "Test2", null), new DvText("Pos2"), null, null));

        proxy.setBloodPressureTrainingSample(bloodPressureSimpleDeV0.getBloodPressureTrainingSample());
        return proxy;
    }
}
