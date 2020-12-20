package org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition.AufnahmedatenAdminEntry;
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition.EntlassungsdatenAdminEntry;
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition.FalltypDefiningCode;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;

public class StationarerVersorgungsfallCompositionContainment extends Containment {
  public SelectAqlField<StationarerVersorgungsfallComposition>
      STATIONARER_VERSORGUNGSFALL_COMPOSITION =
          new AqlFieldImp<StationarerVersorgungsfallComposition>(
              StationarerVersorgungsfallComposition.class,
              "",
              "StationarerVersorgungsfallComposition",
              StationarerVersorgungsfallComposition.class,
              this);

  public SelectAqlField<FalltypDefiningCode> FALLTYP_DEFINING_CODE =
      new AqlFieldImp<FalltypDefiningCode>(
          StationarerVersorgungsfallComposition.class,
          "/context/other_context[at0001]/items[at0005]/value|defining_code",
          "falltypDefiningCode",
          FalltypDefiningCode.class,
          this);

  public SelectAqlField<String> FALLKLASSE_VALUE =
      new AqlFieldImp<String>(
          StationarerVersorgungsfallComposition.class,
          "/context/other_context[at0001]/items[at0004]/value|value",
          "fallklasseValue",
          String.class,
          this);

  public SelectAqlField<String> FALL_KENNUNG_VALUE =
      new AqlFieldImp<String>(
          StationarerVersorgungsfallComposition.class,
          "/context/other_context[at0001]/items[at0003]/value|value",
          "fallKennungValue",
          String.class,
          this);

  public ListSelectAqlField<Cluster> ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          StationarerVersorgungsfallComposition.class,
          "/context/other_context[at0001]/items[at0002]",
          "erweiterung",
          Cluster.class,
          this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          StationarerVersorgungsfallComposition.class,
          "/context/start_time|value",
          "startTimeValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Participation> PARTICIPATIONS =
      new ListAqlFieldImp<Participation>(
          StationarerVersorgungsfallComposition.class,
          "/context/participations",
          "participations",
          Participation.class,
          this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          StationarerVersorgungsfallComposition.class,
          "/context/end_time|value",
          "endTimeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<String> LOCATION =
      new AqlFieldImp<String>(
          StationarerVersorgungsfallComposition.class,
          "/context/location",
          "location",
          String.class,
          this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY =
      new AqlFieldImp<PartyIdentified>(
          StationarerVersorgungsfallComposition.class,
          "/context/health_care_facility",
          "healthCareFacility",
          PartyIdentified.class,
          this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE =
      new AqlFieldImp<Setting>(
          StationarerVersorgungsfallComposition.class,
          "/context/setting|defining_code",
          "settingDefiningCode",
          Setting.class,
          this);

  public SelectAqlField<AufnahmedatenAdminEntry> AUFNAHMEDATEN =
      new AqlFieldImp<AufnahmedatenAdminEntry>(
          StationarerVersorgungsfallComposition.class,
          "/content[openEHR-EHR-ADMIN_ENTRY.admission.v0]",
          "aufnahmedaten",
          AufnahmedatenAdminEntry.class,
          this);

  public SelectAqlField<EntlassungsdatenAdminEntry> ENTLASSUNGSDATEN =
      new AqlFieldImp<EntlassungsdatenAdminEntry>(
          StationarerVersorgungsfallComposition.class,
          "/content[openEHR-EHR-ADMIN_ENTRY.discharge_summary.v0]",
          "entlassungsdaten",
          EntlassungsdatenAdminEntry.class,
          this);

  public SelectAqlField<PartyProxy> COMPOSER =
      new AqlFieldImp<PartyProxy>(
          StationarerVersorgungsfallComposition.class,
          "/composer",
          "composer",
          PartyProxy.class,
          this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          StationarerVersorgungsfallComposition.class,
          "/language",
          "language",
          Language.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          StationarerVersorgungsfallComposition.class,
          "/feeder_audit",
          "feederAudit",
          FeederAudit.class,
          this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE =
      new AqlFieldImp<Category>(
          StationarerVersorgungsfallComposition.class,
          "/category|defining_code",
          "categoryDefiningCode",
          Category.class,
          this);

  public SelectAqlField<Territory> TERRITORY =
      new AqlFieldImp<Territory>(
          StationarerVersorgungsfallComposition.class,
          "/territory",
          "territory",
          Territory.class,
          this);

  private StationarerVersorgungsfallCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.fall.v0");
  }

  public static StationarerVersorgungsfallCompositionContainment getInstance() {
    return new StationarerVersorgungsfallCompositionContainment();
  }
}
