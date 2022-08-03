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

import static org.ehrbase.aql.dto.path.predicate.PredicateHelper.ARCHETYPE_NODE_ID;
import static org.ehrbase.aql.dto.path.predicate.PredicateHelper.NAME_VALUE;
import static org.ehrbase.aql.dto.path.predicate.PredicateHelper.remove;

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

    /**
     * Helper Method to convert the dots from the parser to one for the interpreter.
     * @param selectFieldDto
     * @param contains
     * @return
     */
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
        // find all matching Paths in the node and interpret each
        resolve(input.getContainmentPath(), node).stream()
                .map(r -> interpret(input, r))
                .forEach(result::addAll);

        return result;
    }

    private Set<InterpreterOutput> interpret(
            InterpreterInput input, List<Pair<WebTemplateNode, Deque<WebTemplateNode>>> result) {

        InterpreterOutput interpreterOutput = new InterpreterOutput();
        // set the resolved contains
        interpreterOutput.setContain(result.stream()
                .map(Pair::getLeft)
                .map(Interpreter::toContainment)
                .collect(Collectors.toList()));

        // find and set the index of the containment wich correspond to the input Field and the containment to wich to
        // resolve to.
        int containmentIndex = findContainmentIndex(input.getContainmentPath(), input.getContainment());
        interpreterOutput.setRootContainment(findRootIndex(interpreterOutput.getContain(), containmentIndex));

        // resolve path from containment to rootContainment
        WebTemplateNode current = findPathToContainment(result, interpreterOutput, containmentIndex);

        if (input.getPathFromContentment().isEmpty()) {

            return Collections.singleton(interpreterOutput);
        } else {
            LinkedHashSet<InterpreterOutput> inter = new LinkedHashSet<>();
            current.getChildren().stream()
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
    }

    private static Containment toContainment(WebTemplateNode n) {
        Containment containment = new Containment();

        // COMPOSITION has path '\' and thus must be dealt with extra.
        if (MatcherUtil.findTypeName(n.getNodeId()).equals("COMPOSITION")) {

            containment.setArchetypeId(n.getNodeId());
            containment.setType("COMPOSITION");
            containment.setOtherPredicates(new PredicateLogicalAndOperation(new PredicateComparisonOperatorDto(
                    ARCHETYPE_NODE_ID, ConditionComparisonOperatorSymbol.EQ, new SimpleValue(n.getNodeId()))));

        } else {
            AqlNode lastNode = n.getAqlPathDto().getLastNode();

            containment.setArchetypeId(lastNode.getAtCode());
            containment.setType(MatcherUtil.findTypeName(lastNode.getAtCode()));
            containment.setOtherPredicates(lastNode.getOtherPredicate());
        }

        return containment;
    }

    /**
     * Add to <code>interpreterOutput</code> the path from the root containment {@link InterpreterOutput#getRootContainment()} to <code>containment</code>
     * @param result
     * @param interpreterOutput
     * @param containment
     * @return The {@link WebTemplateNode} which correspond to  <code>containment</code>
     */
    private static WebTemplateNode findPathToContainment(
            List<Pair<WebTemplateNode, Deque<WebTemplateNode>>> result,
            InterpreterOutput interpreterOutput,
            int containment) {
        Deque<WebTemplateNode> webTemplateNodes = new ArrayDeque<>();

        int skip = interpreterOutput.getRootContainment() + 1;
        int maxSize = (containment - interpreterOutput.getRootContainment());
        if (maxSize < 0) {
            maxSize = 0;
        }

        result.stream().skip(skip).limit(maxSize).map(Pair::getValue).forEach(webTemplateNodes::addAll);

        WebTemplateNode curent =
                result.get(interpreterOutput.getRootContainment()).getLeft();

        interpreterOutput.getPathFromRootToValue().setNodeList(new ArrayList<>());

        while (!webTemplateNodes.isEmpty()) {
            WebTemplateNode next = webTemplateNodes.poll();
            InterpreterPathNode interpreterPathNode = new InterpreterPathNode();
            interpreterPathNode.setNormalisedNode(next.getAqlPathDto().getLastNode());
            interpreterPathNode.setOtherPredicate(new PredicateLogicalAndOperation());
            interpreterPathNode.setTemplateNode(new SimpleTemplateNode(next));
            interpreterPathNode.setRepresentingObject(true);

            interpreterOutput.getPathFromRootToValue().getNodeList().add(interpreterPathNode);
            curent = next;
        }
        return curent;
    }

    /**
     * Find a paths from <code>node</code> wich match {@link AqlPath} <code>path</code>
     * @param path
     * @param node
     * @return
     */
    protected Set<List<InterpreterPathNode>> findPathToValue(AqlPath path, WebTemplateNode node) {

        if (MatcherUtil.matches(path.getBaseNode(), node)) {

            InterpreterPathNode interpreterPathNode = new InterpreterPathNode();
            interpreterPathNode.setTemplateNode(new SimpleTemplateNode(node));
            interpreterPathNode.setOtherPredicate(
                    remove(path.getBaseNode().getOtherPredicate(), NAME_VALUE, ARCHETYPE_NODE_ID));
            interpreterPathNode.setNormalisedNode(node.getAqlPathDto().getLastNode());
            interpreterPathNode.setRepresentingObject(true);
            if (path.getNodeCount() == 1) {

                return Collections.singleton(new ArrayList<>(List.of(interpreterPathNode)));

            } else {
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
        }

        return Collections.emptySet();
    }

    /**
     * Returns a {@link InterpreterPathNode} if <code>input</code> matches <code>path</code>
     * @param path
     * @param input
     * @return
     */
    protected Optional<InterpreterPathNode> findPathToValue(AqlPath path, WebTemplateInput input) {

        if (MatcherUtil.matches(path.getBaseNode(), input)) {

            InterpreterPathNode interpreterPathNode = new InterpreterPathNode();
            interpreterPathNode.setNormalisedNode(new AqlNode(
                    Optional.ofNullable(input.getSuffix()).orElse("value"), null, new PredicateLogicalAndOperation()));
            interpreterPathNode.setOtherPredicate(new PredicateLogicalAndOperation());
            interpreterPathNode.setTemplateNode(new SimpleTemplateNode(input));
            interpreterPathNode.setRepresentingObject(false);

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

    private int findContainmentIndex(List<Containment> c, Containment con) {
        for (int i = 0; i < c.size(); i++) {
            if (c.get(i).equals(con)) {
                return i;
            }
        }

        throw new SdkException(String.format("No Element in %s  matches %s", c, con));
    }

    /**
     * Finds all direct contain path containing the {@link Containment} with given <code>id</code>.
     * @param id Might be null to indicate to find all direct paths without the condition to match  <code>id</code>.
     * @param containmentDto
     * @return The set of all direct contain path. {@link Pair#getLeft()} is the path and {@link Pair#getRight()} is the containment in the path matching the given <code>id</code>.
     */
    protected Set<Pair<Containment[], Containment>> findContainment(
            Integer id, ContainmentExpresionDto containmentDto) {
        // @Todo support logical operations
        if (containmentDto instanceof ContainmentDto) {
            return findContainmentInContainmentDto(id, (ContainmentDto) containmentDto);
        } else {
            throw new UnsupportedOperationException(
                    String.format("%s not supported", containmentDto.getClass().getName()));
        }
    }

    /**
     * Handle {@link ContainmentDto}.
     * @see Interpreter#findContainmentIndex(List, Containment)
     * @param id
     * @param containmentDto
     * @return
     */
    private Set<Pair<Containment[], Containment>> findContainmentInContainmentDto(
            Integer id, ContainmentDto containmentDto) {

        // We are at the End
        if (containmentDto.getContains() == null) {
            // match return itself
            if (id == null || id.equals(containmentDto.getId())) {
                Set<Pair<Containment[], Containment>> list = new LinkedHashSet<>();
                list.add(Pair.of(
                        new Containment[] {containmentDto.getContainment()},
                        id != null ? containmentDto.getContainment() : null));

                return list;
                // no match return empty
            } else {
                return Collections.emptySortedSet();
            }
            // more to come
        } else {
            // match. Get  all containing sub paths and add them.
            if (id != null && id.equals(containmentDto.getId())) {
                return findContainment(null, containmentDto.getContains()).stream()
                        .map(a -> Pair.of(
                                ArrayUtils.addFirst(a.getLeft(), containmentDto.getContainment()),
                                containmentDto.getContainment()))
                        .collect(Collectors.toCollection(LinkedHashSet::new));
            } else {
                // no match. Get   containing sub paths wich match and add them.
                return findContainment(id, containmentDto.getContains()).stream()
                        .map(a -> Pair.of(
                                ArrayUtils.addFirst(a.getLeft(), containmentDto.getContainment()), a.getRight()))
                        .collect(Collectors.toCollection(LinkedHashSet::new));
            }
        }
    }

    /**
     * Find all paths in the {@link WebTemplateNode} <code>node</code> matching the <code>contains</code>
     * @param contains
     * @param node
     * @return The set of all matching parts. Each {@link Pair} in the list correspond to an Element of <code>contains</code>. where {@link Pair#getLeft()} is the matching {@link WebTemplateNode} and {@link Pair#getRight()} is the path to this node from the previous.
     */
    protected static Set<List<Pair<WebTemplateNode, Deque<WebTemplateNode>>>> resolve(
            List<Containment> contains, WebTemplateNode node) {

        // We are at the End
        if (CollectionUtils.isEmpty(node.getChildren())) {
            // if all have been matched return the node
            if (contains.size() == 1 && MatcherUtil.matches(contains.get(0), node)) {
                ArrayDeque<WebTemplateNode> right = new ArrayDeque<>();
                right.add(node);
                return Collections.singleton(new ArrayList<>(Collections.singletonList(Pair.of(node, right))));
            } else {

                return Collections.emptySet();
            }
            // more to come
        } else {
            // if all have been matched return the node
            if (contains.size() == 1 && MatcherUtil.matches(contains.get(0), node)) {

                ArrayDeque<WebTemplateNode> right = new ArrayDeque<>();
                right.add(node);
                return Collections.singleton(new ArrayList<>(Collections.singletonList(Pair.of(node, right))));
                // if some have been matched but there are  more search for matches of the sub contains
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
                            a.add(0, Pair.of(node, right));
                            return a;
                        })
                        .forEach(result::add);
                return result;
                // continue searching
            } else {

                LinkedHashSet<List<Pair<WebTemplateNode, Deque<WebTemplateNode>>>> result = new LinkedHashSet<>();
                node.getChildren().stream()
                        .map(c -> resolve(contains, c))
                        .flatMap(Set::stream)
                        .map(p -> {
                            p.get(0).getValue().addFirst(node);
                            return p;
                        })
                        .forEach(result::add);
                return result;
            }
        }
    }
}
