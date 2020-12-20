/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.client.classgenerator;

import static org.assertj.core.api.Assertions.assertThat;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.XmlException;
import org.assertj.core.groups.Tuple;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.parser.OPTParser;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

public class ClassGeneratorTest {

  public static final String PACKAGE_NAME = "org.ehrbase.client.classgenerator.examples";

  private static final boolean WRITE_FILES = false;

  @Test
  public void testGenerate() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(
                OperationalTemplateTestData.BLOOD_PRESSURE_SIMPLE.getStream())
            .getTemplate();
    WebTemplate webTemplate = new OPTParser(template).parse();
    ClassGeneratorConfig config = new ClassGeneratorConfig();
    config.setAddNullFlavor(true);
    ClassGenerator cut = new ClassGenerator(config);
    ClassGeneratorResult generate = null;

    generate = cut.generate(PACKAGE_NAME, webTemplate);

    List<FieldSpec> fieldSpecs =
        generate.getClasses().values().stream()
            .flatMap(Collection::stream)
            .filter(t -> !t.kind.equals(TypeSpec.Kind.ENUM))
            .map(t -> t.fieldSpecs)
            .flatMap(List::stream)
            .collect(Collectors.toList());

    assertThat(fieldSpecs)
        .extracting(f -> f.name, f -> f.type.toString())
        .containsExactlyInAnyOrder(
            new Tuple("versionUid", "org.ehrbase.client.openehrclient.VersionUid"),
            new Tuple("device", "java.util.List<com.nedap.archie.rm.datastructures.Cluster>"),
            new Tuple("language", "org.ehrbase.client.classgenerator.shareddefinition.Language"),
            new Tuple(
                "levelOfExertion", "java.util.List<com.nedap.archie.rm.datastructures.Cluster>"),
            new Tuple("commentValue", "java.lang.String"),
            new Tuple(
                "cuffSizeDefiningCode",
                "org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.CuffSizeDefiningCode"),
            new Tuple(
                "korotkoffSoundsDefiningCode",
                "org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.KorotkoffSoundsDefiningCode"),
            new Tuple("systolicMagnitude", "java.lang.Double"),
            new Tuple("systolicUnits", "java.lang.String"),
            new Tuple("diastolicMagnitude", "java.lang.Double"),
            new Tuple("diastolicUnits", "java.lang.String"),
            new Tuple(
                "positionDefiningCode",
                "org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.PositionDefiningCode"),
            new Tuple("tiltMagnitude", "java.lang.Double"),
            new Tuple("tiltUnits", "java.lang.String"),
            new Tuple("meanArterialPressureMagnitude", "java.lang.Double"),
            new Tuple("meanArterialPressureUnits", "java.lang.String"),
            new Tuple("timeValue", "java.time.temporal.TemporalAccessor"),
            new Tuple("subject", "com.nedap.archie.rm.generic.PartyProxy"),
            new Tuple("originValue", "java.time.temporal.TemporalAccessor"),
            new Tuple("pulsePressureMagnitude", "java.lang.Double"),
            new Tuple("pulsePressureUnits", "java.lang.String"),
            new Tuple(
                "locationOfMeasurementDefiningCode",
                "org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.LocationOfMeasurementDefiningCode"),
            new Tuple("modelValue", "java.lang.String"),
            new Tuple("serialNumberValue", "java.lang.String"),
            new Tuple("dateLastServicedValue", "java.time.temporal.TemporalAccessor"),
            new Tuple("dateLastCalibrationValue", "java.time.temporal.TemporalAccessor"),
            new Tuple("nameValue", "java.lang.String"),
            new Tuple("descriptionValue", "java.lang.String"),
            new Tuple("components", "com.nedap.archie.rm.datastructures.Cluster"),
            new Tuple("servicedByValue", "java.lang.String"),
            new Tuple("manufacturerValue", "java.lang.String"),
            new Tuple("endTimeValue", "java.time.temporal.TemporalAccessor"),
            new Tuple("language", "org.ehrbase.client.classgenerator.shareddefinition.Language"),
            new Tuple("healthCareFacility", "com.nedap.archie.rm.generic.PartyIdentified"),
            new Tuple("composer", "com.nedap.archie.rm.generic.PartyProxy"),
            new Tuple(
                "settingDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.Setting"),
            new Tuple("territory", "org.ehrbase.client.classgenerator.shareddefinition.Territory"),
            new Tuple(
                "bloodPressureTrainingSample",
                "java.util.List<org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.BloodPressureTrainingSampleObservation>"),
            //   new Tuple("location", "java.lang.String"),
            new Tuple(
                "deviceDetailsTrainingSample",
                "java.util.List<org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.DeviceDetailsTrainingSampleCluster>"),
            new Tuple(
                "categoryDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.Category"),
            new Tuple("startTimeValue", "java.time.temporal.TemporalAccessor"),
            new Tuple(
                "participations", "java.util.List<com.nedap.archie.rm.generic.Participation>"),
            new Tuple("feederAudit", "com.nedap.archie.rm.archetyped.FeederAudit"),
            new Tuple("location", "java.lang.String"),
            new Tuple("feederAudit", "com.nedap.archie.rm.archetyped.FeederAudit"),
            new Tuple("feederAudit", "com.nedap.archie.rm.archetyped.FeederAudit"),
            new Tuple(
                "nameNullFlavourDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.NullFlavour"),
            new Tuple(
                "descriptionNullFlavourDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.NullFlavour"),
            new Tuple(
                "manufacturerNullFlavourDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.NullFlavour"),
            new Tuple(
                "modelNullFlavourDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.NullFlavour"),
            new Tuple(
                "serialNumberNullFlavourDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.NullFlavour"),
            new Tuple(
                "dateLastServicedNullFlavourDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.NullFlavour"),
            new Tuple(
                "dateLastCalibrationNullFlavourDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.NullFlavour"),
            new Tuple(
                "servicedByNullFlavourDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.NullFlavour"),
            new Tuple(
                "systolicNullFlavourDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.NullFlavour"),
            new Tuple(
                "diastolicNullFlavourDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.NullFlavour"),
            new Tuple(
                "meanArterialPressureNullFlavourDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.NullFlavour"),
            new Tuple(
                "pulsePressureNullFlavourDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.NullFlavour"),
            new Tuple(
                "commentNullFlavourDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.NullFlavour"),
            new Tuple(
                "positionNullFlavourDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.NullFlavour"),
            new Tuple(
                "tiltNullFlavourDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.NullFlavour"),
            new Tuple(
                "cuffSizeNullFlavourDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.NullFlavour"),
            new Tuple(
                "locationOfMeasurementNullFlavourDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.NullFlavour"),
            new Tuple(
                "korotkoffSoundsNullFlavourDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.NullFlavour"));

