package org.ehrbase.client.classgenerator.examples.befundderblutgasanalysecomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.befundderblutgasanalysecomposition.definition.LaborergebnisObservation;
import org.ehrbase.client.classgenerator.examples.befundderblutgasanalysecomposition.definition.StatusDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.CategoryDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.examples.shareddefinition.SettingDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Territory;

import java.time.temporal.TemporalAccessor;

public class BefundDerBlutgasanalyseCompositionContainment extends Containment {
  public SelectAqlField<BefundDerBlutgasanalyseComposition> BEFUND_DER_BLUTGASANALYSE_COMPOSITION = new AqlFieldImp<BefundDerBlutgasanalyseComposition>(BefundDerBlutgasanalyseComposition.class, "", "BefundDerBlutgasanalyseComposition", BefundDerBlutgasanalyseComposition.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(BefundDerBlutgasanalyseComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(BefundDerBlutgasanalyseComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(BefundDerBlutgasanalyseComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(BefundDerBlutgasanalyseComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<StatusDefiningcode> STATUS_DEFININGCODE = new AqlFieldImp<StatusDefiningcode>(BefundDerBlutgasanalyseComposition.class, "/context/other_context[at0001]/items[at0004]/value|defining_code", "statusDefiningcode", StatusDefiningcode.class, this);

  public SelectAqlField<String> KATEGORIE_VALUE = new AqlFieldImp<String>(BefundDerBlutgasanalyseComposition.class, "/context/other_context[at0001]/items[at0005]/value|value", "kategorieValue", String.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(BefundDerBlutgasanalyseComposition.class, "/territory", "territory", Territory.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(BefundDerBlutgasanalyseComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(BefundDerBlutgasanalyseComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<SettingDefiningcode> SETTING_DEFININGCODE = new AqlFieldImp<SettingDefiningcode>(BefundDerBlutgasanalyseComposition.class, "/context/setting|defining_code", "settingDefiningcode", SettingDefiningcode.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(BefundDerBlutgasanalyseComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(BefundDerBlutgasanalyseComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<CategoryDefiningcode> CATEGORY_DEFININGCODE = new AqlFieldImp<CategoryDefiningcode>(BefundDerBlutgasanalyseComposition.class, "/category|defining_code", "categoryDefiningcode", CategoryDefiningcode.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(BefundDerBlutgasanalyseComposition.class, "/context/other_context[at0001]/items[at0002]", "erweiterung", Cluster.class, this);

  public SelectAqlField<LaborergebnisObservation> LABORERGEBNIS = new AqlFieldImp<LaborergebnisObservation>(BefundDerBlutgasanalyseComposition.class, "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1]", "laborergebnis", LaborergebnisObservation.class, this);

  private BefundDerBlutgasanalyseCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.registereintrag.v1");
  }

  public static BefundDerBlutgasanalyseCompositionContainment getInstance() {
    return new BefundDerBlutgasanalyseCompositionContainment();
  }
}
