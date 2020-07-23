package org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

import java.time.temporal.TemporalAccessor;

public class DeviceDetailsTrainingSampleClusterContainment extends Containment {
  public SelectAqlField<DeviceDetailsTrainingSampleCluster> DEVICE_DETAILS_TRAINING_SAMPLE_CLUSTER = new AqlFieldImp<DeviceDetailsTrainingSampleCluster>(DeviceDetailsTrainingSampleCluster.class, "", "DeviceDetailsTrainingSampleCluster", DeviceDetailsTrainingSampleCluster.class, this);

  public SelectAqlField<String> MODEL_VALUE = new AqlFieldImp<String>(DeviceDetailsTrainingSampleCluster.class, "/items[at0004]/items[at0005]/value|value", "modelValue", String.class, this);

  public SelectAqlField<String> SERIAL_NUMBER_VALUE = new AqlFieldImp<String>(DeviceDetailsTrainingSampleCluster.class, "/items[at0004]/items[at0006]/value|value", "serialNumberValue", String.class, this);

  public SelectAqlField<TemporalAccessor> DATE_LAST_SERVICED_VALUE = new AqlFieldImp<TemporalAccessor>(DeviceDetailsTrainingSampleCluster.class, "/items[at0008]/items[at0009]/value|value", "dateLastServicedValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAccessor> DATE_LAST_CALIBRATION_VALUE = new AqlFieldImp<TemporalAccessor>(DeviceDetailsTrainingSampleCluster.class, "/items[at0008]/items[at0010]/value|value", "dateLastCalibrationValue", TemporalAccessor.class, this);

  public SelectAqlField<String> NAME_VALUE = new AqlFieldImp<String>(DeviceDetailsTrainingSampleCluster.class, "/items[at0001]/value|value", "nameValue", String.class, this);

  public SelectAqlField<String> DESCRIPTION_VALUE = new AqlFieldImp<String>(DeviceDetailsTrainingSampleCluster.class, "/items[at0002]/value|value", "descriptionValue", String.class, this);

  public ListSelectAqlField<Cluster> COMPONENTS = new ListAqlFieldImp<Cluster>(DeviceDetailsTrainingSampleCluster.class, "/items[at0007]/items", "components", Cluster.class, this);

  public SelectAqlField<String> SERVICED_BY_VALUE = new AqlFieldImp<String>(DeviceDetailsTrainingSampleCluster.class, "/items[at0008]/items[at0011]/value|value", "servicedByValue", String.class, this);

  public SelectAqlField<String> MANUFACTURER_VALUE = new AqlFieldImp<String>(DeviceDetailsTrainingSampleCluster.class, "/items[at0004]/items[at0003]/value|value", "manufacturerValue", String.class, this);

  private DeviceDetailsTrainingSampleClusterContainment() {
    super("openEHR-EHR-CLUSTER.sample_device.v1");
  }

  public static DeviceDetailsTrainingSampleClusterContainment getInstance() {
    return new DeviceDetailsTrainingSampleClusterContainment();
  }
}
