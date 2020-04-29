package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datavalues.DvText;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.symptome.v1")
public class SymptomeSection {
  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening_husten.v0]")
  private AnzeichenObservation anzeichen;

  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]")
  private AnzeichenObservationScreeningFragebogenZurSymptomen anzeichenScreeningFragebogenZurSymptomen;

  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening_schnupfen.v0]")
  private AnzeichenObservationScreeningFragebogenZurSymptomen2 anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningSchnupfenV0;

  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening_geschmack.v0]")
  private AnzeichenObservationScreeningFragebogenZurSymptomen3 anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningGeschmackV0;

  @Path("/items[openEHR-EHR-OBSERVATION.body_temperature.v2]")
  private KorpertemperaturObservation korpertemperatur;

  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening_geruch.v0]")
  private AnzeichenObservationScreeningFragebogenZurSymptomen4 anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningGeruchV0;

  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening_weitere.v0]")
  private AnzeichenObservationScreeningFragebogenZurSymptomen5 anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningWeitereV0;

  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening_heiserkeit.v0]")
  private AnzeichenObservationScreeningFragebogenZurSymptomen6 anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningHeiserkeitV0;

  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening_durchfall.v0]")
  private AnzeichenObservationScreeningFragebogenZurSymptomen7 anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningDurchfallV0;

  @Path("/name")
  private DvText name;

  public void setAnzeichen(AnzeichenObservation anzeichen) {
     this.anzeichen = anzeichen;
  }

  public AnzeichenObservation getAnzeichen() {
     return this.anzeichen ;
  }

  public void setAnzeichenScreeningFragebogenZurSymptomen(
      AnzeichenObservationScreeningFragebogenZurSymptomen anzeichenScreeningFragebogenZurSymptomen) {
     this.anzeichenScreeningFragebogenZurSymptomen = anzeichenScreeningFragebogenZurSymptomen;
  }

  public AnzeichenObservationScreeningFragebogenZurSymptomen getAnzeichenScreeningFragebogenZurSymptomen(
      ) {
     return this.anzeichenScreeningFragebogenZurSymptomen ;
  }

  public void setAnzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningSchnupfenV0(
      AnzeichenObservationScreeningFragebogenZurSymptomen2 anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningSchnupfenV0) {
     this.anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningSchnupfenV0 = anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningSchnupfenV0;
  }

  public AnzeichenObservationScreeningFragebogenZurSymptomen2 getAnzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningSchnupfenV0(
      ) {
     return this.anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningSchnupfenV0 ;
  }

  public void setAnzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningGeschmackV0(
      AnzeichenObservationScreeningFragebogenZurSymptomen3 anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningGeschmackV0) {
     this.anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningGeschmackV0 = anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningGeschmackV0;
  }

  public AnzeichenObservationScreeningFragebogenZurSymptomen3 getAnzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningGeschmackV0(
      ) {
     return this.anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningGeschmackV0 ;
  }

  public void setKorpertemperatur(KorpertemperaturObservation korpertemperatur) {
     this.korpertemperatur = korpertemperatur;
  }

  public KorpertemperaturObservation getKorpertemperatur() {
     return this.korpertemperatur ;
  }

  public void setAnzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningGeruchV0(
      AnzeichenObservationScreeningFragebogenZurSymptomen4 anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningGeruchV0) {
     this.anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningGeruchV0 = anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningGeruchV0;
  }

  public AnzeichenObservationScreeningFragebogenZurSymptomen4 getAnzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningGeruchV0(
      ) {
     return this.anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningGeruchV0 ;
  }

  public void setAnzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningWeitereV0(
      AnzeichenObservationScreeningFragebogenZurSymptomen5 anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningWeitereV0) {
     this.anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningWeitereV0 = anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningWeitereV0;
  }

  public AnzeichenObservationScreeningFragebogenZurSymptomen5 getAnzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningWeitereV0(
      ) {
     return this.anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningWeitereV0 ;
  }

  public void setAnzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningHeiserkeitV0(
      AnzeichenObservationScreeningFragebogenZurSymptomen6 anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningHeiserkeitV0) {
     this.anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningHeiserkeitV0 = anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningHeiserkeitV0;
  }

  public AnzeichenObservationScreeningFragebogenZurSymptomen6 getAnzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningHeiserkeitV0(
      ) {
     return this.anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningHeiserkeitV0 ;
  }

  public void setAnzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningDurchfallV0(
      AnzeichenObservationScreeningFragebogenZurSymptomen7 anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningDurchfallV0) {
     this.anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningDurchfallV0 = anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningDurchfallV0;
  }

  public AnzeichenObservationScreeningFragebogenZurSymptomen7 getAnzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningDurchfallV0(
      ) {
     return this.anzeichenScreeningFragebogenZurSymptomenItemsOpenehrEhrObservationSymptomSignScreeningDurchfallV0 ;
  }

    public DvText getName() {
        return name;
    }

    public void setName(DvText name) {
        this.name = name;
    }
}
