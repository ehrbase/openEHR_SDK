package org.ehrbase.validation.constraints.wrappers;

import com.nedap.archie.rm.datatypes.CodePhrase;
import org.ehrbase.validation.terminology.ExternalTerminologyValidationSupport;
import org.openehr.schemas.v1.ARCHETYPECONSTRAINT;
import org.openehr.schemas.v1.CCODEREFERENCE;

import java.util.Map;

public class CCodeReference extends CConstraint implements I_CArchetypeConstraintValidate {

    protected CCodeReference(Map<String, Map<String, String>> localTerminologyLookup, ExternalTerminologyValidationSupport externalTerminologyLookup) {
        super(localTerminologyLookup, externalTerminologyLookup);
    }

    @Override
    public void validate(String path, Object aValue, ARCHETYPECONSTRAINT archetypeconstraint) throws IllegalArgumentException {
        if (!(aValue instanceof CodePhrase))
            throw new IllegalStateException("INTERNAL: argument is not a CodePhrase");

        CodePhrase codePhrase = (CodePhrase) aValue;
        CCODEREFERENCE ccodereference = (CCODEREFERENCE) archetypeconstraint;

        if (externalTerminologyLookup != null && externalTerminologyLookup.supports(ccodereference.getReferenceSetUri())) {
            externalTerminologyLookup.validate(ccodereference.getReferenceSetUri(), codePhrase);
        }
    }
}
