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

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.client.classgenerator.EhrbaseBloodPressureSimpleDeV0;
import org.ehrbase.client.classgenerator.EhrbaseMultiOccurrenceDeV1;
import org.ehrbase.client.flattener.BloodpressureListDe;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

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
        bloodPressureSimpleDeV0.setLanguage(new CodePhrase(new TerminologyId("ISO_639-1"), "de"));
        bloodPressureSimpleDeV0.setTerritory(new CodePhrase(new TerminologyId("ISO_3166-1"), "UY"));
        bloodPressureSimpleDeV0.setSettingDefiningcode(new CodePhrase(new TerminologyId("openehr"), "229"));

        EhrbaseBloodPressureSimpleDeV0.BloodPressureTrainingSample bloodPressureTrainingSample = new EhrbaseBloodPressureSimpleDeV0.BloodPressureTrainingSample();
        bloodPressureTrainingSample.setSystolicMagnitude(22d);
        bloodPressureTrainingSample.setSystolicUnits("mm[Hg]");
        bloodPressureTrainingSample.setDiastolicMagnitude(22d);
        bloodPressureTrainingSample.setDiastolicUnits("mm[Hg]");
        bloodPressureTrainingSample.setMeanArterialPressureMagnitude(22d);
        bloodPressureTrainingSample.setMeanArterialPressureUnits("mm[Hg]");
        bloodPressureTrainingSample.setPulsePressureMagnitude(22d);
        bloodPressureTrainingSample.setPulsePressureUnits("mm[Hg]");
        bloodPressureTrainingSample.setKorotkoffSoundsDefiningcode(EhrbaseBloodPressureSimpleDeV0.BloodPressureTrainingSample.KorotkoffSoundsDefiningcode.FIFTHSOUND);
        bloodPressureTrainingSample.setCuffSizeDefiningcode(EhrbaseBloodPressureSimpleDeV0.BloodPressureTrainingSample.CuffSizeDefiningcode.ADULT);
        bloodPressureTrainingSample.setLocationOfMeasurementDefiningcode(EhrbaseBloodPressureSimpleDeV0.BloodPressureTrainingSample.LocationOfMeasurementDefiningcode.FINGER);
        bloodPressureSimpleDeV0.getBloodPressureTrainingSample().add(bloodPressureTrainingSample);
        return bloodPressureSimpleDeV0;
    }


    public static EhrbaseMultiOccurrenceDeV1 buildEhrbaseMultiOccurrenceDeV1() {
        EhrbaseMultiOccurrenceDeV1 dto = new EhrbaseMultiOccurrenceDeV1();
        dto.setStartTimeValue(OffsetDateTime.now());
        dto.setEndTimeValue(OffsetDateTime.now());
        dto.setLanguage(new CodePhrase(new TerminologyId("ISO_639-1"), "de"));
        dto.setTerritory(new CodePhrase(new TerminologyId("ISO_3166-1"), "UY"));
        dto.setSettingDefiningcode(new CodePhrase(new TerminologyId("openehr"), "229"));
        dto.setBodyTemperature(new ArrayList<>());
        dto.getBodyTemperature().add(new EhrbaseMultiOccurrenceDeV1.BodyTemperature());
        dto.getBodyTemperature().get(0).setHistory(new ArrayList<>());

        EhrbaseMultiOccurrenceDeV1.BodyTemperature.BodyTemperatureHistory history1 = new EhrbaseMultiOccurrenceDeV1.BodyTemperature.BodyTemperatureHistory();
        history1.setTemperatureMagnitude(22d);
        history1.setTemperatureUnits("Cel");
        history1.setCurrentDayOfMenstrualCycleMagnitude(3l);
        dto.getBodyTemperature().get(0).getHistory().add(history1);

        EhrbaseMultiOccurrenceDeV1.BodyTemperature.BodyTemperatureHistory history2 = new EhrbaseMultiOccurrenceDeV1.BodyTemperature.BodyTemperatureHistory();
        history2.setTemperatureMagnitude(11d);
        history2.setTemperatureUnits("Cel");
        history2.setCurrentDayOfMenstrualCycleMagnitude(3l);
        dto.getBodyTemperature().get(0).getHistory().add(history2);

        return dto;
    }
}
