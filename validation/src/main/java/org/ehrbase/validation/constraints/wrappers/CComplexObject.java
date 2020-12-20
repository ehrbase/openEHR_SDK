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

import static org.ehrbase.validation.constraints.wrappers.ValidationException.raise;

import java.util.Map;
import org.openehr.schemas.v1.ARCHETYPECONSTRAINT;
import org.openehr.schemas.v1.CATTRIBUTE;
import org.openehr.schemas.v1.CCOMPLEXOBJECT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validate a complex object
 *
 * @link https://specifications.openehr.org/releases/AM/latest/AOM1.4.html#_c_complex_object_class
 *     <p>Created by christian on 7/22/2016.
 * @see com.nedap.archie.aom.CComplexObject
 */
public class CComplexObject extends CConstraint implements I_CArchetypeConstraintValidate {

  static Logger logger = LoggerFactory.getLogger(CComplexObject.class);

  CComplexObject(Map<String, Map<String, String>> localTerminologyLookup) {
    super(localTerminologyLookup);
  }

  public void validate(String path, Object value, ARCHETYPECONSTRAINT constraint) {

    CCOMPLEXOBJECT ccomplexobject = (CCOMPLEXOBJECT) constraint;

    int attributeCount = ccomplexobject.sizeOfAttributesArray();
    int failCount = 0;
    Exception lastException = null;

    for (CATTRIBUTE cattribute : ccomplexobject.getAttributesArray()) {
      try {
        new CAttribute(localTerminologyLookup).validate(path, value, cattribute);
      } catch (ValidationException e) {
        lastException = e;
        ++failCount;
      }
    }

    if (attributeCount > 0 && failCount > 0) {
      raise(path, lastException.getMessage(), "ELT01");
    }
  }
}
