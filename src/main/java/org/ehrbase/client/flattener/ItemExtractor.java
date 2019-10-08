/*
 *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  This file is part of Project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.client.flattener;

import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.datastructures.Element;
import org.apache.commons.text.CaseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

class ItemExtractor {

    private Logger logger = LoggerFactory.getLogger(ItemExtractor.class);

    private Locatable locatable;
    private String path;
    private String childName;
    private Object child;
    private Object parent;

    public ItemExtractor(Locatable locatable, String path) {
        this.locatable = locatable;
        this.path = path;
        invoke();
    }

    public String getChildName() {
        return childName;
    }

    public Object getChild() {
        return child;
    }

    public Object getParent() {
        return parent;
    }

    private ItemExtractor invoke() {
        PathExtractor pathExtractor = new PathExtractor(path);
        String childPath = pathExtractor.getChildPath();
        String attributeName = pathExtractor.getAttributeName();
        String parentPath = pathExtractor.getParentPath();
        childName = pathExtractor.getChildName();
        child = locatable.itemsAtPath(childPath);

        if (child == null || ((List) child).isEmpty()) {
            child = locatable.itemAtPath(childPath);
        }

        if (child instanceof List && ((List) child).size() == 1) {
            child = ((List) child).get(0);
        }

        if (child instanceof Element) {
            child = ((Element) child).getValue();
        }
        parent = locatable.itemAtPath(parentPath);

        if (attributeName != null && child != null) {
            try {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(CaseUtils.toCamelCase(attributeName, false, '_'), child.getClass(), "is" + Character.toUpperCase(attributeName.charAt(0)) + attributeName.substring(1), null);
                parent = child;
                childName = attributeName;
                child = propertyDescriptor.getReadMethod().invoke(child);
            } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
                logger.warn(e.getMessage(), e);
            }
        }
        return this;
    }
}
