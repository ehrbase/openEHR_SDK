/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.validation.terminology;

import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Action;
import com.nedap.archie.rm.composition.Activity;
import com.nedap.archie.rm.composition.AdminEntry;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.ContentItem;
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
import org.ehrbase.openehr.sdk.validation.terminology.check.CodePhraseCheck;
import org.ehrbase.openehr.sdk.validation.terminology.check.CompositionCheck;
import org.ehrbase.openehr.sdk.validation.terminology.check.DvCodedTextCheck;
import org.ehrbase.openehr.sdk.validation.terminology.check.DvMultimediaCheck;
import org.ehrbase.openehr.sdk.validation.terminology.check.DvOrderedCheck;
import org.ehrbase.openehr.sdk.validation.terminology.check.DvOrdinalCheck;
import org.ehrbase.openehr.sdk.validation.terminology.check.DvTextCheck;
import org.ehrbase.openehr.sdk.validation.terminology.check.EventContextCheck;
import org.ehrbase.openehr.sdk.validation.terminology.check.IntervalEventCheck;
import org.ehrbase.openehr.sdk.validation.terminology.check.IsmTransitionCheck;
import org.ehrbase.openehr.sdk.validation.terminology.check.OriginalVersionCheck;
import org.ehrbase.openehr.sdk.validation.terminology.check.ParticipationCheck;
import org.ehrbase.openehr.sdk.validation.terminology.check.PartyRelationshipCheck;
import org.ehrbase.openehr.sdk.validation.terminology.check.TermMappingCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Visitor of an item structure to perform specific validations:
 * - CodePhrase depending on terminology
 * - units
 */
public class TerminologyValidationVisitor {

    protected static Logger log = LoggerFactory.getLogger(TerminologyValidationVisitor.class);
    private int elementOccurrences = 0; // for statistics and testing
    private final ItemValidator itemValidator;
    private String itemStructureLanguage; // if a composition, the language can be found in the structure
    private static final String PROTOCOL = "protocol";

    public TerminologyValidationVisitor() {
        itemValidator = new ItemValidator(
                // XXX CDR-2273 IsmTransitionCheck was twice, but CodePhraseCheck was missing
                // XXX CDR-2273 Is this complete? See PartyRelationshipCheck vs. PARTY_RELATED.relationship?
                new DvCodedTextCheck(),
                new CompositionCheck(),
                new DvMultimediaCheck(),
                new PartyRelationshipCheck(),
                new CodePhraseCheck(),
                new IsmTransitionCheck(),
                new IntervalEventCheck(),
                new EventContextCheck(),
                new TermMappingCheck(),
                new DvOrderedCheck(),
                new OriginalVersionCheck(),
                new DvOrdinalCheck(),
                new ParticipationCheck(),
                new DvTextCheck());
    }

    /**
     * main entry method, validate a composition.
     *
     * @param composition
     * @throws IllegalArgumentException
     */
    public void validate(Composition composition) throws IllegalArgumentException {
        // TODO CDR-2273 replace Pathables by archie rm info lookup
        if (composition == null) {
            return;
        }

        itemStructureLanguage = composition.getLanguage().getCodeString();

        itemValidator.validate("composition", composition, itemStructureLanguage);

        Pathables.traverse(itemValidator, itemStructureLanguage, composition, "content");

        for (ContentItem item : composition.getContent()) {
            traverse(item);
        }

        if (composition.getContext() != null && composition.getContext().getOtherContext() != null)
            validate(composition.getContext().getOtherContext());
    }

    public void validate(ItemStructure itemStructure) throws IllegalArgumentException {
        traverse(itemStructure);
    }

    public void validate(Locatable locatable) throws IllegalArgumentException {

        if (locatable instanceof Item item) {
            traverse(item);
        } else if (locatable instanceof ItemStructure structure) {
            traverse(structure);
        } else if (locatable instanceof EhrStatus ehrStatus) {
            if (ehrStatus.getOtherDetails() != null) {
                traverse(ehrStatus.getOtherDetails());
            }
        } else throw new IllegalStateException("pathable is not an Item or ItemStructure instance...");
    }

