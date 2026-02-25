/*
 * Copyright (c) 2021 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.conformance_test.extern;

import care.better.platform.web.template.validator.CompositionValidator;
import care.better.platform.web.template.validator.ValidationErrorDto;
import com.nedap.archie.rm.composition.Composition;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import org.ehrbase.openehr.sdk.serialisation.RMDataFormat;
import org.ehrbase.openehr.sdk.validation.LocatableValidator;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplate;
import org.ehrbase.openehr.sdk.webtemplate.parser.OPTParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompositionValidatorImp implements CompositionValidator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<ValidationErrorDto> validate(String template, String rawComposition) throws Exception {

        return validateWithParams(template, rawComposition, false, false);
    }

    @Override
    public List<ValidationErrorDto> validateWithParams(
            String template, String rawComposition, boolean strictTextValidation, boolean relaxedNameMatching)
            throws Exception {
        WebTemplate webTemplate = OPTParser.parse(IOUtils.toInputStream(template, StandardCharsets.UTF_8));
        Composition composition =
                RMDataFormat.canonicalJSON().unmarshal(rawComposition.replace("@class", "_type"), Composition.class);

        LocatableValidator validator = new LocatableValidator();

        return validator.validate(composition, webTemplate).stream()
                .filter(e -> !List.of(
                                "/content[openEHR-EHR-SECTION.ispek_dialog.v1 and name/value='Restraint medication']/items[openEHR-EHR-INSTRUCTION.medication_order.v1]/activities[at0001]/description[at0002]/items[at0070]",
                                "/context/other_context[at0001]/items[at0.0.81]")
                        .contains(e.getAqlPath()))
                .peek(e -> logger.info(e.getMessage() + "|" + e.getAqlPath()))
                .map(e -> new ValidationErrorDto(e.getMessage(), new String[0], 0))
                .collect(Collectors.toList());
    }
}
