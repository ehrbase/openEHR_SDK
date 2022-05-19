/*
 * Copyright (c) 2021 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.webtemplate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WebtemplateCardinality implements Serializable {

    private Integer min;
    private Integer max;
    private final List<String> ids = new ArrayList<>();

    private Boolean excludeFromWebTemplate;

    public WebtemplateCardinality() {}

    public WebtemplateCardinality(WebtemplateCardinality other) {
        this.min = other.min;
        this.max = other.max;
        this.ids.addAll(other.ids);
        this.excludeFromWebTemplate = other.getExcludeFromWebTemplate();
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public List<String> getIds() {
        return ids;
    }

    public Boolean getExcludeFromWebTemplate() {
        return excludeFromWebTemplate;
    }

    public void setExcludeFromWebTemplate(Boolean excludeFromWebTemplate) {
        this.excludeFromWebTemplate = excludeFromWebTemplate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebtemplateCardinality that = (WebtemplateCardinality) o;
        return Objects.equals(min, that.min)
                && Objects.equals(max, that.max)
                && Objects.equals(ids, that.ids)
                && Objects.equals(excludeFromWebTemplate, that.excludeFromWebTemplate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max, ids, excludeFromWebTemplate);
    }
}
