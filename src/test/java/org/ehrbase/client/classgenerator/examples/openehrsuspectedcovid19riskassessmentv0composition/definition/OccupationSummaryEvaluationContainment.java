package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

public class OccupationSummaryEvaluationContainment extends Containment {
  public SelectAqlField<OccupationSummaryEvaluation> OCCUPATION_SUMMARY_EVALUATION = new AqlFieldImp<OccupationSummaryEvaluation>(OccupationSummaryEvaluation.class, "", "OccupationSummaryEvaluation", OccupationSummaryEvaluation.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(OccupationSummaryEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(OccupationSummaryEvaluation.class, "/language", "language", Language.class, this);

  public ListSelectAqlField<OccupationRecordCluster> OCCUPATION_RECORD = new ListAqlFieldImp<OccupationRecordCluster>(OccupationSummaryEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.occupation_record.v1]", "occupationRecord", OccupationRecordCluster.class, this);

  public ListSelectAqlField<Cluster> ADDITIONAL_DETAILS = new ListAqlFieldImp<Cluster>(OccupationSummaryEvaluation.class, "/data[at0001]/items[at0005]", "additionalDetails", Cluster.class, this);

  public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(OccupationSummaryEvaluation.class, "/protocol[at0007]/items[at0008]", "extension", Cluster.class, this);

  private OccupationSummaryEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.occupation_summary.v1");
  }

  public static OccupationSummaryEvaluationContainment getInstance() {
    return new OccupationSummaryEvaluationContainment();
  }
}
