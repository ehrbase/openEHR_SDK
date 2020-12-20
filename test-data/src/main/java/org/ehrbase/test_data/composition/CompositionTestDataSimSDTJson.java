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
  CORONA("Corona", "corona.json"),
  MULTI_OCCURRENCE("multi_occurrence", "multi_occurrence.json"),
  ALL_TYPES("test_all_types", "test_all_types.json");

  private final String filename;
  private final String description;

  CompositionTestDataSimSDTJson(String description, String filename) {
    this.filename = filename;
    this.description = description;
  }

  public InputStream getStream() {
    return getClass().getResourceAsStream("/composition/flat/simSDT/" + filename);
  }

  public String toString() {
    return this.description;
  }
}
