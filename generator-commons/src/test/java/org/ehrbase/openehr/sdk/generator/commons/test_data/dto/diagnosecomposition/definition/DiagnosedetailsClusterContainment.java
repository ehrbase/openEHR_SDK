/*
 * Copyright (c) 2022 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.diagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;

public class DiagnosedetailsClusterContainment extends Containment {
    public SelectAqlField<DiagnosedetailsCluster> DIAGNOSEDETAILS_CLUSTER = new AqlFieldImp<DiagnosedetailsCluster>(
            DiagnosedetailsCluster.class, "", "DiagnosedetailsCluster", DiagnosedetailsCluster.class, this);

    public SelectAqlField<String> BEGRUNDUNG_VON_AUSNAHMEN_VALUE = new AqlFieldImp<String>(
            DiagnosedetailsCluster.class,
            "/items[at0001]/value|value",
            "begrundungVonAusnahmenValue",
            String.class,
            this);

    public SelectAqlField<String> DIAGNOSETYP_VALUE = new AqlFieldImp<String>(
            DiagnosedetailsCluster.class, "/items[at0002]/value|value", "diagnosetypValue", String.class, this);

    public SelectAqlField<Boolean> VORHANDEN_BEI_AUFNAHME_VALUE = new AqlFieldImp<Boolean>(
            DiagnosedetailsCluster.class,
            "/items[at0016]/value|value",
            "vorhandenBeiAufnahmeValue",
            Boolean.class,
            this);

    public SelectAqlField<Boolean> VORHANDEN_BEI_ENTLASSUNG_VALUE = new AqlFieldImp<Boolean>(
            DiagnosedetailsCluster.class,
            "/items[at0017]/value|value",
            "vorhandenBeiEntlassungValue",
            Boolean.class,
            this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            DiagnosedetailsCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private DiagnosedetailsClusterContainment() {
        super("openEHR-EHR-CLUSTER.diagnose_details.v0");
    }

    public static DiagnosedetailsClusterContainment getInstance() {
        return new DiagnosedetailsClusterContainment();
    }
}
