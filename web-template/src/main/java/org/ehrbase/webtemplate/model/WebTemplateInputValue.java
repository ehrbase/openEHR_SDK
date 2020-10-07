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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WebTemplateInputValue {

    private String value;
    private String label;
    private final Map<String, String> localizedLabels = new HashMap<>();
    private final Map<String, String> localizedDescriptions = new HashMap<>();
    private String terminology;
    private final Map<String, WebTemplateTerminology> termBindings = new HashMap<>();
    private Integer ordinal;
    private final List<String> currentStates = new ArrayList<>();

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

    public String getTerminology() {
        return terminology;
    }

    public void setTerminology(String terminology) {
        this.terminology = terminology;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebTemplateInputValue that = (WebTemplateInputValue) o;
        return Objects.equals(value, that.value) &&
                Objects.equals(label, that.label) &&
                localizedLabels.equals(that.localizedLabels) &&
                Objects.equals(terminology, that.terminology);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, label, localizedLabels, terminology);
    }
}
