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

import org.ehrbase.validation.constraints.terminology.ExternalTerminologyValidationSupport;
import org.openehr.schemas.v1.ARCHETYPECONSTRAINT;
import org.openehr.schemas.v1.CBOOLEAN;
import org.openehr.schemas.v1.CDATE;
import org.openehr.schemas.v1.CDATETIME;
import org.openehr.schemas.v1.CDURATION;
import org.openehr.schemas.v1.CINTEGER;
import org.openehr.schemas.v1.CPRIMITIVEOBJECT;
import org.openehr.schemas.v1.CREAL;
import org.openehr.schemas.v1.CSTRING;
import org.openehr.schemas.v1.CTIME;

import java.util.Map;

/**
 * Validate a primitive
 *
 * @see <a href="https://specifications.openehr.org/releases/AM/latest/AOM1.4.html#_c_primitive_object_class">...</a>
 * <p>
 * Created by christian on 7/24/2016.
 */
public class CPrimitive extends CConstraint implements I_CArchetypeConstraintValidate {

    CPrimitive(Map<String, Map<String, String>> localTerminologyLookup, ExternalTerminologyValidationSupport externalTerminologyValidator) {
        super(localTerminologyLookup, externalTerminologyValidator);
    }

    @Override
    public void validate(String path, Object aValue, ARCHETYPECONSTRAINT archetypeconstraint) throws IllegalArgumentException {

        String rmTypeName = ((CPRIMITIVEOBJECT) archetypeconstraint).getRmTypeName();
//        SchemaType type = I_CArchetypeConstraintValidate.findSchemaType("C_"+((CPRIMITIVEOBJECT)archetypeconstraint).getRmTypeName());
//        Object constraint = archetypeconstraint.changeType(type);

        CPRIMITIVEOBJECT constraint = (CPRIMITIVEOBJECT) archetypeconstraint;

        switch (rmTypeName) {
            case "BOOLEAN":
                new CBoolean(localTerminologyLookup, externalTerminologyValidator).validate(path, aValue, (CBOOLEAN) constraint.getItem().changeType(CBOOLEAN.type));
                break;
            case "STRING":
                new CString(localTerminologyLookup, externalTerminologyValidator).validate(path, aValue, (CSTRING) constraint.getItem().changeType(CSTRING.type));
                break;
            case "INTEGER":
                new CInteger(localTerminologyLookup, externalTerminologyValidator).validate(path, aValue, (CINTEGER) constraint.getItem().changeType(CINTEGER.type));
                break;
            case "REAL":
                new CReal(localTerminologyLookup, externalTerminologyValidator).validate(path, aValue, (CREAL) constraint.getItem().changeType(CREAL.type));
                break;
            case "DATE":
                new CDate(localTerminologyLookup, externalTerminologyValidator).validate(path, aValue, (CDATE) constraint.getItem().changeType(CDATE.type));
                break;
            case "DATE_TIME":
                new CDateTime(localTerminologyLookup, externalTerminologyValidator).validate(path, aValue, (CDATETIME) constraint.getItem().changeType(CDATETIME.type));
                break;
            case "TIME":
                new CTime(localTerminologyLookup, externalTerminologyValidator).validate(path, aValue, (CTIME) constraint.getItem().changeType(CTIME.type));
                break;
            case "DURATION":
                new CDuration(localTerminologyLookup, externalTerminologyValidator).validate(path, aValue, (CDURATION) constraint.getItem().changeType(CDURATION.type));
                break;
            default:
                throw new IllegalStateException("INTERNAL: unsupported CPRIMIITVE:" + archetypeconstraint);
        }
    }
}
