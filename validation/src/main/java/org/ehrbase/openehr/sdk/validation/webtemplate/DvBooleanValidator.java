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

import com.nedap.archie.rm.datavalues.DvBoolean;
import java.util.Collections;
import java.util.List;
import org.ehrbase.openehr.sdk.validation.ConstraintViolation;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;

/**
 * {@link ConstraintValidator} that validates a <code>DV_BOOLEAN</code> object.
 *
 * @see com.nedap.archie.rm.datavalues.DvBoolean
 * @since 1.7
 */
@SuppressWarnings("unused")
public class DvBooleanValidator implements ConstraintValidator<DvBoolean> {

    private final PrimitiveConstraintValidator validator = new PrimitiveConstraintValidator();

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<DvBoolean> getAssociatedClass() {
        return DvBoolean.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ConstraintViolation> validate(DvBoolean dvBoolean, WebTemplateNode node) {
        if (!WebTemplateValidationUtils.hasInputs(node)) {
            return Collections.emptyList();
        }

        var input = WebTemplateValidationUtils.getInputWithType(node, "BOOLEAN");
        return validator.validate(node.getAqlPath(), dvBoolean.getValue(), input);
    }
}
