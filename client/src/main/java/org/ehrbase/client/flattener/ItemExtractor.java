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

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.datastructures.Element;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CaseUtils;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.flatpath.FlatPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ItemExtractor {

    private Logger logger = LoggerFactory.getLogger(ItemExtractor.class);

    private boolean multi;

    private RMObject rmObject;
    private String path;
    private String childName;
    private Object child;
    private Object parent;

    public ItemExtractor(RMObject rmObject, String path) {
        this.rmObject = rmObject;
        this.path = path;
        this.multi = false;
        invoke();
    }

    public ItemExtractor(RMObject rmObject, String path, boolean multi) {
        this.rmObject = rmObject;
        this.path = path;
        this.multi = multi;
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
        FlatPath childPath = new FlatPath(pathExtractor.getChildPath());

        String attributeName = pathExtractor.getAttributeName();
        String parentPath = pathExtractor.getParentPath();

        if (StringUtils.isNotBlank(childPath.format(false))) {
            childName = pathExtractor.getChildName();
            //childPath not empty implies  rmObject is Locatable
            if (!Locatable.class.isAssignableFrom(rmObject.getClass())) {
                throw new ClientException(String.format("Locatable not assignable from %s", rmObject.getClass()));
            }
            Locatable locatable = (Locatable) this.rmObject;

            child = locatable.itemsAtPath(childPath.format(false));

            if (child == null || ((List) child).isEmpty()) {
                child = locatable.itemAtPath(childPath.format(false));
            }

            if (StringUtils.isNotBlank(childPath.findOtherPredicate("name/value")) && child instanceof List) {
                child = ((List) child).stream()
                        .filter(c -> childPath.findOtherPredicate("name/value").equals(((Locatable) c).getNameAsString()))
                        .findAny()
                        .orElse(null);

            }

            if (!multi && child instanceof List && ((List) child).size() == 1) {
                child = ((List) child).get(0);
            }


            if (child instanceof Element) {
                child = ((Element) child).getValue();
            }
            parent = locatable.itemAtPath(parentPath);
        } else {
            // already at the right position
            child = rmObject;
        }

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
