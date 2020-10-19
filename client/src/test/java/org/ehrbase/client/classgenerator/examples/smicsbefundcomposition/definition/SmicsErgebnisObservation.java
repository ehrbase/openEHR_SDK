package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.smics_befund.v1")
public class SmicsErgebnisObservation {
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[at0016]/value|defining_code")
    private ErregernameDefiningcode erregernameDefiningcode;

    @Path("/language")
    private Language language;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.location.v1]")
    private List<StandortCluster> standort;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[at0021]/value|value")
    private TemporalAccessor zeitpunktDesErstenErregernachweisesValue;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[at0020]/value|value")
    private TemporalAccessor zeitpunktDesLetztenErregernachweisesValue;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0012]/items[at0013]/value|value")
    private String transmissionswegValue;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0012]/items[at0014]/value|value")
    private String ubertragungswegValue;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[openEHR-EHR-CLUSTER.erregerdetails.v1]")
    private ErregerdetailsCluster erregerdetails;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|defining_code")
    private SmicsErgebniskategorieDefiningcode smicsErgebniskategorieDefiningcode;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|value")
    private Boolean multispeziesValue;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[at0037]")
    private List<LaboranalytResultatAnzahlDerErregernachweiseElement> anzahlDerErregernachweise;

    @Path("/data[at0001]/events[at0002]/time|value")
    private TemporalAccessor timeValue;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006]/value|value")
    private TemporalAccessor beginnValue;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0007]/value|value")
    private TemporalAccessor beginnDerUntersuchungValue;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0008]/value|value")
    private TemporalAccessor endeValue;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0009]/value|value")
    private TemporalAmount dauerValue;

    @Path("/subject")
    private PartyProxy subject;

    @Path("/data[at0001]/origin|value")
    private TemporalAccessor originValue;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[openEHR-EHR-CLUSTER.molekulare_typisierung.v0 and name/value='Erregertypisierung']")
    private ErregertypisierungCluster erregertypisierung;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0011]/value|value")
    private String kommentarValue;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0012]/items[at0038]/value|value")
    private String kommentarValueArtDerUbertragung;

    public void setErregernameDefiningcode(ErregernameDefiningcode erregernameDefiningcode) {
        this.erregernameDefiningcode = erregernameDefiningcode;
    }

    public ErregernameDefiningcode getErregernameDefiningcode() {
        return this.erregernameDefiningcode;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setStandort(List<StandortCluster> standort) {
        this.standort = standort;
    }

    public List<StandortCluster> getStandort() {
        return this.standort;
    }

    public void setZeitpunktDesErstenErregernachweisesValue(
            TemporalAccessor zeitpunktDesErstenErregernachweisesValue) {
        this.zeitpunktDesErstenErregernachweisesValue = zeitpunktDesErstenErregernachweisesValue;
    }

    public TemporalAccessor getZeitpunktDesErstenErregernachweisesValue() {
        return this.zeitpunktDesErstenErregernachweisesValue;
    }

    public void setZeitpunktDesLetztenErregernachweisesValue(
            TemporalAccessor zeitpunktDesLetztenErregernachweisesValue) {
        this.zeitpunktDesLetztenErregernachweisesValue = zeitpunktDesLetztenErregernachweisesValue;
    }

    public TemporalAccessor getZeitpunktDesLetztenErregernachweisesValue() {
        return this.zeitpunktDesLetztenErregernachweisesValue;
    }

    public void setTransmissionswegValue(String transmissionswegValue) {
        this.transmissionswegValue = transmissionswegValue;
    }

    public String getTransmissionswegValue() {
        return this.transmissionswegValue;
    }

    public void setUbertragungswegValue(String ubertragungswegValue) {
        this.ubertragungswegValue = ubertragungswegValue;
    }

    public String getUbertragungswegValue() {
        return this.ubertragungswegValue;
    }

    public void setErregerdetails(ErregerdetailsCluster erregerdetails) {
        this.erregerdetails = erregerdetails;
    }

    public ErregerdetailsCluster getErregerdetails() {
        return this.erregerdetails;
    }

    public void setSmicsErgebniskategorieDefiningcode(
            SmicsErgebniskategorieDefiningcode smicsErgebniskategorieDefiningcode) {
        this.smicsErgebniskategorieDefiningcode = smicsErgebniskategorieDefiningcode;
    }

    public SmicsErgebniskategorieDefiningcode getSmicsErgebniskategorieDefiningcode() {
        return this.smicsErgebniskategorieDefiningcode;
    }

    public void setMultispeziesValue(Boolean multispeziesValue) {
        this.multispeziesValue = multispeziesValue;
    }

    public Boolean isMultispeziesValue() {
        return this.multispeziesValue;
    }

    public void setAnzahlDerErregernachweise(
            List<LaboranalytResultatAnzahlDerErregernachweiseElement> anzahlDerErregernachweise) {
        this.anzahlDerErregernachweise = anzahlDerErregernachweise;
    }

    public List<LaboranalytResultatAnzahlDerErregernachweiseElement> getAnzahlDerErregernachweise() {
        return this.anzahlDerErregernachweise;
    }

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }

    public void setBeginnValue(TemporalAccessor beginnValue) {
        this.beginnValue = beginnValue;
    }

    public TemporalAccessor getBeginnValue() {
        return this.beginnValue;
    }

    public void setBeginnDerUntersuchungValue(TemporalAccessor beginnDerUntersuchungValue) {
        this.beginnDerUntersuchungValue = beginnDerUntersuchungValue;
    }

    public TemporalAccessor getBeginnDerUntersuchungValue() {
        return this.beginnDerUntersuchungValue;
    }

    public void setEndeValue(TemporalAccessor endeValue) {
        this.endeValue = endeValue;
    }

    public TemporalAccessor getEndeValue() {
        return this.endeValue;
    }

    public void setDauerValue(TemporalAmount dauerValue) {
        this.dauerValue = dauerValue;
    }

    public TemporalAmount getDauerValue() {
        return this.dauerValue;
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

    public void setErregertypisierung(ErregertypisierungCluster erregertypisierung) {
        this.erregertypisierung = erregertypisierung;
    }

    public ErregertypisierungCluster getErregertypisierung() {
        return this.erregertypisierung;
    }

    public void setKommentarValue(String kommentarValue) {
        this.kommentarValue = kommentarValue;
    }

    public String getKommentarValue() {
        return this.kommentarValue;
    }

    public void setKommentarValueArtDerUbertragung(String kommentarValueArtDerUbertragung) {
        this.kommentarValueArtDerUbertragung = kommentarValueArtDerUbertragung;
    }

    public String getKommentarValueArtDerUbertragung() {
        return this.kommentarValueArtDerUbertragung;
    }
}
