package org.ehrbase.client.classgenerator.olddtoexamples.testalltypesenv1composition;

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
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.CategoryDefiningcode;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.SettingDefiningcode;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.Territory;
import org.ehrbase.client.classgenerator.olddtoexamples.testalltypesenv1composition.definition.ContextCodedTextDefiningcode;
import org.ehrbase.client.classgenerator.olddtoexamples.testalltypesenv1composition.definition.TestAllTypesEvaluation;
import org.ehrbase.client.classgenerator.olddtoexamples.testalltypesenv1composition.definition.TestAllTypesObservation;
import org.ehrbase.client.classgenerator.olddtoexamples.testalltypesenv1composition.definition.TestAllTypesSection;

public class TestAllTypesEnV1CompositionContainment extends Containment {
  public SelectAqlField<TestAllTypesEnV1Composition> TEST_ALL_TYPES_EN_V1_COMPOSITION =
      new AqlFieldImp<TestAllTypesEnV1Composition>(
          TestAllTypesEnV1Composition.class,
          "",
          "TestAllTypesEnV1Composition",
          TestAllTypesEnV1Composition.class,
          this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          TestAllTypesEnV1Composition.class,
          "/context/end_time|value",
          "endTimeValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<Participation> PARTICIPATIONS =
      new ListAqlFieldImp<Participation>(
          TestAllTypesEnV1Composition.class,
          "/context/participations",
          "participations",
          Participation.class,
          this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          TestAllTypesEnV1Composition.class, "/language", "language", Language.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY =
      new AqlFieldImp<PartyIdentified>(
          TestAllTypesEnV1Composition.class,
          "/context/health_care_facility",
          "healthCareFacility",
          PartyIdentified.class,
          this);

  public SelectAqlField<Territory> TERRITORY =
      new AqlFieldImp<Territory>(
          TestAllTypesEnV1Composition.class, "/territory", "territory", Territory.class, this);

  public SelectAqlField<ContextCodedTextDefiningcode> CONTEXT_CODED_TEXT_DEFININGCODE =
      new AqlFieldImp<ContextCodedTextDefiningcode>(
          TestAllTypesEnV1Composition.class,
          "/context/other_context[at0004]/item[at0005]/value|defining_code",
          "contextCodedTextDefiningcode",
          ContextCodedTextDefiningcode.class,
          this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          TestAllTypesEnV1Composition.class,
          "/context/start_time|value",
          "startTimeValue",
          TemporalAccessor.class,
          this);

  public ListSelectAqlField<TestAllTypesEvaluation> TEST_ALL_TYPES =
      new ListAqlFieldImp<TestAllTypesEvaluation>(
          TestAllTypesEnV1Composition.class,
          "/content[openEHR-EHR-EVALUATION.test_all_types.v1]",
          "testAllTypes",
          TestAllTypesEvaluation.class,
          this);

  public ListSelectAqlField<TestAllTypesSection>
      TEST_ALL_TYPES_CONTENT_OPENEHR_EHR_SECTION_TEST_ALL_TYPES_V1 =
          new ListAqlFieldImp<TestAllTypesSection>(
              TestAllTypesEnV1Composition.class,
              "/content[openEHR-EHR-SECTION.test_all_types.v1]",
              "testAllTypesContentOpenehrEhrSectionTestAllTypesV1",
              TestAllTypesSection.class,
              this);

  public SelectAqlField<PartyProxy> COMPOSER =
      new AqlFieldImp<PartyProxy>(
          TestAllTypesEnV1Composition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<SettingDefiningcode> SETTING_DEFININGCODE =
      new AqlFieldImp<SettingDefiningcode>(
          TestAllTypesEnV1Composition.class,
          "/context/setting|defining_code",
          "settingDefiningcode",
          SettingDefiningcode.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          TestAllTypesEnV1Composition.class,
          "/feeder_audit",
          "feederAudit",
          FeederAudit.class,
          this);

  public ListSelectAqlField<TestAllTypesObservation>
      TEST_ALL_TYPES_CONTENT_OPENEHR_EHR_OBSERVATION_TEST_ALL_TYPES_V1 =
          new ListAqlFieldImp<TestAllTypesObservation>(
              TestAllTypesEnV1Composition.class,
              "/content[openEHR-EHR-OBSERVATION.test_all_types.v1]",
              "testAllTypesContentOpenehrEhrObservationTestAllTypesV1",
              TestAllTypesObservation.class,
              this);

  public SelectAqlField<String> LOCATION =
      new AqlFieldImp<String>(
          TestAllTypesEnV1Composition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<CategoryDefiningcode> CATEGORY_DEFININGCODE =
      new AqlFieldImp<CategoryDefiningcode>(
          TestAllTypesEnV1Composition.class,
          "/category|defining_code",
          "categoryDefiningcode",
          CategoryDefiningcode.class,
          this);

  private TestAllTypesEnV1CompositionContainment() {
    super("openEHR-EHR-COMPOSITION.test_all_types.v1");
  }

  public static TestAllTypesEnV1CompositionContainment getInstance() {
    return new TestAllTypesEnV1CompositionContainment();
  }
}
