package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.symptom_sign-cvid.v0")
public class KeyCovidSymptomCluster {
  /**
   * open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/Key Covid symptom/Symptom/Sign name
   */
  @Path("/items[at0001.1]/value")
  private DvCodedText symptomSignName;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/Key Covid symptom/Structured body site
   */
  @Path("/items[at0147]")
  private List<Cluster> structuredBodySite;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/Key Covid symptom/Specific details
   */
  @Path("/items[at0153]")
  private List<Cluster> specificDetails;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/Key Covid symptom/Previous episodes
   */
  @Path("/items[at0146]")
  private List<Cluster> previousEpisodes;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/Key Covid symptom/Associated symptom/sign
   */
  @Path("/items[at0063]")
  private List<Cluster> associatedSymptomSign;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/Key Covid symptom/Presence
   */
  @Path("/items[at0.1 and name/value='Presence']/value|defining_code")
  private PresenceDefiningCode presenceDefiningCode;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/Key Covid symptom/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setSymptomSignName(DvCodedText symptomSignName) {
     this.symptomSignName = symptomSignName;
  }

  public DvCodedText getSymptomSignName() {
     return this.symptomSignName ;
  }

  public void setStructuredBodySite(List<Cluster> structuredBodySite) {
     this.structuredBodySite = structuredBodySite;
  }

  public List<Cluster> getStructuredBodySite() {
     return this.structuredBodySite ;
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

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
