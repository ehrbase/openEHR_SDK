package org.ehrbase.sarscov2.kennzeichnungerregernachweissarscov2composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Boolean;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.sarscov2.shareddefinition.ErregernameDefiningcode;
import org.ehrbase.sarscov2.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.flag_pathogen.v0")
public class KennzeichnungErregernachweisEvaluation {
  @Path("/data[at0001]/items[at0005]/value|value")
  private Boolean erregernachweisValue;

  @Path("/language")
  private Language language;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/protocol[at0003]/items[at0007]")
  private List<Cluster> erweiterung;

  @Path("/data[at0001]/items[at0015]/value|value")
  private TemporalAccessor zeitpunktDerKennzeichnungValue;

  @Path("/data[at0001]/items[at0012]/value|defining_code")
  private ErregernameDefiningcode erregernameDefiningcode;

  @Path("/data[at0001]/items[at0013]/value|value")
  private String hinweistextValue;

  @Path("/data[at0001]/items[at0011]/value|value")
  private Boolean erregernachweisInDerKlinikValue;

  @Path("/protocol[at0003]/items[at0004]/value|value")
  private TemporalAccessor zuletztAktualisiertValue;

  public void setErregernachweisValue(Boolean erregernachweisValue) {
     this.erregernachweisValue = erregernachweisValue;
  }

  public Boolean isErregernachweisValue() {
     return this.erregernachweisValue ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setZeitpunktDerKennzeichnungValue(TemporalAccessor zeitpunktDerKennzeichnungValue) {
     this.zeitpunktDerKennzeichnungValue = zeitpunktDerKennzeichnungValue;
  }

  public TemporalAccessor getZeitpunktDerKennzeichnungValue() {
     return this.zeitpunktDerKennzeichnungValue ;
  }

  public void setErregernameDefiningcode(ErregernameDefiningcode erregernameDefiningcode) {
     this.erregernameDefiningcode = erregernameDefiningcode;
  }

  public ErregernameDefiningcode getErregernameDefiningcode() {
     return this.erregernameDefiningcode ;
  }

  public void setHinweistextValue(String hinweistextValue) {
     this.hinweistextValue = hinweistextValue;
  }

  public String getHinweistextValue() {
     return this.hinweistextValue ;
  }

  public void setErregernachweisInDerKlinikValue(Boolean erregernachweisInDerKlinikValue) {
     this.erregernachweisInDerKlinikValue = erregernachweisInDerKlinikValue;
  }

  public Boolean isErregernachweisInDerKlinikValue() {
     return this.erregernachweisInDerKlinikValue ;
  }

  public void setZuletztAktualisiertValue(TemporalAccessor zuletztAktualisiertValue) {
     this.zuletztAktualisiertValue = zuletztAktualisiertValue;
  }

  public TemporalAccessor getZuletztAktualisiertValue() {
     return this.zuletztAktualisiertValue ;
  }
}
