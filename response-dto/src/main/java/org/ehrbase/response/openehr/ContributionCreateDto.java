/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.response.openehr;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.changecontrol.OriginalVersion;
import com.nedap.archie.rm.generic.AuditDetails;
import com.nedap.archie.rm.support.identification.HierObjectId;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(
        name = "CONTRIBUTION",
        propOrder = {"uid", "versions", "audit"})
@XmlAccessorType(XmlAccessType.FIELD)
public class ContributionCreateDto extends RMObject {

    private HierObjectId uid;

    @JsonProperty(value = "versions")
    @JacksonXmlElementWrapper(localName = "versions")
    @JacksonXmlProperty(localName = "version")
    private List<OriginalVersion<?>> versions = new ArrayList();

    private AuditDetails audit;

    public ContributionCreateDto() {}

    public ContributionCreateDto(HierObjectId uid, List<OriginalVersion<?>> versions, AuditDetails audit) {
        this.uid = uid;
        this.versions = versions;
        this.audit = audit;
    }

    public HierObjectId getUid() {
        return this.uid;
    }

    public void setUid(HierObjectId uid) {
        this.uid = uid;
    }

    public List<OriginalVersion<?>> getVersions() {
        return this.versions;
    }

    public void setVersions(List<OriginalVersion<?>> versions) {
        this.versions = versions;
    }

    public AuditDetails getAudit() {
        return this.audit;
    }

    public void setAudit(AuditDetails audit) {
        this.audit = audit;
    }
}
