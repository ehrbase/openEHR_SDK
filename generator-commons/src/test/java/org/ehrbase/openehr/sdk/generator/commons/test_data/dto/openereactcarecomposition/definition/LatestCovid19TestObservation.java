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

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Choice;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.EntryEntity;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.laboratory_test_result.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:59:53.711777800+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class LatestCovid19TestObservation implements EntryEntity {
    /**
     * Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Any event/Test name
     * Description: Name of the laboratory investigation performed on the specimen(s).
     * Comment: A test result may be for a single analyte, or a group of items, including panel tests. It is strongly recommended that 'Test name' be coded with a terminology, for example LOINC or SNOMED CT. For example: 'Glucose', 'Urea and Electrolytes', 'Swab', 'Cortisol (am)', 'Potassium in perspiration' or 'Melanoma histopathology'. The name may sometimes include specimen type and patient state, for example 'Fasting blood glucose' or include other information, as 'Potassium (PNA blood gas)'.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|value")
    private String testNameValue;

    /**
     * Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Any event/Specimen detail
     * Description: Details about the physical substance that has been analysed.
     * Comment: If the specimen type is sufficiently specified with a code in the Test name, then this additional data is not required. Linking results to specific specimens may be recorded using 'Specimen identifier' elements in both the CLUSTER.specimen and the various results CLUSTER archetypes.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0065]")
    private List<Cluster> specimenDetail;

    /**
     * Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Any event/Test result
     * Description: Results of the test performed on the specimen(s).
     * Comment: This SLOT may carry an individual analyte, a group, panel or battery of multiple analytes, or a more complex and specific structure.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0097]")
    private List<Cluster> testResult;

    /**
     * Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Any event/Test diagnosis
     * Description: Single word, phrase or brief description that represents the clinical meaning and significance of the laboratory test result.
     * Comment: For example: 'Severe hepatic impairment', 'Salmonella contamination'. Coding of the diagnosis with a terminology is strongly recommended, where possible. This diagnosis should be aligned with the narrative in the 'Conclusion'.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0098]/value|value")
    private String testDiagnosisValue;

    /**
     * Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Any event/Structured test diagnosis
     * Description: A structured or complex diagnosis for the laboratory test.
     * Comment: For example: Anatomical pathology diagnoses consisting of several different axes such as morphology, etiology and function.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0122]")
    private List<Cluster> structuredTestDiagnosis;

    /**
     * Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Any event/Multimedia representation
     * Description: Digital image, video or diagram representing the test result.
     * Comment: Multiple formats are allowed but they should represent equivalent clinical content.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0118]")
    private List<Cluster> multimediaRepresentation;

    /**
     * Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Any event/Structured confounding factors
     * Description: Details of issues or circumstances that impact on the accurate interpretation of the measurement or test result.
     * Comment: For example: Last Normal Menstrual Period (LNMP).
     */
    @Path("/data[at0001]/events[at0002]/state[at0112]/items[at0114]")
    private List<Cluster> structuredConfoundingFactors;

    /**
     * Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Any event/time
     */
    @Path("/data[at0001]/events[at0002]/time|value")
    private TemporalAccessor timeValue;

    /**
     * Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/origin
     */
    @Path("/data[at0001]/origin|value")
    private TemporalAccessor originValue;

    /**
     * Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Receiving laboratory
     * Description: Details of the laboratory which received the request and has overall responsibility to manage reporting of the test, even if other labs perform specific aspects.
     * Comment: This slot is intended to carry details of the laboratory which received the request and has overall responsibility to manage reporting of the test, even if other labs perform specific aspects.
     *
     *  The receiving laboratory may either perform the test or refer it to another laboratory. Where a different laboratory is responsible for performing the testing on specific analytes, it would be expected that these details would be carried in the 'Analyte result detail' SLOT within the CLUSTER.laboratory_test_analyte archetype.
     *
     *
     */
    @Path("/protocol[at0004]/items[at0017]")
    private Cluster receivingLaboratory;

    /**
     * Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Test request details
     * Description: Details about the test request.
     * Comment: In most situations there is one test request and a single corresponding test result, however this repeating cluster allows for the situation where there may be multiple test requests reported using a single test result.
     *
     *  As an example: 'a clinician asks for blood glucose in one request and Urea/electrolytes in a second request, but the lab analyser does both and the lab wishes to report these together'.
     */
    @Path("/protocol[at0004]/items[at0094]")
    private List<LatestCovid19TestTestRequestDetailsCluster> testRequestDetails;

    /**
     * Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Testing details
     * Description: Structured details about the method of analysis, device or interpretation used.
     * Comment: For example: 'details of ELISA/nephelometry'.
     */
    @Path("/protocol[at0004]/items[at0110]")
    private List<Cluster> testingDetails;

    /**
     * Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Extension
     * Description: Additional information required to capture local content or to align with other reference models/formalisms.
     * Comment: For example: local information requirements or additional metadata to align with FHIR or CIMI equivalents.
     */
    @Path("/protocol[at0004]/items[at0117]")
    private List<Cluster> extension;

    /**
     * Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/subject
     */
    @Path("/subject")
    private PartyProxy subject;

    /**
     * Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /**
     * Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Any event/Overall test status
     * Description: The status of the laboratory test result as a whole.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0073]/value")
    @Choice
    private LatestCovid19TestOverallTestStatusChoice overallTestStatus;

    public void setTestNameValue(String testNameValue) {
        this.testNameValue = testNameValue;
    }

    public String getTestNameValue() {
        return this.testNameValue;
    }

    public void setSpecimenDetail(List<Cluster> specimenDetail) {
        this.specimenDetail = specimenDetail;
    }

    public List<Cluster> getSpecimenDetail() {
        return this.specimenDetail;
    }

    public void setTestResult(List<Cluster> testResult) {
        this.testResult = testResult;
    }

    public List<Cluster> getTestResult() {
        return this.testResult;
    }

    public void setTestDiagnosisValue(String testDiagnosisValue) {
        this.testDiagnosisValue = testDiagnosisValue;
    }

    public String getTestDiagnosisValue() {
        return this.testDiagnosisValue;
    }

    public void setStructuredTestDiagnosis(List<Cluster> structuredTestDiagnosis) {
        this.structuredTestDiagnosis = structuredTestDiagnosis;
    }

    public List<Cluster> getStructuredTestDiagnosis() {
        return this.structuredTestDiagnosis;
    }

    public void setMultimediaRepresentation(List<Cluster> multimediaRepresentation) {
        this.multimediaRepresentation = multimediaRepresentation;
    }

    public List<Cluster> getMultimediaRepresentation() {
        return this.multimediaRepresentation;
    }

    public void setStructuredConfoundingFactors(List<Cluster> structuredConfoundingFactors) {
        this.structuredConfoundingFactors = structuredConfoundingFactors;
    }

    public List<Cluster> getStructuredConfoundingFactors() {
        return this.structuredConfoundingFactors;
    }

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }

    public void setOriginValue(TemporalAccessor originValue) {
        this.originValue = originValue;
    }

    public TemporalAccessor getOriginValue() {
        return this.originValue;
    }

    public void setReceivingLaboratory(Cluster receivingLaboratory) {
        this.receivingLaboratory = receivingLaboratory;
    }

    public Cluster getReceivingLaboratory() {
        return this.receivingLaboratory;
    }

    public void setTestRequestDetails(List<LatestCovid19TestTestRequestDetailsCluster> testRequestDetails) {
        this.testRequestDetails = testRequestDetails;
    }

    public List<LatestCovid19TestTestRequestDetailsCluster> getTestRequestDetails() {
        return this.testRequestDetails;
    }

    public void setTestingDetails(List<Cluster> testingDetails) {
        this.testingDetails = testingDetails;
    }

    public List<Cluster> getTestingDetails() {
        return this.testingDetails;
    }

    public void setExtension(List<Cluster> extension) {
        this.extension = extension;
    }

    public List<Cluster> getExtension() {
        return this.extension;
    }

    public void setSubject(PartyProxy subject) {
        this.subject = subject;
    }

    public PartyProxy getSubject() {
        return this.subject;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }

    public void setOverallTestStatus(LatestCovid19TestOverallTestStatusChoice overallTestStatus) {
        this.overallTestStatus = overallTestStatus;
    }

    public LatestCovid19TestOverallTestStatusChoice getOverallTestStatus() {
        return this.overallTestStatus;
    }
}
