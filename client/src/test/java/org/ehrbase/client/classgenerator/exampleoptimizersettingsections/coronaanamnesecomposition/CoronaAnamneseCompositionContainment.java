package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.AndereAktuelleErkrankungenObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.AufenthaltInGesundheitseinrichtungObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.BewertungDesGesundheitsrisikosEvaluation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.ChronischeErkrankungenObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.DienstleistungInstruction;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.DurchfallObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.FieberOderErhoehteKoerpertemperaturObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.FragebogenZumMedikationsScreeningObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.GeschichteHistorieObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.GestoerterGeruchssinnObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.GestoerterGeschmackssinnObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.GesundheitsbezogenerBerufEvaluation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.HeiserkeitObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.HistorieDerReiseObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.HustenObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.KoerpertemperaturObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.PersonenkontaktObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.ProblemDiagnoseEvaluation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.ProblemDiganoseCoronovirusEvaluation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.ReisefallObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.SchnupfenObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.WeitereSymptomeObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.WohnsituationEvaluation;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;

public class CoronaAnamneseCompositionContainment extends Containment {
  public SelectAqlField<CoronaAnamneseComposition> CORONA_ANAMNESE_COMPOSITION =
      new AqlFieldImp<CoronaAnamneseComposition>(
          CoronaAnamneseComposition.class,
          "",
          "CoronaAnamneseComposition",
          CoronaAnamneseComposition.class,
          this);

  public SelectAqlField<String> BERICHT_ID_VALUE =
      new AqlFieldImp<String>(
          CoronaAnamneseComposition.class,
          "/context/other_context[at0001]/items[at0002]/value|value",
          "berichtIdValue",
          String.class,
          this);

  public SelectAqlField<String> STATUS_VALUE =
      new AqlFieldImp<String>(
          CoronaAnamneseComposition.class,
          "/context/other_context[at0001]/items[at0005]/value|value",
          "statusValue",
          String.class,
          this);

