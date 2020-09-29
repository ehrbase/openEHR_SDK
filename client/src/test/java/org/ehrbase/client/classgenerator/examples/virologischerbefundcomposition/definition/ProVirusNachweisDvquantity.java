package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_QUANTITY")
public class ProVirusNachweisDvquantity implements ProVirusNachweisChoice {
    @Path("|magnitude")
    private Double viruslastCtWertMagnitude;

    @Path("|units")
    private String viruslastCtWertUnits;

    public void setViruslastCtWertMagnitude(Double viruslastCtWertMagnitude) {
        this.viruslastCtWertMagnitude = viruslastCtWertMagnitude;
    }

    public Double getViruslastCtWertMagnitude() {
        return this.viruslastCtWertMagnitude;
    }

    public void setViruslastCtWertUnits(String viruslastCtWertUnits) {
        this.viruslastCtWertUnits = viruslastCtWertUnits;
    }

    public String getViruslastCtWertUnits() {
        return this.viruslastCtWertUnits;
    }
}
