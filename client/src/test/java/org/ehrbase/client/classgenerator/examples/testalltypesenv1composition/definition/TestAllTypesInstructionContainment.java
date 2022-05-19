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
package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class TestAllTypesInstructionContainment extends Containment {
    public SelectAqlField<TestAllTypesInstruction> TEST_ALL_TYPES_INSTRUCTION =
            new AqlFieldImp<TestAllTypesInstruction>(
                    TestAllTypesInstruction.class, "", "TestAllTypesInstruction", TestAllTypesInstruction.class, this);

    public SelectAqlField<Temporal> PARTIAL_DATE_VALUE = new AqlFieldImp<Temporal>(
            TestAllTypesInstruction.class,
            "/activities[at0001]/description[at0002]/items[at0003]/value|value",
            "partialDateValue",
            Temporal.class,
            this);

    public SelectAqlField<TemporalAccessor> PARTIAL_DATETIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            TestAllTypesInstruction.class,
            "/activities[at0001]/description[at0002]/items[at0004]/value|value",
            "partialDatetimeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<DvParsable> TIMING = new AqlFieldImp<DvParsable>(
            TestAllTypesInstruction.class, "/activities[at0001]/timing", "timing", DvParsable.class, this);

    public SelectAqlField<String> ACTION_ARCHETYPE_ID = new AqlFieldImp<String>(
            TestAllTypesInstruction.class,
            "/activities[at0001]/action_archetype_id",
            "actionArchetypeId",
            String.class,
            this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(TestAllTypesInstruction.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<String> NARRATIVE_VALUE = new AqlFieldImp<String>(
            TestAllTypesInstruction.class, "/narrative|value", "narrativeValue", String.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(TestAllTypesInstruction.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            TestAllTypesInstruction.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    public SelectAqlField<TemporalAccessor> EXPIRY_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            TestAllTypesInstruction.class, "/expiry_time|value", "expiryTimeValue", TemporalAccessor.class, this);

    private TestAllTypesInstructionContainment() {
        super("openEHR-EHR-INSTRUCTION.test_all_types.v1");
    }

    public static TestAllTypesInstructionContainment getInstance() {
        return new TestAllTypesInstructionContainment();
    }
}
