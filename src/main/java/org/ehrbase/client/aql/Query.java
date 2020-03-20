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

package org.ehrbase.client.aql;

import java.util.Arrays;

public interface Query<T extends Record> {

    String getAql();

    Field<?>[] fields();

    static Query<Record> nativeQuery(String aql, Class<?>... expected) {
        return new NativeQuery<>(aql, Arrays.stream(expected).map(Field::create).toArray(FieldImp[]::new));
    }

    static <T1> Query<Record1<T1>> nativeQuery(String aql, Class<T1> expected1) {
        return new NativeQuery<>(aql, Field.create(expected1));
    }

    static <T1, T2> Query<Record2<T1, T2>> nativeQuery(String aql, Class<T1> expected1, Class<T2> expected2) {
        return new NativeQuery<>(aql, Field.create(expected1), Field.create(expected2));
    }

}
