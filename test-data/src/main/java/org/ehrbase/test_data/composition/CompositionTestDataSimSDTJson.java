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

public enum CompositionTestDataSimSDTJson {
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
  EHRBASE_CONFORMANCE_DATA_TYPES_DV_TEXT(
      "ehrbase_conformance_data_types_dv_text",
      "ehrbase_conformance_data_types_dv_text.json",
      OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_DATA_TYPES_DV_CODED_TEXT_AS_DV_TEXT(
      "ehrbase_conformance_data_types_dv_coded_text_as_dv_text",
      "ehrbase_conformance_data_types_dv_coded_text_as_dv_text.json",
      OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_DATA_TYPES_DV_CODED_TEXT(
      "ehrbase_conformance_data_types_dv_coded_text",
      "ehrbase_conformance_data_types_dv_coded_text.json",
      OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_DATA_TYPES_DV_QUANTITY(
      "ehrbase_conformance_data_types_dv_quantity",
              "ehrbase_conformance_data_types_dv_quantity.json",
      OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_DATA_TYPES_DV_PROPORTION(
          "ehrbase_conformance_data_types_dv_proportion",
          "ehrbase_conformance_data_types_dv_proportion.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_DATA_TYPES_DV_COUNT(
          "ehrbase_conformance_data_types_dv_count",
          "ehrbase_conformance_data_types_dv_count.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_DATA_TYPES_DV_DATE_TIME(
          "ehrbase_conformance_data_types_dv_date_time",
          "ehrbase_conformance_data_types_dv_date_time.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_DATA_TYPES_DV_TIME(
          "ehrbase_conformance_data_types_dv_time",
          "ehrbase_conformance_data_types_dv_time.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_DATA_TYPES_DV_DATE(
          "ehrbase_conformance_data_types_dv_date",
          "ehrbase_conformance_data_types_dv_date.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_DATA_TYPES_DV_DURATION(
          "ehrbase_conformance_data_types_dv_duration",
          "ehrbase_conformance_data_types_dv_duration.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_DATA_TYPES_DV_ORDINAL(
          "ehrbase_conformance_data_types_dv_ordinal",
          "ehrbase_conformance_data_types_dv_ordinal.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_DATA_TYPES_DV_BOOLEAN(
          "ehrbase_conformance_data_types_dv_boolean",
          "ehrbase_conformance_data_types_dv_boolean.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_DATA_TYPES_INTERVAL_DV_QUANTITY(
          "ehrbase_conformance_data_types_interval_dv_quantity",
          "ehrbase_conformance_data_types_interval_dv_quantity.json",
          OperationalTemplateTestData.CONFORMANCE),
  NESTED("nested.en.v1", "nested.en.v1.json",OperationalTemplateTestData.NESTED);

  private final String filename;
  private final String description;

  private final OperationalTemplateTestData template;

  CompositionTestDataSimSDTJson(
      String description, String filename, OperationalTemplateTestData template) {
    this.filename = filename;
    this.description = description;
    this.template = template;
  }

  public InputStream getStream() {
    return getClass().getResourceAsStream("/composition/flat/simSDT/" + filename);
  }

  public OperationalTemplateTestData getTemplate() {
    return template;
  }

  @Override
  public String toString() {
    return this.description;
  }
}
