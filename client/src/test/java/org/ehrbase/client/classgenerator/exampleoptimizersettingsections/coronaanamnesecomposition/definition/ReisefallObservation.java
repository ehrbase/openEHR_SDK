package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

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
@Archetype("openEHR-EHR-OBSERVATION.travel_event.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:13.439033300+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class ReisefallObservation implements EntryEntity {
  /**
   * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis Description: Standardwert,
   * ein undefiniertes Intervallereignis (Zeitraum), das explizit im Template oder zur Laufzeit der
   * Anwendung definiert werden kann.
   */
  @Path("/data[at0001]/events[at0002]")
  private List<ReisefallBeliebigesIntervallereignisIntervalEvent> beliebigesIntervallereignis;

  /** Path: Bericht/Risikogebiet/Reisefall/origin */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Bericht/Risikogebiet/Reisefall/Erweiterung Description: Zusätzliche Informationen zur
   * Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen. Comment: Zum
   * Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an
   * FHIR-Ressourcen.
   */
  @Path("/protocol[at0007]/items[at0021]")
  private List<Cluster> erweiterung;

  /** Path: Bericht/Risikogebiet/Reisefall/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: Bericht/Risikogebiet/Reisefall/language */
  @Path("/language")
  private Language language;

  /** Path: Bericht/Risikogebiet/Reisefall/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setBeliebigesIntervallereignis(
      List<ReisefallBeliebigesIntervallereignisIntervalEvent> beliebigesIntervallereignis) {
    this.beliebigesIntervallereignis = beliebigesIntervallereignis;
  }

  public List<ReisefallBeliebigesIntervallereignisIntervalEvent> getBeliebigesIntervallereignis() {
    return this.beliebigesIntervallereignis;
  }

  public void setOriginValue(TemporalAccessor originValue) {
    this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
    return this.originValue;
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
