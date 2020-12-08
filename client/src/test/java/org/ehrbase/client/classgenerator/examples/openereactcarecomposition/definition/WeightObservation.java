package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.ItemTree;
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
@Archetype("openEHR-EHR-OBSERVATION.body_weight.v2")
public class WeightObservation implements EntryEntity {
  /**
   * open_eREACT-Care/Background/Weight/Any event/Weight
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude")
  private Double weightMagnitude;

  /**
   * open_eREACT-Care/Background/Weight/Any event/Weight
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units")
  private String weightUnits;

  /**
   * open_eREACT-Care/Background/Weight/Any event/state structure
   */
  @Path("/data[at0002]/events[at0003]/state[at0008]")
  private ItemTree stateStructure;

  /**
   * open_eREACT-Care/Background/Weight/Any event/time
   */
  @Path("/data[at0002]/events[at0003]/time|value")
  private TemporalAccessor timeValue;

  /**
   * open_eREACT-Care/Background/Weight/origin
   */
  @Path("/data[at0002]/origin|value")
  private TemporalAccessor originValue;

  /**
   * open_eREACT-Care/Background/Weight/Device
   */
  @Path("/protocol[at0015]/items[at0020]")
  private Cluster device;

  /**
   * open_eREACT-Care/Background/Weight/Extension
   */
  @Path("/protocol[at0015]/items[at0027]")
  private List<Cluster> extension;

  /**
   * open_eREACT-Care/Background/Weight/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * open_eREACT-Care/Background/Weight/language
   */
  @Path("/language")
  private Language language;

  /**
   * open_eREACT-Care/Background/Weight/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setWeightMagnitude(Double weightMagnitude) {
     this.weightMagnitude = weightMagnitude;
  }

  public Double getWeightMagnitude() {
     return this.weightMagnitude ;
  }

  public void setWeightUnits(String weightUnits) {
     this.weightUnits = weightUnits;
  }

  public String getWeightUnits() {
     return this.weightUnits ;
  }

  public void setStateStructure(ItemTree stateStructure) {
     this.stateStructure = stateStructure;
  }

  public ItemTree getStateStructure() {
     return this.stateStructure ;
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
