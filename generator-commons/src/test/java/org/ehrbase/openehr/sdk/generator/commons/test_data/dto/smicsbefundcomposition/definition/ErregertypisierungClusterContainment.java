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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;

public class ErregertypisierungClusterContainment extends Containment {
    public SelectAqlField<ErregertypisierungCluster> ERREGERTYPISIERUNG_CLUSTER = new AqlFieldImp<
            ErregertypisierungCluster>(
            ErregertypisierungCluster.class, "", "ErregertypisierungCluster", ErregertypisierungCluster.class, this);

    public ListSelectAqlField<ErregertypisierungArtDerTypisierungElement> ART_DER_TYPISIERUNG =
            new ListAqlFieldImp<ErregertypisierungArtDerTypisierungElement>(
                    ErregertypisierungCluster.class,
                    "/items[at0001]",
                    "artDerTypisierung",
                    ErregertypisierungArtDerTypisierungElement.class,
                    this);

    public ListSelectAqlField<ErregertypisierungErgebnisElement> ERGEBNIS =
            new ListAqlFieldImp<ErregertypisierungErgebnisElement>(
                    ErregertypisierungCluster.class,
                    "/items[at0008]",
                    "ergebnis",
                    ErregertypisierungErgebnisElement.class,
                    this);

    public SelectAqlField<String> BEWERTUNG_VALUE = new AqlFieldImp<String>(
            ErregertypisierungCluster.class, "/items[at0009]/value|value", "bewertungValue", String.class, this);

    public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(
            ErregertypisierungCluster.class, "/items[at0002]/value|value", "kommentarValue", String.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            ErregertypisierungCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private ErregertypisierungClusterContainment() {
        super("openEHR-EHR-CLUSTER.molekulare_typisierung.v0");
    }

    public static ErregertypisierungClusterContainment getInstance() {
        return new ErregertypisierungClusterContainment();
    }
}
