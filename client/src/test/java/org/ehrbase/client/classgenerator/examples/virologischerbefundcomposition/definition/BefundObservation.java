package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.laboratory_test_result.v1")
public class BefundObservation {
    @Path("/protocol[at0004]/items[at0110]")
    private List<Cluster> testDetails;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.specimen.v1]")
    private List<ProbeCluster> probe;

    @Path("/language")
    private Language language;

    @Path("/protocol[at0004]/items[at0094]/items[openEHR-EHR-CLUSTER.location.v1 and name/value='Einsenderstandort']")
    private EinsenderstandortCluster einsenderstandort;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_panel.v0 and name/value='Kultur']")
    private List<KulturCluster> kultur;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0118]")
    private List<Cluster> multimediaDarstellung;

    @Path("/protocol[at0004]/items[at0094]/items[at0062]/value")
    private DvIdentifier auftragsIdDesAnforderndenEinsendendenSystems;

    @Path("/protocol[at0004]/items[at0094]/items[at0063]/value")
    private DvIdentifier auftragsIdEmpfanger;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|value")
    private String labortestBezeichnungValue;

    @Path("/data[at0001]/events[at0002]/state[at0112]/items[at0114]")
    private List<Cluster> strukturierteErfassungDerStorfaktoren;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0122]")
    private List<Cluster> strukturierteTestdiagnostik;

    @Path("/protocol[at0004]/items[openEHR-EHR-CLUSTER.location.v1 and name/value='Empfängerstandort']")
    private EmpfangerstandortCluster empfangerstandort;

    @Path("/protocol[at0004]/items[at0117]")
    private List<Cluster> erweiterung;

    @Path("/data[at0001]/events[at0002]/time|value")
    private TemporalAccessor timeValue;

    @Path("/protocol[at0004]/items[at0094]/items[at0106]")
    private List<EmpfangerstandortAnforderungElement> anforderung;

    @Path("/subject")
    private PartyProxy subject;

    @Path("/data[at0001]/origin|value")
    private TemporalAccessor originValue;

    @Path("/protocol[at0004]/items[at0094]/items[at0035]")
    private List<Cluster> verteilerliste;

    public void setTestDetails(List<Cluster> testDetails) {
        this.testDetails = testDetails;
    }

    public List<Cluster> getTestDetails() {
        return this.testDetails;
    }

    public void setProbe(List<ProbeCluster> probe) {
        this.probe = probe;
    }

    public List<ProbeCluster> getProbe() {
        return this.probe;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setEinsenderstandort(EinsenderstandortCluster einsenderstandort) {
        this.einsenderstandort = einsenderstandort;
    }

    public EinsenderstandortCluster getEinsenderstandort() {
        return this.einsenderstandort;
    }

    public void setKultur(List<KulturCluster> kultur) {
        this.kultur = kultur;
    }

    public List<KulturCluster> getKultur() {
        return this.kultur;
    }

    public void setMultimediaDarstellung(List<Cluster> multimediaDarstellung) {
        this.multimediaDarstellung = multimediaDarstellung;
    }

    public List<Cluster> getMultimediaDarstellung() {
        return this.multimediaDarstellung;
    }

    public void setAuftragsIdDesAnforderndenEinsendendenSystems(
            DvIdentifier auftragsIdDesAnforderndenEinsendendenSystems) {
        this.auftragsIdDesAnforderndenEinsendendenSystems = auftragsIdDesAnforderndenEinsendendenSystems;
    }

    public DvIdentifier getAuftragsIdDesAnforderndenEinsendendenSystems() {
        return this.auftragsIdDesAnforderndenEinsendendenSystems;
    }

    public void setAuftragsIdEmpfanger(DvIdentifier auftragsIdEmpfanger) {
        this.auftragsIdEmpfanger = auftragsIdEmpfanger;
    }

    public DvIdentifier getAuftragsIdEmpfanger() {
        return this.auftragsIdEmpfanger;
    }

    public void setLabortestBezeichnungValue(String labortestBezeichnungValue) {
        this.labortestBezeichnungValue = labortestBezeichnungValue;
    }

    public String getLabortestBezeichnungValue() {
        return this.labortestBezeichnungValue;
    }

    public void setStrukturierteErfassungDerStorfaktoren(
            List<Cluster> strukturierteErfassungDerStorfaktoren) {
        this.strukturierteErfassungDerStorfaktoren = strukturierteErfassungDerStorfaktoren;
    }

    public List<Cluster> getStrukturierteErfassungDerStorfaktoren() {
        return this.strukturierteErfassungDerStorfaktoren;
    }

    public void setStrukturierteTestdiagnostik(List<Cluster> strukturierteTestdiagnostik) {
        this.strukturierteTestdiagnostik = strukturierteTestdiagnostik;
    }

    public List<Cluster> getStrukturierteTestdiagnostik() {
        return this.strukturierteTestdiagnostik;
    }

    public void setEmpfangerstandort(EmpfangerstandortCluster empfangerstandort) {
        this.empfangerstandort = empfangerstandort;
    }

    public EmpfangerstandortCluster getEmpfangerstandort() {
        return this.empfangerstandort;
    }

    public void setErweiterung(List<Cluster> erweiterung) {
        this.erweiterung = erweiterung;
    }

    public List<Cluster> getErweiterung() {
        return this.erweiterung;
    }

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }

    public void setAnforderung(List<EmpfangerstandortAnforderungElement> anforderung) {
        this.anforderung = anforderung;
    }

    public List<EmpfangerstandortAnforderungElement> getAnforderung() {
        return this.anforderung;
    }

    public void setSubject(PartyProxy subject) {
        this.subject = subject;
    }

    public PartyProxy getSubject() {
        return this.subject;
    }

    public void setOriginValue(TemporalAccessor originValue) {
        this.originValue = originValue;
    }

    public TemporalAccessor getOriginValue() {
        return this.originValue;
    }

    public void setVerteilerliste(List<Cluster> verteilerliste) {
        this.verteilerliste = verteilerliste;
    }

    public List<Cluster> getVerteilerliste() {
        return this.verteilerliste;
    }
}
