package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;
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
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.report.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:53.051764300+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
@Template("Corona_Anamnese")
public class CoronaAnamneseComposition implements CompositionEntity {
  /**
   * Bericht/context/Bericht ID
   */
  @Path("/context/other_context[at0001]/items[at0002]/value|value")
  private String berichtIdValue;

  /**
   * Bericht/context/Status
   */
  @Path("/context/other_context[at0001]/items[at0005]/value|value")
  private String statusValue;

  /**
   * Bericht/context/Erweiterung
   */
  @Path("/context/other_context[at0001]/items[at0006]")
  private List<Cluster> berichtErweiterung;

  /**
   * Bericht/context/start_time
   */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /**
   * Bericht/context/participations
   */
  @Path("/context/participations")
  private List<Participation> participations;

  /**
   * Bericht/context/end_time
   */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /**
   * Bericht/context/location
   */
  @Path("/context/location")
  private String location;

  /**
   * Bericht/context/health_care_facility
   */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /**
   * Bericht/context/setting
   */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Bericht/Geschichte/Historie
   */
  @Path("/content[openEHR-EHR-OBSERVATION.story.v1]")
  private List<GeschichteHistorieObservation> geschichteHistorie;

  /**
   * Bericht/Symptome/Husten/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Bezeichnung des Symptoms oder Anzeichens.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Husten']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value")
  private String hustenBezeichnungDesSymptomsOderAnzeichensValue;

  /**
   * Bericht/Symptome/Husten/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Vorhanden?
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Husten']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code")
  private VorhandenDefiningCode hustenVorhandenDefiningCode;

  /**
   * Bericht/Symptome/Husten/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Detaillierte Angaben zum Symptom/Anzeichen
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Husten']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]")
  private List<Cluster> hustenDetaillierteAngabenZumSymptomAnzeichen;

  /**
   * Bericht/Symptome/Husten/Beliebiges Ereignis/time
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Husten']/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor hustenTimeValue;

  /**
   * Bericht/Symptome/Husten/origin
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Husten']/data[at0001]/origin|value")
  private TemporalAccessor hustenOriginValue;

  /**
   * Bericht/Symptome/Husten/Erweiterung
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Husten']/protocol[at0007]/items[at0021]")
  private List<Cluster> hustenErweiterung;

  /**
   * Bericht/Symptome/Husten/subject
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Husten']/subject")
  private PartyProxy hustenSubject;

  /**
   * Bericht/Symptome/Husten/language
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Husten']/language")
  private Language hustenLanguage;

  /**
   * Bericht/Symptome/Schnupfen/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Bezeichnung des Symptoms oder Anzeichens.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Schnupfen']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value")
  private String schnupfenBezeichnungDesSymptomsOderAnzeichensValue;

  /**
   * Bericht/Symptome/Schnupfen/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Vorhanden?
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Schnupfen']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code")
  private VorhandenDefiningCode schnupfenVorhandenDefiningCode;

  /**
   * Bericht/Symptome/Schnupfen/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Detaillierte Angaben zum Symptom/Anzeichen
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Schnupfen']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]")
  private List<Cluster> schnupfenDetaillierteAngabenZumSymptomAnzeichen;

  /**
   * Bericht/Symptome/Schnupfen/Beliebiges Ereignis/time
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Schnupfen']/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor schnupfenTimeValue;

  /**
   * Bericht/Symptome/Schnupfen/origin
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Schnupfen']/data[at0001]/origin|value")
  private TemporalAccessor schnupfenOriginValue;

  /**
   * Bericht/Symptome/Schnupfen/Erweiterung
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Schnupfen']/protocol[at0007]/items[at0021]")
  private List<Cluster> schnupfenErweiterung;

  /**
   * Bericht/Symptome/Schnupfen/subject
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Schnupfen']/subject")
  private PartyProxy schnupfenSubject;

  /**
   * Bericht/Symptome/Schnupfen/language
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Schnupfen']/language")
  private Language schnupfenLanguage;

  /**
   * Bericht/Symptome/Heiserkeit/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Bezeichnung des Symptoms oder Anzeichens.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Heiserkeit']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value")
  private String heiserkeitBezeichnungDesSymptomsOderAnzeichensValue;

  /**
   * Bericht/Symptome/Heiserkeit/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Vorhanden?
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Heiserkeit']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code")
  private VorhandenDefiningCode heiserkeitVorhandenDefiningCode;

  /**
   * Bericht/Symptome/Heiserkeit/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Detaillierte Angaben zum Symptom/Anzeichen
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Heiserkeit']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]")
  private List<Cluster> heiserkeitDetaillierteAngabenZumSymptomAnzeichen;

  /**
   * Bericht/Symptome/Heiserkeit/Beliebiges Ereignis/time
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Heiserkeit']/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor heiserkeitTimeValue;

  /**
   * Bericht/Symptome/Heiserkeit/origin
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Heiserkeit']/data[at0001]/origin|value")
  private TemporalAccessor heiserkeitOriginValue;

  /**
   * Bericht/Symptome/Heiserkeit/Erweiterung
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Heiserkeit']/protocol[at0007]/items[at0021]")
  private List<Cluster> heiserkeitErweiterung;

  /**
   * Bericht/Symptome/Heiserkeit/subject
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Heiserkeit']/subject")
  private PartyProxy heiserkeitSubject;

  /**
   * Bericht/Symptome/Heiserkeit/language
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Heiserkeit']/language")
  private Language heiserkeitLanguage;

  /**
   * Bericht/Symptome/Fieber oder erhöhte Körpertemperatur/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Bezeichnung des Symptoms oder Anzeichens.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Fieber oder erhöhte Körpertemperatur']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value")
  private String fieberOderErhohteKorpertemperaturBezeichnungDesSymptomsOderAnzeichensValue;

  /**
   * Bericht/Symptome/Fieber oder erhöhte Körpertemperatur/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Vorhanden?
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Fieber oder erhöhte Körpertemperatur']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code")
  private VorhandenDefiningCode fieberOderErhohteKorpertemperaturVorhandenDefiningCode;

  /**
   * Bericht/Symptome/Fieber oder erhöhte Körpertemperatur/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Detaillierte Angaben zum Symptom/Anzeichen
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Fieber oder erhöhte Körpertemperatur']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]")
  private List<Cluster> fieberOderErhohteKorpertemperaturDetaillierteAngabenZumSymptomAnzeichen;

  /**
   * Bericht/Symptome/Fieber oder erhöhte Körpertemperatur/Beliebiges Ereignis/time
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Fieber oder erhöhte Körpertemperatur']/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor fieberOderErhohteKorpertemperaturTimeValue;

  /**
   * Bericht/Symptome/Fieber oder erhöhte Körpertemperatur/origin
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Fieber oder erhöhte Körpertemperatur']/data[at0001]/origin|value")
  private TemporalAccessor fieberOderErhohteKorpertemperaturOriginValue;

  /**
   * Bericht/Symptome/Fieber oder erhöhte Körpertemperatur/Erweiterung
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Fieber oder erhöhte Körpertemperatur']/protocol[at0007]/items[at0021]")
  private List<Cluster> fieberOderErhohteKorpertemperaturErweiterung;

  /**
   * Bericht/Symptome/Fieber oder erhöhte Körpertemperatur/subject
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Fieber oder erhöhte Körpertemperatur']/subject")
  private PartyProxy fieberOderErhohteKorpertemperaturSubject;

  /**
   * Bericht/Symptome/Fieber oder erhöhte Körpertemperatur/language
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Fieber oder erhöhte Körpertemperatur']/language")
  private Language fieberOderErhohteKorpertemperaturLanguage;

  /**
   * Bericht/Symptome/Körpertemperatur/Beliebiges Ereignis/Temperatur
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude")
  private Double temperaturMagnitude;

  /**
   * Bericht/Symptome/Körpertemperatur/Beliebiges Ereignis/Temperatur
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units")
  private String temperaturUnits;

  /**
   * Bericht/Symptome/Körpertemperatur/Beliebiges Ereignis/Störfaktoren
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/data[at0002]/events[at0003]/state[at0029]/items[at0066]")
  private List<KorpertemperaturStorfaktorenElement> storfaktoren;

  /**
   * Bericht/Symptome/Körpertemperatur/Beliebiges Ereignis/Umgebungsbedingungen
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/data[at0002]/events[at0003]/state[at0029]/items[at0056]")
  private List<Cluster> umgebungsbedingungen;

  /**
   * Bericht/Symptome/Körpertemperatur/Beliebiges Ereignis/Betätigung
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/data[at0002]/events[at0003]/state[at0029]/items[at0057]")
  private Cluster betatigung;

  /**
   * Bericht/Symptome/Körpertemperatur/Beliebiges Ereignis/time
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/data[at0002]/events[at0003]/time|value")
  private TemporalAccessor korpertemperaturTimeValue;

  /**
   * Bericht/Symptome/Körpertemperatur/origin
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/data[at0002]/origin|value")
  private TemporalAccessor korpertemperaturOriginValue;

  /**
   * Bericht/Symptome/Körpertemperatur/Strukturierte Lokalisation der Messung
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/protocol[at0020]/items[at0064]")
  private List<Cluster> strukturierteLokalisationDerMessung;

  /**
   * Bericht/Symptome/Körpertemperatur/Gerät
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/protocol[at0020]/items[at0059]")
  private Cluster gerat;

  /**
   * Bericht/Symptome/Körpertemperatur/Erweiterung
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/protocol[at0020]/items[at0062]")
  private List<Cluster> korpertemperaturErweiterung;

  /**
   * Bericht/Symptome/Körpertemperatur/subject
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/subject")
  private PartyProxy korpertemperaturSubject;

  /**
   * Bericht/Symptome/Körpertemperatur/language
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.body_temperature.v2]/language")
  private Language korpertemperaturLanguage;

  /**
   * Bericht/Symptome/Gestörter Geruchssinn/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Bezeichnung des Symptoms oder Anzeichens.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geruchssinn']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value")
  private String gestorterGeruchssinnBezeichnungDesSymptomsOderAnzeichensValue;

  /**
   * Bericht/Symptome/Gestörter Geruchssinn/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Vorhanden?
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geruchssinn']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code")
  private VorhandenDefiningCode gestorterGeruchssinnVorhandenDefiningCode;

  /**
   * Bericht/Symptome/Gestörter Geruchssinn/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Detaillierte Angaben zum Symptom/Anzeichen
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geruchssinn']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]")
  private List<Cluster> gestorterGeruchssinnDetaillierteAngabenZumSymptomAnzeichen;

  /**
   * Bericht/Symptome/Gestörter Geruchssinn/Beliebiges Ereignis/time
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geruchssinn']/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor gestorterGeruchssinnTimeValue;

  /**
   * Bericht/Symptome/Gestörter Geruchssinn/origin
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geruchssinn']/data[at0001]/origin|value")
  private TemporalAccessor gestorterGeruchssinnOriginValue;

  /**
   * Bericht/Symptome/Gestörter Geruchssinn/Erweiterung
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geruchssinn']/protocol[at0007]/items[at0021]")
  private List<Cluster> gestorterGeruchssinnErweiterung;

  /**
   * Bericht/Symptome/Gestörter Geruchssinn/subject
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geruchssinn']/subject")
  private PartyProxy gestorterGeruchssinnSubject;

  /**
   * Bericht/Symptome/Gestörter Geruchssinn/language
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geruchssinn']/language")
  private Language gestorterGeruchssinnLanguage;

  /**
   * Bericht/Symptome/Gestörter Geschmackssinn/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Bezeichnung des Symptoms oder Anzeichens.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geschmackssinn']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value")
  private String gestorterGeschmackssinnBezeichnungDesSymptomsOderAnzeichensValue;

  /**
   * Bericht/Symptome/Gestörter Geschmackssinn/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Vorhanden?
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geschmackssinn']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code")
  private VorhandenDefiningCode gestorterGeschmackssinnVorhandenDefiningCode;

  /**
   * Bericht/Symptome/Gestörter Geschmackssinn/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Detaillierte Angaben zum Symptom/Anzeichen
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geschmackssinn']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]")
  private List<Cluster> gestorterGeschmackssinnDetaillierteAngabenZumSymptomAnzeichen;

  /**
   * Bericht/Symptome/Gestörter Geschmackssinn/Beliebiges Ereignis/time
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geschmackssinn']/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor gestorterGeschmackssinnTimeValue;

  /**
   * Bericht/Symptome/Gestörter Geschmackssinn/origin
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geschmackssinn']/data[at0001]/origin|value")
  private TemporalAccessor gestorterGeschmackssinnOriginValue;

  /**
   * Bericht/Symptome/Gestörter Geschmackssinn/Erweiterung
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geschmackssinn']/protocol[at0007]/items[at0021]")
  private List<Cluster> gestorterGeschmackssinnErweiterung;

  /**
   * Bericht/Symptome/Gestörter Geschmackssinn/subject
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geschmackssinn']/subject")
  private PartyProxy gestorterGeschmackssinnSubject;

  /**
   * Bericht/Symptome/Gestörter Geschmackssinn/language
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geschmackssinn']/language")
  private Language gestorterGeschmackssinnLanguage;

  /**
   * Bericht/Symptome/Durchfall/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Bezeichnung des Symptoms oder Anzeichens.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Durchfall']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value")
  private String durchfallBezeichnungDesSymptomsOderAnzeichensValue;

  /**
   * Bericht/Symptome/Durchfall/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Vorhanden?
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Durchfall']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code")
  private VorhandenDefiningCode durchfallVorhandenDefiningCode;

  /**
   * Bericht/Symptome/Durchfall/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Detaillierte Angaben zum Symptom/Anzeichen
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Durchfall']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]")
  private List<Cluster> durchfallDetaillierteAngabenZumSymptomAnzeichen;

  /**
   * Bericht/Symptome/Durchfall/Beliebiges Ereignis/time
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Durchfall']/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor durchfallTimeValue;

  /**
   * Bericht/Symptome/Durchfall/origin
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Durchfall']/data[at0001]/origin|value")
  private TemporalAccessor durchfallOriginValue;

  /**
   * Bericht/Symptome/Durchfall/Erweiterung
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Durchfall']/protocol[at0007]/items[at0021]")
  private List<Cluster> durchfallErweiterung;

  /**
   * Bericht/Symptome/Durchfall/subject
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Durchfall']/subject")
  private PartyProxy durchfallSubject;

  /**
   * Bericht/Symptome/Durchfall/language
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Durchfall']/language")
  private Language durchfallLanguage;

  /**
   * Bericht/Symptome/Weitere Symptome/origin
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Weitere Symptome']/data[at0001]/origin|value")
  private TemporalAccessor weitereSymptomeOriginValue;

  /**
   * Bericht/Symptome/Weitere Symptome/Erweiterung
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Weitere Symptome']/protocol[at0007]/items[at0021]")
  private List<Cluster> weitereSymptomeErweiterung;

  /**
   * Bericht/Symptome/Weitere Symptome/subject
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Weitere Symptome']/subject")
  private PartyProxy weitereSymptomeSubject;

  /**
   * Bericht/Symptome/Weitere Symptome/language
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Weitere Symptome']/language")
  private Language weitereSymptomeLanguage;

  /**
   * Bericht/Kontakt/Personenkontakt/Beliebiges Ereignis/*Agent (en)
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Personenkontakt']/data[at0001]/events[at0002]/data[at0042]/items[at0043]/value|value")
  private String personenkontaktAgentEnValue;

  /**
   * Bericht/Kontakt/Personenkontakt/Beliebiges Ereignis/*Presence of any exposure? (en)
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Personenkontakt']/data[at0001]/events[at0002]/data[at0042]/items[at0057]/value|defining_code")
  private PresenceOfAnyExposureEnDefiningCode personenkontaktPresenceOfAnyExposureEnDefiningCode;

  /**
   * Bericht/Kontakt/Personenkontakt/Beliebiges Ereignis/*Specific exposure (en)/*Exposure (en)
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Personenkontakt']/data[at0001]/events[at0002]/data[at0042]/items[at0044]/items[at0045]/value|value")
  private String personenkontaktExposureEnValue;

  /**
   * Bericht/Kontakt/Personenkontakt/Beliebiges Ereignis/*Specific exposure (en)/Vorhandensein
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Personenkontakt']/data[at0001]/events[at0002]/data[at0042]/items[at0044]/items[at0046]/value|defining_code")
  private VorhandenseinDefiningCode personenkontaktVorhandenseinDefiningCode;

  /**
   * Bericht/Kontakt/Personenkontakt/Beliebiges Ereignis/Kommentar
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Personenkontakt']/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value|value")
  private String personenkontaktKommentarValue;

  /**
   * Bericht/Kontakt/Personenkontakt/Beliebiges Ereignis/time
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Personenkontakt']/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor personenkontaktTimeValue;

  /**
   * Bericht/Kontakt/Personenkontakt/origin
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Personenkontakt']/data[at0001]/origin|value")
  private TemporalAccessor personenkontaktOriginValue;

  /**
   * Bericht/Kontakt/Personenkontakt/Erweiterung
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Personenkontakt']/protocol[at0004]/items[at0056]")
  private List<Cluster> personenkontaktErweiterung;

  /**
   * Bericht/Kontakt/Personenkontakt/subject
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Personenkontakt']/subject")
  private PartyProxy personenkontaktSubject;

  /**
   * Bericht/Kontakt/Personenkontakt/language
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Personenkontakt']/language")
  private Language personenkontaktLanguage;

  /**
   * Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/Beliebiges Ereignis/*Agent (en)
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Aufenthalt in Gesundheitseinrichtung']/data[at0001]/events[at0002]/data[at0042]/items[at0043]/value|value")
  private String aufenthaltInGesundheitseinrichtungAgentEnValue;

  /**
   * Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/Beliebiges Ereignis/*Presence of any exposure? (en)
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Aufenthalt in Gesundheitseinrichtung']/data[at0001]/events[at0002]/data[at0042]/items[at0057]/value|defining_code")
  private PresenceOfAnyExposureEnDefiningCode aufenthaltInGesundheitseinrichtungPresenceOfAnyExposureEnDefiningCode;

  /**
   * Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/Beliebiges Ereignis/*Specific exposure (en)/*Exposure (en)
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Aufenthalt in Gesundheitseinrichtung']/data[at0001]/events[at0002]/data[at0042]/items[at0044]/items[at0045]/value|value")
  private String aufenthaltInGesundheitseinrichtungExposureEnValue;

  /**
   * Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/Beliebiges Ereignis/*Specific exposure (en)/Vorhandensein
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Aufenthalt in Gesundheitseinrichtung']/data[at0001]/events[at0002]/data[at0042]/items[at0044]/items[at0046]/value|defining_code")
  private VorhandenseinDefiningCode aufenthaltInGesundheitseinrichtungVorhandenseinDefiningCode;

  /**
   * Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/Beliebiges Ereignis/Kommentar
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Aufenthalt in Gesundheitseinrichtung']/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value|value")
  private String aufenthaltInGesundheitseinrichtungKommentarValue;

  /**
   * Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/Beliebiges Ereignis/time
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Aufenthalt in Gesundheitseinrichtung']/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor aufenthaltInGesundheitseinrichtungTimeValue;

  /**
   * Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/origin
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Aufenthalt in Gesundheitseinrichtung']/data[at0001]/origin|value")
  private TemporalAccessor aufenthaltInGesundheitseinrichtungOriginValue;

  /**
   * Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/Erweiterung
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Aufenthalt in Gesundheitseinrichtung']/protocol[at0004]/items[at0056]")
  private List<Cluster> aufenthaltInGesundheitseinrichtungErweiterung;

  /**
   * Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/subject
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Aufenthalt in Gesundheitseinrichtung']/subject")
  private PartyProxy aufenthaltInGesundheitseinrichtungSubject;

  /**
   * Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung/language
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Aufenthalt in Gesundheitseinrichtung']/language")
  private Language aufenthaltInGesundheitseinrichtungLanguage;

  /**
   * Bericht/Risikogebiet/Historie der Reise
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Risikogebiet']/items[openEHR-EHR-OBSERVATION.travel_history.v0]")
  private List<HistorieDerReiseObservation> historieDerReise;

  /**
   * Bericht/Risikogebiet/Reisefall
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Risikogebiet']/items[openEHR-EHR-OBSERVATION.travel_event.v0]")
  private List<ReisefallObservation> reisefall;

  /**
   * Bericht/Allgemeine Angaben/Andere aktuelle Erkrankungen/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Bezeichnung des Symptoms oder Anzeichens.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Andere aktuelle Erkrankungen']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value")
  private String andereAktuelleErkrankungenBezeichnungDesSymptomsOderAnzeichensValue;

  /**
   * Bericht/Allgemeine Angaben/Andere aktuelle Erkrankungen/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Vorhanden?
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Andere aktuelle Erkrankungen']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code")
  private VorhandenDefiningCode andereAktuelleErkrankungenVorhandenDefiningCode;

  /**
   * Bericht/Allgemeine Angaben/Andere aktuelle Erkrankungen/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Detaillierte Angaben zum Symptom/Anzeichen
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Andere aktuelle Erkrankungen']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]")
  private List<Cluster> andereAktuelleErkrankungenDetaillierteAngabenZumSymptomAnzeichen;

  /**
   * Bericht/Allgemeine Angaben/Andere aktuelle Erkrankungen/Beliebiges Ereignis/time
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Andere aktuelle Erkrankungen']/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor andereAktuelleErkrankungenTimeValue;

  /**
   * Bericht/Allgemeine Angaben/Andere aktuelle Erkrankungen/origin
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Andere aktuelle Erkrankungen']/data[at0001]/origin|value")
  private TemporalAccessor andereAktuelleErkrankungenOriginValue;

  /**
   * Bericht/Allgemeine Angaben/Andere aktuelle Erkrankungen/Erweiterung
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Andere aktuelle Erkrankungen']/protocol[at0007]/items[at0021]")
  private List<Cluster> andereAktuelleErkrankungenErweiterung;

  /**
   * Bericht/Allgemeine Angaben/Andere aktuelle Erkrankungen/subject
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Andere aktuelle Erkrankungen']/subject")
  private PartyProxy andereAktuelleErkrankungenSubject;

  /**
   * Bericht/Allgemeine Angaben/Andere aktuelle Erkrankungen/language
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Andere aktuelle Erkrankungen']/language")
  private Language andereAktuelleErkrankungenLanguage;

  /**
   * Bericht/Allgemeine Angaben/Chronische Erkrankungen/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Bezeichnung des Symptoms oder Anzeichens.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Chronische Erkrankungen']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value")
  private String chronischeErkrankungenBezeichnungDesSymptomsOderAnzeichensValue;

  /**
   * Bericht/Allgemeine Angaben/Chronische Erkrankungen/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Vorhanden?
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Chronische Erkrankungen']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code")
  private VorhandenDefiningCode chronischeErkrankungenVorhandenDefiningCode;

  /**
   * Bericht/Allgemeine Angaben/Chronische Erkrankungen/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Detaillierte Angaben zum Symptom/Anzeichen
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Chronische Erkrankungen']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]")
  private List<Cluster> chronischeErkrankungenDetaillierteAngabenZumSymptomAnzeichen;

  /**
   * Bericht/Allgemeine Angaben/Chronische Erkrankungen/Beliebiges Ereignis/time
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Chronische Erkrankungen']/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor chronischeErkrankungenTimeValue;

  /**
   * Bericht/Allgemeine Angaben/Chronische Erkrankungen/origin
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Chronische Erkrankungen']/data[at0001]/origin|value")
  private TemporalAccessor chronischeErkrankungenOriginValue;

  /**
   * Bericht/Allgemeine Angaben/Chronische Erkrankungen/Erweiterung
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Chronische Erkrankungen']/protocol[at0007]/items[at0021]")
  private List<Cluster> chronischeErkrankungenErweiterung;

  /**
   * Bericht/Allgemeine Angaben/Chronische Erkrankungen/subject
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Chronische Erkrankungen']/subject")
  private PartyProxy chronischeErkrankungenSubject;

  /**
   * Bericht/Allgemeine Angaben/Chronische Erkrankungen/language
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Chronische Erkrankungen']/language")
  private Language chronischeErkrankungenLanguage;

  /**
   * Bericht/Allgemeine Angaben/Problem/Diagnose
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1]")
  private List<ProblemDiagnoseEvaluation> problemDiagnose;

  /**
   * Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges Ereignis/Medikamente in Verwendung?
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.medication_use.v0]/data[at0022]/events[at0023]/data[at0001]/items[at0027]/value")
  private DvCodedText medikamenteInVerwendung;

  /**
   * Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges Ereignis/Spezifische Medikamentenklasse/Name der Medikamentenklasse
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.medication_use.v0]/data[at0022]/events[at0023]/data[at0001]/items[at0026]/items[at0002]/value|value")
  private String nameDerMedikamentenklasseValue;

  /**
   * Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges Ereignis/Spezifische Medikamentenklasse/Medikamentenklasse in Verwendung?
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.medication_use.v0]/data[at0022]/events[at0023]/data[at0001]/items[at0026]/items[at0003]/value|defining_code")
  private MedikamentenklasseInVerwendungDefiningCode medikamentenklasseInVerwendungDefiningCode;

  /**
   * Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges Ereignis/Spezifische Medikamentenklasse/Spezifische Medikamente
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.medication_use.v0]/data[at0022]/events[at0023]/data[at0001]/items[at0026]/items[at0008]")
  private List<FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster> spezifischeMedikamente;

  /**
   * Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges Ereignis/time
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.medication_use.v0]/data[at0022]/events[at0023]/time|value")
  private TemporalAccessor fragebogenZumMedikationsScreeningTimeValue;

  /**
   * Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/origin
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.medication_use.v0]/data[at0022]/origin|value")
  private TemporalAccessor fragebogenZumMedikationsScreeningOriginValue;

  /**
   * Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Erweiterung
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.medication_use.v0]/protocol[at0005]/items[at0019]")
  private List<Cluster> fragebogenZumMedikationsScreeningErweiterung;

  /**
   * Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/subject
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.medication_use.v0]/subject")
  private PartyProxy fragebogenZumMedikationsScreeningSubject;

  /**
   * Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/language
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.medication_use.v0]/language")
  private Language fragebogenZumMedikationsScreeningLanguage;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigungsstatus
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.occupation_summary.v1 and name/value='Gesundheitsbezogener Beruf']/data[at0001]/items[at0004]/value|value")
  private String beschaftigungsstatusValue;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.occupation_summary.v1 and name/value='Gesundheitsbezogener Beruf']/data[at0001]/items[openEHR-EHR-CLUSTER.occupation_record.v1]")
  private List<BeschaftigungCluster> beschaftigung;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Zusätzliche Angaben
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.occupation_summary.v1 and name/value='Gesundheitsbezogener Beruf']/data[at0001]/items[at0005]")
  private List<Cluster> zusatzlicheAngaben;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Erweiterung
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.occupation_summary.v1 and name/value='Gesundheitsbezogener Beruf']/protocol[at0007]/items[at0008]")
  private List<Cluster> gesundheitsbezogenerBerufErweiterung;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/subject
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.occupation_summary.v1 and name/value='Gesundheitsbezogener Beruf']/subject")
  private PartyProxy gesundheitsbezogenerBerufSubject;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/language
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.occupation_summary.v1 and name/value='Gesundheitsbezogener Beruf']/language")
  private Language gesundheitsbezogenerBerufLanguage;

  /**
   * Bericht/Allgemeine Angaben/Wohnsituation
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.living_arrangement.v0]")
  private List<WohnsituationEvaluation> wohnsituation;

  /**
   * Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.health_risk.v1]")
  private List<BewertungDesGesundheitsrisikosEvaluation> bewertungDesGesundheitsrisikos;

  /**
   * Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/Name des Problems/ der Diagnose
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1 and name/value='Problem/Diganose Coronovirus']/data[at0001]/items[at0002]/value|value")
  private String nameDesProblemsDerDiagnoseValue;

  /**
   * Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/Anatomische Stelle (strukturiert)
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1 and name/value='Problem/Diganose Coronovirus']/data[at0001]/items[at0039]")
  private List<Cluster> anatomischeStelleStrukturiert;

  /**
   * Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/Spezifische Angaben
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1 and name/value='Problem/Diganose Coronovirus']/data[at0001]/items[at0043]")
  private List<Cluster> spezifischeAngaben;

  /**
   * Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/Status/Diagnosestatus
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1 and name/value='Problem/Diganose Coronovirus']/data[at0001]/items[openEHR-EHR-CLUSTER.problem_qualifier.v1 and name/value='Status']/items[at0004]/value|defining_code")
  private DiagnosestatusDefiningCode diagnosestatusDefiningCode;

  /**
   * Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/Kommentar
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1 and name/value='Problem/Diganose Coronovirus']/data[at0001]/items[at0069]/value|value")
  private String problemDiganoseCoronovirusKommentarValue;

  /**
   * Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/Zuletzt aktualisiert
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1 and name/value='Problem/Diganose Coronovirus']/protocol[at0032]/items[at0070]/value|value")
  private TemporalAccessor zuletztAktualisiertValue;

  /**
   * Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/Erweiterung
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1 and name/value='Problem/Diganose Coronovirus']/protocol[at0032]/items[at0071]")
  private List<Cluster> problemDiganoseCoronovirusErweiterung;

  /**
   * Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/subject
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1 and name/value='Problem/Diganose Coronovirus']/subject")
  private PartyProxy problemDiganoseCoronovirusSubject;

  /**
   * Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/language
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1 and name/value='Problem/Diganose Coronovirus']/language")
  private Language problemDiganoseCoronovirusLanguage;

  /**
   * Bericht/Allgemeine Angaben/Dienstleistung
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-INSTRUCTION.service_request.v1]")
  private List<DienstleistungInstruction> dienstleistung;

  /**
   * Bericht/composer
   */
  @Path("/composer")
  private PartyProxy composer;

