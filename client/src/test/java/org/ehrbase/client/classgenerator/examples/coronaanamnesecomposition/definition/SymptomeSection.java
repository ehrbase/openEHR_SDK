package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.505025900+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class SymptomeSection implements LocatableEntity {
  /**
   * Path: Bericht/Symptome/Husten Description: Ein Personen- oder
   * Selbstbeurteilungs-Screening-Fragebogen zur Ermittlung von Symptomen und Anzeichen.
   */
  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Husten']")
  private HustenObservation husten;

  /**
   * Path: Bericht/Symptome/Schnupfen Description: Ein Personen- oder
   * Selbstbeurteilungs-Screening-Fragebogen zur Ermittlung von Symptomen und Anzeichen.
   */
  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Schnupfen']")
  private SchnupfenObservation schnupfen;

  /**
   * Path: Bericht/Symptome/Heiserkeit Description: Ein Personen- oder
   * Selbstbeurteilungs-Screening-Fragebogen zur Ermittlung von Symptomen und Anzeichen.
   */
  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Heiserkeit']")
  private HeiserkeitObservation heiserkeit;

  /**
   * Path: Bericht/Symptome/Fieber oder erhöhte Körpertemperatur Description: Ein Personen- oder
   * Selbstbeurteilungs-Screening-Fragebogen zur Ermittlung von Symptomen und Anzeichen.
   */
  @Path(
      "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Fieber oder erhöhte Körpertemperatur']")
  private FieberOderErhohteKorpertemperaturObservation fieberOderErhohteKorpertemperatur;

  /**
   * Path: Bericht/Symptome/Körpertemperatur Description: Eine Messung der Körpertemperatur, als
   * Surrogat für die Temperatur des gesamten Körper der Person.
   */
  @Path("/items[openEHR-EHR-OBSERVATION.body_temperature.v2]")
  private KorpertemperaturObservation korpertemperatur;

  /**
   * Path: Bericht/Symptome/Gestörter Geruchssinn Description: Ein Personen- oder
   * Selbstbeurteilungs-Screening-Fragebogen zur Ermittlung von Symptomen und Anzeichen.
   */
  @Path(
      "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geruchssinn']")
  private GestorterGeruchssinnObservation gestorterGeruchssinn;

  /**
   * Path: Bericht/Symptome/Gestörter Geschmackssinn Description: Ein Personen- oder
   * Selbstbeurteilungs-Screening-Fragebogen zur Ermittlung von Symptomen und Anzeichen.
   */
  @Path(
      "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geschmackssinn']")
  private GestorterGeschmackssinnObservation gestorterGeschmackssinn;

  /**
   * Path: Bericht/Symptome/Durchfall Description: Ein Personen- oder
   * Selbstbeurteilungs-Screening-Fragebogen zur Ermittlung von Symptomen und Anzeichen.
   */
  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Durchfall']")
  private DurchfallObservation durchfall;

  /**
   * Path: Bericht/Symptome/Weitere Symptome Description: Ein Personen- oder
   * Selbstbeurteilungs-Screening-Fragebogen zur Ermittlung von Symptomen und Anzeichen.
   */
  @Path(
      "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Weitere Symptome']")
  private WeitereSymptomeObservation weitereSymptome;

  /** Path: Bericht/Symptome/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setHusten(HustenObservation husten) {
    this.husten = husten;
  }

  public HustenObservation getHusten() {
    return this.husten;
  }

  public void setSchnupfen(SchnupfenObservation schnupfen) {
    this.schnupfen = schnupfen;
  }

  public SchnupfenObservation getSchnupfen() {
    return this.schnupfen;
  }

  public void setHeiserkeit(HeiserkeitObservation heiserkeit) {
    this.heiserkeit = heiserkeit;
  }

  public HeiserkeitObservation getHeiserkeit() {
    return this.heiserkeit;
  }

  public void setFieberOderErhohteKorpertemperatur(
      FieberOderErhohteKorpertemperaturObservation fieberOderErhohteKorpertemperatur) {
    this.fieberOderErhohteKorpertemperatur = fieberOderErhohteKorpertemperatur;
  }

  public FieberOderErhohteKorpertemperaturObservation getFieberOderErhohteKorpertemperatur() {
    return this.fieberOderErhohteKorpertemperatur;
  }

  public void setKorpertemperatur(KorpertemperaturObservation korpertemperatur) {
    this.korpertemperatur = korpertemperatur;
  }

  public KorpertemperaturObservation getKorpertemperatur() {
    return this.korpertemperatur;
  }

  public void setGestorterGeruchssinn(GestorterGeruchssinnObservation gestorterGeruchssinn) {
    this.gestorterGeruchssinn = gestorterGeruchssinn;
  }

  public GestorterGeruchssinnObservation getGestorterGeruchssinn() {
    return this.gestorterGeruchssinn;
  }

  public void setGestorterGeschmackssinn(
      GestorterGeschmackssinnObservation gestorterGeschmackssinn) {
    this.gestorterGeschmackssinn = gestorterGeschmackssinn;
  }

  public GestorterGeschmackssinnObservation getGestorterGeschmackssinn() {
    return this.gestorterGeschmackssinn;
  }

  public void setDurchfall(DurchfallObservation durchfall) {
    this.durchfall = durchfall;
  }

  public DurchfallObservation getDurchfall() {
    return this.durchfall;
  }

  public void setWeitereSymptome(WeitereSymptomeObservation weitereSymptome) {
    this.weitereSymptome = weitereSymptome;
  }

  public WeitereSymptomeObservation getWeitereSymptome() {
    return this.weitereSymptome;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