    writeFiles(generate);
  }

  private void writeFiles(ClassGeneratorResult generate) throws IOException {
    if (WRITE_FILES) {
      Path path = Paths.get("..", "client/src/test/java/");
      generate.getClasses().keySet().stream()
          .filter(s -> !s.contains("definition"))
          .findFirst()
          .ifPresent(
              s -> {
                try {
                  FileUtils.cleanDirectory(
                      Paths.get(String.valueOf(path), s.replace(".", "/")).toFile());
                } catch (Exception e) {
                  // NOP
                }
              });
      List<JavaFile> generateFiles = generate.writeFiles(path);

      FieldGenerator fieldGenerator = new FieldGenerator();
      fieldGenerator.generate(generateFiles).writeFiles(path);
    }
  }

  @Test
  public void testGenerateAltEvents() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.ALT_EVENTS.getStream())
            .getTemplate();
    WebTemplate webTemplate = new OPTParser(template).parse();
    ClassGenerator cut = new ClassGenerator(new ClassGeneratorConfig());

    ClassGeneratorResult generate = cut.generate(PACKAGE_NAME, webTemplate);

    List<FieldSpec> fieldSpecs =
        generate.getClasses().values().stream()
            .flatMap(Collection::stream)
            .filter(t -> !t.kind.equals(TypeSpec.Kind.ENUM))
            .map(t -> t.fieldSpecs)
            .flatMap(List::stream)
            .collect(Collectors.toList());

    assertThat(fieldSpecs)
        .extracting(f -> f.name, f -> f.type.toString())
        .containsExactlyInAnyOrder(
            new Tuple("value", "java.lang.String"),
            new Tuple("timeValue", "java.time.temporal.TemporalAccessor"),
            new Tuple("gewichtMagnitude", "java.lang.Double"),
            new Tuple("gewichtUnits", "java.lang.String"),
            new Tuple(
                "confoundingFactorsEn",
                "java.util.List<org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.KorpergewichtConfoundingFactorsEnElement>"),
            new Tuple("commentEnValue", "java.lang.String"),
            new Tuple(
                "stateOfDressEnDefiningCode",
                "org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.StateOfDressEnDefiningCode"),
            new Tuple("value", "java.lang.String"),
            new Tuple("timeValue", "java.time.temporal.TemporalAccessor"),
            new Tuple("gewichtMagnitude", "java.lang.Double"),
            new Tuple("gewichtUnits", "java.lang.String"),
            new Tuple(
                "confoundingFactorsEn",
                "java.util.List<org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.KorpergewichtConfoundingFactorsEnElement>"),
            new Tuple("commentEnValue", "java.lang.String"),
            new Tuple(
                "stateOfDressEnDefiningCode",
                "org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.StateOfDressEnDefiningCode"),
            new Tuple("value", "java.lang.String"),
            new Tuple("timeValue", "java.time.temporal.TemporalAccessor"),
            new Tuple("gewichtMagnitude", "java.lang.Double"),
            new Tuple("gewichtUnits", "java.lang.String"),
            new Tuple(
                "confoundingFactorsEn",
                "java.util.List<org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.KorpergewichtConfoundingFactorsEnElement>"),
            new Tuple("widthValue", "java.time.temporal.TemporalAmount"),
            new Tuple("commentEnValue", "java.lang.String"),
            new Tuple(
                "stateOfDressEnDefiningCode",
                "org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.StateOfDressEnDefiningCode"),
            new Tuple(
                "mathFunctionDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.MathFunction"),
            new Tuple(
                "birthEn",
                "org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.KorpergewichtBirthEnPointEvent"),
            new Tuple(
                "anyEventEn",
                "java.util.List<org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.KorpergewichtAnyEventEnChoice>"),
            new Tuple("extensionEn", "java.util.List<com.nedap.archie.rm.datastructures.Cluster>"),
            new Tuple("language", "org.ehrbase.client.classgenerator.shareddefinition.Language"),
            new Tuple("originValue", "java.time.temporal.TemporalAccessor"),
            new Tuple("gerat", "com.nedap.archie.rm.datastructures.Cluster"),
            new Tuple("subject", "com.nedap.archie.rm.generic.PartyProxy"),
            new Tuple("versionUid", "org.ehrbase.client.openehrclient.VersionUid"),
            new Tuple("endTimeValue", "java.time.temporal.TemporalAccessor"),
            new Tuple("language", "org.ehrbase.client.classgenerator.shareddefinition.Language"),
            new Tuple("healthCareFacility", "com.nedap.archie.rm.generic.PartyIdentified"),
            new Tuple("statusValue", "java.lang.String"),
            new Tuple("berichtIdValue", "java.lang.String"),
            new Tuple("territory", "org.ehrbase.client.classgenerator.shareddefinition.Territory"),
            new Tuple("startTimeValue", "java.time.temporal.TemporalAccessor"),
            new Tuple("composer", "com.nedap.archie.rm.generic.PartyProxy"),
            new Tuple(
                "settingDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.Setting"),
            new Tuple(
                "korpergewicht",
                "java.util.List<org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.KorpergewichtObservation>"),
            new Tuple("erweiterung", "java.util.List<com.nedap.archie.rm.datastructures.Cluster>"),
            new Tuple("location", "java.lang.String"),
            new Tuple(
                "categoryDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.Category"),
            new Tuple(
                "participations", "java.util.List<com.nedap.archie.rm.generic.Participation>"),
            new Tuple("feederAudit", "com.nedap.archie.rm.archetyped.FeederAudit"),
            new Tuple("feederAudit", "com.nedap.archie.rm.archetyped.FeederAudit"),
            new Tuple("feederAudit", "com.nedap.archie.rm.archetyped.FeederAudit"),
            new Tuple("feederAudit", "com.nedap.archie.rm.archetyped.FeederAudit"),
            new Tuple("feederAudit", "com.nedap.archie.rm.archetyped.FeederAudit"),
            new Tuple("sampleCount", "java.lang.Long"),
            new Tuple("feederAudit", "com.nedap.archie.rm.archetyped.FeederAudit"),
            new Tuple("feederAudit", "com.nedap.archie.rm.archetyped.FeederAudit"),
            new Tuple("feederAudit", "com.nedap.archie.rm.archetyped.FeederAudit"));

    writeFiles(generate);
  }

  @Test
  public void testGenerateDiagnose() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.DIAGNOSE.getStream())
            .getTemplate();
    ClassGenerator cut = new ClassGenerator(new ClassGeneratorConfig());
    ClassGeneratorResult generate = cut.generate(PACKAGE_NAME, new OPTParser(template).parse());

    Set<String> derDiagnoseDefiningCode =
        generate
            .getClasses()
            .get("org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition")
            .stream()
            .filter(t -> t.name.equals("NameDesProblemsDerDiagnoseDefiningCode"))
            .findAny()
            .get()
            .enumConstants
            .keySet();

    assertThat(derDiagnoseDefiningCode)
        .containsExactlyInAnyOrder(
            "KORONAVIREN_ALS_URSACHE_VON_KRANKHEITEN_DIE_IN_ANDEREN_KAPITELN_KLASSIFIZIERT_SIND",
            "COVID19_VIRUS_NICHT_NACHGEWIESEN",
            "COVID19_VIRUS_NACHGEWIESEN",
            "INFEKTION_DURCH_KORONAVIREN_NICHT_NAHER_BEZEICHNETER_LOKALISATION");

    writeFiles(generate);
  }

  @Test
  public void testGenerateEpisode() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.EPISODE_OF_CARE.getStream())
            .getTemplate();
    ClassGenerator cut = new ClassGenerator(new ClassGeneratorConfig());
    ClassGeneratorResult generate = cut.generate(PACKAGE_NAME, new OPTParser(template).parse());
    List<String> fieldSpecs =
        generate.getClasses().values().stream()
            .flatMap(Collection::stream)
            .filter(t -> !t.kind.equals(TypeSpec.Kind.ENUM))
            .map(t -> t.fieldSpecs)
            .flatMap(List::stream)
            .map(f -> f.name)
            .collect(Collectors.toList());

    assertThat(fieldSpecs)
        .containsExactlyInAnyOrder(
            "value",
            "value",
            "rankMagnitude",
            "roleValue",
            "diagnosisEntry",
            "identifier",
            "team",
            "statusDefiningCode",
            "language",
            "diagnosis",
            "subject",
            "managingOrganizationValue",
            "typeValue",
            "careManagerValue",
            "versionUid",
            "episodeofcare",
            "endTimeValue",
            "participations",
            "language",
            "composer",
            "settingDefiningCode",
            "territory",
            "feederAudit",
            "location",
            "categoryDefiningCode",
            "startTimeValue",
            "upperValue",
            "lowerValue",
            "lowerIncluded",
            "upperIncluded",
            "healthCareFacility",
            "feederAudit",
            "feederAudit",
            "feederAudit",
            "feederAudit");

    writeFiles(generate);
  }

  @Test
  public void testGenerateSmICS() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.SM_I_C_S_BEFUND.getStream())
            .getTemplate();
    ClassGenerator cut = new ClassGenerator(new ClassGeneratorConfig());
    ClassGeneratorResult generate = cut.generate(PACKAGE_NAME, new OPTParser(template).parse());
    List<String> fieldSpecs =
        generate.getClasses().values().stream()
            .flatMap(Collection::stream)
            .filter(t -> !t.kind.equals(TypeSpec.Kind.ENUM))
            .map(t -> t.fieldSpecs)
            .flatMap(List::stream)
            .map(f -> f.name)
            .collect(Collectors.toList());

    assertThat(fieldSpecs)
        .containsExactlyInAnyOrder(
            "value",
            "valueValue",
            "value",
            "idDerPerson",
            "artDerPersonValue",
            "kommentarValue",
            "fallKategorieValue",
            "fallidentifikationValue",
            "fallArtValue",
            "beteiligtePersonen",
            "zimmerValue",
            "stationValue",
            "details",
            "standorttypValue",
            "standortbeschreibungValue",
            "standortschlusselValue",
            "antibiotikumDefiningCode",
            "resistenzValue",
            "kommentarValue",
            "minimaleHemmkonzentrationMagnitude",
            "minimaleHemmkonzentrationUnits",
            "analyseergebnisDetails",
            "laboranalytResultat",
            "resistenzmechanismusBezeichnungValue",
            "value",
            "antibiogramm",
            "haufigkeit",
            "kommentarValue",
            "resistenzmechanismus",
            "keimzahlMagnitude",
            "keimzahlUnits",
            "weitereErganzungen",
            "keimSubtyp",
            "virulenzfaktorValue",
            "mreKlasseDefiningCode",
            "value",
            "value",
            "value",
            "bewertungValue",
            "kommentarValue",
            "artDerTypisierung",
            "ergebnis",
            "erregernameDefiningCode",
            "language",
            "standort",
            "zeitpunktDesErstenErregernachweisesValue",
            "zeitpunktDesLetztenErregernachweisesValue",
            "transmissionswegValue",
            "ubertragungswegValue",
            "erregerdetails",
            "smicsErgebniskategorieDefiningCode",
            "multispeziesValue",
            "anzahlDerErregernachweise",
            "timeValue",
            "beginnValue",
            "beginnDerUntersuchungValue",
            "endeValue",
            "dauerValue",
            "subject",
            "originValue",
            "erregertypisierung",
            "baumKommentarValue",
            "artDerUbertragungKommentarValue",
            "versionUid",
            "endTimeValue",
            "participations",
            "language",
            "healthCareFacility",
            "statusValue",
            "berichtIdValue",
            "territory",
            "eventsummary",
            "startTimeValue",
            "smicsErgebnis",
            "composer",
            "settingDefiningCode",
            "feederAudit",
            "location",
            "categoryDefiningCode",
            "feederAudit",
            "feederAudit",
            "feederAudit",
            "feederAudit",
            "feederAudit",
            "feederAudit",
            "feederAudit",
            "feederAudit",
            "feederAudit",
            "feederAudit",
            "feederAudit",
            "feederAudit",
            "feederAudit",
            "feederAudit");

    writeFiles(generate);
  }

  @Test
  public void testGenerateD4LQuestionnaire() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.D4L_QUESTIONNAIRE.getStream())
            .getTemplate();
    ClassGenerator cut = new ClassGenerator(new ClassGeneratorConfig());
    ClassGeneratorResult generate = cut.generate(PACKAGE_NAME, new OPTParser(template).parse());

    List<FieldSpec> fieldSpecs =
        generate.getClasses().values().stream()
            .flatMap(Collection::stream)
            .filter(t -> !t.kind.equals(TypeSpec.Kind.ENUM))
            .map(t -> t.fieldSpecs)
            .flatMap(List::stream)
            .collect(Collectors.toList());

    assertThat(fieldSpecs).size().isEqualTo(286);
  }

  @Test
  public void testGeneratePatientenaufenthalt() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.PATIENTEN_AUFENTHALT.getStream())
            .getTemplate();
    ClassGenerator cut = new ClassGenerator(new ClassGeneratorConfig());
    ClassGeneratorResult generate = cut.generate(PACKAGE_NAME, new OPTParser(template).parse());

    List<FieldSpec> fieldSpecs =
        generate.getClasses().values().stream()
            .flatMap(Collection::stream)
            .filter(t -> !t.kind.equals(TypeSpec.Kind.ENUM))
            .map(t -> t.fieldSpecs)
            .flatMap(List::stream)
            .collect(Collectors.toList());

    assertThat(fieldSpecs).size().isEqualTo(47);
    writeFiles(generate);
  }

  @Test
  public void testGenerateStationaererVersorgungsfall() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.EPISODE_SUMMARY.getStream())
            .getTemplate();
    ClassGenerator cut = new ClassGenerator(new ClassGeneratorConfig());
    ClassGeneratorResult generate = cut.generate(PACKAGE_NAME, new OPTParser(template).parse());

    List<FieldSpec> fieldSpecs =
        generate.getClasses().values().stream()
            .flatMap(Collection::stream)
            .filter(t -> !t.kind.equals(TypeSpec.Kind.ENUM))
            .map(t -> t.fieldSpecs)
            .flatMap(List::stream)
            .collect(Collectors.toList());

    assertThat(fieldSpecs).size().isEqualTo(35);
    writeFiles(generate);
  }

  @Test
  public void testGenerateBefundDerBlutgasanalyse() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(
                OperationalTemplateTestData.BEFUND_DER_BLUTGASANALYSE.getStream())
            .getTemplate();
    ClassGenerator cut = new ClassGenerator(new ClassGeneratorConfig());
    ClassGeneratorResult generate = cut.generate(PACKAGE_NAME, new OPTParser(template).parse());
    List<String> fieldSpecs =
        generate.getClasses().values().stream()
            .flatMap(Collection::stream)
            .filter(t -> !t.kind.equals(TypeSpec.Kind.ENUM))
            .map(t -> t.fieldSpecs)
            .flatMap(List::stream)
            .map(f -> f.name)
            .collect(Collectors.toList());

    assertThat(fieldSpecs)
        .containsExactlyInAnyOrder(
            "versionUid",
            "endTimeValue",
            "participations",
            "language",
            "healthCareFacility",
            "statusDefiningCode",
            "kategorieValue",
            "territory",
            "startTimeValue",
            "composer",
            "settingDefiningCode",
            "feederAudit",
            "location",
            "categoryDefiningCode",
            "erweiterung",
            "laborergebnis",
            "ergebnisStatusValue",
            "untersuchterAnalytDefiningCode",
            "analytResultatMagnitude",
            "analytResultatUnits",
            "analyseergebnisDetails",
            "ergebnisStatusValue",
            "untersuchterAnalytDefiningCode",
            "analytResultatMagnitude",
            "analytResultatUnits",
            "analyseergebnisDetails",
            "ergebnisStatusValue",
            "untersuchterAnalytDefiningCode",
            "analytResultatMagnitude",
            "analytResultatUnits",
            "analyseergebnisDetails",
            "ergebnisStatusValue",
            "untersuchterAnalytDefiningCode",
            "analytResultatMagnitude",
            "analytResultatUnits",
            "analyseergebnisDetails",
            "testDetails",
            "phWert",
            "language",
            "sauerstoffpartialdruck",
            "multimediaDarstellung",
            "labortestBezeichnungDefiningCode",
            "strukturierteErfassungDerStorfaktoren",
            "strukturierteTestdiagnostik",
            "kohlendioxidpartialdruck",
            "sauerstoffsattigung",
            "erweiterung",
            "timeValue",
            "probendetail",
            "subject",
            "originValue",
            "laborWelchesDenUntersuchungsauftragAnnimmt",
            "feederAudit",
            "feederAudit",
            "feederAudit",
            "feederAudit",
            "feederAudit");

    writeFiles(generate);
  }

  @Test
  public void testGenerateMultiOccurrence() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.MULTI_OCCURRENCE.getStream())
            .getTemplate();
    ClassGenerator cut = new ClassGenerator(new ClassGeneratorConfig());
    ClassGeneratorResult generate = cut.generate(PACKAGE_NAME, new OPTParser(template).parse());

    List<FieldSpec> fieldSpecs =
        generate.getClasses().values().stream()
            .flatMap(Collection::stream)
            .filter(t -> !t.kind.equals(TypeSpec.Kind.ENUM))
            .map(t -> t.fieldSpecs)
            .flatMap(List::stream)
            .collect(Collectors.toList());

    assertThat(fieldSpecs)
        .extracting(f -> f.name, f -> f.type.toString())
        .containsExactlyInAnyOrder(
            new Tuple("versionUid", "org.ehrbase.client.openehrclient.VersionUid"),
            new Tuple("extension", "java.util.List<com.nedap.archie.rm.datastructures.Cluster>"),
            new Tuple("language", "org.ehrbase.client.classgenerator.shareddefinition.Language"),
            new Tuple(
                "settingDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.Setting"),
            new Tuple(
                "structuredMeasurementLocation",
                "java.util.List<com.nedap.archie.rm.datastructures.Cluster>"),
            new Tuple("originValue", "java.time.temporal.TemporalAccessor"),
            new Tuple("device", "com.nedap.archie.rm.datastructures.Cluster"),
            new Tuple("subject", "com.nedap.archie.rm.generic.PartyProxy"),
            new Tuple(
                "locationOfMeasurementDefiningCode",
                "org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.LocationOfMeasurementDefiningCode"),
            new Tuple("locationOfMeasurementValue", "java.lang.String"),
            new Tuple(
                "bodyExposureDefiningCode",
                "org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.BodyExposureDefiningCode"),
            new Tuple("bodyExposureValue", "java.lang.String"),
            new Tuple("temperatureMagnitude", "java.lang.Double"),
            new Tuple("temperatureUnits", "java.lang.String"),
            new Tuple("descriptionOfThermalStressValue", "java.lang.String"),
            new Tuple("exertion", "com.nedap.archie.rm.datastructures.Cluster"),
            new Tuple("timeValue", "java.time.temporal.TemporalAccessor"),
            new Tuple("endTimeValue", "java.time.temporal.TemporalAccessor"),
            new Tuple("language", "org.ehrbase.client.classgenerator.shareddefinition.Language"),
            new Tuple("healthCareFacility", "com.nedap.archie.rm.generic.PartyIdentified"),
            new Tuple("composer", "com.nedap.archie.rm.generic.PartyProxy"),
            new Tuple("territory", "org.ehrbase.client.classgenerator.shareddefinition.Territory"),
            new Tuple(
                "bodyTemperature",
                "java.util.List<org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.BodyTemperatureObservation>"),
            new Tuple("location", "java.lang.String"),
            new Tuple("startTimeValue", "java.time.temporal.TemporalAccessor"),
            new Tuple("currentDayOfMenstrualCycleMagnitude", "java.lang.Long"),
            new Tuple(
                "environmentalConditions",
                "java.util.List<com.nedap.archie.rm.datastructures.Cluster>"),
            new Tuple(
                "bodyExposure",
                "org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.BodyTemperatureBodyExposureChoice"),
            new Tuple(
                "bodyExposure",
                "org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.BodyTemperatureBodyExposureChoice"),
            new Tuple(
                "locationOfMeasurement",
                "org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.BodyTemperatureLocationOfMeasurementChoice"),
            new Tuple(
                "categoryDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.Category"),
            new Tuple("extension", "java.util.List<com.nedap.archie.rm.datastructures.Cluster>"),
            new Tuple("timeValue", "java.time.temporal.TemporalAccessor"),
            new Tuple("temperatureMagnitude", "java.lang.Double"),
            new Tuple("temperatureUnits", "java.lang.String"),
            new Tuple("descriptionOfThermalStressValue", "java.lang.String"),
            new Tuple("exertion", "com.nedap.archie.rm.datastructures.Cluster"),
            new Tuple("currentDayOfMenstrualCycleMagnitude", "java.lang.Long"),
            new Tuple(
                "environmentalConditions",
                "java.util.List<com.nedap.archie.rm.datastructures.Cluster>"),
            new Tuple("widthValue", "java.time.temporal.TemporalAmount"),
            new Tuple(
                "mathFunctionDefiningCode",
                "org.ehrbase.client.classgenerator.shareddefinition.MathFunction"),
            new Tuple(
                "anyEvent",
                "java.util.List<org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition.BodyTemperatureAnyEventChoice>"),
            new Tuple(
                "participations", "java.util.List<com.nedap.archie.rm.generic.Participation>"),
            new Tuple("feederAudit", "com.nedap.archie.rm.archetyped.FeederAudit"),
            new Tuple("feederAudit", "com.nedap.archie.rm.archetyped.FeederAudit"),
            new Tuple("feederAudit", "com.nedap.archie.rm.archetyped.FeederAudit"),
            new Tuple("feederAudit", "com.nedap.archie.rm.archetyped.FeederAudit"),
            new Tuple("sampleCount", "java.lang.Long"));

    writeFiles(generate);
  }

  @Test
  public void testGenerateAllTypes() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.ALL_TYPES.getStream())
            .getTemplate();
    ClassGenerator cut = new ClassGenerator(new ClassGeneratorConfig());
    ClassGeneratorResult generate = cut.generate(PACKAGE_NAME, new OPTParser(template).parse());

    List<FieldSpec> fieldSpecs =
        generate.getClasses().values().stream()
            .flatMap(Collection::stream)
            .filter(t -> !t.kind.equals(TypeSpec.Kind.ENUM))
            .map(t -> t.fieldSpecs)
            .flatMap(List::stream)
            .collect(Collectors.toList());

    assertThat(fieldSpecs).size().isEqualTo(87L);

    writeFiles(generate);
  }

  @Test
  public void testGenerateVirologyFinding() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.VIROLOGY_FINDING.getStream())
            .getTemplate();
    ClassGenerator cut = new ClassGenerator(new ClassGeneratorConfig());
    ClassGeneratorResult generate = cut.generate(PACKAGE_NAME, new OPTParser(template).parse());

    List<String> fieldSpecs =
        generate.getClasses().values().stream()
            .flatMap(Collection::stream)
            .filter(t -> !t.kind.equals(TypeSpec.Kind.ENUM))
            .map(t -> t.fieldSpecs)
            .flatMap(List::stream)
            .map(f -> f.name)
            .collect(Collectors.toList());

    assertThat(fieldSpecs)
        .containsExactlyInAnyOrder(
            "alternativeStruktur",
            "nameDerKorperstelleValue",
            "multimedialeDarstellung",
            "kommentarDesProbennehmersValue",
            "angabenZumProbennehmer",
            "angabenZumTransport",
            "externerIdentifikator",
            "probenentnahmestelleValue",
            "zusatzlicheAngabenZurProbennahme",
            "behalterDetails",
            "digitaleDarstellung",
            "identifikatorDerUbergeordnetenProbe",
            "zeitpunktDesProbeneingangsValue",
            "kommentarValue",
            "probenartValue",
            "anatomischeLokalisation",
            "laborprobenidentifikator",
            "angabenZurVerarbeitung",
            "identifikatorDesProbennehmers",
            "physischeEigenschaften",
            "zeitpunktDerProbenentnahmeValue",
            "details",
            "standorttypValue",
            "standortbeschreibungValue",
            "standortschlusselValue",
            "nachweisValue",
            "viruslastCtWertMagnitude",
            "viruslastCtWertUnits",
            "virusValue",
            "kommentarValue",
            "zugehorigeLaborprobe",
            "analyseergebnisReihenfolgeMagnitude",
            "analyseergebnisDetails",
            "proVirus",
            "details",
            "standorttypValue",
            "standortbeschreibungValue",
            "standortschlusselValue",
            "value",
            "testDetails",
            "probe",
            "language",
            "einsenderstandort",
            "kultur",
            "multimediaDarstellung",
            "auftragsIdDesAnforderndenEinsendendenSystems",
            "auftragsIdEmpfanger",
            "labortestBezeichnungValue",
            "strukturierteErfassungDerStorfaktoren",
            "strukturierteTestdiagnostik",
            "empfangerstandort",
            "erweiterung",
            "timeValue",
            "anforderung",
            "subject",
            "originValue",
            "verteilerliste",
            "fallKennungValue",
            "versionUid",
            "endTimeValue",
            "befund",
            "participations",
            "language",
            "healthCareFacility",
            "statusValue",
            "berichtIdValue",
            "territory",
            "startTimeValue",
            "fallidentifikation",
            "composer",
            "settingDefiningCode",
            "feederAudit",
            "location",
            "categoryDefiningCode",
            "feederAudit",
            "feederAudit",
            "feederAudit",
            "feederAudit",
            "feederAudit",
            "feederAudit",
            "feederAudit",
            "feederAudit",
            "feederAudit");

    writeFiles(generate);
  }

  @Test
  public void testGenerateCorona() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.CORONA_ANAMNESE.getStream())
            .getTemplate();
    ClassGenerator cut = new ClassGenerator(new ClassGeneratorConfig());
    ClassGeneratorResult generate = cut.generate(PACKAGE_NAME, new OPTParser(template).parse());

    List<FieldSpec> fieldSpecs =
        generate.getClasses().values().stream()
            .flatMap(Collection::stream)
            .filter(t -> !t.kind.equals(TypeSpec.Kind.ENUM))
            .map(t -> t.fieldSpecs)
            .flatMap(List::stream)
            .collect(Collectors.toList());

    assertThat(fieldSpecs).size().isEqualTo(347);

    writeFiles(generate);
  }

  @Test
  public void testGenerateCoronaOptimizerSettingSection() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.CORONA_ANAMNESE.getStream())
            .getTemplate();
    ClassGeneratorConfig config = new ClassGeneratorConfig();
    config.setOptimizerSetting(OptimizerSetting.SECTION);
    Map<Character, String> characterStringMap =
        Map.of(
            'ä', "ae",
            'Ä', "Ae",
            'ö', "oe",
            'Ö', "Oe",
            'ü', "ue",
            'Ü', "ue");
    config.getReplaceChars().putAll(characterStringMap);
    ClassGenerator cut = new ClassGenerator(config);
    ClassGeneratorResult generate =
        cut.generate(
            PACKAGE_NAME.replace("example", "exampleoptimizersettingsection"),
            new OPTParser(template).parse());

    List<String> fieldSpecs =
        generate.getClasses().values().stream()
            .flatMap(Collection::stream)
            .filter(t -> !t.kind.equals(TypeSpec.Kind.ENUM))
            .map(t -> t.fieldSpecs)
            .flatMap(List::stream)
            .map(f -> f.name)
            .collect(Collectors.toList());

    assertThat(fieldSpecs).contains("beschaeftigung");

    assertThat(fieldSpecs).size().isEqualTo(339);

    writeFiles(generate);
  }

  @Test
  public void testGenerateCoronaOptimizerSettingAll() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.CORONA_ANAMNESE.getStream())
            .getTemplate();
    ClassGeneratorConfig config = new ClassGeneratorConfig();
    config.setOptimizerSetting(OptimizerSetting.ALL);
    ClassGenerator cut = new ClassGenerator(config);
    ClassGeneratorResult generate =
        cut.generate(
            PACKAGE_NAME.replace("example", "exampleoptimizersettingall"),
            new OPTParser(template).parse());

    List<FieldSpec> fieldSpecs =
        generate.getClasses().values().stream()
            .flatMap(Collection::stream)
            .filter(t -> !t.kind.equals(TypeSpec.Kind.ENUM))
            .map(t -> t.fieldSpecs)
            .flatMap(List::stream)
            .collect(Collectors.toList());

    assertThat(fieldSpecs).size().isEqualTo(303);

    writeFiles(generate);
  }

  @Test
  public void testGenerateSchwangerschaftsstatus() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(
                OperationalTemplateTestData.SCHWANGERSCHAFTSSTATUS.getStream())
            .getTemplate();
    ClassGenerator cut = new ClassGenerator(new ClassGeneratorConfig());
    ClassGeneratorResult generate = cut.generate(PACKAGE_NAME, new OPTParser(template).parse());

    List<String> fieldSpecs =
        generate.getClasses().values().stream()
            .flatMap(Collection::stream)
            .filter(t -> !t.kind.equals(TypeSpec.Kind.ENUM))
            .map(t -> t.fieldSpecs)
            .flatMap(List::stream)
            .map(f -> f.name)
            .collect(Collectors.toList());

    assertThat(fieldSpecs)
        .containsExactlyInAnyOrder(
            "versionUid",
            "endTimeValue",
            "participations",
            "language",
            "healthCareFacility",
            "statusDefiningCode",
            "kategorieValue",
            "territory",
            "startTimeValue",
            "composer",
            "settingDefiningCode",
            "schwangerschaftsstatus",
            "feederAudit",
            "location",
            "categoryDefiningCode",
            "erweiterung",
            "timeValue",
            "subject",
            "originValue",
            "language",
            "erweiterungen",
            "statusDefiningCode",
            "feederAudit");

    Optional<TypeSpec> status1 =
        generate.getClasses().values().stream()
            .flatMap(Collection::stream)
            .filter(s -> s.name.equals("StatusDefiningCode"))
            .findAny();
    assertThat(status1).isPresent();
    assertThat(status1.get().enumConstants.keySet())
        .containsExactlyInAnyOrder("VORLAUFIG", "FINAL", "REGISTRIERT", "GEANDERT");

    Optional<TypeSpec> status2 =
        generate.getClasses().values().stream()
            .flatMap(Collection::stream)
            .filter(s -> s.name.equals("StatusDefiningCode2"))
            .findAny();
    assertThat(status2).isPresent();
    assertThat(status2.get().enumConstants.keySet())
        .containsExactlyInAnyOrder("SCHWANGER", "NICHT_SCHWANGER", "UNBEKANNT");

    writeFiles(generate);
  }

  @Test
  public void testGenerateReactCare() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.OPEN_E_REACT_CARE.getStream())
            .getTemplate();
    ClassGenerator cut = new ClassGenerator(new ClassGeneratorConfig());
    ClassGeneratorResult generate = cut.generate(PACKAGE_NAME, new OPTParser(template).parse());

    List<FieldSpec> fieldSpecs =
        generate.getClasses().values().stream()
            .flatMap(Collection::stream)
            .filter(t -> !t.kind.equals(TypeSpec.Kind.ENUM))
            .map(t -> t.fieldSpecs)
            .flatMap(List::stream)
            .collect(Collectors.toList());

    assertThat(fieldSpecs).size().isEqualTo(407);

    writeFiles(generate);
  }
}
