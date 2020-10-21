package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-ADMIN_ENTRY.hospitalization.v0")
public class VersorgungsortAdminEntry {
  @Path("/data[at0001]/items[at0004]/value|value")
  private TemporalAccessor beginnValue;

  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.location.v1]")
  private StandortCluster standort;

  @Path("/data[at0001]/items[at0005]/value|value")
  private TemporalAccessor endeValue;

  @Path("/data[at0001]/items[at0006]/value|value")
  private String grundDesAufenthaltesValue;

  @Path("/language")
  private Language language;

  @Path("/data[at0001]/items[at0009]/value|value")
  private String kommentarValue;

  @Path("/subject")
  private PartyProxy subject;

  public void setBeginnValue(TemporalAccessor beginnValue) {
     this.beginnValue = beginnValue;
  }

  public TemporalAccessor getBeginnValue() {
     return this.beginnValue ;
  }

  public void setStandort(StandortCluster standort) {
     this.standort = standort;
  }

  public StandortCluster getStandort() {
     return this.standort ;
  }

  public void setEndeValue(TemporalAccessor endeValue) {
     this.endeValue = endeValue;
  }

  public TemporalAccessor getEndeValue() {
     return this.endeValue ;
  }

  public void setGrundDesAufenthaltesValue(String grundDesAufenthaltesValue) {
     this.grundDesAufenthaltesValue = grundDesAufenthaltesValue;
  }

  public String getGrundDesAufenthaltesValue() {
     return this.grundDesAufenthaltesValue ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }
}
