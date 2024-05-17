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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.openereactcarecomposition.definition;

import org.ehrbase.openehr.sdk.generator.commons.interfaces.EnumValueSet;

public enum OverallTestStatusDefiningCode implements EnumValueSet {
    AMENDED(
            "Amended",
            "The result has been modified subsequent to being Final, and is complete and verified by the responsible pathologist, and result data has been changed.",
            "local",
            "at0040"),

    PRELIMINARY(
            "Preliminary",
            "Verified early results are available, but not all results are final. This is a sub-category of 'Partial'.",
            "local",
            "at0120"),

    FINAL("Final", "The Test result is complete and verified by an authorised person.", "local", "at0038"),

    APPENDED(
            "Appended",
            "Subsequent to being final, the report has been modified by adding new content. The existing content is unchanged. This is a sub-category of 'Amended'.",
            "local",
            "at0119"),

    CORRECTED(
            "Corrected",
            "The result has been modified subsequent to being Final, and is complete and verified by the responsible pathologist. This is a sub-category of 'Amended'.",
            "local",
            "at0115"),

    CANCELLED(
            "Cancelled",
            "The result is unavailable because the test was not started or not completed (also sometimes called 'aborted').",
            "local",
            "at0074"),

    ENTERED_IN_ERROR(
            "Entered in error",
            "The Test Result has been withdrawn following previous Final release.",
            "local",
            "at0116"),

    PARTIAL(
            "Partial",
            "This is a partial (e.g. initial, interim or preliminary) Test Result: data in the Test Result may be incomplete or unverified.",
            "local",
            "at0037");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    OverallTestStatusDefiningCode(String value, String description, String terminologyId, String code) {
        this.value = value;
        this.description = description;
        this.terminologyId = terminologyId;
        this.code = code;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTerminologyId() {
        return this.terminologyId;
    }

    public String getCode() {
        return this.code;
    }
}
