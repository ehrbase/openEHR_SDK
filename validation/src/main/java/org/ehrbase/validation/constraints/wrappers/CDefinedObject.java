/*
 * Modifications copyright (C) 2019 Christian Chevalley, Vitasystems GmbH and Hannover Medical School.

 * This file is part of Project EHRbase

 * Copyright (c) 2015 Christian Chevalley
 * This file is part of Project Ethercis
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

package org.ehrbase.validation.constraints.wrappers;

import org.ehrbase.validation.terminology.ExternalTerminologyValidation;
import org.ehrbase.validation.CompositionValidator;
import org.openehr.schemas.v1.ARCHETYPECONSTRAINT;
import org.openehr.schemas.v1.CCOMPLEXOBJECT;
import org.openehr.schemas.v1.CDOMAINTYPE;
import org.openehr.schemas.v1.CPRIMITIVEOBJECT;

import java.util.Map;

/**
 * Validate any defined object
 *
 * @see <a href="https://specifications.openehr.org/releases/AM/latest/AOM1.4.html#_c_defined_object_class">C_DEFINED_OBJECT Class</a>
 * <p>
 * Created by christian on 7/24/2016.
 * @deprecated as of release 1.7, in favor of {@link CompositionValidator}
 */
@Deprecated(since = "1.7")
public class CDefinedObject extends CConstraint implements I_CArchetypeConstraintValidate {

    protected CDefinedObject(Map<String, Map<String, String>> localTerminologyLookup, ExternalTerminologyValidation externalTerminologyValidator) {
        super(localTerminologyLookup, externalTerminologyValidator);
    }

    @Override
    public void validate(String path, Object aValue, ARCHETYPECONSTRAINT archetypeconstraint) throws IllegalArgumentException {
        if (archetypeconstraint instanceof CCOMPLEXOBJECT) {
            new CComplexObject(localTerminologyLookup, externalTerminologyValidator).validate(path, aValue, archetypeconstraint);
        } else if (archetypeconstraint instanceof CPRIMITIVEOBJECT) {
            new CPrimitive(localTerminologyLookup, externalTerminologyValidator).validate(path, aValue, archetypeconstraint);
        } else if (archetypeconstraint instanceof CDOMAINTYPE) {
            new CDomainType(localTerminologyLookup, externalTerminologyValidator).validate(path, aValue, archetypeconstraint);
        } else {
            throw new IllegalStateException("INTERNAL: unsupported CDefinedObject:" + archetypeconstraint);
        }
    }
}
