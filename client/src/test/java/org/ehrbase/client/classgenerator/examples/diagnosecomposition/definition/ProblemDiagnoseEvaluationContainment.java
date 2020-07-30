package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

import java.time.temporal.TemporalAccessor;

public class ProblemDiagnoseEvaluationContainment extends Containment {
  public SelectAqlField<ProblemDiagnoseEvaluation> PROBLEM_DIAGNOSE_EVALUATION = new AqlFieldImp<ProblemDiagnoseEvaluation>(ProblemDiagnoseEvaluation.class, "", "ProblemDiagnoseEvaluation", ProblemDiagnoseEvaluation.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(ProblemDiagnoseEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<TemporalAccessor> LETZTES_DOKUMENTATIONSDATUM_VALUE = new AqlFieldImp<TemporalAccessor>(ProblemDiagnoseEvaluation.class, "/protocol[at0032]/items[at0070]/value|value", "letztesDokumentationsdatumValue", TemporalAccessor.class, this);

  public SelectAqlField<DiagnosedetailsCluster> DIAGNOSEDETAILS = new AqlFieldImp<DiagnosedetailsCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.diagnose_details.v0]", "diagnosedetails", DiagnosedetailsCluster.class, this);

  public ListSelectAqlField<Cluster> STATUS = new ListAqlFieldImp<Cluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0046]", "status", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> DATUM_DES_AUFTRETENS_DER_ERSTDIAGNOSE_VALUE = new AqlFieldImp<TemporalAccessor>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0077]/value|value", "datumDesAuftretensDerErstdiagnoseValue", TemporalAccessor.class, this);

  public SelectAqlField<AtiopathogeneseCluster> ATIOPATHOGENESE = new AqlFieldImp<AtiopathogeneseCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.etiology.v1]", "atiopathogenese", AtiopathogeneseCluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(ProblemDiagnoseEvaluation.class, "/protocol[at0032]/items[at0071]", "erweiterung", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> FESTSTELLUNGSDATUM_VALUE = new AqlFieldImp<TemporalAccessor>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0003]/value|value", "feststellungsdatumValue", TemporalAccessor.class, this);

  public SelectAqlField<AtiopathogeneseSchweregradChoice> SCHWEREGRAD = new AqlFieldImp<AtiopathogeneseSchweregradChoice>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0005]/value", "schweregrad", AtiopathogeneseSchweregradChoice.class, this);

  public SelectAqlField<TemporalAccessor> DATUM_ZEITPUNKT_DER_GENESUNG_VALUE = new AqlFieldImp<TemporalAccessor>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0030]/value|value", "datumZeitpunktDerGenesungValue", TemporalAccessor.class, this);

  public SelectAqlField<String> DIAGNOSEERLAUTERUNG_VALUE = new AqlFieldImp<String>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0069]/value|value", "diagnoseerlauterungValue", String.class, this);

  public SelectAqlField<NameDesProblemsDerDiagnoseDefiningcode> NAME_DES_PROBLEMS_DER_DIAGNOSE_DEFININGCODE = new AqlFieldImp<NameDesProblemsDerDiagnoseDefiningcode>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0002]/value|defining_code", "nameDesProblemsDerDiagnoseDefiningcode", NameDesProblemsDerDiagnoseDefiningcode.class, this);

  public ListSelectAqlField<AnatomischeLokalisationCluster> ANATOMISCHE_LOKALISATION = new ListAqlFieldImp<AnatomischeLokalisationCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.anatomical_location.v1]", "anatomischeLokalisation", AnatomischeLokalisationCluster.class, this);

  public SelectAqlField<String> FREITEXTBESCHREIBUNG_VALUE = new AqlFieldImp<String>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0009]/value|value", "freitextbeschreibungValue", String.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(ProblemDiagnoseEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<AnatomischeLokalisationDiagnostischeSicherheitChoice> DIAGNOSTISCHE_SICHERHEIT = new AqlFieldImp<AnatomischeLokalisationDiagnostischeSicherheitChoice>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0073]/value", "diagnostischeSicherheit", AnatomischeLokalisationDiagnostischeSicherheitChoice.class, this);

  public SelectAqlField<String> LOKALISATION_VALUE = new AqlFieldImp<String>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0012]/value|value", "lokalisationValue", String.class, this);

  private ProblemDiagnoseEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.problem_diagnosis.v1");
  }

  public static ProblemDiagnoseEvaluationContainment getInstance() {
    return new ProblemDiagnoseEvaluationContainment();
  }
}
