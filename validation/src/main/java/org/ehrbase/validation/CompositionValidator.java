/*
 * Copyright 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehrbase.validation;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rmobjectvalidator.RMObjectValidator;
import java.util.List;
import java.util.stream.Collectors;
import org.ehrbase.validation.terminology.ExternalTerminologyValidation;
import org.ehrbase.validation.webtemplate.ValidationWalker;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.parser.OPTParser;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;

/**
 * Validator that validates composition based on Operational Template / Web Template.
 *
 * @since 1.7
 */
public class CompositionValidator {

  private final RMObjectValidator rmObjectValidator = new RMObjectValidator(
      ArchieRMInfoLookup.getInstance(), archetypeId -> null);

  private ExternalTerminologyValidation externalTerminologyValidation;

  public CompositionValidator() {
  }

  public CompositionValidator(ExternalTerminologyValidation externalTerminologyValidation) {
    this.externalTerminologyValidation = externalTerminologyValidation;
  }

  /**
   * Validates the composition using an Operational Template.
   *
   * @param composition the composition to validate
   * @param template    the operational template used to validate the composition
   * @return the list of constraint violations
   */
  public List<ConstraintViolation> validate(Composition composition,
      OPERATIONALTEMPLATE template) {
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
    var result = rmObjectValidator.validate(composition).stream()
        .map(validationMessage -> new ConstraintViolation(validationMessage.getPath(),
            validationMessage.getMessage()))
        .collect(Collectors.toList());

    if (result.isEmpty()) {
      new ValidationWalker(externalTerminologyValidation)
          .walk(composition, result, template.getTree(), template.getTemplateId());
    }

    return result;
  }

  /**
   * Enable or disable invariant checks in archie library.
   *
   * @param validateInvariants the boolean value
   */
  public void setRunInvariantChecks(boolean validateInvariants) {
    rmObjectValidator.setRunInvariantChecks(validateInvariants);
  }

  /**
   * Sets the {@link ExternalTerminologyValidation} used to validate external terminology.
   *
   * @param externalTerminologyValidation the external terminology validator
   */
  public void setExternalTerminologyValidation(
      ExternalTerminologyValidation externalTerminologyValidation) {
    this.externalTerminologyValidation = externalTerminologyValidation;
  }
}
