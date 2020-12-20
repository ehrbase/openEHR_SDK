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
@Archetype("openEHR-EHR-ADMIN_ENTRY.discharge_summary.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.963031500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class EntlassungsdatenAdminEntry implements EntryEntity {
  /**
   * Path: Stationärer Versorgungsfall/Entlassungsdaten/Art der Entlassung Description: Grund der
   * Entlassung
   */
  @Path("/data[at0001]/items[at0040]/value|value")
  private String artDerEntlassungValue;

  /**
   * Path: Stationärer Versorgungsfall/Entlassungsdaten/Klinischer Zustand des Patienten
   * Description: Klinischer Zustand des Patienten.
   */
  @Path("/data[at0001]/items[at0002]/value|defining_code")
  private KlinischerZustandDesPatientenDefiningCode klinischerZustandDesPatientenDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Entlassungsdaten/Datum/Uhrzeit der Entlassung Description:
   * Datum/Uhrzeit, an dem der Patient entlassen wurde.
   */
  @Path("/data[at0001]/items[at0011 and name/value='Datum/Uhrzeit der Entlassung']/value|value")
  private TemporalAccessor datumUhrzeitDerEntlassungValue;

  /**
   * Path: Stationärer Versorgungsfall/Entlassungsdaten/Zusätzliche Informationen Description:
   * Kommentare
   */
  @Path("/data[at0001]/items[at0050]/value|value")
  private String zusatzlicheInformationenValue;

  /** Path: Stationärer Versorgungsfall/Entlassungsdaten/Letzter Patientenstandort Description: * */
  @Path("/data[at0001]/items[at0066]")
  private List<Cluster> letzterPatientenstandort;

  /**
   * Path: Stationärer Versorgungsfall/Entlassungsdaten/Zugewiesener Patientenstandort Description:
   * Für die lokale Verwendung enthält dieses Feld den Typ der Organisationseinheit oder der
   * klinischen Einheit.
   */
  @Path("/data[at0001]/items[at0067]")
  private List<Cluster> zugewiesenerPatientenstandort;

  /** Path: Stationärer Versorgungsfall/Entlassungsdaten/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: Stationärer Versorgungsfall/Entlassungsdaten/language */
  @Path("/language")
  private Language language;

  /** Path: Stationärer Versorgungsfall/Entlassungsdaten/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setArtDerEntlassungValue(String artDerEntlassungValue) {
    this.artDerEntlassungValue = artDerEntlassungValue;
  }

  public String getArtDerEntlassungValue() {
    return this.artDerEntlassungValue;
  }

  public void setKlinischerZustandDesPatientenDefiningCode(
      KlinischerZustandDesPatientenDefiningCode klinischerZustandDesPatientenDefiningCode) {
    this.klinischerZustandDesPatientenDefiningCode = klinischerZustandDesPatientenDefiningCode;
  }

  public KlinischerZustandDesPatientenDefiningCode getKlinischerZustandDesPatientenDefiningCode() {
    return this.klinischerZustandDesPatientenDefiningCode;
  }

  public void setDatumUhrzeitDerEntlassungValue(TemporalAccessor datumUhrzeitDerEntlassungValue) {
    this.datumUhrzeitDerEntlassungValue = datumUhrzeitDerEntlassungValue;
  }

  public TemporalAccessor getDatumUhrzeitDerEntlassungValue() {
    return this.datumUhrzeitDerEntlassungValue;
  }

  public void setZusatzlicheInformationenValue(String zusatzlicheInformationenValue) {
    this.zusatzlicheInformationenValue = zusatzlicheInformationenValue;
  }

  public String getZusatzlicheInformationenValue() {
    return this.zusatzlicheInformationenValue;
  }

  public void setLetzterPatientenstandort(List<Cluster> letzterPatientenstandort) {
    this.letzterPatientenstandort = letzterPatientenstandort;
  }

  public List<Cluster> getLetzterPatientenstandort() {
    return this.letzterPatientenstandort;
  }

  public void setZugewiesenerPatientenstandort(List<Cluster> zugewiesenerPatientenstandort) {
    this.zugewiesenerPatientenstandort = zugewiesenerPatientenstandort;
  }

  public List<Cluster> getZugewiesenerPatientenstandort() {
    return this.zugewiesenerPatientenstandort;
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
