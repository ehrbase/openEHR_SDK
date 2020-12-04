package org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.sample_device.v1")
public class DeviceDetailsTrainingSampleCluster {
  /**
   * Encounter (training sample)/context/Admin detail/Device details (training sample)/Name
   */
  @Path("/items[at0001]/value|value")
  private String nameValue;

  /**
   * Encounter (training sample)/context/Admin detail/Device details (training sample)/Description
   */
  @Path("/items[at0002]/value|value")
  private String descriptionValue;

  /**
   * Encounter (training sample)/context/Admin detail/Device details (training sample)/Manufacturer details/Manufacturer
   */
  @Path("/items[at0004]/items[at0003]/value|value")
  private String manufacturerValue;

  /**
   * Encounter (training sample)/context/Admin detail/Device details (training sample)/Manufacturer details/Model
   */
  @Path("/items[at0004]/items[at0005]/value|value")
  private String modelValue;

  /**
   * Encounter (training sample)/context/Admin detail/Device details (training sample)/Manufacturer details/Serial number
   */
  @Path("/items[at0004]/items[at0006]/value|value")
  private String serialNumberValue;

  /**
   * Encounter (training sample)/context/Admin detail/Device details (training sample)/Components
   */
  @Path("/items[at0007]")
  private Cluster components;

  /**
   * Encounter (training sample)/context/Admin detail/Device details (training sample)/Servicing/Date last serviced
   */
  @Path("/items[at0008]/items[at0009]/value|value")
  private TemporalAccessor dateLastServicedValue;

  /**
   * Encounter (training sample)/context/Admin detail/Device details (training sample)/Servicing/Date last calibration
   */
  @Path("/items[at0008]/items[at0010]/value|value")
  private TemporalAccessor dateLastCalibrationValue;

  /**
   * Encounter (training sample)/context/Admin detail/Device details (training sample)/Servicing/Serviced by
   */
  @Path("/items[at0008]/items[at0011]/value|value")
  private String servicedByValue;

  public void setNameValue(String nameValue) {
     this.nameValue = nameValue;
  }

  public String getNameValue() {
     return this.nameValue ;
  }

  public void setDescriptionValue(String descriptionValue) {
     this.descriptionValue = descriptionValue;
  }

  public String getDescriptionValue() {
     return this.descriptionValue ;
  }

  public void setManufacturerValue(String manufacturerValue) {
     this.manufacturerValue = manufacturerValue;
  }

  public String getManufacturerValue() {
     return this.manufacturerValue ;
  }

  public void setModelValue(String modelValue) {
     this.modelValue = modelValue;
  }

  public String getModelValue() {
     return this.modelValue ;
  }

  public void setSerialNumberValue(String serialNumberValue) {
     this.serialNumberValue = serialNumberValue;
  }

  public String getSerialNumberValue() {
     return this.serialNumberValue ;
  }

  public void setComponents(Cluster components) {
     this.components = components;
  }

  public Cluster getComponents() {
     return this.components ;
  }

  public void setDateLastServicedValue(TemporalAccessor dateLastServicedValue) {
     this.dateLastServicedValue = dateLastServicedValue;
  }

  public TemporalAccessor getDateLastServicedValue() {
     return this.dateLastServicedValue ;
  }

  public void setDateLastCalibrationValue(TemporalAccessor dateLastCalibrationValue) {
     this.dateLastCalibrationValue = dateLastCalibrationValue;
  }

  public TemporalAccessor getDateLastCalibrationValue() {
     return this.dateLastCalibrationValue ;
  }

  public void setServicedByValue(String servicedByValue) {
     this.servicedByValue = servicedByValue;
  }

  public String getServicedByValue() {
     return this.servicedByValue ;
  }
}
