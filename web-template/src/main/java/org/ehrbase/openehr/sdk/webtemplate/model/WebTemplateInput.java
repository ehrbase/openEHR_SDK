/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.webtemplate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.ehrbase.openehr.sdk.webtemplate.util.WebTemplateUtils;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WebTemplateInput implements Serializable {

    private String suffix;
    private String type;
    private final List<WebTemplateInputValue> list;
    private Boolean listOpen;
    private WebTemplateValidation validation;
    private String terminology;

    @JsonSerialize(using = StringToNumberSerializer.class)
    private String defaultValue;

    public WebTemplateInput() {
        list = new ArrayList<>();
    }

    public WebTemplateInput(WebTemplateInput other) {
        this.suffix = other.suffix;
        this.type = other.type;
        this.listOpen = other.listOpen;
        if (other.validation != null) {
            this.validation = new WebTemplateValidation(other.validation);
        } else {
            this.validation = null;
        }
        this.terminology = other.terminology;
        this.defaultValue = other.defaultValue;
        this.list = WebTemplateUtils.cloneList(other.list, WebTemplateInputValue::new);
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<WebTemplateInputValue> getList() {
        return list;
    }

    public Boolean getListOpen() {
        return listOpen;
    }

    public void setListOpen(Boolean listOpen) {
        this.listOpen = listOpen;
    }

    public WebTemplateValidation getValidation() {
        return validation;
    }

    public void setValidation(WebTemplateValidation validation) {
        this.validation = validation;
    }

    public String getTerminology() {
        return terminology;
    }

    public void setTerminology(String terminology) {
        this.terminology = terminology;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebTemplateInput input = (WebTemplateInput) o;
        return Objects.equals(suffix, input.suffix)
                && Objects.equals(type, input.type)
                && Objects.equals(list, input.list)
                && Objects.equals(listOpen, input.listOpen)
                && Objects.equals(validation, input.validation)
                && Objects.equals(terminology, input.terminology)
                && Objects.equals(defaultValue, input.defaultValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suffix, type, list, listOpen, validation, terminology, defaultValue);
    }
}
