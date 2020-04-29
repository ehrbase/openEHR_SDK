package org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvText;
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
import org.ehrbase.client.classgenerator.examples.shareddefinition.CategoryDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.examples.shareddefinition.SettingDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Territory;
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition.EntlassungsdatenAdminEntry;
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition.FalltypDefiningcode;
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition.PatientenaufnahmeAdminEntry;
import org.ehrbase.client.openehrclient.VersionUid;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.fall.v0")
@Template("Stationärer Versorgungsfall")
public class StationarerVersorgungsfallComposition {
  @Id
  private VersionUid versionUid;

  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  @Path("/content[openEHR-EHR-ADMIN_ENTRY.discharge_summary.v0]")
  private EntlassungsdatenAdminEntry entlassungsdaten;

  @Path("/context/participations")
  private List<Participation> participations;

  @Path("/language")
  private Language language;

  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  @Path("/context/other_context[at0001]/items[at0005]/value|defining_code")
  private FalltypDefiningcode falltypDefiningcode;

  @Path("/context/other_context[at0001]/items[at0004]/value|value")
  private String fallklasseValue;

  @Path("/content[openEHR-EHR-ADMIN_ENTRY.admission.v0]")
  private PatientenaufnahmeAdminEntry patientenaufnahme;

  @Path("/context/other_context[at0001]/items[at0003]/value|value")
  private String fallIdValue;

  @Path("/territory")
  private Territory territory;

  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  @Path("/context/other_context[at0001]/items[at0003]/name|value")
  private String fallIdValueBaum;

  @Path("/composer")
  private PartyProxy composer;

  @Path("/context/setting|defining_code")
  private SettingDefiningcode settingDefiningcode;

  @Path("/context/location")
  private String location;

  @Path("/category|defining_code")
  private CategoryDefiningcode categoryDefiningcode;

  @Path("/context/other_context[at0001]/items[at0002]")
  private List<Cluster> erweiterung;

  @Path("/name")
  private DvText name;

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

  public void setEntlassungsdaten(EntlassungsdatenAdminEntry entlassungsdaten) {
     this.entlassungsdaten = entlassungsdaten;
  }

  public EntlassungsdatenAdminEntry getEntlassungsdaten() {
     return this.entlassungsdaten ;
  }

  public void setParticipations(List<Participation> participations) {
     this.participations = participations;
  }

  public List<Participation> getParticipations() {
     return this.participations ;
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

  public void setFalltypDefiningcode(FalltypDefiningcode falltypDefiningcode) {
     this.falltypDefiningcode = falltypDefiningcode;
  }

  public FalltypDefiningcode getFalltypDefiningcode() {
     return this.falltypDefiningcode ;
  }

  public void setFallklasseValue(String fallklasseValue) {
     this.fallklasseValue = fallklasseValue;
  }

  public String getFallklasseValue() {
     return this.fallklasseValue ;
  }

  public void setPatientenaufnahme(PatientenaufnahmeAdminEntry patientenaufnahme) {
     this.patientenaufnahme = patientenaufnahme;
  }

  public PatientenaufnahmeAdminEntry getPatientenaufnahme() {
     return this.patientenaufnahme ;
  }

  public void setFallIdValue(String fallIdValue) {
     this.fallIdValue = fallIdValue;
  }

  public String getFallIdValue() {
     return this.fallIdValue ;
  }

  public void setTerritory(Territory territory) {
     this.territory = territory;
  }

  public Territory getTerritory() {
     return this.territory ;
  }

  public void setStartTimeValue(TemporalAccessor startTimeValue) {
     this.startTimeValue = startTimeValue;
  }

  public TemporalAccessor getStartTimeValue() {
     return this.startTimeValue ;
  }

  public void setFallIdValueBaum(String fallIdValueBaum) {
     this.fallIdValueBaum = fallIdValueBaum;
  }

  public String getFallIdValueBaum() {
     return this.fallIdValueBaum ;
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

  public void setLocation(String location) {
     this.location = location;
  }

  public String getLocation() {
     return this.location ;
  }

  public void setCategoryDefiningcode(CategoryDefiningcode categoryDefiningcode) {
     this.categoryDefiningcode = categoryDefiningcode;
  }

  public CategoryDefiningcode getCategoryDefiningcode() {
     return this.categoryDefiningcode ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

    public DvText getName() {
        return name;
    }

    public void setName(DvText name) {
        this.name = name;
    }
}
