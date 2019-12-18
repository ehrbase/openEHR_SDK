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

package org.ehrbase.client.flattener;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Composition;
import org.apache.commons.io.IOUtils;
import org.ehrbase.client.TestData;
import org.ehrbase.client.classgenerator.EhrbaseBloodPressureSimpleDeV0;
import org.ehrbase.client.classgenerator.EhrbaseMultiOccurrenceDeV1;
import org.ehrbase.client.classgenerator.TestAllTypesEnV1;
import org.ehrbase.serialisation.CanonicalXML;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalXML;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

public class FlattenerTest {


    @Test
    public void testFlatten() {
        Flattener cut = new Flattener();
        BloodpressureListDe bloodpressureListDe = TestData.buildExampleBloodpressureListDe();

        RMObject rmObject = new Unflattener(new TestDataTemplateProvider()).unflatten(bloodpressureListDe);

        BloodpressureListDe expected = cut.flatten((Locatable) rmObject, BloodpressureListDe.class);

        assertThat(expected).isNotNull();
        assertThat(expected.getStartTime()).isEqualTo(bloodpressureListDe.getStartTime());
        assertThat(expected.getBloodpressures()).extracting(BloodpressureListDe.Bloodpressure::getSystolischValue).containsExactlyInAnyOrder(12d, 22d);

    }


    @Test
    public void testFlattenEhrbaseBloodPressureSimpleDeV0() {
        Flattener cut = new Flattener();
        EhrbaseBloodPressureSimpleDeV0 bloodPressureSimpleDeV0 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        RMObject rmObject = new Unflattener(new TestDataTemplateProvider()).unflatten(bloodPressureSimpleDeV0);

        EhrbaseBloodPressureSimpleDeV0 actual = cut.flatten((Locatable) rmObject, EhrbaseBloodPressureSimpleDeV0.class);

        assertThat(actual).isNotNull();
        assertThat(actual.getBloodPressureTrainingSample()).size().isEqualTo(1);
        assertThat(actual.getBloodPressureTrainingSample().get(0).getSystolicMagnitude()).isEqualTo(22d);
        assertThat(actual.getBloodPressureTrainingSample().get(0).getSystolicUnits()).isEqualTo("mm[Hg]");
        assertThat(actual.getBloodPressureTrainingSample().get(0).getKorotkoffSoundsDefiningcode()).isEqualTo(EhrbaseBloodPressureSimpleDeV0.BloodPressureTrainingSample.KorotkoffSoundsDefiningcode.FIFTHSOUND);

    }


    @Test
    public void testFlattenEhrbaseMultiOccurrenceDeV1() {
        Flattener cut = new Flattener();
        EhrbaseMultiOccurrenceDeV1 bloodPressureSimpleDeV0 = TestData.buildEhrbaseMultiOccurrenceDeV1();
        RMObject rmObject = new Unflattener(new TestDataTemplateProvider()).unflatten(bloodPressureSimpleDeV0);

        EhrbaseMultiOccurrenceDeV1 actual = cut.flatten((Locatable) rmObject, EhrbaseMultiOccurrenceDeV1.class);

        assertThat(actual).isNotNull();
        assertThat(actual.getBodyTemperature()).size().isEqualTo(2);
        EhrbaseMultiOccurrenceDeV1.BodyTemperature bodyTemperature1 = actual.getBodyTemperature().get(0);
        assertThat(bodyTemperature1.getHistory())
                .extracting(EhrbaseMultiOccurrenceDeV1.BodyTemperature.BodyTemperatureHistory::getTemperatureMagnitude)
                .containsExactlyInAnyOrder(11d, 22d);

        EhrbaseMultiOccurrenceDeV1.BodyTemperature.ProtocolLocationOfMeasurementChoice locationOfMeasurement1 = bodyTemperature1.getLocationOfMeasurement();
        assertThat(locationOfMeasurement1.getClass()).isEqualTo(EhrbaseMultiOccurrenceDeV1.BodyTemperature.ProtocolLocationOfMeasurementDvcodedtext.class);
        assertThat(((EhrbaseMultiOccurrenceDeV1.BodyTemperature.ProtocolLocationOfMeasurementDvcodedtext) locationOfMeasurement1).getLocationOfMeasurementDefiningcode()).isEqualTo(EhrbaseMultiOccurrenceDeV1.BodyTemperature.ProtocolLocationOfMeasurementDvcodedtext.LocationOfMeasurementDefiningcode.FOREHEAD);

        EhrbaseMultiOccurrenceDeV1.BodyTemperature bodyTemperature2 = actual.getBodyTemperature().get(1);
        EhrbaseMultiOccurrenceDeV1.BodyTemperature.ProtocolLocationOfMeasurementChoice locationOfMeasurement2 = bodyTemperature2.getLocationOfMeasurement();
        assertThat(locationOfMeasurement2.getClass()).isEqualTo(EhrbaseMultiOccurrenceDeV1.BodyTemperature.ProtocolLocationOfMeasurementDvtext.class);
        assertThat(((EhrbaseMultiOccurrenceDeV1.BodyTemperature.ProtocolLocationOfMeasurementDvtext) locationOfMeasurement2).getLocationOfMeasurementValue()).isEqualTo("location");

    }

    @Test
    public void testFlattenAllTypes() throws IOException {
        Composition composition = new CanonicalXML().unmarshal(IOUtils.toString(CompositionTestDataCanonicalXML.ALL_TYPES.getStream(), StandardCharsets.UTF_8), Composition.class);
        Flattener cut = new Flattener();
        TestAllTypesEnV1 actual = cut.flatten(composition, TestAllTypesEnV1.class);
        assertThat(actual).isNotNull();
        assertThat(actual.getTestAllTypes().get(0).getChoice().getClass()).isEqualTo(TestAllTypesEnV1.TestAllTypes.ArbolChoiceDvcount.class);
        assertThat(((TestAllTypesEnV1.TestAllTypes.ArbolChoiceDvcount) actual.getTestAllTypes().get(0).getChoice()).getChoiceMagnitude()).isEqualTo(148L);
    }
}