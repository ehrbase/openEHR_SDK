package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class AnatomischeLokalisationDiagnostischeSicherheitDvcodedtext implements AnatomischeLokalisationDiagnostischeSicherheitChoice {
    @Path("|defining_code")
    private DiagnostischeSicherheitDefiningcode diagnostischeSicherheitDefiningcode;

    public void setDiagnostischeSicherheitDefiningcode(
            DiagnostischeSicherheitDefiningcode diagnostischeSicherheitDefiningcode) {
        this.diagnostischeSicherheitDefiningcode = diagnostischeSicherheitDefiningcode;
    }

    public DiagnostischeSicherheitDefiningcode getDiagnostischeSicherheitDefiningcode() {
        return this.diagnostischeSicherheitDefiningcode;
    }
}
