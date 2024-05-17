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
package org.ehrbase.openehr.sdk.validation.webtemplate;

import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.ehrbase.openehr.sdk.validation.ConstraintViolation;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;

/**
 * {@link ConstraintValidator} that validates a <code>DV_CODED_TEXT</code> object.
 *
 * @see com.nedap.archie.rm.datavalues.quantity.DvOrdinal
 * @since 1.7
 */
@SuppressWarnings("unused")
public class DvOrdinalValidator implements ConstraintValidator<DvOrdinal> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<DvOrdinal> getAssociatedClass() {
        return DvOrdinal.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ConstraintViolation> validate(DvOrdinal dvOrdinal, WebTemplateNode node) {
        if (!WebTemplateValidationUtils.hasInputs(node)) {
            return Collections.emptyList();
        }

        var symbol = dvOrdinal.getSymbol();
        var result = new DvCodedTextValidator().validate(symbol, node);

        if (result.isEmpty()) {
            var input = WebTemplateValidationUtils.getInputWithType(node, "CODED_TEXT");
            input.getList().stream()
                    .filter(inputValue -> Objects.equals(
                            inputValue.getValue(), symbol.getDefiningCode().getCodeString()))
                    .findFirst()
                    .ifPresent(inputValue -> {
                        if (dvOrdinal.getValue() != inputValue.getOrdinal().longValue()) {
                            result.add(new ConstraintViolation(
                                    node.getAqlPath(),
                                    MessageFormat.format(
                                            "The value {0} must be {1}",
                                            dvOrdinal.getValue(),
                                            inputValue.getOrdinal().longValue())));
                        }
                    });
        }

        return result;
    }
}
