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
package org.ehrbase.openehr.sdk.response.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
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

/**
 * The duplicate of Contribution from com.nedap.archie.rm.changecontrol
 * with changed field versions list of ObjectRef to list of OriginalVersion.
 */
@JsonRootName(value = "CONTRIBUTION")
@XmlType(
        name = "CONTRIBUTION",
        propOrder = {"uid", "versions", "audit"})
@XmlAccessorType(XmlAccessType.FIELD)
public class ContributionCreateDto extends RMObject {

    private HierObjectId uid;

    @JsonProperty(value = "versions")
    @JacksonXmlElementWrapper(localName = "versions")
    @JacksonXmlProperty(localName = "version")
    private List<OriginalVersion<? extends RMObject>> versions = new ArrayList<>();

    private AuditDetails audit;

    /**
     * Instantiates a new Contribution create dto.
     */
    public ContributionCreateDto() {}

    /**
     * Instantiates a new Contribution create dto.
     *
     * @param uid      the uid
     * @param versions the versions
     * @param audit    the audit
     */
    public ContributionCreateDto(
            HierObjectId uid, List<OriginalVersion<? extends RMObject>> versions, AuditDetails audit) {
        this.uid = uid;
        this.versions = versions;
        this.audit = audit;
    }

    /**
     * Gets uid.
     *
     * @return the uid
     */
    public HierObjectId getUid() {
        return this.uid;
    }

    /**
     * Sets uid.
     *
     * @param uid the uid
     */
    public void setUid(HierObjectId uid) {
        this.uid = uid;
    }

    /**
     * Gets versions.
     *
     * @return the versions
     */
    public List<OriginalVersion<? extends RMObject>> getVersions() {
        return this.versions;
    }

    /**
     * Sets versions.
     *
     * @param versions the versions
     */
    public void setVersions(List<OriginalVersion<? extends RMObject>> versions) {
        this.versions = versions;
    }

    /**
     * Gets audit.
     *
     * @return the audit
     */
    public AuditDetails getAudit() {
        return this.audit;
    }

    /**
     * Sets audit.
     *
     * @param audit the audit
     */
    public void setAudit(AuditDetails audit) {
        this.audit = audit;
    }
}
