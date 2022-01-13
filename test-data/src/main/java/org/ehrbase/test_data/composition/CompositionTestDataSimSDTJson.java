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

import java.io.InputStream;

public enum CompositionTestDataSimSDTJson {
  ALTERNATIVE_EVENTS("AlternativeEvents", "AlternativeEvents.json"),
  ALTERNATIVE_EVENTS_2("AlternativeEvents", "AlternativeEvents2.json"),
  VITALSIGNS("Vitalsigns", "Vitalsigns.json"),
  ADVERSE_REACTION_LIST("AlternativeEvents", "IDCR - Adverse Reaction List.json"),
  CORONA("Corona", "corona.json"),
  CORONA_WITH_OTHER_PARTICIPATION("Corona", "corona_with_other_participation.json"),
  CORONA_WITH_CONTEXT("Corona", "corona_with_context.json"),
  CORONA_WITH_FEEDER_AUDIT("Corona with feeder audit", "corona_with_feeder_audit.json"),
  CORONA_WITH_FEEDER_AUDIT_RAW(
      "Corona with feeder audit in Raw", "corona_with_feeder_audit_raw.json"),
  CORONA_WITH_RAW("Corona with raw field", "corona_with_raw.json"),
  MULTI_OCCURRENCE("multi_occurrence", "multi_occurrence.json"),
  MISSING_COUNT("flat_with_missing_count.json", "flat_with_missing_count.json"),
  ALL_TYPES("test_all_types", "test_all_types.json"),
  WORD_WITH_AND("Template with archetype with and in name ", "wordwithandin_test.json"),
  DETERIORIATION_ASSESSMENT(
      "EREACT - Deterioration_assessment", "EREACT - Deterioration_assessment.json"),
  MULTI_LIST("MULTI_LIST", "multi_list.json"),
  NCD("NCD", "NCD.json"),
  EREACT_COVID_MANAGEMENT("flat with action", "EREACT - Covid status monitoring - FLAT.json"),
  NESTED("nested.en.v1", "nested.en.v1.json");

  private final String filename;
  private final String description;

  CompositionTestDataSimSDTJson(String description, String filename) {
    this.filename = filename;
    this.description = description;
  }

  public InputStream getStream() {
    return getClass().getResourceAsStream("/composition/flat/simSDT/" + filename);
  }

  @Override
  public String toString() {
    return this.description;
  }
}
