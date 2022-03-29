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

package org.ehrbase.test_data.composition;

import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;

import java.io.InputStream;

public enum CompositionTestDataSimSDTJson implements CompositionTestDataSimSDTJsonInterface {
  ALTERNATIVE_EVENTS(
      "AlternativeEvents", "AlternativeEvents.json", OperationalTemplateTestData.ALT_EVENTS),
  ALTERNATIVE_EVENTS_2(
      "AlternativeEvents", "AlternativeEvents2.json", OperationalTemplateTestData.ALT_EVENTS),
  VITALSIGNS("Vitalsigns", "Vitalsigns.json", OperationalTemplateTestData.EHRN_VITAL_SIGNS_TEST),
  ADVERSE_REACTION_LIST(
      "AlternativeEvents",
      "IDCR - Adverse Reaction List.json",
      OperationalTemplateTestData.IDCR_ADVERSE_REACTION_LIST),
  CORONA("Corona", "corona.json", OperationalTemplateTestData.CORONA_ANAMNESE),
  CORONA_WITH_OTHER_PARTICIPATION(
      "Corona",
      "corona_with_other_participation.json",
      OperationalTemplateTestData.CORONA_ANAMNESE),
  CORONA_WITH_CONTEXT(
      "Corona", "corona_with_context.json", OperationalTemplateTestData.CORONA_ANAMNESE),
  CORONA_WITH_FEEDER_AUDIT(
      "Corona with feeder audit",
      "corona_with_feeder_audit.json",
      OperationalTemplateTestData.CORONA_ANAMNESE),
  CORONA_WITH_FEEDER_AUDIT_RAW(
      "Corona with feeder audit in Raw",
      "corona_with_feeder_audit_raw.json",
      OperationalTemplateTestData.CORONA_ANAMNESE),
  CORONA_WITH_RAW(
      "Corona with raw field", "corona_with_raw.json", OperationalTemplateTestData.CORONA_ANAMNESE),
  MULTI_OCCURRENCE(
      "multi_occurrence", "multi_occurrence.json", OperationalTemplateTestData.MULTI_OCCURRENCE),
  MISSING_COUNT(
      "flat_with_missing_count.json",
      "flat_with_missing_count.json",
      OperationalTemplateTestData.MULTI_OCCURRENCE),
  ALL_TYPES("test_all_types", "test_all_types.json", OperationalTemplateTestData.ALL_TYPES),
  WORD_WITH_AND(
      "Template with archetype with and in name ",
      "wordwithandin_test.json",
      OperationalTemplateTestData.WORD_WITH_AND),
  DETERIORIATION_ASSESSMENT(
      "EREACT - Deterioration_assessment",
      "EREACT - Deterioration_assessment.json",
      OperationalTemplateTestData.DETERIORIATION_ASSESSMENT),
  MULTI_LIST("MULTI_LIST", "multi_list.json", OperationalTemplateTestData.MULTI_LIST),
  NCD("NCD", "NCD.json", OperationalTemplateTestData.NCD),
  EREACT_COVID_MANAGEMENT(
      "flat with action",
      "EREACT - Covid status monitoring - FLAT.json",
      OperationalTemplateTestData.EREACT_COVID_MANAGEMENT),
  NESTED("nested.en.v1", "nested.en.v1.json",OperationalTemplateTestData.NESTED),
  DURATION_VALIDATION("duration_validation", "duration_validation.json",OperationalTemplateTestData.DURATION_VALIDATION),
  RE_SPECT("nested.en.v1", "ReSPECT.json",OperationalTemplateTestData.RE_SPECT),
  IPS("International Patient Summary", "ips_flat.json",OperationalTemplateTestData.IPS);

  private final String filename;
  private final String description;

  private final OperationalTemplateTestData template;

  CompositionTestDataSimSDTJson(
      String description, String filename, OperationalTemplateTestData template) {
    this.filename = filename;
    this.description = description;
    this.template = template;
  }

  @Override
  public InputStream getStream() {
    return getClass().getResourceAsStream("/composition/flat/simSDT/" + filename);
  }

  @Override
  public OperationalTemplateTestData getTemplate() {
    return template;
  }

  @Override
  public String toString() {
    return this.description;
  }
}
