/*
 * Copyright (c) 2022 vitasystems GmbH.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.validation.terminology;

import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Action;
import com.nedap.archie.rm.composition.Activity;
import com.nedap.archie.rm.composition.AdminEntry;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.ContentItem;
import com.nedap.archie.rm.composition.Entry;
import com.nedap.archie.rm.composition.Evaluation;
import com.nedap.archie.rm.composition.Instruction;
import com.nedap.archie.rm.composition.Observation;
import com.nedap.archie.rm.composition.Section;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.History;
import com.nedap.archie.rm.datastructures.Item;
import com.nedap.archie.rm.datastructures.ItemList;
import com.nedap.archie.rm.datastructures.ItemSingle;
import com.nedap.archie.rm.datastructures.ItemStructure;
import com.nedap.archie.rm.datastructures.ItemTable;
import com.nedap.archie.rm.datastructures.ItemTree;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.ehr.EhrStatus;
import com.nedap.archie.rm.integration.GenericEntry;
import java.util.Map;
import org.ehrbase.openehr.sdk.terminology.openehr.TerminologyService;
import org.ehrbase.openehr.sdk.terminology.openehr.implementation.AttributeCodesetMapping;
import org.ehrbase.openehr.sdk.terminology.openehr.implementation.LocalizedTerminologies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Visitor of an item structure to perform specific validations:
 * - CodePhrase depending on terminology
 * - units
 */
public class ItemStructureVisitor implements I_ItemStructureVisitor {

    protected static Logger log = LoggerFactory.getLogger(ItemStructureVisitor.class);
    private int elementOccurrences = 0; // for statistics and testing
    private ItemValidator itemValidator = new ItemValidator();
    private LocalizedTerminologies localizedTerminologies;
    private AttributeCodesetMapping codesetMapping;
    private String itemStructureLanguage = "en"; // if a composition, the language can be found in the structure

    public ItemStructureVisitor(LocalizedTerminologies localizedTerminologies)
            throws NoSuchMethodException, IllegalAccessException {
        this.localizedTerminologies = localizedTerminologies;
        this.codesetMapping = localizedTerminologies.codesetMapping();

        itemValidator
                .add(new org.ehrbase.openehr.sdk.validation.terminology.validator.Composition())
                .add(new org.ehrbase.openehr.sdk.validation.terminology.validator.DvCodedText())
                .add(new org.ehrbase.openehr.sdk.validation.terminology.validator.DvText())
                .add(new org.ehrbase.openehr.sdk.validation.terminology.validator.IsmTransition())
                .add(new org.ehrbase.openehr.sdk.validation.terminology.validator.DvOrdered())
                .add(new org.ehrbase.openehr.sdk.validation.terminology.validator.EventContext())
                .add(new org.ehrbase.openehr.sdk.validation.terminology.validator.IntervalEvent())
                .add(new org.ehrbase.openehr.sdk.validation.terminology.validator.IsmTransition())
                .add(new org.ehrbase.openehr.sdk.validation.terminology.validator.OriginalVersion())
                .add(new org.ehrbase.openehr.sdk.validation.terminology.validator.Participation())
                .add(new org.ehrbase.openehr.sdk.validation.terminology.validator.PartyRelationship())
                .add(new org.ehrbase.openehr.sdk.validation.terminology.validator.TermMapping())
                .add(new org.ehrbase.openehr.sdk.validation.terminology.validator.DvMultimedia())
                .add(new org.ehrbase.openehr.sdk.validation.terminology.validator.DvOrdinal());
    }

    public ItemStructureVisitor(TerminologyService terminologyService)
            throws NoSuchMethodException, IllegalAccessException {
        this(terminologyService.localizedTerminologies());
    }

    /**
     * main entry method, validate a composition.
     *
     * @param composition
     * @throws IllegalArgumentException
     * @throws InternalError
     */
    @Override
    public void validate(Composition composition) throws IllegalArgumentException, InternalError {
        if (composition == null
                || composition.getContent() == null
                || composition.getContent().isEmpty()) return;

        itemStructureLanguage = composition.getLanguage().getCodeString();

        itemValidator.validate(
                localizedTerminologies.locale(itemStructureLanguage),
                codesetMapping,
                "composition",
                composition,
                itemStructureLanguage);

        new Pathables(
                        localizedTerminologies.locale(itemStructureLanguage),
                        codesetMapping,
                        itemValidator,
                        itemStructureLanguage)
                .traverse(composition, "content");

        for (ContentItem item : composition.getContent()) {
            traverse(item);
        }

        if (composition.getContext() != null && composition.getContext().getOtherContext() != null)
            validate(composition.getContext().getOtherContext());
    }

    @Override
    public void validate(ItemStructure itemStructure) throws IllegalArgumentException, InternalError {
        traverse(itemStructure);
    }

