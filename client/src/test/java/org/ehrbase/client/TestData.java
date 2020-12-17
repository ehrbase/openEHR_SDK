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

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.archetyped.FeederAuditDetails;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartySelf;
import java.net.URI;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.AlternativeEventsComposition;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.KorpergewichtAnyEventEnIntervalEvent;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.KorpergewichtAnyEventEnPointEvent;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.KorpergewichtBirthEnPointEvent;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.KorpergewichtObservation;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.EhrbaseBloodPressureSimpleDeV0Composition;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.BloodPressureTrainingSampleObservation;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.CuffSizeDefiningCode;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.KorotkoffSoundsDefiningCode;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.LocationOfMeasurementDefiningCode;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.EhrbaseMultiOccurrenceDeV1Composition;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.BodyTemperatureAnyEventPointEvent;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.BodyTemperatureLocationOfMeasurementDvCodedText;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.BodyTemperatureLocationOfMeasurementDvText;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.BodyTemperatureObservation;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.EpisodeOfCareComposition;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition.EpisodeofcareAdminEntry;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition.EpisodeofcareIdentifierElement;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition.EpisodeofcareTeamElement;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.PatientenaufenthaltComposition;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.StandortCluster;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.StandortschlusselDefiningCode;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.VersorgungsortAdminEntry;
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.StationarerVersorgungsfallComposition;
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition.AufnahmedatenAdminEntry;
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition.EntlassungsdatenAdminEntry;
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition.KlinischerZustandDesPatientenDefiningCode;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.VirologischerBefundComposition;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition.BefundObservation;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition.FallidentifikationCluster;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition.KulturCluster;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition.ProVirusCluster;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition.ProbeCluster;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.MathFunction;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.flattener.BloodpressureListDe;

