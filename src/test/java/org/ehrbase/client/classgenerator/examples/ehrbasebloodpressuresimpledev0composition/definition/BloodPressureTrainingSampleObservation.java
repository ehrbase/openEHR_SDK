package org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.sample_blood_pressure.v1")
public class BloodPressureTrainingSampleObservation {
    @Path("/protocol[at0011]/items[at1025]")
    private List<Cluster> device;

    @Path("/language")
    private Language language;

    @Path("/data[at0001]/events[at0002]/state[at0007]/items[at1030]")
    private List<Cluster> levelOfExertion;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0033]/value|value")
    private String commentValue;

    @Path("/protocol[at0011]/items[at0013]/value|defining_code")
    private CuffSizeDefiningcode cuffSizeDefiningcode;

    @Path("/protocol[at0011]/items[at1010]/value|defining_code")
    private KorotkoffSoundsDefiningcode korotkoffSoundsDefiningcode;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|magnitude")
    private Double systolicMagnitude;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|units")
    private String systolicUnits;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|magnitude")
    private Double diastolicMagnitude;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|units")
    private String diastolicUnits;

    @Path("/data[at0001]/events[at0002]/state[at0007]/items[at0008]/value|defining_code")
    private PositionDefiningcode positionDefiningcode;

    @Path("/data[at0001]/events[at0002]/state[at0007]/items[at1005]/value|magnitude")
    private Double tiltMagnitude;

    @Path("/data[at0001]/events[at0002]/state[at0007]/items[at1005]/value|units")
    private String tiltUnits;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1006]/value|magnitude")
    private Double meanArterialPressureMagnitude;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1006]/value|units")
    private String meanArterialPressureUnits;

    @Path("/data[at0001]/events[at0002]/time|value")
    private TemporalAccessor timeValue;

    @Path("/subject")
    private PartyProxy subject;

    @Path("/data[at0001]/origin|value")
    private TemporalAccessor originValue;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1007]/value|magnitude")
    private Double pulsePressureMagnitude;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1007]/value|units")
    private String pulsePressureUnits;

    @Path("/protocol[at0011]/items[at0014]/value|defining_code")
    private LocationOfMeasurementDefiningcode locationOfMeasurementDefiningcode;

    public List<Cluster> getDevice() {
        return this.device;
    }

    public void setDevice(List<Cluster> device) {
        this.device = device;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public List<Cluster> getLevelOfExertion() {
        return this.levelOfExertion;
    }

    public void setLevelOfExertion(List<Cluster> levelOfExertion) {
        this.levelOfExertion = levelOfExertion;
    }

    public String getCommentValue() {
        return this.commentValue;
    }

    public void setCommentValue(String commentValue) {
        this.commentValue = commentValue;
    }

    public CuffSizeDefiningcode getCuffSizeDefiningcode() {
        return this.cuffSizeDefiningcode;
    }

    public void setCuffSizeDefiningcode(CuffSizeDefiningcode cuffSizeDefiningcode) {
        this.cuffSizeDefiningcode = cuffSizeDefiningcode;
    }

    public KorotkoffSoundsDefiningcode getKorotkoffSoundsDefiningcode() {
        return this.korotkoffSoundsDefiningcode;
    }

    public void setKorotkoffSoundsDefiningcode(
            KorotkoffSoundsDefiningcode korotkoffSoundsDefiningcode) {
        this.korotkoffSoundsDefiningcode = korotkoffSoundsDefiningcode;
    }

    public Double getSystolicMagnitude() {
        return this.systolicMagnitude;
    }

    public void setSystolicMagnitude(Double systolicMagnitude) {
        this.systolicMagnitude = systolicMagnitude;
    }

    public String getSystolicUnits() {
        return this.systolicUnits;
    }

    public void setSystolicUnits(String systolicUnits) {
        this.systolicUnits = systolicUnits;
    }

    public Double getDiastolicMagnitude() {
        return this.diastolicMagnitude;
    }

    public void setDiastolicMagnitude(Double diastolicMagnitude) {
        this.diastolicMagnitude = diastolicMagnitude;
    }

    public String getDiastolicUnits() {
        return this.diastolicUnits;
    }

    public void setDiastolicUnits(String diastolicUnits) {
        this.diastolicUnits = diastolicUnits;
    }

    public PositionDefiningcode getPositionDefiningcode() {
        return this.positionDefiningcode;
    }

    public void setPositionDefiningcode(PositionDefiningcode positionDefiningcode) {
        this.positionDefiningcode = positionDefiningcode;
    }

    public Double getTiltMagnitude() {
        return this.tiltMagnitude;
    }

    public void setTiltMagnitude(Double tiltMagnitude) {
        this.tiltMagnitude = tiltMagnitude;
    }

    public String getTiltUnits() {
        return this.tiltUnits;
    }

    public void setTiltUnits(String tiltUnits) {
        this.tiltUnits = tiltUnits;
    }

    public Double getMeanArterialPressureMagnitude() {
        return this.meanArterialPressureMagnitude;
    }

    public void setMeanArterialPressureMagnitude(Double meanArterialPressureMagnitude) {
        this.meanArterialPressureMagnitude = meanArterialPressureMagnitude;
    }

    public String getMeanArterialPressureUnits() {
        return this.meanArterialPressureUnits;
    }

    public void setMeanArterialPressureUnits(String meanArterialPressureUnits) {
        this.meanArterialPressureUnits = meanArterialPressureUnits;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public PartyProxy getSubject() {
        return this.subject;
    }

    public void setSubject(PartyProxy subject) {
        this.subject = subject;
    }

    public TemporalAccessor getOriginValue() {
        return this.originValue;
    }

    public void setOriginValue(TemporalAccessor originValue) {
        this.originValue = originValue;
    }

    public Double getPulsePressureMagnitude() {
        return this.pulsePressureMagnitude;
    }

    public void setPulsePressureMagnitude(Double pulsePressureMagnitude) {
        this.pulsePressureMagnitude = pulsePressureMagnitude;
    }

    public String getPulsePressureUnits() {
        return this.pulsePressureUnits;
    }

    public void setPulsePressureUnits(String pulsePressureUnits) {
        this.pulsePressureUnits = pulsePressureUnits;
    }

    public LocationOfMeasurementDefiningcode getLocationOfMeasurementDefiningcode() {
        return this.locationOfMeasurementDefiningcode;
    }

    public void setLocationOfMeasurementDefiningcode(
            LocationOfMeasurementDefiningcode locationOfMeasurementDefiningcode) {
        this.locationOfMeasurementDefiningcode = locationOfMeasurementDefiningcode;
    }
}
