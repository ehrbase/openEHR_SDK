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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.testalltypesenv1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;

public class TestAllTypesObservationContainment extends Containment {
    public SelectAqlField<TestAllTypesObservation> TEST_ALL_TYPES_OBSERVATION =
            new AqlFieldImp<TestAllTypesObservation>(
                    TestAllTypesObservation.class, "", "TestAllTypesObservation", TestAllTypesObservation.class, this);

    public SelectAqlField<String> TEXT_VALUE = new AqlFieldImp<String>(
            TestAllTypesObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|value",
            "textValue",
            String.class,
            this);

    public SelectAqlField<DvCodedText> CODED_TEXT = new AqlFieldImp<DvCodedText>(
            TestAllTypesObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value",
            "codedText",
            DvCodedText.class,
            this);

    public SelectAqlField<DvCodedText> CODED_TEXT_TERMINOLOGY = new AqlFieldImp<DvCodedText>(
            TestAllTypesObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0006]/value",
            "codedTextTerminology",
            DvCodedText.class,
            this);

    public SelectAqlField<Double> QUANTITY_MAGNITUDE = new AqlFieldImp<Double>(
            TestAllTypesObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0007]/value|magnitude",
            "quantityMagnitude",
            Double.class,
            this);

    public SelectAqlField<String> QUANTITY_UNITS = new AqlFieldImp<String>(
            TestAllTypesObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0007]/value|units",
            "quantityUnits",
            String.class,
            this);

    public SelectAqlField<Long> COUNT_MAGNITUDE = new AqlFieldImp<Long>(
            TestAllTypesObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0008]/value|magnitude",
            "countMagnitude",
            Long.class,
            this);

    public SelectAqlField<Temporal> DATE_VALUE = new AqlFieldImp<Temporal>(
            TestAllTypesObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0009]/value|value",
            "dateValue",
            Temporal.class,
            this);

    public SelectAqlField<TemporalAccessor> DATETIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            TestAllTypesObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0010]/value|value",
            "datetimeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<TemporalAccessor> DATETIME_ANY_VALUE = new AqlFieldImp<TemporalAccessor>(
            TestAllTypesObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0011]/value|value",
            "datetimeAnyValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<TemporalAccessor> ARBOL_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            TestAllTypesObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0012]/value|value",
            "arbolTimeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<DvOrdinal> ORDINAL = new AqlFieldImp<DvOrdinal>(
            TestAllTypesObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0013]/value",
            "ordinal",
            DvOrdinal.class,
            this);

    public SelectAqlField<Boolean> BOOLEAN_VALUE = new AqlFieldImp<Boolean>(
            TestAllTypesObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0017]/value|value",
            "booleanValue",
            Boolean.class,
            this);

    public SelectAqlField<TemporalAmount> DURATION_ANY_VALUE = new AqlFieldImp<TemporalAmount>(
            TestAllTypesObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0018]/value|value",
            "durationAnyValue",
            TemporalAmount.class,
            this);

    public SelectAqlField<DvMultimedia> MULTIMEDIA_ANY = new AqlFieldImp<DvMultimedia>(
            TestAllTypesObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0019]/value",
            "multimediaAny",
            DvMultimedia.class,
            this);

    public SelectAqlField<DvParsable> PARSABLE_ANY = new AqlFieldImp<DvParsable>(
            TestAllTypesObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0020]/value",
            "parsableAny",
            DvParsable.class,
            this);

    public SelectAqlField<DvIdentifier> IDENTIFIER = new AqlFieldImp<DvIdentifier>(
            TestAllTypesObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0021]/value",
            "identifier",
            DvIdentifier.class,
            this);

    public SelectAqlField<DvProportion> PROPORTION_ANY = new AqlFieldImp<DvProportion>(
            TestAllTypesObservation.class,
            "/data[at0001]/events[at0002]/data[at0003]/items[at0022]/value",
            "proportionAny",
            DvProportion.class,
            this);

    public SelectAqlField<TemporalAccessor> CUALQUIER_EVENTO_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            TestAllTypesObservation.class,
            "/data[at0001]/events[at0002]/time|value",
            "cualquierEventoTimeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(
            TestAllTypesObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(TestAllTypesObservation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(TestAllTypesObservation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            TestAllTypesObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private TestAllTypesObservationContainment() {
        super("openEHR-EHR-OBSERVATION.test_all_types.v1");
    }

    public static TestAllTypesObservationContainment getInstance() {
        return new TestAllTypesObservationContainment();
    }
}
