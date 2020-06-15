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

import com.nedap.archie.rm.composition.Composition;
import org.apache.commons.io.IOUtils;
import org.assertj.core.groups.Tuple;
import org.ehrbase.client.aql.condition.Condition;
import org.ehrbase.client.aql.containment.ContainmentExpression;
import org.ehrbase.client.aql.field.EhrFields;
import org.ehrbase.client.aql.parameter.Parameter;
import org.ehrbase.client.aql.query.EntityQuery;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record2;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.CoronaAnamneseComposition;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.CoronaAnamneseCompositionContainment;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.FieberOderErhohteKorpertemperaturObservationContainment;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.HustenObservationContainment;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.SymptomeSectionContainment;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.VorhandenDefiningcode;
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CoronaTestIT {

    private static OpenEhrClient openEhrClient;

    @BeforeClass
    public static void setup() throws URISyntaxException {
        openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    }

    @Test
    public void testCorona() throws IOException {

        UUID ehr = openEhrClient.ehrEndpoint().createEhr();

        Composition composition = new CanonicalJson().unmarshal(IOUtils.toString(CompositionTestDataCanonicalJson.CORONA.getStream(), StandardCharsets.UTF_8), Composition.class);
        Flattener flattener = new Flattener();
        CoronaAnamneseComposition coronaAnamneseComposition = flattener.flatten(composition, CoronaAnamneseComposition.class);
        openEhrClient.compositionEndpoint(ehr).mergeCompositionEntity(coronaAnamneseComposition);

        assertThat(openEhrClient.compositionEndpoint(ehr).find(coronaAnamneseComposition.getVersionUid().getUuid(), CoronaAnamneseComposition.class)).isNotNull();


        CoronaAnamneseCompositionContainment coronaAnamneseCompositionContainment = CoronaAnamneseCompositionContainment.getInstance();
        SymptomeSectionContainment symptomeSectionContainment = SymptomeSectionContainment.getInstance();
        coronaAnamneseCompositionContainment.setContains(symptomeSectionContainment);
        HustenObservationContainment hustenObservationContainment = HustenObservationContainment.getInstance();
        FieberOderErhohteKorpertemperaturObservationContainment fieberOderErhohteKorpertemperaturObservationContainment = FieberOderErhohteKorpertemperaturObservationContainment.getInstance();
        symptomeSectionContainment.setContains(ContainmentExpression.and(hustenObservationContainment, fieberOderErhohteKorpertemperaturObservationContainment));

        EntityQuery<Record2<VorhandenDefiningcode, VorhandenDefiningcode>> entityQuery = Query.buildEntityQuery(coronaAnamneseCompositionContainment, hustenObservationContainment.VORHANDEN_DEFININGCODE, fieberOderErhohteKorpertemperaturObservationContainment.VORHANDEN_DEFININGCODE);
        Parameter<UUID> ehrIdParameter = entityQuery.buildParameter();
        entityQuery.where(Condition.equal(EhrFields.EHR_ID(), ehrIdParameter));

        List<Record2<VorhandenDefiningcode, VorhandenDefiningcode>> actual = openEhrClient.aqlEndpoint().execute(entityQuery, ehrIdParameter.setValue(ehr));

        assertThat(actual).extracting(Record2::value1, Record2::value2).containsExactlyInAnyOrder(new Tuple(VorhandenDefiningcode.VORHANDEN, VorhandenDefiningcode.VORHANDEN));


    }
}
