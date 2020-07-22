package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class AtiopathogeneseAtiologieDerKrankheitElement {
    @Path("/value")
    @Choice
    private AtiopathogeneseOrgEhrbaseSerialisationUtilSnakecase5c448433Choice value;

    public void setValue(AtiopathogeneseOrgEhrbaseSerialisationUtilSnakecase5c448433Choice value) {
        this.value = value;
    }

    public AtiopathogeneseOrgEhrbaseSerialisationUtilSnakecase5c448433Choice getValue() {
        return this.value;
    }
}
