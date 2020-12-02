package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-INSTRUCTION.service_request.v1")
public class ServiceRequestInstruction {
  @Path("/activities[at0001]")
  private List<ServiceRequestCurrentActivityActivity> currentActivity;

  @Path("/protocol[at0008]/items[at0141]")
  private Cluster requester;

  @Path("/protocol[at0008]/items[at0142]")
  private Cluster receiver;

  @Path("/protocol[at0008]/items[at0128]")
  private List<Cluster> distributionList;

  @Path("/protocol[at0008]/items[at0112]")
  private List<Cluster> extension;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/narrative|value")
  private String narrativeValue;

  @Path("/language")
  private Language language;

  @Path("/expiry_time|value")
  private TemporalAccessor expiryTimeValue;

  public void setCurrentActivity(List<ServiceRequestCurrentActivityActivity> currentActivity) {
     this.currentActivity = currentActivity;
  }

  public List<ServiceRequestCurrentActivityActivity> getCurrentActivity() {
     return this.currentActivity ;
  }

  public void setRequester(Cluster requester) {
     this.requester = requester;
  }

  public Cluster getRequester() {
     return this.requester ;
  }

  public void setReceiver(Cluster receiver) {
     this.receiver = receiver;
  }

  public Cluster getReceiver() {
     return this.receiver ;
  }

  public void setDistributionList(List<Cluster> distributionList) {
     this.distributionList = distributionList;
  }

  public List<Cluster> getDistributionList() {
     return this.distributionList ;
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

  public void setNarrativeValue(String narrativeValue) {
     this.narrativeValue = narrativeValue;
  }

  public String getNarrativeValue() {
     return this.narrativeValue ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setExpiryTimeValue(TemporalAccessor expiryTimeValue) {
     this.expiryTimeValue = expiryTimeValue;
  }

  public TemporalAccessor getExpiryTimeValue() {
     return this.expiryTimeValue ;
  }
}