public class TestData {
  private TestData() {}

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
    EhrbaseBloodPressureSimpleDeV0Composition bloodPressureSimpleDeV0 =
        new EhrbaseBloodPressureSimpleDeV0Composition();
    bloodPressureSimpleDeV0.setStartTimeValue(
        OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC));
    bloodPressureSimpleDeV0.setEndTimeValue(OffsetDateTime.now());
    bloodPressureSimpleDeV0.setBloodPressureTrainingSample(new ArrayList<>());
    bloodPressureSimpleDeV0.setLanguage(Language.DE);
    bloodPressureSimpleDeV0.setTerritory(Territory.DE);
    bloodPressureSimpleDeV0.setCategoryDefiningCode(Category.EVENT);
    bloodPressureSimpleDeV0.setSettingDefiningCode(Setting.NURSING_HOME_CARE);
    bloodPressureSimpleDeV0.setComposer(new PartyIdentified(null, "Test", null));
    bloodPressureSimpleDeV0.setParticipations(new ArrayList<>());
    bloodPressureSimpleDeV0
        .getParticipations()
        .add(
            new Participation(
                new PartyIdentified(null, "Test", null), new DvText("Pos1"), null, null));
    bloodPressureSimpleDeV0
        .getParticipations()
        .add(
            new Participation(
                new PartyIdentified(null, "Test2", null), new DvText("Pos2"), null, null));

    bloodPressureSimpleDeV0
        .getBloodPressureTrainingSample()
        .add(buildBloodPressureTrainingSampleObservation());
    return bloodPressureSimpleDeV0;
  }

  protected static BloodPressureTrainingSampleObservation
      buildBloodPressureTrainingSampleObservation() {
    BloodPressureTrainingSampleObservation bloodPressureTrainingSample =
        new BloodPressureTrainingSampleObservation();
    bloodPressureTrainingSample.setSubject(new PartySelf());
    bloodPressureTrainingSample.setOriginValue(OffsetDateTime.now());
    bloodPressureTrainingSample.setTimeValue(OffsetDateTime.now());
    bloodPressureTrainingSample.setLanguage(Language.DE);
    bloodPressureTrainingSample.setSystolicMagnitude(22d);
    bloodPressureTrainingSample.setSystolicUnits("mm[Hg]");
    bloodPressureTrainingSample.setDiastolicMagnitude(22d);
    bloodPressureTrainingSample.setDiastolicUnits("mm[Hg]");
    bloodPressureTrainingSample.setMeanArterialPressureNullFlavourDefiningCode(NullFlavour.MASKED);
    bloodPressureTrainingSample.setMeanArterialPressureMagnitude(22d);
    bloodPressureTrainingSample.setMeanArterialPressureUnits("mm[Hg]");
    bloodPressureTrainingSample.setPulsePressureMagnitude(22d);
    bloodPressureTrainingSample.setPulsePressureUnits("mm[Hg]");
    bloodPressureTrainingSample.setKorotkoffSoundsDefiningCode(
        KorotkoffSoundsDefiningCode.FIFTH_SOUND);
    bloodPressureTrainingSample.setCuffSizeDefiningCode(CuffSizeDefiningCode.ADULT);
    bloodPressureTrainingSample.setLocationOfMeasurementDefiningCode(
        LocationOfMeasurementDefiningCode.FINGER);
    return bloodPressureTrainingSample;
  }

  public static EhrbaseMultiOccurrenceDeV1Composition buildEhrbaseMultiOccurrenceDeV1() {
    EhrbaseMultiOccurrenceDeV1Composition dto = new EhrbaseMultiOccurrenceDeV1Composition();
    dto.setStartTimeValue(OffsetDateTime.now());
    dto.setEndTimeValue(OffsetDateTime.now());
    dto.setLanguage(Language.DE);
    dto.setTerritory(Territory.DE);
    dto.setSettingDefiningCode(Setting.DENTAL_CARE);
    dto.setCategoryDefiningCode(Category.EVENT);
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
    BodyTemperatureLocationOfMeasurementDvCodedText locationOfMeasurement =
        new BodyTemperatureLocationOfMeasurementDvCodedText();
    locationOfMeasurement.setLocationOfMeasurementDefiningCode(
        org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition
            .LocationOfMeasurementDefiningCode.FOREHEAD);
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
    BodyTemperatureLocationOfMeasurementDvText locationOfMeasurement =
        new BodyTemperatureLocationOfMeasurementDvText();
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
    alternativeEventsComposition.setStartTimeValue(
        OffsetDateTime.of(2010, 11, 02, 12, 00, 00, 00, ZoneOffset.UTC));
    alternativeEventsComposition.setComposer(new PartyIdentified(null, "Test", null));
    alternativeEventsComposition.setLanguage(Language.EN);
    alternativeEventsComposition.setTerritory(Territory.DE);
    alternativeEventsComposition.setCategoryDefiningCode(Category.EVENT);
    alternativeEventsComposition.setSettingDefiningCode(Setting.COMPLEMENTARY_HEALTH_CARE);
    alternativeEventsComposition.setKorpergewicht(new ArrayList<>());
    KorpergewichtObservation korpergewichtObservation = new KorpergewichtObservation();
    korpergewichtObservation.setLanguage(Language.EN);
    korpergewichtObservation.setSubject(new PartySelf());
    korpergewichtObservation.setOriginValue(
        OffsetDateTime.of(1990, 11, 02, 12, 00, 00, 00, ZoneOffset.UTC));
    alternativeEventsComposition.getKorpergewicht().add(korpergewichtObservation);

    KorpergewichtBirthEnPointEvent birthEnEvent = new KorpergewichtBirthEnPointEvent();
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
    intervalEvent.setMathFunctionDefiningCode(MathFunction.MEAN);
    korpergewichtObservation.getAnyEventEn().add(intervalEvent);
    return alternativeEventsComposition;
  }

  public static EpisodeOfCareComposition buildEpisodeOfCareComposition() {
    EpisodeOfCareComposition episode = new EpisodeOfCareComposition();
    episode.setComposer(new PartyIdentified(null, "Test", null));
    episode.setCategoryDefiningCode(Category.EVENT);
    episode.setLanguage(Language.DE);
    episode.setTerritory(Territory.DE);
    episode.setEpisodeofcare(new ArrayList<>());
    episode.setStartTimeValue(OffsetDateTime.now());
    episode.setSettingDefiningCode(Setting.NURSING_HOME_CARE);

    FeederAudit audit = new FeederAudit();
    audit.setFeederSystemAudit(new FeederAuditDetails());
    audit.getFeederSystemAudit().setSystemId("System 1");
    audit.setOriginatingSystemAudit(new FeederAuditDetails());
    audit.getOriginatingSystemAudit().setSystemId("System 2");
    episode.setFeederAudit(audit);

    EpisodeofcareAdminEntry episodeofcareAdminEntry = new EpisodeofcareAdminEntry();

    episodeofcareAdminEntry.setUpperValue(OffsetDateTime.now());
    episodeofcareAdminEntry.setLowerValue(OffsetDateTime.now());
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

  public static PatientenaufenthaltComposition buildTestPatientenaufenthaltComposition() {
    PatientenaufenthaltComposition patientenaufenthaltComposition =
        new PatientenaufenthaltComposition();

    patientenaufenthaltComposition.setComposer(new PartyIdentified(null, "Test", null));
    patientenaufenthaltComposition.setCategoryDefiningCode(Category.EVENT);
    patientenaufenthaltComposition.setLanguage(Language.DE);
    patientenaufenthaltComposition.setTerritory(Territory.DE);
    patientenaufenthaltComposition.setStartTimeValue(OffsetDateTime.now());
    patientenaufenthaltComposition.setSettingDefiningCode(Setting.NURSING_HOME_CARE);

    VersorgungsortAdminEntry versorgungsortAdminEntry = new VersorgungsortAdminEntry();
    StandortCluster standortCluster = new StandortCluster();
    standortCluster.setStandorttypValue("Test");
    standortCluster.setStandortbeschreibungValue("Beschreibung");
    standortCluster.setStandortschlusselDefiningCode(
        StandortschlusselDefiningCode.ANGIOLOGIE.ANGIOLOGIE);
    standortCluster.setBettplatzkennungValue("Platz 2");

    versorgungsortAdminEntry.setStandort(standortCluster);
    versorgungsortAdminEntry.setBeginnValue(new DvDateTime("2020-01-01T10:00Z").getValue());
    versorgungsortAdminEntry.setEndeValue(new DvDateTime("2020-01-01T12:00Z").getValue());
    versorgungsortAdminEntry.setGrundDesAufenthaltesValue("test value");
    versorgungsortAdminEntry.setLanguage(Language.DE);
    versorgungsortAdminEntry.setSubject(new PartySelf());

    patientenaufenthaltComposition.setVersorgungsort(versorgungsortAdminEntry);

    return patientenaufenthaltComposition;
  }

  public static VirologischerBefundComposition buildTestVirologischerBefundComposition() {

    // openEHR-EHR-COMPOSITION.report-result.v1
    VirologischerBefundComposition virologischerBefundComposition =
        new VirologischerBefundComposition();

    virologischerBefundComposition.setComposer(new PartyIdentified(null, "Test", null));
    virologischerBefundComposition.setCategoryDefiningCode(Category.EVENT);
    virologischerBefundComposition.setLanguage(Language.DE);
    virologischerBefundComposition.setTerritory(Territory.DE);
    virologischerBefundComposition.setStartTimeValue(OffsetDateTime.now());
    virologischerBefundComposition.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);

    // context, other_context
    FallidentifikationCluster fallidentifikationCluster = new FallidentifikationCluster();
    fallidentifikationCluster.setFallKennungValue("9251377");
    virologischerBefundComposition.setFallidentifikation(fallidentifikationCluster);
    virologischerBefundComposition.setBerichtIdValue("15a69a62-1ea7-4111-98a5-28aeae854bcd");
    virologischerBefundComposition.setStatusValue("Endbefund");

    // openEHR-EHR-CLUSTER.specimen.v1
    ProbeCluster probeCluster = new ProbeCluster();
    probeCluster.setZeitpunktDerProbenentnahmeValue(
        new DvDateTime("2020-04-01T12:00:00Z").getValue());
    probeCluster.setProbenartValue("Blut");
    probeCluster.setZeitpunktDesProbeneingangsValue(
        new DvDateTime("2020-04-02T09:00:00Z").getValue());
    probeCluster.setKommentarDesProbennehmersValue("Kommentar zur Probe");
    probeCluster.setKommentarValue("Kommentar");

    // openEHR-EHR-CLUSTER.laboratory_test_analyte.v1
    ProVirusCluster proVirusCluster1 = new ProVirusCluster();
    proVirusCluster1.setVirusValue("SARS-Cov-2");
    proVirusCluster1.setAnalyseergebnisReihenfolgeMagnitude(Long.valueOf(32));

    ProVirusCluster proVirusCluster2 = new ProVirusCluster();
    proVirusCluster2.setVirusValue("SARS-Cov-2");
    proVirusCluster2.setAnalyseergebnisReihenfolgeMagnitude(Long.valueOf(34));

    // openEHR-EHR-CLUSTER.laboratory_test_panel.v0
    KulturCluster kulturCluster = new KulturCluster();
    kulturCluster.setProVirus(new ArrayList<>());
    kulturCluster.getProVirus().add(proVirusCluster1);
    kulturCluster.getProVirus().add(proVirusCluster2);

    // openEHR-EHR-OBSERVATION.laboratory_test_result.v1
    BefundObservation befundObservation = new BefundObservation();
    // set clusters in observation
    befundObservation.setKultur(new ArrayList<>());
    befundObservation.getKultur().add(kulturCluster);
    befundObservation.setProbe(new ArrayList<>());
    befundObservation.getProbe().add(probeCluster);
    befundObservation.setOriginValue(new DvDateTime("2020-04-02T12:00:00Z").getValue());
    befundObservation.setTimeValue(new DvDateTime("2020-04-02T14:00:00Z").getValue());
    befundObservation.setLabortestBezeichnungValue("Virologische Untersuchung");
    befundObservation.setSubject(new PartySelf());
    befundObservation.setLanguage(Language.DE);

    virologischerBefundComposition.setBefund(befundObservation);

    return virologischerBefundComposition;
  }

  public static StationarerVersorgungsfallComposition
      buildTestStationarerVersorgungsfallComposition() {
    StationarerVersorgungsfallComposition stationarerVersorgungsfallComposition =
        new StationarerVersorgungsfallComposition();

    stationarerVersorgungsfallComposition.setComposer(new PartyIdentified(null, "Test", null));
    stationarerVersorgungsfallComposition.setCategoryDefiningCode(Category.EVENT);
    stationarerVersorgungsfallComposition.setLanguage(Language.DE);
    stationarerVersorgungsfallComposition.setTerritory(Territory.DE);

    // context
    stationarerVersorgungsfallComposition.setStartTimeValue(
        new DvDateTime("2020-04-02T12:00:00Z").getValue());
    stationarerVersorgungsfallComposition.setSettingDefiningCode(Setting.NURSING_HOME_CARE);
    // other_context
    stationarerVersorgungsfallComposition.setFallKennungValue("45657678");

    // openEHR-EHR-ADMIN_ENTRY.admission.v0
    AufnahmedatenAdminEntry aufnahmedatenAdminEntry = new AufnahmedatenAdminEntry();
    aufnahmedatenAdminEntry.setDatumUhrzeitDerAufnahmeValue(
        new DvDateTime("2020-04-02T12:00:00Z").getValue());
    aufnahmedatenAdminEntry.setLanguage(Language.DE);
    aufnahmedatenAdminEntry.setSubject(new PartySelf());

    // openEHR-EHR-ADMIN_ENTRY.discharge_summary.v0
    EntlassungsdatenAdminEntry entlassungsdatenAdminEntry = new EntlassungsdatenAdminEntry();
    entlassungsdatenAdminEntry.setLanguage(Language.DE);
    entlassungsdatenAdminEntry.setSubject(new PartySelf());
    entlassungsdatenAdminEntry.setKlinischerZustandDesPatientenDefiningCode(
        KlinischerZustandDesPatientenDefiningCode.UNBESTIMMT);
    entlassungsdatenAdminEntry.setDatumUhrzeitDerEntlassungValue(
        new DvDateTime("2020-04-02T12:00:00Z").getValue());

    // assemble
    stationarerVersorgungsfallComposition.setAufnahmedaten(aufnahmedatenAdminEntry);
    stationarerVersorgungsfallComposition.setEntlassungsdaten(entlassungsdatenAdminEntry);

    return stationarerVersorgungsfallComposition;
  }
}
