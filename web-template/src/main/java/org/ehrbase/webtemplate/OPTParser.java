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

package org.ehrbase.webtemplate;

import org.apache.commons.collections4.ListValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.lang3.StringUtils;
import org.openehr.schemas.v1.ARCHETYPETERM;
import org.openehr.schemas.v1.CARCHETYPEROOT;
import org.openehr.schemas.v1.CATTRIBUTE;
import org.openehr.schemas.v1.CCOMPLEXOBJECT;
import org.openehr.schemas.v1.CDOMAINTYPE;
import org.openehr.schemas.v1.COBJECT;
import org.openehr.schemas.v1.IntervalOfInteger;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.StringDictionaryItem;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OPTParser {
    public static final String PATH_DIVIDER = "/";

    private final OPERATIONALTEMPLATE operationaltemplate;
    private final String defaultLanguage;

    public OPTParser(OPERATIONALTEMPLATE operationaltemplate) {
        this.operationaltemplate = operationaltemplate;
        defaultLanguage = operationaltemplate.getLanguage().getCodeString();
    }

    public WebTemplate parse() {
        WebTemplate webTemplate = new WebTemplate();

        webTemplate.setTemplateId(operationaltemplate.getTemplateId().getValue());
        webTemplate.setDefaultLanguage(defaultLanguage);
        webTemplate.setTree(parseCARCHETYPEROO(operationaltemplate.getDefinition(), ""));
        return webTemplate;
    }

    private WebTemplateNode parseCARCHETYPEROO(CARCHETYPEROOT carchetyperoot, String aqlPath) {


        // extract local Terminologies
        Map<String, Map<String, TermDefinition>> termDefinitionMap = new HashMap<>();
        for (ARCHETYPETERM term : carchetyperoot.getTermDefinitionsArray()) {
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
            String language = defaultLanguage;
            termDefinitionMap.computeIfAbsent(code, c -> new HashMap<>()).put(language, new TermDefinition(code, value, description));

        }
        WebTemplateNode node = parseCCOMPLEXOBJECT(carchetyperoot, aqlPath, termDefinitionMap);
        node.setNodeId(carchetyperoot.getArchetypeId().getValue());
        return node;
    }

    private WebTemplateNode parseCCOMPLEXOBJECT(CCOMPLEXOBJECT ccomplexobject, String aqlPath, Map<String, Map<String, TermDefinition>> termDefinitionMap) {
        WebTemplateNode node = new WebTemplateNode();
        node.setRmType(ccomplexobject.getRmTypeName());
        CATTRIBUTE[] cattributes = ccomplexobject.getAttributesArray();
        node.setAqlPath(aqlPath);

        IntervalOfInteger occurrences = ccomplexobject.getOccurrences();
        node.setMin(occurrences.getLower());
        node.setMax(occurrences.getUpperUnbounded() ? -1 : occurrences.getUpper());

        String nodeId = ccomplexobject.getNodeId();
        if (StringUtils.isNotBlank(nodeId)) {
            String name = OptNameHelper.extractName(ccomplexobject).orElse(termDefinitionMap.get(nodeId).get(defaultLanguage).getValue());
            node.setName(name);
            node.setId(buildId(name));
            node.setNodeId(nodeId);
            node.setLocalizedName(name);
            node.getLocalizedNames().putAll(termDefinitionMap.get(nodeId).entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getValue())));
            node.getLocalizedDescriptions().putAll(termDefinitionMap.get(nodeId).entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getDescription())));
        } else {
            node.setId(buildId(ccomplexobject.getRmTypeName()));
        }

        ListValuedMap<String, WebTemplateNode> multiValuedMap = new ArrayListValuedHashMap<>();
        for (CATTRIBUTE cattribute : cattributes) {
            String pathLoop = aqlPath + PATH_DIVIDER + cattribute.getRmAttributeName();
            if (pathLoop.equals("/category") || pathLoop.endsWith("/name")) {
                continue;
            }

            for (COBJECT cobject : cattribute.getChildrenArray()) {
                WebTemplateNode childNode = parseCOBJECT(cobject, pathLoop, termDefinitionMap);
                if (childNode != null) {
                    multiValuedMap.put(childNode.getAqlPath(), childNode);
                }
            }
        }
        node.getChildren().addAll(multiValuedMap.values());
        return node;
    }

    private WebTemplateNode parseCOBJECT(COBJECT cobject, String aqlPath, Map<String, Map<String, TermDefinition>> termDefinitionMap) {
        if (cobject instanceof CARCHETYPEROOT) {
            String nodeId = StringUtils.isNotBlank(((CARCHETYPEROOT) cobject).getArchetypeId().getValue()) ? ((CARCHETYPEROOT) cobject).getArchetypeId().getValue() : cobject.getNodeId();
            String pathLoop;
            if (StringUtils.isNotBlank(nodeId)) {

                pathLoop = aqlPath + "[" + nodeId + "]";
            } else {
                pathLoop = aqlPath;
            }

            return parseCARCHETYPEROO((CARCHETYPEROOT) cobject, pathLoop);
        } else if (cobject instanceof CCOMPLEXOBJECT) {

            String nodeId = cobject.getNodeId();
            String pathLoop;
            if (StringUtils.isNotBlank(nodeId)) {
                pathLoop = aqlPath + "[" + nodeId + "]";
            } else {
                pathLoop = aqlPath;
            }
            return parseCCOMPLEXOBJECT((CCOMPLEXOBJECT) cobject, pathLoop, termDefinitionMap);
        } else if (cobject instanceof CDOMAINTYPE) {
            String nodeId = cobject.getNodeId();
            String pathLoop;
            if (StringUtils.isNotBlank(nodeId)) {
                pathLoop = aqlPath + "[" + nodeId + "]";
            } else {
                pathLoop = aqlPath;
            }
            WebTemplateNode node = new WebTemplateNode();
            node.setAqlPath(pathLoop);
            node.setRmType(cobject.getRmTypeName());
            return node;
        }

        return null;
    }

    private String buildId(String term) {

        String normalTerm = StringUtils.normalizeSpace(term.toLowerCase().replaceAll("[^a-z0-9äüöß._\\-]", " ").trim()).replace(" ", "_");
        if (StringUtils.isNumeric(normalTerm.substring(0, 1))) {
            normalTerm = "a" + normalTerm;
        }

        return normalTerm;
    }
}
