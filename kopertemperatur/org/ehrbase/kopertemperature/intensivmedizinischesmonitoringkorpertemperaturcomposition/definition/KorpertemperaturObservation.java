package org.ehrbase.kopertemperature.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.kopertemperature.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.body_temperature.v2")
public class KorpertemperaturObservation {
  @Path("/data[at0002]/events[at0003]")
  @Choice
  private List<KorpertemperaturBeliebigesEreignisChoice> beliebigesEreignis;

  @Path("/protocol[at0020]/items[at0062]")
  private List<Cluster> erweiterung;

  @Path("/language")
  private Language language;

  @Path("/protocol[at0020]/items[at0064]")
  private List<Cluster> strukturierteLokalisationDerMessung;

  @Path("/data[at0002]/origin|value")
  private TemporalAccessor originValue;

  @Path("/protocol[at0020]/items[at0059]")
  private Cluster gerat;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/protocol[at0020]/items[at0021]/value")
  @Choice
  private KorpertemperaturLokalisationDerMessungChoice lokalisationDerMessung;

  public void setBeliebigesEreignis(
      List<KorpertemperaturBeliebigesEreignisChoice> beliebigesEreignis) {
     this.beliebigesEreignis = beliebigesEreignis;
  }

  public List<KorpertemperaturBeliebigesEreignisChoice> getBeliebigesEreignis() {
     return this.beliebigesEreignis ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setStrukturierteLokalisationDerMessung(
      List<Cluster> strukturierteLokalisationDerMessung) {
     this.strukturierteLokalisationDerMessung = strukturierteLokalisationDerMessung;
  }

  public List<Cluster> getStrukturierteLokalisationDerMessung() {
     return this.strukturierteLokalisationDerMessung ;
  }

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
  }

  public void setGerat(Cluster gerat) {
     this.gerat = gerat;
  }

  public Cluster getGerat() {
     return this.gerat ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setLokalisationDerMessung(
      KorpertemperaturLokalisationDerMessungChoice lokalisationDerMessung) {
     this.lokalisationDerMessung = lokalisationDerMessung;
  }

  public KorpertemperaturLokalisationDerMessungChoice getLokalisationDerMessung() {
     return this.lokalisationDerMessung ;
  }
}
