package org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
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
    date = "2020-12-09T11:37:52.959763200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class EntlassungsdatenAdminEntry implements EntryEntity {
  /**
   * Stationärer Versorgungsfall/Entlassungsdaten/Art der Entlassung
   */
  @Path("/data[at0001]/items[at0040]/value|value")
  private String artDerEntlassungValue;

  /**
   * Stationärer Versorgungsfall/Entlassungsdaten/Klinischer Zustand des Patienten
   */
  @Path("/data[at0001]/items[at0002]/value|defining_code")
  private KlinischerZustandDesPatientenDefiningCode klinischerZustandDesPatientenDefiningCode;

  /**
   * Stationärer Versorgungsfall/Entlassungsdaten/Datum/Uhrzeit der Entlassung
   */
  @Path("/data[at0001]/items[at0011 and name/value='Datum/Uhrzeit der Entlassung']/value|value")
  private TemporalAccessor datumUhrzeitDerEntlassungValue;

  /**
   * Stationärer Versorgungsfall/Entlassungsdaten/Zusätzliche Informationen
   */
  @Path("/data[at0001]/items[at0050]/value|value")
  private String zusatzlicheInformationenValue;

  /**
   * Stationärer Versorgungsfall/Entlassungsdaten/Letzter Patientenstandort
   */
  @Path("/data[at0001]/items[at0066]")
  private List<Cluster> letzterPatientenstandort;

  /**
   * Stationärer Versorgungsfall/Entlassungsdaten/Zugewiesener Patientenstandort
   */
  @Path("/data[at0001]/items[at0067]")
  private List<Cluster> zugewiesenerPatientenstandort;

  /**
   * Stationärer Versorgungsfall/Entlassungsdaten/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Stationärer Versorgungsfall/Entlassungsdaten/language
   */
  @Path("/language")
  private Language language;

  /**
   * Stationärer Versorgungsfall/Entlassungsdaten/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setArtDerEntlassungValue(String artDerEntlassungValue) {
     this.artDerEntlassungValue = artDerEntlassungValue;
  }

  public String getArtDerEntlassungValue() {
     return this.artDerEntlassungValue ;
  }

  public void setKlinischerZustandDesPatientenDefiningCode(
      KlinischerZustandDesPatientenDefiningCode klinischerZustandDesPatientenDefiningCode) {
     this.klinischerZustandDesPatientenDefiningCode = klinischerZustandDesPatientenDefiningCode;
  }

  public KlinischerZustandDesPatientenDefiningCode getKlinischerZustandDesPatientenDefiningCode() {
     return this.klinischerZustandDesPatientenDefiningCode ;
  }

  public void setDatumUhrzeitDerEntlassungValue(TemporalAccessor datumUhrzeitDerEntlassungValue) {
     this.datumUhrzeitDerEntlassungValue = datumUhrzeitDerEntlassungValue;
  }

  public TemporalAccessor getDatumUhrzeitDerEntlassungValue() {
     return this.datumUhrzeitDerEntlassungValue ;
  }

  public void setZusatzlicheInformationenValue(String zusatzlicheInformationenValue) {
     this.zusatzlicheInformationenValue = zusatzlicheInformationenValue;
  }

  public String getZusatzlicheInformationenValue() {
     return this.zusatzlicheInformationenValue ;
  }

  public void setLetzterPatientenstandort(List<Cluster> letzterPatientenstandort) {
     this.letzterPatientenstandort = letzterPatientenstandort;
  }

  public List<Cluster> getLetzterPatientenstandort() {
     return this.letzterPatientenstandort ;
  }

  public void setZugewiesenerPatientenstandort(List<Cluster> zugewiesenerPatientenstandort) {
     this.zugewiesenerPatientenstandort = zugewiesenerPatientenstandort;
  }

  public List<Cluster> getZugewiesenerPatientenstandort() {
     return this.zugewiesenerPatientenstandort ;
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
