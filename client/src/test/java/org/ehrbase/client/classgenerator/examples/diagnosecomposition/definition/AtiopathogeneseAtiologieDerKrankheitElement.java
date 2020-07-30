package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class AtiopathogeneseAtiologieDerKrankheitElement {
    @Path("/value")
    @Choice
    private AtiopathogeneseChoice value;

    public void setValue(AtiopathogeneseChoice value) {
        this.value = value;
    }

    public AtiopathogeneseChoice getValue() {
        return this.value;
    }
}
