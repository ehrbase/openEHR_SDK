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
import com.nedap.archie.aom.primitives.CBoolean;
import com.nedap.archie.aom.primitives.CDate;
import com.nedap.archie.aom.primitives.CDateTime;
import com.nedap.archie.aom.primitives.CInteger;
import com.nedap.archie.aom.primitives.CReal;
import com.nedap.archie.aom.primitives.CString;
import com.nedap.archie.aom.primitives.CTime;
import com.nedap.archie.base.Interval;
import com.nedap.archie.datetime.DateTimeParsers;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.webtemplate.model.WebTemplateComparisonSymbol;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateInputValue;
import org.ehrbase.webtemplate.model.WebTemplateInterval;

/**
 * @since 1.7
 */
public class PrimitiveConstraintMapper {

  @SuppressWarnings("java:S1452")
  public CPrimitiveObject<?, ?> mapInput(WebTemplateInput input) {
    if (input == null) {
      return null;
    }

    switch (input.getType()) {
      case "BOOLEAN":
        return mapBooleanInput(input);
      case "TEXT":
        return mapTextInput(input);
      case "INTEGER":
        return mapIntegerInput(input);
      case "DECIMAL":
        return mapRealInput(input);
      case "DATE":
        return mapDateInput(input);
      case "TIME":
        return mapTimeInput(input);
      case "DATETIME":
        return mapDateTimeInput(input);
      default:
        throw new IllegalStateException("Unsupported input type: " + input.getType());
    }
  }

  /**
   * Map from {@link WebTemplateInput} to {@link CBoolean}.
   */
  public CBoolean mapBooleanInput(WebTemplateInput input) {
    var cBoolean = new CBoolean();

    input.getList().stream()
        .map(inputValue -> Boolean.valueOf(inputValue.getValue()))
        .forEach(cBoolean::addConstraint);

    return cBoolean;
  }

  /**
   * Map from {@link WebTemplateInput} to {@link CString}.
   */
  public CString mapTextInput(WebTemplateInput input) {
    var cString = new CString();

    if (WebTemplateValidationUtils.hasValidationPattern(input)) {
      cString.addConstraint(input.getValidation().getPattern());
    }

    if (WebTemplateValidationUtils.hasList(input)) {
      input.getList().stream()
          .map(WebTemplateInputValue::getValue)
          .forEach(cString::addConstraint);
    }

    return cString;
  }

  /**
   * Map from {@link WebTemplateInput} to {@link CInteger}.
   */
  public CInteger mapIntegerInput(WebTemplateInput input) {
    var cInteger = new CInteger();

    input.getList().stream()
        .map(inputValue -> new Interval<>(Long.valueOf(inputValue.getValue())))
        .forEach(cInteger::addConstraint);

    if (WebTemplateValidationUtils.hasValidationRange(input)) {
      var interval = mapIntervalOfInteger(input.getValidation().getRange());
      cInteger.addConstraint(interval);
    }

    return cInteger;
  }

  /**
   * Map from {@link WebTemplateInput} to {@link CReal}.
   */
  public CReal mapRealInput(WebTemplateInput input) {
    var cReal = new CReal();

    input.getList().stream()
        .map(inputValue -> new Interval<>(Double.valueOf(inputValue.getValue())))
        .forEach(cReal::addConstraint);

    if (WebTemplateValidationUtils.hasValidationRange(input)) {
      var interval = mapIntervalOfReal(input.getValidation().getRange());
      cReal.addConstraint(interval);
    }

    return cReal;
  }

  /**
   * Map from {@link WebTemplateInput} to {@link CDate}.
   */
  public CDate mapDateInput(WebTemplateInput input) {
    var cDate = new CDate();

    if (WebTemplateValidationUtils.hasValidationRange(input)) {
      var interval = mapIntervalOfDate(input.getValidation().getRange());
      cDate.addConstraint(interval);
    }

    if (WebTemplateValidationUtils.hasValidationPattern(input)) {

      cDate.setPatternConstraint (input.getValidation().getPattern());
    }

    return cDate;
  }

