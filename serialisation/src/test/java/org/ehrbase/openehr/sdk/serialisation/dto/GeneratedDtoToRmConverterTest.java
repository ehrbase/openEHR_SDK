/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.serialisation.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.AdminEntry;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.Observation;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datastructures.IntervalEvent;
import com.nedap.archie.rm.datastructures.PointEvent;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.DvURI;
import com.nedap.archie.rm.datavalues.quantity.DvQuantity;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.TerminologyId;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.BloodpressureListDe;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.TestData;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.alternativeeventscomposition.AlternativeEventsComposition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.befundderblutgasanalysecomposition.BefundDerBlutgasanalyseComposition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.befundderblutgasanalysecomposition.definition.KohlendioxidpartialdruckCluster;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.befundderblutgasanalysecomposition.definition.LaborergebnisObservation;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.coronaanamnesecomposition.CoronaAnamneseComposition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasebloodpressuresimpledev0composition.EhrbaseBloodPressureSimpleDeV0Composition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasemultioccurrencedev1composition.EhrbaseMultiOccurrenceDeV1Composition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.episodeofcarecomposition.EpisodeOfCareComposition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.korpergrossecomposition.KorpergrosseComposition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.korpergrossecomposition.definition.GrosseLangeObservation;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.testalltypesenv1composition.TestAllTypesEnV1Composition;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.openehr.sdk.serialisation.templateprovider.TestDataTemplateProvider;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.jupiter.api.Test;

public class GeneratedDtoToRmConverterTest {

    @Test
    public void testUnflatten() {
        GeneratedDtoToRmConverter cut = new GeneratedDtoToRmConverter(new TestDataTemplateProvider());

        BloodpressureListDe dto = TestData.buildExampleBloodpressureListDe();

        Composition rmObject = (Composition) cut.toRMObject(dto);

        assertThat(rmObject).isNotNull();
        assertThat(rmObject.itemAtPath("/context/start_time/value")).isEqualTo(dto.getStartTime());

        List<Object> observationList =
                rmObject.itemsAtPath("/content[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]");
        assertThat(observationList).size().isEqualTo(2);

        List<Double> systolischValues = observationList.stream()
                .map(o -> (Observation) o)
                .map(o -> (Element) o.itemAtPath("/data[at0001]/events[at0002]/data[at0003]/items[at0004]"))
                .map(e -> (DvQuantity) e.getValue())
                .map(DvQuantity::getMagnitude)
                .collect(Collectors.toList());

        assertThat(systolischValues).containsExactlyInAnyOrder(12d, 22d);
    }

    @Test
    public void testUnflattenInterval() {
        TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();

        GeneratedDtoToRmConverter cut = new GeneratedDtoToRmConverter(templateProvider);

        TestAllTypesEnV1Composition dto = TestData.buildTestAllTypesEnV1Composition();

        Composition rmObject = (Composition) cut.toRMObject(dto);

        assertThat(rmObject).isNotNull();

        assertThat(
                        rmObject.itemAtPath(
                                "/content[openEHR-EHR-EVALUATION.test_all_types.v1]/data[at0001]/items[at0003]/value/upper_included"))
                .isEqualTo(true);

        TestAllTypesEnV1Composition actual = new RmToGeneratedDtoConverter(new TestDataTemplateProvider())
                .toGeneratedDto(rmObject, TestAllTypesEnV1Composition.class);

        Assertions.assertThat(actual.getTestAllTypes2().get(0).isIntervalCountUpperIncluded())
                .isTrue();
    }

    @Test
    public void testUnflattenSingleEvent() {
        GeneratedDtoToRmConverter cut = new GeneratedDtoToRmConverter(new TestDataTemplateProvider());

        KorpergrosseComposition dto = new KorpergrosseComposition();
        dto.setGrosseLange(new GrosseLangeObservation());
        dto.getGrosseLange().setGrosseLangeMagnitude(22d);

        Composition rmObject = (Composition) cut.toRMObject(dto);

        assertThat(rmObject).isNotNull();

        Object event = rmObject.itemAtPath("/content[openEHR-EHR-OBSERVATION.height.v2]/data[at0001]/events[at0002]");
        assertThat(event).isNotNull();
        assertThat(event.getClass()).isEqualTo(PointEvent.class);
        Object quantity = ((PointEvent) event).itemAtPath("/data[at0003]/items[at0004]/value");
        assertThat(quantity).isNotNull();
        assertThat(quantity.getClass()).isEqualTo(DvQuantity.class);
        assertThat(((DvQuantity) quantity).getMagnitude()).isEqualTo(22d);
    }

