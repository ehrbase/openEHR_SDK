package org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0;

import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.ehrbase.client.annotations.*;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0.definition.BloodPressureTrainingSample;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0.definition.DeviceDetailsTrainingSample;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.examples.shareddefinition.SettingDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.sample_encounter.v1")
@Template("ehrbase_blood_pressure_simple.de.v0")
public class EhrbaseBloodPressureSimpleDeV0 {
    @Id
    private VersionUid versionUid;

    @Path("/context/end_time|value")
    private TemporalAccessor endTimeValue;

    @Path("/language")
    private Language language;

    @Path("/context/health_care_facility")
    private PartyIdentified healthCareFacility;

    @Path("/composer|external_ref")
    private PartyRef composerExternalref;

    @Path("/context/setting|defining_code")
    private SettingDefiningcode settingDefiningcode;

    @Path("/territory")
    private Territory territory;

    @Path("/content[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]")
    private List<BloodPressureTrainingSample> bloodPressureTrainingSample;

    @Path("/context/location")
    private String location;

    @Path("/context/other_context[at0001]/items[at0006]/items[openEHR-EHR-CLUSTER.sample_device.v1]")
    private List<DeviceDetailsTrainingSample> deviceDetailsTrainingSample;

    @Path("/context/start_time|value")
    private TemporalAccessor startTimeValue;

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

    public void setComposerExternalref(PartyRef composerExternalref) {
        this.composerExternalref = composerExternalref;
    }

    public PartyRef getComposerExternalref() {
        return this.composerExternalref;
    }

    public void setSettingDefiningcode(SettingDefiningcode settingDefiningcode) {
        this.settingDefiningcode = settingDefiningcode;
    }

    public SettingDefiningcode getSettingDefiningcode() {
        return this.settingDefiningcode;
    }

    public void setTerritory(Territory territory) {
        this.territory = territory;
    }

    public Territory getTerritory() {
        return this.territory;
    }

    public void setBloodPressureTrainingSample(
            List<BloodPressureTrainingSample> bloodPressureTrainingSample) {
        this.bloodPressureTrainingSample = bloodPressureTrainingSample;
    }

    public List<BloodPressureTrainingSample> getBloodPressureTrainingSample() {
        return this.bloodPressureTrainingSample;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    public void setDeviceDetailsTrainingSample(
            List<DeviceDetailsTrainingSample> deviceDetailsTrainingSample) {
        this.deviceDetailsTrainingSample = deviceDetailsTrainingSample;
    }

    public List<DeviceDetailsTrainingSample> getDeviceDetailsTrainingSample() {
        return this.deviceDetailsTrainingSample;
    }

    public void setStartTimeValue(TemporalAccessor startTimeValue) {
        this.startTimeValue = startTimeValue;
    }

    public TemporalAccessor getStartTimeValue() {
        return this.startTimeValue;
    }
}
