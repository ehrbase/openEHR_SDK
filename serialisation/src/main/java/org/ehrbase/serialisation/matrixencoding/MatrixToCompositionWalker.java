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
package org.ehrbase.serialisation.matrixencoding;

import static org.ehrbase.serialisation.jsonencoding.CanonicalJson.MARSHAL_OM;
import static org.ehrbase.util.rmconstants.RmConstants.DV_TEXT;
import static org.ehrbase.util.rmconstants.RmConstants.FEEDER_AUDIT;

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
import org.ehrbase.util.exception.SdkException;
import org.ehrbase.webtemplate.interpreter.Interpreter;
import org.ehrbase.webtemplate.model.WebTemplateNode;

/**
 * @author Stefan Spiska
 */
public class MatrixToCompositionWalker extends ToCompositionWalker<List<ToWalkerDto>> {

    public static final String OTHER_REFERENCE_RANGES = "other_reference_ranges";
    // These subparts are saved as arrays.
    public static final List<Pair<AqlPath, String>> ARRAY_ELEMENTS = List.of(
            Pair.of(AqlPath.parse("mappings"), DV_TEXT),
            Pair.of(AqlPath.parse("mappings"), "DV_CODED_TEXT"),
            Pair.of(AqlPath.parse(OTHER_REFERENCE_RANGES), "DV_QUANTITY"),
            Pair.of(AqlPath.parse(OTHER_REFERENCE_RANGES), "DV_DURATION"),
            Pair.of(AqlPath.parse(OTHER_REFERENCE_RANGES), "DV_ORDINAL"),
            Pair.of(AqlPath.parse(OTHER_REFERENCE_RANGES), "DV_PROPORTION"),
            Pair.of(AqlPath.parse(OTHER_REFERENCE_RANGES), "DV_COUNT"),
            Pair.of(AqlPath.parse(OTHER_REFERENCE_RANGES), "DV_DATE"),
            Pair.of(AqlPath.parse(OTHER_REFERENCE_RANGES), "DV_DATE_TIME"),
            Pair.of(AqlPath.parse(OTHER_REFERENCE_RANGES), "DV_TIME"),
            Pair.of(AqlPath.parse(OTHER_REFERENCE_RANGES), "DV_CODED_TEXT"),
            Pair.of(AqlPath.parse("feeder_system_item_ids"), FEEDER_AUDIT),
            Pair.of(AqlPath.parse("originating_system_item_ids"), FEEDER_AUDIT),
            Pair.of(AqlPath.parse("performer/identifiers"), "PARTICIPATION"),
            Pair.of(AqlPath.parse("identifiers"), "PARTY_IDENTIFIED"),
            Pair.of(AqlPath.parse("identifiers"), "PARTY_RELATED"),
            Pair.of(AqlPath.parse("originating_system_audit/provider/identifiers"), FEEDER_AUDIT),
            Pair.of(AqlPath.parse("originating_system_audit/subject/identifiers"), FEEDER_AUDIT),
            Pair.of(AqlPath.parse("feeder_system_audit/provider/identifiers"), FEEDER_AUDIT),
            Pair.of(AqlPath.parse("feeder_system_audit/subject/identifiers"), FEEDER_AUDIT),
            Pair.of(AqlPath.parse(""), "LINK"));
    public static final String STRING = "STRING";
    public static final String BOOLEAN = "BOOLEAN";

    @Override
    protected List<ToWalkerDto> extract(
            Context<List<ToWalkerDto>> context, WebTemplateNode child, boolean isChoice, Integer i) {

        // If primitive return null
        if (List.of(STRING, "LONG", BOOLEAN).contains(child.getRmType())) {

            return null;
        }

        if (i == null
                && Interpreter.findRmAttributeInfo(context.getNodeDeque().peek(), child)
                        .isMultipleValued()) {
            i = 0;
        }

        List<ToWalkerDto> filter = filter(context.getObjectDeque().peek(), child.getAqlPathDto(), i != null, i);

        // Check that the type is correct
        if (isChoice
                && filter(filter, child.getAqlPathDto().addEnd("/_type"), false, null).stream()
                        .noneMatch(e -> e.value.equals(child.getRmType()))) {
            return null;
        }

        MatrixUtil.addMissingChildren(child);

        if (filter.isEmpty()) {
            return null;
        }
        if (i != null) {
            filter = filter.stream().map(MatrixToCompositionWalker::removeIndex).collect(Collectors.toList());
        }
        return filter;
    }

