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

package org.ehrbase.client.std.marshal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Composition;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.flattener.ItemExtractor;
import org.ehrbase.client.introspect.TemplateIntrospect;
import org.ehrbase.client.introspect.node.ChoiceNode;
import org.ehrbase.client.introspect.node.EndNode;
import org.ehrbase.client.introspect.node.EntityNode;
import org.ehrbase.client.introspect.node.Node;
import org.ehrbase.client.reflection.ReflectionHelper;
import org.ehrbase.client.std.marshal.config.DefaultStdConfig;
import org.ehrbase.client.std.marshal.config.StdConfig;
import org.ehrbase.client.std.marshal.postprocessor.MarshalPostprocessor;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.ehrbase.serialisation.util.SnakeCase;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.ehrbase.client.introspect.TemplateIntrospect.TERM_DIVIDER;

public class FlatJsonMarshaller {

    public static final DefaultStdConfig DEFAULT_STD_CONFIG = new DefaultStdConfig();
    private static final ObjectMapper OBJECT_MAPPER = JacksonUtil.getObjectMapper();
    private static final Map<Class<? extends RMObject>, StdConfig> configMap = ReflectionHelper.buildMap(StdConfig.class);
    private static final Map<Class<? extends RMObject>, MarshalPostprocessor> POSTPROCESSOR_MAP = ReflectionHelper.buildMap(MarshalPostprocessor.class);

    private final TemplateIntrospect introspect;

    public FlatJsonMarshaller(TemplateIntrospect introspect) {
        this.introspect = introspect;
    }

