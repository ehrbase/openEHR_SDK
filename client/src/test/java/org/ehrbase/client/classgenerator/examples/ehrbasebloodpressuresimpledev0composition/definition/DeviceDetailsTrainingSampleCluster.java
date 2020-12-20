package org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.sample_device.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:10.939495900+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class DeviceDetailsTrainingSampleCluster implements LocatableEntity {
  /**
   * Path: Encounter (training sample)/context/Admin detail/Device details (training sample)/Name
   * Description: The name of the device
   */
  @Path("/items[at0001]/value|value")
  private String nameValue;

  /**
   * Path: Encounter (training sample)/context/Tree/Admin detail/Device details (training
   * sample)/Name/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour nameNullFlavourDefiningCode;

  /**
   * Path: Encounter (training sample)/context/Admin detail/Device details (training
   * sample)/Description Description: Description of the device
   */
  @Path("/items[at0002]/value|value")
  private String descriptionValue;

  /**
   * Path: Encounter (training sample)/context/Tree/Admin detail/Device details (training
   * sample)/Description/null_flavour
   */
  @Path("/items[at0002]/null_flavour|defining_code")
  private NullFlavour descriptionNullFlavourDefiningCode;

  /**
   * Path: Encounter (training sample)/context/Admin detail/Device details (training
   * sample)/Manufacturer details/Manufacturer Description: The name of the manufacturer
   */
  @Path("/items[at0004]/items[at0003]/value|value")
  private String manufacturerValue;

  /**
   * Path: Encounter (training sample)/context/Tree/Admin detail/Device details (training
   * sample)/Manufacturer details/Manufacturer/null_flavour
   */
  @Path("/items[at0004]/items[at0003]/null_flavour|defining_code")
  private NullFlavour manufacturerNullFlavourDefiningCode;

  /**
   * Path: Encounter (training sample)/context/Admin detail/Device details (training
   * sample)/Manufacturer details/Model Description: The model of the device
   */
  @Path("/items[at0004]/items[at0005]/value|value")
  private String modelValue;

  /**
   * Path: Encounter (training sample)/context/Tree/Admin detail/Device details (training
   * sample)/Manufacturer details/Model/null_flavour
   */
  @Path("/items[at0004]/items[at0005]/null_flavour|defining_code")
  private NullFlavour modelNullFlavourDefiningCode;

  /**
   * Path: Encounter (training sample)/context/Admin detail/Device details (training
   * sample)/Manufacturer details/Serial number Description: The serial number of the device
   */
  @Path("/items[at0004]/items[at0006]/value|value")
  private String serialNumberValue;

  /**
   * Path: Encounter (training sample)/context/Tree/Admin detail/Device details (training
   * sample)/Manufacturer details/Serial number/null_flavour
   */
  @Path("/items[at0004]/items[at0006]/null_flavour|defining_code")
  private NullFlavour serialNumberNullFlavourDefiningCode;

  /**
   * Path: Encounter (training sample)/context/Admin detail/Device details (training
   * sample)/Components Description: Information about the device components
   */
  @Path("/items[at0007]")
  private Cluster components;

  /**
   * Path: Encounter (training sample)/context/Admin detail/Device details (training
   * sample)/Servicing/Date last serviced Description: The date the device was last serviced
   */
  @Path("/items[at0008]/items[at0009]/value|value")
  private TemporalAccessor dateLastServicedValue;

  /**
   * Path: Encounter (training sample)/context/Tree/Admin detail/Device details (training
   * sample)/Servicing/Date last serviced/null_flavour
   */
  @Path("/items[at0008]/items[at0009]/null_flavour|defining_code")
  private NullFlavour dateLastServicedNullFlavourDefiningCode;

  /**
   * Path: Encounter (training sample)/context/Admin detail/Device details (training
   * sample)/Servicing/Date last calibration Description: Date the device was last calibrated
   */
  @Path("/items[at0008]/items[at0010]/value|value")
  private TemporalAccessor dateLastCalibrationValue;

  /**
   * Path: Encounter (training sample)/context/Tree/Admin detail/Device details (training
   * sample)/Servicing/Date last calibration/null_flavour
   */
  @Path("/items[at0008]/items[at0010]/null_flavour|defining_code")
  private NullFlavour dateLastCalibrationNullFlavourDefiningCode;

  /**
   * Path: Encounter (training sample)/context/Admin detail/Device details (training
   * sample)/Servicing/Serviced by Description: Agent performed the servicing
   */
  @Path("/items[at0008]/items[at0011]/value|value")
  private String servicedByValue;

  /**
   * Path: Encounter (training sample)/context/Tree/Admin detail/Device details (training
   * sample)/Servicing/Serviced by/null_flavour
   */
  @Path("/items[at0008]/items[at0011]/null_flavour|defining_code")
  private NullFlavour servicedByNullFlavourDefiningCode;

  /**
   * Path: Encounter (training sample)/context/Admin detail/Device details (training
   * sample)/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setNameValue(String nameValue) {
    this.nameValue = nameValue;
  }

  public String getNameValue() {
    return this.nameValue;
  }

  public void setNameNullFlavourDefiningCode(NullFlavour nameNullFlavourDefiningCode) {
    this.nameNullFlavourDefiningCode = nameNullFlavourDefiningCode;
  }

  public NullFlavour getNameNullFlavourDefiningCode() {
    return this.nameNullFlavourDefiningCode;
  }

  public void setDescriptionValue(String descriptionValue) {
    this.descriptionValue = descriptionValue;
  }

  public String getDescriptionValue() {
    return this.descriptionValue;
  }

  public void setDescriptionNullFlavourDefiningCode(
      NullFlavour descriptionNullFlavourDefiningCode) {
    this.descriptionNullFlavourDefiningCode = descriptionNullFlavourDefiningCode;
  }

  public NullFlavour getDescriptionNullFlavourDefiningCode() {
    return this.descriptionNullFlavourDefiningCode;
  }

  public void setManufacturerValue(String manufacturerValue) {
    this.manufacturerValue = manufacturerValue;
  }

  public String getManufacturerValue() {
    return this.manufacturerValue;
  }

  public void setManufacturerNullFlavourDefiningCode(
      NullFlavour manufacturerNullFlavourDefiningCode) {
    this.manufacturerNullFlavourDefiningCode = manufacturerNullFlavourDefiningCode;
  }

  public NullFlavour getManufacturerNullFlavourDefiningCode() {
    return this.manufacturerNullFlavourDefiningCode;
  }

  public void setModelValue(String modelValue) {
    this.modelValue = modelValue;
  }

  public String getModelValue() {
    return this.modelValue;
  }

  public void setModelNullFlavourDefiningCode(NullFlavour modelNullFlavourDefiningCode) {
    this.modelNullFlavourDefiningCode = modelNullFlavourDefiningCode;
  }

  public NullFlavour getModelNullFlavourDefiningCode() {
    return this.modelNullFlavourDefiningCode;
  }

  public void setSerialNumberValue(String serialNumberValue) {
    this.serialNumberValue = serialNumberValue;
  }

  public String getSerialNumberValue() {
    return this.serialNumberValue;
  }

  public void setSerialNumberNullFlavourDefiningCode(
      NullFlavour serialNumberNullFlavourDefiningCode) {
    this.serialNumberNullFlavourDefiningCode = serialNumberNullFlavourDefiningCode;
  }

  public NullFlavour getSerialNumberNullFlavourDefiningCode() {
    return this.serialNumberNullFlavourDefiningCode;
  }

  public void setComponents(Cluster components) {
    this.components = components;
  }

  public Cluster getComponents() {
    return this.components;
  }

  public void setDateLastServicedValue(TemporalAccessor dateLastServicedValue) {
    this.dateLastServicedValue = dateLastServicedValue;
  }

  public TemporalAccessor getDateLastServicedValue() {
    return this.dateLastServicedValue;
  }

  public void setDateLastServicedNullFlavourDefiningCode(
      NullFlavour dateLastServicedNullFlavourDefiningCode) {
    this.dateLastServicedNullFlavourDefiningCode = dateLastServicedNullFlavourDefiningCode;
  }

  public NullFlavour getDateLastServicedNullFlavourDefiningCode() {
    return this.dateLastServicedNullFlavourDefiningCode;
  }

  public void setDateLastCalibrationValue(TemporalAccessor dateLastCalibrationValue) {
    this.dateLastCalibrationValue = dateLastCalibrationValue;
  }

  public TemporalAccessor getDateLastCalibrationValue() {
    return this.dateLastCalibrationValue;
  }

  public void setDateLastCalibrationNullFlavourDefiningCode(
      NullFlavour dateLastCalibrationNullFlavourDefiningCode) {
    this.dateLastCalibrationNullFlavourDefiningCode = dateLastCalibrationNullFlavourDefiningCode;
  }

  public NullFlavour getDateLastCalibrationNullFlavourDefiningCode() {
    return this.dateLastCalibrationNullFlavourDefiningCode;
  }

  public void setServicedByValue(String servicedByValue) {
    this.servicedByValue = servicedByValue;
  }

  public String getServicedByValue() {
    return this.servicedByValue;
  }

  public void setServicedByNullFlavourDefiningCode(NullFlavour servicedByNullFlavourDefiningCode) {
    this.servicedByNullFlavourDefiningCode = servicedByNullFlavourDefiningCode;
  }

  public NullFlavour getServicedByNullFlavourDefiningCode() {
    return this.servicedByNullFlavourDefiningCode;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
