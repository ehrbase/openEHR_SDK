package org.ehrbase.client.classgenerator.examples.testalltypesenv1.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_COUNT")
public class ArbolChoiceDvcount implements ArbolChoiceChoice {
    @Path("|magnitude")
    private Long choiceMagnitude;

    public void setChoiceMagnitude(Long choiceMagnitude) {
        this.choiceMagnitude = choiceMagnitude;
    }

    public Long getChoiceMagnitude() {
        return this.choiceMagnitude;
    }
}
