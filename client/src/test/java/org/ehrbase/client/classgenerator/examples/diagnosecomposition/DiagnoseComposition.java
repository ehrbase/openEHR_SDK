package org.ehrbase.client.classgenerator.examples.diagnosecomposition;

import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.*;
import org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition.DiagnoseEvaluation;
import org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition.FallidentifikationCluster;
import org.ehrbase.client.classgenerator.examples.shareddefinition.CategoryDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.examples.shareddefinition.SettingDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.report.v1")
@Template("Diagnose")
public class DiagnoseComposition {
    @Id
    private VersionUid versionUid;

    @Path("/context/end_time|value")
    private TemporalAccessor endTimeValue;

    @Path("/context/participations")
    private List<Participation> participations;

    @Path("/content[openEHR-EHR-EVALUATION.problem_diagnosis.v1]")
    private DiagnoseEvaluation diagnose;

    @Path("/language")
    private Language language;

    @Path("/context/health_care_facility")
    private PartyIdentified healthCareFacility;

    @Path("/context/other_context[at0001]/items[at0002]/value|value")
    private String berichtIdValue;

    @Path("/territory")
    private Territory territory;

    @Path("/context/start_time|value")
    private TemporalAccessor startTimeValue;

    @Path("/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0]")
    private FallidentifikationCluster fallidentifikation;

    @Path("/composer")
    private PartyProxy composer;

    @Path("/context/setting|defining_code")
    private SettingDefiningcode settingDefiningcode;

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

    public void setDiagnose(DiagnoseEvaluation diagnose) {
        this.diagnose = diagnose;
    }

    public DiagnoseEvaluation getDiagnose() {
        return this.diagnose;
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

    public void setFallidentifikation(FallidentifikationCluster fallidentifikation) {
        this.fallidentifikation = fallidentifikation;
    }

    public FallidentifikationCluster getFallidentifikation() {
        return this.fallidentifikation;
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
