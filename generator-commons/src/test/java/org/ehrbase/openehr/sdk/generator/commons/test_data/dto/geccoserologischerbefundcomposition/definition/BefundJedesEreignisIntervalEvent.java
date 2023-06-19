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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.geccoserologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Choice;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.OptionFor;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.IntervalEventEntity;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.MathFunction;

@Entity
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-05-19T16:20:30.063760700+02:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("INTERVAL_EVENT")
public class BefundJedesEreignisIntervalEvent implements IntervalEventEntity, BefundJedesEreignisChoice {
    /**
     * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Bezeichnung Description: Name
     * der Laboruntersuchung, die an der/den Probe(n) durchgeführt wurde. Comment: Ein Laborergebnis
     * kann sich auf ein einzelnes Analyt oder eine Analytgruppe beziehen. Dazu zählen auch komplette
     * Panel an Parametern. Es wird dringend empfohlen, die "Labortest-Bezeichnung" anhand einer
     * Terminologie zu kodiereren, wie zum Beispiel LOINC oder SNOMED CT. Beispiel: "Glukose",
     * "Harnstoff", "Abstrich", "Cortisol", "Leberbiopsie". Der Name kann u.U. auch das Probenmaterial
     * oder den Patientenstatus (z.B. "Blutzuckerspiegel nüchtern") oder andere Informationen
     * beinhalten wie "Kalium (Blutgas)".
     */
    @Path("/data[at0003]/items[at0005]/value|defining_code")
    private LabortestBezeichnungDefiningCode labortestBezeichnungDefiningCode;

    /**
     * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Probendetail Description: Angaben über
     * die Beschaffenheit der analysierten Probe. Comment: Wenn der Probentyp mit einem Code in der
     * Testbezeichnung ausreichend spezifiziert ist, sind diese zusätzlichen Daten nicht erforderlich.
     * Die Verknüpfung von Ergebnissen mit bestimmten Proben kann sowohl in einem CLUSTER.Probe als
     * auch in den verschiedenen CLUSTER Archetypen mit Hilfe von Elementen mit der Bezeichnung
     * "Probe" dokumentiert werden.
     */
    @Path("/data[at0003]/items[at0065]")
    private List<Cluster> probendetail;

    /**
     * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro
     * Analyt/Virusnachweistest Description: Der Name des untersuchten Analyts. Comment: Der Wert
     * dieses Elements wird normalerweise, meist durch eine Spezialisierung, in einem Template oder
     * zur Laufzeit der Anwendung geliefert, um den aktuellen Analyt wiederzugeben. Zum Beispiel:
     * 'Natrium im Serum', 'Hämoglobin'. Die Codierung mit einer externen Terminologie, wie LOINC,
     * NPU, SNOMED-CT oder lokalen Labor-Terminologien wird dringend empfohlen.
     */
    @Path(
            "/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_panel.v0]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='Pro Analyt']/items[at0024 and name/value='Virusnachweistest']/value|defining_code")
    private VirusnachweistestDefiningCode virusnachweistestDefiningCode;

    /**
     * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/Nachweis
     * Description: (Mess-)Wert des Analyt-Resultats. Comment: z.B. "7,3 mmol/l", "Erhöht". Der
     * "Any"-Datentyp wird dann durch eine Spezialisierung, eine Vorlage oder zur Laufzeit der
     * Anwendung auf einen passenden Datentyp eingeschränkt werden müssen, um das aktuelle
     * Analyt-Ergebnis wiederzugeben. Der "Quantity"-Datentyp hat Referenzmodell-Attribute, wie
     * Kennungen für normal/abnormal, Referenzbereiche und Näherungen - für weitere Details s.
     * https://specifications.openehr.org/releases/RM/latest/data_types.html#_dv_quantity_class .
     */
    @Path(
            "/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_panel.v0]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='Pro Analyt']/items[at0001 and name/value='Nachweis']/value|defining_code")
    private NachweisDefiningCode nachweisDefiningCode;

    /**
     * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro
     * Analyt/Analyseergebnis-Details Description: Weitere Details zu einem einzelnen Ergebnis.
     */
    @Path(
            "/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_panel.v0]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='Pro Analyt']/items[at0014]")
    private List<Cluster> analyseergebnisDetails;

    /**
     * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro
     * Analyt/Ergebnis-Status Description: Status des Analyseergebnisses. Comment: Die Werte wurden
     * analog zum HL7 FHIR Diagnostic Report gewählt, die wiederum aus der HL7-Praxis stammen. Andere
     * Codes/Ausdrücke können über den Text 'choice' verwendet werden.
     */
    @Path(
            "/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_panel.v0]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='Pro Analyt']/items[at0005]/value|value")
    private String ergebnisStatusValue;

    /**
     * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Strukturierte Testdiagnostik
     * Description: Eine strukturierte oder komplexe Diagnose für die Laboruntersuchung. Comment: Zum
     * Beispiel: Anatomische Pathologiediagnosen, die aus mehreren verschiedenen Schwerpunkten wie
     * Morphologie, Ätiologie und Funktion zusammengesetzt sind.
     */
    @Path("/data[at0003]/items[at0122]")
    private List<Cluster> strukturierteTestdiagnostik;

