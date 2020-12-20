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

import com.nedap.archie.aom.CComplexObject;
import com.nedap.archie.creation.RMObjectCreator;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.IntervalEvent;
import com.nedap.archie.rm.datastructures.ItemStructure;
import com.nedap.archie.rm.datastructures.PointEvent;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration;
import com.nedap.archie.rminfo.RMAttributeInfo;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ToCompositionWalker<T> extends Walker<T> {

  private static final RMObjectCreator RM_OBJECT_CREATOR =
      new RMObjectCreator(ARCHIE_RM_INFO_LOOKUP);

  private final Logger log = LoggerFactory.getLogger(getClass());
  private final Map<String, List<RMObject>> cloneMap = new HashMap<>();

  @Override
  protected Object extractRMChild(
      RMObject currentRM,
      WebTemplateNode currentNode,
      WebTemplateNode childNode,
      boolean isChoice,
      Integer count) {

    ItemExtractor itemExtractor =
        new ItemExtractor(
                currentRM,
                currentNode,
                childNode,
                isChoice
                    && !List.of("POINT_EVENT", "INTERVAL_EVENT").contains(childNode.getRmType()))
            .invoke();

    Object child = itemExtractor.getChild();
    Object parent = itemExtractor.getParent();
    String attributeName =
        StringUtils.substringBefore(
                StringUtils.removeStart(
                    itemExtractor.getRelativeAql().format(false),
                    itemExtractor.getParentAql().format(false) + "/"),
                "[")
            .replace("/", "");

    if (count != null && child instanceof List) {
      RMObject currentChild;
      List<RMObject> childList;

      if (count == 0) {
        childList = (List<RMObject>) child;
        cloneMap.put(childNode.getAqlPath(), childList);
        childList.forEach(c -> removeProto(count, c, parent, attributeName));
      } else {
        childList = cloneMap.get(childNode.getAqlPath());
        ;
      }

      RMObject proto = childList.get(0);
      RMObject deepClone = deepClone(proto);

      currentChild = deepClone;

      RM_OBJECT_CREATOR.addElementToListOrSetSingleValues(parent, attributeName, deepClone);

      child = currentChild;
    }
    String rmclass = childNode.getRmType();
    if (child == null
        || (child.getClass().equals(PointEvent.class)
            && !childNode.getRmType().equals("POINT_EVENT"))
        || (child.getClass().equals(IntervalEvent.class)
            && !childNode.getRmType().equals("INTERVAL_EVENT"))) {
      CComplexObject elementConstraint = new CComplexObject();
      elementConstraint.setRmTypeName(rmclass);
      Object newChild;
      try {

        newChild = RM_OBJECT_CREATOR.create(elementConstraint);

        if (Event.class.isAssignableFrom(newChild.getClass())) {
          Event newEvent = (Event) newChild;
          Event oldEvent = (Event) child;
          newEvent.setState((ItemStructure) deepClone(oldEvent.getState()));
          newEvent.setData((ItemStructure) deepClone(oldEvent.getData()));
          newEvent.setArchetypeDetails(oldEvent.getArchetypeDetails());
          newEvent.setArchetypeNodeId(oldEvent.getArchetypeNodeId());
          newEvent.setName(oldEvent.getName());
          newEvent.setTime(new DvDateTime());
          if (IntervalEvent.class.isAssignableFrom(newEvent.getClass())) {
            ((IntervalEvent) newEvent).setWidth(new DvDuration());
            ((IntervalEvent<?>) newEvent).setMathFunction(new DvCodedText());
          }
          removeProto(count, child, parent, attributeName);
        }

        RM_OBJECT_CREATOR.addElementToListOrSetSingleValues(
            parent, attributeName, Collections.singletonList(newChild));
      } catch (IllegalArgumentException e) {
        newChild = null;
      }
      child = newChild;
    }

    return child;
  }

  private void removeProto(Integer count, Object child, Object parent, String attributeName) {
    RMAttributeInfo attributeInfo =
        ARCHIE_RM_INFO_LOOKUP.getAttributeInfo(parent.getClass(), attributeName);
    if (attributeInfo.isMultipleValued()) {
      try {
        Object invoke = attributeInfo.getGetMethod().invoke(parent);
        if (ArrayList.class.isAssignableFrom(invoke.getClass())) {
          ((List) invoke).remove(((ArrayList<?>) invoke).lastIndexOf(child));
        }
      } catch (IllegalAccessException | InvocationTargetException e) {
        log.warn(e.getMessage(), e);
      }
    }
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
      currentChild =
          (RMObject)
              extractRMChild(
                  context.getRmObjectDeque().peek(),
                  currentNode,
                  childNode,
                  choices.containsKey(childNode.getAqlPath()),
                  i);
    }

    ImmutablePair<T, RMObject> pair = new ImmutablePair<>(childObject, currentChild);
    return pair;
  }
}
