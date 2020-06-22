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
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.config.RmClassGeneratorConfig;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.flattener.ItemExtractor;
import org.ehrbase.client.introspect.TemplateIntrospect;
import org.ehrbase.client.introspect.node.ChoiceNode;
import org.ehrbase.client.introspect.node.EndNode;
import org.ehrbase.client.introspect.node.EntityNode;
import org.ehrbase.client.introspect.node.Node;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.ehrbase.serialisation.util.SnakeCase;
import org.reflections.Reflections;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.ehrbase.client.introspect.TemplateIntrospect.TERM_DIVIDER;

public class FlatJson {

    private TemplateIntrospect introspect;
    private static final ObjectMapper OBJECT_MAPPER = JacksonUtil.getObjectMapper();

    private final Map<Class, RmClassGeneratorConfig> configMap;

    public FlatJson(TemplateIntrospect introspect) {

        this.introspect = introspect;
        this.configMap = buildConfigMap();
    }

    private Map<Class, RmClassGeneratorConfig> buildConfigMap() {
        Reflections reflections = new Reflections(RmClassGeneratorConfig.class.getPackage().getName());
        Set<Class<? extends RmClassGeneratorConfig>> configs = reflections.getSubTypesOf(RmClassGeneratorConfig.class);

        return configs.stream().map(c -> {
            try {
                return c.getConstructor().newInstance();
            } catch (Exception e) {
                throw new ClientException(e.getMessage(), e);
            }
        }).collect(Collectors.toMap(RmClassGeneratorConfig::getRMClass, c -> c));
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

            result.putAll(handleNode(term, path, locatable, nodeEntry.getKey(), nodeEntry.getValue()));

        }
        return result;
    }

    private Map<String, Object> handleNode(String term, String path, Locatable locatable, String childPath, Node childNode) {

        Map<String, Object> result = new HashMap<>();
        String pathloop = path + childPath;
        String termLoop = term + TERM_DIVIDER + childNode.getName();
        if (childNode instanceof EndNode) {
            Object child = new ItemExtractor(locatable, childPath, ((EndNode) childNode).isMulti()).getChild();

            if (child instanceof List) {
                for (int i = 0; i < ((List) child).size(); i++) {
                    result.putAll(addSingleValue(termLoop + ":" + i, ((List) child).get(i)));
                }
            } else if (child != null) {
                result.putAll(addSingleValue(termLoop, child));
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
                    //TODO set i
                    result.putAll(handleChoiceNode((ChoiceNode) childNode, path, term, ((List) child).get(i)));
                }
            } else if (child != null) {
                result.putAll(handleChoiceNode((ChoiceNode) childNode, path, term, child));
            }
        }
        return result;
    }

    private Map<String, Object> handleChoiceNode(ChoiceNode childNode, String pathloop, String termLoop, Object child) {

        Node endNode = childNode.getNodes().stream()
                .filter(n -> (EndNode.class.isAssignableFrom(n.getClass()) && child.getClass().isAssignableFrom(((EndNode) n).getClazz())
                                ||
                                (EntityNode.class.isAssignableFrom(n.getClass()) && new SnakeCase(child.getClass().getSimpleName()).camelToUpperSnake().equals(((EntityNode) n).getRmName()))

                        )

                )

                .findAny()
                .orElseThrow(() -> new ClientException(String.format("No choice for %s", child.getClass())));
        return new HashMap<>(handleNode(termLoop, pathloop, (Locatable) child, "/", endNode));
    }

    private Map<String, Object> addSingleValue(String termLoop, Object child) {
        Map<String, Object> result = new HashMap<>();
        RmClassGeneratorConfig rmClassGeneratorConfig = configMap.get(child.getClass());
        if (child instanceof CodePhrase) {


            addValue(result, termLoop, "code", ((CodePhrase) child).getCodeString());
            addValue(result, termLoop, "terminology", ((CodePhrase) child).getTerminologyId().getValue());


        } else if (child instanceof DvCodedText) {

            addValue(result, termLoop, "code", ((DvCodedText) child).getDefiningCode().getCodeString());
            addValue(result, termLoop, "terminology", ((DvCodedText) child).getDefiningCode().getTerminologyId().getValue());
            addValue(result, termLoop, "value", ((DvCodedText) child).getValue());

        } else if (child instanceof PartyIdentified) {

            addValue(result, termLoop, "name", ((PartyIdentified) child).getName());


        } else if (child instanceof PartySelf) {

            //NOP

        } else if (rmClassGeneratorConfig != null && rmClassGeneratorConfig.isExpandField()) {

            Set<String> expandFields = rmClassGeneratorConfig.getExpandFields();
            if (expandFields.size() == 1 && expandFields.contains("value")) {

                try {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor("value", child.getClass());
                    Object property = propertyDescriptor.getReadMethod().invoke(child);
                    result.put(termLoop, property);
                } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
                    throw new ClientException(e.getMessage(), e);
                }

            } else {
                for (String propertyName : expandFields) {
                    try {
                        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, child.getClass());
                        Object property = propertyDescriptor.getReadMethod().invoke(child);
                        result.put(termLoop + "|" + propertyName, property);
                    } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
                        throw new ClientException(e.getMessage(), e);
                    }
                }
            }

        } else {
            result.put(termLoop, child);
        }
        return result;

    }

    private void addValue(Map<String, Object> result, String termLoop, String propertyName, String value) {
        if (value != null) {
            result.put(termLoop + "|" + propertyName, value);
        }
    }

}
