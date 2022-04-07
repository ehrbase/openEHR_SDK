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
import com.nedap.archie.rm.composition.*;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.History;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMAttributeInfo;
import com.nedap.archie.rminfo.RMTypeInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlTokenSource;
import org.ehrbase.terminology.client.terminology.TermDefinition;
import org.ehrbase.terminology.client.terminology.TerminologyProvider;
import org.ehrbase.terminology.client.terminology.ValueSet;
import org.ehrbase.util.exception.SdkException;
import org.ehrbase.util.rmconstants.RmConstants;
import org.ehrbase.webtemplate.model.*;
import org.ehrbase.webtemplate.util.WebTemplateUtils;
import org.openehr.schemas.v1.*;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OPTParser {

  public static final String PATH_DIVIDER = "/";
  public static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();
  public static final String CAREFLOW_STEP = "careflow_step";
  public static final String DV_CODED_TEXT = "DV_CODED_TEXT";
  public static final String CODED_TEXT = "CODED_TEXT";
  public static final String OPENEHR = "openehr";
  public static final String CURRENT_STATE = "current_state";

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
    Optional.ofNullable(operationaltemplate.getConstraints())
        .map(TCONSTRAINT::getAttributesArray)
        .stream()
        .flatMap(Arrays::stream)
        .map(this::extractDefault)
        .flatMap(List::stream)
        .forEach(p -> defaultValues.put(p.getKey(), p.getValue()));
  }

  public WebTemplate parse() {
    WebTemplate webTemplate = new WebTemplate();

    webTemplate.setTemplateId(operationaltemplate.getTemplateId().getValue());
    webTemplate.setDefaultLanguage(defaultLanguage);
    webTemplate.setVersion("2.3");
    webTemplate
        .getLanguages()
        .addAll(
            Arrays.stream(operationaltemplate.getDescription().getDetailsArray())
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

    webTemplate.setTree(
        parseCARCHETYPEROO(operationaltemplate.getDefinition(), AqlPath.EMPTY_PATH).get(0));
    return webTemplate;
  }

  static XmlObject[] extractChildren(XmlObject c, String attributes) {
    return c.selectChildren("http://schemas.openehr.org/v1", attributes);
  }

  private List<Pair<String, String>> extractDefault(TATTRIBUTE xmlObject) {
    // XXX performance List<Pair<AqlPath, String>>
    List<Pair<String, String>> defaults = new ArrayList<>();

    String differentialPath = StringUtils.substringAfter(xmlObject.getDifferentialPath(), "/");
    String rmAttributeName = xmlObject.getRmAttributeName();
    AqlPath aql = AqlPath.EMPTY_PATH.addEnd(differentialPath, rmAttributeName);

    // Instead of handling each subtype of DATAVALUE ist handheld generic since each child is an
    // attribute of the DATAVALUE
    List<String> attributeNames =
        Arrays.stream(xmlObject.getChildrenArray())
            .map(TCOMPLEXOBJECT::getDefaultValue)
            .map(XmlTokenSource::newDomNode)
            .map(Node::getFirstChild)
            .map(Node::getChildNodes)
            .map(this::buildList)
            .flatMap(List::stream)
            .map(Node::getNodeName)
            .collect(Collectors.toList());

    attributeNames.forEach(
        n -> {
          Optional<XmlObject> any =
              Arrays.stream(xmlObject.getChildrenArray())
                  .map(TCOMPLEXOBJECT::getDefaultValue)
                  .map(x -> extractChildren(x, n))
                  .flatMap(Arrays::stream)
                  .findAny();
          if (any.isPresent()) {
            if (any.get().newDomNode().getFirstChild().getChildNodes().getLength() == 1) {

              String defaultValue = any.get().newCursor().getTextValue();

              defaults.add(new ImmutablePair<>(aql + "|" + n, defaultValue));
            } else {
              String defaultValue =
                  Arrays.stream(extractChildren(any.get(), "defining_code"))
                      .findAny()
                      .map(x -> extractChildren(x, "code_string"))
                      .stream()
                      .flatMap(Arrays::stream)
                      .map(XmlTokenSource::newCursor)
                      .map(XmlCursor::getTextValue)
                      .findAny()
                      .orElse("");
              defaults.add(new ImmutablePair<>(aql + "|" + "defining_code", defaultValue));
            }
          }
        });
    return defaults;
  }

  private List<Node> buildList(NodeList list) {

    List<Node> nodes = new ArrayList<>();
    for (int i = 0; i < list.getLength(); i++) {
      Node item = list.item(i);
      if (item.getNodeType() == 1) {
        nodes.add(item);
      }
    }
    return nodes;
  }

  private List<WebTemplateNode> parseCARCHETYPEROO(CARCHETYPEROOT carchetyperoot, AqlPath aqlPath) {

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
    otherTermDefinitionMap.forEach(
        (key, value) -> termDefinitionMap.computeIfAbsent(key, c -> new HashMap<>()).putAll(value));

    List<WebTemplateNode> nodes =
        parseCCOMPLEXOBJECT(carchetyperoot, aqlPath, termDefinitionMap, null);
    nodes.forEach(
        node -> {
          node.setNodeId(carchetyperoot.getArchetypeId().getValue());
          addRMAttributes(node, aqlPath, termDefinitionMap);
        });

    return nodes;
  }

  private Map<String, Map<String, TermDefinition>> buildOtherTerms(String archetypeId) {
    Map<String, Map<String, TermDefinition>> otherTermDefinitionMap = new HashMap<>();
    List<CodeDefinitionSet> ontologies = new ArrayList<>();

    ontologies.addAll(
        Optional.ofNullable(operationaltemplate.getOntology())
            .filter(x -> Objects.equals(x.getArchetypeId(), archetypeId))
            .map(ARCHETYPEONTOLOGY::getTermDefinitionsArray)
            .stream()
            .flatMap(Arrays::stream)
            .collect(Collectors.toList()));
    ontologies.addAll(
        Arrays.stream(operationaltemplate.getComponentOntologiesArray())
            .filter(x -> Objects.equals(x.getArchetypeId(), archetypeId))
            .map(ARCHETYPEONTOLOGY::getTermDefinitionsArray)
            .flatMap(Arrays::stream)
            .collect(Collectors.toList()));

    for (CodeDefinitionSet term : ontologies) {
      String language = term.getLanguage();

      for (ARCHETYPETERM items : term.getItemsArray()) {
        String code = items.getCode();
        String text = "";
        String description = "";
        for (StringDictionaryItem item : items.getItemsArray()) {
          String id = item.getId();

          String value = item.getStringValue();
          if (Objects.equals(id, "text")) {
            text = value;
          } else if (Objects.equals(id, "description")) {
            description = value;
          }
        }
        Map<String, TermDefinition> termDefinitionMap =
            otherTermDefinitionMap.computeIfAbsent(code, c -> new HashMap<>());
        termDefinitionMap.put(language, new TermDefinition(code, text, description));
      }
    }

    return otherTermDefinitionMap;
  }

  private static class Name {

    String label;
    Map<String, String> localizedLabels;
  }

  private List<WebTemplateNode> parseCCOMPLEXOBJECT(
      CCOMPLEXOBJECT ccomplexobject,
      AqlPath aqlPath,
      Map<String, Map<String, TermDefinition>> termDefinitionMap,
      String rmAttributeName) {

    Optional<WebTemplateNode> nameNode = buildNameNode(aqlPath, termDefinitionMap, ccomplexobject);

    List<Name> nameValues =
        nameNode.map(WebTemplateNode::getInputs).stream()
            .flatMap(List::stream)
            .filter(i -> i.getSuffix() == null || i.getSuffix().equals("code"))
            .map(WebTemplateInput::getList)
            .flatMap(List::stream)
            .map(
                webTemplateInputValue -> {
                  Name name = new Name();
                  name.label = webTemplateInputValue.getLabel();
                  name.localizedLabels = webTemplateInputValue.getLocalizedLabels();
                  return name;
                })
            .collect(Collectors.toList());

    if (nameValues.size() > 1) {
      return nameValues.stream()
          .map(
              e -> {
                WebTemplateNode node =
                    buildNodeWithName(
                        ccomplexobject, aqlPath, termDefinitionMap, rmAttributeName, e);
                Optional<WebTemplateNode> localNameNode = nameNode.map(WebTemplateNode::new);
                localNameNode.ifPresent(n -> setExplicitName(e, node, n));
                addAnnotations(node, aqlPath.toString(), annotationMap);
                parseComplexObjectSingle(
                    ccomplexobject, node.getAqlPathDto(), termDefinitionMap, localNameNode, node);
                return node;
              })
          .collect(Collectors.toList());

    } else {
      Optional<Name> explicitName = nameValues.stream().findAny();

      WebTemplateNode node =
          buildNodeWithName(
              ccomplexobject,
              aqlPath,
              termDefinitionMap,
              rmAttributeName,
              explicitName.orElse(null));

      nameNode.ifPresent(n -> setExplicitName(explicitName.orElse(null), node, n));
      addAnnotations(node, aqlPath.toString(), annotationMap);
      parseComplexObjectSingle(
          ccomplexobject, node.getAqlPathDto(), termDefinitionMap, nameNode, node);
      return Collections.singletonList(node);
    }
  }

  private void addAnnotations(
      WebTemplateNode node, String aqlPath, Map<String, Map<String, String>> annotationMap) {
    Map<String, String> annotationItems = annotationMap.get(aqlPath);
    if (annotationItems != null) {
      WebTemplateAnnotation annotation = node.getAnnotations();
      if (annotation == null) {
        annotation = new WebTemplateAnnotation();
        node.setAnnotations(annotation);
      }
      annotation.getOther().putAll(annotationItems);
    }
  }

  private void setExplicitName(Name explicitName, WebTemplateNode node, WebTemplateNode n) {
    n.setAqlPath(node.getAqlPathDto().addEnd("name"));

    Optional.ofNullable(explicitName)
        .ifPresent(
            s -> {
              n.getInputs().stream()
                  .filter(i -> i.getSuffix() == null || i.getSuffix().equals("code"))
                  .findAny()
                  .ifPresent(
                      i -> {
                        List<WebTemplateInputValue> list = new ArrayList<>(i.getList());
                        i.getList().clear();
                        list.stream()
                            .filter(v -> s.label.equals(v.getLabel()))
                            .forEach(i.getList()::add);
                      });

              n.findChildById("defining_code")
                  .ifPresent(
                      d -> {
                        d.getInputs().clear();
                        n.getInputs().stream()
                            .map(WebTemplateInput::new)
                            .forEach(d.getInputs()::add);
                      });
            });
  }

  private WebTemplateNode buildNodeWithName(
      CCOMPLEXOBJECT ccomplexobject,
      AqlPath aqlPath,
      Map<String, Map<String, TermDefinition>> termDefinitionMap,
      String rmAttributeName,
      Name explicitName) {
    WebTemplateNode node =
        buildNode(ccomplexobject, rmAttributeName, termDefinitionMap, explicitName);
    node.setAqlPath(aqlPath);

    if (StringUtils.isNotBlank(ccomplexobject.getNodeId()) && explicitName != null) {
      String nameValue = explicitName.label.replace("\\", "\\\\").replace("'", "\\'");
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

        if (cobject instanceof CPRIMITIVEOBJECT) {
          inputMap.put(
              cattribute.getRmAttributeName(),
              inputHandler.extractInput((CPRIMITIVEOBJECT) cobject));
        } else {
          List<WebTemplateNode> childNode =
              parseCOBJECT(cobject, pathLoop, termDefinitionMap, cattribute.getRmAttributeName());
          if (childNode != null) {
            newChildren.addAll(childNode);
          }
        }

        if (cattribute instanceof CSINGLEATTRIBUTE && cattribute.getExistence().getLower() == 0) {
          WebtemplateCardinality cardinality = new WebtemplateCardinality();
          cardinality.setMin(cattribute.getExistence().getLower());
          cardinality.setMax(cattribute.getExistence().getUpper());
          cardinality.setExcludeFromWebTemplate(Boolean.TRUE);

          newChildren.forEach(c -> cardinality.getIds().add(c.getId()));

          node.getCardinalities().add(cardinality);
        }
      }

      List<WebTemplateNode> ismTransitionList =
          newChildren.stream()
              .filter(n -> "ISM_TRANSITION".equals(n.getRmType()))
              .collect(Collectors.toList());
      if (!ismTransitionList.isEmpty()) {
        WebTemplateNode firstChild = ismTransitionList.get(0);
        WebTemplateNode ismTransition = new WebTemplateNode();
        ismTransition.setName(cattribute.getRmAttributeName());
        ismTransition.setId(buildId(cattribute.getRmAttributeName()));
        ismTransition.setMin(firstChild.getMin());
        ismTransition.setMax(firstChild.getMax());
        ismTransition.setRmType("ISM_TRANSITION");
        ismTransition.setInContext(true);
        ismTransition.setAqlPath(aqlPath.addEnd(cattribute.getRmAttributeName()));

        WebTemplateNode careflowStep = new WebTemplateNode();
        WebTemplateNode careflowStepProto =
            firstChild
                .findChildById(CAREFLOW_STEP)
                .orElseThrow(
                    () -> new SdkException(String.format("Missing node: %s", CAREFLOW_STEP)));
        careflowStep.setMin(careflowStepProto.getMin());
        careflowStep.setMax(careflowStepProto.getMin());
        careflowStep.setName("Careflow_step");
        careflowStep.setId(CAREFLOW_STEP);
        careflowStep.setRmType(DV_CODED_TEXT);
        careflowStep.setInContext(true);
        careflowStep.setAqlPath(aqlPath.addEnd(cattribute.getRmAttributeName(), CAREFLOW_STEP));
        WebTemplateInput code = new WebTemplateInput();
        code.setSuffix("code");
        code.setType(CODED_TEXT);
        code.setTerminology("local");

        ismTransitionList.forEach(
            i -> {
              WebTemplateInputValue value =
                  i.findChildById(CAREFLOW_STEP).get().getInputs().get(0).getList().get(0);
              value
                  .getCurrentStates()
                  .addAll(
                      i.findChildById(CURRENT_STATE).get().getInputs().get(0).getList().stream()
                          .map(WebTemplateInputValue::getValue)
                          .collect(Collectors.toList()));
              code.getList().add(value);
            });
        careflowStep.getInputs().add(code);
        ismTransition.getChildren().add(careflowStep);

        WebTemplateNode currentState = new WebTemplateNode();
        WebTemplateNode currentStateProto = firstChild.findChildById(CURRENT_STATE).orElseThrow();
        currentState.setMin(currentStateProto.getMin());
        currentState.setMax(currentStateProto.getMin());
        currentState.setRmType(DV_CODED_TEXT);
        currentState.setName("Current_state");
        currentState.setId(CURRENT_STATE);
        currentState.setInContext(true);
        currentState.setAqlPath(aqlPath.addEnd(cattribute.getRmAttributeName(), CURRENT_STATE));
        WebTemplateInput code2 = new WebTemplateInput();
        code2.setSuffix("code");
        code2.setType(CODED_TEXT);
        code2.setTerminology(OPENEHR);
        code2
            .getList()
            .addAll(
                ismTransitionList.stream()
                    .flatMap(n -> n.findChildById(CURRENT_STATE).stream())
                    .map(WebTemplateNode::getInputs)
                    .flatMap(List::stream)
                    .map(WebTemplateInput::getList)
                    .flatMap(List::stream)
                    .collect(Collectors.toList()));
        currentState.getInputs().add(code2);
        ismTransition.getChildren().add(currentState);
        WebTemplateNode transition = firstChild.findChildById("transition").orElseThrow();
        transition.setAqlPath(aqlPath.addEnd(cattribute.getRmAttributeName(), "transition"));
        transition.setInContext(true);
        ismTransition.getChildren().add(transition);
        node.getChildren().add(ismTransition);
      }

      if (cattribute instanceof CMULTIPLEATTRIBUTE) {

        WebtemplateCardinality webtemplateCardinality =
            Optional.ofNullable(((CMULTIPLEATTRIBUTE) cattribute).getCardinality())
                .map(this::buildCardinality)
                .orElse(null);
        if (webtemplateCardinality != null) {
          cardinaltyList.add(
              Pair.of(
                  webtemplateCardinality,
                  newChildren.stream()
                      .map(WebTemplateNode::getAqlPathDto)
                      .collect(Collectors.toList())));
        }
      }

      // Add missing names to aqlPath if needed
      newChildren.stream()
          // node does not already have name in aql
          .filter(c -> !node.isRelativePathNameDependent(c))
          // there  exist a node which matches the aql without name but not with name
          .filter(
              c ->
                  newChildren.stream()
                      .filter(n -> !n.getAqlPathDto().equals(c.getAqlPathDto()))
                      .anyMatch(n -> n.getAqlPathDto().equals(c.getAqlPathDto(), false)))
          .forEach(
              c -> {
                AqlPath path = AqlPath.parse(c.getAqlPath(true), c.getName());
                c.getChildren().forEach(n -> replaceParentAql(n, c.getAqlPathDto(), path));

                c.setAqlPath(path);
              });

      node.getChildren().addAll(newChildren);
    }

    // Handle choice children
    Collection<List<WebTemplateNode>> values = node.getChoicesInChildren().values();
    values.stream()
        .flatMap(List::stream)
        .forEach(this::updateChoiceId);

    // Inherit name for Element values
    if (node.getRmType().equals("ELEMENT")) {
      // Is any Node
      if (node.getChildren().isEmpty()) {

        Stream.of(
            RmConstants.DV_TEXT,
            RmConstants.DV_CODED_TEXT,
            "DV_MULTIMEDIA",
            "DV_PARSABLE",
            "DV_STATE",
            "DV_BOOLEAN",
            "DV_IDENTIFIER",
            "DV_URI",
            "DV_EHR_URI",
            RmConstants.DV_DURATION,
            "DV_QUANTITY",
            "DV_COUNT",
            "DV_PROPORTION",
            "DV_DATE_TIME",
            "DV_TIME",
            "DV_ORDINAL",
            "DV_DATE"
        ).forEach(t -> addAnyNode(node, t, inputMap));

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
    if (node.getRmType().equals(DV_CODED_TEXT)) {
      List<WebTemplateNode> matching = node.findMatching(n -> n.getRmType().equals("CODE_PHRASE"));
      if (!matching.isEmpty()) {
        node.getInputs().addAll(matching.get(0).getInputs());

      } else {
        node.getInputs().add(InputHandler.buildWebTemplateInput("value", "TEXT"));
        node.getInputs().add(InputHandler.buildWebTemplateInput("code", "TEXT"));
      }
    }

    nameNode.ifPresent(node.getChildren()::add);
    addRMAttributes(node, aqlPath, termDefinitionMap);

    // If inputs where not set from the template set them via rmType;
    if (node.getInputs().isEmpty()) {
      inputHandler.addInputs(node, inputMap);
    }

    makeIdUnique(node);

    cardinaltyList.forEach(
        p -> {
          List<String> nodeIds =
              p.getValue().stream()
                  .map(s -> node.findMatching(n -> n.getAqlPathDto().equals(s)))
                  .flatMap(List::stream)
                  .map(WebTemplateNode::getId)
                  .collect(Collectors.toList());
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
        .map(
            c ->
                parseCOBJECT(
                    c.getChildrenArray(0), aqlPath, termDefinitionMap, c.getRmAttributeName()))
        .filter(Objects::nonNull)
        .flatMap(List::stream)
        .findAny();
  }

  private void addAnyNode(
      WebTemplateNode node, String rmType, Map<String, WebTemplateInput> inputMap) {
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

    Map<Class<?>, List<String>> contextAttributes =
        Map.of(
            Locatable.class,
            List.of("language"),
            Action.class,
            List.of("time"),
            Activity.class,
            List.of("timing", "action_archetype_id"),
            Instruction.class,
            List.of("narrative"),
            IsmTransition.class,
            List.of("current_state", "careflow_step", "transition"),
            History.class,
            List.of("origin"),
            Event.class,
            List.of("time"),
            Entry.class,
            List.of("language", "provider", "other_participations", "subject", "encoding"),
            EventContext.class,
            List.of(
                "start_time",
                "end_time",
                "location",
                "setting",
                "healthCareFacility",
                "participations"),
            Composition.class,
            List.of("language", "territory", "composer", "category"));

    RMTypeInfo typeInfo = ARCHIE_RM_INFO_LOOKUP.getTypeInfo(node.getRmType());
    if (typeInfo != null) {
      contextAttributes.forEach(
          (k, v) -> {
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
        .collect(Collectors.groupingBy(node1 -> node1.getId(false)))
        .values()
        .forEach(
            l -> {
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
      WebTemplateNode node,
      AqlPath aqlPath,
      Map<String, Map<String, TermDefinition>> termDefinitionMap) {
    // Add RM Attributes
    RMTypeInfo typeInfo = ARCHIE_RM_INFO_LOOKUP.getTypeInfo(node.getRmType());
    if (typeInfo != null
        && (Locatable.class.isAssignableFrom(typeInfo.getJavaClass())
            || EventContext.class.isAssignableFrom(typeInfo.getJavaClass())
            || DvInterval.class.isAssignableFrom(typeInfo.getJavaClass())
            || IsmTransition.class.isAssignableFrom(typeInfo.getJavaClass()))) {

      node.getChildren()
          .addAll(
              typeInfo.getAttributes().values().stream()
                  .filter(s -> !s.isComputed())
                  .filter(
                      s ->
                          !Element.class.isAssignableFrom(typeInfo.getJavaClass())
                              || List.of("feeder_audit", "null_flavour").contains(s.getRmName()))
                  .filter(s -> !List.of("value").contains(s.getRmName()))
                  .filter(s -> !Locatable.class.isAssignableFrom(s.getTypeInCollection()))
                  .filter(
                      s ->
                          !DvInterval.class.isAssignableFrom(typeInfo.getJavaClass())
                              || !Objects.equals("interval", s.getRmName()))
                  .map(i -> buildNodeForAttribute(i, aqlPath, termDefinitionMap))
                  // only add if not already there
                  .filter(
                      n ->
                          node.getChildren().stream()
                              .map(WebTemplateNode::getId)
                              .noneMatch(s -> s.equals(n.getId())))
                  .collect(Collectors.toList()));
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

    if (cobject instanceof CARCHETYPEROOT) {
      String nodeId = ((CARCHETYPEROOT) cobject).getArchetypeId().getValue();
      final AqlPath pathLoop;
      if (StringUtils.isNotBlank(nodeId)) {
        pathLoop = aqlPath.replaceLastNode(n -> n.withAtCode(nodeId));
      } else {
        pathLoop = aqlPath;
      }
      return parseCARCHETYPEROO((CARCHETYPEROOT) cobject, pathLoop);

    } else if (cobject instanceof CCOMPLEXOBJECT) {
      String nodeId = cobject.getNodeId();
      final AqlPath pathLoop;
      if (StringUtils.isNotBlank(nodeId)) {
        pathLoop = aqlPath.replaceLastNode(n -> n.withAtCode(nodeId));
      } else {
        pathLoop = aqlPath;
      }
      return parseCCOMPLEXOBJECT(
          (CCOMPLEXOBJECT) cobject, pathLoop, termDefinitionMap, rmAttributeName);

    } else if (cobject instanceof CDOMAINTYPE) {
      String nodeId = cobject.getNodeId();
      final AqlPath pathLoop;
      if (StringUtils.isNotBlank(nodeId)) {
        pathLoop = aqlPath.replaceLastNode(n -> n.withAtCode(nodeId));
      } else {
        pathLoop = aqlPath;
      }
      return Collections.singletonList(
          parseCDOMAINTYPE((CDOMAINTYPE) cobject, pathLoop, termDefinitionMap, rmAttributeName));
    } else if (cobject instanceof ARCHETYPESLOT) {
      String nodeId = cobject.getNodeId();
      final AqlPath pathLoop;
      if (StringUtils.isNotBlank(nodeId)) {
        pathLoop = aqlPath.replaceLastNode(n -> n.withAtCode(nodeId));
      } else {
        pathLoop = aqlPath;
      }
      return Collections.singletonList(
          parseARCHETYPESLOT(
              (ARCHETYPESLOT) cobject, pathLoop, termDefinitionMap, rmAttributeName));
    }
    return null;
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
    } else if (cdomaintype instanceof CDVQUANTITY) {

      WebTemplateInput magnitude = new WebTemplateInput();
      magnitude.setSuffix("magnitude");
      magnitude.setType("DECIMAL");
      Optional.of((CDVQUANTITY) cdomaintype)
          .map(CDVQUANTITY::getAssumedValue)
          .map(DVQUANTITY::getMagnitude)
          .map(d -> Double.toString(d))
          .ifPresent(magnitude::setDefaultValue);
      inputHandler.findDefaultValue(node, "magnitude").ifPresent(magnitude::setDefaultValue);
      node.getInputs().add(magnitude);

      WebTemplateInput unit = new WebTemplateInput();
      unit.setSuffix("unit");
      unit.setType(CODED_TEXT);
      Optional.of((CDVQUANTITY) cdomaintype)
          .map(CDVQUANTITY::getAssumedValue)
          .map(DVQUANTITY::getUnits)
          .ifPresent(unit::setDefaultValue);
      inputHandler.findDefaultValue(node, "units").ifPresent(unit::setDefaultValue);
      node.getInputs().add(unit);

      Arrays.stream(((CDVQUANTITY) cdomaintype).getListArray())
          .forEach(
              o -> {
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
                value
                    .getLocalizedLabels()
                    .putAll(
                        termDefinitionMap
                            .getOrDefault(o.getUnits(), Collections.emptyMap())
                            .entrySet()
                            .stream()
                            .collect(
                                Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getValue())));
                unit.getList().add(value);
              });
      if (unit.getList().size() == 1) {
        magnitude.setValidation(unit.getList().get(0).getValidation());
      }
    } else if (cdomaintype instanceof CDVORDINAL) {
      WebTemplateInput code = new WebTemplateInput();
      inputHandler.findDefaultValue(node, "defining_code").ifPresent(code::setDefaultValue);
      code.setType(CODED_TEXT);
      node.getInputs().add(code);
      Optional.of((CDVORDINAL) cdomaintype)
          .map(CDVORDINAL::getAssumedValue)
          .map(DVORDINAL::getSymbol)
          .map(DVCODEDTEXT::getDefiningCode)
          .map(CODEPHRASE::getCodeString)
          .ifPresent(code::setDefaultValue);
      Arrays.stream(((CDVORDINAL) cdomaintype).getListArray())
          .forEach(
              o -> {
                WebTemplateInputValue value = new WebTemplateInputValue();
                value.setOrdinal(o.getValue());
                value.setValue(o.getSymbol().getDefiningCode().getCodeString());
                value
                    .getLocalizedLabels()
                    .putAll(
                        Optional.ofNullable(termDefinitionMap.get(value.getValue()))
                            .map(Map::entrySet)
                            .stream()
                            .flatMap(Set::stream)
                            .collect(
                                Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getValue())));
                value
                    .getLocalizedDescriptions()
                    .putAll(
                        Optional.ofNullable(termDefinitionMap.get(value.getValue()))
                            .map(Map::entrySet)
                            .stream()
                            .flatMap(Set::stream)
                            .collect(
                                Collectors.toMap(
                                    Map.Entry::getKey, e -> e.getValue().getDescription())));
                value.setLabel(value.getLocalizedLabels().get(defaultLanguage));
                if (!value.getLocalizedDescriptions().containsKey(defaultLanguage)) {
                  value.getLocalizedDescriptions().put(defaultLanguage, "");
                }
                code.getList().add(value);
              });

    } else if (cdomaintype instanceof CCODEPHRASE) {
      WebTemplateInput code = new WebTemplateInput();
      inputHandler.findDefaultValue(node, "defining_code").ifPresent(code::setDefaultValue);
      code.setSuffix("code");
      node.getInputs().add(code);
      Optional.of((CCODEPHRASE) cdomaintype)
          .map(CCODEPHRASE::getAssumedValue)
          .map(CODEPHRASE::getCodeString)
          .ifPresent(code::setDefaultValue);

      if (cdomaintype instanceof CCODEREFERENCE) {
        code.setTerminology(
            Optional.of((CCODEREFERENCE) cdomaintype)
                .map(CCODEREFERENCE::getReferenceSetUri)
                .map(s -> StringUtils.removeStart(s, "terminology:"))
                .orElse(null));
      } else {
        code.setTerminology(
            Optional.of((CCODEPHRASE) cdomaintype)
                .map(CCODEPHRASE::getTerminologyId)
                .map(OBJECTID::getValue)
                .orElse(null));
      }

      if (code.getTerminology().equals(OPENEHR)) {
        ValueSet valueSet =
            TerminologyProvider.findOpenEhrValueSet(
                code.getTerminology(),
                ((CCODEPHRASE) cdomaintype).getCodeListArray(),
                defaultLanguage);

        Map<String, ValueSet> collect =
            languages.stream()
                .collect(
                    Collectors.toMap(
                        Function.identity(),
                        l ->
                            TerminologyProvider.findOpenEhrValueSet(
                                code.getTerminology(),
                                ((CCODEPHRASE) cdomaintype).getCodeListArray(),
                                l)));

        valueSet
            .getTherms()
            .forEach(
                t -> {
                  WebTemplateInputValue value = new WebTemplateInputValue();
                  value.setValue(t.getCode());
                  value.setLabel(t.getValue());
                  value
                      .getLocalizedLabels()
                      .putAll(
                          collect.entrySet().stream()
                              .collect(
                                  Collectors.toMap(
                                      Map.Entry::getKey,
                                      e ->
                                          e.getValue().getTherms().stream()
                                              .filter(x -> x.getCode().equals(t.getCode()))
                                              .findAny()
                                              .map(TermDefinition::getValue)
                                              .orElse(""))));
                  code.getList().add(value);
                });

      } else {
        Arrays.stream(((CCODEPHRASE) cdomaintype).getCodeListArray())
            .map(
                o ->
                    StringUtils.isBlank(code.getTerminology())
                            || "local".equals(code.getTerminology())
                        ? o
                        : code.getTerminology() + "::" + o)
            .forEach(
                o -> {
                  WebTemplateInputValue value = new WebTemplateInputValue();
                  Optional<TermDefinition> termDefinition =
                      Optional.ofNullable(termDefinitionMap.get(o))
                          .map(e -> e.get(defaultLanguage));
                  if (termDefinition.isEmpty()) {
                    o = o.replace(code.getTerminology() + "::", "");
                    termDefinition =
                        Optional.ofNullable(termDefinitionMap.get(o))
                            .map(e -> e.get(defaultLanguage));
                  }

                  if (termDefinition.isPresent()) {
                    value.setValue(termDefinition.get().getCode());
                    if (StringUtils.isNotBlank((code.getTerminology()))) {
                      value.setValue(value.getValue().replace(code.getTerminology() + "::", ""));
                    }
                    value.setLabel(termDefinition.get().getValue());

                    value
                        .getLocalizedLabels()
                        .putAll(
                            termDefinitionMap
                                .getOrDefault(o, Collections.emptyMap())
                                .entrySet()
                                .stream()
                                .collect(
                                    Collectors.toMap(
                                        Map.Entry::getKey, e -> e.getValue().getValue())));
                    value
                        .getLocalizedDescriptions()
                        .putAll(
                            termDefinitionMap
                                .getOrDefault(o, Collections.emptyMap())
                                .entrySet()
                                .stream()
                                .filter(e -> StringUtils.isNotBlank(e.getValue().getDescription()))
                                .collect(
                                    Collectors.toMap(
                                        Map.Entry::getKey, e -> e.getValue().getDescription())));
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
    String nodeId = cobject.getNodeId();
    if (StringUtils.isNotBlank(nodeId)) {

      Optional<String> expliziteName = Optional.ofNullable(nameFromTemplate).map(n -> n.label);

      String name =
          expliziteName.orElse(termDefinitionMap.get(nodeId).get(defaultLanguage).getValue());
      node.setName(name);
      node.setId(buildId(name));
      node.setNodeId(nodeId);
      node.setLocalizedName(name);
      if (nameFromTemplate != null) {
        node.getLocalizedNames().putAll(nameFromTemplate.localizedLabels);
      } else {
        node.getLocalizedNames()
            .putAll(
                termDefinitionMap.get(nodeId).entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getValue())));
      }

      node.getLocalizedNames().put(defaultLanguage, name);

      node.getLocalizedDescriptions()
          .putAll(
              termDefinitionMap.get(nodeId).entrySet().stream()
                  .collect(
                      Collectors.toMap(
                          Map.Entry::getKey,
                          e ->
                              Optional.ofNullable(e.getValue().getDescription())
                                  .orElse(e.getValue().getValue()))));

      Optional.of(termDefinitionMap.get(nodeId))
          .map(m -> m.get(defaultLanguage))
          .map(TermDefinition::getOther)
          .stream()
          .map(Map::entrySet)
          .flatMap(Set::stream)
          .forEach(
              e -> {
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
      String name =
          StringUtils.isNotBlank(rmAttributeName) ? rmAttributeName : cobject.getRmTypeName();
      node.setId(buildId(name));
      node.setName(name);
      node.setLocalizedName(name);
    }
    return node;
  }

  public static String buildId(String term) {

    String normalTerm =
        StringUtils.normalizeSpace(
                term.toLowerCase().replaceAll("[^\\p{IsAlphabetic}0-9._\\-]", " ").trim())
            .replace(" ", "_");
    if (StringUtils.isNumeric(normalTerm.substring(0, 1))) {
      normalTerm = "a" + normalTerm;
    }

    return normalTerm;
  }
}
