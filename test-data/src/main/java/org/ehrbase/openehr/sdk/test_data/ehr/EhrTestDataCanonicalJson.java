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
package org.ehrbase.openehr.sdk.test_data.ehr;

import java.io.InputStream;

public enum EhrTestDataCanonicalJson {
    EHR_STATUS_SUBJECT_EXTERNAL_REF("EhrStatus with subject as ExternalRef", "ehr_status_subject_external_ref.json"),
    EHR_STATUS_SUBJECT_EXTERNAL_REF_OTHER_DETAILS(
            "EhrStatus with subject as ExternalRef and other_details", "ehr_status_other_details_simple.json");

    private final String filename;
    private final String description;

    EhrTestDataCanonicalJson(String description, String filename) {
        this.filename = filename;
        this.description = description;
    }

    public InputStream getStream() {
        return getClass().getResourceAsStream("/ehr/canonical_json/" + filename);
    }

    @Override
    public String toString() {
        return this.description;
    }
}
