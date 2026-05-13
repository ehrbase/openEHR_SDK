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
package org.ehrbase.openehr.sdk.validation.terminology;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Pathable;
import java.lang.reflect.Field;
import java.util.List;
import org.ehrbase.openehr.sdk.validation.terminology.check.ItemField;

public final class Pathables {
    private Pathables() {
        /* NOOP */
    }

    public static void traverse(ItemValidator itemValidator, String language, Pathable pathable, String... excludes)
            throws IllegalArgumentException, IllegalStateException {
        if (pathable == null) {
            return;
        }
        // XXX CDR-2273 really only the declared fields?
        for (Field field : pathable.getClass().getDeclaredFields()) {
            if (field.getType().equals(List.class)) {
                List iterable = ItemField.objectForField(pathable, field);

                for (Object item : iterable) {
                    if (item instanceof RMObject rmObject) {
                        itemValidator.validate(field.getName(), rmObject, language);
                    } else if (item instanceof Pathable p) {
                        // XXX CDR-2273 children are never validated?
                        traverse(itemValidator, language, p, excludes);
                    } else throw new IllegalStateException("Could not handle item in list:" + item);
                }
            } else if (Pathable.class.isAssignableFrom(field.getType())) {
                if (isFieldExcluded(excludes, field.getName())) {
                    // XXX CDR-2273 why is this is only checked here?
                    continue;
                }

                RMObject object = ItemField.objectForField(pathable, field);

                if (object instanceof Pathable p) {
                    Pathables.traverse(itemValidator, language, p, excludes);
                } else if (object != null) {
                    throw new IllegalArgumentException("Internal: couldn't handle object retrieved using getter");
                }
            } else {
                // check if object is handled for validation
                if (itemValidator.isValidatedRmObjectType(field.getType())) {
                    RMObject object = ItemField.objectForField(pathable, field);
                    itemValidator.validate(field.getName(), object, language);
                }
            }
        }
    }

    private static boolean isFieldExcluded(String[] excludes, String fieldName) {
        for (String exclude : excludes) {
            if (exclude.equals(fieldName)) {
                return true;
            }
        }
        return false;
    }
}
