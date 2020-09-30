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
@Archetype("openEHR-EHR-OBSERVATION.pulse.v1")
public class PulseObservation {
    @Path("/protocol[at0010]/items[at1056]")
    private List<Cluster> extension;

    @Path("/language")
    private Language language;

    @Path("/protocol[at0010]/items[at1013]")
    private Cluster device;

    @Path("/data[at0002]/origin|value")
    private TemporalAccessor originValue;

    @Path("/data[at0002]/events[at0003]/state[at0012]/items[at1017]")
    private List<Cluster> exertion;

    @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude")
    private Double pulseRateMagnitude;

    @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units")
    private String pulseRateUnits;

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

    public void setDevice(Cluster device) {
        this.device = device;
    }

    public Cluster getDevice() {
        return this.device;
    }

    public void setOriginValue(TemporalAccessor originValue) {
        this.originValue = originValue;
    }

    public TemporalAccessor getOriginValue() {
        return this.originValue;
    }

    public void setExertion(List<Cluster> exertion) {
        this.exertion = exertion;
    }

    public List<Cluster> getExertion() {
        return this.exertion;
    }

    public void setPulseRateMagnitude(Double pulseRateMagnitude) {
        this.pulseRateMagnitude = pulseRateMagnitude;
    }

    public Double getPulseRateMagnitude() {
        return this.pulseRateMagnitude;
    }

    public void setPulseRateUnits(String pulseRateUnits) {
        this.pulseRateUnits = pulseRateUnits;
    }

    public String getPulseRateUnits() {
        return this.pulseRateUnits;
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
