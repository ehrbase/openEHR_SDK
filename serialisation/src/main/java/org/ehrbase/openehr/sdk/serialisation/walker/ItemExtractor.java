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

import com.nedap.archie.paths.PathSegment;
import com.nedap.archie.query.RMObjectWithPath;
import com.nedap.archie.query.RMPathQuery;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.archetyped.Pathable;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.stream.Stream;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.openehr.sdk.aql.webtemplatepath.AqlPath;
import org.ehrbase.openehr.sdk.util.exception.SdkException;
import org.ehrbase.openehr.sdk.util.rmconstants.RmConstants;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;

public final class ItemExtractor {

    private ItemExtractor() {
        // NOOP
    }

    public static Object extractChild(
            RMObject currentRM, WebTemplateNode currentNode, WebTemplateNode childNode, BooleanSupplier isChoice) {

        if (!(currentRM instanceof Pathable currentPathable)) {
            if (currentRM instanceof DvInterval dvi) {
                return switch (childNode.getAqlPathDto().getLastNode().getName()) {
                    case "upper_included" -> new RmBoolean(dvi.isUpperIncluded());
                    case "lower_included" -> new RmBoolean(dvi.isLowerIncluded());
                    case "lower" -> dvi.getLower();
                    case "upper" -> dvi.getUpper();
                    default -> null;
                };
            }

            throw new SdkException(String.format(
                    "Cannot extract %s from class %s",
                    childNode.getAqlPathDto(), currentRM.getClass().getSimpleName()));
        }

        // EVENT.offset is a calculated and can only be used as template constraint, see ObservationValueInserter
        if (currentRM instanceof Event<?>
                && childNode.getAqlPathDto().getLastNode().getName().equals("offset")) {
            return null;
        }

        AqlPath childPath = currentNode.buildRelativePath(childNode, false);

        // FIXME Performance itemsAtPath
        Stream<?> itemsAtPath = itemsAtPath(childPath, currentPathable);

        String baseName = childPath.getBaseNode().findOtherPredicate(AqlPath.NAME_VALUE_KEY);

        if (StringUtils.isNotBlank(baseName)
                && Locatable.class.isAssignableFrom(Walker.ARCHIE_RM_INFO_LOOKUP.getClass(childNode.getRmType()))) {
            itemsAtPath = itemsAtPath.filter(c -> baseName.equals(((Locatable) c).getNameAsString()));
        }

        if (isChoice.getAsBoolean()) {
            //                itemsAtPath = itemsAtPath.filter(c -> Walker.ARCHIE_RM_INFO_LOOKUP
            //                        .getTypeInfo(c.getClass())
            //                        .getRmName()
            //                        // childNode.getRmType my include Type "DV_INTERVAL<DV_TIME>"
            //                        .equals(StringUtils.substringBefore(childNode.getRmType(), "<")));
            itemsAtPath = itemsAtPath.filter(c -> {
                String childRmType = childNode.getRmType();

                String typeRmName =
                        Walker.ARCHIE_RM_INFO_LOOKUP.getTypeInfo(c.getClass()).getRmName();

                int childLength = childRmType.length();
                int typeLength = typeRmName.length();
                if (childLength == typeLength) {
                    return childRmType.equals(typeRmName);
                } else if (childLength < typeLength) {
                    return false;
                } else if (childRmType.charAt(typeLength) == '<') {
                    return childRmType.startsWith(typeRmName);
                } else {
                    return false;
                }
            });
        }

        Iterator<?> childIt = itemsAtPath.iterator();

        // if rmType not found return null
        if (!childIt.hasNext()) {
            return null;
        }

        if (RMHelper.isMulti(currentNode, childNode)) {
            return IteratorUtils.toList(childIt);
        }
        Object child = childIt.next();
        if (child instanceof Element el && !childNode.getRmType().equals(RmConstants.ELEMENT)) {
            return el.getValue();
        } else {
            return child;
        }
    }

    /**
     * Omits String concatenation + parsing
     */
    public static final class AqlPathRMPathQuery extends RMPathQuery {

        public AqlPathRMPathQuery(AqlPath path) {
            super("");
            List<PathSegment> pathSegments = getPathSegments();
            pathSegments.clear();
            ((ArrayList<?>) pathSegments).ensureCapacity(path.getNodes().size());
            path.getNodes().forEach(node -> pathSegments.add(new PathSegment(node.getName(), node.getAtCode())));
        }
    }

    private static Stream<?> itemsAtPath(AqlPath path, Pathable currentPathable) {
        return new AqlPathRMPathQuery(path)
                .findList(ArchieRMInfoLookup.getInstance(), currentPathable).stream()
                        .map(RMObjectWithPath::getObject);
    }
}
