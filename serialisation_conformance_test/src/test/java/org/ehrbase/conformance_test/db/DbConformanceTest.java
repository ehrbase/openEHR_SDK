/*
 *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *
 *  This file is part of Project EHRbase
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.ehrbase.conformance_test.db;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.composition.Composition;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.assertj.core.api.SoftAssertions;
import org.ehrbase.conformance_test.templateprovider.TestDataTemplateProvider;
import org.ehrbase.serialisation.RMDataFormat;
import org.ehrbase.serialisation.dbencoding.CompositionSerializer;
import org.ehrbase.serialisation.dbencoding.rawjson.LightRawJsonEncoder;
import org.ehrbase.serialisation.flatencoding.FlatFormat;
import org.ehrbase.serialisation.flatencoding.FlatJasonProvider;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.ehrbase.test_data.composition.CompositionTestDataConformanceSDTJson;
import org.ehrbase.validation.CompositionValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.junit.Assert.assertNotNull;

class DbConformanceTest {

   private static final TestDataTemplateProvider TEMPLATE_PROVIDER = new TestDataTemplateProvider();

 private static List<Arguments> testRoundTripArguments(){
     List<Arguments> arguments = new ArrayList<>();

    Arrays.stream(CompositionTestDataConformanceSDTJson.values())
        .forEach(
            test -> {
              switch (test) {
                case EHRBASE_CONFORMANCE_DATA_TYPES_DV_DURATION:
                  arguments.add(
                      Arguments.of(
                          test,
                          new String[] {},
                          new String[] {},
                          new String[] {
                            // see https://github.com/openEHR/archie/issues/379
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0, 1]/data[at0001]/events[at0002, 1]/data[at0003]/items[at0018, 1]/value",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0, 1]/data[at0001]/events[at0002, 1]/data[at0003]/items[at0018, 1]/value/other_reference_ranges[1]/range/interval",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0, 1]/data[at0001]/events[at0002, 1]/data[at0003]/items[at0018, 1]/value/normal_range/interval"
                          }));
                  break;
                case EHRBASE_CONFORMANCE_DATA_TYPES_DV_DATE_TIME:
                  arguments.add(
                      Arguments.of(
                          test,
                          new String[] {},
                          new String[] {
                            // see https://jira.vitagroup.ag/browse/CDR-213
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_date_time|magnitude_status, value: ~",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_date_time|normal_status, value: N",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_date_time/_accuracy, value: P2DT9H52M",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_date_time/_normal_range/lower, value: 2022-01-12T13:22:34.000868+01:00",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_date_time/_normal_range/upper, value: 2022-02-12T13:22:34.000868+01:00",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_date_time/_other_reference_ranges:0/lower, value: 2022-02-12T13:22:34.000868+01:00",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_date_time/_other_reference_ranges:0/upper, value: 2022-03-12T13:22:34.000868+01:00",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_date_time/_other_reference_ranges:0/meaning, value: high"
                          },
                          new String[] {}));
                  break;
                case EHRBASE_CONFORMANCE_ELEMENT_NULL_FLAVOR:
                  arguments.add(
                      Arguments.of(
                          test,
                          new String[] {},
                          new String[] {
                            // see https://jira.vitagroup.ag/browse/CDR-222
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_null_reason, value: sample reason"
                          },
                          new String[] {}));
                  break;

                case EHRBASE_CONFORMANCE_OBSERVATION:
                case EHRBASE_CONFORMANCE_FEEDER_AUDIT_MULTIMEDIA:
                  arguments.add(
                      Arguments.of(
                          test,
                          new String[] {},
                          new String[] {
                            // see https://jira.vitagroup.ag/browse/CDR-219
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/_guideline_id|type, value: GUIDELINE",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/_guideline_id|namespace, value: HOSPITAL-NS",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/_guideline_id|id, value: 3445",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/_guideline_id|id_scheme, value: HOSPITAL-NS",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/_work_flow_id|type, value: WORKFLOW",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/_work_flow_id|namespace, value: HOSPITAL-NS",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/_work_flow_id|id, value: 335645",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/_work_flow_id|id_scheme, value: HOSPITAL-NS"
                          },
                          new String[] {
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0, 1]/workflow_id/id",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0, 1]/workflow_id/type",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0, 1]/workflow_id/namespace",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0, 1]/guideline_id/namespace",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0, 1]/guideline_id/id",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0, 1]/guideline_id/type"
                          }));
                  break;

                case EHRBASE_CONFORMANCE_EVALUATION:
                  arguments.add(
                      Arguments.of(
                          test,
                          new String[] {},
                          new String[] {
                            // see https://jira.vitagroup.ag/browse/CDR-219
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_evaluation/_guideline_id|type, value: GUIDELINE",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_evaluation/_guideline_id|namespace, value: HOSPITAL-NS",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_evaluation/_guideline_id|id, value: 3445",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_evaluation/_guideline_id|id_scheme, value: HOSPITAL-NS",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_evaluation/_work_flow_id|type, value: WORKFLOW",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_evaluation/_work_flow_id|namespace, value: HOSPITAL-NS",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_evaluation/_work_flow_id|id, value: 335645",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_evaluation/_work_flow_id|id_scheme, value: HOSPITAL-NS"
                          },
                          new String[] {
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-EVALUATION.conformance_evaluation.v0, 1]/workflow_id/id",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-EVALUATION.conformance_evaluation.v0, 1]/workflow_id/type",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-EVALUATION.conformance_evaluation.v0, 1]/workflow_id/namespace",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-EVALUATION.conformance_evaluation.v0, 1]/guideline_id/namespace",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-EVALUATION.conformance_evaluation.v0, 1]/guideline_id/id",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-EVALUATION.conformance_evaluation.v0, 1]/guideline_id/type"
                          }));
                  break;
                case EHRBASE_CONFORMANCE_ACTION:
                  arguments.add(
                      Arguments.of(
                          test,
                          new String[] {},
                          new String[] {
                            // see https://jira.vitagroup.ag/browse/CDR-219
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_action/_guideline_id|type, value: GUIDELINE",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_action/_guideline_id|namespace, value: HOSPITAL-NS",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_action/_guideline_id|id, value: 3445",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_action/_guideline_id|id_scheme, value: HOSPITAL-NS",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_action/_work_flow_id|type, value: WORKFLOW",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_action/_work_flow_id|namespace, value: HOSPITAL-NS",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_action/_work_flow_id|id, value: 335645",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_action/_work_flow_id|id_scheme, value: HOSPITAL-NS"
                          },
                          new String[] {
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-ACTION.conformance_action_.v0, 1]/workflow_id/id",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-ACTION.conformance_action_.v0, 1]/workflow_id/type",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-ACTION.conformance_action_.v0, 1]/workflow_id/namespace",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-ACTION.conformance_action_.v0, 1]/guideline_id/namespace",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-ACTION.conformance_action_.v0, 1]/guideline_id/id",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-ACTION.conformance_action_.v0, 1]/guideline_id/type"
                          }));
                  break;
                case EHRBASE_CONFORMANCE_INSTRUCTION:
                  arguments.add(
                      Arguments.of(
                          test,
                          new String[] {},
                          new String[] {
                            // see https://jira.vitagroup.ag/browse/CDR-219
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_instruction/_guideline_id|type, value: GUIDELINE",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_instruction/_guideline_id|namespace, value: HOSPITAL-NS",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_instruction/_guideline_id|id, value: 3445",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_instruction/_guideline_id|id_scheme, value: HOSPITAL-NS",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_instruction/_work_flow_id|type, value: WORKFLOW",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_instruction/_work_flow_id|namespace, value: HOSPITAL-NS",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_instruction/_work_flow_id|id, value: 335645",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_instruction/_work_flow_id|id_scheme, value: HOSPITAL-NS",
                            // https://jira.vitagroup.ag/browse/CDR-221
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_instruction/_wf_definition|value, value: wf_definition",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_instruction/_wf_definition|formalism, value: formalism"
                          },
                          new String[] {
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-INSTRUCTION.conformance_instruction.v0, 1]/workflow_id/id",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-INSTRUCTION.conformance_instruction.v0, 1]/workflow_id/type",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-INSTRUCTION.conformance_instruction.v0, 1]/workflow_id/namespace",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-INSTRUCTION.conformance_instruction.v0, 1]/guideline_id/namespace",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-INSTRUCTION.conformance_instruction.v0, 1]/guideline_id/id",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-INSTRUCTION.conformance_instruction.v0, 1]/guideline_id/type"
                          }));
                  break;

                case EHRBASE_CONFORMANCE_ADMIN_ENTRY:
                  arguments.add(
                      Arguments.of(
                          test,
                          new String[] {},
                          new String[] {
                            // see https://jira.vitagroup.ag/browse/CDR-219

                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_admin_entry/_work_flow_id|type, value: WORKFLOW",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_admin_entry/_work_flow_id|namespace, value: HOSPITAL-NS",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_admin_entry/_work_flow_id|id, value: 335645",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_admin_entry/_work_flow_id|id_scheme, value: HOSPITAL-NS"
                          },
                          new String[] {
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-ADMIN_ENTRY.conformance_admin_entry.v0, 1]/workflow_id/id",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-ADMIN_ENTRY.conformance_admin_entry.v0, 1]/workflow_id/type",
                            "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-ADMIN_ENTRY.conformance_admin_entry.v0, 1]/workflow_id/namespace"
                          }));
                  break;

                case EHRBASE_CONFORMANCE_SECTION:
                  arguments.add(
                      Arguments.of(
                          test,
                          new String[] {},
                          new String[] {
                            // see https://jira.vitagroup.ag/browse/CDR-220
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_uid, value: 9fcc1c70-9349-444d-b9cb-8fa817697f5e",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_link:0|type, value: problem",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_link:0|meaning, value: problem related note",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_link:0|target, value: ehr://ehr.network/347a5490-55ee-4da9-b91a-9bba710f730e",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_audit|version_id, value: final",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_audit|system_id, value: orig",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_audit/location|id, value: 12342341",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_audit/location|id_namespace, value: uk.org.nmc",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_audit/location|id_scheme, value: NMC",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_audit/location|name, value: Org 1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_audit/subject|id, value: 456",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_audit/subject|id_namespace, value: uk.org.nmc",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_audit/subject|id_scheme, value: NMC",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_audit/subject|name, value: Per 1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_audit/provider|id, value: 456",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_audit/provider|id_namespace, value: uk.org.nmc",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_audit/provider|id_scheme, value: NMC",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_audit/provider|name, value: Per 1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_audit|time, value: 2021-12-21T16:02:58.0094262+01:00",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_item_id:0|id, value: id1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_item_id:0|issuer, value: issuer1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_item_id:0|assigner, value: assigner1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_item_id:0|type, value: PERSON",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_item_id:1|id, value: id2",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_item_id:1|issuer, value: issuer2",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_item_id:1|assigner, value: assigner2",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/originating_system_item_id:1|type, value: PERSON",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/original_content, value: Hello world!",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/original_content|formalism, value: text/plain",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/feeder_system_item_id:0|id, value: id1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/feeder_system_item_id:0|issuer, value: issuer1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/feeder_system_item_id:0|assigner, value: assigner1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/feeder_system_item_id:0|type, value: PERSON",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/feeder_system_item_id:1|id, value: id2",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/feeder_system_item_id:1|issuer, value: issuer2",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/feeder_system_item_id:1|assigner, value: assigner2",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/_feeder_audit/feeder_system_item_id:1|type, value: PERSON"
                          },
                          new String[] {}));
                  break;
                case EHRBASE_CONFORMANCE_CLUSTER:
                  arguments.add(
                      Arguments.of(
                          test,
                          new String[] {},
                          new String[] {
                            // see https://jira.vitagroup.ag/browse/CDR-215
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_audit|version_id, value: final",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_audit|system_id, value: orig",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_audit/location|id, value: 12342341",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_audit/location|id_namespace, value: uk.org.nmc",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_audit/location|id_scheme, value: NMC",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_audit/location|name, value: Org 1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_audit/subject|id, value: 456",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_audit/subject|id_namespace, value: uk.org.nmc",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_audit/subject|id_scheme, value: NMC",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_audit/subject|name, value: Per 1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_audit/provider|id, value: 456",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_audit/provider|id_namespace, value: uk.org.nmc",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_audit/provider|id_scheme, value: NMC",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_audit/provider|name, value: Per 1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_audit|time, value: 2021-12-21T16:02:58.0094262+01:00",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_item_id:0|id, value: id1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_item_id:0|issuer, value: issuer1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_item_id:0|assigner, value: assigner1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_item_id:0|type, value: PERSON",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_item_id:1|id, value: id2",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_item_id:1|issuer, value: issuer2",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_item_id:1|assigner, value: assigner2",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/originating_system_item_id:1|type, value: PERSON",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/original_content, value: Hello world!",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/original_content|formalism, value: text/plain",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/feeder_system_item_id:0|id, value: id1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/feeder_system_item_id:0|issuer, value: issuer1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/feeder_system_item_id:0|assigner, value: assigner1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/feeder_system_item_id:0|type, value: PERSON",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/feeder_system_item_id:1|id, value: id2",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/feeder_system_item_id:1|issuer, value: issuer2",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/feeder_system_item_id:1|assigner, value: assigner2",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_feeder_audit/feeder_system_item_id:1|type, value: PERSON",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_uid, value: 9fcc1c70-9349-444d-b9cb-8fa817697f5e",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_link:0|type, value: problem",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_link:0|meaning, value: problem related note",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/conformance_cluster/_link:0|target, value: ehr://ehr.network/347a5490-55ee-4da9-b91a-9bba710f730e"
                          },
                          new String[] {}));
                  break;
                case EHRBASE_CONFORMANCE_ELEMENT_FEEDER_AUDIT:
                  arguments.add(
                      Arguments.of(
                          test,
                          new String[] {},
                          new String[] {
                            // see https://jira.vitagroup.ag/browse/CDR-215
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_uid, value: 9fcc1c70-9349-444d-b9cb-8fa817697f5e",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_link:0|type, value: problem",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_link:0|meaning, value: problem related note",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_link:0|target, value: ehr://ehr.network/347a5490-55ee-4da9-b91a-9bba710f730e",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_audit|version_id, value: final",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_audit|system_id, value: orig",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_audit/location|id, value: 12342341",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_audit/location|id_namespace, value: uk.org.nmc",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_audit/location|id_scheme, value: NMC",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_audit/location|name, value: Org 1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_audit/subject|id, value: 456",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_audit/subject|id_namespace, value: uk.org.nmc",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_audit/subject|id_scheme, value: NMC",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_audit/subject|name, value: Per 1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_audit/provider|id, value: 456",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_audit/provider|id_namespace, value: uk.org.nmc",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_audit/provider|id_scheme, value: NMC",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_audit/provider|name, value: Per 1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_audit|time, value: 2021-12-21T16:02:58.0094262+01:00",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_item_id:0|id, value: id1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_item_id:0|issuer, value: issuer1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_item_id:0|assigner, value: assigner1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_item_id:0|type, value: PERSON",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_item_id:1|id, value: id2",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_item_id:1|issuer, value: issuer2",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_item_id:1|assigner, value: assigner2",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/originating_system_item_id:1|type, value: PERSON",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/original_content, value: Hello world!",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/original_content|formalism, value: text/plain",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/feeder_system_item_id:0|id, value: id1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/feeder_system_item_id:0|issuer, value: issuer1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/feeder_system_item_id:0|assigner, value: assigner1",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/feeder_system_item_id:0|type, value: PERSON",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/feeder_system_item_id:1|id, value: id2",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/feeder_system_item_id:1|issuer, value: issuer2",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/feeder_system_item_id:1|assigner, value: assigner2",
                            "Extra path: conformance-ehrbase.de.v0/conformance_section/conformance_observation/any_event:0/dv_quantity/_feeder_audit/feeder_system_item_id:1|type, value: PERSON"
                          },
                          new String[] {}));
                  break;
                default:
                  arguments.add(
                      Arguments.of(test, new String[] {}, new String[] {}, new String[] {}));
              }
            });

     return arguments;
 }

   @ParameterizedTest
    @MethodSource("testRoundTripArguments")
    void testRoundTrip(
           CompositionTestDataConformanceSDTJson testData, String[] expectedMissing, String[] expectedExtra, String[] expectedValidationErrorPath)
           throws IOException {



       String templateId = testData.getTemplate().getTemplateId();

       RMDataFormat cut =
               new FlatJasonProvider(TEMPLATE_PROVIDER).buildFlatJson(FlatFormat.SIM_SDT, templateId);

       String flat = IOUtils.toString(testData.getStream(), StandardCharsets.UTF_8);
       Composition composition = cut.unmarshal(flat);


       CompositionSerializer compositionSerializerRawJson = new CompositionSerializer();

       String db_encoded = compositionSerializerRawJson.dbEncode(composition);

       assertNotNull(db_encoded);

       String converted = new LightRawJsonEncoder(db_encoded).encodeCompositionAsString();

       assertNotNull(converted);

       Composition actual = new CanonicalJson().unmarshal(converted, Composition.class);



       SoftAssertions softAssertions = new SoftAssertions();

       softAssertions.assertThat(actual).isNotNull();

       CompositionValidator rmObjectValidator =
               new CompositionValidator();

       softAssertions.assertThat(rmObjectValidator.validate(actual, TEMPLATE_PROVIDER.buildIntrospect(templateId).orElseThrow()))
               // are stored in deducted tables and thus are not serialised
               .filteredOn(d -> !List.of("/composer","/language","/category","/territory").contains(d.getAqlPath()))
               // archetype information for composition stored in a deducted  table and thus is not serialised
               .filteredOn(d -> !(Objects.equals("/", d.getAqlPath()) && d.getMessage().equals("Invariant Is_archetype_root failed on type COMPOSITION")))
               .filteredOn(d -> !ArrayUtils.contains(expectedValidationErrorPath, d.getAqlPath()))
               .isEmpty();

       String actualString = cut.marshal(actual);

       String expected = IOUtils.toString(testData.getStream(), StandardCharsets.UTF_8);

       List<String> errors = compere(actualString, expected);

    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("Missing"))
        .containsExactlyInAnyOrder(expectedMissing);

       softAssertions
               .assertThat(errors)
               // are stored in deducted tables and thus are not serialised
               .filteredOn(
                       s ->
                               !List.of(
                                               "Extra path: conformance-ehrbase.de.v0/_uid, value: 6e3a9506-b81c-4d74-a37f-1464fb7106b2::piri.ehrscape.com::1",
                                               "Extra path: conformance-ehrbase.de.v0/language|code, value: en",
                                               "Extra path: conformance-ehrbase.de.v0/language|terminology, value: ISO_639-1",
                                               "Extra path: conformance-ehrbase.de.v0/territory|code, value: US",
                                               "Extra path: conformance-ehrbase.de.v0/territory|terminology, value: ISO_3166-1",
                                               "Extra path: conformance-ehrbase.de.v0/category|code, value: 433",
                                               "Extra path: conformance-ehrbase.de.v0/category|value, value: event",
                                               "Extra path: conformance-ehrbase.de.v0/category|terminology, value: openehr",
                                               "Extra path: conformance-ehrbase.de.v0/context/_health_care_facility|id, value: 9091",
                                               "Extra path: conformance-ehrbase.de.v0/context/_health_care_facility|id_scheme, value: HOSPITAL-NS",
                                               "Extra path: conformance-ehrbase.de.v0/context/_health_care_facility|id_namespace, value: HOSPITAL-NS",
                                               "Extra path: conformance-ehrbase.de.v0/context/_health_care_facility|name, value: Hospital",
                                               "Extra path: conformance-ehrbase.de.v0/context/start_time, value: 2021-12-21T14:19:31.649613+01:00",
                                               "Extra path: conformance-ehrbase.de.v0/context/_end_time, value: 2021-12-21T15:19:31.649613+01:00",
                                               "Extra path: conformance-ehrbase.de.v0/context/_location, value: 2021-12-21T15:19:31.649613+01:00",
                                               "Extra path: conformance-ehrbase.de.v0/context/setting|code, value: 238",
                                               "Extra path: conformance-ehrbase.de.v0/context/setting|value, value: other care",
                                               "Extra path: conformance-ehrbase.de.v0/context/setting|terminology, value: openehr",
                                               "Extra path: conformance-ehrbase.de.v0/composer|name, value: Silvia Blake",
                                               "Extra path: conformance-ehrbase.de.v0/composer|id, value: 1234-5678",
                                               "Extra path: conformance-ehrbase.de.v0/composer|id_scheme, value: UUID",
                                               "Extra path: conformance-ehrbase.de.v0/composer|id_namespace, value: EHR.NETWORK",
                                               "Extra path: conformance-ehrbase.de.v0/context/_participation:0|function, value: requester",
                                               "Extra path: conformance-ehrbase.de.v0/context/_participation:0|mode, value: face-to-face communication",
                                               "Extra path: conformance-ehrbase.de.v0/context/_participation:0|name, value: Dr. Marcus Johnson",
                                               "Extra path: conformance-ehrbase.de.v0/context/_participation:0|id, value: 199",
                                               "Extra path: conformance-ehrbase.de.v0/context/_participation:0|id_scheme, value: HOSPITAL-NS",
                                               "Extra path: conformance-ehrbase.de.v0/context/_participation:0|id_namespace, value: HOSPITAL-NS",
                                               "Extra path: conformance-ehrbase.de.v0/_link:0|type, value: problem",
                                               "Extra path: conformance-ehrbase.de.v0/_link:0|meaning, value: problem related note",
                                               "Extra path: conformance-ehrbase.de.v0/_link:0|target, value: ehr://ehr.network/347a5490-55ee-4da9-b91a-9bba710f730e"
                                       )
                                       .contains(s))
               .filteredOn(s -> s.startsWith("Extra"))
               .containsExactlyInAnyOrder(expectedExtra);

       softAssertions.assertAll();
   }

   private  List<String> compere(String actualJson, String expectedJson)
           throws JsonProcessingException {
       List<String> errors = new ArrayList<>();
       ObjectMapper objectMapper = JacksonUtil.getObjectMapper();

       Map<String, Object> actual = objectMapper.readValue(actualJson, Map.class);
       Map<String, Object> expected = objectMapper.readValue(expectedJson, Map.class);

       actual.forEach(
               (key, value) -> {
                   if (!expected.containsKey(key) || !expected.get(key).equals(value)) {
                       errors.add(String.format("Missing path: %s, value: %s", key, value));
                   }
               });

       expected.forEach(
               (key, value) -> {
                   if (!actual.containsKey(key) || !actual.get(key).equals(value)) {
                       errors.add(String.format("Extra path: %s, value: %s", key, value));
                   }
               });

       return errors;
   }
}
