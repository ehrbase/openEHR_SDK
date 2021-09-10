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
import org.ehrbase.validation.constraints.terminology.ExternalTerminologyValidationException;
import org.ehrbase.validation.constraints.terminology.FhirTerminologyValidationSupport;
import org.ehrbase.validation.constraints.wrappers.CArchetypeConstraint;
import org.ehrbase.validation.constraints.wrappers.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

/**
 * Tests for external FHIR terminology server validation.
 * <p>
 * Note: This class use Mockito.
 */
@SuppressWarnings("HttpUrlsUsage")
public class DvCodedTextExternalTerminologyTest extends ConstraintTestBase {

    private FhirTerminologyValidationSupport fhirTerminologyValidatorMock;

    @Before
    public void setUp() {
        fhirTerminologyValidatorMock = Mockito.mock(FhirTerminologyValidationSupport.class);
    }

    @Test
    public void testFhirCodeSystem() throws IOException, XmlException {
        setUpContext("./src/test/resources/constraints/terminology/fhir_codesystem.xml");
        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://hl7.org/fhir/observation-status"), "final");

        Mockito.when(fhirTerminologyValidatorMock.supports("terminology://fhir.hl7.org/CodeSystem?url=http://hl7.org/fhir/observation-status"))
                .thenReturn(true);
        Mockito.doNothing()
                .when(fhirTerminologyValidatorMock)
                .validate("test", "terminology://fhir.hl7.org/CodeSystem?url=http://hl7.org/fhir/observation-status", codePhrase);

        DvCodedText dvCodedText = new DvCodedText("Final", codePhrase);
        new CArchetypeConstraint(null, fhirTerminologyValidatorMock).validate("test", dvCodedText, archetypeconstraint);
    }

    @Test
    public void testFhirCodeSystem_WrongTerminologyId() throws IOException, XmlException {
        setUpContext("./src/test/resources/constraints/terminology/fhir_codesystem.xml");
        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://hl7.org/fhir/name-use"), "usual");

        Mockito.when(fhirTerminologyValidatorMock.supports("terminology://fhir.hl7.org/CodeSystem?url=http://hl7.org/fhir/observation-status"))
                .thenReturn(true);
        Mockito.doThrow(new ValidationException("test", "CODE_PHRASE_02:CodePhrase terminology does not match, " +
                "expected: http://hl7.org/fhir/observation-status, found: http://hl7.org/fhir/name-use"))
                .when(fhirTerminologyValidatorMock)
                .validate("test", "terminology://fhir.hl7.org/CodeSystem?url=http://hl7.org/fhir/observation-status", codePhrase);

        DvCodedText dvCodedText = new DvCodedText("Usual", codePhrase);
        CArchetypeConstraint constraint = new CArchetypeConstraint(null, fhirTerminologyValidatorMock);
        ValidationException ex = Assert.assertThrows(ValidationException.class, () -> constraint.validate("test", dvCodedText, archetypeconstraint));

        Assert.assertEquals("Validation error at test, ELT01:Validation error at test, CODE_PHRASE_02:CodePhrase terminology does not match, " +
                "expected: http://hl7.org/fhir/observation-status, found: http://hl7.org/fhir/name-use", ex.getMessage());
    }

    @Test
    public void testFhirCodeSystem_WrongCode() throws IOException, XmlException {
        setUpContext("./src/test/resources/constraints/terminology/fhir_codesystem.xml");
        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://hl7.org/fhir/observation-status"), "casual");

        Mockito.when(fhirTerminologyValidatorMock.supports("terminology://fhir.hl7.org/CodeSystem?url=http://hl7.org/fhir/observation-status"))
                .thenReturn(true);
        Mockito.doThrow(new ValidationException("test", "CODE_PHRASE_03:The specified code 'casual' is not known " +
                "to belong to the specified code system 'http://hl7.org/fhir/observation-status'"))
                .when(fhirTerminologyValidatorMock)
                .validate("test", "terminology://fhir.hl7.org/CodeSystem?url=http://hl7.org/fhir/observation-status", codePhrase);

        DvCodedText dvCodedText = new DvCodedText("Casual", codePhrase);
        CArchetypeConstraint constraint = new CArchetypeConstraint(null, fhirTerminologyValidatorMock);
        ValidationException ex = Assert.assertThrows(ValidationException.class, () -> constraint.validate("test", dvCodedText, archetypeconstraint));

        Assert.assertEquals("Validation error at test, ELT01:Validation error at test, CODE_PHRASE_03:The specified code 'casual' " +
                "is not known to belong to the specified code system 'http://hl7.org/fhir/observation-status'", ex.getMessage());
    }

    @Test
    public void testFhirValueSet() throws IOException, XmlException {
        setUpContext("./src/test/resources/constraints/terminology/fhir_valueset.xml");
        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://terminology.hl7.org/CodeSystem/v3-EntityNameUseR2"), "UKN");
        DvCodedText dvCodedText = new DvCodedText("Anonymous", codePhrase);

        Mockito.when(fhirTerminologyValidatorMock.supports("terminology://fhir.hl7.org/ValueSet/$expand?url=http://terminology.hl7.org/ValueSet/v3-EntityNameUseR2"))
                .thenReturn(true);
        Mockito.doNothing()
                .when(fhirTerminologyValidatorMock)
                .validate("test", "terminology://fhir.hl7.org/ValueSet/$expand?url=http://terminology.hl7.org/ValueSet/v3-EntityNameUseR2", codePhrase);

        new CArchetypeConstraint(null, fhirTerminologyValidatorMock).validate("test", dvCodedText, archetypeconstraint);
    }

