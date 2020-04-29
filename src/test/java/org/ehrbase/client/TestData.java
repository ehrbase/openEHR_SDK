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
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.AlternativeEventsComposition;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.KorpergewichtAnyEventEnIntervalEvent;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.KorpergewichtAnyEventEnPointEvent;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.KorpergewichtBirthEnEvent;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.KorpergewichtObservation;
import org.ehrbase.client.classgenerator.examples.beatmungcomposition.BeatmungComposition;
import org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition.*;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.CoronaAnamneseComposition;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.*;
import org.ehrbase.client.classgenerator.examples.diagnosecomposition.DiagnoseComposition;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.EhrbaseBloodPressureSimpleDeV0Composition;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.BloodPressureTrainingSampleObservation;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.CuffSizeDefiningcode;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.KorotkoffSoundsDefiningcode;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.LocationOfMeasurementDefiningcode;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.EhrbaseMultiOccurrenceDeV1Composition;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.BodyTemperatureAnyEventPointEvent;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.BodyTemperatureLocationOfMeasurementDvcodedtext;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.BodyTemperatureLocationOfMeasurementDvtext;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.BodyTemperatureObservation;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.EpisodeOfCareComposition;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition.EpisodeofcareAdminEntry;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition.EpisodeofcareIdentifierElement;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition.EpisodeofcareTeamElement;
import org.ehrbase.client.classgenerator.examples.kennzeichnungerregernachweissarscov2composition.KennzeichnungErregernachweisSARSCoV2Composition;
import org.ehrbase.client.classgenerator.examples.kennzeichnungerregernachweissarscov2composition.definition.KennzeichnungErregernachweisEvaluation;
import org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.OpenEHRSuspectedCOVID19RiskAssessmentV0Composition;
import org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition.*;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.PatientenaufenthaltComposition;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.AufenthaltsdatenAdminEntry;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.StandortCluster;
import org.ehrbase.client.classgenerator.examples.shareddefinition.*;
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.StationarerVersorgungsfallComposition;
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition.EntlassungsdatenAdminEntry;
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition.KlinischerZustandDesPatientenDefiningcode;
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition.PatientenaufnahmeAdminEntry;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.VirologischerBefundComposition;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition.*;
import org.ehrbase.client.flattener.BloodpressureListDe;

