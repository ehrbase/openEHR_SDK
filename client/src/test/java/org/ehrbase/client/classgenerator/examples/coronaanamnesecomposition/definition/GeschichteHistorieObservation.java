package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.story.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.488025800+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class GeschichteHistorieObservation implements EntryEntity {
  /** Path: Bericht/Geschichte/Historie/origin */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Bericht/Geschichte/Historie/Erweiterung Description: Zusätzliche Informationen, die zur
   * Erfassung lokaler Inhalte oder zur Anpassung an andere Referenzmodelle/Formalismen erforderlich
   * sind. Comment: Zum Beispiel: Lokale Informationsanforderungen oder zusätzliche Metadaten, um
   * Verknüpfungen mit FHIR oder CIMI Äquivalenten herzustellen.
   */
  @Path("/protocol[at0007]/items[at0008]")
  private List<Cluster> erweiterung;

  /** Path: Bericht/Geschichte/Historie/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: Bericht/Geschichte/Historie/language */
  @Path("/language")
  private Language language;

  /** Path: Bericht/Geschichte/Historie/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Bericht/Geschichte/Historie/Beliebiges Ereignis Description: Standardmäßiger, nicht näher
   * beschriebener Zeitpunkt oder Intervall Ereignis welches in einem Template oder bei der
   * Anwendung genauer definiert werden kann.
   */
  @Path("/data[at0001]/events[at0002]")
  @Choice
  private List<GeschichteHistorieBeliebigesEreignisChoice> beliebigesEreignis;

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

  public void setBeliebigesEreignis(
      List<GeschichteHistorieBeliebigesEreignisChoice> beliebigesEreignis) {
    this.beliebigesEreignis = beliebigesEreignis;
  }

  public List<GeschichteHistorieBeliebigesEreignisChoice> getBeliebigesEreignis() {
    return this.beliebigesEreignis;
  }
}
