/*
 * Copyright (c) 2023 vitasystems GmbH and Hannover Medical School.
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
 *
 */

package org.ehrbase.openehr.sdk.aql.dto.operand;

import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public interface Freezable<O extends Freezable<O>> extends Cloneable {
    O mutableCopy();

    boolean isImmutable();

    O immutable();

    O clone();

    static <T extends Freezable<T>> List<T> mutableCopy(List<T> list) {
        return list.stream().map(Freezable::mutableCopy).collect(Collectors.toList());
    }

    static <T extends Freezable<T>> List<T> clone(List<T> list) {
        return list.stream().map(Freezable::clone).collect(Collectors.toList());
    }

    static <T extends Freezable<T>> List<T> immutable(List<T> list) {
        return list.stream().map(Freezable::immutable).toList();
    }

    static <F extends Freezable<F>> F clone(F object, UnaryOperator<F> cloneOp) {
        return Optional.of(object).filter(t -> !t.isImmutable()).map(cloneOp).orElse(object);
    }

    static <F extends Freezable<F>> F immutable(F object, UnaryOperator<F> immutableCloneOp) {
        return Optional.of(object)
                .filter(t -> !t.isImmutable())
                .map(immutableCloneOp)
                .orElse(object);
    }
}
