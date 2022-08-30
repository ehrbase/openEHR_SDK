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
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:59:53.510781100+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class AssessmentSection implements LocatableEntity {
    /**
     * Path: open_eREACT-Care/Assessment/DENWIS
     * Description: Dutch Early Nurse Worry Indicator Score (DENWIS)
     */
    @Path("/items[openEHR-EHR-OBSERVATION.denwis.v0]")
    private DenwisObservation denwis;

    /**
     * Path: open_eREACT-Care/Assessment/Sepsis
     * Description: A generic section header which should be renamed in a template to suit a specific clinical context.
     */
    @Path("/items[openEHR-EHR-SECTION.adhoc.v1 and name/value='Sepsis']")
    private SepsisSection sepsis;

    /**
     * Path: open_eREACT-Care/Assessment/Covid
     * Description: A generic section header which should be renamed in a template to suit a specific clinical context.
     */
    @Path("/items[openEHR-EHR-SECTION.adhoc.v1 and name/value='Covid']")
    private CovidSection covid;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2
     * Description: A generic section header which should be renamed in a template to suit a specific clinical context.
     */
    @Path("/items[openEHR-EHR-SECTION.adhoc.v1 and name/value='NEWS2']")
    private News2Section news2;

    /**
     * Path: open_eREACT-Care/Assessment/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setDenwis(DenwisObservation denwis) {
        this.denwis = denwis;
    }

    public DenwisObservation getDenwis() {
        return this.denwis;
    }

    public void setSepsis(SepsisSection sepsis) {
        this.sepsis = sepsis;
    }

    public SepsisSection getSepsis() {
        return this.sepsis;
    }

    public void setCovid(CovidSection covid) {
        this.covid = covid;
    }

    public CovidSection getCovid() {
        return this.covid;
    }

    public void setNews2(News2Section news2) {
        this.news2 = news2;
    }

    public News2Section getNews2() {
        return this.news2;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
