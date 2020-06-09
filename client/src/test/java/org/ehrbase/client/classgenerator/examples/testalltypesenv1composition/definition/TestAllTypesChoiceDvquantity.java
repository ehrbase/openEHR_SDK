package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_QUANTITY")
public class TestAllTypesChoiceDvquantity implements TestAllTypesChoiceChoice {
    @Path("|magnitude")
    private Double choiceMagnitude;

    @Path("|units")
    private String choiceUnits;

    public Double getChoiceMagnitude() {
        return this.choiceMagnitude;
    }

    public void setChoiceMagnitude(Double choiceMagnitude) {
        this.choiceMagnitude = choiceMagnitude;
    }

    public String getChoiceUnits() {
        return this.choiceUnits;
    }

    public void setChoiceUnits(String choiceUnits) {
        this.choiceUnits = choiceUnits;
    }
}
