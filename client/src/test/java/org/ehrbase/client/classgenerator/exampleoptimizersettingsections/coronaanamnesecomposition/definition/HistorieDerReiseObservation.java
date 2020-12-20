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
@Archetype("openEHR-EHR-OBSERVATION.travel_history.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:13.432032100+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class HistorieDerReiseObservation implements EntryEntity {
  /**
   * Path: Bericht/Risikogebiet/Historie der Reise/Jedes Ereignis/Aufenthalt in den letzten 14 Tage
   * in einem der Risikogebiete für Coronainfektion oder Kontakt zu Menschen, die dort waren
   * Description: Ist der Patient in letzter Zeit gereist? Die Definition des Begriffs "kürzlich"
   * kann je nach den Umständen der umfangreicheren Patientengeschichte und dem bekannten
   * Infektionsrisiko variieren.
   */
  @Path(
      "/data[at0001]/events[at0002]/data[at0003]/items[at0111 and name/value='Aufenthalt in den letzten 14 Tage in einem der Risikogebiete für Coronainfektion oder Kontakt zu Menschen, die dort waren']/value|defining_code")
  private AufenthaltInDenLetzten14TageInEinemDerRisikogebieteFuerCoronainfektionOderKonta_
      aufenthaltInDenLetzten14TageInEinemDerRisikogebieteFuerCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningCode;

  /**
   * Path: Bericht/Risikogebiet/Historie der Reise/Jedes Ereignis/Ortsbeschreibung/Standort
   * Description: Standort umfasst sowohl beiläufige Orte (ein Ort, der für die medizinische
   * Versorgung ohne vorherige Benennung oder Genehmigung genutzt wird) als auch spezielle,
   * offiziell benannte Orte. Die Standorte können privat, öffentlich, mobil oder stationär sein.
   */
  @Path(
      "/data[at0001]/events[at0002]/data[at0003]/items[at0134]/items[openEHR-EHR-CLUSTER.location.v1]")
  private StandortCluster standort;

  /**
   * Path: Bericht/Risikogebiet/Historie der Reise/Jedes Ereignis/Detaillierte Angaben zur
   * Exposition Description: *
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0109]")
  private List<Cluster> detaillierteAngabenZurExposition;

  /** Path: Bericht/Risikogebiet/Historie der Reise/Jedes Ereignis/time */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /** Path: Bericht/Risikogebiet/Historie der Reise/origin */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /** Path: Bericht/Risikogebiet/Historie der Reise/*Extension(en) Description: * */
  @Path("/protocol[at0100]/items[at0101]")
  private List<Cluster> extensionEn;

  /** Path: Bericht/Risikogebiet/Historie der Reise/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: Bericht/Risikogebiet/Historie der Reise/language */
  @Path("/language")
  private Language language;

  /** Path: Bericht/Risikogebiet/Historie der Reise/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void
      setAufenthaltInDenLetzten14TageInEinemDerRisikogebieteFuerCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningCode(
          AufenthaltInDenLetzten14TageInEinemDerRisikogebieteFuerCoronainfektionOderKonta_
              aufenthaltInDenLetzten14TageInEinemDerRisikogebieteFuerCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningCode) {
    this
            .aufenthaltInDenLetzten14TageInEinemDerRisikogebieteFuerCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningCode =
        aufenthaltInDenLetzten14TageInEinemDerRisikogebieteFuerCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningCode;
  }

  public AufenthaltInDenLetzten14TageInEinemDerRisikogebieteFuerCoronainfektionOderKonta_
      getAufenthaltInDenLetzten14TageInEinemDerRisikogebieteFuerCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningCode() {
    return this
        .aufenthaltInDenLetzten14TageInEinemDerRisikogebieteFuerCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningCode;
  }

  public void setStandort(StandortCluster standort) {
    this.standort = standort;
  }

  public StandortCluster getStandort() {
    return this.standort;
  }

  public void setDetaillierteAngabenZurExposition(List<Cluster> detaillierteAngabenZurExposition) {
    this.detaillierteAngabenZurExposition = detaillierteAngabenZurExposition;
  }

  public List<Cluster> getDetaillierteAngabenZurExposition() {
    return this.detaillierteAngabenZurExposition;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
    this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
    return this.timeValue;
  }

  public void setOriginValue(TemporalAccessor originValue) {
    this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
    return this.originValue;
  }

  public void setExtensionEn(List<Cluster> extensionEn) {
    this.extensionEn = extensionEn;
  }

  public List<Cluster> getExtensionEn() {
    return this.extensionEn;
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
