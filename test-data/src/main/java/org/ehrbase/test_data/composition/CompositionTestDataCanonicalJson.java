/*
 * Copyright (c) 2019 Stefan Spiska (Vitasystems GmbH) and Hannover Medical School.
 *
 * This file is part of project EHRbase
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehrbase.test_data.composition;

import java.io.InputStream;

public enum CompositionTestDataCanonicalJson {
    LABORATORY_REPORT("Valid Laboratory report", "laboratory_report.json"),
    LABORATORY_REPORT_NO_CONTENT("Laboratory report no content", "laboratory_report_no_content.json"),
    MINIMAL_ADMIN("Minimal Admin", "minimal_admin.json"),
    MINIMAL_EVAL("Minimal Evaluation", "minimal_evaluation.json"),
    MINIMAL_EVAL_NAMED_ITEM_TREE("Minimal Evaluation modified", "minimal_evaluation_item_tree_name.json"),
    MINIMAL_INST("Minimal Instruction", "minimal_instruction.json"),
    MINIMAL_OBS("Minimal Observation", "minimal_observation.json"),
    INVALID("Invalid example", "invalid.json"),
    ALL_TYPES("All types", "all_types_no_multimedia.json"),
    ALL_TYPES_SYSTEMATIC_TESTS("All types 2", "all_types_systematic_tests.json"),
    ALTERNATIVE_TYPES("Alternative types", "alternative_types.json"),
    OBS_ADMIN("Observation+Admin", "obs_admin.json"),
    OBS_ADMIN_NULL("Observation+Admin with null_flavour", "obs_admin_null_flavour.json"),
    OBS_EVA("Observation+Evaluation", "obs_eva.json"),
    OBS_INST("Observation+Instruction", "obs_inst.json"),
    MINIMAL_PERSISTENT("Minimal Persistent", "minimal_persistent.json"),
    NESTED("Nested", "nested.json"),
    TIME_SERIES("Time Series", "time_series.json"),
    CORONA("Corona", "compo_corona.json"),
    SUBJECT_PARTY_IDENTIFIED("Nested subject PARTY_IDENTIFIED", "compo_with_nested_party_identified.json"),
    SUBJECT_PARTY_SELF("Nested subject PARTY_SELF", "compo_with_nested_party_self.json"),
    SUBJECT_PARTY_RELATED("Nested subject PARTY_RELATED", "compo_with_nested_party_related.json"),
    NESTED_PROVIDER("Nested provider PARTY_IDENTIFIED", "compo_with_nested_provider.json"),
    DATE_TIME_TESTS("Composition with multiple partial and full date/time", "datetime_tests.json"),
    DURATION_TESTS("Composition with multiple DvDuration", "duration_tests.json"),
    VIROLOGY_FINDING_WITH_SPECIMEN("virology finding with specimen", "virology_finding_with_specimen.json"),
    VIROLOGY_FINDING_WITH_SPECIMEN_NO_UPDATE("virology finding with specimen simple", "virology_finding_with_specimen_no_update.json"),
    MULTI_OCCURRENCE("multi occurrence", "multi_occurrence.json"),
    ALTERNATIVE_EVENTS("Contains Event with mixed point and interval", "alternative_events.json"),
    CHOICE_ELEMENT("element containing multiple choice to test validation", "choice_validation_test.json"),
    CHOICE_DV_QUANTITY("choices in units", "dvquantity_choice.json"),
    DEMO_VITALS("composition to test archetype_details roundtrip", "demo_vitals_352.json"),
    FEEDER_AUDIT_DETAILS("composition to test feeder audit details", "compo_feeder_audit_details.json"),
    MINIMAL_INSTRUCTION("minimal instruction (contains DV_DURATION)", "minimal_instruction.json");;


    private final String filename;
    private final String description;

    CompositionTestDataCanonicalJson(String description, String filename) {
        this.filename = filename;
        this.description = description;
    }


    public InputStream getStream() {
        return getClass().getResourceAsStream("/composition/canonical_json/" + filename);
    }

    @Override
    public String toString() {
        return this.description;
    }
}