    @Test
    public void testUnflattenBefundDerBlutgasanalyse() {
        GeneratedDtoToRmConverter cut = new GeneratedDtoToRmConverter(new TestDataTemplateProvider());

        BefundDerBlutgasanalyseComposition dto = new BefundDerBlutgasanalyseComposition();

        LaborergebnisObservation laborergebnisObservation = new LaborergebnisObservation();
        KohlendioxidpartialdruckCluster kohlendioxidpartialdruck = new KohlendioxidpartialdruckCluster();
        kohlendioxidpartialdruck.setAnalytResultatMagnitude(22d);
        laborergebnisObservation.setKohlendioxidpartialdruck(kohlendioxidpartialdruck);
        dto.setLaborergebnis(laborergebnisObservation);

        Composition rmObject = (Composition) cut.toRMObject(dto);

        assertThat(rmObject).isNotNull();
        List<Object> clusters = rmObject.itemsAtPath(
                "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1]/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1]");
        assertThat(clusters).size().isEqualTo(1);
    }

    @Test
    public void testUnflattenEhrbaseBloodPressureSimpleDeV0() {
        GeneratedDtoToRmConverter cut = new GeneratedDtoToRmConverter(new TestDataTemplateProvider());

        EhrbaseBloodPressureSimpleDeV0Composition dto = TestData.buildEhrbaseBloodPressureSimpleDeV0();

        Composition rmObject = (Composition) cut.toRMObject(dto);

        assertThat(rmObject).isNotNull();
        assertThat(rmObject.getContext().getParticipations())
                .extracting(p -> ((PartyIdentified) p.getPerformer()).getName(), p -> p.getFunction()
                        .getValue())
                .containsExactlyInAnyOrder(new Tuple("Test", "Pos1"), new Tuple("Test2", "Pos2"));

        assertThat(rmObject.getLanguage())
                .extracting(CodePhrase::getCodeString, c -> c.getTerminologyId().getValue())
                .containsExactly("de", "ISO_639-1");
        assertThat(rmObject.getArchetypeDetails().getTemplateId().getValue())
                .isEqualTo("ehrbase_blood_pressure_simple.de.v0");
        assertThat(rmObject.itemAtPath("/context/start_time/value")).isEqualTo(dto.getStartTimeValue());
        List<Object> observationList =
                rmObject.itemsAtPath("/content[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]");
        assertThat(observationList).size().isEqualTo(1);
        Observation observation = (Observation) observationList.get(0);
        DvCodedText expected = new DvCodedText("Fifth sound", new CodePhrase(new TerminologyId("local"), "at1012"));

        assertThat(observation.itemAtPath("/protocol[at0011]/items[at1010]/value"))
                .isEqualTo(expected);
        assertThat(observation.getSubject())
                .isNotNull()
                .extracting(Object::getClass)
                .isEqualTo(PartySelf.class);
    }

    @Test
    public void testUnflattenCorona() throws IOException {
        Composition expected = new CanonicalJson()
                .unmarshal(
                        IOUtils.toString(CompositionTestDataCanonicalJson.CORONA.getStream(), StandardCharsets.UTF_8),
                        Composition.class);
        RmToGeneratedDtoConverter rmToGeneratedDtoConverter =
                new RmToGeneratedDtoConverter(new TestDataTemplateProvider());
        CoronaAnamneseComposition coronaAnamneseComposition =
                rmToGeneratedDtoConverter.toGeneratedDto(expected, CoronaAnamneseComposition.class);

        GeneratedDtoToRmConverter cut = new GeneratedDtoToRmConverter(new TestDataTemplateProvider());
        Composition actual = (Composition) cut.toRMObject(coronaAnamneseComposition);
        assertThat(actual).isNotNull();
        assertThat(actual.getContent())
                .extracting(Locatable::getNameAsString, Locatable::getArchetypeNodeId)
                .containsExactlyInAnyOrder(actual.getContent().stream()
                        .map(c -> new Tuple(c.getNameAsString(), c.getArchetypeNodeId()))
                        .toArray(Tuple[]::new));
    }

