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
@Archetype("openEHR-EHR-OBSERVATION.respiration.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.556501500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class RespirationsObservation implements EntryEntity {
  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Respirations/Any event/Rate Description: Rate of
   * respiration.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|magnitude")
  private Double rateMagnitude;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Respirations/Any event/Rate Description: Rate of
   * respiration.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|units")
  private String rateUnits;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Respirations/Any event/Inspired oxygen Description:
   * Details of the amount of oxygen being delivered to the subject at the time of observation.
   * Assumed values of 21% oxygen concentration, Fi02 of 0.21 and oxygen flow rate of 0 l/min or 0
   * ml/min.
   */
  @Path("/data[at0001]/events[at0002]/state[at0022]/items[at0055]")
  private Cluster inspiredOxygen;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Respirations/Any event/Exertion Description: Subject's
   * level of exertion at or just prior to the observation being made. Intended only to record
   * exertion only as it might effect respirations and where it would not normally be recorded as
   * part of general clinical observation.
   */
  @Path("/data[at0001]/events[at0002]/state[at0022]/items[at0037]")
  private Cluster exertion;

  /** Path: open_eREACT-Care/Assessment/NEWS2/Respirations/Any event/time */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /** Path: open_eREACT-Care/Assessment/NEWS2/Respirations/origin */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Respirations/Extension Description: Additional
   * information required to capture local context or to align with other reference
   * models/formalisms. Comment: e.g. Local hospital departmental infomation or additional metadata
   * to align with HL7 or CDISC equivalents.
   */
  @Path("/protocol[at0057]/items[at0058]")
  private List<Cluster> extension;

  /** Path: open_eREACT-Care/Assessment/NEWS2/Respirations/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: open_eREACT-Care/Assessment/NEWS2/Respirations/language */
  @Path("/language")
  private Language language;

  /** Path: open_eREACT-Care/Assessment/NEWS2/Respirations/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setRateMagnitude(Double rateMagnitude) {
    this.rateMagnitude = rateMagnitude;
  }

  public Double getRateMagnitude() {
    return this.rateMagnitude;
  }

  public void setRateUnits(String rateUnits) {
    this.rateUnits = rateUnits;
  }

  public String getRateUnits() {
    return this.rateUnits;
  }

  public void setInspiredOxygen(Cluster inspiredOxygen) {
    this.inspiredOxygen = inspiredOxygen;
  }

  public Cluster getInspiredOxygen() {
    return this.inspiredOxygen;
  }

  public void setExertion(Cluster exertion) {
    this.exertion = exertion;
  }

  public Cluster getExertion() {
    return this.exertion;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
    this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
    return this.timeValue;
  }

  public void setOriginValue(TemporalAccessor originValue) {
    this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
    return this.originValue;
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
