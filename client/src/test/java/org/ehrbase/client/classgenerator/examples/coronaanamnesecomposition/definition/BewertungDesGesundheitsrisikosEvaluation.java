package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

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
@Archetype("openEHR-EHR-EVALUATION.health_risk.v1")
public class BewertungDesGesundheitsrisikosEvaluation {
  @Path("/data[at0001]/items[at0002]/value|value")
  private String gesundheitsrisikoValue;

  @Path("/data[at0001]/items[at0016 and name/value='Spezifischer Risikofaktor']/items[at0013]/value|value")
  private String spezifischerRisikofaktorRisikofaktorValue;

  @Path("/data[at0001]/items[at0016 and name/value='Spezifischer Risikofaktor']/items[at0017]/value|defining_code")
  private VorhandenseinDefiningCode2 vorhandenseinDefiningCode;

  @Path("/data[at0001]/items[at0016 and name/value='Spezifischer Risikofaktor']/items[at0027]")
  private List<Cluster> spezifischerRisikofaktorDetails;

  @Path("/data[at0001]/items[at0016 and name/value='Andere Risikofaktoren']/items[at0013]/value|value")
  private String andereRisikofaktorenRisikofaktorValue;

  @Path("/data[at0001]/items[at0016 and name/value='Andere Risikofaktoren']/items[at0017]/value|defining_code")
  private VorhandenseinDefiningCode2 vorhandenseinDefiningCode2;

  @Path("/data[at0001]/items[at0016 and name/value='Andere Risikofaktoren']/items[at0027]")
  private List<Cluster> andereRisikofaktorenDetails;

  @Path("/data[at0001]/items[at0003]/value|value")
  private String risikobewertungValue;

  @Path("/protocol[at0010]/items[at0024]/value|value")
  private TemporalAccessor letzteAktualisierungValue;

  @Path("/protocol[at0010]/items[at0025]/value|value")
  private String bewertungsmethodeValue;

  @Path("/protocol[at0010]/items[at0011]")
  private List<Cluster> erweiterung;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/language")
  private Language language;

  public void setGesundheitsrisikoValue(String gesundheitsrisikoValue) {
     this.gesundheitsrisikoValue = gesundheitsrisikoValue;
  }

  public String getGesundheitsrisikoValue() {
     return this.gesundheitsrisikoValue ;
  }

  public void setSpezifischerRisikofaktorRisikofaktorValue(
      String spezifischerRisikofaktorRisikofaktorValue) {
     this.spezifischerRisikofaktorRisikofaktorValue = spezifischerRisikofaktorRisikofaktorValue;
  }

  public String getSpezifischerRisikofaktorRisikofaktorValue() {
     return this.spezifischerRisikofaktorRisikofaktorValue ;
  }

  public void setVorhandenseinDefiningCode(VorhandenseinDefiningCode2 vorhandenseinDefiningCode) {
     this.vorhandenseinDefiningCode = vorhandenseinDefiningCode;
  }

  public VorhandenseinDefiningCode2 getVorhandenseinDefiningCode() {
     return this.vorhandenseinDefiningCode ;
  }

  public void setSpezifischerRisikofaktorDetails(List<Cluster> spezifischerRisikofaktorDetails) {
     this.spezifischerRisikofaktorDetails = spezifischerRisikofaktorDetails;
  }

  public List<Cluster> getSpezifischerRisikofaktorDetails() {
     return this.spezifischerRisikofaktorDetails ;
  }

  public void setAndereRisikofaktorenRisikofaktorValue(
      String andereRisikofaktorenRisikofaktorValue) {
     this.andereRisikofaktorenRisikofaktorValue = andereRisikofaktorenRisikofaktorValue;
  }

  public String getAndereRisikofaktorenRisikofaktorValue() {
     return this.andereRisikofaktorenRisikofaktorValue ;
  }

  public void setVorhandenseinDefiningCode2(VorhandenseinDefiningCode2 vorhandenseinDefiningCode2) {
     this.vorhandenseinDefiningCode2 = vorhandenseinDefiningCode2;
  }

  public VorhandenseinDefiningCode2 getVorhandenseinDefiningCode2() {
     return this.vorhandenseinDefiningCode2 ;
  }

  public void setAndereRisikofaktorenDetails(List<Cluster> andereRisikofaktorenDetails) {
     this.andereRisikofaktorenDetails = andereRisikofaktorenDetails;
  }

  public List<Cluster> getAndereRisikofaktorenDetails() {
     return this.andereRisikofaktorenDetails ;
  }

  public void setRisikobewertungValue(String risikobewertungValue) {
     this.risikobewertungValue = risikobewertungValue;
  }

  public String getRisikobewertungValue() {
     return this.risikobewertungValue ;
  }

  public void setLetzteAktualisierungValue(TemporalAccessor letzteAktualisierungValue) {
     this.letzteAktualisierungValue = letzteAktualisierungValue;
  }

  public TemporalAccessor getLetzteAktualisierungValue() {
     return this.letzteAktualisierungValue ;
  }

  public void setBewertungsmethodeValue(String bewertungsmethodeValue) {
     this.bewertungsmethodeValue = bewertungsmethodeValue;
  }

  public String getBewertungsmethodeValue() {
     return this.bewertungsmethodeValue ;
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
}
