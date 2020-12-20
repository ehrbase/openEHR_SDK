package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition;

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
import org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition.ContextCodedTextDefiningCode;
import org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition.TestAllTypesEvaluation;
import org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition.TestAllTypesObservation;
import org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition.TestAllTypesSection;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;

public class TestAllTypesEnV1CompositionContainment extends Containment {
  public SelectAqlField<TestAllTypesEnV1Composition> TEST_ALL_TYPES_EN_V1_COMPOSITION =
      new AqlFieldImp<TestAllTypesEnV1Composition>(
          TestAllTypesEnV1Composition.class,
          "",
          "TestAllTypesEnV1Composition",
          TestAllTypesEnV1Composition.class,
          this);

  public SelectAqlField<ContextCodedTextDefiningCode> CONTEXT_CODED_TEXT_DEFINING_CODE =
      new AqlFieldImp<ContextCodedTextDefiningCode>(
          TestAllTypesEnV1Composition.class,
          "/context/other_context[at0004]/item[at0005]/value|defining_code",
          "contextCodedTextDefiningCode",
          ContextCodedTextDefiningCode.class,
          this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          TestAllTypesEnV1Composition.class,
          "/context/start_time|value",
          "startTimeValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Participation> PARTICIPATIONS =
      new ListAqlFieldImp<Participation>(
          TestAllTypesEnV1Composition.class,
          "/context/participations",
          "participations",
          Participation.class,
          this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          TestAllTypesEnV1Composition.class,
          "/context/end_time|value",
          "endTimeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<String> LOCATION =
      new AqlFieldImp<String>(
          TestAllTypesEnV1Composition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY =
      new AqlFieldImp<PartyIdentified>(
          TestAllTypesEnV1Composition.class,
          "/context/health_care_facility",
          "healthCareFacility",
          PartyIdentified.class,
          this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE =
      new AqlFieldImp<Setting>(
          TestAllTypesEnV1Composition.class,
          "/context/setting|defining_code",
          "settingDefiningCode",
          Setting.class,
          this);

  public ListSelectAqlField<TestAllTypesObservation> TEST_ALL_TYPES =
      new ListAqlFieldImp<TestAllTypesObservation>(
          TestAllTypesEnV1Composition.class,
          "/content[openEHR-EHR-OBSERVATION.test_all_types.v1]",
          "testAllTypes",
          TestAllTypesObservation.class,
          this);

  public ListSelectAqlField<TestAllTypesEvaluation> TEST_ALL_TYPES2 =
      new ListAqlFieldImp<TestAllTypesEvaluation>(
          TestAllTypesEnV1Composition.class,
          "/content[openEHR-EHR-EVALUATION.test_all_types.v1]",
          "testAllTypes2",
          TestAllTypesEvaluation.class,
          this);

  public ListSelectAqlField<TestAllTypesSection> TEST_ALL_TYPES3 =
      new ListAqlFieldImp<TestAllTypesSection>(
          TestAllTypesEnV1Composition.class,
          "/content[openEHR-EHR-SECTION.test_all_types.v1]",
          "testAllTypes3",
          TestAllTypesSection.class,
          this);

  public SelectAqlField<PartyProxy> COMPOSER =
      new AqlFieldImp<PartyProxy>(
          TestAllTypesEnV1Composition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          TestAllTypesEnV1Composition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          TestAllTypesEnV1Composition.class,
          "/feeder_audit",
          "feederAudit",
          FeederAudit.class,
          this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE =
      new AqlFieldImp<Category>(
          TestAllTypesEnV1Composition.class,
          "/category|defining_code",
          "categoryDefiningCode",
          Category.class,
          this);

  public SelectAqlField<Territory> TERRITORY =
      new AqlFieldImp<Territory>(
          TestAllTypesEnV1Composition.class, "/territory", "territory", Territory.class, this);

  private TestAllTypesEnV1CompositionContainment() {
    super("openEHR-EHR-COMPOSITION.test_all_types.v1");
  }

  public static TestAllTypesEnV1CompositionContainment getInstance() {
    return new TestAllTypesEnV1CompositionContainment();
  }
}
