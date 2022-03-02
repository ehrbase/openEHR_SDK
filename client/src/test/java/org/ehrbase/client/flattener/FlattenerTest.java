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
import com.nedap.archie.rm.composition.Observation;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.IntervalEvent;
import com.nedap.archie.rm.datastructures.ItemStructure;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartySelf;
import org.apache.commons.io.IOUtils;
import org.assertj.core.groups.Tuple;
import org.ehrbase.client.TestData;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.AlternativeEventsComposition;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.KorpergewichtAnyEventEnIntervalEvent;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.KorpergewichtAnyEventEnPointEvent;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.KorpergewichtBirthEnPointEvent;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.CoronaAnamneseComposition;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.VorhandenDefiningCode;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.EhrbaseBloodPressureSimpleDeV0Composition;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.KorotkoffSoundsDefiningCode;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.EhrbaseMultiOccurrenceDeV1Composition;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.*;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.EpisodeOfCareComposition;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition.EpisodeofcareAdminEntry;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition.EpisodeofcareTeamElement;
import org.ehrbase.client.classgenerator.examples.errortestcomposition.ErrorTestComposition;
import org.ehrbase.client.classgenerator.examples.errortestcomposition.definition.*;
import org.ehrbase.client.classgenerator.examples.korpergrossecomposition.KorpergrosseComposition;
import org.ehrbase.client.classgenerator.examples.korpergrossecomposition.definition.GrosseLangeObservation;
import org.ehrbase.client.classgenerator.shareddefinition.MathFunction;
import org.ehrbase.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ehrbase.client.TestData.buildAlternativeEventsComposition;
import static org.ehrbase.client.TestData.buildEpisodeOfCareComposition;

public class FlattenerTest {

  @Test
  public void testFlatten() {
    Flattener cut = new Flattener(new TestDataTemplateProvider());
    BloodpressureListDe bloodpressureListDe = TestData.buildExampleBloodpressureListDe();

    RMObject rmObject =
        new Unflattener(new TestDataTemplateProvider()).unflatten(bloodpressureListDe);

    BloodpressureListDe expected = cut.flatten(rmObject, BloodpressureListDe.class);

    assertThat(expected).isNotNull();
    assertThat(expected.getStartTime()).isEqualTo(bloodpressureListDe.getStartTime());
    assertThat(expected.getBloodpressures())
        .extracting(BloodpressureListDe.Bloodpressure::getSystolischValue)
        .containsExactlyInAnyOrder(12d, 22d);
  }

  @Test
  public void testFlattenInterval() {
    Flattener cut = new Flattener(new TestDataTemplateProvider());
    ErrorTestComposition composition = new ErrorTestComposition();

    LaboratoryTestResultObservation labTest = new LaboratoryTestResultObservation();

    List<LaboratoryTestResultAnyEventChoice> anyEventList = new ArrayList<>();

    {
      LaboratoryTestResultAnyEventPointEvent eventWithTime =
          new LaboratoryTestResultAnyEventPointEvent();
      eventWithTime.setTestNameValue("Testname");

      SpecimenCluster specimen = new SpecimenCluster();

      SpecimenCollectionDateTimeDvDateTime collectionDateTime =
          new SpecimenCollectionDateTimeDvDateTime();
      collectionDateTime.setCollectionDateTimeValue(
          OffsetDateTime.of(2020, 1, 2, 12, 30, 0, 0, ZoneOffset.UTC));
      specimen.setCollectionDateTime(collectionDateTime);
      eventWithTime.setSpecimen(specimen);

      anyEventList.add(eventWithTime);
    }

    {
      LaboratoryTestResultAnyEventPointEvent eventWithInterval =
          new LaboratoryTestResultAnyEventPointEvent();
      eventWithInterval.setTestNameValue("Testname");

      SpecimenCluster specimen = new SpecimenCluster();

      SpecimenCollectionDateTimeDvIntervalDvDateTime collectionDateTime =
          new SpecimenCollectionDateTimeDvIntervalDvDateTime();
      collectionDateTime.setLowerValue(OffsetDateTime.of(2020, 1, 2, 12, 30, 0, 0, ZoneOffset.UTC));
      collectionDateTime.setUpperValue(OffsetDateTime.of(2020, 1, 2, 13, 30, 0, 0, ZoneOffset.UTC));
      specimen.setCollectionDateTime(collectionDateTime);
      eventWithInterval.setSpecimen(specimen);

      anyEventList.add(eventWithInterval);
    }

    labTest.setAnyEvent(anyEventList);
    composition.setLaboratoryTestResult(labTest);

    RMObject rmObject = new Unflattener(new TestDataTemplateProvider()).unflatten(composition);

    ErrorTestComposition expected = cut.flatten(rmObject, ErrorTestComposition.class);

    assertThat(expected.getLaboratoryTestResult().getAnyEvent())
        .extracting(
            e -> e.getSpecimen().getCollectionDateTime().getClass().getSimpleName(),
            e -> extractTime(e.getSpecimen().getCollectionDateTime()))
        .containsExactly(
            new Tuple("SpecimenCollectionDateTimeDvDateTime", "2020-01-02T12:30Z"),
            new Tuple(
                "SpecimenCollectionDateTimeDvIntervalDvDateTime",
                "2020-01-02T12:30Z-2020-01-02T13:30Z"));
  }

