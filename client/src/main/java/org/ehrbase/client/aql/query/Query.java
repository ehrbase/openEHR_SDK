package org.ehrbase.client.aql.query;

import java.util.Arrays;
import org.ehrbase.client.aql.containment.ContainmentExpression;
import org.ehrbase.client.aql.field.AqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.aql.record.Record;
import org.ehrbase.client.aql.record.Record1;
import org.ehrbase.client.aql.record.Record10;
import org.ehrbase.client.aql.record.Record11;
import org.ehrbase.client.aql.record.Record12;
import org.ehrbase.client.aql.record.Record13;
import org.ehrbase.client.aql.record.Record14;
import org.ehrbase.client.aql.record.Record15;
import org.ehrbase.client.aql.record.Record16;
import org.ehrbase.client.aql.record.Record17;
import org.ehrbase.client.aql.record.Record18;
import org.ehrbase.client.aql.record.Record19;
import org.ehrbase.client.aql.record.Record2;
import org.ehrbase.client.aql.record.Record20;
import org.ehrbase.client.aql.record.Record21;
import org.ehrbase.client.aql.record.Record3;
import org.ehrbase.client.aql.record.Record4;
import org.ehrbase.client.aql.record.Record5;
import org.ehrbase.client.aql.record.Record6;
import org.ehrbase.client.aql.record.Record7;
import org.ehrbase.client.aql.record.Record8;
import org.ehrbase.client.aql.record.Record9;

public interface Query<T extends Record> {
  String buildAql();

  AqlField<Object>[] fields();

  static EntityQuery<Record> buildEntityQuery(
      ContainmentExpression containment, SelectAqlField<?>... selectFields) {
    return new EntityQuery<>(containment, selectFields);
  }

  static NativeQuery<Record> buildNativeQuery(String aql, Class<?>... expected) {
    return new NativeQuery<>(
        aql, Arrays.stream(expected).map(AqlField::create).toArray(AqlField<?>[]::new));
  }

  static <T1> EntityQuery<Record1<T1>> buildEntityQuery(
      ContainmentExpression containment, SelectAqlField<T1> selectField1) {
    return new EntityQuery<>(containment, selectField1);
  }

  static <T1> NativeQuery<Record1<T1>> buildNativeQuery(String aql, Class<T1> expected1) {
    return new NativeQuery<>(aql, AqlField.create(expected1));
  }

  static <T1, T2> EntityQuery<Record2<T1, T2>> buildEntityQuery(
      ContainmentExpression containment,
      SelectAqlField<T1> selectField1,
      SelectAqlField<T2> selectField2) {
    return new EntityQuery<>(containment, selectField1, selectField2);
  }

  static <T1, T2> NativeQuery<Record2<T1, T2>> buildNativeQuery(
      String aql, Class<T1> expected1, Class<T2> expected2) {
    return new NativeQuery<>(aql, AqlField.create(expected1), AqlField.create(expected2));
  }

  static <T1, T2, T3> EntityQuery<Record3<T1, T2, T3>> buildEntityQuery(
      ContainmentExpression containment,
      SelectAqlField<T1> selectField1,
      SelectAqlField<T2> selectField2,
      SelectAqlField<T3> selectField3) {
    return new EntityQuery<>(containment, selectField1, selectField2, selectField3);
  }

  static <T1, T2, T3> NativeQuery<Record3<T1, T2, T3>> buildNativeQuery(
      String aql, Class<T1> expected1, Class<T2> expected2, Class<T3> expected3) {
    return new NativeQuery<>(
        aql, AqlField.create(expected1), AqlField.create(expected2), AqlField.create(expected3));
  }

  static <T1, T2, T3, T4> EntityQuery<Record4<T1, T2, T3, T4>> buildEntityQuery(
      ContainmentExpression containment,
      SelectAqlField<T1> selectField1,
      SelectAqlField<T2> selectField2,
      SelectAqlField<T3> selectField3,
      SelectAqlField<T4> selectField4) {
    return new EntityQuery<>(containment, selectField1, selectField2, selectField3, selectField4);
  }

  static <T1, T2, T3, T4> NativeQuery<Record4<T1, T2, T3, T4>> buildNativeQuery(
      String aql,
      Class<T1> expected1,
      Class<T2> expected2,
      Class<T3> expected3,
      Class<T4> expected4) {
    return new NativeQuery<>(
        aql,
        AqlField.create(expected1),
        AqlField.create(expected2),
        AqlField.create(expected3),
        AqlField.create(expected4));
  }

