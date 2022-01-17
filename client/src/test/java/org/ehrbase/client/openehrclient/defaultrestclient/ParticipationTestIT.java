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
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.support.identification.GenericId;
import org.apache.commons.io.IOUtils;
import org.assertj.core.groups.Tuple;
import org.ehrbase.client.Integration;
import org.ehrbase.client.TestData;
import org.ehrbase.client.aql.condition.Condition;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.containment.ContainmentExpression;
import org.ehrbase.client.aql.field.EhrFields;
import org.ehrbase.client.aql.field.NativeSelectAqlField;
import org.ehrbase.client.aql.parameter.Parameter;
import org.ehrbase.client.aql.query.EntityQuery;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record2;
import org.ehrbase.client.aql.record.Record3;
import org.ehrbase.client.aql.record.Record4;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.CoronaAnamneseComposition;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.CoronaAnamneseCompositionContainment;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.*;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.PatientenaufenthaltCompositionContainment;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.AbteilungsfallClusterContainment;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.StandortClusterContainment;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.StandortschlusselDefiningCode;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.VersorgungsortAdminEntryContainment;
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.StationarerVersorgungsfallCompositionContainment;
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition.AufnahmedatenAdminEntryContainment;
import org.ehrbase.client.classgenerator.examples.stationarerversorgungsfallcomposition.definition.EntlassungsdatenAdminEntryContainment;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.VirologischerBefundComposition;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.VirologischerBefundCompositionContainment;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition.BefundObservationContainment;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition.KulturClusterContainment;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition.ProVirusClusterContainment;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition.ProbeClusterContainment;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.exception.WrongStatusCodeException;
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@Category(Integration.class)
public class ParticipationTestIT {

    private static OpenEhrClient openEhrClient;
    private UUID ehr;

    @BeforeClass
    public static void setup() throws URISyntaxException {
        openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    }

    @After
    public void tearDown(){
        //delete the created EHR using the admin endpoint
        openEhrClient.adminEhrEndpoint().delete(ehr);
    }

    @Test
    @Ignore("see https://github.com/ehrbase/ehrbase/issues/710")
    public void testParticipation() throws IOException {

        ehr = openEhrClient.ehrEndpoint().createEhr();

        Composition composition = new CanonicalJson().unmarshal(IOUtils.toString(CompositionTestDataCanonicalJson.PARTICIPATION_NO_CONTENT.getStream(), StandardCharsets.UTF_8), Composition.class);

        VersionUid versionUid = openEhrClient.compositionEndpoint(ehr).mergeRaw(composition);

        Optional<Composition> postedComposition = openEhrClient.compositionEndpoint(ehr).findRaw(versionUid.getUuid());

        assertThat(postedComposition.get()).isNotNull();

        //check the actual participation IDs and Names
        List<Participation> participations = postedComposition.get().getContext().getParticipations();

        List<String> names = participations.stream()
                .map(p -> p.getPerformer())
                        .map(p -> ((PartyIdentified)p).getName())
                .collect(Collectors.toList());
        assertThat(names).containsExactlyInAnyOrder("Dr. Marcus Johnson", "Zaza Markham");

        List<String> ids = participations.stream()
                .map(p -> p.getPerformer())
                .map(p -> p.getExternalRef().getId().getValue())
                .collect(Collectors.toList());
        assertThat(ids).containsExactlyInAnyOrder("000", "123");


        //use the sames IDs, but change one name. This raises an exception for conflicting identity!
        ((PartyIdentified)composition.getContext().getParticipations().get(0).getPerformer()).setName("Dummy");

        try {
            openEhrClient.compositionEndpoint(ehr).mergeRaw(composition);
            fail("Didn't detect conflicting identity!");
        } catch (WrongStatusCodeException e){
            //continue
        }

        //Now, keep the same names, but change an externalRef id
        composition = new CanonicalJson().unmarshal(IOUtils.toString(CompositionTestDataCanonicalJson.PARTICIPATION_NO_CONTENT.getStream(), StandardCharsets.UTF_8), Composition.class);
        composition.getContext().getParticipations().get(0).getPerformer().getExternalRef().setId(new GenericId("ABC", "HOSPITAL-NS"));

        versionUid = openEhrClient.compositionEndpoint(ehr).mergeRaw(composition);

        postedComposition = openEhrClient.compositionEndpoint(ehr).findRaw(versionUid.getUuid());

        assertThat(postedComposition.get()).isNotNull();

        //check the actual participation IDs and Names
        participations = postedComposition.get().getContext().getParticipations();

        names = participations.stream()
                .map(p -> p.getPerformer())
                .map(p -> ((PartyIdentified)p).getName())
                .collect(Collectors.toList());
        assertThat(names).containsExactlyInAnyOrder("Dr. Marcus Johnson", "Zaza Markham");

        ids = participations.stream()
                .map(p -> p.getPerformer())
                .map(p -> p.getExternalRef().getId().getValue())
                .collect(Collectors.toList());
        assertThat(ids).containsExactlyInAnyOrder("ABC", "123");

        //use the same name and id, but in another namespace

        composition = new CanonicalJson().unmarshal(IOUtils.toString(CompositionTestDataCanonicalJson.PARTICIPATION_NO_CONTENT.getStream(), StandardCharsets.UTF_8), Composition.class);
        composition.getContext().getParticipations().get(0).getPerformer().getExternalRef().setNamespace("ANOTHER_NAMESPACE");

        versionUid = openEhrClient.compositionEndpoint(ehr).mergeRaw(composition);

        postedComposition = openEhrClient.compositionEndpoint(ehr).findRaw(versionUid.getUuid());

        assertThat(postedComposition.get()).isNotNull();

        //check the actual participation IDs and Names
        participations = postedComposition.get().getContext().getParticipations();

        List<String> namespaces = participations.stream()
                .map(p -> p.getPerformer())
                .map(p -> p.getExternalRef().getNamespace())
                .collect(Collectors.toList());
        assertThat(namespaces).containsExactlyInAnyOrder("ANOTHER_NAMESPACE", "ANOTHER-HOSPITAL-NS");


    }
}
