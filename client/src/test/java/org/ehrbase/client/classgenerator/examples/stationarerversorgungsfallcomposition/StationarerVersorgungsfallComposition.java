package org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
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
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition.AufnahmedatenAdminEntry;
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition.EntlassungsdatenAdminEntry;
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition.FalltypDefiningCode;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.fall.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.960033600+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@Template("Stationärer Versorgungsfall")
public class StationarerVersorgungsfallComposition implements CompositionEntity {
  /**
   * Path: Stationärer Versorgungsfall/context/Falltyp Description: Charaktierisierung des Falls als
   * Versorgungs-, Abteilungs- oder Abrechnungsfall.
   */
  @Path("/context/other_context[at0001]/items[at0005]/value|defining_code")
  private FalltypDefiningCode falltypDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/context/Fallklasse Description: Nähere Beschreibung des Falls
   * als Fallklasse, z.B. ambulanter Besuch, stationärer, prä- oder nachstationärer Aufenthalt.
   */
  @Path("/context/other_context[at0001]/items[at0004]/value|value")
  private String fallklasseValue;

  /**
   * Path: Stationärer Versorgungsfall/context/Fall-Kennung Description: Eindeutige Identifikation
   * des Falls, z.B. Fallnummer.
   */
  @Path("/context/other_context[at0001]/items[at0003 and name/value='Fall-Kennung']/value|value")
  private String fallKennungValue;

  /**
   * Path: Stationärer Versorgungsfall/context/Erweiterung Description: Ergänzende Angaben zum Fall
   */
  @Path("/context/other_context[at0001]/items[at0002]")
  private List<Cluster> erweiterung;

  /** Path: Stationärer Versorgungsfall/context/start_time */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /** Path: Stationärer Versorgungsfall/context/participations */
  @Path("/context/participations")
  private List<Participation> participations;

  /** Path: Stationärer Versorgungsfall/context/end_time */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /** Path: Stationärer Versorgungsfall/context/location */
  @Path("/context/location")
  private String location;

  /** Path: Stationärer Versorgungsfall/context/health_care_facility */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /** Path: Stationärer Versorgungsfall/context/setting */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten Description: Wird nur für aufgenommene
   * Patienten verwendet. Es signalisiert den Beginn des Aufenthalts eines Patienten in einer
   * Gesundheitseinrichtung.
   */
  @Path("/content[openEHR-EHR-ADMIN_ENTRY.admission.v0 and name/value='Aufnahmedaten']")
  private AufnahmedatenAdminEntry aufnahmedaten;

  /**
   * Path: Stationärer Versorgungsfall/Entlassungsdaten Description: Wird nur für entlassene
   * Patienten verwendet.
   */
  @Path("/content[openEHR-EHR-ADMIN_ENTRY.discharge_summary.v0]")
  private EntlassungsdatenAdminEntry entlassungsdaten;

  /** Path: Stationärer Versorgungsfall/composer */
  @Path("/composer")
  private PartyProxy composer;

  /** Path: Stationärer Versorgungsfall/language */
  @Path("/language")
  private Language language;

  /** Path: Stationärer Versorgungsfall/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /** Path: Stationärer Versorgungsfall/category */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /** Path: Stationärer Versorgungsfall/territory */
  @Path("/territory")
  private Territory territory;

  @Id private VersionUid versionUid;

  public void setFalltypDefiningCode(FalltypDefiningCode falltypDefiningCode) {
    this.falltypDefiningCode = falltypDefiningCode;
  }

  public FalltypDefiningCode getFalltypDefiningCode() {
    return this.falltypDefiningCode;
  }

  public void setFallklasseValue(String fallklasseValue) {
    this.fallklasseValue = fallklasseValue;
  }

  public String getFallklasseValue() {
    return this.fallklasseValue;
  }

  public void setFallKennungValue(String fallKennungValue) {
    this.fallKennungValue = fallKennungValue;
  }

  public String getFallKennungValue() {
    return this.fallKennungValue;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
    this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
    return this.erweiterung;
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

  public void setAufnahmedaten(AufnahmedatenAdminEntry aufnahmedaten) {
    this.aufnahmedaten = aufnahmedaten;
  }

  public AufnahmedatenAdminEntry getAufnahmedaten() {
    return this.aufnahmedaten;
  }

  public void setEntlassungsdaten(EntlassungsdatenAdminEntry entlassungsdaten) {
    this.entlassungsdaten = entlassungsdaten;
  }

  public EntlassungsdatenAdminEntry getEntlassungsdaten() {
    return this.entlassungsdaten;
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
