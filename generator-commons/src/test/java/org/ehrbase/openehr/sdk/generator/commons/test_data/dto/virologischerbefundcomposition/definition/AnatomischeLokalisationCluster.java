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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.anatomical_location.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:13.765036200+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class AnatomischeLokalisationCluster implements LocatableEntity {
    /**
     * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Anatomische Lokalisation/Name der Körperstelle
     * Description: Identifikation einer einzelnen physischen Stelle entweder am oder innerhalb des menschlichen Körpers.
     */
    @Path("/items[at0001]/value|value")
    private String nameDerKorperstelleValue;

    /**
     * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Anatomische Lokalisation/Alternative Struktur
     * Description: Zusätzliche Informationen über die anatomische Lage mit alternativen Ansätzen zur Beschreibung der gleichen Körperstelle.
     * Comment: Zum Beispiel, relative oder exakte Positionen unter Verwendung von Koordinaten.
     */
    @Path("/items[at0053]")
    private List<Cluster> alternativeStruktur;

    /**
     * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Anatomische Lokalisation/Multimediale Darstellung
     * Description: Bilder oder andere Medien, die der Identifizierung der Körperstelle dienen.
     */
    @Path("/items[at0054]")
    private List<Cluster> multimedialeDarstellung;

    /**
     * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Anatomische Lokalisation/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setNameDerKorperstelleValue(String nameDerKorperstelleValue) {
        this.nameDerKorperstelleValue = nameDerKorperstelleValue;
    }

    public String getNameDerKorperstelleValue() {
        return this.nameDerKorperstelleValue;
    }

    public void setAlternativeStruktur(List<Cluster> alternativeStruktur) {
        this.alternativeStruktur = alternativeStruktur;
    }

    public List<Cluster> getAlternativeStruktur() {
        return this.alternativeStruktur;
    }

    public void setMultimedialeDarstellung(List<Cluster> multimedialeDarstellung) {
        this.multimedialeDarstellung = multimedialeDarstellung;
    }

    public List<Cluster> getMultimedialeDarstellung() {
        return this.multimedialeDarstellung;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
