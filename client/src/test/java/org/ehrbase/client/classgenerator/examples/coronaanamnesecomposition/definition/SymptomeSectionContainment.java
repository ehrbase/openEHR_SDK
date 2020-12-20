package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class SymptomeSectionContainment extends Containment {
  public SelectAqlField<SymptomeSection> SYMPTOME_SECTION =
      new AqlFieldImp<SymptomeSection>(
          SymptomeSection.class, "", "SymptomeSection", SymptomeSection.class, this);

  public SelectAqlField<HustenObservation> HUSTEN =
      new AqlFieldImp<HustenObservation>(
          SymptomeSection.class,
          "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]",
          "husten",
          HustenObservation.class,
          this);

  public SelectAqlField<SchnupfenObservation> SCHNUPFEN =
      new AqlFieldImp<SchnupfenObservation>(
          SymptomeSection.class,
          "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]",
          "schnupfen",
          SchnupfenObservation.class,
          this);

  public SelectAqlField<HeiserkeitObservation> HEISERKEIT =
      new AqlFieldImp<HeiserkeitObservation>(
          SymptomeSection.class,
          "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]",
          "heiserkeit",
          HeiserkeitObservation.class,
          this);

  public SelectAqlField<FieberOderErhohteKorpertemperaturObservation>
      FIEBER_ODER_ERHOHTE_KORPERTEMPERATUR =
          new AqlFieldImp<FieberOderErhohteKorpertemperaturObservation>(
              SymptomeSection.class,
              "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]",
              "fieberOderErhohteKorpertemperatur",
              FieberOderErhohteKorpertemperaturObservation.class,
              this);

  public SelectAqlField<KorpertemperaturObservation> KORPERTEMPERATUR =
      new AqlFieldImp<KorpertemperaturObservation>(
          SymptomeSection.class,
          "/items[openEHR-EHR-OBSERVATION.body_temperature.v2]",
          "korpertemperatur",
          KorpertemperaturObservation.class,
          this);

  public SelectAqlField<GestorterGeruchssinnObservation> GESTORTER_GERUCHSSINN =
      new AqlFieldImp<GestorterGeruchssinnObservation>(
          SymptomeSection.class,
          "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]",
          "gestorterGeruchssinn",
          GestorterGeruchssinnObservation.class,
          this);

  public SelectAqlField<GestorterGeschmackssinnObservation> GESTORTER_GESCHMACKSSINN =
      new AqlFieldImp<GestorterGeschmackssinnObservation>(
          SymptomeSection.class,
          "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]",
          "gestorterGeschmackssinn",
          GestorterGeschmackssinnObservation.class,
          this);

  public SelectAqlField<DurchfallObservation> DURCHFALL =
      new AqlFieldImp<DurchfallObservation>(
          SymptomeSection.class,
          "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]",
          "durchfall",
          DurchfallObservation.class,
          this);

  public SelectAqlField<WeitereSymptomeObservation> WEITERE_SYMPTOME =
      new AqlFieldImp<WeitereSymptomeObservation>(
          SymptomeSection.class,
          "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]",
          "weitereSymptome",
          WeitereSymptomeObservation.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          SymptomeSection.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private SymptomeSectionContainment() {
    super("openEHR-EHR-SECTION.adhoc.v1");
  }

  public static SymptomeSectionContainment getInstance() {
    return new SymptomeSectionContainment();
  }
}
