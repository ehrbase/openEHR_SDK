/*
 *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  This file is part of Project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.ehrbase.client.classgenerator;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DataValue;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;

import java.time.temporal.TemporalAccessor;
import java.util.List;

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

    @Archetype("openEHR-EHR-OBSERVATION.body_temperature.v2")
    @Entity
    public static class BodyTemperature {
        @Path("/protocol[at0020]/items")
        private Cluster protocol;

        @Path("/protocol[at0020]/items[at0021]/value|value")
        private String locationOfMeasurementValue;

        @Path("/protocol[at0020]/items[at0021]/value/defining_code")
        private LocationOfMeasurement locationOfMeasurement;

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

        public void setLocationOfMeasurement(LocationOfMeasurement locationOfMeasurement) {
            this.locationOfMeasurement = locationOfMeasurement;
        }

        public LocationOfMeasurement getLocationOfMeasurement() {
            return this.locationOfMeasurement;
        }

        public void setHistory(List<BodyTemperatureHistory> history) {
            this.history = history;
        }

        public List<BodyTemperatureHistory> getHistory() {
            return this.history;
        }

        public enum LocationOfMeasurement implements EnumValueSet {
            NASOPHARYNX("Nasopharynx", "Temperature is measured within the nasopharynx.", "local", "at0026"),

            SKIN("Skin", "Temperature is measured from exposed skin.", "local", "at0043"),

            INTRAVASCULAR("Intravascular", "Temperature is measured within the vascular system.", "local", "at0028"),

            INGUINALSKINCREASE("Inguinal skin crease", "Temperature is measured in the inguinal skin crease between the leg and abdominal wall.", "local", "at0055"),

            VAGINA("Vagina", "Temperature is measured within the vagina.", "local", "at0051"),

            FOREHEAD("Forehead", "Temperature is measured on the forehead.", "local", "at0061"),

            URINARYBLADDER("Urinary bladder", "Temperature is measured in the urinary bladder.", "local", "at0027"),

            RECTUM("Rectum", "Temperature measured within the rectum.", "local", "at0025"),

            TEMPLE("Temple", "Temperature is measured at the temple, over the superficial temporal artery.", "local", "at0060"),

            AXILLA("Axilla", "Temperature is measured from the skin of the axilla with the arm positioned down by the side.", "local", "at0024"),

            EARCANAL("Ear canal", "Temperature is measured from within the external auditory canal.", "local", "at0023"),

            MOUTH("Mouth", "Temperature is measured within the mouth.", "local", "at0022"),

            OESOPHAGUS("Oesophagus", "Temperatue is measured within the oesophagus.", "local", "at0054");

            private String value;

            private String description;

            private String terminologyId;

            private String code;

            LocationOfMeasurement(String value, String description, String terminologyId, String code) {
                this.value = value;
                this.description = description;
                this.terminologyId = terminologyId;
                this.code = code;
            }

            public String getValue() {
                return this.value;
            }

            public String getDescription() {
                return this.description;
            }

            public String getTerminologyId() {
                return this.terminologyId;
            }

            public String getCode() {
                return this.code;
            }
        }

        @Entity
        public static class BodyTemperatureHistory {
            @Path("/data[at0001]/items[at0004]/value|magnitude")
            private Double temperatureMagnitude;

            @Path("/data[at0001]/items[at0004]/value|units")
            private String temperatureUnits;

            @Path("/state[at0029]/items[at0030]/value/defining_code")
            private BodyExposure bodyExposure;

            @Path("/state[at0029]/items")
            private Cluster state;

            @Path("/state[at0029]/items[at0041]/value|value")
            private String descriptionOfThermalStressValue;

            @Path("/state[at0029]/items[at0065]/value")
            private DataValue value;

            @Path("/state[at0029]/items[at0030]/value|value")
            private String bodyExposureValue;

            @Path("/state[at0029]/items[at0065]/value/magnitude")
            private Object currentDayOfMenstrualCycle;

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

            public void setBodyExposure(BodyExposure bodyExposure) {
                this.bodyExposure = bodyExposure;
            }

            public BodyExposure getBodyExposure() {
                return this.bodyExposure;
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

            public void setValue(DataValue value) {
                this.value = value;
            }

            public DataValue getValue() {
                return this.value;
            }

            public void setBodyExposureValue(String bodyExposureValue) {
                this.bodyExposureValue = bodyExposureValue;
            }

            public String getBodyExposureValue() {
                return this.bodyExposureValue;
            }

            public void setCurrentDayOfMenstrualCycle(Object currentDayOfMenstrualCycle) {
                this.currentDayOfMenstrualCycle = currentDayOfMenstrualCycle;
            }

            public Object getCurrentDayOfMenstrualCycle() {
                return this.currentDayOfMenstrualCycle;
            }

            public enum BodyExposure implements EnumValueSet {
                BEDDING("Increased clothing/bedding", "The person is covered by an increased amount of clothing or bedding than deemed appropriate for the environmental circumstances.", "local", "at0034"),

                NAKED("Naked", "No clothing, bedding or covering.", "local", "at0031");

                private String value;

                private String description;

                private String terminologyId;

                private String code;

                BodyExposure(String value, String description, String terminologyId, String code) {
                    this.value = value;
                    this.description = description;
                    this.terminologyId = terminologyId;
                    this.code = code;
                }

                public String getValue() {
                    return this.value;
                }

                public String getDescription() {
                    return this.description;
                }

                public String getTerminologyId() {
                    return this.terminologyId;
                }

                public String getCode() {
                    return this.code;
                }
            }
        }
    }
}