  /**
   * Bericht/language
   */
  @Path("/language")
  private Language language;

  /**
   * Bericht/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Bericht/category
   */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /**
   * Bericht/territory
   */
  @Path("/territory")
  private Territory territory;

  /**
   * Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/value
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1 and name/value='Problem/Diganose Coronovirus']/data[at0001]/items[at0073]/value")
  @Choice
  private ProblemDiganoseCoronovirusDiagnostischeSicherheitChoice diagnostischeSicherheit;

  /**
   * Bericht/Beliebiges Ereignis
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Weitere Symptome']/data[at0001]/events[at0002]")
  @Choice
  private List<BerichtBeliebigesEreignisChoice> beliebigesEreignis;

  @Id
  private VersionUid versionUid;

  public void setBerichtIdValue(String berichtIdValue) {
     this.berichtIdValue = berichtIdValue;
  }

  public String getBerichtIdValue() {
     return this.berichtIdValue ;
  }

  public void setStatusValue(String statusValue) {
     this.statusValue = statusValue;
  }

  public String getStatusValue() {
     return this.statusValue ;
  }

  public void setBerichtErweiterung(List<Cluster> berichtErweiterung) {
     this.berichtErweiterung = berichtErweiterung;
  }

  public List<Cluster> getBerichtErweiterung() {
     return this.berichtErweiterung ;
  }

  public void setStartTimeValue(TemporalAccessor startTimeValue) {
     this.startTimeValue = startTimeValue;
  }

  public TemporalAccessor getStartTimeValue() {
     return this.startTimeValue ;
  }

  public void setParticipations(List<Participation> participations) {
     this.participations = participations;
  }

  public List<Participation> getParticipations() {
     return this.participations ;
  }

  public void setEndTimeValue(TemporalAccessor endTimeValue) {
     this.endTimeValue = endTimeValue;
  }

  public TemporalAccessor getEndTimeValue() {
     return this.endTimeValue ;
  }

  public void setLocation(String location) {
     this.location = location;
  }

  public String getLocation() {
     return this.location ;
  }

  public void setHealthCareFacility(PartyIdentified healthCareFacility) {
     this.healthCareFacility = healthCareFacility;
  }

  public PartyIdentified getHealthCareFacility() {
     return this.healthCareFacility ;
  }

  public void setSettingDefiningCode(Setting settingDefiningCode) {
     this.settingDefiningCode = settingDefiningCode;
  }

  public Setting getSettingDefiningCode() {
     return this.settingDefiningCode ;
  }

  public void setGeschichteHistorie(List<GeschichteHistorieObservation> geschichteHistorie) {
     this.geschichteHistorie = geschichteHistorie;
  }

  public List<GeschichteHistorieObservation> getGeschichteHistorie() {
     return this.geschichteHistorie ;
  }

  public void setHustenBezeichnungDesSymptomsOderAnzeichensValue(
      String hustenBezeichnungDesSymptomsOderAnzeichensValue) {
     this.hustenBezeichnungDesSymptomsOderAnzeichensValue = hustenBezeichnungDesSymptomsOderAnzeichensValue;
  }

  public String getHustenBezeichnungDesSymptomsOderAnzeichensValue() {
     return this.hustenBezeichnungDesSymptomsOderAnzeichensValue ;
  }

  public void setHustenVorhandenDefiningCode(VorhandenDefiningCode hustenVorhandenDefiningCode) {
     this.hustenVorhandenDefiningCode = hustenVorhandenDefiningCode;
  }

  public VorhandenDefiningCode getHustenVorhandenDefiningCode() {
     return this.hustenVorhandenDefiningCode ;
  }

  public void setHustenDetaillierteAngabenZumSymptomAnzeichen(
      List<Cluster> hustenDetaillierteAngabenZumSymptomAnzeichen) {
     this.hustenDetaillierteAngabenZumSymptomAnzeichen = hustenDetaillierteAngabenZumSymptomAnzeichen;
  }

  public List<Cluster> getHustenDetaillierteAngabenZumSymptomAnzeichen() {
     return this.hustenDetaillierteAngabenZumSymptomAnzeichen ;
  }

  public void setHustenTimeValue(TemporalAccessor hustenTimeValue) {
     this.hustenTimeValue = hustenTimeValue;
  }

  public TemporalAccessor getHustenTimeValue() {
     return this.hustenTimeValue ;
  }

  public void setHustenOriginValue(TemporalAccessor hustenOriginValue) {
     this.hustenOriginValue = hustenOriginValue;
  }

  public TemporalAccessor getHustenOriginValue() {
     return this.hustenOriginValue ;
  }

  public void setHustenErweiterung(List<Cluster> hustenErweiterung) {
     this.hustenErweiterung = hustenErweiterung;
  }

  public List<Cluster> getHustenErweiterung() {
     return this.hustenErweiterung ;
  }

  public void setHustenSubject(PartyProxy hustenSubject) {
     this.hustenSubject = hustenSubject;
  }

  public PartyProxy getHustenSubject() {
     return this.hustenSubject ;
  }

  public void setHustenLanguage(Language hustenLanguage) {
     this.hustenLanguage = hustenLanguage;
  }

  public Language getHustenLanguage() {
     return this.hustenLanguage ;
  }

  public void setSchnupfenBezeichnungDesSymptomsOderAnzeichensValue(
      String schnupfenBezeichnungDesSymptomsOderAnzeichensValue) {
     this.schnupfenBezeichnungDesSymptomsOderAnzeichensValue = schnupfenBezeichnungDesSymptomsOderAnzeichensValue;
  }

  public String getSchnupfenBezeichnungDesSymptomsOderAnzeichensValue() {
     return this.schnupfenBezeichnungDesSymptomsOderAnzeichensValue ;
  }

  public void setSchnupfenVorhandenDefiningCode(
      VorhandenDefiningCode schnupfenVorhandenDefiningCode) {
     this.schnupfenVorhandenDefiningCode = schnupfenVorhandenDefiningCode;
  }

  public VorhandenDefiningCode getSchnupfenVorhandenDefiningCode() {
     return this.schnupfenVorhandenDefiningCode ;
  }

  public void setSchnupfenDetaillierteAngabenZumSymptomAnzeichen(
      List<Cluster> schnupfenDetaillierteAngabenZumSymptomAnzeichen) {
     this.schnupfenDetaillierteAngabenZumSymptomAnzeichen = schnupfenDetaillierteAngabenZumSymptomAnzeichen;
  }

  public List<Cluster> getSchnupfenDetaillierteAngabenZumSymptomAnzeichen() {
     return this.schnupfenDetaillierteAngabenZumSymptomAnzeichen ;
  }

  public void setSchnupfenTimeValue(TemporalAccessor schnupfenTimeValue) {
     this.schnupfenTimeValue = schnupfenTimeValue;
  }

  public TemporalAccessor getSchnupfenTimeValue() {
     return this.schnupfenTimeValue ;
  }

  public void setSchnupfenOriginValue(TemporalAccessor schnupfenOriginValue) {
     this.schnupfenOriginValue = schnupfenOriginValue;
  }

  public TemporalAccessor getSchnupfenOriginValue() {
     return this.schnupfenOriginValue ;
  }

  public void setSchnupfenErweiterung(List<Cluster> schnupfenErweiterung) {
     this.schnupfenErweiterung = schnupfenErweiterung;
  }

  public List<Cluster> getSchnupfenErweiterung() {
     return this.schnupfenErweiterung ;
  }

  public void setSchnupfenSubject(PartyProxy schnupfenSubject) {
     this.schnupfenSubject = schnupfenSubject;
  }

  public PartyProxy getSchnupfenSubject() {
     return this.schnupfenSubject ;
  }

  public void setSchnupfenLanguage(Language schnupfenLanguage) {
     this.schnupfenLanguage = schnupfenLanguage;
  }

  public Language getSchnupfenLanguage() {
     return this.schnupfenLanguage ;
  }

  public void setHeiserkeitBezeichnungDesSymptomsOderAnzeichensValue(
      String heiserkeitBezeichnungDesSymptomsOderAnzeichensValue) {
     this.heiserkeitBezeichnungDesSymptomsOderAnzeichensValue = heiserkeitBezeichnungDesSymptomsOderAnzeichensValue;
  }

  public String getHeiserkeitBezeichnungDesSymptomsOderAnzeichensValue() {
     return this.heiserkeitBezeichnungDesSymptomsOderAnzeichensValue ;
  }

  public void setHeiserkeitVorhandenDefiningCode(
      VorhandenDefiningCode heiserkeitVorhandenDefiningCode) {
     this.heiserkeitVorhandenDefiningCode = heiserkeitVorhandenDefiningCode;
  }

  public VorhandenDefiningCode getHeiserkeitVorhandenDefiningCode() {
     return this.heiserkeitVorhandenDefiningCode ;
  }

  public void setHeiserkeitDetaillierteAngabenZumSymptomAnzeichen(
      List<Cluster> heiserkeitDetaillierteAngabenZumSymptomAnzeichen) {
     this.heiserkeitDetaillierteAngabenZumSymptomAnzeichen = heiserkeitDetaillierteAngabenZumSymptomAnzeichen;
  }

  public List<Cluster> getHeiserkeitDetaillierteAngabenZumSymptomAnzeichen() {
     return this.heiserkeitDetaillierteAngabenZumSymptomAnzeichen ;
  }

  public void setHeiserkeitTimeValue(TemporalAccessor heiserkeitTimeValue) {
     this.heiserkeitTimeValue = heiserkeitTimeValue;
  }

  public TemporalAccessor getHeiserkeitTimeValue() {
     return this.heiserkeitTimeValue ;
  }

  public void setHeiserkeitOriginValue(TemporalAccessor heiserkeitOriginValue) {
     this.heiserkeitOriginValue = heiserkeitOriginValue;
  }

  public TemporalAccessor getHeiserkeitOriginValue() {
     return this.heiserkeitOriginValue ;
  }

  public void setHeiserkeitErweiterung(List<Cluster> heiserkeitErweiterung) {
     this.heiserkeitErweiterung = heiserkeitErweiterung;
  }

  public List<Cluster> getHeiserkeitErweiterung() {
     return this.heiserkeitErweiterung ;
  }

  public void setHeiserkeitSubject(PartyProxy heiserkeitSubject) {
     this.heiserkeitSubject = heiserkeitSubject;
  }

  public PartyProxy getHeiserkeitSubject() {
     return this.heiserkeitSubject ;
  }

  public void setHeiserkeitLanguage(Language heiserkeitLanguage) {
     this.heiserkeitLanguage = heiserkeitLanguage;
  }

  public Language getHeiserkeitLanguage() {
     return this.heiserkeitLanguage ;
  }

  public void setFieberOderErhohteKorpertemperaturBezeichnungDesSymptomsOderAnzeichensValue(
      String fieberOderErhohteKorpertemperaturBezeichnungDesSymptomsOderAnzeichensValue) {
     this.fieberOderErhohteKorpertemperaturBezeichnungDesSymptomsOderAnzeichensValue = fieberOderErhohteKorpertemperaturBezeichnungDesSymptomsOderAnzeichensValue;
  }

  public String getFieberOderErhohteKorpertemperaturBezeichnungDesSymptomsOderAnzeichensValue() {
     return this.fieberOderErhohteKorpertemperaturBezeichnungDesSymptomsOderAnzeichensValue ;
  }

  public void setFieberOderErhohteKorpertemperaturVorhandenDefiningCode(
      VorhandenDefiningCode fieberOderErhohteKorpertemperaturVorhandenDefiningCode) {
     this.fieberOderErhohteKorpertemperaturVorhandenDefiningCode = fieberOderErhohteKorpertemperaturVorhandenDefiningCode;
  }

  public VorhandenDefiningCode getFieberOderErhohteKorpertemperaturVorhandenDefiningCode() {
     return this.fieberOderErhohteKorpertemperaturVorhandenDefiningCode ;
  }

  public void setFieberOderErhohteKorpertemperaturDetaillierteAngabenZumSymptomAnzeichen(
      List<Cluster> fieberOderErhohteKorpertemperaturDetaillierteAngabenZumSymptomAnzeichen) {
     this.fieberOderErhohteKorpertemperaturDetaillierteAngabenZumSymptomAnzeichen = fieberOderErhohteKorpertemperaturDetaillierteAngabenZumSymptomAnzeichen;
  }

  public List<Cluster> getFieberOderErhohteKorpertemperaturDetaillierteAngabenZumSymptomAnzeichen(
      ) {
     return this.fieberOderErhohteKorpertemperaturDetaillierteAngabenZumSymptomAnzeichen ;
  }

  public void setFieberOderErhohteKorpertemperaturTimeValue(
      TemporalAccessor fieberOderErhohteKorpertemperaturTimeValue) {
     this.fieberOderErhohteKorpertemperaturTimeValue = fieberOderErhohteKorpertemperaturTimeValue;
  }

  public TemporalAccessor getFieberOderErhohteKorpertemperaturTimeValue() {
     return this.fieberOderErhohteKorpertemperaturTimeValue ;
  }

  public void setFieberOderErhohteKorpertemperaturOriginValue(
      TemporalAccessor fieberOderErhohteKorpertemperaturOriginValue) {
     this.fieberOderErhohteKorpertemperaturOriginValue = fieberOderErhohteKorpertemperaturOriginValue;
  }

  public TemporalAccessor getFieberOderErhohteKorpertemperaturOriginValue() {
     return this.fieberOderErhohteKorpertemperaturOriginValue ;
  }

  public void setFieberOderErhohteKorpertemperaturErweiterung(
      List<Cluster> fieberOderErhohteKorpertemperaturErweiterung) {
     this.fieberOderErhohteKorpertemperaturErweiterung = fieberOderErhohteKorpertemperaturErweiterung;
  }

  public List<Cluster> getFieberOderErhohteKorpertemperaturErweiterung() {
     return this.fieberOderErhohteKorpertemperaturErweiterung ;
  }

  public void setFieberOderErhohteKorpertemperaturSubject(
      PartyProxy fieberOderErhohteKorpertemperaturSubject) {
     this.fieberOderErhohteKorpertemperaturSubject = fieberOderErhohteKorpertemperaturSubject;
  }

  public PartyProxy getFieberOderErhohteKorpertemperaturSubject() {
     return this.fieberOderErhohteKorpertemperaturSubject ;
  }

  public void setFieberOderErhohteKorpertemperaturLanguage(
      Language fieberOderErhohteKorpertemperaturLanguage) {
     this.fieberOderErhohteKorpertemperaturLanguage = fieberOderErhohteKorpertemperaturLanguage;
  }

  public Language getFieberOderErhohteKorpertemperaturLanguage() {
     return this.fieberOderErhohteKorpertemperaturLanguage ;
  }

  public void setTemperaturMagnitude(Double temperaturMagnitude) {
     this.temperaturMagnitude = temperaturMagnitude;
  }

  public Double getTemperaturMagnitude() {
     return this.temperaturMagnitude ;
  }

  public void setTemperaturUnits(String temperaturUnits) {
     this.temperaturUnits = temperaturUnits;
  }

  public String getTemperaturUnits() {
     return this.temperaturUnits ;
  }

  public void setStorfaktoren(List<KorpertemperaturStorfaktorenElement> storfaktoren) {
     this.storfaktoren = storfaktoren;
  }

  public List<KorpertemperaturStorfaktorenElement> getStorfaktoren() {
     return this.storfaktoren ;
  }

  public void setUmgebungsbedingungen(List<Cluster> umgebungsbedingungen) {
     this.umgebungsbedingungen = umgebungsbedingungen;
  }

  public List<Cluster> getUmgebungsbedingungen() {
     return this.umgebungsbedingungen ;
  }

  public void setBetatigung(Cluster betatigung) {
     this.betatigung = betatigung;
  }

  public Cluster getBetatigung() {
     return this.betatigung ;
  }

  public void setKorpertemperaturTimeValue(TemporalAccessor korpertemperaturTimeValue) {
     this.korpertemperaturTimeValue = korpertemperaturTimeValue;
  }

  public TemporalAccessor getKorpertemperaturTimeValue() {
     return this.korpertemperaturTimeValue ;
  }

  public void setKorpertemperaturOriginValue(TemporalAccessor korpertemperaturOriginValue) {
     this.korpertemperaturOriginValue = korpertemperaturOriginValue;
  }

  public TemporalAccessor getKorpertemperaturOriginValue() {
     return this.korpertemperaturOriginValue ;
  }

  public void setStrukturierteLokalisationDerMessung(
      List<Cluster> strukturierteLokalisationDerMessung) {
     this.strukturierteLokalisationDerMessung = strukturierteLokalisationDerMessung;
  }

  public List<Cluster> getStrukturierteLokalisationDerMessung() {
     return this.strukturierteLokalisationDerMessung ;
  }

  public void setGerat(Cluster gerat) {
     this.gerat = gerat;
  }

  public Cluster getGerat() {
     return this.gerat ;
  }

  public void setKorpertemperaturErweiterung(List<Cluster> korpertemperaturErweiterung) {
     this.korpertemperaturErweiterung = korpertemperaturErweiterung;
  }

  public List<Cluster> getKorpertemperaturErweiterung() {
     return this.korpertemperaturErweiterung ;
  }

  public void setKorpertemperaturSubject(PartyProxy korpertemperaturSubject) {
     this.korpertemperaturSubject = korpertemperaturSubject;
  }

  public PartyProxy getKorpertemperaturSubject() {
     return this.korpertemperaturSubject ;
  }

  public void setKorpertemperaturLanguage(Language korpertemperaturLanguage) {
     this.korpertemperaturLanguage = korpertemperaturLanguage;
  }

  public Language getKorpertemperaturLanguage() {
     return this.korpertemperaturLanguage ;
  }

  public void setGestorterGeruchssinnBezeichnungDesSymptomsOderAnzeichensValue(
      String gestorterGeruchssinnBezeichnungDesSymptomsOderAnzeichensValue) {
     this.gestorterGeruchssinnBezeichnungDesSymptomsOderAnzeichensValue = gestorterGeruchssinnBezeichnungDesSymptomsOderAnzeichensValue;
  }

  public String getGestorterGeruchssinnBezeichnungDesSymptomsOderAnzeichensValue() {
     return this.gestorterGeruchssinnBezeichnungDesSymptomsOderAnzeichensValue ;
  }

  public void setGestorterGeruchssinnVorhandenDefiningCode(
      VorhandenDefiningCode gestorterGeruchssinnVorhandenDefiningCode) {
     this.gestorterGeruchssinnVorhandenDefiningCode = gestorterGeruchssinnVorhandenDefiningCode;
  }

  public VorhandenDefiningCode getGestorterGeruchssinnVorhandenDefiningCode() {
     return this.gestorterGeruchssinnVorhandenDefiningCode ;
  }

  public void setGestorterGeruchssinnDetaillierteAngabenZumSymptomAnzeichen(
      List<Cluster> gestorterGeruchssinnDetaillierteAngabenZumSymptomAnzeichen) {
     this.gestorterGeruchssinnDetaillierteAngabenZumSymptomAnzeichen = gestorterGeruchssinnDetaillierteAngabenZumSymptomAnzeichen;
  }

  public List<Cluster> getGestorterGeruchssinnDetaillierteAngabenZumSymptomAnzeichen() {
     return this.gestorterGeruchssinnDetaillierteAngabenZumSymptomAnzeichen ;
  }

  public void setGestorterGeruchssinnTimeValue(TemporalAccessor gestorterGeruchssinnTimeValue) {
     this.gestorterGeruchssinnTimeValue = gestorterGeruchssinnTimeValue;
  }

  public TemporalAccessor getGestorterGeruchssinnTimeValue() {
     return this.gestorterGeruchssinnTimeValue ;
  }

  public void setGestorterGeruchssinnOriginValue(TemporalAccessor gestorterGeruchssinnOriginValue) {
     this.gestorterGeruchssinnOriginValue = gestorterGeruchssinnOriginValue;
  }

  public TemporalAccessor getGestorterGeruchssinnOriginValue() {
     return this.gestorterGeruchssinnOriginValue ;
  }

  public void setGestorterGeruchssinnErweiterung(List<Cluster> gestorterGeruchssinnErweiterung) {
     this.gestorterGeruchssinnErweiterung = gestorterGeruchssinnErweiterung;
  }

  public List<Cluster> getGestorterGeruchssinnErweiterung() {
     return this.gestorterGeruchssinnErweiterung ;
  }

  public void setGestorterGeruchssinnSubject(PartyProxy gestorterGeruchssinnSubject) {
     this.gestorterGeruchssinnSubject = gestorterGeruchssinnSubject;
  }

  public PartyProxy getGestorterGeruchssinnSubject() {
     return this.gestorterGeruchssinnSubject ;
  }

  public void setGestorterGeruchssinnLanguage(Language gestorterGeruchssinnLanguage) {
     this.gestorterGeruchssinnLanguage = gestorterGeruchssinnLanguage;
  }

  public Language getGestorterGeruchssinnLanguage() {
     return this.gestorterGeruchssinnLanguage ;
  }

  public void setGestorterGeschmackssinnBezeichnungDesSymptomsOderAnzeichensValue(
      String gestorterGeschmackssinnBezeichnungDesSymptomsOderAnzeichensValue) {
     this.gestorterGeschmackssinnBezeichnungDesSymptomsOderAnzeichensValue = gestorterGeschmackssinnBezeichnungDesSymptomsOderAnzeichensValue;
  }

  public String getGestorterGeschmackssinnBezeichnungDesSymptomsOderAnzeichensValue() {
     return this.gestorterGeschmackssinnBezeichnungDesSymptomsOderAnzeichensValue ;
  }

  public void setGestorterGeschmackssinnVorhandenDefiningCode(
      VorhandenDefiningCode gestorterGeschmackssinnVorhandenDefiningCode) {
     this.gestorterGeschmackssinnVorhandenDefiningCode = gestorterGeschmackssinnVorhandenDefiningCode;
  }

  public VorhandenDefiningCode getGestorterGeschmackssinnVorhandenDefiningCode() {
     return this.gestorterGeschmackssinnVorhandenDefiningCode ;
  }

  public void setGestorterGeschmackssinnDetaillierteAngabenZumSymptomAnzeichen(
      List<Cluster> gestorterGeschmackssinnDetaillierteAngabenZumSymptomAnzeichen) {
     this.gestorterGeschmackssinnDetaillierteAngabenZumSymptomAnzeichen = gestorterGeschmackssinnDetaillierteAngabenZumSymptomAnzeichen;
  }

  public List<Cluster> getGestorterGeschmackssinnDetaillierteAngabenZumSymptomAnzeichen() {
     return this.gestorterGeschmackssinnDetaillierteAngabenZumSymptomAnzeichen ;
  }

  public void setGestorterGeschmackssinnTimeValue(
      TemporalAccessor gestorterGeschmackssinnTimeValue) {
     this.gestorterGeschmackssinnTimeValue = gestorterGeschmackssinnTimeValue;
  }

  public TemporalAccessor getGestorterGeschmackssinnTimeValue() {
     return this.gestorterGeschmackssinnTimeValue ;
  }

  public void setGestorterGeschmackssinnOriginValue(
      TemporalAccessor gestorterGeschmackssinnOriginValue) {
     this.gestorterGeschmackssinnOriginValue = gestorterGeschmackssinnOriginValue;
  }

  public TemporalAccessor getGestorterGeschmackssinnOriginValue() {
     return this.gestorterGeschmackssinnOriginValue ;
  }

  public void setGestorterGeschmackssinnErweiterung(
      List<Cluster> gestorterGeschmackssinnErweiterung) {
     this.gestorterGeschmackssinnErweiterung = gestorterGeschmackssinnErweiterung;
  }

  public List<Cluster> getGestorterGeschmackssinnErweiterung() {
     return this.gestorterGeschmackssinnErweiterung ;
  }

  public void setGestorterGeschmackssinnSubject(PartyProxy gestorterGeschmackssinnSubject) {
     this.gestorterGeschmackssinnSubject = gestorterGeschmackssinnSubject;
  }

  public PartyProxy getGestorterGeschmackssinnSubject() {
     return this.gestorterGeschmackssinnSubject ;
  }

  public void setGestorterGeschmackssinnLanguage(Language gestorterGeschmackssinnLanguage) {
     this.gestorterGeschmackssinnLanguage = gestorterGeschmackssinnLanguage;
  }

  public Language getGestorterGeschmackssinnLanguage() {
     return this.gestorterGeschmackssinnLanguage ;
  }

  public void setDurchfallBezeichnungDesSymptomsOderAnzeichensValue(
      String durchfallBezeichnungDesSymptomsOderAnzeichensValue) {
     this.durchfallBezeichnungDesSymptomsOderAnzeichensValue = durchfallBezeichnungDesSymptomsOderAnzeichensValue;
  }

  public String getDurchfallBezeichnungDesSymptomsOderAnzeichensValue() {
     return this.durchfallBezeichnungDesSymptomsOderAnzeichensValue ;
  }

  public void setDurchfallVorhandenDefiningCode(
      VorhandenDefiningCode durchfallVorhandenDefiningCode) {
     this.durchfallVorhandenDefiningCode = durchfallVorhandenDefiningCode;
  }

  public VorhandenDefiningCode getDurchfallVorhandenDefiningCode() {
     return this.durchfallVorhandenDefiningCode ;
  }

  public void setDurchfallDetaillierteAngabenZumSymptomAnzeichen(
      List<Cluster> durchfallDetaillierteAngabenZumSymptomAnzeichen) {
     this.durchfallDetaillierteAngabenZumSymptomAnzeichen = durchfallDetaillierteAngabenZumSymptomAnzeichen;
  }

  public List<Cluster> getDurchfallDetaillierteAngabenZumSymptomAnzeichen() {
     return this.durchfallDetaillierteAngabenZumSymptomAnzeichen ;
  }

  public void setDurchfallTimeValue(TemporalAccessor durchfallTimeValue) {
     this.durchfallTimeValue = durchfallTimeValue;
  }

  public TemporalAccessor getDurchfallTimeValue() {
     return this.durchfallTimeValue ;
  }

  public void setDurchfallOriginValue(TemporalAccessor durchfallOriginValue) {
     this.durchfallOriginValue = durchfallOriginValue;
  }

  public TemporalAccessor getDurchfallOriginValue() {
     return this.durchfallOriginValue ;
  }

  public void setDurchfallErweiterung(List<Cluster> durchfallErweiterung) {
     this.durchfallErweiterung = durchfallErweiterung;
  }

  public List<Cluster> getDurchfallErweiterung() {
     return this.durchfallErweiterung ;
  }

  public void setDurchfallSubject(PartyProxy durchfallSubject) {
     this.durchfallSubject = durchfallSubject;
  }

  public PartyProxy getDurchfallSubject() {
     return this.durchfallSubject ;
  }

  public void setDurchfallLanguage(Language durchfallLanguage) {
     this.durchfallLanguage = durchfallLanguage;
  }

  public Language getDurchfallLanguage() {
     return this.durchfallLanguage ;
  }

  public void setWeitereSymptomeOriginValue(TemporalAccessor weitereSymptomeOriginValue) {
     this.weitereSymptomeOriginValue = weitereSymptomeOriginValue;
  }

  public TemporalAccessor getWeitereSymptomeOriginValue() {
     return this.weitereSymptomeOriginValue ;
  }

  public void setWeitereSymptomeErweiterung(List<Cluster> weitereSymptomeErweiterung) {
     this.weitereSymptomeErweiterung = weitereSymptomeErweiterung;
  }

  public List<Cluster> getWeitereSymptomeErweiterung() {
     return this.weitereSymptomeErweiterung ;
  }

  public void setWeitereSymptomeSubject(PartyProxy weitereSymptomeSubject) {
     this.weitereSymptomeSubject = weitereSymptomeSubject;
  }

  public PartyProxy getWeitereSymptomeSubject() {
     return this.weitereSymptomeSubject ;
  }

  public void setWeitereSymptomeLanguage(Language weitereSymptomeLanguage) {
     this.weitereSymptomeLanguage = weitereSymptomeLanguage;
  }

  public Language getWeitereSymptomeLanguage() {
     return this.weitereSymptomeLanguage ;
  }

  public void setPersonenkontaktAgentEnValue(String personenkontaktAgentEnValue) {
     this.personenkontaktAgentEnValue = personenkontaktAgentEnValue;
  }

  public String getPersonenkontaktAgentEnValue() {
     return this.personenkontaktAgentEnValue ;
  }

  public void setPersonenkontaktPresenceOfAnyExposureEnDefiningCode(
      PresenceOfAnyExposureEnDefiningCode personenkontaktPresenceOfAnyExposureEnDefiningCode) {
     this.personenkontaktPresenceOfAnyExposureEnDefiningCode = personenkontaktPresenceOfAnyExposureEnDefiningCode;
  }

  public PresenceOfAnyExposureEnDefiningCode getPersonenkontaktPresenceOfAnyExposureEnDefiningCode(
      ) {
     return this.personenkontaktPresenceOfAnyExposureEnDefiningCode ;
  }

  public void setPersonenkontaktExposureEnValue(String personenkontaktExposureEnValue) {
     this.personenkontaktExposureEnValue = personenkontaktExposureEnValue;
  }

  public String getPersonenkontaktExposureEnValue() {
     return this.personenkontaktExposureEnValue ;
  }

  public void setPersonenkontaktVorhandenseinDefiningCode(
      VorhandenseinDefiningCode personenkontaktVorhandenseinDefiningCode) {
     this.personenkontaktVorhandenseinDefiningCode = personenkontaktVorhandenseinDefiningCode;
  }

  public VorhandenseinDefiningCode getPersonenkontaktVorhandenseinDefiningCode() {
     return this.personenkontaktVorhandenseinDefiningCode ;
  }

  public void setPersonenkontaktKommentarValue(String personenkontaktKommentarValue) {
     this.personenkontaktKommentarValue = personenkontaktKommentarValue;
  }

  public String getPersonenkontaktKommentarValue() {
     return this.personenkontaktKommentarValue ;
  }

  public void setPersonenkontaktTimeValue(TemporalAccessor personenkontaktTimeValue) {
     this.personenkontaktTimeValue = personenkontaktTimeValue;
  }

  public TemporalAccessor getPersonenkontaktTimeValue() {
     return this.personenkontaktTimeValue ;
  }

  public void setPersonenkontaktOriginValue(TemporalAccessor personenkontaktOriginValue) {
     this.personenkontaktOriginValue = personenkontaktOriginValue;
  }

  public TemporalAccessor getPersonenkontaktOriginValue() {
     return this.personenkontaktOriginValue ;
  }

  public void setPersonenkontaktErweiterung(List<Cluster> personenkontaktErweiterung) {
     this.personenkontaktErweiterung = personenkontaktErweiterung;
  }

  public List<Cluster> getPersonenkontaktErweiterung() {
     return this.personenkontaktErweiterung ;
  }

  public void setPersonenkontaktSubject(PartyProxy personenkontaktSubject) {
     this.personenkontaktSubject = personenkontaktSubject;
  }

  public PartyProxy getPersonenkontaktSubject() {
     return this.personenkontaktSubject ;
  }

  public void setPersonenkontaktLanguage(Language personenkontaktLanguage) {
     this.personenkontaktLanguage = personenkontaktLanguage;
  }

  public Language getPersonenkontaktLanguage() {
     return this.personenkontaktLanguage ;
  }

  public void setAufenthaltInGesundheitseinrichtungAgentEnValue(
      String aufenthaltInGesundheitseinrichtungAgentEnValue) {
     this.aufenthaltInGesundheitseinrichtungAgentEnValue = aufenthaltInGesundheitseinrichtungAgentEnValue;
  }

  public String getAufenthaltInGesundheitseinrichtungAgentEnValue() {
     return this.aufenthaltInGesundheitseinrichtungAgentEnValue ;
  }

  public void setAufenthaltInGesundheitseinrichtungPresenceOfAnyExposureEnDefiningCode(
      PresenceOfAnyExposureEnDefiningCode aufenthaltInGesundheitseinrichtungPresenceOfAnyExposureEnDefiningCode) {
     this.aufenthaltInGesundheitseinrichtungPresenceOfAnyExposureEnDefiningCode = aufenthaltInGesundheitseinrichtungPresenceOfAnyExposureEnDefiningCode;
  }

  public PresenceOfAnyExposureEnDefiningCode getAufenthaltInGesundheitseinrichtungPresenceOfAnyExposureEnDefiningCode(
      ) {
     return this.aufenthaltInGesundheitseinrichtungPresenceOfAnyExposureEnDefiningCode ;
  }

  public void setAufenthaltInGesundheitseinrichtungExposureEnValue(
      String aufenthaltInGesundheitseinrichtungExposureEnValue) {
     this.aufenthaltInGesundheitseinrichtungExposureEnValue = aufenthaltInGesundheitseinrichtungExposureEnValue;
  }

  public String getAufenthaltInGesundheitseinrichtungExposureEnValue() {
     return this.aufenthaltInGesundheitseinrichtungExposureEnValue ;
  }

  public void setAufenthaltInGesundheitseinrichtungVorhandenseinDefiningCode(
      VorhandenseinDefiningCode aufenthaltInGesundheitseinrichtungVorhandenseinDefiningCode) {
     this.aufenthaltInGesundheitseinrichtungVorhandenseinDefiningCode = aufenthaltInGesundheitseinrichtungVorhandenseinDefiningCode;
  }

  public VorhandenseinDefiningCode getAufenthaltInGesundheitseinrichtungVorhandenseinDefiningCode(
      ) {
     return this.aufenthaltInGesundheitseinrichtungVorhandenseinDefiningCode ;
  }

  public void setAufenthaltInGesundheitseinrichtungKommentarValue(
      String aufenthaltInGesundheitseinrichtungKommentarValue) {
     this.aufenthaltInGesundheitseinrichtungKommentarValue = aufenthaltInGesundheitseinrichtungKommentarValue;
  }

  public String getAufenthaltInGesundheitseinrichtungKommentarValue() {
     return this.aufenthaltInGesundheitseinrichtungKommentarValue ;
  }

  public void setAufenthaltInGesundheitseinrichtungTimeValue(
      TemporalAccessor aufenthaltInGesundheitseinrichtungTimeValue) {
     this.aufenthaltInGesundheitseinrichtungTimeValue = aufenthaltInGesundheitseinrichtungTimeValue;
  }

  public TemporalAccessor getAufenthaltInGesundheitseinrichtungTimeValue() {
     return this.aufenthaltInGesundheitseinrichtungTimeValue ;
  }

  public void setAufenthaltInGesundheitseinrichtungOriginValue(
      TemporalAccessor aufenthaltInGesundheitseinrichtungOriginValue) {
     this.aufenthaltInGesundheitseinrichtungOriginValue = aufenthaltInGesundheitseinrichtungOriginValue;
  }

  public TemporalAccessor getAufenthaltInGesundheitseinrichtungOriginValue() {
     return this.aufenthaltInGesundheitseinrichtungOriginValue ;
  }

  public void setAufenthaltInGesundheitseinrichtungErweiterung(
      List<Cluster> aufenthaltInGesundheitseinrichtungErweiterung) {
     this.aufenthaltInGesundheitseinrichtungErweiterung = aufenthaltInGesundheitseinrichtungErweiterung;
  }

  public List<Cluster> getAufenthaltInGesundheitseinrichtungErweiterung() {
     return this.aufenthaltInGesundheitseinrichtungErweiterung ;
  }

  public void setAufenthaltInGesundheitseinrichtungSubject(
      PartyProxy aufenthaltInGesundheitseinrichtungSubject) {
     this.aufenthaltInGesundheitseinrichtungSubject = aufenthaltInGesundheitseinrichtungSubject;
  }

  public PartyProxy getAufenthaltInGesundheitseinrichtungSubject() {
     return this.aufenthaltInGesundheitseinrichtungSubject ;
  }

  public void setAufenthaltInGesundheitseinrichtungLanguage(
      Language aufenthaltInGesundheitseinrichtungLanguage) {
     this.aufenthaltInGesundheitseinrichtungLanguage = aufenthaltInGesundheitseinrichtungLanguage;
  }

  public Language getAufenthaltInGesundheitseinrichtungLanguage() {
     return this.aufenthaltInGesundheitseinrichtungLanguage ;
  }

  public void setHistorieDerReise(List<HistorieDerReiseObservation> historieDerReise) {
     this.historieDerReise = historieDerReise;
  }

  public List<HistorieDerReiseObservation> getHistorieDerReise() {
     return this.historieDerReise ;
  }

  public void setReisefall(List<ReisefallObservation> reisefall) {
     this.reisefall = reisefall;
  }

  public List<ReisefallObservation> getReisefall() {
     return this.reisefall ;
  }

  public void setAndereAktuelleErkrankungenBezeichnungDesSymptomsOderAnzeichensValue(
      String andereAktuelleErkrankungenBezeichnungDesSymptomsOderAnzeichensValue) {
     this.andereAktuelleErkrankungenBezeichnungDesSymptomsOderAnzeichensValue = andereAktuelleErkrankungenBezeichnungDesSymptomsOderAnzeichensValue;
  }

  public String getAndereAktuelleErkrankungenBezeichnungDesSymptomsOderAnzeichensValue() {
     return this.andereAktuelleErkrankungenBezeichnungDesSymptomsOderAnzeichensValue ;
  }

  public void setAndereAktuelleErkrankungenVorhandenDefiningCode(
      VorhandenDefiningCode andereAktuelleErkrankungenVorhandenDefiningCode) {
     this.andereAktuelleErkrankungenVorhandenDefiningCode = andereAktuelleErkrankungenVorhandenDefiningCode;
  }

  public VorhandenDefiningCode getAndereAktuelleErkrankungenVorhandenDefiningCode() {
     return this.andereAktuelleErkrankungenVorhandenDefiningCode ;
  }

  public void setAndereAktuelleErkrankungenDetaillierteAngabenZumSymptomAnzeichen(
      List<Cluster> andereAktuelleErkrankungenDetaillierteAngabenZumSymptomAnzeichen) {
     this.andereAktuelleErkrankungenDetaillierteAngabenZumSymptomAnzeichen = andereAktuelleErkrankungenDetaillierteAngabenZumSymptomAnzeichen;
  }

  public List<Cluster> getAndereAktuelleErkrankungenDetaillierteAngabenZumSymptomAnzeichen() {
     return this.andereAktuelleErkrankungenDetaillierteAngabenZumSymptomAnzeichen ;
  }

  public void setAndereAktuelleErkrankungenTimeValue(
      TemporalAccessor andereAktuelleErkrankungenTimeValue) {
     this.andereAktuelleErkrankungenTimeValue = andereAktuelleErkrankungenTimeValue;
  }

  public TemporalAccessor getAndereAktuelleErkrankungenTimeValue() {
     return this.andereAktuelleErkrankungenTimeValue ;
  }

  public void setAndereAktuelleErkrankungenOriginValue(
      TemporalAccessor andereAktuelleErkrankungenOriginValue) {
     this.andereAktuelleErkrankungenOriginValue = andereAktuelleErkrankungenOriginValue;
  }

  public TemporalAccessor getAndereAktuelleErkrankungenOriginValue() {
     return this.andereAktuelleErkrankungenOriginValue ;
  }

  public void setAndereAktuelleErkrankungenErweiterung(
      List<Cluster> andereAktuelleErkrankungenErweiterung) {
     this.andereAktuelleErkrankungenErweiterung = andereAktuelleErkrankungenErweiterung;
  }

  public List<Cluster> getAndereAktuelleErkrankungenErweiterung() {
     return this.andereAktuelleErkrankungenErweiterung ;
  }

  public void setAndereAktuelleErkrankungenSubject(PartyProxy andereAktuelleErkrankungenSubject) {
     this.andereAktuelleErkrankungenSubject = andereAktuelleErkrankungenSubject;
  }

  public PartyProxy getAndereAktuelleErkrankungenSubject() {
     return this.andereAktuelleErkrankungenSubject ;
  }

  public void setAndereAktuelleErkrankungenLanguage(Language andereAktuelleErkrankungenLanguage) {
     this.andereAktuelleErkrankungenLanguage = andereAktuelleErkrankungenLanguage;
  }

  public Language getAndereAktuelleErkrankungenLanguage() {
     return this.andereAktuelleErkrankungenLanguage ;
  }

  public void setChronischeErkrankungenBezeichnungDesSymptomsOderAnzeichensValue(
      String chronischeErkrankungenBezeichnungDesSymptomsOderAnzeichensValue) {
     this.chronischeErkrankungenBezeichnungDesSymptomsOderAnzeichensValue = chronischeErkrankungenBezeichnungDesSymptomsOderAnzeichensValue;
  }

  public String getChronischeErkrankungenBezeichnungDesSymptomsOderAnzeichensValue() {
     return this.chronischeErkrankungenBezeichnungDesSymptomsOderAnzeichensValue ;
  }

  public void setChronischeErkrankungenVorhandenDefiningCode(
      VorhandenDefiningCode chronischeErkrankungenVorhandenDefiningCode) {
     this.chronischeErkrankungenVorhandenDefiningCode = chronischeErkrankungenVorhandenDefiningCode;
  }

  public VorhandenDefiningCode getChronischeErkrankungenVorhandenDefiningCode() {
     return this.chronischeErkrankungenVorhandenDefiningCode ;
  }

  public void setChronischeErkrankungenDetaillierteAngabenZumSymptomAnzeichen(
      List<Cluster> chronischeErkrankungenDetaillierteAngabenZumSymptomAnzeichen) {
     this.chronischeErkrankungenDetaillierteAngabenZumSymptomAnzeichen = chronischeErkrankungenDetaillierteAngabenZumSymptomAnzeichen;
  }

  public List<Cluster> getChronischeErkrankungenDetaillierteAngabenZumSymptomAnzeichen() {
     return this.chronischeErkrankungenDetaillierteAngabenZumSymptomAnzeichen ;
  }

  public void setChronischeErkrankungenTimeValue(TemporalAccessor chronischeErkrankungenTimeValue) {
     this.chronischeErkrankungenTimeValue = chronischeErkrankungenTimeValue;
  }

  public TemporalAccessor getChronischeErkrankungenTimeValue() {
     return this.chronischeErkrankungenTimeValue ;
  }

  public void setChronischeErkrankungenOriginValue(
      TemporalAccessor chronischeErkrankungenOriginValue) {
     this.chronischeErkrankungenOriginValue = chronischeErkrankungenOriginValue;
  }

  public TemporalAccessor getChronischeErkrankungenOriginValue() {
     return this.chronischeErkrankungenOriginValue ;
  }

  public void setChronischeErkrankungenErweiterung(
      List<Cluster> chronischeErkrankungenErweiterung) {
     this.chronischeErkrankungenErweiterung = chronischeErkrankungenErweiterung;
  }

  public List<Cluster> getChronischeErkrankungenErweiterung() {
     return this.chronischeErkrankungenErweiterung ;
  }

  public void setChronischeErkrankungenSubject(PartyProxy chronischeErkrankungenSubject) {
     this.chronischeErkrankungenSubject = chronischeErkrankungenSubject;
  }

  public PartyProxy getChronischeErkrankungenSubject() {
     return this.chronischeErkrankungenSubject ;
  }

  public void setChronischeErkrankungenLanguage(Language chronischeErkrankungenLanguage) {
     this.chronischeErkrankungenLanguage = chronischeErkrankungenLanguage;
  }

  public Language getChronischeErkrankungenLanguage() {
     return this.chronischeErkrankungenLanguage ;
  }

  public void setProblemDiagnose(List<ProblemDiagnoseEvaluation> problemDiagnose) {
     this.problemDiagnose = problemDiagnose;
  }

  public List<ProblemDiagnoseEvaluation> getProblemDiagnose() {
     return this.problemDiagnose ;
  }

  public void setMedikamenteInVerwendung(DvCodedText medikamenteInVerwendung) {
     this.medikamenteInVerwendung = medikamenteInVerwendung;
  }

  public DvCodedText getMedikamenteInVerwendung() {
     return this.medikamenteInVerwendung ;
  }

  public void setNameDerMedikamentenklasseValue(String nameDerMedikamentenklasseValue) {
     this.nameDerMedikamentenklasseValue = nameDerMedikamentenklasseValue;
  }

  public String getNameDerMedikamentenklasseValue() {
     return this.nameDerMedikamentenklasseValue ;
  }

  public void setMedikamentenklasseInVerwendungDefiningCode(
      MedikamentenklasseInVerwendungDefiningCode medikamentenklasseInVerwendungDefiningCode) {
     this.medikamentenklasseInVerwendungDefiningCode = medikamentenklasseInVerwendungDefiningCode;
  }

  public MedikamentenklasseInVerwendungDefiningCode getMedikamentenklasseInVerwendungDefiningCode(
      ) {
     return this.medikamentenklasseInVerwendungDefiningCode ;
  }

  public void setSpezifischeMedikamente(
      List<FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster> spezifischeMedikamente) {
     this.spezifischeMedikamente = spezifischeMedikamente;
  }

  public List<FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster> getSpezifischeMedikamente(
      ) {
     return this.spezifischeMedikamente ;
  }

  public void setFragebogenZumMedikationsScreeningTimeValue(
      TemporalAccessor fragebogenZumMedikationsScreeningTimeValue) {
     this.fragebogenZumMedikationsScreeningTimeValue = fragebogenZumMedikationsScreeningTimeValue;
  }

  public TemporalAccessor getFragebogenZumMedikationsScreeningTimeValue() {
     return this.fragebogenZumMedikationsScreeningTimeValue ;
  }

  public void setFragebogenZumMedikationsScreeningOriginValue(
      TemporalAccessor fragebogenZumMedikationsScreeningOriginValue) {
     this.fragebogenZumMedikationsScreeningOriginValue = fragebogenZumMedikationsScreeningOriginValue;
  }

  public TemporalAccessor getFragebogenZumMedikationsScreeningOriginValue() {
     return this.fragebogenZumMedikationsScreeningOriginValue ;
  }

  public void setFragebogenZumMedikationsScreeningErweiterung(
      List<Cluster> fragebogenZumMedikationsScreeningErweiterung) {
     this.fragebogenZumMedikationsScreeningErweiterung = fragebogenZumMedikationsScreeningErweiterung;
  }

  public List<Cluster> getFragebogenZumMedikationsScreeningErweiterung() {
     return this.fragebogenZumMedikationsScreeningErweiterung ;
  }

  public void setFragebogenZumMedikationsScreeningSubject(
      PartyProxy fragebogenZumMedikationsScreeningSubject) {
     this.fragebogenZumMedikationsScreeningSubject = fragebogenZumMedikationsScreeningSubject;
  }

  public PartyProxy getFragebogenZumMedikationsScreeningSubject() {
     return this.fragebogenZumMedikationsScreeningSubject ;
  }

  public void setFragebogenZumMedikationsScreeningLanguage(
      Language fragebogenZumMedikationsScreeningLanguage) {
     this.fragebogenZumMedikationsScreeningLanguage = fragebogenZumMedikationsScreeningLanguage;
  }

  public Language getFragebogenZumMedikationsScreeningLanguage() {
     return this.fragebogenZumMedikationsScreeningLanguage ;
  }

  public void setBeschaftigungsstatusValue(String beschaftigungsstatusValue) {
     this.beschaftigungsstatusValue = beschaftigungsstatusValue;
  }

  public String getBeschaftigungsstatusValue() {
     return this.beschaftigungsstatusValue ;
  }

  public void setBeschaftigung(List<BeschaftigungCluster> beschaftigung) {
     this.beschaftigung = beschaftigung;
  }

  public List<BeschaftigungCluster> getBeschaftigung() {
     return this.beschaftigung ;
  }

  public void setZusatzlicheAngaben(List<Cluster> zusatzlicheAngaben) {
     this.zusatzlicheAngaben = zusatzlicheAngaben;
  }

  public List<Cluster> getZusatzlicheAngaben() {
     return this.zusatzlicheAngaben ;
  }

  public void setGesundheitsbezogenerBerufErweiterung(
      List<Cluster> gesundheitsbezogenerBerufErweiterung) {
     this.gesundheitsbezogenerBerufErweiterung = gesundheitsbezogenerBerufErweiterung;
  }

  public List<Cluster> getGesundheitsbezogenerBerufErweiterung() {
     return this.gesundheitsbezogenerBerufErweiterung ;
  }

  public void setGesundheitsbezogenerBerufSubject(PartyProxy gesundheitsbezogenerBerufSubject) {
     this.gesundheitsbezogenerBerufSubject = gesundheitsbezogenerBerufSubject;
  }

  public PartyProxy getGesundheitsbezogenerBerufSubject() {
     return this.gesundheitsbezogenerBerufSubject ;
  }

  public void setGesundheitsbezogenerBerufLanguage(Language gesundheitsbezogenerBerufLanguage) {
     this.gesundheitsbezogenerBerufLanguage = gesundheitsbezogenerBerufLanguage;
  }

  public Language getGesundheitsbezogenerBerufLanguage() {
     return this.gesundheitsbezogenerBerufLanguage ;
  }

  public void setWohnsituation(List<WohnsituationEvaluation> wohnsituation) {
     this.wohnsituation = wohnsituation;
  }

  public List<WohnsituationEvaluation> getWohnsituation() {
     return this.wohnsituation ;
  }

  public void setBewertungDesGesundheitsrisikos(
      List<BewertungDesGesundheitsrisikosEvaluation> bewertungDesGesundheitsrisikos) {
     this.bewertungDesGesundheitsrisikos = bewertungDesGesundheitsrisikos;
  }

  public List<BewertungDesGesundheitsrisikosEvaluation> getBewertungDesGesundheitsrisikos() {
     return this.bewertungDesGesundheitsrisikos ;
  }

  public void setNameDesProblemsDerDiagnoseValue(String nameDesProblemsDerDiagnoseValue) {
     this.nameDesProblemsDerDiagnoseValue = nameDesProblemsDerDiagnoseValue;
  }

  public String getNameDesProblemsDerDiagnoseValue() {
     return this.nameDesProblemsDerDiagnoseValue ;
  }

  public void setAnatomischeStelleStrukturiert(List<Cluster> anatomischeStelleStrukturiert) {
     this.anatomischeStelleStrukturiert = anatomischeStelleStrukturiert;
  }

  public List<Cluster> getAnatomischeStelleStrukturiert() {
     return this.anatomischeStelleStrukturiert ;
  }

  public void setSpezifischeAngaben(List<Cluster> spezifischeAngaben) {
     this.spezifischeAngaben = spezifischeAngaben;
  }

  public List<Cluster> getSpezifischeAngaben() {
     return this.spezifischeAngaben ;
  }

  public void setDiagnosestatusDefiningCode(DiagnosestatusDefiningCode diagnosestatusDefiningCode) {
     this.diagnosestatusDefiningCode = diagnosestatusDefiningCode;
  }

  public DiagnosestatusDefiningCode getDiagnosestatusDefiningCode() {
     return this.diagnosestatusDefiningCode ;
  }

  public void setProblemDiganoseCoronovirusKommentarValue(
      String problemDiganoseCoronovirusKommentarValue) {
     this.problemDiganoseCoronovirusKommentarValue = problemDiganoseCoronovirusKommentarValue;
  }

  public String getProblemDiganoseCoronovirusKommentarValue() {
     return this.problemDiganoseCoronovirusKommentarValue ;
  }

  public void setZuletztAktualisiertValue(TemporalAccessor zuletztAktualisiertValue) {
     this.zuletztAktualisiertValue = zuletztAktualisiertValue;
  }

  public TemporalAccessor getZuletztAktualisiertValue() {
     return this.zuletztAktualisiertValue ;
  }

  public void setProblemDiganoseCoronovirusErweiterung(
      List<Cluster> problemDiganoseCoronovirusErweiterung) {
     this.problemDiganoseCoronovirusErweiterung = problemDiganoseCoronovirusErweiterung;
  }

  public List<Cluster> getProblemDiganoseCoronovirusErweiterung() {
     return this.problemDiganoseCoronovirusErweiterung ;
  }

  public void setProblemDiganoseCoronovirusSubject(PartyProxy problemDiganoseCoronovirusSubject) {
     this.problemDiganoseCoronovirusSubject = problemDiganoseCoronovirusSubject;
  }

  public PartyProxy getProblemDiganoseCoronovirusSubject() {
     return this.problemDiganoseCoronovirusSubject ;
  }

  public void setProblemDiganoseCoronovirusLanguage(Language problemDiganoseCoronovirusLanguage) {
     this.problemDiganoseCoronovirusLanguage = problemDiganoseCoronovirusLanguage;
  }

  public Language getProblemDiganoseCoronovirusLanguage() {
     return this.problemDiganoseCoronovirusLanguage ;
  }

  public void setDienstleistung(List<DienstleistungInstruction> dienstleistung) {
     this.dienstleistung = dienstleistung;
  }

  public List<DienstleistungInstruction> getDienstleistung() {
     return this.dienstleistung ;
  }

  public void setComposer(PartyProxy composer) {
     this.composer = composer;
  }

  public PartyProxy getComposer() {
     return this.composer ;
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

  public void setCategoryDefiningCode(Category categoryDefiningCode) {
     this.categoryDefiningCode = categoryDefiningCode;
  }

  public Category getCategoryDefiningCode() {
     return this.categoryDefiningCode ;
  }

  public void setTerritory(Territory territory) {
     this.territory = territory;
  }

  public Territory getTerritory() {
     return this.territory ;
  }

  public void setDiagnostischeSicherheit(
      ProblemDiganoseCoronovirusDiagnostischeSicherheitChoice diagnostischeSicherheit) {
     this.diagnostischeSicherheit = diagnostischeSicherheit;
  }

  public ProblemDiganoseCoronovirusDiagnostischeSicherheitChoice getDiagnostischeSicherheit() {
     return this.diagnostischeSicherheit ;
  }

  public void setBeliebigesEreignis(List<BerichtBeliebigesEreignisChoice> beliebigesEreignis) {
     this.beliebigesEreignis = beliebigesEreignis;
  }

  public List<BerichtBeliebigesEreignisChoice> getBeliebigesEreignis() {
     return this.beliebigesEreignis ;
  }

  public VersionUid getVersionUid() {
     return this.versionUid ;
  }

  public void setVersionUid(VersionUid versionUid) {
     this.versionUid = versionUid;
  }
}
