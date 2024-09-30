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
package org.ehrbase.openehr.sdk.serialisation.walker;

import static org.ehrbase.openehr.sdk.util.rmconstants.RmConstants.*;

import com.nedap.archie.openehrtestrm.Element;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.EventContext;
import com.nedap.archie.rm.composition.IsmTransition;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMTypeInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.ehrbase.openehr.sdk.aql.webtemplatepath.AqlPath;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.openehr.sdk.util.rmconstants.RmConstants;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplate;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;

public abstract class Walker<T> {

    public static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

    public void walk(
            Composition composition,
            T object,
            WebTemplate webTemplate,
            DefaultValues defaultValues,
            String templateId) {

        walk(composition, object, webTemplate.getTree(), defaultValues, templateId);
    }

    public void walk(RMObject composition, T object, WebTemplateNode root, String templateId) {
        walk(composition, object, root, null, templateId);
    }

    public void walk(
            RMObject composition, T object, WebTemplateNode root, DefaultValues defaultValues, String templateId) {

        Context<T> context = new Context<>();

        context.getNodeDeque().push(new WebTemplateNode(root));
        context.getObjectDeque().push(object);
        context.getRmObjectDeque().push(composition);
        context.setTemplateId(templateId);

        if (defaultValues != null) {
            context.setDefaultValues(defaultValues);
        } else {
            context.setDefaultValues(new DefaultValues());
        }

        handle(context);
    }

    private void handle(Context<T> context) {

        preHandle(context);
        WebTemplateNode currentNode = context.getNodeDeque().peek();

        if (visitChildren(currentNode)) {

            if (ACTION.equals(currentNode.getRmType())) {
                Iterator<WebTemplateNode> it = currentNode.getChildren().iterator();
                while (it.hasNext() && !ISM_TRANSITION.equals(it.next().getRmType())) {
                    // skip until AFTER first ISM_TRANSITION
                }
                // remove addition ISM_TRANSITIONs
                while (it.hasNext()) {
                    if (ISM_TRANSITION.equals(it.next().getRmType())) {
                        it.remove();
                    }
                }
            }
            handleInheritance(currentNode);

            Map<AqlPath, List<WebTemplateNode>> childrenByPath = currentNode.getChildren().stream()
                    .collect(Collectors.groupingBy(
                            WebTemplateNode::getAqlPathDto, LinkedHashMap::new, Collectors.toList()));

            Map<String, List<WebTemplateNode>> allChoices = currentNode.getChoicesInChildren();

            for (List<WebTemplateNode> childrenForPath : childrenByPath.values()) {

                boolean isMulti = childrenForPath.stream().anyMatch(n -> RMHelper.isMulti(currentNode, n));

                Stream<NodeConstellation> childConstellations;
                if (!isMulti) {
                    childConstellations =
                            streamChildConstellations(context, currentNode, allChoices, childrenForPath, null);

                } else {
                    // Number of entries to be added
                    int size = calculateSize(context, childrenForPath.get(0));
                    childConstellations = IntStream.range(0, size)
                            .mapToObj(Integer::valueOf)
                            .flatMap(index ->
                                    streamChildConstellations(context, currentNode, allChoices, childrenForPath, index)
                                            // for each index at most one of the choices is retained
                                            .findFirst()
                                            // an index may be skipped if none of the choices is accepted
                                            .stream());
                }

                childConstellations.forEach(constellation -> {
                    WebTemplateNode childNode = constellation.getNode();
                    context.getNodeDeque().push(childNode);
                    context.getObjectDeque().push(constellation.getObject());
                    context.getRmObjectDeque().push(constellation.getRmObject());
                    if (constellation.getIndex() != null) {
                        context.getCountMap().put(new NodeId(childNode), constellation.getIndex());
                    }
                    handle(context);
                });
            }
        }
        postHandle(context);
        insertDefaults(context);
        context.getRmObjectDeque().remove();
        context.getNodeDeque().remove();
        context.getObjectDeque().remove();
    }

    private Stream<NodeConstellation> streamChildConstellations(
            Context<T> context,
            WebTemplateNode currentNode,
            Map<String, List<WebTemplateNode>> choices,
            List<WebTemplateNode> childrenForPath,
            Integer index) {
        return childrenForPath.stream()
                .map(childNode -> {
                    var pair = extractPair(context, currentNode, choices, childNode, index);
                    if (ObjectUtils.anyNull(pair.getLeft(), pair.getRight())) {
                        return null;
                    } else {
                        return new NodeConstellation(index, pair.getLeft(), pair.getRight(), childNode);
                    }
                })
                .filter(Objects::nonNull);
    }

