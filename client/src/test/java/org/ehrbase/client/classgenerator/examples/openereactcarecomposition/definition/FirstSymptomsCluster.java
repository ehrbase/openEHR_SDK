package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
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
    date = "2021-02-16T12:59:53.631777300+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class FirstSymptomsCluster implements LocatableEntity {
  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/First symptoms/Symptom/Sign name
   * Description: The name of the reported symptom or sign.
   * Comment: Symptom name should be coded with a terminology, where possible.
   */
  @Path("/items[at0001.1]/value")
  private DvCodedText symptomSignName;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/First symptoms/Structured body site
   * Description: Structured body site where the symptom or sign was reported.
   * Comment: If the anatomical location is included in the Symptom name via precoordinated codes, use of this SLOT becomes redundant. If the anatomical location is recorded using the 'Body site' data element, then use of CLUSTER archetypes in this SLOT is not allowed - record only the simple 'Body site' OR 'Structured body site', but not both.
   */
  @Path("/items[at0147]")
  private List<Cluster> structuredBodySite;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/First symptoms/Date of onset of first symptoms
   * Description: The onset for this episode of the symptom or sign.
   * Comment: While partial dates are permitted, the exact date and time of onset can be recorded, if appropriate. If this symptom or sign is experienced for the first time or is a re-occurrence, this date is used to represent the onset of this episode. If this symptom or sign is ongoing, this data element may be redundant if it has been recorded previously.
   */
  @Path("/items[at0152 and name/value='Date of onset of first symptoms']/value|value")
  private TemporalAccessor dateOfOnsetOfFirstSymptomsValue;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/First symptoms/Duration
   * Description: The duration of this episode of the symptom or sign since onset.
   * Comment: If 'Date/time of onset' and 'Date/time of resolution' are used in systems, this data element may be calculated, or alternatively, be considered redundant in this scenario.
   */
  @Path("/items[at0028]/value|value")
  private TemporalAmount durationValue;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/First symptoms/Trend
   * Description: Description progression of the symptom or sign at the time of reporting.
   * Comment: Occurrences of this data element are set to 0..* to allow multiple types of progression to be separated out in a template if desired - for example, severity or frequency.
   */
  @Path("/items[at0180 and name/value='Trend']/value|defining_code")
  private TrendDefiningCode trendDefiningCode;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/First symptoms/Impact
   * Description: Description of the impact of this symptom or sign.
   * Comment: Assessment of impact could consider the severity, duration and frequency of the symptom as well as the type of impact including, but not limited to, functional, social and emotional impact. Occurrences of this data element are set to 0..* to allow multiple types of impact to be separated out in a template if desired. Examples for functional impact from hearing loss may include: 'Difficulty Hearing in Quiet Environment'; 'Difficulty Hearing the TV or Radio'; 'Difficulty Hearing Group Conversation'; and 'Difficulty Hearing on Phone'.
   */
  @Path("/items[at0155]/value|value")
  private String impactValue;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/First symptoms/Specific details
   * Description: Specific data elements that are additionally required to record as unique attributes of the identified symptom or sign.
   * Comment: For example: CTCAE grading.
   */
  @Path("/items[at0153]")
  private List<Cluster> specificDetails;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/First symptoms/Previous episodes
   * Description: Structured details of the symptom or sign during a previous episode.
   * Comment: In linked clinical systems, it is possible that previous episodes are already recorded within the EHR. Systems can allow the clinician to LINK to relevant previous episodes. However in a system or message without LINKs to existing data or with a new patient, additional instances of the symptom archetype could be included here to represent previous episodes. It is recommended that new instances of the Symptom archetype inserted in this SLOT represent one or many previous episodes to this Symptom instance only.
   */
  @Path("/items[at0146]")
  private List<Cluster> previousEpisodes;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/First symptoms/Associated symptom/sign
   * Description: Structured details about any associated symptoms or signs that are concurrent.
   * Comment: In linked clinical systems, it is possible that associated symptoms or signs are already recorded within the EHR. Systems can allow the clinician to LINK to relevant associated symptoms/signs. However in a system or message without LINKs to existing data or with a new patient, additional instances of the symptom archetype could be included here to represent associated symptoms/signs.
   */
  @Path("/items[at0063]")
  private List<Cluster> associatedSymptomSign;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/First symptoms/Presence
   * Description: Is the symptom present or not?
   */
  @Path("/items[at0.1]/value|defining_code")
  private PresenceDefiningCode presenceDefiningCode;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/First symptoms/feeder_audit
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

  public void setDateOfOnsetOfFirstSymptomsValue(TemporalAccessor dateOfOnsetOfFirstSymptomsValue) {
     this.dateOfOnsetOfFirstSymptomsValue = dateOfOnsetOfFirstSymptomsValue;
  }

  public TemporalAccessor getDateOfOnsetOfFirstSymptomsValue() {
     return this.dateOfOnsetOfFirstSymptomsValue ;
  }

  public void setDurationValue(TemporalAmount durationValue) {
     this.durationValue = durationValue;
  }

  public TemporalAmount getDurationValue() {
     return this.durationValue ;
  }

  public void setTrendDefiningCode(TrendDefiningCode trendDefiningCode) {
     this.trendDefiningCode = trendDefiningCode;
  }

  public TrendDefiningCode getTrendDefiningCode() {
     return this.trendDefiningCode ;
  }

  public void setImpactValue(String impactValue) {
     this.impactValue = impactValue;
  }

  public String getImpactValue() {
     return this.impactValue ;
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