  private String extractTime(SpecimenCollectionDateTimeChoice collectionDateTime) {

    if (collectionDateTime instanceof SpecimenCollectionDateTimeDvIntervalDvDateTime) {
      return ((SpecimenCollectionDateTimeDvIntervalDvDateTime) collectionDateTime)
              .getLowerValue()
              .toString()
          + "-"
          + ((SpecimenCollectionDateTimeDvIntervalDvDateTime) collectionDateTime)
              .getUpperValue()
              .toString();
    } else {
      return ((SpecimenCollectionDateTimeDvDateTime) collectionDateTime)
          .getCollectionDateTimeValue()
          .toString();
    }
  }

  @Test
  public void testFlattenSingleEventPointEvent() {
    Unflattener unflattener = new Unflattener(new TestDataTemplateProvider());

    KorpergrosseComposition dto = new KorpergrosseComposition();
    dto.setGrosseLange(new GrosseLangeObservation());
    dto.getGrosseLange().setGrosseLangeMagnitude(22d);

    Composition rmObject = (Composition) unflattener.unflatten(dto);

    assertThat(rmObject).isNotNull();

    Flattener cut = new Flattener(new TestDataTemplateProvider());

    KorpergrosseComposition actual = cut.flatten(rmObject, KorpergrosseComposition.class);

    assertThat(actual.getGrosseLange().getGrosseLangeMagnitude()).isEqualTo(22d);

  }

  @Test
  public void testFlattenSingleEventIntervallEvent() {
    Unflattener unflattener = new Unflattener(new TestDataTemplateProvider());

    KorpergrosseComposition dto = new KorpergrosseComposition();
    dto.setGrosseLange(new GrosseLangeObservation());
    dto.getGrosseLange().setGrosseLangeMagnitude(22d);

    Composition rmObject = (Composition) unflattener.unflatten(dto);
    Observation observation = (Observation) rmObject.getContent().get(0);

    Event<ItemStructure> event = observation.getData().getEvents().get(0);
    observation.getData().getEvents().remove(event);

    IntervalEvent<ItemStructure> intervalEvent = new IntervalEvent<>();
    intervalEvent.setData(event.getData());
    intervalEvent.setArchetypeNodeId(event.getArchetypeNodeId());
    intervalEvent.setSampleCount(10L);
    observation.getData().getEvents().add(intervalEvent);

    assertThat(rmObject).isNotNull();

    Flattener cut = new Flattener(new TestDataTemplateProvider());

    KorpergrosseComposition actual = cut.flatten(rmObject, KorpergrosseComposition.class);

    assertThat(actual.getGrosseLange().getGrosseLangeMagnitude()).isEqualTo(22d);

  }

