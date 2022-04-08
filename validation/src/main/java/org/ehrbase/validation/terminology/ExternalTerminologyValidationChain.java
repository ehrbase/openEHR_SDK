/*
 * Copyright (c) 2021 Vitasystems GmbH.
 *
 * This file is part of project EHRbase
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.validation.terminology;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.ehrbase.functional.Try;
import org.ehrbase.validation.ConstraintViolationException;

import com.nedap.archie.rm.datavalues.DvCodedText;

/**
 * {@link ExternalTerminologyValidation} that provides support for chaining several external terminology server.
 */
public class ExternalTerminologyValidationChain implements ExternalTerminologyValidation {

    private final List<ExternalTerminologyValidation> chain;

    public ExternalTerminologyValidationChain() {
        chain = new ArrayList<>();
    }

    public ExternalTerminologyValidationChain(List<ExternalTerminologyValidation> chain) {
        this.chain = chain;
    }

    
    
    @Override
    public boolean supports(TerminologyParam param) {
      for(ExternalTerminologyValidation next : chain) if(next.supports(param))
        return true;
      return false;
    }
    

    
    /**
     * Adds the given external terminology server to the chain.
     *
     * @param externalTerminologyValidationSupport the external terminology server to add
     */
    public void addExternalTerminologyValidationSupport(
        ExternalTerminologyValidation externalTerminologyValidationSupport) {
        chain.add(externalTerminologyValidationSupport);
    }

    @Override
    public Try<Boolean,ConstraintViolationException> validate(TerminologyParam param) {
      for(ExternalTerminologyValidation next : chain) if(next.supports(param))
        return next.validate(param);
      return Try.success(Boolean.FALSE);
    }

    @Override
    public List<DvCodedText> expand(TerminologyParam param) {
      for(ExternalTerminologyValidation next : chain) if(next.supports(param))
        return next.expand(param);
      return Collections.emptyList();
    }
}