    @Override
    public void validate(Locatable locatable) throws IllegalArgumentException, InternalError {

        if (locatable instanceof Item) traverse((Item) locatable);
        else if (locatable instanceof ItemStructure) traverse((ItemStructure) locatable);
        else if (locatable instanceof EhrStatus && ((EhrStatus) locatable).getOtherDetails() != null)
            traverse(((EhrStatus) locatable).getOtherDetails());
        else throw new IllegalStateException("pathable is not an Item or ItemStructure instance...");
    }

    /**
     * main entry method, validate an arbitrary entry (evaluation, observation, instruction, action)
     *
     * @param entry
     * @return
     * @throws IllegalArgumentException, InternalError
     */
    private void validate(Entry entry) throws IllegalArgumentException, InternalError {
        traverse(entry);
    }

    /**
     * convenience method for processing an Evaluation
     *
     * @param entry
     * @return
     * @throws IllegalArgumentException, InternalError
     */
    private void validate(Evaluation entry) throws IllegalArgumentException, InternalError {
        if (entry == null || entry.getData() == null) return;

        traverse(entry);
    }

    /**
     * convenience method for processing an Observation
     *
     * @param entry
     * @return
     * @throws IllegalArgumentException, InternalError
     */
    private void validate(Observation entry) throws IllegalArgumentException, InternalError {
        if (entry == null || entry.getData() == null) return;

        traverse(entry);
    }

    /**
     * convenience method for processing an Instruction
     *
     * @param entry
     * @return
     * @throws IllegalArgumentException, InternalError
     */
    private void validate(Instruction entry) throws IllegalArgumentException, InternalError {
        if (entry == null || entry.getActivities() == null) return;

        traverse(entry);
    }

    /**
     * convenience method for processing an Instruction
     *
     * @param entry
     * @return
     * @throws IllegalArgumentException, InternalError
     */
    private void validate(Action entry) throws IllegalArgumentException, InternalError {
        if (entry == null || entry.getDescription() == null) return;

        traverse(entry);
    }

    /**
     * convenience method for processing an Activity
     *
     * @param entry
     * @return
     * @throws IllegalArgumentException, InternalError
     */
    private void validate(Activity entry) throws IllegalArgumentException, InternalError {
        if (entry == null || entry.getDescription() == null) return;

        traverse(entry);
    }

    //	public void setMode(WalkerOutputMode mode) {
    //		this.tag_mode = mode;
    //	}

    /**
     * domain level: Observation, evaluation, instruction, action. section, admin etc.
     *
     * @param item
     * @throws IllegalArgumentException, InternalError
     */
    private void traverse(ContentItem item) throws IllegalArgumentException, InternalError {

        Map<String, Object> retmap = null;

        if (item == null) {
            return;
        }

        log.debug("traverse element of class:" + item.getClass() + ", nodeid:" + item.getArchetypeNodeId());

        if (item instanceof Observation) {
            Observation observation = (Observation) item;

            new Pathables(
                            localizedTerminologies.locale(itemStructureLanguage),
                            codesetMapping,
                            itemValidator,
                            itemStructureLanguage)
                    .traverse(observation, "protocol", "data", "state");

            if (observation.getProtocol() != null) traverse(observation.getProtocol());

            if (observation.getData() != null) traverse(observation.getData());

            if (observation.getState() != null) traverse(observation.getState());

        } else if (item instanceof Evaluation) {
            Evaluation evaluation = (Evaluation) item;

            new Pathables(
                            localizedTerminologies.locale(itemStructureLanguage),
                            codesetMapping,
                            itemValidator,
                            itemStructureLanguage)
                    .traverse(evaluation, "protocol", "data");

            if (evaluation.getProtocol() != null) traverse(evaluation.getProtocol());

            if (evaluation.getData() != null) traverse(evaluation.getData());

        } else if (item instanceof Instruction) {
            Instruction instruction = (Instruction) item;

            new Pathables(
                            localizedTerminologies.locale(itemStructureLanguage),
                            codesetMapping,
                            itemValidator,
                            itemStructureLanguage)
                    .traverse(instruction, "protocol", "activities");

            if (instruction.getProtocol() != null) traverse(instruction.getProtocol());

            if (instruction.getActivities() != null) {
                for (Activity activity : instruction.getActivities()) {
                    traverse(activity);
                }
            }

        } else if (item instanceof Action) {
            Action action = (Action) item;

            new Pathables(
                            localizedTerminologies.locale(itemStructureLanguage),
                            codesetMapping,
                            itemValidator,
                            itemStructureLanguage)
                    .traverse(action, "protocol", "description");

            if (action.getProtocol() != null) traverse(action.getProtocol());

            if (action.getDescription() != null) traverse(action.getDescription());

        } else if (item instanceof Section) {

            for (ContentItem contentItem : ((Section) item).getItems()) {
                traverse(contentItem);
            }

        } else if (item instanceof AdminEntry) {
            AdminEntry adminEntry = (AdminEntry) item;

            new Pathables(
                            localizedTerminologies.locale(itemStructureLanguage),
                            codesetMapping,
                            itemValidator,
                            itemStructureLanguage)
                    .traverse(adminEntry, "data");

            if (adminEntry.getData() != null) traverse(adminEntry.getData());

        } else if (item instanceof GenericEntry) {
            GenericEntry genericEntry = (GenericEntry) item;

            new Pathables(
                            localizedTerminologies.locale(itemStructureLanguage),
                            codesetMapping,
                            itemValidator,
                            itemStructureLanguage)
                    .traverse(genericEntry, "data");

            traverse(genericEntry.getData());

        } else {
            log.warn("This item is not handled!" + item.getName());
        }
    }

