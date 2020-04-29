package org.ehrbase.client.classgenerator.examples.beatmungcomposition;

import com.nedap.archie.rm.datastructures.Cluster;
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
import org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition.BeobachtungenAmBeatmungsgeratObservation;
import org.ehrbase.client.classgenerator.examples.shareddefinition.CategoryDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.examples.shareddefinition.SettingDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Territory;

public class BeatmungCompositionContainment extends Containment {
  public SelectAqlField<BeatmungComposition> BEATMUNG_COMPOSITION = new AqlFieldImp<BeatmungComposition>(BeatmungComposition.class, "", "BeatmungComposition", BeatmungComposition.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(BeatmungComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(BeatmungComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(BeatmungComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(BeatmungComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<String> STATUS_VALUE = new AqlFieldImp<String>(BeatmungComposition.class, "/context/other_context[at0001]/items[at0005]/value|value", "statusValue", String.class, this);

  public SelectAqlField<String> BERICHT_ID_VALUE = new AqlFieldImp<String>(BeatmungComposition.class, "/context/other_context[at0001]/items[at0002]/value|value", "berichtIdValue", String.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(BeatmungComposition.class, "/territory", "territory", Territory.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(BeatmungComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<BeobachtungenAmBeatmungsgeratObservation> BEOBACHTUNGEN_AM_BEATMUNGSGERAT = new ListAqlFieldImp<BeobachtungenAmBeatmungsgeratObservation>(BeatmungComposition.class, "/content[openEHR-EHR-OBSERVATION.ventilator_vital_signs.v0]", "beobachtungenAmBeatmungsgerat", BeobachtungenAmBeatmungsgeratObservation.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(BeatmungComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<SettingDefiningcode> SETTING_DEFININGCODE = new AqlFieldImp<SettingDefiningcode>(BeatmungComposition.class, "/context/setting|defining_code", "settingDefiningcode", SettingDefiningcode.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(BeatmungComposition.class, "/context/other_context[at0001]/items[at0006]", "erweiterung", Cluster.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(BeatmungComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<CategoryDefiningcode> CATEGORY_DEFININGCODE = new AqlFieldImp<CategoryDefiningcode>(BeatmungComposition.class, "/category|defining_code", "categoryDefiningcode", CategoryDefiningcode.class, this);

  private BeatmungCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.report.v1");
  }

  public static BeatmungCompositionContainment getInstance() {
    return new BeatmungCompositionContainment();
  }
}
