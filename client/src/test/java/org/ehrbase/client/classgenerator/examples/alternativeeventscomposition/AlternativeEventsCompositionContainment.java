package org.ehrbase.client.classgenerator.examples.alternativeeventscomposition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition.KorpergewichtObservation;
import org.ehrbase.client.classgenerator.examples.shareddefinition.CategoryDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.examples.shareddefinition.SettingDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Territory;

import java.time.temporal.TemporalAccessor;

public class AlternativeEventsCompositionContainment extends Containment {
  public SelectAqlField<AlternativeEventsComposition> ALTERNATIVE_EVENTS_COMPOSITION = new AqlFieldImp<AlternativeEventsComposition>(AlternativeEventsComposition.class, "", "AlternativeEventsComposition", AlternativeEventsComposition.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(AlternativeEventsComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(AlternativeEventsComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(AlternativeEventsComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(AlternativeEventsComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<String> STATUS_VALUE = new AqlFieldImp<String>(AlternativeEventsComposition.class, "/context/other_context[at0001]/items[at0005]/value|value", "statusValue", String.class, this);

  public SelectAqlField<String> BERICHT_ID_VALUE = new AqlFieldImp<String>(AlternativeEventsComposition.class, "/context/other_context[at0001]/items[at0002]/value|value", "berichtIdValue", String.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(AlternativeEventsComposition.class, "/territory", "territory", Territory.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(AlternativeEventsComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(AlternativeEventsComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<SettingDefiningcode> SETTING_DEFININGCODE = new AqlFieldImp<SettingDefiningcode>(AlternativeEventsComposition.class, "/context/setting|defining_code", "settingDefiningcode", SettingDefiningcode.class, this);

  public ListSelectAqlField<KorpergewichtObservation> KORPERGEWICHT = new ListAqlFieldImp<KorpergewichtObservation>(AlternativeEventsComposition.class, "/content[openEHR-EHR-OBSERVATION.body_weight.v2]", "korpergewicht", KorpergewichtObservation.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(AlternativeEventsComposition.class, "/context/other_context[at0001]/items[at0006]", "erweiterung", Cluster.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(AlternativeEventsComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<CategoryDefiningcode> CATEGORY_DEFININGCODE = new AqlFieldImp<CategoryDefiningcode>(AlternativeEventsComposition.class, "/category|defining_code", "categoryDefiningcode", CategoryDefiningcode.class, this);

  private AlternativeEventsCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.report.v1");
  }

  public static AlternativeEventsCompositionContainment getInstance() {
    return new AlternativeEventsCompositionContainment();
  }
}
