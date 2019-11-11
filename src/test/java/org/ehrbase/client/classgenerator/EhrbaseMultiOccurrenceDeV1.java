package org.ehrbase.client.classgenerator;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.encounter.v1")
@Template("ehrbase_multi_occurrence.de.v1")
public class EhrbaseMultiOccurrenceDeV1 {
    @Path("/context/end_time|value")
    private TemporalAccessor endTimeValue;

    @Path("/language")
    private CodePhrase language;

    @Path("/context/health_care_facility")
    private PartyIdentified healthCareFacility;

    @Path("/composer|external_ref")
    private PartyRef composerExternalref;

    @Path("/context/setting|defining_code")
    private CodePhrase settingDefiningcode;

    @Path("/context/setting|value")
    private String settingValue;

    @Path("/territory")
    private CodePhrase territory;

    @Path("/content[openEHR-EHR-OBSERVATION.body_temperature.v2]")
    private List<BodyTemperature> bodyTemperature;

    @Path("/context/other_context[at0001]/items")
    private Cluster tree;

    @Path("/context/location")
    private String location;

    @Path("/context/start_time|value")
    private TemporalAccessor startTimeValue;

    public void setEndTimeValue(TemporalAccessor endTimeValue) {
        this.endTimeValue = endTimeValue;
    }

    public TemporalAccessor getEndTimeValue() {
        return this.endTimeValue;
    }

    public void setLanguage(CodePhrase language) {
        this.language = language;
    }

    public CodePhrase getLanguage() {
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

    public void setSettingDefiningcode(CodePhrase settingDefiningcode) {
        this.settingDefiningcode = settingDefiningcode;
    }

    public CodePhrase getSettingDefiningcode() {
        return this.settingDefiningcode;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    public String getSettingValue() {
        return this.settingValue;
    }

    public void setTerritory(CodePhrase territory) {
        this.territory = territory;
    }

    public CodePhrase getTerritory() {
        return this.territory;
    }

    public void setBodyTemperature(List<BodyTemperature> bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public List<BodyTemperature> getBodyTemperature() {
        return this.bodyTemperature;
    }

    public void setTree(Cluster tree) {
        this.tree = tree;
    }

    public Cluster getTree() {
        return this.tree;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    public void setStartTimeValue(TemporalAccessor startTimeValue) {
        this.startTimeValue = startTimeValue;
    }

    public TemporalAccessor getStartTimeValue() {
        return this.startTimeValue;
    }

    @Entity
    @Archetype("openEHR-EHR-OBSERVATION.body_temperature.v2")
    public static class BodyTemperature {
        @Path("/protocol[at0020]/items")
        private Cluster protocol;

        @Path("/protocol[at0020]/items[at0021]/value|value")
        private String locationOfMeasurementValue;

        @Path("/data[at0002]/events")
        private List<BodyTemperatureHistory> history;

        public void setProtocol(Cluster protocol) {
            this.protocol = protocol;
        }

        public Cluster getProtocol() {
            return this.protocol;
        }

        public void setLocationOfMeasurementValue(String locationOfMeasurementValue) {
            this.locationOfMeasurementValue = locationOfMeasurementValue;
        }

        public String getLocationOfMeasurementValue() {
            return this.locationOfMeasurementValue;
        }

        public void setHistory(List<BodyTemperatureHistory> history) {
            this.history = history;
        }

        public List<BodyTemperatureHistory> getHistory() {
            return this.history;
        }

        @Entity
        public static class BodyTemperatureHistory {
            @Path("/data[at0001]/items[at0004]/value|magnitude")
            private Double temperatureMagnitude;

            @Path("/data[at0001]/items[at0004]/value|units")
            private String temperatureUnits;

            @Path("/state[at0029]/items[at0030]/value|value")
            private String bodyExposureValue;

            @Path("/state[at0029]/items")
            private Cluster state;

            @Path("/state[at0029]/items[at0041]/value|value")
            private String descriptionOfThermalStressValue;

            @Path("/state[at0029]/items[at0065]/value|magnitude")
            private Long currentDayOfMenstrualCycleMagnitude;

            public void setTemperatureMagnitude(Double temperatureMagnitude) {
                this.temperatureMagnitude = temperatureMagnitude;
            }

            public Double getTemperatureMagnitude() {
                return this.temperatureMagnitude;
            }

            public void setTemperatureUnits(String temperatureUnits) {
                this.temperatureUnits = temperatureUnits;
            }

            public String getTemperatureUnits() {
                return this.temperatureUnits;
            }

            public void setBodyExposureValue(String bodyExposureValue) {
                this.bodyExposureValue = bodyExposureValue;
            }

            public String getBodyExposureValue() {
                return this.bodyExposureValue;
            }

            public void setState(Cluster state) {
                this.state = state;
            }

            public Cluster getState() {
                return this.state;
            }

            public void setDescriptionOfThermalStressValue(String descriptionOfThermalStressValue) {
                this.descriptionOfThermalStressValue = descriptionOfThermalStressValue;
            }

            public String getDescriptionOfThermalStressValue() {
                return this.descriptionOfThermalStressValue;
            }

            public void setCurrentDayOfMenstrualCycleMagnitude(Long currentDayOfMenstrualCycleMagnitude) {
                this.currentDayOfMenstrualCycleMagnitude = currentDayOfMenstrualCycleMagnitude;
            }

            public Long getCurrentDayOfMenstrualCycleMagnitude() {
                return this.currentDayOfMenstrualCycleMagnitude;
            }
        }
    }
}
