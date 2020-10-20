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

import com.nedap.archie.creation.RMObjectCreator;
import com.nedap.archie.rm.RMObject;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.webtemplate.model.WebTemplateNode;

import java.util.List;

public abstract class ToCompositionWalker<T> extends Walker<T> {

    private static final RMObjectCreator RM_OBJECT_CREATOR = new RMObjectCreator(ARCHIE_RM_INFO_LOOKUP);

    @Override
    protected Object extractRMChild(RMObject currentRM, WebTemplateNode currentNode, WebTemplateNode childNode, boolean isChoice, Integer count) {

        ItemExtractor itemExtractor = new ItemExtractor(currentRM, currentNode, childNode, isChoice).invoke();

        Object child = itemExtractor.getChild();

        if (count != null && child instanceof List) {
            RMObject currentChild;
            List<RMObject> childList = (List<RMObject>) child;
            if (count > 0) {
                RMObject deepClone = deepClone(childList.get(0));
                childList.add(deepClone);
                currentChild = deepClone;
                RM_OBJECT_CREATOR.addElementToListOrSetSingleValues(itemExtractor.getParent(), StringUtils.removeStart(itemExtractor.getRelativeAql().format(false), itemExtractor.getParentAql().format(false) + "/"), deepClone);
            } else {
                currentChild = childList.get(0);
            }
            child = currentChild;
        }

        return child;
    }
}
