/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.webtemplate.filter;

import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMTypeInfo;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.ehrbase.openehr.sdk.util.rmconstants.RmConstants;
import org.ehrbase.openehr.sdk.webtemplate.model.FilteredWebTemplate;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplate;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateInput;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;
import org.ehrbase.openehr.sdk.webtemplate.model.WebtemplateCardinality;
import org.ehrbase.openehr.sdk.webtemplate.parser.InputHandler;
import org.ehrbase.openehr.sdk.webtemplate.parser.OPTParser;
import org.ehrbase.openehr.sdk.webtemplate.util.WebTemplateUtils;

public class Filter implements WebTemplateFilter {

    public static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

    @Override
    public FilteredWebTemplate filter(WebTemplate webTemplate) {
        FilteredWebTemplate clone = new FilteredWebTemplate(webTemplate);
        Pair<List<WebTemplateNode>, Map<Pair<String, String>, Deque<WebTemplateNode>>> filter =
                filter(clone.getTree(), webTemplate, new ArrayDeque<>());
        clone.setTree(filter.getLeft().get(0));
        clone.setFilteredNodeMap(filter.getRight());

        return clone;
    }

    protected Pair<List<WebTemplateNode>, Map<Pair<String, String>, Deque<WebTemplateNode>>> filter(
            WebTemplateNode node, WebTemplate context, Deque<WebTemplateNode> deque) {

        WebTemplateNode oldNode = new WebTemplateNode(node);
        preHandle(node);
        List<WebTemplateNode> nodes;
        List<WebTemplateNode> filteredChildren = new ArrayList<>();
        Map<Pair<String, String>, Deque<WebTemplateNode>> nodeMap = new HashMap<>();
        deque.push(node);
        node.getChildren().stream().map(n -> filter(n, context, deque)).forEach(p -> {
            filteredChildren.addAll(p.getLeft());
            nodeMap.putAll(p.getRight());
        });
        deque.poll();
        node.getChildren().clear();
        node.getChildren().addAll(filteredChildren);
        if (skip(node, context, deque)) {
            nodes = filteredChildren;
            if (node.getRmType().equals(RmConstants.ELEMENT)) {
                node.getChildren().forEach(n -> {
                    n.setMin(node.getMin());
                    n.setMax(node.getMax());
                });
            }
            for (WebTemplateNode child : filteredChildren) {
                nodeMap.get(new ImmutablePair<>(child.getAqlPath(), child.getRmType()))
                        .addLast(oldNode);
            }
        } else {
            nodes = Collections.singletonList(node);
            nodeMap.put(new ImmutablePair<>(node.getAqlPath(), node.getRmType()), new ArrayDeque<>());
        }
        OPTParser.makeIdUnique(node);
        return new ImmutablePair<>(nodes, nodeMap);
    }

    protected void preHandle(WebTemplateNode node) {

        List<WebTemplateNode> ismTransitionList = node.getChildren().stream()
                .filter(n -> RmConstants.ISM_TRANSITION.equals(n.getRmType()))
                .collect(Collectors.toList());
        if (!ismTransitionList.isEmpty()) {
            node.getChildren().removeAll(ismTransitionList);
            node.getChildren().add(ismTransitionList.get(0));
        }

        if (node.getRmType().equals(RmConstants.ELEMENT)) {
            if (WebTemplateUtils.isChoiceDvCodedTextAndDvText(node)) {
                WebTemplateNode merged = mergeDVText(node);
                merged.setId(node.getId());
                node.getChildren().clear();
                node.getChildren().add(merged);
            } else {
                List<WebTemplateNode> trueChildren = WebTemplateUtils.getTrueChildrenElement(node);
                if (trueChildren.size() == 1) {
                    // Element will be skipped and the value node inherits the id
                    trueChildren.get(0).setId(node.getId());
                }
            }
        }

        List<WebtemplateCardinality> cardinalities = node.getCardinalities().stream()
                .filter(webtemplateCardinality ->
                        BooleanUtils.isNotTrue(webtemplateCardinality.getExcludeFromWebTemplate()))
                .collect(Collectors.toList());

        node.getCardinalities().clear();
        node.getCardinalities().addAll(cardinalities);
    }