    @Override
    protected void preHandle(Context<List<ToWalkerDto>> context) {

        WebTemplateNode node = context.getNodeDeque().peek();

        if (!visitChildren(node)) {

            Map<String, Object> unflatten = unflatten(
                    node.getRmType(),
                    node.getAqlPathDto(),
                    context.getObjectDeque().peek());

            RMObject peek = context.getRmObjectDeque().peek();

            // We do not correctly serialise Link and Archetyped and thus have to do that manually. (see
            // https://github.com/ehrbase/ehrbase/issues/924)
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

    private static Map<String, Object> unflatten(String rmType, AqlPath aqlPath, List<ToWalkerDto> entries) {
        List<ToWalkerDto> collect = entries.stream()
                .map(o -> {
                    ToWalkerDto toWalkerDto = new ToWalkerDto();
                    toWalkerDto.path = o.path.removeStart(aqlPath);
                    if (isJsonArray(toWalkerDto, rmType)) {
                        try {
                            toWalkerDto.value = MARSHAL_OM.readValue(o.value.toString(), List.class);
                        } catch (JsonProcessingException e) {
                            throw new SdkException(e.getMessage());
                        }
                    } else {
                        toWalkerDto.value = o.value;
                    }

                    toWalkerDto.index = o.index;
                    return toWalkerDto;
                })
                .collect(Collectors.toList());
        return unflatten(collect);
    }

    private static boolean isJsonArray(ToWalkerDto toWalkerDto, String rmType) {

        return ARRAY_ELEMENTS.stream()
                .anyMatch(p -> p.getRight().equals(rmType) && toWalkerDto.path.equals(p.getLeft()));
    }

    private static Map<String, Object> unflatten(List<ToWalkerDto> entries) {

        Map<String, List<ToWalkerDto>> collect = entries.stream()
                .collect(Collectors.groupingBy(e -> e.path.getBaseNode().getName()));

        return collect.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> {

            // Elementar Value found
            if (e.getValue().size() == 1 && e.getValue().get(0).path.getNodeCount() <= 1) {
                return e.getValue().get(0).value;
            }
            // unfaltten sub-values
            else {
                return unflatten(e.getValue().stream()
                        .map(o -> {
                            ToWalkerDto toWalkerDto = new ToWalkerDto();
                            toWalkerDto.value = o.value;
                            toWalkerDto.path = o.path.removeStart(1);
                            toWalkerDto.index = o.index;
                            return toWalkerDto;
                        })
                        .collect(Collectors.toList()));
            }
        }));
    }

    @Override
    protected void postHandle(Context<List<ToWalkerDto>> context) {
        super.postHandle(context);

        RMObject rmObject = context.getRmObjectDeque().peek();

        // missing in the webtemplate and thus have to be handled manually
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
                    DV_TEXT,
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
                    STRING,
                    context.getNodeDeque().peek().getAqlPathDto().addEnd("/location"));
        }

        if (rmObject instanceof Activity) {

            add(
                    ((Activity) rmObject)::setActionArchetypeId,
                    context.getObjectDeque().peek(),
                    STRING,
                    context.getNodeDeque().peek().getAqlPathDto().addEnd("/action_archetype_id"));
        }

        if (rmObject instanceof DvInterval) {

            add(
                    ((DvInterval) rmObject)::setLowerIncluded,
                    context.getObjectDeque().peek(),
                    BOOLEAN,
                    context.getNodeDeque().peek().getAqlPathDto().addEnd("/lower_included"));
            add(
                    ((DvInterval) rmObject)::setUpperIncluded,
                    context.getObjectDeque().peek(),
                    BOOLEAN,
                    context.getNodeDeque().peek().getAqlPathDto().addEnd("/upper_included"));
            add(
                    ((DvInterval) rmObject)::setLowerUnbounded,
                    context.getObjectDeque().peek(),
                    BOOLEAN,
                    context.getNodeDeque().peek().getAqlPathDto().addEnd("/lower_unbounded"));
            add(
                    ((DvInterval) rmObject)::setUpperUnbounded,
                    context.getObjectDeque().peek(),
                    BOOLEAN,
                    context.getNodeDeque().peek().getAqlPathDto().addEnd("/upper_unbounded"));
        }
    }

    @Override
    protected int calculateSize(Context<List<ToWalkerDto>> context, WebTemplateNode childNode) {

        return filter(context.getObjectDeque().peek(), childNode.getAqlPathDto(), true, null).stream()
                .mapToInt(e -> e.index.get(0) + 1)
                .max()
                .orElse(0);
    }

    private static <T> void add(Consumer<T> setter, List<ToWalkerDto> toWalkerDtoList, String rmType, AqlPath aqlPath) {

        List<ToWalkerDto> filter = filter(toWalkerDtoList, aqlPath, false, null);

        if (!filter.isEmpty()) {
            Map<String, Object> unflaten = unflatten(rmType, aqlPath, filter);

            Object object = buildObject(unflaten);

            if (object instanceof Integer && rmType.equals("LONG")) {
                object = ((Integer) object).longValue();
            }

            setter.accept((T) object);
        }
    }

    /**
     * Return
     * @param list
     * @param path
     * @param checkIndex
     * @param index
     * @return
     */
    static List<ToWalkerDto> filter(List<ToWalkerDto> list, AqlPath path, boolean checkIndex, Integer index) {

        return list.stream().filter(e -> matches(e, path, checkIndex, index)).collect(Collectors.toList());
    }

    private static boolean matches(ToWalkerDto toWalkerDto, AqlPath path, boolean checkIndex, Integer index) {

        if (!toWalkerDto.path.startsWith(path)) {
            return false;
        }

        if (checkIndex && toWalkerDto.index.isEmpty()) {
            return false;
        }

        return index == null || toWalkerDto.index.get(0).equals(index);
    }

    private static ToWalkerDto removeIndex(ToWalkerDto current) {

        ToWalkerDto toWalkerDto = new ToWalkerDto(current);
        toWalkerDto.index.remove(0);
        return toWalkerDto;
    }
}
