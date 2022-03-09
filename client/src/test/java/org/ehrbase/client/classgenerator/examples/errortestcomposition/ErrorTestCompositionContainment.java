package org.ehrbase.client.classgenerator.examples.errortestcomposition;

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
import org.ehrbase.client.classgenerator.examples.errortestcomposition.definition.LaboratoryTestResultObservation;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;

import java.time.temporal.TemporalAccessor;

public class ErrorTestCompositionContainment extends Containment {
  public SelectAqlField<ErrorTestComposition> ERROR_TEST_COMPOSITION =
      new AqlFieldImp<ErrorTestComposition>(
          ErrorTestComposition.class, "", "ErrorTestComposition", ErrorTestComposition.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE =
      new AqlFieldImp<Category>(
          ErrorTestComposition.class,
          "/category|defining_code",
          "categoryDefiningCode",
          Category.class,
          this);

  public SelectAqlField<String> REPORT_ID_VALUE =
      new AqlFieldImp<String>(
          ErrorTestComposition.class,
          "/context/other_context[at0001]/items[at0002]/value|value",
          "reportIdValue",
          String.class,
          this);

  public ListSelectAqlField<Cluster> EXTENSION =
      new ListAqlFieldImp<Cluster>(
          ErrorTestComposition.class,
          "/context/other_context[at0001]/items[at0006]",
          "extension",
          Cluster.class,
          this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          ErrorTestComposition.class,
          "/context/start_time|value",
          "startTimeValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Participation> PARTICIPATIONS =
      new ListAqlFieldImp<Participation>(
          ErrorTestComposition.class,
          "/context/participations",
          "participations",
          Participation.class,
          this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          ErrorTestComposition.class,
          "/context/end_time|value",
          "endTimeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<String> LOCATION =
      new AqlFieldImp<String>(
          ErrorTestComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY =
      new AqlFieldImp<PartyIdentified>(
          ErrorTestComposition.class,
          "/context/health_care_facility",
          "healthCareFacility",
          PartyIdentified.class,
          this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE =
      new AqlFieldImp<Setting>(
          ErrorTestComposition.class,
          "/context/setting|defining_code",
          "settingDefiningCode",
          Setting.class,
          this);

  public SelectAqlField<LaboratoryTestResultObservation> LABORATORY_TEST_RESULT =
      new AqlFieldImp<LaboratoryTestResultObservation>(
          ErrorTestComposition.class,
          "/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1]",
          "laboratoryTestResult",
          LaboratoryTestResultObservation.class,
          this);

  public SelectAqlField<PartyProxy> COMPOSER =
      new AqlFieldImp<PartyProxy>(
          ErrorTestComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          ErrorTestComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          ErrorTestComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Territory> TERRITORY =
      new AqlFieldImp<Territory>(
          ErrorTestComposition.class, "/territory", "territory", Territory.class, this);

  private ErrorTestCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.report.v1");
  }

  public static ErrorTestCompositionContainment getInstance() {
    return new ErrorTestCompositionContainment();
  }
}
