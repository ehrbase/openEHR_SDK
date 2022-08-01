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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.ehrbase.aql.dto.condition.ConditionComparisonOperatorSymbol;
import org.ehrbase.aql.dto.condition.SimpleValue;
import org.ehrbase.aql.dto.containment.Containment;
import org.ehrbase.aql.dto.containment.ContainmentDto;
import org.ehrbase.aql.dto.containment.ContainmentExpresionDto;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.aql.dto.path.AqlPath.AqlNode;
import org.ehrbase.aql.dto.path.predicate.PredicateComparisonOperatorDto;
import org.ehrbase.aql.dto.path.predicate.PredicateHelper;
import org.ehrbase.aql.dto.path.predicate.PredicateLogicalAndOperation;
import org.ehrbase.aql.dto.select.SelectFieldDto;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.templateprovider.TemplateProvider;

/**
 * @author Stefan Spiska
 */
public class Interpreter {

    private final List<String> resolveTo;

    private final TemplateProvider templateProvider;

    public Interpreter(TemplateProvider templateProvider, List<String> resolveTo) {
        this.templateProvider = templateProvider;
        this.resolveTo = resolveTo;
    }

    protected Set<InterpreterInput> toInterpreterInputSet(
            SelectFieldDto selectFieldDto, ContainmentExpresionDto contains) {

        return findContainment(selectFieldDto.getContainmentId(), contains).stream()
                .map(r -> {
                    InterpreterInput interpreterInput = new InterpreterInput();

                    interpreterInput.setContainment(r.getRight());
                    interpreterInput.setContainmentPath(
                            Arrays.stream(r.getLeft()).collect(Collectors.toList()));
                    interpreterInput.setPathFromContentment(selectFieldDto.getAqlPathDto());

                    return interpreterInput;
                })
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Set<InterpreterOutput> interpret(
            SelectFieldDto selectFieldDto, ContainmentExpresionDto contains, String templateId) {

        return interpret(toInterpreterInputSet(selectFieldDto, contains), templateId);
    }

    public Set<InterpreterOutput> interpret(Set<InterpreterInput> inputs, String templateId) {
        Set<InterpreterOutput> result = new LinkedHashSet<>();

        inputs.stream()
                .map(i -> interpret(
                        i, templateProvider.buildIntrospect(templateId).get().getTree()))
                .forEach(result::addAll);

        return result;
    }

    private Set<InterpreterOutput> interpret(InterpreterInput input, WebTemplateNode node) {
        LinkedHashSet<InterpreterOutput> result = new LinkedHashSet<>();
        resolve(input.getContainmentPath(), input.getContainment(), node).stream()
                .map(r -> interpret(input, r))
                .forEach(result::addAll);

        return result;
    }

    private Set<InterpreterOutput> interpret(
            InterpreterInput input, List<Pair<WebTemplateNode, Deque<WebTemplateNode>>> result) {
        InterpreterOutput interpreterOutput = new InterpreterOutput();

        interpreterOutput.setContain(result.stream()
                .map(n -> {
                    Containment containment = new Containment();
                    AqlNode lastNode = n.getLeft().getAqlPathDto().getLastNode();
                    String atCode = Optional.ofNullable(lastNode.getAtCode())
                            .orElse(n.getLeft().getNodeId());
                    containment.setArchetypeId(atCode);
                    containment.setType(findTypeName(atCode));
                    containment.setOtherPredicates(lastNode.getOtherPredicate());

                    if (lastNode.getAtCode() == null) {
                        PredicateLogicalAndOperation predicateLogicalAndOperation = new PredicateLogicalAndOperation();
                        PredicateComparisonOperatorDto predicateComparisonOperatorDto =
                                new PredicateComparisonOperatorDto();
                        predicateComparisonOperatorDto.setStatement(PredicateHelper.ARCHETYPE_NODE_ID);
                        predicateComparisonOperatorDto.setSymbol(ConditionComparisonOperatorSymbol.EQ);
                        predicateComparisonOperatorDto.setValue(new SimpleValue(atCode));
                        predicateLogicalAndOperation.setValues(new ArrayList<>());
                        predicateLogicalAndOperation.getValues().add(predicateComparisonOperatorDto);
                        containment.setOtherPredicates(predicateLogicalAndOperation);
                    }

                    return containment;
                })
                .collect(Collectors.toList()));
        Collections.reverse(interpreterOutput.getContain());

        int continment = findContainment(input.getContainmentPath(), input.getContainment()) + 1;

        interpreterOutput.setRootContainment(findRoot(interpreterOutput.getContain(), continment - 1));

        Deque<WebTemplateNode> webTemplateNodes = new ArrayDeque<>();

        int skip = result.size() - continment;
        int maxSize = (continment - interpreterOutput.getRootContainment()) - 1;
        if (maxSize < 0) {
            maxSize = 0;
        }

        result.stream().skip(skip).limit(maxSize).map(Pair::getValue).forEach(webTemplateNodes::addAll);

        WebTemplateNode curent = result.get(result.size() - interpreterOutput.getRootContainment() - 1)
                .getLeft();

        interpreterOutput.getPathFromRootToValue().setNodeList(new ArrayList<>());

        while (!webTemplateNodes.isEmpty()) {
            WebTemplateNode next = webTemplateNodes.pollLast();
            InterpreterPathNode interpreterPathNode = new InterpreterPathNode();
            interpreterPathNode.setNormalisedNode(
                    next.getAqlPathDto().removeStart(curent.getAqlPathDto()).getBaseNode());
            interpreterPathNode.setOtherPredicate(new PredicateLogicalAndOperation());
            interpreterPathNode.setTemplateNode(new SimpleTemplateNode(next));

            interpreterOutput.getPathFromRootToValue().getNodeList().add(interpreterPathNode);
            curent = next;
        }

        LinkedHashSet<InterpreterOutput> inter = new LinkedHashSet<>();
        curent.getChildren().stream()
                .map(c -> findPathToValue(input.getPathFromContentment(), c))
                .flatMap(Set::stream)
                .map(l -> {
                    InterpreterOutput output = new InterpreterOutput(interpreterOutput);
                    output.getPathFromRootToValue().getNodeList().addAll(l);
                    return output;
                })
                .forEach(inter::add);

        return inter;
    }

    protected Set<List<InterpreterPathNode>> findPathToValue(AqlPath path, WebTemplateNode node) {

        if (matches(path.getBaseNode(), node)) {

            InterpreterPathNode interpreterPathNode = new InterpreterPathNode();
            interpreterPathNode.setTemplateNode(new SimpleTemplateNode(node));
            interpreterPathNode.setOtherPredicate(new PredicateLogicalAndOperation());
            interpreterPathNode.setNormalisedNode(node.getAqlPathDto().getLastNode());

            if (CollectionUtils.isEmpty(node.getChildren())) {

                Optional<InterpreterPathNode> input = node.getInputs().stream()
                        .map(i -> findPathToValue(path.removeStart(1), i))
                        .flatMap(Optional::stream)
                        .findAny();
                if (input.isEmpty()) {
                    return Collections.emptySet();
                } else {
                    return Collections.singleton(new ArrayList<>(List.of(interpreterPathNode, input.get())));
                }
            } else {

                LinkedHashSet<List<InterpreterPathNode>> result = new LinkedHashSet<>();

                node.getChildren().stream()
                        .map(c -> findPathToValue(path.removeStart(1), c))
                        .flatMap(Set::stream)
                        .forEach(l -> {
                            l.add(0, interpreterPathNode);
                            result.add(l);
                        });

                return result;
            }
        }

        return Collections.emptySet();
    }

    protected Optional<InterpreterPathNode> findPathToValue(AqlPath path, WebTemplateInput input) {

        if (matches(path.getBaseNode(), input)) {

            InterpreterPathNode interpreterPathNode = new InterpreterPathNode();
            interpreterPathNode.setNormalisedNode(new AqlNode(
                    Optional.ofNullable(input.getSuffix()).orElse("value"), null, new PredicateLogicalAndOperation()));
            interpreterPathNode.setOtherPredicate(new PredicateLogicalAndOperation());
            interpreterPathNode.setTemplateNode(new SimpleTemplateNode(input));

            return Optional.of(interpreterPathNode);
        }

        return Optional.empty();
    }

    private boolean matches(AqlNode node, WebTemplateInput input) {
        return node.getName().equals(Optional.ofNullable(input.getSuffix()).orElse("value"));
    }

    private int findRoot(List<Containment> c, int i) {
        for (; i >= 0; i--) {
            if (resolveTo.contains(c.get(i).getType())) {
                return i;
            }
        }

        throw new RuntimeException();
    }

    private int findContainment(List<Containment> c, Containment con) {
        for (int i = c.size() - 1; i >= 0; i--) {
            if (c.get(i).equals(con)) {
                return i;
            }
        }

        throw new RuntimeException();
    }

    protected Set<Pair<Containment[], Containment>> findContainment(
            Integer id, ContainmentExpresionDto containmentDto) {

        if (containmentDto instanceof ContainmentDto) {
            Containment node = new Containment(
                    findTypeName(((ContainmentDto) containmentDto).getArchetypeId()),
                    ((ContainmentDto) containmentDto).getArchetypeId(),
                    new PredicateLogicalAndOperation());
            if (((ContainmentDto) containmentDto).getContains() == null) {

                if (id == null || id.equals(((ContainmentDto) containmentDto).getId())) {
                    Set<Pair<Containment[], Containment>> list = new LinkedHashSet<>();
                    final Containment found;
                    if (id != null) {
                        found = node;
                    } else {
                        found = null;
                    }
                    list.add(Pair.of(new Containment[] {node}, found));

                    return list;
                } else {
                    return Collections.emptySortedSet();
                }
            } else {

                if (id != null && id.equals(((ContainmentDto) containmentDto).getId())) {
                    return findContainment(null, ((ContainmentDto) containmentDto).getContains()).stream()
                            .map(a -> Pair.of(ArrayUtils.addFirst(a.getLeft(), node), node))
                            .collect(Collectors.toCollection(LinkedHashSet::new));
                } else {
                    return findContainment(id, ((ContainmentDto) containmentDto).getContains()).stream()
                            .map(a -> Pair.of(ArrayUtils.addFirst(a.getLeft(), node), a.getRight()))
                            .collect(Collectors.toCollection(LinkedHashSet::new));
                }
            }
        }

        return Collections.emptySet();
    }

    protected Set<List<Pair<WebTemplateNode, Deque<WebTemplateNode>>>> resolve(
            List<Containment> contains, Containment id, WebTemplateNode node) {
        if (CollectionUtils.isEmpty(node.getChildren())) {

            if (contains.size() == 1 && matches(contains.get(0), node)) {
                ArrayDeque<WebTemplateNode> right = new ArrayDeque<>();
                right.add(node);
                return Collections.singleton(new ArrayList<>(Collections.singletonList(Pair.of(node, right))));
            } else {

                return Collections.emptySet();
            }
        } else {
            if (contains.size() == 1 && matches(contains.get(0), node)) {
                ArrayDeque<WebTemplateNode> right = new ArrayDeque<>();
                right.add(node);
                return Collections.singleton(new ArrayList<>(Collections.singletonList(Pair.of(node, right))));
            } else if (matches(contains.get(0), node)) {
                LinkedHashSet<List<Pair<WebTemplateNode, Deque<WebTemplateNode>>>> result = new LinkedHashSet<>();

                node.getChildren().stream()
                        .map(c -> {
                            var nextContains = new ArrayList<>(contains);
                            nextContains.remove(0);
                            return resolve(nextContains, id, c);
                        })
                        .flatMap(Set::stream)
                        .map(a -> {
                            ArrayDeque<WebTemplateNode> right = new ArrayDeque<>();
                            right.add(node);
                            a.add(Pair.of(node, right));
                            return a;
                        })
                        .forEach(r -> result.add(r));
                return result;
            } else {
                LinkedHashSet<List<Pair<WebTemplateNode, Deque<WebTemplateNode>>>> result = new LinkedHashSet<>();
                node.getChildren().stream()
                        .map(c -> resolve(contains, id, c))
                        .flatMap(Set::stream)
                        .map(p -> {
                            p.get(p.size() - 1).getValue().addLast(node);
                            return p;
                        })
                        .forEach(result::add);

                return result;
            }
        }
    }

    private boolean matches(Containment contain, WebTemplateNode node) {

        String atCode = contain.getArchetypeId();
        String typeName = contain.getType();
        boolean isJustType = typeName != null && typeName.equals(atCode);

        if (isJustType) {
            if (!typeName.equals(StringUtils.substringBetween(node.getNodeId(), "openEHR-EHR-", "."))) {
                return false;
            }
        } else {

            if (!Objects.equals(atCode, node.getNodeId())) {
                return false;
            }
        }

        return true;
    }

    private boolean matches(AqlNode contain, WebTemplateNode node) {

        AqlNode nodeAqlNode = node.getAqlPathDto().getLastNode();

        if (!Objects.equals(contain.getName(), nodeAqlNode.getName())) {

            return false;
        }

        String atCode = contain.getAtCode();

        if (atCode != null) {
            String typeName = findTypeName(atCode);
            boolean isJustType = typeName != null && typeName.equals(atCode);

            if (isJustType) {
                if (!typeName.equals(StringUtils.substringBetween(nodeAqlNode.getAtCode(), "openEHR-EHR-", "."))) {
                    return false;
                }
            } else {

                if (!Objects.equals(atCode, nodeAqlNode.getAtCode())) {
                    return false;
                }
            }
        }

        if (contain.findOtherPredicate(NAME_VALUE) != null
                && !contain.findOtherPredicate(NAME_VALUE).equals(node.getName())) {
            return false;
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