  static <T1, T2, T3, T4, T5> EntityQuery<Record5<T1, T2, T3, T4, T5>> buildEntityQuery(
      ContainmentExpression containment,
      SelectAqlField<T1> selectField1,
      SelectAqlField<T2> selectField2,
      SelectAqlField<T3> selectField3,
      SelectAqlField<T4> selectField4,
      SelectAqlField<T5> selectField5) {
    return new EntityQuery<>(
        containment, selectField1, selectField2, selectField3, selectField4, selectField5);
  }

  static <T1, T2, T3, T4, T5> NativeQuery<Record5<T1, T2, T3, T4, T5>> buildNativeQuery(
      String aql,
      Class<T1> expected1,
      Class<T2> expected2,
      Class<T3> expected3,
      Class<T4> expected4,
      Class<T5> expected5) {
    return new NativeQuery<>(
        aql,
        AqlField.create(expected1),
        AqlField.create(expected2),
        AqlField.create(expected3),
        AqlField.create(expected4),
        AqlField.create(expected5));
  }

  static <T1, T2, T3, T4, T5, T6> EntityQuery<Record6<T1, T2, T3, T4, T5, T6>> buildEntityQuery(
      ContainmentExpression containment,
      SelectAqlField<T1> selectField1,
      SelectAqlField<T2> selectField2,
      SelectAqlField<T3> selectField3,
      SelectAqlField<T4> selectField4,
      SelectAqlField<T5> selectField5,
      SelectAqlField<T6> selectField6) {
    return new EntityQuery<>(
        containment,
        selectField1,
        selectField2,
        selectField3,
        selectField4,
        selectField5,
        selectField6);
  }

  static <T1, T2, T3, T4, T5, T6> NativeQuery<Record6<T1, T2, T3, T4, T5, T6>> buildNativeQuery(
      String aql,
      Class<T1> expected1,
      Class<T2> expected2,
      Class<T3> expected3,
      Class<T4> expected4,
      Class<T5> expected5,
      Class<T6> expected6) {
    return new NativeQuery<>(
        aql,
        AqlField.create(expected1),
        AqlField.create(expected2),
        AqlField.create(expected3),
        AqlField.create(expected4),
        AqlField.create(expected5),
        AqlField.create(expected6));
  }

  static <T1, T2, T3, T4, T5, T6, T7>
      EntityQuery<Record7<T1, T2, T3, T4, T5, T6, T7>> buildEntityQuery(
          ContainmentExpression containment,
          SelectAqlField<T1> selectField1,
          SelectAqlField<T2> selectField2,
          SelectAqlField<T3> selectField3,
          SelectAqlField<T4> selectField4,
          SelectAqlField<T5> selectField5,
          SelectAqlField<T6> selectField6,
          SelectAqlField<T7> selectField7) {
    return new EntityQuery<>(
        containment,
        selectField1,
        selectField2,
        selectField3,
        selectField4,
        selectField5,
        selectField6,
        selectField7);
  }

