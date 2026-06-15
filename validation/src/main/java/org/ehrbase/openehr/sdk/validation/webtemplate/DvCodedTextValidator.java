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
package org.ehrbase.openehr.sdk.validation.webtemplate;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.ehrbase.openehr.sdk.validation.ConstraintViolation;
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

    private static final String URL_PARAM = "url";

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
        // see Web Templates Guide 6.2. DV_CODED_TEXT
        // archetype-internal terminology
        List<ConstraintViolation> codedText = WebTemplateValidationUtils.findInputWithType(node, "CODED_TEXT")
                .map(input -> validateInternalCode(node.getAqlPath(), dvCodedText, input))
                .orElse(List.of());
        // external / openehr terminology
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
                        "DV_CODED_TEXT/defining_code/terminology_id does not match. expected: %s; found: %s"
                                .formatted(
                                        input.getTerminology(),
                                        definingCode.getTerminologyId().getValue())));

        Optional<ConstraintViolation> otherViolation = Optional.of(input)
                .filter(WebTemplateValidationUtils::hasList)
                .map(i -> {
                    var matching = i.getList().stream()
                            .filter(inputValue -> Objects.equals(inputValue.getValue(), definingCode.getCodeString()))
                            .findFirst();

                    if (matching.isEmpty()) {
                        if (Boolean.TRUE.equals(i.getListOpen())) {
                            return null;
                        }

                        return new ConstraintViolation(
                                aqlPath,
                                "DV_CODED_TEXT/defining_code/code_string does not match any option. found: %s"
                                        .formatted(definingCode.getCodeString()));
                        // TODO CDR-2273 check matching->getLocalizedLabels?
                    } else if (!Objects.equals(matching.get().getLabel(), dvCodedText.getValue())) {
                        return new ConstraintViolation(
                                aqlPath,
                                "DV_CODED_TEXT/value does not match. expected: %s; found: %s"
                                        .formatted(matching.get().getLabel(), dvCodedText.getValue()));
                    } else {
                        return null;
                    }
                });

        return ConstraintValidator.concat(terminologyViolation, otherViolation);
    }

    private List<ConstraintViolation> validateExternalTerminology(
            String aqlPath, DvCodedText dvCodedText, WebTemplateInput input) {

        CodePhrase definingCode = dvCodedText.getDefiningCode();
        TerminologyParam tp = TerminologyParam.ofFhir(input.getTerminology(), definingCode);

        // for fhir CodeSystems check that the terminology_id matches that from the template
        if (tp != null && tp.resouceType() == TerminologyParam.ResouceType.CODE_SYSTEM) {
            String expectedUrl = tp.getParam(URL_PARAM);
            String termId = definingCode.getTerminologyId().getValue();
            if (!Objects.equals(expectedUrl, termId)) {
                return List.of(new ConstraintViolation(
                        aqlPath,
                        "DV_CODED_TEXT/defining_code/terminology_id does not match. expected: %s; found: %s"
                                .formatted(expectedUrl, termId)));
            }
        }

        if (externalTerminologyValidation == null) {
            return List.of();
        }
        if (externalTerminologyValidation.supports(tp)) {
            ConstraintViolation constraintViolation = externalTerminologyValidation.validate(tp);
            if (constraintViolation != null) {
                return List.of(new ConstraintViolation(
                        aqlPath, "Invalid terminology: %s".formatted(constraintViolation.getMessage())));
            }
        }
        return List.of();
    }
}
