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
import java.util.List;
import java.util.Objects;

public class WebTemplateInput {

    private String suffix;
    private String type;
    private final List<WebTemplateInputValue> list = new ArrayList();
    private Boolean listOpen;
    private WebTemplateValidation validation;
    private String terminology;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebTemplateInput that = (WebTemplateInput) o;
        return Objects.equals(suffix, that.suffix) &&
                Objects.equals(type, that.type) &&
                list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suffix, type, list);
    }
}
