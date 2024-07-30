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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.ehr.EhrStatus;
import com.nedap.archie.rm.support.identification.HierObjectId;
import java.util.List;
import org.ehrbase.openehr.sdk.response.dto.ehrscape.CompositionDto;
import org.ehrbase.openehr.sdk.response.dto.ehrscape.ContributionDto;

/**
 * Basic set of response data regarding EHR operations. Used as default or when `PREFER` header requests minimal response.
 *
 * @deprecated without replaced because used by EHRbase only.
 */
@JacksonXmlRootElement(localName = "ehr")
@Deprecated(since = "2.14.0", forRemoval = true)
@SuppressWarnings("java:S1133")
public class EhrResponseData {

    @JsonProperty(value = "system_id")
    private HierObjectId systemId;

    @JsonProperty(value = "ehr_id")
    private HierObjectId ehrId;

    @JsonProperty(value = "ehr_status")
    private EhrStatus ehrStatus;

    @JsonProperty(value = "time_created")
    private DvDateTime timeCreated;

    @JsonProperty
    private List<CompositionDto> compositions;

    @JsonProperty
    private List<ContributionDto> contributions;

    public HierObjectId getSystemId() {
        return systemId;
    }

    public void setSystemId(HierObjectId systemId) {
        this.systemId = systemId;
    }

    public HierObjectId getEhrId() {
        return ehrId;
    }

    public void setEhrId(HierObjectId ehrId) {
        this.ehrId = ehrId;
    }

    public EhrStatus getEhrStatus() {
        return ehrStatus;
    }

    public void setEhrStatus(EhrStatus ehrStatus) {
        this.ehrStatus = ehrStatus;
    }

    public DvDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = new DvDateTime(timeCreated);
    }

    public void setTimeCreated(DvDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public List<CompositionDto> getCompositions() {
        return compositions;
    }

    public void setCompositions(List<CompositionDto> compositions) {
        this.compositions = compositions;
    }

    public List<ContributionDto> getContributions() {
        return contributions;
    }

    public void setContributions(List<ContributionDto> contributions) {
        this.contributions = contributions;
    }
}