import java.net.URI;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

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
        bloodPressureSimpleDeV0.setStartTimeValue(OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC));
        bloodPressureSimpleDeV0.setEndTimeValue(OffsetDateTime.now());
        bloodPressureSimpleDeV0.setBloodPressureTrainingSample(new ArrayList<>());
        bloodPressureSimpleDeV0.setLanguage(Language.DE);
        bloodPressureSimpleDeV0.setTerritory(Territory.DE);
        bloodPressureSimpleDeV0.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        bloodPressureSimpleDeV0.setSettingDefiningcode(SettingDefiningcode.NURSINGHOMECARE);
        bloodPressureSimpleDeV0.setComposer(new PartyIdentified(null, "Test", null));
        bloodPressureSimpleDeV0.setParticipations(new ArrayList<>());
        bloodPressureSimpleDeV0.getParticipations().add(new Participation(new PartyIdentified(null, "Test", null), new DvText("Pos1"), null, null));
        bloodPressureSimpleDeV0.getParticipations().add(new Participation(new PartyIdentified(null, "Test2", null), new DvText("Pos2"), null, null));

        BloodPressureTrainingSampleObservation bloodPressureTrainingSample = new BloodPressureTrainingSampleObservation();
        bloodPressureTrainingSample.setSubject(new PartySelf());
        bloodPressureTrainingSample.setOriginValue(OffsetDateTime.now());
        bloodPressureTrainingSample.setTimeValue(OffsetDateTime.now());
        bloodPressureTrainingSample.setLanguage(Language.DE);
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
        bodyTemperature.setLanguage(Language.DE);
        bodyTemperature.setSubject(new PartySelf());
        bodyTemperature.setOriginValue(OffsetDateTime.now());
        bodyTemperature.setAnyEvent(new ArrayList<>());
        BodyTemperatureLocationOfMeasurementDvcodedtext locationOfMeasurement = new BodyTemperatureLocationOfMeasurementDvcodedtext();
        locationOfMeasurement.setLocationOfMeasurementDefiningcode(org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.LocationOfMeasurementDefiningcode.FOREHEAD);
        bodyTemperature.setLocationOfMeasurement(locationOfMeasurement);

        BodyTemperatureAnyEventPointEvent history1 = new BodyTemperatureAnyEventPointEvent();
        history1.setTemperatureMagnitude(22d);
        history1.setTemperatureUnits("Cel");
        history1.setCurrentDayOfMenstrualCycleMagnitude(3l);
        history1.setTimeValue(OffsetDateTime.now());
        bodyTemperature.getAnyEvent().add(history1);

        BodyTemperatureAnyEventPointEvent history2 = new BodyTemperatureAnyEventPointEvent();
        history2.setTemperatureMagnitude(11d);
        history2.setTemperatureUnits("Cel");
        history2.setCurrentDayOfMenstrualCycleMagnitude(3l);
        history2.setTimeValue(OffsetDateTime.now());
        bodyTemperature.getAnyEvent().add(history2);
        return bodyTemperature;
    }

    private static BodyTemperatureObservation buildBodyTemperature2() {
        BodyTemperatureObservation bodyTemperature = new BodyTemperatureObservation();
        bodyTemperature.setLanguage(Language.DE);
        bodyTemperature.setSubject(new PartySelf());
        bodyTemperature.setOriginValue(OffsetDateTime.now());
        bodyTemperature.setAnyEvent(new ArrayList<>());
        BodyTemperatureLocationOfMeasurementDvtext locationOfMeasurement = new BodyTemperatureLocationOfMeasurementDvtext();
        locationOfMeasurement.setLocationOfMeasurementValue("location");
        bodyTemperature.setLocationOfMeasurement(locationOfMeasurement);

        BodyTemperatureAnyEventPointEvent history1 = new BodyTemperatureAnyEventPointEvent();
        history1.setTemperatureMagnitude(22d);
        history1.setTemperatureUnits("Cel");
        history1.setCurrentDayOfMenstrualCycleMagnitude(3l);
        history1.setTimeValue(OffsetDateTime.now());
        bodyTemperature.getAnyEvent().add(history1);

        BodyTemperatureAnyEventPointEvent history2 = new BodyTemperatureAnyEventPointEvent();
        history2.setTemperatureMagnitude(11d);
        history2.setTemperatureUnits("Cel");
        history2.setCurrentDayOfMenstrualCycleMagnitude(3l);
        history2.setTimeValue(OffsetDateTime.now());
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

        KorpergewichtBirthEnEvent birthEnEvent = new KorpergewichtBirthEnEvent();
        birthEnEvent.setGewichtMagnitude(30d);
        birthEnEvent.setGewichtUnits("kg");
        birthEnEvent.setTimeValue(OffsetDateTime.of(1990, 11, 02, 12, 00, 00, 00, ZoneOffset.UTC));

        korpergewichtObservation.setBirthEn(birthEnEvent);
        korpergewichtObservation.setAnyEventEn(new ArrayList<>());

        KorpergewichtAnyEventEnPointEvent pointEvent = new KorpergewichtAnyEventEnPointEvent();
        pointEvent.setGewichtMagnitude(55d);
        pointEvent.setGewichtUnits("kg");
        pointEvent.setTimeValue(OffsetDateTime.of(2013, 11, 02, 12, 00, 00, 00, ZoneOffset.UTC));
        korpergewichtObservation.getAnyEventEn().add(pointEvent);

        KorpergewichtAnyEventEnIntervalEvent intervalEvent = new KorpergewichtAnyEventEnIntervalEvent();
        intervalEvent.setGewichtMagnitude(60d);
        intervalEvent.setGewichtUnits("kg");
        intervalEvent.setTimeValue(OffsetDateTime.of(2015, 11, 02, 12, 00, 00, 00, ZoneOffset.UTC));
        intervalEvent.setWidthValue(Duration.ofDays(30));
        intervalEvent.setMathFunctionDefiningcode(MathFunctionDefiningcode.MEAN);
        korpergewichtObservation.getAnyEventEn().add(intervalEvent);
        return alternativeEventsComposition;
    }

    public static EpisodeOfCareComposition buildEpisodeOfCareComposition() {
        EpisodeOfCareComposition episode = new EpisodeOfCareComposition();
        episode.setComposer(new PartyIdentified(null, "Test", null));
        episode.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        episode.setLanguage(Language.DE);
        episode.setTerritory(Territory.DE);
        episode.setEpisodeofcare(new ArrayList<>());
        episode.setStartTimeValue(OffsetDateTime.now());
        episode.setSettingDefiningcode(SettingDefiningcode.NURSINGHOMECARE);

        EpisodeofcareAdminEntry episodeofcareAdminEntry = new EpisodeofcareAdminEntry();

        DvInterval<DvDateTime> periode = new DvInterval<>();
        periode.setLower(new DvDateTime(OffsetDateTime.now()));
        periode.setUpper(new DvDateTime(OffsetDateTime.now()));
        episodeofcareAdminEntry.setPeriod(periode);
        episodeofcareAdminEntry.setLanguage(Language.DE);
        episodeofcareAdminEntry.setSubject(new PartySelf());
        episodeofcareAdminEntry.setIdentifier(new ArrayList<>());
        EpisodeofcareIdentifierElement identifierElement = new EpisodeofcareIdentifierElement();
        DvIdentifier value = new DvIdentifier();
        value.setId("123");
        identifierElement.setValue(value);
        episodeofcareAdminEntry.getIdentifier().add(identifierElement);

        EpisodeofcareIdentifierElement identifierElement2 = new EpisodeofcareIdentifierElement();
        DvIdentifier value2 = new DvIdentifier();
        value2.setId("456");
        identifierElement2.setValue(value2);
        episodeofcareAdminEntry.getIdentifier().add(identifierElement2);

        episodeofcareAdminEntry.setTeam(new ArrayList<>());
        EpisodeofcareTeamElement teamElement1 = new EpisodeofcareTeamElement();
        teamElement1.setValue(URI.create("https://github.com/ehrbase"));
        episodeofcareAdminEntry.getTeam().add(teamElement1);


        episode.getEpisodeofcare().add(episodeofcareAdminEntry);
        return episode;
    }

    public static BeatmungComposition buildBeatmungsComposition() {

        BeatmungComposition composition = new BeatmungComposition();
        composition.setComposer(new PartyIdentified(null, "Test", null));
        composition.setLanguage(Language.DE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(OffsetDateTime.of(2020, 04, 02, 12, 00, 00, 00, ZoneOffset.UTC));
        composition.setSettingDefiningcode(SettingDefiningcode.NURSINGHOMECARE);

        BeobachtungenAmBeatmungsgeratObservation observation = new BeobachtungenAmBeatmungsgeratObservation();
        observation.setLanguage(Language.DE);
        observation.setSubject(new PartySelf());

        MesswerteCluster messwerteCluster = new MesswerteCluster();
        messwerteCluster.setArtDerBeatmungDefiningcode(ArtDerBeatmungDefiningcode.INVASIV);
        messwerteCluster.setBeatmungsformDefiningcode(BeatmungsformDefiningcode.HFO);
        messwerteCluster.setHeaterGenutztValue(true);

        LuftsauerstoffFrequenzElement element = new LuftsauerstoffFrequenzElement();
        element.setUnits("Hz");
        element.setMagnitude(200d);

        DvCodedText name = new DvCodedText();
        name.setValue("Atemfrequenz");

        CodePhrase phrase = new CodePhrase();
        phrase.setCodeString("at0010");
        TerminologyId terminologyId = new TerminologyId();
        terminologyId.setValue("local");
        phrase.setTerminologyId(terminologyId);
        name.setDefiningCode(phrase);

        element.setName(name);

        List<LuftsauerstoffFrequenzElement> elementList = new ArrayList<>();


        elementList.add(element);
        messwerteCluster.setFrequenz(elementList);

        observation.setMesswerte(messwerteCluster);
        observation.setTimeValue(OffsetDateTime.of(2020, 04, 02, 12, 00, 00, 00, ZoneOffset.UTC));
        observation.setOriginValue(OffsetDateTime.of(2020, 04, 02, 12, 00, 00, 00, ZoneOffset.UTC));

        List<BeobachtungenAmBeatmungsgeratObservation> list = new ArrayList<>();
        list.add(observation);
        composition.setBeobachtungenAmBeatmungsgerat(list);

        MedizingeratCluster medizingeratCluster = new MedizingeratCluster();
        medizingeratCluster.setGeratenameValue("EVITA XL");
        medizingeratCluster.setHerstellerValue("Siemens");
        //medizingeratCluster.setEindeutigeIdentifikationsnummerId(new DvIdentifier().setId("dfdgfdggfhghgfj")));

        observation.setMedizingerat(medizingeratCluster);

        return composition;
    }

   /* public static OpenEHRSuspectedCOVID19RiskAssessmentV0Composition buildCovidRiskAssessmentComposition() {

        OpenEHRSuspectedCOVID19RiskAssessmentV0Composition covidRISK = new OpenEHRSuspectedCOVID19RiskAssessmentV0Composition();

        covidRISK.setComposer(new PartyIdentified(null, "Test", null));
        covidRISK.setLanguage(Language.DE);
        covidRISK.setTerritory(Territory.DE);
        covidRISK.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        covidRISK.setStartTimeValue(OffsetDateTime.of(2020, 04, 02, 12, 00, 00, 00, ZoneOffset.UTC));
        covidRISK.setSettingDefiningcode(SettingDefiningcode.NURSINGHOMECARE);

        //Add Patient's history
        HistoryObservation history = new HistoryObservation();
        history.setStoryValue("Der Sohn des Patienten fuhr in Ischgl Ski und wurde vor zwei Wochen positiv getestet");
        history.setTimeValue(OffsetDateTime.of(2020, 04, 02, 12, 00, 00, 00, ZoneOffset.UTC));
        history.setSubject(new PartySelf());
        history.setLanguage(Language.EN);
        history.setOriginValue(OffsetDateTime.of(2020, 04, 02, 12, 00, 00, 00, ZoneOffset.UTC));
        List<HistoryObservation> historyObservationList = new ArrayList<>();
        historyObservationList.add(history);
        covidRISK.setHistory(historyObservationList);

        //Set Symptom/sign screening questionnaire
        SignScreeningQuestionnaireObservation signScreening = new SignScreeningQuestionnaireObservation();

        SymptomSignScreeningQuestionnaireAnyEventPointEvent screeningEvent = new SymptomSignScreeningQuestionnaireAnyEventPointEvent();
        screeningEvent.setScreeningPurposeDefiningcode(ScreeningPurposeDefiningcode.CODE840544004);
        screeningEvent.setTimeValue(OffsetDateTime.of(2020, 04, 02, 12, 00, 00, 00, ZoneOffset.UTC));

        SymptomSignScreeningQuestionnaireSignClusterSpecificSymptomTree signInfluenca = new SymptomSignScreeningQuestionnaireSignClusterSpecificSymptomTree();
        signInfluenca.setSymptomOrSignNameDefiningcode(SymptomOrSignNameDefiningcode.CODE49727002);
        signInfluenca.setOnsetValue(OffsetDateTime.of(2020, 04, 02, 12, 00, 00, 00, ZoneOffset.UTC));
        signInfluenca.setPresenceDefiningcode(PresenceDefiningcode.PRESENT);

        SymptomSignScreeningQuestionnaireSignClusterSpecificSymptomTree signCough= new SymptomSignScreeningQuestionnaireSignClusterSpecificSymptomTree();
        signCough.setSymptomOrSignNameDefiningcode(SymptomOrSignNameDefiningcode.CODE386661006);
        signCough.setOnsetValue(OffsetDateTime.of(2020, 04, 02, 12, 00, 00, 00, ZoneOffset.UTC));
        signCough.setPresenceDefiningcode(PresenceDefiningcode.PRESENT);

        SymptomSignScreeningQuestionnaireSignClusterSpecificSymptomTree signFever= new SymptomSignScreeningQuestionnaireSignClusterSpecificSymptomTree();
        signFever.setSymptomOrSignNameDefiningcode(SymptomOrSignNameDefiningcode.CODE49727002);
        signFever.setOnsetValue(OffsetDateTime.of(2020, 04, 02, 12, 00, 00, 00, ZoneOffset.UTC));
        signFever.setPresenceDefiningcode(PresenceDefiningcode.ABSENT);

        List<SymptomSignScreeningQuestionnaireSignClusterSpecificSymptomTree> signList = new ArrayList<>();
        signList.add(signInfluenca);
        signList.add(signCough);
        signList.add(signFever);

        screeningEvent.setSign(signList);

        List<SymptomSignScreeningQuestionnaireAnyEventPointEvent> signScreeningQuestionnaireAnyEventPointEventList = new ArrayList<>();
        signScreeningQuestionnaireAnyEventPointEventList.add(screeningEvent);

        signScreening.setAnyEvent(signScreeningQuestionnaireAnyEventPointEventList);
        signScreening.setOriginValue(OffsetDateTime.of(2020, 04, 02, 12, 00, 00, 00, ZoneOffset.UTC));
        signScreening.setSubject(new PartySelf());
        signScreening.setLanguage(Language.EN);

        List<SignScreeningQuestionnaireObservation> signScreeningQuestionnaireObservationList = new ArrayList<>();
        signScreeningQuestionnaireObservationList.add(signScreening);
        covidRISK.setSignScreeningQuestionnaire(signScreeningQuestionnaireObservationList);

        return covidRISK;

    }*/


    public static CoronaAnamneseComposition buildCoronaAnamnese(){

        CoronaAnamneseComposition composition = new CoronaAnamneseComposition();
        composition.setLanguage(Language.DE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setComposer(new PartyIdentified(null, "Test", null));
        composition.setStartTimeValue(OffsetDateTime.of(2020, 04, 02, 12, 00, 00, 00, ZoneOffset.UTC));
        composition.setSettingDefiningcode(SettingDefiningcode.NURSINGHOMECARE);

        SymptomeSection symptomeSection = new SymptomeSection();
        symptomeSection.setName(new DvText("Symptome"));

        AnzeichenObservation observation = new AnzeichenObservation();
        observation.setSubject(new PartySelf());
        observation.setBezeichnungDesSymptomsOderAnzeichensValue("Husten");
        observation.setLanguage(Language.DE);
        observation.setOriginValue(OffsetDateTime.of(2020, 04, 02, 12, 00, 00, 00, ZoneOffset.UTC));
        observation.setTimeValue(OffsetDateTime.of(2020, 04, 02, 12, 00, 00, 00, ZoneOffset.UTC));
        observation.setVorhandenDefiningcode(VorhandenDefiningcode.NICHTVORHANDEN);
        observation.setName(new DvText("Husten?"));

        symptomeSection.setAnzeichen(observation);
        composition.setSymptome(symptomeSection);

        AnzeichenObservationScreeningFragebogenZurSymptomen symptome = new AnzeichenObservationScreeningFragebogenZurSymptomen();
        symptome.setSubject(new PartySelf());
        symptome.setBezeichnungDesSymptomsOderAnzeichensValue("Weitere Anzeichen");
        symptome.setLanguage(Language.DE);
        symptome.setOriginValue(OffsetDateTime.of(2020, 04, 02, 12, 00, 00, 00, ZoneOffset.UTC));
        symptome.setTimeValue(OffsetDateTime.of(2020, 04, 02, 12, 00, 00, 00, ZoneOffset.UTC));
        symptome.setVorhandenDefiningcode(VorhandenDefiningcode.NICHTVORHANDEN);
        symptome.setName(new DvText("Weitere Anzeichen"));

        symptomeSection.setAnzeichenScreeningFragebogenZurSymptomen(symptome);

        AllgmeineAngabenSection allgmeineAngabenSection = new AllgmeineAngabenSection();
        allgmeineAngabenSection.setName(new DvText("Allgemeine Angaben"));

        DiagnoseEvaluationProblem diagnose = new DiagnoseEvaluationProblem();
        diagnose.setDerDiagnoseValue("Freitextdiagnose");
        diagnose.setLanguage(Language.DE);
        diagnose.setSubject(new PartySelf());
        diagnose.setName(new DvText("Diagnose"));

        allgmeineAngabenSection.setDiagnoseProblem(diagnose);
        composition.setAllgmeineAngaben(allgmeineAngabenSection);

        return composition;
    }



    public static  VirologischerBefundComposition buildVirologischerBefundComposition(OffsetDateTime of) {

        VirologischerBefundComposition composition = new VirologischerBefundComposition();
        composition.setLanguage(Language.DE);
        composition.setTerritory(Territory.DE);
        composition.setComposer(new PartyIdentified(null, "Test", null));
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(of);
        composition.setSettingDefiningcode(SettingDefiningcode.SECONDARYMEDICALCARE);

        composition.setBerichtIdValue(UUID.randomUUID().toString());
        composition.setStatusValue("Endbefund");

        FallidentifikationCluster fallidentifikationCluster = new FallidentifikationCluster();
        fallidentifikationCluster.setFallKennungValue(String.valueOf(ThreadLocalRandom.current().nextInt(100000, 9999999)));
        composition.setFallidentifikation(fallidentifikationCluster);
        fallidentifikationCluster.setName(new DvText("Fallnummer"));

        LaborergebnisObservation virologischesLaborergebnis = new LaborergebnisObservation();
        virologischesLaborergebnis.setName(new DvText("Laborergebnis"));
        virologischesLaborergebnis.setSubject(new PartySelf());

        virologischesLaborergebnis.setLanguage(Language.DE);
        virologischesLaborergebnis.setTimeValue(of.plusHours(ThreadLocalRandom.current().nextInt(1, 48)));
        virologischesLaborergebnis.setLabortestBezeichnungValue("Virologische Untersuchung");
        virologischesLaborergebnis.setOriginValue(of);

        //Add Anforderung
        StandortOriginalerNameDerAngefordertenTestungElement anforderung = new StandortOriginalerNameDerAngefordertenTestungElement();
        List<StandortOriginalerNameDerAngefordertenTestungElement> angefordertenTestungElementList = new ArrayList<>();
        angefordertenTestungElementList.add(anforderung);
        virologischesLaborergebnis.setOriginalerNameDerAngefordertenTestung(angefordertenTestungElementList);
        anforderung.setName(new DvText("Anforderung"));

        //Auftrags-ID
        DvIdentifier auftragsID = new DvIdentifier();
        auftragsID.setId(UUID.randomUUID().toString());
        auftragsID.setIssuer("SAP ish med");
        virologischesLaborergebnis.setAuftragsIdEmpfanger(auftragsID);

        //Empfänger-ID
        DvIdentifier receiverID = new DvIdentifier();
        receiverID.setId(UUID.randomUUID().toString());
        receiverID.setIssuer("SAP ish med");
        virologischesLaborergebnis.setEinsendendenSystems(receiverID);

        //Probe Cluster
        ProbeCluster probeCluster = new ProbeCluster();
        probeCluster.setZeitpunktDerProbenentnahmeValue(of.minusDays(ThreadLocalRandom.current().nextInt(1, 3)));
        probeCluster.setZeitpunktDesProbeneingangsValue(of.minusHours(ThreadLocalRandom.current().nextInt(1, 12)));
        probeCluster.setProbenartValue("Blut");
        probeCluster.setName(new DvText("Probe"));
        probeCluster.setKommentarValue("Kommentar zur Probe");
        probeCluster.setKommentarDesProbennehmersValue("Kommentar");

        List<ProbeCluster> probeClusterList = new ArrayList<>();
        probeClusterList.add(probeCluster);
        virologischesLaborergebnis.setProbe(probeClusterList);

        //Resultat Cluster
        LaboranalytResultatCluster resultatCluster = new LaboranalytResultatCluster();

        resultatCluster.setName(new DvText("Probe"));
        LaboranalytResultatAnalytResultatDvtext text = new LaboranalytResultatAnalytResultatDvtext();

        resultatCluster.setUntersuchterAnalytValueName("Blut");

        LaboranalytResultatAnalytResultatDvquantity resultatDvquantity = new LaboranalytResultatAnalytResultatDvquantity();
       // resultatDvquantity.setName(new DvText("Testergebnis"));
        resultatDvquantity.setAnalytResultatMagnitude(ThreadLocalRandom.current().nextDouble(20, 45));
        resultatDvquantity.setAnalytResultatUnits("ct");

        if(resultatDvquantity.getAnalytResultatMagnitude() > 35) {
            text.setAnalytResultatValue("positiv");
        }else  text.setAnalytResultatValue("negativ");

        resultatCluster.setAnalytResultat(text);
        resultatCluster.setAnalytResultat(resultatDvquantity);
        resultatCluster.setUntersuchterAnalytValue("SARS-Cov-2");
       //resultatCluster.setAnalytResultatName(new DvText("CT Wert"));

        resultatCluster.setAnalytResultatName(new DvText("Wert"));

        List<LaboranalytResultatCluster> labortestPanelClusterListe = new ArrayList<>();
        labortestPanelClusterListe.add(resultatCluster);

        //Labortest Cluster
        LabortestPanelCluster panel = new LabortestPanelCluster();
        panel.setName(new DvText("Test Panel"));

        panel.setLaboranalytResultat(labortestPanelClusterListe);

        List<LabortestPanelCluster> panelClusterList = new ArrayList<>();
        panelClusterList.add(panel);

        virologischesLaborergebnis.setLabortestPanel(panelClusterList);
        composition.setLaborergebnis(virologischesLaborergebnis);

        return composition;
    }

    public static PatientenaufenthaltComposition buildPatientenAufenthaltComposition(OffsetDateTime of) {

        PatientenaufenthaltComposition composition = new PatientenaufenthaltComposition();
        composition.setLanguage(Language.DE);
        composition.setTerritory(Territory.DE);
        composition.setComposer(new PartyIdentified(null, "Test", null));
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(of);
        composition.setSettingDefiningcode(SettingDefiningcode.SECONDARYMEDICALCARE);
        composition.setName(new DvText("Patientenaufenthalt"));

        AufenthaltsdatenAdminEntry aufenthalt = new AufenthaltsdatenAdminEntry();
        aufenthalt.setSubject(new PartySelf());
        aufenthalt.setBeginnValue(of);
        aufenthalt.setEndeValue(of.plusDays(2));
        aufenthalt.setLanguage(Language.DE);
        aufenthalt.setName(new DvText("Aufenthalt"));

        StandortCluster standortCluster = new StandortCluster();

        StandortschlusselDefiningcode standort = StandortschlusselDefiningcode.values()[(int)(Math.random()*StandortschlusselDefiningcode.values().length)];
        standortCluster.setStandortschlusselDefiningcode(standort);
        standortCluster.setName(new DvText("Standort"));
        standortCluster.setStandortbeschreibungValue("Beschreibung");
        standortCluster.setStandorttypValue("Test");
        standortCluster.setStationValue(String.valueOf(ThreadLocalRandom.current().nextInt(1, 10)));
        standortCluster.setBettstellplatzValue(String.valueOf(ThreadLocalRandom.current().nextInt(1, 5)));
        standortCluster.setBettstellplatzValueName("Bettstellplatz");
        aufenthalt.setStandort(standortCluster);
        composition.setAufenthaltsdaten(aufenthalt);

        return composition;
    }


    public static StationarerVersorgungsfallComposition buildVersorgungsfallComposition(OffsetDateTime of, int fallID) {

        StationarerVersorgungsfallComposition composition = new StationarerVersorgungsfallComposition();
        composition.setLanguage(Language.DE);
        composition.setTerritory(Territory.DE);
        composition.setComposer(new PartyIdentified(null, "Test", null));
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(of);
        composition.setSettingDefiningcode(SettingDefiningcode.SECONDARYMEDICALCARE);
        composition.setFallIdValue(String.valueOf(fallID));
        composition.setName(new DvText("Versorgungsfall"));
        composition.setFallIdValueBaum("Fall-ID");
        composition.setFallIdValue(UUID.randomUUID().toString());

        PatientenaufnahmeAdminEntry aufnahme = new PatientenaufnahmeAdminEntry();
        aufnahme.setName(new DvText("Aufnahme"));
        aufnahme.setLanguage(Language.DE);
        aufnahme.setSubject(new PartySelf());
        aufnahme.setUhrzeitDerAufnahmeValue(of);

        EntlassungsdatenAdminEntry entlassung = new EntlassungsdatenAdminEntry();
        entlassung.setName(new DvText("Entlassung"));
        entlassung.setUhrzeitValue("Entlassungsdatum");
        entlassung.setLanguage(Language.DE);
        entlassung.setSubject(new PartySelf());
        entlassung.setUhrzeitValueEntlassungsdatum(of.plusMonths(2));
        entlassung.setKlinischerZustandDesPatientenDefiningcode(KlinischerZustandDesPatientenDefiningcode.UNBESTIMMT);

        composition.setPatientenaufnahme(aufnahme);
        composition.setEntlassungsdaten(entlassung);

        return composition;
    }


    public static KennzeichnungErregernachweisSARSCoV2Composition buildCovid19Flag(OffsetDateTime of) {

        KennzeichnungErregernachweisSARSCoV2Composition composition = new KennzeichnungErregernachweisSARSCoV2Composition();
        composition.setLanguage(Language.DE);
        composition.setTerritory(Territory.DE);
        composition.setComposer(new PartyIdentified(null, "Test", null));
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(of);
        composition.setSettingDefiningcode(SettingDefiningcode.NURSINGHOMECARE);

        KennzeichnungErregernachweisEvaluation erregernachweis = new KennzeichnungErregernachweisEvaluation();
        erregernachweis.setLanguage(Language.DE);
        erregernachweis.setSubject(new PartySelf());
        erregernachweis.setErregernachweisValue(true);
        erregernachweis.setErregernameValue("SARS-CoV-2");
        erregernachweis.setZeitpunktDerKennzeichnungValue(of);


        erregernachweis.setZuletztAktualisiertValue(of);
        composition.setKennzeichnungErregernachweis(erregernachweis);

        return composition;
    }


    public static DiagnoseComposition buildDiagnose(OffsetDateTime of) {

        DiagnoseComposition composition = new DiagnoseComposition();
        composition.setLanguage(Language.DE);
        composition.setTerritory(Territory.DE);
        composition.setComposer(new PartyIdentified(null, "Test", null));
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(of);
        composition.setSettingDefiningcode(SettingDefiningcode.SECONDARYMEDICALCARE);

        org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition.DiagnoseEvaluation diagnose = new org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition.DiagnoseEvaluation();
        diagnose.setSubject(new PartySelf());
        diagnose.setLanguage(Language.DE);
        diagnose.setZeitpunktDerKlinischenFeststellungValueDatum("Zeitpunkt der Feststellung");
        diagnose.setZeitpunktDerKlinischenFeststellungValue(of.minusHours(4));
        diagnose.setDerDiagnoseDefiningcode(DerDiagnoseDefiningcode.U072); //
        diagnose.setDerErstdiagnoseValueZeitpunktDesAuftretens("Zeitpunkt des Auftretens");
        diagnose.setDerErstdiagnoseValue(of.minusHours(4));
        composition.setDiagnose(diagnose);

        return composition;
    }


}
