/*
 * Copyright (c) 2020 Christian Chevalley (Hannover Medical School) and Vitasystems GmbH
 *
 * This file is part of project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package org.ehrbase.client.openehrclient.defaultrestclient.systematic.compositionquery;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import org.apache.commons.io.IOUtils;
import org.ehrbase.client.aql.parameter.ParameterValue;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record1;
import org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.TestAllTypesEnV1Composition;
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.client.openehrclient.CompositionEndpoint;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClientTestHelper;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.CanonicalUtil;
import org.ehrbase.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.response.openehr.QueryResponseData;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CanonicalCompoAllTypeQuery1Test extends CanonicalUtil {
    private static OpenEhrClient openEhrClient;
    private CompositionEndpoint compositionEndpoint;
    private Composition allTypesComposition;

    private UUID ehrUUID;
    private UUID compositionUUID;
    
    private DvDateTime actualDvDateTime;

    @BeforeClass
    public static void setup() throws URISyntaxException {
        openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    }

    @Before
    public void setUp() throws IOException {
        actualDvDateTime = new DvDateTime(OffsetDateTime.now());
        ehrUUID = openEhrClient.ehrEndpoint().createEhr();
        compositionEndpoint = openEhrClient.compositionEndpoint(ehrUUID);
        allTypesComposition = new CanonicalJson().unmarshal(IOUtils.toString(CompositionTestDataCanonicalJson.ALL_TYPES.getStream(), StandardCharsets.UTF_8), Composition.class);
        
        Flattener flattener = new Flattener(new TestDataTemplateProvider());
        TestAllTypesEnV1Composition testAllTypesEnV1Composition = flattener.flatten(allTypesComposition, TestAllTypesEnV1Composition.class);
        
        //create the composition
        TestAllTypesEnV1Composition comp = compositionEndpoint.mergeCompositionEntity(testAllTypesEnV1Composition);
        compositionUUID  = comp.getVersionUid().getUuid();
    }

    @Test
    public void testCompositionAttributeQuery(){
        String rootPath = "c";
        RMObject referenceNode = allTypesComposition;

        String[] attributePaths = {
                "name",
                "name/value",
                "archetype_details/archetype_id",
                "archetype_details/archetype_id/value",
                "archetype_details/template_id",
                "archetype_details/template_id/value",
                //TODO: https://github.com/ehrbase/ehrbase/tree/feature/463_rm_version_persistence
//                "archetype_details/rm_version",
                "archetype_node_id",
                "uid",
                "uid/value",
                "language",
                "language/code_string",
                "language/terminology_id",
                "language/terminology_id/value",
                "territory",
                "territory/code_string",
                "territory/terminology_id",
                "territory/terminology_id/value",
                "category",
                "category/value",
                "category/defining_code/code_string",
                "category/defining_code/terminology_id",
                "category/defining_code/terminology_id/value",
                "composer",
                "composer/name",
                //TODO:https://github.com/ehrbase/project_management/issues/486
//                "composer/external_ref",
                "composer/external_ref/type",
                "composer/external_ref/namespace",
                //TODO:https://github.com/ehrbase/project_management/issues/486
//                "composer/external_ref/id",
                "composer/external_ref/id/value"
        };

        for (String attributePath: attributePaths) {
            QueryResponseData result = performQuery(rootPath, attributePath, "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]");

            List<Object> objectList = result.getRows().get(0);


            //special case, c/uid is actually completed after db insertion, hence cannot be compared with the initial
            //null value
            if (attributePath.contains("uid")){
                assertThat(objectList.get(0)).isNotNull();
            }
            else
                assertThat(valueObject(objectList.get(0)))
                        .as(rootPath+"/"+attributePath)
                        .isEqualTo(attributeValueAt(referenceNode, attributePath));
        }
    }
    
    @Test
    public void testContextAttributesDrillDown(){
        
        String rootPath = "c/context";
        RMObject referenceNode = allTypesComposition.getContext();
        
        String[] attributePaths = {
                "start_time",
                "start_time/value",
                "end_time",
                "end_time/value",
                //TODO: https://github.com/ehrbase/project_management/issues/487
//                "setting",
                "setting/value",
                "setting/defining_code/code_string",
                //TODO: https://github.com/ehrbase/project_management/issues/465
//                "setting/defining_code/terminology_id",
//                "setting/defining_code/terminology_id/value"
        };

        for (String attributePath: attributePaths) {
            QueryResponseData result = performQuery(rootPath, attributePath, "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]");

            List<Object> objectList = result.getRows().get(0);

            assertThat(valueObject(objectList.get(0)))
                    .as(rootPath+"/"+attributePath)
                    .isEqualTo(attributeValueAt(referenceNode, attributePath));
        }
    }

    @Test
    public void testObservationDrillDown(){

        String rootPath = "o";
        RMObject referenceNode = (RMObject) allTypesComposition.itemsAtPath("/content[openEHR-EHR-OBSERVATION.test_all_types.v1]").get(0);

        String[] attributePaths = {
                "name",
                "name/value",
                "archetype_node_id",
                //TODO: https://github.com/ehrbase/project_management/issues/465
//                "language",
                "language/code_string",
                //TODO: https://github.com/ehrbase/openEHR_SDK/tree/feature/465_name_in_terminology_id
//                "language/terminology_id",
                "language/terminology_id/value",
                //TODO: https://github.com/ehrbase/openEHR_SDK/tree/feature/465_name_in_terminology_id
//                "encoding",
                "encoding/code_string",
                //TODO: https://github.com/ehrbase/openEHR_SDK/tree/feature/465_name_in_terminology_id
//                "encoding/terminology_id",
                //TODO: https://github.com/ehrbase/project_management/issues/490
//                "encoding/terminology_id/value",
//                //TODO: https://github.com/ehrbase/project_management/issues/488
//                "subject",
                //TODO: multiple discrepancies (extraneous _type, terminology name etc.
//                "data[at0001]",
                "data[at0001]/name",
                "data[at0001]/name/value",
                "data[at0001]/archetype_node_id",
                "data[at0001]/origin",
                "data[at0001]/origin/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/events[at0002]",
                "data[at0001]/events[at0002]/name",
                "data[at0001]/events[at0002]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/events[at0002]/archetype_node_id",
                "data[at0001]/events[at0002]/time",
                "data[at0001]/events[at0002]/time/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/events[at0002]/data[at0003]",
                "data[at0001]/events[at0002]/data[at0003]/name",
                "data[at0001]/events[at0002]/data[at0003]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/events[at0002]/data[at0003]/archetype_node_id",
                // data values
                //DvText
                //TODO:https://github.com/ehrbase/project_management/issues/484
//                "data[at0001]/events[at0002]/data[at0003]/items[at0004]",
                "data[at0001]/events[at0002]/data[at0003]/items[at0004]/name",
                "data[at0001]/events[at0002]/data[at0003]/items[at0004]/name/value",
                "data[at0001]/events[at0002]/data[at0003]/items[at0004]/value",
                "data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/value",
                //DvCodedText
                //TODO: https://github.com/ehrbase/project_management/issues/484
//                "data[at0001]/events[at0002]/data[at0003]/items[at0005]",
                "data[at0001]/events[at0002]/data[at0003]/items[at0005]/name",
                "data[at0001]/events[at0002]/data[at0003]/items[at0005]/name/value",
                "data[at0001]/events[at0002]/data[at0003]/items[at0005]/value",
                "data[at0001]/events[at0002]/data[at0003]/items[at0005]/value/value",
                "data[at0001]/events[at0002]/data[at0003]/items[at0005]/value/defining_code",
                "data[at0001]/events[at0002]/data[at0003]/items[at0005]/value/defining_code/code_string",
                //TODO: https://github.com/ehrbase/project_management/issues/465
//                "data[at0001]/events[at0002]/data[at0003]/items[at0005]/value/defining_code/terminology_id",
                "data[at0001]/events[at0002]/data[at0003]/items[at0005]/value/defining_code/terminology_id/value",
                //DvQuantity
                "data[at0001]/events[at0002]/data[at0003]/items[at0007]/name",
                "data[at0001]/events[at0002]/data[at0003]/items[at0007]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/485
//                "data[at0001]/events[at0002]/data[at0003]/items[at0007]/value",
                //TODO: https://github.com/ehrbase/project_management/issues/489
//                "data[at0001]/events[at0002]/data[at0003]/items[at0007]/value/magnitude",
                "data[at0001]/events[at0002]/data[at0003]/items[at0007]/value/units",
                //DvCount
                "data[at0001]/events[at0002]/data[at0003]/items[at0008]/name",
                "data[at0001]/events[at0002]/data[at0003]/items[at0008]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/485
//                "data[at0001]/events[at0002]/data[at0003]/items[at0008]/value",
                //TODO: https://github.com/ehrbase/project_management/issues/489
//                "data[at0001]/events[at0002]/data[at0003]/items[at0008]/value/magnitude",
                //DvDate
                "data[at0001]/events[at0002]/data[at0003]/items[at0009]/name",
                "data[at0001]/events[at0002]/data[at0003]/items[at0009]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/485
//                "data[at0001]/events[at0002]/data[at0003]/items[at0009]/value",
                "data[at0001]/events[at0002]/data[at0003]/items[at0009]/value/value",
                //DvDate
                "data[at0001]/events[at0002]/data[at0003]/items[at0010]/name",
                "data[at0001]/events[at0002]/data[at0003]/items[at0010]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/485
//                "data[at0001]/events[at0002]/data[at0003]/items[at0010]/value",
                "data[at0001]/events[at0002]/data[at0003]/items[at0010]/value/value",
                //DvDateTime
                "data[at0001]/events[at0002]/data[at0003]/items[at0011]/name",
                "data[at0001]/events[at0002]/data[at0003]/items[at0011]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/485
//                "data[at0001]/events[at0002]/data[at0003]/items[at0011]/value",
                "data[at0001]/events[at0002]/data[at0003]/items[at0011]/value/value",
                //DvTime
                "data[at0001]/events[at0002]/data[at0003]/items[at0012]/name",
                "data[at0001]/events[at0002]/data[at0003]/items[at0012]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/485
//                "data[at0001]/events[at0002]/data[at0003]/items[at0012]/value",
                "data[at0001]/events[at0002]/data[at0003]/items[at0012]/value/value",
//                //DvOrdinal
                "data[at0001]/events[at0002]/data[at0003]/items[at0013]/name",
                //TODO: erroneous cast for ordinal name/value
//                "data[at0001]/events[at0002]/data[at0003]/items[at0013]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/485
//                "data[at0001]/events[at0002]/data[at0003]/items[at0013]/value",
                //TODO: https://github.com/ehrbase/project_management/issues/489
//                "data[at0001]/events[at0002]/data[at0003]/items[at0013]/value/value",
                //TODO: ...
//                "data[at0001]/events[at0002]/data[at0003]/items[at0013]/value/symbol",
                //TODO: https://github.com/ehrbase/project_management/issues/491
//                "data[at0001]/events[at0002]/data[at0003]/items[at0013]/value/symbol/value",
                //TODO: https://github.com/ehrbase/project_management/issues/465
//                "data[at0001]/events[at0002]/data[at0003]/items[at0013]/value/symbol/defining_code",
                "data[at0001]/events[at0002]/data[at0003]/items[at0013]/value/symbol/defining_code/code_string",
//                "data[at0001]/events[at0002]/data[at0003]/items[at0013]/value/symbol/defining_code/terminology_id",
//                "data[at0001]/events[at0002]/data[at0003]/items[at0013]/value/symbol/defining_code/terminology_id/value",
                //DvBoolean
                "data[at0001]/events[at0002]/data[at0003]/items[at0017]/name",
                "data[at0001]/events[at0002]/data[at0003]/items[at0017]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/485
//                "data[at0001]/events[at0002]/data[at0003]/items[at0017]/value",
                //TODO: https://github.com/ehrbase/project_management/issues/491
//                "data[at0001]/events[at0002]/data[at0003]/items[at0017]/value/value",
                //DvDuration
                "data[at0001]/events[at0002]/data[at0003]/items[at0018]/name",
                "data[at0001]/events[at0002]/data[at0003]/items[at0018]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/485
//                "data[at0001]/events[at0002]/data[at0003]/items[at0018]/value",
                "data[at0001]/events[at0002]/data[at0003]/items[at0018]/value/value",
                //DvMultimedia
                "data[at0001]/events[at0002]/data[at0003]/items[at0019]/name",
                "data[at0001]/events[at0002]/data[at0003]/items[at0019]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/485
//                "data[at0001]/events[at0002]/data[at0003]/items[at0019]/value",
                //TODO: https://github.com/ehrbase/project_management/issues/485
//                "data[at0001]/events[at0002]/data[at0003]/items[at0019]/value/uri",
                "data[at0001]/events[at0002]/data[at0003]/items[at0019]/value/uri/value",
                //TODO: https://github.com/ehrbase/openEHR_SDK/tree/feature/465_name_in_terminology_id
//                "data[at0001]/events[at0002]/data[at0003]/items[at0019]/value/media_type",
                "data[at0001]/events[at0002]/data[at0003]/items[at0019]/value/media_type/code_string",
                //TODO: https://github.com/ehrbase/openEHR_SDK/tree/feature/465_name_in_terminology_id
//                "data[at0001]/events[at0002]/data[at0003]/items[at0019]/value/media_type/terminology_id",
                "data[at0001]/events[at0002]/data[at0003]/items[at0019]/value/media_type/terminology_id/value",
                //TODO: https://github.com/ehrbase/project_management/issues/491
//                "data[at0001]/events[at0002]/data[at0003]/items[at0019]/value/size",
                //DvParsable
                "data[at0001]/events[at0002]/data[at0003]/items[at0020]/name",
                "data[at0001]/events[at0002]/data[at0003]/items[at0020]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/485
//                "data[at0001]/events[at0002]/data[at0003]/items[at0020]/value",
                "data[at0001]/events[at0002]/data[at0003]/items[at0020]/value/value",
                "data[at0001]/events[at0002]/data[at0003]/items[at0020]/value/formalism",
                //DvIdentifier
                "data[at0001]/events[at0002]/data[at0003]/items[at0021]/name",
                "data[at0001]/events[at0002]/data[at0003]/items[at0021]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/485
//                "data[at0001]/events[at0002]/data[at0003]/items[at0021]/value",
                "data[at0001]/events[at0002]/data[at0003]/items[at0021]/value/issuer",
                "data[at0001]/events[at0002]/data[at0003]/items[at0021]/value/assigner",
                "data[at0001]/events[at0002]/data[at0003]/items[at0021]/value/id",
                "data[at0001]/events[at0002]/data[at0003]/items[at0021]/value/type",
                //DvProportion
                "data[at0001]/events[at0002]/data[at0003]/items[at0022]/name",
                "data[at0001]/events[at0002]/data[at0003]/items[at0022]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/485
//                "data[at0001]/events[at0002]/data[at0003]/items[at0022]/value",
                //TODO: https://github.com/ehrbase/project_management/issues/491
//                "data[at0001]/events[at0002]/data[at0003]/items[at0022]/value/numerator",
//                "data[at0001]/events[at0002]/data[at0003]/items[at0022]/value/denominator",
//                "data[at0001]/events[at0002]/data[at0003]/items[at0022]/value/type",
//                "data[at0001]/events[at0002]/data[at0003]/items[at0022]/value/precision"

        };

        for (String attributePath: attributePaths) {
            QueryResponseData result = performQuery(rootPath, attributePath,
                    "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                            " contains observation o[openEHR-EHR-OBSERVATION.test_all_types.v1]");

            List<Object> objectList = result.getRows().get(0);

            assertThat(valueObject(objectList.get(0)))
                    .as(rootPath+"/"+attributePath)
                    .isEqualTo(attributeValueAt(referenceNode, attributePath));
        }
    }

    @Test
    public void testEvaluationDrillDown(){

        String rootPath = "eval";
        RMObject referenceNode = (RMObject) allTypesComposition.itemsAtPath("/content[openEHR-EHR-EVALUATION.test_all_types.v1]").get(0);

        String[] attributePaths = {
                "name",
                "name/value",
                "archetype_node_id",
                //TODO: https://github.com/ehrbase/project_management/issues/465
//                "language",
                "language/code_string",
                //TODO: https://github.com/ehrbase/openEHR_SDK/tree/feature/465_name_in_terminology_id
//                "language/terminology_id",
                "language/terminology_id/value",
                //TODO: https://github.com/ehrbase/openEHR_SDK/tree/feature/465_name_in_terminology_id
//                "encoding",
                "encoding/code_string",
                //TODO: https://github.com/ehrbase/openEHR_SDK/tree/feature/465_name_in_terminology_id
//                "encoding/terminology_id",
                //TODO: https://github.com/ehrbase/project_management/issues/490
//                "encoding/terminology_id/value",
//                //TODO: https://github.com/ehrbase/project_management/issues/488
//                "subject",
                //TODO: multiple discrepancies (extraneous _type, terminology name etc.
//                "data[at0001]",
                "data[at0001]/name",
                "data[at0001]/name/value",
//                "data[at0001]/archetype_node_id",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/items[at0002]",
                //TODO: https://github.com/ehrbase/project_management/issues/492
//                "data[at0001]/items[at0002]/name",
//                "data[at0001]/items[at0002]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/items[at0002]/archetype_node_id",
                //DvInterval<DvCount>
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/items[at0003]",
                "data[at0001]/items[at0003]/name",
                "data[at0001]/items[at0003]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/items[at0003]/archetype_node_id",
                //TODO: https://github.com/ehrbase/project_management/issues/485
//                "data[at0001]/items[at0003]/value",
                //TODO: https://github.com/ehrbase/project_management/issues/485
//                "data[at0001]/items[at0003]/value/lower",
                //TODO: https://github.com/ehrbase/project_management/issues/491
//                "data[at0001]/items[at0003]/value/lower/magnitude",
                //TODO: https://github.com/ehrbase/project_management/issues/485
//                "data[at0001]/items[at0003]/value/upper",
                //TODO:https://github.com/ehrbase/project_management/issues/491
//                "data[at0001]/items[at0003]/value/upper/magnitude",
                //TODO:https://github.com/ehrbase/project_management/issues/491
//                "data[at0001]/items[at0003]/value/lower_unbounded",
                //TODO:https://github.com/ehrbase/project_management/issues/491
//                "data[at0001]/items[at0003]/value/upper_unbounded",
                //DvInterval<DvQuantity>
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/items[at0004]",
                "data[at0001]/items[at0004]/name",
                "data[at0001]/items[at0004]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/items[at0004]/archetype_node_id",
                //TODO: https://github.com/ehrbase/project_management/issues/485
//                "data[at0001]/items[at0004]/value",
                //TODO: https://github.com/ehrbase/project_management/issues/485
                //TODO:https://github.com/ehrbase/project_management/issues/493
//                "data[at0001]/items[at0004]/value/lower",
                //TODO:https://github.com/ehrbase/project_management/issues/491
//                "data[at0001]/items[at0004]/value/lower/magnitude",
                //TODO: https://github.com/ehrbase/project_management/issues/493
//                "data[at0001]/items[at0004]/value/lower/units",
                //TODO: https://github.com/ehrbase/project_management/issues/485
                //TODO: https://github.com/ehrbase/project_management/issues/493
//                "data[at0001]/items[at0004]/value/upper",
                //TODO:https://github.com/ehrbase/project_management/issues/491
//                "data[at0001]/items[at0004]/value/upper/magnitude",
                //TODO: https://github.com/ehrbase/project_management/issues/493
//                "data[at0001]/items[at0004]/value/upper/units",
                //TODO:https://github.com/ehrbase/project_management/issues/491
//                "data[at0001]/items[at0004]/value/lower_unbounded",
                //TODO:https://github.com/ehrbase/project_management/issues/491
//                "data[at0001]/items[at0004]/value/upper_unbounded",
                //DvInterval<DvDateTime>
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/items[at0005]",
                "data[at0001]/items[at0005]/name",
                "data[at0001]/items[at0005]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/items[at0005]/archetype_node_id",
                //TODO: https://github.com/ehrbase/project_management/issues/485
//                "data[at0001]/items[at0005]/value",
                //TODO: https://github.com/ehrbase/project_management/issues/493
//                "data[at0001]/items[at0005]/value/lower",
//                "data[at0001]/items[at0005]/value/lower/value",
                //TODO: https://github.com/ehrbase/project_management/issues/493
//                "data[at0001]/items[at0005]/value/upper",
//                "data[at0001]/items[at0005]/value/upper/value",
                //TODO:https://github.com/ehrbase/project_management/issues/491
//                "data[at0001]/items[at0005]/value/lower_unbounded",
//                "data[at0001]/items[at0005]/value/upper_unbounded",
                //Choice?
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/items[at0009]",
                "data[at0001]/items[at0009]/name",
                "data[at0001]/items[at0009]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/items[at0009]/archetype_node_id",
                //TODO: https://github.com/ehrbase/project_management/issues/489
                //TODO: https://github.com/ehrbase/project_management/issues/494
//                "data[at0001]/items[at0009]/value/magnitude",
//                "data[at0001]/items[at0009]/value/units",

                //Clusters...
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/items[at0006]",
                "data[at0001]/items[at0006]/name",
                "data[at0001]/items[at0006]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/items[at0006]/archetype_node_id",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/items[at0006]/items[at0007]",
                "data[at0001]/items[at0006]/items[at0007]/name",
                "data[at0001]/items[at0006]/items[at0007]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/items[at0006]/items[at0007]/archetype_node_id",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/items[at0006]/items[at0007]/items[at0008]",
                "data[at0001]/items[at0006]/items[at0007]/items[at0008]/name",
                "data[at0001]/items[at0006]/items[at0007]/items[at0008]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/items[at0006]/items[at0007]/items[at0008]/archetype_node_id",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/items[at0006]/items[at0007]/items[at0008]/items[at0010]",
                "data[at0001]/items[at0006]/items[at0007]/items[at0008]/items[at0010]/name",
                "data[at0001]/items[at0006]/items[at0007]/items[at0008]/items[at0010]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/items[at0006]/items[at0007]/items[at0008]/items[at0010]/archetype_node_id",
                "data[at0001]/items[at0006]/items[at0007]/items[at0008]/items[at0010]/value",
                "data[at0001]/items[at0006]/items[at0007]/items[at0008]/items[at0010]/value/value"
        };

        for (String attributePath: attributePaths) {
            QueryResponseData result = performQuery(rootPath, attributePath,
                    "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                            " contains evaluation eval[openEHR-EHR-EVALUATION.test_all_types.v1]");

            List<Object> objectList = result.getRows().get(0);

            assertThat(valueObject(objectList.get(0)))
                    .as(rootPath+"/"+attributePath)
                    .isEqualTo(attributeValueAt(referenceNode, attributePath));
        }
    }

    @Test
    public void testSectionDrillDown(){

        String rootPath = "s";
        RMObject referenceNode = (RMObject) allTypesComposition.itemsAtPath("/content[openEHR-EHR-SECTION.test_all_types.v1]").get(0);

        String[] attributePaths = {
                "name",
                "name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "archetype_node_id",
                //TODO: multiple discrepancies (extraneous _type, terminology name etc.
//                "items[at0001]",
                "items[at0001]/name",
                "items[at0001]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//              "items[at0001]/archetype_node_id",
                //TODO: multiple discrepancies (extraneous _type, terminology name etc.
//                "items[at0001]/items[at0002]",
                "items[at0001]/items[at0002]/name",
                "items[at0001]/items[at0002]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//              "items[at0001]/items[at0002]/archetype_node_id"

        };

        for (String attributePath: attributePaths) {
            QueryResponseData result = performQuery(rootPath, attributePath,
                    "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                            " contains section s[openEHR-EHR-SECTION.test_all_types.v1]");

            List<Object> objectList = result.getRows().get(0);

            assertThat(valueObject(objectList.get(0)))
                    .as(rootPath+"/"+attributePath)
                    .isEqualTo(attributeValueAt(referenceNode, attributePath));
        }
    }

    @Test
    public void testInstructionDrillDown(){

        String rootPath = "i";
        RMObject referenceNode = (RMObject) allTypesComposition
                .itemsAtPath("/content[openEHR-EHR-SECTION.test_all_types.v1]/items[at0001]/items[at0002]/items[openEHR-EHR-INSTRUCTION.test_all_types.v1]").get(0);

        String[] attributePaths = {
                "name",
                "name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "archetype_node_id",
                //TODO: https://github.com/ehrbase/project_management/issues/465
//                "language",
                "language/code_string",
                //TODO: https://github.com/ehrbase/openEHR_SDK/tree/feature/465_name_in_terminology_id
//                "language/terminology_id",
                "language/terminology_id/value",
                //TODO: https://github.com/ehrbase/openEHR_SDK/tree/feature/465_name_in_terminology_id
//                "encoding",
                "encoding/code_string",
                //TODO: https://github.com/ehrbase/openEHR_SDK/tree/feature/465_name_in_terminology_id
//                "encoding/terminology_id",
                //TODO: https://github.com/ehrbase/project_management/issues/490
//                "encoding/terminology_id/value",
//                //TODO: https://github.com/ehrbase/project_management/issues/488
//                "subject",
                //TODO: https://github.com/ehrbase/project_management/issues/495
//                "narrative",
                //TODO: https://github.com/ehrbase/project_management/issues/496
//                "narrative/value",
                //TODO: multiple discrepancies (extraneous _type, terminology name etc.
//                "activities[at0001]",
                //TODO: https://github.com/ehrbase/project_management/issues/497
//                "activities[at0001]/name",
//                "activities[at0001]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/498
//                "activities[at0001]/timing",
//                "activities[at0001]/timing/value",
//                "activities[at0001]/timing/formalism",
                //TODO:https://github.com/ehrbase/project_management/issues/499
//                "activities[at0001]/action_archetype_id",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "activities[at0001]/archetype_node_id",
                //TODO: multiple discrepancies (extraneous _type, terminology name etc.
//                "activities[at0001]/description[at0002]",
                //TODO: https://github.com/ehrbase/project_management/issues/500
//                "activities[at0001]/description[at0002]/name",
//                "activities[at0001]/description[at0002]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "activities[at0001]/description[at0002]/archetype_node_id",
                //TODO: multiple discrepancies (extraneous _type, terminology name etc.
//                "activities[at0001]/description[at0002]/items[at0003]",
                "activities[at0001]/description[at0002]/items[at0003]/name",
                "activities[at0001]/description[at0002]/items[at0003]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "activities[at0001]/description[at0002]/items[at0003]/archetype_node_id",
                //TODO: https://github.com/ehrbase/project_management/issues/485
//                "activities[at0001]/description[at0002]/items[at0003]/value",
                "activities[at0001]/description[at0002]/items[at0003]/value/value",
                //TODO: multiple discrepancies (extraneous _type, terminology name etc.
//                "activities[at0001]/description[at0002]/items[at0004]",
                //TODO:https://github.com/ehrbase/project_management/issues/485
//                "activities[at0001]/description[at0002]/items[at0004]/name",
                "activities[at0001]/description[at0002]/items[at0004]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "activities[at0001]/description[at0002]/items[at0004]/archetype_node_id",
                //TODO:https://github.com/ehrbase/project_management/issues/485
//                "activities[at0001]/description[at0002]/items[at0004]/value",
                "activities[at0001]/description[at0002]/items[at0004]/value/value",


        };

        for (String attributePath: attributePaths) {
            QueryResponseData result = performQuery(rootPath, attributePath,
                    "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                            " contains section s[openEHR-EHR-SECTION.test_all_types.v1]" +
                            " contains instruction i[openEHR-EHR-INSTRUCTION.test_all_types.v1]");

            List<Object> objectList = result.getRows().get(0);

            assertThat(valueObject(objectList.get(0)))
                    .as(rootPath+"/"+attributePath)
                    .isEqualTo(attributeValueAt(referenceNode, attributePath));
        }
    }

    @Test
    public void testActionDrillDown(){

        String rootPath = "a";
        RMObject referenceNode = (RMObject) allTypesComposition
                .itemsAtPath("/content[openEHR-EHR-SECTION.test_all_types.v1]/items[at0001]/items[at0002]/items[openEHR-EHR-ACTION.test_all_types.v1]").get(0);

        String[] attributePaths = {
                "name",
                "name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "archetype_node_id",
                //TODO: https://github.com/ehrbase/project_management/issues/465
//                "language",
                "language/code_string",
                //TODO: https://github.com/ehrbase/openEHR_SDK/tree/feature/465_name_in_terminology_id
//                "language/terminology_id",
                "language/terminology_id/value",
                //TODO: https://github.com/ehrbase/openEHR_SDK/tree/feature/465_name_in_terminology_id
//                "encoding",
                "encoding/code_string",
                //TODO: https://github.com/ehrbase/openEHR_SDK/tree/feature/465_name_in_terminology_id
//                "encoding/terminology_id",
                //TODO: https://github.com/ehrbase/project_management/issues/490
//                "encoding/terminology_id/value",
//                //TODO: https://github.com/ehrbase/project_management/issues/488
//                "subject",
//                //TODO:https://github.com/ehrbase/project_management/issues/495
//                "time",
                "time/value",
                //TODO:https://github.com/ehrbase/project_management/issues/501
//                "ism_transition",
//                "ism_transition/current_state",
//                "ism_transition/current_state/value",
//                "ism_transition/current_state/defining_code",
//                "ism_transition/current_state/defining_code/code_string",
//                "ism_transition/current_state/defining_code/terminology_id",
//                "ism_transition/current_state/defining_code/terminology_id/value",
                //TODO:https://github.com/ehrbase/project_management/issues/484
                //TODO:https://github.com/ehrbase/project_management/issues/502
//                "description[at0001]/items[openEHR-EHR-CLUSTER.test_all_types.v1]/items[at0001]/items[at0002]/items[at0003]",
                //TODO: https://github.com/ehrbase/project_management/issues/485
//                "description[at0001]/items[openEHR-EHR-CLUSTER.test_all_types.v1]/items[at0001]/items[at0002]/items[at0003]/value",
                //TODO:https://github.com/ehrbase/project_management/issues/491
//                "description[at0001]/items[openEHR-EHR-CLUSTER.test_all_types.v1]/items[at0001]/items[at0002]/items[at0003]/value/value"


        };

        for (String attributePath: attributePaths) {
            QueryResponseData result = performQuery(rootPath, attributePath,
                    "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                            " contains section s[openEHR-EHR-SECTION.test_all_types.v1]" +
                            " contains action a[openEHR-EHR-ACTION.test_all_types.v1]");

            List<Object> objectList = result.getRows().get(0);

            assertThat(valueObject(objectList.get(0)))
                    .as(rootPath+"/"+attributePath)
                    .isEqualTo(attributeValueAt(referenceNode, attributePath));
        }
    }

    @Test
    public void testAdminEntryDrillDown(){

        String rootPath = "a";
        RMObject referenceNode = (RMObject) allTypesComposition
                .itemsAtPath("/content[openEHR-EHR-SECTION.test_all_types.v1]/items[at0001]/items[openEHR-EHR-ADMIN_ENTRY.test_all_types.v1]").get(0);

        String[] attributePaths = {
                "name",
                "name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "archetype_node_id",
                //TODO: https://github.com/ehrbase/project_management/issues/465
//                "language",
                "language/code_string",
                //TODO: https://github.com/ehrbase/openEHR_SDK/tree/feature/465_name_in_terminology_id
//                "language/terminology_id",
                "language/terminology_id/value",
                //TODO: https://github.com/ehrbase/openEHR_SDK/tree/feature/465_name_in_terminology_id
//                "encoding",
                "encoding/code_string",
                //TODO: https://github.com/ehrbase/openEHR_SDK/tree/feature/465_name_in_terminology_id
//                "encoding/terminology_id",
                //TODO: https://github.com/ehrbase/project_management/issues/490
//                "encoding/terminology_id/value",
//                //TODO: https://github.com/ehrbase/project_management/issues/488
//                "subject",
                //TODO: https://github.com/ehrbase/project_management/issues/483
//                "data[at0001]/items[at0002]",
                //TODO: https://github.com/ehrbase/project_management/issues/492
//                "data[at0001]/items[at0002]/name",
//                "data[at0001]/items[at0002]/name/value",
                //TODO: https://github.com/ehrbase/project_management/issues/483
                //TODO: https://github.com/ehrbase/project_management/issues/500
                //TODO: https://github.com/ehrbase/project_management/issues/503
//                "data[at0001]/items[at0002]/value",
//                "data[at0001]/items[at0002]/value/value",



        };

        for (String attributePath: attributePaths) {
            QueryResponseData result = performQuery(rootPath, attributePath,
                    "composition c[openEHR-EHR-COMPOSITION.test_all_types.v1]" +
                            " contains section s[openEHR-EHR-SECTION.test_all_types.v1]" +
                            " contains ADMIN_ENTRY a[openEHR-EHR-ADMIN_ENTRY.test_all_types.v1]");

            List<Object> objectList = result.getRows().get(0);

            assertThat(valueObject(objectList.get(0)))
                    .as(rootPath+"/"+attributePath)
                    .isEqualTo(attributeValueAt(referenceNode, attributePath));
        }
    }

    private String buildAqlCompositionQuery(String rootPath, String attributePath, String containment){
        String aqlSelect = rootPath+"/"+attributePath;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select ");
        stringBuilder.append(aqlSelect);
        stringBuilder.append(" from EHR e[ehr_id/value = $ehr_id]");
        stringBuilder.append(" contains ");
        stringBuilder.append(containment);
        stringBuilder.append(" WHERE c/uid/value = $comp_uuid");

        return stringBuilder.toString();
    }

    private QueryResponseData performQuery(String rootPath, String attributePath, String containment){
        Query<Record1<Map>> query = Query.buildNativeQuery(
                buildAqlCompositionQuery(rootPath, attributePath, containment)
                , Map.class
        );

        return openEhrClient.aqlEndpoint().executeRaw(query,
                new ParameterValue("ehr_id", ehrUUID),
                new ParameterValue("comp_uuid", compositionUUID));
    }

}
