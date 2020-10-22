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
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration;
import com.nedap.archie.rminfo.RMAttributeInfo;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class ToCompositionWalker<T> extends Walker<T> {

    private static final RMObjectCreator RM_OBJECT_CREATOR = new RMObjectCreator(ARCHIE_RM_INFO_LOOKUP);

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    protected Object extractRMChild(RMObject currentRM, WebTemplateNode currentNode, WebTemplateNode childNode, boolean isChoice, Integer count) {

        ItemExtractor itemExtractor = new ItemExtractor(currentRM, currentNode, childNode, isChoice).invoke();

        Object child = itemExtractor.getChild();
        Object parent = itemExtractor.getParent();
        String attributeName = StringUtils.substringBefore(StringUtils.removeStart(itemExtractor.getRelativeAql().format(false), itemExtractor.getParentAql().format(false) + "/"), "[").replace("/", "");

        if (count != null && child instanceof List) {
            RMObject currentChild;
            List<RMObject> childList = (List<RMObject>) child;
            if (count > 0) {
                RMObject deepClone = deepClone(childList.get(0));
                childList.add(deepClone);
                currentChild = deepClone;

                RM_OBJECT_CREATOR.addElementToListOrSetSingleValues(parent, attributeName, deepClone);
            } else {
                currentChild = childList.get(0);
            }
            child = currentChild;
        }
        String rmclass = childNode.getRmType();
        if (child == null) {
            CComplexObject elementConstraint = new CComplexObject();
            elementConstraint.setRmTypeName(rmclass);
            Object newChild = RM_OBJECT_CREATOR.create(elementConstraint);
            if (Event.class.isAssignableFrom(newChild.getClass())) {
                Event newEvent = (Event) newChild;
                Event oldEvent = (Event) child;
                newEvent.setState(oldEvent.getState());
                newEvent.setData(oldEvent.getData());
                newEvent.setArchetypeDetails(oldEvent.getArchetypeDetails());
                newEvent.setArchetypeNodeId(oldEvent.getArchetypeNodeId());
                newEvent.setName(oldEvent.getName());
                newEvent.setTime(oldEvent.getTime());
                if (IntervalEvent.class.isAssignableFrom(newEvent.getClass())) {
                    ((IntervalEvent) newEvent).setWidth(new DvDuration());
                    ((IntervalEvent<?>) newEvent).setMathFunction(new DvCodedText());
                }

            }
            RMAttributeInfo attributeInfo = ARCHIE_RM_INFO_LOOKUP.getAttributeInfo(parent.getClass(), attributeName);
            if (attributeInfo.isMultipleValued()) {
                try {
                    Object invoke = attributeInfo.getGetMethod().invoke(parent);
                    if (Collection.class.isAssignableFrom(invoke.getClass())) {
                        ((Collection) invoke).remove(child);
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    log.warn(e.getMessage(), e);
                }
            }

            RM_OBJECT_CREATOR.addElementToListOrSetSingleValues(parent, attributeName, Collections.singletonList(newChild));
            child = newChild;
        }

        return child;
    }
}
