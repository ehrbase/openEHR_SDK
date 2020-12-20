package org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
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
@Archetype("openEHR-EHR-ADMIN_ENTRY.admission.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.961033700+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class AufnahmedatenAdminEntry implements EntryEntity {
  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Versorgungsfallgrund Description: Der Umstand,
   * unter dem der Patient aufgenommen wird.
   */
  @Path("/data[at0001]/items[at0013 and name/value='Versorgungsfallgrund']/value|value")
  private String versorgungsfallgrundValue;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Art der Aufnahme Description: Nähere
   * Beschreibung der Art der Aufnahme, z.B. Unfall oder Notfall.
   */
  @Path("/data[at0001]/items[at0049]/value|value")
  private String artDerAufnahmeValue;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Datum/Uhrzeit der Aufnahme Description:
   * Datum/Zeit, an dem der Patient aufgenommen wurde.
   */
  @Path("/data[at0001]/items[at0071]/value|value")
  private TemporalAccessor datumUhrzeitDerAufnahmeValue;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Zugewiesener Patientenstandort Description:
   * Zugewiesener Patientenstandort
   */
  @Path("/data[at0001]/items[at0131]")
  private List<Cluster> zugewiesenerPatientenstandort;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Vorheriger Patientenstandort Description:
   * Vorheriger Patientenstandort
   */
  @Path("/data[at0001]/items[at0132]")
  private List<Cluster> vorherigerPatientenstandort;

  /** Path: Stationärer Versorgungsfall/Aufnahmedaten/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: Stationärer Versorgungsfall/Aufnahmedaten/language */
  @Path("/language")
  private Language language;

  /** Path: Stationärer Versorgungsfall/Aufnahmedaten/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setVersorgungsfallgrundValue(String versorgungsfallgrundValue) {
    this.versorgungsfallgrundValue = versorgungsfallgrundValue;
  }

  public String getVersorgungsfallgrundValue() {
    return this.versorgungsfallgrundValue;
  }

  public void setArtDerAufnahmeValue(String artDerAufnahmeValue) {
    this.artDerAufnahmeValue = artDerAufnahmeValue;
  }

  public String getArtDerAufnahmeValue() {
    return this.artDerAufnahmeValue;
  }

  public void setDatumUhrzeitDerAufnahmeValue(TemporalAccessor datumUhrzeitDerAufnahmeValue) {
    this.datumUhrzeitDerAufnahmeValue = datumUhrzeitDerAufnahmeValue;
  }

  public TemporalAccessor getDatumUhrzeitDerAufnahmeValue() {
    return this.datumUhrzeitDerAufnahmeValue;
  }

  public void setZugewiesenerPatientenstandort(List<Cluster> zugewiesenerPatientenstandort) {
    this.zugewiesenerPatientenstandort = zugewiesenerPatientenstandort;
  }

  public List<Cluster> getZugewiesenerPatientenstandort() {
    return this.zugewiesenerPatientenstandort;
  }

  public void setVorherigerPatientenstandort(List<Cluster> vorherigerPatientenstandort) {
    this.vorherigerPatientenstandort = vorherigerPatientenstandort;
  }

  public List<Cluster> getVorherigerPatientenstandort() {
    return this.vorherigerPatientenstandort;
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
