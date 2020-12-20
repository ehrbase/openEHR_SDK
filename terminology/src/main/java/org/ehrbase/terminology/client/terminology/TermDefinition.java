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

package org.ehrbase.terminology.client.terminology;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class TermDefinition {
  private final String code;
  private final String value;
  private final String description;
  private final Map<String, String> other;

  public TermDefinition(String code, String value, String description) {
    this.code = code;
    this.value = value;
    this.description = description;
    this.other = Collections.emptyMap();
  }

  public TermDefinition(String code, String value, String description, Map<String, String> other) {
    this.code = code;
    this.value = value;
    this.description = description;
    this.other = other;
  }

  public String getCode() {
    return code;
  }

  public String getValue() {
    return value;
  }

  public String getDescription() {
    return description;
  }

  public Map<String, String> getOther() {
    return other;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TermDefinition that = (TermDefinition) o;
    return Objects.equals(code, that.code)
        && Objects.equals(value, that.value)
        && Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, value, description);
  }
}
