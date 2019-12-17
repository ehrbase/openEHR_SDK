package org.ehrbase.client.classgenerator;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.ehrbase.client.annotations.*;

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

    @Path("/territory")
    private CodePhrase territory;

    @Path("/content[openEHR-EHR-OBSERVATION.body_temperature.v2]")
    private List<BodyTemperature> bodyTemperature;

    @Path("/context/location")
    private String location;

    @Path("/context/start_time|value")
    private TemporalAccessor startTimeValue;

    @Path("/context/other_context[at0001]/items[at0002]")
    private List<Cluster> extension;

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

    public void setExtension(List<Cluster> extension) {
        this.extension = extension;
    }

    public List<Cluster> getExtension() {
        return this.extension;
    }

    @Entity
    @Archetype("openEHR-EHR-OBSERVATION.body_temperature.v2")
    public static class BodyTemperature {
        @Path("/protocol[at0020]/items[at0062]")
        private List<Cluster> extension;

        @Path("/language")
        private CodePhrase language;

        @Path("/protocol[at0020]/items[at0064]")
        private List<Cluster> structuredMeasurementLocation;

        @Path("/data[at0002]/origin|value")
        private TemporalAccessor originValue;

        @Path("/protocol[at0020]/items[at0059]")
        private Cluster device;

        @Path("/subject|external_ref")
        private PartyRef subjectExternalref;

        @Path("/protocol[at0020]/items[at0021]/value")
        @Choice
        private ProtocolLocationOfMeasurementChoice locationOfMeasurement;

        @Path("/data[at0002]/events")
        private List<BodyTemperatureHistory> history;

        public void setExtension(List<Cluster> extension) {
            this.extension = extension;
        }

        public List<Cluster> getExtension() {
            return this.extension;
        }

        public void setLanguage(CodePhrase language) {
            this.language = language;
        }

        public CodePhrase getLanguage() {
            return this.language;
        }

        public void setStructuredMeasurementLocation(List<Cluster> structuredMeasurementLocation) {
            this.structuredMeasurementLocation = structuredMeasurementLocation;
        }

        public List<Cluster> getStructuredMeasurementLocation() {
            return this.structuredMeasurementLocation;
        }

        public void setOriginValue(TemporalAccessor originValue) {
            this.originValue = originValue;
        }

        public TemporalAccessor getOriginValue() {
            return this.originValue;
        }

        public void setDevice(Cluster device) {
            this.device = device;
        }

        public Cluster getDevice() {
            return this.device;
        }

        public void setSubjectExternalref(PartyRef subjectExternalref) {
            this.subjectExternalref = subjectExternalref;
        }

        public PartyRef getSubjectExternalref() {
            return this.subjectExternalref;
        }

        public void setLocationOfMeasurement(
                ProtocolLocationOfMeasurementChoice locationOfMeasurement) {
            this.locationOfMeasurement = locationOfMeasurement;
        }

        public ProtocolLocationOfMeasurementChoice getLocationOfMeasurement() {
            return this.locationOfMeasurement;
        }

        public void setHistory(List<BodyTemperatureHistory> history) {
            this.history = history;
        }

        public List<BodyTemperatureHistory> getHistory() {
            return this.history;
        }

        public interface ProtocolLocationOfMeasurementChoice {
        }

        @Entity
        @OptionFor("DvCodedText")
        public static class ProtocolLocationOfMeasurementDvcodedtext implements ProtocolLocationOfMeasurementChoice {
            @Path("|defining_code")
            private LocationOfMeasurementDefiningcode locationOfMeasurementDefiningcode;

            public void setLocationOfMeasurementDefiningcode(
                    LocationOfMeasurementDefiningcode locationOfMeasurementDefiningcode) {
                this.locationOfMeasurementDefiningcode = locationOfMeasurementDefiningcode;
            }

            public LocationOfMeasurementDefiningcode getLocationOfMeasurementDefiningcode() {
                return this.locationOfMeasurementDefiningcode;
            }

            public enum LocationOfMeasurementDefiningcode implements EnumValueSet {
                INTRAVASCULAR("Intravascular", "Temperature is measured within the vascular system.", "local", "at0028"),

                MOUTH("Mouth", "Temperature is measured within the mouth.", "local", "at0022"),

                FOREHEAD("Forehead", "Temperature is measured on the forehead.", "local", "at0061"),

                INGUINALSKINCREASE("Inguinal skin crease", "Temperature is measured in the inguinal skin crease between the leg and abdominal wall.", "local", "at0055"),

                NASOPHARYNX("Nasopharynx", "Temperature is measured within the nasopharynx.", "local", "at0026"),

                URINARYBLADDER("Urinary bladder", "Temperature is measured in the urinary bladder.", "local", "at0027"),

                RECTUM("Rectum", "Temperature measured within the rectum.", "local", "at0025"),

                OESOPHAGUS("Oesophagus", "Temperatue is measured within the oesophagus.", "local", "at0054"),

                AXILLA("Axilla", "Temperature is measured from the skin of the axilla with the arm positioned down by the side.", "local", "at0024"),

                EARCANAL("Ear canal", "Temperature is measured from within the external auditory canal.", "local", "at0023"),

                TEMPLE("Temple", "Temperature is measured at the temple, over the superficial temporal artery.", "local", "at0060"),

                VAGINA("Vagina", "Temperature is measured within the vagina.", "local", "at0051"),

                SKIN("Skin", "Temperature is measured from exposed skin.", "local", "at0043");

                private String value;

                private String description;

                private String terminologyId;

                private String code;

                LocationOfMeasurementDefiningcode(String value, String description, String terminologyId,
                                                  String code) {
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

        @Entity
        @OptionFor("DvText")
        public static class ProtocolLocationOfMeasurementDvtext implements ProtocolLocationOfMeasurementChoice {
            @Path("|value")
            private String locationOfMeasurementValue;

            public void setLocationOfMeasurementValue(String locationOfMeasurementValue) {
                this.locationOfMeasurementValue = locationOfMeasurementValue;
            }

            public String getLocationOfMeasurementValue() {
                return this.locationOfMeasurementValue;
            }
        }

        @Entity
        public static class BodyTemperatureHistory {
            @Path("/time|value")
            private TemporalAccessor timeValue;

            @Path("/data[at0001]/items[at0004]/value|magnitude")
            private Double temperatureMagnitude;

            @Path("/data[at0001]/items[at0004]/value|units")
            private String temperatureUnits;

            @Path("/state[at0029]/items[at0041]/value|value")
            private String descriptionOfThermalStressValue;

            @Path("/state[at0029]/items[at0057]")
            private Cluster exertion;

            @Path("/state[at0029]/items[at0065]/value|magnitude")
            private Long currentDayOfMenstrualCycleMagnitude;

            @Path("/state[at0029]/items[at0056]")
            private List<Cluster> environmentalConditions;

            @Path("/state[at0029]/items[at0030]/value")
            @Choice
            private StateBodyExposureChoice bodyExposure;

            public void setTimeValue(TemporalAccessor timeValue) {
                this.timeValue = timeValue;
            }

            public TemporalAccessor getTimeValue() {
                return this.timeValue;
            }

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

            public void setDescriptionOfThermalStressValue(String descriptionOfThermalStressValue) {
                this.descriptionOfThermalStressValue = descriptionOfThermalStressValue;
            }

            public String getDescriptionOfThermalStressValue() {
                return this.descriptionOfThermalStressValue;
            }

            public void setExertion(Cluster exertion) {
                this.exertion = exertion;
            }

            public Cluster getExertion() {
                return this.exertion;
            }

            public void setCurrentDayOfMenstrualCycleMagnitude(Long currentDayOfMenstrualCycleMagnitude) {
                this.currentDayOfMenstrualCycleMagnitude = currentDayOfMenstrualCycleMagnitude;
            }

            public Long getCurrentDayOfMenstrualCycleMagnitude() {
                return this.currentDayOfMenstrualCycleMagnitude;
            }

            public void setEnvironmentalConditions(List<Cluster> environmentalConditions) {
                this.environmentalConditions = environmentalConditions;
            }

            public List<Cluster> getEnvironmentalConditions() {
                return this.environmentalConditions;
            }

            public void setBodyExposure(StateBodyExposureChoice bodyExposure) {
                this.bodyExposure = bodyExposure;
            }

            public StateBodyExposureChoice getBodyExposure() {
                return this.bodyExposure;
            }

            public interface StateBodyExposureChoice {
            }

            @Entity
            @OptionFor("DvCodedText")
            public static class StateBodyExposureDvcodedtext implements StateBodyExposureChoice {
                @Path("|defining_code")
                private BodyExposureDefiningcode bodyExposureDefiningcode;

                public void setBodyExposureDefiningcode(BodyExposureDefiningcode bodyExposureDefiningcode) {
                    this.bodyExposureDefiningcode = bodyExposureDefiningcode;
                }

                public BodyExposureDefiningcode getBodyExposureDefiningcode() {
                    return this.bodyExposureDefiningcode;
                }

                public enum BodyExposureDefiningcode implements EnumValueSet {
                    BEDDING("Reduced clothing/bedding", "The person is covered by a lesser amount of clothing or bedding than deemed appropriate for the environmental circumstances.", "local", "at0032"),

                    NAKED("Naked", "No clothing, bedding or covering.", "local", "at0031");

                    private String value;

                    private String description;

                    private String terminologyId;

                    private String code;

                    BodyExposureDefiningcode(String value, String description, String terminologyId,
                                             String code) {
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

            @Entity
            @OptionFor("DvText")
            public static class StateBodyExposureDvtext implements StateBodyExposureChoice {
                @Path("|value")
                private String bodyExposureValue;

                public void setBodyExposureValue(String bodyExposureValue) {
                    this.bodyExposureValue = bodyExposureValue;
                }

                public String getBodyExposureValue() {
                    return this.bodyExposureValue;
                }
            }
        }
    }
}
