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
package org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import org.ehrbase.openehr.sdk.client.Integration;
import org.ehrbase.openehr.sdk.client.openehrclient.OpenEhrClient;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataCanonicalJson;
import org.ehrbase.openehr.sdk.util.exception.WrongStatusCodeException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Integration.class)
public class ParticipationTestIT {

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
    @Ignore("see https://github.com/ehrbase/ehrbase/issues/710")
    public void testParticipation() throws IOException {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        Composition composition = new CanonicalJson()
                .unmarshal(
                        IOUtils.toString(
                                CompositionTestDataCanonicalJson.PARTICIPATION_NO_CONTENT.getStream(),
                                StandardCharsets.UTF_8),
                        Composition.class);

        ObjectVersionId versionUid = openEhrClient.compositionEndpoint(ehr).mergeRaw(composition);

        Optional<Composition> postedComposition = openEhrClient
                .compositionEndpoint(ehr)
                .findRaw(UUID.fromString(versionUid.getObjectId().getValue()));

        assertThat(postedComposition.get()).isNotNull();

        // check the actual participation IDs and Names
        List<Participation> participations =
                postedComposition.get().getContext().getParticipations();

        List<String> names = participations.stream()
                .map(p -> p.getPerformer())
                .map(p -> ((PartyIdentified) p).getName())
                .collect(Collectors.toList());
        assertThat(names).containsExactlyInAnyOrder("Dr. Marcus Johnson", "Zaza Markham");

        List<String> ids = participations.stream()
                .map(p -> p.getPerformer())
                .map(p -> p.getExternalRef().getId().getValue())
                .collect(Collectors.toList());
        assertThat(ids).containsExactlyInAnyOrder("000", "123");

        // use the sames IDs, but change one name. This raises an exception for conflicting identity!
        ((PartyIdentified) composition.getContext().getParticipations().get(0).getPerformer()).setName("Dummy");

        try {
            openEhrClient.compositionEndpoint(ehr).mergeRaw(composition);
            fail("Didn't detect conflicting identity!");
        } catch (WrongStatusCodeException e) {
            // continue
        }

        // Now, keep the same names, but change an externalRef id
        composition = new CanonicalJson()
                .unmarshal(
                        IOUtils.toString(
                                CompositionTestDataCanonicalJson.PARTICIPATION_NO_CONTENT.getStream(),
                                StandardCharsets.UTF_8),
                        Composition.class);
        composition
                .getContext()
                .getParticipations()
                .get(0)
                .getPerformer()
                .getExternalRef()
                .setId(new GenericId("ABC", "HOSPITAL-NS"));

        versionUid = openEhrClient.compositionEndpoint(ehr).mergeRaw(composition);

        postedComposition = openEhrClient
                .compositionEndpoint(ehr)
                .findRaw(UUID.fromString(versionUid.getObjectId().getValue()));

        assertThat(postedComposition.get()).isNotNull();

        // check the actual participation IDs and Names
        participations = postedComposition.get().getContext().getParticipations();

        names = participations.stream()
                .map(p -> p.getPerformer())
                .map(p -> ((PartyIdentified) p).getName())
                .collect(Collectors.toList());
        assertThat(names).containsExactlyInAnyOrder("Dr. Marcus Johnson", "Zaza Markham");

        ids = participations.stream()
                .map(p -> p.getPerformer())
                .map(p -> p.getExternalRef().getId().getValue())
                .collect(Collectors.toList());
        assertThat(ids).containsExactlyInAnyOrder("ABC", "123");

        // use the same name and id, but in another namespace

        composition = new CanonicalJson()
                .unmarshal(
                        IOUtils.toString(
                                CompositionTestDataCanonicalJson.PARTICIPATION_NO_CONTENT.getStream(),
                                StandardCharsets.UTF_8),
                        Composition.class);
        composition
                .getContext()
                .getParticipations()
                .get(0)
                .getPerformer()
                .getExternalRef()
                .setNamespace("ANOTHER_NAMESPACE");

        versionUid = openEhrClient.compositionEndpoint(ehr).mergeRaw(composition);

        postedComposition = openEhrClient
                .compositionEndpoint(ehr)
                .findRaw(UUID.fromString(versionUid.getObjectId().getValue()));

        assertThat(postedComposition.get()).isNotNull();

        // check the actual participation IDs and Names
        participations = postedComposition.get().getContext().getParticipations();

        List<String> namespaces = participations.stream()
                .map(p -> p.getPerformer())
                .map(p -> p.getExternalRef().getNamespace())
                .collect(Collectors.toList());
        assertThat(namespaces).containsExactlyInAnyOrder("ANOTHER_NAMESPACE", "ANOTHER-HOSPITAL-NS");
    }
}
