package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_TEXT")
public class ProVirusNachweisDvtext implements ProVirusNachweisChoice {
    @Path("|value")
    private String nachweisValue;

    public void setNachweisValue(String nachweisValue) {
        this.nachweisValue = nachweisValue;
    }

    public String getNachweisValue() {
        return this.nachweisValue;
    }
}
