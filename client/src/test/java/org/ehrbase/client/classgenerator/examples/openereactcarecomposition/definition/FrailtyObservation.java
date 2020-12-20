package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
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
@Archetype("openEHR-EHR-OBSERVATION.clinical_frailty_scale.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.331497700+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class FrailtyObservation implements EntryEntity {
  /**
   * Path: open_eREACT-Care/Background/Frailty/Any point in time event/Assessment Description:
   * Assessed level of frailty.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value")
  private DvOrdinal assessment;

  /** Path: open_eREACT-Care/Background/Frailty/Any point in time event/time */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /** Path: open_eREACT-Care/Background/Frailty/origin */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: open_eREACT-Care/Background/Frailty/Extension Description: Additional information
   * required to extend the model with local content or to align with other reference
   * models/formalisms. Comment: For example: local information requirements; or additional metadata
   * to align with FHIR.
   */
  @Path("/protocol[at0014]/items[at0015]")
  private List<Cluster> extension;

  /** Path: open_eREACT-Care/Background/Frailty/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: open_eREACT-Care/Background/Frailty/language */
  @Path("/language")
  private Language language;

  /** Path: open_eREACT-Care/Background/Frailty/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setAssessment(DvOrdinal assessment) {
    this.assessment = assessment;
  }

  public DvOrdinal getAssessment() {
    return this.assessment;
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
