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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.befundderblutgasanalysecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;

public class LaborergebnisObservationContainment extends Containment {
    public SelectAqlField<LaborergebnisObservation> LABORERGEBNIS_OBSERVATION = new AqlFieldImp<
            LaborergebnisObservation>(
            LaborergebnisObservation.class, "", "LaborergebnisObservation", LaborergebnisObservation.class, this);

    public SelectAqlField<LabortestBezeichnungDefiningCode> LABORTEST_BEZEICHNUNG_DEFINING_CODE =
            new AqlFieldImp<LabortestBezeichnungDefiningCode>(
                    LaborergebnisObservation.class,
                    "/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|defining_code",
                    "labortestBezeichnungDefiningCode",
                    LabortestBezeichnungDefiningCode.class,
                    this);

    public ListSelectAqlField<Cluster> PROBENDETAIL = new ListAqlFieldImp<Cluster>(
            LaborergebnisObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0065]",
            "probendetail",
            Cluster.class,
            this);

    public SelectAqlField<KohlendioxidpartialdruckCluster> KOHLENDIOXIDPARTIALDRUCK =
            new AqlFieldImp<KohlendioxidpartialdruckCluster>(
                    LaborergebnisObservation.class,
                    "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1]",
                    "kohlendioxidpartialdruck",
                    KohlendioxidpartialdruckCluster.class,
                    this);

    public SelectAqlField<SauerstoffpartialdruckCluster> SAUERSTOFFPARTIALDRUCK =
            new AqlFieldImp<SauerstoffpartialdruckCluster>(
                    LaborergebnisObservation.class,
                    "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1]",
                    "sauerstoffpartialdruck",
                    SauerstoffpartialdruckCluster.class,
                    this);

    public SelectAqlField<PhWertCluster> PH_WERT = new AqlFieldImp<PhWertCluster>(
            LaborergebnisObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1]",
            "phWert",
            PhWertCluster.class,
            this);

    public SelectAqlField<SauerstoffsattigungCluster> SAUERSTOFFSATTIGUNG = new AqlFieldImp<SauerstoffsattigungCluster>(
            LaborergebnisObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1]",
            "sauerstoffsattigung",
            SauerstoffsattigungCluster.class,
            this);

    public ListSelectAqlField<Cluster> STRUKTURIERTE_TESTDIAGNOSTIK = new ListAqlFieldImp<Cluster>(
            LaborergebnisObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0122]",
            "strukturierteTestdiagnostik",
            Cluster.class,
            this);

    public ListSelectAqlField<Cluster> MULTIMEDIA_DARSTELLUNG = new ListAqlFieldImp<Cluster>(
            LaborergebnisObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0118]",
            "multimediaDarstellung",
            Cluster.class,
            this);

    public ListSelectAqlField<Cluster> STRUKTURIERTE_ERFASSUNG_DER_STORFAKTOREN = new ListAqlFieldImp<Cluster>(
            LaborergebnisObservation.class,
            "/data[at0001]/events[at0002]/state[at0112]/items[at0114]",
            "strukturierteErfassungDerStorfaktoren",
            Cluster.class,
            this);

    public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            LaborergebnisObservation.class,
            "/data[at0001]/events[at0002]/time|value",
            "timeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(
            LaborergebnisObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

    public SelectAqlField<Cluster> LABOR_WELCHES_DEN_UNTERSUCHUNGSAUFTRAG_ANNIMMT = new AqlFieldImp<Cluster>(
            LaborergebnisObservation.class,
            "/protocol[at0004]/items[at0017]",
            "laborWelchesDenUntersuchungsauftragAnnimmt",
            Cluster.class,
            this);

    public ListSelectAqlField<Cluster> TEST_DETAILS = new ListAqlFieldImp<Cluster>(
            LaborergebnisObservation.class, "/protocol[at0004]/items[at0110]", "testDetails", Cluster.class, this);

    public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(
            LaborergebnisObservation.class, "/protocol[at0004]/items[at0117]", "erweiterung", Cluster.class, this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(LaborergebnisObservation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(LaborergebnisObservation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            LaborergebnisObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private LaborergebnisObservationContainment() {
        super("openEHR-EHR-OBSERVATION.laboratory_test_result.v1");
    }

    public static LaborergebnisObservationContainment getInstance() {
        return new LaborergebnisObservationContainment();
    }
}
