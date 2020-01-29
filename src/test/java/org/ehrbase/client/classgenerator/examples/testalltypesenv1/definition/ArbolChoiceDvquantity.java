package org.ehrbase.client.classgenerator.examples.testalltypesenv1.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_QUANTITY")
public class ArbolChoiceDvquantity implements ArbolChoiceChoice {
    @Path("|magnitude")
    private Double choiceMagnitude;

    @Path("|units")
    private String choiceUnits;

    public void setChoiceMagnitude(Double choiceMagnitude) {
        this.choiceMagnitude = choiceMagnitude;
    }

    public Double getChoiceMagnitude() {
        return this.choiceMagnitude;
    }

    public void setChoiceUnits(String choiceUnits) {
        this.choiceUnits = choiceUnits;
    }

    public String getChoiceUnits() {
        return this.choiceUnits;
    }
}
