package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.laboratory_test_result.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:51.635759700+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class LatestCovid19TestObservation implements EntryEntity {
  /**
   * open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Any event/Test name
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|value")
  private String testNameValue;

  /**
   * open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Any event/Specimen detail
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0065]")
  private List<Cluster> specimenDetail;

  /**
   * open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Any event/Test result
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0097]")
  private List<Cluster> testResult;

  /**
   * open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Any event/Test diagnosis
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0098]/value|value")
  private String testDiagnosisValue;

  /**
   * open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Any event/Structured test diagnosis
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0122]")
  private List<Cluster> structuredTestDiagnosis;

  /**
   * open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Any event/Multimedia representation
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0118]")
  private List<Cluster> multimediaRepresentation;

  /**
   * open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Any event/Structured confounding factors
   */
  @Path("/data[at0001]/events[at0002]/state[at0112]/items[at0114]")
  private List<Cluster> structuredConfoundingFactors;

  /**
   * open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Any event/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /**
   * open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Receiving laboratory
   */
  @Path("/protocol[at0004]/items[at0017]")
  private Cluster receivingLaboratory;

  /**
   * open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Test request details
   */
  @Path("/protocol[at0004]/items[at0094]")
  private List<LatestCovid19TestTestRequestDetailsCluster> testRequestDetails;

  /**
   * open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Testing details
   */
  @Path("/protocol[at0004]/items[at0110]")
  private List<Cluster> testingDetails;

  /**
   * open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Extension
   */
  @Path("/protocol[at0004]/items[at0117]")
  private List<Cluster> extension;

  /**
   * open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/language
   */
  @Path("/language")
  private Language language;

  /**
   * open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Any event/value
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0073]/value")
  @Choice
  private LatestCovid19TestOverallTestStatusChoice overallTestStatus;

  public void setTestNameValue(String testNameValue) {
     this.testNameValue = testNameValue;
  }

  public String getTestNameValue() {
     return this.testNameValue ;
  }

  public void setSpecimenDetail(List<Cluster> specimenDetail) {
     this.specimenDetail = specimenDetail;
  }

  public List<Cluster> getSpecimenDetail() {
     return this.specimenDetail ;
  }

  public void setTestResult(List<Cluster> testResult) {
     this.testResult = testResult;
  }

  public List<Cluster> getTestResult() {
     return this.testResult ;
  }

  public void setTestDiagnosisValue(String testDiagnosisValue) {
     this.testDiagnosisValue = testDiagnosisValue;
  }

  public String getTestDiagnosisValue() {
     return this.testDiagnosisValue ;
  }

  public void setStructuredTestDiagnosis(List<Cluster> structuredTestDiagnosis) {
     this.structuredTestDiagnosis = structuredTestDiagnosis;
  }

  public List<Cluster> getStructuredTestDiagnosis() {
     return this.structuredTestDiagnosis ;
  }

  public void setMultimediaRepresentation(List<Cluster> multimediaRepresentation) {
     this.multimediaRepresentation = multimediaRepresentation;
  }

  public List<Cluster> getMultimediaRepresentation() {
     return this.multimediaRepresentation ;
  }

  public void setStructuredConfoundingFactors(List<Cluster> structuredConfoundingFactors) {
     this.structuredConfoundingFactors = structuredConfoundingFactors;
  }

  public List<Cluster> getStructuredConfoundingFactors() {
     return this.structuredConfoundingFactors ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
  }

  public void setReceivingLaboratory(Cluster receivingLaboratory) {
     this.receivingLaboratory = receivingLaboratory;
  }

  public Cluster getReceivingLaboratory() {
     return this.receivingLaboratory ;
  }

  public void setTestRequestDetails(
      List<LatestCovid19TestTestRequestDetailsCluster> testRequestDetails) {
     this.testRequestDetails = testRequestDetails;
  }

  public List<LatestCovid19TestTestRequestDetailsCluster> getTestRequestDetails() {
     return this.testRequestDetails ;
  }

  public void setTestingDetails(List<Cluster> testingDetails) {
     this.testingDetails = testingDetails;
  }

  public List<Cluster> getTestingDetails() {
     return this.testingDetails ;
  }

  public void setExtension(List<Cluster> extension) {
     this.extension = extension;
  }

  public List<Cluster> getExtension() {
     return this.extension ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setOverallTestStatus(LatestCovid19TestOverallTestStatusChoice overallTestStatus) {
     this.overallTestStatus = overallTestStatus;
  }

  public LatestCovid19TestOverallTestStatusChoice getOverallTestStatus() {
     return this.overallTestStatus ;
  }
}
