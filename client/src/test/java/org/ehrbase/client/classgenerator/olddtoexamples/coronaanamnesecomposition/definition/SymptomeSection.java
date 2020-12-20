package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class SymptomeSection {
  @Path(
      "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Weitere Symptome']")
  private WeitereSymptomeObservation weitereSymptome;

  @Path(
      "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Fieber oder erhöhte Körpertemperatur']")
  private FieberOderErhohteKorpertemperaturObservation fieberOderErhohteKorpertemperatur;

  @Path(
      "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geschmackssinn']")
  private GestorterGeschmackssinnObservation gestorterGeschmackssinn;

  @Path("/items[openEHR-EHR-OBSERVATION.body_temperature.v2]")
  private KorpertemperaturObservation korpertemperatur;

  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Heiserkeit']")
  private HeiserkeitObservation heiserkeit;

  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Husten']")
  private HustenObservation husten;

  @Path(
      "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geruchssinn']")
  private GestorterGeruchssinnObservation gestorterGeruchssinn;

  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Durchfall']")
  private DurchfallObservation durchfall;

  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Schnupfen']")
  private SchnupfenObservation schnupfen;

  public void setWeitereSymptome(WeitereSymptomeObservation weitereSymptome) {
    this.weitereSymptome = weitereSymptome;
  }

  public WeitereSymptomeObservation getWeitereSymptome() {
    return this.weitereSymptome;
  }

  public void setFieberOderErhohteKorpertemperatur(
      FieberOderErhohteKorpertemperaturObservation fieberOderErhohteKorpertemperatur) {
    this.fieberOderErhohteKorpertemperatur = fieberOderErhohteKorpertemperatur;
  }

  public FieberOderErhohteKorpertemperaturObservation getFieberOderErhohteKorpertemperatur() {
    return this.fieberOderErhohteKorpertemperatur;
  }

  public void setGestorterGeschmackssinn(
      GestorterGeschmackssinnObservation gestorterGeschmackssinn) {
    this.gestorterGeschmackssinn = gestorterGeschmackssinn;
  }

  public GestorterGeschmackssinnObservation getGestorterGeschmackssinn() {
    return this.gestorterGeschmackssinn;
  }

  public void setKorpertemperatur(KorpertemperaturObservation korpertemperatur) {
    this.korpertemperatur = korpertemperatur;
  }

  public KorpertemperaturObservation getKorpertemperatur() {
    return this.korpertemperatur;
  }

  public void setHeiserkeit(HeiserkeitObservation heiserkeit) {
    this.heiserkeit = heiserkeit;
  }

  public HeiserkeitObservation getHeiserkeit() {
    return this.heiserkeit;
  }

  public void setHusten(HustenObservation husten) {
    this.husten = husten;
  }

  public HustenObservation getHusten() {
    return this.husten;
  }

  public void setGestorterGeruchssinn(GestorterGeruchssinnObservation gestorterGeruchssinn) {
    this.gestorterGeruchssinn = gestorterGeruchssinn;
  }

  public GestorterGeruchssinnObservation getGestorterGeruchssinn() {
    return this.gestorterGeruchssinn;
  }

  public void setDurchfall(DurchfallObservation durchfall) {
    this.durchfall = durchfall;
  }

  public DurchfallObservation getDurchfall() {
    return this.durchfall;
  }

  public void setSchnupfen(SchnupfenObservation schnupfen) {
    this.schnupfen = schnupfen;
  }

  public SchnupfenObservation getSchnupfen() {
    return this.schnupfen;
  }
}
