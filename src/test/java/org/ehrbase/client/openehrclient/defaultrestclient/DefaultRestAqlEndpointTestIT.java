/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.client.openehrclient.defaultrestclient;

import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import org.assertj.core.groups.Tuple;
import org.ehrbase.client.Integration;
import org.ehrbase.client.TestData;
import org.ehrbase.client.aql.ParameterValue;
import org.ehrbase.client.aql.Query;
import org.ehrbase.client.aql.Record2;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.EhrbaseBloodPressureSimpleDeV0Composition;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.VersionUid;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.net.URISyntaxException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@Category(Integration.class)
public class DefaultRestAqlEndpointTestIT {
    private static OpenEhrClient openEhrClient;

    @BeforeClass
    public static void setup() throws URISyntaxException {
        openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    }

    @Test
    public void testExecute() {

        UUID ehr = openEhrClient.ehrEndpoint().createEhr();

        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        Query<Record2<String, DvDateTime>> query = Query.nativeQuery("select  a/template_id, a/context/start_time from EHR e[ehr_id/value = $ehr_id]  contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1]", String.class, DvDateTime.class);

        List<Record2<String, DvDateTime>> result = openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull();
        assertThat(result).size().isEqualTo(2);


    }

    @Test
    public void testExecuteValue() {

        UUID ehr = openEhrClient.ehrEndpoint().createEhr();

        EhrbaseBloodPressureSimpleDeV0Composition comp1 = openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());
        EhrbaseBloodPressureSimpleDeV0Composition comp2 = openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        Query<Record2<Double, OffsetDateTime>> query = Query.nativeQuery("select  a/content[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude, a/context/start_time/value from EHR e[ehr_id/value = $ehr_id]  contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1]", Double.class, OffsetDateTime.class);

        List<Record2<Double, OffsetDateTime>> result = openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull();
        assertThat(result).size().isEqualTo(2);
        assertThat(result).
                extracting(objectVersionIdOffsetDateTimeRecord2 -> objectVersionIdOffsetDateTimeRecord2.value1(), Record2::value2)
                .containsExactlyInAnyOrder(
                        new Tuple(22d, OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC)),
                        new Tuple(22d, OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC))
                );

    }

    @Test
    public void testExecuteCustomConverters() {

        UUID ehr = openEhrClient.ehrEndpoint().createEhr();

        EhrbaseBloodPressureSimpleDeV0Composition comp1 = openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());
        EhrbaseBloodPressureSimpleDeV0Composition comp2 = openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(TestData.buildEhrbaseBloodPressureSimpleDeV0());

        Query<Record2<VersionUid, TemporalAccessor>> query = Query.nativeQuery("select  a/uid/value, a/context/start_time/value from EHR e[ehr_id/value = $ehr_id]  contains COMPOSITION a [openEHR-EHR-COMPOSITION.sample_encounter.v1]", VersionUid.class, TemporalAccessor.class);

        List<Record2<VersionUid, TemporalAccessor>> result = openEhrClient.aqlEndpoint().execute(query, new ParameterValue("ehr_id", ehr));
        assertThat(result).isNotNull();
        assertThat(result).size().isEqualTo(2);
        assertThat(result).
                extracting(objectVersionIdOffsetDateTimeRecord2 -> objectVersionIdOffsetDateTimeRecord2.value1().toString(), Record2::value2)
                .containsExactlyInAnyOrder(
                        new Tuple(comp1.getVersionUid().toString(), OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC)),
                        new Tuple(comp2.getVersionUid().toString(), OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC))
                );

    }
}