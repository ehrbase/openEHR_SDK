/*
 * Copyright (c) 2019 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.validation;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datastructures.ItemTree;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.ehr.EhrStatus;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.PartyRef;
import com.nedap.archie.xml.JAXBUtil;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.IOUtils;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.ArchieObjectMapperProvider;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataCanonicalXML;
import org.ehrbase.openehr.sdk.test_data.item_structure.ItemStruktureTestDataCanonicalJson;
import org.ehrbase.openehr.sdk.validation.terminology.TerminologyValidationVisitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TerminologyValidationVisitorTest {
    private TerminologyValidationVisitor validationVisitor;

    @BeforeEach
    void setup() {
        validationVisitor = new TerminologyValidationVisitor();
    }

    @Test
    void elementVisitorTest() {
        Composition composition = loadComposition("test_all_types.fixed.v1.xml");

        validationVisitor.validate(composition);
        // 25 ELEMENTs + 1 ITEM_SINGLE!
        assertThat(validationVisitor.getElementOccurrences()).isEqualTo(26);
    }

    @Test
    void elementVisitorTestNor() {
        Composition composition = loadComposition("IDCR-LabReportRAW1_with_normal_status.xml");

        assertDoesNotThrow(() -> validationVisitor.validate(composition));
    }

    @Test
    void elementVisitorTest2() {
        Composition composition = loadComposition("RIPPLE-ConformanceTest.xml");

        validationVisitor.validate(composition);
        // 4 elements are in the context/other_context structure
        assertThat(validationVisitor.getElementOccurrences()).isEqualTo(61);

        validationVisitor.validate(composition.getContext().getOtherContext());
        assertThat(validationVisitor.getElementOccurrences()).isEqualTo(65);
    }

    @Test
    void elementVisitorTest3() {
        Composition composition = loadComposition("RIPPLE-ConformanceTest_invalid_other_context_mm_type.xml");

        assertThatThrownBy(() -> validationVisitor.validate(composition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("supplied code string [video/mp3] is not found in codeset:media types");
    }

    @Test
    void ehrVisitorTest() throws IOException {
        String value = IOUtils.toString(ItemStruktureTestDataCanonicalJson.SIMPLE_EHR_OTHER_Details.getStream(), UTF_8);

        ObjectMapper objectMapper = ArchieObjectMapperProvider.getObjectMapper();

        ItemTree otherDetails = objectMapper.readValue(value, ItemTree.class);

        EhrStatus ehrStatus = new EhrStatus(
                "ehr_status", new DvText("ehr_status"), new PartySelf(new PartyRef()), true, true, otherDetails);

        validationVisitor.validate(ehrStatus);
        assertThat(validationVisitor.getElementOccurrences()).isEqualTo(3);
    }

    @Test
    void testValidateTestAllTypesWithInvalidParticipations() {
        CompositionTestDataCanonicalXML src = CompositionTestDataCanonicalXML.ALL_TYPES_INVALID_PARTICIPATIONS;
        Composition composition = loadComposition(src);

        assertThatThrownBy(() -> validationVisitor.validate(composition)).isInstanceOf(IllegalArgumentException.class);
    }

    private static Composition loadComposition(String fileName) {
        try (InputStream in =
                Thread.currentThread().getContextClassLoader().getResourceAsStream("composition/" + fileName)) {
            Unmarshaller unmarshaller = JAXBUtil.getArchieJAXBContext().createUnmarshaller();
            return (Composition) unmarshaller.unmarshal(in);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Composition loadComposition(CompositionTestDataCanonicalXML src) {
        try (InputStream in = src.getStream()) {
            Unmarshaller unmarshaller = JAXBUtil.getArchieJAXBContext().createUnmarshaller();
            return (Composition) unmarshaller.unmarshal(in);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