    private final class NodeConstellation {
        private final Integer index;
        private final T object;
        private final RMObject rmObject;
        private final WebTemplateNode node;

        private NodeConstellation(Integer index, T object, RMObject rmObject, WebTemplateNode node) {
            this.index = index;
            this.object = object;
            this.rmObject = rmObject;
            this.node = node;
        }

        public Integer getIndex() {
            return index;
        }

        public T getObject() {
            return object;
        }

        public RMObject getRmObject() {
            return rmObject;
        }

        public WebTemplateNode getNode() {
            return node;
        }
    }

    /**
     * Add inheritance classes as explicit choices
     *
     * @param currentNode
     */
    protected void handleInheritance(WebTemplateNode currentNode) {

        List<WebTemplateNode> nodesToAdd = new ArrayList<>();

        Iterator<WebTemplateNode> it = currentNode.getChildren().iterator();
        while (it.hasNext()) {
            WebTemplateNode childNode = it.next();

            // Add explicit DV_CODED_TEXT
            if (childNode.getRmType().equals(DV_TEXT) && siblingMissing(currentNode, childNode, DV_CODED_TEXT)) {
                nodesToAdd.add(buildCopy(childNode, DV_CODED_TEXT));
            }

            // Add explicit Party
            if (childNode.getRmType().equals(PARTY_PROXY)) {
                nodesToAdd.add(buildCopy(childNode, PARTY_SELF));
                nodesToAdd.add(buildCopy(childNode, PARTY_IDENTIFIED));
                nodesToAdd.add(copyAsPartyRelated(childNode));
                it.remove();

            } else if (childNode.getRmType().equals(PARTY_IDENTIFIED)
                    && siblingMissing(currentNode, childNode, PARTY_RELATED)) {
                // Add explicit Party related
                nodesToAdd.add(copyAsPartyRelated(childNode));
            }

            // Add explicit Event
            if (childNode.getRmType().equals(EVENT)) {

                WebTemplateNode intervalEvent = buildCopy(childNode, INTERVAL_EVENT);

                WebTemplateNode width = new WebTemplateNode();
                width.setId("width");
                width.setName("width");
                width.setRmType(RmConstants.DV_DURATION);
                width.setMax(1);
                width.setMin(1);
                width.setAqlPath(intervalEvent.getAqlPathDto().addEnd("width"));
                intervalEvent.getChildren().add(width);

                WebTemplateNode math = new WebTemplateNode();
                math.setId("math_function");
                math.setName("math_function");
                math.setRmType(DV_CODED_TEXT);
                math.setMax(1);
                math.setMin(1);
                math.setAqlPath(intervalEvent.getAqlPathDto().addEnd("math_function"));
                intervalEvent.getChildren().add(math);

                WebTemplateNode sampleCount = new WebTemplateNode();
                sampleCount.setId("sample_count");
                sampleCount.setName("sample_count");
                sampleCount.setRmType("LONG");
                sampleCount.setMax(1);
                sampleCount.setAqlPath(intervalEvent.getAqlPathDto().addEnd("sample_count"));
                intervalEvent.getChildren().add(sampleCount);

                nodesToAdd.add(intervalEvent);

                nodesToAdd.add(buildCopy(childNode, POINT_EVENT));

                it.remove();
            }
        }

        if (!nodesToAdd.isEmpty()) {
            currentNode.getChildren().addAll(nodesToAdd);
        }
    }

    private static boolean siblingMissing(WebTemplateNode parentNode, WebTemplateNode childNode, String rmType) {
        return parentNode.getChildren().size() <= 1
                || parentNode.getChildren().stream()
                        .filter(n -> n != childNode)
                        .filter(n -> n.getAqlPathDto().equals(childNode.getAqlPathDto()))
                        .noneMatch(n -> rmType.equals(n.getRmType()));
    }

    private static WebTemplateNode copyAsPartyRelated(WebTemplateNode party) {
        WebTemplateNode node = buildCopy(party, PARTY_RELATED);

        WebTemplateNode relationship = new WebTemplateNode();
        relationship.setId("relationship");
        relationship.setName("relationship");
        relationship.setRmType(DV_CODED_TEXT);
        relationship.setMax(1);
        relationship.setMin(1);
        relationship.setAqlPath(node.getAqlPathDto().addEnd("relationship"));
        node.getChildren().add(relationship);
        return node;
    }

