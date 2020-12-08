package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.pulse_oximetry.v1")
public class PulseOximetryObservation implements EntryEntity {
  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/Any event/SpO₂
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006]/value")
  private DvProportion spo;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/Any event/Waveform
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0054]")
  private List<Cluster> waveform;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/Any event/Multimedia image
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0060]")
  private List<Cluster> multimediaImage;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/Any event/Exertion
   */
  @Path("/data[at0001]/events[at0002]/state[at0014]/items[at0034]")
  private Cluster exertion;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/Any event/Inspired oxygen
   */
  @Path("/data[at0001]/events[at0002]/state[at0014]/items[at0015]")
  private Cluster inspiredOxygen;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/Any event/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/Oximetry device
   */
  @Path("/protocol[at0007]/items[at0018]")
  private Cluster oximetryDevice;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/Extension
   */
  @Path("/protocol[at0007]/items[at0059]")
  private List<Cluster> extension;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/language
   */
  @Path("/language")
  private Language language;

  /**
   * open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setSpo(DvProportion spo) {
     this.spo = spo;
  }

  public DvProportion getSpo() {
     return this.spo ;
  }

  public void setWaveform(List<Cluster> waveform) {
     this.waveform = waveform;
  }

  public List<Cluster> getWaveform() {
     return this.waveform ;
  }

  public void setMultimediaImage(List<Cluster> multimediaImage) {
     this.multimediaImage = multimediaImage;
  }

  public List<Cluster> getMultimediaImage() {
     return this.multimediaImage ;
  }

  public void setExertion(Cluster exertion) {
     this.exertion = exertion;
  }

  public Cluster getExertion() {
     return this.exertion ;
  }

  public void setInspiredOxygen(Cluster inspiredOxygen) {
     this.inspiredOxygen = inspiredOxygen;
  }

  public Cluster getInspiredOxygen() {
     return this.inspiredOxygen ;
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

  public void setOximetryDevice(Cluster oximetryDevice) {
     this.oximetryDevice = oximetryDevice;
  }

  public Cluster getOximetryDevice() {
     return this.oximetryDevice ;
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
