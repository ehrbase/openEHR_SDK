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

package org.ehrbase.client.std.umarshal;

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
import org.ehrbase.client.reflection.ReflectionHelper;
import org.ehrbase.client.std.marshal.config.DefaultStdConfig;
import org.ehrbase.client.std.marshal.config.StdConfig;
import org.ehrbase.client.std.umarshal.postprocessor.UnmarshalPostprocessor;
import org.ehrbase.client.std.umarshal.rmunmarshaller.DefaultRMUnmarshaller;
import org.ehrbase.client.std.umarshal.rmunmarshaller.RMUnmarshaller;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.ehrbase.serialisation.util.SnakeCase;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.ehrbase.client.introspect.TemplateIntrospect.TERM_DIVIDER;

public class FlatJsonUnmarshaller {

    public static final DefaultStdConfig DEFAULT_STD_CONFIG = new DefaultStdConfig();
    private static final Map<Class<?>, StdConfig> configMap = ReflectionHelper.buildMap(StdConfig.class);
    private static final Map<Class<?>, RMUnmarshaller> UNMARSHALLER_MAP = ReflectionHelper.buildMap(RMUnmarshaller.class);
    private static final Map<Class<?>, UnmarshalPostprocessor> POSTPROCESSOR_MAP = ReflectionHelper.buildMap(UnmarshalPostprocessor.class);
    private static final ObjectMapper OBJECT_MAPPER = JacksonUtil.getObjectMapper();
    public static final OptSkeletonBuilder OPT_SKELETON_BUILDER = new OptSkeletonBuilder();
    public static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();
    private static final RMObjectCreator RM_OBJECT_CREATOR = new RMObjectCreator(ARCHIE_RM_INFO_LOOKUP);
    public static final Normalizer NORMALIZER = new Normalizer();

    private final Logger log = LoggerFactory.getLogger(getClass());

    private Set<String> consumedPath;


    private Map<String, String> currentValues;

    /**
     * Unmarshal flat Json to Composition
     *
     * @param flat                the flat Json
     * @param introspect          the introspect belonging to the template
     * @param operationalTemplate the template of the flat json
     * @return
     */
    public Composition unmarshal(String flat, TemplateIntrospect introspect, OPERATIONALTEMPLATE operationalTemplate) {

        consumedPath = new HashSet<>();

        try {

            currentValues = new HashMap<>();
            for (Iterator<Map.Entry<String, JsonNode>> it = OBJECT_MAPPER.readTree(flat).fields(); it.hasNext(); ) {
                Map.Entry<String, JsonNode> e = it.next();
                currentValues.put(e.getKey(), e.getValue().toString());

            }
            ArchetypeNode root = introspect.getRoot();
            Composition generate = (Composition) OPT_SKELETON_BUILDER.generate(operationalTemplate);

            handleEntityNode(new Context<>(root.getName(), "", generate, "", root, currentValues, null));
            return NORMALIZER.normalize(generate);
        } catch (JsonProcessingException e) {
            throw new ClientException(e.getMessage());
        }


    }

    public Set<String> getUnconsumed() {
        if (currentValues != null && consumedPath != null) {
            HashSet<String> set = new HashSet<>(currentValues.keySet());
            set.removeAll(consumedPath);
            return set;
        } else {
            return Collections.emptySet();
        }
    }

    private void handleEntityNode(Context<EntityNode, Locatable> context) {

        String termLoop = context.getCurrentTerm();

        if (context.getOuterCount() != null) {
            termLoop = termLoop + ":" + context.getOuterCount();
        }

        if (context.getCurrentObject() != null) {

            String finalTermLoop = termLoop;
            context.getNode().getChildren().forEach((p, n) -> mapNode(new Context<>(finalTermLoop, context.getCurrentPath(), context.getCurrentObject(), p, n, context.getCurrentValues(), null)));

            postprocess(termLoop, context.getCurrentValues(), context.getCurrentObject());
        }
    }


    private <T extends RMObject> void postprocess(String term, Map<String, String> values, T locatable) {

        List<UnmarshalPostprocessor<? super T>> postprocessor = Stream.concat(Stream.of(locatable.getClass()), ClassUtils.getAllSuperclasses(locatable.getClass()).stream())
                .map(POSTPROCESSOR_MAP::get)
                .filter(Objects::nonNull)
                .map(p -> (UnmarshalPostprocessor<? super T>) p)
                .collect(Collectors.toList());
        postprocessor.forEach(p -> {
            p.process(term, locatable, values);
            consumedPath.addAll(p.getConsumedPaths());
        });
    }

    private void mapNode(Context<Node, Locatable> context) {

        String pathLoop = context.getCurrentPath() + context.getPathToNode();
        String termLoop = addTerms(context.getCurrentTerm(), context.getNode().getName());


        Map<String, String> subValues = context.getCurrentValues().entrySet().stream()
                .filter(e -> e.getKey().startsWith(termLoop))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (context.getNode() instanceof EntityNode) {
            boolean multi = ((EntityNode) context.getNode()).isMulti();
            Object child = new ItemExtractor(context.getCurrentObject(), context.getPathToNode(), multi).getChild();
            handleMulti(new Context<>(termLoop, pathLoop, child, "", (EntityNode) context.getNode(), subValues, null), this::handleEntityNode);
        } else if (context.getNode() instanceof EndNode) {
            boolean multi = ((EndNode) context.getNode()).isMulti();
            Object child = new ItemExtractor(context.getCurrentObject(), context.getPathToNode(), multi).getChild();
            handleMulti(new Context<>(termLoop, pathLoop, child, "", (EndNode) context.getNode(), subValues, null), this::handleEndNode);
        } else if (context.getNode() instanceof ChoiceNode) {
            boolean multi = ((ChoiceNode) context.getNode()).isMulti();
            Object child = new ItemExtractor(context.getCurrentObject(), context.getPathToNode(), multi).getChild();
            handleMulti(new Context<>(context.getCurrentTerm(), context.getCurrentPath(), child, context.getPathToNode(), (ChoiceNode) context.getNode(), subValues, null), c -> handleChoiceNode(c, context.currentObject));
        }


    }

