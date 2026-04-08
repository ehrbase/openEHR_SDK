/*
 * Copyright (c) 2019 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.terminology.openehr.implementation;

import java.util.HashMap;
import java.util.Map;
import org.ehrbase.openehr.sdk.terminology.openehr.TerminologyResourceException;
import org.ehrbase.openehr.sdk.util.SnakeCase;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * This class provide mappings between RM object attributes and their corresponding entry into openehr terminology
 * F.e. mapping between Composition.lifecycle_state and its matching entry: 'version lifecycle state' in openehr terminology (en)
 * or 'estado de ciclo de vida de versão' (pt)
 */
public class AttributeCodesetMapping {

    private final Map<String, Map<String, AttributeGroupMap>> groupMaps;
    private static final String ATTRIBUTE_MAP_DEFINITION = "attribute_to_openehr_codesets.xml";
    private static final String OPENEHR_ID = "openehr";
    private static final String EXTERNAL_ID_PREFIX = "openehr_";

    /**
     * Gets an terminology source loaded with specified xml content
     */
    public static AttributeCodesetMapping getInstance(String xmlfilename) throws TerminologyResourceException {
        return new AttributeCodesetMapping(xmlfilename);
    }

    public static AttributeCodesetMapping getInstance() throws TerminologyResourceException {
        return new AttributeCodesetMapping(ATTRIBUTE_MAP_DEFINITION);
    }

    private Map<String, Map<String, AttributeGroupMap>> getMappers() {
        return groupMaps;
    }

    /*
     * Constructs an instance loaded with terminology content
     */
    private AttributeCodesetMapping(String filename) throws TerminologyResourceException {
        groupMaps = loadMappersFromXML(filename);
    }

    private static Map<String, Map<String, AttributeGroupMap>> loadMappersFromXML(String filename)
            throws TerminologyResourceException {
        Map<String, Map<String, AttributeGroupMap>> groupMaps = new HashMap<>();
        try {
            Element root = XMLTerminologySource.parseXml(filename);
            NodeList mapList = root.getElementsByTagName("map");

            for (int idx = 0; idx < mapList.getLength(); idx++) {
                Element element = (Element) mapList.item(idx);
                NodeList terminologyNodes = element.getElementsByTagName("terminology");
                if (terminologyNodes.getLength() == 0) {
                    throw new IllegalArgumentException("no terminology specified for entry:" + element.toString());
                }
                String terminology = terminologyNodes.item(0).getTextContent();
                if (terminology.startsWith(EXTERNAL_ID_PREFIX)) {
                    terminology = OPENEHR_ID;
                }
                Map<String, AttributeGroupMap> groupMap = groupMaps.get(terminology);
                if (groupMap == null) {
                    groupMap = new HashMap<>();
                    groupMaps.put(terminology, groupMap);
                }
                AttributeGroupMap attributeGroupMap = loadMap(element);
                groupMap.put(attributeGroupMap.getAttribute(), attributeGroupMap);
            }
            return groupMaps;
        } catch (TerminologyResourceException e) {
            throw e;
        } catch (Exception e) {
            throw new TerminologyResourceException(e.getMessage());
        }
    }

    /*
     * Loads a code set from XML element
     */
    private static AttributeGroupMap loadMap(Element element) {
        String rmAttribute =
                element.getElementsByTagName("rm_attribute").item(0).getTextContent();
        NodeList containerNodes = element.getElementsByTagName("container");
        String container;
        if (containerNodes.getLength() > 0) {
            container = containerNodes.item(0).getTextContent();
        } else {
            container = "";
        }
        NodeList matcherList = element.getElementsByTagName("match");
        Map<String, String> matcherMap = new HashMap<>(matcherList.getLength());
        for (int idx = 0; idx < matcherList.getLength(); idx++) {
            Element matcher = (Element) matcherList.item(idx);
            NodeList codeStringMaps = matcher.getElementsByTagName("codeset");
            for (int j = 0; j < codeStringMaps.getLength(); j++) {
                Element codeStringDef = (Element) codeStringMaps.item(j);
                String language = codeStringDef.getAttribute("language");
                String id = codeStringDef.getAttribute("id");
                matcherMap.put(language, id);
            }
        }

        return new AttributeGroupMap(rmAttribute, container, matcherMap);
    }

    public String actualAttributeId(String terminology, String attribute, String language) {
        if (attribute == null) {
            return null;
        }

        AttributeGroupMap attributeGroupMap =
                getMappers().get(fixTerminology(terminology)).get(new SnakeCase(attribute).camelToSnake());
        if (attributeGroupMap == null) {
            throw new IllegalArgumentException(
                    "attribute:" + attribute + ", is not defined in terminology:" + terminology);
        }

        String attributeId = attributeGroupMap.getIdMap().get(language);
        if (attributeId != null) {
            return attributeId;
        }
        // fallback to English
        return attributeGroupMap.getIdMap().get("en");
    }

    public boolean isLocalizedAttribute(String terminology, String attribute, String language) {
        if (attribute == null) {
            return false;
        }

        Map<String, AttributeGroupMap> attributeGroupMapByAttribute =
                getMappers().get(fixTerminology(terminology));
        if (attributeGroupMapByAttribute == null) {
            throw new IllegalArgumentException("Invalid terminology id:" + terminology);
        }

        AttributeGroupMap attributeGroupMap = attributeGroupMapByAttribute.get(new SnakeCase(attribute).camelToSnake());
        if (attributeGroupMap == null) {
            throw new IllegalArgumentException(
                    "attribute:" + attribute + ", is not defined in terminology:" + terminology);
        }

        // default to English
        return attributeGroupMap.getIdMap().containsKey(language);
    }

    // openehr_compression_algorithm, openehr_integrity_check_algorithm,openehr_normal_status
    private String fixTerminology(String terminology) {
        if (terminology.contains(OPENEHR_ID)) {
            return OPENEHR_ID;
        } else {
            return terminology;
        }
    }

    public ContainerType containerType(String terminology, String attribute) {
        Map<String, AttributeGroupMap> termMap = getMappers().get(terminology);
        if (termMap == null) {
            return ContainerType.UNDEFINED;
        }
        AttributeGroupMap attributeGroupMap = termMap.get(attribute);
        if (attributeGroupMap != null) {
            return attributeGroupMap.getContainer();
        }
        return OPENEHR_ID.equals(terminology) ? ContainerType.GROUP : ContainerType.CODESET;
    }
}
