/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.webtemplate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WebTemplateTerminology implements Serializable {

    private String value;
    private String terminologyId;

    public WebTemplateTerminology() {}

    public WebTemplateTerminology(WebTemplateTerminology other) {
        this.value = other.value;
        this.terminologyId = other.terminologyId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTerminologyId() {
        return terminologyId;
    }

    public void setTerminologyId(String terminologyId) {
        this.terminologyId = terminologyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebTemplateTerminology that = (WebTemplateTerminology) o;
        return Objects.equals(value, that.value) && Objects.equals(terminologyId, that.terminologyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, terminologyId);
    }
}
