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
package org.ehrbase.serialisation.matrix;

import static org.ehrbase.serialisation.jsonencoding.CanonicalJson.MARSHAL_OM;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Archetyped;
import com.nedap.archie.rm.archetyped.Link;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.archetyped.TemplateId;
import com.nedap.archie.rm.composition.Activity;
import com.nedap.archie.rm.composition.EventContext;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datastructures.IntervalEvent;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rm.support.identification.ArchetypeID;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.building.webtemplateskeletnbuilder.WebTemplateSkeletonBuilder;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.ToCompositionWalker;
import org.ehrbase.util.rmconstants.RmConstants;
import org.ehrbase.webtemplate.model.WebTemplateNode;

/**
 * @author Stefan Spiska
 */
public class MatrixToCompositionWalker extends ToCompositionWalker<List<Entry>> {

    // These subparts are saved as arrays.
    public static final List<Pair<AqlPath, String>> ARRAY_ELEMENTS = List.of(
            Pair.of(AqlPath.parse("mappings"), "DV_TEXT"),
            Pair.of(AqlPath.parse("mappings"), "DV_CODED_TEXT"),
            Pair.of(AqlPath.parse("other_reference_ranges"), "DV_QUANTITY"),
            Pair.of(AqlPath.parse("other_reference_ranges"), "DV_DURATION"),
            Pair.of(AqlPath.parse("other_reference_ranges"), "DV_ORDINAL"),
            Pair.of(AqlPath.parse("other_reference_ranges"), "DV_PROPORTION"),
            Pair.of(AqlPath.parse("other_reference_ranges"), "DV_COUNT"),
            Pair.of(AqlPath.parse("other_reference_ranges"), "DV_DATE"),
            Pair.of(AqlPath.parse("other_reference_ranges"), "DV_DATE_TIME"),
            Pair.of(AqlPath.parse("other_reference_ranges"), "DV_TIME"),
            Pair.of(AqlPath.parse("other_reference_ranges"), "DV_CODED_TEXT"),
            Pair.of(AqlPath.parse("feeder_system_item_ids"), "FEEDER_AUDIT"),
            Pair.of(AqlPath.parse("originating_system_item_ids"), "FEEDER_AUDIT"),
            Pair.of(AqlPath.parse("performer/identifiers"), "PARTICIPATION"),
            Pair.of(AqlPath.parse("identifiers"), "PARTY_IDENTIFIED"),
            Pair.of(AqlPath.parse("identifiers"), "PARTY_RELATED"),
            Pair.of(AqlPath.parse("originating_system_audit/provider/identifiers"), "FEEDER_AUDIT"),
            Pair.of(AqlPath.parse("originating_system_audit/subject/identifiers"), "FEEDER_AUDIT"),
            Pair.of(AqlPath.parse("feeder_system_audit/provider/identifiers"), "FEEDER_AUDIT"),
            Pair.of(AqlPath.parse("feeder_system_audit/subject/identifiers"), "FEEDER_AUDIT"),
            Pair.of(AqlPath.parse(""), "LINK"));

    @Override
    protected List<Entry> extract(Context<List<Entry>> context, WebTemplateNode child, boolean isChoice, Integer i) {

        if (child.getRmType().equals("STRING")) {

            return null;
        }

        List<Entry> filter = filter(context.getObjectDeque().peek(), child.getAqlPathDto(), i != null, i);

        if (isChoice
                && filter(filter, child.getAqlPathDto().addEnd("/_type"), false, null).stream()
                        .noneMatch(e -> e.value.equals(child.getRmType()))) {
            return null;
        }

        if (child.getRmType().equals(RmConstants.ELEMENT)) {

            WebTemplateNode links = new WebTemplateNode();

            links.setRmType("LINK");
            links.setAqlPath(child.getAqlPathDto().addEnd("/links"));
            links.setId("links");
            child.getChildren().add(links);
            child.setMax(0);
            child.setMax(0);
        }

        if (child.getRmType().equals(RmConstants.ISM_TRANSITION)) {

            WebTemplateNode reason = new WebTemplateNode();

            reason.setRmType("DV_TEXT");
            reason.setAqlPath(child.getAqlPathDto().addEnd("/reason"));
            reason.setId("reason");
            child.getChildren().add(reason);
            child.setMax(0);
            child.setMax(0);
        }

        if (filter.isEmpty()) {
            return null;
        }
        return filter;
    }

