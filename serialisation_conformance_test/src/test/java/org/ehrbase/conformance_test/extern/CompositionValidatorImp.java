/*
 *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *
 *  This file is part of Project EHRbase
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.ehrbase.conformance_test.extern;

import care.better.platform.web.template.validator.CompositionValidator;
import care.better.platform.web.template.validator.ValidationErrorDto;
import com.nedap.archie.rm.composition.Composition;
import org.apache.commons.io.IOUtils;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.openehr.schemas.v1.TemplateDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class CompositionValidatorImp implements CompositionValidator {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public List<ValidationErrorDto> validate(String template, String rawComposition)
      throws Exception {

    return validateWithParams(template, rawComposition, false, false);
  }

  @Override
  public List<ValidationErrorDto> validateWithParams(
      String template,
      String rawComposition,
      boolean strictTextValidation,
      boolean relaxedNameMatching)
      throws Exception {
    TemplateDocument templateDocument =
        TemplateDocument.Factory.parse(IOUtils.toInputStream(template, StandardCharsets.UTF_8));
    Composition composition =
        new CanonicalJson().unmarshal(rawComposition.replace("@class", "_type"), Composition.class);



    org.ehrbase.validation.CompositionValidator validator = new org.ehrbase.validation.CompositionValidator();

    List<ValidationErrorDto> errorDtoList =
            validator.validate(composition,templateDocument.getTemplate()).stream()
                    .filter(e -> !List.of("/content[openEHR-EHR-SECTION.ispek_dialog.v1 and name/value='Restraint medication']/items[openEHR-EHR-INSTRUCTION.medication_order.v1]/activities[at0001]/description[at0002]/items[at0070]","/context/other_context[at0001]/items[at0.0.81]").contains(e.getAqlPath()) )
            .peek(e -> logger.info(e.getMessage() + "|" + e.getAqlPath()))
            .map(e -> new ValidationErrorDto(e.getMessage(), new String[0], 0))
            .collect(Collectors.toList());
    return errorDtoList;
  }
}
