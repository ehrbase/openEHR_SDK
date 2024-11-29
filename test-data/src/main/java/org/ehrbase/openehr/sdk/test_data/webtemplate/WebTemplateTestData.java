/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.test_data.webtemplate;

import java.io.InputStream;

public enum WebTemplateTestData {
    ALTERNATIVE_EVENTS("AlternativeEvents", "AlternativeEvents.json"),
    CORONA("Corona", "corona_anamnese.json"),
    MULTI_OCCURRENCE("multi_occurrence", "multi_occurrence.json"),
    ALL_TYPES("test_all_types", "test_all_types.json"),
    INITIAL_ASSESSMENT("example.initialassesment.v0", "example.initialassesment.json"),
    CONSTRAIN_TEST("example with many constrains / default values", "constrain_test.json"),
    LANGUAGE_TEST("example with multiple languages", "language_test.json"),
    GECCO_SEROLOGISCHER_BEFUND("template with any Element", "gecco_serologischer_befund.json"),
    ADDICTION("example with multiple languages", "addiction.json"),
    GECCO_DIAGNOSE("example with snomed terminologies", "GECCO_Diagnose.json"),
    TESTING_TEMPLATE_N("Template with fixed values", "Testing_Template_N.json"),
    IPS_TEMPLATE("IPS Template", "ips_template.json"),
    SKIPPED_INVALID_NODE_IDS(
            "webtemplate for a template containing invalid node_id's (/category and /context)",
            "skipped_invalid_node_ids.json");

    private final String filename;
    private final String description;

    WebTemplateTestData(String description, String filename) {
        this.filename = filename;
        this.description = description;
    }

    public InputStream getStream() {
        return getClass().getResourceAsStream("/webtemplate/" + filename);
    }

    public String toString() {
        return this.description;
    }
}
