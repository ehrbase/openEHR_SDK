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

package org.ehrbase.client.std.umarschal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.aom.CComplexObject;
import com.nedap.archie.creation.RMObjectCreator;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.IntervalEvent;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMAttributeInfo;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.client.building.OptSkeletonBuilder;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.flattener.ItemExtractor;
import org.ehrbase.client.introspect.TemplateIntrospect;
import org.ehrbase.client.introspect.node.*;
import org.ehrbase.client.normalizer.Normalizer;
import org.ehrbase.client.std.marshal.config.DefaultStdConfig;
import org.ehrbase.client.std.marshal.config.StdConfig;
import org.ehrbase.client.std.umarschal.postprozessor.Postprozessor;
import org.ehrbase.client.std.umarschal.rmunmarshaller.DefaultRMUnmarshaller;
import org.ehrbase.client.std.umarschal.rmunmarshaller.RMUnmarshaller;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.ehrbase.serialisation.util.SnakeCase;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.ehrbase.client.introspect.TemplateIntrospect.TERM_DIVIDER;

public class FlatJsonUnmarshaller {

    public static final DefaultStdConfig DEFAULT_STD_CONFIG = new DefaultStdConfig();
    private static final Map<Class, StdConfig<?>> configMap = buildConfigMap();
    private static final ObjectMapper OBJECT_MAPPER = JacksonUtil.getObjectMapper();
    public static final OptSkeletonBuilder OPT_SKELETON_BUILDER = new OptSkeletonBuilder();
    public static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();
    private static final RMObjectCreator RM_OBJECT_CREATOR = new RMObjectCreator(ARCHIE_RM_INFO_LOOKUP);
    public static final Normalizer NORMALIZER = new Normalizer();

    private final Logger log = LoggerFactory.getLogger(getClass());

    private Set<String> consumedPath;

    private static final Map<Class, RMUnmarshaller<?>> UNMARSHALLER_MAP = buildUnmarshallerMap();
    private static final Map<Class, Postprozessor<?>> POSTPROCESSOR_MAP = buildPostprozessorMap();
    private Map<String, String> curentValues;


    public static Map<Class, StdConfig<?>> buildConfigMap() {

        Reflections reflections = new Reflections(StdConfig.class.getPackage().getName());
        Set<Class<? extends StdConfig>> configs = reflections.getSubTypesOf(StdConfig.class);

        return configs.stream()
                .filter(c -> !Modifier.isAbstract(c.getModifiers()))
                .map(c -> {
                    try {
                        return c.getConstructor().newInstance();
                    } catch (Exception e) {
                        throw new ClientException(e.getMessage(), e);
                    }
                }).collect(Collectors.toMap(StdConfig::getRMClass, c -> c));
    }

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
            root.getChildren().forEach((p, node) -> mapNode(new Context<>(path, term, p, node, values, locatable, null)));