  @Test
  public void testFlattenEhrbaseBloodPressureSimpleDeV0() {
    Flattener cut = new Flattener(new TestDataTemplateProvider());
    EhrbaseBloodPressureSimpleDeV0Composition bloodPressureSimpleDeV0 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();
    RMObject rmObject =
        new Unflattener(new TestDataTemplateProvider()).unflatten(bloodPressureSimpleDeV0);

    EhrbaseBloodPressureSimpleDeV0Composition actual =
        cut.flatten((Locatable) rmObject, EhrbaseBloodPressureSimpleDeV0Composition.class);

    assertThat(actual).isNotNull();

    assertThat(actual.getComposer())
        .isNotNull()
        .extracting(Object::getClass)
        .isEqualTo(PartyIdentified.class);

    PartyIdentified composer = (PartyIdentified) actual.getComposer();
    assertThat(composer.getName()).isEqualTo("Test");
    assertThat(actual.getParticipations())
        .extracting(
            p -> ((PartyIdentified) p.getPerformer()).getName(), p -> p.getFunction().getValue())
        .containsExactlyInAnyOrder(new Tuple("Test", "Pos1"), new Tuple("Test2", "Pos2"));

    assertThat(actual.getBloodPressureTrainingSample()).size().isEqualTo(1);
    assertThat(actual.getBloodPressureTrainingSample().get(0).getSubject())
        .isNotNull()
        .extracting(Object::getClass)
        .isEqualTo(PartySelf.class);
    assertThat(actual.getBloodPressureTrainingSample().get(0).getSystolicMagnitude())
        .isEqualTo(22d);
    assertThat(actual.getBloodPressureTrainingSample().get(0).getSystolicUnits())
        .isEqualTo("mm[Hg]");
    assertThat(actual.getBloodPressureTrainingSample().get(0).getKorotkoffSoundsDefiningCode())
        .isEqualTo(KorotkoffSoundsDefiningCode.FIFTH_SOUND);
    assertThat(
            actual
                .getBloodPressureTrainingSample()
                .get(0)
                .getMeanArterialPressureNullFlavourDefiningCode())
        .isNull();
  }

  @Test
  public void testFlattenEhrbaseMultiOccurrenceDeV1() {
    Flattener cut = new Flattener(new TestDataTemplateProvider());
    EhrbaseMultiOccurrenceDeV1Composition bloodPressureSimpleDeV0 =
        TestData.buildEhrbaseMultiOccurrenceDeV1();
    RMObject rmObject =
        new Unflattener(new TestDataTemplateProvider()).unflatten(bloodPressureSimpleDeV0);

    EhrbaseMultiOccurrenceDeV1Composition actual =
        cut.flatten((Locatable) rmObject, EhrbaseMultiOccurrenceDeV1Composition.class);

    assertThat(actual).isNotNull();
    assertThat(actual.getBodyTemperature()).size().isEqualTo(2);
    BodyTemperatureObservation bodyTemperature1 = actual.getBodyTemperature().get(0);
    assertThat(bodyTemperature1.getAnyEvent())
        .extracting(e -> ((BodyTemperatureAnyEventPointEvent) e).getTemperatureMagnitude())
        .containsExactlyInAnyOrder(11d, 22d);

    BodyTemperatureLocationOfMeasurementChoice locationOfMeasurement1 =
        bodyTemperature1.getLocationOfMeasurement();
    assertThat(locationOfMeasurement1.getClass())
        .isEqualTo(BodyTemperatureLocationOfMeasurementDvCodedText.class);
    assertThat(
            ((BodyTemperatureLocationOfMeasurementDvCodedText) locationOfMeasurement1)
                .getLocationOfMeasurementDefiningCode())
        .isEqualTo(LocationOfMeasurementDefiningCode.FOREHEAD);

    BodyTemperatureObservation bodyTemperature2 = actual.getBodyTemperature().get(1);
    BodyTemperatureLocationOfMeasurementChoice locationOfMeasurement2 =
        bodyTemperature2.getLocationOfMeasurement();
    assertThat(locationOfMeasurement2.getClass())
        .isEqualTo(BodyTemperatureLocationOfMeasurementDvText.class);
    assertThat(
            ((BodyTemperatureLocationOfMeasurementDvText) locationOfMeasurement2)
                .getLocationOfMeasurementValue())
        .isEqualTo("location");
  }

  @Test
  public void testFlattenCorona() throws IOException {
    Composition composition =
        new CanonicalJson()
            .unmarshal(
                IOUtils.toString(
                    CompositionTestDataCanonicalJson.CORONA.getStream(), StandardCharsets.UTF_8),
                Composition.class);
    Flattener cut = new Flattener(new TestDataTemplateProvider());
    CoronaAnamneseComposition actual = cut.flatten(composition, CoronaAnamneseComposition.class);
    assertThat(actual).isNotNull();
    assertThat(actual.getSymptome()).isNotNull();
    assertThat(actual.getSymptome().getHeiserkeit()).isNotNull();
    assertThat(actual.getSymptome().getHeiserkeit().getVorhandenDefiningCode())
        .isEqualTo(VorhandenDefiningCode.NICHT_VORHANDEN);
  }

