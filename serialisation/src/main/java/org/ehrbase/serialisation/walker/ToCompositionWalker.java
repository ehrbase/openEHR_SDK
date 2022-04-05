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

package org.ehrbase.serialisation.walker;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Archetyped;
import com.nedap.archie.rm.archetyped.FeederAuditDetails;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.archetyped.TemplateId;
import com.nedap.archie.rm.composition.*;
import com.nedap.archie.rm.datastructures.*;
import com.nedap.archie.rm.support.identification.ArchetypeID;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.ehrbase.building.webtemplateskeletnbuilder.WebTemplateSkeletonBuilder;
import org.ehrbase.serialisation.walker.defaultvalues.defaultinserter.DefaultValueInserter;
import org.ehrbase.util.reflection.ReflectionHelper;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.parser.NodeId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static org.ehrbase.util.rmconstants.RmConstants.RM_VERSION_1_4_0;

public abstract class ToCompositionWalker<T> extends Walker<T> {

  private static final Map<Class<?>, DefaultValueInserter> DEFAULT_VALUE_INSERTER_MAP =
      ReflectionHelper.buildMap(DefaultValueInserter.class);

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Override
  protected void postHandle(Context<T> context) {
    RMObject currentRM = context.getRmObjectDeque().peek();
    WebTemplateNode currentNode = context.getNodeDeque().peek();

    if (currentRM instanceof Locatable) {
      NodeId nodeId = new NodeId(currentNode.getNodeId());
      if (nodeId.isArchetypeId()) {
        Archetyped archetyped = new Archetyped();
        archetyped.setArchetypeId(new ArchetypeID(nodeId.getNodeId()));
        archetyped.setRmVersion(RM_VERSION_1_4_0);
        TemplateId templateId = new TemplateId();
        templateId.setValue(context.getTemplateId());
        archetyped.setTemplateId(templateId);
        ((Locatable) currentRM).setArchetypeDetails(archetyped);
        ((Locatable) currentRM).setArchetypeNodeId(nodeId.getNodeId());
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
    if (currentRM instanceof CareEntry) {

      if (Optional.of(currentRM)
          .map(CareEntry.class::cast)
          .map(CareEntry::getProtocol)
          .map(ItemStructure::getItems)
          .filter(this::isNotEmpty)
          .isEmpty()) {

        ((CareEntry) currentRM).setProtocol(null);
      }
    }

    if (currentRM instanceof Observation) {

      if (Optional.of(currentRM)
          .map(Observation.class::cast)
          .map(Observation::getState)
          .map(History::getEvents)
          .filter(this::isNotEmpty)
          .isEmpty()) {

        ((Observation) currentRM).setState(null);
      }

      if (Optional.of(currentRM)
          .map(Observation.class::cast)
          .map(Observation::getData)
          .map(History::getEvents)
          .filter(this::isNotEmpty)
          .isEmpty()) {

        ((Observation) currentRM).setData(null);
      }
    }

    if (currentRM instanceof Action) {

      if (Optional.of(currentRM)
          .map(Action.class::cast)
          .map(Action::getDescription)
          .map(ItemStructure::getItems)
          .filter(this::isNotEmpty)
          .isEmpty()) {

        ((Action) currentRM).setDescription(null);
      }
    }

    if (currentRM instanceof Evaluation) {

      if (Optional.of(currentRM)
          .map(Evaluation.class::cast)
          .map(Evaluation::getData)
          .map(ItemStructure::getItems)
          .filter(this::isNotEmpty)
          .isEmpty()) {

        ((Evaluation) currentRM).setData(null);
      }
    }

    if (currentRM instanceof FeederAuditDetails) {

      if (Optional.of(currentRM)
          .map(FeederAuditDetails.class::cast)
          .map(FeederAuditDetails::getOtherDetails)
          .map(ItemStructure::getItems)
          .filter(this::isNotEmpty)
          .isEmpty()) {

        ((FeederAuditDetails) currentRM).setOtherDetails(null);
      }
    }

    if (currentRM instanceof AdminEntry) {

      if (Optional.of(currentRM)
          .map(AdminEntry.class::cast)
          .map(AdminEntry::getData)
          .map(ItemStructure::getItems)
          .filter(this::isNotEmpty)
          .isEmpty()) {

        ((AdminEntry) currentRM).setData(null);
      }
    }

    if (currentRM instanceof EventContext) {

      if (Optional.of(currentRM)
          .map(EventContext.class::cast)
          .map(EventContext::getOtherContext)
          .map(ItemStructure::getItems)
          .filter(this::isNotEmpty)
          .isEmpty()) {

        ((EventContext) currentRM).setOtherContext(null);
      }
    }

    if (currentRM instanceof InstructionDetails) {

      if (Optional.of(currentRM)
          .map(InstructionDetails.class::cast)
          .map(InstructionDetails::getWfDetails)
          .map(ItemStructure::getItems)
          .filter(this::isNotEmpty)
          .isEmpty()) {

        ((InstructionDetails) currentRM).setWfDetails(null);
      }
    }

    if (currentRM instanceof Event) {

      if (Optional.of(currentRM)
          .map(Event.class::cast)
          .map(Event::getState)
          .map(ItemStructure::getItems)
          .filter(this::isNotEmpty)
          .isEmpty()) {

        ((Event) currentRM).setState(null);
      }

      if (Optional.of(currentRM)
          .map(Event.class::cast)
          .map(Event::getData)
          .map(ItemStructure::getItems)
          .filter(this::isNotEmpty)
          .isEmpty()) {

        ((Event) currentRM).setData(null);
      }
    }

    if (currentRM instanceof ItemSingle && !isNotEmpty(((ItemSingle) currentRM).getItem())) {
      ((ItemSingle) currentRM).setItem(null);
    }

    if (currentRM instanceof ItemList && ((ItemList) currentRM).getItems() != null) {

      ((ItemList) currentRM)
          .setItems(
              ((ItemList) currentRM)
                  .getItems().stream().filter(this::isNotEmpty).collect(Collectors.toList()));
    }
    if (currentRM instanceof ItemTable && ((ItemTable) currentRM).getRows() != null) {

      ((ItemTable) currentRM)
          .setRows(
              ((ItemTable) currentRM)
                  .getRows().stream().filter(this::isNotEmpty).collect(Collectors.toList()));
    }

    if (currentRM instanceof ItemTree && ((ItemTree) currentRM).getItems() != null) {

      ((ItemTree) currentRM)
          .setItems(
              ((ItemTree) currentRM)
                  .getItems().stream().filter(this::isNotEmpty).collect(Collectors.toList()));
    }

    if (currentRM instanceof Cluster && ((Cluster) currentRM).getItems() != null) {

      ((Cluster) currentRM)
          .setItems(
              ((Cluster) currentRM)
                  .getItems().stream().filter(this::isNotEmpty).collect(Collectors.toList()));
    }
  }

  private boolean isNotEmpty(Item item) {

    if (item instanceof Element) {
      return ((Element) item).getValue() != null || ((Element) item).getNullFlavour() != null;
    } else if (item instanceof Cluster) {
      return !CollectionUtils.isEmpty(((Cluster) item).getItems());
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

      if (currentNode.getRmType().equals("ELEMENT")
          && childNode.getRmType().equals("DV_CODED_TEXT")
          && childNode.getInputs().stream().anyMatch(in -> "other".equals(in.getSuffix()))) {
        isChoice = true;
      }

      currentChild =
          (RMObject)
              extractRMChild(
                  context.getRmObjectDeque().peek(), currentNode, childNode, isChoice, i);
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

    postprocessor.forEach(
        p ->
            p.insert(
                context.getRmObjectDeque().peek(),
                context.getDefaultValues(),
                context.getNodeDeque().peek()));
  }
}
