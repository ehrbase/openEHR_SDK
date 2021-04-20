package org.ehrbase.client.classgenerator.examples.laborbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.PointEventEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-04-19T11:36:01.997081+07:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@OptionFor("POINT_EVENT")
public class LaborergebnisJedesEreignisPointEvent implements PointEventEntity, LaborergebnisJedesEreignisChoice {
  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Anforderung
   * Description: Name der Laboruntersuchung, die an der/den Probe(n) durchgeführt wurde.
   * Comment: Ein Laborergebnis kann sich auf ein einzelnes Analyt oder eine Analytgruppe beziehen. Dazu zählen auch komplette Panel an Parametern.
   *                         Es wird dringend empfohlen, die "Labortest-Bezeichnung" anhand einer Terminologie zu kodiereren, wie zum Beispiel LOINC oder SNOMED CT. Beispiel: "Glukose", "Harnstoff", "Abstrich", "Cortisol", "Leberbiopsie". Der Name kann u.U. auch das Probenmaterial oder den Patientenstatus (z.B. "Blutzuckerspiegel nüchtern") oder andere Informationen beinhalten wie "Kalium (Blutgas)".
   */
  @Path("/data[at0003]/items[at0005 and name/value='Anforderung']/value|value")
  private String anforderungValue;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Tree/Anforderung/null_flavour
   */
  @Path("/data[at0003]/items[at0005 and name/value='Anforderung']/null_flavour|defining_code")
  private NullFlavour anforderungNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe
   * Description: Eine physikalische Probe zur Erforschung, Untersuchung oder Analyse, die von einer Person entnommen wurde oder die sich auf die Person bezieht.
   * Comment: Zum Beispiel: Gewebe, Körperflüssigkeit oder Lebensmittel.
   */
  @Path("/data[at0003]/items[openEHR-EHR-CLUSTER.specimen.v1]")
  private List<ProbeCluster> probe;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Gesamt-Untersuchungsstatus
   * Description: Der Status des gesamten Laborprüfergebnisses.
   * Comment: Die Werte wurden so ausgewählt, dass sie mit denen im HL7 FHIR Diagnosebericht übereinstimmen, der historisch aus der HL7v2Praxis stammt. Andere lokale Codes/Begriffe können über den Text "Auswahl" verwendet werden.
   *
   *                         Dieses Element kann mehrfach vorkommen, um Fälle abzudecken, bei denen der Status für verschiedene Aspekte des Ergebnisses in mehrere Elemente unterteilt wurde.
   */
  @Path("/data[at0003]/items[at0073 and name/value='Gesamt-Untersuchungsstatus']/value|defining_code")
  private GesamtUntersuchungsstatusDefiningCode gesamtUntersuchungsstatusDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Tree/Gesamt-Untersuchungsstatus/null_flavour
   */
  @Path("/data[at0003]/items[at0073 and name/value='Gesamt-Untersuchungsstatus']/null_flavour|defining_code")
  private NullFlavour gesamtUntersuchungsstatusNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Laboranalyt-Resultat
   * Description: Ergebnis eines Labortests für einen bestimmten Analytwert.
   * Comment: Beispiele: 'Natrium', 'Leukozytenzahl', 'T3'. Üblicherweise über eine externe Terminologie codiert.
   */
  @Path("/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1]")
  private List<LaboranalytResultatCluster> laboranalytResultat;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Interpretation
   * Description: Beschreibung der wichtigsten Ergebnisse.
   * Comment: Zum Beispiel: "Das Muster lässt auf eine erhebliche Nierenfunktionsstörung schließen". Der Inhalt der Zusammenfassung unterscheidet sich je nach durchgeführter Untersuchung. Diese Zusammenfassung sollte mit der kodierten "Testdiagnose" übereinstimmen.
   */
  @Path("/data[at0003]/items[at0057 and name/value='Interpretation']/value|value")
  private String interpretationValue;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Tree/Interpretation/null_flavour
   */
  @Path("/data[at0003]/items[at0057 and name/value='Interpretation']/null_flavour|defining_code")
  private NullFlavour interpretationNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Strukturierte Testdiagnostik
   * Description: Eine strukturierte oder komplexe Diagnose für die Laboruntersuchung.
   * Comment: Zum Beispiel: Anatomische Pathologiediagnosen, die aus mehreren verschiedenen Schwerpunkten wie Morphologie, Ätiologie und Funktion zusammengesetzt sind.
   */
  @Path("/data[at0003]/items[at0122]")
  private List<Cluster> strukturierteTestdiagnostik;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Multimedia-Darstellung
   * Description: Bild, Video oder Diagramm zur Visualisierung des Testergebnisses.
   * Comment: Mehrere Formate sind erlaubt - diese sollten aber einen äquivalenten klinischen Inhalt darstellen.
   */
  @Path("/data[at0003]/items[at0118]")
  private List<Cluster> multimediaDarstellung;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Kommentar
   * Description: Weitere Informationen über das Laborergebnis, welche bisher nicht in den anderen Feldern erfasst wurden.
   */
  @Path("/data[at0003]/items[at0101]/value|value")
  private String kommentarValue;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Tree/Kommentar/null_flavour
   */
  @Path("/data[at0003]/items[at0101]/null_flavour|defining_code")
  private NullFlavour kommentarNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Strukturierte Erfassung der Störfaktoren
   * Description: Einzelheiten zu Problemen oder Umständen, die sich auf die genaue Interpretation der Messung oder des Prüfergebnisses auswirken.
   * Comment: Zum Beispiel: Letzte normale Menstruationsperiode (LNMP).
   */
  @Path("/state[at0112]/items[at0114]")
  private List<Cluster> strukturierteErfassungDerStoerfaktoren;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  public void setAnforderungValue(String anforderungValue) {
     this.anforderungValue = anforderungValue;
  }

