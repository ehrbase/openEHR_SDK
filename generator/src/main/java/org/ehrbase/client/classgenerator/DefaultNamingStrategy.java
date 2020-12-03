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

package org.ehrbase.client.classgenerator;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CaseUtils;
import org.ehrbase.serialisation.util.SnakeCase;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.parser.FlatPath;

import java.util.Deque;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

public class DefaultNamingStrategy implements NamingStrategy {

  public static final String TERM_DIVIDER = "_";
  private ClassGeneratorConfig config;

  public DefaultNamingStrategy(ClassGeneratorConfig config) {

    this.config = config;
  }

  @Override
  public String buildClassName(
      ClassGeneratorContext context, WebTemplateNode node, boolean isChoice, boolean isEnum) {

    String name = replaceElementName(context, node);
    if (context.nodeDeque.isEmpty()) {
      name = new SnakeCase(context.webTemplate.getTemplateId()).camelToSnake();
    } else {

      if (!node.isArchetype() && !isEnum) {
        name = findLastArchetype(context.nodeDeque).getName() + TERM_DIVIDER + name;
      }
    }
    if (isChoice) {
      name = name + TERM_DIVIDER + "choice";
    } else if (node.getRmType().equals("DV_CODED_TEXT") && isEnum) {
      name = name + TERM_DIVIDER + "defining_code";
    } else {
      name = name + TERM_DIVIDER + node.getRmType();
    }

    String fieldName =
        StringUtils.abbreviate(
            normalise(name, true),
            ClassGenerator.ABBREV_MARKER,
            ClassGenerator.CLASS_NAME_MAX_WIDTH);

    if (context.currentClassNameMap.containsKey(fieldName)) {
      context.currentClassNameMap.put(fieldName, context.currentClassNameMap.get(fieldName) + 1);
      fieldName = fieldName + context.currentClassNameMap.get(fieldName);
    } else {
      context.currentClassNameMap.put(fieldName, 1);
    }
    fieldName = sanitizeNumber(fieldName);
    return fieldName;
  }

  private String makeNameUnique(ClassGeneratorContext context, WebTemplateNode node) {

    WebTemplateNode parent = context.nodeDeque.peek();
    String name = replaceElementName(context, node);
    String finalName = name;
    if (parent.getChildren().stream()
            .filter(
                n ->
                    replaceElementName(context, n).equals(finalName)
                        && !Objects.equals(node.getAqlPath(), n.getAqlPath()))
            .count()
        > 0) {
      if (!Objects.equals(context.unFilteredNodeDeque.peek().getRmType(), "ELEMENT")) {
        name = context.unFilteredNodeDeque.peek().getName() + TERM_DIVIDER + name;
      } else {
        WebTemplateNode poll = context.unFilteredNodeDeque.poll();
        name = context.unFilteredNodeDeque.peek().getName() + TERM_DIVIDER + name;
        context.unFilteredNodeDeque.push(poll);
      }
    }

    return name;
  }

  private String replaceElementName(ClassGeneratorContext context, WebTemplateNode node) {
    String name = node.getName();
    WebTemplateNode trueParent =
        Optional.ofNullable(context.webTemplate.findFiltersNodes(node))
            .map(Deque::peek)
            .orElse(null);
    if (Objects.equals(
        Optional.ofNullable(trueParent).map(WebTemplateNode::getRmType).orElse(null), "ELEMENT")) {
      name = trueParent.getName();
    }
    return name;
  }

  private String sanitizeNumber(String fieldName) {
    if (!Character.isAlphabetic(fieldName.charAt(0))) {
      if (Character.isLowerCase(fieldName.charAt(0))) {
        fieldName = "n" + fieldName;
      } else {
        fieldName = "N" + fieldName;
      }
    }
    return fieldName;
  }

  private WebTemplateNode findLastArchetype(Deque<WebTemplateNode> nodeDeque) {
    for (Iterator<WebTemplateNode> it = nodeDeque.iterator(); it.hasNext(); ) {
      WebTemplateNode node = it.next();
      if (node.isArchetype()) {
        return node;
      }
    }
    return null;
  }

  @Override
  public String toEnumName(String fieldName) {
    fieldName = sanitizeNumber(fieldName);
    return new SnakeCase(normalise(fieldName, false)).camelToUpperSnake();
  }

  @Override
  public String buildFieldName(ClassGeneratorContext context, String path, WebTemplateNode node) {
    String name = node.getName();
    String attributeName = new FlatPath(path).getLast().getAttributeName();

    if (!context.nodeDeque.isEmpty()) {
      if (StringUtils.isBlank(attributeName) || attributeName.equals("value")) {
        name = makeNameUnique(context, node);
      } else {
        name = replaceElementName(context, node);
      }
    }

    if (StringUtils.isNotBlank(attributeName)) {
      name = name + TERM_DIVIDER + attributeName;
    }
    if (name.equals("value")) {
      name = context.nodeDeque.peek().getName();
    }
    if (context.nodeDeque.peek().getRmType().equals("ELEMENT")) {
      name = "value";
    }

    String fieldName = "";

    fieldName = normalise(name, false);

    if (context.currentFieldNameMap.peek().containsKey(fieldName)) {
      context
          .currentFieldNameMap
          .peek()
          .put(fieldName, context.currentFieldNameMap.peek().get(fieldName) + 1);
      fieldName = fieldName + context.currentFieldNameMap.peek().get(fieldName);
    } else {
      context.currentFieldNameMap.peek().put(fieldName, 1);
    }
    fieldName = sanitizeNumber(fieldName);
    return fieldName;
  }

  private String normalise(String name, boolean capitalizeFirstLetter) {
    if (StringUtils.isBlank(name) || name.equals("_")) {
      return RandomStringUtils.randomAlphabetic(10);
    }

    String normalisedString =
        StringUtils.strip(
            StringUtils.stripAccents(name).replace("ß", "ss").replaceAll("[^A-Za-z0-9_]", "_"),
            "_");
    return CaseUtils.toCamelCase(normalisedString, capitalizeFirstLetter, '_');
  }
}
