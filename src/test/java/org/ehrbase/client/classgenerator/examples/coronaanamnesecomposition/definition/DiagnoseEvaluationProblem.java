package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1")
public class DiagnoseEvaluationProblem {
  @Path("/protocol[at0032]/items[at0071]")
  private List<Cluster> erweiterung;

  @Path("/data[at0001]/items[at0069]/value|value")
  private String kommentarValue;

  @Path("/data[at0001]/items[at0002]/value|value")
  private String derDiagnoseValue;

  @Path("/data[at0001]/items[at0039]")
  private List<Cluster> anatomischeStelleStrukturiert;

  @Path("/language")
  private Language language;

  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.problem_qualifier.v1]")
  private DiagnoseAttributCluster diagnoseAttribut;

  @Path("/protocol[at0032]/items[at0070]/value|value")
  private TemporalAccessor zuletztAktualisiertValue;

  @Path("/subject")
  private PartyProxy subject;

    @Path("/name")
    private DvText name;

    public DvText getName() {
        return name;
    }

    public void setName(DvText name) {
        this.name = name;
    }

    @Path("/data[at0001]/items[at0043]")
  private List<Cluster> spezifischeAngaben;

  @Path("/data[at0001]/items[at0073]/value")
  @Choice
  private ProblemDiagnoseAttributDiagnostischeSicherheitChoice diagnostischeSicherheit;

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
  }

  public void setDerDiagnoseValue(String derDiagnoseValue) {
     this.derDiagnoseValue = derDiagnoseValue;
  }

  public String getDerDiagnoseValue() {
     return this.derDiagnoseValue ;
  }

  public void setAnatomischeStelleStrukturiert(List<Cluster> anatomischeStelleStrukturiert) {
     this.anatomischeStelleStrukturiert = anatomischeStelleStrukturiert;
  }

  public List<Cluster> getAnatomischeStelleStrukturiert() {
     return this.anatomischeStelleStrukturiert ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setDiagnoseAttribut(DiagnoseAttributCluster diagnoseAttribut) {
     this.diagnoseAttribut = diagnoseAttribut;
  }

  public DiagnoseAttributCluster getDiagnoseAttribut() {
     return this.diagnoseAttribut ;
  }

  public void setZuletztAktualisiertValue(TemporalAccessor zuletztAktualisiertValue) {
     this.zuletztAktualisiertValue = zuletztAktualisiertValue;
  }

  public TemporalAccessor getZuletztAktualisiertValue() {
     return this.zuletztAktualisiertValue ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setSpezifischeAngaben(List<Cluster> spezifischeAngaben) {
     this.spezifischeAngaben = spezifischeAngaben;
  }

  public List<Cluster> getSpezifischeAngaben() {
     return this.spezifischeAngaben ;
  }

  public void setDiagnostischeSicherheit(
      ProblemDiagnoseAttributDiagnostischeSicherheitChoice diagnostischeSicherheit) {
     this.diagnostischeSicherheit = diagnostischeSicherheit;
  }

  public ProblemDiagnoseAttributDiagnostischeSicherheitChoice getDiagnostischeSicherheit() {
     return this.diagnostischeSicherheit ;
  }
}
