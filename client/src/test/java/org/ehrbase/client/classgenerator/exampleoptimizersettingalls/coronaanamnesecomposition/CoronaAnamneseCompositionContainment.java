package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition.BerichtBeliebigesEreignisChoice;
import org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition.BeschaftigungCluster;
import org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition.BewertungDesGesundheitsrisikosEvaluation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition.DiagnosestatusDefiningCode;
import org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition.DienstleistungInstruction;
import org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition.FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster;
import org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition.GeschichteHistorieObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition.HistorieDerReiseObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition.KorpertemperaturStorfaktorenElement;
import org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition.MedikamentenklasseInVerwendungDefiningCode;
import org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition.PresenceOfAnyExposureEnDefiningCode;
import org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition.ProblemDiagnoseEvaluation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition.ProblemDiganoseCoronovirusDiagnostischeSicherheitChoice;
import org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition.ReisefallObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition.VorhandenDefiningCode;
import org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition.VorhandenseinDefiningCode;
import org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition.WohnsituationEvaluation;
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

  public ListSelectAqlField<Cluster> BERICHT_ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/context/other_context[at0001]/items[at0006]",
          "berichtErweiterung",
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

  public SelectAqlField<String> HUSTEN_BEZEICHNUNG_DES_SYMPTOMS_ODER_ANZEICHENS_VALUE =
      new AqlFieldImp<String>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value",
          "hustenBezeichnungDesSymptomsOderAnzeichensValue",
          String.class,
          this);

  public SelectAqlField<VorhandenDefiningCode> HUSTEN_VORHANDEN_DEFINING_CODE =
      new AqlFieldImp<VorhandenDefiningCode>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code",
          "hustenVorhandenDefiningCode",
          VorhandenDefiningCode.class,
          this);

  public ListSelectAqlField<Cluster> HUSTEN_DETAILLIERTE_ANGABEN_ZUM_SYMPTOM_ANZEICHEN =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]",
          "hustenDetaillierteAngabenZumSymptomAnzeichen",
          Cluster.class,
          this);

  public SelectAqlField<TemporalAccessor> HUSTEN_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/time|value",
          "hustenTimeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> HUSTEN_ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/origin|value",
          "hustenOriginValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> HUSTEN_ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/protocol[at0007]/items[at0021]",
          "hustenErweiterung",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> HUSTEN_SUBJECT =
      new AqlFieldImp<PartyProxy>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/subject",
          "hustenSubject",
          PartyProxy.class,
          this);

  public SelectAqlField<Language> HUSTEN_LANGUAGE =
      new AqlFieldImp<Language>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/language",
          "hustenLanguage",
          Language.class,
          this);

  public SelectAqlField<String> SCHNUPFEN_BEZEICHNUNG_DES_SYMPTOMS_ODER_ANZEICHENS_VALUE =
      new AqlFieldImp<String>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value",
          "schnupfenBezeichnungDesSymptomsOderAnzeichensValue",
          String.class,
          this);

  public SelectAqlField<VorhandenDefiningCode> SCHNUPFEN_VORHANDEN_DEFINING_CODE =
      new AqlFieldImp<VorhandenDefiningCode>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code",
          "schnupfenVorhandenDefiningCode",
          VorhandenDefiningCode.class,
          this);

  public ListSelectAqlField<Cluster> SCHNUPFEN_DETAILLIERTE_ANGABEN_ZUM_SYMPTOM_ANZEICHEN =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]",
          "schnupfenDetaillierteAngabenZumSymptomAnzeichen",
          Cluster.class,
          this);

  public SelectAqlField<TemporalAccessor> SCHNUPFEN_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/time|value",
          "schnupfenTimeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> SCHNUPFEN_ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/origin|value",
          "schnupfenOriginValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> SCHNUPFEN_ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/protocol[at0007]/items[at0021]",
          "schnupfenErweiterung",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SCHNUPFEN_SUBJECT =
      new AqlFieldImp<PartyProxy>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/subject",
          "schnupfenSubject",
          PartyProxy.class,
          this);

  public SelectAqlField<Language> SCHNUPFEN_LANGUAGE =
      new AqlFieldImp<Language>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/language",
          "schnupfenLanguage",
          Language.class,
          this);

  public SelectAqlField<String> HEISERKEIT_BEZEICHNUNG_DES_SYMPTOMS_ODER_ANZEICHENS_VALUE =
      new AqlFieldImp<String>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value",
          "heiserkeitBezeichnungDesSymptomsOderAnzeichensValue",
          String.class,
          this);

  public SelectAqlField<VorhandenDefiningCode> HEISERKEIT_VORHANDEN_DEFINING_CODE =
      new AqlFieldImp<VorhandenDefiningCode>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code",
          "heiserkeitVorhandenDefiningCode",
          VorhandenDefiningCode.class,
          this);

  public ListSelectAqlField<Cluster> HEISERKEIT_DETAILLIERTE_ANGABEN_ZUM_SYMPTOM_ANZEICHEN =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]",
          "heiserkeitDetaillierteAngabenZumSymptomAnzeichen",
          Cluster.class,
          this);

  public SelectAqlField<TemporalAccessor> HEISERKEIT_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/time|value",
          "heiserkeitTimeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> HEISERKEIT_ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/origin|value",
          "heiserkeitOriginValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> HEISERKEIT_ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/protocol[at0007]/items[at0021]",
          "heiserkeitErweiterung",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> HEISERKEIT_SUBJECT =
      new AqlFieldImp<PartyProxy>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/subject",
          "heiserkeitSubject",
          PartyProxy.class,
          this);

  public SelectAqlField<Language> HEISERKEIT_LANGUAGE =
      new AqlFieldImp<Language>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/language",
          "heiserkeitLanguage",
          Language.class,
          this);

  public SelectAqlField<String>
      FIEBER_ODER_ERHOHTE_KORPERTEMPERATUR_BEZEICHNUNG_DES_SYMPTOMS_ODER_ANZEICHENS_VALUE =
          new AqlFieldImp<String>(
              CoronaAnamneseComposition.class,
              "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value",
              "fieberOderErhohteKorpertemperaturBezeichnungDesSymptomsOderAnzeichensValue",
              String.class,
              this);

  public SelectAqlField<VorhandenDefiningCode>
      FIEBER_ODER_ERHOHTE_KORPERTEMPERATUR_VORHANDEN_DEFINING_CODE =
          new AqlFieldImp<VorhandenDefiningCode>(
              CoronaAnamneseComposition.class,
              "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code",
              "fieberOderErhohteKorpertemperaturVorhandenDefiningCode",
              VorhandenDefiningCode.class,
              this);

  public ListSelectAqlField<Cluster>
      FIEBER_ODER_ERHOHTE_KORPERTEMPERATUR_DETAILLIERTE_ANGABEN_ZUM_SYMPTOM_ANZEICHEN =
          new ListAqlFieldImp<Cluster>(
              CoronaAnamneseComposition.class,
              "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]",
              "fieberOderErhohteKorpertemperaturDetaillierteAngabenZumSymptomAnzeichen",
              Cluster.class,
              this);

  public SelectAqlField<TemporalAccessor> FIEBER_ODER_ERHOHTE_KORPERTEMPERATUR_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/time|value",
          "fieberOderErhohteKorpertemperaturTimeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> FIEBER_ODER_ERHOHTE_KORPERTEMPERATUR_ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/origin|value",
          "fieberOderErhohteKorpertemperaturOriginValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> FIEBER_ODER_ERHOHTE_KORPERTEMPERATUR_ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/protocol[at0007]/items[at0021]",
          "fieberOderErhohteKorpertemperaturErweiterung",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> FIEBER_ODER_ERHOHTE_KORPERTEMPERATUR_SUBJECT =
      new AqlFieldImp<PartyProxy>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/subject",
          "fieberOderErhohteKorpertemperaturSubject",
          PartyProxy.class,
          this);

  public SelectAqlField<Language> FIEBER_ODER_ERHOHTE_KORPERTEMPERATUR_LANGUAGE =
      new AqlFieldImp<Language>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/language",
          "fieberOderErhohteKorpertemperaturLanguage",
          Language.class,
          this);

  public SelectAqlField<Double> TEMPERATUR_MAGNITUDE =
      new AqlFieldImp<Double>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude",
          "temperaturMagnitude",
          Double.class,
          this);

  public SelectAqlField<String> TEMPERATUR_UNITS =
      new AqlFieldImp<String>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units",
          "temperaturUnits",
          String.class,
          this);

  public ListSelectAqlField<KorpertemperaturStorfaktorenElement> STORFAKTOREN =
      new ListAqlFieldImp<KorpertemperaturStorfaktorenElement>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/data[at0002]/events[at0003]/state[at0029]/items[at0066]",
          "storfaktoren",
          KorpertemperaturStorfaktorenElement.class,
          this);

  public ListSelectAqlField<Cluster> UMGEBUNGSBEDINGUNGEN =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/data[at0002]/events[at0003]/state[at0029]/items[at0056]",
          "umgebungsbedingungen",
          Cluster.class,
          this);

  public SelectAqlField<Cluster> BETATIGUNG =
      new AqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/data[at0002]/events[at0003]/state[at0029]/items[at0057]",
          "betatigung",
          Cluster.class,
          this);

  public SelectAqlField<TemporalAccessor> KORPERTEMPERATUR_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/data[at0002]/events[at0003]/time|value",
          "korpertemperaturTimeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> KORPERTEMPERATUR_ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/data[at0002]/origin|value",
          "korpertemperaturOriginValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> STRUKTURIERTE_LOKALISATION_DER_MESSUNG =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/protocol[at0020]/items[at0064]",
          "strukturierteLokalisationDerMessung",
          Cluster.class,
          this);

  public SelectAqlField<Cluster> GERAT =
      new AqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/protocol[at0020]/items[at0059]",
          "gerat",
          Cluster.class,
          this);

  public ListSelectAqlField<Cluster> KORPERTEMPERATUR_ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/protocol[at0020]/items[at0062]",
          "korpertemperaturErweiterung",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> KORPERTEMPERATUR_SUBJECT =
      new AqlFieldImp<PartyProxy>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/subject",
          "korpertemperaturSubject",
          PartyProxy.class,
          this);

  public SelectAqlField<Language> KORPERTEMPERATUR_LANGUAGE =
      new AqlFieldImp<Language>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/language",
          "korpertemperaturLanguage",
          Language.class,
          this);

  public SelectAqlField<String>
      GESTORTER_GERUCHSSINN_BEZEICHNUNG_DES_SYMPTOMS_ODER_ANZEICHENS_VALUE =
          new AqlFieldImp<String>(
              CoronaAnamneseComposition.class,
              "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value",
              "gestorterGeruchssinnBezeichnungDesSymptomsOderAnzeichensValue",
              String.class,
              this);

  public SelectAqlField<VorhandenDefiningCode> GESTORTER_GERUCHSSINN_VORHANDEN_DEFINING_CODE =
      new AqlFieldImp<VorhandenDefiningCode>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code",
          "gestorterGeruchssinnVorhandenDefiningCode",
          VorhandenDefiningCode.class,
          this);

  public ListSelectAqlField<Cluster>
      GESTORTER_GERUCHSSINN_DETAILLIERTE_ANGABEN_ZUM_SYMPTOM_ANZEICHEN =
          new ListAqlFieldImp<Cluster>(
              CoronaAnamneseComposition.class,
              "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]",
              "gestorterGeruchssinnDetaillierteAngabenZumSymptomAnzeichen",
              Cluster.class,
              this);

  public SelectAqlField<TemporalAccessor> GESTORTER_GERUCHSSINN_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/time|value",
          "gestorterGeruchssinnTimeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> GESTORTER_GERUCHSSINN_ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/origin|value",
          "gestorterGeruchssinnOriginValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> GESTORTER_GERUCHSSINN_ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/protocol[at0007]/items[at0021]",
          "gestorterGeruchssinnErweiterung",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> GESTORTER_GERUCHSSINN_SUBJECT =
      new AqlFieldImp<PartyProxy>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/subject",
          "gestorterGeruchssinnSubject",
          PartyProxy.class,
          this);

  public SelectAqlField<Language> GESTORTER_GERUCHSSINN_LANGUAGE =
      new AqlFieldImp<Language>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/language",
          "gestorterGeruchssinnLanguage",
          Language.class,
          this);

  public SelectAqlField<String>
      GESTORTER_GESCHMACKSSINN_BEZEICHNUNG_DES_SYMPTOMS_ODER_ANZEICHENS_VALUE =
          new AqlFieldImp<String>(
              CoronaAnamneseComposition.class,
              "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value",
              "gestorterGeschmackssinnBezeichnungDesSymptomsOderAnzeichensValue",
              String.class,
              this);

  public SelectAqlField<VorhandenDefiningCode> GESTORTER_GESCHMACKSSINN_VORHANDEN_DEFINING_CODE =
      new AqlFieldImp<VorhandenDefiningCode>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code",
          "gestorterGeschmackssinnVorhandenDefiningCode",
          VorhandenDefiningCode.class,
          this);

  public ListSelectAqlField<Cluster>
      GESTORTER_GESCHMACKSSINN_DETAILLIERTE_ANGABEN_ZUM_SYMPTOM_ANZEICHEN =
          new ListAqlFieldImp<Cluster>(
              CoronaAnamneseComposition.class,
              "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]",
              "gestorterGeschmackssinnDetaillierteAngabenZumSymptomAnzeichen",
              Cluster.class,
              this);

  public SelectAqlField<TemporalAccessor> GESTORTER_GESCHMACKSSINN_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/time|value",
          "gestorterGeschmackssinnTimeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> GESTORTER_GESCHMACKSSINN_ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/origin|value",
          "gestorterGeschmackssinnOriginValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> GESTORTER_GESCHMACKSSINN_ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/protocol[at0007]/items[at0021]",
          "gestorterGeschmackssinnErweiterung",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> GESTORTER_GESCHMACKSSINN_SUBJECT =
      new AqlFieldImp<PartyProxy>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/subject",
          "gestorterGeschmackssinnSubject",
          PartyProxy.class,
          this);

  public SelectAqlField<Language> GESTORTER_GESCHMACKSSINN_LANGUAGE =
      new AqlFieldImp<Language>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/language",
          "gestorterGeschmackssinnLanguage",
          Language.class,
          this);

  public SelectAqlField<String> DURCHFALL_BEZEICHNUNG_DES_SYMPTOMS_ODER_ANZEICHENS_VALUE =
      new AqlFieldImp<String>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value",
          "durchfallBezeichnungDesSymptomsOderAnzeichensValue",
          String.class,
          this);

  public SelectAqlField<VorhandenDefiningCode> DURCHFALL_VORHANDEN_DEFINING_CODE =
      new AqlFieldImp<VorhandenDefiningCode>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code",
          "durchfallVorhandenDefiningCode",
          VorhandenDefiningCode.class,
          this);

  public ListSelectAqlField<Cluster> DURCHFALL_DETAILLIERTE_ANGABEN_ZUM_SYMPTOM_ANZEICHEN =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]",
          "durchfallDetaillierteAngabenZumSymptomAnzeichen",
          Cluster.class,
          this);

  public SelectAqlField<TemporalAccessor> DURCHFALL_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/time|value",
          "durchfallTimeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> DURCHFALL_ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/origin|value",
          "durchfallOriginValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> DURCHFALL_ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/protocol[at0007]/items[at0021]",
          "durchfallErweiterung",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> DURCHFALL_SUBJECT =
      new AqlFieldImp<PartyProxy>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/subject",
          "durchfallSubject",
          PartyProxy.class,
          this);

  public SelectAqlField<Language> DURCHFALL_LANGUAGE =
      new AqlFieldImp<Language>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/language",
          "durchfallLanguage",
          Language.class,
          this);

  public SelectAqlField<TemporalAccessor> WEITERE_SYMPTOME_ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/origin|value",
          "weitereSymptomeOriginValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> WEITERE_SYMPTOME_ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/protocol[at0007]/items[at0021]",
          "weitereSymptomeErweiterung",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> WEITERE_SYMPTOME_SUBJECT =
      new AqlFieldImp<PartyProxy>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/subject",
          "weitereSymptomeSubject",
          PartyProxy.class,
          this);

  public SelectAqlField<Language> WEITERE_SYMPTOME_LANGUAGE =
      new AqlFieldImp<Language>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/language",
          "weitereSymptomeLanguage",
          Language.class,
          this);

  public SelectAqlField<String> PERSONENKONTAKT_AGENT_EN_VALUE =
      new AqlFieldImp<String>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]/data[at0001]/events[at0002]/data[at0042]/items[at0043]/value|value",
          "personenkontaktAgentEnValue",
          String.class,
          this);

  public SelectAqlField<PresenceOfAnyExposureEnDefiningCode>
      PERSONENKONTAKT_PRESENCE_OF_ANY_EXPOSURE_EN_DEFINING_CODE =
          new AqlFieldImp<PresenceOfAnyExposureEnDefiningCode>(
              CoronaAnamneseComposition.class,
              "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]/data[at0001]/events[at0002]/data[at0042]/items[at0057]/value|defining_code",
              "personenkontaktPresenceOfAnyExposureEnDefiningCode",
              PresenceOfAnyExposureEnDefiningCode.class,
              this);

  public SelectAqlField<String> PERSONENKONTAKT_EXPOSURE_EN_VALUE =
      new AqlFieldImp<String>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]/data[at0001]/events[at0002]/data[at0042]/items[at0044]/items[at0045]/value|value",
          "personenkontaktExposureEnValue",
          String.class,
          this);

  public SelectAqlField<VorhandenseinDefiningCode> PERSONENKONTAKT_VORHANDENSEIN_DEFINING_CODE =
      new AqlFieldImp<VorhandenseinDefiningCode>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]/data[at0001]/events[at0002]/data[at0042]/items[at0044]/items[at0046]/value|defining_code",
          "personenkontaktVorhandenseinDefiningCode",
          VorhandenseinDefiningCode.class,
          this);

  public SelectAqlField<String> PERSONENKONTAKT_KOMMENTAR_VALUE =
      new AqlFieldImp<String>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value|value",
          "personenkontaktKommentarValue",
          String.class,
          this);

  public SelectAqlField<TemporalAccessor> PERSONENKONTAKT_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]/data[at0001]/events[at0002]/time|value",
          "personenkontaktTimeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> PERSONENKONTAKT_ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]/data[at0001]/origin|value",
          "personenkontaktOriginValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> PERSONENKONTAKT_ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]/protocol[at0004]/items[at0056]",
          "personenkontaktErweiterung",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> PERSONENKONTAKT_SUBJECT =
      new AqlFieldImp<PartyProxy>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]/subject",
          "personenkontaktSubject",
          PartyProxy.class,
          this);

  public SelectAqlField<Language> PERSONENKONTAKT_LANGUAGE =
      new AqlFieldImp<Language>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]/language",
          "personenkontaktLanguage",
          Language.class,
          this);

  public SelectAqlField<String> AUFENTHALT_IN_GESUNDHEITSEINRICHTUNG_AGENT_EN_VALUE =
      new AqlFieldImp<String>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]/data[at0001]/events[at0002]/data[at0042]/items[at0043]/value|value",
          "aufenthaltInGesundheitseinrichtungAgentEnValue",
          String.class,
          this);

  public SelectAqlField<PresenceOfAnyExposureEnDefiningCode>
      AUFENTHALT_IN_GESUNDHEITSEINRICHTUNG_PRESENCE_OF_ANY_EXPOSURE_EN_DEFINING_CODE =
          new AqlFieldImp<PresenceOfAnyExposureEnDefiningCode>(
              CoronaAnamneseComposition.class,
              "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]/data[at0001]/events[at0002]/data[at0042]/items[at0057]/value|defining_code",
              "aufenthaltInGesundheitseinrichtungPresenceOfAnyExposureEnDefiningCode",
              PresenceOfAnyExposureEnDefiningCode.class,
              this);

  public SelectAqlField<String> AUFENTHALT_IN_GESUNDHEITSEINRICHTUNG_EXPOSURE_EN_VALUE =
      new AqlFieldImp<String>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]/data[at0001]/events[at0002]/data[at0042]/items[at0044]/items[at0045]/value|value",
          "aufenthaltInGesundheitseinrichtungExposureEnValue",
          String.class,
          this);

  public SelectAqlField<VorhandenseinDefiningCode>
      AUFENTHALT_IN_GESUNDHEITSEINRICHTUNG_VORHANDENSEIN_DEFINING_CODE =
          new AqlFieldImp<VorhandenseinDefiningCode>(
              CoronaAnamneseComposition.class,
              "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]/data[at0001]/events[at0002]/data[at0042]/items[at0044]/items[at0046]/value|defining_code",
              "aufenthaltInGesundheitseinrichtungVorhandenseinDefiningCode",
              VorhandenseinDefiningCode.class,
              this);

  public SelectAqlField<String> AUFENTHALT_IN_GESUNDHEITSEINRICHTUNG_KOMMENTAR_VALUE =
      new AqlFieldImp<String>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value|value",
          "aufenthaltInGesundheitseinrichtungKommentarValue",
          String.class,
          this);

  public SelectAqlField<TemporalAccessor> AUFENTHALT_IN_GESUNDHEITSEINRICHTUNG_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]/data[at0001]/events[at0002]/time|value",
          "aufenthaltInGesundheitseinrichtungTimeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> AUFENTHALT_IN_GESUNDHEITSEINRICHTUNG_ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]/data[at0001]/origin|value",
          "aufenthaltInGesundheitseinrichtungOriginValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> AUFENTHALT_IN_GESUNDHEITSEINRICHTUNG_ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]/protocol[at0004]/items[at0056]",
          "aufenthaltInGesundheitseinrichtungErweiterung",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> AUFENTHALT_IN_GESUNDHEITSEINRICHTUNG_SUBJECT =
      new AqlFieldImp<PartyProxy>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]/subject",
          "aufenthaltInGesundheitseinrichtungSubject",
          PartyProxy.class,
          this);

  public SelectAqlField<Language> AUFENTHALT_IN_GESUNDHEITSEINRICHTUNG_LANGUAGE =
      new AqlFieldImp<Language>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]/language",
          "aufenthaltInGesundheitseinrichtungLanguage",
          Language.class,
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

  public SelectAqlField<String>
      ANDERE_AKTUELLE_ERKRANKUNGEN_BEZEICHNUNG_DES_SYMPTOMS_ODER_ANZEICHENS_VALUE =
          new AqlFieldImp<String>(
              CoronaAnamneseComposition.class,
              "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value",
              "andereAktuelleErkrankungenBezeichnungDesSymptomsOderAnzeichensValue",
              String.class,
              this);

  public SelectAqlField<VorhandenDefiningCode>
      ANDERE_AKTUELLE_ERKRANKUNGEN_VORHANDEN_DEFINING_CODE =
          new AqlFieldImp<VorhandenDefiningCode>(
              CoronaAnamneseComposition.class,
              "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code",
              "andereAktuelleErkrankungenVorhandenDefiningCode",
              VorhandenDefiningCode.class,
              this);

  public ListSelectAqlField<Cluster>
      ANDERE_AKTUELLE_ERKRANKUNGEN_DETAILLIERTE_ANGABEN_ZUM_SYMPTOM_ANZEICHEN =
          new ListAqlFieldImp<Cluster>(
              CoronaAnamneseComposition.class,
              "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]",
              "andereAktuelleErkrankungenDetaillierteAngabenZumSymptomAnzeichen",
              Cluster.class,
              this);

  public SelectAqlField<TemporalAccessor> ANDERE_AKTUELLE_ERKRANKUNGEN_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/time|value",
          "andereAktuelleErkrankungenTimeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> ANDERE_AKTUELLE_ERKRANKUNGEN_ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/origin|value",
          "andereAktuelleErkrankungenOriginValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> ANDERE_AKTUELLE_ERKRANKUNGEN_ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/protocol[at0007]/items[at0021]",
          "andereAktuelleErkrankungenErweiterung",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> ANDERE_AKTUELLE_ERKRANKUNGEN_SUBJECT =
      new AqlFieldImp<PartyProxy>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/subject",
          "andereAktuelleErkrankungenSubject",
          PartyProxy.class,
          this);

  public SelectAqlField<Language> ANDERE_AKTUELLE_ERKRANKUNGEN_LANGUAGE =
      new AqlFieldImp<Language>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/language",
          "andereAktuelleErkrankungenLanguage",
          Language.class,
          this);

  public SelectAqlField<String>
      CHRONISCHE_ERKRANKUNGEN_BEZEICHNUNG_DES_SYMPTOMS_ODER_ANZEICHENS_VALUE =
          new AqlFieldImp<String>(
              CoronaAnamneseComposition.class,
              "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value",
              "chronischeErkrankungenBezeichnungDesSymptomsOderAnzeichensValue",
              String.class,
              this);

  public SelectAqlField<VorhandenDefiningCode> CHRONISCHE_ERKRANKUNGEN_VORHANDEN_DEFINING_CODE =
      new AqlFieldImp<VorhandenDefiningCode>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code",
          "chronischeErkrankungenVorhandenDefiningCode",
          VorhandenDefiningCode.class,
          this);

  public ListSelectAqlField<Cluster>
      CHRONISCHE_ERKRANKUNGEN_DETAILLIERTE_ANGABEN_ZUM_SYMPTOM_ANZEICHEN =
          new ListAqlFieldImp<Cluster>(
              CoronaAnamneseComposition.class,
              "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]",
              "chronischeErkrankungenDetaillierteAngabenZumSymptomAnzeichen",
              Cluster.class,
              this);

  public SelectAqlField<TemporalAccessor> CHRONISCHE_ERKRANKUNGEN_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]/time|value",
          "chronischeErkrankungenTimeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> CHRONISCHE_ERKRANKUNGEN_ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/origin|value",
          "chronischeErkrankungenOriginValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> CHRONISCHE_ERKRANKUNGEN_ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/protocol[at0007]/items[at0021]",
          "chronischeErkrankungenErweiterung",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> CHRONISCHE_ERKRANKUNGEN_SUBJECT =
      new AqlFieldImp<PartyProxy>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/subject",
          "chronischeErkrankungenSubject",
          PartyProxy.class,
          this);

  public SelectAqlField<Language> CHRONISCHE_ERKRANKUNGEN_LANGUAGE =
      new AqlFieldImp<Language>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/language",
          "chronischeErkrankungenLanguage",
          Language.class,
          this);

  public ListSelectAqlField<ProblemDiagnoseEvaluation> PROBLEM_DIAGNOSE =
      new ListAqlFieldImp<ProblemDiagnoseEvaluation>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1]",
          "problemDiagnose",
          ProblemDiagnoseEvaluation.class,
          this);

  public SelectAqlField<DvCodedText> MEDIKAMENTE_IN_VERWENDUNG =
      new AqlFieldImp<DvCodedText>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.medication_use.v0]/data[at0022]/events[at0023]/data[at0001]/items[at0027]/value",
          "medikamenteInVerwendung",
          DvCodedText.class,
          this);

  public SelectAqlField<String> NAME_DER_MEDIKAMENTENKLASSE_VALUE =
      new AqlFieldImp<String>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.medication_use.v0]/data[at0022]/events[at0023]/data[at0001]/items[at0026]/items[at0002]/value|value",
          "nameDerMedikamentenklasseValue",
          String.class,
          this);

  public SelectAqlField<MedikamentenklasseInVerwendungDefiningCode>
      MEDIKAMENTENKLASSE_IN_VERWENDUNG_DEFINING_CODE =
          new AqlFieldImp<MedikamentenklasseInVerwendungDefiningCode>(
              CoronaAnamneseComposition.class,
              "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.medication_use.v0]/data[at0022]/events[at0023]/data[at0001]/items[at0026]/items[at0003]/value|defining_code",
              "medikamentenklasseInVerwendungDefiningCode",
              MedikamentenklasseInVerwendungDefiningCode.class,
              this);

  public ListSelectAqlField<FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster>
      SPEZIFISCHE_MEDIKAMENTE =
          new ListAqlFieldImp<FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster>(
              CoronaAnamneseComposition.class,
              "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.medication_use.v0]/data[at0022]/events[at0023]/data[at0001]/items[at0026]/items[at0008]",
              "spezifischeMedikamente",
              FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster.class,
              this);

  public SelectAqlField<TemporalAccessor> FRAGEBOGEN_ZUM_MEDIKATIONS_SCREENING_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.medication_use.v0]/data[at0022]/events[at0023]/time|value",
          "fragebogenZumMedikationsScreeningTimeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> FRAGEBOGEN_ZUM_MEDIKATIONS_SCREENING_ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.medication_use.v0]/data[at0022]/origin|value",
          "fragebogenZumMedikationsScreeningOriginValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> FRAGEBOGEN_ZUM_MEDIKATIONS_SCREENING_ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.medication_use.v0]/protocol[at0005]/items[at0019]",
          "fragebogenZumMedikationsScreeningErweiterung",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> FRAGEBOGEN_ZUM_MEDIKATIONS_SCREENING_SUBJECT =
      new AqlFieldImp<PartyProxy>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.medication_use.v0]/subject",
          "fragebogenZumMedikationsScreeningSubject",
          PartyProxy.class,
          this);

  public SelectAqlField<Language> FRAGEBOGEN_ZUM_MEDIKATIONS_SCREENING_LANGUAGE =
      new AqlFieldImp<Language>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.medication_use.v0]/language",
          "fragebogenZumMedikationsScreeningLanguage",
          Language.class,
          this);

  public SelectAqlField<String> BESCHAFTIGUNGSSTATUS_VALUE =
      new AqlFieldImp<String>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.occupation_summary.v1]/data[at0001]/items[at0004]/value|value",
          "beschaftigungsstatusValue",
          String.class,
          this);

  public ListSelectAqlField<BeschaftigungCluster> BESCHAFTIGUNG =
      new ListAqlFieldImp<BeschaftigungCluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.occupation_summary.v1]/data[at0001]/items[openEHR-EHR-CLUSTER.occupation_record.v1]",
          "beschaftigung",
          BeschaftigungCluster.class,
          this);

  public ListSelectAqlField<Cluster> ZUSATZLICHE_ANGABEN =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.occupation_summary.v1]/data[at0001]/items[at0005]",
          "zusatzlicheAngaben",
          Cluster.class,
          this);

  public ListSelectAqlField<Cluster> GESUNDHEITSBEZOGENER_BERUF_ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.occupation_summary.v1]/protocol[at0007]/items[at0008]",
          "gesundheitsbezogenerBerufErweiterung",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> GESUNDHEITSBEZOGENER_BERUF_SUBJECT =
      new AqlFieldImp<PartyProxy>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.occupation_summary.v1]/subject",
          "gesundheitsbezogenerBerufSubject",
          PartyProxy.class,
          this);

  public SelectAqlField<Language> GESUNDHEITSBEZOGENER_BERUF_LANGUAGE =
      new AqlFieldImp<Language>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.occupation_summary.v1]/language",
          "gesundheitsbezogenerBerufLanguage",
          Language.class,
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

  public SelectAqlField<String> NAME_DES_PROBLEMS_DER_DIAGNOSE_VALUE =
      new AqlFieldImp<String>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1]/data[at0001]/items[at0002]/value|value",
          "nameDesProblemsDerDiagnoseValue",
          String.class,
          this);

  public ListSelectAqlField<Cluster> ANATOMISCHE_STELLE_STRUKTURIERT =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1]/data[at0001]/items[at0039]",
          "anatomischeStelleStrukturiert",
          Cluster.class,
          this);

  public ListSelectAqlField<Cluster> SPEZIFISCHE_ANGABEN =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1]/data[at0001]/items[at0043]",
          "spezifischeAngaben",
          Cluster.class,
          this);

  public SelectAqlField<DiagnosestatusDefiningCode> DIAGNOSESTATUS_DEFINING_CODE =
      new AqlFieldImp<DiagnosestatusDefiningCode>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1]/data[at0001]/items[openEHR-EHR-CLUSTER.problem_qualifier.v1]/items[at0004]/value|defining_code",
          "diagnosestatusDefiningCode",
          DiagnosestatusDefiningCode.class,
          this);

  public SelectAqlField<String> PROBLEM_DIGANOSE_CORONOVIRUS_KOMMENTAR_VALUE =
      new AqlFieldImp<String>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1]/data[at0001]/items[at0069]/value|value",
          "problemDiganoseCoronovirusKommentarValue",
          String.class,
          this);

  public SelectAqlField<TemporalAccessor> ZULETZT_AKTUALISIERT_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1]/protocol[at0032]/items[at0070]/value|value",
          "zuletztAktualisiertValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Cluster> PROBLEM_DIGANOSE_CORONOVIRUS_ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1]/protocol[at0032]/items[at0071]",
          "problemDiganoseCoronovirusErweiterung",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> PROBLEM_DIGANOSE_CORONOVIRUS_SUBJECT =
      new AqlFieldImp<PartyProxy>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1]/subject",
          "problemDiganoseCoronovirusSubject",
          PartyProxy.class,
          this);

  public SelectAqlField<Language> PROBLEM_DIGANOSE_CORONOVIRUS_LANGUAGE =
      new AqlFieldImp<Language>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1]/language",
          "problemDiganoseCoronovirusLanguage",
          Language.class,
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

  public SelectAqlField<ProblemDiganoseCoronovirusDiagnostischeSicherheitChoice>
      DIAGNOSTISCHE_SICHERHEIT =
          new AqlFieldImp<ProblemDiganoseCoronovirusDiagnostischeSicherheitChoice>(
              CoronaAnamneseComposition.class,
              "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1]/data[at0001]/items[at0073]/value",
              "diagnostischeSicherheit",
              ProblemDiganoseCoronovirusDiagnostischeSicherheitChoice.class,
              this);

  public ListSelectAqlField<BerichtBeliebigesEreignisChoice> BELIEBIGES_EREIGNIS =
      new ListAqlFieldImp<BerichtBeliebigesEreignisChoice>(
          CoronaAnamneseComposition.class,
          "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]/data[at0001]/events[at0002]",
          "beliebigesEreignis",
          BerichtBeliebigesEreignisChoice.class,
          this);

  private CoronaAnamneseCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.report.v1");
  }

  public static CoronaAnamneseCompositionContainment getInstance() {
    return new CoronaAnamneseCompositionContainment();
  }
}
