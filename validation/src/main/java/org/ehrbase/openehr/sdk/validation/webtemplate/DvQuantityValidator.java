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

import com.nedap.archie.rm.datavalues.quantity.DvQuantity;
import java.util.List;
import java.util.Optional;
import org.ehrbase.openehr.sdk.validation.ConstraintViolation;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateInput;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateInputValue;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;

/**
 * {@link ConstraintValidator} that validates a <code>DV_QUANTITY</code> object.
 *
 * @see com.nedap.archie.rm.datavalues.quantity.DvQuantity
 * @since 1.7
 */
@SuppressWarnings("unused")
public class DvQuantityValidator implements ConstraintValidator<DvQuantity> {

    private static final PrimitiveConstraintMapper CONSTRAINT_MAPPER = new PrimitiveConstraintMapper();

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<DvQuantity> getAssociatedClass() {
        return DvQuantity.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ConstraintViolation> validate(DvQuantity quantity, WebTemplateNode node) {

        return ConstraintValidator.concat(
                WebTemplateValidationUtils.findInputWithSuffix(node, "magnitude")
                        .map(input -> validateMagnitude(node.getAqlPath(), quantity, input))
                        .orElse(List.of()),
                WebTemplateValidationUtils.findInputWithSuffix(node, "unit")
                        .map(input -> validateUnit(node.getAqlPath(), quantity, input))
                        .orElse(List.of()));
    }

    private List<ConstraintViolation> validateMagnitude(String path, DvQuantity quantity, WebTemplateInput input) {
        return PrimitiveConstraintValidator.validate(path, quantity.getMagnitude(), input);
    }

    private List<ConstraintViolation> validateUnit(String path, DvQuantity quantity, WebTemplateInput unitInput) {
        var cString = CONSTRAINT_MAPPER.mapTextInput(unitInput);
        var violations = PrimitiveConstraintValidator.validate(path, quantity.getUnits(), cString);

        if (!violations.isEmpty()) {
            return violations;
        }

        Optional<WebTemplateInputValue> inputValue =
                WebTemplateValidationUtils.findInputValue(unitInput, quantity.getUnits());

        return inputValue
                .map(unitValue -> ConstraintValidator.concat(
                        validateRange(path, quantity, unitValue), validatePrecision(path, quantity, unitValue)))
                .orElse(List.of());
    }

    private List<ConstraintViolation> validateRange(String path, DvQuantity quantity, WebTemplateInputValue unitValue) {
        if (WebTemplateValidationUtils.hasValidationRange(unitValue)) {
            return PrimitiveConstraintValidator.validate(
                    path,
                    quantity.getMagnitude(),
                    CONSTRAINT_MAPPER.mapRealInterval(unitValue.getValidation().getRange()));
        } else {
            return List.of();
        }
    }

    private List<ConstraintViolation> validatePrecision(
            String path, DvQuantity quantity, WebTemplateInputValue unitValue) {
        if (WebTemplateValidationUtils.hasValidationPrecision(unitValue) && quantity.getPrecision() != null) {
            return PrimitiveConstraintValidator.validate(
                    path,
                    quantity.getPrecision(),
                    CONSTRAINT_MAPPER.mapIntegerInterval(
                            unitValue.getValidation().getPrecision()));
        } else {
            return List.of();
        }
    }
}
