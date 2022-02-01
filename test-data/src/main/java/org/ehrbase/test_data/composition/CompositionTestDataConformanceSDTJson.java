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

public enum CompositionTestDataConformanceSDTJson implements CompositionTestDataSimSDTJsonInterface {

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
  EHRBASE_CONFORMANCE_DATA_TYPES_DV_IDENTIFIER(
          "ehrbase_conformance_data_types_dv_identifier",
          "ehrbase_conformance_data_types_dv_identifier.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_DATA_TYPES_DV_URI(
          "ehrbase_conformance_data_types_dv_uri",
          "ehrbase_conformance_data_types_dv_uri.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_DATA_TYPES_DV_EHR_URI(
          "ehrbase_conformance_data_types_dv_ehr_uri",
          "ehrbase_conformance_data_types_dv_ehr_uri.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_DATA_TYPES_DV_PARSABLE(
          "ehrbase_conformance_data_types_dv_parsable",
          "ehrbase_conformance_data_types_dv_parsable.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_DATA_TYPES_DV_MULTIMEDIA(
          "ehrbase_conformance_data_types_dv_multimedia",
          "ehrbase_conformance_data_types_dv_multimedia.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_DATA_TYPES_INTERVAL_DV_QUANTITY(
          "ehrbase_conformance_data_types_interval_dv_quantity",
          "ehrbase_conformance_data_types_interval_dv_quantity.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_ELEMENT_NULL_FLAVOR(
          "ehrbase_conformance_Element_null_flavor",
          "ehrbase_conformance_Element_null_flavor.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_CLUSTER(
          "ehrbase_conformance_cluster",
          "ehrbase_conformance_cluster.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_POINT_EVENT(
          "ehrbase_conformance_point_event",
          "ehrbase_conformance_point_event.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_INTERVAL_EVENT(
          "ehrbase_conformance_interval_event",
          "ehrbase_conformance_interval_event.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_COMPOSITION(
          "ehrbase_conformance_composition",
          "ehrbase_conformance_composition.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_OBSERVATION(
          "ehrbase_conformance_observation",
          "ehrbase_conformance_observation.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_EVALUATION(
          "ehrbase_conformance_evaluation",
          "ehrbase_conformance_evaluation.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_ACTION(
          "ehrbase_conformance_action",
          "ehrbase_conformance_action.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_INSTRUCTION(
          "ehrbase_conformance_instruction",
          "ehrbase_conformance_instruction.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_ADMIN_ENTRY(
          "ehrbase_conformance_admin_entry",
          "ehrbase_conformance_admin_entry.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_FEEDER_AUDIT_MULTIMEDIA(
          "ehrbase_conformance_feeder_audit_multimedia",
          "ehrbase_conformance_feeder_audit_multimedia.json",
          OperationalTemplateTestData.CONFORMANCE),
  EHRBASE_CONFORMANCE_ELEMENT_FEEDER_AUDIT(
          "ehrbase_conformance_Element_feeder_audit",
          "ehrbase_conformance_Element_feeder_audit.json",
          OperationalTemplateTestData.CONFORMANCE);

  private final String filename;
  private final String description;

  private final OperationalTemplateTestData template;

  CompositionTestDataConformanceSDTJson(
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
