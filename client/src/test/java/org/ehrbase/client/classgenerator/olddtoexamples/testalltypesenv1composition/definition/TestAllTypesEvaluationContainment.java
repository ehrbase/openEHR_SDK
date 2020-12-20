package org.ehrbase.client.classgenerator.olddtoexamples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rm.generic.PartyProxy;
import java.net.URI;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.Language;

public class TestAllTypesEvaluationContainment extends Containment {
  public SelectAqlField<TestAllTypesEvaluation> TEST_ALL_TYPES_EVALUATION =
      new AqlFieldImp<TestAllTypesEvaluation>(
          TestAllTypesEvaluation.class,
          "",
          "TestAllTypesEvaluation",
          TestAllTypesEvaluation.class,
          this);

  public SelectAqlField<DvInterval> INTERVAL_QUANTITY =
      new AqlFieldImp<DvInterval>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0004]/value",
          "intervalQuantity",
          DvInterval.class,
          this);

  public SelectAqlField<String> TEXT2_VALUE =
      new AqlFieldImp<String>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0006]/items[at0007]/items[at0008]/items[at0010]/value|value",
          "text2Value",
          String.class,
          this);

  public SelectAqlField<DvInterval> INTERVAL_COUNT =
      new AqlFieldImp<DvInterval>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0003]/value",
          "intervalCount",
          DvInterval.class,
          this);

  public SelectAqlField<DvInterval> INTERVAL_DATETIME =
      new AqlFieldImp<DvInterval>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0005]/value",
          "intervalDatetime",
          DvInterval.class,
          this);

  public SelectAqlField<URI> URI_VALUE =
      new AqlFieldImp<URI>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0002]/value|value",
          "uriValue",
          URI.class,
          this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          TestAllTypesEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<TestAllTypesChoiceChoice> CHOICE =
      new AqlFieldImp<TestAllTypesChoiceChoice>(
          TestAllTypesEvaluation.class,
          "/data[at0001]/items[at0009]/value",
          "choice",
          TestAllTypesChoiceChoice.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          TestAllTypesEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  private TestAllTypesEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.test_all_types.v1");
  }

  public static TestAllTypesEvaluationContainment getInstance() {
    return new TestAllTypesEvaluationContainment();
  }
}
