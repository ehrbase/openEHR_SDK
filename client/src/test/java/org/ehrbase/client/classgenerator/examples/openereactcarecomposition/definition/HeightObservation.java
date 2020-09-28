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
@Archetype("openEHR-EHR-OBSERVATION.height.v2")
public class HeightObservation {
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|magnitude")
    private Double heightLengthMagnitude;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|units")
    private String heightLengthUnits;

    @Path("/language")
    private Language language;

    @Path("/protocol[at0007]/items[at0011]")
    private Cluster device;

    @Path("/protocol[at0007]/items[at0022]")
    private List<Cluster> extension;

    @Path("/data[at0001]/events[at0002]/time|value")
    private TemporalAccessor timeValue;

    @Path("/subject")
    private PartyProxy subject;

    @Path("/data[at0001]/origin|value")
    private TemporalAccessor originValue;

    public void setHeightLengthMagnitude(Double heightLengthMagnitude) {
        this.heightLengthMagnitude = heightLengthMagnitude;
    }

    public Double getHeightLengthMagnitude() {
        return this.heightLengthMagnitude;
    }

    public void setHeightLengthUnits(String heightLengthUnits) {
        this.heightLengthUnits = heightLengthUnits;
    }

    public String getHeightLengthUnits() {
        return this.heightLengthUnits;
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

    public void setExtension(List<Cluster> extension) {
        this.extension = extension;
    }

    public List<Cluster> getExtension() {
        return this.extension;
    }

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
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
}
