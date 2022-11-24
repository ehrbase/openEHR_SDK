/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.aql.dto.condition;

import java.io.Serializable;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;

public final class SimpleValue implements Value, Serializable {

    private final Object value;

    public static final SimpleValue NULL_VALUE = new SimpleValue();

    public SimpleValue() {
        this.value = null;
    }

    private SimpleValue(Object value) {
        this.value = value;
    }

    public SimpleValue(String value) {
        this.value = value;
    }

    public SimpleValue(Number value) {
        this.value = value;
    }

    public SimpleValue(Boolean value) {
        this.value = value;
    }

    public SimpleValue(SimpleValue other) {
        this.value = other.value;
    }

    public SimpleValue(TemporalAccessor value) {
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }

    private static final Class[] SUPPORTED_TYPES = {String.class, TemporalAccessor.class, Number.class, Boolean.class};

    private static final String ERR_UNSUPPORTED_TYPE = "Type not supported: %s";

    public static SimpleValue forImmutableObject(Object immutableObject) {
        if (immutableObject == null) {
            return NULL_VALUE;
        }
        Class<?> objectType = immutableObject.getClass();
        if (objectType == SimpleValue.class) {
            return (SimpleValue) immutableObject;
        }
        for (int i = 0; i < SUPPORTED_TYPES.length; i++) {
            if (SUPPORTED_TYPES[i].isAssignableFrom(objectType)) {
                return new SimpleValue(immutableObject);
            }
        }
        throw new IllegalArgumentException(String.format(ERR_UNSUPPORTED_TYPE, objectType.getName()));
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SimpleValue)) return false;
        final SimpleValue other = (SimpleValue) o;
        if (!other.canEqual(this)) return false;
        final Object otherValue = other.getValue();
        return Objects.equals(value, otherValue);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SimpleValue;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + (value == null ? 43 : value.hashCode());
        return result;
    }

    public String toString() {
        return "SimpleValue(value=" + this.getValue() + ")";
    }
}
