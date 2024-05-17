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

import com.nedap.archie.rm.RMObject;
import java.util.List;
import org.ehrbase.openehr.sdk.util.reflection.ClassDependent;
import org.ehrbase.openehr.sdk.validation.ConstraintViolation;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;

/**
 * Interface used by {@link ValidationWalker} for validating RMObject according to constraints
 * defined in the template.
 *
 * @param <T> the RMObject type
 * @since 1.7
 */
public interface ConstraintValidator<T extends RMObject> extends ClassDependent<T> {

    /**
     * Validate the supplied RMObject based on constraint defined in the node.
     *
     * @param rmObject the RMObject to validate
     * @param node     the current node
     * @return the validation result
     */
    List<ConstraintViolation> validate(T rmObject, WebTemplateNode node);
}
