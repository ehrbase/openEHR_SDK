package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
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
import org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition.AllgemeineAngabenSection;
import org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition.GeschichteHistorieObservation;
import org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition.KontaktSection;
import org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition.RisikogebietSection;
import org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition.SymptomeSection;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.CategoryDefiningcode;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.SettingDefiningcode;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.report.v1")
@Template("Corona_Anamnese")
public class CoronaAnamneseComposition {
  @Id private VersionUid versionUid;

  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Risikogebiet']")
  private RisikogebietSection risikogebiet;

  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  @Path("/context/participations")
  private List<Participation> participations;

  @Path("/language")
  private Language language;

  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  @Path("/context/other_context[at0001]/items[at0005]/value|value")
  private String statusValue;

  @Path("/content[openEHR-EHR-OBSERVATION.story.v1]")
  private List<GeschichteHistorieObservation> geschichteHistorie;

  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']")
  private AllgemeineAngabenSection allgemeineAngaben;

  @Path("/context/other_context[at0001]/items[at0002]/value|value")
  private String berichtIdValue;

  @Path("/territory")
  private Territory territory;

  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']")
  private SymptomeSection symptome;

  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']")
  private KontaktSection kontakt;

  @Path("/composer")
  private PartyProxy composer;

  @Path("/context/setting|defining_code")
  private SettingDefiningcode settingDefiningcode;

  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  @Path("/context/other_context[at0001]/items[at0006]")
  private List<Cluster> erweiterung;

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

  public void setRisikogebiet(RisikogebietSection risikogebiet) {
    this.risikogebiet = risikogebiet;
  }

  public RisikogebietSection getRisikogebiet() {
    return this.risikogebiet;
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

  public void setStatusValue(String statusValue) {
    this.statusValue = statusValue;
  }

  public String getStatusValue() {
    return this.statusValue;
  }

  public void setGeschichteHistorie(List<GeschichteHistorieObservation> geschichteHistorie) {
    this.geschichteHistorie = geschichteHistorie;
  }

  public List<GeschichteHistorieObservation> getGeschichteHistorie() {
    return this.geschichteHistorie;
  }

  public void setAllgemeineAngaben(AllgemeineAngabenSection allgemeineAngaben) {
    this.allgemeineAngaben = allgemeineAngaben;
  }

  public AllgemeineAngabenSection getAllgemeineAngaben() {
    return this.allgemeineAngaben;
  }

  public void setBerichtIdValue(String berichtIdValue) {
    this.berichtIdValue = berichtIdValue;
  }

  public String getBerichtIdValue() {
    return this.berichtIdValue;
  }

  public void setTerritory(Territory territory) {
    this.territory = territory;
  }

  public Territory getTerritory() {
    return this.territory;
  }

  public void setStartTimeValue(TemporalAccessor startTimeValue) {
    this.startTimeValue = startTimeValue;
  }

  public TemporalAccessor getStartTimeValue() {
    return this.startTimeValue;
  }

  public void setSymptome(SymptomeSection symptome) {
    this.symptome = symptome;
  }

  public SymptomeSection getSymptome() {
    return this.symptome;
  }

  public void setKontakt(KontaktSection kontakt) {
    this.kontakt = kontakt;
  }

  public KontaktSection getKontakt() {
    return this.kontakt;
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

  public void setErweiterung(List<Cluster> erweiterung) {
    this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
    return this.erweiterung;
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
