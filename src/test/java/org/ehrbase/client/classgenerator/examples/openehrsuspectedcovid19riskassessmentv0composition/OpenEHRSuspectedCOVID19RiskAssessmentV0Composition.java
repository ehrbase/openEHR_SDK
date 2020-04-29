package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition;

import com.nedap.archie.rm.datastructures.Cluster;
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
import org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition.BodyTemperatureObservation;
import org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition.DiagnosisEvaluation;
import org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition.HealthRiskAssessmentEvaluation;
import org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition.HistoryObservation;
import org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition.LivingArrangementEvaluation;
import org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition.OccupationSummaryEvaluation;
import org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition.ServiceRequestInstruction;
import org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition.SignScreeningQuestionnaireObservation;
import org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition.TravelEventObservation;
import org.ehrbase.client.classgenerator.examples.shareddefinition.CategoryDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.examples.shareddefinition.SettingDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.encounter.v1")
@Template("openEHR suspected COVID-19 risk assessment.v0")
public class OpenEHRSuspectedCOVID19RiskAssessmentV0Composition {
  @Id
  private VersionUid versionUid;

  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  @Path("/content[openEHR-EHR-EVALUATION.living_arrangement.v0]")
  private LivingArrangementEvaluation livingArrangement;

  @Path("/context/participations")
  private List<Participation> participations;

  @Path("/content[openEHR-EHR-EVALUATION.problem_diagnosis.v1]")
  private List<DiagnosisEvaluation> diagnosis;

  @Path("/language")
  private Language language;

  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  @Path("/content[openEHR-EHR-OBSERVATION.travel_event.v0]")
  private List<TravelEventObservation> travelEvent;

  @Path("/content[openEHR-EHR-OBSERVATION.story.v1]")
  private List<HistoryObservation> history;

  @Path("/territory")
  private Territory territory;

  @Path("/content[openEHR-EHR-EVALUATION.health_risk.v1]")
  private List<HealthRiskAssessmentEvaluation> healthRiskAssessment;

  @Path("/content[openEHR-EHR-EVALUATION.occupation_summary.v1]")
  private OccupationSummaryEvaluation occupationSummary;

  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  @Path("/content[openEHR-EHR-INSTRUCTION.service_request.v1]")
  private List<ServiceRequestInstruction> serviceRequest;

  @Path("/composer")
  private PartyProxy composer;

  @Path("/context/setting|defining_code")
  private SettingDefiningcode settingDefiningcode;

  @Path("/content[openEHR-EHR-OBSERVATION.body_temperature.v2]")
  private List<BodyTemperatureObservation> bodyTemperature;

  @Path("/context/location")
  private String location;

  @Path("/content[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]")
  private List<SignScreeningQuestionnaireObservation> signScreeningQuestionnaire;

  @Path("/category|defining_code")
  private CategoryDefiningcode categoryDefiningcode;

  @Path("/context/other_context[at0001]/items[at0002]")
  private List<Cluster> extension;

  public VersionUid getVersionUid() {
     return this.versionUid ;
  }

  public void setVersionUid(VersionUid versionUid) {
     this.versionUid = versionUid;
  }

  public void setEndTimeValue(TemporalAccessor endTimeValue) {
     this.endTimeValue = endTimeValue;
  }

  public TemporalAccessor getEndTimeValue() {
     return this.endTimeValue ;
  }

  public void setLivingArrangement(LivingArrangementEvaluation livingArrangement) {
     this.livingArrangement = livingArrangement;
  }

  public LivingArrangementEvaluation getLivingArrangement() {
     return this.livingArrangement ;
  }

  public void setParticipations(List<Participation> participations) {
     this.participations = participations;
  }

  public List<Participation> getParticipations() {
     return this.participations ;
  }

  public void setDiagnosis(List<DiagnosisEvaluation> diagnosis) {
     this.diagnosis = diagnosis;
  }

  public List<DiagnosisEvaluation> getDiagnosis() {
     return this.diagnosis ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setHealthCareFacility(PartyIdentified healthCareFacility) {
     this.healthCareFacility = healthCareFacility;
  }

  public PartyIdentified getHealthCareFacility() {
     return this.healthCareFacility ;
  }

  public void setTravelEvent(List<TravelEventObservation> travelEvent) {
     this.travelEvent = travelEvent;
  }

  public List<TravelEventObservation> getTravelEvent() {
     return this.travelEvent ;
  }

  public void setHistory(List<HistoryObservation> history) {
     this.history = history;
  }

  public List<HistoryObservation> getHistory() {
     return this.history ;
  }

  public void setTerritory(Territory territory) {
     this.territory = territory;
  }

  public Territory getTerritory() {
     return this.territory ;
  }

  public void setHealthRiskAssessment(List<HealthRiskAssessmentEvaluation> healthRiskAssessment) {
     this.healthRiskAssessment = healthRiskAssessment;
  }

  public List<HealthRiskAssessmentEvaluation> getHealthRiskAssessment() {
     return this.healthRiskAssessment ;
  }

  public void setOccupationSummary(OccupationSummaryEvaluation occupationSummary) {
     this.occupationSummary = occupationSummary;
  }

  public OccupationSummaryEvaluation getOccupationSummary() {
     return this.occupationSummary ;
  }

  public void setStartTimeValue(TemporalAccessor startTimeValue) {
     this.startTimeValue = startTimeValue;
  }

  public TemporalAccessor getStartTimeValue() {
     return this.startTimeValue ;
  }

  public void setServiceRequest(List<ServiceRequestInstruction> serviceRequest) {
     this.serviceRequest = serviceRequest;
  }

  public List<ServiceRequestInstruction> getServiceRequest() {
     return this.serviceRequest ;
  }

  public void setComposer(PartyProxy composer) {
     this.composer = composer;
  }

  public PartyProxy getComposer() {
     return this.composer ;
  }

  public void setSettingDefiningcode(SettingDefiningcode settingDefiningcode) {
     this.settingDefiningcode = settingDefiningcode;
  }

  public SettingDefiningcode getSettingDefiningcode() {
     return this.settingDefiningcode ;
  }

  public void setBodyTemperature(List<BodyTemperatureObservation> bodyTemperature) {
     this.bodyTemperature = bodyTemperature;
  }

  public List<BodyTemperatureObservation> getBodyTemperature() {
     return this.bodyTemperature ;
  }

  public void setLocation(String location) {
     this.location = location;
  }

  public String getLocation() {
     return this.location ;
  }

  public void setSignScreeningQuestionnaire(
      List<SignScreeningQuestionnaireObservation> signScreeningQuestionnaire) {
     this.signScreeningQuestionnaire = signScreeningQuestionnaire;
  }

  public List<SignScreeningQuestionnaireObservation> getSignScreeningQuestionnaire() {
     return this.signScreeningQuestionnaire ;
  }

  public void setCategoryDefiningcode(CategoryDefiningcode categoryDefiningcode) {
     this.categoryDefiningcode = categoryDefiningcode;
  }

  public CategoryDefiningcode getCategoryDefiningcode() {
     return this.categoryDefiningcode ;
  }

  public void setExtension(List<Cluster> extension) {
     this.extension = extension;
  }

  public List<Cluster> getExtension() {
     return this.extension ;
  }
}
