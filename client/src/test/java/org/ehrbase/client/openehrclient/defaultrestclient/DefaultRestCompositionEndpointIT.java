/*
 *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  This file is part of Project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.client.openehrclient.defaultrestclient;

import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.Integration;
import org.ehrbase.client.TestData;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.EhrbaseBloodPressureSimpleDeV0Composition;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.KorotkoffSoundsDefiningcode;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.EhrbaseMultiOccurrenceDeV1Composition;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.*;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.EpisodeOfCareComposition;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition.EpisodeofcareAdminEntry;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition.EpisodeofcareTeamElement;
import org.ehrbase.client.classgenerator.examples.shareddefinition.SettingDefiningcode;
import org.ehrbase.client.exception.OptimisticLockException;
import org.ehrbase.client.openehrclient.CompositionEndpoint;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.VersionUid;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@Category(Integration.class)
public class DefaultRestCompositionEndpointIT {

    private static OpenEhrClient openEhrClient;

    @BeforeClass
    public static void setup() throws URISyntaxException {
        openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    }

    @Test
    public void testSaveCompositionEntity() {

        UUID ehr = openEhrClient.ehrEndpoint().createEhr();
        EhrbaseBloodPressureSimpleDeV0Composition bloodPressureSimpleDeV0 = TestData.buildEhrbaseBloodPressureSimpleDeV0();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(bloodPressureSimpleDeV0);
        assertThat(bloodPressureSimpleDeV0.getVersionUid()).isNotNull();
        assertThat(bloodPressureSimpleDeV0.getVersionUid().getVersion()).isEqualTo(1L);

        bloodPressureSimpleDeV0.setSettingDefiningcode(SettingDefiningcode.EMERGENCY_CARE);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(bloodPressureSimpleDeV0);
        assertThat(bloodPressureSimpleDeV0.getVersionUid()).isNotNull();
        assertThat(bloodPressureSimpleDeV0.getVersionUid().getVersion()).isEqualTo(2L);

        bloodPressureSimpleDeV0.setVersionUid(
                new VersionUid(bloodPressureSimpleDeV0.getVersionUid().getUuid(),
                        bloodPressureSimpleDeV0.getVersionUid().getSystem(),
                        1L));

        try {
            openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(bloodPressureSimpleDeV0);
            fail();
        } catch (RuntimeException e) {
            assertThat(e.getClass()).isEqualTo(OptimisticLockException.class);
        }
    }

    @Test
    public void testFind() {

        UUID ehr = openEhrClient.ehrEndpoint().createEhr();
        EhrbaseBloodPressureSimpleDeV0Composition bloodPressureSimpleDeV0 = TestData.buildEhrbaseBloodPressureSimpleDeV0();

        CompositionEndpoint compositionEndpoint = openEhrClient.compositionEndpoint(ehr);
        EhrbaseBloodPressureSimpleDeV0Composition version1 = compositionEndpoint.mergeCompositionEntity(bloodPressureSimpleDeV0);

        Optional<EhrbaseBloodPressureSimpleDeV0Composition> actual = compositionEndpoint.find(version1.getVersionUid().getUuid(), EhrbaseBloodPressureSimpleDeV0Composition.class);
        assertTrue(actual.isPresent());

        assertThat(actual.get().getComposer()).isNotNull().extracting(Object::getClass).isEqualTo(PartyIdentified.class);

        PartyIdentified composer = (PartyIdentified) actual.get().getComposer();
        assertThat(composer.getName()).isEqualTo("Test");

        assertThat(actual.get().getBloodPressureTrainingSample()).size().isEqualTo(1);
        assertThat(actual.get().getBloodPressureTrainingSample().get(0).getSubject()).isNotNull().extracting(Object::getClass).isEqualTo(PartySelf.class);
        assertThat(actual.get().getBloodPressureTrainingSample().get(0).getSystolicMagnitude()).isEqualTo(22d);
        assertThat(actual.get().getBloodPressureTrainingSample().get(0).getSystolicUnits()).isEqualTo("mm[Hg]");
        assertThat(actual.get().getBloodPressureTrainingSample().get(0).getKorotkoffSoundsDefiningcode()).isEqualTo(KorotkoffSoundsDefiningcode.FIFTH_SOUND);
    }

    @Test
    public void testEhrbaseMultiOccurrenceDeV1() {

        UUID ehr = openEhrClient.ehrEndpoint().createEhr();
        EhrbaseMultiOccurrenceDeV1Composition bloodPressureSimpleDeV0 = TestData.buildEhrbaseMultiOccurrenceDeV1();

        CompositionEndpoint compositionEndpoint = openEhrClient.compositionEndpoint(ehr);
        EhrbaseMultiOccurrenceDeV1Composition version1 = compositionEndpoint.mergeCompositionEntity(bloodPressureSimpleDeV0);

        Optional<EhrbaseMultiOccurrenceDeV1Composition> actual = compositionEndpoint.find(version1.getVersionUid().getUuid(), EhrbaseMultiOccurrenceDeV1Composition.class);
        assertTrue(actual.isPresent());
        BodyTemperatureObservation bodyTemperature1 = actual.get().getBodyTemperature().get(0);
        assertThat(bodyTemperature1.getAnyEvent())
                .extracting(e -> ((BodyTemperatureAnyEventPointEvent) e).getTemperatureMagnitude())
                .containsExactlyInAnyOrder(11d, 22d);

        BodyTemperatureLocationOfMeasurementChoice locationOfMeasurement1 = bodyTemperature1.getLocationOfMeasurement();
        assertThat(locationOfMeasurement1.getClass()).isEqualTo(BodyTemperatureLocationOfMeasurementDvcodedtext.class);
        assertThat(((BodyTemperatureLocationOfMeasurementDvcodedtext) locationOfMeasurement1).getLocationOfMeasurementDefiningcode()).isEqualTo(LocationOfMeasurementDefiningcode.FOREHEAD);

        BodyTemperatureObservation bodyTemperature2 = actual.get().getBodyTemperature().get(1);
        BodyTemperatureLocationOfMeasurementChoice locationOfMeasurement2 = bodyTemperature2.getLocationOfMeasurement();
        assertThat(locationOfMeasurement2.getClass()).isEqualTo(BodyTemperatureLocationOfMeasurementDvtext.class);
        assertThat(((BodyTemperatureLocationOfMeasurementDvtext) locationOfMeasurement2).getLocationOfMeasurementValue()).isEqualTo("location");

    }

    @Test
    public void testEpisodeOfCare() {

        UUID ehr = openEhrClient.ehrEndpoint().createEhr();
        EpisodeOfCareComposition bloodPressureSimpleDeV0 = TestData.buildEpisodeOfCareComposition();

        CompositionEndpoint compositionEndpoint = openEhrClient.compositionEndpoint(ehr);
        EpisodeOfCareComposition version1 = compositionEndpoint.mergeCompositionEntity(bloodPressureSimpleDeV0);

        Optional<EpisodeOfCareComposition> actual = compositionEndpoint.find(version1.getVersionUid().getUuid(), EpisodeOfCareComposition.class);
        assertTrue(actual.isPresent());
        assertThat(actual.get().getVersionUid()).extracting(v -> v.getUuid().toString(), VersionUid::getSystem, VersionUid::getVersion).containsExactly(version1.getVersionUid().getUuid().toString(), "local.ehrbase.org", 1L);
        assertThat(actual.get().getEpisodeofcare()).size().isEqualTo(1);

        EpisodeofcareAdminEntry episodeofcareAdminEntry = actual.get().getEpisodeofcare().get(0);

        assertThat(episodeofcareAdminEntry.getIdentifier()).extracting(e -> e.getValue().getId()).containsExactlyInAnyOrder("123", "456");

        assertThat(episodeofcareAdminEntry.getTeam()).extracting(EpisodeofcareTeamElement::getValue).containsExactlyInAnyOrder(URI.create("https://github.com/ehrbase"));

    }

}