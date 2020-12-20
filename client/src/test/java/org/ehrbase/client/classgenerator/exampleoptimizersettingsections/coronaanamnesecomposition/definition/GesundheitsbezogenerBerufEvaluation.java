package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

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
@Archetype("openEHR-EHR-EVALUATION.occupation_summary.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:13.469034100+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class GesundheitsbezogenerBerufEvaluation implements EntryEntity {
  /**
   * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigungsstatus Description:
   * Aussage über die aktuelle Beschäftigung der Person.
   */
  @Path("/data[at0001]/items[at0004]/value|value")
  private String beschaeftigungsstatusValue;

  /**
   * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung Description: Ein
   * einzelner Job oder eine einzelne Rolle, die von einer Person während eines bestimmten Zeitraums
   * ausgeführt wurde.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.occupation_record.v1]")
  private List<BeschaeftigungCluster> beschaeftigung;

  /**
   * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Zusätzliche Angaben Description:
   * Weitere Angaben zu den aktuellen Jobs oder Rollen oder zum vorherigen Beschäftigungsverlauf
   * einer Person.
   */
  @Path("/data[at0001]/items[at0005]")
  private List<Cluster> zusaetzlicheAngaben;

  /**
   * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Erweiterung Description:
   * Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere
   * Referenzmodelle/Formalismen. Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche
   * Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0007]/items[at0008]")
  private List<Cluster> erweiterung;

  /** Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/language */
  @Path("/language")
  private Language language;

  /** Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setBeschaeftigungsstatusValue(String beschaeftigungsstatusValue) {
    this.beschaeftigungsstatusValue = beschaeftigungsstatusValue;
  }

  public String getBeschaeftigungsstatusValue() {
    return this.beschaeftigungsstatusValue;
  }

  public void setBeschaeftigung(List<BeschaeftigungCluster> beschaeftigung) {
    this.beschaeftigung = beschaeftigung;
  }

  public List<BeschaeftigungCluster> getBeschaeftigung() {
    return this.beschaeftigung;
  }

  public void setZusaetzlicheAngaben(List<Cluster> zusaetzlicheAngaben) {
    this.zusaetzlicheAngaben = zusaetzlicheAngaben;
  }

  public List<Cluster> getZusaetzlicheAngaben() {
    return this.zusaetzlicheAngaben;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
    this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
    return this.erweiterung;
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
