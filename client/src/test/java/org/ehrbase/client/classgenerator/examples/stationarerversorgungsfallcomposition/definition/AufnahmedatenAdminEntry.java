package org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-ADMIN_ENTRY.admission.v0")
public class AufnahmedatenAdminEntry {
  @Path("/data[at0001]/items[at0132]")
  private List<Cluster> vorherigerPatientenstandort;

  @Path("/language")
  private Language language;

  @Path("/data[at0001]/items[at0049]/value|value")
  private String artDerAufnahmeValue;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/data[at0001]/items[at0131]")
  private List<Cluster> zugewiesenerPatientenstandort;

  @Path("/data[at0001]/items[at0013]/value|value")
  private String versorgungsfallgrundValue;

  @Path("/data[at0001]/items[at0071]/value|value")
  private TemporalAccessor datumUhrzeitDerAufnahmeValue;

  public void setVorherigerPatientenstandort(List<Cluster> vorherigerPatientenstandort) {
     this.vorherigerPatientenstandort = vorherigerPatientenstandort;
  }

  public List<Cluster> getVorherigerPatientenstandort() {
     return this.vorherigerPatientenstandort ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setArtDerAufnahmeValue(String artDerAufnahmeValue) {
     this.artDerAufnahmeValue = artDerAufnahmeValue;
  }

  public String getArtDerAufnahmeValue() {
     return this.artDerAufnahmeValue ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setZugewiesenerPatientenstandort(List<Cluster> zugewiesenerPatientenstandort) {
     this.zugewiesenerPatientenstandort = zugewiesenerPatientenstandort;
  }

  public List<Cluster> getZugewiesenerPatientenstandort() {
     return this.zugewiesenerPatientenstandort ;
  }

  public void setVersorgungsfallgrundValue(String versorgungsfallgrundValue) {
     this.versorgungsfallgrundValue = versorgungsfallgrundValue;
  }

  public String getVersorgungsfallgrundValue() {
     return this.versorgungsfallgrundValue ;
  }

  public void setDatumUhrzeitDerAufnahmeValue(TemporalAccessor datumUhrzeitDerAufnahmeValue) {
     this.datumUhrzeitDerAufnahmeValue = datumUhrzeitDerAufnahmeValue;
  }

  public TemporalAccessor getDatumUhrzeitDerAufnahmeValue() {
     return this.datumUhrzeitDerAufnahmeValue ;
  }
}
