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
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0.EhrbaseBloodPressureSimpleDeV0;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0.definition.KorotkoffSoundsDefiningcode;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1.EhrbaseMultiOccurrenceDeV1;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1.definition.*;
import org.ehrbase.client.classgenerator.examples.shareddefinition.SettingDefiningcode;
import org.ehrbase.client.exception.OptimisticLockException;
import org.ehrbase.client.openehrclient.CompositionEndpoint;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.VersionUid;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

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
        EhrbaseBloodPressureSimpleDeV0 bloodPressureSimpleDeV0 = TestData.buildEhrbaseBloodPressureSimpleDeV0();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(bloodPressureSimpleDeV0);
        assertThat(bloodPressureSimpleDeV0.getVersionUid()).isNotNull();
        assertThat(bloodPressureSimpleDeV0.getVersionUid().getVersion()).isEqualTo(1L);

        bloodPressureSimpleDeV0.setSettingDefiningcode(SettingDefiningcode.EMERGENCYCARE);
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
        EhrbaseBloodPressureSimpleDeV0 bloodPressureSimpleDeV0 = TestData.buildEhrbaseBloodPressureSimpleDeV0();

        CompositionEndpoint compositionEndpoint = openEhrClient.compositionEndpoint(ehr);
        EhrbaseBloodPressureSimpleDeV0 version1 = compositionEndpoint.mergeCompositionEntity(bloodPressureSimpleDeV0);

        Optional<EhrbaseBloodPressureSimpleDeV0> actual = compositionEndpoint.find(version1.getVersionUid().getUuid(), EhrbaseBloodPressureSimpleDeV0.class);
        assertTrue(actual.isPresent());

        assertThat(actual.get().getComposer()).isNotNull().extracting(Object::getClass).isEqualTo(PartyIdentified.class);

        PartyIdentified composer = (PartyIdentified) actual.get().getComposer();
        assertThat(composer.getName()).isEqualTo("Test");

        assertThat(actual.get().getBloodPressureTrainingSample()).size().isEqualTo(1);
        assertThat(actual.get().getBloodPressureTrainingSample().get(0).getSubject()).isNotNull().extracting(Object::getClass).isEqualTo(PartySelf.class);
        assertThat(actual.get().getBloodPressureTrainingSample().get(0).getSystolicMagnitude()).isEqualTo(22d);
        assertThat(actual.get().getBloodPressureTrainingSample().get(0).getSystolicUnits()).isEqualTo("mm[Hg]");
        assertThat(actual.get().getBloodPressureTrainingSample().get(0).getKorotkoffSoundsDefiningcode()).isEqualTo(KorotkoffSoundsDefiningcode.FIFTHSOUND);
    }

    @Test
    public void testEhrbaseMultiOccurrenceDeV1() {

        UUID ehr = openEhrClient.ehrEndpoint().createEhr();
        EhrbaseMultiOccurrenceDeV1 bloodPressureSimpleDeV0 = TestData.buildEhrbaseMultiOccurrenceDeV1();

        CompositionEndpoint compositionEndpoint = openEhrClient.compositionEndpoint(ehr);
        EhrbaseMultiOccurrenceDeV1 version1 = compositionEndpoint.mergeCompositionEntity(bloodPressureSimpleDeV0);

        Optional<EhrbaseMultiOccurrenceDeV1> actual = compositionEndpoint.find(version1.getVersionUid().getUuid(), EhrbaseMultiOccurrenceDeV1.class);
        assertTrue(actual.isPresent());
        BodyTemperature bodyTemperature1 = actual.get().getBodyTemperature().get(0);
        assertThat(bodyTemperature1.getHistory())
                .extracting(BodyTemperatureHistory::getTemperatureMagnitude)
                .containsExactlyInAnyOrder(11d, 22d);

        ProtocolLocationOfMeasurementChoice locationOfMeasurement1 = bodyTemperature1.getLocationOfMeasurement();
        assertThat(locationOfMeasurement1.getClass()).isEqualTo(ProtocolLocationOfMeasurementDvcodedtext.class);
        assertThat(((ProtocolLocationOfMeasurementDvcodedtext) locationOfMeasurement1).getLocationOfMeasurementDefiningcode()).isEqualTo(LocationOfMeasurementDefiningcode.FOREHEAD);

        BodyTemperature bodyTemperature2 = actual.get().getBodyTemperature().get(1);
        ProtocolLocationOfMeasurementChoice locationOfMeasurement2 = bodyTemperature2.getLocationOfMeasurement();
        assertThat(locationOfMeasurement2.getClass()).isEqualTo(ProtocolLocationOfMeasurementDvtext.class);
        assertThat(((ProtocolLocationOfMeasurementDvtext) locationOfMeasurement2).getLocationOfMeasurementValue()).isEqualTo("location");

    }


}