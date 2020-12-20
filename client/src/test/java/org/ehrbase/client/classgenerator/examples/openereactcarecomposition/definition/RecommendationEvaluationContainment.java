package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class RecommendationEvaluationContainment extends Containment {
  public SelectAqlField<RecommendationEvaluation> RECOMMENDATION_EVALUATION =
      new AqlFieldImp<RecommendationEvaluation>(
          RecommendationEvaluation.class,
          "",
          "RecommendationEvaluation",
          RecommendationEvaluation.class,
          this);

  public ListSelectAqlField<RecommendationRecommendationElement> RECOMMENDATION =
      new ListAqlFieldImp<RecommendationRecommendationElement>(
          RecommendationEvaluation.class,
          "/data[at0001]/items[at0002]",
          "recommendation",
          RecommendationRecommendationElement.class,
          this);

  public ListSelectAqlField<Cluster> EXTENSION =
      new ListAqlFieldImp<Cluster>(
          RecommendationEvaluation.class,
          "/protocol[at0004]/items[at0005]",
          "extension",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          RecommendationEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          RecommendationEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          RecommendationEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private RecommendationEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.recommendation.v1");
  }

  public static RecommendationEvaluationContainment getInstance() {
    return new RecommendationEvaluationContainment();
  }
}
