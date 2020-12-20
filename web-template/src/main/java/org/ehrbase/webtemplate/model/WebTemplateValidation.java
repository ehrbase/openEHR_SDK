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

package org.ehrbase.webtemplate.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WebTemplateValidation implements Serializable {

  public WebTemplateValidation() {}

  public WebTemplateValidation(WebTemplateValidation other) {
    this.other = other.other;
  }

  @JsonAnySetter private Map<String, Object> other = new HashMap<>();

  public Map<String, Object> getOther() {
    return other;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WebTemplateValidation that = (WebTemplateValidation) o;
    return Objects.equals(other, that.other);
  }

  @Override
  public int hashCode() {
    return Objects.hash(other);
  }
}
