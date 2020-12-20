package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.clinical_synopsis.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.520505500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class CovidNotesEvaluation implements EntryEntity {
  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid notes/Synopsis Description: The summary,
   * assessment, conclusions or evaluation of the clinical findings.
   */
  @Path("/data[at0001]/items[at0002]/value|value")
  private String synopsisValue;

  /**
   * Path: open_eREACT-Care/Assessment/Covid/Covid notes/Extension Description: Additional
   * information required to capture local content or to align with other reference
   * models/formalisms. Comment: For example: local information requirements or additional metadata
   * to align with FHIR or CIMI equivalents.
   */
  @Path("/protocol[at0003]/items[at0004]")
  private List<Cluster> extension;

  /** Path: open_eREACT-Care/Assessment/Covid/Covid notes/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: open_eREACT-Care/Assessment/Covid/Covid notes/language */
  @Path("/language")
  private Language language;

  /** Path: open_eREACT-Care/Assessment/Covid/Covid notes/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setSynopsisValue(String synopsisValue) {
    this.synopsisValue = synopsisValue;
  }

  public String getSynopsisValue() {
    return this.synopsisValue;
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
