package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.body_temperature.v2")
public class KorpertemperaturObservation {
  @Path("/protocol[at0020]/items[at0062]")
  private List<Cluster> erweiterung;

  @Path("/data[at0002]/events[at0003]/state[at0029]/items[at0066]")
  private List<KorpertemperaturStorfaktorenElement> storfaktoren;

  @Path("/language")
  private Language language;

  @Path("/protocol[at0020]/items[at0064]")
  private List<Cluster> strukturierteLokalisationDerMessung;

  @Path("/data[at0002]/origin|value")
  private TemporalAccessor originValue;

  @Path("/data[at0002]/events[at0003]/state[at0029]/items[at0057]")
  private Cluster betatigung;

  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude")
  private Double temperaturMagnitude;

  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units")
  private String temperaturUnits;

  @Path("/data[at0002]/events[at0003]/state[at0029]/items[at0056]")
  private List<Cluster> umgebungsbedingungen;

  @Path("/protocol[at0020]/items[at0059]")
  private Cluster gerat;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/data[at0002]/events[at0003]/time|value")
  private TemporalAccessor timeValue;

  public void setErweiterung(List<Cluster> erweiterung) {
    this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
    return this.erweiterung;
  }

  public void setStorfaktoren(List<KorpertemperaturStorfaktorenElement> storfaktoren) {
    this.storfaktoren = storfaktoren;
  }

  public List<KorpertemperaturStorfaktorenElement> getStorfaktoren() {
    return this.storfaktoren;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public Language getLanguage() {
    return this.language;
  }

  public void setStrukturierteLokalisationDerMessung(
      List<Cluster> strukturierteLokalisationDerMessung) {
    this.strukturierteLokalisationDerMessung = strukturierteLokalisationDerMessung;
  }

  public List<Cluster> getStrukturierteLokalisationDerMessung() {
    return this.strukturierteLokalisationDerMessung;
  }

  public void setOriginValue(TemporalAccessor originValue) {
    this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
    return this.originValue;
  }

  public void setBetatigung(Cluster betatigung) {
    this.betatigung = betatigung;
  }

  public Cluster getBetatigung() {
    return this.betatigung;
  }

  public void setTemperaturMagnitude(Double temperaturMagnitude) {
    this.temperaturMagnitude = temperaturMagnitude;
  }

  public Double getTemperaturMagnitude() {
    return this.temperaturMagnitude;
  }

  public void setTemperaturUnits(String temperaturUnits) {
    this.temperaturUnits = temperaturUnits;
  }

  public String getTemperaturUnits() {
    return this.temperaturUnits;
  }

  public void setUmgebungsbedingungen(List<Cluster> umgebungsbedingungen) {
    this.umgebungsbedingungen = umgebungsbedingungen;
  }

  public List<Cluster> getUmgebungsbedingungen() {
    return this.umgebungsbedingungen;
  }

  public void setGerat(Cluster gerat) {
    this.gerat = gerat;
  }

  public Cluster getGerat() {
    return this.gerat;
  }

  public void setSubject(PartyProxy subject) {
    this.subject = subject;
  }

  public PartyProxy getSubject() {
    return this.subject;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
    this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
    return this.timeValue;
  }
}
