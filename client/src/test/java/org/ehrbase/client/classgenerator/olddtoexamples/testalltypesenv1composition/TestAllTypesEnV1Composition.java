package org.ehrbase.client.classgenerator.olddtoexamples.testalltypesenv1composition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.CategoryDefiningcode;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.SettingDefiningcode;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.Territory;
import org.ehrbase.client.classgenerator.olddtoexamples.testalltypesenv1composition.definition.ContextCodedTextDefiningcode;
import org.ehrbase.client.classgenerator.olddtoexamples.testalltypesenv1composition.definition.TestAllTypesEvaluation;
import org.ehrbase.client.classgenerator.olddtoexamples.testalltypesenv1composition.definition.TestAllTypesObservation;
import org.ehrbase.client.classgenerator.olddtoexamples.testalltypesenv1composition.definition.TestAllTypesSection;
import org.ehrbase.client.openehrclient.VersionUid;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.test_all_types.v1")
@Template("test_all_types.en.v1")
public class TestAllTypesEnV1Composition {
  @Id private VersionUid versionUid;

  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  @Path("/context/participations")
  private List<Participation> participations;

  @Path("/language")
  private Language language;

  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  @Path("/territory")
  private Territory territory;

  @Path("/context/other_context[at0004]/item[at0005]/value|defining_code")
  private ContextCodedTextDefiningcode contextCodedTextDefiningcode;

  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  @Path("/content[openEHR-EHR-EVALUATION.test_all_types.v1]")
  private List<TestAllTypesEvaluation> testAllTypes;

  @Path("/content[openEHR-EHR-SECTION.test_all_types.v1]")
  private List<TestAllTypesSection> testAllTypesContentOpenehrEhrSectionTestAllTypesV1;

  @Path("/composer")
  private PartyProxy composer;

  @Path("/context/setting|defining_code")
  private SettingDefiningcode settingDefiningcode;

  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  @Path("/content[openEHR-EHR-OBSERVATION.test_all_types.v1]")
  private List<TestAllTypesObservation> testAllTypesContentOpenehrEhrObservationTestAllTypesV1;

  @Path("/context/location")
  private String location;

  @Path("/category|defining_code")
  private CategoryDefiningcode categoryDefiningcode;

  public VersionUid getVersionUid() {
    return this.versionUid;
  }

  public void setVersionUid(VersionUid versionUid) {
    this.versionUid = versionUid;
  }

  public void setEndTimeValue(TemporalAccessor endTimeValue) {
    this.endTimeValue = endTimeValue;
  }

  public TemporalAccessor getEndTimeValue() {
    return this.endTimeValue;
  }

  public void setParticipations(List<Participation> participations) {
    this.participations = participations;
  }

  public List<Participation> getParticipations() {
    return this.participations;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public Language getLanguage() {
    return this.language;
  }

  public void setHealthCareFacility(PartyIdentified healthCareFacility) {
    this.healthCareFacility = healthCareFacility;
  }

  public PartyIdentified getHealthCareFacility() {
    return this.healthCareFacility;
  }

  public void setTerritory(Territory territory) {
    this.territory = territory;
  }

  public Territory getTerritory() {
    return this.territory;
  }

  public void setContextCodedTextDefiningcode(
      ContextCodedTextDefiningcode contextCodedTextDefiningcode) {
    this.contextCodedTextDefiningcode = contextCodedTextDefiningcode;
  }

  public ContextCodedTextDefiningcode getContextCodedTextDefiningcode() {
    return this.contextCodedTextDefiningcode;
  }

  public void setStartTimeValue(TemporalAccessor startTimeValue) {
    this.startTimeValue = startTimeValue;
  }

  public TemporalAccessor getStartTimeValue() {
    return this.startTimeValue;
  }

  public void setTestAllTypes(List<TestAllTypesEvaluation> testAllTypes) {
    this.testAllTypes = testAllTypes;
  }

  public List<TestAllTypesEvaluation> getTestAllTypes() {
    return this.testAllTypes;
  }

  public void setTestAllTypesContentOpenehrEhrSectionTestAllTypesV1(
      List<TestAllTypesSection> testAllTypesContentOpenehrEhrSectionTestAllTypesV1) {
    this.testAllTypesContentOpenehrEhrSectionTestAllTypesV1 =
        testAllTypesContentOpenehrEhrSectionTestAllTypesV1;
  }

  public List<TestAllTypesSection> getTestAllTypesContentOpenehrEhrSectionTestAllTypesV1() {
    return this.testAllTypesContentOpenehrEhrSectionTestAllTypesV1;
  }

  public void setComposer(PartyProxy composer) {
    this.composer = composer;
  }

  public PartyProxy getComposer() {
    return this.composer;
  }

  public void setSettingDefiningcode(SettingDefiningcode settingDefiningcode) {
    this.settingDefiningcode = settingDefiningcode;
  }

  public SettingDefiningcode getSettingDefiningcode() {
    return this.settingDefiningcode;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }

  public void setTestAllTypesContentOpenehrEhrObservationTestAllTypesV1(
      List<TestAllTypesObservation> testAllTypesContentOpenehrEhrObservationTestAllTypesV1) {
    this.testAllTypesContentOpenehrEhrObservationTestAllTypesV1 =
        testAllTypesContentOpenehrEhrObservationTestAllTypesV1;
  }

  public List<TestAllTypesObservation> getTestAllTypesContentOpenehrEhrObservationTestAllTypesV1() {
    return this.testAllTypesContentOpenehrEhrObservationTestAllTypesV1;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getLocation() {
    return this.location;
  }

  public void setCategoryDefiningcode(CategoryDefiningcode categoryDefiningcode) {
    this.categoryDefiningcode = categoryDefiningcode;
  }

  public CategoryDefiningcode getCategoryDefiningcode() {
    return this.categoryDefiningcode;
  }
}
