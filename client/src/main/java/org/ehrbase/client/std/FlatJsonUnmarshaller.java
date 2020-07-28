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

package org.ehrbase.client.std;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Composition;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.client.building.OptSkeletonBuilder;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.flattener.ItemExtractor;
import org.ehrbase.client.introspect.TemplateIntrospect;
import org.ehrbase.client.introspect.node.*;
import org.ehrbase.client.normalizer.Normalizer;
import org.ehrbase.client.std.postprozessor.Postprozessor;
import org.ehrbase.client.std.rmunmarshaller.DefaultRMUnmarshaller;
import org.ehrbase.client.std.rmunmarshaller.RMUnmarshaller;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.ehrbase.client.introspect.TemplateIntrospect.TERM_DIVIDER;

public class FlatJsonUnmarshaller {

    private static final ObjectMapper OBJECT_MAPPER = JacksonUtil.getObjectMapper();
    public static final OptSkeletonBuilder OPT_SKELETON_BUILDER = new OptSkeletonBuilder();
    public static final Normalizer NORMALIZER = new Normalizer();

    private final Logger log = LoggerFactory.getLogger(getClass());

    private Set<String> consumedPath;

    private static final Map<Class, RMUnmarshaller<?>> UNMARSHALLER_MAP = buildUnmarshallerMap();
    private static final Map<Class, Postprozessor<?>> POSTPROZESSOR_MAP = buildPostprozessorMap();
    private Map<String, String> curentValues;

    private static Map<Class, RMUnmarshaller<?>> buildUnmarshallerMap() {
        Reflections reflections = new Reflections(RMUnmarshaller.class.getPackage().getName());
        Set<Class<? extends RMUnmarshaller>> configs = reflections.getSubTypesOf(RMUnmarshaller.class);

        return configs.stream()
                .filter(c -> !Modifier.isAbstract(c.getModifiers()))
                .map(c -> {
                    try {
                        return c.getConstructor().newInstance();
                    } catch (Exception e) {
                        throw new ClientException(e.getMessage(), e);
                    }
                }).collect(Collectors.toMap(RMUnmarshaller::getRMClass, c -> c));
    }

    private static Map<Class, Postprozessor<?>> buildPostprozessorMap() {
        Reflections reflections = new Reflections(Postprozessor.class.getPackage().getName());
        Set<Class<? extends Postprozessor>> configs = reflections.getSubTypesOf(Postprozessor.class);

        return configs.stream()
                .filter(c -> !Modifier.isAbstract(c.getModifiers()))
                .map(c -> {
                    try {
                        return c.getConstructor().newInstance();
                    } catch (Exception e) {
                        throw new ClientException(e.getMessage(), e);
                    }
                }).collect(Collectors.toMap(Postprozessor::getRMClass, c -> c));
    }


    public Composition unmarshal(String flat, TemplateIntrospect introspect, OPERATIONALTEMPLATE operationalTemplate) {

        consumedPath = new HashSet<>();
        Composition generate = (Composition) OPT_SKELETON_BUILDER.generate(operationalTemplate);

        try {

            curentValues = new HashMap<>();
            for (Iterator<Map.Entry<String, JsonNode>> it = OBJECT_MAPPER.readTree(flat).fields(); it.hasNext(); ) {
                Map.Entry<String, JsonNode> e = it.next();
                curentValues.put(e.getKey(), e.getValue().toString());

            }
            ArchetypeNode root = introspect.getRoot();

            handelArchetypeNode(root.getName(), "", root, curentValues, generate);

        } catch (JsonProcessingException e) {
            throw new ClientException(e.getMessage());
        }

        return NORMALIZER.normalize(generate);

    }

    public Set<String> getUnconsumed() {
        if (curentValues != null && consumedPath != null) {
            HashSet<String> set = new HashSet<>(curentValues.keySet());
            set.removeAll(consumedPath);
            return set;
        } else {
            return Collections.emptySet();
        }
    }

    private void handelArchetypeNode(String term, String path, EntityNode root, Map<String, String> values, Locatable locatable) {
        if (locatable != null) {
            root.getChildren().forEach((p, node) -> mapNode(path, term, p, node, values, locatable, null));

            postprocess(term, values, locatable);
        }
    }


    private <T extends RMObject> void postprocess(String term, Map<String, String> values, T locatable) {

        List<Postprozessor<? super T>> postprozessor = Stream.concat(Stream.of(locatable.getClass()), ClassUtils.getAllSuperclasses(locatable.getClass()).stream())
                .map(POSTPROZESSOR_MAP::get)
                .filter(Objects::nonNull)
                .map(p -> (Postprozessor<? super T>) p)
                .collect(Collectors.toList());
        postprozessor.forEach(p -> {
            p.prozess(term, locatable, values);
            consumedPath.addAll(p.getConsumedPaths());
        });
    }

