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

@Archetype("openEHR-EHR-COMPOSITION.sample_encounter.v1")
@Template("ehrbase_blood_pressure_simple.de.v0")
@Entity
public class EhrbaseBloodPressureSimpleDeV0 {
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

    @Path("/content[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]")
    private List<BloodPressureTrainingSample> bloodPressureTrainingSample;

    @Path("/context/location")
    private String location;

    @Path("/context/other_context[at0001]/items[at0006]/items[openEHR-EHR-CLUSTER.sample_device.v1]")
    private DeviceDetailsTrainingSample deviceDetailsTrainingSample;

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
            DeviceDetailsTrainingSample deviceDetailsTrainingSample) {
        this.deviceDetailsTrainingSample = deviceDetailsTrainingSample;
    }

    public DeviceDetailsTrainingSample getDeviceDetailsTrainingSample() {
        return this.deviceDetailsTrainingSample;
    }

    public void setStartTimeValue(TemporalAccessor startTimeValue) {
        this.startTimeValue = startTimeValue;
    }

    public TemporalAccessor getStartTimeValue() {
        return this.startTimeValue;
    }

    @Archetype("openEHR-EHR-OBSERVATION.sample_blood_pressure.v1")
    @Entity
    public static class BloodPressureTrainingSample {
        @Path("/data[at0001]/events[at0002]/state[at0007]/items")
        private Cluster stateStructure;

        @Path("/protocol[at0011]/items")
        private Cluster listStructure;

        @Path("/protocol[at0011]/items[at0013]/value/defining_code")
        private CuffSize cuffSize;

        @Path("/protocol[at0011]/items[at0014]/value/defining_code")
        private LocationOfMeasurement locationOfMeasurement;

        @Path("/protocol[at0011]/items[at1010]/value/defining_code")
        private KorotkoffSounds korotkoffSounds;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0033]/value|value")
        private String commentValue;

        @Path("/protocol[at0011]/items[at0013]/value")
        private DataValue value;

        @Path("/protocol[at0011]/items[at1010]/value")
        private DataValue value2;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|magnitude")
        private Double systolicMagnitude;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|units")
        private String systolicUnits;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|magnitude")
        private Double diastolicMagnitude;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|units")
        private String diastolicUnits;

        @Path("/data[at0001]/events[at0002]/state[at0007]/items[at0008]/value")
        private DataValue value3;

        @Path("/data[at0001]/events[at0002]/state[at0007]/items[at1005]/value|magnitude")
        private Double tiltMagnitude;

        @Path("/data[at0001]/events[at0002]/state[at0007]/items[at1005]/value|units")
        private String tiltUnits;

        @Path("/data[at0001]/events[at0002]/state[at0007]/items[at0008]/value/defining_code")
        private Position position;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1006]/value|magnitude")
        private Double meanArterialPressureMagnitude;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1006]/value|units")
        private String meanArterialPressureUnits;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1007]/value|magnitude")
        private Double pulsePressureMagnitude;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1007]/value|units")
        private String pulsePressureUnits;

        @Path("/protocol[at0011]/items[at0014]/value")
        private DataValue value4;

        public void setStateStructure(Cluster stateStructure) {
            this.stateStructure = stateStructure;
        }

        public Cluster getStateStructure() {
            return this.stateStructure;
        }

        public void setListStructure(Cluster listStructure) {
            this.listStructure = listStructure;
        }

        public Cluster getListStructure() {
            return this.listStructure;
        }

        public void setCuffSize(CuffSize cuffSize) {
            this.cuffSize = cuffSize;
        }

        public CuffSize getCuffSize() {
            return this.cuffSize;
        }

        public void setLocationOfMeasurement(LocationOfMeasurement locationOfMeasurement) {
            this.locationOfMeasurement = locationOfMeasurement;
        }

        public LocationOfMeasurement getLocationOfMeasurement() {
            return this.locationOfMeasurement;
        }

        public void setKorotkoffSounds(KorotkoffSounds korotkoffSounds) {
            this.korotkoffSounds = korotkoffSounds;
        }

        public KorotkoffSounds getKorotkoffSounds() {
            return this.korotkoffSounds;
        }

        public void setCommentValue(String commentValue) {
            this.commentValue = commentValue;
        }

        public String getCommentValue() {
            return this.commentValue;
        }

        public void setValue(DataValue value) {
            this.value = value;
        }

        public DataValue getValue() {
            return this.value;
        }

        public void setValue2(DataValue value2) {
            this.value2 = value2;
        }

        public DataValue getValue2() {
            return this.value2;
        }

        public void setSystolicMagnitude(Double systolicMagnitude) {
            this.systolicMagnitude = systolicMagnitude;
        }

        public Double getSystolicMagnitude() {
            return this.systolicMagnitude;
        }

        public void setSystolicUnits(String systolicUnits) {
            this.systolicUnits = systolicUnits;
        }

        public String getSystolicUnits() {
            return this.systolicUnits;
        }

        public void setDiastolicMagnitude(Double diastolicMagnitude) {
            this.diastolicMagnitude = diastolicMagnitude;
        }

        public Double getDiastolicMagnitude() {
            return this.diastolicMagnitude;
        }

        public void setDiastolicUnits(String diastolicUnits) {
            this.diastolicUnits = diastolicUnits;
        }

        public String getDiastolicUnits() {
            return this.diastolicUnits;
        }

        public void setValue3(DataValue value3) {
            this.value3 = value3;
        }

        public DataValue getValue3() {
            return this.value3;
        }

        public void setTiltMagnitude(Double tiltMagnitude) {
            this.tiltMagnitude = tiltMagnitude;
        }

        public Double getTiltMagnitude() {
            return this.tiltMagnitude;
        }

        public void setTiltUnits(String tiltUnits) {
            this.tiltUnits = tiltUnits;
        }

        public String getTiltUnits() {
            return this.tiltUnits;
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        public Position getPosition() {
            return this.position;
        }

        public void setMeanArterialPressureMagnitude(Double meanArterialPressureMagnitude) {
            this.meanArterialPressureMagnitude = meanArterialPressureMagnitude;
        }

        public Double getMeanArterialPressureMagnitude() {
            return this.meanArterialPressureMagnitude;
        }

        public void setMeanArterialPressureUnits(String meanArterialPressureUnits) {
            this.meanArterialPressureUnits = meanArterialPressureUnits;
        }

        public String getMeanArterialPressureUnits() {
            return this.meanArterialPressureUnits;
        }

        public void setPulsePressureMagnitude(Double pulsePressureMagnitude) {
            this.pulsePressureMagnitude = pulsePressureMagnitude;
        }

        public Double getPulsePressureMagnitude() {
            return this.pulsePressureMagnitude;
        }

        public void setPulsePressureUnits(String pulsePressureUnits) {
            this.pulsePressureUnits = pulsePressureUnits;
        }

        public String getPulsePressureUnits() {
            return this.pulsePressureUnits;
        }

        public void setValue4(DataValue value4) {
            this.value4 = value4;
        }

        public DataValue getValue4() {
            return this.value4;
        }

        public enum CuffSize implements EnumValueSet {
            NEONATAL("Neonatal", "A cuff used for a new born - bladder approx 3cm x 6cm", "local", "at1009"),

            ADULTTHIGH("Adult Thigh", "A cuff used for an adult thigh - bladder approx 20cm x 42 cm", "local", "at1008"),

            INFANT("Infant", "A cuff used for infants - bladder approx 5cm x 15cm", "local", "at1018"),

            SMALLADULT("Small Adult", "A cuff used for a small adult - bladder approx 10cm x 24 cm", "local", "at1019"),

            CHILD("Paediatric/Child", "A cuff that is appropriate for a child or thin arm - bladder approx 8cm x 21 cm", "local", "at0017"),

            LARGEADULT("Large Adult", "A cuff for adults with larger arms - bladder approx 16cm x 38cm", "local", "at0016"),

            ADULT("Adult", "A cuff that is standard for an adult - bladder approx 13cm x 30cm", "local", "at0015");

            private String value;

            private String description;

            private String terminologyId;

            private String code;

            CuffSize(String value, String description, String terminologyId, String code) {
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

        public enum LocationOfMeasurement implements EnumValueSet {
            LEFTTHIGH("Left thigh", "The left thigh of the person", "local", "at0028"),

            RIGHTWRIST("Right wrist", "The right wrist of the person", "local", "at1020"),

            RIGHTARM("Right arm", "The right arm of the person", "local", "at0025"),

            LEFTANKLE("Left ankle", "The left ankle of the person", "local", "at1032"),

            RIGHTANKLE("Right ankle", "The right ankle of the person", "local", "at1031"),

            FINGER("Finger", "A finger of the person", "local", "at1026"),

            LEFTARM("Left arm", "The left arm of the person", "local", "at0026"),

            RIGHTTHIGH("Right thigh", "The right thigh of the person", "local", "at0027"),

            INTRAARTERIAL("Intra-arterial", "Blood pressure monitored via an intra-arterial line", "local", "at0032"),

            LEFTWRIST("Left wrist", "The left wrist of the person", "local", "at1021");

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

        public enum KorotkoffSounds implements EnumValueSet {
            FOURTHSOUND("Fourth sound", "The fourth Korotkoff sound is identified as an abrupt muffling of sounds", "local", "at1011"),

            FIFTHSOUND("Fifth sound", "The fifth Korotkoff sound is identified by absence of sounds as the cuff pressure drops below the diastolic blood pressure", "local", "at1012");

            private String value;

            private String description;

            private String terminologyId;

            private String code;

            KorotkoffSounds(String value, String description, String terminologyId, String code) {
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

        public enum Position implements EnumValueSet {
            LEFTLATERAL("Left Lateral", "Lying on the left side at the time of blood pressure measurement", "local", "at1014"),

            STANDING("Standing", "Standing at the time of blood pressure measurement", "local", "at1000"),

            RECLINING("Reclining", "Reclining at the time of blood pressure measurement", "local", "at1002"),

            SITTING("Sitting", "Sitting (for example on bed or chair) at the time of blood pressure measurement", "local", "at1001"),

            TRENDELENBURG("Trendelenburg", "Lying flat on the back (supine position) with the feet higher than the head at the time of blood pressure measurement", "local", "at1013"),

            LYING("Lying", "Lying flat at the time of blood pressure measurement", "local", "at1003");

            private String value;

            private String description;

            private String terminologyId;

            private String code;

            Position(String value, String description, String terminologyId, String code) {
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

    @Archetype("openEHR-EHR-CLUSTER.sample_device.v1")
    @Entity
    public static class DeviceDetailsTrainingSample {
        @Path("/items[at0004]/items[at0005]/value|value")
        private String modelValue;

        @Path("/items[at0004]/items[at0006]/value|value")
        private String serialNumberValue;

        @Path("/items[at0008]/items[at0009]/value|value")
        private TemporalAccessor dateLastServicedValue;

        @Path("/items[at0008]/items[at0010]/value|value")
        private TemporalAccessor dateLastCalibrationValue;

        @Path("/items[at0001]/value|value")
        private String nameValue;

        @Path("/items[at0002]/value|value")
        private String descriptionValue;

        @Path("/items[at0007]/items")
        private Cluster components;

        @Path("/items[at0008]/items[at0011]/value|value")
        private String servicedByValue;

        @Path("/items[at0004]/items[at0003]/value|value")
        private String manufacturerValue;

        public void setModelValue(String modelValue) {
            this.modelValue = modelValue;
        }

        public String getModelValue() {
            return this.modelValue;
        }

        public void setSerialNumberValue(String serialNumberValue) {
            this.serialNumberValue = serialNumberValue;
        }

        public String getSerialNumberValue() {
            return this.serialNumberValue;
        }

        public void setDateLastServicedValue(TemporalAccessor dateLastServicedValue) {
            this.dateLastServicedValue = dateLastServicedValue;
        }

        public TemporalAccessor getDateLastServicedValue() {
            return this.dateLastServicedValue;
        }

        public void setDateLastCalibrationValue(TemporalAccessor dateLastCalibrationValue) {
            this.dateLastCalibrationValue = dateLastCalibrationValue;
        }

        public TemporalAccessor getDateLastCalibrationValue() {
            return this.dateLastCalibrationValue;
        }

        public void setNameValue(String nameValue) {
            this.nameValue = nameValue;
        }

        public String getNameValue() {
            return this.nameValue;
        }

        public void setDescriptionValue(String descriptionValue) {
            this.descriptionValue = descriptionValue;
        }

        public String getDescriptionValue() {
            return this.descriptionValue;
        }

        public void setComponents(Cluster components) {
            this.components = components;
        }

        public Cluster getComponents() {
            return this.components;
        }

        public void setServicedByValue(String servicedByValue) {
            this.servicedByValue = servicedByValue;
        }

        public String getServicedByValue() {
            return this.servicedByValue;
        }

        public void setManufacturerValue(String manufacturerValue) {
            this.manufacturerValue = manufacturerValue;
        }

        public String getManufacturerValue() {
            return this.manufacturerValue;
        }
    }
}
