/*
 * Copyright (c) 2022 vitasystems GmbH.
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

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:59:53.782779500+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class ServiceRequestCurrentActivityActivity implements LocatableEntity {
    /**
     * Path: open_eREACT-Care/Response/Service request/Current Activity/Service name
     * Description: The name of the single service or activity requested.
     * Comment: Coding of the 'Service name' with a coding system is desirable, if available. For example: 'referral' to an endocrinologist for diabetes management.
     */
    @Path("/description[at0009]/items[at0121]/value")
    private DvCodedText serviceName;

    /**
     * Path: open_eREACT-Care/Response/Service request/Current Activity/Reason for request
     * Description: A short phrase describing the reason for the request.
     * Comment: Coding of the 'Reason for request' with a coding system is desirable, if available. This data element allows multiple occurrences to enable the user to record a multiple responses, if required. For example: 'manage diabetes complications'.
     */
    @Path("/description[at0009]/items[at0062]")
    private List<ServiceRequestReasonForRequestElement> reasonForRequest;

    /**
     * Path: open_eREACT-Care/Response/Service request/Current Activity/Reason for isolation
     * Description: Narrative description about the reason for request.
     * Comment: For example: 'The patient's diabetes has recently become more difficult to stabilise and renal function is deteriorating'.
     */
    @Path("/description[at0009]/items[at0064 and name/value='Reason for isolation']/value|value")
    private String reasonForIsolationValue;

    /**
     * Path: open_eREACT-Care/Response/Service request/Current Activity/Complex timing
     * Description: Details about a complex service request requiring a sequence of timings.
     * Comment: For example: 'hourly vital signs observations for 4 hours, then 4 hourly for 20 hours' or 'every third Wednesday for 3 visits' or .
     */
    @Path("/description[at0009]/items[at0151]")
    private List<Cluster> complexTiming;

    /**
     * Path: open_eREACT-Care/Response/Service request/Current Activity/Date isolation due to start
     * Description: The date/time that marks the beginning of the valid period of time for delivery of this service.
     * Comment: This date/time is the equivalent to the earliest possible date for service delivery. For example: sometimes a certain amount of time must pass before a service can be performed, for example some procedures can only be performed once the patient has stopped taking medications for a specific amount of time.
     */
    @Path("/description[at0009]/items[at0145 and name/value='Date isolation due to start']/value|value")
    private TemporalAccessor dateIsolationDueToStartValue;

    /**
     * Path: open_eREACT-Care/Response/Service request/Current Activity/Date isolation due to end
     * Description: The date/time that marks the conclusion of the clinically valid period of time for delivery of this service.
     * Comment: This date/time is the equivalent to the latest possible date for service delivery or to the date of expiry for this request. For example: a service may be required to be completed before another event, such as scheduled surgery.
     */
    @Path("/description[at0009]/items[at0144 and name/value='Date isolation due to end']/value|value")
    private TemporalAccessor dateIsolationDueToEndValue;

    /**
     * Path: open_eREACT-Care/Response/Service request/Current Activity/Specific details
     * Description: Additional detail about the service requested.
     * Comment: For example: Specimen details for a laboratory test request, or anatomical location for a procedure request.
     */
    @Path("/description[at0009]/items[at0132]")
    private List<Cluster> specificDetails;

    /**
     * Path: open_eREACT-Care/Response/Service request/Current Activity/Supporting information
     * Description: Digital document, image, video or diagram supplied as additional information to support or inform the request.
     */
    @Path("/description[at0009]/items[at0149]")
    private List<Cluster> supportingInformation;

    /**
     * Path: open_eREACT-Care/Response/Service request/Current Activity/Patient requirements
     * Description: Language, transport or other personal requirements to support the patient's attendance or participation in provision of the service.
     */
    @Path("/description[at0009]/items[at0116]")
    private List<Cluster> patientRequirements;

    /**
     * Path: open_eREACT-Care/Response/Service request/Current Activity/timing
     */
    @Path("/timing")
    private DvParsable timing;

    /**
     * Path: open_eREACT-Care/Response/Service request/Current Activity/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setServiceName(DvCodedText serviceName) {
        this.serviceName = serviceName;
    }

    public DvCodedText getServiceName() {
        return this.serviceName;
    }

    public void setReasonForRequest(List<ServiceRequestReasonForRequestElement> reasonForRequest) {
        this.reasonForRequest = reasonForRequest;
    }

    public List<ServiceRequestReasonForRequestElement> getReasonForRequest() {
        return this.reasonForRequest;
    }

    public void setReasonForIsolationValue(String reasonForIsolationValue) {
        this.reasonForIsolationValue = reasonForIsolationValue;
    }

    public String getReasonForIsolationValue() {
        return this.reasonForIsolationValue;
    }

    public void setComplexTiming(List<Cluster> complexTiming) {
        this.complexTiming = complexTiming;
    }

    public List<Cluster> getComplexTiming() {
        return this.complexTiming;
    }

    public void setDateIsolationDueToStartValue(TemporalAccessor dateIsolationDueToStartValue) {
        this.dateIsolationDueToStartValue = dateIsolationDueToStartValue;
    }

    public TemporalAccessor getDateIsolationDueToStartValue() {
        return this.dateIsolationDueToStartValue;
    }

    public void setDateIsolationDueToEndValue(TemporalAccessor dateIsolationDueToEndValue) {
        this.dateIsolationDueToEndValue = dateIsolationDueToEndValue;
    }

    public TemporalAccessor getDateIsolationDueToEndValue() {
        return this.dateIsolationDueToEndValue;
    }

    public void setSpecificDetails(List<Cluster> specificDetails) {
        this.specificDetails = specificDetails;
    }

    public List<Cluster> getSpecificDetails() {
        return this.specificDetails;
    }

    public void setSupportingInformation(List<Cluster> supportingInformation) {
        this.supportingInformation = supportingInformation;
    }

    public List<Cluster> getSupportingInformation() {
        return this.supportingInformation;
    }

    public void setPatientRequirements(List<Cluster> patientRequirements) {
        this.patientRequirements = patientRequirements;
    }

    public List<Cluster> getPatientRequirements() {
        return this.patientRequirements;
    }

    public void setTiming(DvParsable timing) {
        this.timing = timing;
    }

    public DvParsable getTiming() {
        return this.timing;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
