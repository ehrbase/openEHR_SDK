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

package org.ehrbase.validation.webtemplate;

import com.nedap.archie.rm.datavalues.DvCodedText;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.ehrbase.functional.Try;
import org.ehrbase.validation.ConstraintViolation;
import org.ehrbase.validation.ConstraintViolationException;
import org.ehrbase.validation.terminology.ExternalTerminologyValidation;
import org.ehrbase.validation.terminology.TerminologyParam;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateNode;

/**
 * {@link ConstraintValidator} that validates a <code>DV_CODED_TEXT</code> object.
 *
 * @see com.nedap.archie.rm.datavalues.DvCodedText
 * @since 1.7
 */
@SuppressWarnings("unused")
public class DvCodedTextValidator implements ConstraintValidator<DvCodedText> {

  private final PrimitiveConstraintValidator validator = new PrimitiveConstraintValidator();

  private ExternalTerminologyValidation externalTerminologyValidation;

  public DvCodedTextValidator() {
  }

  public DvCodedTextValidator(ExternalTerminologyValidation externalTerminologyValidation) {
    this.externalTerminologyValidation = externalTerminologyValidation;
  }

  @Override
  public Class<DvCodedText> getAssociatedClass() {
    return DvCodedText.class;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<ConstraintViolation> validate(DvCodedText dvCodedText, WebTemplateNode node) {
    List<ConstraintViolation> result = new ArrayList<>();

    WebTemplateValidationUtils.findInputWithType(node, "CODED_TEXT")
        .ifPresent(
            input -> result.addAll(validateInternalCode(node.getAqlPath(), dvCodedText, input)));

    WebTemplateValidationUtils.findInputWithType(node, "TEXT")
        .ifPresent(input -> result.addAll(
            validateExternalTerminology(node.getAqlPath(), dvCodedText, input)));

    return result;
  }

  private List<ConstraintViolation> validateInternalCode(String aqlPath,
      DvCodedText dvCodedText,
      WebTemplateInput input) {
    List<ConstraintViolation> result = new ArrayList<>();

    var definingCode = dvCodedText.getDefiningCode();
    if (input.getTerminology() != null && !Objects.equals(input.getTerminology(),
        definingCode.getTerminologyId().getValue())) {
      result.add(new ConstraintViolation(aqlPath,
          MessageFormat.format("CodePhrase terminology does not match, expected: {0}, found: {1}",
              input.getTerminology(), definingCode.getTerminologyId().getValue())));
    }

    if (WebTemplateValidationUtils.hasList(input)) {
      var matching = input.getList().stream()
          .filter(inputValue -> Objects.equals(inputValue.getValue(), definingCode.getCodeString()))
          .findFirst();

      if (matching.isEmpty()) {
        result.add(new ConstraintViolation(aqlPath,
            MessageFormat.format("CodePhrase codeString does not match any option, found: {0}",
                definingCode.getCodeString())));
      } else {
        if (!matching.get().getLabel().equals(dvCodedText.getValue())) {
          result.add(new ConstraintViolation(aqlPath,
              MessageFormat.format("CodePhrase codeString does not match any option, found: {0}",
                  definingCode.getCodeString())));
        }
      }
    }

    return result;
  }

  private List<ConstraintViolation> validateExternalTerminology(String aqlPath, DvCodedText dvCodedText, WebTemplateInput input) {
    List<ConstraintViolation> result = new ArrayList<>();
    
    TerminologyParam tp = TerminologyParam.ofFhir(input.getTerminology());
      tp.setCodePhrase(dvCodedText.getDefiningCode());

    if(externalTerminologyValidation != null && externalTerminologyValidation.supports(tp)) {
      Try<Boolean,ConstraintViolationException> validationResult = externalTerminologyValidation.validate(tp);
      if(validationResult.isFailure()) {
        ConstraintViolationException ex = validationResult.getAsFailure().get();
        result.add(new ConstraintViolation(aqlPath, "Failed to validate " + dvCodedText.toString()));
        result.addAll(ex.getConstraintViolations());
      }
    }

    return result;
  }
}
