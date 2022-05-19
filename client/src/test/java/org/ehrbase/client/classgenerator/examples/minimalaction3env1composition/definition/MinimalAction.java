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
package org.ehrbase.client.classgenerator.examples.minimalaction3env1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.client.classgenerator.shareddefinition.Transition;

@Entity
@Archetype("openEHR-EHR-ACTION.minimal.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-11-15T09:37:43.294581+07:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0")
public class MinimalAction implements EntryEntity {
    /**
     * Path: Minimal/Minimal/Multimedia
     * Description: *
     */
    @Path("/description[at0001]/items[at0002]/value")
    private DvMultimedia multimedia;

    /**
     * Path: Minimal/Minimal/Arbol/Multimedia/null_flavour
     */
    @Path("/description[at0001]/items[at0002]/null_flavour|defining_code")
    private NullFlavour multimediaNullFlavourDefiningCode;

    /**
     * Path: Minimal/Minimal/subject
     */
    @Path("/subject")
    private PartyProxy subject;

    /**
     * Path: Minimal/Minimal/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: Minimal/Minimal/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /**
     * Path: Minimal/Minimal/time
     */
    @Path("/time|value")
    private TemporalAccessor timeValue;

    /**
     * Path: Minimal/Minimal/ism_transition/Careflow_step
     */
    @Path("/ism_transition/careflow_step|defining_code")
    private CareflowStepDefiningCode careflowStepDefiningCode;

    /**
     * Path: Minimal/Minimal/ism_transition/Current_state
     */
    @Path("/ism_transition/current_state|defining_code")
    private CurrentStateDefiningCode currentStateDefiningCode;

    /**
     * Path: Minimal/Minimal/ism_transition/transition
     */
    @Path("/ism_transition/transition|defining_code")
    private Transition transitionDefiningCode;

    public void setMultimedia(DvMultimedia multimedia) {
        this.multimedia = multimedia;
    }

    public DvMultimedia getMultimedia() {
        return this.multimedia;
    }

    public void setMultimediaNullFlavourDefiningCode(NullFlavour multimediaNullFlavourDefiningCode) {
        this.multimediaNullFlavourDefiningCode = multimediaNullFlavourDefiningCode;
    }

    public NullFlavour getMultimediaNullFlavourDefiningCode() {
        return this.multimediaNullFlavourDefiningCode;
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

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }

    public void setCareflowStepDefiningCode(CareflowStepDefiningCode careflowStepDefiningCode) {
        this.careflowStepDefiningCode = careflowStepDefiningCode;
    }

    public CareflowStepDefiningCode getCareflowStepDefiningCode() {
        return this.careflowStepDefiningCode;
    }

    public void setCurrentStateDefiningCode(CurrentStateDefiningCode currentStateDefiningCode) {
        this.currentStateDefiningCode = currentStateDefiningCode;
    }

    public CurrentStateDefiningCode getCurrentStateDefiningCode() {
        return this.currentStateDefiningCode;
    }

    public void setTransitionDefiningCode(Transition transitionDefiningCode) {
        this.transitionDefiningCode = transitionDefiningCode;
    }

    public Transition getTransitionDefiningCode() {
        return this.transitionDefiningCode;
    }
}
