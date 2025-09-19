/*
 * Copyright (c) 2019 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.serialisation.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.Observation;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.IntervalEvent;
import com.nedap.archie.rm.datastructures.ItemStructure;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartySelf;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.MathFunction;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.BloodpressureListDe;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.TestData;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.alternativeeventscomposition.AlternativeEventsComposition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.alternativeeventscomposition.definition.KorpergewichtAnyEventEnIntervalEvent;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.alternativeeventscomposition.definition.KorpergewichtAnyEventEnPointEvent;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.alternativeeventscomposition.definition.KorpergewichtBirthEnPointEvent;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.coronaanamnesecomposition.CoronaAnamneseComposition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.coronaanamnesecomposition.definition.VorhandenDefiningCode;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasebloodpressuresimpledev0composition.EhrbaseBloodPressureSimpleDeV0Composition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasebloodpressuresimpledev0composition.definition.KorotkoffSoundsDefiningCode;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasemultioccurrencedev1composition.EhrbaseMultiOccurrenceDeV1Composition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasemultioccurrencedev1composition.definition.BodyTemperatureAnyEventPointEvent;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasemultioccurrencedev1composition.definition.BodyTemperatureLocationOfMeasurementChoice;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasemultioccurrencedev1composition.definition.BodyTemperatureLocationOfMeasurementDvCodedText;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasemultioccurrencedev1composition.definition.BodyTemperatureLocationOfMeasurementDvText;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasemultioccurrencedev1composition.definition.BodyTemperatureObservation;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasemultioccurrencedev1composition.definition.LocationOfMeasurementDefiningCode;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.episodeofcarecomposition.EpisodeOfCareComposition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.episodeofcarecomposition.definition.EpisodeofcareAdminEntry;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.episodeofcarecomposition.definition.EpisodeofcareTeamElement;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.errortestcomposition.ErrorTestComposition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.errortestcomposition.definition.LaboratoryTestResultAnyEventChoice;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.errortestcomposition.definition.LaboratoryTestResultAnyEventPointEvent;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.errortestcomposition.definition.LaboratoryTestResultObservation;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.errortestcomposition.definition.SpecimenCluster;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.errortestcomposition.definition.SpecimenCollectionDateTimeChoice;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.errortestcomposition.definition.SpecimenCollectionDateTimeDvDateTime;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.errortestcomposition.definition.SpecimenCollectionDateTimeDvIntervalDvDateTime;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.korpergrossecomposition.KorpergrosseComposition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.korpergrossecomposition.definition.GrosseLangeObservation;
import org.ehrbase.openehr.sdk.serialisation.RMDataFormat;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.TestDataTemplateProvider;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.Test;

public class RmToGeneratedDtoConverterTest {

    @Test
    public void testFlatten() {
        RmToGeneratedDtoConverter cut = new RmToGeneratedDtoConverter(new TestDataTemplateProvider());
        BloodpressureListDe bloodpressureListDe = TestData.buildExampleBloodpressureListDe();

        RMObject rmObject =
                new GeneratedDtoToRmConverter(new TestDataTemplateProvider()).toRMObject(bloodpressureListDe);

        BloodpressureListDe expected = cut.toGeneratedDto(rmObject, BloodpressureListDe.class);

        Assertions.assertThat(expected).isNotNull();
        Assertions.assertThat(expected.getStartTime()).isEqualTo(bloodpressureListDe.getStartTime());
        Assertions.assertThat(expected.getBloodpressures())
                .extracting(BloodpressureListDe.Bloodpressure::getSystolischValue)
                .containsExactlyInAnyOrder(12d, 22d);
    }

    @Test
    public void testFlattenInterval() {
        RmToGeneratedDtoConverter cut = new RmToGeneratedDtoConverter(new TestDataTemplateProvider());
        ErrorTestComposition composition = new ErrorTestComposition();

        LaboratoryTestResultObservation labTest = new LaboratoryTestResultObservation();

        List<LaboratoryTestResultAnyEventChoice> anyEventList = new ArrayList<>();

        {
            LaboratoryTestResultAnyEventPointEvent eventWithTime = new LaboratoryTestResultAnyEventPointEvent();
            eventWithTime.setTestNameValue("Testname");

            SpecimenCluster specimen = new SpecimenCluster();

            SpecimenCollectionDateTimeDvDateTime collectionDateTime = new SpecimenCollectionDateTimeDvDateTime();
            collectionDateTime.setCollectionDateTimeValue(OffsetDateTime.of(2020, 1, 2, 12, 30, 0, 0, ZoneOffset.UTC));
            specimen.setCollectionDateTime(collectionDateTime);
            eventWithTime.setSpecimen(specimen);

            anyEventList.add(eventWithTime);
        }

        {
            LaboratoryTestResultAnyEventPointEvent eventWithInterval = new LaboratoryTestResultAnyEventPointEvent();
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

        RMObject rmObject = new GeneratedDtoToRmConverter(new TestDataTemplateProvider()).toRMObject(composition);

        ErrorTestComposition expected = cut.toGeneratedDto(rmObject, ErrorTestComposition.class);

        Assertions.assertThat(expected.getLaboratoryTestResult().getAnyEvent())
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
        GeneratedDtoToRmConverter generatedDtoToRmConverter =
                new GeneratedDtoToRmConverter(new TestDataTemplateProvider());

        KorpergrosseComposition dto = new KorpergrosseComposition();
        dto.setGrosseLange(new GrosseLangeObservation());
        dto.getGrosseLange().setGrosseLangeMagnitude(22d);

        Composition rmObject = (Composition) generatedDtoToRmConverter.toRMObject(dto);

        assertThat(rmObject).isNotNull();

        RmToGeneratedDtoConverter cut = new RmToGeneratedDtoConverter(new TestDataTemplateProvider());

        KorpergrosseComposition actual = cut.toGeneratedDto(rmObject, KorpergrosseComposition.class);

        Assertions.assertThat(actual.getGrosseLange().getGrosseLangeMagnitude()).isEqualTo(22d);
    }

    @Test
    public void testFlattenSingleEventIntervallEvent() {
        GeneratedDtoToRmConverter generatedDtoToRmConverter =
                new GeneratedDtoToRmConverter(new TestDataTemplateProvider());

        KorpergrosseComposition dto = new KorpergrosseComposition();
        dto.setGrosseLange(new GrosseLangeObservation());
        dto.getGrosseLange().setGrosseLangeMagnitude(22d);

        Composition rmObject = (Composition) generatedDtoToRmConverter.toRMObject(dto);
        Observation observation = (Observation) rmObject.getContent().get(0);

        Event<ItemStructure> event = observation.getData().getEvents().get(0);
        observation.getData().getEvents().remove(event);

        IntervalEvent<ItemStructure> intervalEvent = new IntervalEvent<>();
        intervalEvent.setData(event.getData());
        intervalEvent.setArchetypeNodeId(event.getArchetypeNodeId());
        intervalEvent.setSampleCount(10L);
        observation.getData().getEvents().add(intervalEvent);

        assertThat(rmObject).isNotNull();

        RmToGeneratedDtoConverter cut = new RmToGeneratedDtoConverter(new TestDataTemplateProvider());

        KorpergrosseComposition actual = cut.toGeneratedDto(rmObject, KorpergrosseComposition.class);

        Assertions.assertThat(actual.getGrosseLange().getGrosseLangeMagnitude()).isEqualTo(22d);
    }

    @Test
    public void testFlattenEhrbaseBloodPressureSimpleDeV0() {
        RmToGeneratedDtoConverter cut = new RmToGeneratedDtoConverter(new TestDataTemplateProvider());
        EhrbaseBloodPressureSimpleDeV0Composition bloodPressureSimpleDeV0 =
                TestData.buildEhrbaseBloodPressureSimpleDeV0();
        RMObject rmObject =
                new GeneratedDtoToRmConverter(new TestDataTemplateProvider()).toRMObject(bloodPressureSimpleDeV0);

        EhrbaseBloodPressureSimpleDeV0Composition actual =
                cut.toGeneratedDto((Locatable) rmObject, EhrbaseBloodPressureSimpleDeV0Composition.class);

        Assertions.assertThat(actual).isNotNull();

        Assertions.assertThat(actual.getComposer())
                .isNotNull()
                .extracting(Object::getClass)
                .isEqualTo(PartyIdentified.class);

        PartyIdentified composer = (PartyIdentified) actual.getComposer();
        assertThat(composer.getName()).isEqualTo("Test");
        Assertions.assertThat(actual.getParticipations())
                .extracting(p -> ((PartyIdentified) p.getPerformer()).getName(), p -> p.getFunction()
                        .getValue())
                .containsExactlyInAnyOrder(new Tuple("Test", "Pos1"), new Tuple("Test2", "Pos2"));

        Assertions.assertThat(actual.getBloodPressureTrainingSample()).size().isEqualTo(1);
        Assertions.assertThat(actual.getBloodPressureTrainingSample().get(0).getSubject())
                .isNotNull()
                .extracting(Object::getClass)
                .isEqualTo(PartySelf.class);
        Assertions.assertThat(actual.getBloodPressureTrainingSample().get(0).getSystolicMagnitude())
                .isEqualTo(22d);
        Assertions.assertThat(actual.getBloodPressureTrainingSample().get(0).getSystolicUnits())
                .isEqualTo("mm[Hg]");
        Assertions.assertThat(actual.getBloodPressureTrainingSample().get(0).getKorotkoffSoundsDefiningCode())
                .isEqualTo(KorotkoffSoundsDefiningCode.FIFTH_SOUND);
        Assertions.assertThat(
                        actual.getBloodPressureTrainingSample().get(0).getMeanArterialPressureNullFlavourDefiningCode())
                .isNull();
    }

    @Test
    public void testFlattenEhrbaseMultiOccurrenceDeV1() {
        RmToGeneratedDtoConverter cut = new RmToGeneratedDtoConverter(new TestDataTemplateProvider());
        EhrbaseMultiOccurrenceDeV1Composition bloodPressureSimpleDeV0 = TestData.buildEhrbaseMultiOccurrenceDeV1();
        RMObject rmObject =
                new GeneratedDtoToRmConverter(new TestDataTemplateProvider()).toRMObject(bloodPressureSimpleDeV0);

        EhrbaseMultiOccurrenceDeV1Composition actual =
                cut.toGeneratedDto((Locatable) rmObject, EhrbaseMultiOccurrenceDeV1Composition.class);

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getBodyTemperature()).size().isEqualTo(2);
        BodyTemperatureObservation bodyTemperature1 =
                actual.getBodyTemperature().get(0);
        Assertions.assertThat(bodyTemperature1.getAnyEvent())
                .extracting(e -> ((BodyTemperatureAnyEventPointEvent) e).getTemperatureMagnitude())
                .containsExactlyInAnyOrder(11d, 22d);

        BodyTemperatureLocationOfMeasurementChoice locationOfMeasurement1 = bodyTemperature1.getLocationOfMeasurement();
        Assertions.assertThat(locationOfMeasurement1.getClass())
                .isEqualTo(BodyTemperatureLocationOfMeasurementDvCodedText.class);
        Assertions.assertThat(((BodyTemperatureLocationOfMeasurementDvCodedText) locationOfMeasurement1)
                        .getLocationOfMeasurementDefiningCode())
                .isEqualTo(LocationOfMeasurementDefiningCode.FOREHEAD);

        BodyTemperatureObservation bodyTemperature2 =
                actual.getBodyTemperature().get(1);
        BodyTemperatureLocationOfMeasurementChoice locationOfMeasurement2 = bodyTemperature2.getLocationOfMeasurement();
        Assertions.assertThat(locationOfMeasurement2.getClass())
                .isEqualTo(BodyTemperatureLocationOfMeasurementDvText.class);
        Assertions.assertThat(((BodyTemperatureLocationOfMeasurementDvText) locationOfMeasurement2)
                        .getLocationOfMeasurementValue())
                .isEqualTo("location");
    }

    @Test
    public void testFlattenCorona() throws IOException {
        Composition composition = RMDataFormat.canonicalJSON()
                .unmarshal(
                        IOUtils.toString(CompositionTestDataCanonicalJson.CORONA.getStream(), StandardCharsets.UTF_8),
                        Composition.class);
        RmToGeneratedDtoConverter cut = new RmToGeneratedDtoConverter(new TestDataTemplateProvider());
        CoronaAnamneseComposition actual = cut.toGeneratedDto(composition, CoronaAnamneseComposition.class);
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getSymptome()).isNotNull();
        Assertions.assertThat(actual.getSymptome().getHeiserkeit()).isNotNull();
        Assertions.assertThat(actual.getSymptome().getHeiserkeit().getVorhandenDefiningCode())
                .isEqualTo(VorhandenDefiningCode.NICHT_VORHANDEN);
    }

    @Test
    public void testFlattenAltEvents() {
        Composition composition = (Composition) new GeneratedDtoToRmConverter(new TestDataTemplateProvider())
                .toRMObject(TestData.buildAlternativeEventsComposition());
        RmToGeneratedDtoConverter cut = new RmToGeneratedDtoConverter(new TestDataTemplateProvider());
        AlternativeEventsComposition actual = cut.toGeneratedDto(composition, AlternativeEventsComposition.class);
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getKorpergewicht()).size().isEqualTo(1);
        KorpergewichtBirthEnPointEvent birthEn =
                actual.getKorpergewicht().get(0).getBirthEn();
        Assertions.assertThat(birthEn.getTimeValue())
                .isEqualTo(OffsetDateTime.of(1990, 11, 2, 12, 0, 0, 0, ZoneOffset.UTC));
        Assertions.assertThat(birthEn.getGewichtMagnitude()).isEqualTo(30d);
        Assertions.assertThat(birthEn.getGewichtUnits()).isEqualTo("kg");

        List<KorpergewichtAnyEventEnPointEvent> eventEnPointEvents =
                actual.getKorpergewicht().get(0).getAnyEventEn().stream()
                        .filter(e -> KorpergewichtAnyEventEnPointEvent.class.isAssignableFrom(e.getClass()))
                        .map(e -> (KorpergewichtAnyEventEnPointEvent) e)
                        .collect(Collectors.toList());
        Assertions.assertThat(eventEnPointEvents).size().isEqualTo(1);
        Assertions.assertThat(eventEnPointEvents.get(0).getTimeValue())
                .isEqualTo(OffsetDateTime.of(2013, 11, 2, 12, 0, 0, 0, ZoneOffset.UTC));
        Assertions.assertThat(eventEnPointEvents.get(0).getGewichtMagnitude()).isEqualTo(55d);
        Assertions.assertThat(eventEnPointEvents.get(0).getGewichtUnits()).isEqualTo("kg");

        List<KorpergewichtAnyEventEnIntervalEvent> anyEventEnIntervalEvents =
                actual.getKorpergewicht().get(0).getAnyEventEn().stream()
                        .filter(e -> KorpergewichtAnyEventEnIntervalEvent.class.isAssignableFrom(e.getClass()))
                        .map(e -> (KorpergewichtAnyEventEnIntervalEvent) e)
                        .collect(Collectors.toList());
        Assertions.assertThat(eventEnPointEvents).size().isEqualTo(1);
        Assertions.assertThat(anyEventEnIntervalEvents.get(0).getTimeValue())
                .isEqualTo(OffsetDateTime.of(2015, 11, 2, 12, 0, 0, 0, ZoneOffset.UTC));
        Assertions.assertThat(anyEventEnIntervalEvents.get(0).getGewichtMagnitude())
                .isEqualTo(60d);
        Assertions.assertThat(anyEventEnIntervalEvents.get(0).getGewichtUnits()).isEqualTo("kg");
        Assertions.assertThat(anyEventEnIntervalEvents.get(0).getMathFunctionDefiningCode())
                .isEqualTo(MathFunction.MEAN);
        Assertions.assertThat(anyEventEnIntervalEvents.get(0).getWidthValue()).isEqualTo(Duration.ofDays(30));
    }

    @Test
    public void testFlattenEpisodeOfCare() {
        Composition composition = (Composition) new GeneratedDtoToRmConverter(new TestDataTemplateProvider())
                .toRMObject(TestData.buildEpisodeOfCareComposition());
        RmToGeneratedDtoConverter cut = new RmToGeneratedDtoConverter(new TestDataTemplateProvider());
        EpisodeOfCareComposition actual = cut.toGeneratedDto(composition, EpisodeOfCareComposition.class);
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getEpisodeofcare()).size().isEqualTo(1);
        EpisodeofcareAdminEntry episodeofcareAdminEntry =
                actual.getEpisodeofcare().get(0);

        Assertions.assertThat(episodeofcareAdminEntry.getIdentifier())
                .extracting(e -> e.getValue().getId())
                .containsExactlyInAnyOrder("123", "456");

        Assertions.assertThat(episodeofcareAdminEntry.getTeam())
                .extracting(EpisodeofcareTeamElement::getValue)
                .containsExactlyInAnyOrder(URI.create("https://github.com/ehrbase"));
    }
}
