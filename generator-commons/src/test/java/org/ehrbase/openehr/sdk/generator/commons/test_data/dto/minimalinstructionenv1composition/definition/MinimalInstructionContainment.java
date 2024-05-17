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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.minimalinstructionenv1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.NullFlavour;

public class MinimalInstructionContainment extends Containment {
    public SelectAqlField<MinimalInstruction> MINIMAL_INSTRUCTION = new AqlFieldImp<MinimalInstruction>(
            MinimalInstruction.class, "", "MinimalInstruction", MinimalInstruction.class, this);

    public SelectAqlField<TemporalAmount> DURATION_VALUE = new AqlFieldImp<TemporalAmount>(
            MinimalInstruction.class,
            "/activities[at0001]/description[at0002]/items[at0003]/value|value",
            "durationValue",
            TemporalAmount.class,
            this);

    public SelectAqlField<NullFlavour> DURATION_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(
            MinimalInstruction.class,
            "/activities[at0001]/description[at0002]/items[at0003]/null_flavour|defining_code",
            "durationNullFlavourDefiningCode",
            NullFlavour.class,
            this);

    public SelectAqlField<DvParsable> TIMING = new AqlFieldImp<DvParsable>(
            MinimalInstruction.class, "/activities[at0001]/timing", "timing", DvParsable.class, this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(MinimalInstruction.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<String> NARRATIVE_VALUE =
            new AqlFieldImp<String>(MinimalInstruction.class, "/narrative|value", "narrativeValue", String.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(MinimalInstruction.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            MinimalInstruction.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    public SelectAqlField<TemporalAccessor> EXPIRY_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            MinimalInstruction.class, "/expiry_time|value", "expiryTimeValue", TemporalAccessor.class, this);

    private MinimalInstructionContainment() {
        super("openEHR-EHR-INSTRUCTION.minimal.v1");
    }

    public static MinimalInstructionContainment getInstance() {
        return new MinimalInstructionContainment();
    }
}
