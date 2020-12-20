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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.ehrbase.webtemplate.parser.FlatPath;
import org.ehrbase.webtemplate.parser.NodeId;

public class WebTemplate implements Serializable {

  private String templateId;
  private String version;
  private String defaultLanguage;
  private final List<String> languages = new ArrayList<>();
  private WebTemplateNode tree;

  public WebTemplate() {}

  public WebTemplate(WebTemplate other) {
    this.templateId = other.templateId;
    this.version = other.version;
    this.defaultLanguage = other.defaultLanguage;
    if (other.tree != null) {
      this.tree = new WebTemplateNode(other.tree);
    } else {
      this.tree = null;
    }
    this.languages.addAll(other.languages);
  }

  public String getTemplateId() {
    return templateId;
  }

  public void setTemplateId(String templateId) {
    this.templateId = templateId;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getDefaultLanguage() {
    return defaultLanguage;
  }

  public void setDefaultLanguage(String defaultLanguage) {
    this.defaultLanguage = defaultLanguage;
  }

  public List<String> getLanguages() {
    return languages;
  }

  public WebTemplateNode getTree() {
    return tree;
  }

  public void setTree(WebTemplateNode tree) {
    this.tree = tree;
  }

  public List<WebTemplateNode> upperNotBounded() {
    return tree.findMatching(t -> t.getMax() == -1);
  }

  public List<WebTemplateNode> multiValued() {
    return tree.multiValued();
  }

  public Optional<WebTemplateNode> findByAqlPath(String aql) {
    return findAllByAqlPath(aql, true).stream().findFirst();
  }

  public List<WebTemplateNode> findAllByAqlPath(String aql, boolean ignoreName) {

    return tree.findMatching(
        c ->
            new FlatPath(aql)
                .format(!ignoreName)
                .equals(new FlatPath(c.getAqlPath()).format(!ignoreName)));
  }

  public Set<Set<NodeId>> findAllContainmentCombinations() {
    return findAllContainmentCombinations(tree);
  }

  private Set<Set<NodeId>> findAllContainmentCombinations(WebTemplateNode tree) {
    Set<Set<NodeId>> containments = new LinkedHashSet<>();
    final NodeId currentContainment;
    if (tree.getNodeId() != null && new NodeId(tree.getNodeId()).isArchetypeId()) {

      currentContainment = new NodeId(tree.getNodeId());

      containments.add(new LinkedHashSet<>(Set.of(currentContainment)));

    } else {
      currentContainment = null;
    }

    for (WebTemplateNode child : tree.getChildren()) {
      Set<Set<NodeId>> subSets = findAllContainmentCombinations(child);

      containments.addAll(subSets);
      if (currentContainment != null) {
        containments.addAll(
            subSets.stream()
                .map(
                    s -> {
                      Set<NodeId> list = new LinkedHashSet<>(Set.of(currentContainment));
                      list.addAll(s);
                      return list;
                    })
                .collect(Collectors.toSet()));
      }
    }

    return containments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WebTemplate that = (WebTemplate) o;
    return Objects.equals(templateId, that.templateId)
        && Objects.equals(version, that.version)
        && Objects.equals(defaultLanguage, that.defaultLanguage)
        && languages.equals(that.languages)
        && Objects.equals(tree, that.tree);
  }

  @Override
  public int hashCode() {
    return Objects.hash(templateId, version, defaultLanguage, languages, tree);
  }
}
