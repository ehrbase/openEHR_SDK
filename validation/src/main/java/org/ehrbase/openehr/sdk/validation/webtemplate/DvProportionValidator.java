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

import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import java.util.List;
import org.ehrbase.openehr.sdk.validation.ConstraintViolation;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;

/**
 * {@link ConstraintValidator} that validates a <code>DV_PROPORTION</code>.
 *
 * @see com.nedap.archie.rm.datavalues.quantity.DvProportion
 * @since 1.7
 */
@SuppressWarnings("unused")
public class DvProportionValidator implements ConstraintValidator<DvProportion> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<DvProportion> getAssociatedClass() {
        return DvProportion.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ConstraintViolation> validate(DvProportion dvProportion, WebTemplateNode node) {
        if (!WebTemplateValidationUtils.hasInputs(node)) {
            return List.of();
        }

        var numerator = WebTemplateValidationUtils.getInputWithSuffix(node, "numerator");
        var denominator = WebTemplateValidationUtils.getInputWithSuffix(node, "denominator");
        return ConstraintValidator.concat(
                PrimitiveConstraintValidator.validate(node.getAqlPath(), dvProportion.getNumerator(), numerator),
                PrimitiveConstraintValidator.validate(node.getAqlPath(), dvProportion.getDenominator(), denominator));
    }
}
