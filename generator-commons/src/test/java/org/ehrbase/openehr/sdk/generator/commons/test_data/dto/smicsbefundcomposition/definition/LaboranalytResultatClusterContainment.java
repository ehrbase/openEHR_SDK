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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;

public class LaboranalytResultatClusterContainment extends Containment {
    public SelectAqlField<LaboranalytResultatCluster> LABORANALYT_RESULTAT_CLUSTER = new AqlFieldImp<
            LaboranalytResultatCluster>(
            LaboranalytResultatCluster.class, "", "LaboranalytResultatCluster", LaboranalytResultatCluster.class, this);

    public SelectAqlField<AntibiotikumDefiningCode> ANTIBIOTIKUM_DEFINING_CODE =
            new AqlFieldImp<AntibiotikumDefiningCode>(
                    LaboranalytResultatCluster.class,
                    "/items[at0024]/value|defining_code",
                    "antibiotikumDefiningCode",
                    AntibiotikumDefiningCode.class,
                    this);

    public SelectAqlField<Double> MINIMALE_HEMMKONZENTRATION_MAGNITUDE = new AqlFieldImp<Double>(
            LaboranalytResultatCluster.class,
            "/items[at0001]/value|magnitude",
            "minimaleHemmkonzentrationMagnitude",
            Double.class,
            this);

    public SelectAqlField<String> MINIMALE_HEMMKONZENTRATION_UNITS = new AqlFieldImp<String>(
            LaboranalytResultatCluster.class,
            "/items[at0001]/value|units",
            "minimaleHemmkonzentrationUnits",
            String.class,
            this);

    public ListSelectAqlField<Cluster> ANALYSEERGEBNIS_DETAILS = new ListAqlFieldImp<Cluster>(
            LaboranalytResultatCluster.class, "/items[at0014]", "analyseergebnisDetails", Cluster.class, this);

    public SelectAqlField<String> RESISTENZ_VALUE = new AqlFieldImp<String>(
            LaboranalytResultatCluster.class, "/items[at0004]/value|value", "resistenzValue", String.class, this);

    public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(
            LaboranalytResultatCluster.class, "/items[at0003]/value|value", "kommentarValue", String.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            LaboranalytResultatCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private LaboranalytResultatClusterContainment() {
        super("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1");
    }

    public static LaboranalytResultatClusterContainment getInstance() {
        return new LaboranalytResultatClusterContainment();
    }
}
