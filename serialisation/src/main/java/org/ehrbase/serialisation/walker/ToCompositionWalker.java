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

import static org.ehrbase.util.rmconstants.RmConstants.RM_VERSION_1_4_0;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.*;
import com.nedap.archie.rm.composition.*;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.History;
import com.nedap.archie.rm.datastructures.ItemStructure;
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

public abstract class ToCompositionWalker<T> extends Walker<T> {


  private static final Map<Class<?>, DefaultValueInserter> DEFAULT_VALUE_INSERTER_MAP =
      ReflectionHelper.buildMap(DefaultValueInserter.class);

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Override
  protected void postHandle(Context<T> context) {
    RMObject currentRM = context.getRmObjectDeque().peek();
    WebTemplateNode currentNode = context.getNodeDeque().peek();

    if (currentRM instanceof Locatable) {
      org.ehrbase.webtemplate.parser.NodeId nodeId = new NodeId(currentNode.getNodeId());
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
   * @param currentRM
   */
  private void normalise(RMObject currentRM) {
    if (currentRM instanceof CareEntry) {

      if (Optional.of(currentRM)
              .map(CareEntry.class::cast)
              .map(CareEntry::getProtocol)
              .map(ItemStructure::getItems)
              .filter(CollectionUtils::isNotEmpty)
              .isEmpty()) {

        ((CareEntry) currentRM).setProtocol(null);
      }
    }


    if (currentRM instanceof Observation) {

      if (Optional.of(currentRM)
          .map(Observation.class::cast)
          .map(Observation::getState)
          .map(History::getEvents)
          .filter(CollectionUtils::isNotEmpty)
          .isEmpty()) {

        ((Observation) currentRM).setState(null);
      }

      if (Optional.of(currentRM)
          .map(Observation.class::cast)
          .map(Observation::getData)
          .map(History::getEvents)
          .filter(CollectionUtils::isNotEmpty)
          .isEmpty()) {

        ((Observation) currentRM).setData(null);
      }
    }

    if (currentRM instanceof Action) {

      if (Optional.of(currentRM)
          .map(Action.class::cast)
          .map(Action::getDescription)
          .map(ItemStructure::getItems)
          .filter(CollectionUtils::isNotEmpty)
          .isEmpty()) {

        ((Action) currentRM).setDescription(null);
      }
    }

    if (currentRM instanceof Evaluation) {

      if (Optional.of(currentRM)
          .map(Evaluation.class::cast)
          .map(Evaluation::getData)
          .map(ItemStructure::getItems)
          .filter(CollectionUtils::isNotEmpty)
          .isEmpty()) {

        ((Evaluation) currentRM).setData(null);
      }
    }

    if (currentRM instanceof FeederAuditDetails) {

      if (Optional.of(currentRM)
              .map(FeederAuditDetails.class::cast)
              .map(FeederAuditDetails::getOtherDetails)
              .map(ItemStructure::getItems)
              .filter(CollectionUtils::isNotEmpty)
              .isEmpty()) {

        ((FeederAuditDetails) currentRM).setOtherDetails(null);
      }
    }

    if (currentRM instanceof AdminEntry) {

      if (Optional.of(currentRM)
              .map(AdminEntry.class::cast)
              .map(AdminEntry::getData)
              .map(ItemStructure::getItems)
              .filter(CollectionUtils::isNotEmpty)
              .isEmpty()) {

        ((AdminEntry) currentRM).setData(null);
      }
    }

    if (currentRM instanceof EventContext) {

      if (Optional.of(currentRM)
              .map(EventContext.class::cast)
              .map(EventContext::getOtherContext)
              .map(ItemStructure::getItems)
              .filter(CollectionUtils::isNotEmpty)
              .isEmpty()) {

        ((EventContext) currentRM).setOtherContext(null);
      }
    }

    if (currentRM instanceof InstructionDetails) {

      if (Optional.of(currentRM)
              .map(InstructionDetails.class::cast)
              .map(InstructionDetails::getWfDetails)
              .map(ItemStructure::getItems)
              .filter(CollectionUtils::isNotEmpty)
              .isEmpty()) {

        ((InstructionDetails) currentRM).setWfDetails(null);
      }
    }

    if (currentRM instanceof Event) {

      if (Optional.of(currentRM)
              .map(Event.class::cast)
              .map(Event::getState)
              .map(ItemStructure::getItems)
              .filter(CollectionUtils::isNotEmpty)
              .isEmpty()) {

        ((Event) currentRM).setState(null);
      }

      if (Optional.of(currentRM)
              .map(Event.class::cast)
              .map(Event::getData)
              .map(ItemStructure::getItems)
              .filter(CollectionUtils::isNotEmpty)
              .isEmpty()) {

        ((Event) currentRM).setData(null);
      }
    }
  }

  @Override
  protected Object extractRMChild(
      RMObject currentRM,
      WebTemplateNode currentNode,
      WebTemplateNode childNode,
      boolean isChoice,
      Integer count) {

    Object newChild = WebTemplateSkeletonBuilder.build(childNode, false, Object.class);

    WebTemplateSkeletonBuilder.insert(currentNode,currentRM,childNode, newChild);

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
        p -> p.insert(context.getRmObjectDeque().peek(), context.getDefaultValues()));
  }
}
