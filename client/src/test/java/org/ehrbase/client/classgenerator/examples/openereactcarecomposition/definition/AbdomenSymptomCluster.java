package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.symptom_sign-cvid.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.478503200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class AbdomenSymptomCluster implements LocatableEntity {
  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/Abdomen symptom/Symptom/Sign
   * name Description: The name of the reported symptom or sign.
   */
  @Path("/items[at0001.1]/value")
  private DvCodedText symptomSignName;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/Abdomen symptom/Structured
   * body site Description: Structured body site where the symptom or sign was reported. Comment: If
   * the anatomical location is included in the Symptom name via precoordinated codes, use of this
   * SLOT becomes redundant. If the anatomical location is recorded using the 'Body site' data
   * element, then use of CLUSTER archetypes in this SLOT is not allowed - record only the simple
   * 'Body site' OR 'Structured body site', but not both.
   */
  @Path("/items[at0147]")
  private List<Cluster> structuredBodySite;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/Abdomen symptom/Specific
   * details Description: Specific data elements that are additionally required to record as unique
   * attributes of the identified symptom or sign. Comment: For example: CTCAE grading.
   */
  @Path("/items[at0153]")
  private List<Cluster> specificDetails;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/Abdomen symptom/Previous
   * episodes Description: Structured details of the symptom or sign during a previous episode.
   * Comment: In linked clinical systems, it is possible that previous episodes are already recorded
   * within the EHR. Systems can allow the clinician to LINK to relevant previous episodes. However
   * in a system or message without LINKs to existing data or with a new patient, additional
   * instances of the symptom archetype could be included here to represent previous episodes. It is
   * recommended that new instances of the Symptom archetype inserted in this SLOT represent one or
   * many previous episodes to this Symptom instance only.
   */
  @Path("/items[at0146]")
  private List<Cluster> previousEpisodes;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/Abdomen symptom/Associated
   * symptom/sign Description: Structured details about any associated symptoms or signs that are
   * concurrent. Comment: In linked clinical systems, it is possible that associated symptoms or
   * signs are already recorded within the EHR. Systems can allow the clinician to LINK to relevant
   * associated symptoms/signs. However in a system or message without LINKs to existing data or
   * with a new patient, additional instances of the symptom archetype could be included here to
   * represent associated symptoms/signs.
   */
  @Path("/items[at0063]")
  private List<Cluster> associatedSymptomSign;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/Abdomen symptom/Presence
   * Description: Is the symptom present or not?
   */
  @Path("/items[at0.1 and name/value='Presence']/value|defining_code")
  private PresenceDefiningCode presenceDefiningCode;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/Abdomen symptom/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setSymptomSignName(DvCodedText symptomSignName) {
    this.symptomSignName = symptomSignName;
  }

  public DvCodedText getSymptomSignName() {
    return this.symptomSignName;
  }

  public void setStructuredBodySite(List<Cluster> structuredBodySite) {
    this.structuredBodySite = structuredBodySite;
  }

  public List<Cluster> getStructuredBodySite() {
    return this.structuredBodySite;
  }

  public void setSpecificDetails(List<Cluster> specificDetails) {
    this.specificDetails = specificDetails;
  }

  public List<Cluster> getSpecificDetails() {
    return this.specificDetails;
  }

  public void setPreviousEpisodes(List<Cluster> previousEpisodes) {
    this.previousEpisodes = previousEpisodes;
  }

  public List<Cluster> getPreviousEpisodes() {
    return this.previousEpisodes;
  }

  public void setAssociatedSymptomSign(List<Cluster> associatedSymptomSign) {
    this.associatedSymptomSign = associatedSymptomSign;
  }

  public List<Cluster> getAssociatedSymptomSign() {
    return this.associatedSymptomSign;
  }

  public void setPresenceDefiningCode(PresenceDefiningCode presenceDefiningCode) {
    this.presenceDefiningCode = presenceDefiningCode;
  }

  public PresenceDefiningCode getPresenceDefiningCode() {
    return this.presenceDefiningCode;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
