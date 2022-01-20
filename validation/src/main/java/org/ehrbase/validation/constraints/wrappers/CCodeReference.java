package org.ehrbase.validation.constraints.wrappers;

import com.nedap.archie.rm.datatypes.CodePhrase;
import org.ehrbase.validation.terminology.ExternalTerminologyValidation;
import org.openehr.schemas.v1.ARCHETYPECONSTRAINT;
import org.openehr.schemas.v1.CCODEREFERENCE;

import java.util.Map;

/**
 * @deprecated as of release 1.7, in favor of {@link org.ehrbase.validation.webtemplate.DvCodedTextValidator}
 */
@Deprecated(since = "1.7")
public class CCodeReference extends CConstraint implements I_CArchetypeConstraintValidate {

    protected CCodeReference(Map<String, Map<String, String>> localTerminologyLookup, ExternalTerminologyValidation externalTerminologyValidator) {
        super(localTerminologyLookup, externalTerminologyValidator);
    }

    @Override
    public void validate(String path, Object aValue, ARCHETYPECONSTRAINT archetypeconstraint) throws IllegalArgumentException {
        if (!(aValue instanceof CodePhrase))
            throw new IllegalStateException("INTERNAL: argument is not a CodePhrase");

        CodePhrase codePhrase = (CodePhrase) aValue;
        CCODEREFERENCE ccodereference = (CCODEREFERENCE) archetypeconstraint;

        if (externalTerminologyValidator != null && externalTerminologyValidator.supports(ccodereference.getReferenceSetUri())) {
            externalTerminologyValidator.validate(path, ccodereference.getReferenceSetUri(), codePhrase);
        }
    }
}
