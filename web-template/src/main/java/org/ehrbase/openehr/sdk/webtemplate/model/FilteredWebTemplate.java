/*
 * Copyright (c) 2020 vitasystems GmbH.
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Deque;
import java.util.Map;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FilteredWebTemplate extends WebTemplate {

    public FilteredWebTemplate(WebTemplate other) {
        super(other);
    }

    @JsonIgnore
    public Map<Pair<String, String>, Deque<WebTemplateNode>> filteredNodeMap;

    public void setFilteredNodeMap(Map<Pair<String, String>, Deque<WebTemplateNode>> filteredNodeMap) {
        this.filteredNodeMap = filteredNodeMap;
    }

    public Deque<WebTemplateNode> findFiltersNodes(WebTemplateNode node) {

        return filteredNodeMap.get(new ImmutablePair<>(node.getAqlPath(), node.getRmType()));
    }
}