    /**
     * Marshal the composition to flat json
     *
     * @param composition
     * @return
     */
    public String toFlatJson(Composition composition) {

        Map<String, Object> result = new HashMap<>(handleEntityNode(new Context<>(introspect.getRootName(), "", composition, "", introspect.getRoot(), null)));

        try {
            return OBJECT_MAPPER.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    private Map<String, Object> handleEntityNode(Context<EntityNode, Locatable> context) {
        Map<String, Object> result = new HashMap<>();

        final String term;
        if (context.outerCount != null) {
            term = context.getCurrentTerm() + ":" + context.outerCount;
        } else {
            term = context.getCurrentTerm();
        }
        for (Map.Entry<String, Node> nodeEntry : context.getNode().getChildren().entrySet()) {

            result.putAll(handleNode(new Context<>(term, context.getCurrentPath(), context.getCurrentObject(), nodeEntry.getKey(), nodeEntry.getValue(), null)));

        }

        List<MarshalPostprocessor> postprocessor = Stream.concat(Stream.of(context.getCurrentObject().getClass()), ClassUtils.getAllSuperclasses(context.getCurrentObject().getClass()).stream())
                .map(POSTPROCESSOR_MAP::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        postprocessor.forEach(p -> p.process(term, context.getCurrentObject(), result));

        return result;
    }

    private Map<String, Object> handleNode(Context<Node, RMObject> context) {

        Map<String, Object> result = new HashMap<>();
        String pathloop = context.getCurrentPath() + context.getPathToNode();
        String termLoop = StringUtils.isNotBlank(context.getNode().getName()) ? context.getCurrentTerm() + TERM_DIVIDER + StringUtils.stripStart(context.getNode().getName(), TERM_DIVIDER) : context.getCurrentTerm();

        if (context.getOuterCount() != null) {
            termLoop = termLoop + ":" + context.getOuterCount();
        }

        if (context.getNode() instanceof EndNode) {

            Object child = new ItemExtractor(context.getCurrentObject(), context.getPathToNode(), ((EndNode) context.getNode()).isMulti()).getChild();
            result.putAll(handleMulti(new Context<>(termLoop, pathloop, child, "", (EndNode) context.getNode(), null), this::handleEndNode));

        } else if (context.getNode() instanceof EntityNode) {

            Object child = new ItemExtractor(context.getCurrentObject(), context.getPathToNode(), ((EntityNode) context.getNode()).isMulti()).getChild();
            result.putAll(handleMulti(new Context<>(termLoop, pathloop, child, "", (EntityNode) context.getNode(), null), this::handleEntityNode));

        } else if (context.getNode() instanceof ChoiceNode) {

            Object child = new ItemExtractor(context.getCurrentObject(), context.getPathToNode(), ((ChoiceNode) context.getNode()).isMulti()).getChild();
            result.putAll(handleMulti(new Context<>(context.getCurrentTerm(), context.getCurrentPath(), child, "", (ChoiceNode) context.getNode(), null), this::handleChoiceNode));

        }
        return result;
    }

    private <T extends Node, V> Map<String, Object> handleMulti(Context<T, ?> context, Function<Context<T, V>, Map<String, Object>> handler) {

        Object child = context.getCurrentObject();
        if (child instanceof List) {
            Map<String, Object> result = new HashMap<>();
            for (int i = 0; i < ((List<V>) child).size(); i++) {
                result.putAll(handler.apply(new Context<>(context.getCurrentTerm(), context.currentPath, ((List<V>) child).get(i), context.pathToNode, context.node, i)));
            }
            return result;
        } else if (child != null) {
            return handler.apply(new Context<>(context.getCurrentTerm(), context.currentPath, (V) context.currentObject, context.pathToNode, context.node, null));
        }
        return Collections.emptyMap();
    }

    private Map<String, Object> handleChoiceNode(Context<ChoiceNode, Object> context) {

        Node endNode = context.getNode().getNodes().stream()
                .filter(n -> (EndNode.class.isAssignableFrom(n.getClass()) && context.getCurrentObject().getClass().isAssignableFrom(((EndNode) n).getClazz())
                                ||
                                (EntityNode.class.isAssignableFrom(n.getClass()) && new SnakeCase(context.getCurrentObject().getClass().getSimpleName()).camelToUpperSnake().equals(((EntityNode) n).getRmName()))

                        )

                )

                .findAny()
                .orElseThrow(() -> new ClientException(String.format("No choice for %s", context.getCurrentObject().getClass())));
        return new HashMap<>(handleNode(new Context<>(context.getCurrentTerm(), context.getCurrentPath(), (Locatable) context.getCurrentObject(), "", endNode, context.getOuterCount())));
    }

    private Map<String, Object> handleEndNode(Context<EndNode, Object> context) {

        final String term;
        if (context.outerCount != null) {
            term = context.getCurrentTerm() + ":" + context.outerCount;
        } else {
            term = context.getCurrentTerm();
        }

        if (context.getCurrentObject() instanceof RMObject) {
            StdConfig stdConfig = configMap.getOrDefault(context.getCurrentObject().getClass(), DEFAULT_STD_CONFIG);

            return stdConfig.buildChildValues(term, (RMObject) context.getCurrentObject());

        } else if (context.getCurrentObject() != null) {
            return Map.of(term, context.getCurrentObject());
        } else {
            return Collections.emptyMap();
        }
    }


    private static class Context<T extends Node, V> {
        /**
         * current name based path
         */
        private final String currentTerm;
        /**
         * current aql based path
         */
        private final String currentPath;
        private final V currentObject;
        private final String pathToNode;
        private final T node;
        private final Integer outerCount;

        private Context(String currentTerm, String currentPath, V currentObject, String pathToNode, T node, Integer outerCount) {
            this.currentTerm = currentTerm;
            this.currentPath = currentPath;
            this.currentObject = currentObject;
            this.pathToNode = pathToNode;
            this.node = node;
            this.outerCount = outerCount;
        }

        public String getCurrentTerm() {
            return currentTerm;
        }

        public String getCurrentPath() {
            return currentPath;
        }

        public V getCurrentObject() {
            return currentObject;
        }

        public String getPathToNode() {
            return pathToNode;
        }

        public T getNode() {
            return node;
        }

        public Integer getOuterCount() {
            return outerCount;
        }
    }
}