            postprocess(term, values, locatable);
        }
    }


    private <T extends RMObject> void postprocess(String term, Map<String, String> values, T locatable) {

        List<Postprozessor<? super T>> postprocessor = Stream.concat(Stream.of(locatable.getClass()), ClassUtils.getAllSuperclasses(locatable.getClass()).stream())
                .map(POSTPROCESSOR_MAP::get)
                .filter(Objects::nonNull)
                .map(p -> (Postprozessor<? super T>) p)
                .collect(Collectors.toList());
        postprocessor.forEach(p -> {
            p.prozess(term, locatable, values);
            consumedPath.addAll(p.getConsumedPaths());
        });
    }

    private void mapNode(Context<Node, Locatable> context) {

        String pathLoop = context.getPath() + context.getChildPath();
        String termLoop = addTerms(context.getTerm(), context.getNode().getName());

        if (context.getOuterCount() != null) {
            termLoop = termLoop + ":" + context.getOuterCount();
        }
        Map<String, String> subValues = context.getValues().entrySet().stream()
                .filter(e -> e.getKey().startsWith(context.getTerm()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (context.getNode() instanceof EntityNode) {
            boolean multi = ((EntityNode) context.getNode()).isMulti();
            Object child = new ItemExtractor(context.getLocatable(), context.getChildPath(), multi).getChild();
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
                    handelArchetypeNode(termLoop + ":" + i, pathLoop, (EntityNode) context.getNode(), subValues, (Locatable) child);
                }

            } else {
                handelArchetypeNode(termLoop, pathLoop, (EntityNode) context.getNode(), subValues, (Locatable) child);
            }
        } else if (context.getNode() instanceof EndNode) {
            boolean multi = ((EndNode) context.getNode()).isMulti();
            Object child = new ItemExtractor(context.getLocatable(), context.getChildPath(), multi).getChild();
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
                    handleEndNode(pathLoop, termLoop + ":" + i, (EndNode) context.getNode(), child, subValues);
                }
            } else {
                handleEndNode(pathLoop, termLoop, (EndNode) context.getNode(), child, subValues);
            }
        } else if (context.getNode() instanceof ChoiceNode) {
            boolean multi = ((ChoiceNode) context.getNode()).isMulti();
            Object child = new ItemExtractor(context.getLocatable(), context.getChildPath(), multi).getChild();

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
                    handleChoiceNode(context.getPath(), context.getTerm(), context.getChildPath(), (ChoiceNode) context.getNode(), child, context.getValues(), i, context.getLocatable());
                }
            } else {
                handleChoiceNode(context.getPath(), context.getTerm(), context.getChildPath(), (ChoiceNode) context.getNode(), child, context.getValues(), null, context.getLocatable());
            }
        }


    }

    private void handleChoiceNode(String path, String term, String childPath, ChoiceNode choiceNode, Object child, Map<String, String> values, Integer outer, Locatable locatable) {

        if (values.isEmpty()) {
            return;
        }

        Node actualNode = choiceNode.getNodes().stream().filter(n -> isMatchingNode(n, values, term, path, outer)).findFirst().orElseThrow(() -> new ClientException(String.format("No matching Node for term: %s path: %s", term, path)));
        String pathLoop = path + childPath;
        String actualNodeName = actualNode.getName();
        String termLoop = addTerms(term, actualNodeName);

        if (outer != null) {
            termLoop = termLoop + ":" + outer;
        }

        ItemExtractor itemExtractor = new ItemExtractor(locatable, childPath, outer != null);
        String childName = itemExtractor.getChildName();
        Object parent = itemExtractor.getParent();
        final String rmclass;
        if (actualNode instanceof EntityNode) {
            rmclass = ((EntityNode) actualNode).getRmName();
        } else {
            rmclass = new SnakeCase(((EndNode) actualNode).getClazz().getSimpleName()).camelToUpperSnake();
        }
        CComplexObject elementConstraint = new CComplexObject();
        elementConstraint.setRmTypeName(rmclass);
        Object newChild = RM_OBJECT_CREATOR.create(elementConstraint);
        if (Event.class.isAssignableFrom(newChild.getClass())) {
            Event newEvent = (Event) newChild;
            Event oldEvent = (Event) child;
            newEvent.setState(oldEvent.getState());
            newEvent.setData(oldEvent.getData());
            newEvent.setArchetypeDetails(oldEvent.getArchetypeDetails());
            newEvent.setArchetypeNodeId(oldEvent.getArchetypeNodeId());
            newEvent.setName(oldEvent.getName());
            newEvent.setTime(oldEvent.getTime());
            if (IntervalEvent.class.isAssignableFrom(newEvent.getClass())) {
                ((IntervalEvent) newEvent).setWidth(new DvDuration());
                ((IntervalEvent<?>) newEvent).setMathFunction(new DvCodedText());
            }

        }
        RMAttributeInfo attributeInfo = ARCHIE_RM_INFO_LOOKUP.getAttributeInfo(parent.getClass(), childName);
        if (attributeInfo.isMultipleValued()) {
            try {
                Object invoke = attributeInfo.getGetMethod().invoke(parent);
                if (Collection.class.isAssignableFrom(invoke.getClass())) {
                    ((Collection) invoke).remove(child);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                log.warn(e.getMessage(), e);
            }
        }

        RM_OBJECT_CREATOR.addElementToListOrSetSingleValues(parent, childName, Collections.singletonList(newChild));

        if (actualNode instanceof EntityNode) {
            handelArchetypeNode(termLoop, pathLoop, (EntityNode) actualNode, values, (Locatable) newChild);
        } else {
            handleEndNode(pathLoop, termLoop, (EndNode) actualNode, newChild, values);
        }

    }

    private String addTerms(String term, String actualNodeName) {
        return StringUtils.isNotBlank(actualNodeName) ? term + TERM_DIVIDER + StringUtils.stripStart(actualNodeName, "/") : term;
    }


    private boolean isMatchingNode(Node node, Map<String, String> values, String term, String path, Integer outerCount) {

        String termLoop = addTerms(term, node.getName());

        if (outerCount != null) {
            termLoop = termLoop + ":" + outerCount;
        }
        final String termLoop2 = termLoop;

        Map<String, String> subValues = values.entrySet().stream()
                .filter(e -> e.getKey().startsWith(termLoop2))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (node instanceof EntityNode) {

            for (Node n : ((EntityNode) node).getChildren().values()) {
                String prefix = addTerms(termLoop, n.getName());
                subValues = subValues.entrySet().stream()
                        .filter(e -> !e.getKey().startsWith(prefix))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            }

            return subValues.isEmpty();
        } else {

            StdConfig<?> stdConfig = configMap.getOrDefault(((EndNode) node).getClazz(), DEFAULT_STD_CONFIG);
            return stdConfig.valueCount(((EndNode) node).getClazz()).contains(subValues.size());
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

    private static class Context<T extends Node, V> {
        private final String path;
        private final String term;
        private final String childPath;
        private final T node;
        private final Map<String, String> values;
        private final V locatable;
        private final Integer outerCount;

        private Context(String path, String term, String childPath, T node, Map<String, String> values, V locatable, Integer outerCount) {
            this.path = path;
            this.term = term;
            this.childPath = childPath;
            this.node = node;
            this.values = values;
            this.locatable = locatable;
            this.outerCount = outerCount;
        }

        public String getPath() {
            return path;
        }

        public String getTerm() {
            return term;
        }

        public String getChildPath() {
            return childPath;
        }

        public T getNode() {
            return node;
        }

        public Map<String, String> getValues() {
            return values;
        }

        public V getLocatable() {
            return locatable;
        }

        public Integer getOuterCount() {
            return outerCount;
        }
    }
}
