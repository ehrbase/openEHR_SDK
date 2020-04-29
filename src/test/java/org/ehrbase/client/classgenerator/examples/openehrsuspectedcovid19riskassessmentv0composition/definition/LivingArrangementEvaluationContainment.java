package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Long;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

public class LivingArrangementEvaluationContainment extends Containment {
  public SelectAqlField<LivingArrangementEvaluation> LIVING_ARRANGEMENT_EVALUATION = new AqlFieldImp<LivingArrangementEvaluation>(LivingArrangementEvaluation.class, "", "LivingArrangementEvaluation", LivingArrangementEvaluation.class, this);

  public ListSelectAqlField<DwellingCluster> DWELLING = new ListAqlFieldImp<DwellingCluster>(LivingArrangementEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.dwelling.v0]", "dwelling", DwellingCluster.class, this);

  public SelectAqlField<String> DESCRIPTION_VALUE = new AqlFieldImp<String>(LivingArrangementEvaluation.class, "/data[at0001]/items[at0003]/value|value", "descriptionValue", String.class, this);

  public SelectAqlField<Long> NUMBER_OF_HOUSEHOLD_MEMBERS_MAGNITUDE = new AqlFieldImp<Long>(LivingArrangementEvaluation.class, "/data[at0001]/items[at0007]/value|magnitude", "numberOfHouseholdMembersMagnitude", Long.class, this);

  public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(LivingArrangementEvaluation.class, "/protocol[at0002]/items[at0011]", "extension", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(LivingArrangementEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(LivingArrangementEvaluation.class, "/language", "language", Language.class, this);

  private LivingArrangementEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.living_arrangement.v0");
  }

  public static LivingArrangementEvaluationContainment getInstance() {
    return new LivingArrangementEvaluationContainment();
  }
}
