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

package org.ehrbase.webtemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WebTemplateNode implements Serializable {

    private String id;
    private String name;
    private String localizedName;
    private String rmType;
    private String nodeId;
    private int min;
    private int max;
    private final Map<String, String> localizedNames = new HashMap();
    private final Map<String, String> localizedDescriptions = new HashMap<>();
    private String aqlPath;
    private final List<WebTemplateNode> children = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getRmType() {
        return rmType;
    }

    public void setRmType(String rmType) {
        this.rmType = rmType;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public Map<String, String> getLocalizedNames() {
        return localizedNames;
    }

    public Map<String, String> getLocalizedDescriptions() {
        return localizedDescriptions;
    }

    public String getAqlPath() {
        return aqlPath;
    }

    public void setAqlPath(String aqlPath) {
        this.aqlPath = aqlPath;
    }

    public List<WebTemplateNode> getChildren() {
        return children;
    }


    public List<WebTemplateNode> findMatching(Predicate<WebTemplateNode> filter) {

        List<WebTemplateNode> matching = new ArrayList<>(children.stream().map(c -> c.findMatching(filter)).flatMap(List::stream).collect(Collectors.toList()));

        if (filter.test(this)) {
            matching.add(this);
        }
        return matching;
    }

}
