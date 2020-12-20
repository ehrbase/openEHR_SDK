package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition;

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
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.AbteilungsfallCluster;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.VersorgungsfallCluster;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.VersorgungsortAdminEntry;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;

public class PatientenaufenthaltCompositionContainment extends Containment {
  public SelectAqlField<PatientenaufenthaltComposition> PATIENTENAUFENTHALT_COMPOSITION =
      new AqlFieldImp<PatientenaufenthaltComposition>(
          PatientenaufenthaltComposition.class,
          "",
          "PatientenaufenthaltComposition",
          PatientenaufenthaltComposition.class,
          this);

  public SelectAqlField<VersorgungsfallCluster> VERSORGUNGSFALL =
      new AqlFieldImp<VersorgungsfallCluster>(
          PatientenaufenthaltComposition.class,
          "/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0]",
          "versorgungsfall",
          VersorgungsfallCluster.class,
          this);

  public SelectAqlField<AbteilungsfallCluster> ABTEILUNGSFALL =
      new AqlFieldImp<AbteilungsfallCluster>(
          PatientenaufenthaltComposition.class,
          "/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0]",
          "abteilungsfall",
          AbteilungsfallCluster.class,
          this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          PatientenaufenthaltComposition.class,
          "/context/start_time|value",
          "startTimeValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Participation> PARTICIPATIONS =
      new ListAqlFieldImp<Participation>(
          PatientenaufenthaltComposition.class,
          "/context/participations",
          "participations",
          Participation.class,
          this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          PatientenaufenthaltComposition.class,
          "/context/end_time|value",
          "endTimeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<String> LOCATION =
      new AqlFieldImp<String>(
          PatientenaufenthaltComposition.class,
          "/context/location",
          "location",
          String.class,
          this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY =
      new AqlFieldImp<PartyIdentified>(
          PatientenaufenthaltComposition.class,
          "/context/health_care_facility",
          "healthCareFacility",
          PartyIdentified.class,
          this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE =
      new AqlFieldImp<Setting>(
          PatientenaufenthaltComposition.class,
          "/context/setting|defining_code",
          "settingDefiningCode",
          Setting.class,
          this);

  public SelectAqlField<VersorgungsortAdminEntry> VERSORGUNGSORT =
      new AqlFieldImp<VersorgungsortAdminEntry>(
          PatientenaufenthaltComposition.class,
          "/content[openEHR-EHR-ADMIN_ENTRY.hospitalization.v0]",
          "versorgungsort",
          VersorgungsortAdminEntry.class,
          this);

  public SelectAqlField<PartyProxy> COMPOSER =
      new AqlFieldImp<PartyProxy>(
          PatientenaufenthaltComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          PatientenaufenthaltComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          PatientenaufenthaltComposition.class,
          "/feeder_audit",
          "feederAudit",
          FeederAudit.class,
          this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE =
      new AqlFieldImp<Category>(
          PatientenaufenthaltComposition.class,
          "/category|defining_code",
          "categoryDefiningCode",
          Category.class,
          this);

  public SelectAqlField<Territory> TERRITORY =
      new AqlFieldImp<Territory>(
          PatientenaufenthaltComposition.class, "/territory", "territory", Territory.class, this);

  private PatientenaufenthaltCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.event_summary.v0");
  }

  public static PatientenaufenthaltCompositionContainment getInstance() {
    return new PatientenaufenthaltCompositionContainment();
  }
}