  public ListSelectAqlField<Cluster> ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/context/other_context[at0001]/items[at0006]",
          "erweiterung",
          Cluster.class,
          this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/context/start_time|value",
          "startTimeValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Participation> PARTICIPATIONS =
      new ListAqlFieldImp<Participation>(
          CoronaAnamneseComposition.class,
          "/context/participations",
          "participations",
          Participation.class,
          this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/context/end_time|value",
          "endTimeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<String> LOCATION =
      new AqlFieldImp<String>(
          CoronaAnamneseComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY =
      new AqlFieldImp<PartyIdentified>(
          CoronaAnamneseComposition.class,
          "/context/health_care_facility",
          "healthCareFacility",
          PartyIdentified.class,
          this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE =
      new AqlFieldImp<Setting>(
          CoronaAnamneseComposition.class,
          "/context/setting|defining_code",
          "settingDefiningCode",
          Setting.class,
          this);

  public ListSelectAqlField<GeschichteHistorieObservation> GESCHICHTE_HISTORIE =
      new ListAqlFieldImp<GeschichteHistorieObservation>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-OBSERVATION.story.v1]",
          "geschichteHistorie",
          GeschichteHistorieObservation.class,
          this);

  public SelectAqlField<HustenObservation> HUSTEN =
      new AqlFieldImp<HustenObservation>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]",
          "husten",
          HustenObservation.class,
          this);

  public SelectAqlField<SchnupfenObservation> SCHNUPFEN =
      new AqlFieldImp<SchnupfenObservation>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]",
          "schnupfen",
          SchnupfenObservation.class,
          this);

  public SelectAqlField<HeiserkeitObservation> HEISERKEIT =
      new AqlFieldImp<HeiserkeitObservation>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]",
          "heiserkeit",
          HeiserkeitObservation.class,
          this);

  public SelectAqlField<FieberOderErhoehteKoerpertemperaturObservation>
      FIEBER_ODER_ERHOEHTE_KOERPERTEMPERATUR =
          new AqlFieldImp<FieberOderErhoehteKoerpertemperaturObservation>(
              CoronaAnamneseComposition.class,
              "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]",
              "fieberOderErhoehteKoerpertemperatur",
              FieberOderErhoehteKoerpertemperaturObservation.class,
              this);

  public SelectAqlField<KoerpertemperaturObservation> KOERPERTEMPERATUR =
      new AqlFieldImp<KoerpertemperaturObservation>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.body_temperature.v2]",
          "koerpertemperatur",
          KoerpertemperaturObservation.class,
          this);

  public SelectAqlField<GestoerterGeruchssinnObservation> GESTOERTER_GERUCHSSINN =
      new AqlFieldImp<GestoerterGeruchssinnObservation>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]",
          "gestoerterGeruchssinn",
          GestoerterGeruchssinnObservation.class,
          this);

  public SelectAqlField<GestoerterGeschmackssinnObservation> GESTOERTER_GESCHMACKSSINN =
      new AqlFieldImp<GestoerterGeschmackssinnObservation>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]",
          "gestoerterGeschmackssinn",
          GestoerterGeschmackssinnObservation.class,
          this);

  public SelectAqlField<DurchfallObservation> DURCHFALL =
      new AqlFieldImp<DurchfallObservation>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]",
          "durchfall",
          DurchfallObservation.class,
          this);

  public SelectAqlField<WeitereSymptomeObservation> WEITERE_SYMPTOME =
      new AqlFieldImp<WeitereSymptomeObservation>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]",
          "weitereSymptome",
          WeitereSymptomeObservation.class,
          this);

  public SelectAqlField<PersonenkontaktObservation> PERSONENKONTAKT =
      new AqlFieldImp<PersonenkontaktObservation>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]",
          "personenkontakt",
          PersonenkontaktObservation.class,
          this);

  public SelectAqlField<AufenthaltInGesundheitseinrichtungObservation>
      AUFENTHALT_IN_GESUNDHEITSEINRICHTUNG =
          new AqlFieldImp<AufenthaltInGesundheitseinrichtungObservation>(
              CoronaAnamneseComposition.class,
              "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]",
              "aufenthaltInGesundheitseinrichtung",
              AufenthaltInGesundheitseinrichtungObservation.class,
              this);

  public ListSelectAqlField<HistorieDerReiseObservation> HISTORIE_DER_REISE =
      new ListAqlFieldImp<HistorieDerReiseObservation>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.travel_history.v0]",
          "historieDerReise",
          HistorieDerReiseObservation.class,
          this);

  public ListSelectAqlField<ReisefallObservation> REISEFALL =
      new ListAqlFieldImp<ReisefallObservation>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.travel_event.v0]",
          "reisefall",
          ReisefallObservation.class,
          this);

  public SelectAqlField<AndereAktuelleErkrankungenObservation> ANDERE_AKTUELLE_ERKRANKUNGEN =
      new AqlFieldImp<AndereAktuelleErkrankungenObservation>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]",
          "andereAktuelleErkrankungen",
          AndereAktuelleErkrankungenObservation.class,
          this);

  public SelectAqlField<ChronischeErkrankungenObservation> CHRONISCHE_ERKRANKUNGEN =
      new AqlFieldImp<ChronischeErkrankungenObservation>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]",
          "chronischeErkrankungen",
          ChronischeErkrankungenObservation.class,
          this);

  public ListSelectAqlField<ProblemDiagnoseEvaluation> PROBLEM_DIAGNOSE =
      new ListAqlFieldImp<ProblemDiagnoseEvaluation>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1]",
          "problemDiagnose",
          ProblemDiagnoseEvaluation.class,
          this);

  public SelectAqlField<FragebogenZumMedikationsScreeningObservation>
      FRAGEBOGEN_ZUM_MEDIKATIONS_SCREENING =
          new AqlFieldImp<FragebogenZumMedikationsScreeningObservation>(
              CoronaAnamneseComposition.class,
              "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.medication_use.v0]",
              "fragebogenZumMedikationsScreening",
              FragebogenZumMedikationsScreeningObservation.class,
              this);

  public SelectAqlField<GesundheitsbezogenerBerufEvaluation> GESUNDHEITSBEZOGENER_BERUF =
      new AqlFieldImp<GesundheitsbezogenerBerufEvaluation>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.occupation_summary.v1]",
          "gesundheitsbezogenerBeruf",
          GesundheitsbezogenerBerufEvaluation.class,
          this);

  public ListSelectAqlField<WohnsituationEvaluation> WOHNSITUATION =
      new ListAqlFieldImp<WohnsituationEvaluation>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.living_arrangement.v0]",
          "wohnsituation",
          WohnsituationEvaluation.class,
          this);

  public ListSelectAqlField<BewertungDesGesundheitsrisikosEvaluation>
      BEWERTUNG_DES_GESUNDHEITSRISIKOS =
          new ListAqlFieldImp<BewertungDesGesundheitsrisikosEvaluation>(
              CoronaAnamneseComposition.class,
              "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.health_risk.v1]",
              "bewertungDesGesundheitsrisikos",
              BewertungDesGesundheitsrisikosEvaluation.class,
              this);

  public SelectAqlField<ProblemDiganoseCoronovirusEvaluation> PROBLEM_DIGANOSE_CORONOVIRUS =
      new AqlFieldImp<ProblemDiganoseCoronovirusEvaluation>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1]",
          "problemDiganoseCoronovirus",
          ProblemDiganoseCoronovirusEvaluation.class,
          this);

  public ListSelectAqlField<DienstleistungInstruction> DIENSTLEISTUNG =
      new ListAqlFieldImp<DienstleistungInstruction>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-INSTRUCTION.service_request.v1]",
          "dienstleistung",
          DienstleistungInstruction.class,
          this);

  public SelectAqlField<PartyProxy> COMPOSER =
      new AqlFieldImp<PartyProxy>(
          CoronaAnamneseComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          CoronaAnamneseComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          CoronaAnamneseComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE =
      new AqlFieldImp<Category>(
          CoronaAnamneseComposition.class,
          "/category|defining_code",
          "categoryDefiningCode",
          Category.class,
          this);

  public SelectAqlField<Territory> TERRITORY =
      new AqlFieldImp<Territory>(
          CoronaAnamneseComposition.class, "/territory", "territory", Territory.class, this);

  private CoronaAnamneseCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.report.v1");
  }

  public static CoronaAnamneseCompositionContainment getInstance() {
    return new CoronaAnamneseCompositionContainment();
  }
}
