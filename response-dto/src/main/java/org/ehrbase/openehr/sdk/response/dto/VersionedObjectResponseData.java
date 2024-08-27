/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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
import com.google.common.base.CaseFormat;
import com.nedap.archie.rm.changecontrol.VersionedObject;
import com.nedap.archie.rm.support.identification.HierObjectId;
import com.nedap.archie.rm.support.identification.ObjectId;
import com.nedap.archie.rm.support.identification.ObjectRef;
import java.time.format.DateTimeFormatter;

/**
 * @deprecated without replaced because used by EHRbase only.
 */
@JacksonXmlRootElement(localName = "ehr_status")
@Deprecated(since = "2.14.0", forRemoval = true)
@SuppressWarnings({"java:S1133", "java:S1452"})
public class VersionedObjectResponseData<T> {

    @JsonProperty(value = "_type")
    private String type;

    @JsonProperty
    private HierObjectId uid;

    @JsonProperty(value = "owner_id")
    private ObjectRef<? extends ObjectId> ownerId;

    @JsonProperty(value = "time_created")
    private String timeCreated;

    public VersionedObjectResponseData(VersionedObject<T> versionedObject) {
        // take the complete name with package and removes the package part to get plain class name
        String className = versionedObject
                .getClass()
                .getName()
                .replace(versionedObject.getClass().getPackage().getName() + ".", "");
        // format to upper underscore
        setType(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, className));
        setUid(versionedObject.getUid());
        setOwnerId(versionedObject.getOwnerId());
        setTimeCreated(DateTimeFormatter.ISO_DATE_TIME.format(
                versionedObject.getTimeCreated().getValue()));
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HierObjectId getUid() {
        return uid;
    }

    public void setUid(HierObjectId uid) {
        this.uid = uid;
    }

    public ObjectRef<? extends ObjectId> getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(ObjectRef<? extends ObjectId> ownerId) {
        this.ownerId = ownerId;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }
}
