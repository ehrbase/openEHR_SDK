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

package org.ehrbase.webtemplate.parser;

import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMAttributeInfo;
import com.nedap.archie.rminfo.RMTypeInfo;
import org.apache.commons.collections4.ListValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.openehr.schemas.v1.ARCHETYPETERM;
import org.openehr.schemas.v1.CARCHETYPEROOT;
import org.openehr.schemas.v1.CATTRIBUTE;
import org.openehr.schemas.v1.CCODEPHRASE;
import org.openehr.schemas.v1.CCOMPLEXOBJECT;
import org.openehr.schemas.v1.CDOMAINTYPE;
import org.openehr.schemas.v1.CDVORDINAL;
import org.openehr.schemas.v1.CDVQUANTITY;
import org.openehr.schemas.v1.CDVSTATE;
import org.openehr.schemas.v1.COBJECT;
import org.openehr.schemas.v1.IntervalOfInteger;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.StringDictionaryItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class OPTParser {
    public static final String PATH_DIVIDER = "/";
    public static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

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
        webTemplate.setVersion("2.3");
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
            termDefinitionMap.computeIfAbsent(code, c -> new HashMap<>()).put(defaultLanguage, new TermDefinition(code, value, description));

        }
        WebTemplateNode node = parseCCOMPLEXOBJECT(carchetyperoot, aqlPath, termDefinitionMap);
        node.setNodeId(carchetyperoot.getArchetypeId().getValue());
        return node;
    }

    private WebTemplateNode parseCCOMPLEXOBJECT(CCOMPLEXOBJECT ccomplexobject, String aqlPath, Map<String, Map<String, TermDefinition>> termDefinitionMap) {

        WebTemplateNode node = buildNode(ccomplexobject);
        node.setAqlPath(aqlPath);
        String nodeId = ccomplexobject.getNodeId();
        if (StringUtils.isNotBlank(nodeId)) {
            Optional<String> expliziteName = OptNameHelper.extractName(ccomplexobject);
            if (expliziteName.isPresent()) {
                FlatPath path = new FlatPath(node.getAqlPath());
                FlatPath lastSegment = path;
                while (lastSegment.getChild() != null) {
                    lastSegment = lastSegment.getChild();
                }
                lastSegment.addOtherPredicate("name/value", expliziteName.get());
                node.setAqlPath(path.format(true));
                aqlPath = path.format(true);
            }
            String name = expliziteName.orElse(termDefinitionMap.get(nodeId).get(defaultLanguage).getValue());
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
        CATTRIBUTE[] cattributes = ccomplexobject.getAttributesArray();
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
        //Inherit name for Element values
        if (node.getRmType().equals("ELEMENT")) {
            if (node.getChildren().size() == 1) {
                WebTemplateNode value = node.getChildren().get(0);
                value.setId(node.getId());
                value.setName(node.getName());
            }
        }

        //Add RM Attributes
        RMTypeInfo typeInfo = ARCHIE_RM_INFO_LOOKUP.getTypeInfo(node.getRmType());
        if (typeInfo != null && Locatable.class.isAssignableFrom(typeInfo.getJavaClass())) {
            String finalAqlPath = aqlPath;
            node.getChildren().addAll(typeInfo.getAttributes()
                    .values()
                    .stream()
                    .filter(s -> List.of("language", "time", "subject", "encoding", "territory", "composer", "math_function", "width").contains(s.getRmName()))
                    .map(i -> buildNodeForAttribute(i, finalAqlPath, termDefinitionMap))
                    .collect(Collectors.toList()));
        }
        return node;
    }

    private WebTemplateNode buildNodeForAttribute(RMAttributeInfo attributeInfo, String aqlPath, Map<String, Map<String, TermDefinition>> termDefinitionMap) {
        WebTemplateNode node = new WebTemplateNode();
        node.setAqlPath(aqlPath + PATH_DIVIDER + attributeInfo.getRmName());
        node.setName(attributeInfo.getRmName());
        node.setId(buildId(attributeInfo.getRmName()));
        node.setRmType(attributeInfo.getTypeNameInCollection());
        return node;
    }

    private WebTemplateNode parseCOBJECT(COBJECT cobject, String aqlPath, Map<String, Map<String, TermDefinition>> termDefinitionMap) {

        if (cobject instanceof CARCHETYPEROOT) {
            String nodeId = StringUtils.isNotBlank(((CARCHETYPEROOT) cobject).getArchetypeId().getValue()) ? ((CARCHETYPEROOT) cobject).getArchetypeId().getValue() : cobject.getNodeId();
            final String pathLoop;
            if (StringUtils.isNotBlank(nodeId)) {

                pathLoop = aqlPath + "[" + nodeId + "]";
            } else {
                pathLoop = aqlPath;
            }

            return parseCARCHETYPEROO((CARCHETYPEROOT) cobject, pathLoop);
        } else if (cobject instanceof CCOMPLEXOBJECT) {

            String nodeId = cobject.getNodeId();
            final String pathLoop;
            if (StringUtils.isNotBlank(nodeId)) {
                pathLoop = aqlPath + "[" + nodeId + "]";
            } else {
                pathLoop = aqlPath;
            }
            return parseCCOMPLEXOBJECT((CCOMPLEXOBJECT) cobject, pathLoop, termDefinitionMap);
        } else if (cobject instanceof CDOMAINTYPE) {
            return parseCDOMAINTYPE((CDOMAINTYPE) cobject, aqlPath, termDefinitionMap);
        }
        return null;
    }

    private WebTemplateNode parseCDOMAINTYPE(CDOMAINTYPE cdomaintype, String aqlPath, Map<String, Map<String, TermDefinition>> termDefinitionMap) {
        String nodeId = cdomaintype.getNodeId();
        final String pathLoop;
        if (StringUtils.isNotBlank(nodeId)) {
            pathLoop = aqlPath + "[" + nodeId + "]";
        } else {
            pathLoop = aqlPath;
        }
        WebTemplateNode node = buildNode(cdomaintype);
        node.setAqlPath(pathLoop);
        if (cdomaintype instanceof CDVSTATE) {

        } else if (cdomaintype instanceof CDVQUANTITY) {

            WebTemplateInput magnitude = new WebTemplateInput();
            magnitude.setSuffix("magnitude");
            magnitude.setType("DECIMAL");
            node.getInputs().add(magnitude);

            WebTemplateInput unit = new WebTemplateInput();
            unit.setSuffix("unit");
            unit.setType("CODED_TEXT");
            node.getInputs().add(unit);

        } else if (cdomaintype instanceof CDVORDINAL) {

        } else if (cdomaintype instanceof CCODEPHRASE) {

        } else {
            throw new RuntimeException(String.format("Unexpected class: %s", cdomaintype.getClass().getSimpleName()));
        }
        return node;
    }

    private WebTemplateNode buildNode(COBJECT cobject) {
        WebTemplateNode node = new WebTemplateNode();
        node.setRmType(cobject.getRmTypeName());
        IntervalOfInteger occurrences = cobject.getOccurrences();
        node.setMin(occurrences.getLowerUnbounded() ? -1 : occurrences.getLower());
        node.setMax(occurrences.getUpperUnbounded() ? -1 : occurrences.getUpper());
        return node;
    }

    private String buildId(String term) {

        String normalTerm = StringUtils.normalizeSpace(term.toLowerCase().replaceAll("[^a-z0-9äüöß._\\-]", " ").trim()).replace(" ", "_");
        if (StringUtils.isNumeric(normalTerm.substring(0, 1))) {
            normalTerm = "a" + normalTerm;
        }

        return normalTerm;
    }
}
