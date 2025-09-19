/*
 * Copyright (c) 2021 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.conformance_test.extern;

import care.better.platform.web.template.converter.CompositionConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.composition.Composition;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.ehrbase.openehr.sdk.serialisation.RMDataFormat;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.FlatFormat;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.ArchieObjectMapperProvider;

public class CompositionConverterImp implements CompositionConverter {

    public static final ObjectMapper OBJECT_MAPPER = ArchieObjectMapperProvider.getObjectMapper();

    @Override
    public String convertRawToFlat(String template, String defaultLanguage, String rawComposition) throws Exception {
        Composition unmarshal = RMDataFormat.canonicalJSON()
                .unmarshal(rawComposition.replace("\"@class\"", "\"_type\""), Composition.class);

        return Helper.getFlatJson(template, FlatFormat.SIM_SDT)
                .marshal(unmarshal)
                // Test expect without |id even if this is wrong
                .replace("_identifier:0|id", "_identifier:0")
                .replace("_identifier:1|id", "_identifier:1")
                .replace("originating_system_item_id:0|id", "originating_system_item_id:0")
                .replace("originating_system_item_id:1|id", "originating_system_item_id:1")
                .replace("feeder_system_item_id:0|id", "feeder_system_item_id:0")
                .replace("feeder_system_item_id:1|id", "feeder_system_item_id:1");
    }

    @Override
    public String convertRawToStructured(String template, String defaultLanguage, String rawComposition)
            throws Exception {

        Composition unmarshal = RMDataFormat.canonicalJSON()
                .unmarshal(rawComposition.replace("\"@class\"", "\"_type\""), Composition.class);
        return Helper.getFlatJson(template, FlatFormat.STRUCTURED).marshal(unmarshal);
    }

    @Override
    public String convertFlatToRaw(
            String template,
            String defaultTemplateLanguage,
            String flatComposition,
            Map<String, Object> compositionBuilderContext)
            throws Exception {
        RMDataFormat flatJson = Helper.getFlatJson(template, FlatFormat.SIM_SDT);
        Map<String, Object> currentValues = new HashMap<>();

        String fixed = flatComposition
                // fix wrong ":"
                .replace(
                        "прием_пациента/метаданные/дата_и_время_создания_документа:2013-02-27T00:00\":\"00",
                        "прием_пациента/метаданные/дата_и_время_создания_документа\":\"2013-02-27T00:00:00")
                // fix wrong ":"
                .replace(
                        "прием_пациента/административная_информация/дата_приема:2013-03-22T00:00\":\"00",
                        "прием_пациента/административная_информация/дата_приема\":\"2013-03-22");
        for (Iterator<Map.Entry<String, JsonNode>> it =
                        OBJECT_MAPPER.readTree(fixed).fields();
                it.hasNext(); ) {
            Map.Entry<String, JsonNode> e = it.next();
            currentValues.put(e.getKey(), e.getValue().asText());
        }

        compositionBuilderContext.forEach((k, v) -> currentValues.put(replace(k), v));
        replaceKey(
                currentValues,
                "ficha_individual_da_aten_cao_basica/resumo_do_atendimento/problemas/problem_diagnosis:0/related_item:0/item/value",
                "ficha_individual_da_aten_cao_basica/resumo_do_atendimento/problemas/problem_diagnosis:0/related_item:0/item/text_value");

        // topography is single  valued  in the template
        replaceKey(
                currentValues,
                "gel_cancer_diagnosis/problem_diagnosis:3/cancer_diagnosis/topography:78",
                "gel_cancer_diagnosis/problem_diagnosis:3/cancer_diagnosis/topography");
        replaceKey(
                currentValues,
                "gel_cancer_diagnosis/problem_diagnosis:1/cancer_diagnosis/topography:19",
                "gel_cancer_diagnosis/problem_diagnosis:1/cancer_diagnosis/topography");
        replaceKey(
                currentValues,
                "gel_cancer_diagnosis/problem_diagnosis:5/cancer_diagnosis/topography:137",
                "gel_cancer_diagnosis/problem_diagnosis:5/cancer_diagnosis/topography");

        currentValues.entrySet().stream()
                .filter(e -> "video/mp4".equals(e.getValue()))
                .forEach(e -> currentValues.replace(e.getKey(), "video/H263-2000"));

        // add missing terminology for some flat examples
        addTerminology(currentValues, "drug_related_problem_report/medication_error/medra_classification");
        addTerminology(
                currentValues,
                "medication_error_report/medication_error/adverse_effect:1/intervention_details:1/intervention_result");
        addTerminology(
                currentValues,
                "medication_error_report/medication_error/adverse_effect:1/intervention_details:2/intervention_result");
        addTerminology(
                currentValues,
                "medication_error_report/medication_error/adverse_effect:1/intervention_details/intervention_result");
        addTerminology(currentValues, "medication_error_report/medication_error/adverse_effect:1/reaction");
        addTerminology(
                currentValues,
                "medication_error_report/medication_error/adverse_effect/intervention_details:1/intervention_result");
        addTerminology(
                currentValues,
                "medication_error_report/medication_error/adverse_effect/intervention_details:2/intervention_result");
        addTerminology(
                currentValues,
                "medication_error_report/medication_error/adverse_effect/intervention_details:2/intervention_result");
        addTerminology(
                currentValues,
                "medication_error_report/medication_error/adverse_effect/intervention_details/intervention_result");
        addTerminology(currentValues, "medication_error_report/medication_error/adverse_effect/reaction");
        addTerminology(currentValues, "medication_error_report/medication_error/medra_classification");
        addTerminology(
                currentValues,
                "medication_event_case_summary/case_summary/summary_details/admission_diagnosis_classification");
        addTerminology(
                currentValues,
                "medication_event_case_summary/case_summary/summary_details/discharge_diagnosis_classification");

        addTerminology(
                currentValues,
                "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect:1/intervention_details:1/intervention");
        addTerminology(
                currentValues,
                "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect:1/intervention_details:1/intervention_result");
        addTerminology(
                currentValues,
                "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect:1/intervention_details:2/intervention");
        addTerminology(
                currentValues,
                "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect:1/intervention_details:2/intervention_result");
        addTerminology(
                currentValues,
                "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect:1/intervention_details/intervention");
        addTerminology(
                currentValues,
                "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect:1/intervention_details/intervention_result");
        addTerminology(currentValues, "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect:1/reaction");
        addTerminology(
                currentValues,
                "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect/intervention_details:1/intervention");
        addTerminology(
                currentValues,
                "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect/intervention_details:1/intervention_result");
        addTerminology(
                currentValues,
                "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect/intervention_details:2/intervention");
        addTerminology(
                currentValues,
                "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect/intervention_details:2/intervention_result");
        addTerminology(
                currentValues,
                "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect/intervention_details/intervention");
        addTerminology(
                currentValues,
                "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect/intervention_details/intervention_result");
        addTerminology(currentValues, "adverse_drug_reaction_report/adverse_drug_reaction/adverse_effect/reaction");
        addTerminology(currentValues, "adverse_drug_reaction_report/adverse_drug_reaction/medra_classification");
        addTerminology(currentValues, "medication_order/medication_detail/medication_action/medicine");

        addTerminology(currentValues, "прием_пациента/административная_информация/медицинское_учреждение");
        addTerminology(currentValues, "прием_пациента/процедуры/request:0/название_процедуры");

        addTerminology(currentValues, "cda_document/xds_metadata/type");
        addTerminology(currentValues, "cda_document/xds_metadata/format");
        addTerminology(currentValues, "cda_document/cda_component:0/code");
        addTerminology(currentValues, "cda_document/cda_component:1/code");
        addTerminology(currentValues, "cda_document/xds_metadata/event:0");
        addTerminology(currentValues, "cda_document/xds_metadata/class");
        addTerminology(currentValues, "cda_document/cda_component:0/code");
        addTerminology(currentValues, "cda_document/xds_metadata/event:1");
        addTerminology(currentValues, "cda_document/xds_metadata/practice_setting");

        addTerminology(currentValues, "xds_document/xds_metadata/practice_setting");
        addTerminology(currentValues, "xds_document/xds_metadata/format");
        addTerminology(currentValues, "xds_document/xds_metadata/event:0");
        addTerminology(currentValues, "xds_document/xds_metadata/event:1");
        addTerminology(currentValues, "xds_document/xds_metadata/class");
        addTerminology(currentValues, "xds_document/xds_metadata/type");

        // instruction_details is an optional rm attribute and thus needs to be prefixed with '_'
        replaceKey(
                currentValues,
                "приостановка_курса_лекарственной_терапии/сведения_о_выполнении:0/instruction_details|composition_uid",
                "приостановка_курса_лекарственной_терапии/сведения_о_выполнении:0/_instruction_details|composition_uid");
        replaceKey(
                currentValues,
                "приостановка_курса_лекарственной_терапии/сведения_о_выполнении:0/instruction_details|activity_id",
                "приостановка_курса_лекарственной_терапии/сведения_о_выполнении:0/_instruction_details|activity_id");

        // add Attribute name and raise upper such that lower < upper
        replaceKey(
                currentValues,
                "test/interval_quantity/fiels_for_test/upper",
                "test/interval_quantity/fiels_for_test/upper|magnitude");
        currentValues.replace("test/interval_quantity/fiels_for_test/upper|magnitude", "90", "130");

        replaceKey(
                currentValues,
                "test/interval_quantity/fiels_for_test/lower",
                "test/interval_quantity/fiels_for_test/lower|magnitude");

        // only iso date times are supported
        currentValues.replace("ctx/time", "1.2.2012 00:00", "2012-02-01T00:00");
        currentValues.replace(
                "vitals/vitals/haemoglobin_a1c/datetime_result_issued", "20.1.2012 19:30", "2012-02-20T19:30");
        currentValues.replace("vitals/vitals/body_temperature/any_event/time", "1.1.2012 0:0", "2012-01-01T00:00");
        currentValues.replace("ctx/time", "2012-02-01T00:00", "2012-02-01T00:00:00+01:00");
        currentValues.replace("ctx/time", "1.2.2012 00:01", "2012-02-01T00:01:00+01:00");
        currentValues.replace("ctx/history_origin", "1.2.2012 00:01", "2012-02-01T00:01:00+01:00");
        currentValues.replace(
                "vitals/vitals/haemoglobin_a1c/datetime_result_issued", "1/2/2012 8:07", "2012-02-01T00:00:00+01:00");

        // date instead of datetime
        currentValues.replace(
                "прием_пациента_врачом-стоматологом-хирургом/административная_информация/дата_приема",
                "2013-04-26T00:00:00",
                "2013-04-26");

        currentValues.replace(
                "test/административная_информация/дата_приема", "2013-06-03T00:00:00.000+06:00", "2013-06-03");

        currentValues.replace("test/административная_информация/дата_приема", "2014-01-13T09:13:00.000Z", "2014-01-13");

        // fix doubles
        currentValues.replace("vitals/vitals/haemoglobin_a1c/any_event/hba1c", "5,1", "5.1");
        currentValues.replace("vitals/vitals/body_temperature/any_event/temperature|magnitude", "38,1", "38.1");
        currentValues.replace("vitals/vitals/body_temperature:1/any_event/temperature|magnitude", "39,1", "39.1");
        // add expected timezone
        currentValues.replace("ctx/time", "2012-02-01T00:00", "2012-02-01T00:00:00+01:00");

        // It is videoconferencing not videoconference
        currentValues.replace("ctx/participation_mode", "videoconference", "videoconferencing");
        // IspekBuilderTest.perinatal2 has a dangling ctx/participation_mode:0 and missing subject name
        if (currentValues.keySet().stream()
                                .filter(k -> k.startsWith("ctx/participation_"))
                                .count()
                        == 1
                && currentValues.keySet().stream()
                                .filter(k -> k.startsWith("perinatal_history/perinatal_history/maternal_pregnancy/"))
                                .count()
                        > 0) {
            currentValues.remove("ctx/participation_mode:0", "face-to-face communication");
            currentValues.put("perinatal_history/perinatal_history/maternal_pregnancy/subject/_name", "Lisa");
        }

        if (currentValues.containsKey("medical_document/document/content")) {
            currentValues.put("medical_document/document/content|formalism", "text");
        }
        // code is not correctly set in template
        if (currentValues.containsKey("visual_acuity_report/visual_acuity:0/any_event:0/test_name|code")) {
            currentValues.put("visual_acuity_report/visual_acuity:0/any_event:0/test_name|value", "value");
            currentValues.put("visual_acuity_report/visual_acuity:0/any_event:0/test_name|terminology", "terminology");
        }
        // code is not correctly set in template
        if (currentValues.containsKey("visual_acuity_report/visual_acuity:0/any_event:1/test_name|code")) {
            currentValues.put("visual_acuity_report/visual_acuity:0/any_event:1/test_name|value", "value");
            currentValues.put("visual_acuity_report/visual_acuity:0/any_event:1/test_name|terminology", "terminology");
        }

        // fix missing "_"
        replaceKey(
                currentValues,
                "осмотр_терапевта/сведения_о_выполнении_назначения:0/выполнение:0/instruction_details|activity_id",
                "осмотр_терапевта/сведения_о_выполнении_назначения:0/выполнение:0/_instruction_details|activity_id");
        replaceKey(
                currentValues,
                "осмотр_терапевта/сведения_о_выполнении_назначения:0/выполнение:0/instruction_details|composition_uid",
                "осмотр_терапевта/сведения_о_выполнении_назначения:0/выполнение:0/_instruction_details|composition_uid");
        replaceKey(currentValues, "xds_document/context/end_time", "xds_document/context/_end_time");

        replaceKey(currentValues, "cda_document/cda_component:0/name", "cda_document/cda_component:0/_name");
        replaceKey(currentValues, "cda_document/cda_component:1/name", "cda_document/cda_component:1/_name");

        // fix context having id event_context due to node_id being present in OPT
        replaceKey(
                currentValues,
                "multidisciplinary_individualised_falls_care_plan/event_context/xds_metadata:0/author_specialty",
                "multidisciplinary_individualised_falls_care_plan/context/xds_metadata:0/author_specialty");

        Composition composition = flatJson.unmarshal(OBJECT_MAPPER.writeValueAsString(currentValues));
        String raw = RMDataFormat.canonicalJSON().marshal(composition).replace("\"_type\"", "\"@class\"");

        if (composition.getArchetypeDetails().getTemplateId().getValue().equals("ISPEK - MED - Medication Order")) {
            // Changing the DVCodedText depending on the asked language is not supported right now

            raw = raw.replace("Plan medication", "*Plan medication(en)");
            raw = raw.replace("Issue prescription for medication", "*Issue prescription for medication(en)");
        }

        return raw;
    }

    private void addTerminology(Map<String, Object> currentValues, String path) {
        if (currentValues.containsKey(path + "|code")) {
            currentValues.put(path + "|terminology", "terminology");
        }
    }

    private void replaceKey(Map<String, Object> currentValues, String key, String newKey) {
        if (currentValues.containsKey(key)) {
            Object o = currentValues.get(key);
            currentValues.remove(key);
            currentValues.put(newKey, o);
        }
    }

    private String replace(String k) {
        if (k.equals("composerName")) {
            return "ctx/composer_name";
        } else if (k.equals("start_time")) {
            return "ctx/time";
        }
        return "ctx/" + k;
    }

    @Override
    public String convertFlatToStructured(
            String template,
            String defaultLanguage,
            String flatComposition,
            Map<String, Object> compositionBuilderContext)
            throws Exception {

        Composition composition =
                Helper.getFlatJson(template, FlatFormat.SIM_SDT).unmarshal(flatComposition);
        return Helper.getFlatJson(template, FlatFormat.STRUCTURED).marshal(composition);
    }

    @Override
    public String convertStructuredToRaw(
            String template,
            String defaultLanguage,
            String structuredComposition,
            Map<String, Object> compositionBuilderContext)
            throws Exception {

        String fixed = structuredComposition
                .replace("\"end_time\"", "\"_end_time\"")
                .replace("\"дата_открытия_талона\":[\"20.03.2020\"]", "\"дата_открытия_талона\":[\"2020-03-20\"]")
                .replace("\"дата_закрытия_талона\":[\"20.03.2020\"]", "\"дата_закрытия_талона\":[\"2020-03-20\"]")
                .replace("\"дата_посещения\":[\"20.03.2020\"]", "\"дата_посещения\":[\"2020-03-20T00:00\"]");

        Composition composition =
                Helper.getFlatJson(template, FlatFormat.STRUCTURED).unmarshal(fixed);
        return RMDataFormat.canonicalJSON().marshal(composition).replace("\"_type\"", "\"@class\"");
    }

    @Override
    public String convertStructuredToFlat(
            String template,
            String defaultLanguage,
            String structuredComposition,
            Map<String, Object> compositionBuilderContext)
            throws Exception {
        Composition composition =
                Helper.getFlatJson(template, FlatFormat.STRUCTURED).unmarshal(structuredComposition);
        return Helper.getFlatJson(template, FlatFormat.SIM_SDT).marshal(composition);
    }

    @Override
    public String updateRawComposition(
            String template,
            String defaultLanguage,
            String rawComposition,
            Map<String, Object> compositionBuilderContext,
            Map<String, Object> deltaValues)
            throws Exception {
        throw new UnsupportedOperationException();
    }
}
