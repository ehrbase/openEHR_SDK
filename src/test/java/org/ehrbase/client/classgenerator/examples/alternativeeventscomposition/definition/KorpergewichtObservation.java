package org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.body_weight.v2")
public class KorpergewichtObservation {
    @Path("/data[at0002]/events[at0026]")
    private KorpergewichtBirthEnEvent birthEn;

    @Path("/data[at0002]/events[at0003]")
    @Choice
    private List<KorpergewichtAnyEventEnChoice> anyEventEn;

    @Path("/protocol[at0015]/items[at0027]")
    private List<Cluster> extensionEn;

    @Path("/language")
    private Language language;

    @Path("/data[at0002]/origin|value")
    private TemporalAccessor originValue;

    @Path("/protocol[at0015]/items[at0020]")
    private Cluster gerat;

    @Path("/subject")
    private PartyProxy subject;

    public void setBirthEn(KorpergewichtBirthEnEvent birthEn) {
        this.birthEn = birthEn;
    }

    public KorpergewichtBirthEnEvent getBirthEn() {
        return this.birthEn;
    }

    public void setAnyEventEn(List<KorpergewichtAnyEventEnChoice> anyEventEn) {
        this.anyEventEn = anyEventEn;
    }

    public List<KorpergewichtAnyEventEnChoice> getAnyEventEn() {
        return this.anyEventEn;
    }

    public void setExtensionEn(List<Cluster> extensionEn) {
        this.extensionEn = extensionEn;
    }

    public List<Cluster> getExtensionEn() {
        return this.extensionEn;
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

    public void setGerat(Cluster gerat) {
        this.gerat = gerat;
    }

    public Cluster getGerat() {
        return this.gerat;
    }

    public void setSubject(PartyProxy subject) {
        this.subject = subject;
    }

    public PartyProxy getSubject() {
        return this.subject;
    }
}
