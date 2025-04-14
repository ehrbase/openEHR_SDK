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
public class WebTemplateValidation implements Serializable {

    private WebTemplateInterval precision;
    private WebTemplateInterval range;
    private String pattern;

    public WebTemplateValidation() {}

    public WebTemplateValidation(WebTemplateValidation other) {
        if (other.precision != null) {
            this.precision = new WebTemplateInterval(other.precision);
        }

        if (other.range != null) {
            this.range = new WebTemplateInterval(other.range);
        }

        this.pattern = other.pattern;
    }

    public WebTemplateInterval getPrecision() {
        return precision;
    }

    public void setPrecision(WebTemplateInterval precision) {
        this.precision = precision;
    }

    public WebTemplateInterval getRange() {
        return range;
    }

    public void setRange(WebTemplateInterval range) {
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
