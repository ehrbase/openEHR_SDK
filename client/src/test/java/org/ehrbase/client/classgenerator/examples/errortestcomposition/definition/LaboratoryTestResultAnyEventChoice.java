package org.ehrbase.client.classgenerator.examples.errortestcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;

import javax.annotation.processing.Generated;
import java.time.temporal.TemporalAccessor;
import java.util.List;

@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-03-02T14:11:00.878600700+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public interface LaboratoryTestResultAnyEventChoice {
  String getTestNameValue();

  void setTestNameValue(String testNameValue);

  SpecimenCluster getSpecimen();

  void setSpecimen(SpecimenCluster specimen);

  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);

  List<Cluster> getMultimediaRepresentation();

  void setMultimediaRepresentation(List<Cluster> multimediaRepresentation);

  List<Cluster> getTestResult();

  void setTestResult(List<Cluster> testResult);

  List<Cluster> getStructuredConfoundingFactors();

  void setStructuredConfoundingFactors(List<Cluster> structuredConfoundingFactors);

  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);

  List<Cluster> getStructuredTestDiagnosis();

  void setStructuredTestDiagnosis(List<Cluster> structuredTestDiagnosis);
}
