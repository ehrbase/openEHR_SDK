package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartyProxy;
import java.net.URI;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class TestAllTypesEvaluationContainment extends Containment {
  public SelectAqlField<TestAllTypesEvaluation> TEST_ALL_TYPES_EVALUATION =
      new AqlFieldImp<TestAllTypesEvaluation>(
          TestAllTypesEvaluation.class,
          "",
          "TestAllTypesEvaluation",
          TestAllTypesEvaluation.class,
          this);

  public SelectAqlField<URI> URI_VALUE =
      new AqlFieldImp<URI>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0002]/value|value",
          "uriValue",
          URI.class,
          this);

  public SelectAqlField<Long> UPPER_MAGNITUDE =
      new AqlFieldImp<Long>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0003]/value/upper|magnitude",
          "upperMagnitude",
          Long.class,
          this);

  public SelectAqlField<Long> LOWER_MAGNITUDE =
      new AqlFieldImp<Long>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0003]/value/lower|magnitude",
          "lowerMagnitude",
          Long.class,
          this);

  public SelectAqlField<Boolean> INTERVAL_COUNT_LOWER_INCLUDED =
      new AqlFieldImp<Boolean>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0003]/value/lower_included",
          "intervalCountLowerIncluded",
          Boolean.class,
          this);

  public SelectAqlField<Boolean> INTERVAL_COUNT_UPPER_INCLUDED =
      new AqlFieldImp<Boolean>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0003]/value/upper_included",
          "intervalCountUpperIncluded",
          Boolean.class,
          this);

  public SelectAqlField<Double> UPPER_MAGNITUDE2 =
      new AqlFieldImp<Double>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0004]/value/upper|magnitude",
          "upperMagnitude2",
          Double.class,
          this);

  public SelectAqlField<String> UPPER_UNITS =
      new AqlFieldImp<String>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0004]/value/upper|units",
          "upperUnits",
          String.class,
          this);

  public SelectAqlField<Double> LOWER_MAGNITUDE2 =
      new AqlFieldImp<Double>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0004]/value/lower|magnitude",
          "lowerMagnitude2",
          Double.class,
          this);

  public SelectAqlField<String> LOWER_UNITS =
      new AqlFieldImp<String>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0004]/value/lower|units",
          "lowerUnits",
          String.class,
          this);

  public SelectAqlField<Boolean> INTERVAL_QUANTITY_LOWER_INCLUDED =
      new AqlFieldImp<Boolean>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0004]/value/lower_included",
          "intervalQuantityLowerIncluded",
          Boolean.class,
          this);

  public SelectAqlField<Boolean> INTERVAL_QUANTITY_UPPER_INCLUDED =
      new AqlFieldImp<Boolean>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0004]/value/upper_included",
          "intervalQuantityUpperIncluded",
          Boolean.class,
          this);

  public SelectAqlField<TemporalAccessor> INTERVAL_DATETIME_UPPER_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0005]/value/upper|value",
          "intervalDatetimeUpperValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> INTERVAL_DATETIME_LOWER_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0005]/value/lower|value",
          "intervalDatetimeLowerValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<Boolean> INTERVAL_DATETIME_LOWER_INCLUDED =
      new AqlFieldImp<Boolean>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0005]/value/lower_included",
          "intervalDatetimeLowerIncluded",
          Boolean.class,
          this);

  public SelectAqlField<Boolean> INTERVAL_DATETIME_UPPER_INCLUDED =
      new AqlFieldImp<Boolean>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0005]/value/upper_included",
          "intervalDatetimeUpperIncluded",
          Boolean.class,
          this);

  public SelectAqlField<String> TEXT2_VALUE =
      new AqlFieldImp<String>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0006]/items[at0007]/items[at0008]/items[at0010]/value|value",
          "text2Value",
          String.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          TestAllTypesEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          TestAllTypesEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          TestAllTypesEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<TestAllTypesChoiceChoice> CHOICE =
      new AqlFieldImp<TestAllTypesChoiceChoice>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0009]/value",
          "choice",
          TestAllTypesChoiceChoice.class,
          this);

  private TestAllTypesEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.test_all_types.v1");
  }

  public static TestAllTypesEvaluationContainment getInstance() {
    return new TestAllTypesEvaluationContainment();
  }
}
