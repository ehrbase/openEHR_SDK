/*
 * Copyright (c) 2024 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.validation;

import com.nedap.archie.aom.CObject;
import com.nedap.archie.flattener.OperationalTemplateProvider;
import com.nedap.archie.rminfo.ModelInfoLookup;
import com.nedap.archie.rmobjectvalidator.RMObjectValidationMessage;
import com.nedap.archie.rmobjectvalidator.RMObjectValidationMessageType;
import com.nedap.archie.rmobjectvalidator.RMObjectValidator;
import com.nedap.archie.rmobjectvalidator.ValidationConfiguration;
import java.util.Collection;

public class ArchetypeNeglectingRMObjectValidator extends RMObjectValidator {

    public ArchetypeNeglectingRMObjectValidator(
            ModelInfoLookup lookup,
            OperationalTemplateProvider provider,
            ValidationConfiguration validationConfiguration) {
        super(lookup, provider, validationConfiguration);
    }

    protected void addMessage(RMObjectValidationMessage message) {
        if (message.getType() != RMObjectValidationMessageType.ARCHETYPE_NOT_FOUND) {
            super.addMessage(message);
        }
    }

    protected void addMessage(CObject cobject, String actualPath, String message, RMObjectValidationMessageType type) {
        if (type != RMObjectValidationMessageType.ARCHETYPE_NOT_FOUND) {
            super.addMessage(cobject, actualPath, message, type);
        }
    }

    protected void addAllMessages(Collection<RMObjectValidationMessage> messages) {
        messages.forEach(this::addMessage);
    }
}
