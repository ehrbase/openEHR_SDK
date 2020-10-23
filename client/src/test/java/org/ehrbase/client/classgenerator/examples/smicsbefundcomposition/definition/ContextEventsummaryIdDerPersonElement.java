package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class ContextEventsummaryIdDerPersonElement {
    @Path("/value")
    @Choice
    private ContextEventsummaryChoice value;

    public void setValue(ContextEventsummaryChoice value) {
        this.value = value;
    }

    public ContextEventsummaryChoice getValue() {
        return this.value;
    }
}