    private void mapNode(String path, String term, String childPath, Node node, Map<String, String> values, Locatable locatable, Integer outerCount) {

        String pathLoop = path + childPath;
        String termLoop = StringUtils.isNotBlank(node.getName()) ? term + TERM_DIVIDER + StringUtils.stripStart(node.getName(), "/") : term;

        if (outerCount != null) {
            termLoop = termLoop + ":" + outerCount;
        }
        Map<String, String> subValues = values.entrySet().stream()
                .filter(e -> e.getKey().startsWith(term))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (node instanceof EntityNode) {
            boolean multi = ((EntityNode) node).isMulti();
            Object child = new ItemExtractor(locatable, childPath, multi).getChild();
            if (child instanceof List) {
                Integer integer = findCount(subValues, termLoop);
                List childList = (List) child;
                child = childList.get(0);
                RMObject prototype = (RMObject) child;
                for (int i = 0; i <= integer; i++) {
                    if (i > 0) {
                        RMObject deepClone = deepClone(prototype);
                        childList.add(deepClone);
                        child = deepClone;
                    }
                    handelArchetypeNode(termLoop + ":" + i, pathLoop, (EntityNode) node, subValues, (Locatable) child);
                }

            } else {
                handelArchetypeNode(termLoop, pathLoop, (EntityNode) node, subValues, (Locatable) child);
            }
        } else if (node instanceof EndNode) {
            boolean multi = ((EndNode) node).isMulti();
            Object child = new ItemExtractor(locatable, childPath, multi).getChild();
            if (child instanceof List) {
                Integer integer = findCount(subValues, termLoop);
                List childList = (List) child;
                child = childList.get(0);
                RMObject prototype = (RMObject) child;
                for (int i = 0; i <= integer; i++) {
                    if (i > 0) {
                        RMObject deepClone = deepClone(prototype);
                        childList.add(deepClone);
                        child = deepClone;
                    }
                    handleEndNode(pathLoop, termLoop + ":" + i, (EndNode) node, child, subValues);
                }
            } else {
                handleEndNode(pathLoop, termLoop, (EndNode) node, child, subValues);
            }
        } else if (node instanceof ChoiceNode) {
            boolean multi = ((ChoiceNode) node).isMulti();
            Object child = new ItemExtractor(locatable, childPath, multi).getChild();

            if (child instanceof List) {
                Integer integer = findCount(subValues, termLoop);
                List childList = (List) child;
                child = childList.get(0);
                RMObject prototype = (RMObject) child;
                for (int i = 0; i <= integer; i++) {
                    if (i > 0) {
                        RMObject deepClone = deepClone(prototype);
                        childList.add(deepClone);
                        child = deepClone;
                    }
                    handleChoiceNode(path, term, childPath, (ChoiceNode) node, child, values, i, locatable);
                }
            } else {
                handleChoiceNode(path, term, childPath, (ChoiceNode) node, child, values, null, locatable);
            }
        }


    }

    private void handleChoiceNode(String path, String term, String childPath, ChoiceNode choiceNode, Object child, Map<String, String> values, Integer outer, Locatable locatable) {
        for (Node node : choiceNode.getNodes()) {
            mapNode(path, term, childPath, node, values, locatable, outer);
        }

    }


    private void handleEndNode(String pathLoop, String termLoop, EndNode node, Object child, Map<String, String> values) {
        if (child == null) {
            //NOOP
        } else if (child instanceof RMObject) {
            RMUnmarshaller rmUnmarshaller = UNMARSHALLER_MAP.getOrDefault(child.getClass(), new DefaultRMUnmarshaller());
            rmUnmarshaller.handle(termLoop, (RMObject) child, values);
            consumedPath.addAll(rmUnmarshaller.getConsumedPaths());
            postprocess(termLoop, values, (RMObject) child);
        } else {
            log.warn("Unhandled: {}", termLoop);
        }
    }


    private Integer findCount(Map<String, String> subValues, String termLoop) {
        return subValues.keySet().stream()
                .map(s -> StringUtils.substringBetween(s, termLoop + ":", TERM_DIVIDER))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .sorted()
                .reduce((first, second) -> second)
                .orElse(0);
    }

    private <T extends RMObject> T deepClone(RMObject rmObjekt) {
        CanonicalJson canonicalXML = new CanonicalJson();
        return (T) canonicalXML.unmarshal(canonicalXML.marshal(rmObjekt), rmObjekt.getClass());
    }
}
