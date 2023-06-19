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

import com.nedap.archie.rm.datavalues.DvState;
import java.util.List;
import org.ehrbase.openehr.sdk.validation.ConstraintViolation;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;

/**
 * {@link ConstraintValidator} that validates a <code>DV_STATE</code> object.
 *
 * @see com.nedap.archie.rm.datavalues.DvState
 * @since 1.7
 */
@SuppressWarnings("unused")
public class DvStateValidator implements ConstraintValidator<DvState> {

    private final DvCodedTextValidator dvCodedTextValidator = new DvCodedTextValidator();

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<DvState> getAssociatedClass() {
        return DvState.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ConstraintViolation> validate(DvState dvState, WebTemplateNode node) {
        return dvCodedTextValidator.validate(dvState.getValue(), node);
    }
}
