package laborbefund.laborbefundcomposition.definition;

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
public class ProbeCluster2 {
  @Path("/items[at0079]/value|value")
  private String kommentarDesProbennehmersValue;

  @Path("/items[at0071]")
  private List<Cluster> angabenZumProbennehmer;

  @Path("/items[at0093]")
  private List<Cluster> angabenZumTransport;

  @Path("/items[at0087]/value|value")
  private String probenentnahmestelleValue;

  @Path("/items[at0083]")
  private List<Cluster> zusatzlicheAngabenZurProbennahme;

  @Path("/items[at0085]")
  private List<Cluster> behalterDetails;

  @Path("/items[at0096]")
  private List<Cluster> digitaleDarstellung;

  @Path("/items[at0003]/value")
  private DvIdentifier identifikatorDerUbergeordnetenProbe;

  @Path("/items[at0034]/value|value")
  private TemporalAccessor zeitpunktDesProbeneingangsValue;

  @Path("/items[at0045]/value|value")
  private String kommentarValue;

  @Path("/items[at0034]/name|value")
  private String zeitpunktDesProbeneingangsValueName;

  @Path("/items[at0015]/name|value")
  private String zeitpunktDerProbenentnahmeValue;

  @Path("/items[at0029]/value|value")
  private String probenartValue;

  @Path("/items[at0001]/value")
  private DvIdentifier laborprobenidentifikator;

  @Path("/items[at0013]")
  private List<Cluster> anatomischeProbenentnahmestelle;

  @Path("/items[at0068]")
  private List<Cluster> angabenZurVerarbeitung;

  @Path("/items[at0027]")
  private List<Cluster> physischeEigenschaften;

  @Path("/items[at0015]/value|value")
  private TemporalAccessor zeitpunktDerProbenentnahmeValueValue;

  public void setKommentarDesProbennehmersValue(String kommentarDesProbennehmersValue) {
     this.kommentarDesProbennehmersValue = kommentarDesProbennehmersValue;
  }

  public String getKommentarDesProbennehmersValue() {
     return this.kommentarDesProbennehmersValue ;
  }

  public void setAngabenZumProbennehmer(List<Cluster> angabenZumProbennehmer) {
     this.angabenZumProbennehmer = angabenZumProbennehmer;
  }

  public List<Cluster> getAngabenZumProbennehmer() {
     return this.angabenZumProbennehmer ;
  }

  public void setAngabenZumTransport(List<Cluster> angabenZumTransport) {
     this.angabenZumTransport = angabenZumTransport;
  }

  public List<Cluster> getAngabenZumTransport() {
     return this.angabenZumTransport ;
  }

  public void setProbenentnahmestelleValue(String probenentnahmestelleValue) {
     this.probenentnahmestelleValue = probenentnahmestelleValue;
  }

  public String getProbenentnahmestelleValue() {
     return this.probenentnahmestelleValue ;
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

  public void setZeitpunktDesProbeneingangsValue(TemporalAccessor zeitpunktDesProbeneingangsValue) {
     this.zeitpunktDesProbeneingangsValue = zeitpunktDesProbeneingangsValue;
  }

  public TemporalAccessor getZeitpunktDesProbeneingangsValue() {
     return this.zeitpunktDesProbeneingangsValue ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
  }

  public void setZeitpunktDesProbeneingangsValueName(String zeitpunktDesProbeneingangsValueName) {
     this.zeitpunktDesProbeneingangsValueName = zeitpunktDesProbeneingangsValueName;
  }

  public String getZeitpunktDesProbeneingangsValueName() {
     return this.zeitpunktDesProbeneingangsValueName ;
  }

  public void setZeitpunktDerProbenentnahmeValue(String zeitpunktDerProbenentnahmeValue) {
     this.zeitpunktDerProbenentnahmeValue = zeitpunktDerProbenentnahmeValue;
  }

  public String getZeitpunktDerProbenentnahmeValue() {
     return this.zeitpunktDerProbenentnahmeValue ;
  }

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

  public void setAnatomischeProbenentnahmestelle(List<Cluster> anatomischeProbenentnahmestelle) {
     this.anatomischeProbenentnahmestelle = anatomischeProbenentnahmestelle;
  }

  public List<Cluster> getAnatomischeProbenentnahmestelle() {
     return this.anatomischeProbenentnahmestelle ;
  }

  public void setAngabenZurVerarbeitung(List<Cluster> angabenZurVerarbeitung) {
     this.angabenZurVerarbeitung = angabenZurVerarbeitung;
  }

  public List<Cluster> getAngabenZurVerarbeitung() {
     return this.angabenZurVerarbeitung ;
  }

  public void setPhysischeEigenschaften(List<Cluster> physischeEigenschaften) {
     this.physischeEigenschaften = physischeEigenschaften;
  }

  public List<Cluster> getPhysischeEigenschaften() {
     return this.physischeEigenschaften ;
  }

  public void setZeitpunktDerProbenentnahmeValueValue(
      TemporalAccessor zeitpunktDerProbenentnahmeValueValue) {
     this.zeitpunktDerProbenentnahmeValueValue = zeitpunktDerProbenentnahmeValueValue;
  }

  public TemporalAccessor getZeitpunktDerProbenentnahmeValueValue() {
     return this.zeitpunktDerProbenentnahmeValueValue ;
  }
}
