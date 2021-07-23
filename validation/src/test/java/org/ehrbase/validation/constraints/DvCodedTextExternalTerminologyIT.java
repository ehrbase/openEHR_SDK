/*
 * Copyright (c) 2019 Vitasystems GmbH and Hannover Medical School.
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

package org.ehrbase.validation.constraints;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.validation.constraints.terminology.FhirTerminologyValidationSupport;
import org.ehrbase.validation.constraints.wrappers.CArchetypeConstraint;
import org.ehrbase.validation.constraints.wrappers.ValidationException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

/**
 * Integration tests for external FHIR terminology server validation.
 * <p>
 * Note: Requires a FHIR Terminology Server (https://r4.ontoserver.csiro.au/fhir) up and running
 * and the following CodeSystem and ValueSet:
 * <ul>
 *     <li>http://hl7.org/fhir/observation-status</li>
 *     <li>http://terminology.hl7.org/ValueSet/v3-EntityNameUseR2</li>
 * </ul>
 */
@SuppressWarnings("HttpUrlsUsage")
public class DvCodedTextExternalTerminologyIT extends ConstraintTestBase {

    private final FhirTerminologyValidationSupport fhirTerminologyValidator = new FhirTerminologyValidationSupport("https://r4.ontoserver.csiro.au/fhir");

    @Test
    @Ignore("Requires a FHIR Terminology Server")
    public void testFhirCodeSystem() throws IOException, XmlException {
        setUpContext("./src/test/resources/constraints/terminology/fhir_codesystem.xml");

        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://hl7.org/fhir/observation-status"), "final");
        DvCodedText dvCodedText = new DvCodedText("Final", codePhrase);

        new CArchetypeConstraint(null, fhirTerminologyValidator).validate("test", dvCodedText, archetypeconstraint);
    }

    @Test
    @Ignore("Requires a FHIR Terminology Server")
    public void testFhirCodeSystem_WrongTerminologyId() throws IOException, XmlException {
        setUpContext("./src/test/resources/constraints/terminology/fhir_codesystem.xml");

        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://hl7.org/fhir/name-use"), "usual");
        DvCodedText dvCodedText = new DvCodedText("Usual", codePhrase);

        CArchetypeConstraint constraint = new CArchetypeConstraint(null, fhirTerminologyValidator);
        ValidationException ex = Assert.assertThrows(ValidationException.class, () -> constraint.validate("test", dvCodedText, archetypeconstraint));

        Assert.assertEquals("Validation error at test, ELT01:Validation error at test, CODE_PHRASE_02:CodePhrase terminology does not match, " +
                "expected: http://hl7.org/fhir/observation-status, found: http://hl7.org/fhir/name-use", ex.getMessage());
    }

    @Test
    @Ignore("Requires a FHIR Terminology Server")
    public void testFhirCodeSystem_WrongCode() throws IOException, XmlException {
        setUpContext("./src/test/resources/constraints/terminology/fhir_codesystem.xml");

        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://hl7.org/fhir/observation-status"), "casual");
        DvCodedText dvCodedText = new DvCodedText("Casual", codePhrase);

        CArchetypeConstraint constraint = new CArchetypeConstraint(null, fhirTerminologyValidator);
        ValidationException ex = Assert.assertThrows(ValidationException.class, () -> constraint.validate("test", dvCodedText, archetypeconstraint));

        Assert.assertEquals("Validation error at test, ELT01:Validation error at test, CODE_PHRASE_03:The specified code 'casual' " +
                "is not known to belong to the specified code system 'http://hl7.org/fhir/observation-status'", ex.getMessage());
    }

    @Test
    @Ignore("Requires a FHIR Terminology Server")
    public void testFhirValueSet() throws IOException, XmlException {
        setUpContext("./src/test/resources/constraints/terminology/fhir_valueset.xml");

        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://terminology.hl7.org/ValueSet/v3-EntityNameUseR2"), "ANON");
        DvCodedText dvCodedText = new DvCodedText("Anonymous", codePhrase);

        new CArchetypeConstraint(null, fhirTerminologyValidator).validate("test", dvCodedText, archetypeconstraint);
    }

    @Test
    @Ignore("Requires a FHIR Terminology Server")
    public void testFhirValueSet_WrongTerminologyId() throws IOException, XmlException {
        setUpContext("./src/test/resources/constraints/terminology/fhir_valueset.xml");

        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://hl7.org/fhir/ValueSet/languages"), "de");
        DvCodedText dvCodedText = new DvCodedText("German", codePhrase);

        CArchetypeConstraint constraint = new CArchetypeConstraint(null, fhirTerminologyValidator);
        ValidationException ex = Assert.assertThrows(ValidationException.class, () -> constraint.validate("test", dvCodedText, archetypeconstraint));

        Assert.assertEquals("Validation error at test, ELT01:Validation error at test, CODE_PHRASE_02:CodePhrase terminology does not match, " +
                "expected: http://terminology.hl7.org/ValueSet/v3-EntityNameUseR2, found: http://hl7.org/fhir/ValueSet/languages", ex.getMessage());
    }

    @Test
    @Ignore("Requires a FHIR Terminology Server")
    public void testFhirValueSet_WrongCode() throws IOException, XmlException {
        setUpContext("./src/test/resources/constraints/terminology/fhir_valueset.xml");

        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://terminology.hl7.org/ValueSet/v3-EntityNameUseR2"), "UKN");
        DvCodedText dvCodedText = new DvCodedText("Unknown", codePhrase);

        CArchetypeConstraint constraint = new CArchetypeConstraint(null, fhirTerminologyValidator);
        ValidationException ex = Assert.assertThrows(ValidationException.class, () -> constraint.validate("test", dvCodedText, archetypeconstraint));

        Assert.assertEquals("Validation error at test, ELT01:Validation error at test, CODE_PHRASE_03:CodePhrase codeString does not match any option, found: UKN", ex.getMessage());
    }
}
