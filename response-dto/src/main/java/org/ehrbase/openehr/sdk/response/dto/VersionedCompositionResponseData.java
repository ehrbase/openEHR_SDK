/*
 * Copyright (c) 2019 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.response.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * @deprecated without replaced because used by EHRbase only.
 */
@JacksonXmlRootElement
@Deprecated(since = "2.14.0", forRemoval = true)
@SuppressWarnings("java:S1133")
public class VersionedCompositionResponseData {
    // TODO only for stub for now. Need to change it to real RM versioned_composition or alike later! makes swagger-ui
    // fail right with: Maximum call stack size exceeded
    String versionedComposition;
    // TODO only for stub for now. Need to change it to real RM versioned_composition or alike later! makes swagger-ui
    // fail right with: Maximum call stack size exceeded
    String revisionHistory;
    // TODO only for stub for now. Need to change it to real RM version of versionedComposition or alike later! makes
    // swagger-ui fail right with: Maximum call stack size exceeded
    String version; // should be Version<Composition>

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRevisionHistory() {
        return revisionHistory;
    }

    public void setRevisionHistory(String revisionHistory) {
        this.revisionHistory = revisionHistory;
    }

    public String getComposition() {
        return versionedComposition;
    }

    public void setComposition(String composition) {
        this.versionedComposition = composition;
    }
}
