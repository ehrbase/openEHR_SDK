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

import com.nedap.archie.rm.RMObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.apache.commons.collections4.ListUtils;
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

    static <S> List<S> concat(List<S> l0, List<S> l1) {
        if (l1.isEmpty()) {
            return l0;
        }
        if (l0.isEmpty()) {
            return l1;
        } else if (l0 instanceof ArrayList) {
            l0.addAll(l1);
            return l0;
        } else {
            return ListUtils.union(l0, l1);
        }
    }

    static <S> List<S> concat(Stream<S> s0, Stream<S> s1) {
        return Stream.of(s0, s1).flatMap(s -> s).toList();
    }

    static <S> List<S> concat(Optional<S> s0, Optional<S> s1) {
        if (s1.isEmpty()) {
            return s0.map(List::of).orElse(List.of());
        } else if (s0.isEmpty()) {
            return List.of(s1.get());
        } else {
            return List.of(s0.get(), s1.get());
        }
    }

    static void addAll(List<ConstraintViolation> target, List<ConstraintViolation> toBeAdded) {
        if (!toBeAdded.isEmpty()) {
            target.addAll(toBeAdded);
        }
    }
}
