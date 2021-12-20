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

package org.ehrbase.webtemplate.tests;

import care.better.platform.web.template.WorkflowIdTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.Entry;
import com.nedap.archie.rm.composition.Section;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.ObjectId;
import com.nedap.archie.rm.support.identification.ObjectRef;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.serialisation.RMDataFormat;
import org.ehrbase.serialisation.flatencoding.FlatFormat;
import org.ehrbase.serialisation.flatencoding.FlatJasonProvider;
import org.junit.Test;
import org.openehr.schemas.v1.TemplateDocument;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkflowIdTestOverwrite extends WorkflowIdTest {

  public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Override
  @Test
  public void workflowIdInCtx() throws Exception {
    String template = this.getFileContent("/res/Demo Vitals.opt");
    Map<String, Object> flatComposition =
        ImmutableMap.<String, Object>builder()
            .put("ctx/language", "sl")
            .put("ctx/territory", "SI")
            .put("ctx/composer_name", "Composer")
            .put("ctx/id_scheme", "ispek")
            .put("ctx/id_namespace", "ispek")
            .put("ctx/end_time", "2016-01-01T12:30:30Z")
            .put("ctx/work_flow_id|id", "wf_id")
            .put("ctx/work_flow_id|namespace", "wf_ns")
            .put("ctx/work_flow_id|id_scheme", "wf_scheme")
            .put("ctx/work_flow_id|type", "wf_type")
            .put("vitals/vitals/haemoglobin_a1c/any_event/test_status|terminology", "local")
            .put("vitals/vitals/haemoglobin_a1c/any_event/test_status|code", "at0037")
            .build();

    RMDataFormat flatJson = getFlatJson(template, FlatFormat.SIM_SDT);
    Composition composition = flatJson.unmarshal(OBJECT_MAPPER.writeValueAsString(flatComposition));

    Entry contentItem = (Entry) ((Section) composition.getContent().get(0)).getItems().get(0);

    ObjectRef<? extends ObjectId> workflowId = contentItem.getWorkflowId();

    assertThat(workflowId).isNotNull();
    assertThat(workflowId.getId())
        .hasSameClassAs(new GenericId())
        .extracting(ObjectId::getValue, i -> ((GenericId) i).getScheme())
        .containsExactly("wf_id", "wf_scheme");
    assertThat(workflowId.getNamespace()).isEqualTo("wf_ns");
    assertThat(workflowId.getType()).isEqualTo("wf_type");

    Map<String, Object> map = OBJECT_MAPPER.readValue(flatJson.marshal(composition), Map.class);

    assertThat(map)
        .containsEntry("vitals/vitals/haemoglobin_a1c:0/_work_flow_id|id", "wf_id")
        .containsEntry("vitals/vitals/haemoglobin_a1c:0/_work_flow_id|id_scheme", "wf_scheme")
        .containsEntry("vitals/vitals/haemoglobin_a1c:0/_work_flow_id|type", "wf_type")
        .containsEntry("vitals/vitals/haemoglobin_a1c:0/_work_flow_id|namespace", "wf_ns");
  }

  @Override
  @Test
  public void workflowIdDirect() throws Exception {
    String template = this.getFileContent("/res/Demo Vitals.opt");

    OffsetDateTime dateTime =
        ZonedDateTime.of(2015, 1, 1, 10, 31, 16, 0, ZoneId.systemDefault()).toOffsetDateTime();

    Map<String, Object> flatComposition =
        ImmutableMap.<String, Object>builder()
            .put("ctx/language", "sl")
            .put("ctx/territory", "SI")
            .put("ctx/composer_name", "Composer")
            .put("ctx/id_scheme", "ispek")
            .put("ctx/id_namespace", "ispek")
            .put("ctx/end_time", "2016-01-01T12:30:30Z")
            .put(
                "vitals/vitals/haemoglobin_a1c/history_origin",
                DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(dateTime))
            .put("vitals/vitals/haemoglobin_a1c/any_event/test_status|terminology", "local")
            .put("vitals/vitals/haemoglobin_a1c/any_event/test_status|code", "at0037")
            .put("vitals/vitals/haemoglobin_a1c/_work_flow_id|id", "1")
            .put("vitals/vitals/haemoglobin_a1c/_work_flow_id|id_scheme", "x")
            .put("vitals/vitals/haemoglobin_a1c/_work_flow_id|namespace", "y")
            .put("vitals/vitals/haemoglobin_a1c/_work_flow_id|type", "wf")
            .build();

    RMDataFormat flatJson = getFlatJson(template, FlatFormat.SIM_SDT);
    Composition composition = flatJson.unmarshal(OBJECT_MAPPER.writeValueAsString(flatComposition));

    Entry contentItem = (Entry) ((Section) composition.getContent().get(0)).getItems().get(0);

    ObjectRef<? extends ObjectId> workflowId = contentItem.getWorkflowId();

    assertThat(workflowId).isNotNull();
    assertThat(workflowId.getId())
        .hasSameClassAs(new GenericId())
        .extracting(ObjectId::getValue, i -> ((GenericId) i).getScheme())
        .containsExactly("1", "x");
    assertThat(workflowId.getNamespace()).isEqualTo("y");
    assertThat(workflowId.getType()).isEqualTo("wf");

    Map<String, Object> map = OBJECT_MAPPER.readValue(flatJson.marshal(composition), Map.class);

    assertThat(map)
        .containsEntry("vitals/vitals/haemoglobin_a1c:0/_work_flow_id|id", "1")
        .containsEntry("vitals/vitals/haemoglobin_a1c:0/_work_flow_id|id_scheme", "x")
        .containsEntry("vitals/vitals/haemoglobin_a1c:0/_work_flow_id|type", "wf")
        .containsEntry("vitals/vitals/haemoglobin_a1c:0/_work_flow_id|namespace", "y");
  }

  @Override
  @Test
  public void workflowIdInCtxAndDirect() throws Exception {
    String template = this.getFileContent("/res/Demo Vitals.opt");

    Map<String, Object> flatComposition =
        ImmutableMap.<String, Object>builder()
            .put("ctx/language", "sl")
            .put("ctx/territory", "SI")
            .put("ctx/composer_name", "Composer")
            .put("ctx/id_scheme", "ispek")
            .put("ctx/id_namespace", "ispek")
            .put("ctx/end_time", "2016-01-01T12:30:30Z")
            .put("ctx/work_flow_id|id", "wf_id")
            .put("ctx/work_flow_id|namespace", "wf_ns")
            .put("ctx/work_flow_id|id_scheme", "wf_scheme")
            .put("ctx/work_flow_id|type", "wf_type")
            .put("vitals/vitals/haemoglobin_a1c/any_event/test_status|terminology", "local")
            .put("vitals/vitals/haemoglobin_a1c/any_event/test_status|code", "at0037")
            .put("vitals/vitals/haemoglobin_a1c:1/any_event/test_status|terminology", "local")
            .put("vitals/vitals/haemoglobin_a1c:1/any_event/test_status|code", "at0037")
            .put("vitals/vitals/haemoglobin_a1c:1/_work_flow_id|id", "1")
            .put("vitals/vitals/haemoglobin_a1c:1/_work_flow_id|id_scheme", "x")
            .put("vitals/vitals/haemoglobin_a1c:1/_work_flow_id|namespace", "y")
            .put("vitals/vitals/haemoglobin_a1c:1/_work_flow_id|type", "wf")
            .build();

    RMDataFormat flatJson = getFlatJson(template, FlatFormat.SIM_SDT);
    Composition composition = flatJson.unmarshal(OBJECT_MAPPER.writeValueAsString(flatComposition));

    Entry contentItem1 = (Entry) ((Section) composition.getContent().get(0)).getItems().get(0);

    ObjectRef<? extends ObjectId> workflowId1 = contentItem1.getWorkflowId();

    assertThat(workflowId1).isNotNull();
    assertThat(workflowId1.getId())
        .hasSameClassAs(new GenericId())
        .extracting(ObjectId::getValue, i -> ((GenericId) i).getScheme())
        .containsExactly("wf_id", "wf_scheme");
    assertThat(workflowId1.getNamespace()).isEqualTo("wf_ns");
    assertThat(workflowId1.getType()).isEqualTo("wf_type");

    Entry contentItem2 = (Entry) ((Section) composition.getContent().get(0)).getItems().get(1);

    ObjectRef<? extends ObjectId> workflowId2 = contentItem2.getWorkflowId();

    assertThat(workflowId2).isNotNull();
    assertThat(workflowId2.getId())
        .hasSameClassAs(new GenericId())
        .extracting(ObjectId::getValue, i -> ((GenericId) i).getScheme())
        .containsExactly("1", "x");
    assertThat(workflowId2.getNamespace()).isEqualTo("y");
    assertThat(workflowId2.getType()).isEqualTo("wf");

    Map<String, Object> map = OBJECT_MAPPER.readValue(flatJson.marshal(composition), Map.class);

    assertThat(map)
        .containsEntry("vitals/vitals/haemoglobin_a1c:0/_work_flow_id|id", "wf_id")
        .containsEntry("vitals/vitals/haemoglobin_a1c:0/_work_flow_id|id_scheme", "wf_scheme")
        .containsEntry("vitals/vitals/haemoglobin_a1c:0/_work_flow_id|type", "wf_type")
        .containsEntry("vitals/vitals/haemoglobin_a1c:0/_work_flow_id|namespace", "wf_ns")
        .containsEntry("vitals/vitals/haemoglobin_a1c:1/_work_flow_id|id", "1")
        .containsEntry("vitals/vitals/haemoglobin_a1c:1/_work_flow_id|id_scheme", "x")
        .containsEntry("vitals/vitals/haemoglobin_a1c:1/_work_flow_id|type", "wf")
        .containsEntry("vitals/vitals/haemoglobin_a1c:1/_work_flow_id|namespace", "y");
  }

  private RMDataFormat getFlatJson(String template, FlatFormat flatFormat)
      throws XmlException, IOException {
    TemplateDocument templateDocument =
        TemplateDocument.Factory.parse(IOUtils.toInputStream(template, StandardCharsets.UTF_8));

    RMDataFormat flatJson =
        new FlatJasonProvider(t -> Optional.ofNullable(templateDocument.getTemplate()))
            .buildFlatJson(flatFormat, templateDocument.getTemplate().getTemplateId().getValue());
    return flatJson;
  }
}
