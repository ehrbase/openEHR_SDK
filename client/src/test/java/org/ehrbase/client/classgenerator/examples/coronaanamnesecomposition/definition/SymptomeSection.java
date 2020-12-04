package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class SymptomeSection {
  /**
   * Bericht/Symptome/Husten
   */
  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Husten']")
  private HustenObservation husten;

  /**
   * Bericht/Symptome/Schnupfen
   */
  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Schnupfen']")
  private SchnupfenObservation schnupfen;

  /**
   * Bericht/Symptome/Heiserkeit
   */
  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Heiserkeit']")
  private HeiserkeitObservation heiserkeit;

  /**
   * Bericht/Symptome/Fieber oder erhöhte Körpertemperatur
   */
  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Fieber oder erhöhte Körpertemperatur']")
  private FieberOderErhohteKorpertemperaturObservation fieberOderErhohteKorpertemperatur;

  /**
   * Bericht/Symptome/Körpertemperatur
   */
  @Path("/items[openEHR-EHR-OBSERVATION.body_temperature.v2]")
  private KorpertemperaturObservation korpertemperatur;

  /**
   * Bericht/Symptome/Gestörter Geruchssinn
   */
  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geruchssinn']")
  private GestorterGeruchssinnObservation gestorterGeruchssinn;

  /**
   * Bericht/Symptome/Gestörter Geschmackssinn
   */
  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geschmackssinn']")
  private GestorterGeschmackssinnObservation gestorterGeschmackssinn;

  /**
   * Bericht/Symptome/Durchfall
   */
  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Durchfall']")
  private DurchfallObservation durchfall;

  /**
   * Bericht/Symptome/Weitere Symptome
   */
  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Weitere Symptome']")
  private WeitereSymptomeObservation weitereSymptome;

  /**
   * Bericht/Symptome/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setHusten(HustenObservation husten) {
     this.husten = husten;
  }

  public HustenObservation getHusten() {
     return this.husten ;
  }

  public void setSchnupfen(SchnupfenObservation schnupfen) {
     this.schnupfen = schnupfen;
  }

  public SchnupfenObservation getSchnupfen() {
     return this.schnupfen ;
  }

  public void setHeiserkeit(HeiserkeitObservation heiserkeit) {
     this.heiserkeit = heiserkeit;
  }

  public HeiserkeitObservation getHeiserkeit() {
     return this.heiserkeit ;
  }

  public void setFieberOderErhohteKorpertemperatur(
      FieberOderErhohteKorpertemperaturObservation fieberOderErhohteKorpertemperatur) {
     this.fieberOderErhohteKorpertemperatur = fieberOderErhohteKorpertemperatur;
  }

  public FieberOderErhohteKorpertemperaturObservation getFieberOderErhohteKorpertemperatur() {
     return this.fieberOderErhohteKorpertemperatur ;
  }

  public void setKorpertemperatur(KorpertemperaturObservation korpertemperatur) {
     this.korpertemperatur = korpertemperatur;
  }

  public KorpertemperaturObservation getKorpertemperatur() {
     return this.korpertemperatur ;
  }

  public void setGestorterGeruchssinn(GestorterGeruchssinnObservation gestorterGeruchssinn) {
     this.gestorterGeruchssinn = gestorterGeruchssinn;
  }

  public GestorterGeruchssinnObservation getGestorterGeruchssinn() {
     return this.gestorterGeruchssinn ;
  }

  public void setGestorterGeschmackssinn(
      GestorterGeschmackssinnObservation gestorterGeschmackssinn) {
     this.gestorterGeschmackssinn = gestorterGeschmackssinn;
  }

  public GestorterGeschmackssinnObservation getGestorterGeschmackssinn() {
     return this.gestorterGeschmackssinn ;
  }

  public void setDurchfall(DurchfallObservation durchfall) {
     this.durchfall = durchfall;
  }

  public DurchfallObservation getDurchfall() {
     return this.durchfall ;
  }

  public void setWeitereSymptome(WeitereSymptomeObservation weitereSymptome) {
     this.weitereSymptome = weitereSymptome;
  }

  public WeitereSymptomeObservation getWeitereSymptome() {
     return this.weitereSymptome ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
