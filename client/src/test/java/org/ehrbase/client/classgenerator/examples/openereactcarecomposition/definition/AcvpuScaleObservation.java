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
@Archetype("openEHR-EHR-OBSERVATION.acvpu.v0")
public class AcvpuScaleObservation {
    @Path("/protocol[at0009]/items[at0011]")
    private List<Cluster> extension;

    @Path("/data[at0001]/events[at0002]/time|value")
    private TemporalAccessor timeValue;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|defining_code")
    private AcvpuDefiningcode acvpuDefiningcode;

    @Path("/subject")
    private PartyProxy subject;

    @Path("/data[at0001]/origin|value")
    private TemporalAccessor originValue;

    @Path("/language")
    private Language language;

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

    public void setAcvpuDefiningcode(AcvpuDefiningcode acvpuDefiningcode) {
        this.acvpuDefiningcode = acvpuDefiningcode;
    }

    public AcvpuDefiningcode getAcvpuDefiningcode() {
        return this.acvpuDefiningcode;
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

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }
}