    @Test
    public void testUnflattenEhrbaseMultiOccurrenceDeV1() {
        GeneratedDtoToRmConverter cut = new GeneratedDtoToRmConverter(new TestDataTemplateProvider());

        EhrbaseMultiOccurrenceDeV1Composition dto = TestData.buildEhrbaseMultiOccurrenceDeV1();

        Composition rmObject = (Composition) cut.toRMObject(dto);

        assertThat(rmObject).isNotNull();
        assertThat(rmObject.getArchetypeDetails().getTemplateId().getValue())
                .isEqualTo("ehrbase_multi_occurrence.de.v1");
        List<Object> observationList = rmObject.itemsAtPath("/content[openEHR-EHR-OBSERVATION.body_temperature.v2]");
        assertThat(observationList).size().isEqualTo(2);

        Observation observation1 = (Observation) observationList.get(0);
        List<Object> objects = observation1.itemsAtPath("/data[at0002]/events");
        assertThat(objects)
                .extracting(o -> ((PointEvent) o))
                .extracting(p -> (DvQuantity) p.itemAtPath("/data[at0001]/items[at0004]/value"))
                .extracting(DvQuantity::getMagnitude)
                .containsExactlyInAnyOrder(11d, 22d);
        DvCodedText dvCodedText = (DvCodedText) observation1.itemAtPath("/protocol[at0020]/items[at0021]/value");
        assertThat(dvCodedText.getValue()).isEqualTo("Forehead");

        Observation observation2 = (Observation) observationList.get(1);
        DvText dvText = (DvText) observation2.itemAtPath("/protocol[at0020]/items[at0021]/value");
        assertFalse(dvText instanceof DvCodedText);
        assertThat(dvText.getValue()).isEqualTo("location");
    }

    @Test
    public void testUnflattenAltEvent() {
        AlternativeEventsComposition alternativeEventsComposition = TestData.buildAlternativeEventsComposition();

        GeneratedDtoToRmConverter cut = new GeneratedDtoToRmConverter(new TestDataTemplateProvider());
        Composition actual = (Composition) cut.toRMObject(alternativeEventsComposition);

        assertThat(actual).isNotNull();
        assertThat(actual.getContent()).size().isEqualTo(1);
        Observation actualObservation = (Observation) actual.getContent().get(0);
        assertThat(actualObservation.getData().getEvents()).size().isEqualTo(3);
        assertThat(actualObservation.getData().getEvents())
                .extracting(e -> (Class) e.getClass())
                .containsExactlyInAnyOrder(PointEvent.class, PointEvent.class, IntervalEvent.class);

        List<PointEvent> pointEvents = actualObservation.getData().getEvents().stream()
                .filter(e -> PointEvent.class.isAssignableFrom(e.getClass()))
                .map(e -> (PointEvent) e)
                .collect(Collectors.toList());
        assertThat(pointEvents)
                .extracting(
                        p -> p.getTime().getValue(),
                        p -> ((DvQuantity) ((Element) p.getData().getItems().get(0)).getValue()).getMagnitude(),
                        p -> ((DvQuantity) ((Element) p.getData().getItems().get(0)).getValue()).getUnits())
                .containsExactlyInAnyOrder(
                        new Tuple(OffsetDateTime.of(1990, 11, 02, 12, 00, 00, 00, ZoneOffset.UTC), 30d, "kg"),
                        new Tuple(OffsetDateTime.of(2013, 11, 02, 12, 00, 00, 00, ZoneOffset.UTC), 55d, "kg"));

        List<IntervalEvent> intervalEvents = actualObservation.getData().getEvents().stream()
                .filter(e -> IntervalEvent.class.isAssignableFrom(e.getClass()))
                .map(e -> (IntervalEvent) e)
                .collect(Collectors.toList());
        assertThat(intervalEvents)
                .extracting(
                        p -> p.getTime().getValue(),
                        p -> ((DvQuantity) ((Element) p.getData().getItems().get(0)).getValue()).getMagnitude(),
                        p -> ((DvQuantity) ((Element) p.getData().getItems().get(0)).getValue()).getUnits(),
                        p -> p.getMathFunction().getValue(),
                        p -> p.getWidth().getValue())
                .containsExactlyInAnyOrder(new Tuple(
                        OffsetDateTime.of(2015, 11, 02, 12, 00, 00, 00, ZoneOffset.UTC),
                        60d,
                        "kg",
                        "mean",
                        Duration.ofDays(30)));
    }

    @Test
    public void testUnflattenEpsiode() {
        EpisodeOfCareComposition episode = TestData.buildEpisodeOfCareComposition();

        GeneratedDtoToRmConverter cut = new GeneratedDtoToRmConverter(new TestDataTemplateProvider());
        Composition actual = (Composition) cut.toRMObject(episode);

        assertThat(actual).isNotNull();

        assertThat(actual.getContent()).size().isEqualTo(1);
        AdminEntry actualAdminEntry = (AdminEntry) actual.getContent().get(0);
        List<Object> identifiers = actualAdminEntry.itemsAtPath("/data[at0001]/items[at0002]/value");
        assertThat(identifiers).extracting(i -> ((DvIdentifier) i).getId()).containsExactlyInAnyOrder("123", "456");

        List<Object> uris = actualAdminEntry.itemsAtPath("/data[at0001]/items[at0013]/value");
        assertThat(uris)
                .extracting(u -> ((DvURI) u).getValue())
                .containsExactlyInAnyOrder(URI.create("https://github.com/ehrbase"));
    }
}
