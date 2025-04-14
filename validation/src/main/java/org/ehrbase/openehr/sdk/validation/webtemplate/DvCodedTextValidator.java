/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.validation.webtemplate;

import com.nedap.archie.rm.datavalues.DvCodedText;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.ehrbase.openehr.sdk.util.functional.Try;
import org.ehrbase.openehr.sdk.validation.ConstraintViolation;
import org.ehrbase.openehr.sdk.validation.ConstraintViolationException;
import org.ehrbase.openehr.sdk.validation.terminology.ExternalTerminologyValidation;
import org.ehrbase.openehr.sdk.validation.terminology.TerminologyParam;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateInput;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;

/**
 * {@link ConstraintValidator} that validates a <code>DV_CODED_TEXT</code> object.
 *
 * @see com.nedap.archie.rm.datavalues.DvCodedText
 * @since 1.7
 */
@SuppressWarnings("unused")
public class DvCodedTextValidator implements ConstraintValidator<DvCodedText> {

    private ExternalTerminologyValidation externalTerminologyValidation;

    public DvCodedTextValidator() {}

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
        List<ConstraintViolation> codedText = WebTemplateValidationUtils.findInputWithType(node, "CODED_TEXT")
                .map(input -> validateInternalCode(node.getAqlPath(), dvCodedText, input))
                .orElse(List.of());
        List<ConstraintViolation> text = WebTemplateValidationUtils.findInputWithType(node, "TEXT")
                .map(input1 -> validateExternalTerminology(node.getAqlPath(), dvCodedText, input1))
                .orElse(List.of());
        return ConstraintValidator.concat(codedText, text);
    }

    private List<ConstraintViolation> validateInternalCode(
            String aqlPath, DvCodedText dvCodedText, WebTemplateInput input) {

        var definingCode = dvCodedText.getDefiningCode();

        Optional<ConstraintViolation> terminologyViolation = Optional.of(input)
                .filter(i -> i.getTerminology() != null
                        && !Objects.equals(
                                i.getTerminology(),
                                definingCode.getTerminologyId().getValue()))
                .map(i -> new ConstraintViolation(
                        aqlPath,
                        MessageFormat.format(
                                "CodePhrase terminology does not match, expected: {0}, found: {1}",
                                input.getTerminology(),
                                definingCode.getTerminologyId().getValue())));

        Optional<ConstraintViolation> otherViolation = Optional.of(input)
                .filter(WebTemplateValidationUtils::hasList)
                .map(i -> {
                    var matching = input.getList().stream()
                            .filter(inputValue -> Objects.equals(inputValue.getValue(), definingCode.getCodeString()))
                            .findFirst();

                    if (matching.isEmpty()) {
                        return new ConstraintViolation(
                                aqlPath,
                                MessageFormat.format(
                                        "CodePhrase codeString does not match any option, found: {0}",
                                        definingCode.getCodeString()));
                    } else if (!matching.get().getLabel().equals(dvCodedText.getValue())) {
                        return new ConstraintViolation(
                                aqlPath,
                                MessageFormat.format(
                                        "Dv_Coded_Text value does not match. found: {0} expected: {1}",
                                        dvCodedText.getValue(), matching.get().getLabel()));
                    } else {
                        return null;
                    }
                });

        return ConstraintValidator.concat(terminologyViolation, otherViolation);
    }

    private List<ConstraintViolation> validateExternalTerminology(
            String aqlPath, DvCodedText dvCodedText, WebTemplateInput input) {
        if (externalTerminologyValidation == null) {
            return List.of();
        }

        TerminologyParam tp = TerminologyParam.ofFhir(input.getTerminology());
        tp.setCodePhrase(dvCodedText.getDefiningCode());

        if (externalTerminologyValidation.supports(tp)) {
            Try<Boolean, ConstraintViolationException> validationResult = externalTerminologyValidation.validate(tp);
            if (validationResult.isFailure()) {
                ConstraintViolationException ex =
                        validationResult.getAsFailure().get();
                return ConstraintValidator.concat(
                        List.of(new ConstraintViolation(aqlPath, "Failed to validate " + dvCodedText)),
                        ex.getConstraintViolations());
            }
        }
        return List.of();
    }
}
