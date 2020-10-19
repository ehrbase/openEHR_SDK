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
import com.nedap.archie.rm.composition.EventContext;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMAttributeInfo;
import com.nedap.archie.rminfo.RMTypeInfo;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.terminology.client.terminology.TermDefinition;
import org.ehrbase.util.reflection.ReflectionHelper;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateInputValue;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.parser.config.RmIntrospectConfig;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class OPTParser {
    public static final String PATH_DIVIDER = "/";
    public static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();
    private static final Map<Class<?>, RmIntrospectConfig> configMap = ReflectionHelper.buildMap(RmIntrospectConfig.class);

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
        WebTemplateNode node = parseCCOMPLEXOBJECT(carchetyperoot, aqlPath, termDefinitionMap, null);
        node.setNodeId(carchetyperoot.getArchetypeId().getValue());
        addRMAttributes(node, aqlPath, termDefinitionMap);
        return node;
    }

    private WebTemplateNode parseCCOMPLEXOBJECT(CCOMPLEXOBJECT ccomplexobject, String aqlPath, Map<String, Map<String, TermDefinition>> termDefinitionMap, String rmAttributeName) {

        Optional<String> expliziteName = OptNameHelper.extractName(ccomplexobject);
        WebTemplateNode node = buildNode(ccomplexobject, rmAttributeName, termDefinitionMap);
        node.setAqlPath(aqlPath);

        if (StringUtils.isNotBlank(ccomplexobject.getNodeId()) && expliziteName.isPresent()) {
            FlatPath path = new FlatPath(node.getAqlPath());
            FlatPath lastSegment = path;
            while (lastSegment.getChild() != null) {
                lastSegment = lastSegment.getChild();
            }
            lastSegment.addOtherPredicate("name/value", expliziteName.get());
            node.setAqlPath(path.format(true));
            aqlPath = path.format(true);
        }

        CATTRIBUTE[] cattributes = ccomplexobject.getAttributesArray();

        for (CATTRIBUTE cattribute : cattributes) {
            String pathLoop = aqlPath + PATH_DIVIDER + cattribute.getRmAttributeName();
            if (
                //Will be set via Attributes
                    pathLoop.equals("/category")
                            || pathLoop.endsWith("/name")) {
                continue;
            }
            List<WebTemplateNode> newChildren = new ArrayList<>();
            for (COBJECT cobject : cattribute.getChildrenArray()) {
                WebTemplateNode childNode = parseCOBJECT(cobject, pathLoop, termDefinitionMap, cattribute.getRmAttributeName());
                if (childNode != null) {
                    newChildren.add(childNode);
                }
            }

            List<WebTemplateNode> ismTransitionList = newChildren.stream()
                    .filter(n -> "ISM_TRANSITION".equals(n.getRmType()))
                    .collect(Collectors.toList());
            if (!ismTransitionList.isEmpty()) {

                WebTemplateNode ismTransition = new WebTemplateNode();
                ismTransition.setName(cattribute.getRmAttributeName());
                ismTransition.setId(buildId(cattribute.getRmAttributeName()));
                ismTransition.setMin(ismTransitionList.get(0).getMin());
                ismTransition.setMax(ismTransitionList.get(0).getMax());
                ismTransition.setRmType("ISM_TRANSITION");
                ismTransition.setAqlPath(aqlPath + "/" + cattribute.getRmAttributeName());
                node.getChildren().add(ismTransition);
            }

            node.getChildren().addAll(newChildren);
        }

        //Inherit name for Element values
        if (node.getRmType().equals("ELEMENT")) {
            if (node.getChildren().size() == 1) {
                WebTemplateNode value = node.getChildren().get(0);
                value.setId(node.getId());
                value.setName(node.getName());
                value.setMax(node.getMax());
            }
        }
        node.getChoicesInChildren()
                .values()
                .stream()
                .flatMap(List::stream)
                .filter(n -> n.getRmType().startsWith("DV_"))
                .forEach(n -> n.setId(n.getRmType().replace("DV_", "").toLowerCase() + "_value"));

        addRMAttributes(node, aqlPath, termDefinitionMap);

        makeIdUnique(node);


        return node;
    }

    public static void makeIdUnique(WebTemplateNode node) {
        //Make ids unique in for a children
        node.getChildren().stream()
                .collect(Collectors.groupingBy(WebTemplateNode::getId))
                .values()
                .stream()
                .filter(l -> l.size() > 1)
                .forEach(l -> {
                            for (int i = 0; i < l.size(); i++) {
                                if (i > 0) {
                                    WebTemplateNode n = l.get(i);
                                    n.setId(n.getId() + (i + 1));
                                }
                            }
                        }
                );
    }

    private void addRMAttributes(WebTemplateNode node, String aqlPath, Map<String, Map<String, TermDefinition>> termDefinitionMap) {
        //Add RM Attributes
        RMTypeInfo typeInfo = ARCHIE_RM_INFO_LOOKUP.getTypeInfo(node.getRmType());
        if (typeInfo != null && (Locatable.class.isAssignableFrom(typeInfo.getJavaClass()) || EventContext.class.isAssignableFrom(typeInfo.getJavaClass())) && !Element.class.isAssignableFrom(typeInfo.getJavaClass())) {
            Set<String> attributeNames = Optional.of(configMap.get(typeInfo.getJavaClass())).map(RmIntrospectConfig::getNonTemplateFields).orElse(Collections.emptySet());

            node.getChildren().addAll(typeInfo.getAttributes()
                    .values()
                    .stream()
                    //      .filter(s ->attributeNames.contains(s.getRmName()))
                    .filter(s -> !List.of("value").contains(s.getRmName()))
                    .filter(s -> !Locatable.class.isAssignableFrom(s.getTypeInCollection()))
                    //   .filter(s -> List.of("language", "time", "subject", "encoding", "territory", "composer", "math_function", "width", "category", "setting", "start_time","timing","narrative").contains(s.getRmName()))
                    .map(i -> buildNodeForAttribute(i, aqlPath, termDefinitionMap))
                    //only add if not already there
                    .filter(n -> node.getChildren().stream().map(WebTemplateNode::getId).noneMatch(s -> s.equals(n.getId())))
                    .collect(Collectors.toList()));
        }
    }

    private WebTemplateNode buildNodeForAttribute(RMAttributeInfo attributeInfo, String aqlPath, Map<String, Map<String, TermDefinition>> termDefinitionMap) {
        WebTemplateNode node = new WebTemplateNode();
        node.setAqlPath(aqlPath + PATH_DIVIDER + attributeInfo.getRmName());
        node.setName(attributeInfo.getRmName());
        node.setId(buildId(attributeInfo.getRmName()));
        node.setRmType(attributeInfo.getTypeNameInCollection());
        node.setMax(1);
        node.setMin(0);
        return node;
    }

    private WebTemplateNode parseCOBJECT(COBJECT cobject, String aqlPath, Map<String, Map<String, TermDefinition>> termDefinitionMap, String rmAttributeName) {


        if (cobject instanceof CARCHETYPEROOT) {
            String nodeId = ((CARCHETYPEROOT) cobject).getArchetypeId().getValue();
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
            return parseCCOMPLEXOBJECT((CCOMPLEXOBJECT) cobject, pathLoop, termDefinitionMap, rmAttributeName);

        } else if (cobject instanceof CDOMAINTYPE) {
            String nodeId = cobject.getNodeId();
            final String pathLoop;
            if (StringUtils.isNotBlank(nodeId)) {
                pathLoop = aqlPath + "[" + nodeId + "]";
            } else {
                pathLoop = aqlPath;
            }
            return parseCDOMAINTYPE((CDOMAINTYPE) cobject, pathLoop, termDefinitionMap, rmAttributeName);
        }
        return null;
    }

    private WebTemplateNode parseCDOMAINTYPE(CDOMAINTYPE cdomaintype, String aqlPath, Map<String, Map<String, TermDefinition>> termDefinitionMap, String rmAttributeName) {

        WebTemplateNode node = buildNode(cdomaintype, rmAttributeName, termDefinitionMap);
        node.setAqlPath(aqlPath);
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
            WebTemplateInput code = new WebTemplateInput();
            code.setType("CODED_TEXT");
            node.getInputs().add(code);
            Arrays.stream(((CDVORDINAL) cdomaintype).getListArray()).forEach(
                    o -> {
                        WebTemplateInputValue value = new WebTemplateInputValue();
                        value.setOrdinal(o.getValue());
                        value.setValue(o.getSymbol().getDefiningCode().getCodeString());
                        value.getLocalizedLabels().putAll(
                                Optional.ofNullable(termDefinitionMap.get(value.getValue()))
                                        .map(Map::entrySet)
                                        .stream()
                                        .flatMap(Set::stream)
                                        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getValue()))
                        );
                        value.setLabel(value.getLocalizedLabels().get(defaultLanguage));
                        code.getList().add(value);
                    }
            );


        } else if (cdomaintype instanceof CCODEPHRASE) {

        } else {
            throw new RuntimeException(String.format("Unexpected class: %s", cdomaintype.getClass().getSimpleName()));
        }
        return node;
    }

    private WebTemplateNode buildNode(COBJECT cobject, String rmAttributeName, Map<String, Map<String, TermDefinition>> termDefinitionMap) {
        WebTemplateNode node = new WebTemplateNode();
        node.setRmType(cobject.getRmTypeName());
        IntervalOfInteger occurrences = cobject.getOccurrences();
        node.setMin(occurrences.getLowerUnbounded() ? -1 : occurrences.getLower());
        node.setMax(occurrences.getUpperUnbounded() ? -1 : occurrences.getUpper());
        String nodeId = cobject.getNodeId();
        if (StringUtils.isNotBlank(nodeId)) {

            Optional<String> expliziteName;
            if (cobject instanceof CCOMPLEXOBJECT) {
                expliziteName = OptNameHelper.extractName((CCOMPLEXOBJECT) cobject);
            } else {
                expliziteName = Optional.empty();
            }

            String name = expliziteName.orElse(termDefinitionMap.get(nodeId).get(defaultLanguage).getValue());
            node.setName(name);
            node.setId(buildId(name));
            node.setNodeId(nodeId);
            node.setLocalizedName(name);
            node.getLocalizedNames().putAll(termDefinitionMap.get(nodeId).entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getValue())));
            node.getLocalizedDescriptions().putAll(termDefinitionMap.get(nodeId).entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getDescription())));
        } else {
            String name = StringUtils.isNotBlank(rmAttributeName) ? rmAttributeName : cobject.getRmTypeName();
            node.setId(buildId(name));
            node.setName(name);
            node.setLocalizedName(name);
        }
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
