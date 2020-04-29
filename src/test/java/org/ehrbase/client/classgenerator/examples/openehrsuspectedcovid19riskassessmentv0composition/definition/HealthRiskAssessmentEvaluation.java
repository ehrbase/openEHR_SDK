package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.HealthRiskDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.health_risk.v1")
public class HealthRiskAssessmentEvaluation {
  @Path("/protocol[at0010]/items[at0024]/value|value")
  private TemporalAccessor lastUpdatedValue;

  @Path("/data[at0001]/items[at0016]/items[at0013]/value")
  @Choice
  private HealthRiskAssessmentRiskFactorChoice riskFactor;

  @Path("/data[at0001]/items[at0003]/value|value")
  private String riskAssessmentValue;

  @Path("/protocol[at0010]/items[at0011]")
  private List<Cluster> extension;

  @Path("/data[at0001]/items[at0002]/value|defining_code")
  private HealthRiskDefiningcode healthRiskDefiningcode;

  @Path("/data[at0001]/items[at0016]/name")
  @Choice
  private HealthRiskAssessmentRiskFactorsChoice riskFactors;

  @Path("/language")
  private Language language;

  @Path("/data[at0001]/items[at0016]/items[at0017]/value")
  @Choice
  private HealthRiskAssessmentPresenceChoice presence;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/protocol[at0010]/items[at0025]/value|value")
  private String assessmentMethodValue;

  @Path("/data[at0001]/items[at0016]/items[at0027]")
  @Choice
  private HealthRiskAssessmentDetailChoice detail;

  public void setLastUpdatedValue(TemporalAccessor lastUpdatedValue) {
     this.lastUpdatedValue = lastUpdatedValue;
  }

  public TemporalAccessor getLastUpdatedValue() {
     return this.lastUpdatedValue ;
  }

  public void setRiskFactor(HealthRiskAssessmentRiskFactorChoice riskFactor) {
     this.riskFactor = riskFactor;
  }

  public HealthRiskAssessmentRiskFactorChoice getRiskFactor() {
     return this.riskFactor ;
  }

  public void setRiskAssessmentValue(String riskAssessmentValue) {
     this.riskAssessmentValue = riskAssessmentValue;
  }

  public String getRiskAssessmentValue() {
     return this.riskAssessmentValue ;
  }

  public void setExtension(List<Cluster> extension) {
     this.extension = extension;
  }

  public List<Cluster> getExtension() {
     return this.extension ;
  }

  public void setHealthRiskDefiningcode(HealthRiskDefiningcode healthRiskDefiningcode) {
     this.healthRiskDefiningcode = healthRiskDefiningcode;
  }

  public HealthRiskDefiningcode getHealthRiskDefiningcode() {
     return this.healthRiskDefiningcode ;
  }

  public void setRiskFactors(HealthRiskAssessmentRiskFactorsChoice riskFactors) {
     this.riskFactors = riskFactors;
  }

  public HealthRiskAssessmentRiskFactorsChoice getRiskFactors() {
     return this.riskFactors ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setPresence(HealthRiskAssessmentPresenceChoice presence) {
     this.presence = presence;
  }

  public HealthRiskAssessmentPresenceChoice getPresence() {
     return this.presence ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setAssessmentMethodValue(String assessmentMethodValue) {
     this.assessmentMethodValue = assessmentMethodValue;
  }

  public String getAssessmentMethodValue() {
     return this.assessmentMethodValue ;
  }

  public void setDetail(HealthRiskAssessmentDetailChoice detail) {
     this.detail = detail;
  }

  public HealthRiskAssessmentDetailChoice getDetail() {
     return this.detail ;
  }
}
