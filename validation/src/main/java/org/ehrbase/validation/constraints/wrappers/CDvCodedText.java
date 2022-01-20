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

import com.nedap.archie.rm.datavalues.DvCodedText;
import org.apache.xmlbeans.SchemaType;
import org.ehrbase.validation.terminology.ExternalTerminologyValidation;
import org.openehr.schemas.v1.ARCHETYPECONSTRAINT;
import org.openehr.schemas.v1.CCODEPHRASE;
import org.openehr.schemas.v1.CCODEREFERENCE;
import org.openehr.schemas.v1.COBJECT;
import org.openehr.schemas.v1.CONSTRAINTREF;
import org.openehr.schemas.v1.CSINGLEATTRIBUTE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Validate a DvCodedText
 *
 * <p>The validation check the passed code phrase bound to local or openehr terminology
 *
 * @see DvCodedText
 * <p>Created by christian on 8/10/2016.
 * @deprecated as of release 1.7, in favor of {@link org.ehrbase.validation.webtemplate.DvCodedTextValidator}
 */
@Deprecated(since = "1.7")
public class CDvCodedText extends CConstraint implements I_CArchetypeConstraintValidate {

    private final Logger logger = LoggerFactory.getLogger(CDvCodedText.class);

    CDvCodedText(Map<String, Map<String, String>> localTerminologyLookup, ExternalTerminologyValidation externalTerminologyValidator) {
        super(localTerminologyLookup, externalTerminologyValidator);
    }

    @Override
    public void validate(String path, Object aValue, ARCHETYPECONSTRAINT archetypeconstraint) {

        DvCodedText checkValue = (DvCodedText) aValue;

        if (!(archetypeconstraint instanceof CSINGLEATTRIBUTE)) {
            ValidationException.raise(path, "Constraint for DvCodedText is not applicable:" + archetypeconstraint, "CODED_TEXT_03");
        }
        CSINGLEATTRIBUTE csingleattribute = (CSINGLEATTRIBUTE) archetypeconstraint;

        COBJECT cobject = csingleattribute.getChildrenArray(0);
        if (cobject != null) {
            SchemaType type = I_CArchetypeConstraintValidate.findSchemaType(I_CArchetypeConstraintValidate.getXmlType(cobject));
            cobject = (COBJECT) cobject.changeType(type);
        }

        if (cobject instanceof CCODEREFERENCE) {
            CCODEREFERENCE ccodereference = (CCODEREFERENCE) cobject;

            new CArchetypeConstraint(localTerminologyLookup, externalTerminologyValidator).validate(path, checkValue.getDefiningCode(), ccodereference);
        } else if (cobject instanceof CCODEPHRASE) {
            CCODEPHRASE ccodephrase = (CCODEPHRASE) cobject;

            // use code phrase validation checker
            new CArchetypeConstraint(localTerminologyLookup, externalTerminologyValidator)
                    .validate(path, checkValue.getDefiningCode(), ccodephrase);

            if (ccodephrase.isSetTerminologyId()
                    && ccodephrase.getTerminologyId() != null
                    && ccodephrase.getTerminologyId().getValue() != null
                    && (ccodephrase.getTerminologyId().getValue().equals("local")
                    || ccodephrase.getTerminologyId().getValue().equals("openehr"))) { // if null, the terminology is let free and we cannot check the code
                checkCodedValue(path, checkValue);
            }
        } else if (cobject instanceof CONSTRAINTREF) { // safely ignore it!
            logger.warn("Constraint reference is not supported, path: {}", path);
        } else {
            ValidationException.raise(path, "Constraint child is not a code phrase constraint:" + cobject, "CODED_TEXT_02");
        }
    }

    /**
     * check if the dvCodedText value is as coded in the code phrase
     *
     * @return
     */
    private void checkCodedValue(String path, DvCodedText codedText) {
        if (localTerminologyLookup == null) // mostly a test scenario
            return;

        for (Map<String, String> lookup : localTerminologyLookup.values()) {
            if (lookup.containsKey(codedText.getDefiningCode().getCodeString())
                    && lookup.get(codedText.getDefiningCode().getCodeString()).equals(codedText.getValue())) {
                return;
            }
        }
        ValidationException.raise(
                path, "CodedText value is not valid:" + codedText.toString(), "CODED_TEXT_01");
    }
}
