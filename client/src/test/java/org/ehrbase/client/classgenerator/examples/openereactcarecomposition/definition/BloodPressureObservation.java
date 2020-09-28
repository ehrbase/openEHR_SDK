package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.blood_pressure.v2")
public class BloodPressureObservation {
    @Path("/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value|magnitude")
    private Double systolicMagnitude;

    @Path("/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value|units")
    private String systolicUnits;

    @Path("/data[at0001]/events[at0006]/state[at0007]/items[at1030]")
    private Cluster exertion;

    @Path("/protocol[at0011]/items[at1057]")
    private List<Cluster> structuredMeasurementLocation;

    @Path("/protocol[at0011]/items[at1025]")
    private Cluster device;

    @Path("/protocol[at0011]/items[at1058]")
    private List<Cluster> extension;

    @Path("/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value|magnitude")
    private Double diastolicMagnitude;

    @Path("/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value|units")
    private String diastolicUnits;

    @Path("/language")
    private Language language;

    @Path("/subject")
    private PartyProxy subject;

    @Path("/data[at0001]/origin|value")
    private TemporalAccessor originValue;

    @Path("/data[at0001]/events[at0006]/time|value")
    private TemporalAccessor timeValue;

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

    public void setExertion(Cluster exertion) {
        this.exertion = exertion;
    }

    public Cluster getExertion() {
        return this.exertion;
    }

    public void setStructuredMeasurementLocation(List<Cluster> structuredMeasurementLocation) {
        this.structuredMeasurementLocation = structuredMeasurementLocation;
    }

    public List<Cluster> getStructuredMeasurementLocation() {
        return this.structuredMeasurementLocation;
    }

    public void setDevice(Cluster device) {
        this.device = device;
    }

    public Cluster getDevice() {
        return this.device;
    }

    public void setExtension(List<Cluster> extension) {
        this.extension = extension;
    }

    public List<Cluster> getExtension() {
        return this.extension;
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

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setSubject(PartyProxy subject) {
        this.subject = subject;
    }

    public PartyProxy getSubject() {
        return this.subject;
    }

    public void setOriginValue(TemporalAccessor originValue) {
        this.originValue = originValue;
    }

    public TemporalAccessor getOriginValue() {
        return this.originValue;
    }

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }
}
