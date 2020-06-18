package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.MathFunctionDefiningcode;

import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;

@Entity
@OptionFor("INTERVAL_EVENT")
public class WeitereSymptomeBeliebigesEreignisIntervalEvent implements WeitereSymptomeBeliebigesEreignisChoice {
    @Path("/time|value")
    private TemporalAccessor timeValue;

    @Path("/math_function|defining_code")
    private MathFunctionDefiningcode mathFunctionDefiningcode;

    @Path("/data[at0003]/items[at0022]")
    private List<WeitereSymptomeAnzeichenClusterSpezifischesSymptom> anzeichen;

    @Path("/width|value")
    private TemporalAmount widthValue;

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }

    public void setMathFunctionDefiningcode(MathFunctionDefiningcode mathFunctionDefiningcode) {
        this.mathFunctionDefiningcode = mathFunctionDefiningcode;
    }

    public MathFunctionDefiningcode getMathFunctionDefiningcode() {
        return this.mathFunctionDefiningcode;
    }

    public void setAnzeichen(List<WeitereSymptomeAnzeichenClusterSpezifischesSymptom> anzeichen) {
        this.anzeichen = anzeichen;
    }

    public List<WeitereSymptomeAnzeichenClusterSpezifischesSymptom> getAnzeichen() {
        return this.anzeichen;
    }

    public void setWidthValue(TemporalAmount widthValue) {
        this.widthValue = widthValue;
    }

    public TemporalAmount getWidthValue() {
        return this.widthValue;
    }
}
