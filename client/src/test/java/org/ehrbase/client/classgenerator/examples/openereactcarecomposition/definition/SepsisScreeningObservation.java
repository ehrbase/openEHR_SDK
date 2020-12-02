package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.sepsis_screening_tool.v0")
public class SepsisScreeningObservation {
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006]")
  private List<SepsisScreeningRiskFactorsForSepsisElement> riskFactorsForSepsis;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0011]")
  private List<SepsisScreeningLikelySourceOfInfectionElement> likelySourceOfInfection;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0058]")
  private List<SepsisScreeningRedFlagAcuteElement> redFlagAcute;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0083]")
  private List<SepsisScreeningAmberFlagAcuteElement> amberFlagAcute;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0036]")
  private List<SepsisScreening999FlagElement> N999Flag;

  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  @Path("/protocol[at0004]/items[at0005]")
  private List<Cluster> extension;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/language")
  private Language language;

  public void setRiskFactorsForSepsis(
      List<SepsisScreeningRiskFactorsForSepsisElement> riskFactorsForSepsis) {
     this.riskFactorsForSepsis = riskFactorsForSepsis;
  }

  public List<SepsisScreeningRiskFactorsForSepsisElement> getRiskFactorsForSepsis() {
     return this.riskFactorsForSepsis ;
  }

  public void setLikelySourceOfInfection(
      List<SepsisScreeningLikelySourceOfInfectionElement> likelySourceOfInfection) {
     this.likelySourceOfInfection = likelySourceOfInfection;
  }

  public List<SepsisScreeningLikelySourceOfInfectionElement> getLikelySourceOfInfection() {
     return this.likelySourceOfInfection ;
  }

  public void setRedFlagAcute(List<SepsisScreeningRedFlagAcuteElement> redFlagAcute) {
     this.redFlagAcute = redFlagAcute;
  }

  public List<SepsisScreeningRedFlagAcuteElement> getRedFlagAcute() {
     return this.redFlagAcute ;
  }

  public void setAmberFlagAcute(List<SepsisScreeningAmberFlagAcuteElement> amberFlagAcute) {
     this.amberFlagAcute = amberFlagAcute;
  }

  public List<SepsisScreeningAmberFlagAcuteElement> getAmberFlagAcute() {
     return this.amberFlagAcute ;
  }

  public void setN999Flag(List<SepsisScreening999FlagElement> N999Flag) {
     this.N999Flag = N999Flag;
  }

  public List<SepsisScreening999FlagElement> getN999Flag() {
     return this.N999Flag ;
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

  public void setExtension(List<Cluster> extension) {
     this.extension = extension;
  }

  public List<Cluster> getExtension() {
     return this.extension ;
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
