/*
 * Copyright (c) 2021 Vitasystems GmbH.
 *
 * This file is part of project EHRbase
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.validation.terminology;

import com.nedap.archie.rm.datatypes.CodePhrase;
import org.ehrbase.validation.constraints.wrappers.ValidationException;

/**
 * This interface provides support for external terminology validation.
 */
public interface ExternalTerminologyValidationSupport {

    /**
     * Can this {@link ExternalTerminologyValidationSupport} {@link #validate(String, CodePhrase) validate}
     * code phrase coming from the given terminology.
     *
     * @param referenceSetUri the reference URI of the external terminology
     * @return {@code true} if this {@link ExternalTerminologyValidationSupport} can indeed
     * {@link #validate(String, CodePhrase) validate} instances of the
     * supplied terminology
     */
    boolean supports(String referenceSetUri);

    /**
     * Validate the supplied {@link CodePhrase}.
     *
     * @param referenceSetUri the reference URI of the external terminology
     * @param codePhrase      the code phrase that is to be validated
     */
    void validate(String referenceSetUri, CodePhrase codePhrase) throws ValidationException;
}
