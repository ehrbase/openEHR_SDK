package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.travel_history.v0")
public class HistorieDerReiseObservation {
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0111 and name/value='Aufenthalt in den letzten 14 Tage in einem der Risikogebiete für Coronainfektion oder Kontakt zu Menschen, die dort waren']/value|defining_code")
  private AufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontak_ aufenthaltInDenLetzten14TageInEinemDerRisikogebieteFurCoronainfektionOderKontaktZuMenschenDieDortWarenDefiningCode;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0134]/items[openEHR-EHR-CLUSTER.location.v1]")
  private StandortCluster standort;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0109]")
  private List<Cluster> detaillierteAngabenZurExposition;

  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  @Path("/protocol[at0100]/items[at0101]")
  private List<Cluster> extensionEn;

  @Path("/subject")
  private PartyProxy subject;

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

  public void setStandort(StandortCluster standort) {
     this.standort = standort;
  }

  public StandortCluster getStandort() {
     return this.standort ;
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
