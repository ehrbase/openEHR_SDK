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
import com.fasterxml.jackson.databind.node.ValueNode;
import com.nedap.archie.rm.RMObject;
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
import org.ehrbase.webtemplate.model.WebTemplateNode;

/**
 * @author Stefan Spiska
 */
public class CompositionToMatrixWalker extends FromCompositionWalker<WalkerDto> {

    private List<String> resolveTo = List.of("OBSERVATION", "EVALUATION", "INSTRUCTION", "ACTION");

    @Override
    protected WalkerDto extract(Context<WalkerDto> context, WebTemplateNode child, boolean isChoice, Integer i) {

        WalkerDto next = new WalkerDto(context.getObjectDeque().peek());

        if (child.getId().equals("context")) {
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

        return next;
    }

    @Override
    protected void preHandle(Context<WalkerDto> context) {

        WebTemplateNode node = context.getNodeDeque().peek();

        if (node.getChildren().isEmpty()) {

            WalkerDto walkerDto = context.getObjectDeque().peek();

            AqlPath relativ = node.getAqlPathDto()
                    .removeStart(walkerDto.getCurrentResolve().getPathFromRoot());

            RMObject rmObject = context.getRmObjectDeque().peek();
            walkerDto
                    .getMatrix()
                    .get(walkerDto.getCurrentResolve())
                    .get(walkerDto.getCurrentIndex())
                    .putAll(flatten(relativ, MARSHAL_OM.valueToTree(rmObject)));
            if (rmObject instanceof DvTime) {
                walkerDto
                        .getMatrix()
                        .get(walkerDto.getCurrentResolve())
                        .get(walkerDto.getCurrentIndex())
                        .put(relativ.addEnd("/magnitude"), ((DvTime) rmObject).getMagnitude());
            }
            if (rmObject instanceof DvDate) {
                walkerDto
                        .getMatrix()
                        .get(walkerDto.getCurrentResolve())
                        .get(walkerDto.getCurrentIndex())
                        .put(relativ.addEnd("/magnitude"), ((DvDate) rmObject).getMagnitude());
            }
            if (rmObject instanceof DvDateTime) {
                walkerDto
                        .getMatrix()
                        .get(walkerDto.getCurrentResolve())
                        .get(walkerDto.getCurrentIndex())
                        .put(relativ.addEnd("/magnitude"), ((DvDateTime) rmObject).getMagnitude());
            }
            if (rmObject instanceof DvDuration) {
                walkerDto
                        .getMatrix()
                        .get(walkerDto.getCurrentResolve())
                        .get(walkerDto.getCurrentIndex())
                        .put(relativ.addEnd("/magnitude"), ((DvDuration) rmObject).getMagnitude());
            }
        }
    }

    private Map<AqlPath, Object> flatten(AqlPath prefix, JsonNode node) {

        LinkedHashMap<AqlPath, Object> result = new LinkedHashMap<>();
        if (node instanceof ObjectNode) {

            for (Iterator<Map.Entry<String, JsonNode>> it = node.fields(); it.hasNext(); ) {
                Map.Entry<String, JsonNode> child = it.next();

                result.putAll(flatten(prefix.addEnd("/" + child.getKey()), child.getValue()));
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
    protected void postHandle(Context<WalkerDto> context) {}

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
