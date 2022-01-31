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

import com.nedap.archie.rm.datavalues.quantity.DvCount;
import java.util.Collections;
import java.util.List;
import org.ehrbase.validation.ConstraintViolation;
import org.ehrbase.webtemplate.model.WebTemplateNode;

/**
 * {@link ConstraintValidator} that validates a <code>DV_COUNT</code> object.
 *
 * @see com.nedap.archie.rm.datavalues.quantity.DvCount
 * @since 1.7
 */
@SuppressWarnings("unused")
public class DvCountValidator implements ConstraintValidator<DvCount> {

  private final PrimitiveConstraintValidator validator = new PrimitiveConstraintValidator();

  /**
   * {@inheritDoc}
   */
  @Override
  public Class<DvCount> getAssociatedClass() {
    return DvCount.class;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<ConstraintViolation> validate(DvCount dvCount, WebTemplateNode node) {
    if (!WebTemplateValidationUtils.hasInputs(node)) {
      return Collections.emptyList();
    }

    var input = WebTemplateValidationUtils.getInputWithType(node, "INTEGER");
    return validator.validate(node.getAqlPath(), dvCount.getMagnitude(), input);
  }
}
