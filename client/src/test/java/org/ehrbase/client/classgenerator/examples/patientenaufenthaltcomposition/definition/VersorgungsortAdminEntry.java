package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-ADMIN_ENTRY.hospitalization.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.896030200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class VersorgungsortAdminEntry implements EntryEntity {
  /**
   * Path: Patientenaufenthalt/Versorgungsort/Beginn Description: Zeitlicher Beginn des Aufenthaltes
   * am beschriebenen Ort.
   */
  @Path("/data[at0001]/items[at0004]/value|value")
  private TemporalAccessor beginnValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsort/Ende Description: Zeitliches Ende des Aufenthaltes am
   * beschriebenen Ort.
   */
  @Path("/data[at0001]/items[at0005]/value|value")
  private TemporalAccessor endeValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsort/Grund des Aufenthaltes Description: Grund des
   * Aufenthaltes
   */
  @Path("/data[at0001]/items[at0006]/value|value")
  private String grundDesAufenthaltesValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsort/Standort Description: Standort umfasst sowohl
   * beiläufige Orte (ein Ort, der für die medizinische Versorgung ohne vorherige Benennung oder
   * Genehmigung genutzt wird) als auch spezielle, offiziell benannte Orte. Die Standorte können
   * privat, öffentlich, mobil oder stationär sein.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.location.v1]")
  private StandortCluster standort;

  /** Path: Patientenaufenthalt/Versorgungsort/Kommentar Description: Zusätzliche Kommentare. */
  @Path("/data[at0001]/items[at0009]/value|value")
  private String kommentarValue;

  /** Path: Patientenaufenthalt/Versorgungsort/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: Patientenaufenthalt/Versorgungsort/language */
  @Path("/language")
  private Language language;

  /** Path: Patientenaufenthalt/Versorgungsort/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setBeginnValue(TemporalAccessor beginnValue) {
    this.beginnValue = beginnValue;
  }

  public TemporalAccessor getBeginnValue() {
    return this.beginnValue;
  }

  public void setEndeValue(TemporalAccessor endeValue) {
    this.endeValue = endeValue;
  }

  public TemporalAccessor getEndeValue() {
    return this.endeValue;
  }

  public void setGrundDesAufenthaltesValue(String grundDesAufenthaltesValue) {
    this.grundDesAufenthaltesValue = grundDesAufenthaltesValue;
  }

  public String getGrundDesAufenthaltesValue() {
    return this.grundDesAufenthaltesValue;
  }

  public void setStandort(StandortCluster standort) {
    this.standort = standort;
  }

  public StandortCluster getStandort() {
    return this.standort;
  }

  public void setKommentarValue(String kommentarValue) {
    this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
    return this.kommentarValue;
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
