package org.ehrbase.client.classgenerator.examples.laborbefundcomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.classgenerator.examples.laborbefundcomposition.definition.FallidentifikationCluster;
import org.ehrbase.client.classgenerator.examples.laborbefundcomposition.definition.LaborergebnisObservation;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.report-result.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-04-19T11:36:01.806339+07:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@Template("Laborbefund")
public class LaborbefundComposition implements CompositionEntity {
  /**
   * Path: Laborbefund/category
   */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /**
   * Path: Laborbefund/context/Identifikator des Laborbefunds
   * Description: Eindeutige Identifikation des Berichts.
   */
  @Path("/context/other_context[at0001]/items[at0002 and name/value='Identifikator des Laborbefunds']/value|value")
  private String identifikatorDesLaborbefundsValue;

  /**
   * Path: Laborbefund/context/Baum/Identifikator des Laborbefunds/null_flavour
   */
  @Path("/context/other_context[at0001]/items[at0002 and name/value='Identifikator des Laborbefunds']/null_flavour|defining_code")
  private NullFlavour identifikatorDesLaborbefundsNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/context/Status
   * Description: Status des gesamten Ergebnisberichts. Hinweis: Dies ist nicht der Status einzelner Berichtskomponenten.
   */
  @Path("/context/other_context[at0001]/items[at0005]/value|value")
  private String statusValue;

  /**
   * Path: Laborbefund/context/Baum/Status/null_flavour
   */
  @Path("/context/other_context[at0001]/items[at0005]/null_flavour|defining_code")
  private NullFlavour statusNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/context/Fallidentifikation
   * Description: Zur Erfassung von Details zur Identifikation eines Falls im Gesundheitswesen.
   */
  @Path("/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0]")
  private FallidentifikationCluster fallidentifikation;

  /**
   * Path: Laborbefund/context/start_time
   */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /**
   * Path: Laborbefund/context/participations
   */
  @Path("/context/participations")
  private List<Participation> participations;

  /**
   * Path: Laborbefund/context/end_time
   */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /**
   * Path: Laborbefund/context/location
   */
  @Path("/context/location")
  private String location;

  /**
   * Path: Laborbefund/context/health_care_facility
   */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /**
   * Path: Laborbefund/context/setting
   */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis
   * Description: Das Ergebnis - einschließlich der Befunde und der Interpretation des Labors - einer Untersuchung, die an Proben durchgeführt wurde, die von einer Einzelperson stammen oder mit dieser Person zusammenhängen.
   */
  @Path("/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1]")
  private List<LaborergebnisObservation> laborergebnis;

  /**
   * Path: Laborbefund/composer
   */
  @Path("/composer")
  private PartyProxy composer;

  /**
   * Path: Laborbefund/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Laborbefund/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Laborbefund/territory
   */
  @Path("/territory")
  private Territory territory;

  @Id
  private VersionUid versionUid;

  public void setCategoryDefiningCode(Category categoryDefiningCode) {
     this.categoryDefiningCode = categoryDefiningCode;
  }

  public Category getCategoryDefiningCode() {
     return this.categoryDefiningCode ;
  }

  public void setIdentifikatorDesLaborbefundsValue(String identifikatorDesLaborbefundsValue) {
     this.identifikatorDesLaborbefundsValue = identifikatorDesLaborbefundsValue;
  }

  public String getIdentifikatorDesLaborbefundsValue() {
     return this.identifikatorDesLaborbefundsValue ;
  }

  public void setIdentifikatorDesLaborbefundsNullFlavourDefiningCode(
      NullFlavour identifikatorDesLaborbefundsNullFlavourDefiningCode) {
     this.identifikatorDesLaborbefundsNullFlavourDefiningCode = identifikatorDesLaborbefundsNullFlavourDefiningCode;
  }

  public NullFlavour getIdentifikatorDesLaborbefundsNullFlavourDefiningCode() {
     return this.identifikatorDesLaborbefundsNullFlavourDefiningCode ;
  }

  public void setStatusValue(String statusValue) {
     this.statusValue = statusValue;
  }

  public String getStatusValue() {
     return this.statusValue ;
  }

  public void setStatusNullFlavourDefiningCode(NullFlavour statusNullFlavourDefiningCode) {
     this.statusNullFlavourDefiningCode = statusNullFlavourDefiningCode;
  }

  public NullFlavour getStatusNullFlavourDefiningCode() {
     return this.statusNullFlavourDefiningCode ;
  }

  public void setFallidentifikation(FallidentifikationCluster fallidentifikation) {
     this.fallidentifikation = fallidentifikation;
  }

  public FallidentifikationCluster getFallidentifikation() {
     return this.fallidentifikation ;
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

  public void setSettingDefiningCode(Setting settingDefiningCode) {
     this.settingDefiningCode = settingDefiningCode;
  }

  public Setting getSettingDefiningCode() {
     return this.settingDefiningCode ;
  }

  public void setLaborergebnis(List<LaborergebnisObservation> laborergebnis) {
     this.laborergebnis = laborergebnis;
  }

  public List<LaborergebnisObservation> getLaborergebnis() {
     return this.laborergebnis ;
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
