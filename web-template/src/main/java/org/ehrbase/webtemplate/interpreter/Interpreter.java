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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
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
import org.ehrbase.util.exception.SdkException;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.templateprovider.TemplateProvider;

/**
 * @author Stefan Spiska
 */
public class Interpreter {

    private final List<String> resolveTo;

    private final TemplateProvider templateProvider;

    /**
     *
     * @param templateProvider Used {@link TemplateProvider}
     * @param resolveTo To wich {@link com.nedap.archie.rm.RMObject} should the part be resolved
     */
    public Interpreter(TemplateProvider templateProvider, List<String> resolveTo) {
        this.templateProvider = templateProvider;
        this.resolveTo = resolveTo;
    }

    private Set<InterpreterInput> toInterpreterInputSet(
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

    /**
     * Called from {@link @see Interpreter#interpret(InterpreterInput, WebTemplateNode) }. Can be overwritten to take advantage of caching.
     * @see Interpreter#interpret(InterpreterInput, WebTemplateNode)
     * @param inputs
     * @param templateId
     * @return
     */
    protected Set<InterpreterOutput> interpret(Set<InterpreterInput> inputs, String templateId) {
        Set<InterpreterOutput> result = new LinkedHashSet<>();

        inputs.stream()
                .map(i -> interpret(
                        i, templateProvider.buildIntrospect(templateId).get().getTree()))
                .forEach(result::addAll);

        return result;
    }

    private Set<InterpreterOutput> interpret(InterpreterInput input, WebTemplateNode node) {
        LinkedHashSet<InterpreterOutput> result = new LinkedHashSet<>();
        resolve(input.getContainmentPath(), node).stream()
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
                    containment.setType(MatcherUtil.findTypeName(atCode));
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

        interpreterOutput.setRootContainment(findRootIndex(interpreterOutput.getContain(), continment - 1));

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

        if (MatcherUtil.matches(path.getBaseNode(), node)) {

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

        if (MatcherUtil.matches(path.getBaseNode(), input)) {

            InterpreterPathNode interpreterPathNode = new InterpreterPathNode();
            interpreterPathNode.setNormalisedNode(new AqlNode(
                    Optional.ofNullable(input.getSuffix()).orElse("value"), null, new PredicateLogicalAndOperation()));
            interpreterPathNode.setOtherPredicate(new PredicateLogicalAndOperation());
            interpreterPathNode.setTemplateNode(new SimpleTemplateNode(input));

            return Optional.of(interpreterPathNode);
        }

        return Optional.empty();
    }

    private int findRootIndex(List<Containment> c, int i) {
        for (; i >= 0; i--) {
            if (resolveTo.contains(c.get(i).getType())) {
                return i;
            }
        }

        throw new SdkException(String.format("No Element in %s  matches %s", c, resolveTo));
    }

    private int findContainment(List<Containment> c, Containment con) {
        for (int i = c.size() - 1; i >= 0; i--) {
            if (c.get(i).equals(con)) {
                return i;
            }
        }

        throw new SdkException(String.format("No Element in %s  matches %s", c, con));
    }

    /**
     * Finds all direct contain path containing the {@link Containment} with given id.
     * @param id
     * @param containmentDto
     * @return
     */
    protected Set<Pair<Containment[], Containment>> findContainment(
            Integer id, ContainmentExpresionDto containmentDto) {

        if (containmentDto instanceof ContainmentDto) {
            return findContainmentInContainmentDto(id, (ContainmentDto) containmentDto);
        } else {
            throw new UnsupportedOperationException(
                    String.format("%s not supported", containmentDto.getClass().getName()));
        }
    }

    private Set<Pair<Containment[], Containment>> findContainmentInContainmentDto(
            Integer id, ContainmentDto containmentDto) {

        if (containmentDto.getContains() == null) {

            if (id == null || id.equals(containmentDto.getId())) {

                Set<Pair<Containment[], Containment>> list = new LinkedHashSet<>();
                list.add(Pair.of(
                        new Containment[] {containmentDto.getContainment()},
                        id != null ? containmentDto.getContainment() : null));

                return list;
            } else {
                return Collections.emptySortedSet();
            }
        } else {

            if (id != null && id.equals(containmentDto.getId())) {
                return findContainment(null, containmentDto.getContains()).stream()
                        .map(a -> Pair.of(
                                ArrayUtils.addFirst(a.getLeft(), containmentDto.getContainment()),
                                containmentDto.getContainment()))
                        .collect(Collectors.toCollection(LinkedHashSet::new));
            } else {
                return findContainment(id, containmentDto.getContains()).stream()
                        .map(a -> Pair.of(
                                ArrayUtils.addFirst(a.getLeft(), containmentDto.getContainment()), a.getRight()))
                        .collect(Collectors.toCollection(LinkedHashSet::new));
            }
        }
    }

    protected Set<List<Pair<WebTemplateNode, Deque<WebTemplateNode>>>> resolve(
            List<Containment> contains, WebTemplateNode node) {

        if (CollectionUtils.isEmpty(node.getChildren())) {

            if (contains.size() == 1 && MatcherUtil.matches(contains.get(0), node)) {
                ArrayDeque<WebTemplateNode> right = new ArrayDeque<>();
                right.add(node);
                return Collections.singleton(new ArrayList<>(Collections.singletonList(Pair.of(node, right))));
            } else {

                return Collections.emptySet();
            }
        } else {
            if (contains.size() == 1 && MatcherUtil.matches(contains.get(0), node)) {
                ArrayDeque<WebTemplateNode> right = new ArrayDeque<>();
                right.add(node);
                return Collections.singleton(new ArrayList<>(Collections.singletonList(Pair.of(node, right))));
            } else if (MatcherUtil.matches(contains.get(0), node)) {
                LinkedHashSet<List<Pair<WebTemplateNode, Deque<WebTemplateNode>>>> result = new LinkedHashSet<>();

                node.getChildren().stream()
                        .map(c -> {
                            var nextContains = new ArrayList<>(contains);
                            nextContains.remove(0);
                            return resolve(nextContains, c);
                        })
                        .flatMap(Set::stream)
                        .map(a -> {
                            ArrayDeque<WebTemplateNode> right = new ArrayDeque<>();
                            right.add(node);
                            a.add(Pair.of(node, right));
                            return a;
                        })
                        .forEach(result::add);
                return result;
            } else {
                LinkedHashSet<List<Pair<WebTemplateNode, Deque<WebTemplateNode>>>> result = new LinkedHashSet<>();
                node.getChildren().stream()
                        .map(c -> resolve(contains, c))
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
}
