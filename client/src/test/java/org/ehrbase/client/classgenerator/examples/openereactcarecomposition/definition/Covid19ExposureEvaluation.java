package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.health_risk-covid.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.499501400+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class Covid19ExposureEvaluation implements EntryEntity {
  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Health risk Description:
   * Identification of the potential future disease, condition or health issue for which the risk is
   * being assessed, by name.
   */
  @Path("/data[at0001]/items[at0002.1]/value|defining_code")
  private HealthRiskDefiningCode healthRiskDefiningCode;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Care home has suspected/confirmed
   * Covid-19/Risk factor Description: Identification of the risk factor, by name.
   */
  @Path(
      "/data[at0001]/items[at0016 and name/value='Care home has suspected/confirmed Covid-19']/items[at0013.1]/value|defining_code")
  private RiskFactorDefiningCode careHomeHasSuspectedConfirmedCovid19RiskFactorDefiningCode;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Care home has suspected/confirmed
   * Covid-19/Presence Description: Presence of the risk factor.
   */
  @Path(
      "/data[at0001]/items[at0016 and name/value='Care home has suspected/confirmed Covid-19']/items[at0017.1]/value|defining_code")
  private PresenceDefiningCode2 careHomeHasSuspectedConfirmedCovid19PresenceDefiningCode;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Care home has suspected/confirmed
   * Covid-19/Detail Description: Structured detail about other aspects of the risk factor
   * assessment. Comment: For example: Prevalence of the risk factor in family members.
   */
  @Path(
      "/data[at0001]/items[at0016 and name/value='Care home has suspected/confirmed Covid-19']/items[at0027.1]")
  private List<Cluster> careHomeHasSuspectedConfirmedCovid19Detail;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Contact with confirmed case/Risk
   * factor Description: Identification of the risk factor, by name.
   */
  @Path(
      "/data[at0001]/items[at0016 and name/value='Contact with confirmed case']/items[at0013.1]/value|defining_code")
  private RiskFactorDefiningCode2 contactWithConfirmedCaseRiskFactorDefiningCode;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Contact with confirmed case/Presence
   * Description: Presence of the risk factor.
   */
  @Path(
      "/data[at0001]/items[at0016 and name/value='Contact with confirmed case']/items[at0017.1]/value|defining_code")
  private PresenceDefiningCode2 contactWithConfirmedCasePresenceDefiningCode;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Contact with confirmed case/Detail
   * Description: Structured detail about other aspects of the risk factor assessment. Comment: For
   * example: Prevalence of the risk factor in family members.
   */
  @Path("/data[at0001]/items[at0016 and name/value='Contact with confirmed case']/items[at0027.1]")
  private List<Cluster> contactWithConfirmedCaseDetail;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Other residents/household members
   * unwell/Risk factor Description: Identification of the risk factor, by name.
   */
  @Path(
      "/data[at0001]/items[at0016 and name/value='Other residents/household members unwell']/items[at0013.1]/value|defining_code")
  private RiskFactorDefiningCode3 otherResidentsHouseholdMembersUnwellRiskFactorDefiningCode;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Other residents/household members
   * unwell/Presence Description: Presence of the risk factor.
   */
  @Path(
      "/data[at0001]/items[at0016 and name/value='Other residents/household members unwell']/items[at0017.1]/value|defining_code")
  private PresenceDefiningCode2 otherResidentsHouseholdMembersUnwellPresenceDefiningCode;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Other residents/household members
   * unwell/Detail Description: Structured detail about other aspects of the risk factor assessment.
   * Comment: For example: Prevalence of the risk factor in family members.
   */
  @Path(
      "/data[at0001]/items[at0016 and name/value='Other residents/household members unwell']/items[at0027.1]")
  private List<Cluster> otherResidentsHouseholdMembersUnwellDetail;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Risk assessment Description:
   * Evaluation of the health risk.
   */
  @Path("/data[at0001]/items[at0003.1]/value|defining_code")
  private RiskAssessmentDefiningCode riskAssessmentDefiningCode;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid-19 exposure/Extension Description: Additional
   * information required to capture local content or to align with other reference
   * models/formalisms. Comment: For example: local information requirements or additional metadata
   * to align with FHIR or CIMI equivalents.
   */
  @Path("/protocol[at0010]/items[at0011]")
  private List<Cluster> extension;

  /** Path: open_eREACT-Care/Assessment/Covid/Covid-19 exposure/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: open_eREACT-Care/Assessment/Covid/Covid-19 exposure/language */
  @Path("/language")
  private Language language;

  /** Path: open_eREACT-Care/Assessment/Covid/Covid-19 exposure/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setHealthRiskDefiningCode(HealthRiskDefiningCode healthRiskDefiningCode) {
    this.healthRiskDefiningCode = healthRiskDefiningCode;
  }

  public HealthRiskDefiningCode getHealthRiskDefiningCode() {
    return this.healthRiskDefiningCode;
  }

  public void setCareHomeHasSuspectedConfirmedCovid19RiskFactorDefiningCode(
      RiskFactorDefiningCode careHomeHasSuspectedConfirmedCovid19RiskFactorDefiningCode) {
    this.careHomeHasSuspectedConfirmedCovid19RiskFactorDefiningCode =
        careHomeHasSuspectedConfirmedCovid19RiskFactorDefiningCode;
  }

  public RiskFactorDefiningCode getCareHomeHasSuspectedConfirmedCovid19RiskFactorDefiningCode() {
    return this.careHomeHasSuspectedConfirmedCovid19RiskFactorDefiningCode;
  }

  public void setCareHomeHasSuspectedConfirmedCovid19PresenceDefiningCode(
      PresenceDefiningCode2 careHomeHasSuspectedConfirmedCovid19PresenceDefiningCode) {
    this.careHomeHasSuspectedConfirmedCovid19PresenceDefiningCode =
        careHomeHasSuspectedConfirmedCovid19PresenceDefiningCode;
  }

  public PresenceDefiningCode2 getCareHomeHasSuspectedConfirmedCovid19PresenceDefiningCode() {
    return this.careHomeHasSuspectedConfirmedCovid19PresenceDefiningCode;
  }

  public void setCareHomeHasSuspectedConfirmedCovid19Detail(
      List<Cluster> careHomeHasSuspectedConfirmedCovid19Detail) {
    this.careHomeHasSuspectedConfirmedCovid19Detail = careHomeHasSuspectedConfirmedCovid19Detail;
  }

  public List<Cluster> getCareHomeHasSuspectedConfirmedCovid19Detail() {
    return this.careHomeHasSuspectedConfirmedCovid19Detail;
  }

  public void setContactWithConfirmedCaseRiskFactorDefiningCode(
      RiskFactorDefiningCode2 contactWithConfirmedCaseRiskFactorDefiningCode) {
    this.contactWithConfirmedCaseRiskFactorDefiningCode =
        contactWithConfirmedCaseRiskFactorDefiningCode;
  }

  public RiskFactorDefiningCode2 getContactWithConfirmedCaseRiskFactorDefiningCode() {
    return this.contactWithConfirmedCaseRiskFactorDefiningCode;
  }

  public void setContactWithConfirmedCasePresenceDefiningCode(
      PresenceDefiningCode2 contactWithConfirmedCasePresenceDefiningCode) {
    this.contactWithConfirmedCasePresenceDefiningCode =
        contactWithConfirmedCasePresenceDefiningCode;
  }

  public PresenceDefiningCode2 getContactWithConfirmedCasePresenceDefiningCode() {
    return this.contactWithConfirmedCasePresenceDefiningCode;
  }

  public void setContactWithConfirmedCaseDetail(List<Cluster> contactWithConfirmedCaseDetail) {
    this.contactWithConfirmedCaseDetail = contactWithConfirmedCaseDetail;
  }

  public List<Cluster> getContactWithConfirmedCaseDetail() {
    return this.contactWithConfirmedCaseDetail;
  }

  public void setOtherResidentsHouseholdMembersUnwellRiskFactorDefiningCode(
      RiskFactorDefiningCode3 otherResidentsHouseholdMembersUnwellRiskFactorDefiningCode) {
    this.otherResidentsHouseholdMembersUnwellRiskFactorDefiningCode =
        otherResidentsHouseholdMembersUnwellRiskFactorDefiningCode;
  }

  public RiskFactorDefiningCode3 getOtherResidentsHouseholdMembersUnwellRiskFactorDefiningCode() {
    return this.otherResidentsHouseholdMembersUnwellRiskFactorDefiningCode;
  }

  public void setOtherResidentsHouseholdMembersUnwellPresenceDefiningCode(
      PresenceDefiningCode2 otherResidentsHouseholdMembersUnwellPresenceDefiningCode) {
    this.otherResidentsHouseholdMembersUnwellPresenceDefiningCode =
        otherResidentsHouseholdMembersUnwellPresenceDefiningCode;
  }

  public PresenceDefiningCode2 getOtherResidentsHouseholdMembersUnwellPresenceDefiningCode() {
    return this.otherResidentsHouseholdMembersUnwellPresenceDefiningCode;
  }

  public void setOtherResidentsHouseholdMembersUnwellDetail(
      List<Cluster> otherResidentsHouseholdMembersUnwellDetail) {
    this.otherResidentsHouseholdMembersUnwellDetail = otherResidentsHouseholdMembersUnwellDetail;
  }

  public List<Cluster> getOtherResidentsHouseholdMembersUnwellDetail() {
    return this.otherResidentsHouseholdMembersUnwellDetail;
  }

  public void setRiskAssessmentDefiningCode(RiskAssessmentDefiningCode riskAssessmentDefiningCode) {
    this.riskAssessmentDefiningCode = riskAssessmentDefiningCode;
  }

  public RiskAssessmentDefiningCode getRiskAssessmentDefiningCode() {
    return this.riskAssessmentDefiningCode;
  }

  public void setExtension(List<Cluster> extension) {
    this.extension = extension;
  }

  public List<Cluster> getExtension() {
    return this.extension;
  }

  public void setSubject(PartyProxy subject) {
    this.subject = subject;
  }

  public PartyProxy getSubject() {
    return this.subject;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public Language getLanguage() {
    return this.language;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