    @Override
    protected void preHandle(Context<List<Entry>> context) {

        WebTemplateNode node = context.getNodeDeque().peek();

        if (!visitChildren(node)) {

            Map<String, Object> unflatten = unflaten(
                    node.getRmType(),
                    node.getAqlPathDto(),
                    context.getObjectDeque().peek());

            RMObject peek = context.getRmObjectDeque().peek();

            if (peek instanceof Link) {
                unflatten.put("_type", "LINK");
            }
            if (peek instanceof Archetyped) {

                ((Archetyped) peek).setRmVersion(unflatten.get("rm_version").toString());

                ((Archetyped) peek)
                        .setArchetypeId(
                                new ArchetypeID(unflatten.get("archetype_id").toString()));
                if (unflatten.containsKey("template_id")) {

                    TemplateId templateId = new TemplateId();
                    templateId.setValue(unflatten.get("template_id").toString());
                    ((Archetyped) peek).setTemplateId(templateId);
                }
            } else {

                Object newRmObject;
                newRmObject = buildObject(unflatten);

                RMObject oldRM = context.getRmObjectDeque().poll();
                RMObject parentRM = context.getRmObjectDeque().peek();
                WebTemplateNode currentNode = context.getNodeDeque().poll();
                WebTemplateNode parentNode = context.getNodeDeque().peek();

                WebTemplateSkeletonBuilder.remove(parentNode, parentRM, currentNode, oldRM);
                WebTemplateSkeletonBuilder.insert(parentNode, parentRM, currentNode, newRmObject);
                context.getRmObjectDeque().push((RMObject) wrap(newRmObject));
                context.getNodeDeque().push(currentNode);
            }
        }
    }

    private static Object buildObject(Map<String, Object> unflatten) {
        Object newRmObject;
        if (unflatten.size() > 1 || unflatten.containsKey("_type")) {
            newRmObject = MARSHAL_OM.convertValue(unflatten, RMObject.class);
        } else {
            newRmObject = unflatten.values().stream().findAny().orElseThrow();
        }
        return newRmObject;
    }

