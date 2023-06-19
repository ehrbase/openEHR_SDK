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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.errortestcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2022-03-02T14:11:00.878600700+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public interface LaboratoryTestResultAnyEventChoice {
    String getTestNameValue();

    void setTestNameValue(String testNameValue);

    SpecimenCluster getSpecimen();

    void setSpecimen(SpecimenCluster specimen);

    TemporalAccessor getTimeValue();

    void setTimeValue(TemporalAccessor timeValue);

    List<Cluster> getMultimediaRepresentation();

    void setMultimediaRepresentation(List<Cluster> multimediaRepresentation);

    List<Cluster> getTestResult();

    void setTestResult(List<Cluster> testResult);

    List<Cluster> getStructuredConfoundingFactors();

    void setStructuredConfoundingFactors(List<Cluster> structuredConfoundingFactors);

    FeederAudit getFeederAudit();

    void setFeederAudit(FeederAudit feederAudit);

    List<Cluster> getStructuredTestDiagnosis();

    void setStructuredTestDiagnosis(List<Cluster> structuredTestDiagnosis);
}
