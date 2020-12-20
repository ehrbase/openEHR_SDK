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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class WebTemplateInputValue implements Serializable {

  private String value;
  private String label;
  private final Map<String, String> localizedLabels = new HashMap<>();
  private final Map<String, String> localizedDescriptions = new HashMap<>();
  private final Map<String, WebTemplateTerminology> termBindings = new HashMap<>();
  private Integer ordinal;
  private final List<String> currentStates = new ArrayList<>();
  private WebTemplateValidation validation;

  public WebTemplateInputValue() {}

  public WebTemplateInputValue(WebTemplateInputValue other) {
    this.value = other.value;
    this.label = other.label;
    this.ordinal = other.ordinal;
    if (other.validation != null) {
      this.validation = new WebTemplateValidation(other.validation);
    } else {
      this.validation = null;
    }
    this.localizedLabels.putAll(other.localizedLabels);
    this.localizedDescriptions.putAll(other.localizedDescriptions);
    this.termBindings.putAll(
        other.termBindings.entrySet().stream()
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey, e -> new WebTemplateTerminology(e.getValue()))));
    this.currentStates.addAll(other.currentStates);
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public Map<String, String> getLocalizedLabels() {
    return localizedLabels;
  }

  public Map<String, String> getLocalizedDescriptions() {
    return localizedDescriptions;
  }

  public Map<String, WebTemplateTerminology> getTermBindings() {
    return termBindings;
  }

  public Integer getOrdinal() {
    return ordinal;
  }

  public void setOrdinal(Integer ordinal) {
    this.ordinal = ordinal;
  }

  public List<String> getCurrentStates() {
    return currentStates;
  }

  public WebTemplateValidation getValidation() {
    return validation;
  }

  public void setValidation(WebTemplateValidation validation) {
    this.validation = validation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WebTemplateInputValue value1 = (WebTemplateInputValue) o;
    return Objects.equals(value, value1.value)
        && Objects.equals(label, value1.label)
        && Objects.equals(localizedLabels, value1.localizedLabels)
        && Objects.equals(localizedDescriptions, value1.localizedDescriptions)
        && Objects.equals(termBindings, value1.termBindings)
        && Objects.equals(ordinal, value1.ordinal)
        && Objects.equals(currentStates, value1.currentStates)
        && Objects.equals(validation, value1.validation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        value,
        label,
        localizedLabels,
        localizedDescriptions,
        termBindings,
        ordinal,
        currentStates,
        validation);
  }
}
