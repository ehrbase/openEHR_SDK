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
@Archetype("openEHR-EHR-OBSERVATION.body_weight.v2")
public class WeightObservation {
    @Path("/protocol[at0015]/items[at0027]")
    private List<Cluster> extension;

    @Path("/language")
    private Language language;

    @Path("/data[at0002]/origin|value")
    private TemporalAccessor originValue;

    @Path("/protocol[at0015]/items[at0020]")
    private Cluster device;

    @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude")
    private Double weightMagnitude;

    @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units")
    private String weightUnits;

    @Path("/subject")
    private PartyProxy subject;

    @Path("/data[at0002]/events[at0003]/time|value")
    private TemporalAccessor timeValue;

    public void setExtension(List<Cluster> extension) {
        this.extension = extension;
    }

    public List<Cluster> getExtension() {
        return this.extension;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
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

    public void setWeightMagnitude(Double weightMagnitude) {
        this.weightMagnitude = weightMagnitude;
    }

    public Double getWeightMagnitude() {
        return this.weightMagnitude;
    }

    public void setWeightUnits(String weightUnits) {
        this.weightUnits = weightUnits;
    }

    public String getWeightUnits() {
        return this.weightUnits;
    }

    public void setSubject(PartyProxy subject) {
        this.subject = subject;
    }

    public PartyProxy getSubject() {
        return this.subject;
    }

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }
}
