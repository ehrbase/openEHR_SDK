package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datatypes.CodePhrase;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-CLUSTER.symptom_sign-cvid.v0")
public class FirstSymptomsCluster {
  @Path("/items[at0001.1]/value|defining_code")
  private CodePhrase symptomSignNameDefiningCode;

  @Path("/items[at0147]")
  private List<Cluster> structuredBodySite;

  @Path("/items[at0152 and name/value='Date of onset of first symptoms']/value|value")
  private TemporalAccessor dateOfOnsetOfFirstSymptomsValue;

  @Path("/items[at0028]/value|value")
  private TemporalAmount durationValue;

  @Path("/items[at0180 and name/value='Trend']/value|defining_code")
  private TrendDefiningCode trendDefiningCode;

  @Path("/items[at0155]/value|value")
  private String impactValue;

  @Path("/items[at0153]")
  private List<Cluster> specificDetails;

  @Path("/items[at0146]")
  private List<Cluster> previousEpisodes;

  @Path("/items[at0063]")
  private List<Cluster> associatedSymptomSign;

  @Path("/items[at0.1]/value|defining_code")
  private PresenceDefiningCode presenceDefiningCode;

  public void setSymptomSignNameDefiningCode(CodePhrase symptomSignNameDefiningCode) {
     this.symptomSignNameDefiningCode = symptomSignNameDefiningCode;
  }

  public CodePhrase getSymptomSignNameDefiningCode() {
     return this.symptomSignNameDefiningCode ;
  }

  public void setStructuredBodySite(List<Cluster> structuredBodySite) {
     this.structuredBodySite = structuredBodySite;
  }

  public List<Cluster> getStructuredBodySite() {
     return this.structuredBodySite ;
  }

  public void setDateOfOnsetOfFirstSymptomsValue(TemporalAccessor dateOfOnsetOfFirstSymptomsValue) {
     this.dateOfOnsetOfFirstSymptomsValue = dateOfOnsetOfFirstSymptomsValue;
  }

  public TemporalAccessor getDateOfOnsetOfFirstSymptomsValue() {
     return this.dateOfOnsetOfFirstSymptomsValue ;
  }

  public void setDurationValue(TemporalAmount durationValue) {
     this.durationValue = durationValue;
  }

  public TemporalAmount getDurationValue() {
     return this.durationValue ;
  }

  public void setTrendDefiningCode(TrendDefiningCode trendDefiningCode) {
     this.trendDefiningCode = trendDefiningCode;
  }

  public TrendDefiningCode getTrendDefiningCode() {
     return this.trendDefiningCode ;
  }

  public void setImpactValue(String impactValue) {
     this.impactValue = impactValue;
  }

  public String getImpactValue() {
     return this.impactValue ;
  }

  public void setSpecificDetails(List<Cluster> specificDetails) {
     this.specificDetails = specificDetails;
  }

  public List<Cluster> getSpecificDetails() {
     return this.specificDetails ;
  }

  public void setPreviousEpisodes(List<Cluster> previousEpisodes) {
     this.previousEpisodes = previousEpisodes;
  }

  public List<Cluster> getPreviousEpisodes() {
     return this.previousEpisodes ;
  }

  public void setAssociatedSymptomSign(List<Cluster> associatedSymptomSign) {
     this.associatedSymptomSign = associatedSymptomSign;
  }

  public List<Cluster> getAssociatedSymptomSign() {
     return this.associatedSymptomSign ;
  }

  public void setPresenceDefiningCode(PresenceDefiningCode presenceDefiningCode) {
     this.presenceDefiningCode = presenceDefiningCode;
  }

  public PresenceDefiningCode getPresenceDefiningCode() {
     return this.presenceDefiningCode ;
  }
}
