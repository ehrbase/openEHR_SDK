/*
 * Copyright 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehrbase.webtemplate.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.ehrbase.webtemplate.model.WebTemplateNode;

/**
 * Convenience methods for working with Web Templates.
 */
public class WebTemplateUtils {

  private WebTemplateUtils() {
  }

  /**
   * Return whether the node has two children with types <code>DV_CODED_TEXT</code> and
   * <code>DV_TEXT</code>.
   *
   * @param node the node to check
   * @return <code>true</code> if the provided input both children; <code>false</code>
   * otherwise
   */
  public static boolean hasDvCodedTextAndDvText(WebTemplateNode node) {
    List<String> childrenIds = getTrueChildren(node).stream()
        .map(WebTemplateNode::getId)
        .collect(Collectors.toList());

    return childrenIds.size() == 2
        && childrenIds.containsAll(List.of("coded_text_value", "text_value"))
        && node.getChoicesInChildren().size() > 0;
  }

  /**
   * Return the list of "true" children for the node.
   *
   * @param node the node to check
   * @return the list of "true" children
   */
  public static List<WebTemplateNode> getTrueChildren(WebTemplateNode node) {
    if (!"ELEMENT".equals(node.getRmType())) {
      return Collections.emptyList();
    }
    return node.getChildren().stream()
        .filter(childNode -> !"name".equals(childNode.getName()))
        .filter(childNode -> !List.of("null_flavour", "feeder_audit").contains(childNode.getName())
            || !childNode.isNullable())
        .collect(Collectors.toList());
  }
}
