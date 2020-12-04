package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Long;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.news2.v1")
public class News2ScoreObservation {
  /**
   * open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Any point in time event/Respiration rate
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006]/value")
  private DvOrdinal respirationRate;

  /**
   * open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Any point in time event/SpO₂ Scale 1
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0029]/value")
  private DvOrdinal spoScale1;

  /**
   * open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Any point in time event/SpO₂ Scale 2
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0047]/value")
  private DvOrdinal spoScale2;

  /**
   * open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Any point in time event/Air or oxygen?
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0034]/value")
  private DvOrdinal airOrOxygen;

  /**
   * open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Any point in time event/Systolic blood pressure
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value")
  private DvOrdinal systolicBloodPressure;

  /**
   * open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Any point in time event/Pulse
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value")
  private DvOrdinal pulse;

  /**
   * open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Any point in time event/Consciousness
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0008]/value")
  private DvOrdinal consciousness;

  /**
   * open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Any point in time event/Temperature
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0007]/value")
  private DvOrdinal temperature;

  /**
   * open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Any point in time event/Total score
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0028]/value|magnitude")
  private Long totalScoreMagnitude;

  /**
   * open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Any point in time event/Clinical risk category
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0056]/value|defining_code")
  private ClinicalRiskCategoryDefiningCode clinicalRiskCategoryDefiningCode;

  /**
   * open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Any point in time event/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /**
   * open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Extension
   */
  @Path("/protocol[at0045]/items[at0046]")
  private List<Cluster> extension;

  /**
   * open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/language
   */
  @Path("/language")
  private Language language;

  public void setRespirationRate(DvOrdinal respirationRate) {
     this.respirationRate = respirationRate;
  }

  public DvOrdinal getRespirationRate() {
     return this.respirationRate ;
  }

  public void setSpoScale1(DvOrdinal spoScale1) {
     this.spoScale1 = spoScale1;
  }

  public DvOrdinal getSpoScale1() {
     return this.spoScale1 ;
  }

  public void setSpoScale2(DvOrdinal spoScale2) {
     this.spoScale2 = spoScale2;
  }

  public DvOrdinal getSpoScale2() {
     return this.spoScale2 ;
  }

  public void setAirOrOxygen(DvOrdinal airOrOxygen) {
     this.airOrOxygen = airOrOxygen;
  }

  public DvOrdinal getAirOrOxygen() {
     return this.airOrOxygen ;
  }

  public void setSystolicBloodPressure(DvOrdinal systolicBloodPressure) {
     this.systolicBloodPressure = systolicBloodPressure;
  }

  public DvOrdinal getSystolicBloodPressure() {
     return this.systolicBloodPressure ;
  }

  public void setPulse(DvOrdinal pulse) {
     this.pulse = pulse;
  }

  public DvOrdinal getPulse() {
     return this.pulse ;
  }

  public void setConsciousness(DvOrdinal consciousness) {
     this.consciousness = consciousness;
  }

  public DvOrdinal getConsciousness() {
     return this.consciousness ;
  }

  public void setTemperature(DvOrdinal temperature) {
     this.temperature = temperature;
  }

  public DvOrdinal getTemperature() {
     return this.temperature ;
  }

  public void setTotalScoreMagnitude(Long totalScoreMagnitude) {
     this.totalScoreMagnitude = totalScoreMagnitude;
  }

  public Long getTotalScoreMagnitude() {
     return this.totalScoreMagnitude ;
  }

  public void setClinicalRiskCategoryDefiningCode(
      ClinicalRiskCategoryDefiningCode clinicalRiskCategoryDefiningCode) {
     this.clinicalRiskCategoryDefiningCode = clinicalRiskCategoryDefiningCode;
  }

  public ClinicalRiskCategoryDefiningCode getClinicalRiskCategoryDefiningCode() {
     return this.clinicalRiskCategoryDefiningCode ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
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
