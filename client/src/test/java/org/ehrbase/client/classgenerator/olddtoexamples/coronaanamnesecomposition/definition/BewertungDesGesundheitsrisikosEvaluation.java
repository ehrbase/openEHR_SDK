package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.health_risk.v1")
public class BewertungDesGesundheitsrisikosEvaluation {
  @Path("/protocol[at0010]/items[at0024]/value|value")
  private TemporalAccessor letzteAktualisierungValue;

  @Path("/data[at0001]/items[at0016]/items[at0013]/value")
  @Choice
  private BewertungDesGesundheitsrisikosRisikofaktorChoice risikofaktor;

  @Path("/data[at0001]/items[at0003]/value|value")
  private String risikobewertungValue;

  @Path("/protocol[at0010]/items[at0011]")
  private List<Cluster> erweiterung;

  @Path("/data[at0001]/items[at0002]/value|value")
  private String gesundheitsrisikoValue;

  @Path("/language")
  private Language language;

  @Path("/data[at0001]/items[at0016]/items[at0017]/value")
  @Choice
  private BewertungDesGesundheitsrisikosVorhandenseinChoice vorhandensein;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/protocol[at0010]/items[at0025]/value|value")
  private String bewertungsmethodeValue;

  @Path("/data[at0001]/items[at0016]/items[at0027]")
  @Choice
  private BewertungDesGesundheitsrisikosDetailsChoice details;

  public void setLetzteAktualisierungValue(TemporalAccessor letzteAktualisierungValue) {
    this.letzteAktualisierungValue = letzteAktualisierungValue;
  }

  public TemporalAccessor getLetzteAktualisierungValue() {
    return this.letzteAktualisierungValue;
  }

  public void setRisikofaktor(BewertungDesGesundheitsrisikosRisikofaktorChoice risikofaktor) {
    this.risikofaktor = risikofaktor;
  }

  public BewertungDesGesundheitsrisikosRisikofaktorChoice getRisikofaktor() {
    return this.risikofaktor;
  }

  public void setRisikobewertungValue(String risikobewertungValue) {
    this.risikobewertungValue = risikobewertungValue;
  }

  public String getRisikobewertungValue() {
    return this.risikobewertungValue;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
    this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
    return this.erweiterung;
  }

  public void setGesundheitsrisikoValue(String gesundheitsrisikoValue) {
    this.gesundheitsrisikoValue = gesundheitsrisikoValue;
  }

  public String getGesundheitsrisikoValue() {
    return this.gesundheitsrisikoValue;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public Language getLanguage() {
    return this.language;
  }

  public void setVorhandensein(BewertungDesGesundheitsrisikosVorhandenseinChoice vorhandensein) {
    this.vorhandensein = vorhandensein;
  }

  public BewertungDesGesundheitsrisikosVorhandenseinChoice getVorhandensein() {
    return this.vorhandensein;
  }

  public void setSubject(PartyProxy subject) {
    this.subject = subject;
  }

  public PartyProxy getSubject() {
    return this.subject;
  }

  public void setBewertungsmethodeValue(String bewertungsmethodeValue) {
    this.bewertungsmethodeValue = bewertungsmethodeValue;
  }

  public String getBewertungsmethodeValue() {
    return this.bewertungsmethodeValue;
  }

  public void setDetails(BewertungDesGesundheitsrisikosDetailsChoice details) {
    this.details = details;
  }

  public BewertungDesGesundheitsrisikosDetailsChoice getDetails() {
    return this.details;
  }
}
