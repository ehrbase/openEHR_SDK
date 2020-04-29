package org.ehrbase.kopertemperature.intensivmedizinischesmonitoringkorpertemperaturcomposition;

import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.kopertemperature.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.FallidentifikationCluster;
import org.ehrbase.kopertemperature.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KorpertemperaturObservation;
import org.ehrbase.kopertemperature.shareddefinition.CategoryDefiningcode;
import org.ehrbase.kopertemperature.shareddefinition.Language;
import org.ehrbase.kopertemperature.shareddefinition.SettingDefiningcode;
import org.ehrbase.kopertemperature.shareddefinition.Territory;

public class IntensivmedizinischesMonitoringKorpertemperaturCompositionContainment extends Containment {
  public SelectAqlField<IntensivmedizinischesMonitoringKorpertemperaturComposition> INTENSIVMEDIZINISCHES_MONITORING_KORPERTEMPERATUR_COMPOSITION = new AqlFieldImp<IntensivmedizinischesMonitoringKorpertemperaturComposition>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "", "IntensivmedizinischesMonitoringKorpertemperaturComposition", IntensivmedizinischesMonitoringKorpertemperaturComposition.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<String> STATUS_VALUE = new AqlFieldImp<String>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/context/other_context[at0001]/items[at0005]/value|value", "statusValue", String.class, this);

  public SelectAqlField<String> BERICHT_ID_VALUE = new AqlFieldImp<String>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/context/other_context[at0001]/items[at0002]/value|value", "berichtIdValue", String.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/territory", "territory", Territory.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<FallidentifikationCluster> FALLIDENTIFIKATION = new AqlFieldImp<FallidentifikationCluster>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0]", "fallidentifikation", FallidentifikationCluster.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<SettingDefiningcode> SETTING_DEFININGCODE = new AqlFieldImp<SettingDefiningcode>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/context/setting|defining_code", "settingDefiningcode", SettingDefiningcode.class, this);

  public ListSelectAqlField<KorpertemperaturObservation> KORPERTEMPERATUR = new ListAqlFieldImp<KorpertemperaturObservation>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/content[openEHR-EHR-OBSERVATION.body_temperature.v2]", "korpertemperatur", KorpertemperaturObservation.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<CategoryDefiningcode> CATEGORY_DEFININGCODE = new AqlFieldImp<CategoryDefiningcode>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/category|defining_code", "categoryDefiningcode", CategoryDefiningcode.class, this);

  public SelectAqlField<String> BERICHT_ID_VALUE_TREE = new AqlFieldImp<String>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/context/other_context[at0001]/items[at0002]/name|value", "berichtIdValueTree", String.class, this);

  private IntensivmedizinischesMonitoringKorpertemperaturCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.report.v1");
  }

  public static IntensivmedizinischesMonitoringKorpertemperaturCompositionContainment getInstance(
      ) {
    return new IntensivmedizinischesMonitoringKorpertemperaturCompositionContainment();
  }
}
