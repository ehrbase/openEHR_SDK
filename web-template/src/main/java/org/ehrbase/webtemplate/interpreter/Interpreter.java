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

import static org.ehrbase.aql.dto.path.predicate.PredicateHelper.NAME_VALUE;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.aql.dto.containment.ContainmentDto;
import org.ehrbase.aql.dto.containment.ContainmentExpresionDto;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.aql.dto.path.predicate.PredicateLogicalAndOperation;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.templateprovider.TemplateProvider;

/**
 * @author Stefan Spiska
 */
public class Interpreter {

    private final List<String> resolveTo = List.of("COMPOSITION", "OBSERVATION", "EVALUATION", "INSTRUCTION", "Action");

    private final TemplateProvider templateProvider;

    public Interpreter(TemplateProvider templateProvider) {
        this.templateProvider = templateProvider;
    }

    protected Set<AqlPath.AqlNode[]> findContainment(int id, ContainmentExpresionDto containmentDto) {

        if (containmentDto instanceof ContainmentDto) {
            AqlPath.AqlNode node = new AqlPath.AqlNode(
                    ((ContainmentDto) containmentDto).getIdentifier(),
                    ((ContainmentDto) containmentDto).getArchetypeId(),
                    new PredicateLogicalAndOperation());
            if (((ContainmentDto) containmentDto).getContains() == null) {

                if (id == ((ContainmentDto) containmentDto).getId()) {
                    Set<AqlPath.AqlNode[]> list = new LinkedHashSet<>();
                    list.add(new AqlPath.AqlNode[] {node});
                    return list;
                } else {
                    return Collections.emptySortedSet();
                }
            } else {

                if (id == ((ContainmentDto) containmentDto).getId()) {
                    Set<AqlPath.AqlNode[]> list = new LinkedHashSet<>();
                    list.add(new AqlPath.AqlNode[] {node});
                    return list;
                } else {
                    return findContainment(id, ((ContainmentDto) containmentDto).getContains()).stream()
                            .map(a -> ArrayUtils.addFirst(a, node))
                            .collect(Collectors.toCollection(LinkedHashSet::new));
                }
            }
        }

        return Collections.emptySet();
    }

    protected Set<WebTemplateNode[]> resolve(Set<AqlPath.AqlNode[]> contains, String templateId) {

        LinkedHashSet<WebTemplateNode[]> result = new LinkedHashSet<>();

        contains.stream()
                .map(a -> resolve(
                        a,
                        templateProvider
                                .buildIntrospect(templateId)
                                .orElseThrow()
                                .getTree()))
                .forEach(result::addAll);

        return result.stream().map(this::relativ).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private WebTemplateNode[] relativ(WebTemplateNode[] path) {

        for (int i = path.length - 1; i >= 0; --i) {
            if (resolveTo.contains(findTypeName(path[i].getNodeId()))) {
                return ArrayUtils.subarray(path, i, path.length);
            }
        }

        return path;
    }

    private Set<WebTemplateNode[]> resolve(AqlPath.AqlNode[] contains, WebTemplateNode node) {

        if (CollectionUtils.isEmpty(node.getChildren())) {

            if (contains.length == 1 && matches(contains[0], node)) {
                return Collections.singleton(new WebTemplateNode[] {node});
            } else {

                return Collections.emptySet();
            }

        } else {

            if (contains.length == 1 && matches(contains[0], node)) {
                return Collections.singleton(new WebTemplateNode[] {node});
            } else if (matches(contains[0], node)) {

                LinkedHashSet<WebTemplateNode[]> result = new LinkedHashSet<>();

                node.getChildren().stream()
                        .map(c -> resolve(ArrayUtils.remove(contains, 0), c))
                        .flatMap(Set::stream)
                        .map(a -> ArrayUtils.addFirst(a, node))
                        .forEach(result::add);
                return result;
            } else {
                LinkedHashSet<WebTemplateNode[]> result = new LinkedHashSet<>();
                node.getChildren().stream().map(c -> resolve(contains, c)).forEach(result::addAll);

                return result;
            }
        }
    }

    private boolean matches(AqlPath.AqlNode contain, WebTemplateNode node) {

        if (contain.findOtherPredicate(NAME_VALUE) != null
                && !contain.findOtherPredicate(NAME_VALUE).equals(node.getName())) {
            return false;
        }

        String atCode = contain.getAtCode();
        String typeName = findTypeName(atCode);

        boolean isJustType = typeName != null && typeName.equals(atCode);

        if (isJustType) {
            if (!typeName.equals(StringUtils.substringBetween(node.getNodeId(), "openEHR-EHR-", "."))) {
                return false;
            }
        } else {

            if (!atCode.equals(node.getNodeId())) {
                return false;
            }
        }

        return true;
    }

    private String findTypeName(String atCode) {
        String typeName = null;
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
