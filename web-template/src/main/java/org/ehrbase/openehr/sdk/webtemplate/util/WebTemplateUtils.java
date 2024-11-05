/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.webtemplate.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import org.ehrbase.openehr.sdk.util.rmconstants.RmConstants;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;

/**
 * Convenience methods for working with Web Templates.
 */
public class WebTemplateUtils {

    private WebTemplateUtils() {}

    /**
     * Return whether the node is a choice between <code>DV_CODED_TEXT</code> and
     * <code>DV_TEXT</code>.
     *
     * @param node the node to check
     * @return <code>true</code> if the provided input is choice; <code>false</code>
     * otherwise
     */
    public static boolean isChoiceDvCodedTextAndDvText(WebTemplateNode node) {
        List<String> childrenIds = getTrueChildrenElement(node).stream()
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
    public static List<WebTemplateNode> getTrueChildrenElement(WebTemplateNode node) {
        if (!RmConstants.ELEMENT.equals(node.getRmType())) {
            return Collections.emptyList();
        }

        return node.getChildren().stream()
                .filter(childNode -> !"name".equals(childNode.getName()))
                .filter(childNode -> !List.of("null_flavour", "feeder_audit").contains(childNode.getName())
                        || !childNode.isNullable())
                .collect(Collectors.toList());
    }

    public static <T> List<T> cloneList(List<T> list, UnaryOperator<T> elementCloner) {
        List<T> clonedList = new ArrayList<>(list.size());
        list.forEach(el -> clonedList.add(elementCloner.apply(el)));
        return clonedList;
    }

    public static <K, V> LinkedHashMap<K, V> cloneMap(Map<K, V> map, UnaryOperator<V> valueCloner) {
        if (map.isEmpty()) {
            return new LinkedHashMap<>();
        }
        LinkedHashMap<K, V> newMap = new LinkedHashMap<>((map.size() * 4 / 3) + 1, 0.75f);
        map.forEach((k, v) -> newMap.put(k, valueCloner.apply(v)));
        return newMap;
    }
}
