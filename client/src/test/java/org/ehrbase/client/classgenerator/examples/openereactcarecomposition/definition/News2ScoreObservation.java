package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.news2.v1")
public class News2ScoreObservation {
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0028]/value|magnitude")
    private Long totalScoreMagnitude;

    @Path("/language")
    private Language language;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0029]/value")
    private DvOrdinal spoScale1;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0056]/value|defining_code")
    private ClinicalRiskCategoryDefiningcode clinicalRiskCategoryDefiningcode;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0034]/value")
    private DvOrdinal airOrOxygen;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0047]/value")
    private DvOrdinal spoScale2;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value")
    private DvOrdinal systolicBloodPressure;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value")
    private DvOrdinal pulse;

    @Path("/data[at0001]/events[at0002]/time|value")
    private TemporalAccessor timeValue;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006]/value")
    private DvOrdinal respirationRate;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0008]/value")
    private DvOrdinal consciousness;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0007]/value")
    private DvOrdinal temperature;

    @Path("/subject")
    private PartyProxy subject;

    @Path("/data[at0001]/origin|value")
    private TemporalAccessor originValue;

    @Path("/protocol[at0045]/items[at0046]")
    private List<Cluster> extension;

    public void setTotalScoreMagnitude(Long totalScoreMagnitude) {
        this.totalScoreMagnitude = totalScoreMagnitude;
    }

    public Long getTotalScoreMagnitude() {
        return this.totalScoreMagnitude;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setSpoScale1(DvOrdinal spoScale1) {
        this.spoScale1 = spoScale1;
    }

    public DvOrdinal getSpoScale1() {
        return this.spoScale1;
    }

    public void setClinicalRiskCategoryDefiningcode(
            ClinicalRiskCategoryDefiningcode clinicalRiskCategoryDefiningcode) {
        this.clinicalRiskCategoryDefiningcode = clinicalRiskCategoryDefiningcode;
    }

    public ClinicalRiskCategoryDefiningcode getClinicalRiskCategoryDefiningcode() {
        return this.clinicalRiskCategoryDefiningcode;
    }

    public void setAirOrOxygen(DvOrdinal airOrOxygen) {
        this.airOrOxygen = airOrOxygen;
    }

    public DvOrdinal getAirOrOxygen() {
        return this.airOrOxygen;
    }

    public void setSpoScale2(DvOrdinal spoScale2) {
        this.spoScale2 = spoScale2;
    }

    public DvOrdinal getSpoScale2() {
        return this.spoScale2;
    }

    public void setSystolicBloodPressure(DvOrdinal systolicBloodPressure) {
        this.systolicBloodPressure = systolicBloodPressure;
    }

    public DvOrdinal getSystolicBloodPressure() {
        return this.systolicBloodPressure;
    }

    public void setPulse(DvOrdinal pulse) {
        this.pulse = pulse;
    }

    public DvOrdinal getPulse() {
        return this.pulse;
    }

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }

    public void setRespirationRate(DvOrdinal respirationRate) {
        this.respirationRate = respirationRate;
    }

    public DvOrdinal getRespirationRate() {
        return this.respirationRate;
    }

    public void setConsciousness(DvOrdinal consciousness) {
        this.consciousness = consciousness;
    }

    public DvOrdinal getConsciousness() {
        return this.consciousness;
    }

    public void setTemperature(DvOrdinal temperature) {
        this.temperature = temperature;
    }

    public DvOrdinal getTemperature() {
        return this.temperature;
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

    public void setExtension(List<Cluster> extension) {
        this.extension = extension;
    }

    public List<Cluster> getExtension() {
        return this.extension;
    }
}