    private static WebTemplateNode buildCopy(WebTemplateNode childNode, String rmType) {
        WebTemplateNode partyIdent = new WebTemplateNode(childNode);
        partyIdent.setRmType(rmType);
        return partyIdent;
    }

    protected abstract ImmutablePair<T, RMObject> extractPair(
            Context<T> context,
            WebTemplateNode currentNode,
            Map<String, List<WebTemplateNode>> choices,
            WebTemplateNode childNode,
            Integer i);

    protected abstract Object extractRMChild(
            RMObject currentRM,
            WebTemplateNode currentNode,
            WebTemplateNode childNode,
            boolean isChoice,
            Integer count);

    protected boolean visitChildren(WebTemplateNode node) {
        RMTypeInfo typeInfo = ARCHIE_RM_INFO_LOOKUP.getTypeInfo(node.getRmType());
        return typeInfo != null
                && (Locatable.class.isAssignableFrom(typeInfo.getJavaClass())
                        || EventContext.class.isAssignableFrom(typeInfo.getJavaClass())
                        || DvInterval.class.isAssignableFrom(typeInfo.getJavaClass())
                        || IsmTransition.class.isAssignableFrom(typeInfo.getJavaClass())
                        || Element.class.isAssignableFrom(typeInfo.getJavaClass()));
    }

    protected abstract T extract(Context<T> context, WebTemplateNode child, boolean isChoice, Integer i);

    protected abstract void preHandle(Context<T> context);

    protected abstract void postHandle(Context<T> context);

    protected void insertDefaults(Context<T> context) {}

    protected Object wrap(Object child) {
        if (child != null) {
            if (String.class.isAssignableFrom(child.getClass())) {
                child = new RmString((String) child);
            } else if (Long.class.isAssignableFrom(child.getClass())) {
                child = new RmLong((Long) child);
            }
            if (Boolean.class.isAssignableFrom(child.getClass())) {
                child = new RmBoolean((Boolean) child);
            }
        }
        return child;
    }

    protected abstract int calculateSize(Context<T> context, WebTemplateNode childNode);

    protected RMObject deepClone(RMObject rmObject) {
        if (rmObject == null) {
            return null;
        }
        CanonicalJson canonicalXML = new CanonicalJson();
        return canonicalXML.unmarshal(canonicalXML.marshal(rmObject), rmObject.getClass());
    }

    public static class EventHelper {
        private WebTemplateNode event;
        private WebTemplateNode pointEvent;
        private WebTemplateNode intervalEvent;

        public EventHelper(WebTemplateNode event) {
            this.event = event;
        }

        public WebTemplateNode getPointEvent() {
            return pointEvent;
        }

        public WebTemplateNode getIntervalEvent() {
            return intervalEvent;
        }

        public EventHelper invoke() {
            pointEvent = new WebTemplateNode(event);
            intervalEvent = new WebTemplateNode(event);
            pointEvent.setRmType(RmConstants.POINT_EVENT);
            intervalEvent.setRmType(RmConstants.INTERVAL_EVENT);

            WebTemplateNode width = new WebTemplateNode();
            width.setId("width");
            width.setName("width");
            width.setRmType(RmConstants.DV_DURATION);
            width.setMax(1);
            width.setMin(1);
            width.setAqlPath(event.getAqlPathDto().addEnd("width"));
            intervalEvent.getChildren().add(width);

            WebTemplateNode math = new WebTemplateNode();
            math.setId("math_function");
            math.setName("math_function");
            math.setRmType(DV_CODED_TEXT);
            math.setMax(1);
            math.setMin(1);
            math.setAqlPath(event.getAqlPathDto().addEnd("math_function"));
            intervalEvent.getChildren().add(math);

            WebTemplateNode sampleCount = new WebTemplateNode();
            sampleCount.setId("sample_count");
            sampleCount.setName("sample_count");
            sampleCount.setRmType("LONG");
            sampleCount.setMax(1);
            sampleCount.setAqlPath(event.getAqlPathDto().addEnd("sample_count"));
            intervalEvent.getChildren().add(sampleCount);

            return this;
        }
    }
}
