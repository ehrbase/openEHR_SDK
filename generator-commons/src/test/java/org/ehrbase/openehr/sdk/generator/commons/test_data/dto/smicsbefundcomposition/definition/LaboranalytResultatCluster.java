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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:58:09.942430500+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class LaboranalytResultatCluster implements LocatableEntity {
    /**
     * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/Antibiogramm/Laboranalyt-Resultat/Antibiotikum
     * Description: Die Bezeichnung des Analyt-Resultats
     * Comment: Der Wert dieses Elements wird normalerweise meist durch eine Spezialisierung, durch einer Vorlage oder zur Laufzeit geliefert, um den aktuellen Analyt wiederzugeben. Zum Beispiel: 'Natrium im Serum','Hämoglobin'.
     *                                                                                 Die Codierung mit einer externen Terminologie, wie LOINC, NPU, SNOMED-CT oder lokalen Labor-Terminologien wird dringend empfohlen.
     *
     */
    @Path("/items[at0024 and name/value='Antibiotikum']/value|defining_code")
    private AntibiotikumDefiningCode antibiotikumDefiningCode;

    /**
     * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/Antibiogramm/Laboranalyt-Resultat/Minimale Hemmkonzentration
     * Description: (Mess-)Wert des Analyt-Resultats.
     * Comment: z.B. '7.3 mmol/l', 'Erhöht'. Der 'Any'-Datentyp wird dann
     *                                                                                 durch eine Spezialisierung, eine Vorlage oder zur Laufzeit
     *                                                                                 auf einen passenden Datentypen eingeschränkt werden müssen, um das aktuelle Analyt-Ergebnis wiederzugeben. Der 'Quantity'-Datentyp hat Referenzmodell-Attribute, wie Kennungen für normal/abnormal, Referenzbereiche und Näherungen - für weitere Details s. https://specifications.openehr.org/releases/RM/latest/data_types.html#_dv_quantity_class .
     *
     */
    @Path("/items[at0001 and name/value='Minimale Hemmkonzentration']/value|magnitude")
    private Double minimaleHemmkonzentrationMagnitude;

    /**
     * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/Antibiogramm/Laboranalyt-Resultat/Minimale Hemmkonzentration
     * Description: (Mess-)Wert des Analyt-Resultats.
     * Comment: z.B. '7.3 mmol/l', 'Erhöht'. Der 'Any'-Datentyp wird dann
     *                                                                                 durch eine Spezialisierung, eine Vorlage oder zur Laufzeit
     *                                                                                 auf einen passenden Datentypen eingeschränkt werden müssen, um das aktuelle Analyt-Ergebnis wiederzugeben. Der 'Quantity'-Datentyp hat Referenzmodell-Attribute, wie Kennungen für normal/abnormal, Referenzbereiche und Näherungen - für weitere Details s. https://specifications.openehr.org/releases/RM/latest/data_types.html#_dv_quantity_class .
     *
     */
    @Path("/items[at0001 and name/value='Minimale Hemmkonzentration']/value|units")
    private String minimaleHemmkonzentrationUnits;

    /**
     * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/Antibiogramm/Laboranalyt-Resultat/Analyseergebnis-Details
     * Description: Weitere Details zu einem einzelnen Ergebnis.
     */
    @Path("/items[at0014]")
    private List<Cluster> analyseergebnisDetails;

    /**
     * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/Antibiogramm/Laboranalyt-Resultat/Resistenz
     * Description: Zusätzliche Hinweise zur Anwendbarkeit des Referenzbereichs für dieses Resultat oder (codierter) Text, ob das Resultat im Referenzbereich ist oder nicht.
     * Comment: z.B.: 'im Referenzbereich, bezogen auf Alter und Geschlecht'.
     */
    @Path("/items[at0004 and name/value='Resistenz']/value|value")
    private String resistenzValue;

    /**
     * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/Antibiogramm/Laboranalyt-Resultat/Kommentar
     * Description: Kommentar zum Analyt-Resultat, soweit noch nicht in anderen Feldern erfasst.
     */
    @Path("/items[at0003]/value|value")
    private String kommentarValue;

    /**
     * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/Antibiogramm/Laboranalyt-Resultat/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setAntibiotikumDefiningCode(AntibiotikumDefiningCode antibiotikumDefiningCode) {
        this.antibiotikumDefiningCode = antibiotikumDefiningCode;
    }

    public AntibiotikumDefiningCode getAntibiotikumDefiningCode() {
        return this.antibiotikumDefiningCode;
    }

    public void setMinimaleHemmkonzentrationMagnitude(Double minimaleHemmkonzentrationMagnitude) {
        this.minimaleHemmkonzentrationMagnitude = minimaleHemmkonzentrationMagnitude;
    }

    public Double getMinimaleHemmkonzentrationMagnitude() {
        return this.minimaleHemmkonzentrationMagnitude;
    }

    public void setMinimaleHemmkonzentrationUnits(String minimaleHemmkonzentrationUnits) {
        this.minimaleHemmkonzentrationUnits = minimaleHemmkonzentrationUnits;
    }

    public String getMinimaleHemmkonzentrationUnits() {
        return this.minimaleHemmkonzentrationUnits;
    }

    public void setAnalyseergebnisDetails(List<Cluster> analyseergebnisDetails) {
        this.analyseergebnisDetails = analyseergebnisDetails;
    }

    public List<Cluster> getAnalyseergebnisDetails() {
        return this.analyseergebnisDetails;
    }

    public void setResistenzValue(String resistenzValue) {
        this.resistenzValue = resistenzValue;
    }

    public String getResistenzValue() {
        return this.resistenzValue;
    }

    public void setKommentarValue(String kommentarValue) {
        this.kommentarValue = kommentarValue;
    }

    public String getKommentarValue() {
        return this.kommentarValue;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
