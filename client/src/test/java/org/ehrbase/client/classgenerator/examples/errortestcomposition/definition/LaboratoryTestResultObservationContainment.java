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
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class LaboratoryTestResultObservationContainment extends Containment {
    public SelectAqlField<LaboratoryTestResultObservation> LABORATORY_TEST_RESULT_OBSERVATION =
            new AqlFieldImp<LaboratoryTestResultObservation>(
                    LaboratoryTestResultObservation.class,
                    "",
                    "LaboratoryTestResultObservation",
                    LaboratoryTestResultObservation.class,
                    this);

    public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(
            LaboratoryTestResultObservation.class,
            "/data[at0001]/origin|value",
            "originValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<Cluster> RECEIVING_LABORATORY = new AqlFieldImp<Cluster>(
            LaboratoryTestResultObservation.class,
            "/protocol[at0004]/items[at0017]",
            "receivingLaboratory",
            Cluster.class,
            this);

    public ListSelectAqlField<Cluster> TESTING_DETAILS = new ListAqlFieldImp<Cluster>(
            LaboratoryTestResultObservation.class,
            "/protocol[at0004]/items[at0110]",
            "testingDetails",
            Cluster.class,
            this);

    public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(
            LaboratoryTestResultObservation.class, "/protocol[at0004]/items[at0117]", "extension", Cluster.class, this);

    public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(
            LaboratoryTestResultObservation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(
            LaboratoryTestResultObservation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            LaboratoryTestResultObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    public ListSelectAqlField<LaboratoryTestResultAnyEventChoice> ANY_EVENT =
            new ListAqlFieldImp<LaboratoryTestResultAnyEventChoice>(
                    LaboratoryTestResultObservation.class,
                    "/data[at0001]/events[at0002]",
                    "anyEvent",
                    LaboratoryTestResultAnyEventChoice.class,
                    this);

    private LaboratoryTestResultObservationContainment() {
        super("openEHR-EHR-OBSERVATION.laboratory_test_result.v1");
    }

    public static LaboratoryTestResultObservationContainment getInstance() {
        return new LaboratoryTestResultObservationContainment();
    }
}
