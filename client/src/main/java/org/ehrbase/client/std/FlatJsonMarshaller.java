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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.Entry;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.flattener.ItemExtractor;
import org.ehrbase.client.introspect.TemplateIntrospect;
import org.ehrbase.client.introspect.node.ChoiceNode;
import org.ehrbase.client.introspect.node.EndNode;
import org.ehrbase.client.introspect.node.EntityNode;
import org.ehrbase.client.introspect.node.Node;
import org.ehrbase.client.std.config.DefaultStdConfig;
import org.ehrbase.client.std.config.StdConfig;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.ehrbase.serialisation.util.SnakeCase;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.ehrbase.client.introspect.TemplateIntrospect.PATH_DIVIDER;
import static org.ehrbase.client.introspect.TemplateIntrospect.TERM_DIVIDER;

public class FlatJsonMarshaller {

    public static final DefaultStdConfig DEFAULT_STD_CONFIG = new DefaultStdConfig();
    private TemplateIntrospect introspect;
    private static final ObjectMapper OBJECT_MAPPER = JacksonUtil.getObjectMapper();

    private final Map<Class, StdConfig<?>> configMap;

    public FlatJsonMarshaller(TemplateIntrospect introspect) {

        this.introspect = introspect;
        this.configMap = buildConfigMap();
    }

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

    public String toFlatJson(Composition composition) {

        Map<String, Object> result = new HashMap<>(handelEntityNode(introspect.getRootName(), "", introspect.getRoot(), composition));

        try {
            return OBJECT_MAPPER.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    Map<String, Object> handelEntityNode(String term, String path, EntityNode archetypeNode, Locatable locatable) {
        Map<String, Object> result = new HashMap<>();

        for (Map.Entry<String, Node> nodeEntry : archetypeNode.getChildren().entrySet()) {

            result.putAll(handleNode(term, path, locatable, nodeEntry.getKey(), nodeEntry.getValue(), null));

        }

        if (locatable instanceof Entry) {
            result.put(term + PATH_DIVIDER + "encoding|code", "UTF-8");
            result.put(term + PATH_DIVIDER + "encoding|terminology", "IANA_character-sets");
        }

        if (locatable instanceof Composition) {
            result.put(term + PATH_DIVIDER + "_uid", locatable.getUid().getValue());
        }
        return result;
    }

    private Map<String, Object> handleNode(String term, String path, Locatable locatable, String childPath, Node childNode, Integer outerCount) {

        Map<String, Object> result = new HashMap<>();
        String pathloop = path + childPath;
        String termLoop = StringUtils.isNotBlank(childNode.getName()) ? term + TERM_DIVIDER + childNode.getName() : term;
        if (outerCount != null) {
            termLoop = termLoop + ":" + outerCount;
        }

        if (childNode instanceof EndNode) {
            Object child = new ItemExtractor(locatable, childPath, ((EndNode) childNode).isMulti()).getChild();

            if (child instanceof List) {
                for (int i = 0; i < ((List) child).size(); i++) {
                    result.putAll(buildChildValues(termLoop + ":" + i, ((List) child).get(i)));
                }
            } else if (child != null) {
                result.putAll(buildChildValues(termLoop, child));
            }
        } else if (childNode instanceof EntityNode) {
            Object child = new ItemExtractor(locatable, childPath, ((EntityNode) childNode).isMulti()).getChild();

            if (child instanceof List) {
                for (int i = 0; i < ((List) child).size(); i++) {
                    result.putAll(handelEntityNode(termLoop + ":" + i, pathloop, (EntityNode) childNode, (Locatable) ((List) child).get(i)));
                }
            } else if (child != null) {
                result.putAll(handelEntityNode(termLoop, pathloop, (EntityNode) childNode, (Locatable) child));
            }
        } else if (childNode instanceof ChoiceNode) {
            Object child = new ItemExtractor(locatable, childPath, ((ChoiceNode) childNode).isMulti()).getChild();
            if (child instanceof List) {
                for (int i = 0; i < ((List) child).size(); i++) {

                    result.putAll(handleChoiceNode((ChoiceNode) childNode, path, term, ((List) child).get(i), i));
                }
            } else if (child != null) {
                result.putAll(handleChoiceNode((ChoiceNode) childNode, path, term, child, null));
            }
        }
        return result;
    }

    private Map<String, Object> handleChoiceNode(ChoiceNode childNode, String pathloop, String termLoop, Object child, Integer count) {

        Node endNode = childNode.getNodes().stream()
                .filter(n -> (EndNode.class.isAssignableFrom(n.getClass()) && child.getClass().isAssignableFrom(((EndNode) n).getClazz())
                                ||
                                (EntityNode.class.isAssignableFrom(n.getClass()) && new SnakeCase(child.getClass().getSimpleName()).camelToUpperSnake().equals(((EntityNode) n).getRmName()))

                        )

                )

                .findAny()
                .orElseThrow(() -> new ClientException(String.format("No choice for %s", child.getClass())));
        return new HashMap<>(handleNode(termLoop, pathloop, (Locatable) child, "/", endNode, count));
    }

    private Map<String, Object> buildChildValues(String termLoop, Object child) {

        StdConfig stdConfig = configMap.getOrDefault(child.getClass(), DEFAULT_STD_CONFIG);

        return stdConfig.buildChildValues(termLoop, (RMObject) child);

    }


}
