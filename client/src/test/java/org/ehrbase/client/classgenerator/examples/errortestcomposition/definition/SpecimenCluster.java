package org.ehrbase.client.classgenerator.examples.errortestcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

import javax.annotation.processing.Generated;
import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-CLUSTER.specimen.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-03-02T14:11:00.837598200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class SpecimenCluster implements LocatableEntity {
  /**
   * Path: ErrorTest/Laboratory test result/Any event/Specimen/Date/time received Description: The
   * date and time that the sample was received at the laboratory.
   */
  @Path("/items[at0034]/value|value")
  private TemporalAccessor dateTimeReceivedValue;

  /**
   * Path: ErrorTest/Laboratory test result/Any event/Specimen/Physical properties Description:
   * Physical dimensions, mass or non-measurable properties of the specimen. Comment: For example:
   * Volume, mass, circumference, colour, smell, turbidity. This element can be used to specify the
   * properties of the specimen to be collected, in the context of an INSTRUCTION archetype, or the
   * properties of the specimen which was collected, in the context of an ACTION or OBSERVATION
   * archetype. For example, an INSTRUCTION may request the collection of 20 ml of blood, while the
   * corresponding ACTION records that only 15 ml was collected.
   */
  @Path("/items[at0027]")
  private List<Cluster> physicalProperties;

  /**
   * Path: ErrorTest/Laboratory test result/Any event/Specimen/Structured source site Description: A
   * structured description of the area of the body from where the specimen is collected. Comment:
   * Utilise the more detailed archetypes to describe structured or more complex anatomical sites,
   * or to support recording the source site at run-time by the application. If the body site has
   * been fully identified in the 'Source site' data element, this SLOT becomes redundant.
   */
  @Path("/items[at0013]")
  private List<Cluster> structuredSourceSite;

  /**
   * Path: ErrorTest/Laboratory test result/Any event/Specimen/Specimen collector details
   * Description: The person or organisation responsible for collecting the specimen.
   */
  @Path("/items[at0071]")
  private List<Cluster> specimenCollectorDetails;

  /**
   * Path: ErrorTest/Laboratory test result/Any event/Specimen/Additional collection details
   * Description: Addtional details related to specific collection methods. Comment: For example
   * details about needle biopsies in prostate cancer, where both the request and reporting about
   * the specimen are detailed and specific.
   */
  @Path("/items[at0083]")
  private List<Cluster> additionalCollectionDetails;

  /**
   * Path: ErrorTest/Laboratory test result/Any event/Specimen/Container details Description:
   * Details about containers used.
   */
  @Path("/items[at0085]")
  private List<Cluster> containerDetails;

  /**
   * Path: ErrorTest/Laboratory test result/Any event/Specimen/Processing details Description:
   * Structured details about preparation or processing of the specimen. Comment: For example:
   * Staining or fixation.
   */
  @Path("/items[at0068]")
  private List<Cluster> processingDetails;

  /**
   * Path: ErrorTest/Laboratory test result/Any event/Specimen/Transport details Description:
   * Structured details about transport of the specimen.
   */
  @Path("/items[at0093]")
  private List<Cluster> transportDetails;

  /**
   * Path: ErrorTest/Laboratory test result/Any event/Specimen/Digital representation Description:
   * Structured details about a digital representation of the specimen. Comment: For example the
   * scanned image of a histopathology slide.
   */
  @Path("/items[at0096]")
  private List<Cluster> digitalRepresentation;

  /** Path: ErrorTest/Laboratory test result/Any event/Specimen/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: ErrorTest/Laboratory test result/Any event/Specimen/Collection date/time Description: The
   * date and time that collection has been ordered to take place or has taken place.
   */
  @Path("/items[at0015]/value")
  @Choice
  private SpecimenCollectionDateTimeChoice collectionDateTime;

  public void setDateTimeReceivedValue(TemporalAccessor dateTimeReceivedValue) {
    this.dateTimeReceivedValue = dateTimeReceivedValue;
  }

  public TemporalAccessor getDateTimeReceivedValue() {
    return this.dateTimeReceivedValue;
  }

  public void setPhysicalProperties(List<Cluster> physicalProperties) {
    this.physicalProperties = physicalProperties;
  }

  public List<Cluster> getPhysicalProperties() {
    return this.physicalProperties;
  }

  public void setStructuredSourceSite(List<Cluster> structuredSourceSite) {
    this.structuredSourceSite = structuredSourceSite;
  }

  public List<Cluster> getStructuredSourceSite() {
    return this.structuredSourceSite;
  }

  public void setSpecimenCollectorDetails(List<Cluster> specimenCollectorDetails) {
    this.specimenCollectorDetails = specimenCollectorDetails;
  }

  public List<Cluster> getSpecimenCollectorDetails() {
    return this.specimenCollectorDetails;
  }

  public void setAdditionalCollectionDetails(List<Cluster> additionalCollectionDetails) {
    this.additionalCollectionDetails = additionalCollectionDetails;
  }

  public List<Cluster> getAdditionalCollectionDetails() {
    return this.additionalCollectionDetails;
  }

  public void setContainerDetails(List<Cluster> containerDetails) {
    this.containerDetails = containerDetails;
  }

  public List<Cluster> getContainerDetails() {
    return this.containerDetails;
  }

  public void setProcessingDetails(List<Cluster> processingDetails) {
    this.processingDetails = processingDetails;
  }

  public List<Cluster> getProcessingDetails() {
    return this.processingDetails;
  }

  public void setTransportDetails(List<Cluster> transportDetails) {
    this.transportDetails = transportDetails;
  }

  public List<Cluster> getTransportDetails() {
    return this.transportDetails;
  }

  public void setDigitalRepresentation(List<Cluster> digitalRepresentation) {
    this.digitalRepresentation = digitalRepresentation;
  }

  public List<Cluster> getDigitalRepresentation() {
    return this.digitalRepresentation;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }

  public void setCollectionDateTime(SpecimenCollectionDateTimeChoice collectionDateTime) {
    this.collectionDateTime = collectionDateTime;
  }

  public SpecimenCollectionDateTimeChoice getCollectionDateTime() {
    return this.collectionDateTime;
  }
}
