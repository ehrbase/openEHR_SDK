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

import org.ehrbase.client.aql.field.AqlField;

public class RecordImp<
        T1,
        T2,
        T3,
        T4,
        T5,
        T6,
        T7,
        T8,
        T9,
        T10,
        T11,
        T12,
        T13,
        T14,
        T15,
        T16,
        T17,
        T18,
        T19,
        T20,
        T21>
    extends AbstractRecordImp<
        T1,
        T2,
        T3,
        T4,
        T5,
        T6,
        T7,
        T8,
        T9,
        T10,
        T11,
        T12,
        T13,
        T14,
        T15,
        T16,
        T17,
        T18,
        T19,
        T20,
        T21> {

  private final AqlField<?>[] aqlFields;
  private final Object[] values;

  public RecordImp(AqlField<?>... aqlFields) {
    this.aqlFields = aqlFields;
    values = new Object[aqlFields.length];
  }

  @Override
  public AqlField<?> field(int index) {
    return aqlFields[index];
  }

  @Override
  public AqlField<?>[] fields() {
    return aqlFields;
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
