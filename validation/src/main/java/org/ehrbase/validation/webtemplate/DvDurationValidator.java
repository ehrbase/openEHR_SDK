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

import com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.ehrbase.validation.ConstraintViolation;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.threeten.extra.PeriodDuration;

/**
 * {@link ConstraintValidator} that validates a <code>DV_DURATION</code> object.
 *
 * @see com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration
 * @since 1.7
 */
@SuppressWarnings("unused")
public class DvDurationValidator implements ConstraintValidator<DvDuration> {

  private final PrimitiveConstraintValidator validator = new PrimitiveConstraintValidator();

  /**
   * {@inheritDoc}
   */
  @Override
  public Class<DvDuration> getAssociatedClass() {
    return DvDuration.class;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<ConstraintViolation> validate(DvDuration dvDuration, WebTemplateNode node) {
    if (!WebTemplateValidationUtils.hasInputs(node)) {
      return Collections.emptyList();
    }

    List<ConstraintViolation> result = new ArrayList<>();

    var duration = PeriodDuration.from(dvDuration.getValue());
    node.getInputs().forEach(input -> {
      var value = getValue(duration, input.getSuffix());
      result.addAll(validator.validate(node.getAqlPath(), value, input));
    });

    return result;
  }

  private Long getValue(PeriodDuration duration, String unit) {
    if (unit == null) {
      throw new IllegalArgumentException("Unit must not be null");
    }

    switch (unit) {
      case "year":
        return duration.get(ChronoUnit.YEARS);
      case "month":
        return duration.get(ChronoUnit.MONTHS);
      case "day":
        return duration.get(ChronoUnit.DAYS);
      case "week":
        return duration.get(ChronoUnit.DAYS) / 7;
      case "hour":
        return (long) duration.getDuration().toHoursPart();
      case "minute":
        return (long) duration.getDuration().toMinutesPart();
      case "second":
        return (long) duration.getDuration().toSecondsPart();
      default:
        throw new IllegalArgumentException("Unsupported unit: " + unit);
    }
  }
}
