package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition.ContextCodedTextDefiningCode;
import org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition.TestAllTypesEvaluation;
import org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition.TestAllTypesObservation;
import org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition.TestAllTypesSection;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.test_all_types.v1")
@Template("test_all_types.en.v1")
public class TestAllTypesEnV1Composition {
  @Path("/context/other_context[at0004]/item[at0005]/value|defining_code")
  private ContextCodedTextDefiningCode contextCodedTextDefiningCode;

  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  @Path("/context/participations")
  private List<Participation> participations;

  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  @Path("/context/location")
  private String location;

  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  @Path("/context/setting")
  private DvCodedText setting;

  @Path("/content[openEHR-EHR-OBSERVATION.test_all_types.v1]")
  private List<TestAllTypesObservation> testAllTypesTestAllTypes;

  @Path("/content[openEHR-EHR-EVALUATION.test_all_types.v1]")
  private List<TestAllTypesEvaluation> testAllTypesTestAllTypes2;

  @Path("/content[openEHR-EHR-SECTION.test_all_types.v1]")
  private List<TestAllTypesSection> testAllTypesTestAllTypes3;

  @Path("/composer")
  private PartyProxy composer;

  @Path("/language")
  private Language language;

  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  @Path("/category")
  private DvCodedText category;

  @Path("/territory")
  private Territory territory;

  @Id
  private VersionUid versionUid;

  public void setContextCodedTextDefiningCode(
      ContextCodedTextDefiningCode contextCodedTextDefiningCode) {
     this.contextCodedTextDefiningCode = contextCodedTextDefiningCode;
  }

  public ContextCodedTextDefiningCode getContextCodedTextDefiningCode() {
     return this.contextCodedTextDefiningCode ;
  }

  public void setStartTimeValue(TemporalAccessor startTimeValue) {
     this.startTimeValue = startTimeValue;
  }

  public TemporalAccessor getStartTimeValue() {
     return this.startTimeValue ;
  }

  public void setParticipations(List<Participation> participations) {
     this.participations = participations;
  }

  public List<Participation> getParticipations() {
     return this.participations ;
  }

  public void setEndTimeValue(TemporalAccessor endTimeValue) {
     this.endTimeValue = endTimeValue;
  }

  public TemporalAccessor getEndTimeValue() {
     return this.endTimeValue ;
  }

  public void setLocation(String location) {
     this.location = location;
  }

  public String getLocation() {
     return this.location ;
  }

  public void setHealthCareFacility(PartyIdentified healthCareFacility) {
     this.healthCareFacility = healthCareFacility;
  }

  public PartyIdentified getHealthCareFacility() {
     return this.healthCareFacility ;
  }

  public void setSetting(DvCodedText setting) {
     this.setting = setting;
  }

  public DvCodedText getSetting() {
     return this.setting ;
  }

  public void setTestAllTypesTestAllTypes(List<TestAllTypesObservation> testAllTypesTestAllTypes) {
     this.testAllTypesTestAllTypes = testAllTypesTestAllTypes;
  }

  public List<TestAllTypesObservation> getTestAllTypesTestAllTypes() {
     return this.testAllTypesTestAllTypes ;
  }

  public void setTestAllTypesTestAllTypes2(List<TestAllTypesEvaluation> testAllTypesTestAllTypes2) {
     this.testAllTypesTestAllTypes2 = testAllTypesTestAllTypes2;
  }

  public List<TestAllTypesEvaluation> getTestAllTypesTestAllTypes2() {
     return this.testAllTypesTestAllTypes2 ;
  }

  public void setTestAllTypesTestAllTypes3(List<TestAllTypesSection> testAllTypesTestAllTypes3) {
     this.testAllTypesTestAllTypes3 = testAllTypesTestAllTypes3;
  }

  public List<TestAllTypesSection> getTestAllTypesTestAllTypes3() {
     return this.testAllTypesTestAllTypes3 ;
  }

  public void setComposer(PartyProxy composer) {
     this.composer = composer;
  }

  public PartyProxy getComposer() {
     return this.composer ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setCategory(DvCodedText category) {
     this.category = category;
  }

  public DvCodedText getCategory() {
     return this.category ;
  }

  public void setTerritory(Territory territory) {
     this.territory = territory;
  }

  public Territory getTerritory() {
     return this.territory ;
  }

  public VersionUid getVersionUid() {
     return this.versionUid ;
  }

  public void setVersionUid(VersionUid versionUid) {
     this.versionUid = versionUid;
  }
}
