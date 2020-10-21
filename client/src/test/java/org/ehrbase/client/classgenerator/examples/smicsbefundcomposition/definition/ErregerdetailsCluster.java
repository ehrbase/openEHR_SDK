package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

import java.util.List;

@Entity
@Archetype("openEHR-EHR-CLUSTER.erregerdetails.v1")
public class ErregerdetailsCluster {
    @Path("/items[openEHR-EHR-CLUSTER.laboratory_test_panel.v0 and name/value='Antibiogramm']")
    private AntibiogrammCluster antibiogramm;

    @Path("/items[at0003]/value")
    private DvOrdinal haufigkeit;

    @Path("/items[at0062]/value|value")
    private String kommentarValue;

    @Path("/items[at0057]")
    private List<LaboranalytResultatResistenzmechanismusCluster> resistenzmechanismus;

    @Path("/items[at0035]/value|magnitude")
    private Double keimzahlMagnitude;

    @Path("/items[at0035]/value|units")
    private String keimzahlUnits;

    @Path("/items[at0059]")
    private List<Cluster> weitereErganzungen;

    @Path("/items[at0047]")
    private List<LaboranalytResultatKeimSubtypElement> keimSubtyp;

    @Path("/items[at0035]/null_flavour|defining_code")
    private KeimzahlDefiningcode keimzahlDefiningcode;

    @Path("/items[at0016]/value|value")
    private String virulenzfaktorValue;

    @Path("/items[at0018]/value|defining_code")
    private MreKlasseDefiningcode mreKlasseDefiningcode;

    public void setAntibiogramm(AntibiogrammCluster antibiogramm) {
        this.antibiogramm = antibiogramm;
    }

    public AntibiogrammCluster getAntibiogramm() {
        return this.antibiogramm;
    }

    public void setHaufigkeit(DvOrdinal haufigkeit) {
        this.haufigkeit = haufigkeit;
    }

    public DvOrdinal getHaufigkeit() {
        return this.haufigkeit;
    }

    public void setKommentarValue(String kommentarValue) {
        this.kommentarValue = kommentarValue;
    }

    public String getKommentarValue() {
        return this.kommentarValue;
    }

    public void setResistenzmechanismus(
            List<LaboranalytResultatResistenzmechanismusCluster> resistenzmechanismus) {
        this.resistenzmechanismus = resistenzmechanismus;
    }

    public List<LaboranalytResultatResistenzmechanismusCluster> getResistenzmechanismus() {
        return this.resistenzmechanismus;
    }

    public void setKeimzahlMagnitude(Double keimzahlMagnitude) {
        this.keimzahlMagnitude = keimzahlMagnitude;
    }

    public Double getKeimzahlMagnitude() {
        return this.keimzahlMagnitude;
    }

    public void setKeimzahlUnits(String keimzahlUnits) {
        this.keimzahlUnits = keimzahlUnits;
    }

    public String getKeimzahlUnits() {
        return this.keimzahlUnits;
    }

    public void setWeitereErganzungen(List<Cluster> weitereErganzungen) {
        this.weitereErganzungen = weitereErganzungen;
    }

    public List<Cluster> getWeitereErganzungen() {
        return this.weitereErganzungen;
    }

    public void setKeimSubtyp(List<LaboranalytResultatKeimSubtypElement> keimSubtyp) {
        this.keimSubtyp = keimSubtyp;
    }

    public List<LaboranalytResultatKeimSubtypElement> getKeimSubtyp() {
        return this.keimSubtyp;
    }

    public void setKeimzahlDefiningcode(KeimzahlDefiningcode keimzahlDefiningcode) {
        this.keimzahlDefiningcode = keimzahlDefiningcode;
    }

    public KeimzahlDefiningcode getKeimzahlDefiningcode() {
        return this.keimzahlDefiningcode;
    }

    public void setVirulenzfaktorValue(String virulenzfaktorValue) {
        this.virulenzfaktorValue = virulenzfaktorValue;
    }

    public String getVirulenzfaktorValue() {
        return this.virulenzfaktorValue;
    }

    public void setMreKlasseDefiningcode(MreKlasseDefiningcode mreKlasseDefiningcode) {
        this.mreKlasseDefiningcode = mreKlasseDefiningcode;
    }

    public MreKlasseDefiningcode getMreKlasseDefiningcode() {
        return this.mreKlasseDefiningcode;
    }
}