    /**
     * domain level: Observation, evaluation, instruction, action. section, admin etc.
     *
     * @param item
     * @throws IllegalArgumentException
     */
    private void traverse(ContentItem item) throws IllegalArgumentException {
        if (item == null) {
            return;
        }

        TerminologyValidationVisitor.log.trace(
                "traverse element of class: {}, nodeid: {}", item.getClass(), item.getArchetypeNodeId());

        if (item instanceof Observation observation) {
            Pathables.traverse(itemValidator, itemStructureLanguage, observation, PROTOCOL, "data", "state");
            traverse(observation.getProtocol());
            traverse(observation.getData());
            traverse(observation.getState());

        } else if (item instanceof Evaluation evaluation) {
            Pathables.traverse(itemValidator, itemStructureLanguage, evaluation, PROTOCOL, "data");
            traverse(evaluation.getProtocol());
            traverse(evaluation.getData());

        } else if (item instanceof Instruction instruction) {
            Pathables.traverse(itemValidator, itemStructureLanguage, instruction, PROTOCOL, "activities");
            traverse(instruction.getProtocol());

            if (instruction.getActivities() != null) {
                for (Activity activity : instruction.getActivities()) {
                    traverse(activity);
                }
            }

        } else if (item instanceof Action action) {
            Pathables.traverse(itemValidator, itemStructureLanguage, action, PROTOCOL, "description");
            traverse(action.getProtocol());
            traverse(action.getDescription());

        } else if (item instanceof Section section) {
            for (ContentItem contentItem : section.getItems()) {
                traverse(contentItem);
            }

        } else if (item instanceof AdminEntry adminEntry) {
            Pathables.traverse(itemValidator, itemStructureLanguage, adminEntry, "data");
            traverse(adminEntry.getData());

        } else if (item instanceof GenericEntry genericEntry) {
            Pathables.traverse(itemValidator, itemStructureLanguage, genericEntry, "data");
            traverse(genericEntry.getData());

        } else {
            log.warn("This item is not handled: {}!", item.getName());
        }
    }

    private void traverse(Activity activity) throws IllegalArgumentException {
        if (activity == null) {
            return;
        }

        log.trace("traverse activity: {}", activity);

        traverse(activity.getDescription()); // don't add a /data in path for description (don't ask me why...)
    }

    /**
     * History level in composition
     *
     * @param item
     * @throws IllegalArgumentException
     */
    private void traverse(History<?> item) throws IllegalArgumentException {
        if (item == null) {
            return;
        }

        log.trace("traverse history: {}", item);

        // CHC: 160531 add explicit name

        traverse(item.getSummary());

        if (item.getEvents() != null) {
            for (Event<?> event : item.getEvents()) {
                itemValidator.validate("event", event, itemStructureLanguage);
                traverse(event.getData());
                traverse(event.getState());
            }
        }
    }

    /**
     * ItemStructure: single, tree or table
     *
     * @param item
     * @throws IllegalArgumentException
     */
    private void traverse(ItemStructure item) throws IllegalArgumentException {
        if (item == null) {
            return;
        }

        log.trace("traverse itemstructure: {}", item);

        if (item instanceof ItemSingle itemSingle) {
            if (itemSingle.getItem() != null) {
                traverse(itemSingle.getItem());
            }
        } else if (item instanceof ItemList list) {
            if (list.getItems() != null) {

                for (Element element : list.getItems()) {
                    traverse(element);
                }
            }
        } else if (item instanceof ItemTree tree) {
            if (tree.getItems() != null) {
                for (Item subItem : tree.getItems()) {
                    traverse(subItem);
                }
            }

        } else if (item instanceof ItemTable table) {
            if (table.getRows() != null) {
                for (Item subItem : table.getRows()) {
                    traverse(subItem);
                }
            }
        }
    }

    private void validateElement(Element element) throws IllegalArgumentException {
        log.trace("should validate this element: {}", element);
        elementOccurrences += 1;

        if (element.getNullFlavour() != null && itemValidator.isValidatedRmObjectType(element.getNullFlavour())) {
            itemValidator.validate("null_flavour", element.getNullFlavour(), itemStructureLanguage);
        }

        if (element.getValue() != null && itemValidator.isValidatedRmObjectType(element.getValue())) {
            itemValidator.validate(null, element.getValue(), itemStructureLanguage);
        }

        if (element.getName() instanceof DvCodedText text) {
            itemValidator.validate(null, text, itemStructureLanguage);
        }
    }

    /**
     * Element level, normally cannot go deeper...
     *
     * @param item
     * @throws IllegalArgumentException
     */
    private void traverse(Item item) throws IllegalArgumentException {
        if (item == null) {
            return;
        }
        log.trace("traverse item: {}", item);

        if (item instanceof Element el) {
            validateElement(el);
        } else if (item instanceof Cluster c && c.getItems() != null) {
            for (Object o : c.getItems()) {
                if (o instanceof Item clusterItem) {
                    traverse(clusterItem);
                } else throw new IllegalStateException("Cannot handle cluster item:" + o);
            }
        }
    }

    public int getElementOccurrences() {
        return elementOccurrences;
    }
}