    private static Map<String, Object> unflaten(String rmType, AqlPath aqlPath, List<Entry> entries) {
        List<Entry> collect = entries.stream()
                .map(o -> {
                    Entry entry = new Entry();
                    entry.path = o.path.removeStart(aqlPath);
                    if (isJsonArray(entry, rmType)) {
                        try {
                            entry.value = MARSHAL_OM.readValue(o.value.toString(), List.class);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        entry.value = o.value;
                    }

                    entry.index = o.index;
                    return entry;
                })
                .collect(Collectors.toList());
        Map<String, Object> unflatten = unflatten(collect);
        return unflatten;
    }

    private static boolean isJsonArray(Entry entry, String rmType) {

        return ARRAY_ELEMENTS.stream().anyMatch(p -> p.getRight().equals(rmType) && entry.path.equals(p.getLeft()));
    }

    private static Map<String, Object> unflatten(List<Entry> entries) {

        Map<String, List<Entry>> collect = entries.stream()
                .collect(Collectors.groupingBy(e -> e.path.getBaseNode().getName()));

        return collect.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> {
            if (e.getValue().size() == 1 && e.getValue().get(0).path.getNodeCount() <= 1) {
                return e.getValue().get(0).value;
            }
            return unflatten(e.getValue().stream()
                    .map(o -> {
                        Entry entry = new Entry();
                        entry.value = o.value;
                        entry.path = o.path.removeStart(1);
                        entry.index = o.index;
                        return entry;
                    })
                    .collect(Collectors.toList()));
        }));
    }

    @Override
    protected void postHandle(Context<List<Entry>> context) {
        super.postHandle(context);

        RMObject rmObject = context.getRmObjectDeque().peek();

        if (rmObject instanceof Locatable) {

            add(
                    ((Locatable) rmObject)::setUid,
                    context.getObjectDeque().peek(),
                    "HIER_OBJECT_ID",
                    context.getNodeDeque().peek().getAqlPathDto().addEnd("/uid"));
        }

        if (rmObject instanceof Element) {

            add(
                    ((Element) rmObject)::setNullReason,
                    context.getObjectDeque().peek(),
                    "DV_TEXT",
                    context.getNodeDeque().peek().getAqlPathDto().addEnd("/null_reason"));
        }

        if (rmObject instanceof IntervalEvent) {

            add(
                    ((IntervalEvent) rmObject)::setSampleCount,
                    context.getObjectDeque().peek(),
                    "LONG",
                    context.getNodeDeque().peek().getAqlPathDto().addEnd("/sample_count"));
        }

        if (rmObject instanceof EventContext) {

            add(
                    ((EventContext) rmObject)::setLocation,
                    context.getObjectDeque().peek(),
                    "STRING",
                    context.getNodeDeque().peek().getAqlPathDto().addEnd("/location"));
        }

        if (rmObject instanceof Activity) {

            add(
                    ((Activity) rmObject)::setActionArchetypeId,
                    context.getObjectDeque().peek(),
                    "STRING",
                    context.getNodeDeque().peek().getAqlPathDto().addEnd("/action_archetype_id"));
        }

        if (rmObject instanceof DvInterval) {

            add(
                    ((DvInterval) rmObject)::setLowerIncluded,
                    context.getObjectDeque().peek(),
                    "BOOLEAN",
                    context.getNodeDeque().peek().getAqlPathDto().addEnd("/lower_included"));
            add(
                    ((DvInterval) rmObject)::setUpperIncluded,
                    context.getObjectDeque().peek(),
                    "BOOLEAN",
                    context.getNodeDeque().peek().getAqlPathDto().addEnd("/upper_included"));
            add(
                    ((DvInterval) rmObject)::setLowerUnbounded,
                    context.getObjectDeque().peek(),
                    "BOOLEAN",
                    context.getNodeDeque().peek().getAqlPathDto().addEnd("/lower_unbounded"));
            add(
                    ((DvInterval) rmObject)::setUpperUnbounded,
                    context.getObjectDeque().peek(),
                    "BOOLEAN",
                    context.getNodeDeque().peek().getAqlPathDto().addEnd("/upper_unbounded"));
        }
    }

    @Override
    protected int calculateSize(Context<List<Entry>> context, WebTemplateNode childNode) {

        return filter(context.getObjectDeque().peek(), childNode.getAqlPathDto(), true, null).stream()
                .mapToInt(e -> e.index.get(0) + 1)
                .max()
                .orElse(0);
    }

    private static <T> void add(Consumer<T> setter, List<Entry> entryList, String rmType, AqlPath aqlPath) {

        List<Entry> filter = filter(entryList, aqlPath, false, null);

        if (!filter.isEmpty()) {
            Map<String, Object> unflaten = unflaten(rmType, aqlPath, filter);

            Object object = buildObject(unflaten);

            if (object instanceof Integer && rmType.equals("LONG")) {
                object = ((Integer) object).longValue();
            }

            setter.accept((T) object);
        }
    }

    static List<Entry> filter(List<Entry> list, AqlPath path, boolean checkIndex, Integer index) {

        return list.stream().filter(e -> matches(e, path, checkIndex, index)).collect(Collectors.toList());
    }

    private static boolean matches(Entry entry, AqlPath path, boolean checkIndex, Integer index) {

        if (!entry.path.startsWith(path)) {
            return false;
        }

        if (checkIndex && entry.index.isEmpty()) {
            return false;
        }

        return index == null || entry.index.get(0).equals(index);
    }
}
