package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

import java.util.List;

@Entity
@Archetype("openEHR-EHR-EVALUATION.health_risk-covid.v0")
public class Covid19ExposureEvaluation {
  @Path("/data[at0001]/items[at0002.1]/value|defining_code")
  private HealthRiskDefiningCode healthRiskDefiningCode;

  @Path("/data[at0001]/items[at0016 and name/value='Care home has suspected/confirmed Covid-19']/items[at0013.1]/value|defining_code")
  private RiskFactorDefiningCode riskFactorDefiningCode;

  @Path("/data[at0001]/items[at0016 and name/value='Care home has suspected/confirmed Covid-19']/items[at0017.1]/value|defining_code")
  private PresenceDefiningCode2 presenceDefiningCode;

  @Path("/data[at0001]/items[at0016 and name/value='Care home has suspected/confirmed Covid-19']/items[at0027.1]")
  private List<Cluster> careHomeHasSuspectedConfirmedCovid19Detail;

  @Path("/data[at0001]/items[at0016 and name/value='Contact with confirmed case']/items[at0013.1]/value|defining_code")
  private RiskFactorDefiningCode2 riskFactorDefiningCode2;

  @Path("/data[at0001]/items[at0016 and name/value='Contact with confirmed case']/items[at0017.1]/value|defining_code")
  private PresenceDefiningCode2 presenceDefiningCode2;

  @Path("/data[at0001]/items[at0016 and name/value='Contact with confirmed case']/items[at0027.1]")
  private List<Cluster> contactWithConfirmedCaseDetail;

  @Path("/data[at0001]/items[at0016 and name/value='Other residents/household members unwell']/items[at0013.1]/value|defining_code")
  private RiskFactorDefiningCode3 riskFactorDefiningCode3;

  @Path("/data[at0001]/items[at0016 and name/value='Other residents/household members unwell']/items[at0017.1]/value|defining_code")
  private PresenceDefiningCode2 presenceDefiningCode3;

  @Path("/data[at0001]/items[at0016 and name/value='Other residents/household members unwell']/items[at0027.1]")
  private List<Cluster> otherResidentsHouseholdMembersUnwellDetail;

  @Path("/data[at0001]/items[at0003.1]/value|defining_code")
  private RiskAssessmentDefiningCode riskAssessmentDefiningCode;

  @Path("/protocol[at0010]/items[at0011]")
  private List<Cluster> extension;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/language")
  private Language language;

  public void setHealthRiskDefiningCode(HealthRiskDefiningCode healthRiskDefiningCode) {
     this.healthRiskDefiningCode = healthRiskDefiningCode;
  }

  public HealthRiskDefiningCode getHealthRiskDefiningCode() {
     return this.healthRiskDefiningCode ;
  }

  public void setRiskFactorDefiningCode(RiskFactorDefiningCode riskFactorDefiningCode) {
     this.riskFactorDefiningCode = riskFactorDefiningCode;
  }

  public RiskFactorDefiningCode getRiskFactorDefiningCode() {
     return this.riskFactorDefiningCode ;
  }

  public void setPresenceDefiningCode(PresenceDefiningCode2 presenceDefiningCode) {
     this.presenceDefiningCode = presenceDefiningCode;
  }

  public PresenceDefiningCode2 getPresenceDefiningCode() {
     return this.presenceDefiningCode ;
  }

  public void setCareHomeHasSuspectedConfirmedCovid19Detail(
      List<Cluster> careHomeHasSuspectedConfirmedCovid19Detail) {
     this.careHomeHasSuspectedConfirmedCovid19Detail = careHomeHasSuspectedConfirmedCovid19Detail;
  }

  public List<Cluster> getCareHomeHasSuspectedConfirmedCovid19Detail() {
     return this.careHomeHasSuspectedConfirmedCovid19Detail ;
  }

  public void setRiskFactorDefiningCode2(RiskFactorDefiningCode2 riskFactorDefiningCode2) {
     this.riskFactorDefiningCode2 = riskFactorDefiningCode2;
  }

  public RiskFactorDefiningCode2 getRiskFactorDefiningCode2() {
     return this.riskFactorDefiningCode2 ;
  }

  public void setPresenceDefiningCode2(PresenceDefiningCode2 presenceDefiningCode2) {
     this.presenceDefiningCode2 = presenceDefiningCode2;
  }

  public PresenceDefiningCode2 getPresenceDefiningCode2() {
     return this.presenceDefiningCode2 ;
  }

  public void setContactWithConfirmedCaseDetail(List<Cluster> contactWithConfirmedCaseDetail) {
     this.contactWithConfirmedCaseDetail = contactWithConfirmedCaseDetail;
  }

  public List<Cluster> getContactWithConfirmedCaseDetail() {
     return this.contactWithConfirmedCaseDetail ;
  }

  public void setRiskFactorDefiningCode3(RiskFactorDefiningCode3 riskFactorDefiningCode3) {
     this.riskFactorDefiningCode3 = riskFactorDefiningCode3;
  }

  public RiskFactorDefiningCode3 getRiskFactorDefiningCode3() {
     return this.riskFactorDefiningCode3 ;
  }

  public void setPresenceDefiningCode3(PresenceDefiningCode2 presenceDefiningCode3) {
     this.presenceDefiningCode3 = presenceDefiningCode3;
  }

  public PresenceDefiningCode2 getPresenceDefiningCode3() {
     return this.presenceDefiningCode3 ;
  }

  public void setOtherResidentsHouseholdMembersUnwellDetail(
      List<Cluster> otherResidentsHouseholdMembersUnwellDetail) {
     this.otherResidentsHouseholdMembersUnwellDetail = otherResidentsHouseholdMembersUnwellDetail;
  }

  public List<Cluster> getOtherResidentsHouseholdMembersUnwellDetail() {
     return this.otherResidentsHouseholdMembersUnwellDetail ;
  }

  public void setRiskAssessmentDefiningCode(RiskAssessmentDefiningCode riskAssessmentDefiningCode) {
     this.riskAssessmentDefiningCode = riskAssessmentDefiningCode;
  }

  public RiskAssessmentDefiningCode getRiskAssessmentDefiningCode() {
     return this.riskAssessmentDefiningCode ;
  }

  public void setExtension(List<Cluster> extension) {
     this.extension = extension;
  }

  public List<Cluster> getExtension() {
     return this.extension ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }
}
