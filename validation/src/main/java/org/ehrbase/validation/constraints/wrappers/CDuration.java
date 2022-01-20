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
import org.openehr.schemas.v1.CDURATION;
import org.openehr.schemas.v1.CPRIMITIVE;

import java.util.Map;

/**
 * Validate a DvDuration
 *
 * @see <a href="https://specifications.openehr.org/releases/AM/latest/AOM1.4.html#_c_duration_class">C_DURATION Class</a>
 * <p>
 * Created by christian on 7/23/2016.
 * @see com.nedap.archie.aom.primitives.CDuration
 * @deprecated as of release 1.7, in favor of {@link org.ehrbase.validation.webtemplate.DvDurationValidator}
 */
@Deprecated(since = "1.7")
public class CDuration extends CConstraint implements I_CTypeValidate {

    protected CDuration(Map<String, Map<String, String>> localTerminologyLookup, ExternalTerminologyValidation externalTerminologyValidator) {
        super(localTerminologyLookup, externalTerminologyValidator);
    }

    @Override
    public void validate(String path, Object aValue, CPRIMITIVE cprimitive) {
        CDURATION cduration = (CDURATION) cprimitive;
        String dvDurationStr = aValue.toString();

        //check pattern if any
        //TODO: use a pattern matching test for duration
//        if (cduration.isSetPattern() && !dvDurationStr.matches(cduration.getPattern())){
//            throw new ValidationException(path, "Supplied value does not match pattern:"+dvDurationStr);
//        }

        //range check
        if (cduration.isSetRange()) {
            IntervalComparator.isWithinBoundaries(dvDurationStr, cduration.getRange());
        }
    }
}
