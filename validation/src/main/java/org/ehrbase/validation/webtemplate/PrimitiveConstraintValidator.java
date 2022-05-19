/*
 * Copyright 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehrbase.validation.webtemplate;

import com.nedap.archie.aom.CPrimitiveObject;
import com.nedap.archie.query.RMObjectWithPath;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rmobjectvalidator.validations.RMPrimitiveObjectValidation;
import java.util.List;
import java.util.stream.Collectors;
import org.ehrbase.validation.ConstraintViolation;
import org.ehrbase.webtemplate.model.WebTemplateInput;

/**
 * @since 1.7
 */
public class PrimitiveConstraintValidator {

  private final PrimitiveConstraintMapper constraintMapper = new PrimitiveConstraintMapper();

  public List<ConstraintViolation> validate(String aqlPath, Object value,
      CPrimitiveObject<?, ?> cobject) {
    var validationMessages = RMPrimitiveObjectValidation.validate(ArchieRMInfoLookup.getInstance(),
        List.of(new RMObjectWithPath(value, aqlPath)), aqlPath, cobject);
    return validationMessages.stream()
        .map(validationMessage -> new ConstraintViolation(validationMessage.getPath(),
            validationMessage.getMessage()))
        .collect(Collectors.toList());
  }

  public List<ConstraintViolation> validate(String aqlPath, Object value,
      WebTemplateInput input) {
    return validate(aqlPath, value, constraintMapper.mapInput(input));
  }
}
