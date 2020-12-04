package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.travel_history.v0")
public class HistorieDerReiseObservation {
  /**
   * Bericht/Risikogebiet/Historie der Reise/Jedes Ereignis/Aufenthalt in den letzten 14 Tage in einem der Risikogebiete für Coronainfektion oder Kontakt zu Menschen, die dort waren
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0111 and name/value='Aufenthalt in den letzten 14 Tage in einem der Risikogebiete für Coronainfektion oder Kontakt zu Menschen, die dort waren']/value|defining_code")
  private AufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontak_ aufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningCode;

  /**
   * Bericht/Risikogebiet/Historie der Reise/Jedes Ereignis/Ortsbeschreibung/Standort/Standortbeschreibung
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0134]/items[openEHR-EHR-CLUSTER.location.v1]/items[at0046]/value|value")
  private String standortbeschreibungValue;

  /**
   * Bericht/Risikogebiet/Historie der Reise/Jedes Ereignis/Ortsbeschreibung/Standort/Details
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0134]/items[openEHR-EHR-CLUSTER.location.v1]/items[at0047]")
  private List<Cluster> details;

  /**
   * Bericht/Risikogebiet/Historie der Reise/Jedes Ereignis/Detaillierte Angaben zur Exposition
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0109]")
  private List<Cluster> detaillierteAngabenZurExposition;

  /**
   * Bericht/Risikogebiet/Historie der Reise/Jedes Ereignis/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Bericht/Risikogebiet/Historie der Reise/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Bericht/Risikogebiet/Historie der Reise/*Extension(en)
   */
  @Path("/protocol[at0100]/items[at0101]")
  private List<Cluster> extensionEn;

  /**
   * Bericht/Risikogebiet/Historie der Reise/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Bericht/Risikogebiet/Historie der Reise/language
   */
  @Path("/language")
  private Language language;

  public void setAufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningCode(
      AufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontak_ aufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningCode) {
     this.aufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningCode = aufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningCode;
  }

  public AufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontak_ getAufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningCode(
      ) {
     return this.aufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningCode ;
  }

  public void setStandortbeschreibungValue(String standortbeschreibungValue) {
     this.standortbeschreibungValue = standortbeschreibungValue;
  }

  public String getStandortbeschreibungValue() {
     return this.standortbeschreibungValue ;
  }

  public void setDetails(List<Cluster> details) {
     this.details = details;
  }

  public List<Cluster> getDetails() {
     return this.details ;
  }

  public void setDetaillierteAngabenZurExposition(List<Cluster> detaillierteAngabenZurExposition) {
     this.detaillierteAngabenZurExposition = detaillierteAngabenZurExposition;
  }

  public List<Cluster> getDetaillierteAngabenZurExposition() {
     return this.detaillierteAngabenZurExposition ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
  }

  public void setExtensionEn(List<Cluster> extensionEn) {
     this.extensionEn = extensionEn;
  }

  public List<Cluster> getExtensionEn() {
     return this.extensionEn ;
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
}
