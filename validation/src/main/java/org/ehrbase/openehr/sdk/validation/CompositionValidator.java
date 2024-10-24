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
package org.ehrbase.openehr.sdk.validation;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rmobjectvalidator.RMObjectValidationMessage;
import com.nedap.archie.rmobjectvalidator.ValidationConfiguration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import org.ehrbase.openehr.sdk.validation.terminology.ExternalTerminologyValidation;
import org.ehrbase.openehr.sdk.validation.webtemplate.ValidationWalker;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplate;
import org.ehrbase.openehr.sdk.webtemplate.parser.OPTParser;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;

/**
 * Validator that checks a composition against constraints define in an Operational Template or a
 * Web Template.
 * This class is NOT thread-safe!
 */
public class CompositionValidator {

    private final boolean checkForChildrenNotInTemplate;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private ValidationConfiguration validationCfg = new ValidationConfiguration.Builder()
            .validateInvariants(true)
            .failOnUnknownTerminologyId(false)
            .build();

    private ConfigurableRMObjectValidator rmObjectValidator;

    {
        initObjectValidator(false);
    }

    private ExternalTerminologyValidation externalTerminologyValidation;

    public void setArchetypeValidation(boolean archetypeValidation) {
        initObjectValidator(archetypeValidation);
    }

    private void initObjectValidator(boolean archetypeValidation) {
        try {
            lock.writeLock().lock();
            rmObjectValidator = new ConfigurableRMObjectValidator(
                    ArchieRMInfoLookup.getInstance(), archetypeId -> null, validationCfg, archetypeValidation);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public ConfigurableRMObjectValidator getRmObjectValidator() {
        try {
            lock.readLock().lock();
            return rmObjectValidator;
        } finally {
            lock.readLock().unlock();
        }
    }

    public CompositionValidator() {
        this(false);
    }

    public CompositionValidator(boolean checkForChildrenNotInTemplate) {

        this(null, checkForChildrenNotInTemplate);
    }

    public CompositionValidator(ExternalTerminologyValidation externalTerminologyValidation) {

        this(externalTerminologyValidation, false);
    }

    public CompositionValidator(
            ExternalTerminologyValidation externalTerminologyValidation, boolean checkForChildrenNotInTemplate) {
        this.externalTerminologyValidation = externalTerminologyValidation;
        this.checkForChildrenNotInTemplate = checkForChildrenNotInTemplate;
    }

    /**
     * Validates the composition using an Operational Template.
     *
     * @param composition the composition to validate
     * @param template    the operational template used to validate the composition
     * @return the list of constraint violations
     */
    public List<ConstraintViolation> validate(Composition composition, OPERATIONALTEMPLATE template) {
        return validate(composition, new OPTParser(template).parse());
    }

    /**
     * Validates the composition using a Web Template.
     *
     * @param composition the composition to validate
     * @param template    the web template used to validate the composition
     * @return the list of constraint violations
     */
    public List<ConstraintViolation> validate(Composition composition, WebTemplate template) {
        List<RMObjectValidationMessage> messages = getRmObjectValidator().validate(composition);
        if (messages.isEmpty()) {
            List<ConstraintViolation> result = new ArrayList<>();
            new ValidationWalker(externalTerminologyValidation, true)
                    .walk(composition, result, template.getTree(), template.getTemplateId());
            return result;
        } else {
            return messages.stream()
                    .map(validationMessage ->
                            new ConstraintViolation(validationMessage.getPath(), validationMessage.getMessage()))
                    .collect(Collectors.toList());
        }
    }

    /**
     * Enable or disable invariant checks in archie library.
     *
     * @param validateInvariants the boolean value
     */
    public void setRunInvariantChecks(boolean validateInvariants) {
        try {
            lock.writeLock().lock();
            validationCfg = new ValidationConfiguration.Builder()
                    .validateInvariants(validateInvariants)
                    .failOnUnknownTerminologyId(false)
                    .build();
            initObjectValidator(rmObjectValidator.isArchetypeValidation());
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Sets the {@link ExternalTerminologyValidation} used to validate external terminology.
     *
     * @param externalTerminologyValidation the external terminology validator
     */
    public void setExternalTerminologyValidation(ExternalTerminologyValidation externalTerminologyValidation) {
        this.externalTerminologyValidation = externalTerminologyValidation;
    }
}
