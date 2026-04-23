/*
 * Copyright (c) 2019 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.validation.terminology;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.datavalues.quantity.DvOrdered;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import org.ehrbase.openehr.sdk.validation.terminology.check.TerminologyCheck;

public class ItemValidator {

    private final Map<Class<?>, TerminologyCheck> validationRegistryList;

    public ItemValidator(TerminologyCheck... checks) {
        validationRegistryList = Arrays.stream(checks).collect(Collectors.toMap(TerminologyCheck::rmClass, c -> c));
    }

    boolean isValidatedRmObjectType(RMObject rmObject) {
        return isValidatedRmObjectType(rmObject.getClass());
    }

    boolean isValidatedRmObjectType(Class<?> aRmObjectClass) {
        return validationRegistryList.containsKey(aRmObjectClass) || DvOrdered.class.isAssignableFrom(aRmObjectClass);
    }

    private TerminologyCheck getValidator(RMObject rmObject) {
        return getValidator(rmObject.getClass());
    }

    private TerminologyCheck getValidator(Class<?> rmClass) {
        TerminologyCheck validationHandler = validationRegistryList.get(rmClass);
        if (validationHandler != null) {
            return validationHandler;
        }
        if (DvOrdered.class.isAssignableFrom(rmClass)) {
            return getValidator(DvOrdered.class);
        }
        // XXX CDR-2273 seems this had only been done if no DvOrdered handler was found
        //        throw new IllegalStateException(
        //                "No Validator for " + rmClass.getCanonicalName());
        return null;
    }

    public void validate(String fieldName, RMObject rmObject, String language) {
        if (rmObject == null) {
            return;
        }
        TerminologyCheck validationHandler = getValidator(rmObject);
        if (validationHandler != null) {
            validationHandler.check(fieldName, rmObject, language);
        }
    }
}
