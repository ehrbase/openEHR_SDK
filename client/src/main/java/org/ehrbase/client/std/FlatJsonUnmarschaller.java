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
import com.nedap.archie.datetime.DateTimeParsers;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.Entry;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDate;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvTime;
import com.nedap.archie.rm.support.identification.HierObjectId;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.client.building.OptSkeletonBuilder;
import org.ehrbase.client.classgenerator.config.RmClassGeneratorConfig;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.flattener.ItemExtractor;
import org.ehrbase.client.introspect.TemplateIntrospect;
import org.ehrbase.client.introspect.node.*;
import org.ehrbase.client.normalizer.Normalizer;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.ehrbase.client.introspect.TemplateIntrospect.PATH_DIVIDER;
import static org.ehrbase.client.introspect.TemplateIntrospect.TERM_DIVIDER;

public class FlatJsonUnmarschaller {

    private static final ObjectMapper OBJECT_MAPPER = JacksonUtil.getObjectMapper();
    public static final OptSkeletonBuilder OPT_SKELETON_BUILDER = new OptSkeletonBuilder();
    public static final Normalizer NORMALIZER = new Normalizer();

    private final Logger log = LoggerFactory.getLogger(getClass());

    private Set<String> consumedPath;

    private static final Map<Class, RmClassGeneratorConfig> configMap = buildConfigMap();
    private Map<String, String> curentValues;

    private static Map<Class, RmClassGeneratorConfig> buildConfigMap() {
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


    public Composition Unmarschall(String flat, TemplateIntrospect introspect, OPERATIONALTEMPLATE operationalTemplate) {

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

        root.getChildren().forEach((p, node) -> mapNode(path, term, p, node, values, locatable, null));
        if (locatable instanceof Entry) {

            consumedPath.add(term + PATH_DIVIDER + "encoding|code");
            consumedPath.add(term + PATH_DIVIDER + "encoding|terminology");


        }

        if (locatable instanceof Composition) {

            String strip = StringUtils.strip(values.get(term + PATH_DIVIDER + "_uid") + "", "\"");
            if (StringUtils.isNotBlank(strip)) {
                locatable.setUid(new HierObjectId(strip));
            }
            consumedPath.add(term + PATH_DIVIDER + "_uid");
        }
    }

    private void mapNode(String path, String term, String childPath, Node node, Map<String, String> values, Locatable locatable, Integer outerCount) {

        String pathLoop = path + childPath;
        String termLoop = StringUtils.isNotBlank(node.getName()) ? term + TERM_DIVIDER + StringUtils.stripStart(node.getName(), "/") : node.getName();

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
        if (child instanceof CodePhrase) {
            setValue(termLoop, "code", values, ((CodePhrase) child)::setCodeString, String.class);
            ((CodePhrase) child).setTerminologyId(new TerminologyId());
            setValue(termLoop, "terminology", values, t -> ((CodePhrase) child).getTerminologyId().setValue(t), String.class);
        } else if (child instanceof DvCodedText) {
            setValue(termLoop, "value", values, c -> ((DvCodedText) child).setValue(c), String.class);
            ((DvCodedText) child).setDefiningCode(new CodePhrase());
            setValue(termLoop, "code", values, c -> ((DvCodedText) child).getDefiningCode().setCodeString(c), String.class);
            ((DvCodedText) child).getDefiningCode().setTerminologyId(new TerminologyId());
            setValue(termLoop, "terminology", values, t -> ((DvCodedText) child).getDefiningCode().getTerminologyId().setValue(t), String.class);
        } else if (child instanceof DvDateTime) {
            String s = values.get(termLoop);
            if (StringUtils.isNotBlank(s)) {
                ((DvDateTime) child).setValue(DateTimeParsers.parseDateTimeValue(StringUtils.strip(s, "\"")));
                consumedPath.add(termLoop);
            }
        } else if (child instanceof DvTime) {
            String s = values.get(termLoop);
            if (StringUtils.isNotBlank(s)) {
                ((DvTime) child).setValue(DateTimeParsers.parseTimeValue(StringUtils.strip(s, "\"")));
                consumedPath.add(termLoop);
            }
        } else if (child instanceof DvDate) {
            String s = values.get(termLoop);
            if (StringUtils.isNotBlank(s)) {
                ((DvDate) child).setValue(DateTimeParsers.parseDateValue(StringUtils.strip(s, "\"")));
                consumedPath.add(termLoop);
            }
        } else if (child instanceof DvDuration) {
            String s = values.get(termLoop);
            if (StringUtils.isNotBlank(s)) {
                ((DvDuration) child).setValue(DateTimeParsers.parseDurationValue(StringUtils.strip(s, "\"")));
                consumedPath.add(termLoop);
            }
        } else if (child instanceof RMObject) {
            RmClassGeneratorConfig rmClassGeneratorConfig = configMap.get(child.getClass());
            if (rmClassGeneratorConfig != null && rmClassGeneratorConfig.isExpandField()) {

                Set<String> expandFields = rmClassGeneratorConfig.getExpandFields();
                if (expandFields.size() == 1 && expandFields.contains("value")) {

                    try {
                        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("value", child.getClass());

                        setValue(termLoop, null, values, s -> {
                            try {
                                propertyDescriptor.getWriteMethod().invoke(child, s);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                throw new ClientException(e.getMessage(), e);
                            }
                        }, propertyDescriptor.getPropertyType());
                    } catch (IntrospectionException e) {
                        throw new ClientException(e.getMessage(), e);
                    }

                } else {
                    for (String propertyName : expandFields) {
                        try {
                            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, child.getClass());
                            setValue(termLoop, propertyName, values, s -> {
                                try {
                                    propertyDescriptor.getWriteMethod().invoke(child, s);
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    throw new ClientException(e.getMessage(), e);
                                }
                            }, propertyDescriptor.getPropertyType());
                        } catch (IntrospectionException e) {
                            throw new ClientException(e.getMessage(), e);
                        }
                    }
                }

            }
        } else {
            log.warn("Unhandled: " + termLoop);
        }
    }

    private <T> void setValue(String term, String propertyName, Map<String, String> values, Consumer<T> consumer, Class<T> clazz) {
        String key = propertyName != null ? term + "|" + propertyName : term;
        String jasonValue = values.get(key);
        if (StringUtils.isNotBlank(jasonValue)) {
            try {
                T value = OBJECT_MAPPER.readValue(jasonValue, clazz);
                consumer.accept(value);
                consumedPath.add(key);
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
            }
        } else {
            consumer.accept(null);
            consumedPath.add(key);
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
