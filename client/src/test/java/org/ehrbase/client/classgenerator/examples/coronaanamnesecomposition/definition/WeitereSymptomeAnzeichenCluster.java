package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

import java.util.List;

@Entity
public class WeitereSymptomeAnzeichenCluster {
    @Path("/items[at0005]/value|defining_code")
    private VorhandenDefiningcode vorhandenDefiningcode;

    @Path("/items[at0004]/value|value")
    private String bezeichnungDesSymptomsOderAnzeichensValue;

    @Path("/items[at0035]/value|value")
    private String kommentarValue;

    @Path("/items[at0026]")
    private List<Cluster> anzeichen;

    public void setVorhandenDefiningcode(VorhandenDefiningcode vorhandenDefiningcode) {
        this.vorhandenDefiningcode = vorhandenDefiningcode;
    }

    public VorhandenDefiningcode getVorhandenDefiningcode() {
        return this.vorhandenDefiningcode;
    }

    public void setBezeichnungDesSymptomsOderAnzeichensValue(
            String bezeichnungDesSymptomsOderAnzeichensValue) {
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
