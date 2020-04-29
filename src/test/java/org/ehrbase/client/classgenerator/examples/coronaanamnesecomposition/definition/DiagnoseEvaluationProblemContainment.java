package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

public class DiagnoseEvaluationProblemContainment extends Containment {
  public SelectAqlField<DiagnoseEvaluationProblem> DIAGNOSE_EVALUATION_PROBLEM = new AqlFieldImp<DiagnoseEvaluationProblem>(DiagnoseEvaluationProblem.class, "", "DiagnoseEvaluationProblem", DiagnoseEvaluationProblem.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(DiagnoseEvaluationProblem.class, "/protocol[at0032]/items[at0071]", "erweiterung", Cluster.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(DiagnoseEvaluationProblem.class, "/data[at0001]/items[at0069]/value|value", "kommentarValue", String.class, this);

  public SelectAqlField<String> DER_DIAGNOSE_VALUE = new AqlFieldImp<String>(DiagnoseEvaluationProblem.class, "/data[at0001]/items[at0002]/value|value", "derDiagnoseValue", String.class, this);

  public ListSelectAqlField<Cluster> ANATOMISCHE_STELLE_STRUKTURIERT = new ListAqlFieldImp<Cluster>(DiagnoseEvaluationProblem.class, "/data[at0001]/items[at0039]", "anatomischeStelleStrukturiert", Cluster.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(DiagnoseEvaluationProblem.class, "/language", "language", Language.class, this);

  public SelectAqlField<DiagnoseAttributCluster> DIAGNOSE_ATTRIBUT = new AqlFieldImp<DiagnoseAttributCluster>(DiagnoseEvaluationProblem.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.problem_qualifier.v1]", "diagnoseAttribut", DiagnoseAttributCluster.class, this);

  public SelectAqlField<TemporalAccessor> ZULETZT_AKTUALISIERT_VALUE = new AqlFieldImp<TemporalAccessor>(DiagnoseEvaluationProblem.class, "/protocol[at0032]/items[at0070]/value|value", "zuletztAktualisiertValue", TemporalAccessor.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(DiagnoseEvaluationProblem.class, "/subject", "subject", PartyProxy.class, this);

  public ListSelectAqlField<Cluster> SPEZIFISCHE_ANGABEN = new ListAqlFieldImp<Cluster>(DiagnoseEvaluationProblem.class, "/data[at0001]/items[at0043]", "spezifischeAngaben", Cluster.class, this);

  public SelectAqlField<ProblemDiagnoseAttributDiagnostischeSicherheitChoice> DIAGNOSTISCHE_SICHERHEIT = new AqlFieldImp<ProblemDiagnoseAttributDiagnostischeSicherheitChoice>(DiagnoseEvaluationProblem.class, "/data[at0001]/items[at0073]/value", "diagnostischeSicherheit", ProblemDiagnoseAttributDiagnostischeSicherheitChoice.class, this);

  private DiagnoseEvaluationProblemContainment() {
    super("openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1");
  }

  public static DiagnoseEvaluationProblemContainment getInstance() {
    return new DiagnoseEvaluationProblemContainment();
  }
}
