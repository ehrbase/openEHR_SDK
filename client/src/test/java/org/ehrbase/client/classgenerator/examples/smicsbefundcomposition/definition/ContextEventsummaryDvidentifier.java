package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_IDENTIFIER")
public class ContextEventsummaryDvidentifier implements ContextEventsummaryChoice {
    @Path("")
    private DvIdentifier value;

    public void setValue(DvIdentifier value) {
        this.value = value;
    }

    public DvIdentifier getValue() {
        return this.value;
    }
}