    private void traverse(Activity activity) throws IllegalArgumentException, InternalError {
        if (activity == null) return;

        log.debug("traverse activity:" + activity);

        traverse(activity.getDescription()); // don't add a /data in path for description (don't ask me why...)
    }

    /**
     * History level in composition
     *
     * @param item
     * @throws IllegalArgumentException, InternalError
     */
    private void traverse(History<?> item) throws IllegalArgumentException, InternalError {
        if (item == null) {
            return;
        }

        log.debug("traverse history:" + item);

        // CHC: 160531 add explicit name

        if (item.getSummary() != null) traverse(item.getSummary());

        if (item.getEvents() != null) {

            for (Event<?> event : item.getEvents()) {

                itemValidator.validate(
                        localizedTerminologies.locale(itemStructureLanguage),
                        codesetMapping,
                        "event",
                        event,
                        itemStructureLanguage);

                if (event.getData() != null) traverse(event.getData());
                if (event.getState() != null) traverse(event.getState());
            }
        }
    }

    /**
     * ItemStructure: single, tree or table
     *
     * @param item
     * @throws IllegalArgumentException, InternalError
     */
    private void traverse(ItemStructure item) throws IllegalArgumentException, InternalError {

        log.debug("traverse itemstructure:" + item);

        if (item == null) {
            return;
        }

        if (item instanceof ItemSingle) {
            ItemSingle itemSingle = (ItemSingle) item;
            if (itemSingle.getItem() != null) {
                traverse(itemSingle.getItem());
            }
        } else if (item instanceof ItemList) {
            ItemList list = (ItemList) item;
            if (list.getItems() != null) {

                for (Element element : list.getItems()) {
                    traverse(element);
                }
            }
        } else if (item instanceof ItemTree) {
            ItemTree tree = (ItemTree) item;

            if (tree.getItems() != null) {

                for (Item subItem : tree.getItems()) {
                    traverse(subItem);
                }
            }

        } else if (item instanceof ItemTable) {
            ItemTable table = (ItemTable) item;
            if (table.getRows() != null) {

                for (Item subItem : table.getRows()) {
                    traverse(subItem);
                }
            }
        }
    }

    private void validateElement(Element element) throws IllegalArgumentException, InternalError {
        log.debug("should validate this element:" + element);
        elementOccurrences += 1;

        if (element.getNullFlavour() != null && itemValidator.isValidatedRmObjectType(element.getNullFlavour())) {
            itemValidator.validate(
                    localizedTerminologies.locale(itemStructureLanguage),
                    codesetMapping,
                    "null_flavour",
                    element.getNullFlavour(),
                    itemStructureLanguage);
        }

        if (element.getValue() != null && itemValidator.isValidatedRmObjectType(element.getValue())) {
            itemValidator.validate(
                    localizedTerminologies.locale(itemStructureLanguage),
                    codesetMapping,
                    null,
                    element.getValue(),
                    itemStructureLanguage);
        }

        if (element.getName() != null && element.getName() instanceof DvCodedText) {
            itemValidator.validate(
                    localizedTerminologies.locale(itemStructureLanguage),
                    codesetMapping,
                    null,
                    element.getName(),
                    itemStructureLanguage);
        }
    }

    /**
     * Element level, normally cannot go deeper...
     *
     * @param item
     * @throws IllegalArgumentException, InternalError
     */
    private void traverse(Item item) throws IllegalArgumentException, InternalError {
        log.debug("traverse item:" + item);

        if (item == null) {
            return;
        }

        if (item instanceof Element) {
            validateElement((Element) item);
        } else if (item instanceof Cluster) {

            Cluster c = (Cluster) item;

            if (c.getItems() != null) {

                for (Object clusterItem : c.getItems()) {
                    if (clusterItem instanceof Item) traverse((Item) clusterItem);
                    else throw new IllegalStateException("Cannot handle cluster item:" + clusterItem);
                }
            }
        }
    }

    public int getElementOccurrences() {
        return elementOccurrences;
    }
}
