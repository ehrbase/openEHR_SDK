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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.optimizersettingsections.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.IntervalEventEntity;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.MathFunction;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:13.441032+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class ReisefallBeliebigesIntervallereignisIntervalEvent implements IntervalEventEntity {
    /**
     * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Letzte Reise?
     * Description: Ist die Person während des angegebenen Zeitraums gereist?
     */
    @Path("/data[at0003]/items[at0004 and name/value='Letzte Reise?']/value|defining_code")
    private LetzteReiseDefiningCode letzteReiseDefiningCode;

    /**
     * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Inland/Ausland
     * Description: War die Reise inländisch, international oder beides?
     */
    @Path("/data[at0003]/items[at0026]/value|defining_code")
    private InlandAuslandDefiningCode inlandAuslandDefiningCode;

    /**
     * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/Bestimmte Reise
     * Description: Details zu einer einzelnen Reise außerhalb vom Heimatstandort.
     * Comment: Die Reise hat ein einziges Abreise- und Rückreisedatum von/zu dem Heimatstandort. Eine Reise kann den Besuch vieler einzelner Orte beinhalten. Diese Details sollten im Cluster für bestimmtes Reiseziel dargestellt werden.
     */
    @Path("/data[at0003]/items[at0008]")
    private List<ReisefallBestimmteReiseCluster> bestimmteReise;

    /**
     * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/math_function
     */
    @Path("/math_function|defining_code")
    private MathFunction mathFunctionDefiningCode;

    /**
     * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/sample_count
     */
    @Path("/sample_count")
    private Long sampleCount;

    /**
     * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/width
     */
    @Path("/width|value")
    private TemporalAmount widthValue;

    /**
     * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /**
     * Path: Bericht/Risikogebiet/Reisefall/Beliebiges Intervallereignis/time
     */
    @Path("/time|value")
    private TemporalAccessor timeValue;

    public void setLetzteReiseDefiningCode(LetzteReiseDefiningCode letzteReiseDefiningCode) {
        this.letzteReiseDefiningCode = letzteReiseDefiningCode;
    }

    public LetzteReiseDefiningCode getLetzteReiseDefiningCode() {
        return this.letzteReiseDefiningCode;
    }

    public void setInlandAuslandDefiningCode(InlandAuslandDefiningCode inlandAuslandDefiningCode) {
        this.inlandAuslandDefiningCode = inlandAuslandDefiningCode;
    }

    public InlandAuslandDefiningCode getInlandAuslandDefiningCode() {
        return this.inlandAuslandDefiningCode;
    }

    public void setBestimmteReise(List<ReisefallBestimmteReiseCluster> bestimmteReise) {
        this.bestimmteReise = bestimmteReise;
    }

    public List<ReisefallBestimmteReiseCluster> getBestimmteReise() {
        return this.bestimmteReise;
    }

    public void setMathFunctionDefiningCode(MathFunction mathFunctionDefiningCode) {
        this.mathFunctionDefiningCode = mathFunctionDefiningCode;
    }

    public MathFunction getMathFunctionDefiningCode() {
        return this.mathFunctionDefiningCode;
    }

    public void setSampleCount(Long sampleCount) {
        this.sampleCount = sampleCount;
    }

    public Long getSampleCount() {
        return this.sampleCount;
    }

    public void setWidthValue(TemporalAmount widthValue) {
        this.widthValue = widthValue;
    }

    public TemporalAmount getWidthValue() {
        return this.widthValue;
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
