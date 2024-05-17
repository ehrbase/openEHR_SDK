/*
 * Copyright (c) 2023 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * Allows for dynamically creating a tree data structure and then freezing it by creating an immutable clone.<br>
 * In order to edit a frozen model, a "thawed" mutable clone can be produced.<br>
 * Mutable models may contain frozen values, but frozen values must only consist of frozen and immutable objects.
 */
public interface Freezable<O extends Freezable<O>> extends Cloneable {
    O thawed();

    @JsonIgnore
    boolean isFrozen();

    O frozen();

    O clone();

    static <T extends Freezable<T>> List<T> thawed(List<T> list) {
        return list.stream().map(Freezable::thawed).collect(Collectors.toList());
    }

    static <T extends Freezable<T>> List<T> clone(List<T> list) {
        return list.stream().map(Freezable::clone).collect(Collectors.toList());
    }

    static <T extends Freezable<T>> List<T> frozen(List<T> list) {
        return list.stream().map(Freezable::frozen).toList();
    }

    static <F extends Freezable<F>> F clone(F object, UnaryOperator<F> cloneOp) {
        return Optional.of(object).filter(t -> !t.isFrozen()).map(cloneOp).orElse(object);
    }

    static <F extends Freezable<F>> F frozen(F object, UnaryOperator<F> immutableCloneOp) {
        return Optional.of(object)
                .filter(t -> !t.isFrozen())
                .map(immutableCloneOp)
                .orElse(object);
    }
}
