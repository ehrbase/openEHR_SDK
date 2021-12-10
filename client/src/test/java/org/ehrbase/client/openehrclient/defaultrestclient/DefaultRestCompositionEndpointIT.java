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

package org.ehrbase.client.openehrclient.defaultrestclient;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartySelf;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.apache.commons.io.IOUtils;
import org.assertj.core.groups.Tuple;
import org.ehrbase.client.Integration;
import org.ehrbase.client.TestData;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.EhrbaseBloodPressureSimpleDeV0Composition;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.KorotkoffSoundsDefiningCode;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.EhrbaseMultiOccurrenceDeV1Composition;
import org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.*;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.EpisodeOfCareComposition;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition.EpisodeofcareAdminEntry;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition.EpisodeofcareTeamElement;
import org.ehrbase.client.classgenerator.examples.geccoserologischerbefundcomposition.GECCOSerologischerBefundComposition;
import org.ehrbase.client.classgenerator.examples.geccoserologischerbefundcomposition.definition.AnforderungDefiningCode;
import org.ehrbase.client.classgenerator.examples.geccoserologischerbefundcomposition.definition.BefundJedesEreignisPointEvent;
import org.ehrbase.client.classgenerator.examples.geccoserologischerbefundcomposition.definition.BefundObservation;
import org.ehrbase.client.classgenerator.examples.geccoserologischerbefundcomposition.definition.ProAnalytQuantitativesErgebnisDvCount;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.VirologischerBefundComposition;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition.ProVirusCluster;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.ParticipationMode;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.exception.OptimisticLockException;
import org.ehrbase.client.exception.WrongStatusCodeException;
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.client.openehrclient.CompositionEndpoint;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValuePath;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClientTestHelper.setupDefaultRestClientWithDefaultProvider;
import static org.junit.Assert.*;

@Category(Integration.class)
public class DefaultRestCompositionEndpointIT {

  private static OpenEhrClient openEhrClient;
  private UUID ehr;

  @BeforeClass
  public static void setup() throws URISyntaxException {
    openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
  }

  @After
  public void tearDown(){
    //delete the created EHR using the admin endpoint
    openEhrClient.adminEhrEndpoint().delete(ehr);
  }

  @Test
  public void testSaveCompositionEntity() {

    ehr = openEhrClient.ehrEndpoint().createEhr();
    EhrbaseBloodPressureSimpleDeV0Composition bloodPressureSimpleDeV0 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();

    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(bloodPressureSimpleDeV0);
    assertThat(bloodPressureSimpleDeV0.getVersionUid()).isNotNull();
    assertThat(bloodPressureSimpleDeV0.getVersionUid().getVersion()).isEqualTo(1L);

    bloodPressureSimpleDeV0.setSettingDefiningCode(Setting.EMERGENCY_CARE);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(bloodPressureSimpleDeV0);
    assertThat(bloodPressureSimpleDeV0.getVersionUid()).isNotNull();
    assertThat(bloodPressureSimpleDeV0.getVersionUid().getVersion()).isEqualTo(2L);

    bloodPressureSimpleDeV0.setVersionUid(
        new VersionUid(
            bloodPressureSimpleDeV0.getVersionUid().getUuid(),
            bloodPressureSimpleDeV0.getVersionUid().getSystem(),
            1L));

    try {
      openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(bloodPressureSimpleDeV0);
      fail();
    } catch (RuntimeException e) {
      assertThat(e.getClass()).isEqualTo(OptimisticLockException.class);
    }
  }
  @Test
  public void testSaveCompositionEntityNative() {

    ehr = openEhrClient.ehrEndpoint().createEhr();
    EhrbaseBloodPressureSimpleDeV0Composition bloodPressureSimpleDeV0 =
            TestData.buildEhrbaseBloodPressureSimpleDeV0();

    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(bloodPressureSimpleDeV0);
    assertThat(bloodPressureSimpleDeV0.getVersionUid()).isNotNull();
    assertThat(bloodPressureSimpleDeV0.getVersionUid().getVersion()).isEqualTo(1L);


    Optional<Composition> aNative = openEhrClient.compositionEndpoint(ehr).findRaw(bloodPressureSimpleDeV0.getVersionUid().getUuid());

    assertThat(aNative).isPresent();
    assertThat(aNative.get().getUid().getExtension()).isEqualTo("local.ehrbase.org::1");

   VersionUid versionUid= openEhrClient.compositionEndpoint(ehr).mergeRaw(aNative.get());

    assertThat(versionUid.getVersion()).isEqualTo(2l);

    aNative.get().setUid(null);

    versionUid = openEhrClient.compositionEndpoint(ehr).mergeRaw(aNative.get());
    assertThat(versionUid.getVersion()).isEqualTo(1L);

  }

