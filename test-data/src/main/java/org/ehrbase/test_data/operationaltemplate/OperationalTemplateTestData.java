/*
 * Copyright (c) 2019 Stefan Spiska (Vitasystems GmbH) and Hannover Medical School.
 *
 * This file is part of project EHRbase
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehrbase.test_data.operationaltemplate;

import java.io.InputStream;
import java.util.Arrays;

public enum OperationalTemplateTestData {
  IDCR_IMMUNISATION_SUMMARY(
      "IDCR Immunisation summary",
      "IDCR - Immunisation summary.v0.opt",
      "IDCR - Immunisation summary.v0"),
  RIPPLE_CONFORMANCE_TEST(
      "RIPPLE Conformance Test",
      "RIPPLE-Conformance Test.opt",
      "RIPPLE - Conformance Test template"),
  IDCR_LABORATORY_TEST(
      "IDCR  Laboratory Test Report",
      "IDCR - Laboratory Test Report.v0.opt",
      "IDCR - Laboratory Test Report.v0"),
  EHRN_VITAL_SIGNS_TEST("EHRN Vital signs.v2", "EHRN Vital signs.v2.opt", "EHRN Vital signs.v2"),
  IDCR_PROBLEM_LIST(
      "IDCR  Problem List.v1", "IDCR - Problem List.v1.opt", "IDCR - Problem List.v1"),
  IDCR_ADVERSE_REACTION_LIST(
      "IDCR -  Adverse Reaction List.v1",
      "IDCR - Adverse Reaction List.v1.opt",
      "Adverse Reaction List.v1"),
  BLOOD_PRESSURE_SIMPLE(
      "Very simple blood_pressure template",
      "ehrbase_blood_pressure_simple.de.v0.opt",
      "ehrbase_blood_pressure_simple.de.v0"),
  MULTI_OCCURRENCE(
      "Template with multiple occurrence",
      "ehrbase_multi_occurrence.de.opt",
      "ehrbase_multi_occurrence.de.v1"),
  EREACT_COVID_MANAGEMENT(
      "Template with action", "EREACT - COVID management.v0.opt", "EREACT - COVID management.v0"),
  ALL_TYPES("Template with all types", "Test_all_types.opt", "test_all_types.en.v1"),
  ALT_EVENTS("Template with alternative Events", "AlternativeEvents.opt", "AlternativeEvents"),
  TWO_EVENTS("Template with two Events", "TwoEvents.opt", "SingleEvent"),
  WORD_WITH_AND(
      "Template with archetype with and in name ",
      "wordwithandin_test.v0.opt",
      "wordwithandin_test.v0"),
  AVERAGE_24(
      "Template with  a Interval Events", "24StundenDurchschnitt.opt", "24StundenDurchschnitt"),
  EPISODE_OF_CARE("EpisodeOfCare", "EpisodeOfCare.opt", "EpisodeOfCare"),
  CORONA_ANAMNESE("Corona_Anamnese", "corona_anamnese.opt", "Corona_Anamnese"),
  DIAGNOSE("Diagnose", "Diagnose.opt", "Diagnose"),
  OPEN_E_REACT_CARE("open_eREACT-Care", "open_eREACT-Care.opt", "open_eREACT-Care"),
  SCHWANGERSCHAFTSSTATUS(
      "Schwangerschaftsstatus", "Schwangerschaftsstatus.opt", "Schwangerschaftsstatus"),
  VIROLOGY_FINDING("Virologischer Befund", "Virologischer_Befund.opt", "Virologischer Befund"),
  BEFUND_DER_BLUTGASANALYSE(
      "Befund der Blutgasanalyse", "Befund_der_Blutgasanalyse.opt", "Befund der Blutgasanalyse"),
  SM_I_C_S_BEFUND("SmICS-Befund", "SmICS-Befund.opt", "SmICS-Befund"),
  MULTI_LIST("template with ITEM_TREE", "Multi_list.opt", "Multi_list"),
  PATIENTEN_AUFENTHALT(
      "NUM test hospitalization", "Patientenaufenthalt.opt", "Patientenaufenthalt"),
  EPISODE_SUMMARY(
      "Stationärer Versorgungsfall",
      "stationärer_versorgungsfall.opt",
      "Stationärer Versorgungsfall"),
  INITIAL_ASSESSMENT(
      "example.initialassesment.v0",
      "example_initialassesment.v0.opt",
      "example.initialassesment.v0"),
  TESTING_TEMPLATE_N("Template with fixed values", "Testing_Template_N.opt", "Testing Template N"),
  D4L_QUESTIONNAIRE("D4L_questionnaire", "D4L_questionnaire.opt", "D4L_questionnaire"),
  CONSTRAIN_TEST(
      "example with many constrains / default values", "constrain_test.opt", "constrain_test"),
  LANGUAGE_TEST("example with multiple languages", "language_test.opt", "language_test"),
  SINGLE_EVENT("example with a single valued EVENT", "single_event.opt", "Körpergröße"),
  ADDICTION("example ", "additciton_alcohol.opt", "AddictionAlcoholTemplate"),
  MINIMAL_EVALUATION("minimal evaluation", "minimal_evaluation.opt", "minimal_evaluation.en.v1"),
  MINIMAL_INSTRUCTION(
      "minimal instruction", "minimal_instruction.opt", "minimal_instruction.en.v1"),
  GECCO_SEROLOGISCHER_BEFUND(
      "template with any Element", "GECCO_Serologischer Befund.opt", "GECCO_Serologischer Befund"),
  DETERIORIATION_ASSESSMENT(
      "Deterioriation assessment ",
      "EREACT - Deterioriation assessment.v0.opt",
      "EREACT - Deterioriation assessment.v0"),
  VIROLOGISCHER_BEFUND("Virologischer Befund", "Virologischer_Befund.opt", "Virologischer Befund"),
  LABOR_BEFUND("Labor Befund", "laborbefund.opt", "Laborbefund"),
  GECCO_DIAGNOSE("example with snomed terminologies", "GECCO_Diagnose.opt", "GECCO_Diagnose"),
  MINIMAL_ACTION_2("Minimal Action 2", "minimal_action2.opt", "Minimal action 2"),
  MINIMAL_ACTION("Minimal Action 3", "minimal_action3.opt", "minimal_action_3.en.v1"),
  NCD("ncd", "NCD.opt", "NCD"),
  CONFORMANCE(
      "ehrbase conformance test template",
      "conformance_ehrbase.de.v0.opt",
      "conformance-ehrbase.de.v0"),
  MULTIMEDIA_TEST("MultimediaTest", "multimedia_test.en.v1.opt", "multimedia_test.en.v1");

  private final String filename;
  private final String templateId;
  private final String description;

  OperationalTemplateTestData(String description, String filename, String templateId) {
    this.filename = filename;
    this.description = description;
    this.templateId = templateId;
  }

  public InputStream getStream() {
    return getClass().getResourceAsStream("/operationaltemplate/" + filename);
  }

  public static OperationalTemplateTestData findByTemplateId(String templateId) {
    return Arrays.stream(OperationalTemplateTestData.values())
        .filter(o -> o.getTemplateId().equals(templateId))
        .findAny()
        .orElse(null);
  }

  public String getTemplateId() {
    return templateId;
  }
}