    /**
     * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Multimedia-Darstellung Description:
     * Bild, Video oder Diagramm zur Visualisierung des Testergebnisses. Comment: Mehrere Formate sind
     * erlaubt - diese sollten aber einen äquivalenten klinischen Inhalt darstellen.
     */
    @Path("/data[at0003]/items[at0118]")
    private List<Cluster> multimediaDarstellung;

    /**
     * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Strukturierte Erfassung der Störfaktoren
     * Description: Einzelheiten zu Problemen oder Umständen, die sich auf die genaue Interpretation
     * der Messung oder des Prüfergebnisses auswirken. Comment: Zum Beispiel: Letzte normale
     * Menstruationsperiode (LNMP).
     */
    @Path("/state[at0112]/items[at0114]")
    private List<Cluster> strukturierteErfassungDerStorfaktoren;

    /** Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/feeder_audit */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /** Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/time */
    @Path("/time|value")
    private TemporalAccessor timeValue;

    /** Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/width */
    @Path("/width|value")
    private TemporalAmount widthValue;

    /** Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/math_function */
    @Path("/math_function|defining_code")
    private MathFunction mathFunctionDefiningCode;

    /** Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/sample_count */
    @Path("/sample_count")
    private Long sampleCount;

    /**
     * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/Testmethode
     * Description: Die Beschreibung der Methode, mit der der Test nur für diesen Analyten
     * durchgeführt wurde.
     */
    @Path(
            "/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_panel.v0]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='Pro Analyt']/items[at0028]/value")
    @Choice
    private ProAnalytTestmethodeChoice testmethode;

    /**
     * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/Quantitatives
     * Ergebnis Description: (Mess-)Wert des Analyt-Resultats.
     */
    @Path(
            "/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_panel.v0]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='Pro Analyt']/items[at0001 and name/value='Quantitatives Ergebnis']/value")
    @Choice
    private ProAnalytQuantitativesErgebnisChoice quantitativesErgebnis;

    public void setLabortestBezeichnungDefiningCode(LabortestBezeichnungDefiningCode labortestBezeichnungDefiningCode) {
        this.labortestBezeichnungDefiningCode = labortestBezeichnungDefiningCode;
    }

    public LabortestBezeichnungDefiningCode getLabortestBezeichnungDefiningCode() {
        return this.labortestBezeichnungDefiningCode;
    }

    public void setProbendetail(List<Cluster> probendetail) {
        this.probendetail = probendetail;
    }

    public List<Cluster> getProbendetail() {
        return this.probendetail;
    }

    public void setVirusnachweistestDefiningCode(VirusnachweistestDefiningCode virusnachweistestDefiningCode) {
        this.virusnachweistestDefiningCode = virusnachweistestDefiningCode;
    }

    public VirusnachweistestDefiningCode getVirusnachweistestDefiningCode() {
        return this.virusnachweistestDefiningCode;
    }

    public void setNachweisDefiningCode(NachweisDefiningCode nachweisDefiningCode) {
        this.nachweisDefiningCode = nachweisDefiningCode;
    }

    public NachweisDefiningCode getNachweisDefiningCode() {
        return this.nachweisDefiningCode;
    }

    public void setAnalyseergebnisDetails(List<Cluster> analyseergebnisDetails) {
        this.analyseergebnisDetails = analyseergebnisDetails;
    }

    public List<Cluster> getAnalyseergebnisDetails() {
        return this.analyseergebnisDetails;
    }

    public void setErgebnisStatusValue(String ergebnisStatusValue) {
        this.ergebnisStatusValue = ergebnisStatusValue;
    }

    public String getErgebnisStatusValue() {
        return this.ergebnisStatusValue;
    }

    public void setStrukturierteTestdiagnostik(List<Cluster> strukturierteTestdiagnostik) {
        this.strukturierteTestdiagnostik = strukturierteTestdiagnostik;
    }

    public List<Cluster> getStrukturierteTestdiagnostik() {
        return this.strukturierteTestdiagnostik;
    }

    public void setMultimediaDarstellung(List<Cluster> multimediaDarstellung) {
        this.multimediaDarstellung = multimediaDarstellung;
    }

    public List<Cluster> getMultimediaDarstellung() {
        return this.multimediaDarstellung;
    }

    public void setStrukturierteErfassungDerStorfaktoren(List<Cluster> strukturierteErfassungDerStorfaktoren) {
        this.strukturierteErfassungDerStorfaktoren = strukturierteErfassungDerStorfaktoren;
    }

    public List<Cluster> getStrukturierteErfassungDerStorfaktoren() {
        return this.strukturierteErfassungDerStorfaktoren;
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

    public void setWidthValue(TemporalAmount widthValue) {
        this.widthValue = widthValue;
    }

    public TemporalAmount getWidthValue() {
        return this.widthValue;
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

    public void setTestmethode(ProAnalytTestmethodeChoice testmethode) {
        this.testmethode = testmethode;
    }

    public ProAnalytTestmethodeChoice getTestmethode() {
        return this.testmethode;
    }

    public void setQuantitativesErgebnis(ProAnalytQuantitativesErgebnisChoice quantitativesErgebnis) {
        this.quantitativesErgebnis = quantitativesErgebnis;
    }

    public ProAnalytQuantitativesErgebnisChoice getQuantitativesErgebnis() {
        return this.quantitativesErgebnis;
    }
}
