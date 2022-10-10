/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.webtemplate.interpreter;

import java.util.Objects;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.aql.dto.condition.SimpleValue;
import org.ehrbase.aql.dto.containment.Containment;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.aql.dto.path.predicate.PredicateComparisonOperatorDto;
import org.ehrbase.aql.dto.path.predicate.PredicateHelper;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateNode;

/**
 * Helper class containing the matching logic for {@link Interpreter}.
 */
public class MatcherUtil {

    private MatcherUtil() {
        // Util Class
    }

    /**
     * Check if {@link WebTemplateInput#getSuffix()} matches {@link AqlPath.AqlNode#getName()}. 'value' might be left out as suffix
     * @param node
     * @param input
     * @return
     */
    static boolean matches(AqlPath.AqlNode node, WebTemplateInput input) {
        // value might be left out as suffix
        return node.getName().equals(Optional.ofNullable(input.getSuffix()).orElse("value"));
    }

    /**
     * Check whether {@link Containment} matches {@link WebTemplateNode}. that is
     * <ul>
     *     <li>type matches</li>
     *     <li>ArchetypeId matches or is not set in {@link Containment} </li>
     *     <li>name/value matches or is not set in {@link Containment} </li>
     * </ul>
     * @param contain
     * @param node
     * @return
     */
    static boolean matches(Containment contain, WebTemplateNode node) {

        boolean isJustType = contain.getType() != null
                // The Aql parser puts the type in bold fields, if hte aql contains contains only a type
                && (contain.getArchetypeId() == null || contain.getType().equals(contain.getArchetypeId()));

        if (isJustType) {
            if (!contain.getType().equals(node.getRmType())) {
                return false;
            }
        } else {

            if (!Objects.equals(contain.getArchetypeId(), node.getNodeId())) {
                return false;
            }
        }

        Optional<Object> nameValue = PredicateHelper.find(contain.getOtherPredicates(), PredicateHelper.NAME_VALUE)
                .map(PredicateComparisonOperatorDto::getValue)
                .map(SimpleValue.class::cast)
                .map(SimpleValue::getValue);

        return nameValue.isEmpty() || nameValue.get().equals(node.getName());
    }

    /**
     *
     * Check whether {@link AqlPath.AqlNode} matches {@link WebTemplateNode}. that is
     * <ul>
     *   <li>path name matches</li>
     *   <li>atCode matches or is not set in {@link AqlPath.AqlNode} </li>
     *   <li>name/value matches or is not set in {@link AqlPath.AqlNode} </li>
     * </ul>
     * @param path
     * @param node
     * @return
     */
    static boolean matches(AqlPath.AqlNode path, WebTemplateNode node) {

        AqlPath.AqlNode nodeAqlNode = node.getAqlPathDto().getLastNode();

        if (!Objects.equals(path.getName(), nodeAqlNode.getName())) {

            return false;
        }

        String atCode = path.getAtCode();

        if (atCode != null && !atCode.equals(nodeAqlNode.getAtCode())) {
            return false;
        }

        return path.findNameValue() == null
                || path.findOtherPredicate(PredicateHelper.NAME_VALUE).equals(node.getName());
    }

    /**
     * extract the type from a nodeId
     * @param atCode
     * @return
     */
    static String findTypeName(String atCode) {
        String typeName = null;

        if (atCode == null) {
            return "";
        }

        if (atCode.contains("openEHR-EHR-")) {

            typeName = StringUtils.substringBetween(atCode, "openEHR-EHR-", ".");
        } else if (atCode.startsWith("at")) {
            typeName = null;
        } else {
            typeName = atCode;
        }
        return typeName;
    }
}
