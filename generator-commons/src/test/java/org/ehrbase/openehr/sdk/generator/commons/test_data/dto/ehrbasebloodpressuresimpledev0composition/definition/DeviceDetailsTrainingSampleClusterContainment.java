/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasebloodpressuresimpledev0composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.NullFlavour;

public class DeviceDetailsTrainingSampleClusterContainment extends Containment {
    public SelectAqlField<DeviceDetailsTrainingSampleCluster> DEVICE_DETAILS_TRAINING_SAMPLE_CLUSTER =
            new AqlFieldImp<DeviceDetailsTrainingSampleCluster>(
                    DeviceDetailsTrainingSampleCluster.class,
                    "",
                    "DeviceDetailsTrainingSampleCluster",
                    DeviceDetailsTrainingSampleCluster.class,
                    this);

    public SelectAqlField<String> NAME_VALUE = new AqlFieldImp<String>(
            DeviceDetailsTrainingSampleCluster.class, "/items[at0001]/value|value", "nameValue", String.class, this);

    public SelectAqlField<NullFlavour> NAME_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(
            DeviceDetailsTrainingSampleCluster.class,
            "/items[at0001]/null_flavour|defining_code",
            "nameNullFlavourDefiningCode",
            NullFlavour.class,
            this);

    public SelectAqlField<String> DESCRIPTION_VALUE = new AqlFieldImp<String>(
            DeviceDetailsTrainingSampleCluster.class,
            "/items[at0002]/value|value",
            "descriptionValue",
            String.class,
            this);

    public SelectAqlField<NullFlavour> DESCRIPTION_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(
            DeviceDetailsTrainingSampleCluster.class,
            "/items[at0002]/null_flavour|defining_code",
            "descriptionNullFlavourDefiningCode",
            NullFlavour.class,
            this);

    public SelectAqlField<String> MANUFACTURER_VALUE = new AqlFieldImp<String>(
            DeviceDetailsTrainingSampleCluster.class,
            "/items[at0004]/items[at0003]/value|value",
            "manufacturerValue",
            String.class,
            this);

    public SelectAqlField<NullFlavour> MANUFACTURER_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(
            DeviceDetailsTrainingSampleCluster.class,
            "/items[at0004]/items[at0003]/null_flavour|defining_code",
            "manufacturerNullFlavourDefiningCode",
            NullFlavour.class,
            this);

    public SelectAqlField<String> MODEL_VALUE = new AqlFieldImp<String>(
            DeviceDetailsTrainingSampleCluster.class,
            "/items[at0004]/items[at0005]/value|value",
            "modelValue",
            String.class,
            this);

    public SelectAqlField<NullFlavour> MODEL_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(
            DeviceDetailsTrainingSampleCluster.class,
            "/items[at0004]/items[at0005]/null_flavour|defining_code",
            "modelNullFlavourDefiningCode",
            NullFlavour.class,
            this);

    public SelectAqlField<String> SERIAL_NUMBER_VALUE = new AqlFieldImp<String>(
            DeviceDetailsTrainingSampleCluster.class,
            "/items[at0004]/items[at0006]/value|value",
            "serialNumberValue",
            String.class,
            this);

    public SelectAqlField<NullFlavour> SERIAL_NUMBER_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(
            DeviceDetailsTrainingSampleCluster.class,
            "/items[at0004]/items[at0006]/null_flavour|defining_code",
            "serialNumberNullFlavourDefiningCode",
            NullFlavour.class,
            this);

    public SelectAqlField<Cluster> COMPONENTS = new AqlFieldImp<Cluster>(
            DeviceDetailsTrainingSampleCluster.class, "/items[at0007]", "components", Cluster.class, this);

    public SelectAqlField<TemporalAccessor> DATE_LAST_SERVICED_VALUE = new AqlFieldImp<TemporalAccessor>(
            DeviceDetailsTrainingSampleCluster.class,
            "/items[at0008]/items[at0009]/value|value",
            "dateLastServicedValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<NullFlavour> DATE_LAST_SERVICED_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(
            DeviceDetailsTrainingSampleCluster.class,
            "/items[at0008]/items[at0009]/null_flavour|defining_code",
            "dateLastServicedNullFlavourDefiningCode",
            NullFlavour.class,
            this);

    public SelectAqlField<TemporalAccessor> DATE_LAST_CALIBRATION_VALUE = new AqlFieldImp<TemporalAccessor>(
            DeviceDetailsTrainingSampleCluster.class,
            "/items[at0008]/items[at0010]/value|value",
            "dateLastCalibrationValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<NullFlavour> DATE_LAST_CALIBRATION_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(
            DeviceDetailsTrainingSampleCluster.class,
            "/items[at0008]/items[at0010]/null_flavour|defining_code",
            "dateLastCalibrationNullFlavourDefiningCode",
            NullFlavour.class,
            this);

    public SelectAqlField<String> SERVICED_BY_VALUE = new AqlFieldImp<String>(
            DeviceDetailsTrainingSampleCluster.class,
            "/items[at0008]/items[at0011]/value|value",
            "servicedByValue",
            String.class,
            this);

    public SelectAqlField<NullFlavour> SERVICED_BY_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(
            DeviceDetailsTrainingSampleCluster.class,
            "/items[at0008]/items[at0011]/null_flavour|defining_code",
            "servicedByNullFlavourDefiningCode",
            NullFlavour.class,
            this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            DeviceDetailsTrainingSampleCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private DeviceDetailsTrainingSampleClusterContainment() {
        super("openEHR-EHR-CLUSTER.sample_device.v1");
    }

    public static DeviceDetailsTrainingSampleClusterContainment getInstance() {
        return new DeviceDetailsTrainingSampleClusterContainment();
    }
}
