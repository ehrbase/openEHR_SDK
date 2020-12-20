package org.ehrbase.client.classgenerator.examples.episodeofcarecomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition.EpisodeofcareAdminEntry;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;

public class EpisodeOfCareCompositionContainment extends Containment {
  public SelectAqlField<EpisodeOfCareComposition> EPISODE_OF_CARE_COMPOSITION =
      new AqlFieldImp<EpisodeOfCareComposition>(
          EpisodeOfCareComposition.class,
          "",
          "EpisodeOfCareComposition",
          EpisodeOfCareComposition.class,
          this);

  public ListSelectAqlField<EpisodeofcareAdminEntry> EPISODEOFCARE =
      new ListAqlFieldImp<EpisodeofcareAdminEntry>(
          EpisodeOfCareComposition.class,
          "/content[openEHR-EHR-ADMIN_ENTRY.episodeofcare.v0]",
          "episodeofcare",
          EpisodeofcareAdminEntry.class,
          this);

  public SelectAqlField<PartyProxy> COMPOSER =
      new AqlFieldImp<PartyProxy>(
          EpisodeOfCareComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          EpisodeOfCareComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          EpisodeOfCareComposition.class,
          "/context/start_time|value",
          "startTimeValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Participation> PARTICIPATIONS =
      new ListAqlFieldImp<Participation>(
          EpisodeOfCareComposition.class,
          "/context/participations",
          "participations",
          Participation.class,
          this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          EpisodeOfCareComposition.class,
          "/context/end_time|value",
          "endTimeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<String> LOCATION =
      new AqlFieldImp<String>(
          EpisodeOfCareComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY =
      new AqlFieldImp<PartyIdentified>(
          EpisodeOfCareComposition.class,
          "/context/health_care_facility",
          "healthCareFacility",
          PartyIdentified.class,
          this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE =
      new AqlFieldImp<Setting>(
          EpisodeOfCareComposition.class,
          "/context/setting|defining_code",
          "settingDefiningCode",
          Setting.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          EpisodeOfCareComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE =
      new AqlFieldImp<Category>(
          EpisodeOfCareComposition.class,
          "/category|defining_code",
          "categoryDefiningCode",
          Category.class,
          this);

  public SelectAqlField<Territory> TERRITORY =
      new AqlFieldImp<Territory>(
          EpisodeOfCareComposition.class, "/territory", "territory", Territory.class, this);

  private EpisodeOfCareCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.versorgungsfall.v0");
  }

  public static EpisodeOfCareCompositionContainment getInstance() {
    return new EpisodeOfCareCompositionContainment();
  }
}
