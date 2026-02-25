/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.webtemplate.parser;

import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.archetyped.Pathable;
import com.nedap.archie.rm.composition.Action;
import com.nedap.archie.rm.composition.Activity;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.Entry;
import com.nedap.archie.rm.composition.EventContext;
import com.nedap.archie.rm.composition.Instruction;
import com.nedap.archie.rm.composition.IsmTransition;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.History;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMAttributeInfo;
import com.nedap.archie.rminfo.RMTypeInfo;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlTokenSource;
import org.ehrbase.openehr.sdk.aql.webtemplatepath.AqlPath;
import org.ehrbase.openehr.sdk.terminology.TermDefinition;
import org.ehrbase.openehr.sdk.terminology.TerminologyProvider;
import org.ehrbase.openehr.sdk.terminology.ValueSet;
import org.ehrbase.openehr.sdk.util.exception.SdkException;
import org.ehrbase.openehr.sdk.util.rmconstants.RmConstants;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplate;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateAnnotation;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateInput;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateInputValue;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateValidation;
import org.ehrbase.openehr.sdk.webtemplate.model.WebtemplateCardinality;
import org.ehrbase.openehr.sdk.webtemplate.util.WebTemplateUtils;
import org.openehr.schemas.v1.ANNOTATION;
import org.openehr.schemas.v1.ARCHETYPEONTOLOGY;
import org.openehr.schemas.v1.ARCHETYPESLOT;
import org.openehr.schemas.v1.ARCHETYPETERM;
import org.openehr.schemas.v1.CARCHETYPEROOT;
import org.openehr.schemas.v1.CARDINALITY;
import org.openehr.schemas.v1.CATTRIBUTE;
import org.openehr.schemas.v1.CCODEPHRASE;
import org.openehr.schemas.v1.CCODEREFERENCE;
import org.openehr.schemas.v1.CCOMPLEXOBJECT;
import org.openehr.schemas.v1.CDOMAINTYPE;
import org.openehr.schemas.v1.CDVORDINAL;
import org.openehr.schemas.v1.CDVQUANTITY;
import org.openehr.schemas.v1.CDVSTATE;
import org.openehr.schemas.v1.CMULTIPLEATTRIBUTE;
import org.openehr.schemas.v1.COBJECT;
import org.openehr.schemas.v1.CODEPHRASE;
import org.openehr.schemas.v1.CPRIMITIVEOBJECT;
import org.openehr.schemas.v1.CSINGLEATTRIBUTE;
import org.openehr.schemas.v1.DVCODEDTEXT;
import org.openehr.schemas.v1.DVORDINAL;
import org.openehr.schemas.v1.DVQUANTITY;
import org.openehr.schemas.v1.FLATARCHETYPEONTOLOGY;
import org.openehr.schemas.v1.IntervalOfInteger;
import org.openehr.schemas.v1.OBJECTID;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.RESOURCEDESCRIPTIONITEM;
import org.openehr.schemas.v1.StringDictionaryItem;
import org.openehr.schemas.v1.TATTRIBUTE;
import org.openehr.schemas.v1.TCOMPLEXOBJECT;
import org.openehr.schemas.v1.TCONSTRAINT;
import org.openehr.schemas.v1.TemplateDocument;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OPTParser {

    public static final String PATH_DIVIDER = "/";
    public static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();
    public static final String CAREFLOW_STEP = "careflow_step";
    public static final String CODED_TEXT = "CODED_TEXT";
    public static final String OPENEHR = "openehr";
    public static final String CURRENT_STATE = "current_state";

    private static final Set<String> LOCATABLE_TYPES =
            ArchieRMInfoLookup.getInstance().getTypeInfo(Locatable.class).getAllDescendantClasses().stream()
                    .map(RMTypeInfo::getRmName)
                    .collect(Collectors.toSet());

    private final OPERATIONALTEMPLATE operationaltemplate;
    private final String defaultLanguage;
    private final Map<String, String> defaultValues = new HashMap<>();
    private final InputHandler inputHandler = new InputHandler(defaultValues);
    private final Map<String, Map<String, String>> annotationMap = new HashMap<>();
    private List<String> languages;

    private final Map<String, String> choiceIdCache = new HashMap<>();

    private boolean updateChoiceId(WebTemplateNode node) {
        String rmType = node.getRmType();
        if (rmType.startsWith("DV_")) {
            node.setId(choiceIdCache.computeIfAbsent(rmType, t -> t.substring(3).toLowerCase() + "_value"));
            return true;
        } else {
            return false;
        }
    }

    public OPTParser(OPERATIONALTEMPLATE operationaltemplate) {
        this.operationaltemplate = operationaltemplate;
        defaultLanguage = operationaltemplate.getLanguage().getCodeString();
        Optional.ofNullable(operationaltemplate.getConstraints()).map(TCONSTRAINT::getAttributesArray).stream()
                .flatMap(Arrays::stream)
                .map(this::extractDefault)
                .flatMap(List::stream)
                .forEach(p -> defaultValues.put(p.getKey(), p.getValue()));
    }

    public static WebTemplate parse(OPERATIONALTEMPLATE operationaltemplate) {
        return new OPTParser(operationaltemplate).parse();
    }

    public static WebTemplate parse(InputStream in) throws XmlException, IOException {
        try (in) {
            return parse(TemplateDocument.Factory.parse(in).getTemplate());
        }
    }

    public WebTemplate parse() {
        WebTemplate webTemplate = new WebTemplate();

        webTemplate.setTemplateId(operationaltemplate.getTemplateId().getValue());
        webTemplate.setDefaultLanguage(defaultLanguage);
        webTemplate.setVersion("2.3");
        webTemplate
                .getLanguages()
                .addAll(Arrays.stream(operationaltemplate.getDescription().getDetailsArray())
                        .map(RESOURCEDESCRIPTIONITEM::getLanguage)
                        .map(CODEPHRASE::getCodeString)
                        .collect(Collectors.toSet()));

        languages = webTemplate.getLanguages();

        for (ANNOTATION annotation : operationaltemplate.getAnnotationsArray()) {
            Map<String, String> items = new HashMap<>();
            for (StringDictionaryItem item : annotation.getItemsArray()) {
                items.put(item.getId(), item.getStringValue());
            }
            // remove archetype id from path because nodes do not contain that in their path
            annotationMap.put(annotation.getPath().replaceAll("^[^/]+", ""), items);
        }

        webTemplate.setTree(parseCARCHETYPEROOT(operationaltemplate.getDefinition(), AqlPath.EMPTY_PATH)
                .get(0));
        return webTemplate;
    }

    static Stream<XmlObject> extractChildren(XmlObject c, String attributes) {
        return Arrays.stream(c.selectChildren("http://schemas.openehr.org/v1", attributes));
    }

    private List<Pair<String, String>> extractDefault(TATTRIBUTE xmlObject) {
        // XXX performance List<Pair<AqlPath, String>>
        List<Pair<String, String>> defaults = new ArrayList<>();

        String differentialPath = StringUtils.substringAfter(xmlObject.getDifferentialPath(), "/");
        String rmAttributeName = xmlObject.getRmAttributeName();
        AqlPath aqlPath = AqlPath.EMPTY_PATH.addEnd(differentialPath, rmAttributeName);

        // Instead of handling each subtype of DATAVALUE ist handheld generic since each child is an
        // attribute of the DATAVALUE
        Arrays.stream(xmlObject.getChildrenArray())
                .map(TCOMPLEXOBJECT::getDefaultValue)
                .map(XmlTokenSource::newDomNode)
                .map(Node::getFirstChild)
                .map(Node::getChildNodes)
                .flatMap(OPTParser::elementNodes)
                .map(Node::getNodeName)
                .forEach(nodeName -> addDefault(xmlObject, nodeName, aqlPath, defaults));
        return defaults;
    }

    private static void addDefault(
            TATTRIBUTE xmlObject, String nodeName, AqlPath aqlPath, List<Pair<String, String>> defaults) {
        childByName(xmlObject, nodeName).ifPresent(any -> {
            String key;
            String defaultValue;
            if (any.newDomNode().getFirstChild().getChildNodes().getLength() == 1) {
                defaultValue = any.newCursor().getTextValue();
                key = aqlPath + "|" + nodeName;
            } else {
                defaultValue = extractChildren(any, "defining_code")
                        .flatMap(x -> extractChildren(x, "code_string"))
                        .map(XmlTokenSource::newCursor)
                        .map(XmlCursor::getTextValue)
                        .findAny()
                        .orElse("");
                key = aqlPath + "|" + "defining_code";
            }
            Pair<String, String> defaultPair = Pair.of(key, defaultValue);
            defaults.add(defaultPair);
        });
    }

    private static Optional<XmlObject> childByName(TATTRIBUTE xmlObject, String nodeName) {
        return Arrays.stream(xmlObject.getChildrenArray())
                .map(TCOMPLEXOBJECT::getDefaultValue)
                .flatMap(x -> extractChildren(x, nodeName))
                .findAny();
    }

    private static Stream<Node> elementNodes(NodeList list) {
        return IntStream.range(0, list.getLength())
                .mapToObj(list::item)
                .filter(item -> item.getNodeType() == Node.ELEMENT_NODE);
    }

    private List<WebTemplateNode> parseCARCHETYPEROOT(CARCHETYPEROOT carchetyperoot, AqlPath aqlPath) {

        // extract local Terminologies
        Map<String, Map<String, TermDefinition>> termDefinitionMap = new HashMap<>();
        for (ARCHETYPETERM term : carchetyperoot.getTermDefinitionsArray()) {
            String code = term.getCode();
            String value = null;
            String description = null;
            Map<String, String> otherMap = new HashMap<>();
            for (StringDictionaryItem item : term.getItemsArray()) {
                if ("text".equals(item.getId())) {
                    value = item.getStringValue();
                } else if ("description".equals(item.getId())) {
                    description = item.getStringValue();
                } else {
                    otherMap.put(item.getId(), item.getStringValue());
                }
            }
            termDefinitionMap
                    .computeIfAbsent(code, c -> new HashMap<>())
                    .put(defaultLanguage, new TermDefinition(code, value, description, otherMap));
        }

        Map<String, Map<String, TermDefinition>> otherTermDefinitionMap =
                buildOtherTerms(carchetyperoot.getArchetypeId().getValue());
        otherTermDefinitionMap.forEach((key, value) ->
                termDefinitionMap.computeIfAbsent(key, c -> new HashMap<>()).putAll(value));

        List<WebTemplateNode> nodes = parseCCOMPLEXOBJECT(carchetyperoot, aqlPath, termDefinitionMap, null);
        nodes.forEach(node -> {
            node.setNodeId(carchetyperoot.getArchetypeId().getValue());
            addRMAttributes(node, aqlPath, termDefinitionMap);
        });

        return nodes;
    }

    private Map<String, Map<String, TermDefinition>> buildOtherTerms(String archetypeId) {
        Map<String, Map<String, TermDefinition>> otherTermDefinitionMap = new HashMap<>();

        Stream<FLATARCHETYPEONTOLOGY> rootOntologies = Optional.ofNullable(operationaltemplate.getOntology()).stream();
        Stream<FLATARCHETYPEONTOLOGY> componentOntologies =
                Arrays.stream(operationaltemplate.getComponentOntologiesArray());

        Stream.concat(rootOntologies, componentOntologies)
                .filter(x -> Objects.equals(x.getArchetypeId(), archetypeId))
                .map(ARCHETYPEONTOLOGY::getTermDefinitionsArray)
                .flatMap(Arrays::stream)
                .forEach(term -> {
                    for (ARCHETYPETERM items : term.getItemsArray()) {
                        TermDefinition termDefinition = toTermDefinition(items);

                        Map<String, TermDefinition> termDefinitionByLanguage =
                                otherTermDefinitionMap.computeIfAbsent(termDefinition.getCode(), c -> new HashMap<>());
                        termDefinitionByLanguage.put(term.getLanguage(), termDefinition);
                    }
                });

        return otherTermDefinitionMap;
    }

    private static TermDefinition toTermDefinition(ARCHETYPETERM items) {
        String text = "";
        String description = "";
        for (StringDictionaryItem item : items.getItemsArray()) {
            switch (item.getId()) {
                case "text" -> text = item.getStringValue();
                case "description" -> description = item.getStringValue();
                default -> {
                    /*noop*/
                }
            }
        }
        return new TermDefinition(items.getCode(), text, description);
    }

    private record Name(String label, Map<String, String> localizedLabels) {}

    private List<WebTemplateNode> parseCCOMPLEXOBJECT(
            CCOMPLEXOBJECT ccomplexobject,
            AqlPath aqlPath,
            Map<String, Map<String, TermDefinition>> termDefinitionMap,
            String rmAttributeName) {

        Optional<WebTemplateNode> nameNode = buildNameNode(aqlPath, termDefinitionMap, ccomplexobject);

        List<Name> nameValues = nameNode.map(WebTemplateNode::getInputs).stream()
                .flatMap(List::stream)
                .filter(i -> i.getSuffix() == null || i.getSuffix().equals("code"))
                .map(WebTemplateInput::getList)
                .flatMap(List::stream)
                .map(webTemplateInputValue ->
                        new Name(webTemplateInputValue.getLabel(), webTemplateInputValue.getLocalizedLabels()))
                .toList();

        if (nameValues.size() > 1) {
            return nameValues.stream()
                    .map(e -> {
                        WebTemplateNode node =
                                buildNodeWithName(ccomplexobject, aqlPath, termDefinitionMap, rmAttributeName, e);
                        Optional<WebTemplateNode> localNameNode = nameNode.map(WebTemplateNode::new);
                        localNameNode.ifPresent(n -> setExplicitName(e, node, n));
                        addAnnotations(node, aqlPath.toString(), annotationMap);
                        parseComplexObjectSingle(
                                ccomplexobject, node.getAqlPathDto(), termDefinitionMap, localNameNode, node);
                        return node;
                    })
                    .toList();

        } else {
            Optional<Name> explicitName = nameValues.stream().findAny();

            WebTemplateNode node = buildNodeWithName(
                    ccomplexobject, aqlPath, termDefinitionMap, rmAttributeName, explicitName.orElse(null));

            nameNode.ifPresent(n -> setExplicitName(explicitName.orElse(null), node, n));
            addAnnotations(node, aqlPath.toString(), annotationMap);
            parseComplexObjectSingle(ccomplexobject, node.getAqlPathDto(), termDefinitionMap, nameNode, node);
            return Collections.singletonList(node);
        }
    }

    private void addAnnotations(WebTemplateNode node, String aqlPath, Map<String, Map<String, String>> annotationMap) {
        Optional.of(aqlPath).map(annotationMap::get).ifPresent(annotationItems -> {
            Optional.of(node)
                    .map(WebTemplateNode::getAnnotations)
                    .orElseGet(() -> {
                        var a = new WebTemplateAnnotation();
                        node.setAnnotations(a);
                        return a;
                    })
                    .getOther()
                    .putAll(annotationItems);
        });
    }

    private void setExplicitName(Name explicitName, WebTemplateNode node, WebTemplateNode n) {
        n.setAqlPath(node.getAqlPathDto().addEnd("name"));

        Optional.ofNullable(explicitName).ifPresent(s -> {
            n.getInputs().stream()
                    .filter(i -> i.getSuffix() == null || i.getSuffix().equals("code"))
                    .findAny()
                    .ifPresent(i -> {
                        List<WebTemplateInputValue> list = new ArrayList<>(i.getList());
                        i.getList().clear();
                        list.stream().filter(v -> s.label.equals(v.getLabel())).forEach(i.getList()::add);
                    });

            n.findChildById("defining_code").ifPresent(d -> {
                d.getInputs().clear();
                n.getInputs().stream().map(WebTemplateInput::new).forEach(d.getInputs()::add);
            });
        });
    }

    private WebTemplateNode buildNodeWithName(
            CCOMPLEXOBJECT ccomplexobject,
            AqlPath aqlPath,
            Map<String, Map<String, TermDefinition>> termDefinitionMap,
            String rmAttributeName,
            Name explicitName) {
        WebTemplateNode node = buildNode(ccomplexobject, rmAttributeName, termDefinitionMap, explicitName);
        node.setAqlPath(aqlPath);

        if (StringUtils.isNotBlank(ccomplexobject.getNodeId()) && explicitName != null) {
            String nameValue = explicitName.label;
            node.setAqlPath(node.getAqlPathDto().replaceLastNode(n -> n.withNameValue(nameValue)));
        }
        return node;
    }

    private void parseComplexObjectSingle(
            CCOMPLEXOBJECT ccomplexobject,
            AqlPath aqlPath,
            Map<String, Map<String, TermDefinition>> termDefinitionMap,
            Optional<WebTemplateNode> nameNode,
            WebTemplateNode node) {
        Map<String, WebTemplateInput> inputMap = new HashMap<>();
        List<Pair<WebtemplateCardinality, List<AqlPath>>> cardinaltyList = new ArrayList<>();

        for (CATTRIBUTE cattribute : ccomplexobject.getAttributesArray()) {
            AqlPath pathLoop = aqlPath.addEnd(cattribute.getRmAttributeName());
            if (
            // Will be set via Attributes
            pathLoop.getLastNode().getName().equals("name")) {
                continue;
            }

            List<WebTemplateNode> newChildren = new ArrayList<>();
            for (COBJECT cobject : cattribute.getChildrenArray()) {

                if (cobject instanceof CPRIMITIVEOBJECT cprimitiveobject) {
                    inputMap.put(cattribute.getRmAttributeName(), inputHandler.extractInput(cprimitiveobject));
                } else {
                    List<WebTemplateNode> childNode =
                            parseCOBJECT(cobject, pathLoop, termDefinitionMap, cattribute.getRmAttributeName());

                    if (cobject instanceof ARCHETYPESLOT) {
                        childNode.forEach(c -> c.setArchetypeSlot(true));
                    }
                    newChildren.addAll(childNode);
                }

                if (cattribute instanceof CSINGLEATTRIBUTE
                        && cattribute.getExistence().getLower() == 0) {
                    WebtemplateCardinality cardinality = new WebtemplateCardinality();
                    cardinality.setMin(cattribute.getExistence().getLower());
                    cardinality.setMax(cattribute.getExistence().getUpper());
                    cardinality.setExcludeFromWebTemplate(Boolean.TRUE);

                    newChildren.forEach(c -> cardinality.getIds().add(c.getId()));

                    node.getCardinalities().add(cardinality);
                }
            }

            List<WebTemplateNode> ismTransitionList = newChildren.stream()
                    .filter(n -> RmConstants.ISM_TRANSITION.equals(n.getRmType()))
                    .toList();
            if (!ismTransitionList.isEmpty()) {
                WebTemplateNode firstChild = ismTransitionList.get(0);
                WebTemplateNode ismTransition = new WebTemplateNode();
                ismTransition.setName(cattribute.getRmAttributeName());
                ismTransition.setId(buildId(cattribute.getRmAttributeName()));
                ismTransition.setMin(firstChild.getMin());
                ismTransition.setMax(firstChild.getMax());
                ismTransition.setRmType(RmConstants.ISM_TRANSITION);
                ismTransition.setInContext(true);
                ismTransition.setAqlPath(aqlPath.addEnd(cattribute.getRmAttributeName()));

                WebTemplateNode careflowStep = new WebTemplateNode();
                WebTemplateNode careflowStepProto = firstChild
                        .findChildById(CAREFLOW_STEP)
                        .orElseThrow(() -> new SdkException(String.format("Missing node: %s", CAREFLOW_STEP)));
                careflowStep.setMin(careflowStepProto.getMin());
                careflowStep.setMax(careflowStepProto.getMin());
                careflowStep.setName("Careflow_step");
                careflowStep.setId(CAREFLOW_STEP);
                careflowStep.setRmType(RmConstants.DV_CODED_TEXT);
                careflowStep.setInContext(true);
                careflowStep.setAqlPath(aqlPath.addEnd(cattribute.getRmAttributeName(), CAREFLOW_STEP));
                WebTemplateInput code = new WebTemplateInput();
                code.setSuffix("code");
                code.setType(CODED_TEXT);
                code.setTerminology("local");

                ismTransitionList.forEach(i -> {
                    WebTemplateInputValue value = i.findChildById(CAREFLOW_STEP)
                            .get()
                            .getInputs()
                            .get(0)
                            .getList()
                            .get(0);
                    value.getCurrentStates()
                            .addAll(i.findChildById(CURRENT_STATE).get().getInputs().get(0).getList().stream()
                                    .map(WebTemplateInputValue::getValue)
                                    .toList());
                    code.getList().add(value);
                });
                careflowStep.getInputs().add(code);
                ismTransition.getChildren().add(careflowStep);

                WebTemplateNode currentState = new WebTemplateNode();
                WebTemplateNode currentStateProto =
                        firstChild.findChildById(CURRENT_STATE).orElseThrow();
                currentState.setMin(currentStateProto.getMin());
                currentState.setMax(currentStateProto.getMin());
                currentState.setRmType(RmConstants.DV_CODED_TEXT);
                currentState.setName("Current_state");
                currentState.setId(CURRENT_STATE);
                currentState.setInContext(true);
                currentState.setAqlPath(aqlPath.addEnd(cattribute.getRmAttributeName(), CURRENT_STATE));
                WebTemplateInput code2 = new WebTemplateInput();
                code2.setSuffix("code");
                code2.setType(CODED_TEXT);
                code2.setTerminology(OPENEHR);
                code2.getList()
                        .addAll(ismTransitionList.stream()
                                .flatMap(n -> n.findChildById(CURRENT_STATE).stream())
                                .map(WebTemplateNode::getInputs)
                                .flatMap(List::stream)
                                .map(WebTemplateInput::getList)
                                .flatMap(List::stream)
                                .toList());
                currentState.getInputs().add(code2);
                ismTransition.getChildren().add(currentState);
                WebTemplateNode transition =
                        firstChild.findChildById("transition").orElseThrow();
                transition.setAqlPath(aqlPath.addEnd(cattribute.getRmAttributeName(), "transition"));
                transition.setInContext(true);
                ismTransition.getChildren().add(transition);
                node.getChildren().add(ismTransition);
            }

            Optional.of(cattribute)
                    .filter(CMULTIPLEATTRIBUTE.class::isInstance)
                    .map(CMULTIPLEATTRIBUTE.class::cast)
                    .map(CMULTIPLEATTRIBUTE::getCardinality)
                    .map(this::buildCardinality)
                    .map(webtemplateCardinality -> {
                        List<AqlPath> paths = newChildren.stream()
                                .map(WebTemplateNode::getAqlPathDto)
                                .collect(Collectors.toList());
                        return Pair.of(webtemplateCardinality, paths);
                    })
                    .ifPresent(cardinaltyList::add);

            // Add missing names to aqlPath if needed
            newChildren.stream()
                    // node does not already have name in aql
                    .filter(c -> !node.isRelativePathNameDependent(c))
                    // there  exist a node which matches the aql without name but not with name
                    .filter(c -> newChildren.stream()
                            .filter(n -> !n.getAqlPathDto().equals(c.getAqlPathDto()))
                            .anyMatch(n -> n.getAqlPathDto().equals(c.getAqlPathDto(), false)))
                    .forEach(c -> {
                        AqlPath path = AqlPath.parse(c.getAqlPath(true), c.getName());
                        c.getChildren().forEach(n -> replaceParentAql(n, c.getAqlPathDto(), path));

                        c.setAqlPath(path);
                    });

            node.getChildren().addAll(newChildren);
        }

        // Handle choice children
        Collection<List<WebTemplateNode>> values = node.getChoicesInChildren().values();
        values.stream().flatMap(List::stream).forEach(this::updateChoiceId);

        // Inherit name for Element values
        if (node.getRmType().equals(RmConstants.ELEMENT)) {
            // Is any Node
            if (node.getChildren().isEmpty()) {

                Stream.of(
                                RmConstants.DV_TEXT,
                                RmConstants.DV_CODED_TEXT,
                                RmConstants.DV_MULTIMEDIA,
                                RmConstants.DV_PARSABLE,
                                RmConstants.DV_STATE,
                                RmConstants.DV_BOOLEAN,
                                RmConstants.DV_IDENTIFIER,
                                RmConstants.DV_URI,
                                RmConstants.DV_EHR_URI,
                                RmConstants.DV_DURATION,
                                RmConstants.DV_QUANTITY,
                                RmConstants.DV_COUNT,
                                RmConstants.DV_PROPORTION,
                                RmConstants.DV_DATE_TIME,
                                RmConstants.DV_TIME,
                                RmConstants.DV_ORDINAL,
                                RmConstants.DV_DATE)
                        .forEach(t -> addAnyNode(node, t, inputMap));

            } else {
                List<WebTemplateNode> trueChildren = WebTemplateUtils.getTrueChildrenElement(node);
                trueChildren.forEach(c -> pushProperties(node, c));

                // choice between value and null_flavour
                if (trueChildren.size() != 1 && node.getChoicesInChildren().isEmpty()) {
                    WebTemplateUtils.getTrueChildrenElement(node).stream()
                            .filter(this::updateChoiceId)
                            .forEach(n -> {
                                n.getLocalizedDescriptions().putAll(node.getLocalizedDescriptions());
                                n.getLocalizedNames().putAll(node.getLocalizedNames());
                            });
                }
            }
        }

        // Push inputs for DV_CODED_TEXT up
        if (node.getRmType().equals(RmConstants.DV_CODED_TEXT)) {
            List<WebTemplateNode> matching =
                    node.findMatching(n -> n.getRmType().equals(RmConstants.CODE_PHRASE));
            List<WebTemplateInput> inputs = node.getInputs();
            if (matching.isEmpty()) {
                inputs.add(InputHandler.buildWebTemplateInput("value", "TEXT"));
                inputs.add(InputHandler.buildWebTemplateInput("code", "TEXT"));
            } else {
                inputs.addAll(matching.get(0).getInputs());
            }
        }

        nameNode.ifPresent(node.getChildren()::add);
        addRMAttributes(node, aqlPath, termDefinitionMap);

        // If inputs where not set from the template set them via rmType
        if (node.getInputs().isEmpty()) {
            inputHandler.addInputs(node, inputMap);
        }

        makeIdUnique(node);

        cardinaltyList.forEach(p -> {
            List<String> nodeIds = p.getValue().stream()
                    .map(s -> node.findMatching(n -> n.getAqlPathDto().equals(s)))
                    .flatMap(List::stream)
                    .map(WebTemplateNode::getId)
                    .toList();
            // only add non-trivial cardinalities.
            if ((p.getKey().getMax() != null
                            && p.getKey().getMax() != -1
                            && p.getKey().getMax() < nodeIds.size())
                    || (p.getKey().getMin() != null && p.getKey().getMin() > 1)) {
                p.getKey().getIds().addAll(nodeIds);
                node.getCardinalities().add(p.getKey());
            }
        });

        node.getChildren().forEach(child -> addInContext(node, child));
    }

    private void replaceParentAql(WebTemplateNode node, AqlPath oldBase, AqlPath newBase) {
        AqlPath childAql = node.getAqlPathDto();

        AqlPath relativeAql = childAql.removeStart(oldBase);

        node.setAqlPath(newBase.addEnd(relativeAql));

        node.getChildren().forEach(n -> replaceParentAql(n, childAql, node.getAqlPathDto()));
    }

    private Optional<WebTemplateNode> buildNameNode(
            AqlPath aqlPath,
            Map<String, Map<String, TermDefinition>> termDefinitionMap,
            CCOMPLEXOBJECT ccomplexobject) {

        return Arrays.stream(ccomplexobject.getAttributesArray())
                .filter(c -> c.getRmAttributeName().equals("name"))
                .filter(c -> c.getChildrenArray().length == 1)
                .map(c -> parseCOBJECT(c.getChildrenArray(0), aqlPath, termDefinitionMap, c.getRmAttributeName()))
                .flatMap(List::stream)
                .findAny();
    }

    private void addAnyNode(WebTemplateNode node, String rmType, Map<String, WebTemplateInput> inputMap) {
        WebTemplateNode subNode = new WebTemplateNode();
        subNode.setRmType(rmType);
        updateChoiceId(subNode);
        subNode.setName(node.getName());
        subNode.setAqlPath(node.getAqlPathDto().addEnd("value"));
        subNode.setInContext(true);
        subNode.setMax(1);
        subNode.setMin(0);
        subNode.setLocalizedName(node.getLocalizedName());
        subNode.getLocalizedDescriptions().putAll(node.getLocalizedDescriptions());
        subNode.getLocalizedNames().putAll(node.getLocalizedNames());
        inputHandler.addInputs(subNode, inputMap);
        node.getChildren().add(subNode);
    }

    private void addInContext(WebTemplateNode node, WebTemplateNode child) {

        Map<Class<?>, List<String>> contextAttributes = Map.of(
                Locatable.class,
                List.of("language"),
                Action.class,
                List.of("time"),
                Activity.class,
                List.of("timing", "action_archetype_id"),
                Instruction.class,
                List.of("narrative"),
                IsmTransition.class,
                List.of(CURRENT_STATE, CAREFLOW_STEP, "transition"),
                History.class,
                List.of("origin"),
                Event.class,
                List.of("time"),
                Entry.class,
                List.of("language", "provider", "other_participations", "subject", "encoding"),
                EventContext.class,
                List.of("start_time", "end_time", "location", "setting", "healthCareFacility", "participations"),
                Composition.class,
                List.of("language", "territory", "composer", "category"));

        RMTypeInfo typeInfo = ARCHIE_RM_INFO_LOOKUP.getTypeInfo(node.getRmType());
        if (typeInfo != null) {
            contextAttributes.forEach((k, v) -> {
                if (k.isAssignableFrom(typeInfo.getJavaClass()) && v.contains(child.getId())) {
                    child.setInContext(true);
                }
            });
        }
    }

    private WebtemplateCardinality buildCardinality(CARDINALITY xmlObject) {
        WebtemplateCardinality webtemplateCardinality = new WebtemplateCardinality();
        if (xmlObject.getInterval() != null) {
            if (!xmlObject.getInterval().getLowerUnbounded()) {
                Optional.of(xmlObject)
                        .map(CARDINALITY::getInterval)
                        .map(IntervalOfInteger::getLower)
                        .ifPresent(webtemplateCardinality::setMin);
            }
            if (!xmlObject.getInterval().getUpperUnbounded()) {
                Optional.of(xmlObject)
                        .map(CARDINALITY::getInterval)
                        .map(IntervalOfInteger::getUpper)
                        .ifPresent(webtemplateCardinality::setMax);
            } else {
                webtemplateCardinality.setMax(-1);
            }
        }
        return webtemplateCardinality;
    }

    private void pushProperties(WebTemplateNode node, WebTemplateNode value) {
        value.setNodeId(node.getNodeId());
        value.setAnnotations(node.getAnnotations());
        value.setName(node.getName());
        value.getLocalizedDescriptions().putAll(node.getLocalizedDescriptions());
        value.getLocalizedNames().putAll(node.getLocalizedNames());
        value.setLocalizedName(node.getLocalizedName());
    }

    public static void makeIdUnique(WebTemplateNode node) {
        // Make ids unique in for a children
        node.getChildren().stream()
                .collect(Collectors.groupingBy(n -> n.getId(false)))
                .values()
                .forEach(l -> {
                    if (l.size() > 1) {
                        for (int i = 0; i < l.size(); i++) {
                            if (i > 0) {
                                WebTemplateNode n = l.get(i);
                                int optionalIdNumber = i + 1;
                                n.setOptionalIdNumber(optionalIdNumber);

                                if (RmConstants.ELEMENT.equals(n.getRmType())) {
                                    n.getChildren().stream()
                                            .filter(c -> c.getId().equals(n.getId(false)))
                                            .forEach(c -> c.setOptionalIdNumber(optionalIdNumber));
                                }
                            }
                        }
                    } else {
                        l.get(0).setOptionalIdNumber(null);
                    }
                });
    }

    private void addRMAttributes(
            WebTemplateNode node, AqlPath aqlPath, Map<String, Map<String, TermDefinition>> termDefinitionMap) {
        // Add RM Attributes
        RMTypeInfo typeInfo = ARCHIE_RM_INFO_LOOKUP.getTypeInfo(node.getRmType());
        if (typeInfo == null) {
            return;
        }
        Class<?> javaClass = typeInfo.getJavaClass();
        if (Pathable.class.isAssignableFrom(javaClass) || DvInterval.class.isAssignableFrom(javaClass)) {

            node.getChildren()
                    .addAll(typeInfo.getAttributes().values().stream()
                            .filter(s -> !s.isComputed())
                            // EVENT.offset is not marked computed in archie
                            .filter(s -> !(Event.class.isAssignableFrom(javaClass) && "offset".equals(s.getRmName())))
                            .filter(s -> !(Element.class.isAssignableFrom(javaClass)
                                    && !List.of("name", "feeder_audit", "null_flavour")
                                            .contains(s.getRmName())))
                            .filter(s -> !"value".equals(s.getRmName()))
                            .filter(s -> !Locatable.class.isAssignableFrom(s.getTypeInCollection()))
                            .filter(s ->
                                    !(DvInterval.class.isAssignableFrom(javaClass) && "interval".equals(s.getRmName())))
                            .map(i -> buildNodeForAttribute(i, aqlPath, termDefinitionMap))
                            // only add if not already there
                            .filter(n -> node.getChildren().stream()
                                    .map(WebTemplateNode::getId)
                                    .noneMatch(s -> s.equals(n.getId())))
                            .toList());
        }
    }

    private WebTemplateNode buildNodeForAttribute(
            RMAttributeInfo attributeInfo,
            AqlPath aqlPath,
            Map<String, Map<String, TermDefinition>> termDefinitionMap) {
        WebTemplateNode node = new WebTemplateNode();
        node.setAqlPath(aqlPath.addEnd(attributeInfo.getRmName()));
        node.setName(attributeInfo.getRmName());
        node.setId(buildId(attributeInfo.getRmName()));
        node.setRmType(attributeInfo.getTypeNameInCollection());
        node.setMax(attributeInfo.isMultipleValued() ? -1 : 1);
        node.setMin(attributeInfo.isNullable() ? 0 : 1);
        if (List.of("action_archetype_id", "math_function").contains(attributeInfo.getRmName())) {
            node.setMin(1);
        }

        inputHandler.addInputs(node, Collections.emptyMap());

        addRMAttributes(node, node.getAqlPathDto(), termDefinitionMap);
        return node;
    }

    private List<WebTemplateNode> parseCOBJECT(
            COBJECT cobject,
            AqlPath aqlPath,
            Map<String, Map<String, TermDefinition>> termDefinitionMap,
            String rmAttributeName) {

        if (cobject instanceof CARCHETYPEROOT carchetyperoot) {
            String nodeId = carchetyperoot.getArchetypeId().getValue();
            final AqlPath pathLoop;
            if (StringUtils.isNotBlank(nodeId)) {
                pathLoop = aqlPath.replaceLastNode(n -> n.withAtCode(nodeId));
            } else {
                pathLoop = aqlPath;
            }
            return parseCARCHETYPEROOT(carchetyperoot, pathLoop);

        } else if (cobject instanceof CCOMPLEXOBJECT ccomplexobject) {
            String nodeId = isLocatableNode(ccomplexobject) ? ccomplexobject.getNodeId() : null;
            final AqlPath pathLoop;
            if (StringUtils.isNotBlank(nodeId)) {
                pathLoop = aqlPath.replaceLastNode(n -> n.withAtCode(nodeId));
            } else {
                pathLoop = aqlPath;
            }
            return parseCCOMPLEXOBJECT(ccomplexobject, pathLoop, termDefinitionMap, rmAttributeName);

        } else if (cobject instanceof CDOMAINTYPE cdomaintype) {
            String nodeId = isLocatableNode(cdomaintype) ? cdomaintype.getNodeId() : null;
            final AqlPath pathLoop;
            if (StringUtils.isNotBlank(nodeId)) {
                pathLoop = aqlPath.replaceLastNode(n -> n.withAtCode(nodeId));
            } else {
                pathLoop = aqlPath;
            }
            return Collections.singletonList(
                    parseCDOMAINTYPE(cdomaintype, pathLoop, termDefinitionMap, rmAttributeName));
        } else if (cobject instanceof ARCHETYPESLOT archetypeslot) {
            String nodeId = archetypeslot.getNodeId();
            final AqlPath pathLoop;
            if (StringUtils.isNotBlank(nodeId)) {
                pathLoop = aqlPath.replaceLastNode(n -> n.withAtCode(nodeId));
            } else {
                pathLoop = aqlPath;
            }
            return Collections.singletonList(
                    parseARCHETYPESLOT(archetypeslot, pathLoop, termDefinitionMap, rmAttributeName));
        }

        // this validation must be removed after xsd will be updated and DV_SCALE will be implemented
        if ("DV_SCALE".equals(cobject.getRmTypeName())) {
            throw new IllegalArgumentException(
                    MessageFormat.format("The supplied template is not supported: Unsupported type {0}.", "DV_SCALE"));
        }

        return List.of();
    }

    /**
     * This check is a workaround for a bug in the archetype designer, due to which non-LOCATABLEs may have a node_id.
     * The generated opt is invalid, but we still accept it and ignore the node_id in this case.
     * @return true if the given nodes rm_type_name is a LOCATABLE type name
     */
    private static boolean isLocatableNode(final COBJECT cobject) {
        return LOCATABLE_TYPES.contains(cobject.getRmTypeName());
    }

    private WebTemplateNode parseARCHETYPESLOT(
            ARCHETYPESLOT cobject,
            AqlPath pathLoop,
            Map<String, Map<String, TermDefinition>> termDefinitionMap,
            String rmAttributeName) {
        WebTemplateNode node = buildNode(cobject, rmAttributeName, termDefinitionMap, null);
        node.setAqlPath(pathLoop);
        return node;
    }

    private WebTemplateNode parseCDOMAINTYPE(
            CDOMAINTYPE cdomaintype,
            AqlPath aqlPath,
            Map<String, Map<String, TermDefinition>> termDefinitionMap,
            String rmAttributeName) {

        WebTemplateNode node = buildNode(cdomaintype, rmAttributeName, termDefinitionMap, null);
        node.setAqlPath(aqlPath);
        if (cdomaintype instanceof CDVSTATE) {
            throw new SdkException(
                    String.format("Unexpected class: %s", cdomaintype.getClass().getSimpleName()));
        } else if (cdomaintype instanceof CDVQUANTITY cdvquantity) {

            WebTemplateInput magnitude = new WebTemplateInput();
            magnitude.setSuffix("magnitude");
            magnitude.setType("DECIMAL");
            Optional.of(cdvquantity)
                    .map(CDVQUANTITY::getAssumedValue)
                    .map(DVQUANTITY::getMagnitude)
                    .map(d -> Double.toString(d))
                    .ifPresent(magnitude::setDefaultValue);
            inputHandler.findDefaultValue(node, "magnitude").ifPresent(magnitude::setDefaultValue);
            node.getInputs().add(magnitude);

            WebTemplateInput unit = new WebTemplateInput();
            unit.setSuffix("unit");
            unit.setType(CODED_TEXT);
            Optional.of(cdvquantity)
                    .map(CDVQUANTITY::getAssumedValue)
                    .map(DVQUANTITY::getUnits)
                    .ifPresent(unit::setDefaultValue);
            inputHandler.findDefaultValue(node, "units").ifPresent(unit::setDefaultValue);
            node.getInputs().add(unit);

            Arrays.stream(cdvquantity.getListArray()).forEach(o -> {
                WebTemplateInputValue value = new WebTemplateInputValue();
                value.setLabel(o.getUnits());
                value.setValue(o.getUnits());

                WebTemplateValidation validation = new WebTemplateValidation();
                boolean addValidation = false;
                if (o.getMagnitude() != null) {
                    addValidation = true;
                    validation.setRange(inputHandler.extractInterval(o.getMagnitude()));
                }
                if (o.getPrecision() != null) {
                    addValidation = true;
                    validation.setPrecision(inputHandler.extractInterval(o.getPrecision()));
                }
                if (addValidation) {
                    value.setValidation(validation);
                }
                value.getLocalizedLabels()
                        .putAll(termDefinitionMap.getOrDefault(o.getUnits(), Collections.emptyMap()).entrySet().stream()
                                .collect(Collectors.toMap(
                                        Map.Entry::getKey, e -> e.getValue().getValue())));
                unit.getList().add(value);
            });
            if (unit.getList().size() == 1) {
                magnitude.setValidation(unit.getList().get(0).getValidation());
            }
        } else if (cdomaintype instanceof CDVORDINAL cdvordinal) {
            WebTemplateInput code = new WebTemplateInput();
            inputHandler.findDefaultValue(node, "defining_code").ifPresent(code::setDefaultValue);
            code.setType(CODED_TEXT);
            node.getInputs().add(code);
            Optional.of(cdvordinal)
                    .map(CDVORDINAL::getAssumedValue)
                    .map(DVORDINAL::getSymbol)
                    .map(DVCODEDTEXT::getDefiningCode)
                    .map(CODEPHRASE::getCodeString)
                    .ifPresent(code::setDefaultValue);
            Arrays.stream((cdvordinal).getListArray()).forEach(o -> {
                WebTemplateInputValue value = new WebTemplateInputValue();
                value.setOrdinal(o.getValue());
                value.setValue(o.getSymbol().getDefiningCode().getCodeString());
                value.getLocalizedLabels()
                        .putAll(Optional.ofNullable(termDefinitionMap.get(value.getValue())).map(Map::entrySet).stream()
                                .flatMap(Set::stream)
                                .collect(Collectors.toMap(
                                        Map.Entry::getKey, e -> e.getValue().getValue())));
                value.getLocalizedDescriptions()
                        .putAll(Optional.ofNullable(termDefinitionMap.get(value.getValue())).map(Map::entrySet).stream()
                                .flatMap(Set::stream)
                                .collect(Collectors.toMap(
                                        Map.Entry::getKey, e -> e.getValue().getDescription())));
                value.setLabel(value.getLocalizedLabels().get(defaultLanguage));
                if (!value.getLocalizedDescriptions().containsKey(defaultLanguage)) {
                    value.getLocalizedDescriptions().put(defaultLanguage, "");
                }
                code.getList().add(value);
            });

        } else if (cdomaintype instanceof CCODEPHRASE ccodephrase) {
            WebTemplateInput code = new WebTemplateInput();
            inputHandler.findDefaultValue(node, "defining_code").ifPresent(code::setDefaultValue);
            code.setSuffix("code");
            node.getInputs().add(code);
            Optional.of(ccodephrase)
                    .map(CCODEPHRASE::getAssumedValue)
                    .map(CODEPHRASE::getCodeString)
                    .ifPresent(code::setDefaultValue);

            if (ccodephrase instanceof CCODEREFERENCE ccodereference) {
                code.setTerminology(Optional.of(ccodereference)
                        .map(CCODEREFERENCE::getReferenceSetUri)
                        .map(s -> StringUtils.removeStart(s, "terminology:"))
                        .orElse(null));
            } else {
                code.setTerminology(Optional.of(ccodephrase)
                        .map(CCODEPHRASE::getTerminologyId)
                        .map(OBJECTID::getValue)
                        .orElse(null));
            }

            if (code.getTerminology().equals(OPENEHR)) {
                ValueSet valueSet = TerminologyProvider.findOpenEhrValueSet(
                        code.getTerminology(), ccodephrase.getCodeListArray(), defaultLanguage);

                Map<String, ValueSet> collect = languages.stream()
                        .collect(Collectors.toMap(
                                Function.identity(),
                                l -> TerminologyProvider.findOpenEhrValueSet(
                                        code.getTerminology(), ccodephrase.getCodeListArray(), l)));

                valueSet.getTherms().forEach(t -> {
                    WebTemplateInputValue value = new WebTemplateInputValue();
                    value.setValue(t.getCode());
                    value.setLabel(t.getValue());
                    value.getLocalizedLabels()
                            .putAll(collect.entrySet().stream()
                                    .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getTherms().stream()
                                            .filter(x -> x.getCode().equals(t.getCode()))
                                            .findAny()
                                            .map(TermDefinition::getValue)
                                            .orElse(""))));
                    code.getList().add(value);
                });

            } else {
                Arrays.stream(ccodephrase.getCodeListArray())
                        .map(o -> StringUtils.isBlank(code.getTerminology()) || "local".equals(code.getTerminology())
                                ? o
                                : code.getTerminology() + "::" + o)
                        .forEach(o -> {
                            WebTemplateInputValue value = new WebTemplateInputValue();
                            Optional<TermDefinition> termDefinition = Optional.ofNullable(termDefinitionMap.get(o))
                                    .map(e -> e.get(defaultLanguage));
                            if (termDefinition.isEmpty()) {
                                o = o.replace(code.getTerminology() + "::", "");
                                termDefinition = Optional.ofNullable(termDefinitionMap.get(o))
                                        .map(e -> e.get(defaultLanguage));
                            }

                            if (termDefinition.isPresent()) {
                                value.setValue(termDefinition.get().getCode());
                                if (StringUtils.isNotBlank((code.getTerminology()))) {
                                    value.setValue(value.getValue().replace(code.getTerminology() + "::", ""));
                                }
                                value.setLabel(termDefinition.get().getValue());

                                value.getLocalizedLabels()
                                        .putAll(
                                                termDefinitionMap
                                                        .getOrDefault(o, Collections.emptyMap())
                                                        .entrySet()
                                                        .stream()
                                                        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue()
                                                                .getValue())));
                                value.getLocalizedDescriptions()
                                        .putAll(
                                                termDefinitionMap
                                                        .getOrDefault(o, Collections.emptyMap())
                                                        .entrySet()
                                                        .stream()
                                                        .filter(e -> StringUtils.isNotBlank(
                                                                e.getValue().getDescription()))
                                                        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue()
                                                                .getDescription())));
                                if (!value.getLocalizedDescriptions().containsKey(defaultLanguage)) {
                                    value.getLocalizedDescriptions().put(defaultLanguage, "");
                                }
                                code.getList().add(value);
                            }
                        });
            }

            if (code.getList().isEmpty()) {
                code.setType("TEXT");
                WebTemplateInput value = InputHandler.buildWebTemplateInput("value", "TEXT");
                value.setTerminology(code.getTerminology());
                node.getInputs().add(value);
            } else {
                code.setType(CODED_TEXT);
            }

        } else {
            throw new SdkException(
                    String.format("Unexpected class: %s", cdomaintype.getClass().getSimpleName()));
        }
        return node;
    }

    private WebTemplateNode buildNode(
            COBJECT cobject,
            String rmAttributeName,
            Map<String, Map<String, TermDefinition>> termDefinitionMap,
            Name nameFromTemplate) {
        WebTemplateNode node = new WebTemplateNode();
        node.setRmType(cobject.getRmTypeName());
        IntervalOfInteger occurrences = cobject.getOccurrences();
        node.setMin(occurrences.getLowerUnbounded() ? -1 : occurrences.getLower());
        node.setMax(occurrences.getUpperUnbounded() ? -1 : occurrences.getUpper());

        String nodeId = isLocatableNode(cobject) ? cobject.getNodeId() : null;
        if (StringUtils.isNotBlank(nodeId)) {

            Optional<String> expliziteName =
                    Optional.ofNullable(nameFromTemplate).map(n -> n.label);

            String name = expliziteName.orElse(
                    termDefinitionMap.get(nodeId).get(defaultLanguage).getValue());
            node.setName(name);
            node.setId(buildId(name));
            node.setNodeId(nodeId);
            node.setLocalizedName(name);
            if (nameFromTemplate != null) {
                node.getLocalizedNames().putAll(nameFromTemplate.localizedLabels);
            } else {
                node.getLocalizedNames()
                        .putAll(termDefinitionMap.get(nodeId).entrySet().stream()
                                .collect(Collectors.toMap(
                                        Map.Entry::getKey, e -> e.getValue().getValue())));
            }

            node.getLocalizedNames().put(defaultLanguage, name);

            node.getLocalizedDescriptions()
                    .putAll(termDefinitionMap.get(nodeId).entrySet().stream()
                            .collect(Collectors.toMap(Map.Entry::getKey, e -> Optional.ofNullable(
                                            e.getValue().getDescription())
                                    .orElse(e.getValue().getValue()))));

            Optional.of(termDefinitionMap.get(nodeId))
                    .map(m -> m.get(defaultLanguage))
                    .map(TermDefinition::getOther)
                    .stream()
                    .map(Map::entrySet)
                    .flatMap(Set::stream)
                    .forEach(e -> {
                        if (node.getAnnotations() == null) {
                            node.setAnnotations(new WebTemplateAnnotation());
                        }
                        if (e.getKey().equals("comment")) {
                            node.getAnnotations().setComment(e.getValue());
                        } else {
                            node.getAnnotations().getOther().put(e.getKey(), e.getValue());
                        }
                    });
        } else {
            String name = StringUtils.isNotBlank(rmAttributeName) ? rmAttributeName : cobject.getRmTypeName();
            node.setId(buildId(name));
            node.setName(name);
            node.setLocalizedName(name);
        }
        return node;
    }

    public static String buildId(String term) {

        String normalTerm = StringUtils.normalizeSpace(term.toLowerCase()
                        .replaceAll("[^\\p{IsAlphabetic}0-9._\\-]", " ")
                        .trim())
                .replace(" ", "_");
        if (StringUtils.isNumeric(normalTerm.substring(0, 1))) {
            normalTerm = "a" + normalTerm;
        }

        return normalTerm;
    }
}
