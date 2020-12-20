package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Transition;

@Entity
@Archetype("openEHR-EHR-ACTION.service.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.597503+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class ServiceAction implements EntryEntity {
  /**
   * Path: open_eREACT-Care/Response/Service/Service name Description: Identification of the
   * clinical service to be/being carried out.
   */
  @Path("/description[at0001]/items[at0011]/value")
  private DvCodedText serviceName;

  /**
   * Path: open_eREACT-Care/Response/Service/Description Description: Narrative description about
   * the service, as appropriate for the pathway step.
   */
  @Path("/description[at0001]/items[at0013]/value|value")
  private String descriptionValue;

  /**
   * Path: open_eREACT-Care/Response/Service/Service detail Description: Structured information
   * about the service. Comment: Use to capture detailed, structured information about specified
   * aspects of the service.
   */
  @Path("/description[at0001]/items[at0027]")
  private List<Cluster> serviceDetail;

  /**
   * Path: open_eREACT-Care/Response/Service/Multimedia Description: Mulitimedia representation of a
   * performed service.
   */
  @Path("/description[at0001]/items[at0029]")
  private List<Cluster> multimedia;

  /**
   * Path: open_eREACT-Care/Response/Service/Requestor Description: Details about the healthcare
   * provider or organisation requesting the service.
   */
  @Path("/protocol[at0015]/items[at0017]")
  private List<Cluster> requestor;

  /**
   * Path: open_eREACT-Care/Response/Service/Receiver Description: Details about the healthcare
   * provider or organisation receiving the request for referral.
   */
  @Path("/protocol[at0015]/items[at0019]")
  private List<Cluster> receiver;

  /** Path: open_eREACT-Care/Response/Service/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: open_eREACT-Care/Response/Service/language */
  @Path("/language")
  private Language language;

  /** Path: open_eREACT-Care/Response/Service/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /** Path: open_eREACT-Care/Response/Service/time */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  /** Path: open_eREACT-Care/Response/Service/ism_transition/Careflow_step */
  @Path("/ism_transition/careflow_step|defining_code")
  private CareflowStepDefiningCode careflowStepDefiningCode;

  /** Path: open_eREACT-Care/Response/Service/ism_transition/Current_state */
  @Path("/ism_transition/current_state|defining_code")
  private CurrentStateDefiningCode currentStateDefiningCode;

  /** Path: open_eREACT-Care/Response/Service/ism_transition/transition */
  @Path("/ism_transition/transition|defining_code")
  private Transition transitionDefiningCode;

  public void setServiceName(DvCodedText serviceName) {
    this.serviceName = serviceName;
  }

  public DvCodedText getServiceName() {
    return this.serviceName;
  }

  public void setDescriptionValue(String descriptionValue) {
    this.descriptionValue = descriptionValue;
  }

  public String getDescriptionValue() {
    return this.descriptionValue;
  }

  public void setServiceDetail(List<Cluster> serviceDetail) {
    this.serviceDetail = serviceDetail;
  }

  public List<Cluster> getServiceDetail() {
    return this.serviceDetail;
  }

  public void setMultimedia(List<Cluster> multimedia) {
    this.multimedia = multimedia;
  }

  public List<Cluster> getMultimedia() {
    return this.multimedia;
  }

  public void setRequestor(List<Cluster> requestor) {
    this.requestor = requestor;
  }

  public List<Cluster> getRequestor() {
    return this.requestor;
  }

  public void setReceiver(List<Cluster> receiver) {
    this.receiver = receiver;
  }

  public List<Cluster> getReceiver() {
    return this.receiver;
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

  public void setTimeValue(TemporalAccessor timeValue) {
    this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
    return this.timeValue;
  }

  public void setCareflowStepDefiningCode(CareflowStepDefiningCode careflowStepDefiningCode) {
    this.careflowStepDefiningCode = careflowStepDefiningCode;
  }

  public CareflowStepDefiningCode getCareflowStepDefiningCode() {
    return this.careflowStepDefiningCode;
  }

  public void setCurrentStateDefiningCode(CurrentStateDefiningCode currentStateDefiningCode) {
    this.currentStateDefiningCode = currentStateDefiningCode;
  }

  public CurrentStateDefiningCode getCurrentStateDefiningCode() {
    return this.currentStateDefiningCode;
  }

  public void setTransitionDefiningCode(Transition transitionDefiningCode) {
    this.transitionDefiningCode = transitionDefiningCode;
  }

  public Transition getTransitionDefiningCode() {
    return this.transitionDefiningCode;
  }
}
