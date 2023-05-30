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
package org.ehrbase.aql.dto.operant;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @author Stefan Spiska
 */
public abstract class Primitive<T>
        implements Terminal, ColumnExpression, MatchesOperant, LikeOperant, PathPredicateOperand {

    T value;

    public Primitive() {}

    public Primitive(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
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

    public static <U> Primitive<U> clone(Primitive<U> other) {

        Primitive newInstance = null;
        try {
            newInstance = other.getClass().getConstructor().newInstance();
            newInstance.setValue(other.getValue());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return newInstance;
    }
}