  static <T1, T2, T3, T4, T5, T6, T7>
      NativeQuery<Record7<T1, T2, T3, T4, T5, T6, T7>> buildNativeQuery(
          String aql,
          Class<T1> expected1,
          Class<T2> expected2,
          Class<T3> expected3,
          Class<T4> expected4,
          Class<T5> expected5,
          Class<T6> expected6,
          Class<T7> expected7) {
    return new NativeQuery<>(
        aql,
        AqlField.create(expected1),
        AqlField.create(expected2),
        AqlField.create(expected3),
        AqlField.create(expected4),
        AqlField.create(expected5),
        AqlField.create(expected6),
        AqlField.create(expected7));
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8>
      EntityQuery<Record8<T1, T2, T3, T4, T5, T6, T7, T8>> buildEntityQuery(
          ContainmentExpression containment,
          SelectAqlField<T1> selectField1,
          SelectAqlField<T2> selectField2,
          SelectAqlField<T3> selectField3,
          SelectAqlField<T4> selectField4,
          SelectAqlField<T5> selectField5,
          SelectAqlField<T6> selectField6,
          SelectAqlField<T7> selectField7,
          SelectAqlField<T8> selectField8) {
    return new EntityQuery<>(
        containment,
        selectField1,
        selectField2,
        selectField3,
        selectField4,
        selectField5,
        selectField6,
        selectField7,
        selectField8);
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8>
      NativeQuery<Record8<T1, T2, T3, T4, T5, T6, T7, T8>> buildNativeQuery(
          String aql,
          Class<T1> expected1,
          Class<T2> expected2,
          Class<T3> expected3,
          Class<T4> expected4,
          Class<T5> expected5,
          Class<T6> expected6,
          Class<T7> expected7,
          Class<T8> expected8) {
    return new NativeQuery<>(
        aql,
        AqlField.create(expected1),
        AqlField.create(expected2),
        AqlField.create(expected3),
        AqlField.create(expected4),
        AqlField.create(expected5),
        AqlField.create(expected6),
        AqlField.create(expected7),
        AqlField.create(expected8));
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9>
      EntityQuery<Record9<T1, T2, T3, T4, T5, T6, T7, T8, T9>> buildEntityQuery(
          ContainmentExpression containment,
          SelectAqlField<T1> selectField1,
          SelectAqlField<T2> selectField2,
          SelectAqlField<T3> selectField3,
          SelectAqlField<T4> selectField4,
          SelectAqlField<T5> selectField5,
          SelectAqlField<T6> selectField6,
          SelectAqlField<T7> selectField7,
          SelectAqlField<T8> selectField8,
          SelectAqlField<T9> selectField9) {
    return new EntityQuery<>(
        containment,
        selectField1,
        selectField2,
        selectField3,
        selectField4,
        selectField5,
        selectField6,
        selectField7,
        selectField8,
        selectField9);
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9>
      NativeQuery<Record9<T1, T2, T3, T4, T5, T6, T7, T8, T9>> buildNativeQuery(
          String aql,
          Class<T1> expected1,
          Class<T2> expected2,
          Class<T3> expected3,
          Class<T4> expected4,
          Class<T5> expected5,
          Class<T6> expected6,
          Class<T7> expected7,
          Class<T8> expected8,
          Class<T9> expected9) {
    return new NativeQuery<>(
        aql,
        AqlField.create(expected1),
        AqlField.create(expected2),
        AqlField.create(expected3),
        AqlField.create(expected4),
        AqlField.create(expected5),
        AqlField.create(expected6),
        AqlField.create(expected7),
        AqlField.create(expected8),
        AqlField.create(expected9));
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>
      EntityQuery<Record10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>> buildEntityQuery(
          ContainmentExpression containment,
          SelectAqlField<T1> selectField1,
          SelectAqlField<T2> selectField2,
          SelectAqlField<T3> selectField3,
          SelectAqlField<T4> selectField4,
          SelectAqlField<T5> selectField5,
          SelectAqlField<T6> selectField6,
          SelectAqlField<T7> selectField7,
          SelectAqlField<T8> selectField8,
          SelectAqlField<T9> selectField9,
          SelectAqlField<T10> selectField10) {
    return new EntityQuery<>(
        containment,
        selectField1,
        selectField2,
        selectField3,
        selectField4,
        selectField5,
        selectField6,
        selectField7,
        selectField8,
        selectField9,
        selectField10);
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>
      NativeQuery<Record10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>> buildNativeQuery(
          String aql,
          Class<T1> expected1,
          Class<T2> expected2,
          Class<T3> expected3,
          Class<T4> expected4,
          Class<T5> expected5,
          Class<T6> expected6,
          Class<T7> expected7,
          Class<T8> expected8,
          Class<T9> expected9,
          Class<T10> expected10) {
    return new NativeQuery<>(
        aql,
        AqlField.create(expected1),
        AqlField.create(expected2),
        AqlField.create(expected3),
        AqlField.create(expected4),
        AqlField.create(expected5),
        AqlField.create(expected6),
        AqlField.create(expected7),
        AqlField.create(expected8),
        AqlField.create(expected9),
        AqlField.create(expected10));
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>
      EntityQuery<Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> buildEntityQuery(
          ContainmentExpression containment,
          SelectAqlField<T1> selectField1,
          SelectAqlField<T2> selectField2,
          SelectAqlField<T3> selectField3,
          SelectAqlField<T4> selectField4,
          SelectAqlField<T5> selectField5,
          SelectAqlField<T6> selectField6,
          SelectAqlField<T7> selectField7,
          SelectAqlField<T8> selectField8,
          SelectAqlField<T9> selectField9,
          SelectAqlField<T10> selectField10,
          SelectAqlField<T11> selectField11) {
    return new EntityQuery<>(
        containment,
        selectField1,
        selectField2,
        selectField3,
        selectField4,
        selectField5,
        selectField6,
        selectField7,
        selectField8,
        selectField9,
        selectField10,
        selectField11);
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>
      NativeQuery<Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> buildNativeQuery(
          String aql,
          Class<T1> expected1,
          Class<T2> expected2,
          Class<T3> expected3,
          Class<T4> expected4,
          Class<T5> expected5,
          Class<T6> expected6,
          Class<T7> expected7,
          Class<T8> expected8,
          Class<T9> expected9,
          Class<T10> expected10,
          Class<T11> expected11) {
    return new NativeQuery<>(
        aql,
        AqlField.create(expected1),
        AqlField.create(expected2),
        AqlField.create(expected3),
        AqlField.create(expected4),
        AqlField.create(expected5),
        AqlField.create(expected6),
        AqlField.create(expected7),
        AqlField.create(expected8),
        AqlField.create(expected9),
        AqlField.create(expected10),
        AqlField.create(expected11));
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>
      EntityQuery<Record12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>> buildEntityQuery(
          ContainmentExpression containment,
          SelectAqlField<T1> selectField1,
          SelectAqlField<T2> selectField2,
          SelectAqlField<T3> selectField3,
          SelectAqlField<T4> selectField4,
          SelectAqlField<T5> selectField5,
          SelectAqlField<T6> selectField6,
          SelectAqlField<T7> selectField7,
          SelectAqlField<T8> selectField8,
          SelectAqlField<T9> selectField9,
          SelectAqlField<T10> selectField10,
          SelectAqlField<T11> selectField11,
          SelectAqlField<T12> selectField12) {
    return new EntityQuery<>(
        containment,
        selectField1,
        selectField2,
        selectField3,
        selectField4,
        selectField5,
        selectField6,
        selectField7,
        selectField8,
        selectField9,
        selectField10,
        selectField11,
        selectField12);
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>
      NativeQuery<Record12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>> buildNativeQuery(
          String aql,
          Class<T1> expected1,
          Class<T2> expected2,
          Class<T3> expected3,
          Class<T4> expected4,
          Class<T5> expected5,
          Class<T6> expected6,
          Class<T7> expected7,
          Class<T8> expected8,
          Class<T9> expected9,
          Class<T10> expected10,
          Class<T11> expected11,
          Class<T12> expected12) {
    return new NativeQuery<>(
        aql,
        AqlField.create(expected1),
        AqlField.create(expected2),
        AqlField.create(expected3),
        AqlField.create(expected4),
        AqlField.create(expected5),
        AqlField.create(expected6),
        AqlField.create(expected7),
        AqlField.create(expected8),
        AqlField.create(expected9),
        AqlField.create(expected10),
        AqlField.create(expected11),
        AqlField.create(expected12));
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>
      EntityQuery<Record13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>>
          buildEntityQuery(
              ContainmentExpression containment,
              SelectAqlField<T1> selectField1,
              SelectAqlField<T2> selectField2,
              SelectAqlField<T3> selectField3,
              SelectAqlField<T4> selectField4,
              SelectAqlField<T5> selectField5,
              SelectAqlField<T6> selectField6,
              SelectAqlField<T7> selectField7,
              SelectAqlField<T8> selectField8,
              SelectAqlField<T9> selectField9,
              SelectAqlField<T10> selectField10,
              SelectAqlField<T11> selectField11,
              SelectAqlField<T12> selectField12,
              SelectAqlField<T13> selectField13) {
    return new EntityQuery<>(
        containment,
        selectField1,
        selectField2,
        selectField3,
        selectField4,
        selectField5,
        selectField6,
        selectField7,
        selectField8,
        selectField9,
        selectField10,
        selectField11,
        selectField12,
        selectField13);
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>
      NativeQuery<Record13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>>
          buildNativeQuery(
              String aql,
              Class<T1> expected1,
              Class<T2> expected2,
              Class<T3> expected3,
              Class<T4> expected4,
              Class<T5> expected5,
              Class<T6> expected6,
              Class<T7> expected7,
              Class<T8> expected8,
              Class<T9> expected9,
              Class<T10> expected10,
              Class<T11> expected11,
              Class<T12> expected12,
              Class<T13> expected13) {
    return new NativeQuery<>(
        aql,
        AqlField.create(expected1),
        AqlField.create(expected2),
        AqlField.create(expected3),
        AqlField.create(expected4),
        AqlField.create(expected5),
        AqlField.create(expected6),
        AqlField.create(expected7),
        AqlField.create(expected8),
        AqlField.create(expected9),
        AqlField.create(expected10),
        AqlField.create(expected11),
        AqlField.create(expected12),
        AqlField.create(expected13));
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>
      EntityQuery<Record14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>>
          buildEntityQuery(
              ContainmentExpression containment,
              SelectAqlField<T1> selectField1,
              SelectAqlField<T2> selectField2,
              SelectAqlField<T3> selectField3,
              SelectAqlField<T4> selectField4,
              SelectAqlField<T5> selectField5,
              SelectAqlField<T6> selectField6,
              SelectAqlField<T7> selectField7,
              SelectAqlField<T8> selectField8,
              SelectAqlField<T9> selectField9,
              SelectAqlField<T10> selectField10,
              SelectAqlField<T11> selectField11,
              SelectAqlField<T12> selectField12,
              SelectAqlField<T13> selectField13,
              SelectAqlField<T14> selectField14) {
    return new EntityQuery<>(
        containment,
        selectField1,
        selectField2,
        selectField3,
        selectField4,
        selectField5,
        selectField6,
        selectField7,
        selectField8,
        selectField9,
        selectField10,
        selectField11,
        selectField12,
        selectField13,
        selectField14);
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>
      NativeQuery<Record14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>>
          buildNativeQuery(
              String aql,
              Class<T1> expected1,
              Class<T2> expected2,
              Class<T3> expected3,
              Class<T4> expected4,
              Class<T5> expected5,
              Class<T6> expected6,
              Class<T7> expected7,
              Class<T8> expected8,
              Class<T9> expected9,
              Class<T10> expected10,
              Class<T11> expected11,
              Class<T12> expected12,
              Class<T13> expected13,
              Class<T14> expected14) {
    return new NativeQuery<>(
        aql,
        AqlField.create(expected1),
        AqlField.create(expected2),
        AqlField.create(expected3),
        AqlField.create(expected4),
        AqlField.create(expected5),
        AqlField.create(expected6),
        AqlField.create(expected7),
        AqlField.create(expected8),
        AqlField.create(expected9),
        AqlField.create(expected10),
        AqlField.create(expected11),
        AqlField.create(expected12),
        AqlField.create(expected13),
        AqlField.create(expected14));
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>
      EntityQuery<Record15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>>
          buildEntityQuery(
              ContainmentExpression containment,
              SelectAqlField<T1> selectField1,
              SelectAqlField<T2> selectField2,
              SelectAqlField<T3> selectField3,
              SelectAqlField<T4> selectField4,
              SelectAqlField<T5> selectField5,
              SelectAqlField<T6> selectField6,
              SelectAqlField<T7> selectField7,
              SelectAqlField<T8> selectField8,
              SelectAqlField<T9> selectField9,
              SelectAqlField<T10> selectField10,
              SelectAqlField<T11> selectField11,
              SelectAqlField<T12> selectField12,
              SelectAqlField<T13> selectField13,
              SelectAqlField<T14> selectField14,
              SelectAqlField<T15> selectField15) {
    return new EntityQuery<>(
        containment,
        selectField1,
        selectField2,
        selectField3,
        selectField4,
        selectField5,
        selectField6,
        selectField7,
        selectField8,
        selectField9,
        selectField10,
        selectField11,
        selectField12,
        selectField13,
        selectField14,
        selectField15);
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>
      NativeQuery<Record15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>>
          buildNativeQuery(
              String aql,
              Class<T1> expected1,
              Class<T2> expected2,
              Class<T3> expected3,
              Class<T4> expected4,
              Class<T5> expected5,
              Class<T6> expected6,
              Class<T7> expected7,
              Class<T8> expected8,
              Class<T9> expected9,
              Class<T10> expected10,
              Class<T11> expected11,
              Class<T12> expected12,
              Class<T13> expected13,
              Class<T14> expected14,
              Class<T15> expected15) {
    return new NativeQuery<>(
        aql,
        AqlField.create(expected1),
        AqlField.create(expected2),
        AqlField.create(expected3),
        AqlField.create(expected4),
        AqlField.create(expected5),
        AqlField.create(expected6),
        AqlField.create(expected7),
        AqlField.create(expected8),
        AqlField.create(expected9),
        AqlField.create(expected10),
        AqlField.create(expected11),
        AqlField.create(expected12),
        AqlField.create(expected13),
        AqlField.create(expected14),
        AqlField.create(expected15));
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>
      EntityQuery<Record16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>>
          buildEntityQuery(
              ContainmentExpression containment,
              SelectAqlField<T1> selectField1,
              SelectAqlField<T2> selectField2,
              SelectAqlField<T3> selectField3,
              SelectAqlField<T4> selectField4,
              SelectAqlField<T5> selectField5,
              SelectAqlField<T6> selectField6,
              SelectAqlField<T7> selectField7,
              SelectAqlField<T8> selectField8,
              SelectAqlField<T9> selectField9,
              SelectAqlField<T10> selectField10,
              SelectAqlField<T11> selectField11,
              SelectAqlField<T12> selectField12,
              SelectAqlField<T13> selectField13,
              SelectAqlField<T14> selectField14,
              SelectAqlField<T15> selectField15,
              SelectAqlField<T16> selectField16) {
    return new EntityQuery<>(
        containment,
        selectField1,
        selectField2,
        selectField3,
        selectField4,
        selectField5,
        selectField6,
        selectField7,
        selectField8,
        selectField9,
        selectField10,
        selectField11,
        selectField12,
        selectField13,
        selectField14,
        selectField15,
        selectField16);
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>
      NativeQuery<Record16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>>
          buildNativeQuery(
              String aql,
              Class<T1> expected1,
              Class<T2> expected2,
              Class<T3> expected3,
              Class<T4> expected4,
              Class<T5> expected5,
              Class<T6> expected6,
              Class<T7> expected7,
              Class<T8> expected8,
              Class<T9> expected9,
              Class<T10> expected10,
              Class<T11> expected11,
              Class<T12> expected12,
              Class<T13> expected13,
              Class<T14> expected14,
              Class<T15> expected15,
              Class<T16> expected16) {
    return new NativeQuery<>(
        aql,
        AqlField.create(expected1),
        AqlField.create(expected2),
        AqlField.create(expected3),
        AqlField.create(expected4),
        AqlField.create(expected5),
        AqlField.create(expected6),
        AqlField.create(expected7),
        AqlField.create(expected8),
        AqlField.create(expected9),
        AqlField.create(expected10),
        AqlField.create(expected11),
        AqlField.create(expected12),
        AqlField.create(expected13),
        AqlField.create(expected14),
        AqlField.create(expected15),
        AqlField.create(expected16));
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>
      EntityQuery<
              Record17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>>
          buildEntityQuery(
              ContainmentExpression containment,
              SelectAqlField<T1> selectField1,
              SelectAqlField<T2> selectField2,
              SelectAqlField<T3> selectField3,
              SelectAqlField<T4> selectField4,
              SelectAqlField<T5> selectField5,
              SelectAqlField<T6> selectField6,
              SelectAqlField<T7> selectField7,
              SelectAqlField<T8> selectField8,
              SelectAqlField<T9> selectField9,
              SelectAqlField<T10> selectField10,
              SelectAqlField<T11> selectField11,
              SelectAqlField<T12> selectField12,
              SelectAqlField<T13> selectField13,
              SelectAqlField<T14> selectField14,
              SelectAqlField<T15> selectField15,
              SelectAqlField<T16> selectField16,
              SelectAqlField<T17> selectField17) {
    return new EntityQuery<>(
        containment,
        selectField1,
        selectField2,
        selectField3,
        selectField4,
        selectField5,
        selectField6,
        selectField7,
        selectField8,
        selectField9,
        selectField10,
        selectField11,
        selectField12,
        selectField13,
        selectField14,
        selectField15,
        selectField16,
        selectField17);
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>
      NativeQuery<
              Record17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>>
          buildNativeQuery(
              String aql,
              Class<T1> expected1,
              Class<T2> expected2,
              Class<T3> expected3,
              Class<T4> expected4,
              Class<T5> expected5,
              Class<T6> expected6,
              Class<T7> expected7,
              Class<T8> expected8,
              Class<T9> expected9,
              Class<T10> expected10,
              Class<T11> expected11,
              Class<T12> expected12,
              Class<T13> expected13,
              Class<T14> expected14,
              Class<T15> expected15,
              Class<T16> expected16,
              Class<T17> expected17) {
    return new NativeQuery<>(
        aql,
        AqlField.create(expected1),
        AqlField.create(expected2),
        AqlField.create(expected3),
        AqlField.create(expected4),
        AqlField.create(expected5),
        AqlField.create(expected6),
        AqlField.create(expected7),
        AqlField.create(expected8),
        AqlField.create(expected9),
        AqlField.create(expected10),
        AqlField.create(expected11),
        AqlField.create(expected12),
        AqlField.create(expected13),
        AqlField.create(expected14),
        AqlField.create(expected15),
        AqlField.create(expected16),
        AqlField.create(expected17));
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>
      EntityQuery<
              Record18<
                  T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>>
          buildEntityQuery(
              ContainmentExpression containment,
              SelectAqlField<T1> selectField1,
              SelectAqlField<T2> selectField2,
              SelectAqlField<T3> selectField3,
              SelectAqlField<T4> selectField4,
              SelectAqlField<T5> selectField5,
              SelectAqlField<T6> selectField6,
              SelectAqlField<T7> selectField7,
              SelectAqlField<T8> selectField8,
              SelectAqlField<T9> selectField9,
              SelectAqlField<T10> selectField10,
              SelectAqlField<T11> selectField11,
              SelectAqlField<T12> selectField12,
              SelectAqlField<T13> selectField13,
              SelectAqlField<T14> selectField14,
              SelectAqlField<T15> selectField15,
              SelectAqlField<T16> selectField16,
              SelectAqlField<T17> selectField17,
              SelectAqlField<T18> selectField18) {
    return new EntityQuery<>(
        containment,
        selectField1,
        selectField2,
        selectField3,
        selectField4,
        selectField5,
        selectField6,
        selectField7,
        selectField8,
        selectField9,
        selectField10,
        selectField11,
        selectField12,
        selectField13,
        selectField14,
        selectField15,
        selectField16,
        selectField17,
        selectField18);
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>
      NativeQuery<
              Record18<
                  T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>>
          buildNativeQuery(
              String aql,
              Class<T1> expected1,
              Class<T2> expected2,
              Class<T3> expected3,
              Class<T4> expected4,
              Class<T5> expected5,
              Class<T6> expected6,
              Class<T7> expected7,
              Class<T8> expected8,
              Class<T9> expected9,
              Class<T10> expected10,
              Class<T11> expected11,
              Class<T12> expected12,
              Class<T13> expected13,
              Class<T14> expected14,
              Class<T15> expected15,
              Class<T16> expected16,
              Class<T17> expected17,
              Class<T18> expected18) {
    return new NativeQuery<>(
        aql,
        AqlField.create(expected1),
        AqlField.create(expected2),
        AqlField.create(expected3),
        AqlField.create(expected4),
        AqlField.create(expected5),
        AqlField.create(expected6),
        AqlField.create(expected7),
        AqlField.create(expected8),
        AqlField.create(expected9),
        AqlField.create(expected10),
        AqlField.create(expected11),
        AqlField.create(expected12),
        AqlField.create(expected13),
        AqlField.create(expected14),
        AqlField.create(expected15),
        AqlField.create(expected16),
        AqlField.create(expected17),
        AqlField.create(expected18));
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19>
      EntityQuery<
              Record19<
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
                  T19>>
          buildEntityQuery(
              ContainmentExpression containment,
              SelectAqlField<T1> selectField1,
              SelectAqlField<T2> selectField2,
              SelectAqlField<T3> selectField3,
              SelectAqlField<T4> selectField4,
              SelectAqlField<T5> selectField5,
              SelectAqlField<T6> selectField6,
              SelectAqlField<T7> selectField7,
              SelectAqlField<T8> selectField8,
              SelectAqlField<T9> selectField9,
              SelectAqlField<T10> selectField10,
              SelectAqlField<T11> selectField11,
              SelectAqlField<T12> selectField12,
              SelectAqlField<T13> selectField13,
              SelectAqlField<T14> selectField14,
              SelectAqlField<T15> selectField15,
              SelectAqlField<T16> selectField16,
              SelectAqlField<T17> selectField17,
              SelectAqlField<T18> selectField18,
              SelectAqlField<T19> selectField19) {
    return new EntityQuery<>(
        containment,
        selectField1,
        selectField2,
        selectField3,
        selectField4,
        selectField5,
        selectField6,
        selectField7,
        selectField8,
        selectField9,
        selectField10,
        selectField11,
        selectField12,
        selectField13,
        selectField14,
        selectField15,
        selectField16,
        selectField17,
        selectField18,
        selectField19);
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19>
      NativeQuery<
              Record19<
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
                  T19>>
          buildNativeQuery(
              String aql,
              Class<T1> expected1,
              Class<T2> expected2,
              Class<T3> expected3,
              Class<T4> expected4,
              Class<T5> expected5,
              Class<T6> expected6,
              Class<T7> expected7,
              Class<T8> expected8,
              Class<T9> expected9,
              Class<T10> expected10,
              Class<T11> expected11,
              Class<T12> expected12,
              Class<T13> expected13,
              Class<T14> expected14,
              Class<T15> expected15,
              Class<T16> expected16,
              Class<T17> expected17,
              Class<T18> expected18,
              Class<T19> expected19) {
    return new NativeQuery<>(
        aql,
        AqlField.create(expected1),
        AqlField.create(expected2),
        AqlField.create(expected3),
        AqlField.create(expected4),
        AqlField.create(expected5),
        AqlField.create(expected6),
        AqlField.create(expected7),
        AqlField.create(expected8),
        AqlField.create(expected9),
        AqlField.create(expected10),
        AqlField.create(expected11),
        AqlField.create(expected12),
        AqlField.create(expected13),
        AqlField.create(expected14),
        AqlField.create(expected15),
        AqlField.create(expected16),
        AqlField.create(expected17),
        AqlField.create(expected18),
        AqlField.create(expected19));
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20>
      EntityQuery<
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
                  T20>>
          buildEntityQuery(
              ContainmentExpression containment,
              SelectAqlField<T1> selectField1,
              SelectAqlField<T2> selectField2,
              SelectAqlField<T3> selectField3,
              SelectAqlField<T4> selectField4,
              SelectAqlField<T5> selectField5,
              SelectAqlField<T6> selectField6,
              SelectAqlField<T7> selectField7,
              SelectAqlField<T8> selectField8,
              SelectAqlField<T9> selectField9,
              SelectAqlField<T10> selectField10,
              SelectAqlField<T11> selectField11,
              SelectAqlField<T12> selectField12,
              SelectAqlField<T13> selectField13,
              SelectAqlField<T14> selectField14,
              SelectAqlField<T15> selectField15,
              SelectAqlField<T16> selectField16,
              SelectAqlField<T17> selectField17,
              SelectAqlField<T18> selectField18,
              SelectAqlField<T19> selectField19,
              SelectAqlField<T20> selectField20) {
    return new EntityQuery<>(
        containment,
        selectField1,
        selectField2,
        selectField3,
        selectField4,
        selectField5,
        selectField6,
        selectField7,
        selectField8,
        selectField9,
        selectField10,
        selectField11,
        selectField12,
        selectField13,
        selectField14,
        selectField15,
        selectField16,
        selectField17,
        selectField18,
        selectField19,
        selectField20);
  }

  static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20>
      NativeQuery<
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
                  T20>>
          buildNativeQuery(
              String aql,
              Class<T1> expected1,
              Class<T2> expected2,
              Class<T3> expected3,
              Class<T4> expected4,
              Class<T5> expected5,
              Class<T6> expected6,
              Class<T7> expected7,
              Class<T8> expected8,
              Class<T9> expected9,
              Class<T10> expected10,
              Class<T11> expected11,
              Class<T12> expected12,
              Class<T13> expected13,
              Class<T14> expected14,
              Class<T15> expected15,
              Class<T16> expected16,
              Class<T17> expected17,
              Class<T18> expected18,
              Class<T19> expected19,
              Class<T20> expected20) {
    return new NativeQuery<>(
        aql,
        AqlField.create(expected1),
        AqlField.create(expected2),
        AqlField.create(expected3),
        AqlField.create(expected4),
        AqlField.create(expected5),
        AqlField.create(expected6),
        AqlField.create(expected7),
        AqlField.create(expected8),
        AqlField.create(expected9),
        AqlField.create(expected10),
        AqlField.create(expected11),
        AqlField.create(expected12),
        AqlField.create(expected13),
        AqlField.create(expected14),
        AqlField.create(expected15),
        AqlField.create(expected16),
        AqlField.create(expected17),
        AqlField.create(expected18),
        AqlField.create(expected19),
        AqlField.create(expected20));
  }

  static <
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
      EntityQuery<
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
                  T21>>
          buildEntityQuery(
              ContainmentExpression containment,
              SelectAqlField<T1> selectField1,
              SelectAqlField<T2> selectField2,
              SelectAqlField<T3> selectField3,
              SelectAqlField<T4> selectField4,
              SelectAqlField<T5> selectField5,
              SelectAqlField<T6> selectField6,
              SelectAqlField<T7> selectField7,
              SelectAqlField<T8> selectField8,
              SelectAqlField<T9> selectField9,
              SelectAqlField<T10> selectField10,
              SelectAqlField<T11> selectField11,
              SelectAqlField<T12> selectField12,
              SelectAqlField<T13> selectField13,
              SelectAqlField<T14> selectField14,
              SelectAqlField<T15> selectField15,
              SelectAqlField<T16> selectField16,
              SelectAqlField<T17> selectField17,
              SelectAqlField<T18> selectField18,
              SelectAqlField<T19> selectField19,
              SelectAqlField<T20> selectField20,
              SelectAqlField<T21> selectField21) {
    return new EntityQuery<>(
        containment,
        selectField1,
        selectField2,
        selectField3,
        selectField4,
        selectField5,
        selectField6,
        selectField7,
        selectField8,
        selectField9,
        selectField10,
        selectField11,
        selectField12,
        selectField13,
        selectField14,
        selectField15,
        selectField16,
        selectField17,
        selectField18,
        selectField19,
        selectField20,
        selectField21);
  }

  static <
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
      NativeQuery<
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
                  T21>>
          buildNativeQuery(
              String aql,
              Class<T1> expected1,
              Class<T2> expected2,
              Class<T3> expected3,
              Class<T4> expected4,
              Class<T5> expected5,
              Class<T6> expected6,
              Class<T7> expected7,
              Class<T8> expected8,
              Class<T9> expected9,
              Class<T10> expected10,
              Class<T11> expected11,
              Class<T12> expected12,
              Class<T13> expected13,
              Class<T14> expected14,
              Class<T15> expected15,
              Class<T16> expected16,
              Class<T17> expected17,
              Class<T18> expected18,
              Class<T19> expected19,
              Class<T20> expected20,
              Class<T21> expected21) {
    return new NativeQuery<>(
        aql,
        AqlField.create(expected1),
        AqlField.create(expected2),
        AqlField.create(expected3),
        AqlField.create(expected4),
        AqlField.create(expected5),
        AqlField.create(expected6),
        AqlField.create(expected7),
        AqlField.create(expected8),
        AqlField.create(expected9),
        AqlField.create(expected10),
        AqlField.create(expected11),
        AqlField.create(expected12),
        AqlField.create(expected13),
        AqlField.create(expected14),
        AqlField.create(expected15),
        AqlField.create(expected16),
        AqlField.create(expected17),
        AqlField.create(expected18),
        AqlField.create(expected19),
        AqlField.create(expected20),
        AqlField.create(expected21));
  }
}
