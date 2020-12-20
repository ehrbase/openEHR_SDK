package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition.ContextCodedTextDefiningCode;
import org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition.TestAllTypesEvaluation;
import org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition.TestAllTypesObservation;
import org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition.TestAllTypesSection;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.test_all_types.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:10.792494500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@Template("test_all_types.en.v1")
public class TestAllTypesEnV1Composition implements CompositionEntity {
  /** Path: Test all types/context/context coded text Description: * */
  @Path("/context/other_context[at0004]/item[at0005]/value|defining_code")
  private ContextCodedTextDefiningCode contextCodedTextDefiningCode;

  /** Path: Test all types/context/start_time */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /** Path: Test all types/context/participations */
  @Path("/context/participations")
  private List<Participation> participations;

  /** Path: Test all types/context/end_time */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /** Path: Test all types/context/location */
  @Path("/context/location")
  private String location;

  /** Path: Test all types/context/health_care_facility */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /** Path: Test all types/context/setting */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /** Path: Test all types/Test all types Description: unknown */
  @Path("/content[openEHR-EHR-OBSERVATION.test_all_types.v1]")
  private List<TestAllTypesObservation> testAllTypes;

  /** Path: Test all types/Test all types Description: unknown */
  @Path("/content[openEHR-EHR-EVALUATION.test_all_types.v1]")
  private List<TestAllTypesEvaluation> testAllTypes2;

  /** Path: Test all types/Test all types Description: unknown */
  @Path("/content[openEHR-EHR-SECTION.test_all_types.v1]")
  private List<TestAllTypesSection> testAllTypes3;

  /** Path: Test all types/composer */
  @Path("/composer")
  private PartyProxy composer;

  /** Path: Test all types/language */
  @Path("/language")
  private Language language;

  /** Path: Test all types/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /** Path: Test all types/category */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /** Path: Test all types/territory */
  @Path("/territory")
  private Territory territory;

  @Id private VersionUid versionUid;

  public void setContextCodedTextDefiningCode(
      ContextCodedTextDefiningCode contextCodedTextDefiningCode) {
    this.contextCodedTextDefiningCode = contextCodedTextDefiningCode;
  }

  public ContextCodedTextDefiningCode getContextCodedTextDefiningCode() {
    return this.contextCodedTextDefiningCode;
  }

  public void setStartTimeValue(TemporalAccessor startTimeValue) {
    this.startTimeValue = startTimeValue;
  }

  public TemporalAccessor getStartTimeValue() {
    return this.startTimeValue;
  }

  public void setParticipations(List<Participation> participations) {
    this.participations = participations;
  }

  public List<Participation> getParticipations() {
    return this.participations;
  }

  public void setEndTimeValue(TemporalAccessor endTimeValue) {
    this.endTimeValue = endTimeValue;
  }

  public TemporalAccessor getEndTimeValue() {
    return this.endTimeValue;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getLocation() {
    return this.location;
  }

  public void setHealthCareFacility(PartyIdentified healthCareFacility) {
    this.healthCareFacility = healthCareFacility;
  }

  public PartyIdentified getHealthCareFacility() {
    return this.healthCareFacility;
  }

  public void setSettingDefiningCode(Setting settingDefiningCode) {
    this.settingDefiningCode = settingDefiningCode;
  }

  public Setting getSettingDefiningCode() {
    return this.settingDefiningCode;
  }

  public void setTestAllTypes(List<TestAllTypesObservation> testAllTypes) {
    this.testAllTypes = testAllTypes;
  }

  public List<TestAllTypesObservation> getTestAllTypes() {
    return this.testAllTypes;
  }

  public void setTestAllTypes2(List<TestAllTypesEvaluation> testAllTypes2) {
    this.testAllTypes2 = testAllTypes2;
  }

  public List<TestAllTypesEvaluation> getTestAllTypes2() {
    return this.testAllTypes2;
  }

  public void setTestAllTypes3(List<TestAllTypesSection> testAllTypes3) {
    this.testAllTypes3 = testAllTypes3;
  }

  public List<TestAllTypesSection> getTestAllTypes3() {
    return this.testAllTypes3;
  }

  public void setComposer(PartyProxy composer) {
    this.composer = composer;
  }

  public PartyProxy getComposer() {
    return this.composer;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public Language getLanguage() {
    return this.language;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }

  public void setCategoryDefiningCode(Category categoryDefiningCode) {
    this.categoryDefiningCode = categoryDefiningCode;
  }

  public Category getCategoryDefiningCode() {
    return this.categoryDefiningCode;
  }

  public void setTerritory(Territory territory) {
    this.territory = territory;
  }

  public Territory getTerritory() {
    return this.territory;
  }

  public VersionUid getVersionUid() {
    return this.versionUid;
  }

  public void setVersionUid(VersionUid versionUid) {
    this.versionUid = versionUid;
  }
}
