/*
 *
 *  *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.webtemplate;

import care.better.platform.web.template.validator.CompositionValidator;
import care.better.platform.web.template.validator.ValidationErrorDto;
import com.nedap.archie.rm.composition.Composition;
import org.apache.commons.io.IOUtils;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.validation.Validator;
import org.ehrbase.webtemplate.model.WebTemplateValidation;
import org.openehr.schemas.v1.TemplateDocument;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

public class CompositionValidatorImp implements CompositionValidator {
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
    try {
      new Validator(templateDocument.getTemplate()).check(new CanonicalJson().unmarshal(rawComposition.replace("@class","_type"), Composition.class));
    } catch (RuntimeException e) {
      return Collections.singletonList(new ValidationErrorDto(e.getMessage(), new String[0], 0));
    }
    return Collections.emptyList();
  }
}
