package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.respiration.v1")
public class RespirationsObservation implements EntryEntity {
  /**
   * open_eREACT-Care/Assessment/NEWS2/Respirations/Any event/Rate
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|magnitude")
  private Double rateMagnitude;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Respirations/Any event/Rate
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|units")
  private String rateUnits;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Respirations/Any event/Inspired oxygen
   */
  @Path("/data[at0001]/events[at0002]/state[at0022]/items[at0055]")
  private Cluster inspiredOxygen;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Respirations/Any event/Exertion
   */
  @Path("/data[at0001]/events[at0002]/state[at0022]/items[at0037]")
  private Cluster exertion;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Respirations/Any event/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Respirations/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Respirations/Extension
   */
  @Path("/protocol[at0057]/items[at0058]")
  private List<Cluster> extension;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Respirations/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Respirations/language
   */
  @Path("/language")
  private Language language;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Respirations/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setRateMagnitude(Double rateMagnitude) {
     this.rateMagnitude = rateMagnitude;
  }

  public Double getRateMagnitude() {
     return this.rateMagnitude ;
  }

  public void setRateUnits(String rateUnits) {
     this.rateUnits = rateUnits;
  }

  public String getRateUnits() {
     return this.rateUnits ;
  }

  public void setInspiredOxygen(Cluster inspiredOxygen) {
     this.inspiredOxygen = inspiredOxygen;
  }

  public Cluster getInspiredOxygen() {
     return this.inspiredOxygen ;
  }

  public void setExertion(Cluster exertion) {
     this.exertion = exertion;
  }

  public Cluster getExertion() {
     return this.exertion ;
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

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