  public String getAnforderungValue() {
     return this.anforderungValue ;
  }

  public void setAnforderungNullFlavourDefiningCode(
      NullFlavour anforderungNullFlavourDefiningCode) {
     this.anforderungNullFlavourDefiningCode = anforderungNullFlavourDefiningCode;
  }

  public NullFlavour getAnforderungNullFlavourDefiningCode() {
     return this.anforderungNullFlavourDefiningCode ;
  }

  public void setProbe(List<ProbeCluster> probe) {
     this.probe = probe;
  }

  public List<ProbeCluster> getProbe() {
     return this.probe ;
  }

  public void setGesamtUntersuchungsstatusDefiningCode(
      GesamtUntersuchungsstatusDefiningCode gesamtUntersuchungsstatusDefiningCode) {
     this.gesamtUntersuchungsstatusDefiningCode = gesamtUntersuchungsstatusDefiningCode;
  }

  public GesamtUntersuchungsstatusDefiningCode getGesamtUntersuchungsstatusDefiningCode() {
     return this.gesamtUntersuchungsstatusDefiningCode ;
  }

  public void setGesamtUntersuchungsstatusNullFlavourDefiningCode(
      NullFlavour gesamtUntersuchungsstatusNullFlavourDefiningCode) {
     this.gesamtUntersuchungsstatusNullFlavourDefiningCode = gesamtUntersuchungsstatusNullFlavourDefiningCode;
  }

  public NullFlavour getGesamtUntersuchungsstatusNullFlavourDefiningCode() {
     return this.gesamtUntersuchungsstatusNullFlavourDefiningCode ;
  }

  public void setLaboranalytResultat(List<LaboranalytResultatCluster> laboranalytResultat) {
     this.laboranalytResultat = laboranalytResultat;
  }

  public List<LaboranalytResultatCluster> getLaboranalytResultat() {
     return this.laboranalytResultat ;
  }

  public void setInterpretationValue(String interpretationValue) {
     this.interpretationValue = interpretationValue;
  }

  public String getInterpretationValue() {
     return this.interpretationValue ;
  }

  public void setInterpretationNullFlavourDefiningCode(
      NullFlavour interpretationNullFlavourDefiningCode) {
     this.interpretationNullFlavourDefiningCode = interpretationNullFlavourDefiningCode;
  }

  public NullFlavour getInterpretationNullFlavourDefiningCode() {
     return this.interpretationNullFlavourDefiningCode ;
  }

  public void setStrukturierteTestdiagnostik(List<Cluster> strukturierteTestdiagnostik) {
     this.strukturierteTestdiagnostik = strukturierteTestdiagnostik;
  }

  public List<Cluster> getStrukturierteTestdiagnostik() {
     return this.strukturierteTestdiagnostik ;
  }

  public void setMultimediaDarstellung(List<Cluster> multimediaDarstellung) {
     this.multimediaDarstellung = multimediaDarstellung;
  }

  public List<Cluster> getMultimediaDarstellung() {
     return this.multimediaDarstellung ;
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

  public void setStrukturierteErfassungDerStoerfaktoren(
      List<Cluster> strukturierteErfassungDerStoerfaktoren) {
     this.strukturierteErfassungDerStoerfaktoren = strukturierteErfassungDerStoerfaktoren;
  }

  public List<Cluster> getStrukturierteErfassungDerStoerfaktoren() {
     return this.strukturierteErfassungDerStoerfaktoren ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }
}
