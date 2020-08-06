package org.ehrbase.client.classgenerator.examples.episodeofcarecomposition;

import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition.EpisodeofcareAdminEntry;
import org.ehrbase.client.classgenerator.examples.shareddefinition.CategoryDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.examples.shareddefinition.SettingDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Territory;

import java.time.temporal.TemporalAccessor;

public class EpisodeOfCareCompositionContainment extends Containment {
  public SelectAqlField<EpisodeOfCareComposition> EPISODE_OF_CARE_COMPOSITION = new AqlFieldImp<EpisodeOfCareComposition>(EpisodeOfCareComposition.class, "", "EpisodeOfCareComposition", EpisodeOfCareComposition.class, this);

  public ListSelectAqlField<EpisodeofcareAdminEntry> EPISODEOFCARE = new ListAqlFieldImp<EpisodeofcareAdminEntry>(EpisodeOfCareComposition.class, "/content[openEHR-EHR-ADMIN_ENTRY.episodeofcare.v0]", "episodeofcare", EpisodeofcareAdminEntry.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(EpisodeOfCareComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(EpisodeOfCareComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(EpisodeOfCareComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<PartyIdentified> HEALTHCAREFACILITY = new AqlFieldImp<PartyIdentified>(EpisodeOfCareComposition.class, "/context/health_care_facility", "healthcarefacility", PartyIdentified.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(EpisodeOfCareComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<SettingDefiningcode> SETTING_DEFININGCODE = new AqlFieldImp<SettingDefiningcode>(EpisodeOfCareComposition.class, "/context/setting|defining_code", "settingDefiningcode", SettingDefiningcode.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(EpisodeOfCareComposition.class, "/territory", "territory", Territory.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(EpisodeOfCareComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<CategoryDefiningcode> CATEGORY_DEFININGCODE = new AqlFieldImp<CategoryDefiningcode>(EpisodeOfCareComposition.class, "/category|defining_code", "categoryDefiningcode", CategoryDefiningcode.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(EpisodeOfCareComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  private EpisodeOfCareCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.versorgungsfall.v0");
  }

  public static EpisodeOfCareCompositionContainment getInstance() {
    return new EpisodeOfCareCompositionContainment();
  }
}
