package org.ehrbase.client.aql.record;

import org.ehrbase.client.aql.field.AqlField;

public interface Record6<T1, T2, T3, T4, T5, T6> extends Record {
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
}
