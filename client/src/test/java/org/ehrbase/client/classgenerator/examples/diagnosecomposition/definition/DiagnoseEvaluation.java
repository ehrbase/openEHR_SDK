package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-EVALUATION.problem_diagnosis.v1")
public class DiagnoseEvaluation {
    @Path("/data[at0001]/items[at0069]/name|value")
    private String kommentarValue;

    @Path("/language")
    private Language language;

    @Path("/protocol[at0032]/items[at0070]/value|value")
    private TemporalAccessor zuletztAktualisiertValue;

    @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.diagnose_details.v0]")
    private DiagnosedetailsCluster diagnosedetails;

    @Path("/data[at0001]/items[at0046]")
    private List<Cluster> status;

    @Path("/data[at0001]/items[at0009]/name|value")
    private String klinischeBeschreibungValue;

    @Path("/data[at0001]/items[at0077]/value|value")
    private TemporalAccessor derErstdiagnoseValue;

    @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.etiology.v1]")
    private AtiopathogeneseCluster atiopathogenese;

    @Path("/protocol[at0032]/items[at0071]")
    private List<Cluster> erweiterung;

    @Path("/data[at0001]/items[at0003]/value|value")
    private TemporalAccessor zeitpunktDerKlinischenFeststellungValue;

    @Path("/data[at0001]/items[at0005]/value")
    @Choice
    private AtiopathogeneseSchweregradChoice schweregrad;

    @Path("/data[at0001]/items[at0030]/value|value")
    private TemporalAccessor zeitpunktDerGenesungValue;

    @Path("/protocol[at0032]/items[at0070]/name|value")
    private String zuletztAktualisiertValueTree;

    @Path("/data[at0001]/items[at0069]/value|value")
    private String kommentarValueStructure;

    @Path("/data[at0001]/items[at0002]/value|defining_code")
    private DerDiagnoseDefiningcode derDiagnoseDefiningcode;

    @Path("/data[at0001]/items[at0012]/name|value")
    private String korperstelleValue;

    @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.anatomical_location.v1]")
    private List<AnatomischeLokalisationCluster> anatomischeLokalisation;

    @Path("/data[at0001]/items[at0077]/name|value")
    private String derErstdiagnoseValueZeitpunktDesAuftretens;

    @Path("/data[at0001]/items[at0009]/value|value")
    private String klinischeBeschreibungValueStructure;

    @Path("/subject")
    private PartyProxy subject;

    @Path("/data[at0001]/items[at0003]/name|value")
    private String zeitpunktDerKlinischenFeststellungValueDatum;

    @Path("/data[at0001]/items[at0073]/value")
    @Choice
    private AnatomischeLokalisationDiagnostischeSicherheitChoice diagnostischeSicherheit;

    @Path("/data[at0001]/items[at0012]/value|value")
    private String korperstelleValueStructure;

    public void setKommentarValue(String kommentarValue) {
        this.kommentarValue = kommentarValue;
    }

    public String getKommentarValue() {
        return this.kommentarValue;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setZuletztAktualisiertValue(TemporalAccessor zuletztAktualisiertValue) {
        this.zuletztAktualisiertValue = zuletztAktualisiertValue;
    }

    public TemporalAccessor getZuletztAktualisiertValue() {
        return this.zuletztAktualisiertValue;
    }

    public void setDiagnosedetails(DiagnosedetailsCluster diagnosedetails) {
        this.diagnosedetails = diagnosedetails;
    }

    public DiagnosedetailsCluster getDiagnosedetails() {
        return this.diagnosedetails;
    }

    public void setStatus(List<Cluster> status) {
        this.status = status;
    }

    public List<Cluster> getStatus() {
        return this.status;
    }

    public void setKlinischeBeschreibungValue(String klinischeBeschreibungValue) {
        this.klinischeBeschreibungValue = klinischeBeschreibungValue;
    }

    public String getKlinischeBeschreibungValue() {
        return this.klinischeBeschreibungValue;
    }

    public void setDerErstdiagnoseValue(TemporalAccessor derErstdiagnoseValue) {
        this.derErstdiagnoseValue = derErstdiagnoseValue;
    }

    public TemporalAccessor getDerErstdiagnoseValue() {
        return this.derErstdiagnoseValue;
    }

    public void setAtiopathogenese(AtiopathogeneseCluster atiopathogenese) {
        this.atiopathogenese = atiopathogenese;
    }

    public AtiopathogeneseCluster getAtiopathogenese() {
        return this.atiopathogenese;
    }

    public void setErweiterung(List<Cluster> erweiterung) {
        this.erweiterung = erweiterung;
    }

    public List<Cluster> getErweiterung() {
        return this.erweiterung;
    }

    public void setZeitpunktDerKlinischenFeststellungValue(
            TemporalAccessor zeitpunktDerKlinischenFeststellungValue) {
        this.zeitpunktDerKlinischenFeststellungValue = zeitpunktDerKlinischenFeststellungValue;
    }

    public TemporalAccessor getZeitpunktDerKlinischenFeststellungValue() {
        return this.zeitpunktDerKlinischenFeststellungValue;
    }

    public void setSchweregrad(AtiopathogeneseSchweregradChoice schweregrad) {
        this.schweregrad = schweregrad;
    }

    public AtiopathogeneseSchweregradChoice getSchweregrad() {
        return this.schweregrad;
    }

    public void setZeitpunktDerGenesungValue(TemporalAccessor zeitpunktDerGenesungValue) {
        this.zeitpunktDerGenesungValue = zeitpunktDerGenesungValue;
    }

    public TemporalAccessor getZeitpunktDerGenesungValue() {
        return this.zeitpunktDerGenesungValue;
    }

    public void setZuletztAktualisiertValueTree(String zuletztAktualisiertValueTree) {
        this.zuletztAktualisiertValueTree = zuletztAktualisiertValueTree;
    }

    public String getZuletztAktualisiertValueTree() {
        return this.zuletztAktualisiertValueTree;
    }

    public void setKommentarValueStructure(String kommentarValueStructure) {
        this.kommentarValueStructure = kommentarValueStructure;
    }

    public String getKommentarValueStructure() {
        return this.kommentarValueStructure;
    }

    public void setDerDiagnoseDefiningcode(DerDiagnoseDefiningcode derDiagnoseDefiningcode) {
        this.derDiagnoseDefiningcode = derDiagnoseDefiningcode;
    }

    public DerDiagnoseDefiningcode getDerDiagnoseDefiningcode() {
        return this.derDiagnoseDefiningcode;
    }

    public void setKorperstelleValue(String korperstelleValue) {
        this.korperstelleValue = korperstelleValue;
    }

    public String getKorperstelleValue() {
        return this.korperstelleValue;
    }

    public void setAnatomischeLokalisation(
            List<AnatomischeLokalisationCluster> anatomischeLokalisation) {
        this.anatomischeLokalisation = anatomischeLokalisation;
    }

    public List<AnatomischeLokalisationCluster> getAnatomischeLokalisation() {
        return this.anatomischeLokalisation;
    }

    public void setDerErstdiagnoseValueZeitpunktDesAuftretens(
            String derErstdiagnoseValueZeitpunktDesAuftretens) {
        this.derErstdiagnoseValueZeitpunktDesAuftretens = derErstdiagnoseValueZeitpunktDesAuftretens;
    }

    public String getDerErstdiagnoseValueZeitpunktDesAuftretens() {
        return this.derErstdiagnoseValueZeitpunktDesAuftretens;
    }

    public void setKlinischeBeschreibungValueStructure(String klinischeBeschreibungValueStructure) {
        this.klinischeBeschreibungValueStructure = klinischeBeschreibungValueStructure;
    }

    public String getKlinischeBeschreibungValueStructure() {
        return this.klinischeBeschreibungValueStructure;
    }

    public void setSubject(PartyProxy subject) {
        this.subject = subject;
    }

    public PartyProxy getSubject() {
        return this.subject;
    }

    public void setZeitpunktDerKlinischenFeststellungValueDatum(
            String zeitpunktDerKlinischenFeststellungValueDatum) {
        this.zeitpunktDerKlinischenFeststellungValueDatum = zeitpunktDerKlinischenFeststellungValueDatum;
    }

    public String getZeitpunktDerKlinischenFeststellungValueDatum() {
        return this.zeitpunktDerKlinischenFeststellungValueDatum;
    }

    public void setDiagnostischeSicherheit(
            AnatomischeLokalisationDiagnostischeSicherheitChoice diagnostischeSicherheit) {
        this.diagnostischeSicherheit = diagnostischeSicherheit;
    }

    public AnatomischeLokalisationDiagnostischeSicherheitChoice getDiagnostischeSicherheit() {
        return this.diagnostischeSicherheit;
    }

    public void setKorperstelleValueStructure(String korperstelleValueStructure) {
        this.korperstelleValueStructure = korperstelleValueStructure;
    }

    public String getKorperstelleValueStructure() {
        return this.korperstelleValueStructure;
    }
}
