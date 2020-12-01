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
@Archetype("openEHR-EHR-INSTRUCTION.service_request.v1")
public class DienstleistungInstruction {
  @Path("/activities[at0001]")
  private List<DienstleistungAktuelleAktivitatActivity> aktuelleAktivitat;

  @Path("/protocol[at0008]/items[at0141]")
  private Cluster einsender;

  @Path("/protocol[at0008]/items[at0142]")
  private Cluster empfanger;

  @Path("/protocol[at0008]/items[at0128]")
  private List<Cluster> verteilerliste;

  @Path("/protocol[at0008]/items[at0112]")
  private List<Cluster> erweiterung;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/narrative|value")
  private String narrativeValue;

  @Path("/language")
  private Language language;

  @Path("/expiry_time|value")
  private TemporalAccessor expiryTimeValue;

  public void setAktuelleAktivitat(
      List<DienstleistungAktuelleAktivitatActivity> aktuelleAktivitat) {
     this.aktuelleAktivitat = aktuelleAktivitat;
  }

  public List<DienstleistungAktuelleAktivitatActivity> getAktuelleAktivitat() {
     return this.aktuelleAktivitat ;
  }

  public void setEinsender(Cluster einsender) {
     this.einsender = einsender;
  }

  public Cluster getEinsender() {
     return this.einsender ;
  }

  public void setEmpfanger(Cluster empfanger) {
     this.empfanger = empfanger;
  }

  public Cluster getEmpfanger() {
     return this.empfanger ;
  }

  public void setVerteilerliste(List<Cluster> verteilerliste) {
     this.verteilerliste = verteilerliste;
  }

  public List<Cluster> getVerteilerliste() {
     return this.verteilerliste ;
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

  public void setNarrativeValue(String narrativeValue) {
     this.narrativeValue = narrativeValue;
  }

  public String getNarrativeValue() {
     return this.narrativeValue ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setExpiryTimeValue(TemporalAccessor expiryTimeValue) {
     this.expiryTimeValue = expiryTimeValue;
  }

  public TemporalAccessor getExpiryTimeValue() {
     return this.expiryTimeValue ;
  }
}
