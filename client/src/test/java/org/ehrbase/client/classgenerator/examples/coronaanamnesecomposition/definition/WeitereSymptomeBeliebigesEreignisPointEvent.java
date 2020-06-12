package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@OptionFor("POINT_EVENT")
public class WeitereSymptomeBeliebigesEreignisPointEvent implements WeitereSymptomeBeliebigesEreignisChoice {
    @Path("/time|value")
    private TemporalAccessor timeValue;

    @Path("/data[at0003]/items[at0022]")
    private List<WeitereSymptomeAnzeichenCluster> anzeichen;

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }

    public void setAnzeichen(List<WeitereSymptomeAnzeichenCluster> anzeichen) {
        this.anzeichen = anzeichen;
    }

    public List<WeitereSymptomeAnzeichenCluster> getAnzeichen() {
        return this.anzeichen;
    }
}
