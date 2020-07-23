package org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-CLUSTER.sample_device.v1")
public class DeviceDetailsTrainingSampleCluster {
    @Path("/items[at0004]/items[at0005]/value|value")
    private String modelValue;

    @Path("/items[at0004]/items[at0006]/value|value")
    private String serialNumberValue;

    @Path("/items[at0008]/items[at0009]/value|value")
    private TemporalAccessor dateLastServicedValue;

    @Path("/items[at0008]/items[at0010]/value|value")
    private TemporalAccessor dateLastCalibrationValue;

    @Path("/items[at0001]/value|value")
    private String nameValue;

    @Path("/items[at0002]/value|value")
    private String descriptionValue;

    @Path("/items[at0007]/items")
    private List<Cluster> components;

    @Path("/items[at0008]/items[at0011]/value|value")
    private String servicedByValue;

    @Path("/items[at0004]/items[at0003]/value|value")
    private String manufacturerValue;

    public void setModelValue(String modelValue) {
        this.modelValue = modelValue;
    }

    public String getModelValue() {
        return this.modelValue;
    }

    public void setSerialNumberValue(String serialNumberValue) {
        this.serialNumberValue = serialNumberValue;
    }

    public String getSerialNumberValue() {
        return this.serialNumberValue;
    }

    public void setDateLastServicedValue(TemporalAccessor dateLastServicedValue) {
        this.dateLastServicedValue = dateLastServicedValue;
    }

    public TemporalAccessor getDateLastServicedValue() {
        return this.dateLastServicedValue;
    }

    public void setDateLastCalibrationValue(TemporalAccessor dateLastCalibrationValue) {
        this.dateLastCalibrationValue = dateLastCalibrationValue;
    }

    public TemporalAccessor getDateLastCalibrationValue() {
        return this.dateLastCalibrationValue;
    }

    public void setNameValue(String nameValue) {
        this.nameValue = nameValue;
    }

    public String getNameValue() {
        return this.nameValue;
    }

    public void setDescriptionValue(String descriptionValue) {
        this.descriptionValue = descriptionValue;
    }

    public String getDescriptionValue() {
        return this.descriptionValue;
    }

    public void setComponents(List<Cluster> components) {
        this.components = components;
    }

    public List<Cluster> getComponents() {
        return this.components;
    }

    public void setServicedByValue(String servicedByValue) {
        this.servicedByValue = servicedByValue;
    }

    public String getServicedByValue() {
        return this.servicedByValue;
    }

    public void setManufacturerValue(String manufacturerValue) {
        this.manufacturerValue = manufacturerValue;
    }

    public String getManufacturerValue() {
        return this.manufacturerValue;
    }
}
