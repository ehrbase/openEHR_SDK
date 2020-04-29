package org.ehrbase.client.classgenerator.examples.allergiescomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

public class AdverseReactionRiskEvaluationContainment extends Containment {
  public SelectAqlField<AdverseReactionRiskEvaluation> ADVERSE_REACTION_RISK_EVALUATION = new AqlFieldImp<AdverseReactionRiskEvaluation>(AdverseReactionRiskEvaluation.class, "", "AdverseReactionRiskEvaluation", AdverseReactionRiskEvaluation.class, this);

  public SelectAqlField<CodePhrase> STATUS_DEFININGCODE = new AqlFieldImp<CodePhrase>(AdverseReactionRiskEvaluation.class, "/data[at0001]/items[at0063]/value|defining_code", "statusDefiningcode", CodePhrase.class, this);

  public SelectAqlField<String> SUBSTANCE_VALUE = new AqlFieldImp<String>(AdverseReactionRiskEvaluation.class, "/data[at0001]/items[at0002]/value|value", "substanceValue", String.class, this);

  public SelectAqlField<String> COMMENT_VALUE = new AqlFieldImp<String>(AdverseReactionRiskEvaluation.class, "/data[at0001]/items[at0006]/value|value", "commentValue", String.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(AdverseReactionRiskEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<CodePhrase> REACTION_MECHANISM_DEFININGCODE = new AqlFieldImp<CodePhrase>(AdverseReactionRiskEvaluation.class, "/data[at0001]/items[at0058]/value|defining_code", "reactionMechanismDefiningcode", CodePhrase.class, this);

  public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(AdverseReactionRiskEvaluation.class, "/protocol[at0042]/items[at0128]", "extension", Cluster.class, this);

  public SelectAqlField<CodePhrase> CATEGORY_DEFININGCODE = new AqlFieldImp<CodePhrase>(AdverseReactionRiskEvaluation.class, "/data[at0001]/items[at0120]/value|defining_code", "categoryDefiningcode", CodePhrase.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(AdverseReactionRiskEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<CriticalityDefiningcode> CRITICALITY_DEFININGCODE = new AqlFieldImp<CriticalityDefiningcode>(AdverseReactionRiskEvaluation.class, "/data[at0001]/items[at0101]/value|defining_code", "criticalityDefiningcode", CriticalityDefiningcode.class, this);

  private AdverseReactionRiskEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.adverse_reaction_risk.v1");
  }

  public static AdverseReactionRiskEvaluationContainment getInstance() {
    return new AdverseReactionRiskEvaluationContainment();
  }
}
