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
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.AlternativeEventsComposition;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.AnyEventEnIntervalEvent;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.AnyEventEnPointEvent;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.BirthEnEvent;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.KorpergewichtObservation;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.EhrbaseBloodPressureSimpleDeV0Composition;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.BloodPressureTrainingSampleObservation;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.CuffSizeDefiningcode;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.KorotkoffSoundsDefiningcode;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.LocationOfMeasurementDefiningcode;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.EhrbaseMultiOccurrenceDeV1Composition;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.AnyEventPointEvent;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.BodyTemperatureObservation;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.LocationOfMeasurementDvcodedtext;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.LocationOfMeasurementDvtext;
import org.ehrbase.client.classgenerator.examples.shareddefinition.*;
import org.ehrbase.client.flattener.BloodpressureListDe;

import java.time.Duration;
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

    public static EhrbaseBloodPressureSimpleDeV0Composition buildEhrbaseBloodPressureSimpleDeV0() {
        EhrbaseBloodPressureSimpleDeV0Composition bloodPressureSimpleDeV0 = new EhrbaseBloodPressureSimpleDeV0Composition();
        bloodPressureSimpleDeV0.setStartTimeValue(OffsetDateTime.now());
        bloodPressureSimpleDeV0.setEndTimeValue(OffsetDateTime.now());
        bloodPressureSimpleDeV0.setBloodPressureTrainingSample(new ArrayList<>());
        bloodPressureSimpleDeV0.setLanguage(Language.DE);
        bloodPressureSimpleDeV0.setTerritory(Territory.DE);
        bloodPressureSimpleDeV0.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        bloodPressureSimpleDeV0.setSettingDefiningcode(SettingDefiningcode.NURSINGHOMECARE);
        bloodPressureSimpleDeV0.setComposer(new PartyIdentified(null, "Test", null));

        BloodPressureTrainingSampleObservation bloodPressureTrainingSample = new BloodPressureTrainingSampleObservation();
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


    public static EhrbaseMultiOccurrenceDeV1Composition buildEhrbaseMultiOccurrenceDeV1() {
        EhrbaseMultiOccurrenceDeV1Composition dto = new EhrbaseMultiOccurrenceDeV1Composition();
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

    private static BodyTemperatureObservation buildBodyTemperature1() {
        BodyTemperatureObservation bodyTemperature = new BodyTemperatureObservation();
        bodyTemperature.setAnyEvent(new ArrayList<>());
        LocationOfMeasurementDvcodedtext locationOfMeasurement = new LocationOfMeasurementDvcodedtext();
        locationOfMeasurement.setLocationOfMeasurementDefiningcode(org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.LocationOfMeasurementDefiningcode.FOREHEAD);
        bodyTemperature.setLocationOfMeasurement(locationOfMeasurement);

        AnyEventPointEvent history1 = new AnyEventPointEvent();
        history1.setTemperatureMagnitude(22d);
        history1.setTemperatureUnits("Cel");
        history1.setCurrentDayOfMenstrualCycleMagnitude(3l);
        bodyTemperature.getAnyEvent().add(history1);

        AnyEventPointEvent history2 = new AnyEventPointEvent();
        history2.setTemperatureMagnitude(11d);
        history2.setTemperatureUnits("Cel");
        history2.setCurrentDayOfMenstrualCycleMagnitude(3l);
        bodyTemperature.getAnyEvent().add(history2);
        return bodyTemperature;
    }

    private static BodyTemperatureObservation buildBodyTemperature2() {
        BodyTemperatureObservation bodyTemperature = new BodyTemperatureObservation();
        bodyTemperature.setAnyEvent(new ArrayList<>());
        LocationOfMeasurementDvtext locationOfMeasurement = new LocationOfMeasurementDvtext();
        locationOfMeasurement.setLocationOfMeasurementValue("location");
        bodyTemperature.setLocationOfMeasurement(locationOfMeasurement);

        AnyEventPointEvent history1 = new AnyEventPointEvent();
        history1.setTemperatureMagnitude(22d);
        history1.setTemperatureUnits("Cel");
        history1.setCurrentDayOfMenstrualCycleMagnitude(3l);
        bodyTemperature.getAnyEvent().add(history1);

        AnyEventPointEvent history2 = new AnyEventPointEvent();
        history2.setTemperatureMagnitude(11d);
        history2.setTemperatureUnits("Cel");
        history2.setCurrentDayOfMenstrualCycleMagnitude(3l);
        bodyTemperature.getAnyEvent().add(history2);
        return bodyTemperature;
    }

    public static AlternativeEventsComposition buildAlternativeEventsComposition() {
        AlternativeEventsComposition alternativeEventsComposition = new AlternativeEventsComposition();
        alternativeEventsComposition.setStartTimeValue(OffsetDateTime.of(2010, 11, 02, 12, 00, 00, 00, ZoneOffset.UTC));
        alternativeEventsComposition.setComposer(new PartyIdentified(null, "Test", null));
        alternativeEventsComposition.setLanguage(Language.EN);
        alternativeEventsComposition.setTerritory(Territory.DE);
        alternativeEventsComposition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        alternativeEventsComposition.setKorpergewicht(new ArrayList<>());
        KorpergewichtObservation korpergewichtObservation = new KorpergewichtObservation();
        alternativeEventsComposition.getKorpergewicht().add(korpergewichtObservation);

        BirthEnEvent birthEnEvent = new BirthEnEvent();
        birthEnEvent.setGewichtMagnitude(30d);
        birthEnEvent.setGewichtUnits("kg");
        birthEnEvent.setTimeValue(OffsetDateTime.of(1990, 11, 02, 12, 00, 00, 00, ZoneOffset.UTC));

        korpergewichtObservation.setBirthEn(birthEnEvent);
        korpergewichtObservation.setAnyEventEn(new ArrayList<>());

        AnyEventEnPointEvent pointEvent = new AnyEventEnPointEvent();
        pointEvent.setGewichtMagnitude(55d);
        pointEvent.setGewichtUnits("kg");
        pointEvent.setTimeValue(OffsetDateTime.of(2013, 11, 02, 12, 00, 00, 00, ZoneOffset.UTC));
        korpergewichtObservation.getAnyEventEn().add(pointEvent);

        AnyEventEnIntervalEvent intervalEvent = new AnyEventEnIntervalEvent();
        intervalEvent.setGewichtMagnitude(60d);
        intervalEvent.setGewichtUnits("kg");
        intervalEvent.setTimeValue(OffsetDateTime.of(2015, 11, 02, 12, 00, 00, 00, ZoneOffset.UTC));
        intervalEvent.setWidthValue(Duration.ofDays(30));
        intervalEvent.setMathFunctionDefiningcode(MathFunctionDefiningcode.MEAN);
        korpergewichtObservation.getAnyEventEn().add(intervalEvent);
        return alternativeEventsComposition;
    }
}