  @Test
  public void testSaveCompositionEntityProxy() {


    ehr = openEhrClient.ehrEndpoint().createEhr();
    EhrbaseBloodPressureSimpleDeV0Composition bloodPressureSimpleDeV0 =
            TestData.buildEhrbaseBloodPressureSimpleDeV0();

    ProxyEhrbaseBloodPressureSimpleDeV0Composition proxy = new ProxyEhrbaseBloodPressureSimpleDeV0Composition();

    proxy.dummy = "dummy";

    proxy.setStartTimeValue(
            OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC));
    proxy.setEndTimeValue(OffsetDateTime.now());
    proxy.setBloodPressureTrainingSample(new ArrayList<>());
    proxy.setLanguage(Language.DE);
    proxy.setTerritory(Territory.DE);
    proxy.setCategoryDefiningCode(org.ehrbase.client.classgenerator.shareddefinition.Category.EVENT);
    proxy.setSettingDefiningCode(Setting.NURSING_HOME_CARE);
    proxy.setComposer(new PartyIdentified(null, "Test", null));
    proxy.setParticipations(new ArrayList<>());
    proxy
            .getParticipations()
            .add(
                    new Participation(
                            new PartyIdentified(null, "Test", null), new DvText("Pos1"), null, null));
    proxy
            .getParticipations()
            .add(
                    new Participation(
                            new PartyIdentified(null, "Test2", null), new DvText("Pos2"), null, null));


    proxy.setBloodPressureTrainingSample(bloodPressureSimpleDeV0.getBloodPressureTrainingSample());


    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(proxy);
    assertThat(proxy.getVersionUid()).isNotNull();
    assertThat(proxy.getVersionUid().getVersion()).isEqualTo(1L);

    proxy.setSettingDefiningCode(Setting.EMERGENCY_CARE);
    openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(proxy);
    assertThat(proxy.getVersionUid()).isNotNull();
    assertThat(proxy.getVersionUid().getVersion()).isEqualTo(2L);

    proxy.setVersionUid(
            new VersionUid(
                    proxy.getVersionUid().getUuid(),
                    proxy.getVersionUid().getSystem(),
                    1L));

    try {
      openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(proxy);
      fail();
    } catch (RuntimeException e) {
      assertThat(e.getClass()).isEqualTo(OptimisticLockException.class);
    }
  }

  @Test
  public void testSaveCompositionEntityCgiProxy() {


    ehr = openEhrClient.ehrEndpoint().createEhr();
    EhrbaseBloodPressureSimpleDeV0Composition bloodPressureSimpleDeV0 =
            TestData.buildEhrbaseBloodPressureSimpleDeV0();

    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(EhrbaseBloodPressureSimpleDeV0Composition.class);
    enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
      if (method.getDeclaringClass() != Object.class && method.getName().equals("getLanguage")) {
        return Language.EN;
      } else {
        return proxy.invokeSuper(obj, args);
      }
    } );

   EhrbaseBloodPressureSimpleDeV0Composition proxy = (EhrbaseBloodPressureSimpleDeV0Composition) enhancer.create();


    proxy.setStartTimeValue(
            OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC));
    proxy.setEndTimeValue(OffsetDateTime.now());
    proxy.setBloodPressureTrainingSample(new ArrayList<>());
    proxy.setLanguage(Language.DE);
    proxy.setTerritory(Territory.DE);
    proxy.setCategoryDefiningCode(org.ehrbase.client.classgenerator.shareddefinition.Category.EVENT);
    proxy.setSettingDefiningCode(Setting.NURSING_HOME_CARE);
    proxy.setComposer(new PartyIdentified(null, "Test", null));
    proxy.setParticipations(new ArrayList<>());
    proxy
            .getParticipations()
            .add(
                    new Participation(
                            new PartyIdentified(null, "Test", null), new DvText("Pos1"), null, null));
    proxy
            .getParticipations()
            .add(
                    new Participation(
                            new PartyIdentified(null, "Test2", null), new DvText("Pos2"), null, null));


    proxy.setBloodPressureTrainingSample(bloodPressureSimpleDeV0.getBloodPressureTrainingSample());


openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(proxy);
    assertThat(proxy.getVersionUid()).isNotNull();
    assertThat(proxy.getVersionUid().getVersion()).isEqualTo(1L);

    EhrbaseBloodPressureSimpleDeV0Composition actual = openEhrClient.compositionEndpoint(ehr).find(proxy.getVersionUid().getUuid(),EhrbaseBloodPressureSimpleDeV0Composition.class).get();
    assertThat(actual.getLanguage()).isEqualTo(Language.EN);


  }

  @Test
  public void testSaveCompositionEntityWithAny() {

    ehr = openEhrClient.ehrEndpoint().createEhr();
    GECCOSerologischerBefundComposition composition =
        TestData.buildGeccoSerologischerBefundComposition();

    composition = openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(composition);

    Optional<GECCOSerologischerBefundComposition> actual =
        openEhrClient
            .compositionEndpoint(ehr)
            .find(composition.getVersionUid().getUuid(), GECCOSerologischerBefundComposition.class);

    assertThat(actual).isPresent();

    assertThat(actual.get().getBefund()).size().isEqualTo(1);
    assertThat(actual.get().getBefund().get(0).getJedesEreignis()).size().isEqualTo(1);
    assertThat(actual.get().getBefund().get(0).getJedesEreignis().get(0).getQuantitativesErgebnis())
        .isNotNull();
    assertThat(
            actual
                .get()
                .getBefund()
                .get(0)
                .getJedesEreignis()
                .get(0)
                .getQuantitativesErgebnis()
                .getClass())
        .isEqualTo(ProAnalytQuantitativesErgebnisDvCount.class);
    assertThat(
            ((ProAnalytQuantitativesErgebnisDvCount)
                    actual
                        .get()
                        .getBefund()
                        .get(0)
                        .getJedesEreignis()
                        .get(0)
                        .getQuantitativesErgebnis())
                .getQuantitativesErgebnisMagnitude())
        .isEqualTo(22l);
  }

  @Test
  public void testSaveCompositionEntityWithAnyProxy() {

    ehr = openEhrClient.ehrEndpoint().createEhr();
    GECCOSerologischerBefundComposition composition1 = new GECCOSerologischerBefundComposition();
    composition1.setLanguage(Language.DE);
    composition1.setTerritory(Territory.DE);
    composition1.setCategoryDefiningCode(org.ehrbase.client.classgenerator.shareddefinition.Category.EVENT);
    composition1.setSettingDefiningCode(Setting.NURSING_HOME_CARE);
    composition1.setComposer(new PartyIdentified(null, "Test", null));
    composition1.setStartTimeValue(OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC));
    composition1.setParticipations(new ArrayList<>());
    composition1.setBefund(new ArrayList<>());

    Enhancer enhancerObservation = new Enhancer();

    enhancerObservation.setSuperclass(BefundObservation.class);
    enhancerObservation.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
      if (method.getDeclaringClass() != Object.class && method.getName().equals("getLanguage")) {
        return Language.EN;
      } else {
        return proxy.invokeSuper(obj, args);
      }
    } );

    BefundObservation befundObservation =
            (BefundObservation) enhancerObservation.create();
    composition1.getBefund().add(befundObservation);
    befundObservation.setSubject(new PartySelf());
    befundObservation.setJedesEreignis(new ArrayList<>());
    // Read will be overwritten by enhancerObservation
    befundObservation.setLanguage(Language.DE);
    befundObservation.setAnforderungDefiningCode(
        AnforderungDefiningCode.SARS_COV2_COVID19_AB_PANEL_SERUM_OR_PLASMA_BY_IMMUNOASSAY);
    befundObservation.setOriginValue(
        OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC));
    BefundJedesEreignisPointEvent event = new BefundJedesEreignisPointEvent();
    event.setTimeValue(OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC));
    befundObservation.getJedesEreignis().add(event);

    Enhancer enhancerProAnalytQuantitativesErgebnisDvCount = new Enhancer();
    enhancerProAnalytQuantitativesErgebnisDvCount.setSuperclass(ProAnalytQuantitativesErgebnisDvCount.class);

    enhancerProAnalytQuantitativesErgebnisDvCount.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
      if (method.getDeclaringClass() != Object.class && method.getName().equals("getQuantitativesErgebnisMagnitude")) {
        return 33l;
      } else {
        return proxy.invokeSuper(obj, args);
      }
    } );

    ProAnalytQuantitativesErgebnisDvCount quantitativesErgebnis =
            (ProAnalytQuantitativesErgebnisDvCount) enhancerProAnalytQuantitativesErgebnisDvCount.create();
    quantitativesErgebnis.setQuantitativesErgebnisMagnitude(22l);
    event.setQuantitativesErgebnis(quantitativesErgebnis);
    GECCOSerologischerBefundComposition composition =
            composition1;

    composition = openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(composition);

    Optional<GECCOSerologischerBefundComposition> actual =
            openEhrClient
                    .compositionEndpoint(ehr)
                    .find(composition.getVersionUid().getUuid(), GECCOSerologischerBefundComposition.class);

    assertThat(actual).isPresent();

    assertThat(actual.get().getBefund()).size().isEqualTo(1);
    BefundObservation actualObservation = actual.get().getBefund().get(0);
    assertThat(actualObservation.getLanguage()).isEqualTo(Language.EN);
    assertThat(actualObservation.getJedesEreignis()).size().isEqualTo(1);
    assertThat(actualObservation.getJedesEreignis().get(0).getQuantitativesErgebnis())
            .isNotNull();
    assertThat(
            actualObservation
                    .getJedesEreignis()
                    .get(0)
                    .getQuantitativesErgebnis()
                    .getClass())
            .isEqualTo(ProAnalytQuantitativesErgebnisDvCount.class);
    assertThat(
            ((ProAnalytQuantitativesErgebnisDvCount)
                    actualObservation
                            .getJedesEreignis()
                            .get(0)
                            .getQuantitativesErgebnis())
                    .getQuantitativesErgebnisMagnitude())
            .isEqualTo(33L);
  }

  @Test
  public void testSaveCompositionWithDefaultEntity() throws URISyntaxException {

    openEhrClient =
        setupDefaultRestClientWithDefaultProvider(
            o -> {
              DefaultValues defaultValues = new DefaultValues();
              defaultValues.addDefaultValue(
                  DefaultValuePath.END_TIME,
                  OffsetDateTime.of(2019, 05, 03, 22, 00, 00, 00, ZoneOffset.UTC));

              Participation participation =
                  new Participation(
                      new PartyIdentified(null, "Dr. Peter", null),
                      new DvText("Performer"),
                      ParticipationMode.PHYSICALLY_PRESENT.toCodedText(),
                      null);
              defaultValues.addDefaultValue(
                  DefaultValuePath.PARTICIPATION,
                  new ArrayList<>(Collections.singleton(participation)));
              return defaultValues;
            });

    ehr = openEhrClient.ehrEndpoint().createEhr();
    EhrbaseBloodPressureSimpleDeV0Composition bloodPressureSimpleDeV0 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0WithEmptyFields();

    // Not set during creation
    assertThat(bloodPressureSimpleDeV0.getEndTimeValue()).isNull();
    assertThat(bloodPressureSimpleDeV0.getParticipations()).isEmpty();

    // during saving default values will be set
    bloodPressureSimpleDeV0 =
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(bloodPressureSimpleDeV0);

    assertThat(bloodPressureSimpleDeV0.getEndTimeValue())
        .isEqualTo(OffsetDateTime.of(2019, 05, 03, 22, 00, 00, 00, ZoneOffset.UTC));
    assertThat(bloodPressureSimpleDeV0.getParticipations())
        .extracting(
            p -> ((PartyIdentified) p.getPerformer()).getName(), p -> p.getMode().getValue())
        .containsExactlyInAnyOrder(new Tuple("Dr. Peter", "physically present"));
  }

  @Test
  public void testFind() {

    ehr = openEhrClient.ehrEndpoint().createEhr();
    EhrbaseBloodPressureSimpleDeV0Composition bloodPressureSimpleDeV0 =
        TestData.buildEhrbaseBloodPressureSimpleDeV0();

    CompositionEndpoint compositionEndpoint = openEhrClient.compositionEndpoint(ehr);
    EhrbaseBloodPressureSimpleDeV0Composition version1 =
        compositionEndpoint.mergeCompositionEntity(bloodPressureSimpleDeV0);

    Optional<EhrbaseBloodPressureSimpleDeV0Composition> actual =
        compositionEndpoint.find(
            version1.getVersionUid().getUuid(), EhrbaseBloodPressureSimpleDeV0Composition.class);
    assertTrue(actual.isPresent());

    assertThat(actual.get().getComposer())
        .isNotNull()
        .extracting(Object::getClass)
        .isEqualTo(PartyIdentified.class);

    PartyIdentified composer = (PartyIdentified) actual.get().getComposer();
    assertThat(composer.getName()).isEqualTo("Test");

    assertThat(actual.get().getBloodPressureTrainingSample()).size().isEqualTo(1);
    assertThat(actual.get().getBloodPressureTrainingSample().get(0).getSubject())
        .isNotNull()
        .extracting(Object::getClass)
        .isEqualTo(PartySelf.class);
    assertThat(actual.get().getBloodPressureTrainingSample().get(0).getSystolicMagnitude())
        .isEqualTo(22d);
    assertThat(actual.get().getBloodPressureTrainingSample().get(0).getSystolicUnits())
        .isEqualTo("mm[Hg]");
    assertThat(
            actual.get().getBloodPressureTrainingSample().get(0).getKorotkoffSoundsDefiningCode())
        .isEqualTo(KorotkoffSoundsDefiningCode.FIFTH_SOUND);
  }

  @Test
  public void testEhrbaseMultiOccurrenceDeV1() {

    ehr = openEhrClient.ehrEndpoint().createEhr();
    EhrbaseMultiOccurrenceDeV1Composition bloodPressureSimpleDeV0 =
        TestData.buildEhrbaseMultiOccurrenceDeV1();

    CompositionEndpoint compositionEndpoint = openEhrClient.compositionEndpoint(ehr);
    EhrbaseMultiOccurrenceDeV1Composition version1 =
        compositionEndpoint.mergeCompositionEntity(bloodPressureSimpleDeV0);

    Optional<EhrbaseMultiOccurrenceDeV1Composition> actual =
        compositionEndpoint.find(
            version1.getVersionUid().getUuid(), EhrbaseMultiOccurrenceDeV1Composition.class);
    assertTrue(actual.isPresent());
    BodyTemperatureObservation bodyTemperature1 = actual.get().getBodyTemperature().get(0);
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

    BodyTemperatureObservation bodyTemperature2 = actual.get().getBodyTemperature().get(1);
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
  public void testEpisodeOfCare() {

    ehr = openEhrClient.ehrEndpoint().createEhr();
    EpisodeOfCareComposition bloodPressureSimpleDeV0 = TestData.buildEpisodeOfCareComposition();

    CompositionEndpoint compositionEndpoint = openEhrClient.compositionEndpoint(ehr);
    EpisodeOfCareComposition version1 =
        compositionEndpoint.mergeCompositionEntity(bloodPressureSimpleDeV0);

    Optional<EpisodeOfCareComposition> actual =
        compositionEndpoint.find(
            version1.getVersionUid().getUuid(), EpisodeOfCareComposition.class);
    assertTrue(actual.isPresent());
    assertThat(actual.get().getVersionUid())
        .extracting(v -> v.getUuid().toString(), VersionUid::getVersion)
        .containsExactly(version1.getVersionUid().getUuid().toString(), 1L);
    assertThat(actual.get().getFeederAudit())
        .isNotNull()
        .extracting(
            feederAudit -> feederAudit.getFeederSystemAudit().getSystemId(),
            feederAudit1 -> feederAudit1.getOriginatingSystemAudit().getSystemId())
        .containsExactly("System 1", "System 2");

    assertThat(actual.get().getEpisodeofcare()).size().isEqualTo(1);

    EpisodeofcareAdminEntry episodeofcareAdminEntry = actual.get().getEpisodeofcare().get(0);

    assertThat(episodeofcareAdminEntry.getIdentifier())
        .extracting(e -> e.getValue().getId())
        .containsExactlyInAnyOrder("123", "456");

    assertThat(episodeofcareAdminEntry.getTeam())
        .extracting(EpisodeofcareTeamElement::getValue)
        .containsExactlyInAnyOrder(URI.create("https://github.com/ehrbase"));
  }

  @Test
  public void testVirologischerBefund() throws IOException {
    Composition composition =
        new CanonicalJson()
            .unmarshal(
                IOUtils.toString(
                    CompositionTestDataCanonicalJson.VIROLOGY_FINDING_WITH_SPECIMEN.getStream(),
                    StandardCharsets.UTF_8),
                Composition.class);

    assertThat(
            composition.itemsAtPath(
                "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1]/data[at0001]/events[at0002]/data[at0003]"))
        .isNotNull();

    Flattener flattener = new Flattener(new TestDataTemplateProvider());

    VirologischerBefundComposition virologischerBefundComposition =
        flattener.flatten(composition, VirologischerBefundComposition.class);
    assertThat(virologischerBefundComposition.getBefund()).isNotNull();

    // with the test data
    virologischerBefundComposition = TestData.buildTestVirologischerBefundComposition();
    assertThat(virologischerBefundComposition.getBefund()).isNotNull();

    ehr = openEhrClient.ehrEndpoint().createEhr();
    VirologischerBefundComposition version1 =
        openEhrClient
            .compositionEndpoint(ehr)
            .mergeCompositionEntity(virologischerBefundComposition);
    Optional<VirologischerBefundComposition> actual =
        openEhrClient
            .compositionEndpoint(ehr)
            .find(version1.getVersionUid().getUuid(), VirologischerBefundComposition.class);
    assertThat(actual).isPresent();
    assertThat(actual.get().getBefund()).isNotNull();

    assertThat(actual.get().getBefund().getKultur().get(0).getProVirus())
        .extracting(
            ProVirusCluster::getVirusValue, ProVirusCluster::getAnalyseergebnisReihenfolgeMagnitude)
        .containsExactlyInAnyOrder(new Tuple("SARS-Cov-2", 32L), new Tuple("SARS-Cov-2", 34L));
  }

  @Test
  public void testDeleteCompositionValid() {
    ehr = openEhrClient.ehrEndpoint().createEhr();
    CompositionEndpoint compositionEndpoint = openEhrClient.compositionEndpoint(ehr);

    EhrbaseBloodPressureSimpleDeV0Composition composition = TestData.buildEhrbaseBloodPressureSimpleDeV0();
    composition = compositionEndpoint.mergeCompositionEntity(composition);

    Optional<EhrbaseBloodPressureSimpleDeV0Composition> result;
    result = compositionEndpoint.find(composition.getVersionUid().getUuid(), EhrbaseBloodPressureSimpleDeV0Composition.class);
    assertTrue(result.isPresent());

    compositionEndpoint.delete(composition.getVersionUid());

    result = compositionEndpoint.find(composition.getVersionUid().getUuid(), EhrbaseBloodPressureSimpleDeV0Composition.class);
    assertTrue(result.isEmpty());
  }

  @Test
  public void testDeleteCompositionNullPrecedingVersionUid() {
    ehr = openEhrClient.ehrEndpoint().createEhr();
    CompositionEndpoint compositionEndpoint = openEhrClient.compositionEndpoint(ehr);

    assertThrows(ClientException.class, () -> compositionEndpoint.delete(null));
  }

  @Test
  public void testDeleteCompositionUnknownPrecedingVersionUid() {
    ehr = openEhrClient.ehrEndpoint().createEhr();
    CompositionEndpoint compositionEndpoint = openEhrClient.compositionEndpoint(ehr);

    VersionUid precedingVersionUid = new VersionUid("bdd8e4f3-b587-479f-a2ff-d4143ef9ea06::local.ehrbase.org::1");
    assertThatNoException().isThrownBy(() -> compositionEndpoint.delete(precedingVersionUid));
  }

  @Test
  public void testDeleteCompositionInvalidPrecedingVersionUid() {
    ehr = openEhrClient.ehrEndpoint().createEhr();
    CompositionEndpoint compositionEndpoint = openEhrClient.compositionEndpoint(ehr);

    EhrbaseBloodPressureSimpleDeV0Composition composition = TestData.buildEhrbaseBloodPressureSimpleDeV0();
    composition = compositionEndpoint.mergeCompositionEntity(composition);
    composition = compositionEndpoint.mergeCompositionEntity(composition);

    VersionUid precedingVersionUid = new VersionUid(composition.getVersionUid().getUuid(), composition.getVersionUid().getSystem(), 1);
    assertThrows(WrongStatusCodeException.class, () -> compositionEndpoint.delete(precedingVersionUid));
  }
}
