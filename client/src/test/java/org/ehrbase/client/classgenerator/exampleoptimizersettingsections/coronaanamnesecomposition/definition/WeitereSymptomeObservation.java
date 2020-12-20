package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

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
@Archetype("openEHR-EHR-OBSERVATION.symptom_sign_screening.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:13.407034200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class WeitereSymptomeObservation implements EntryEntity {
  /** Path: Bericht/Symptome/Weitere Symptome/origin */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Bericht/Symptome/Weitere Symptome/Erweiterung Description: Zusätzliche Informationen zur
   * Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen. Comment: Zum
   * Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an
   * FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0007]/items[at0021]")
  private List<Cluster> erweiterung;

  /** Path: Bericht/Symptome/Weitere Symptome/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: Bericht/Symptome/Weitere Symptome/language */
  @Path("/language")
  private Language language;

  /** Path: Bericht/Symptome/Weitere Symptome/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Bericht/Symptome/Weitere Symptome/Beliebiges Ereignis Description: Standardwert, ein
   * undefinierter/s Zeitpunkt oder Intervallereignis, das explizit im Template oder zur Laufzeit
   * der Anwendung definiert werden kann.
   */
  @Path("/data[at0001]/events[at0002]")
  @Choice
  private List<WeitereSymptomeBeliebigesEreignisChoice> beliebigesEreignis;

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
      List<WeitereSymptomeBeliebigesEreignisChoice> beliebigesEreignis) {
    this.beliebigesEreignis = beliebigesEreignis;
  }

  public List<WeitereSymptomeBeliebigesEreignisChoice> getBeliebigesEreignis() {
    return this.beliebigesEreignis;
  }
}
