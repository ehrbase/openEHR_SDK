package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.DiagnosisNameDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.problem_diagnosis.v1")
public class DiagnosisEvaluation {
  @Path("/protocol[at0032]/items[at0071]")
  private List<Cluster> extension;

  @Path("/data[at0001]/items[at0002]/value|defining_code")
  private DiagnosisNameDefiningcode diagnosisNameDefiningcode;

  @Path("/data[at0001]/items[at0039]")
  private List<Cluster> structuredBodySite;

  @Path("/language")
  private Language language;

  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.problem_qualifier.v1]")
  private List<DiagnosisQualifierCluster> diagnosisQualifier;

  @Path("/protocol[at0032]/items[at0070]/value|value")
  private TemporalAccessor lastUpdatedValue;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/data[at0001]/items[at0043]")
  private List<Cluster> specificDetails;

  public void setExtension(List<Cluster> extension) {
     this.extension = extension;
  }

  public List<Cluster> getExtension() {
     return this.extension ;
  }

  public void setDiagnosisNameDefiningcode(DiagnosisNameDefiningcode diagnosisNameDefiningcode) {
     this.diagnosisNameDefiningcode = diagnosisNameDefiningcode;
  }

  public DiagnosisNameDefiningcode getDiagnosisNameDefiningcode() {
     return this.diagnosisNameDefiningcode ;
  }

  public void setStructuredBodySite(List<Cluster> structuredBodySite) {
     this.structuredBodySite = structuredBodySite;
  }

  public List<Cluster> getStructuredBodySite() {
     return this.structuredBodySite ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setDiagnosisQualifier(List<DiagnosisQualifierCluster> diagnosisQualifier) {
     this.diagnosisQualifier = diagnosisQualifier;
  }

  public List<DiagnosisQualifierCluster> getDiagnosisQualifier() {
     return this.diagnosisQualifier ;
  }

  public void setLastUpdatedValue(TemporalAccessor lastUpdatedValue) {
     this.lastUpdatedValue = lastUpdatedValue;
  }

  public TemporalAccessor getLastUpdatedValue() {
     return this.lastUpdatedValue ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setSpecificDetails(List<Cluster> specificDetails) {
     this.specificDetails = specificDetails;
  }

  public List<Cluster> getSpecificDetails() {
     return this.specificDetails ;
  }
}
