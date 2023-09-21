/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.serialisation.walker;

import static org.ehrbase.openehr.sdk.util.rmconstants.RmConstants.RM_VERSION_1_0_4;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Archetyped;
import com.nedap.archie.rm.archetyped.FeederAuditDetails;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.archetyped.TemplateId;
import com.nedap.archie.rm.composition.Action;
import com.nedap.archie.rm.composition.AdminEntry;
import com.nedap.archie.rm.composition.CareEntry;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.Evaluation;
import com.nedap.archie.rm.composition.EventContext;
import com.nedap.archie.rm.composition.InstructionDetails;
import com.nedap.archie.rm.composition.Observation;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.History;
import com.nedap.archie.rm.datastructures.Item;
import com.nedap.archie.rm.datastructures.ItemList;
import com.nedap.archie.rm.datastructures.ItemSingle;
import com.nedap.archie.rm.datastructures.ItemStructure;
import com.nedap.archie.rm.datastructures.ItemTable;
import com.nedap.archie.rm.datastructures.ItemTree;
import com.nedap.archie.rm.support.identification.ArchetypeID;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.defaultinserter.DefaultValueInserter;
import org.ehrbase.openehr.sdk.util.reflection.ReflectionHelper;
import org.ehrbase.openehr.sdk.util.rmconstants.RmConstants;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;
import org.ehrbase.openehr.sdk.webtemplate.parser.NodeId;
import org.ehrbase.openehr.sdk.webtemplate.webtemplateskeletonbuilder.WebTemplateSkeletonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ToCompositionWalker<T> extends Walker<T> {

    private static final Map<Class<?>, DefaultValueInserter> DEFAULT_VALUE_INSERTER_MAP =
            ReflectionHelper.buildMap(DefaultValueInserter.class);

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    protected void postHandle(Context<T> context) {
        RMObject currentRM = context.getRmObjectDeque().peek();
        WebTemplateNode currentNode = context.getNodeDeque().peek();

        if (currentRM instanceof Locatable locatable) {
            NodeId nodeId = new NodeId(Objects.requireNonNull(currentNode).getNodeId());
            if (nodeId.isArchetypeId()) {
                Archetyped archetyped = new Archetyped();
                archetyped.setArchetypeId(new ArchetypeID(nodeId.getNodeId()));
                archetyped.setRmVersion(RM_VERSION_1_0_4);
                locatable.setArchetypeDetails(archetyped);
                locatable.setArchetypeNodeId(nodeId.getNodeId());

                if (currentRM instanceof Composition composition) {
                    TemplateId templateId = new TemplateId();
                    templateId.setValue(context.getTemplateId());
                    Archetyped archetypeDetails = composition.getArchetypeDetails();
                    archetypeDetails.setTemplateId(templateId);
                }
            }
        }

        normalise(currentRM);
    }

    /**
     * Remove empty {@link ItemStructure}
     *
     * @param currentRM
     */
    private void normalise(RMObject currentRM) {
        if (currentRM instanceof CareEntry careEntry && (Optional.of(careEntry)
                    .map(CareEntry::getProtocol)
                    .map(ItemStructure::getItems)
                    .filter(this::isNotEmpty)
                    .isEmpty())) {

                careEntry.setProtocol(null);
        }

        if (currentRM instanceof Observation observation) {

            if (Optional.of(observation)
                    .map(Observation::getState)
                    .map(History::getEvents)
                    .filter(this::isNotEmpty)
                    .isEmpty()) {

                observation.setState(null);
            }

            if (Optional.of(observation)
                    .map(Observation::getData)
                    .map(History::getEvents)
                    .filter(this::isNotEmpty)
                    .isEmpty()) {

                observation.setData(null);
            }
        }

        if (currentRM instanceof Action action && (Optional.of(action)
                    .map(Action::getDescription)
                    .map(ItemStructure::getItems)
                    .filter(this::isNotEmpty)
                    .isEmpty())) {

                action.setDescription(null);
        }

        if (currentRM instanceof Evaluation evaluation && (Optional.of(evaluation)
                    .map(Evaluation::getData)
                    .map(ItemStructure::getItems)
                    .filter(this::isNotEmpty)
                    .isEmpty())) {

                evaluation.setData(null);
        }

        if (currentRM instanceof FeederAuditDetails feederAuditDetails && (Optional.of(feederAuditDetails)
                    .map(FeederAuditDetails::getOtherDetails)
                    .map(ItemStructure::getItems)
                    .filter(this::isNotEmpty)
                    .isEmpty())) {

            feederAuditDetails.setOtherDetails(null);
        }

        if (currentRM instanceof AdminEntry adminEntry && (Optional.of(adminEntry)
                    .map(AdminEntry::getData)
                    .map(ItemStructure::getItems)
                    .filter(this::isNotEmpty)
                    .isEmpty())) {

            adminEntry.setData(null);
        }

        if (currentRM instanceof EventContext eventContext && (Optional.of(eventContext)
                    .map(EventContext::getOtherContext)
                    .map(ItemStructure::getItems)
                    .filter(this::isNotEmpty)
                    .isEmpty())) {

                eventContext.setOtherContext(null);
        }

        if (currentRM instanceof InstructionDetails instructionDetails && (Optional.of(instructionDetails)
                    .map(InstructionDetails::getWfDetails)
                    .map(ItemStructure::getItems)
                    .filter(this::isNotEmpty)
                    .isEmpty())) {

                instructionDetails.setWfDetails(null);
        }

        if (currentRM instanceof Event event) {

            if (Optional.of(event)
                    .map(Event::getState)
                    .map(ItemStructure::getItems)
                    .filter(this::isNotEmpty)
                    .isEmpty()) {

                event.setState(null);
            }

            if (Optional.of(event)
                    .map(Event::getData)
                    .map(ItemStructure::getItems)
                    .filter(this::isNotEmpty)
                    .isEmpty()) {

                event.setData(null);
            }
        }

        if (currentRM instanceof ItemSingle itemSingle && !isNotEmpty(itemSingle.getItem())) {
            itemSingle.setItem(null);
        }

        if (currentRM instanceof ItemList itemList && itemList.getItems() != null) {

            itemList.setItems(itemList
                    .getItems().stream().filter(this::isNotEmpty).collect(Collectors.toList()));
        }

        if (currentRM instanceof ItemTable itemTable && itemTable.getRows() != null) {

            itemTable.setRows(itemTable
                    .getRows().stream().filter(this::isNotEmpty).collect(Collectors.toList()));
        }

        if (currentRM instanceof ItemTree itemTree && itemTree.getItems() != null) {

            itemTree.setItems(itemTree
                    .getItems().stream().filter(this::isNotEmpty).collect(Collectors.toList()));
        }

        if (currentRM instanceof Cluster cluster && cluster.getItems() != null) {

            cluster.setItems(cluster
                    .getItems().stream().filter(this::isNotEmpty).collect(Collectors.toList()));
        }
    }

    private boolean isNotEmpty(Item item) {

        if (item instanceof Element element) {
            return element.getValue() != null || element.getNullFlavour() != null;
        } else if (item instanceof Cluster cluster) {
            return !CollectionUtils.isEmpty(cluster.getItems());
        }

        return true;
    }

    private boolean isNotEmpty(Collection<?> items) {

        return items != null && items.stream().anyMatch(Objects::nonNull);
    }

    @Override
    protected Object extractRMChild(
            RMObject currentRM,
            WebTemplateNode currentNode,
            WebTemplateNode childNode,
            boolean isChoice,
            Integer count) {

        Object newChild = WebTemplateSkeletonBuilder.build(childNode, false, Object.class);

        WebTemplateSkeletonBuilder.insert(currentNode, currentRM, childNode, newChild);

        return wrap(newChild);
    }

    protected ImmutablePair<T, RMObject> extractPair(
            Context<T> context,
            WebTemplateNode currentNode,
            Map<String, List<WebTemplateNode>> choices,
            WebTemplateNode childNode,
            Integer i) {
        RMObject currentChild = null;
        T childObject = null;
        childObject = extract(context, childNode, choices.containsKey(childNode.getAqlPath()), i);
        if (childObject != null) {

            boolean isChoice = choices.containsKey(childNode.getAqlPath());

            if (currentNode.getRmType().equals(RmConstants.ELEMENT)
                    && childNode.getRmType().equals(RmConstants.DV_CODED_TEXT)
                    && childNode.getInputs().stream().anyMatch(in -> "other".equals(in.getSuffix()))) {
                isChoice = true;
            }

            currentChild =
                    (RMObject) extractRMChild(context.getRmObjectDeque().peek(), currentNode, childNode, isChoice, i);
        }

        return new ImmutablePair<>(childObject, currentChild);
    }

    @Override
    protected void insertDefaults(Context<T> context) {

        List<DefaultValueInserter<? super RMObject>> postprocessor = new ArrayList<>();

        Class<?> currentClass = context.getRmObjectDeque().peek().getClass();

        while (currentClass != null) {
            if (DEFAULT_VALUE_INSERTER_MAP.containsKey(currentClass)) {
                postprocessor.add(DEFAULT_VALUE_INSERTER_MAP.get(currentClass));
            }

            currentClass = currentClass.getSuperclass();
        }

        postprocessor.forEach(p -> p.insert(
                context.getRmObjectDeque().peek(),
                context.getDefaultValues(),
                context.getNodeDeque().peek()));
    }
}