  @Test
  public void testFlattenAltEvents() {
    Composition composition =
        (Composition)
            new Unflattener(new TestDataTemplateProvider())
                .unflatten(buildAlternativeEventsComposition());
    Flattener cut = new Flattener(new TestDataTemplateProvider());
    AlternativeEventsComposition actual =
        cut.flatten(composition, AlternativeEventsComposition.class);
    assertThat(actual).isNotNull();
    assertThat(actual.getKorpergewicht()).size().isEqualTo(1);
    KorpergewichtBirthEnPointEvent birthEn = actual.getKorpergewicht().get(0).getBirthEn();
    assertThat(birthEn.getTimeValue())
        .isEqualTo(OffsetDateTime.of(1990, 11, 2, 12, 0, 0, 0, ZoneOffset.UTC));
    assertThat(birthEn.getGewichtMagnitude()).isEqualTo(30d);
    assertThat(birthEn.getGewichtUnits()).isEqualTo("kg");

    List<KorpergewichtAnyEventEnPointEvent> eventEnPointEvents =
        actual.getKorpergewicht().get(0).getAnyEventEn().stream()
            .filter(e -> KorpergewichtAnyEventEnPointEvent.class.isAssignableFrom(e.getClass()))
            .map(e -> (KorpergewichtAnyEventEnPointEvent) e)
            .collect(Collectors.toList());
    assertThat(eventEnPointEvents).size().isEqualTo(1);
    assertThat(eventEnPointEvents.get(0).getTimeValue())
        .isEqualTo(OffsetDateTime.of(2013, 11, 2, 12, 0, 0, 0, ZoneOffset.UTC));
    assertThat(eventEnPointEvents.get(0).getGewichtMagnitude()).isEqualTo(55d);
    assertThat(eventEnPointEvents.get(0).getGewichtUnits()).isEqualTo("kg");

    List<KorpergewichtAnyEventEnIntervalEvent> anyEventEnIntervalEvents =
        actual.getKorpergewicht().get(0).getAnyEventEn().stream()
            .filter(e -> KorpergewichtAnyEventEnIntervalEvent.class.isAssignableFrom(e.getClass()))
            .map(e -> (KorpergewichtAnyEventEnIntervalEvent) e)
            .collect(Collectors.toList());
    assertThat(eventEnPointEvents).size().isEqualTo(1);
    assertThat(anyEventEnIntervalEvents.get(0).getTimeValue())
        .isEqualTo(OffsetDateTime.of(2015, 11, 2, 12, 0, 0, 0, ZoneOffset.UTC));
    assertThat(anyEventEnIntervalEvents.get(0).getGewichtMagnitude()).isEqualTo(60d);
    assertThat(anyEventEnIntervalEvents.get(0).getGewichtUnits()).isEqualTo("kg");
    assertThat(anyEventEnIntervalEvents.get(0).getMathFunctionDefiningCode())
        .isEqualTo(MathFunction.MEAN);
    assertThat(anyEventEnIntervalEvents.get(0).getWidthValue()).isEqualTo(Duration.ofDays(30));
  }

  @Test
  public void testFlattenEpisodeOfCare() {
    Composition composition =
        (Composition)
            new Unflattener(new TestDataTemplateProvider())
                .unflatten(buildEpisodeOfCareComposition());
    Flattener cut = new Flattener(new TestDataTemplateProvider());
    EpisodeOfCareComposition actual = cut.flatten(composition, EpisodeOfCareComposition.class);
    assertThat(actual).isNotNull();
    assertThat(actual.getEpisodeofcare()).size().isEqualTo(1);
    EpisodeofcareAdminEntry episodeofcareAdminEntry = actual.getEpisodeofcare().get(0);

    assertThat(episodeofcareAdminEntry.getIdentifier())
        .extracting(e -> e.getValue().getId())
        .containsExactlyInAnyOrder("123", "456");

    assertThat(episodeofcareAdminEntry.getTeam())
        .extracting(EpisodeofcareTeamElement::getValue)
        .containsExactlyInAnyOrder(URI.create("https://github.com/ehrbase"));
  }
}
