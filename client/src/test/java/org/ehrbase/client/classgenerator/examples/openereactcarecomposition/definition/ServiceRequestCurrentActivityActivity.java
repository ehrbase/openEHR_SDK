package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class ServiceRequestCurrentActivityActivity {
  @Path("/description[at0009]/items[at0121]/value")
  private DvCodedText serviceName;

  @Path("/description[at0009]/items[at0062]")
  private List<ServiceRequestReasonForRequestElement> reasonForRequest;

  @Path("/description[at0009]/items[at0064 and name/value='Reason for isolation']/value|value")
  private String reasonForIsolationValue;

  @Path("/description[at0009]/items[at0151]")
  private List<Cluster> complexTiming;

  @Path("/description[at0009]/items[at0145 and name/value='Date isolation due to start']/value|value")
  private TemporalAccessor dateIsolationDueToStartValue;

  @Path("/description[at0009]/items[at0144 and name/value='Date isolation due to end']/value|value")
  private TemporalAccessor dateIsolationDueToEndValue;

  @Path("/description[at0009]/items[at0132]")
  private List<Cluster> specificDetails;

  @Path("/description[at0009]/items[at0149]")
  private List<Cluster> supportingInformation;

  @Path("/description[at0009]/items[at0116]")
  private List<Cluster> patientRequirements;

  @Path("/timing")
  private DvParsable timing;

  public void setServiceName(DvCodedText serviceName) {
     this.serviceName = serviceName;
  }

  public DvCodedText getServiceName() {
     return this.serviceName ;
  }

  public void setReasonForRequest(List<ServiceRequestReasonForRequestElement> reasonForRequest) {
     this.reasonForRequest = reasonForRequest;
  }

  public List<ServiceRequestReasonForRequestElement> getReasonForRequest() {
     return this.reasonForRequest ;
  }

  public void setReasonForIsolationValue(String reasonForIsolationValue) {
     this.reasonForIsolationValue = reasonForIsolationValue;
  }

  public String getReasonForIsolationValue() {
     return this.reasonForIsolationValue ;
  }

  public void setComplexTiming(List<Cluster> complexTiming) {
     this.complexTiming = complexTiming;
  }

  public List<Cluster> getComplexTiming() {
     return this.complexTiming ;
  }

  public void setDateIsolationDueToStartValue(TemporalAccessor dateIsolationDueToStartValue) {
     this.dateIsolationDueToStartValue = dateIsolationDueToStartValue;
  }

  public TemporalAccessor getDateIsolationDueToStartValue() {
     return this.dateIsolationDueToStartValue ;
  }

  public void setDateIsolationDueToEndValue(TemporalAccessor dateIsolationDueToEndValue) {
     this.dateIsolationDueToEndValue = dateIsolationDueToEndValue;
  }

  public TemporalAccessor getDateIsolationDueToEndValue() {
     return this.dateIsolationDueToEndValue ;
  }

  public void setSpecificDetails(List<Cluster> specificDetails) {
     this.specificDetails = specificDetails;
  }

  public List<Cluster> getSpecificDetails() {
     return this.specificDetails ;
  }

  public void setSupportingInformation(List<Cluster> supportingInformation) {
     this.supportingInformation = supportingInformation;
  }

  public List<Cluster> getSupportingInformation() {
     return this.supportingInformation ;
  }

  public void setPatientRequirements(List<Cluster> patientRequirements) {
     this.patientRequirements = patientRequirements;
  }

  public List<Cluster> getPatientRequirements() {
     return this.patientRequirements ;
  }

  public void setTiming(DvParsable timing) {
     this.timing = timing;
  }

  public DvParsable getTiming() {
     return this.timing ;
  }
}
