package org.ehrbase.client.aql.record;

import org.ehrbase.client.aql.field.AqlField;

public interface Record1<T1> extends Record {
  T1 value1();

  AqlField<T1> field1();
}
