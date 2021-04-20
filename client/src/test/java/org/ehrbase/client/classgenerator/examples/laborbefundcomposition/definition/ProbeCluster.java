package org.ehrbase.client.classgenerator.examples.laborbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.specimen.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-04-19T11:36:01.910237+07:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class ProbeCluster implements LocatableEntity {
  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Probenart
   * Description: Die Art der Probe.
   * Comment: Zum Beispiel: Venöses Blut, Bakterienkultur, Zytologie oder Prostatabiopsie. Nach Möglichkeit wird die Kodierung der Probenart mit einer Terminologie bevorzugt.
   */
  @Path("/items[at0029]/value|value")
  private String probenartValue;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Tree/Probe/Probenart/null_flavour
   */
  @Path("/items[at0029]/null_flavour|defining_code")
  private NullFlavour probenartNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Laborprobenidentifikator
   * Description: Eine eindeutige Kennung der Probe, die normalerweise vom Labor vergeben wird.
   * Comment: Wird manchmal als "Accession Identifier" bezeichnet. Probenbehälter, z. B. Vakuumflaschen oder Gewebekassetten, haben ihre eigenen Kennungen, die im Element "Container identifier" des Archetyps "Probenbehälter" eingetragen werden können.
   */
  @Path("/items[at0001]/value")
  private DvIdentifier laborprobenidentifikator;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Tree/Probe/Laborprobenidentifikator/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour laborprobenidentifikatorNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Laboreingang
   * Description: Datum und Uhrzeit des Eingangs der Probe im Labor.
   */
  @Path("/items[at0034 and name/value='Laboreingang']/value|value")
  private TemporalAccessor laboreingangValue;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Tree/Probe/Laboreingang/null_flavour
   */
  @Path("/items[at0034 and name/value='Laboreingang']/null_flavour|defining_code")
  private NullFlavour laboreingangNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Physische Eigenschaften
   * Description: Physische Größen, Masse oder nicht messbare Eigenschaften der uentnehmenden Probe.
   * Comment: Zum Beispiel: Volumen, Masse, Umfang, Farbe, Geruch, Trübung. Mit diesem Element können die Eigenschaften der zu entnehmenden Probe im Kontext eines INSTRUCTION-Archetyps oder die Eigenschaften des zu entnehmenden Probe im Kontext eines ACTION- oder OBSERVATION-Archetyps angegeben werden. Beispielsweise kann eine INSTRUCTION die Entnahme von 20 ml Blut verlangen, während die entsprechende ACTION aufzeichnet, dass nur 15 ml entnommen wurden.
   */
  @Path("/items[at0027]")
  private List<Cluster> physischeEigenschaften;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Kommentar des Probennehmers
   * Description: Ergänzende Beschreibung zur Probenentnahme.
   */
  @Path("/items[at0079]/value|value")
  private String kommentarDesProbennehmersValue;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Tree/Probe/Kommentar des Probennehmers/null_flavour
   */
  @Path("/items[at0079]/null_flavour|defining_code")
  private NullFlavour kommentarDesProbennehmersNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Probenentnahmestelle
   * Description: Identifizierung der Entnahmestelle, an der die Probe entnommen wurde.
   * Comment: Nach Möglichkeit wird die Kodierung des Namens der Probenentnahmestelle mit einer Terminologie bevorzugt. Verwenden Sie dieses Datenelement, um vorkoordinierte Probenentnahmestellen zu erfassen. Wenn die Anforderungen für die Darstellung der Probenentnahmestelle zur Laufzeit der Anwendung bestimmt werden oder diese eine komplexere Modellierung erfordern, z.B. relative Positionen, verwenden Sie in diesem Archetyp den SLOT "Anatomische Entnahmestelle". Wenn die Probenentnahmestelle über vorab koordinierte Kodes in das Element "Probenart" einbezogen wird, wird dieses Datenelement überflüssig.
   */
  @Path("/items[at0087]/value|value")
  private String probenentnahmestelleValue;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Tree/Probe/Probenentnahmestelle/null_flavour
   */
  @Path("/items[at0087]/null_flavour|defining_code")
  private NullFlavour probenentnahmestelleNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Anatomische Probenentnahmestelle
   * Description: Strukturierte Angaben zur anatomischen Stelle, an der die Probe entnommen wurde.
   * Comment: Verwenden Sie diesen Archetypen zur Beschreibung strukturierter oder komplexerer anatomischen Stellen oder zur Unterstützung der Aufzeichnung des Entstehungsortes während der Anwendung. Wenn die Anatomische Entnahmestelle über vorab koordinierte Codes in das Element "Entnahmestelle" einbezogen wird, wird die Verwendung dieses SLOTs überflüssig.
   */
  @Path("/items[at0013]")
  private List<Cluster> anatomischeProbenentnahmestelle;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Probenentnahme
   * Description: Das Datum und die Uhrzeit, zu der die Probennahme angeordnet wurde oder stattfand.
   * Comment: Dieser Zeitpunkt wird hauptsächlich in den Zeitangaben, von INSTRUCTION, ACTION oder OBSERVATION erfasst. Da es sich jedoch um eine wichtige Information handelt, kann es nützlich sein, diese auch direkt mit der Probe selbst zu verknüpfen.
   */
  @Path("/items[at0015 and name/value='Probenentnahme']/value|value")
  private TemporalAccessor probenentnahmeValue;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Tree/Probe/Probenentnahme/null_flavour
   */
  @Path("/items[at0015 and name/value='Probenentnahme']/null_flavour|defining_code")
  private NullFlavour probenentnahmeNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Angaben zum Probennehmer
   * Description: Die Person oder Organisation, die für die Probennahme verantwortlich ist.
   */
  @Path("/items[at0071]")
  private List<Cluster> angabenZumProbennehmer;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Zusätzliche Angaben zur Probennahme
   * Description: Zusätzliche Angaben zu bestimmten Methoden der Probennahme.
   * Comment: Zum Beispiel Details zu Nadelbiopsien bei Prostatakrebs, bei denen sowohl die Anforderung als auch die Beschreibung über die Probe detailliert und spezifisch sind.
   */
  @Path("/items[at0083]")
  private List<Cluster> zusaetzlicheAngabenZurProbennahme;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Behälter Details
   * Description: Details über Behälter, der/die verwendet wurde/n.
   */
  @Path("/items[at0085]")
  private List<Cluster> behaelterDetails;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Angaben zur Verarbeitung
   * Description: Einzelheiten zu einem normalerweise im Labor durchgeführten Vorbereitungs- oder Verarbeitungsschritt.
   * Comment: Zum Beispiel: Färbung oder Fixierung.
   */
  @Path("/items[at0068]")
  private List<Cluster> angabenZurVerarbeitung;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Angaben zum Transport
   * Description: Angaben zum Transport der Probe.
   */
  @Path("/items[at0093]")
  private List<Cluster> angabenZumTransport;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Digitale Darstellung
   * Description: Strukturierte Details über eine digitale Darstellung des Präparates.
   * Comment: Zum Beispiel das gescannte Bild eines Histopathologie-Dias.
   */
  @Path("/items[at0096]")
  private List<Cluster> digitaleDarstellung;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Identifikator der übergeordneten Probe
   * Description: Eindeutige Kennung der verwandten Probe(n), bei der die Probe in Teilproben aufgeteilt ist.
   * Comment: Zum Beispiel: eine bestimmte Probe eines Objektträgers für die Histologie würde einen bestimmten Paraffinwachsblock als Ausgangsprobe haben.
   */
  @Path("/items[at0003]/value")
  private DvIdentifier identifikatorDerUebergeordnetenProbe;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Tree/Probe/Identifikator der übergeordneten Probe/null_flavour
   */
  @Path("/items[at0003]/null_flavour|defining_code")
  private NullFlavour identifikatorDerUebergeordnetenProbeNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Kommentar
   * Description: Zusätzliche Beschreibungen der Probe, die nicht in anderen Feldern abgebildet werden können.
   */
  @Path("/items[at0045]/value|value")
  private String kommentarValue;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Tree/Probe/Kommentar/null_flavour
   */
  @Path("/items[at0045]/null_flavour|defining_code")
  private NullFlavour kommentarNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setProbenartValue(String probenartValue) {
     this.probenartValue = probenartValue;
  }

  public String getProbenartValue() {
     return this.probenartValue ;
  }

  public void setProbenartNullFlavourDefiningCode(NullFlavour probenartNullFlavourDefiningCode) {
     this.probenartNullFlavourDefiningCode = probenartNullFlavourDefiningCode;
  }

  public NullFlavour getProbenartNullFlavourDefiningCode() {
     return this.probenartNullFlavourDefiningCode ;
  }

  public void setLaborprobenidentifikator(DvIdentifier laborprobenidentifikator) {
     this.laborprobenidentifikator = laborprobenidentifikator;
  }

  public DvIdentifier getLaborprobenidentifikator() {
     return this.laborprobenidentifikator ;
  }

  public void setLaborprobenidentifikatorNullFlavourDefiningCode(
      NullFlavour laborprobenidentifikatorNullFlavourDefiningCode) {
     this.laborprobenidentifikatorNullFlavourDefiningCode = laborprobenidentifikatorNullFlavourDefiningCode;
  }

  public NullFlavour getLaborprobenidentifikatorNullFlavourDefiningCode() {
     return this.laborprobenidentifikatorNullFlavourDefiningCode ;
  }

  public void setLaboreingangValue(TemporalAccessor laboreingangValue) {
     this.laboreingangValue = laboreingangValue;
  }

  public TemporalAccessor getLaboreingangValue() {
     return this.laboreingangValue ;
  }

  public void setLaboreingangNullFlavourDefiningCode(
      NullFlavour laboreingangNullFlavourDefiningCode) {
     this.laboreingangNullFlavourDefiningCode = laboreingangNullFlavourDefiningCode;
  }

  public NullFlavour getLaboreingangNullFlavourDefiningCode() {
     return this.laboreingangNullFlavourDefiningCode ;
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

  public void setKommentarDesProbennehmersNullFlavourDefiningCode(
      NullFlavour kommentarDesProbennehmersNullFlavourDefiningCode) {
     this.kommentarDesProbennehmersNullFlavourDefiningCode = kommentarDesProbennehmersNullFlavourDefiningCode;
  }

  public NullFlavour getKommentarDesProbennehmersNullFlavourDefiningCode() {
     return this.kommentarDesProbennehmersNullFlavourDefiningCode ;
  }

  public void setProbenentnahmestelleValue(String probenentnahmestelleValue) {
     this.probenentnahmestelleValue = probenentnahmestelleValue;
  }

  public String getProbenentnahmestelleValue() {
     return this.probenentnahmestelleValue ;
  }

  public void setProbenentnahmestelleNullFlavourDefiningCode(
      NullFlavour probenentnahmestelleNullFlavourDefiningCode) {
     this.probenentnahmestelleNullFlavourDefiningCode = probenentnahmestelleNullFlavourDefiningCode;
  }

  public NullFlavour getProbenentnahmestelleNullFlavourDefiningCode() {
     return this.probenentnahmestelleNullFlavourDefiningCode ;
  }

  public void setAnatomischeProbenentnahmestelle(List<Cluster> anatomischeProbenentnahmestelle) {
     this.anatomischeProbenentnahmestelle = anatomischeProbenentnahmestelle;
  }

  public List<Cluster> getAnatomischeProbenentnahmestelle() {
     return this.anatomischeProbenentnahmestelle ;
  }

  public void setProbenentnahmeValue(TemporalAccessor probenentnahmeValue) {
     this.probenentnahmeValue = probenentnahmeValue;
  }

  public TemporalAccessor getProbenentnahmeValue() {
     return this.probenentnahmeValue ;
  }

  public void setProbenentnahmeNullFlavourDefiningCode(
      NullFlavour probenentnahmeNullFlavourDefiningCode) {
     this.probenentnahmeNullFlavourDefiningCode = probenentnahmeNullFlavourDefiningCode;
  }

  public NullFlavour getProbenentnahmeNullFlavourDefiningCode() {
     return this.probenentnahmeNullFlavourDefiningCode ;
  }

  public void setAngabenZumProbennehmer(List<Cluster> angabenZumProbennehmer) {
     this.angabenZumProbennehmer = angabenZumProbennehmer;
  }

  public List<Cluster> getAngabenZumProbennehmer() {
     return this.angabenZumProbennehmer ;
  }

  public void setZusaetzlicheAngabenZurProbennahme(
      List<Cluster> zusaetzlicheAngabenZurProbennahme) {
     this.zusaetzlicheAngabenZurProbennahme = zusaetzlicheAngabenZurProbennahme;
  }

  public List<Cluster> getZusaetzlicheAngabenZurProbennahme() {
     return this.zusaetzlicheAngabenZurProbennahme ;
  }

  public void setBehaelterDetails(List<Cluster> behaelterDetails) {
     this.behaelterDetails = behaelterDetails;
  }

  public List<Cluster> getBehaelterDetails() {
     return this.behaelterDetails ;
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

  public void setIdentifikatorDerUebergeordnetenProbe(
      DvIdentifier identifikatorDerUebergeordnetenProbe) {
     this.identifikatorDerUebergeordnetenProbe = identifikatorDerUebergeordnetenProbe;
  }

  public DvIdentifier getIdentifikatorDerUebergeordnetenProbe() {
     return this.identifikatorDerUebergeordnetenProbe ;
  }

  public void setIdentifikatorDerUebergeordnetenProbeNullFlavourDefiningCode(
      NullFlavour identifikatorDerUebergeordnetenProbeNullFlavourDefiningCode) {
     this.identifikatorDerUebergeordnetenProbeNullFlavourDefiningCode = identifikatorDerUebergeordnetenProbeNullFlavourDefiningCode;
  }

  public NullFlavour getIdentifikatorDerUebergeordnetenProbeNullFlavourDefiningCode() {
     return this.identifikatorDerUebergeordnetenProbeNullFlavourDefiningCode ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
  }

  public void setKommentarNullFlavourDefiningCode(NullFlavour kommentarNullFlavourDefiningCode) {
     this.kommentarNullFlavourDefiningCode = kommentarNullFlavourDefiningCode;
  }

  public NullFlavour getKommentarNullFlavourDefiningCode() {
     return this.kommentarNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
