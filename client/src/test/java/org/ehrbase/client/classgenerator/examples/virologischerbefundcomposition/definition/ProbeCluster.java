package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.specimen.v1")
public class ProbeCluster {
  @Path("/items[at0029]/value|value")
  private String probenartValue;

  @Path("/items[at0001]/value")
  private DvIdentifier laborprobenidentifikator;

  @Path("/items[at0088]/value")
  private DvIdentifier externerIdentifikator;

  @Path("/items[at0034]/value|value")
  private TemporalAccessor zeitpunktDesProbeneingangsValue;

  @Path("/items[at0027]")
  private List<Cluster> physischeEigenschaften;

  @Path("/items[at0079]/value|value")
  private String kommentarDesProbennehmersValue;

  @Path("/items[at0087]/value|value")
  private String probenentnahmestelleValue;

  @Path("/items[openEHR-EHR-CLUSTER.anatomical_location.v1]")
  private AnatomischeLokalisationCluster anatomischeLokalisation;

  @Path("/items[at0015]/value|value")
  private TemporalAccessor zeitpunktDerProbenentnahmeValue;

  @Path("/items[at0070]/value")
  private DvIdentifier identifikatorDesProbennehmers;

  @Path("/items[at0071]")
  private List<Cluster> angabenZumProbennehmer;

  @Path("/items[at0083]")
  private List<Cluster> zusatzlicheAngabenZurProbennahme;

  @Path("/items[at0085]")
  private List<Cluster> behalterDetails;

  @Path("/items[at0068]")
  private List<Cluster> angabenZurVerarbeitung;

  @Path("/items[at0093]")
  private List<Cluster> angabenZumTransport;

  @Path("/items[at0096]")
  private List<Cluster> digitaleDarstellung;

  @Path("/items[at0003]/value")
  private DvIdentifier identifikatorDerUbergeordnetenProbe;

  @Path("/items[at0045]/value|value")
  private String kommentarValue;

  public void setProbenartValue(String probenartValue) {
     this.probenartValue = probenartValue;
  }

  public String getProbenartValue() {
     return this.probenartValue ;
  }

  public void setLaborprobenidentifikator(DvIdentifier laborprobenidentifikator) {
     this.laborprobenidentifikator = laborprobenidentifikator;
  }

  public DvIdentifier getLaborprobenidentifikator() {
     return this.laborprobenidentifikator ;
  }

  public void setExternerIdentifikator(DvIdentifier externerIdentifikator) {
     this.externerIdentifikator = externerIdentifikator;
  }

  public DvIdentifier getExternerIdentifikator() {
     return this.externerIdentifikator ;
  }

  public void setZeitpunktDesProbeneingangsValue(TemporalAccessor zeitpunktDesProbeneingangsValue) {
     this.zeitpunktDesProbeneingangsValue = zeitpunktDesProbeneingangsValue;
  }

  public TemporalAccessor getZeitpunktDesProbeneingangsValue() {
     return this.zeitpunktDesProbeneingangsValue ;
  }

  public void setPhysischeEigenschaften(List<Cluster> physischeEigenschaften) {
     this.physischeEigenschaften = physischeEigenschaften;
  }

  public List<Cluster> getPhysischeEigenschaften() {
     return this.physischeEigenschaften ;
  }

  public void setKommentarDesProbennehmersValue(String kommentarDesProbennehmersValue) {
     this.kommentarDesProbennehmersValue = kommentarDesProbennehmersValue;
  }

  public String getKommentarDesProbennehmersValue() {
     return this.kommentarDesProbennehmersValue ;
  }

  public void setProbenentnahmestelleValue(String probenentnahmestelleValue) {
     this.probenentnahmestelleValue = probenentnahmestelleValue;
  }

  public String getProbenentnahmestelleValue() {
     return this.probenentnahmestelleValue ;
  }

  public void setAnatomischeLokalisation(AnatomischeLokalisationCluster anatomischeLokalisation) {
     this.anatomischeLokalisation = anatomischeLokalisation;
  }

  public AnatomischeLokalisationCluster getAnatomischeLokalisation() {
     return this.anatomischeLokalisation ;
  }

  public void setZeitpunktDerProbenentnahmeValue(TemporalAccessor zeitpunktDerProbenentnahmeValue) {
     this.zeitpunktDerProbenentnahmeValue = zeitpunktDerProbenentnahmeValue;
  }

  public TemporalAccessor getZeitpunktDerProbenentnahmeValue() {
     return this.zeitpunktDerProbenentnahmeValue ;
  }

  public void setIdentifikatorDesProbennehmers(DvIdentifier identifikatorDesProbennehmers) {
     this.identifikatorDesProbennehmers = identifikatorDesProbennehmers;
  }

  public DvIdentifier getIdentifikatorDesProbennehmers() {
     return this.identifikatorDesProbennehmers ;
  }

  public void setAngabenZumProbennehmer(List<Cluster> angabenZumProbennehmer) {
     this.angabenZumProbennehmer = angabenZumProbennehmer;
  }

  public List<Cluster> getAngabenZumProbennehmer() {
     return this.angabenZumProbennehmer ;
  }

  public void setZusatzlicheAngabenZurProbennahme(List<Cluster> zusatzlicheAngabenZurProbennahme) {
     this.zusatzlicheAngabenZurProbennahme = zusatzlicheAngabenZurProbennahme;
  }

  public List<Cluster> getZusatzlicheAngabenZurProbennahme() {
     return this.zusatzlicheAngabenZurProbennahme ;
  }

  public void setBehalterDetails(List<Cluster> behalterDetails) {
     this.behalterDetails = behalterDetails;
  }

  public List<Cluster> getBehalterDetails() {
     return this.behalterDetails ;
  }

  public void setAngabenZurVerarbeitung(List<Cluster> angabenZurVerarbeitung) {
     this.angabenZurVerarbeitung = angabenZurVerarbeitung;
  }

  public List<Cluster> getAngabenZurVerarbeitung() {
     return this.angabenZurVerarbeitung ;
  }

  public void setAngabenZumTransport(List<Cluster> angabenZumTransport) {
     this.angabenZumTransport = angabenZumTransport;
  }

  public List<Cluster> getAngabenZumTransport() {
     return this.angabenZumTransport ;
  }

  public void setDigitaleDarstellung(List<Cluster> digitaleDarstellung) {
     this.digitaleDarstellung = digitaleDarstellung;
  }

  public List<Cluster> getDigitaleDarstellung() {
     return this.digitaleDarstellung ;
  }

  public void setIdentifikatorDerUbergeordnetenProbe(
      DvIdentifier identifikatorDerUbergeordnetenProbe) {
     this.identifikatorDerUbergeordnetenProbe = identifikatorDerUbergeordnetenProbe;
  }

  public DvIdentifier getIdentifikatorDerUbergeordnetenProbe() {
     return this.identifikatorDerUbergeordnetenProbe ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
  }
}
