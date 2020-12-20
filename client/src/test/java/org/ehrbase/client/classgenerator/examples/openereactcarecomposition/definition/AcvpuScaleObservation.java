package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.ItemTree;
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
@Archetype("openEHR-EHR-OBSERVATION.acvpu.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.561503400+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class AcvpuScaleObservation implements EntryEntity {
  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/ACVPU scale/Any point in time event/ACVPU Description:
   * The assessment of the patient's level of consciousness.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|defining_code")
  private AcvpuDefiningCode acvpuDefiningCode;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/ACVPU scale/Any point in time event/Tree Description: @
   * internal @
   */
  @Path("/data[at0001]/events[at0002]/state[at0013]")
  private ItemTree tree;

  /** Path: open_eREACT-Care/Assessment/NEWS2/ACVPU scale/Any point in time event/time */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /** Path: open_eREACT-Care/Assessment/NEWS2/ACVPU scale/origin */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/ACVPU scale/Extension Description: Additional
   * information required to capture local content or to align with other reference
   * models/formalisms. Comment: e.g. Local information requirements or additional metadata to align
   * with FHIR or CIMI equivalents.
   */
  @Path("/protocol[at0009]/items[at0011]")
  private List<Cluster> extension;

  /** Path: open_eREACT-Care/Assessment/NEWS2/ACVPU scale/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: open_eREACT-Care/Assessment/NEWS2/ACVPU scale/language */
  @Path("/language")
  private Language language;

  /** Path: open_eREACT-Care/Assessment/NEWS2/ACVPU scale/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setAcvpuDefiningCode(AcvpuDefiningCode acvpuDefiningCode) {
    this.acvpuDefiningCode = acvpuDefiningCode;
  }

  public AcvpuDefiningCode getAcvpuDefiningCode() {
    return this.acvpuDefiningCode;
  }

  public void setTree(ItemTree tree) {
    this.tree = tree;
  }

  public ItemTree getTree() {
    return this.tree;
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
