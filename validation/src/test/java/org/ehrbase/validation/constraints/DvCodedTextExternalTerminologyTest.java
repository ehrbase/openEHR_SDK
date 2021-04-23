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
Add failimport org.ehrbase.validation.constraints.terminology.ExternalTerminologyValidationException;
import org.ehrbase.validation.constraints.terminology.FhirTerminologyValidationSupport;
import org.ehrbase.validation.constraints.wrappers.CArchetypeConstraint;
import org.ehrbase.validation.constraints.wrappers.ValidationException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

@SuppressWarnings("HttpUrlsUsage")
public class DvCodedTextExternalTerminologyTest extends ConstraintTestBase {

    private final FhirTerminologyValidationSupport fhirTerminologyValidator = new FhirTerminologyValidationSupport("https://r4.ontoserver.csiro.au/fhir");

    @Test
    public void testFhirCodeSystem() throws IOException, XmlException {
        setUpContext("./src/test/resources/constraints/terminology/fhir_codesystem.xml");

        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://terminology.hl7.org/CodeSystem/FDI-surface"), "B");
        DvCodedText dvCodedText = new DvCodedText("Buccal", codePhrase);

        new CArchetypeConstraint(null, fhirTerminologyValidator).validate("test", dvCodedText, archetypeconstraint);
    }

    @Test
    public void testFhirCodeSystem_WrongTerminologyId() throws IOException, XmlException {
        setUpContext("./src/test/resources/constraints/terminology/fhir_codesystem.xml");

        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://terminology.hl7.org/CodeSystem/v2-0444"), "G");
        DvCodedText dvCodedText = new DvCodedText("Prefix Given Middle Family Suffix", codePhrase);

        CArchetypeConstraint constraint = new CArchetypeConstraint(null, fhirTerminologyValidator);
        ValidationException ex = Assert.assertThrows(ValidationException.class, () -> constraint.validate("test", dvCodedText, archetypeconstraint));

        Assert.assertEquals("Validation error at test, ELT01:Validation error at test, CODE_PHRASE_02:CodePhrase terminology does not match, " +
                "expected: http://terminology.hl7.org/CodeSystem/FDI-surface, found: http://terminology.hl7.org/CodeSystem/v2-0444", ex.getMessage());
    }

    @Test
    public void testFhirCodeSystem_WrongCode() throws IOException, XmlException {
        setUpContext("./src/test/resources/constraints/terminology/fhir_codesystem.xml");

        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://terminology.hl7.org/CodeSystem/FDI-surface"), "G");
        DvCodedText dvCodedText = new DvCodedText("Prefix Given Middle Family Suffix", codePhrase);

        CArchetypeConstraint constraint = new CArchetypeConstraint(null, fhirTerminologyValidator);
        ValidationException ex = Assert.assertThrows(ValidationException.class, () -> constraint.validate("test", dvCodedText, archetypeconstraint));

        Assert.assertEquals("Validation error at test, ELT01:Validation error at test, CODE_PHRASE_03:The specified code 'G' " +
                "is not known to belong to the specified code system 'http://terminology.hl7.org/CodeSystem/FDI-surface'", ex.getMessage());
    }

    @Test
    public void testFhirValueSet() throws IOException, XmlException {
        setUpContext("./src/test/resources/constraints/terminology/fhir_valueset.xml");

        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://hl7.org/fhir/ValueSet/surface"), "B");
        DvCodedText dvCodedText = new DvCodedText("Buccal", codePhrase);

        new CArchetypeConstraint(null, fhirTerminologyValidator).validate("test", dvCodedText, archetypeconstraint);
    }

    @Test
    public void testFhirValueSet_WrongTerminologyId() throws IOException, XmlException {
        setUpContext("./src/test/resources/constraints/terminology/fhir_valueset.xml");

        CodePhrase codePhrase = new CodePhrase(new TerminologyId("https://www.netzwerk-universitaetsmedizin.de/fhir/ValueSet/frailty-score"), "4");
        DvCodedText dvCodedText = new DvCodedText("Vulnerable", codePhrase);

        CArchetypeConstraint constraint = new CArchetypeConstraint(null, fhirTerminologyValidator);
        ValidationException ex = Assert.assertThrows(ValidationException.class, () -> constraint.validate("test", dvCodedText, archetypeconstraint));

        Assert.assertEquals("Validation error at test, ELT01:Validation error at test, CODE_PHRASE_02:CodePhrase terminology does not match, " +
                "expected: http://hl7.org/fhir/ValueSet/surface, found: https://www.netzwerk-universitaetsmedizin.de/fhir/ValueSet/frailty-score", ex.getMessage());
    }

    @Test
    public void testFhirValueSet_WrongCode() throws IOException, XmlException {
        setUpContext("./src/test/resources/constraints/terminology/fhir_valueset.xml");

        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://hl7.org/fhir/ValueSet/surface"), "4");
        DvCodedText dvCodedText = new DvCodedText("Vulnerable", codePhrase);

        CArchetypeConstraint constraint = new CArchetypeConstraint(null, fhirTerminologyValidator);
        ValidationException ex = Assert.assertThrows(ValidationException.class, () -> constraint.validate("test", dvCodedText, archetypeconstraint));

        Assert.assertEquals("Validation error at test, ELT01:Validation error at test, CODE_PHRASE_03:CodePhrase codeString does not match any option, found:4", ex.getMessage());
    }

    @Test
    public void testFailOnError_Enabled() throws IOException, XmlException {
        FhirTerminologyValidationSupport validationSupport = new FhirTerminologyValidationSupport("https://r4.ontoserver.csiro.fr/fhir");
        setUpContext("./src/test/resources/constraints/terminology/fhir_codesystem.xml");

        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://terminology.hl7.org/CodeSystem/FDI-surface"), "B");
        DvCodedText dvCodedText = new DvCodedText("Buccal", codePhrase);

        CArchetypeConstraint constraint = new CArchetypeConstraint(null, validationSupport);
        ExternalTerminologyValidationException ex = Assert.assertThrows(ExternalTerminologyValidationException.class, () -> constraint.validate("test", dvCodedText, archetypeconstraint));

        Assert.assertEquals("An error occurred while checking if FHIR terminology server supports the referenceSetUri: " +
                "terminology://fhir.hl7.org/CodeSystem?url=http://terminology.hl7.org/CodeSystem/FDI-surface", ex.getMessage());
    }

    @Test
    public void testFailOnError_Disabled() throws IOException, XmlException {
        FhirTerminologyValidationSupport validationSupport = new FhirTerminologyValidationSupport("https://r4.ontoserver.csiro.fr/fhir", false);
        setUpContext("./src/test/resources/constraints/terminology/fhir_codesystem.xml");

        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://terminology.hl7.org/CodeSystem/FDI-surface"), "B");
        DvCodedText dvCodedText = new DvCodedText("Buccal", codePhrase);

        new CArchetypeConstraint(null, validationSupport).validate("test", dvCodedText, archetypeconstraint);

        Assert.assertTrue("No exception is thrown", true);
    }
}