    private <T extends Node, V> void handleMulti(Context<T, ?> context, Consumer<Context<T, V>> consumer) {

        if (context.getCurrentObject() instanceof List) {
            Integer integer = findCount(context.currentValues, context.currentTerm);
            List<V> childList = (List<V>) context.getCurrentObject();
            V child = childList.get(0);
            RMObject prototype = (RMObject) child;
            for (int i = 0; i <= integer; i++) {
                if (i > 0) {
                    V deepClone = (V) deepClone(prototype);
                    childList.add(deepClone);
                    child = deepClone;
                }
                consumer.accept(new Context<>(context.currentTerm, context.currentPath, child, context.pathToNode, context.node, context.currentValues, i));
            }
        } else {
            consumer.accept(new Context<>(context.currentTerm, context.currentPath, (V) context.getCurrentObject(), context.pathToNode, context.node, context.currentValues, context.outerCount));
        }
    }

    private void handleChoiceNode(Context<ChoiceNode, Object> context, Locatable locatable) {

        if (context.getCurrentValues().isEmpty()) {
            return;
        }

        Node actualNode = context.getNode().getNodes().stream().filter(n -> isMatchingNode(n, context.getCurrentValues(), context.getCurrentTerm(), context.getCurrentPath(), context.getOuterCount())).findFirst().orElseThrow(() -> new ClientException(String.format("No matching Node for currentTerm: %s currentPath: %s", context.getCurrentTerm(), context.getCurrentPath())));
        String pathLoop = context.getCurrentPath() + context.getPathToNode();
        String actualNodeName = actualNode.getName();
        String termLoop = addTerms(context.getCurrentTerm(), actualNodeName);

        if (context.getOuterCount() != null) {
            termLoop = termLoop + ":" + context.getOuterCount();
        }

        ItemExtractor itemExtractor = new ItemExtractor(locatable, context.getPathToNode(), context.getOuterCount() != null);
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
            Event oldEvent = (Event) context.getCurrentObject();
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
                    ((Collection) invoke).remove(context.getCurrentObject());
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                log.warn(e.getMessage(), e);
            }
        }

        RM_OBJECT_CREATOR.addElementToListOrSetSingleValues(parent, childName, Collections.singletonList(newChild));

        if (actualNode instanceof EntityNode) {
            handleEntityNode(new Context(termLoop, pathLoop, newChild, "", actualNode, context.getCurrentValues(), null));
        } else {
            handleEndNode(new Context<>(termLoop, pathLoop, newChild, "", (EndNode) actualNode, context.getCurrentValues(), null));
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


    private void handleEndNode(Context<EndNode, Object> context) {
        String termLoop = context.getCurrentTerm();
        if (context.getOuterCount() != null) {
            termLoop = termLoop + ":" + context.getOuterCount();
        }

        if (context.getCurrentObject() == null) {
            //NOOP
        } else if (context.getCurrentObject() instanceof RMObject) {
            RMUnmarshaller rmUnmarshaller = UNMARSHALLER_MAP.getOrDefault(context.getCurrentObject().getClass(), new DefaultRMUnmarshaller());
            rmUnmarshaller.handle(termLoop, (RMObject) context.getCurrentObject(), context.getCurrentValues());
            consumedPath.addAll(rmUnmarshaller.getConsumedPaths());
            postprocess(termLoop, context.getCurrentValues(), (RMObject) context.getCurrentObject());
        } else {
            log.warn("Unhandled: {}", context.getCurrentTerm());
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

    private <T extends RMObject> T deepClone(RMObject rmObject) {
        CanonicalJson canonicalXML = new CanonicalJson();
        return (T) canonicalXML.unmarshal(canonicalXML.marshal(rmObject), rmObject.getClass());
    }

    private static class Context<T extends Node, V> {
        /**
         * current name based path
         */
        private final String currentPath;
        /**
         * current aql based path
         */
        private final String currentTerm;
        private final String pathToNode;
        private final T node;
        private final Map<String, String> currentValues;
        private final V currentObject;
        private final Integer outerCount;

        private Context(String currentTerm, String currentPath, V currentObject, String pathToNode, T node, Map<String, String> currentValues, Integer outerCount) {
            this.currentPath = currentPath;
            this.currentTerm = currentTerm;
            this.pathToNode = pathToNode;
            this.node = node;
            this.currentValues = currentValues;
            this.currentObject = currentObject;
            this.outerCount = outerCount;
        }

        public String getCurrentPath() {
            return currentPath;
        }

        public String getCurrentTerm() {
            return currentTerm;
        }

        public String getPathToNode() {
            return pathToNode;
        }

        public T getNode() {
            return node;
        }

        public Map<String, String> getCurrentValues() {
            return currentValues;
        }

        public V getCurrentObject() {
            return currentObject;
        }

        public Integer getOuterCount() {
            return outerCount;
        }
    }
}
