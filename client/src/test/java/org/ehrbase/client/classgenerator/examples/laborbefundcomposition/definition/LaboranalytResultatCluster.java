package org.ehrbase.client.classgenerator.examples.laborbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-04-19T11:36:01.952516+07:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class LaboranalytResultatCluster implements LocatableEntity {
  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Laboranalyt-Resultat/Bezeichnung
   * Description: Die Bezeichnung des Analyt-Resultats
   * Comment: Der Wert dieses Elements wird normalerweise meist durch eine Spezialisierung, durch einer Vorlage oder zur Laufzeit geliefert, um den aktuellen Analyt wiederzugeben. Zum Beispiel: 'Natrium im Serum','Hämoglobin'.
   *                                                         Die Codierung mit einer externen Terminologie, wie LOINC, NPU, SNOMED-CT oder lokalen Labor-Terminologien wird dringend empfohlen.
   */
  @Path("/items[at0024 and name/value='Bezeichnung']/value|value")
  private String bezeichnungValue;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Tree/Laboranalyt-Resultat/Bezeichnung/null_flavour
   */
  @Path("/items[at0024 and name/value='Bezeichnung']/null_flavour|defining_code")
  private NullFlavour bezeichnungNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Tree/Laboranalyt-Resultat/Messwert/null_flavour
   */
  @Path("/items[at0001 and name/value='Messwert']/null_flavour|defining_code")
  private NullFlavour messwertNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Laboranalyt-Resultat/Analyseergebnis-Details
   * Description: Weitere Details zu einem einzelnen Ergebnis.
   */
  @Path("/items[at0014]")
  private List<Cluster> analyseergebnisDetails;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Laboranalyt-Resultat/Referenzbereich Typ
   * Description: Zusätzliche Hinweise zur Anwendbarkeit des Referenzbereichs für dieses Resultat oder (codierter) Text, ob das Resultat im Referenzbereich ist oder nicht.
   * Comment: z.B.: 'im Referenzbereich, bezogen auf Alter und Geschlecht'.
   */
  @Path("/items[at0004 and name/value='Referenzbereich Typ']/value|value")
  private String referenzbereichTypValue;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Tree/Laboranalyt-Resultat/Referenzbereich Typ/null_flavour
   */
  @Path("/items[at0004 and name/value='Referenzbereich Typ']/null_flavour|defining_code")
  private NullFlavour referenzbereichTypNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Laboranalyt-Resultat/Zeitpunkt Validation
   * Description: Datum und Zeit, an dem das Analyseergebnis im Labor medizinisch validiert wurde.
   * Comment: In vielen Gerichtsbarkeiten wird angenommen, dass der 'Ergebnisstatus' die medizinische Validation einschliesst, in anderen wird diese anhand dieses Datenelements separat erfasst und befundet
   */
  @Path("/items[at0025]/value|value")
  private TemporalAccessor zeitpunktValidationValue;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Tree/Laboranalyt-Resultat/Zeitpunkt Validation/null_flavour
   */
  @Path("/items[at0025]/null_flavour|defining_code")
  private NullFlavour zeitpunktValidationNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Tree/Laboranalyt-Resultat/Ergebnis-Status/null_flavour
   */
  @Path("/items[at0005]/null_flavour|defining_code")
  private NullFlavour ergebnisStatusNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Laboranalyt-Resultat/Dokumentationsdatum Untersuchung
   * Description: Datum und/oder Zeitpunkt an dem der Status für das Analyseergebnis gesetzt wurde.
   */
  @Path("/items[at0006 and name/value='Dokumentationsdatum Untersuchung']/value|value")
  private TemporalAccessor dokumentationsdatumUntersuchungValue;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Tree/Laboranalyt-Resultat/Dokumentationsdatum Untersuchung/null_flavour
   */
  @Path("/items[at0006 and name/value='Dokumentationsdatum Untersuchung']/null_flavour|defining_code")
  private NullFlavour dokumentationsdatumUntersuchungNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Tree/Laboranalyt-Resultat/Probe ID/null_flavour
   */
  @Path("/items[at0026 and name/value='Probe ID']/null_flavour|defining_code")
  private NullFlavour probeIdNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Laboranalyt-Resultat/Kommentar
   * Description: Kommentar zum Analyt-Resultat, soweit noch nicht in anderen Feldern erfasst.
   */
  @Path("/items[at0003]/value|value")
  private String kommentarValue;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Tree/Laboranalyt-Resultat/Kommentar/null_flavour
   */
  @Path("/items[at0003]/null_flavour|defining_code")
  private NullFlavour kommentarNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Laboranalyt-Resultat/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Laboranalyt-Resultat/Messwert
   * Description: (Mess-)Wert des Analyt-Resultats.
   */
  @Path("/items[at0001 and name/value='Messwert']/value")
  @Choice
  private LaboranalytResultatMesswertChoice messwert;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Laboranalyt-Resultat/Probe ID
   * Description: Kennung der Probe, die für das Analyseergebnis verwendet wurde.
   */
  @Path("/items[at0026 and name/value='Probe ID']/value")
  @Choice
  private LaboranalytResultatProbeIdChoice probeId;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Laboranalyt-Resultat/Ergebnis-Status
   * Description: Status des Analyseergebnisses.
   */
  @Path("/items[at0005]/value")
  @Choice
  private LaboranalytResultatErgebnisStatusChoice ergebnisStatus;

  public void setBezeichnungValue(String bezeichnungValue) {
     this.bezeichnungValue = bezeichnungValue;
  }

  public String getBezeichnungValue() {
     return this.bezeichnungValue ;
  }

  public void setBezeichnungNullFlavourDefiningCode(
      NullFlavour bezeichnungNullFlavourDefiningCode) {
     this.bezeichnungNullFlavourDefiningCode = bezeichnungNullFlavourDefiningCode;
  }

  public NullFlavour getBezeichnungNullFlavourDefiningCode() {
     return this.bezeichnungNullFlavourDefiningCode ;
  }

  public void setMesswertNullFlavourDefiningCode(NullFlavour messwertNullFlavourDefiningCode) {
     this.messwertNullFlavourDefiningCode = messwertNullFlavourDefiningCode;
  }

  public NullFlavour getMesswertNullFlavourDefiningCode() {
     return this.messwertNullFlavourDefiningCode ;
  }

  public void setAnalyseergebnisDetails(List<Cluster> analyseergebnisDetails) {
     this.analyseergebnisDetails = analyseergebnisDetails;
  }

  public List<Cluster> getAnalyseergebnisDetails() {
     return this.analyseergebnisDetails ;
  }

  public void setReferenzbereichTypValue(String referenzbereichTypValue) {
     this.referenzbereichTypValue = referenzbereichTypValue;
  }

  public String getReferenzbereichTypValue() {
     return this.referenzbereichTypValue ;
  }

  public void setReferenzbereichTypNullFlavourDefiningCode(
      NullFlavour referenzbereichTypNullFlavourDefiningCode) {
     this.referenzbereichTypNullFlavourDefiningCode = referenzbereichTypNullFlavourDefiningCode;
  }

  public NullFlavour getReferenzbereichTypNullFlavourDefiningCode() {
     return this.referenzbereichTypNullFlavourDefiningCode ;
  }

  public void setZeitpunktValidationValue(TemporalAccessor zeitpunktValidationValue) {
     this.zeitpunktValidationValue = zeitpunktValidationValue;
  }

  public TemporalAccessor getZeitpunktValidationValue() {
     return this.zeitpunktValidationValue ;
  }

  public void setZeitpunktValidationNullFlavourDefiningCode(
      NullFlavour zeitpunktValidationNullFlavourDefiningCode) {
     this.zeitpunktValidationNullFlavourDefiningCode = zeitpunktValidationNullFlavourDefiningCode;
  }

  public NullFlavour getZeitpunktValidationNullFlavourDefiningCode() {
     return this.zeitpunktValidationNullFlavourDefiningCode ;
  }

  public void setErgebnisStatusNullFlavourDefiningCode(
      NullFlavour ergebnisStatusNullFlavourDefiningCode) {
     this.ergebnisStatusNullFlavourDefiningCode = ergebnisStatusNullFlavourDefiningCode;
  }

  public NullFlavour getErgebnisStatusNullFlavourDefiningCode() {
     return this.ergebnisStatusNullFlavourDefiningCode ;
  }

  public void setDokumentationsdatumUntersuchungValue(
      TemporalAccessor dokumentationsdatumUntersuchungValue) {
     this.dokumentationsdatumUntersuchungValue = dokumentationsdatumUntersuchungValue;
  }

  public TemporalAccessor getDokumentationsdatumUntersuchungValue() {
     return this.dokumentationsdatumUntersuchungValue ;
  }

  public void setDokumentationsdatumUntersuchungNullFlavourDefiningCode(
      NullFlavour dokumentationsdatumUntersuchungNullFlavourDefiningCode) {
     this.dokumentationsdatumUntersuchungNullFlavourDefiningCode = dokumentationsdatumUntersuchungNullFlavourDefiningCode;
  }

  public NullFlavour getDokumentationsdatumUntersuchungNullFlavourDefiningCode() {
     return this.dokumentationsdatumUntersuchungNullFlavourDefiningCode ;
  }

  public void setProbeIdNullFlavourDefiningCode(NullFlavour probeIdNullFlavourDefiningCode) {
     this.probeIdNullFlavourDefiningCode = probeIdNullFlavourDefiningCode;
  }

  public NullFlavour getProbeIdNullFlavourDefiningCode() {
     return this.probeIdNullFlavourDefiningCode ;
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

  public void setMesswert(LaboranalytResultatMesswertChoice messwert) {
     this.messwert = messwert;
  }

  public LaboranalytResultatMesswertChoice getMesswert() {
     return this.messwert ;
  }

  public void setProbeId(LaboranalytResultatProbeIdChoice probeId) {
     this.probeId = probeId;
  }

  public LaboranalytResultatProbeIdChoice getProbeId() {
     return this.probeId ;
  }

  public void setErgebnisStatus(LaboranalytResultatErgebnisStatusChoice ergebnisStatus) {
     this.ergebnisStatus = ergebnisStatus;
  }

  public LaboranalytResultatErgebnisStatusChoice getErgebnisStatus() {
     return this.ergebnisStatus ;
  }
}
