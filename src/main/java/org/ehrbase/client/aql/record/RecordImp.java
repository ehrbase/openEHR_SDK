/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.client.aql.record;

import org.ehrbase.client.aql.field.Field;

public class RecordImp<T1, T2>
        implements
        Record1<T1>,
        Record2<T1, T2> {

    private final Field<?>[] fields;
    private final Object[] values;

    public RecordImp(Field<?>... fields) {
        this.fields = fields;
        values = new Object[fields.length];
    }

    @Override
    public Field<T1> field1() {
        return (Field<T1>) field(0);
    }

    @Override
    public Field<T2> field2() {
        return (Field<T2>) field(1);
    }

    @Override
    public T1 value1() {
        return (T1) value(0);
    }

    @Override
    public T2 value2() {
        return (T2) value(1);
    }

    @Override
    public Field<?> field(int index) {
        return fields[index];
    }

    @Override
    public Field<?>[] fields() {
        return fields;
    }

    @Override
    public Object value(int index) {
        return values[index];
    }

    @Override
    public Object[] values() {
        return values;
    }

    public void putValue(int index, Object value) {
        values[index] = value;
    }
}
