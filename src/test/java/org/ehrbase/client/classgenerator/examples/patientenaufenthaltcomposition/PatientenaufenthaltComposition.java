package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition;

import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.AufenthaltsdatenAdminEntry;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.PatientenaufenthaltOrgEhrbaseEhrEncodeWrappersSnakecase6ab72419FallidentifikationChoice;
import org.ehrbase.client.classgenerator.examples.shareddefinition.CategoryDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.examples.shareddefinition.SettingDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.event_summary.v0")
@Template("Patientenaufenthalt")
public class PatientenaufenthaltComposition {
  @Id
  private VersionUid versionUid;

  @Path("/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0]")
  @Choice
  private PatientenaufenthaltOrgEhrbaseEhrEncodeWrappersSnakecase6ab72419FallidentifikationChoice fallidentifikation;

  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  @Path("/context/participations")
  private List<Participation> participations;

  @Path("/language")
  private Language language;

  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  @Path("/composer")
  private PartyProxy composer;

  @Path("/content[openEHR-EHR-ADMIN_ENTRY.hospitalization.v0]")
  private AufenthaltsdatenAdminEntry aufenthaltsdaten;

  @Path("/context/setting|defining_code")
  private SettingDefiningcode settingDefiningcode;

  @Path("/territory")
  private Territory territory;

  @Path("/context/location")
  private String location;

  @Path("/category|defining_code")
  private CategoryDefiningcode categoryDefiningcode;

  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

    @Path("/name")
    private DvText name;

  public VersionUid getVersionUid() {
     return this.versionUid ;
  }

  public void setVersionUid(VersionUid versionUid) {
     this.versionUid = versionUid;
  }

  public void setFallidentifikation(
      PatientenaufenthaltOrgEhrbaseEhrEncodeWrappersSnakecase6ab72419FallidentifikationChoice fallidentifikation) {
     this.fallidentifikation = fallidentifikation;
  }

  public PatientenaufenthaltOrgEhrbaseEhrEncodeWrappersSnakecase6ab72419FallidentifikationChoice getFallidentifikation(
      ) {
     return this.fallidentifikation ;
  }

  public void setEndTimeValue(TemporalAccessor endTimeValue) {
     this.endTimeValue = endTimeValue;
  }

  public TemporalAccessor getEndTimeValue() {
     return this.endTimeValue ;
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

  public void setComposer(PartyProxy composer) {
     this.composer = composer;
  }

  public PartyProxy getComposer() {
     return this.composer ;
  }

  public void setAufenthaltsdaten(AufenthaltsdatenAdminEntry aufenthaltsdaten) {
     this.aufenthaltsdaten = aufenthaltsdaten;
  }

  public AufenthaltsdatenAdminEntry getAufenthaltsdaten() {
     return this.aufenthaltsdaten ;
  }

  public void setSettingDefiningcode(SettingDefiningcode settingDefiningcode) {
     this.settingDefiningcode = settingDefiningcode;
  }

  public SettingDefiningcode getSettingDefiningcode() {
     return this.settingDefiningcode ;
  }

  public void setTerritory(Territory territory) {
     this.territory = territory;
  }

  public Territory getTerritory() {
     return this.territory ;
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

  public void setStartTimeValue(TemporalAccessor startTimeValue) {
     this.startTimeValue = startTimeValue;
  }

  public TemporalAccessor getStartTimeValue() {
     return this.startTimeValue ;
  }

    public DvText getName() {
        return name;
    }

    public void setName(DvText name) {
        this.name = name;
    }
}
