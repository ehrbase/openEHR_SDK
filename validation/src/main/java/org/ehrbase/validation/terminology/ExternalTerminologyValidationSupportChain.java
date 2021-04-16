package org.ehrbase.validation.terminology;

import com.nedap.archie.rm.datatypes.CodePhrase;

import java.util.ArrayList;
import java.util.List;

public class ExternalTerminologyValidationSupportChain implements ExternalTerminologyValidationSupport {

    private final List<ExternalTerminologyValidationSupport> chain;

    public ExternalTerminologyValidationSupportChain() {
        chain = new ArrayList<>();
    }

    public ExternalTerminologyValidationSupportChain(List<ExternalTerminologyValidationSupport> chain) {
        this.chain = chain;
    }

    @Override
    public boolean supports(String referenceSetUri) {
        for (ExternalTerminologyValidationSupport next : chain) {
            if (next.supports(referenceSetUri)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void validate(String referenceSetUri, CodePhrase codePhrase) {
        for (ExternalTerminologyValidationSupport next : chain) {
            next.validate(referenceSetUri, codePhrase);
        }
    }
}
