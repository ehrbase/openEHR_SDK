package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_TEXT")
public class BewertungDesGesundheitsrisikosRisikofaktorenDvtext implements BewertungDesGesundheitsrisikosRisikofaktorenChoice {
    @Path("|value")
    private String risikofaktorenValue;

    public void setRisikofaktorenValue(String risikofaktorenValue) {
        this.risikofaktorenValue = risikofaktorenValue;
    }

    public String getRisikofaktorenValue() {
        return this.risikofaktorenValue;
    }
}
