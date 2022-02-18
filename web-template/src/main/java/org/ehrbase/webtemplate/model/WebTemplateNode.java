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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.webtemplate.parser.FlatPath;

import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WebTemplateNode implements Serializable {

  private static final ArchieRMInfoLookup RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

  private String id;
  @JsonIgnore private Integer optionalIdNumber;
  private String name;
  private String localizedName;
  private String rmType;
  private String nodeId;
  private int min;
  private int max;
  private final Map<String, String> localizedNames = new HashMap<>();
  private final Map<String, String> localizedDescriptions = new HashMap<>();

  @JsonIgnore
  private final String[] aqlCache = new String[2];

  @JsonSerialize(using = AqlPathSerializer.class)
  private String aqlPath;

  private final List<WebTemplateNode> children = new ArrayList<>();
  private final List<WebTemplateInput> inputs = new ArrayList<>();
  private Boolean inContext;
  private final Map<String, WebTemplateTerminology> termBindings = new HashMap<>();
  private final List<String> dependsOn = new ArrayList<>();
  private WebTemplateAnnotation annotations;
  private final List<ProportionType> proportionTypes = new ArrayList<>();
  private final List<WebtemplateCardinality> cardinalities = new ArrayList<>();

  public WebTemplateNode() {}

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
    this.dependsOn.addAll(other.dependsOn);
    if (other.annotations != null) {
      this.annotations = new WebTemplateAnnotation(other.annotations);
    } else {
      this.annotations = null;
    }

    this.cardinalities.addAll(
        other.cardinalities.stream().map(WebtemplateCardinality::new).collect(Collectors.toList()));
    this.inputs.addAll(
        other.getInputs().stream().map(WebTemplateInput::new).collect(Collectors.toList()));
    this.getChildren()
        .addAll(other.children.stream().map(WebTemplateNode::new).collect(Collectors.toList()));
    this.localizedNames.putAll(other.localizedNames);
    this.localizedDescriptions.putAll(other.localizedDescriptions);
    this.proportionTypes.addAll(other.getProportionTypes());
    this.termBindings.putAll(
        other.termBindings.entrySet().stream()
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey, e -> new WebTemplateTerminology(e.getValue()))));
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
    return aqlPath;
  }

  public String getAqlPath(boolean withOtherPredicates) {
    int idx = withOtherPredicates ? 0 : 1;
    if (aqlCache[idx] == null) {
      aqlCache[idx] = new FlatPath(aqlPath).format(withOtherPredicates);
    }
    return aqlCache[idx];
  }

  public void setAqlPath(String aqlPath) {
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
    return children.stream()
        .collect(Collectors.groupingBy(WebTemplateNode::getAqlPath))
        .entrySet()
        .stream()
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

  public String buildRelativePath(WebTemplateNode child) {
    return FlatPath.removeStart(new FlatPath(child.getAqlPath()), new FlatPath(this.getAqlPath()))
        .toString();
  }

  public boolean isRelativePathNameDependent(WebTemplateNode child){
    return FlatPath.removeStart(new FlatPath(child.getAqlPath()), new FlatPath(this.getAqlPath())).getLast().findOtherPredicate("name/value") != null;
  }
  public List<WebTemplateNode> findMatching(Predicate<WebTemplateNode> filter) {

    List<WebTemplateNode> matching =
            children.stream()
                .map(c -> c.findMatching(filter))
                .flatMap(List::stream)
                .collect(Collectors.toList());

    if (filter.test(this)) {
      matching.add(this);
    }
    return matching;
  }

  public List<WebTemplateNode> multiValued() {
    List<WebTemplateNode> matching =
            children.stream()
                .map(WebTemplateNode::multiValued)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    if (this.max != 1) {
      matching.add(this);
    }

    // Add all which are multi if ignoring name
    Map<String, List<WebTemplateNode>> collect =
        children.stream()
            .collect(Collectors.groupingBy(n -> new FlatPath(n.getAqlPath()).format(false)));
    collect.forEach(
        (k, v) -> {
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
    return "WebTemplateNode{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", rmType='" + rmType + '\'' +
        ", aqlPath='" + aqlPath + '\'' +
        '}';
  }
}
