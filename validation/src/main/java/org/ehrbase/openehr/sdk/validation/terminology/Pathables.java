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
package org.ehrbase.openehr.sdk.validation.terminology;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Pathable;
import java.lang.reflect.Field;
import java.util.List;
import org.ehrbase.openehr.sdk.terminology.openehr.TerminologyInterface;
import org.ehrbase.openehr.sdk.terminology.openehr.implementation.AttributeCodesetMapping;
import org.ehrbase.openehr.sdk.validation.terminology.validator.ItemField;

public class Pathables {

    private ItemValidator itemValidator;
    private TerminologyInterface terminologyInterface;
    private AttributeCodesetMapping codesetMapping;
    private String language;

    Pathables(
            TerminologyInterface terminologyInterface,
            AttributeCodesetMapping codesetMapping,
            ItemValidator itemValidator,
            String language) {
        this.terminologyInterface = terminologyInterface;
        this.itemValidator = itemValidator;
        this.codesetMapping = codesetMapping;
        this.language = language;
    }

    public void traverse(Pathable pathable, String... excludes) throws IllegalArgumentException, InternalError {

        for (Field field : pathable.getClass().getDeclaredFields()) {
            if (!field.getType().equals(List.class)) {
                try {
                    if (field.getType() == field.getType().asSubclass(Pathable.class)) {

                        if (isFieldExcluded(excludes, field.getName())) continue;

                        RMObject object = new ItemField<RMObject>(pathable).objectForField(field);

                        if (object instanceof Pathable) {
                            new Pathables(terminologyInterface, codesetMapping, itemValidator, language)
                                    .traverse((Pathable) object, excludes);
                        } else if (object != null)
                            throw new IllegalArgumentException(
                                    "Internal: couldn't handle object retrieved using getter");
                    }
                } catch (ClassCastException e) {
                    // check if object is handled for validation
                    if (itemValidator.isValidatedRmObjectType(field.getType())) {
                        RMObject object = new ItemField<RMObject>(pathable).objectForField(field);
                        itemValidator.validate(terminologyInterface, codesetMapping, field.getName(), object, language);
                    }
                }
            } // continue
            else { // iterate the array
                List iterable = new ItemField<List>(pathable).objectForField(field);

                for (Object item : iterable) {
                    if (item instanceof RMObject) {
                        itemValidator.validate(
                                terminologyInterface, codesetMapping, field.getName(), (RMObject) item, language);
                    } else if (item instanceof Pathable) {
                        traverse((Pathable) item, excludes);
                    } else throw new IllegalStateException("Could not handle item in list:" + item);
                }
            }
        }
    }

    private boolean isFieldExcluded(String[] excludes, String fieldName) {
        for (String exclude : excludes) {
            if (exclude.equals(fieldName)) return true;
        }

        return false;
    }
}
