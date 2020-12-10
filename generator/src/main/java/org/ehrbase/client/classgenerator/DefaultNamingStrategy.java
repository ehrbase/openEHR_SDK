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

import com.google.common.base.CharMatcher;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMTypeInfo;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CaseUtils;
import org.ehrbase.serialisation.util.SnakeCase;
import org.ehrbase.webtemplate.model.WebTemplateAnnotation;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.parser.FlatPath;

import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

public class DefaultNamingStrategy implements NamingStrategy {

  private static final ArchieRMInfoLookup RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();
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
        name = findLastArchetype(context.unFilteredNodeDeque).getName() + TERM_DIVIDER + name;
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
        if (config.getOptimizerSetting().equals(OptimizerSetting.ALL)
            && !context.unFilteredNodeDeque.isEmpty()) {
          name = findLastArchetype(context.unFilteredNodeDeque).getName() + TERM_DIVIDER + name;
        } else {
          name = context.unFilteredNodeDeque.peek().getName() + TERM_DIVIDER + name;
        }
      } else {
        if (config.getOptimizerSetting().equals(OptimizerSetting.ALL)
            && !context.unFilteredNodeDeque.isEmpty()) {
          name = findLastArchetype(context.unFilteredNodeDeque).getName() + TERM_DIVIDER + name;
        } else {
          WebTemplateNode poll = context.unFilteredNodeDeque.poll();
          name = context.unFilteredNodeDeque.peek().getName() + TERM_DIVIDER + name;
          context.unFilteredNodeDeque.push(poll);
        }
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
      if (name.equals("null_flavour")) {
        name = trueParent.getName() + TERM_DIVIDER + "null_flavour";
      } else {
        name = trueParent.getName();
      }
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
  public String buildFieldJavadoc(ClassGeneratorContext context, WebTemplateNode node) {
    StringJoiner joiner = new StringJoiner("/");
    for (Iterator<WebTemplateNode> it = context.unFilteredNodeDeque.descendingIterator();
        it.hasNext(); ) {
      WebTemplateNode n = it.next();
      if (!List.of(
                      "HISTORY",
                      "ITEM_TREE",
                      "ITEM_LIST",
                      "ITEM_SINGLE",
                      "ITEM_TABLE",
                      "ITEM_STRUCTURE")
                  .contains(n.getRmType())
              && (!n.getRmType().equals("ELEMENT"))
          || node.getName().equals("null_flavour")) {
        joiner.add(n.getName());
      }
    }
    joiner.add(node.getName());
    String path = joiner.toString();
    StringBuilder sb = new StringBuilder();
    sb.append("Path: ").append(path);
    if (StringUtils.isNotBlank(
        node.getLocalizedDescriptions().get(context.webTemplate.getDefaultLanguage()))) {
      sb.append("\n")
          .append("Description: ")
          .append(node.getLocalizedDescriptions().get(context.webTemplate.getDefaultLanguage()));
    }
    if(Optional.of(node).map(WebTemplateNode::getAnnotations).map(WebTemplateAnnotation::getComment).isPresent()){
      sb.append("\n")
              .append("Comment: ")
              .append(node.getAnnotations().getComment());
    }
    return sb.toString();
  }

  @Override
  public String buildFieldName(ClassGeneratorContext context, String path, WebTemplateNode node) {
    String name = node.getName();
    String attributeName = new FlatPath(path).getLast().getAttributeName();

    if (!context.nodeDeque.isEmpty()) {
      if ((StringUtils.isBlank(attributeName)
              || List.of("defining_code", "value").contains(attributeName))
          && !isEntityAttribute(context, node)) {
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
    if (context.nodeDeque.peek().getRmType().equals("ELEMENT") && !name.equals("feeder_audit")) {
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

  private boolean isEntityAttribute(ClassGeneratorContext context, WebTemplateNode node) {
    FlatPath relativPath = new FlatPath(context.nodeDeque.peek().buildRelativPath(node));
    RMTypeInfo typeInfo = RM_INFO_LOOKUP.getTypeInfo(context.nodeDeque.peek().getRmType());

    return relativPath.getChild() == null
        && typeInfo != null
        && typeInfo.getAttributes().containsKey(relativPath.getName());
  }

  private String normalise(String name, boolean capitalizeFirstLetter) {
    for (Map.Entry<Character, String> entry : config.getReplaceChars().entrySet()) {
      name = CharMatcher.is(entry.getKey()).replaceFrom(name, entry.getValue());
    }
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
