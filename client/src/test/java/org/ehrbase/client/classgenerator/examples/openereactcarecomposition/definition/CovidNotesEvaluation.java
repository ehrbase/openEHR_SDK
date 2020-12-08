package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.clinical_synopsis.v1")
public class CovidNotesEvaluation implements EntryEntity {
  /**
   * open_eREACT-Care/Assessment/Covid/Covid notes/Synopsis
   */
  @Path("/data[at0001]/items[at0002]/value|value")
  private String synopsisValue;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid notes/Extension
   */
  @Path("/protocol[at0003]/items[at0004]")
  private List<Cluster> extension;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid notes/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid notes/language
   */
  @Path("/language")
  private Language language;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid notes/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setSynopsisValue(String synopsisValue) {
     this.synopsisValue = synopsisValue;
  }

  public String getSynopsisValue() {
     return this.synopsisValue ;
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
