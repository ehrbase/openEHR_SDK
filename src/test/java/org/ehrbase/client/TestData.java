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
package org.ehrbase.client;

import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.HierObjectId;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0.EhrbaseBloodPressureSimpleDeV0;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0.definition.BloodPressureTrainingSample;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0.definition.CuffSizeDefiningcode;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0.definition.KorotkoffSoundsDefiningcode;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0.definition.LocationOfMeasurementDefiningcode;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1.EhrbaseMultiOccurrenceDeV1;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1.definition.BodyTemperature;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1.definition.BodyTemperatureHistory;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1.definition.ProtocolLocationOfMeasurementDvcodedtext;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1.definition.ProtocolLocationOfMeasurementDvtext;
import org.ehrbase.client.classgenerator.examples.shareddefinition.CategoryDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.examples.shareddefinition.SettingDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Territory;
import org.ehrbase.client.flattener.BloodpressureListDe;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestData {
    private TestData() {
    }

    public static BloodpressureListDe buildExampleBloodpressureListDe() {
        BloodpressureListDe dto = new BloodpressureListDe();
        OffsetDateTime startTime = OffsetDateTime.of(2019, 9, 10, 12, 0, 0, 0, ZoneOffset.ofHours(2));
        dto.setStartTime(startTime);
        List<BloodpressureListDe.Bloodpressure> bloodpressureList = new ArrayList<>();

        BloodpressureListDe.Bloodpressure bloodpressure1 = new BloodpressureListDe.Bloodpressure();
        bloodpressure1.setSystolischValue(12d);
        bloodpressureList.add(bloodpressure1);

        BloodpressureListDe.Bloodpressure bloodpressure2 = new BloodpressureListDe.Bloodpressure();
        bloodpressure2.setSystolischValue(22d);
        bloodpressureList.add(bloodpressure2);

        dto.setBloodpressures(bloodpressureList);
        return dto;
    }

    public static EhrbaseBloodPressureSimpleDeV0 buildEhrbaseBloodPressureSimpleDeV0() {
        EhrbaseBloodPressureSimpleDeV0 bloodPressureSimpleDeV0 = new EhrbaseBloodPressureSimpleDeV0();
        bloodPressureSimpleDeV0.setStartTimeValue(OffsetDateTime.now());
        bloodPressureSimpleDeV0.setEndTimeValue(OffsetDateTime.now());
        bloodPressureSimpleDeV0.setBloodPressureTrainingSample(new ArrayList<>());
        bloodPressureSimpleDeV0.setLanguage(Language.DE);
        bloodPressureSimpleDeV0.setTerritory(Territory.DE);
        bloodPressureSimpleDeV0.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        bloodPressureSimpleDeV0.setSettingDefiningcode(SettingDefiningcode.NURSINGHOMECARE);
        bloodPressureSimpleDeV0.setComposer(new PartyIdentified(null, "Test", null));

        BloodPressureTrainingSample bloodPressureTrainingSample = new BloodPressureTrainingSample();
        bloodPressureTrainingSample.setSubject(new PartySelf());
        bloodPressureTrainingSample.setSystolicMagnitude(22d);
        bloodPressureTrainingSample.setSystolicUnits("mm[Hg]");
        bloodPressureTrainingSample.setDiastolicMagnitude(22d);
        bloodPressureTrainingSample.setDiastolicUnits("mm[Hg]");
        bloodPressureTrainingSample.setMeanArterialPressureMagnitude(22d);
        bloodPressureTrainingSample.setMeanArterialPressureUnits("mm[Hg]");
        bloodPressureTrainingSample.setPulsePressureMagnitude(22d);
        bloodPressureTrainingSample.setPulsePressureUnits("mm[Hg]");
        bloodPressureTrainingSample.setKorotkoffSoundsDefiningcode(KorotkoffSoundsDefiningcode.FIFTHSOUND);
        bloodPressureTrainingSample.setCuffSizeDefiningcode(CuffSizeDefiningcode.ADULT);
        bloodPressureTrainingSample.setLocationOfMeasurementDefiningcode(LocationOfMeasurementDefiningcode.FINGER);
        bloodPressureSimpleDeV0.getBloodPressureTrainingSample().add(bloodPressureTrainingSample);
        return bloodPressureSimpleDeV0;
    }

    private static PartyRef buildPartyRef() {
        return new PartyRef(new HierObjectId(UUID.randomUUID().toString()), "local", "type");
    }

    public static EhrbaseMultiOccurrenceDeV1 buildEhrbaseMultiOccurrenceDeV1() {
        EhrbaseMultiOccurrenceDeV1 dto = new EhrbaseMultiOccurrenceDeV1();
        dto.setStartTimeValue(OffsetDateTime.now());
        dto.setEndTimeValue(OffsetDateTime.now());
        dto.setLanguage(Language.DE);
        dto.setTerritory(Territory.DE);
        dto.setSettingDefiningcode(SettingDefiningcode.DENTALCARE);
        dto.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        dto.setComposer(new PartyIdentified(null, "Test", null));
        dto.setBodyTemperature(new ArrayList<>());

        dto.getBodyTemperature().add(buildBodyTemperature1());
        dto.getBodyTemperature().add(buildBodyTemperature2());
        return dto;
    }

    private static BodyTemperature buildBodyTemperature1() {
        BodyTemperature bodyTemperature = new BodyTemperature();
        bodyTemperature.setHistory(new ArrayList<>());
        ProtocolLocationOfMeasurementDvcodedtext locationOfMeasurement = new ProtocolLocationOfMeasurementDvcodedtext();
        locationOfMeasurement.setLocationOfMeasurementDefiningcode(org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1.definition.LocationOfMeasurementDefiningcode.FOREHEAD);
        bodyTemperature.setLocationOfMeasurement(locationOfMeasurement);

        BodyTemperatureHistory history1 = new BodyTemperatureHistory();
        history1.setTemperatureMagnitude(22d);
        history1.setTemperatureUnits("Cel");
        history1.setCurrentDayOfMenstrualCycleMagnitude(3l);
        bodyTemperature.getHistory().add(history1);

        BodyTemperatureHistory history2 = new BodyTemperatureHistory();
        history2.setTemperatureMagnitude(11d);
        history2.setTemperatureUnits("Cel");
        history2.setCurrentDayOfMenstrualCycleMagnitude(3l);
        bodyTemperature.getHistory().add(history2);
        return bodyTemperature;
    }

    private static BodyTemperature buildBodyTemperature2() {
        BodyTemperature bodyTemperature = new BodyTemperature();
        bodyTemperature.setHistory(new ArrayList<>());
        ProtocolLocationOfMeasurementDvtext locationOfMeasurement = new ProtocolLocationOfMeasurementDvtext();
        locationOfMeasurement.setLocationOfMeasurementValue("location");
        bodyTemperature.setLocationOfMeasurement(locationOfMeasurement);

        BodyTemperatureHistory history1 = new BodyTemperatureHistory();
        history1.setTemperatureMagnitude(22d);
        history1.setTemperatureUnits("Cel");
        history1.setCurrentDayOfMenstrualCycleMagnitude(3l);
        bodyTemperature.getHistory().add(history1);

        BodyTemperatureHistory history2 = new BodyTemperatureHistory();
        history2.setTemperatureMagnitude(11d);
        history2.setTemperatureUnits("Cel");
        history2.setCurrentDayOfMenstrualCycleMagnitude(3l);
        bodyTemperature.getHistory().add(history2);
        return bodyTemperature;
    }
}
