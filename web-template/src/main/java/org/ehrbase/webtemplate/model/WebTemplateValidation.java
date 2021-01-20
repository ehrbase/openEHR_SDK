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

import java.io.Serializable;
import java.util.Objects;

public class WebTemplateValidation implements Serializable {

  private WebTemplateValidationInterval precision;
  private WebTemplateValidationInterval range;
  private String pattern;

  public WebTemplateValidation() {}

  public WebTemplateValidation(WebTemplateValidation other) {
    if (other.precision != null) {
      this.precision = new WebTemplateValidationInterval(other.precision);
    }

    if (other.range != null) {
      this.range = new WebTemplateValidationInterval(other.range);
    }

    this.pattern = other.pattern;
  }

  public WebTemplateValidationInterval getPrecision() {
    return precision;
  }

  public void setPrecision(WebTemplateValidationInterval precision) {
    this.precision = precision;
  }

  public WebTemplateValidationInterval getRange() {
    return range;
  }

  public void setRange(WebTemplateValidationInterval range) {
    this.range = range;
  }

  public String getPattern() {
    return pattern;
  }

  public void setPattern(String pattern) {
    this.pattern = pattern;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WebTemplateValidation that = (WebTemplateValidation) o;
    return Objects.equals(precision, that.precision)
        && Objects.equals(range, that.range)
        && Objects.equals(pattern, that.pattern);
  }

  @Override
  public int hashCode() {
    return Objects.hash(precision, range, pattern);
  }

  @Override
  public String toString() {
    return "WebTemplateValidation{"
        + "precision="
        + precision
        + ", range="
        + range
        + ", pattern='"
        + pattern
        + '\''
        + '}';
  }
}
