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
package org.ehrbase.serialisation.walker;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.archetyped.Pathable;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.util.exception.SdkException;
import org.ehrbase.util.rmconstants.RmConstants;
import org.ehrbase.webtemplate.model.WebTemplateNode;

public class ItemExtractor {
    private RMObject currentRM;
    private WebTemplateNode currentNode;
    private WebTemplateNode childNode;
    private boolean isChoice;
    private Object child;
    private String parentAql;
    private Object parent;

    public ItemExtractor(RMObject currentRM, WebTemplateNode currentNode, WebTemplateNode childNode, boolean isChoice) {
        this.currentRM = currentRM;
        this.currentNode = currentNode;
        this.childNode = childNode;
        this.isChoice = isChoice;
    }

    public Object getChild() {
        return child;
    }

    public ItemExtractor invoke() {

        AqlPath childPath = currentNode.buildRelativePath(childNode, false);
        if (childPath.getNodeCount() > 1) {
            parentAql = childPath.removeEnd(1).getPath();
        } else {
            parentAql = "/";
        }

        if (currentRM instanceof Pathable) {
            try {
                child = ((Pathable) currentRM).itemsAtPath(childPath.format(false));
                if (child == null || ((List<?>) child).isEmpty()) {
                    child = ((Pathable) currentRM).itemAtPath(childPath.format(false));
                }
            } catch (RuntimeException e) {
                child = null;
            }
            parent = ((Pathable) currentRM).itemAtPath(parentAql);
        } else if (currentRM instanceof DvInterval) {
            switch (childPath.getLastNode().getName()) {
                case "upper_included":
                    child = new RmBoolean(((DvInterval<?>) currentRM).isUpperIncluded());
                    break;
                case "lower_included":
                    child = new RmBoolean(((DvInterval<?>) currentRM).isLowerIncluded());
                    break;
                case "lower":
                    child = ((DvInterval<?>) currentRM).getLower();
                    break;
                case "upper":
                    child = ((DvInterval<?>) currentRM).getUpper();
                    break;
                default:
                    // NOOP
            }
            parent = currentRM;
        } else {
            throw new SdkException(String.format(
                    "Can not extract from class %s", currentRM.getClass().getSimpleName()));
        }

        if (StringUtils.isNotBlank(childPath.getBaseNode().findOtherPredicate(AqlPath.NAME_VALUE_KEY))
                && child instanceof List
                && Locatable.class.isAssignableFrom(Walker.ARCHIE_RM_INFO_LOOKUP.getClass(childNode.getRmType()))) {
            child = ((List<?>) child)
                    .stream()
                            .filter(c -> childPath
                                    .getBaseNode()
                                    .findOtherPredicate(AqlPath.NAME_VALUE_KEY)
                                    .equals(((Locatable) c).getNameAsString()))
                            .collect(Collectors.toList());
            // if name not found return null
            if (((List<?>) child).isEmpty()) {
                child = null;
            }
        }
        if (isChoice && child instanceof List) {
            child = ((List<?>) child)
                    .stream()
                            .filter(c -> Walker.ARCHIE_RM_INFO_LOOKUP
                                    .getTypeInfo(c.getClass())
                                    .getRmName()
                                    // childNode.getRmType my include Type "DV_INTERVAL<DV_TIME>"
                                    .equals(StringUtils.substringBefore(childNode.getRmType(), "<")))
                            .collect(Collectors.toList());
            // if rmType not found return null
            if (((List<?>) child).isEmpty()) {
                child = null;
            }
        }

        if ((childNode.getMax() == 1 || currentNode.getRmType().equals(RmConstants.ELEMENT)) && child instanceof List) {
            child = ((List<?>) child).stream().findFirst().orElse(null);
        }

        if (child instanceof Element && !childNode.getRmType().equals(RmConstants.ELEMENT)) {
            child = ((Element) child).getValue();
        }
        return this;
    }

    public AqlPath getParentAql() {
        return AqlPath.parse(parentAql);
    }

    public Object getParent() {
        return parent;
    }
}
