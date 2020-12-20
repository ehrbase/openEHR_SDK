package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-INSTRUCTION.service_request.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.588500300+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class ServiceRequestInstruction implements EntryEntity {
  /**
   * Path: open_eREACT-Care/Response/Service request/Current Activity Description: Current Activity.
   */
  @Path("/activities[at0001]")
  private List<ServiceRequestCurrentActivityActivity> currentActivity;

  /**
   * Path: open_eREACT-Care/Response/Service request/Requester Description: Details about the
   * clinician or organisation requesting the service.
   */
  @Path("/protocol[at0008]/items[at0141]")
  private Cluster requester;

  /**
   * Path: open_eREACT-Care/Response/Service request/Receiver Description: Details about the
   * clinician or organisation receiving the request for service.
   */
  @Path("/protocol[at0008]/items[at0142]")
  private Cluster receiver;

  /**
   * Path: open_eREACT-Care/Response/Service request/Distribution list Description: Details of
   * additional clinicians, organisations or agencies who require copies of any communication.
   */
  @Path("/protocol[at0008]/items[at0128]")
  private List<Cluster> distributionList;

  /**
   * Path: open_eREACT-Care/Response/Service request/Extension Description: Additional information
   * required to capture local content or to align with other reference models/formalisms. Comment:
   * For example: local information requirements or additional metadata to align with FHIR or CIMI
   * equivalents.
   */
  @Path("/protocol[at0008]/items[at0112]")
  private List<Cluster> extension;

  /** Path: open_eREACT-Care/Response/Service request/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: open_eREACT-Care/Response/Service request/narrative */
  @Path("/narrative|value")
  private String narrativeValue;

  /** Path: open_eREACT-Care/Response/Service request/language */
  @Path("/language")
  private Language language;

  /** Path: open_eREACT-Care/Response/Service request/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /** Path: open_eREACT-Care/Response/Service request/expiry_time */
  @Path("/expiry_time|value")
  private TemporalAccessor expiryTimeValue;

  public void setCurrentActivity(List<ServiceRequestCurrentActivityActivity> currentActivity) {
    this.currentActivity = currentActivity;
  }

  public List<ServiceRequestCurrentActivityActivity> getCurrentActivity() {
    return this.currentActivity;
  }

  public void setRequester(Cluster requester) {
    this.requester = requester;
  }

  public Cluster getRequester() {
    return this.requester;
  }

  public void setReceiver(Cluster receiver) {
    this.receiver = receiver;
  }

  public Cluster getReceiver() {
    return this.receiver;
  }

  public void setDistributionList(List<Cluster> distributionList) {
    this.distributionList = distributionList;
  }

  public List<Cluster> getDistributionList() {
    return this.distributionList;
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

  public void setNarrativeValue(String narrativeValue) {
    this.narrativeValue = narrativeValue;
  }

  public String getNarrativeValue() {
    return this.narrativeValue;
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

  public void setExpiryTimeValue(TemporalAccessor expiryTimeValue) {
    this.expiryTimeValue = expiryTimeValue;
  }

  public TemporalAccessor getExpiryTimeValue() {
    return this.expiryTimeValue;
  }
}
