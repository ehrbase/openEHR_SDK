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
package org.ehrbase.webtemplate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.aql.dto.path.AqlPath;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WebTemplateNode implements Serializable {

    private static final ArchieRMInfoLookup RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

    private String id;

    @JsonIgnore
    private Integer optionalIdNumber;

    private String name;
    private String localizedName;
    private String rmType;
    private String nodeId;
    private int min;
    private int max;
    private final Map<String, String> localizedNames;
    private final Map<String, String> localizedDescriptions;

    @JsonIgnore
    private final String[] aqlCache = new String[2];

    private AqlPath aqlPath;

    private final List<WebTemplateNode> children;
    private final List<WebTemplateInput> inputs;
    private Boolean inContext;
    private final Map<String, WebTemplateTerminology> termBindings;
    private final List<String> dependsOn;
    private WebTemplateAnnotation annotations;
    private final List<ProportionType> proportionTypes;
    private final List<WebtemplateCardinality> cardinalities;

    public WebTemplateNode() {
        localizedNames = new LinkedHashMap<>();
        localizedDescriptions = new LinkedHashMap<>();
        children = new ArrayList<>();
        inputs = new ArrayList<>();
        termBindings = new LinkedHashMap<>();
        proportionTypes = new ArrayList<>();
        dependsOn = new ArrayList<>();
        cardinalities = new ArrayList<>();
    }

    public WebTemplateNode(WebTemplateNode other) {
        this.id = other.id;
        this.optionalIdNumber = other.optionalIdNumber;
        this.name = other.name;
        this.localizedName = other.localizedName;
        this.rmType = other.rmType;
        this.nodeId = other.nodeId;
        this.min = other.min;
        this.max = other.max;
        this.aqlPath = other.aqlPath;
        this.inContext = other.inContext;
        this.dependsOn = new ArrayList<>(other.dependsOn);
        if (other.annotations != null) {
            this.annotations = new WebTemplateAnnotation(other.annotations);
        }

        this.cardinalities =
                other.cardinalities.stream().map(WebtemplateCardinality::new).collect(Collectors.toList());
        this.inputs = other.inputs.stream().map(WebTemplateInput::new).collect(Collectors.toList());
        this.children = other.children.stream().map(WebTemplateNode::new).collect(Collectors.toList());
        this.localizedNames = new LinkedHashMap<>(other.localizedNames);
        this.localizedDescriptions = new LinkedHashMap<>(other.localizedDescriptions);
        this.proportionTypes = new ArrayList<>(other.getProportionTypes());
        this.termBindings = other.termBindings.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> new WebTemplateTerminology(e.getValue()),
                        (a, b) -> a,
                        LinkedHashMap::new));
    }

    public String getId() {
        return getId(true);
    }

    public String getId(boolean withOptionalIdNumber) {
        if (withOptionalIdNumber && optionalIdNumber != null) {
            return id + optionalIdNumber;
        } else {
            return id;
        }
    }

    public void setOptionalIdNumber(Integer optionalIdNumber) {
        this.optionalIdNumber = optionalIdNumber;
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
        return aqlPath.format(true);
    }

    @JsonIgnore
    public AqlPath getAqlPathDto() {
        return aqlPath;
    }

    public String getAqlPath(boolean withOtherPredicates) {
        int idx = withOtherPredicates ? 0 : 1;
        if (aqlCache[idx] == null) {
            aqlCache[idx] = aqlPath.format(withOtherPredicates);
        }
        return aqlCache[idx];
    }

    public void setAqlPath(String aqlPath) {
        setAqlPath(AqlPath.parse(aqlPath));
    }

    public void setAqlPath(AqlPath aqlPath) {
        this.aqlPath = aqlPath;
        Arrays.fill(aqlCache, null);
    }

    public List<WebTemplateNode> getChildren() {
        return children;
    }

    public List<WebtemplateCardinality> getCardinalities() {
        return cardinalities;
    }

    @JsonIgnore
    public Map<String, List<WebTemplateNode>> getChoicesInChildren() {
        return children.stream().collect(Collectors.groupingBy(WebTemplateNode::getAqlPath)).entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<WebTemplateInput> getInputs() {
        return inputs;
    }

    public Boolean getInContext() {
        return inContext;
    }

    public void setInContext(Boolean inContext) {
        this.inContext = inContext;
    }

    public Map<String, WebTemplateTerminology> getTermBindings() {
        return termBindings;
    }

    public List<String> getDependsOn() {
        return dependsOn;
    }

    public WebTemplateAnnotation getAnnotations() {
        return annotations;
    }

    public void setAnnotations(WebTemplateAnnotation annotations) {
        this.annotations = annotations;
    }

    public List<ProportionType> getProportionTypes() {
        return proportionTypes;
    }

    public Optional<WebTemplateNode> findChildById(String id) {
        return children.stream().filter(n -> n.getId().equals(id)).findAny();
    }

    public AqlPath buildRelativePath(WebTemplateNode child, boolean checkIfTrueChild) {

        if (checkIfTrueChild) {
            return child.getAqlPathDto().removeStart(this.getAqlPathDto());

        } else {
            AqlPath me = this.aqlPath;
            AqlPath other = child.aqlPath;

            if (!me.hasPath()) {
                return other;
            }

            return other.getEnd(other.getNodeCount() - me.getNodeCount());
        }
    }

    public boolean isRelativePathNameDependent(WebTemplateNode child) {
        return buildRelativePath(child, false).getLastNode().findOtherPredicate(AqlPath.NAME_VALUE_KEY) != null;
    }

    public List<WebTemplateNode> findMatching(Predicate<WebTemplateNode> filter) {

        List<WebTemplateNode> matching = children.stream()
                .map(c -> c.findMatching(filter))
                .flatMap(List::stream)
                .collect(Collectors.toList());

        if (filter.test(this)) {
            matching.add(this);
        }
        return matching;
    }

    public static Stream<WebTemplateNode> streamSubtree(WebTemplateNode node, boolean depthFirst) {
        return streamSubtree(List.of(node), depthFirst);
    }

    public static Stream<WebTemplateNode> streamSubtree(List<WebTemplateNode> nodes, boolean depthFirst) {
        if (nodes.isEmpty()) {
            return Stream.of();
        } else if (depthFirst) {
            return Stream.concat(nodes.stream().flatMap(n -> streamSubtree(n.getChildren(), true)), nodes.stream());
        } else {
            return Stream.concat(nodes.stream(), nodes.stream().flatMap(n -> streamSubtree(n.getChildren(), false)));
        }
    }

    public List<WebTemplateNode> multiValued() {
        List<WebTemplateNode> matching = children.stream()
                .map(WebTemplateNode::multiValued)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        if (this.max != 1) {
            matching.add(this);
        }

        // Add all which are multi if ignoring name
        Map<String, List<WebTemplateNode>> collect = children.stream()
                .collect(Collectors.groupingBy(n -> n.getAqlPathDto().format(false)));
        collect.forEach((k, v) -> {
            if (v.size() > 1) {
                matching.addAll(v.stream().filter(n -> n.max == 1).collect(Collectors.toList()));
            }
        });
        return matching;
    }

    @JsonIgnore
    public boolean isArchetype() {
        return RM_INFO_LOOKUP.getTypeInfo(this.getRmType()) != null
                && Locatable.class.isAssignableFrom(
                        RM_INFO_LOOKUP.getTypeInfo(this.getRmType()).getJavaClass())
                && !StringUtils.startsWith(this.getNodeId(), "at");
    }

    @JsonIgnore
    public boolean isArchetypeSlot() {
        return RM_INFO_LOOKUP.getTypeInfo(this.getRmType()) != null
                && Locatable.class.isAssignableFrom(
                        RM_INFO_LOOKUP.getTypeInfo(this.getRmType()).getJavaClass())
                && StringUtils.startsWith(this.getNodeId(), "at")
                && this.getChildren().isEmpty();
    }

    @JsonIgnore
    public boolean isNullable() {
        return min == 0;
    }

    @JsonIgnore
    public boolean isMulti() {
        return max != 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebTemplateNode that = (WebTemplateNode) o;
        return min == that.min
                && max == that.max
                && Objects.equals(id, that.id)
                && Objects.equals(optionalIdNumber, that.optionalIdNumber)
                && Objects.equals(name, that.name)
                && Objects.equals(localizedName, that.localizedName)
                && Objects.equals(rmType, that.rmType)
                && Objects.equals(nodeId, that.nodeId)
                && Objects.equals(localizedNames, that.localizedNames)
                && Objects.equals(localizedDescriptions, that.localizedDescriptions)
                && Objects.equals(aqlPath, that.aqlPath)
                && Objects.equals(children, that.children)
                && Objects.equals(inputs, that.inputs)
                && Objects.equals(inContext, that.inContext)
                && Objects.equals(termBindings, that.termBindings)
                && Objects.equals(dependsOn, that.dependsOn)
                && Objects.equals(annotations, that.annotations)
                && Objects.equals(proportionTypes, that.proportionTypes)
                && Objects.equals(cardinalities, that.cardinalities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                optionalIdNumber,
                name,
                localizedName,
                rmType,
                nodeId,
                min,
                max,
                localizedNames,
                localizedDescriptions,
                aqlPath,
                children,
                inputs,
                inContext,
                termBindings,
                dependsOn,
                annotations,
                proportionTypes,
                cardinalities);
    }

    @Override
    public String toString() {
        return "WebTemplateNode{" + "id='"
                + id + '\'' + ", name='"
                + name + '\'' + ", rmType='"
                + rmType + '\'' + ", aqlPath='"
                + aqlPath + '\'' + '}';
    }
}
