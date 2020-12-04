package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.travel_event.v0")
public class ReisefallObservation {
  /**
   * Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis
   */
  @Path("/data[at0001]/events[at0002]")
  private List<ReisefallBeliebigesIntervallereignisIntervalEvent> beliebigesIntervallereignis;

  /**
   * Bericht/Risikogebiet/Reisefall/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Bericht/Risikogebiet/Reisefall/Erweiterung
   */
  @Path("/protocol[at0007]/items[at0021]")
  private List<Cluster> erweiterung;

  /**
   * Bericht/Risikogebiet/Reisefall/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Bericht/Risikogebiet/Reisefall/language
   */
  @Path("/language")
  private Language language;

  /**
   * Bericht/Risikogebiet/Reisefall/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setBeliebigesIntervallereignis(
      List<ReisefallBeliebigesIntervallereignisIntervalEvent> beliebigesIntervallereignis) {
     this.beliebigesIntervallereignis = beliebigesIntervallereignis;
  }

  public List<ReisefallBeliebigesIntervallereignisIntervalEvent> getBeliebigesIntervallereignis() {
     return this.beliebigesIntervallereignis ;
  }

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
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
