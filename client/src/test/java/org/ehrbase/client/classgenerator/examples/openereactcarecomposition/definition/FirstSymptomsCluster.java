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
    date = "2020-12-09T11:37:51.606761+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class FirstSymptomsCluster implements LocatableEntity {
  /**
   * open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/First symptoms/Symptom/Sign name
   */
  @Path("/items[at0001.1]/value")
  private DvCodedText symptomSignName;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/First symptoms/Structured body site
   */
  @Path("/items[at0147]")
  private List<Cluster> structuredBodySite;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/First symptoms/Date of onset of first symptoms
   */
  @Path("/items[at0152 and name/value='Date of onset of first symptoms']/value|value")
  private TemporalAccessor dateOfOnsetOfFirstSymptomsValue;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/First symptoms/Duration
   */
  @Path("/items[at0028]/value|value")
  private TemporalAmount durationValue;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/First symptoms/Trend
   */
  @Path("/items[at0180 and name/value='Trend']/value|defining_code")
  private TrendDefiningCode trendDefiningCode;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/First symptoms/Impact
   */
  @Path("/items[at0155]/value|value")
  private String impactValue;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/First symptoms/Specific details
   */
  @Path("/items[at0153]")
  private List<Cluster> specificDetails;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/First symptoms/Previous episodes
   */
  @Path("/items[at0146]")
  private List<Cluster> previousEpisodes;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/First symptoms/Associated symptom/sign
   */
  @Path("/items[at0063]")
  private List<Cluster> associatedSymptomSign;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/First symptoms/Presence
   */
  @Path("/items[at0.1]/value|defining_code")
  private PresenceDefiningCode presenceDefiningCode;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid symptoms/Any event/First symptoms/feeder_audit
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
