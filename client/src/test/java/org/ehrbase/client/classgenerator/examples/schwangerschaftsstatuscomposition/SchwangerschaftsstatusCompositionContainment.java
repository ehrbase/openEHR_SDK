package org.ehrbase.client.classgenerator.examples.schwangerschaftsstatuscomposition;

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
import org.ehrbase.client.classgenerator.examples.schwangerschaftsstatuscomposition.definition.SchwangerschaftsstatusObservation;
import org.ehrbase.client.classgenerator.examples.schwangerschaftsstatuscomposition.definition.StatusDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.CategoryDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.examples.shareddefinition.SettingDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Territory;

import java.time.temporal.TemporalAccessor;

public class SchwangerschaftsstatusCompositionContainment extends Containment {
  public SelectAqlField<SchwangerschaftsstatusComposition> SCHWANGERSCHAFTSSTATUS_COMPOSITION = new AqlFieldImp<SchwangerschaftsstatusComposition>(SchwangerschaftsstatusComposition.class, "", "SchwangerschaftsstatusComposition", SchwangerschaftsstatusComposition.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(SchwangerschaftsstatusComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(SchwangerschaftsstatusComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(SchwangerschaftsstatusComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(SchwangerschaftsstatusComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<StatusDefiningcode> STATUS_DEFININGCODE = new AqlFieldImp<StatusDefiningcode>(SchwangerschaftsstatusComposition.class, "/context/other_context[at0001]/items[at0004]/value|defining_code", "statusDefiningcode", StatusDefiningcode.class, this);

  public SelectAqlField<String> KATEGORIE_VALUE = new AqlFieldImp<String>(SchwangerschaftsstatusComposition.class, "/context/other_context[at0001]/items[at0005]/value|value", "kategorieValue", String.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(SchwangerschaftsstatusComposition.class, "/territory", "territory", Territory.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(SchwangerschaftsstatusComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(SchwangerschaftsstatusComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<SettingDefiningcode> SETTING_DEFININGCODE = new AqlFieldImp<SettingDefiningcode>(SchwangerschaftsstatusComposition.class, "/context/setting|defining_code", "settingDefiningcode", SettingDefiningcode.class, this);

  public SelectAqlField<SchwangerschaftsstatusObservation> SCHWANGERSCHAFTSSTATUS = new AqlFieldImp<SchwangerschaftsstatusObservation>(SchwangerschaftsstatusComposition.class, "/content[openEHR-EHR-OBSERVATION.pregnancy_status.v0]", "schwangerschaftsstatus", SchwangerschaftsstatusObservation.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(SchwangerschaftsstatusComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(SchwangerschaftsstatusComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<CategoryDefiningcode> CATEGORY_DEFININGCODE = new AqlFieldImp<CategoryDefiningcode>(SchwangerschaftsstatusComposition.class, "/category|defining_code", "categoryDefiningcode", CategoryDefiningcode.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(SchwangerschaftsstatusComposition.class, "/context/other_context[at0001]/items[at0002]", "erweiterung", Cluster.class, this);

  private SchwangerschaftsstatusCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.registereintrag.v1");
  }

  public static SchwangerschaftsstatusCompositionContainment getInstance() {
    return new SchwangerschaftsstatusCompositionContainment();
  }
}
