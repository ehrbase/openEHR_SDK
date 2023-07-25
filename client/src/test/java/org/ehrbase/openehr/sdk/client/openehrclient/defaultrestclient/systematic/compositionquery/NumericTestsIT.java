/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient.systematic.compositionquery;

import static org.assertj.core.api.Assertions.assertThat;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.quantity.DvQuantity;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.ehrbase.openehr.sdk.client.Integration;
import org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient.systematic.compositionquery.queries.arbitrary.NumericQuery;
import org.ehrbase.openehr.sdk.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.minimalevaluationenv1composition.MinimalEvaluationEnV1Composition;
import org.ehrbase.openehr.sdk.serialisation.dto.RmToGeneratedDtoConverter;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Integration.class)
public class NumericTestsIT extends CanonicalCompoAllTypeQueryIT {

    protected NumericQuery numericQuery;

    @Before
    public void setUp() throws IOException {
        super.setUp(null);

        // build a number of compositions with different DvQuantity values and different names

        aComposition = new CanonicalJson()
                .unmarshal(
                        IOUtils.toString(
                                CompositionTestDataCanonicalJson.MINIMAL_EVAL.getStream(), StandardCharsets.UTF_8),
                        Composition.class);

        for (int i = 0; i < 10; i++) {
            Element element = (Element) aComposition
                    .itemsAtPath("/content[openEHR-EHR-EVALUATION.minimal.v1]/data[at0001]/items[at0002]")
                    .get(0);
            element.setValue(new DvQuantity("kg", Double.valueOf("" + (i + 1)), 0L));
            element.setName(new DvText("value-" + i + 1));
            RmToGeneratedDtoConverter rmToGeneratedDtoConverter =
                    new RmToGeneratedDtoConverter(new TestDataTemplateProvider());
            MinimalEvaluationEnV1Composition minimalEvaluationEnV1Composition =
                    rmToGeneratedDtoConverter.toGeneratedDto(aComposition, MinimalEvaluationEnV1Composition.class);
            //        create the composition
            MinimalEvaluationEnV1Composition comp =
                    compositionEndpoint.mergeCompositionEntity(minimalEvaluationEnV1Composition);
        }

        numericQuery = new NumericQuery(ehrUUID, openEhrClient);
    }

    @Test
    public void testNumeric1() throws IOException {
        String csvTestSet = dirPath + "/arbitrary/numeric_tests.csv";

        assertThat(numericQuery.testItemPaths(dirPath + "/arbitrary", csvTestSet))
                .isTrue();
    }
}
