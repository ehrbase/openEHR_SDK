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
package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvCodedText;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:59:53.783780100+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class ServiceRequestReasonForRequestElement implements LocatableEntity {
    /**
     * Path: open_eREACT-Care/Response/Service request/Current Activity/Reason for request
     * Description: A short phrase describing the reason for the request.
     * Comment: Coding of the 'Reason for request' with a coding system is desirable, if available. This data element allows multiple occurrences to enable the user to record a multiple responses, if required. For example: 'manage diabetes complications'.
     */
    @Path("/value")
    private DvCodedText value;

    /**
     * Path: open_eREACT-Care/Response/Service request/Current Activity/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setValue(DvCodedText value) {
        this.value = value;
    }

    public DvCodedText getValue() {
        return this.value;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
