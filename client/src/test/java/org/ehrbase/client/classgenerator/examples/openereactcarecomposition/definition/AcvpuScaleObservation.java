package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.ItemTree;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.acvpu.v0")
public class AcvpuScaleObservation implements EntryEntity {
  /**
   * open_eREACT-Care/Assessment/NEWS2/ACVPU scale/Any point in time event/ACVPU
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|defining_code")
  private AcvpuDefiningCode acvpuDefiningCode;

  /**
   * open_eREACT-Care/Assessment/NEWS2/ACVPU scale/Any point in time event/Tree
   */
  @Path("/data[at0001]/events[at0002]/state[at0013]")
  private ItemTree tree;

  /**
   * open_eREACT-Care/Assessment/NEWS2/ACVPU scale/Any point in time event/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /**
   * open_eREACT-Care/Assessment/NEWS2/ACVPU scale/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * open_eREACT-Care/Assessment/NEWS2/ACVPU scale/Extension
   */
  @Path("/protocol[at0009]/items[at0011]")
  private List<Cluster> extension;

  /**
   * open_eREACT-Care/Assessment/NEWS2/ACVPU scale/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * open_eREACT-Care/Assessment/NEWS2/ACVPU scale/language
   */
  @Path("/language")
  private Language language;

  /**
   * open_eREACT-Care/Assessment/NEWS2/ACVPU scale/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setAcvpuDefiningCode(AcvpuDefiningCode acvpuDefiningCode) {
     this.acvpuDefiningCode = acvpuDefiningCode;
  }

  public AcvpuDefiningCode getAcvpuDefiningCode() {
     return this.acvpuDefiningCode ;
  }

  public void setTree(ItemTree tree) {
     this.tree = tree;
  }

  public ItemTree getTree() {
     return this.tree ;
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
