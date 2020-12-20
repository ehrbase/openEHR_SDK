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

public class PastHistoryEvaluationContainment extends Containment {
  public SelectAqlField<PastHistoryEvaluation> PAST_HISTORY_EVALUATION =
      new AqlFieldImp<PastHistoryEvaluation>(
          PastHistoryEvaluation.class,
          "",
          "PastHistoryEvaluation",
          PastHistoryEvaluation.class,
          this);

  public SelectAqlField<String> SYNOPSIS_VALUE =
      new AqlFieldImp<String>(
          PastHistoryEvaluation.class,
          "/data[at0001]/items[at0002]/value|value",
          "synopsisValue",
          String.class,
          this);

  public ListSelectAqlField<Cluster> EXTENSION =
      new ListAqlFieldImp<Cluster>(
          PastHistoryEvaluation.class,
          "/protocol[at0003]/items[at0004]",
          "extension",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          PastHistoryEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          PastHistoryEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          PastHistoryEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private PastHistoryEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.clinical_synopsis.v1");
  }

  public static PastHistoryEvaluationContainment getInstance() {
    return new PastHistoryEvaluationContainment();
  }
}
