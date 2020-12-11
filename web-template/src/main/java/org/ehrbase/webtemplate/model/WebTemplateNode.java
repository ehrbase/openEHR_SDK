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

import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import net.minidev.json.annotate.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.webtemplate.parser.FlatPath;

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
  private String aqlPath;
  private final List<WebTemplateNode> children = new ArrayList<>();
  private final List<WebTemplateInput> inputs = new ArrayList<>();
  private Boolean inContext;
  private final Map<String, WebTemplateTerminology> termBindings = new HashMap<>();
  private final List<String> dependsOn = new ArrayList<>();
  private WebTemplateAnnotation annotations;
  private final List<String> proportionTypes = new ArrayList<>();

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
    return new FlatPath(aqlPath).format(withOtherPredicates);
  }

  public void setAqlPath(String aqlPath) {
    this.aqlPath = aqlPath;
  }

  public List<WebTemplateNode> getChildren() {
    return children;
  }

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

  public List<String> getProportionTypes() {
    return proportionTypes;
  }

  public Optional<WebTemplateNode> findChildById(String id) {
    return children.stream().filter(n -> n.getId().equals(id)).findAny();
  }

  public String buildRelativPath(WebTemplateNode child) {
    return FlatPath.removeStart(new FlatPath(child.getAqlPath()), new FlatPath(this.getAqlPath()))
        .toString();
  }

  public List<WebTemplateNode> findMatching(Predicate<WebTemplateNode> filter) {

    List<WebTemplateNode> matching =
        new ArrayList<>(
            children.stream()
                .map(c -> c.findMatching(filter))
                .flatMap(List::stream)
                .collect(Collectors.toList()));

    if (filter.test(this)) {
      matching.add(this);
    }
    return matching;
  }

  public List<WebTemplateNode> multiValued() {
    List<WebTemplateNode> matching =
        new ArrayList<>(
            children.stream()
                .map(WebTemplateNode::multiValued)
                .flatMap(List::stream)
                .collect(Collectors.toList()));
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

  public boolean isArchetype() {
    return RM_INFO_LOOKUP.getTypeInfo(this.getRmType()) != null
        && Locatable.class.isAssignableFrom(
            RM_INFO_LOOKUP.getTypeInfo(this.getRmType()).getJavaClass())
        && !StringUtils.startsWith(this.getNodeId(), "at");
  }

  public boolean isArchetypeSlot() {
    return RM_INFO_LOOKUP.getTypeInfo(this.getRmType()) != null
        && Locatable.class.isAssignableFrom(
            RM_INFO_LOOKUP.getTypeInfo(this.getRmType()).getJavaClass())
        && StringUtils.startsWith(this.getNodeId(), "at")
        && this.getChildren().isEmpty();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WebTemplateNode node = (WebTemplateNode) o;
    return min == node.min
        && max == node.max
        && Objects.equals(id, node.id)
        && Objects.equals(optionalIdNumber, node.optionalIdNumber)
        && Objects.equals(name, node.name)
        && Objects.equals(localizedName, node.localizedName)
        && Objects.equals(rmType, node.rmType)
        && Objects.equals(nodeId, node.nodeId)
        && Objects.equals(localizedNames, node.localizedNames)
        && Objects.equals(localizedDescriptions, node.localizedDescriptions)
        && Objects.equals(aqlPath, node.aqlPath)
        && Objects.equals(children, node.children)
        && Objects.equals(inputs, node.inputs)
        && Objects.equals(inContext, node.inContext)
        && Objects.equals(termBindings, node.termBindings)
        && Objects.equals(dependsOn, node.dependsOn)
        && Objects.equals(annotations, node.annotations)
        && Objects.equals(proportionTypes, node.proportionTypes);
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
        proportionTypes);
  }

  public boolean isMulti() {
    return max != 1;
  }
}
