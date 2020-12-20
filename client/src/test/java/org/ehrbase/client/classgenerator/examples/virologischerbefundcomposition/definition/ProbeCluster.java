package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.specimen.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:13.763034400+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class ProbeCluster implements LocatableEntity {
  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Probenart Description: Die Art der
   * Probe.
   */
  @Path("/items[at0029]/value|value")
  private String probenartValue;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Laborprobenidentifikator Description:
   * Eine eindeutige Kennung der Probe, die normalerweise vom Labor vergeben wird.
   */
  @Path("/items[at0001]/value")
  private DvIdentifier laborprobenidentifikator;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Externer Identifikator Description: Eine
   * eindeutige Kennung der Probe, die von einer Organisation außerhalb des Labors wie dem
   * Auftraggeber zugeordnet wurde.
   */
  @Path("/items[at0088]/value")
  private DvIdentifier externerIdentifikator;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Zeitpunkt des Probeneingangs
   * Description: Datum und Uhrzeit des Eingangs der Probe im Labor.
   */
  @Path("/items[at0034]/value|value")
  private TemporalAccessor zeitpunktDesProbeneingangsValue;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Physische Eigenschaften Description:
   * Physische Größen, Masse oder nicht messbare Eigenschaften der uentnehmenden Probe. Comment: Zum
   * Beispiel: Volumen, Masse, Umfang, Farbe, Geruch, Trübung. Mit diesem Element können die
   * Eigenschaften der zu entnehmenden Probe im Kontext eines INSTRUCTION-Archetyps oder die
   * Eigenschaften des zu entnehmenden Probe im Kontext eines ACTION- oder OBSERVATION-Archetyps
   * angegeben werden. Beispielsweise kann eine INSTRUCTION die Entnahme von 20 ml Blut verlangen,
   * während die entsprechende ACTION aufzeichnet, dass nur 15 ml entnommen wurden.
   */
  @Path("/items[at0027]")
  private List<Cluster> physischeEigenschaften;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Kommentar des Probennehmers Description:
   * Ergänzende Beschreibung zur Probenentnahme.
   */
  @Path("/items[at0079]/value|value")
  private String kommentarDesProbennehmersValue;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Probenentnahmestelle Description:
   * Identifizierung der Entnahmestelle, an der die Probe entnommen wurde.
   */
  @Path("/items[at0087]/value|value")
  private String probenentnahmestelleValue;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Anatomische Lokalisation Description:
   * Eine physische Stelle am oder innerhalb des menschlichen Körpers.
   */
  @Path("/items[openEHR-EHR-CLUSTER.anatomical_location.v1]")
  private AnatomischeLokalisationCluster anatomischeLokalisation;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Zeitpunkt der Probenentnahme
   * Description: Das Datum und die Uhrzeit, zu der die Probennahme angeordnet wurde oder stattfand.
   */
  @Path("/items[at0015]/value|value")
  private TemporalAccessor zeitpunktDerProbenentnahmeValue;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Identifikator des Probennehmers
   * Description: Die Person oder Organisation die für die Entnahme der Probe verantwortlich war.
   */
  @Path("/items[at0070]/value")
  private DvIdentifier identifikatorDesProbennehmers;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Angaben zum Probennehmer Description:
   * Die Person oder Organisation, die für die Probennahme verantwortlich ist.
   */
  @Path("/items[at0071]")
  private List<Cluster> angabenZumProbennehmer;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Zusätzliche Angaben zur Probennahme
   * Description: Zusätzliche Angaben zu bestimmten Methoden der Probennahme. Comment: Zum Beispiel
   * Details zu Nadelbiopsien bei Prostatakrebs, bei denen sowohl die Anforderung als auch die
   * Beschreibung über die Probe detailliert und spezifisch sind.
   */
  @Path("/items[at0083]")
  private List<Cluster> zusatzlicheAngabenZurProbennahme;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Behälter Details Description: Details
   * über Behälter, der/die verwendet wurde/n.
   */
  @Path("/items[at0085]")
  private List<Cluster> behalterDetails;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Angaben zur Verarbeitung Description:
   * Einzelheiten zu einem normalerweise im Labor durchgeführten Vorbereitungs- oder
   * Verarbeitungsschritt. Comment: Zum Beispiel: Färbung oder Fixierung.
   */
  @Path("/items[at0068]")
  private List<Cluster> angabenZurVerarbeitung;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Angaben zum Transport Description:
   * Angaben zum Transport der Probe.
   */
  @Path("/items[at0093]")
  private List<Cluster> angabenZumTransport;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Digitale Darstellung Description:
   * Strukturierte Details über eine digitale Darstellung des Präparates. Comment: Zum Beispiel das
   * gescannte Bild eines Histopathologie-Dias.
   */
  @Path("/items[at0096]")
  private List<Cluster> digitaleDarstellung;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Identifikator der übergeordneten Probe
   * Description: Eindeutige Kennung der verwandten Probe(n), bei der die Probe in Teilproben
   * aufgeteilt ist.
   */
  @Path("/items[at0003]/value")
  private DvIdentifier identifikatorDerUbergeordnetenProbe;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Kommentar Description: Zusätzliche
   * Beschreibungen der Probe, die nicht in anderen Feldern abgebildet werden können.
   */
  @Path("/items[at0045]/value|value")
  private String kommentarValue;

  /** Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setProbenartValue(String probenartValue) {
    this.probenartValue = probenartValue;
  }

  public String getProbenartValue() {
    return this.probenartValue;
  }

  public void setLaborprobenidentifikator(DvIdentifier laborprobenidentifikator) {
    this.laborprobenidentifikator = laborprobenidentifikator;
  }

  public DvIdentifier getLaborprobenidentifikator() {
    return this.laborprobenidentifikator;
  }

  public void setExternerIdentifikator(DvIdentifier externerIdentifikator) {
    this.externerIdentifikator = externerIdentifikator;
  }

  public DvIdentifier getExternerIdentifikator() {
    return this.externerIdentifikator;
  }

  public void setZeitpunktDesProbeneingangsValue(TemporalAccessor zeitpunktDesProbeneingangsValue) {
    this.zeitpunktDesProbeneingangsValue = zeitpunktDesProbeneingangsValue;
  }

  public TemporalAccessor getZeitpunktDesProbeneingangsValue() {
    return this.zeitpunktDesProbeneingangsValue;
  }

  public void setPhysischeEigenschaften(List<Cluster> physischeEigenschaften) {
    this.physischeEigenschaften = physischeEigenschaften;
  }

  public List<Cluster> getPhysischeEigenschaften() {
    return this.physischeEigenschaften;
  }

  public void setKommentarDesProbennehmersValue(String kommentarDesProbennehmersValue) {
    this.kommentarDesProbennehmersValue = kommentarDesProbennehmersValue;
  }

  public String getKommentarDesProbennehmersValue() {
    return this.kommentarDesProbennehmersValue;
  }

  public void setProbenentnahmestelleValue(String probenentnahmestelleValue) {
    this.probenentnahmestelleValue = probenentnahmestelleValue;
  }

  public String getProbenentnahmestelleValue() {
    return this.probenentnahmestelleValue;
  }

  public void setAnatomischeLokalisation(AnatomischeLokalisationCluster anatomischeLokalisation) {
    this.anatomischeLokalisation = anatomischeLokalisation;
  }

  public AnatomischeLokalisationCluster getAnatomischeLokalisation() {
    return this.anatomischeLokalisation;
  }

  public void setZeitpunktDerProbenentnahmeValue(TemporalAccessor zeitpunktDerProbenentnahmeValue) {
    this.zeitpunktDerProbenentnahmeValue = zeitpunktDerProbenentnahmeValue;
  }

  public TemporalAccessor getZeitpunktDerProbenentnahmeValue() {
    return this.zeitpunktDerProbenentnahmeValue;
  }

  public void setIdentifikatorDesProbennehmers(DvIdentifier identifikatorDesProbennehmers) {
    this.identifikatorDesProbennehmers = identifikatorDesProbennehmers;
  }

  public DvIdentifier getIdentifikatorDesProbennehmers() {
    return this.identifikatorDesProbennehmers;
  }

  public void setAngabenZumProbennehmer(List<Cluster> angabenZumProbennehmer) {
    this.angabenZumProbennehmer = angabenZumProbennehmer;
  }

  public List<Cluster> getAngabenZumProbennehmer() {
    return this.angabenZumProbennehmer;
  }

  public void setZusatzlicheAngabenZurProbennahme(List<Cluster> zusatzlicheAngabenZurProbennahme) {
    this.zusatzlicheAngabenZurProbennahme = zusatzlicheAngabenZurProbennahme;
  }

  public List<Cluster> getZusatzlicheAngabenZurProbennahme() {
    return this.zusatzlicheAngabenZurProbennahme;
  }

  public void setBehalterDetails(List<Cluster> behalterDetails) {
    this.behalterDetails = behalterDetails;
  }

  public List<Cluster> getBehalterDetails() {
    return this.behalterDetails;
  }

  public void setAngabenZurVerarbeitung(List<Cluster> angabenZurVerarbeitung) {
    this.angabenZurVerarbeitung = angabenZurVerarbeitung;
  }

  public List<Cluster> getAngabenZurVerarbeitung() {
    return this.angabenZurVerarbeitung;
  }

  public void setAngabenZumTransport(List<Cluster> angabenZumTransport) {
    this.angabenZumTransport = angabenZumTransport;
  }

  public List<Cluster> getAngabenZumTransport() {
    return this.angabenZumTransport;
  }

  public void setDigitaleDarstellung(List<Cluster> digitaleDarstellung) {
    this.digitaleDarstellung = digitaleDarstellung;
  }

  public List<Cluster> getDigitaleDarstellung() {
    return this.digitaleDarstellung;
  }

  public void setIdentifikatorDerUbergeordnetenProbe(
      DvIdentifier identifikatorDerUbergeordnetenProbe) {
    this.identifikatorDerUbergeordnetenProbe = identifikatorDerUbergeordnetenProbe;
  }

  public DvIdentifier getIdentifikatorDerUbergeordnetenProbe() {
    return this.identifikatorDerUbergeordnetenProbe;
  }

  public void setKommentarValue(String kommentarValue) {
    this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
    return this.kommentarValue;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
