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
import com.nedap.archie.rm.archetyped.Archetyped;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.archetyped.TemplateId;
import com.nedap.archie.rm.support.identification.ArchetypeID;
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

    // TODO-527: correct assessment? should archetype_details added to ALL locatables, not just root ones?
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
