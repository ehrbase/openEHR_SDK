package org.ehrbase.client.classgenerator.examples.laborbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.laboratory_test_result.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-04-19T11:36:01.857823+07:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class LaborergebnisObservation implements EntryEntity {
  /**
   * Path: Laborbefund/Laborergebnis/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Laborbefund/Laborergebnis/Laborbereich
   * Description: Standort umfasst sowohl beiläufige Orte (ein Ort, der für die medizinische Versorgung ohne vorherige Benennung oder Genehmigung genutzt wird) als auch spezielle, offiziell benannte Orte. Die Standorte können privat, öffentlich, mobil oder stationär sein.
   */
  @Path("/protocol[at0004]/items[openEHR-EHR-CLUSTER.location.v1 and name/value='Laborbereich']")
  private LaborbereichCluster laborbereich;

  /**
   * Path: Laborbefund/Laborergebnis/Details der Testanforderung
   * Description: Details zur Testanforderung.
   * Comment: In den meisten Fällen gibt es eine Testanfrage und ein einzelnes entsprechendes Testergebnis. Jedoch ermöglicht dieser wiederholte Cluster die Situation, dass mehrere Testanfragen mit einem einzigen Testergebnis gemeldet werden können.
   *
   *                         Als Beispiel: "Ein Arzt fordert in einer Anfrage Blutzucker und in einer zweiten Anfrage Harnstoff/Elektrolyte an, aber das Laboranalysegerät führt beides durch und das Labor möchte diese zusammen melden".
   */
  @Path("/protocol[at0004]/items[at0094]")
  private List<LaborergebnisDetailsDerTestanforderungCluster> detailsDerTestanforderung;

  /**
   * Path: Laborbefund/Laborergebnis/Untersuchungsmethode
   * Description: Die Beschreibung der Methode, mit dem der Test durchgeführt wurde.
   * Comment: Wenn möglich, ist eine Kodierung mit einer Terminologie wünschenswert.
   */
  @Path("/protocol[at0004]/items[at0121 and name/value='Untersuchungsmethode']/value|value")
  private String untersuchungsmethodeValue;

  /**
   * Path: Laborbefund/Laborergebnis/Tree/Untersuchungsmethode/null_flavour
   */
  @Path("/protocol[at0004]/items[at0121 and name/value='Untersuchungsmethode']/null_flavour|defining_code")
  private NullFlavour untersuchungsmethodeNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Test Details
   * Description: Strukturierte Details über die beim Labortest verwendete Methodik, das Gerät oder die Auswertung.
   * Comment: Zum Beispiel: "Details der ELISA/Nephelometrie".
   */
  @Path("/protocol[at0004]/items[at0110]")
  private List<Cluster> testDetails;

  /**
   * Path: Laborbefund/Laborergebnis/Erweiterung
   * Description: Weitere Informationen, die erforderlich sind, um lokale Inhalte abzubilden oder das Modell an andere Referenzmodelle anzupassen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten, um ein Mapping auf FHIR oder CIMI Modelle zu ermöglichen.
   */
  @Path("/protocol[at0004]/items[at0117]")
  private List<Cluster> erweiterung;

  /**
   * Path: Laborbefund/Laborergebnis/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Laborbefund/Laborergebnis/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Laborbefund/Laborergebnis/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis
   * Description: Jeder Zeitpunkt oder jedes Intervall, das in einem Template oder zur Laufzeit definiert werden kann.
   */
  @Path("/data[at0001]/events[at0002]")
  @Choice
  private List<LaborergebnisJedesEreignisChoice> jedesEreignis;

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
  }

  public void setLaborbereich(LaborbereichCluster laborbereich) {
     this.laborbereich = laborbereich;
  }

  public LaborbereichCluster getLaborbereich() {
     return this.laborbereich ;
  }

  public void setDetailsDerTestanforderung(
      List<LaborergebnisDetailsDerTestanforderungCluster> detailsDerTestanforderung) {
     this.detailsDerTestanforderung = detailsDerTestanforderung;
  }

  public List<LaborergebnisDetailsDerTestanforderungCluster> getDetailsDerTestanforderung() {
     return this.detailsDerTestanforderung ;
  }

  public void setUntersuchungsmethodeValue(String untersuchungsmethodeValue) {
     this.untersuchungsmethodeValue = untersuchungsmethodeValue;
  }

  public String getUntersuchungsmethodeValue() {
     return this.untersuchungsmethodeValue ;
  }

  public void setUntersuchungsmethodeNullFlavourDefiningCode(
      NullFlavour untersuchungsmethodeNullFlavourDefiningCode) {
     this.untersuchungsmethodeNullFlavourDefiningCode = untersuchungsmethodeNullFlavourDefiningCode;
  }

  public NullFlavour getUntersuchungsmethodeNullFlavourDefiningCode() {
     return this.untersuchungsmethodeNullFlavourDefiningCode ;
  }

  public void setTestDetails(List<Cluster> testDetails) {
     this.testDetails = testDetails;
  }

  public List<Cluster> getTestDetails() {
     return this.testDetails ;
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

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setJedesEreignis(List<LaborergebnisJedesEreignisChoice> jedesEreignis) {
     this.jedesEreignis = jedesEreignis;
  }

  public List<LaborergebnisJedesEreignisChoice> getJedesEreignis() {
     return this.jedesEreignis ;
  }
}
