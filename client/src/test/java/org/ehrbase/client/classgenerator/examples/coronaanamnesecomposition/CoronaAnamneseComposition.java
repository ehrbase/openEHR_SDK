package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
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
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.AllgemeineAngabenSection;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.GeschichteHistorieObservation;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.KontaktSection;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.RisikogebietSection;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.SymptomeSection;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.report.v1")
@Template("Corona_Anamnese")
public class CoronaAnamneseComposition {
  /**
   * Bericht/context/Bericht ID
   */
  @Path("/context/other_context[at0001]/items[at0002]/value|value")
  private String berichtIdValue;

  /**
   * Bericht/context/Status
   */
  @Path("/context/other_context[at0001]/items[at0005]/value|value")
  private String statusValue;

  /**
   * Bericht/context/Erweiterung
   */
  @Path("/context/other_context[at0001]/items[at0006]")
  private List<Cluster> erweiterung;

  /**
   * Bericht/context/start_time
   */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /**
   * Bericht/context/participations
   */
  @Path("/context/participations")
  private List<Participation> participations;

  /**
   * Bericht/context/end_time
   */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /**
   * Bericht/context/location
   */
  @Path("/context/location")
  private String location;

  /**
   * Bericht/context/health_care_facility
   */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /**
   * Bericht/context/setting
   */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Bericht/Geschichte/Historie
   */
  @Path("/content[openEHR-EHR-OBSERVATION.story.v1]")
  private List<GeschichteHistorieObservation> geschichteHistorie;

  /**
   * Bericht/Symptome
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']")
  private SymptomeSection symptome;

  /**
   * Bericht/Kontakt
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']")
  private KontaktSection kontakt;

  /**
   * Bericht/Risikogebiet
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Risikogebiet']")
  private RisikogebietSection risikogebiet;

  /**
   * Bericht/Allgemeine Angaben
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']")
  private AllgemeineAngabenSection allgemeineAngaben;

  /**
   * Bericht/composer
   */
  @Path("/composer")
  private PartyProxy composer;

  /**
   * Bericht/language
   */
  @Path("/language")
  private Language language;

  /**
   * Bericht/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Bericht/category
   */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /**
   * Bericht/territory
   */
  @Path("/territory")
  private Territory territory;

  @Id
  private VersionUid versionUid;

  public void setBerichtIdValue(String berichtIdValue) {
     this.berichtIdValue = berichtIdValue;
  }

  public String getBerichtIdValue() {
     return this.berichtIdValue ;
  }

  public void setStatusValue(String statusValue) {
     this.statusValue = statusValue;
  }

  public String getStatusValue() {
     return this.statusValue ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
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

  public void setGeschichteHistorie(List<GeschichteHistorieObservation> geschichteHistorie) {
     this.geschichteHistorie = geschichteHistorie;
  }

  public List<GeschichteHistorieObservation> getGeschichteHistorie() {
     return this.geschichteHistorie ;
  }

  public void setSymptome(SymptomeSection symptome) {
     this.symptome = symptome;
  }

  public SymptomeSection getSymptome() {
     return this.symptome ;
  }

  public void setKontakt(KontaktSection kontakt) {
     this.kontakt = kontakt;
  }

  public KontaktSection getKontakt() {
     return this.kontakt ;
  }

  public void setRisikogebiet(RisikogebietSection risikogebiet) {
     this.risikogebiet = risikogebiet;
  }

  public RisikogebietSection getRisikogebiet() {
     return this.risikogebiet ;
  }

  public void setAllgemeineAngaben(AllgemeineAngabenSection allgemeineAngaben) {
     this.allgemeineAngaben = allgemeineAngaben;
  }

  public AllgemeineAngabenSection getAllgemeineAngaben() {
     return this.allgemeineAngaben ;
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

  public void setCategoryDefiningCode(Category categoryDefiningCode) {
     this.categoryDefiningCode = categoryDefiningCode;
  }

  public Category getCategoryDefiningCode() {
     return this.categoryDefiningCode ;
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
