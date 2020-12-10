package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.occupation_summary.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T09:57:06.123356100+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class GesundheitsbezogenerBerufEvaluation implements EntryEntity {
  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigungsstatus
   */
  @Path("/data[at0001]/items[at0004]/value|value")
  private String beschaeftigungsstatusValue;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.occupation_record.v1]")
  private List<BeschaeftigungCluster> beschaeftigung;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Zusätzliche Angaben
   */
  @Path("/data[at0001]/items[at0005]")
  private List<Cluster> zusaetzlicheAngaben;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Erweiterung
   */
  @Path("/protocol[at0007]/items[at0008]")
  private List<Cluster> erweiterung;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/language
   */
  @Path("/language")
  private Language language;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setBeschaeftigungsstatusValue(String beschaeftigungsstatusValue) {
     this.beschaeftigungsstatusValue = beschaeftigungsstatusValue;
  }

  public String getBeschaeftigungsstatusValue() {
     return this.beschaeftigungsstatusValue ;
  }

  public void setBeschaeftigung(List<BeschaeftigungCluster> beschaeftigung) {
     this.beschaeftigung = beschaeftigung;
  }

  public List<BeschaeftigungCluster> getBeschaeftigung() {
     return this.beschaeftigung ;
  }

  public void setZusaetzlicheAngaben(List<Cluster> zusaetzlicheAngaben) {
     this.zusaetzlicheAngaben = zusaetzlicheAngaben;
  }

  public List<Cluster> getZusaetzlicheAngaben() {
     return this.zusaetzlicheAngaben ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
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
