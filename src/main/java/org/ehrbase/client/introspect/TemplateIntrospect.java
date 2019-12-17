/*
 *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  This file is part of Project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.client.introspect;

import com.nedap.archie.rm.archetyped.Pathable;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import org.apache.commons.collections4.ListValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.introspect.config.RmIntrospectConfig;
import org.ehrbase.client.introspect.node.*;
import org.ehrbase.ehr.encode.wrappers.SnakeCase;
import org.openehr.schemas.v1.*;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public class TemplateIntrospect {

    private static final ArchieRMInfoLookup RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

    public static final String PATH_DIVIDER = "/";
    public static final String TERM_DIVIDER = "/";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final Map<Class, RmIntrospectConfig> configMap;
    private final OPERATIONALTEMPLATE operationaltemplate;
    private final ArchetypeNode root;


    public TemplateIntrospect(OPERATIONALTEMPLATE operationaltemplate) {

        this.operationaltemplate = operationaltemplate;
        configMap = buildConfigMap();
        root = buildNodeMap();

    }

    public static Map<Class, RmIntrospectConfig> buildConfigMap() {

        Reflections reflections = new Reflections(RmIntrospectConfig.class.getPackage().getName());
        Set<Class<? extends RmIntrospectConfig>> configs = reflections.getSubTypesOf(RmIntrospectConfig.class);

        return configs.stream()
                .filter(c -> !Modifier.isAbstract(c.getModifiers()))
                .map(c -> {
                    try {
                        return c.getConstructor().newInstance();
                    } catch (Exception e) {
                        throw new ClientException(e.getMessage(), e);
                    }
                }).collect(Collectors.toMap(RmIntrospectConfig::getRMClass, c -> c));
    }

    private ArchetypeNode buildNodeMap() {
        CARCHETYPEROOT definition = operationaltemplate.getDefinition();
        return handleCARCHETYPEROOT(definition, "", false);

    }

    private ArchetypeNode handleCARCHETYPEROOT(CARCHETYPEROOT definition, String name, boolean multi) {
        Map<String, TermDefinition> termDef = new HashMap<>();
        // Keep term definition to map
        for (ARCHETYPETERM term : definition.getTermDefinitionsArray()) {
            String code = term.getCode();
            String value = null;
            String description = null;
            for (StringDictionaryItem item : term.getItemsArray()) {
                if ("text".equals(item.getId())) {
                    value = item.getStringValue();
                }
                if ("description".equals(item.getId()))
                    description = item.getStringValue();
            }
            termDef.put(code, new TermDefinition(code, value, description));

        }
        return new ArchetypeNode(Optional.ofNullable(termDef.get("at0000")).map(TermDefinition::getValue).orElse(name), definition.getArchetypeId().getValue(), handleCCOMPLEXOBJECT(definition, "", termDef, ""), multi);
    }

    private Map<String, Node> handleCCOMPLEXOBJECT(CCOMPLEXOBJECT ccomplexobject, String path, Map<String, TermDefinition> termDef, String term) {

        HashMap<String, Node> localNodeMap = new HashMap<>();
        log.trace("RmTyp: {}", ccomplexobject.getRmTypeName());
        Class rmClass = RM_INFO_LOOKUP.getClass(ccomplexobject.getRmTypeName());

        if (Pathable.class.isAssignableFrom(rmClass)) {
            localNodeMap.putAll(handleNonTemplateFields(rmClass, path));


            CATTRIBUTE[] cattributes = ccomplexobject.getAttributesArray();
            if (ArrayUtils.isNotEmpty(cattributes)) {
                ListValuedMap<String, Node> multiValuedMap = new ArrayListValuedHashMap<>();

                for (CATTRIBUTE cattribute : cattributes) {
                    String pathLoop = path + PATH_DIVIDER + cattribute.getRmAttributeName();
                    log.trace("Path: {}", pathLoop);
                    if (
                            (Event.class.isAssignableFrom(rmClass) && pathLoop.contains("offset")) // event.offset is a calculated value
                                    || pathLoop.equals("/category") // set from template
                    ) {
                        continue;
                    }

                    for (COBJECT cobject : cattribute.getChildrenArray()) {
                        multiValuedMap.putAll(handleCOBJECT(cobject, pathLoop, termDef, term));
                    }

                }
                multiValuedMap
                        .asMap()
                        .forEach((key, value) -> {
                            if (value.size() == 1) {
                                localNodeMap.put(key, value.iterator().next());
                            } else {
                                localNodeMap.put(key,
                                        new ChoiceNode(
                                                value.iterator().next().getName(),
                                                value.stream()
                                                        .filter(n -> EndNode.class.isAssignableFrom(n.getClass()))
                                                        .map(n -> (EndNode) n)
                                                        .collect(Collectors.toList())
                                        )
                                );
                            }
                        });
            }

        } else {
            Set<TermDefinition> termDefinitionSet = Arrays.stream(ccomplexobject.getAttributesArray())
                    .flatMap(c -> Arrays.asList(c.getChildrenArray()).stream())
                    .map(c -> buildTermSet(c, termDef))
                    .findAny()
                    .orElse(Collections.emptySet());
            localNodeMap.put(path, new EndNode(findJavaClass(ccomplexobject.getRmTypeName()), term, termDefinitionSet));
        }
        return localNodeMap;
    }

    private Map<String, Node> handleCOBJECT(COBJECT cobject, String path, Map<String, TermDefinition> termDef, String term) {

        boolean multi = cobject.getOccurrences().getUpper() > 1 || cobject.getOccurrences().getUpperUnbounded();

        if (cobject instanceof CARCHETYPEROOT && !((CARCHETYPEROOT) cobject).getArchetypeId().getValue().isEmpty()) {
            path = path + "[" + ((CARCHETYPEROOT) cobject).getArchetypeId().getValue() + "]";
            log.trace("Path: {}", path);

            if (!cobject.getNodeId().isEmpty() && termDef.containsKey(cobject.getNodeId())) {
                term = term + TERM_DIVIDER + termDef.get(cobject.getNodeId()).getValue();
            }

            return Collections.singletonMap(path, handleCARCHETYPEROOT((CARCHETYPEROOT) cobject, term, multi));

        } else if (cobject instanceof CCOMPLEXOBJECT && multi) {

            return Collections.singletonMap(path, handleEntity((CCOMPLEXOBJECT) cobject, term, termDef, multi));

        } else if (cobject instanceof CCOMPLEXOBJECT) {

            if (!cobject.getNodeId().isEmpty()) {
                path = path + "[" + cobject.getNodeId() + "]";
                log.trace("Path: {}", path);
                if (termDef.containsKey(cobject.getNodeId())) {
                    term = term + TERM_DIVIDER + termDef.get(cobject.getNodeId()).getValue();
                }
            }

            return handleCCOMPLEXOBJECT((CCOMPLEXOBJECT) cobject, path, termDef, term);

        } else if (cobject instanceof ARCHETYPESLOT) {
            if (!cobject.getNodeId().isEmpty()) {
                path = path + "[" + cobject.getNodeId() + "]";
                log.trace("Path: {}", path);
                if (termDef.containsKey(cobject.getNodeId())) {
                    term = term + TERM_DIVIDER + termDef.get(cobject.getNodeId()).getValue();
                }
            }
            return Collections.singletonMap(path, new SlotNode(findJavaClass(cobject.getRmTypeName()), term, Collections.emptySet(), multi));

        } else {

            Set<TermDefinition> termDefinitions = buildTermSet(cobject, termDef);
            return Collections.singletonMap(path, new EndNode(findJavaClass(cobject.getRmTypeName()), term, termDefinitions));
        }
    }

    private Set<TermDefinition> buildTermSet(COBJECT cobject, Map<String, TermDefinition> termDef) {
        Set<TermDefinition> termDefinitions;
        if (cobject instanceof CCODEPHRASE) {

            termDefinitions = Arrays.stream(((CCODEPHRASE) cobject).getCodeListArray()).filter(termDef::containsKey).map(termDef::get).collect(Collectors.toSet());
        } else {
            termDefinitions = Collections.emptySet();
        }
        return termDefinitions;
    }


    private Class findJavaClass(String rmName) {
        switch (rmName) {
            case "STRING":
                return String.class;
            case "INTEGER":
            case "INTEGER64":
                return Long.class;
            case "BOOLEAN":
                return Boolean.class;
            case "REAL":
            case "DOUBLE":
                return Double.class;
            default:
                return RM_INFO_LOOKUP.getClass(rmName);
        }
    }

    private EntityNode handleEntity(CCOMPLEXOBJECT cobject, String name, Map<String, TermDefinition> termDef, boolean multi) {
        return new EntityNode(Optional.ofNullable(termDef.get("at0000")).map(TermDefinition::getValue).orElse("") + name, multi, handleCCOMPLEXOBJECT(cobject, "", termDef, ""));
    }

    private Map<String, Node> handleNonTemplateFields(Class clazz, String path) {
        RmIntrospectConfig introspectConfig = configMap.get(clazz);
        if (introspectConfig != null) {
            HashMap<String, Node> localNodeMap = new HashMap<>();
            Arrays.stream(FieldUtils.getAllFields(clazz))
                    .filter(f -> introspectConfig.getNonTemplateFields().contains(f.getName()))
                    .forEach(f -> {
                        String snakeName = new SnakeCase(f.getName()).camelToSnake();
                        String localPath = path + PATH_DIVIDER + snakeName;
                        localNodeMap.put(localPath, new EndNode(f.getType(), snakeName));
                    });
            return localNodeMap;
        } else {
            log.debug("No RmIntrospectConfig for {}", clazz);
            return Collections.emptyMap();
        }
    }

    public ArchetypeNode getRoot() {
        return root;
    }
}
