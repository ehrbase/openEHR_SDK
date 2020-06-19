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
import org.ehrbase.client.classgenerator.config.RmClassGeneratorConfig;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.flattener.ItemExtractor;
import org.ehrbase.client.introspect.TemplateIntrospect;
import org.ehrbase.client.introspect.node.ArchetypeNode;
import org.ehrbase.client.introspect.node.EndNode;
import org.ehrbase.client.introspect.node.Node;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.reflections.Reflections;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.ehrbase.client.introspect.TemplateIntrospect.PATH_DIVIDER;
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

        Map<String, Object> result = new HashMap<>(handelNode(introspect.getRootName(), "", introspect.getRoot(), composition));

        try {
            return OBJECT_MAPPER.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    Map<String, Object> handelNode(String term, String path, ArchetypeNode archetypeNode, Locatable locatable) {
        Map<String, Object> result = new HashMap<>();

        for (Map.Entry<String, Node> nodeEntry : archetypeNode.getChildren().entrySet()) {

            Node childNode = nodeEntry.getValue();
            String pathloop = path + PATH_DIVIDER + nodeEntry.getKey();
            String termLoop = term + TERM_DIVIDER + childNode.getName();
            if (childNode instanceof EndNode) {
                Object child = new ItemExtractor(locatable, nodeEntry.getKey(), ((EndNode) childNode).isMulti()).getChild();

                if (child instanceof List) {
                    for (int i = 0; i < ((List) child).size(); i++) {
                        result.putAll(addSingleValue(termLoop + "[" + i + "]", ((List) child).get(i)));
                    }
                } else if (child != null) {
                    result.putAll(addSingleValue(termLoop, child));
                }
            } else if (childNode instanceof ArchetypeNode) {
                Object child = new ItemExtractor(locatable, nodeEntry.getKey(), ((ArchetypeNode) childNode).isMulti()).getChild();

                if (child instanceof List) {
                    for (int i = 0; i < ((List) child).size(); i++) {
                        result.putAll(handelNode(termLoop + "[" + i + "]", pathloop, (ArchetypeNode) childNode, (Locatable) ((List) child).get(i)));
                    }
                } else if (child != null) {
                    result.putAll(handelNode(termLoop, pathloop, (ArchetypeNode) childNode, (Locatable) child));
                }
            }

        }
        return result;
    }

    private Map<String, Object> addSingleValue(String termLoop, Object child) {
        Map<String, Object> result = new HashMap<>();
        RmClassGeneratorConfig rmClassGeneratorConfig = configMap.get(child.getClass());
        if (child instanceof CodePhrase) {

            result.put(termLoop + "|" + "code", ((CodePhrase) child).getCodeString());
            result.put(termLoop + "|" + "terminology", ((CodePhrase) child).getTerminologyId().getValue());


        } else if (child instanceof DvCodedText) {

            result.put(termLoop + "|" + "code", (((DvCodedText) child).getDefiningCode().getCodeString()));
            result.put(termLoop + "|" + "terminology", ((DvCodedText) child).getDefiningCode().getTerminologyId().getValue());
            result.put(termLoop + "|" + "value", ((DvCodedText) child).getValue());

        } else if (rmClassGeneratorConfig != null && rmClassGeneratorConfig.isExpandField()) {
            for (String propertyName : rmClassGeneratorConfig.getExpandFields()) {
                try {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, child.getClass());
                    Object property = propertyDescriptor.getReadMethod().invoke(child);
                    result.put(termLoop + "|" + propertyName, property);
                } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
                    throw new ClientException(e.getMessage(), e);
                }
            }

        } else {
            result.put(termLoop, child);
        }
        return result;

    }
}
