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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.laborbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.NullFlavour;

public class LaboranalytResultatClusterContainment extends Containment {
    public SelectAqlField<LaboranalytResultatCluster> LABORANALYT_RESULTAT_CLUSTER = new AqlFieldImp<
            LaboranalytResultatCluster>(
            LaboranalytResultatCluster.class, "", "LaboranalytResultatCluster", LaboranalytResultatCluster.class, this);

    public SelectAqlField<String> BEZEICHNUNG_VALUE = new AqlFieldImp<String>(
            LaboranalytResultatCluster.class, "/items[at0024]/value|value", "bezeichnungValue", String.class, this);

    public SelectAqlField<NullFlavour> BEZEICHNUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(
            LaboranalytResultatCluster.class,
            "/items[at0024]/null_flavour|defining_code",
            "bezeichnungNullFlavourDefiningCode",
            NullFlavour.class,
            this);

    public SelectAqlField<NullFlavour> MESSWERT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(
            LaboranalytResultatCluster.class,
            "/items[at0001]/null_flavour|defining_code",
            "messwertNullFlavourDefiningCode",
            NullFlavour.class,
            this);

    public ListSelectAqlField<Cluster> ANALYSEERGEBNIS_DETAILS = new ListAqlFieldImp<Cluster>(
            LaboranalytResultatCluster.class, "/items[at0014]", "analyseergebnisDetails", Cluster.class, this);

    public SelectAqlField<String> REFERENZBEREICH_TYP_VALUE = new AqlFieldImp<String>(
            LaboranalytResultatCluster.class,
            "/items[at0004]/value|value",
            "referenzbereichTypValue",
            String.class,
            this);

    public SelectAqlField<NullFlavour> REFERENZBEREICH_TYP_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(
            LaboranalytResultatCluster.class,
            "/items[at0004]/null_flavour|defining_code",
            "referenzbereichTypNullFlavourDefiningCode",
            NullFlavour.class,
            this);

    public SelectAqlField<TemporalAccessor> ZEITPUNKT_VALIDATION_VALUE = new AqlFieldImp<TemporalAccessor>(
            LaboranalytResultatCluster.class,
            "/items[at0025]/value|value",
            "zeitpunktValidationValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<NullFlavour> ZEITPUNKT_VALIDATION_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(
            LaboranalytResultatCluster.class,
            "/items[at0025]/null_flavour|defining_code",
            "zeitpunktValidationNullFlavourDefiningCode",
            NullFlavour.class,
            this);

    public SelectAqlField<NullFlavour> ERGEBNIS_STATUS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(
            LaboranalytResultatCluster.class,
            "/items[at0005]/null_flavour|defining_code",
            "ergebnisStatusNullFlavourDefiningCode",
            NullFlavour.class,
            this);

    public SelectAqlField<TemporalAccessor> DOKUMENTATIONSDATUM_UNTERSUCHUNG_VALUE = new AqlFieldImp<TemporalAccessor>(
            LaboranalytResultatCluster.class,
            "/items[at0006]/value|value",
            "dokumentationsdatumUntersuchungValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<NullFlavour> DOKUMENTATIONSDATUM_UNTERSUCHUNG_NULL_FLAVOUR_DEFINING_CODE =
            new AqlFieldImp<NullFlavour>(
                    LaboranalytResultatCluster.class,
                    "/items[at0006]/null_flavour|defining_code",
                    "dokumentationsdatumUntersuchungNullFlavourDefiningCode",
                    NullFlavour.class,
                    this);

    public SelectAqlField<NullFlavour> PROBE_ID_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(
            LaboranalytResultatCluster.class,
            "/items[at0026]/null_flavour|defining_code",
            "probeIdNullFlavourDefiningCode",
            NullFlavour.class,
            this);

    public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(
            LaboranalytResultatCluster.class, "/items[at0003]/value|value", "kommentarValue", String.class, this);

    public SelectAqlField<NullFlavour> KOMMENTAR_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(
            LaboranalytResultatCluster.class,
            "/items[at0003]/null_flavour|defining_code",
            "kommentarNullFlavourDefiningCode",
            NullFlavour.class,
            this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            LaboranalytResultatCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    public SelectAqlField<LaboranalytResultatMesswertChoice> MESSWERT =
            new AqlFieldImp<LaboranalytResultatMesswertChoice>(
                    LaboranalytResultatCluster.class,
                    "/items[at0001]/value",
                    "messwert",
                    LaboranalytResultatMesswertChoice.class,
                    this);

    public SelectAqlField<LaboranalytResultatProbeIdChoice> PROBE_ID =
            new AqlFieldImp<LaboranalytResultatProbeIdChoice>(
                    LaboranalytResultatCluster.class,
                    "/items[at0026]/value",
                    "probeId",
                    LaboranalytResultatProbeIdChoice.class,
                    this);

    public SelectAqlField<LaboranalytResultatErgebnisStatusChoice> ERGEBNIS_STATUS =
            new AqlFieldImp<LaboranalytResultatErgebnisStatusChoice>(
                    LaboranalytResultatCluster.class,
                    "/items[at0005]/value",
                    "ergebnisStatus",
                    LaboranalytResultatErgebnisStatusChoice.class,
                    this);

    private LaboranalytResultatClusterContainment() {
        super("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1");
    }

    public static LaboranalytResultatClusterContainment getInstance() {
        return new LaboranalytResultatClusterContainment();
    }
}
