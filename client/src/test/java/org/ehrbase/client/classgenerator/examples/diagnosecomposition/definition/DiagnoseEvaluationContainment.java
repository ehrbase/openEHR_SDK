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

public class DiagnoseEvaluationContainment extends Containment {
  public SelectAqlField<DiagnoseEvaluation> DIAGNOSE_EVALUATION = new AqlFieldImp<DiagnoseEvaluation>(DiagnoseEvaluation.class, "", "DiagnoseEvaluation", DiagnoseEvaluation.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(DiagnoseEvaluation.class, "/data[at0001]/items[at0069]/name|value", "kommentarValue", String.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(DiagnoseEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<TemporalAccessor> ZULETZT_AKTUALISIERT_VALUE = new AqlFieldImp<TemporalAccessor>(DiagnoseEvaluation.class, "/protocol[at0032]/items[at0070]/value|value", "zuletztAktualisiertValue", TemporalAccessor.class, this);

  public SelectAqlField<DiagnosedetailsCluster> DIAGNOSEDETAILS = new AqlFieldImp<DiagnosedetailsCluster>(DiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.diagnose_details.v0]", "diagnosedetails", DiagnosedetailsCluster.class, this);

  public ListSelectAqlField<Cluster> STATUS = new ListAqlFieldImp<Cluster>(DiagnoseEvaluation.class, "/data[at0001]/items[at0046]", "status", Cluster.class, this);

  public SelectAqlField<String> KLINISCHE_BESCHREIBUNG_VALUE = new AqlFieldImp<String>(DiagnoseEvaluation.class, "/data[at0001]/items[at0009]/name|value", "klinischeBeschreibungValue", String.class, this);

  public SelectAqlField<TemporalAccessor> DER_ERSTDIAGNOSE_VALUE = new AqlFieldImp<TemporalAccessor>(DiagnoseEvaluation.class, "/data[at0001]/items[at0077]/value|value", "derErstdiagnoseValue", TemporalAccessor.class, this);

  public SelectAqlField<AtiopathogeneseCluster> ATIOPATHOGENESE = new AqlFieldImp<AtiopathogeneseCluster>(DiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.etiology.v1]", "atiopathogenese", AtiopathogeneseCluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(DiagnoseEvaluation.class, "/protocol[at0032]/items[at0071]", "erweiterung", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> ZEITPUNKT_DER_KLINISCHEN_FESTSTELLUNG_VALUE = new AqlFieldImp<TemporalAccessor>(DiagnoseEvaluation.class, "/data[at0001]/items[at0003]/value|value", "zeitpunktDerKlinischenFeststellungValue", TemporalAccessor.class, this);

  public SelectAqlField<AtiopathogeneseSchweregradChoice> SCHWEREGRAD = new AqlFieldImp<AtiopathogeneseSchweregradChoice>(DiagnoseEvaluation.class, "/data[at0001]/items[at0005]/value", "schweregrad", AtiopathogeneseSchweregradChoice.class, this);

  public SelectAqlField<TemporalAccessor> ZEITPUNKT_DER_GENESUNG_VALUE = new AqlFieldImp<TemporalAccessor>(DiagnoseEvaluation.class, "/data[at0001]/items[at0030]/value|value", "zeitpunktDerGenesungValue", TemporalAccessor.class, this);

  public SelectAqlField<String> ZULETZT_AKTUALISIERT_VALUE_TREE = new AqlFieldImp<String>(DiagnoseEvaluation.class, "/protocol[at0032]/items[at0070]/name|value", "zuletztAktualisiertValueTree", String.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE_STRUCTURE = new AqlFieldImp<String>(DiagnoseEvaluation.class, "/data[at0001]/items[at0069]/value|value", "kommentarValueStructure", String.class, this);

  public SelectAqlField<DerDiagnoseDefiningcode> DER_DIAGNOSE_DEFININGCODE = new AqlFieldImp<DerDiagnoseDefiningcode>(DiagnoseEvaluation.class, "/data[at0001]/items[at0002]/value|defining_code", "derDiagnoseDefiningcode", DerDiagnoseDefiningcode.class, this);

  public SelectAqlField<String> KORPERSTELLE_VALUE = new AqlFieldImp<String>(DiagnoseEvaluation.class, "/data[at0001]/items[at0012]/name|value", "korperstelleValue", String.class, this);

  public ListSelectAqlField<AnatomischeLokalisationCluster> ANATOMISCHE_LOKALISATION = new ListAqlFieldImp<AnatomischeLokalisationCluster>(DiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.anatomical_location.v1]", "anatomischeLokalisation", AnatomischeLokalisationCluster.class, this);

  public SelectAqlField<String> DER_ERSTDIAGNOSE_VALUE_ZEITPUNKT_DES_AUFTRETENS = new AqlFieldImp<String>(DiagnoseEvaluation.class, "/data[at0001]/items[at0077]/name|value", "derErstdiagnoseValueZeitpunktDesAuftretens", String.class, this);

  public SelectAqlField<String> KLINISCHE_BESCHREIBUNG_VALUE_STRUCTURE = new AqlFieldImp<String>(DiagnoseEvaluation.class, "/data[at0001]/items[at0009]/value|value", "klinischeBeschreibungValueStructure", String.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(DiagnoseEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<String> ZEITPUNKT_DER_KLINISCHEN_FESTSTELLUNG_VALUE_DATUM = new AqlFieldImp<String>(DiagnoseEvaluation.class, "/data[at0001]/items[at0003]/name|value", "zeitpunktDerKlinischenFeststellungValueDatum", String.class, this);

  public SelectAqlField<AnatomischeLokalisationDiagnostischeSicherheitChoice> DIAGNOSTISCHE_SICHERHEIT = new AqlFieldImp<AnatomischeLokalisationDiagnostischeSicherheitChoice>(DiagnoseEvaluation.class, "/data[at0001]/items[at0073]/value", "diagnostischeSicherheit", AnatomischeLokalisationDiagnostischeSicherheitChoice.class, this);

  public SelectAqlField<String> KORPERSTELLE_VALUE_STRUCTURE = new AqlFieldImp<String>(DiagnoseEvaluation.class, "/data[at0001]/items[at0012]/value|value", "korperstelleValueStructure", String.class, this);

  private DiagnoseEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.problem_diagnosis.v1");
  }

  public static DiagnoseEvaluationContainment getInstance() {
    return new DiagnoseEvaluationContainment();
  }
}
