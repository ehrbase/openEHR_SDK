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

import com.nedap.archie.aom.CPrimitiveObject;
import com.nedap.archie.query.RMObjectWithPath;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rmobjectvalidator.RMObjectValidationMessage;
import com.nedap.archie.rmobjectvalidator.validations.RMPrimitiveObjectValidation;
import java.util.List;
import org.ehrbase.openehr.sdk.validation.ConstraintViolation;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateInput;

/**
 * @since 1.7
 */
public class PrimitiveConstraintValidator {

    private static final PrimitiveConstraintMapper CONSTRAINT_MAPPER = new PrimitiveConstraintMapper();

    private PrimitiveConstraintValidator() {
        // NOOP
    }

    public static List<ConstraintViolation> validate(String aqlPath, Object value, CPrimitiveObject<?, ?> cobject) {
        // TODO fix deprecation
        List<RMObjectValidationMessage> msgs = RMPrimitiveObjectValidation.validate(
                ArchieRMInfoLookup.getInstance(), List.of(new RMObjectWithPath(value, aqlPath)), aqlPath, cobject);
        if (msgs.isEmpty()) {
            return List.of();
        } else {
            return msgs.stream()
                    .map(validationMessage ->
                            new ConstraintViolation(validationMessage.getPath(), validationMessage.getMessage()))
                    .toList();
        }
    }

    public static List<ConstraintViolation> validate(String aqlPath, Object value, WebTemplateInput input) {
        return validate(aqlPath, value, CONSTRAINT_MAPPER.mapInput(input));
    }
}
