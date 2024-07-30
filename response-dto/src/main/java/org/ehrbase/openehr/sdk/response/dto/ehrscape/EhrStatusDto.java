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
package org.ehrbase.openehr.sdk.response.dto.ehrscape;

/**
 * @deprecated without replaced because used by EHRbase only.
 */
@Deprecated(since = "2.14.0", forRemoval = true)
@SuppressWarnings("java:S1133")
public class EhrStatusDto {

    String subjectId;
    String subjectNamespace;
    boolean queryable;
    boolean modifiable;
    StructuredString otherDetails;
    String otherDetailsTemplateId;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectNamespace() {
        return subjectNamespace;
    }

    public void setSubjectNamespace(String subjectNamespace) {
        this.subjectNamespace = subjectNamespace;
    }

    public boolean isQueryable() {
        return queryable;
    }

    public void setQueryable(boolean queryable) {
        this.queryable = queryable;
    }

    public boolean isModifiable() {
        return modifiable;
    }

    public void setModifiable(boolean modifiable) {
        this.modifiable = modifiable;
    }

    public StructuredString getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(StructuredString otherDetails) {
        this.otherDetails = otherDetails;
    }

    public String getOtherDetailsTemplateId() {
        return otherDetailsTemplateId;
    }

    public void setOtherDetailsTemplateId(String otherDetailsTemplateId) {
        this.otherDetailsTemplateId = otherDetailsTemplateId;
    }
}
