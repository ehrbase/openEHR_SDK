package org.ehrbase.client.aql.record;

import org.ehrbase.client.aql.field.AqlField;

abstract class AbstractRecordImp<
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
    implements Record1<T1>,
        Record2<T1, T2>,
        Record3<T1, T2, T3>,
        Record4<T1, T2, T3, T4>,
        Record5<T1, T2, T3, T4, T5>,
        Record6<T1, T2, T3, T4, T5, T6>,
        Record7<T1, T2, T3, T4, T5, T6, T7>,
        Record8<T1, T2, T3, T4, T5, T6, T7, T8>,
        Record9<T1, T2, T3, T4, T5, T6, T7, T8, T9>,
        Record10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>,
        Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>,
        Record12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>,
        Record13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>,
        Record14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>,
        Record15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>,
        Record16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>,
        Record17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>,
        Record18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>,
        Record19<
            T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19>,
        Record20<
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
            T20>,
        Record21<
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
  public T1 value1() {
    return (T1) value(0);
  }

  public AqlField<T1> field1() {
    return (AqlField<T1>) field(0);
  }

  public T2 value2() {
    return (T2) value(1);
  }

  public AqlField<T2> field2() {
    return (AqlField<T2>) field(1);
  }

  public T3 value3() {
    return (T3) value(2);
  }

  public AqlField<T3> field3() {
    return (AqlField<T3>) field(2);
  }

  public T4 value4() {
    return (T4) value(3);
  }

  public AqlField<T4> field4() {
    return (AqlField<T4>) field(3);
  }

  public T5 value5() {
    return (T5) value(4);
  }

  public AqlField<T5> field5() {
    return (AqlField<T5>) field(4);
  }

  public T6 value6() {
    return (T6) value(5);
  }

  public AqlField<T6> field6() {
    return (AqlField<T6>) field(5);
  }

  public T7 value7() {
    return (T7) value(6);
  }

  public AqlField<T7> field7() {
    return (AqlField<T7>) field(6);
  }

  public T8 value8() {
    return (T8) value(7);
  }

  public AqlField<T8> field8() {
    return (AqlField<T8>) field(7);
  }

  public T9 value9() {
    return (T9) value(8);
  }

  public AqlField<T9> field9() {
    return (AqlField<T9>) field(8);
  }

  public T10 value10() {
    return (T10) value(9);
  }

  public AqlField<T10> field10() {
    return (AqlField<T10>) field(9);
  }

  public T11 value11() {
    return (T11) value(10);
  }

  public AqlField<T11> field11() {
    return (AqlField<T11>) field(10);
  }

  public T12 value12() {
    return (T12) value(11);
  }

  public AqlField<T12> field12() {
    return (AqlField<T12>) field(11);
  }

  public T13 value13() {
    return (T13) value(12);
  }

  public AqlField<T13> field13() {
    return (AqlField<T13>) field(12);
  }

  public T14 value14() {
    return (T14) value(13);
  }

  public AqlField<T14> field14() {
    return (AqlField<T14>) field(13);
  }

  public T15 value15() {
    return (T15) value(14);
  }

  public AqlField<T15> field15() {
    return (AqlField<T15>) field(14);
  }

  public T16 value16() {
    return (T16) value(15);
  }

  public AqlField<T16> field16() {
    return (AqlField<T16>) field(15);
  }

  public T17 value17() {
    return (T17) value(16);
  }

  public AqlField<T17> field17() {
    return (AqlField<T17>) field(16);
  }

  public T18 value18() {
    return (T18) value(17);
  }

  public AqlField<T18> field18() {
    return (AqlField<T18>) field(17);
  }

  public T19 value19() {
    return (T19) value(18);
  }

  public AqlField<T19> field19() {
    return (AqlField<T19>) field(18);
  }

  public T20 value20() {
    return (T20) value(19);
  }

  public AqlField<T20> field20() {
    return (AqlField<T20>) field(19);
  }

  public T21 value21() {
    return (T21) value(20);
  }

  public AqlField<T21> field21() {
    return (AqlField<T21>) field(20);
  }
}