    @Test
    public void testFhirValueSet_WrongTerminologyId() throws IOException, XmlException {
        setUpContext("./src/test/resources/constraints/terminology/fhir_valueset.xml");
        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://snomed.info/sct"), "ANON");
        DvCodedText dvCodedText = new DvCodedText("Anonymous", codePhrase);

        Mockito.when(fhirTerminologyValidatorMock.supports("terminology://fhir.hl7.org/ValueSet/$expand?url=http://terminology.hl7.org/ValueSet/v3-EntityNameUseR2"))
                .thenReturn(true);
        Mockito.doThrow(new ValidationException("test", "CODE_PHRASE_02:CodePhrase terminology does not match, " +
                "expected: http://terminology.hl7.org/CodeSystem/v3-EntityNameUseR2, found: http://snomed.info/sct"))
                .when(fhirTerminologyValidatorMock)
                .validate("test", "terminology://fhir.hl7.org/ValueSet/$expand?url=http://terminology.hl7.org/ValueSet/v3-EntityNameUseR2", codePhrase);

        CArchetypeConstraint constraint = new CArchetypeConstraint(null, fhirTerminologyValidatorMock);
        ValidationException ex = Assert.assertThrows(ValidationException.class, () -> constraint.validate("test", dvCodedText, archetypeconstraint));

        Assert.assertEquals("Validation error at test, ELT01:Validation error at test, CODE_PHRASE_02:CodePhrase terminology does not match, " +
                "expected: http://terminology.hl7.org/CodeSystem/v3-EntityNameUseR2, found: http://snomed.info/sct", ex.getMessage());
    }

    @Test
    public void testFhirValueSet_WrongCode() throws IOException, XmlException {
        setUpContext("./src/test/resources/constraints/terminology/fhir_valueset.xml");
        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://terminology.hl7.org/CodeSystem/v3-EntityNameUseR2"), "UKN");
        DvCodedText dvCodedText = new DvCodedText("Unknown", codePhrase);

        Mockito.when(fhirTerminologyValidatorMock.supports("terminology://fhir.hl7.org/ValueSet/$expand?url=http://terminology.hl7.org/ValueSet/v3-EntityNameUseR2"))
                .thenReturn(true);
        Mockito.doThrow(new ValidationException("test", "CODE_PHRASE_03:CodePhrase codeString does not match any option " +
                        "from the specified ValueSet http://terminology.hl7.org/ValueSet/v3-EntityNameUseR2, found: UKN"))
                .when(fhirTerminologyValidatorMock)
                .validate("test", "terminology://fhir.hl7.org/ValueSet/$expand?url=http://terminology.hl7.org/ValueSet/v3-EntityNameUseR2", codePhrase);

        CArchetypeConstraint constraint = new CArchetypeConstraint(null, fhirTerminologyValidatorMock);
        ValidationException ex = Assert.assertThrows(ValidationException.class, () -> constraint.validate("test", dvCodedText, archetypeconstraint));

        Assert.assertEquals("Validation error at test, ELT01:Validation error at test, CODE_PHRASE_03:CodePhrase codeString does not match any option " +
                "from the specified ValueSet http://terminology.hl7.org/ValueSet/v3-EntityNameUseR2, found: UKN", ex.getMessage());
    }

    @Test
    public void testFailOnError_Enabled() throws IOException, XmlException {
        FhirTerminologyValidationSupport validationSupport = new FhirTerminologyValidationSupport("https://wrong.terminology.server/fhir");
        setUpContext("./src/test/resources/constraints/terminology/fhir_codesystem.xml");

        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://hl7.org/fhir/observation-status"), "B");
        DvCodedText dvCodedText = new DvCodedText("Buccal", codePhrase);

        CArchetypeConstraint constraint = new CArchetypeConstraint(null, validationSupport);
        ExternalTerminologyValidationException ex = Assert.assertThrows(ExternalTerminologyValidationException.class, () -> constraint.validate("test", dvCodedText, archetypeconstraint));

        Assert.assertEquals("An error occurred while checking if FHIR terminology server supports the referenceSetUri: " +
                "terminology://fhir.hl7.org/CodeSystem?url=http://hl7.org/fhir/observation-status", ex.getMessage());
    }

    @Test
    public void testFailOnError_Disabled() throws IOException, XmlException {
        FhirTerminologyValidationSupport validationSupport = new FhirTerminologyValidationSupport("https://wrong.terminology.server/fhir", false);
        setUpContext("./src/test/resources/constraints/terminology/fhir_codesystem.xml");

        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://terminology.hl7.org/CodeSystem/FDI-surface"), "B");
        DvCodedText dvCodedText = new DvCodedText("Buccal", codePhrase);

        new CArchetypeConstraint(null, validationSupport).validate("test", dvCodedText, archetypeconstraint);

        Assert.assertTrue("No exception is thrown", true);
    }
}
