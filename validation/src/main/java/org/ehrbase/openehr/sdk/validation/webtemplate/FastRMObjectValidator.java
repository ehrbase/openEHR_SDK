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
package org.ehrbase.openehr.sdk.validation.webtemplate;

import com.nedap.archie.flattener.OperationalTemplateProvider;
import com.nedap.archie.rminfo.ModelInfoLookup;
import com.nedap.archie.rmobjectvalidator.RMObjectValidator;
import com.nedap.archie.rmobjectvalidator.ValidationConfiguration;

public class FastRMObjectValidator extends RMObjectValidator {



    @Deprecated
    public FastRMObjectValidator(ModelInfoLookup lookup, OperationalTemplateProvider provider) {
        super(lookup, provider, new ValidationConfiguration.Builder().build());
    }

    /**
     * Creates an RM Object Validator with the given ModelInfoLook class, and the given OperationalTemplateProvider
     * The ModelInfoLookup is used for model access, and model specific constructions.
     * The OperationalTemplateProvider is used to retrieve other referenced archetypes in case of ArchetypeSlots.
     */
    public FastRMObjectValidator(
            ModelInfoLookup lookup,
            OperationalTemplateProvider provider,
            ValidationConfiguration validationConfiguration) {
        super(lookup, provider, new ValidationConfiguration.Builder().build());
    }
}
