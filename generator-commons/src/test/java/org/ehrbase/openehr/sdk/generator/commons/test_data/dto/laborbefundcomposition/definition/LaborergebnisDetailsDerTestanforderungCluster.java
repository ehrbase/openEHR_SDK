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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.laborbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.NullFlavour;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-06-10T14:23:37.514740+07:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.4.0")
public class LaborergebnisDetailsDerTestanforderungCluster implements LocatableEntity {
    /**
     * Path: Laborbefund/Laborergebnis/Details der Testanforderung/Auftrags-ID (Einsender)
     * Description: Lokale Auftrags-ID des anfordernden/einsendenden Systems.
     * Comment: Äquivalent zur "HL7 Placer Order Identifier".
     */
    @Path("/items[at0062 and name/value='Auftrags-ID (Einsender)']/value")
    private DvIdentifier auftragsIdEinsender;

    /**
     * Path: Laborbefund/Laborergebnis/Tree/Details der Testanforderung/Auftrags-ID (Einsender)/null_flavour
     */
    @Path("/items[at0062 and name/value='Auftrags-ID (Einsender)']/null_flavour|defining_code")
    private NullFlavour auftragsIdEinsenderNullFlavourDefiningCode;

    /**
     * Path: Laborbefund/Laborergebnis/Details der Testanforderung/Auftrags-ID (Empfänger)
     * Description: Lokale Auftrags-ID, die vom auftragsempfangendem System, gewöhnlich dem Laborinformationssystem (LIS) zugewiesen wird.
     * Comment: Die Vergabe einer solchen ID ermöglicht das Nachverfolgen des Auftragsstatus und das Verlinken der Ergebnisse zum Auftrag. Es erlaubt auch das Verwalten von weiteren Erkundigungen und Nachfragen und ist äquivalent zum "HL7 Filler Order Identifier".
     */
    @Path("/items[at0063]/value")
    private DvIdentifier auftragsIdEmpfaenger;

    /**
     * Path: Laborbefund/Laborergebnis/Tree/Details der Testanforderung/Auftrags-ID (Empfänger)/null_flavour
     */
    @Path("/items[at0063]/null_flavour|defining_code")
    private NullFlavour auftragsIdEmpfaengerNullFlavourDefiningCode;

    /**
     * Path: Laborbefund/Laborergebnis/Details der Testanforderung/Einsender
     * Description: Details über den Kliniker oder die Abteilung, die das Labortestergebnis angefordert hat.
     */
    @Path("/items[at0090]")
    private Cluster einsender;

    /**
     * Path: Laborbefund/Laborergebnis/Details der Testanforderung/Verteilerliste
     * Description: Details über weitere Kliniker oder Organisationen, die eine Kopie der Analyseergebnisse benötigen.
     * Comment: Die "Verteilerliste" dient nur zu Informationszwecken. Der Hauptempfänger des Berichts ist die Person, die dazu bestimmt ist, auf die Information zu reagieren.
     */
    @Path("/items[at0035]")
    private List<Cluster> verteilerliste;

    /**
     * Path: Laborbefund/Laborergebnis/Details der Testanforderung/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setAuftragsIdEinsender(DvIdentifier auftragsIdEinsender) {
        this.auftragsIdEinsender = auftragsIdEinsender;
    }

    public DvIdentifier getAuftragsIdEinsender() {
        return this.auftragsIdEinsender;
    }

    public void setAuftragsIdEinsenderNullFlavourDefiningCode(NullFlavour auftragsIdEinsenderNullFlavourDefiningCode) {
        this.auftragsIdEinsenderNullFlavourDefiningCode = auftragsIdEinsenderNullFlavourDefiningCode;
    }

    public NullFlavour getAuftragsIdEinsenderNullFlavourDefiningCode() {
        return this.auftragsIdEinsenderNullFlavourDefiningCode;
    }

    public void setAuftragsIdEmpfaenger(DvIdentifier auftragsIdEmpfaenger) {
        this.auftragsIdEmpfaenger = auftragsIdEmpfaenger;
    }

    public DvIdentifier getAuftragsIdEmpfaenger() {
        return this.auftragsIdEmpfaenger;
    }

    public void setAuftragsIdEmpfaengerNullFlavourDefiningCode(
            NullFlavour auftragsIdEmpfaengerNullFlavourDefiningCode) {
        this.auftragsIdEmpfaengerNullFlavourDefiningCode = auftragsIdEmpfaengerNullFlavourDefiningCode;
    }

    public NullFlavour getAuftragsIdEmpfaengerNullFlavourDefiningCode() {
        return this.auftragsIdEmpfaengerNullFlavourDefiningCode;
    }

    public void setEinsender(Cluster einsender) {
        this.einsender = einsender;
    }

    public Cluster getEinsender() {
        return this.einsender;
    }

    public void setVerteilerliste(List<Cluster> verteilerliste) {
        this.verteilerliste = verteilerliste;
    }

    public List<Cluster> getVerteilerliste() {
        return this.verteilerliste;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
