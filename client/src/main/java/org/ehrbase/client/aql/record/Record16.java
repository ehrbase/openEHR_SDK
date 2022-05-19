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
package org.ehrbase.client.aql.record;

import org.ehrbase.client.aql.field.AqlField;

public interface Record16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> extends Record {
    T1 value1();

    AqlField<T1> field1();

    T2 value2();

    AqlField<T2> field2();

    T3 value3();

    AqlField<T3> field3();

    T4 value4();

    AqlField<T4> field4();

    T5 value5();

    AqlField<T5> field5();

    T6 value6();

    AqlField<T6> field6();

    T7 value7();

    AqlField<T7> field7();

    T8 value8();

    AqlField<T8> field8();

    T9 value9();

    AqlField<T9> field9();

    T10 value10();

    AqlField<T10> field10();

    T11 value11();

    AqlField<T11> field11();

    T12 value12();

    AqlField<T12> field12();

    T13 value13();

    AqlField<T13> field13();

    T14 value14();

    AqlField<T14> field14();

    T15 value15();

    AqlField<T15> field15();

    T16 value16();

    AqlField<T16> field16();
}
