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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;

@Entity
public class WeitereSymptomeSpezifischesSymptomAnzeichenCluster {
    @Path("/items[at0005]/value|defining_code")
    private VorhandenDefiningcode2 vorhandenDefiningcode;

    @Path("/items[at0004]/value|value")
    private String bezeichnungDesSymptomsOderAnzeichensValue;

    @Path("/items[at0035]/value|value")
    private String kommentarValue;

    @Path("/items[at0026]")
    private List<Cluster> anzeichen;

    public void setVorhandenDefiningcode(VorhandenDefiningcode2 vorhandenDefiningcode) {
        this.vorhandenDefiningcode = vorhandenDefiningcode;
    }

    public VorhandenDefiningcode2 getVorhandenDefiningcode() {
        return this.vorhandenDefiningcode;
    }

    public void setBezeichnungDesSymptomsOderAnzeichensValue(String bezeichnungDesSymptomsOderAnzeichensValue) {
        this.bezeichnungDesSymptomsOderAnzeichensValue = bezeichnungDesSymptomsOderAnzeichensValue;
    }

    public String getBezeichnungDesSymptomsOderAnzeichensValue() {
        return this.bezeichnungDesSymptomsOderAnzeichensValue;
    }

    public void setKommentarValue(String kommentarValue) {
        this.kommentarValue = kommentarValue;
    }

    public String getKommentarValue() {
        return this.kommentarValue;
    }

    public void setAnzeichen(List<Cluster> anzeichen) {
        this.anzeichen = anzeichen;
    }

    public List<Cluster> getAnzeichen() {
        return this.anzeichen;
    }
}
