package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

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
@Archetype("openEHR-EHR-OBSERVATION.laboratory_test_result.v1")
public class LatestCovid19TestObservation {
    @Path("/protocol[at0004]/items[at0110]")
    private List<Cluster> testingDetails;

    @Path("/protocol[at0004]/items[at0094]")
    private List<LatestCovid19TestTestRequestDetailsCluster> testRequestDetails;

    @Path("/language")
    private Language language;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0098]/value|value")
    private String testDiagnosisValue;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0118]")
    private List<Cluster> multimediaRepresentation;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|value")
    private String testNameValue;

    @Path("/data[at0001]/events[at0002]/state[at0112]/items[at0114]")
    private List<Cluster> structuredConfoundingFactors;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0122]")
    private List<Cluster> structuredTestDiagnosis;

    @Path("/protocol[at0004]/items[at0117]")
    private List<Cluster> extension;

    @Path("/data[at0001]/events[at0002]/time|value")
    private TemporalAccessor timeValue;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0065]")
    private List<Cluster> specimenDetail;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0097]")
    private List<Cluster> testResult;

    @Path("/subject")
    private PartyProxy subject;

    @Path("/data[at0001]/origin|value")
    private TemporalAccessor originValue;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0073]/value")
    @Choice
    private LatestCovid19TestOverallTestStatusChoice overallTestStatus;

    @Path("/protocol[at0004]/items[at0017]")
    private Cluster receivingLaboratory;

    public void setTestingDetails(List<Cluster> testingDetails) {
        this.testingDetails = testingDetails;
    }

    public List<Cluster> getTestingDetails() {
        return this.testingDetails;
    }

    public void setTestRequestDetails(
            List<LatestCovid19TestTestRequestDetailsCluster> testRequestDetails) {
        this.testRequestDetails = testRequestDetails;
    }

    public List<LatestCovid19TestTestRequestDetailsCluster> getTestRequestDetails() {
        return this.testRequestDetails;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setTestDiagnosisValue(String testDiagnosisValue) {
        this.testDiagnosisValue = testDiagnosisValue;
    }

    public String getTestDiagnosisValue() {
        return this.testDiagnosisValue;
    }

    public void setMultimediaRepresentation(List<Cluster> multimediaRepresentation) {
        this.multimediaRepresentation = multimediaRepresentation;
    }

    public List<Cluster> getMultimediaRepresentation() {
        return this.multimediaRepresentation;
    }

    public void setTestNameValue(String testNameValue) {
        this.testNameValue = testNameValue;
    }

    public String getTestNameValue() {
        return this.testNameValue;
    }

    public void setStructuredConfoundingFactors(List<Cluster> structuredConfoundingFactors) {
        this.structuredConfoundingFactors = structuredConfoundingFactors;
    }

    public List<Cluster> getStructuredConfoundingFactors() {
        return this.structuredConfoundingFactors;
    }

    public void setStructuredTestDiagnosis(List<Cluster> structuredTestDiagnosis) {
        this.structuredTestDiagnosis = structuredTestDiagnosis;
    }

    public List<Cluster> getStructuredTestDiagnosis() {
        return this.structuredTestDiagnosis;
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

    public void setSpecimenDetail(List<Cluster> specimenDetail) {
        this.specimenDetail = specimenDetail;
    }

    public List<Cluster> getSpecimenDetail() {
        return this.specimenDetail;
    }

    public void setTestResult(List<Cluster> testResult) {
        this.testResult = testResult;
    }

    public List<Cluster> getTestResult() {
        return this.testResult;
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

    public void setOverallTestStatus(LatestCovid19TestOverallTestStatusChoice overallTestStatus) {
        this.overallTestStatus = overallTestStatus;
    }

    public LatestCovid19TestOverallTestStatusChoice getOverallTestStatus() {
        return this.overallTestStatus;
    }

    public void setReceivingLaboratory(Cluster receivingLaboratory) {
        this.receivingLaboratory = receivingLaboratory;
    }

    public Cluster getReceivingLaboratory() {
        return this.receivingLaboratory;
    }
}
