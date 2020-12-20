package org.ehrbase.client.aql.record;

import org.ehrbase.client.aql.field.AqlField;

public interface Record2<T1, T2> extends Record {
  T1 value1();

  AqlField<T1> field1();

  T2 value2();

  AqlField<T2> field2();
}