    public static WebTemplateNode mergeDVText(WebTemplateNode node) {
        WebTemplateNode merged = new WebTemplateNode();
        merged.setId("value");
        merged.setName(node.getName());
        merged.setMax(node.getMax());
        merged.setMin(node.getMin());
        merged.setRmType(RmConstants.DV_CODED_TEXT);
        WebTemplateNode codedTextValue = node.findChildById("coded_text_value").orElseThrow();
        merged.getInputs().addAll(codedTextValue.getInputs());
        merged.setAqlPath(codedTextValue.getAqlPathDto());
        merged.getLocalizedDescriptions().putAll(node.getLocalizedDescriptions());
        merged.getLocalizedNames().putAll(node.getLocalizedNames());
        merged.setLocalizedName(node.getLocalizedName());
        merged.setAnnotations(node.getAnnotations());
        WebTemplateInput other = InputHandler.buildWebTemplateInput("other", "TEXT");

        merged.getInputs().add(other);
        merged.getInputs().stream()
                .filter(i -> Objects.equals(i.getSuffix(), "code"))
                .findAny()
                .ifPresent(i -> i.setListOpen(true));
        return merged;
    }

    protected boolean skip(WebTemplateNode node, WebTemplate context, Deque<WebTemplateNode> deque) {
        WebTemplateNode parent = deque.peek();
        if (node.isArchetypeSlot()) {
            return true;
        }
        if (parent != null && isNonMandatoryRmAttribute(node, parent)) {
            return true;
        }

        if (Set.of(
                        RmConstants.HISTORY,
                        RmConstants.ITEM_TREE,
                        RmConstants.ITEM_LIST,
                        RmConstants.ITEM_SINGLE,
                        RmConstants.ITEM_TABLE,
                        RmConstants.ITEM_STRUCTURE)
                .contains(node.getRmType())) {
            return true;
        } else if (parent != null && isEvent(node)) {
            return parent.getChildren().stream().filter(this::isEvent).count() == 1 && node.getMax() == 1;
        } else if (node.getRmType().equals(RmConstants.ELEMENT)) {
            return node.getChildren().size() == 1;
        } else if (node.getRmType().equals(RmConstants.CODE_PHRASE) && parent != null) {
            return parent.getRmType().equals(RmConstants.DV_CODED_TEXT);
        }
        return false;
    }

    protected boolean isEvent(WebTemplateNode node) {
        RMTypeInfo typeInfo = ARCHIE_RM_INFO_LOOKUP.getTypeInfo(node.getRmType());
        return typeInfo != null && Event.class.isAssignableFrom(typeInfo.getJavaClass());
    }

    protected boolean isNonMandatoryRmAttribute(WebTemplateNode node, WebTemplateNode parent) {
        RMTypeInfo typeInfo = ARCHIE_RM_INFO_LOOKUP.getTypeInfo(parent.getRmType());
        boolean nonMandatoryRmAttribute = typeInfo.getAttributes().containsKey(node.getName()) && node.getMin() == 0;
        boolean mandatoryNotInWebTemplate = List.of(
                        "name",
                        "archetype_node_id",
                        "origin",
                        "media_type",
                        "upper_included",
                        "lower_included",
                        "upper_unbounded",
                        "lower_unbounded")
                .contains(node.getName());
        boolean nonMandatoryInWebTemplate =
                typeInfo.getRmName().equals("ACTIVITY") && node.getName().equals("timing")
                        || typeInfo.getRmName().equals(RmConstants.INSTRUCTION)
                                && node.getName().equals("expiry_time")
                        || typeInfo.getRmName().equals(RmConstants.ISM_TRANSITION)
                                && node.getName().equals("transition")
                        || typeInfo.getRmName().equals(RmConstants.COMPOSITION)
                                && node.getName().equals("context");

        return (nonMandatoryRmAttribute || mandatoryNotInWebTemplate) && !nonMandatoryInWebTemplate;
    }
}
