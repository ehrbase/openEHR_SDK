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
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartySelf;
import org.apache.commons.io.IOUtils;
import org.assertj.core.groups.Tuple;
import org.ehrbase.client.TestData;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.AlternativeEventsComposition;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.KorpergewichtAnyEventEnIntervalEvent;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.KorpergewichtAnyEventEnPointEvent;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.KorpergewichtBirthEnEvent;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.CoronaAnamneseComposition;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.VorhandenDefiningcode;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.EhrbaseBloodPressureSimpleDeV0Composition;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.KorotkoffSoundsDefiningcode;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.EhrbaseMultiOccurrenceDeV1Composition;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.*;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.EpisodeOfCareComposition;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition.EpisodeofcareAdminEntry;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition.EpisodeofcareTeamElement;
import org.ehrbase.client.classgenerator.examples.shareddefinition.MathFunctionDefiningcode;
import org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.TestAllTypesEnV1Composition;
import org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition.TestAllTypesChoiceDvcount;
import org.ehrbase.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.xmlencoding.CanonicalXML;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalXML;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ehrbase.client.TestData.buildAlternativeEventsComposition;
import static org.ehrbase.client.TestData.buildEpisodeOfCareComposition;

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
        EhrbaseBloodPressureSimpleDeV0Composition bloodPressureSimpleDeV0 = TestData.buildEhrbaseBloodPressureSimpleDeV0();
        RMObject rmObject = new Unflattener(new TestDataTemplateProvider()).unflatten(bloodPressureSimpleDeV0);

        EhrbaseBloodPressureSimpleDeV0Composition actual = cut.flatten((Locatable) rmObject, EhrbaseBloodPressureSimpleDeV0Composition.class);

        assertThat(actual).isNotNull();

        assertThat(actual.getComposer()).isNotNull().extracting(Object::getClass).isEqualTo(PartyIdentified.class);

        PartyIdentified composer = (PartyIdentified) actual.getComposer();
        assertThat(composer.getName()).isEqualTo("Test");
        assertThat(actual.getParticipations()).extracting(
                p -> ((PartyIdentified) p.getPerformer()).getName(),
                p -> p.getFunction().getValue()
        )
                .containsExactlyInAnyOrder(
                        new Tuple("Test", "Pos1"),
                        new Tuple("Test2", "Pos2")
                );

        assertThat(actual.getBloodPressureTrainingSample()).size().isEqualTo(1);
        assertThat(actual.getBloodPressureTrainingSample().get(0).getSubject()).isNotNull().extracting(Object::getClass).isEqualTo(PartySelf.class);
        assertThat(actual.getBloodPressureTrainingSample().get(0).getSystolicMagnitude()).isEqualTo(22d);
        assertThat(actual.getBloodPressureTrainingSample().get(0).getSystolicUnits()).isEqualTo("mm[Hg]");
        assertThat(actual.getBloodPressureTrainingSample().get(0).getKorotkoffSoundsDefiningcode()).isEqualTo(KorotkoffSoundsDefiningcode.FIFTH_SOUND);

    }


    @Test
    public void testFlattenEhrbaseMultiOccurrenceDeV1() {
        Flattener cut = new Flattener();
        EhrbaseMultiOccurrenceDeV1Composition bloodPressureSimpleDeV0 = TestData.buildEhrbaseMultiOccurrenceDeV1();
        RMObject rmObject = new Unflattener(new TestDataTemplateProvider()).unflatten(bloodPressureSimpleDeV0);

        EhrbaseMultiOccurrenceDeV1Composition actual = cut.flatten((Locatable) rmObject, EhrbaseMultiOccurrenceDeV1Composition.class);

        assertThat(actual).isNotNull();
        assertThat(actual.getBodyTemperature()).size().isEqualTo(2);
        BodyTemperatureObservation bodyTemperature1 = actual.getBodyTemperature().get(0);
        assertThat(bodyTemperature1.getAnyEvent())
                .extracting(e -> ((BodyTemperatureAnyEventPointEvent) e).getTemperatureMagnitude())
                .containsExactlyInAnyOrder(11d, 22d);

        BodyTemperatureLocationOfMeasurementChoice locationOfMeasurement1 = bodyTemperature1.getLocationOfMeasurement();
        assertThat(locationOfMeasurement1.getClass()).isEqualTo(BodyTemperatureLocationOfMeasurementDvcodedtext.class);
        assertThat(((BodyTemperatureLocationOfMeasurementDvcodedtext) locationOfMeasurement1).getLocationOfMeasurementDefiningcode()).isEqualTo(LocationOfMeasurementDefiningcode.FOREHEAD);

        BodyTemperatureObservation bodyTemperature2 = actual.getBodyTemperature().get(1);
        BodyTemperatureLocationOfMeasurementChoice locationOfMeasurement2 = bodyTemperature2.getLocationOfMeasurement();
        assertThat(locationOfMeasurement2.getClass()).isEqualTo(BodyTemperatureLocationOfMeasurementDvtext.class);
        assertThat(((BodyTemperatureLocationOfMeasurementDvtext) locationOfMeasurement2).getLocationOfMeasurementValue()).isEqualTo("location");

    }

    @Test
    public void testFlattenAllTypes() throws IOException {
        Composition composition = new CanonicalXML().unmarshal(IOUtils.toString(CompositionTestDataCanonicalXML.ALL_TYPES.getStream(), StandardCharsets.UTF_8), Composition.class);
        Flattener cut = new Flattener();
        TestAllTypesEnV1Composition actual = cut.flatten(composition, TestAllTypesEnV1Composition.class);
        assertThat(actual).isNotNull();
        assertThat(actual.getTestAllTypes().get(0).getChoice().getClass()).isEqualTo(TestAllTypesChoiceDvcount.class);
        assertThat(((TestAllTypesChoiceDvcount) actual.getTestAllTypes().get(0).getChoice()).getChoiceMagnitude()).isEqualTo(148L);
    }

    @Test
    public void testFlattenCorona() throws IOException {
        Composition composition = new CanonicalJson().unmarshal(IOUtils.toString(CompositionTestDataCanonicalJson.CORONA.getStream(), StandardCharsets.UTF_8), Composition.class);
        Flattener cut = new Flattener();
        CoronaAnamneseComposition actual = cut.flatten(composition, CoronaAnamneseComposition.class);
        assertThat(actual).isNotNull();
        assertThat(actual.getSymptome()).isNotNull();
        assertThat(actual.getSymptome().getHeiserkeit()).isNotNull();
        assertThat(actual.getSymptome().getHeiserkeit().getVorhandenDefiningcode()).isEqualTo(VorhandenDefiningcode.NICHT_VORHANDEN);
    }

    @Test
    public void testFlattenAltEvents() {
        Composition composition = (Composition) new Unflattener(new TestDataTemplateProvider()).unflatten(buildAlternativeEventsComposition());
        Flattener cut = new Flattener();
        AlternativeEventsComposition actual = cut.flatten(composition, AlternativeEventsComposition.class);
        assertThat(actual).isNotNull();
        assertThat(actual.getKorpergewicht()).size().isEqualTo(1);
        KorpergewichtBirthEnEvent birthEn = actual.getKorpergewicht().get(0).getBirthEn();
        assertThat(birthEn.getTimeValue()).isEqualTo(OffsetDateTime.of(1990, 11, 2, 12, 0, 0, 0, ZoneOffset.UTC));
        assertThat(birthEn.getGewichtMagnitude()).isEqualTo(30d);
        assertThat(birthEn.getGewichtUnits()).isEqualTo("kg");

        List<KorpergewichtAnyEventEnPointEvent> eventEnPointEvents = actual.getKorpergewicht().get(0).getAnyEventEn().stream().filter(e -> KorpergewichtAnyEventEnPointEvent.class.isAssignableFrom(e.getClass())).map(e -> (KorpergewichtAnyEventEnPointEvent) e).collect(Collectors.toList());
        assertThat(eventEnPointEvents).size().isEqualTo(1);
        assertThat(eventEnPointEvents.get(0).getTimeValue()).isEqualTo(OffsetDateTime.of(2013, 11, 2, 12, 0, 0, 0, ZoneOffset.UTC));
        assertThat(eventEnPointEvents.get(0).getGewichtMagnitude()).isEqualTo(55d);
        assertThat(eventEnPointEvents.get(0).getGewichtUnits()).isEqualTo("kg");


        List<KorpergewichtAnyEventEnIntervalEvent> anyEventEnIntervalEvents = actual.getKorpergewicht().get(0).getAnyEventEn().stream().filter(e -> KorpergewichtAnyEventEnIntervalEvent.class.isAssignableFrom(e.getClass())).map(e -> (KorpergewichtAnyEventEnIntervalEvent) e).collect(Collectors.toList());
        assertThat(eventEnPointEvents).size().isEqualTo(1);
        assertThat(anyEventEnIntervalEvents.get(0).getTimeValue()).isEqualTo(OffsetDateTime.of(2015, 11, 2, 12, 0, 0, 0, ZoneOffset.UTC));
        assertThat(anyEventEnIntervalEvents.get(0).getGewichtMagnitude()).isEqualTo(60d);
        assertThat(anyEventEnIntervalEvents.get(0).getGewichtUnits()).isEqualTo("kg");
        assertThat(anyEventEnIntervalEvents.get(0).getMathFunctionDefiningcode()).isEqualTo(MathFunctionDefiningcode.MEAN);
        assertThat(anyEventEnIntervalEvents.get(0).getWidthValue()).isEqualTo(Duration.ofDays(30));
    }

    @Test
    public void testFlattenEpisodeOfCare() {
        Composition composition = (Composition) new Unflattener(new TestDataTemplateProvider()).unflatten(buildEpisodeOfCareComposition());
        Flattener cut = new Flattener();
        EpisodeOfCareComposition actual = cut.flatten(composition, EpisodeOfCareComposition.class);
        assertThat(actual).isNotNull();
        assertThat(actual.getEpisodeofcare()).size().isEqualTo(1);
        EpisodeofcareAdminEntry episodeofcareAdminEntry = actual.getEpisodeofcare().get(0);

        assertThat(episodeofcareAdminEntry.getIdentifier()).extracting(e -> e.getValue().getId()).containsExactlyInAnyOrder("123", "456");

        assertThat(episodeofcareAdminEntry.getTeam()).extracting(EpisodeofcareTeamElement::getValue).containsExactlyInAnyOrder(URI.create("https://github.com/ehrbase"));

    }
}