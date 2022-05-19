/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.client.classgenerator.examples.errortestcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class SpecimenClusterContainment extends Containment {
    public SelectAqlField<SpecimenCluster> SPECIMEN_CLUSTER =
            new AqlFieldImp<SpecimenCluster>(SpecimenCluster.class, "", "SpecimenCluster", SpecimenCluster.class, this);

    public SelectAqlField<TemporalAccessor> DATE_TIME_RECEIVED_VALUE = new AqlFieldImp<TemporalAccessor>(
            SpecimenCluster.class, "/items[at0034]/value|value", "dateTimeReceivedValue", TemporalAccessor.class, this);

    public ListSelectAqlField<Cluster> PHYSICAL_PROPERTIES = new ListAqlFieldImp<Cluster>(
            SpecimenCluster.class, "/items[at0027]", "physicalProperties", Cluster.class, this);

    public ListSelectAqlField<Cluster> STRUCTURED_SOURCE_SITE = new ListAqlFieldImp<Cluster>(
            SpecimenCluster.class, "/items[at0013]", "structuredSourceSite", Cluster.class, this);

    public ListSelectAqlField<Cluster> SPECIMEN_COLLECTOR_DETAILS = new ListAqlFieldImp<Cluster>(
            SpecimenCluster.class, "/items[at0071]", "specimenCollectorDetails", Cluster.class, this);

    public ListSelectAqlField<Cluster> ADDITIONAL_COLLECTION_DETAILS = new ListAqlFieldImp<Cluster>(
            SpecimenCluster.class, "/items[at0083]", "additionalCollectionDetails", Cluster.class, this);

    public ListSelectAqlField<Cluster> CONTAINER_DETAILS = new ListAqlFieldImp<Cluster>(
            SpecimenCluster.class, "/items[at0085]", "containerDetails", Cluster.class, this);

    public ListSelectAqlField<Cluster> PROCESSING_DETAILS = new ListAqlFieldImp<Cluster>(
            SpecimenCluster.class, "/items[at0068]", "processingDetails", Cluster.class, this);

    public ListSelectAqlField<Cluster> TRANSPORT_DETAILS = new ListAqlFieldImp<Cluster>(
            SpecimenCluster.class, "/items[at0093]", "transportDetails", Cluster.class, this);

    public ListSelectAqlField<Cluster> DIGITAL_REPRESENTATION = new ListAqlFieldImp<Cluster>(
            SpecimenCluster.class, "/items[at0096]", "digitalRepresentation", Cluster.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            SpecimenCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    public SelectAqlField<SpecimenCollectionDateTimeChoice> COLLECTION_DATE_TIME =
            new AqlFieldImp<SpecimenCollectionDateTimeChoice>(
                    SpecimenCluster.class,
                    "/items[at0015]/value",
                    "collectionDateTime",
                    SpecimenCollectionDateTimeChoice.class,
                    this);

    private SpecimenClusterContainment() {
        super("openEHR-EHR-CLUSTER.specimen.v1");
    }

    public static SpecimenClusterContainment getInstance() {
        return new SpecimenClusterContainment();
    }
}
