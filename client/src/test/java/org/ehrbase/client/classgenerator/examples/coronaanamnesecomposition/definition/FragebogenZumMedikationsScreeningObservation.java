package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.medication_use.v0")
public class FragebogenZumMedikationsScreeningObservation {
  @Path("/data[at0022]/events[at0023]/data[at0001]/items[at0027]/value|defining_code")
  private CodePhrase medikamenteInVerwendungDefiningCode;

  @Path("/data[at0022]/events[at0023]/data[at0001]/items[at0026]/items[at0002]/value|value")
  private String nameDerMedikamentenklasseValue;

  @Path("/data[at0022]/events[at0023]/data[at0001]/items[at0026]/items[at0003]/value|defining_code")
  private MedikamentenklasseInVerwendungDefiningCode medikamentenklasseInVerwendungDefiningCode;

  @Path("/data[at0022]/events[at0023]/data[at0001]/items[at0026]/items[at0008]")
  private List<FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster> spezifischeMedikamente;

  @Path("/data[at0022]/events[at0023]/time|value")
  private TemporalAccessor timeValue;

  @Path("/data[at0022]/origin|value")
  private TemporalAccessor originValue;

  @Path("/protocol[at0005]/items[at0019]")
  private List<Cluster> erweiterung;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/language")
  private Language language;

  public void setMedikamenteInVerwendungDefiningCode(
      CodePhrase medikamenteInVerwendungDefiningCode) {
     this.medikamenteInVerwendungDefiningCode = medikamenteInVerwendungDefiningCode;
  }

  public CodePhrase getMedikamenteInVerwendungDefiningCode() {
     return this.medikamenteInVerwendungDefiningCode ;
  }

  public void setNameDerMedikamentenklasseValue(String nameDerMedikamentenklasseValue) {
     this.nameDerMedikamentenklasseValue = nameDerMedikamentenklasseValue;
  }

  public String getNameDerMedikamentenklasseValue() {
     return this.nameDerMedikamentenklasseValue ;
  }

  public void setMedikamentenklasseInVerwendungDefiningCode(
      MedikamentenklasseInVerwendungDefiningCode medikamentenklasseInVerwendungDefiningCode) {
     this.medikamentenklasseInVerwendungDefiningCode = medikamentenklasseInVerwendungDefiningCode;
  }

  public MedikamentenklasseInVerwendungDefiningCode getMedikamentenklasseInVerwendungDefiningCode(
      ) {
     return this.medikamentenklasseInVerwendungDefiningCode ;
  }

  public void setSpezifischeMedikamente(
      List<FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster> spezifischeMedikamente) {
     this.spezifischeMedikamente = spezifischeMedikamente;
  }

  public List<FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster> getSpezifischeMedikamente(
      ) {
     return this.spezifischeMedikamente ;
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
