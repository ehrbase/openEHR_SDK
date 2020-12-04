package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.health_risk-covid.v0")
public class Covid19ExposureEvaluation {
  /**
   * open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Health risk
   */
  @Path("/data[at0001]/items[at0002.1]/value|defining_code")
  private HealthRiskDefiningCode healthRiskDefiningCode;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Care home has suspected/confirmed Covid-19/Risk factor
   */
  @Path("/data[at0001]/items[at0016 and name/value='Care home has suspected/confirmed Covid-19']/items[at0013.1]/value|defining_code")
  private RiskFactorDefiningCode careHomeHasSuspectedConfirmedCovid19RiskFactorDefiningCode;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Care home has suspected/confirmed Covid-19/Presence
   */
  @Path("/data[at0001]/items[at0016 and name/value='Care home has suspected/confirmed Covid-19']/items[at0017.1]/value|defining_code")
  private PresenceDefiningCode2 careHomeHasSuspectedConfirmedCovid19PresenceDefiningCode;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Care home has suspected/confirmed Covid-19/Detail
   */
  @Path("/data[at0001]/items[at0016 and name/value='Care home has suspected/confirmed Covid-19']/items[at0027.1]")
  private List<Cluster> careHomeHasSuspectedConfirmedCovid19Detail;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Contact with confirmed case/Risk factor
   */
  @Path("/data[at0001]/items[at0016 and name/value='Contact with confirmed case']/items[at0013.1]/value|defining_code")
  private RiskFactorDefiningCode2 contactWithConfirmedCaseRiskFactorDefiningCode;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Contact with confirmed case/Presence
   */
  @Path("/data[at0001]/items[at0016 and name/value='Contact with confirmed case']/items[at0017.1]/value|defining_code")
  private PresenceDefiningCode2 contactWithConfirmedCasePresenceDefiningCode;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Contact with confirmed case/Detail
   */
  @Path("/data[at0001]/items[at0016 and name/value='Contact with confirmed case']/items[at0027.1]")
  private List<Cluster> contactWithConfirmedCaseDetail;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Other residents/household members unwell/Risk factor
   */
  @Path("/data[at0001]/items[at0016 and name/value='Other residents/household members unwell']/items[at0013.1]/value|defining_code")
  private RiskFactorDefiningCode3 otherResidentsHouseholdMembersUnwellRiskFactorDefiningCode;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Other residents/household members unwell/Presence
   */
  @Path("/data[at0001]/items[at0016 and name/value='Other residents/household members unwell']/items[at0017.1]/value|defining_code")
  private PresenceDefiningCode2 otherResidentsHouseholdMembersUnwellPresenceDefiningCode;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Other residents/household members unwell/Detail
   */
  @Path("/data[at0001]/items[at0016 and name/value='Other residents/household members unwell']/items[at0027.1]")
  private List<Cluster> otherResidentsHouseholdMembersUnwellDetail;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Risk assessment
   */
  @Path("/data[at0001]/items[at0003.1]/value|defining_code")
  private RiskAssessmentDefiningCode riskAssessmentDefiningCode;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Extension
   */
  @Path("/protocol[at0010]/items[at0011]")
  private List<Cluster> extension;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid-19 exposure/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid-19 exposure/language
   */
  @Path("/language")
  private Language language;

  public void setHealthRiskDefiningCode(HealthRiskDefiningCode healthRiskDefiningCode) {
     this.healthRiskDefiningCode = healthRiskDefiningCode;
  }

  public HealthRiskDefiningCode getHealthRiskDefiningCode() {
     return this.healthRiskDefiningCode ;
  }

  public void setCareHomeHasSuspectedConfirmedCovid19RiskFactorDefiningCode(
      RiskFactorDefiningCode careHomeHasSuspectedConfirmedCovid19RiskFactorDefiningCode) {
     this.careHomeHasSuspectedConfirmedCovid19RiskFactorDefiningCode = careHomeHasSuspectedConfirmedCovid19RiskFactorDefiningCode;
  }

