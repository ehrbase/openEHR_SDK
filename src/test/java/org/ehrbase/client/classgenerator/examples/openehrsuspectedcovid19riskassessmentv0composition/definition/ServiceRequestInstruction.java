package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-INSTRUCTION.service_request.v1")
public class ServiceRequestInstruction {
  @Path("/narrative|value")
  private String narrativeValue;

  @Path("/language")
  private Language language;

  @Path("/protocol[at0008]/items[at0142]")
  private Cluster receiver;

  @Path("/protocol[at0008]/items[at0112]")
  private List<Cluster> extension;

  @Path("/protocol[at0008]/items[at0141]")
  private Cluster requester;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/activities[at0001]")
  private List<ServiceRequestCurrentActivityActivity> currentActivity;

  @Path("/protocol[at0008]/items[at0128]")
  private List<Cluster> distributionList;

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

  public void setReceiver(Cluster receiver) {
     this.receiver = receiver;
  }

  public Cluster getReceiver() {
     return this.receiver ;
  }

  public void setExtension(List<Cluster> extension) {
     this.extension = extension;
  }

  public List<Cluster> getExtension() {
     return this.extension ;
  }

  public void setRequester(Cluster requester) {
     this.requester = requester;
  }

  public Cluster getRequester() {
     return this.requester ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setCurrentActivity(List<ServiceRequestCurrentActivityActivity> currentActivity) {
     this.currentActivity = currentActivity;
  }

  public List<ServiceRequestCurrentActivityActivity> getCurrentActivity() {
     return this.currentActivity ;
  }

  public void setDistributionList(List<Cluster> distributionList) {
     this.distributionList = distributionList;
  }

  public List<Cluster> getDistributionList() {
     return this.distributionList ;
  }
}
