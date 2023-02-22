/*
 * Copyright (c) 2021 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.building.webtemplateskeletnbuilder;

import static org.ehrbase.util.rmconstants.RmConstants.DV_CODED_TEXT;
import static org.ehrbase.util.rmconstants.RmConstants.RM_VERSION_1_0_4;

import com.nedap.archie.aom.CComplexObject;
import com.nedap.archie.creation.RMObjectCreator;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Archetyped;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.archetyped.TemplateId;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.Entry;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.generic.PartyRelated;
import com.nedap.archie.rm.support.identification.ArchetypeID;
import com.nedap.archie.rm.support.identification.HierObjectId;
import com.nedap.archie.rm.support.identification.TerminologyId;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMAttributeInfo;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.ehrbase.util.exception.SdkException;
import org.ehrbase.util.rmconstants.RmConstants;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateNode;

public class WebTemplateSkeletonBuilder {

    private static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

    private static final RMObjectCreator RM_OBJECT_CREATOR = new RMObjectCreator(ARCHIE_RM_INFO_LOOKUP);

    private WebTemplateSkeletonBuilder() {
        // NOP
    }

    public static Composition build(WebTemplate template, boolean withChildren) {
        Composition composition = build(template.getTree(), withChildren, Composition.class);

        Archetyped archetypeDetails = new Archetyped();
        composition.setArchetypeDetails(archetypeDetails);
        archetypeDetails.setTemplateId(new TemplateId());
        archetypeDetails.getTemplateId().setValue(template.getTemplateId());
        archetypeDetails.setRmVersion(RM_VERSION_1_0_4);
        archetypeDetails.setArchetypeId(new ArchetypeID(composition.getArchetypeNodeId()));

        return composition;
    }

    @SuppressWarnings("unchecked")
    public static <T> T build(WebTemplateNode node, boolean withChildren, Class<T> clazz) {

        String rmClass = node.getRmType();

        CComplexObject elementConstraint = new CComplexObject();
        elementConstraint.setRmTypeName(rmClass);
        Object skeleton;

        switch (rmClass) {
            case RmConstants.UID_BASED_ID:
                skeleton = new HierObjectId();
                break;
            case RmConstants.PARTY_PROXY:
                skeleton = new PartyIdentified();
                break;
            case "STRING":
            case "LONG":
                skeleton = null;
                break;
            case "BOOLEAN":
                skeleton = false;
                break;
            default:
                skeleton = RM_OBJECT_CREATOR.create(elementConstraint);
                break;
        }

        if (withChildren) {
            node.getChildren().stream()
                    .filter(n -> !Set.of("name", "archetype_node_id", "offset").contains(n.getId()))
                    .forEach(c -> {
                        Object childObject = build(c, true, Object.class);

                        insert(node, (RMObject) skeleton, c, childObject);
                    });
        }

        if (skeleton instanceof Locatable) {
            Optional<WebTemplateNode> name = node.findChildById("name");
            if (name.isPresent()) {
                if (name.get().getRmType().equals(RmConstants.DV_CODED_TEXT)) {
                    ((Locatable) skeleton)
                            .setName(extractDefault(name.get(), DvCodedText.class)
                                    .orElseThrow());
                } else {
                    ((Locatable) skeleton)
                            .setName(extractDefault(name.get(), DvText.class).orElse(new DvText(node.getName())));
                }
            } else {
                ((Locatable) skeleton).setName(new DvText(node.getName()));
            }
            ((Locatable) skeleton).setArchetypeNodeId(node.getNodeId());
        }

        if (skeleton instanceof Entry) {
            ((Entry) skeleton).setEncoding(new CodePhrase(new TerminologyId("IANA_character-sets"), "UTF-8"));
            node.findChildById("subject")
                    .map(n -> build(n, false, PartyProxy.class))
                    .ifPresent(((Entry) skeleton)::setSubject);
        }

        if (skeleton instanceof Composition) {
            node.findChildById("category")
                    .flatMap(n -> extractDefault(n, DvCodedText.class))
                    .ifPresent(((Composition) skeleton)::setCategory);
        }

        if (skeleton instanceof DvInterval) {
            ((DvInterval<?>) skeleton).setLowerIncluded(true);
            ((DvInterval<?>) skeleton).setUpperIncluded(true);
        }

        if (skeleton instanceof PartyRelated) {
            node.findChildById("relationship")
                    .flatMap(n -> extractDefault(n, DvCodedText.class))
                    .ifPresent(((PartyRelated) skeleton)::setRelationship);
        }

        if (skeleton == null || clazz.isAssignableFrom(skeleton.getClass())) {
            return (T) skeleton;
        } else {
            throw new SdkException(String.format("%s not assignable from %s", skeleton.getClass(), clazz));
        }
    }

    public static void insert(
            WebTemplateNode parentNode, RMObject parentObject, WebTemplateNode childNode, Object childObject) {

        String attributeName = childNode
                .getAqlPathDto()
                .removeStart(parentNode.getAqlPathDto())
                .getLastNode()
                .getName();

        RM_OBJECT_CREATOR.addElementToListOrSetSingleValues(
                parentObject, attributeName, Collections.singletonList(childObject));
    }

    public static void remove(
            WebTemplateNode parentNode, RMObject parentObject, WebTemplateNode childNode, Object removeChildObject) {

        String attributeName = childNode
                .getAqlPathDto()
                .removeStart(parentNode.getAqlPathDto())
                .getLastNode()
                .getName();

        RMAttributeInfo attributeInfo = ARCHIE_RM_INFO_LOOKUP.getAttributeInfo(parentObject.getClass(), attributeName);

        try {
            Object actualChild = attributeInfo.getGetMethod().invoke(parentObject);

            if (actualChild instanceof Collection) {
                ((Collection<?>) actualChild).remove(removeChildObject);
            } else if (Objects.equals(actualChild, removeChildObject)) {
                attributeInfo.getSetMethod().invoke(parentObject, (Object) null);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new SdkException(e.getMessage());
        }
    }

    public static <T> Optional<T> extractDefault(WebTemplateNode node, Class<T> clazz) {
        if (node.getRmType().equals(DV_CODED_TEXT) && node.getMin() > 0) {
            return node.getInputs().stream()
                    .filter(i -> i.getSuffix().equals("code"))
                    .filter(i -> i.getList().size() == 1)
                    .findAny()
                    .map(code -> new DvCodedText(
                            code.getList().get(0).getLabel(),
                            new CodePhrase(
                                    new TerminologyId(code.getTerminology()),
                                    code.getList().get(0).getValue())))
                    .map(clazz::cast);

        } else {
            return Optional.empty();
        }
    }
}