  /**
   * Map from {@link WebTemplateInput} to {@link CDateTime}.
   */
  public CDateTime mapDateTimeInput(WebTemplateInput input) {
    var cDateTime = new CDateTime();

    if (WebTemplateValidationUtils.hasValidationRange(input)) {
      var interval = mapIntervalOfDateTime(input.getValidation().getRange());
      cDateTime.addConstraint(interval);
    }

    if (WebTemplateValidationUtils.hasValidationPattern(input)) {
      cDateTime.setPatternConstraint(input.getValidation().getPattern());
    }

    return cDateTime;
  }

  /**
   * Map from {@link WebTemplateInput} to {@link CTime}.
   */
  public CTime mapTimeInput(WebTemplateInput input) {
    var cTime = new CTime();

    if (WebTemplateValidationUtils.hasValidationRange(input)) {
      var interval = mapIntervalOfTime(input.getValidation().getRange());
      cTime.addConstraint(interval);
    }

    if (WebTemplateValidationUtils.hasValidationPattern(input)) {
      cTime.setPatternConstraint(input.getValidation().getPattern());
    }

    return cTime;
  }

  public CInteger mapIntegerInterval(WebTemplateInterval<?> interval) {
    var cInteger = new CInteger();
    cInteger.addConstraint(mapIntervalOfInteger(interval));
    return cInteger;
  }

  public CReal mapRealInterval(WebTemplateInterval<?> interval) {
    var cReal = new CReal();
    cReal.addConstraint(mapIntervalOfReal(interval));
    return cReal;
  }

  private Interval<Long> mapIntervalOfInteger(WebTemplateInterval<?> interval) {
    Long lower = null;
    if (interval.getMin() instanceof Number) {
      lower = ((Number) interval.getMin()).longValue();
    }
    Long upper = null;
    if (interval.getMax() instanceof Number) {
      upper = ((Number) interval.getMax()).longValue();
    }
    return new Interval<>(lower, upper, isLowerIncluded(interval), isUpperIncluded(interval));
  }

  private Interval<Double> mapIntervalOfReal(WebTemplateInterval<?> interval) {
    Double lower = null;
    if (interval.getMin() instanceof Number) {
      lower = ((Number) interval.getMin()).doubleValue();
    }
    Double upper = null;
    if (interval.getMax() instanceof Number) {
      upper = ((Number) interval.getMax()).doubleValue();
    }
    return new Interval<>(lower, upper, isLowerIncluded(interval), isUpperIncluded(interval));
  }

  private Interval<Temporal> mapIntervalOfDate(WebTemplateInterval<?> interval) {
    Temporal lower = null;
    if (interval.getMin() instanceof String) {
      lower = DateTimeParsers.parseDateValue((String) interval.getMin());
    }
    Temporal upper = null;
    if (interval.getMax() instanceof String) {
      upper = DateTimeParsers.parseDateValue((String) interval.getMax());
    }
    return new Interval<>(lower, upper, isLowerIncluded(interval), isUpperIncluded(interval));
  }

  private Interval<TemporalAccessor> mapIntervalOfDateTime(WebTemplateInterval<?> interval) {
    TemporalAccessor lower = null;
    if (interval.getMin() instanceof String) {
      lower = DateTimeParsers.parseDateTimeValue((String) interval.getMin());
    }
    TemporalAccessor upper = null;
    if (interval.getMin() instanceof String) {
      upper = DateTimeParsers.parseDateTimeValue((String) interval.getMax());
    }
    return new Interval<>(lower, upper, isLowerIncluded(interval), isUpperIncluded(interval));
  }

  private Interval<TemporalAccessor> mapIntervalOfTime(WebTemplateInterval<?> interval) {
    TemporalAccessor lower = null;
    if (interval.getMin() instanceof String) {
      lower = DateTimeParsers.parseTimeValue((String) interval.getMin());
    }
    TemporalAccessor upper = null;
    if (interval.getMin() instanceof String) {
      upper = DateTimeParsers.parseTimeValue((String) interval.getMax());
    }
    return new Interval<>(lower, upper, isLowerIncluded(interval), isUpperIncluded(interval));
  }

  private boolean isLowerIncluded(WebTemplateInterval<?> interval) {
    return interval.getMinOp() == null || interval.getMinOp() == WebTemplateComparisonSymbol.GT_EQ;
  }

  private boolean isUpperIncluded(WebTemplateInterval<?> interval) {
    return interval.getMaxOp() == null || interval.getMaxOp() == WebTemplateComparisonSymbol.LT_EQ;
  }
}
