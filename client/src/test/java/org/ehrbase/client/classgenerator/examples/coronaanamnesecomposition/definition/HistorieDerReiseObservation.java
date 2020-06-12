package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.travel_history.v0")
public class HistorieDerReiseObservation {
    @Path("/language")
    private Language language;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0134]/items[openEHR-EHR-CLUSTER.location.v1]")
    private StandortCluster standort;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0111]/value|defining_code")
    private KurzlicheReiseDefiningcode kurzlicheReiseDefiningcode;

    @Path("/data[at0001]/events[at0002]/time|value")
    private TemporalAccessor timeValue;

    @Path("/subject")
    private PartyProxy subject;

    @Path("/data[at0001]/origin|value")
    private TemporalAccessor originValue;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0111]/name|value")
    private String kurzlicheReiseValue;

    @Path("/protocol[at0100]/items[at0101]")
    private List<Cluster> extensionEn;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0109]")
    private List<Cluster> detaillierteAngabenZurExposition;

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setStandort(StandortCluster standort) {
        this.standort = standort;
    }

    public StandortCluster getStandort() {
        return this.standort;
    }

    public void setKurzlicheReiseDefiningcode(KurzlicheReiseDefiningcode kurzlicheReiseDefiningcode) {
        this.kurzlicheReiseDefiningcode = kurzlicheReiseDefiningcode;
    }

    public KurzlicheReiseDefiningcode getKurzlicheReiseDefiningcode() {
        return this.kurzlicheReiseDefiningcode;
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

    public void setKurzlicheReiseValue(String kurzlicheReiseValue) {
        this.kurzlicheReiseValue = kurzlicheReiseValue;
    }

    public String getKurzlicheReiseValue() {
        return this.kurzlicheReiseValue;
    }

    public void setExtensionEn(List<Cluster> extensionEn) {
        this.extensionEn = extensionEn;
    }

    public List<Cluster> getExtensionEn() {
        return this.extensionEn;
    }

    public void setDetaillierteAngabenZurExposition(List<Cluster> detaillierteAngabenZurExposition) {
        this.detaillierteAngabenZurExposition = detaillierteAngabenZurExposition;
    }

    public List<Cluster> getDetaillierteAngabenZurExposition() {
        return this.detaillierteAngabenZurExposition;
    }
}
