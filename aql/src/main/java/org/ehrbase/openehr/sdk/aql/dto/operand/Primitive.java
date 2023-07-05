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
 */
package org.ehrbase.openehr.sdk.aql.dto.operand;

import java.lang.constant.Constable;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import org.ehrbase.openehr.sdk.util.Freezable;

/**
 * @author Stefan Spiska
 */
public abstract class Primitive<T extends Constable, O extends Primitive<T, O>>
        implements Operand, ColumnExpression, MatchesOperand, PathPredicateOperand<O> {

    T value;

    protected boolean immutable = false;

    protected Primitive() {}

    protected Primitive(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        if (immutable) {
            throw new IllegalStateException(
                    "%s is immutable".formatted(getClass().getSimpleName()));
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Primitive primitive = (Primitive) o;
        return Objects.equals(value, primitive.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public boolean isFrozen() {
        return immutable;
    }

    public O frozen() {
        return Freezable.frozen((O) this, t -> {
            O clone = clone();
            clone.immutable = true;
            return clone;
        });
    }

    public O clone() {
        return Freezable.clone((O) this, Primitive::thawed);
    }

    public O thawed() {
        try {
            O newInstance = (O) this.getClass().getDeclaredConstructor().newInstance();
            newInstance.setValue(this.getValue());
            return newInstance;
        } catch (InstantiationException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
