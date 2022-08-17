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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.POJONode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Archetyped;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Activity;
import com.nedap.archie.rm.composition.EventContext;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datastructures.IntervalEvent;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDate;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvTime;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FromCompositionWalker;
import org.ehrbase.serialisation.walker.RmPrimitive;
import org.ehrbase.util.rmconstants.RmConstants;
import org.ehrbase.webtemplate.model.WebTemplateNode;

/**
 * @author Stefan Spiska
 */
public class CompositionToMatrixWalker extends FromCompositionWalker<FromWalkerDto> {

    private List<String> resolveTo = List.of("OBSERVATION", "EVALUATION", "INSTRUCTION", "ACTION", "ADMIN_ENTRY");

    @Override
    protected FromWalkerDto extract(
            Context<FromWalkerDto> context, WebTemplateNode child, boolean isChoice, Integer i) {

        FromWalkerDto next = new FromWalkerDto(context.getObjectDeque().peek());

        if (List.of("context", "links", "feeder_audit").contains(child.getId())) {
            next.setRootFound(true);
        }

        if (child.getNodeId() != null
                && findTypeName(child.getNodeId()) != null
                && resolveTo.contains(findTypeName(child.getNodeId()))) {

            Resolve nextResolve = new Resolve(next.getCurrentResolve());
            nextResolve.setArchetypeId(child.getNodeId());
            nextResolve.setPathFromRoot(
                    child.getAqlPathDto().removeStart(next.getCurrentResolve().getPathFromRoot()));
            if (i != null) {
                nextResolve.getCount().add(child.getAqlPath(), i);
            }
            next.updateResolve(nextResolve);
            next.setRootFound(true);

        } else {

            if (i != null) {

                if (next.isRootFound()) {
                    next.getCurrentIndex().add(child.getAqlPath(), i);
                    next.getMatrix().get(next.getCurrentResolve()).put(next.getCurrentIndex(), new LinkedHashMap<>());
                } else {
                    next.getCurrentResolve().getCount().add(child.getAqlPath(), i);
                }
            }
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
        return next;
    }

    @Override
    protected void preHandle(Context<FromWalkerDto> context) {

        WebTemplateNode node = context.getNodeDeque().peek();
        FromWalkerDto fromWalkerDto = context.getObjectDeque().peek();
        RMObject rmObject = context.getRmObjectDeque().peek();
        AqlPath relativ = node.getAqlPathDto()
                .removeStart(fromWalkerDto.getCurrentResolve().getPathFromRoot());
        if (!visitChildren(node)) {

            if (rmObject instanceof RmPrimitive) {
                fromWalkerDto
                        .getMatrix()
                        .get(fromWalkerDto.getCurrentResolve())
                        .get(fromWalkerDto.getCurrentIndex())
                        .put(relativ, ((RmPrimitive<?>) rmObject).getValue());
            } else {
                fromWalkerDto
                        .getMatrix()
                        .get(fromWalkerDto.getCurrentResolve())
                        .get(fromWalkerDto.getCurrentIndex())
                        .putAll(flatten(relativ, MARSHAL_OM.valueToTree(rmObject)));
            }

            if (rmObject instanceof Archetyped) {
                fromWalkerDto
                        .getMatrix()
                        .get(fromWalkerDto.getCurrentResolve())
                        .get(fromWalkerDto.getCurrentIndex())
                        .put(relativ.addEnd("/_type"), "ARCHETYPED");
            }

            if (rmObject instanceof DvTime) {
                fromWalkerDto
                        .getMatrix()
                        .get(fromWalkerDto.getCurrentResolve())
                        .get(fromWalkerDto.getCurrentIndex())
                        .put(relativ.addEnd("/magnitude"), ((DvTime) rmObject).getMagnitude());
            }
            if (rmObject instanceof DvDate) {
                fromWalkerDto
                        .getMatrix()
                        .get(fromWalkerDto.getCurrentResolve())
                        .get(fromWalkerDto.getCurrentIndex())
                        .put(relativ.addEnd("/magnitude"), ((DvDate) rmObject).getMagnitude());
            }
            if (rmObject instanceof DvDateTime) {
                fromWalkerDto
                        .getMatrix()
                        .get(fromWalkerDto.getCurrentResolve())
                        .get(fromWalkerDto.getCurrentIndex())
                        .put(relativ.addEnd("/magnitude"), ((DvDateTime) rmObject).getMagnitude());
            }
            if (rmObject instanceof DvDuration) {
                fromWalkerDto
                        .getMatrix()
                        .get(fromWalkerDto.getCurrentResolve())
                        .get(fromWalkerDto.getCurrentIndex())
                        .put(relativ.addEnd("/magnitude"), ((DvDuration) rmObject).getMagnitude());
            }
        } else {

            fromWalkerDto
                    .getMatrix()
                    .get(fromWalkerDto.getCurrentResolve())
                    .get(fromWalkerDto.getCurrentIndex())
                    .put(relativ.addEnd("/_type"), node.getRmType());
        }
    }

    private Map<AqlPath, Object> flatten(AqlPath prefix, JsonNode node) {

        LinkedHashMap<AqlPath, Object> result = new LinkedHashMap<>();
        if (node instanceof ObjectNode) {

            for (Iterator<Map.Entry<String, JsonNode>> it = node.fields(); it.hasNext(); ) {
                Map.Entry<String, JsonNode> child = it.next();

                result.putAll(flatten(prefix.addEnd("/" + child.getKey()), child.getValue()));
            }

        } else if (node instanceof POJONode) {
            try {
                result.putAll(flatten(prefix, MARSHAL_OM.readTree((node).toString())));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else if (node instanceof ValueNode) {
            if (node instanceof NumericNode) {
                result.put(prefix, node.numberValue());
            } else if (node instanceof BooleanNode) {
                result.put(prefix, node.booleanValue());
            } else {
                result.put(prefix, node.asText());
            }
        } else if (node instanceof ArrayNode) {
            try {
                result.put(prefix, MARSHAL_OM.writeValueAsString(node));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Override
    protected void postHandle(Context<FromWalkerDto> context) {

        WebTemplateNode node = context.getNodeDeque().peek();
        FromWalkerDto fromWalkerDto = context.getObjectDeque().peek();
        RMObject rmObject = context.getRmObjectDeque().peek();
        AqlPath relativ = node.getAqlPathDto()
                .removeStart(fromWalkerDto.getCurrentResolve().getPathFromRoot());

        if (rmObject instanceof Locatable) {

            add(fromWalkerDto, relativ.addEnd("/uid"), ((Locatable) rmObject).getUid());
        }

        if (rmObject instanceof Element) {

            add(fromWalkerDto, relativ.addEnd("/null_reason"), ((Element) rmObject).getNullReason());
        }

        if (rmObject instanceof IntervalEvent) {

            add(fromWalkerDto, relativ.addEnd("/sample_count"), ((IntervalEvent) rmObject).getSampleCount());
        }

        if (rmObject instanceof EventContext) {

            add(fromWalkerDto, relativ.addEnd("/location"), ((EventContext) rmObject).getLocation());
        }

        if (rmObject instanceof Activity) {

            add(fromWalkerDto, relativ.addEnd("/action_archetype_id"), ((Activity) rmObject).getActionArchetypeId());
        }

        if (rmObject instanceof DvInterval) {
            add(fromWalkerDto, relativ.addEnd("/lower_included"), ((DvInterval) rmObject).isLowerIncluded());
            add(fromWalkerDto, relativ.addEnd("/upper_included"), ((DvInterval) rmObject).isUpperIncluded());
            add(fromWalkerDto, relativ.addEnd("/lower_unbounded"), ((DvInterval) rmObject).isLowerUnbounded());
            add(fromWalkerDto, relativ.addEnd("/upper_unbounded"), ((DvInterval) rmObject).isUpperUnbounded());
        }
    }

    private void add(FromWalkerDto fromWalkerDto, AqlPath relativ, Object o) {
        if (o != null) {
            fromWalkerDto
                    .getMatrix()
                    .get(fromWalkerDto.getCurrentResolve())
                    .get(fromWalkerDto.getCurrentIndex())
                    .putAll(flatten(relativ, MARSHAL_OM.valueToTree(o)));
        }
    }

    static String findTypeName(String atCode) {
        String typeName = null;

        if (atCode.contains("openEHR-EHR-")) {

            typeName = StringUtils.substringBetween(atCode, "openEHR-EHR-", ".");
        } else if (atCode.startsWith("at")) {
            typeName = null;
        } else {
            typeName = atCode;
        }
        return typeName;
    }
}
