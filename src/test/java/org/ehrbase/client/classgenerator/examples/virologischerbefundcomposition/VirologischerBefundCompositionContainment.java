package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition;

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
import org.ehrbase.client.classgenerator.examples.shareddefinition.CategoryDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.examples.shareddefinition.SettingDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Territory;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition.FallidentifikationCluster;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition.LaborergebnisObservation;

public class VirologischerBefundCompositionContainment extends Containment {
  public SelectAqlField<VirologischerBefundComposition> VIROLOGISCHER_BEFUND_COMPOSITION = new AqlFieldImp<VirologischerBefundComposition>(VirologischerBefundComposition.class, "", "VirologischerBefundComposition", VirologischerBefundComposition.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(VirologischerBefundComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(VirologischerBefundComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(VirologischerBefundComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(VirologischerBefundComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<String> STATUS_VALUE = new AqlFieldImp<String>(VirologischerBefundComposition.class, "/context/other_context[at0001]/items[at0005]/value|value", "statusValue", String.class, this);

  public SelectAqlField<String> BERICHT_ID_VALUE = new AqlFieldImp<String>(VirologischerBefundComposition.class, "/context/other_context[at0001]/items[at0002]/value|value", "berichtIdValue", String.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(VirologischerBefundComposition.class, "/territory", "territory", Territory.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(VirologischerBefundComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<FallidentifikationCluster> FALLIDENTIFIKATION = new AqlFieldImp<FallidentifikationCluster>(VirologischerBefundComposition.class, "/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0]", "fallidentifikation", FallidentifikationCluster.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(VirologischerBefundComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<SettingDefiningcode> SETTING_DEFININGCODE = new AqlFieldImp<SettingDefiningcode>(VirologischerBefundComposition.class, "/context/setting|defining_code", "settingDefiningcode", SettingDefiningcode.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(VirologischerBefundComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<CategoryDefiningcode> CATEGORY_DEFININGCODE = new AqlFieldImp<CategoryDefiningcode>(VirologischerBefundComposition.class, "/category|defining_code", "categoryDefiningcode", CategoryDefiningcode.class, this);

  public SelectAqlField<LaborergebnisObservation> LABORERGEBNIS = new AqlFieldImp<LaborergebnisObservation>(VirologischerBefundComposition.class, "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1]", "laborergebnis", LaborergebnisObservation.class, this);

  private VirologischerBefundCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.report-result.v1");
  }

  public static VirologischerBefundCompositionContainment getInstance() {
    return new VirologischerBefundCompositionContainment();
  }
}
