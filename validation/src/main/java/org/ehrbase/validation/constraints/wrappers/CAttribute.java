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

import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.datavalues.DataValue;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvText;
import org.apache.commons.text.WordUtils;
import org.apache.xmlbeans.SchemaType;
import org.ehrbase.validation.terminology.ExternalTerminologyValidation;
import org.ehrbase.serialisation.util.SnakeToCamel;
import org.openehr.schemas.v1.ARCHETYPECONSTRAINT;
import org.openehr.schemas.v1.CATTRIBUTE;
import org.openehr.schemas.v1.CMULTIPLEATTRIBUTE;
import org.openehr.schemas.v1.CSINGLEATTRIBUTE;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * validate the constraints for an attribute node
 *
 * @see <a href="https://specifications.openehr.org/releases/AM/latest/AOM1.4.html#_c_attribute_class">C_ATTRIBUTE Class</a>
 * <p>Created by christian on 7/23/2016.
 * @see com.nedap.archie.aom.CAttribute
 * @deprecated as of release 1.7, in favor of {@link org.ehrbase.validation.webtemplate.DefaultValidator}
 */
@Deprecated(since = "1.7")
public class CAttribute extends CConstraint implements I_CArchetypeConstraintValidate {

    private boolean isAttributeResolved = false; // true if a getter or function has been found

    CAttribute(Map<String, Map<String, String>> localTerminologyLookup, ExternalTerminologyValidation externalTerminologyValidator) {
        super(localTerminologyLookup, externalTerminologyValidator);
    }

    @Override
    public void validate(String path, Object aValue, ARCHETYPECONSTRAINT archetypeconstraint) {

        // change the XML type to match the XML expression
        SchemaType type =
                I_CArchetypeConstraintValidate.findSchemaType(
                        I_CArchetypeConstraintValidate.getXmlType(archetypeconstraint));

        CATTRIBUTE cattribute = (CATTRIBUTE) archetypeconstraint.changeType(type);

        if (cattribute.getRmAttributeName().equals("defining_code")) {
            if (aValue instanceof DvCodedText) {
                // process this DvText as a DvCodedText
                new CDvCodedText(localTerminologyLookup, externalTerminologyValidator).validate(path, aValue, cattribute);
            } else if (aValue instanceof DvText) {
                new CDvText(localTerminologyLookup, externalTerminologyValidator).validate(path, aValue, cattribute);
            }

            return;
        }

        Object value = findAttribute(aValue, cattribute.getRmAttributeName());

        if (value == null && IntervalComparator.isOptional(cattribute.getExistence())) return;

        if (!isAttributeResolved && value == null) {
            // check for a function
            value = getFunctionValue(aValue, cattribute.getRmAttributeName());
            if (!isAttributeResolved)
                ValidationException.raise(
                        path,
                        "The following attribute:"
                                + cattribute.getRmAttributeName()
                                + " is expected in object:"
                                + aValue,
                        "ATTR01");
        }

        if (value == null) {
            // resolved but missing
            ValidationException.raise(
                    path, "Mandatory attribute has no value:" + cattribute.getRmAttributeName(), "ATTR02");
        }
        // if value is an enum use its actual value
        else if (value.getClass().isEnum()) {
            value = getEnumValue(path, value);
        }

        if (cattribute instanceof CSINGLEATTRIBUTE) {
            new CSingleAttribute(localTerminologyLookup, externalTerminologyValidator).validate(path, value, cattribute);
        } else if (cattribute instanceof CMULTIPLEATTRIBUTE) {
            new CMultipleAttribute(localTerminologyLookup, externalTerminologyValidator).validate(path, value, cattribute);
        }
    }

    private Object findAttribute(Object object, String attribute) {
        // locate the attribute to check
        if (object instanceof Locatable) {
            return ((Locatable) object).itemAtPath("/" + attribute);
        } else if (object instanceof DataValue) {
            return getAttributeValue(object, attribute);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private Object getAttributeValue(Object obj, String attribute) {
        Class rmClass = obj.getClass();
        Object value = null;
        Method getter;
        String getterName = "get" + new SnakeToCamel(attribute).convert();

        try {
            getter = rmClass.getMethod(getterName);
            isAttributeResolved = true;
            value = getter.invoke(obj, null);

        } catch (Exception e) {
            isAttributeResolved = false;
        }
        return value;
    }

    private Object getFunctionValue(Object obj, String attribute) {
        Class rmClass = obj.getClass();
        Object value = null;
        Method function;
        String functionName = WordUtils.uncapitalize(new SnakeToCamel(attribute).convert());

        try {
            function = rmClass.getMethod(functionName);
            isAttributeResolved = true;
            value = function.invoke(obj);

        } catch (Exception e) {
            isAttributeResolved = false;
        }
        return value;
    }

    private Object getEnumValue(String path, Object obj) {
        Class rmClass = obj.getClass();
        Object value = null;

        try {
            Method getter = rmClass.getMethod("getValue");
            value = getter.invoke(obj);

        } catch (Exception e) {
            ValidationException.raise(path, "Couldn't handle enum value", e.getMessage());
        }
        return value;
    }
}