  public RiskFactorDefiningCode getCareHomeHasSuspectedConfirmedCovid19RiskFactorDefiningCode() {
     return this.careHomeHasSuspectedConfirmedCovid19RiskFactorDefiningCode ;
  }

  public void setCareHomeHasSuspectedConfirmedCovid19PresenceDefiningCode(
      PresenceDefiningCode2 careHomeHasSuspectedConfirmedCovid19PresenceDefiningCode) {
     this.careHomeHasSuspectedConfirmedCovid19PresenceDefiningCode = careHomeHasSuspectedConfirmedCovid19PresenceDefiningCode;
  }

  public PresenceDefiningCode2 getCareHomeHasSuspectedConfirmedCovid19PresenceDefiningCode() {
     return this.careHomeHasSuspectedConfirmedCovid19PresenceDefiningCode ;
  }

  public void setCareHomeHasSuspectedConfirmedCovid19Detail(
      List<Cluster> careHomeHasSuspectedConfirmedCovid19Detail) {
     this.careHomeHasSuspectedConfirmedCovid19Detail = careHomeHasSuspectedConfirmedCovid19Detail;
  }

  public List<Cluster> getCareHomeHasSuspectedConfirmedCovid19Detail() {
     return this.careHomeHasSuspectedConfirmedCovid19Detail ;
  }

  public void setContactWithConfirmedCaseRiskFactorDefiningCode(
      RiskFactorDefiningCode2 contactWithConfirmedCaseRiskFactorDefiningCode) {
     this.contactWithConfirmedCaseRiskFactorDefiningCode = contactWithConfirmedCaseRiskFactorDefiningCode;
  }

  public RiskFactorDefiningCode2 getContactWithConfirmedCaseRiskFactorDefiningCode() {
     return this.contactWithConfirmedCaseRiskFactorDefiningCode ;
  }

  public void setContactWithConfirmedCasePresenceDefiningCode(
      PresenceDefiningCode2 contactWithConfirmedCasePresenceDefiningCode) {
     this.contactWithConfirmedCasePresenceDefiningCode = contactWithConfirmedCasePresenceDefiningCode;
  }

  public PresenceDefiningCode2 getContactWithConfirmedCasePresenceDefiningCode() {
     return this.contactWithConfirmedCasePresenceDefiningCode ;
  }

  public void setContactWithConfirmedCaseDetail(List<Cluster> contactWithConfirmedCaseDetail) {
     this.contactWithConfirmedCaseDetail = contactWithConfirmedCaseDetail;
  }

  public List<Cluster> getContactWithConfirmedCaseDetail() {
     return this.contactWithConfirmedCaseDetail ;
  }

  public void setOtherResidentsHouseholdMembersUnwellRiskFactorDefiningCode(
      RiskFactorDefiningCode3 otherResidentsHouseholdMembersUnwellRiskFactorDefiningCode) {
     this.otherResidentsHouseholdMembersUnwellRiskFactorDefiningCode = otherResidentsHouseholdMembersUnwellRiskFactorDefiningCode;
  }

  public RiskFactorDefiningCode3 getOtherResidentsHouseholdMembersUnwellRiskFactorDefiningCode() {
     return this.otherResidentsHouseholdMembersUnwellRiskFactorDefiningCode ;
  }

  public void setOtherResidentsHouseholdMembersUnwellPresenceDefiningCode(
      PresenceDefiningCode2 otherResidentsHouseholdMembersUnwellPresenceDefiningCode) {
     this.otherResidentsHouseholdMembersUnwellPresenceDefiningCode = otherResidentsHouseholdMembersUnwellPresenceDefiningCode;
  }

  public PresenceDefiningCode2 getOtherResidentsHouseholdMembersUnwellPresenceDefiningCode() {
     return this.otherResidentsHouseholdMembersUnwellPresenceDefiningCode ;
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
