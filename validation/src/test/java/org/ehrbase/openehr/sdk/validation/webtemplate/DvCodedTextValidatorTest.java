/*
 * Copyright (c) 2024 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.validation.webtemplate;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.support.identification.TerminologyId;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.List;
import org.ehrbase.openehr.sdk.util.functional.Try;
import org.ehrbase.openehr.sdk.validation.ConstraintViolation;
import org.ehrbase.openehr.sdk.validation.ConstraintViolationException;
import org.ehrbase.openehr.sdk.validation.terminology.ExternalTerminologyValidation;
import org.ehrbase.openehr.sdk.validation.terminology.TerminologyParam;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DvCodedTextValidatorTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static WebTemplateNode parseNode(String resourceName) {
        try (InputStream in =
                DvCodedTextValidatorTest.class.getResourceAsStream("/webtemplate_nodes/" + resourceName)) {
            return objectMapper.readValue(in, WebTemplateNode.class);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private ExternalTerminologyValidation externalTerminologyValidationMock;

    @BeforeEach
    void setUp() {
        externalTerminologyValidationMock = Mockito.mock(ExternalTerminologyValidation.class);
    }

    @Test
    void testValidate() {
        var validator = new DvCodedTextValidator();

        var node = parseNode("dv_codedtext.json");
        var result = validator.validate(dvCodedText("First", "at0028", "local"), node);
        assertThat(result).isEmpty();

        result = validator.validate(dvCodedText("Test", "at0028", "local"), node);
        assertThat(result).hasSize(1);
        result = validator.validate(dvCodedText("First", "at0029", "local"), node);
        assertThat(result).hasSize(1);
    }

    private static @NonNull DvCodedText dvCodedText(String value, String codeString, String termId) {
        return new DvCodedText(value, new CodePhrase(new TerminologyId(termId), codeString));
    }

    @Test
    void testValidate_UnsupportedExternalTerminology() {
        var node = parseNode("dv_codedtext_unsupported.json");
        var dvCodedText =
                dvCodedText("Iodine-deficiency related thyroid disorders and allied conditions", "E01", "ICD10");

        var result = new DvCodedTextValidator(externalTerminologyValidationMock).validate(dvCodedText, node);
        assertThat(result).isEmpty();
    }

    @Test
    void testValidate_FhirCodeSystem() {
        var codePhrase = new CodePhrase(new TerminologyId("http://hl7.org/fhir/observation-status"), "final");

        // Mockito initialization
        Mockito.when(externalTerminologyValidationMock.supports(TerminologyParam.ofFhir(
                        "//fhir.hl7.org/CodeSystem?url=http://hl7.org/fhir/observation-status")))
                .thenReturn(true);

        TerminologyParam tp =
                TerminologyParam.ofFhir("//fhir.hl7.org/CodeSystem?url=http://hl7.org/fhir/observation-status");
        tp.setCodePhrase(codePhrase);

        Mockito.when(externalTerminologyValidationMock.validate(tp)).thenReturn(Try.success(Boolean.TRUE));

        //    Mockito.doNothing()
        //        .when(fhirTerminologyValidationMock)
        //        .validate(tp);

        var validator = new DvCodedTextValidator(externalTerminologyValidationMock);
        var node = parseNode("dv_codedtext_fhir_codesystem.json");

        var result = validator.validate(new DvCodedText("Final", codePhrase), node);
        assertThat(result).isEmpty();
    }

    @Test
    void testValidate_FhirCodeSystem_WrongTerminologyId() {
        var codePhrase = new CodePhrase(new TerminologyId("http://hl7.org/fhir/name-use"), "usual");

        TerminologyParam tp =
                TerminologyParam.ofFhir("//fhir.hl7.org/CodeSystem?url=http://hl7.org/fhir/observation-status");
        tp.setCodePhrase(codePhrase);

        // Mockito initialization
        Mockito.when(externalTerminologyValidationMock.supports(tp)).thenReturn(true);

        Mockito.when(externalTerminologyValidationMock.validate(tp))
                .thenReturn(
                        Try.failure(
                                new ConstraintViolationException(
                                        List.of(
                                                new ConstraintViolation(
                                                        "/test/dv_coded_text_fhir_value_set",
                                                        "The terminology http://hl7.org/fhir/name-use must be http://hl7.org/fhir/observation-status")))));

        var validator = new DvCodedTextValidator(externalTerminologyValidationMock);
        var node = parseNode("dv_codedtext_fhir_codesystem.json");

        var result = validator.validate(new DvCodedText("Usual", codePhrase), node);
        assertThat(result).isNotEmpty();
    }

    @Test
    void testValidate_FhirCodeSystem_WrongCode() {
        var codePhrase = new CodePhrase(new TerminologyId("http://hl7.org/fhir/observation-status"), "casual");

        TerminologyParam tp =
                TerminologyParam.ofFhir("//fhir.hl7.org/CodeSystem?url=http://hl7.org/fhir/observation-status");
        tp.setCodePhrase(codePhrase);

        // Mockito initialization
        Mockito.when(externalTerminologyValidationMock.supports(tp)).thenReturn(true);

        Mockito.when(externalTerminologyValidationMock.validate(tp))
                .thenReturn(
                        Try.failure(
                                new ConstraintViolationException(
                                        List.of(
                                                new ConstraintViolation(
                                                        "/test/dv_coded_text_fhir_code_system",
                                                        "The specified code 'casual' is not known to belong to the specified code system 'http://hl7.org/fhir/observation-status'")))));

        //    Mockito.doThrow(
        //            new ConstraintViolationException(List.of(
        //                new ConstraintViolation("/test/dv_coded_text_fhir_code_system",
        //                    "The specified code 'casual' is not known to belong to the specified code system
        // 'http://hl7.org/fhir/observation-status'"))))
        //        .when(fhirTerminologyValidationMock)
        //        .validate(tp);

        var validator = new DvCodedTextValidator(externalTerminologyValidationMock);
        var node = parseNode("dv_codedtext_fhir_codesystem.json");

        var result = validator.validate(new DvCodedText("Casual", codePhrase), node);
        assertThat(result).isNotEmpty();
    }

    @Test
    void testValidate_FhirValueSet() {
        var codePhrase =
                new CodePhrase(new TerminologyId("http://terminology.hl7.org/CodeSystem/v3-EntityNameUseR2"), "UKN");

        TerminologyParam tp = TerminologyParam.ofFhir(
                "//fhir.hl7.org/ValueSet/$expand?url=http://terminology.hl7.org/ValueSet/v3-EntityNameUseR2");
        tp.setCodePhrase(codePhrase);

        // Mockito initialization
        Mockito.when(externalTerminologyValidationMock.supports(tp)).thenReturn(true);

        Mockito.when(externalTerminologyValidationMock.validate(tp)).thenReturn(Try.success(true));

        var validator = new DvCodedTextValidator(externalTerminologyValidationMock);
        var node = parseNode("dv_codedtext_fhir_valueset.json");

        var result = validator.validate(new DvCodedText("Anonymous", codePhrase), node);
        assertThat(result).isEmpty();
    }

    @Test
    void testValidate_FhirValueSet_WrongTerminologyId() {
        var codePhrase = new CodePhrase(new TerminologyId("http://snomed.info/sct"), "ANON");

        TerminologyParam tp = TerminologyParam.ofFhir(
                "//fhir.hl7.org/ValueSet/$expand?url=http://terminology.hl7.org/ValueSet/v3-EntityNameUseR2");
        tp.setCodePhrase(codePhrase);

        // Mockito initialization
        Mockito.when(externalTerminologyValidationMock.supports(tp)).thenReturn(true);

        Mockito.when(externalTerminologyValidationMock.validate(tp))
                .thenReturn(
                        Try.failure(
                                new ConstraintViolationException(
                                        List.of(
                                                new ConstraintViolation(
                                                        "/test/dv_coded_text_fhir_value_set",
                                                        "The terminology http://snomed.info/sct must be http://terminology.hl7.org/CodeSystem/v3-EntityNameUseR2")))));

        var validator = new DvCodedTextValidator(externalTerminologyValidationMock);
        var node = parseNode("dv_codedtext_fhir_valueset.json");

        var result = validator.validate(new DvCodedText("Anonymous", codePhrase), node);
        assertThat(result).isNotEmpty();
    }

    @Test
    void testValidate_FhirValueSet_WrongCode() {
        var codePhrase =
                new CodePhrase(new TerminologyId("http://terminology.hl7.org/CodeSystem/v3-EntityNameUseR2"), "UKN");

        TerminologyParam tp = TerminologyParam.ofFhir(
                "//fhir.hl7.org/ValueSet/$expand?url=http://terminology.hl7.org/ValueSet/v3-EntityNameUseR2");
        tp.setCodePhrase(codePhrase);

        // Mockito initialization
        Mockito.when(externalTerminologyValidationMock.supports(tp)).thenReturn(true);

        Mockito.when(externalTerminologyValidationMock.validate(tp))
                .thenReturn(
                        Try.failure(
                                new ConstraintViolationException(
                                        List.of(
                                                new ConstraintViolation(
                                                        "/test/dv_coded_text_fhir_value_set",
                                                        "The value UKN does not match any option from value set http://terminology.hl7.org/ValueSet/v3-EntityNameUseR2")))));

        var validator = new DvCodedTextValidator(externalTerminologyValidationMock);
        var node = parseNode("dv_codedtext_fhir_valueset.json");

        var result = validator.validate(new DvCodedText("Anonymous", codePhrase), node);
        assertThat(result).isNotEmpty();
    }
}
