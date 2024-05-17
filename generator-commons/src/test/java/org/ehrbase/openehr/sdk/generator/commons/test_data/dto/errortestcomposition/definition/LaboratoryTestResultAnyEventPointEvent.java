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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.errortestcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.OptionFor;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.PointEventEntity;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2022-03-02T14:11:00.869598900+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("POINT_EVENT")
public class LaboratoryTestResultAnyEventPointEvent implements PointEventEntity, LaboratoryTestResultAnyEventChoice {
    /**
     * Path: ErrorTest/Laboratory test result/Any event/Test name Description: Name of the laboratory
     * investigation performed on the specimen(s). Comment: A test result may be for a single analyte,
     * or a group of items, including panel tests. It is strongly recommended that 'Test name' be
     * coded with a terminology, for example LOINC or SNOMED CT. For example: 'Glucose', 'Urea and
     * Electrolytes', 'Swab', 'Cortisol (am)', 'Potassium in perspiration' or 'Melanoma
     * histopathology'. The name may sometimes include specimen type and patient state, for example
     * 'Fasting blood glucose' or include other information, as 'Potassium (PNA blood gas)'.
     */
    @Path("/data[at0003]/items[at0005]/value|value")
    private String testNameValue;

    /**
     * Path: ErrorTest/Laboratory test result/Any event/Specimen Description: A physical sample
     * collected from, or related to, an individual for the purpose of investigation, examination or
     * analysis. Comment: For example: Tissue or body fluid.
     */
    @Path("/data[at0003]/items[openEHR-EHR-CLUSTER.specimen.v1]")
    private SpecimenCluster specimen;

    /**
     * Path: ErrorTest/Laboratory test result/Any event/Test result Description: Results of the test
     * performed on the specimen(s). Comment: This SLOT may carry an individual analyte, a group,
     * panel or battery of multiple analytes, or a more complex and specific structure.
     */
    @Path("/data[at0003]/items[at0097]")
    private List<Cluster> testResult;

    /**
     * Path: ErrorTest/Laboratory test result/Any event/Structured test diagnosis Description: A
     * structured or complex diagnosis for the laboratory test. Comment: For example: Anatomical
     * pathology diagnoses consisting of several different axes such as morphology, etiology and
     * function.
     */
    @Path("/data[at0003]/items[at0122]")
    private List<Cluster> structuredTestDiagnosis;

    /**
     * Path: ErrorTest/Laboratory test result/Any event/Multimedia representation Description: Digital
     * image, video or diagram representing the test result. Comment: Multiple formats are allowed but
     * they should represent equivalent clinical content.
     */
    @Path("/data[at0003]/items[at0118]")
    private List<Cluster> multimediaRepresentation;

    /**
     * Path: ErrorTest/Laboratory test result/Any event/Structured confounding factors Description:
     * Details of issues or circumstances that impact on the accurate interpretation of the
     * measurement or test result. Comment: For example: Last Normal Menstrual Period (LNMP).
     */
    @Path("/state[at0112]/items[at0114]")
    private List<Cluster> structuredConfoundingFactors;

    /** Path: ErrorTest/Laboratory test result/Any event/feeder_audit */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /** Path: ErrorTest/Laboratory test result/Any event/time */
    @Path("/time|value")
    private TemporalAccessor timeValue;

    public void setTestNameValue(String testNameValue) {
        this.testNameValue = testNameValue;
    }

    public String getTestNameValue() {
        return this.testNameValue;
    }

    public void setSpecimen(SpecimenCluster specimen) {
        this.specimen = specimen;
    }

    public SpecimenCluster getSpecimen() {
        return this.specimen;
    }

    public void setTestResult(List<Cluster> testResult) {
        this.testResult = testResult;
    }

    public List<Cluster> getTestResult() {
        return this.testResult;
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

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }
}
