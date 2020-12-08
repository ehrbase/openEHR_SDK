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
@Archetype("openEHR-EHR-OBSERVATION.pulse.v1")
public class PulseObservation implements EntryEntity {
  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse/Any event/Pulse Rate
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004 and name/value='Pulse Rate']/value|magnitude")
  private Double pulseRateMagnitude;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse/Any event/Pulse Rate
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004 and name/value='Pulse Rate']/value|units")
  private String pulseRateUnits;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse/Any event/Exertion
   */
  @Path("/data[at0002]/events[at0003]/state[at0012]/items[at1017]")
  private List<Cluster> exertion;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse/Any event/time
   */
  @Path("/data[at0002]/events[at0003]/time|value")
  private TemporalAccessor timeValue;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse/origin
   */
  @Path("/data[at0002]/origin|value")
  private TemporalAccessor originValue;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse/Device
   */
  @Path("/protocol[at0010]/items[at1013]")
  private Cluster device;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse/Extension
   */
  @Path("/protocol[at0010]/items[at1056]")
  private List<Cluster> extension;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse/language
   */
  @Path("/language")
  private Language language;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setPulseRateMagnitude(Double pulseRateMagnitude) {
     this.pulseRateMagnitude = pulseRateMagnitude;
  }

  public Double getPulseRateMagnitude() {
     return this.pulseRateMagnitude ;
  }

  public void setPulseRateUnits(String pulseRateUnits) {
     this.pulseRateUnits = pulseRateUnits;
  }

  public String getPulseRateUnits() {
     return this.pulseRateUnits ;
  }

  public void setExertion(List<Cluster> exertion) {
     this.exertion = exertion;
  }

  public List<Cluster> getExertion() {
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

  public void setDevice(Cluster device) {
     this.device = device;
  }

  public Cluster getDevice() {
     return this.device ;
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
