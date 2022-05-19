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

import static org.assertj.core.api.Assertions.assertThat;

import com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration;
import org.assertj.core.groups.Tuple;
import org.ehrbase.serialisation.walker.DurationHelper;
import org.ehrbase.validation.ConstraintViolation;
import org.ehrbase.webtemplate.model.WebTemplateComparisonSymbol;
import org.junit.jupiter.api.Test;

class DvDurationValidatorTest extends AbstractRMObjectValidatorTest {

  private final DvDurationValidator validator = new DvDurationValidator();

  @Test
  void testValidate_Duration() throws Exception {
    var node = parseNode("/webtemplate_nodes/dv_duration_duration.json");

    // min
    assertThat(DurationHelper.getTotalComparisonSymbol(node, DurationHelper.MIN_MAX.MIN))
        .isPresent()
        .get()
        .isEqualTo(WebTemplateComparisonSymbol.GT_EQ);
    assertThat(DurationHelper.buildTotalRange(node, DurationHelper.MIN_MAX.MIN))
        .isPresent()
        .get()
        .hasToString("PT0S");
    // max
    assertThat(DurationHelper.getTotalComparisonSymbol(node, DurationHelper.MIN_MAX.MAX))
        .isPresent()
        .get()
        .isEqualTo(WebTemplateComparisonSymbol.LT_EQ);
    assertThat(DurationHelper.buildTotalRange(node, DurationHelper.MIN_MAX.MAX))
        .isPresent()
        .get()
        .hasToString("PT12H30M15S");

    // in Validation Range
    var result = validator.validate(new DvDuration("PT10H20M10S"), node);
    assertThat(result)
        .extracting(ConstraintViolation::getAqlPath, ConstraintViolation::getMessage)
        .containsExactlyInAnyOrder();
    // bigger then Validation Range
    result = validator.validate(new DvDuration("PT20H45M45S"), node);
    assertThat(result)
        .extracting(ConstraintViolation::getAqlPath, ConstraintViolation::getMessage)
        .containsExactlyInAnyOrder(
            new Tuple(
                "/test/dv_duration_duration", "The value PT20H45M45S must be <= PT12H30M15S"));

    // less than Validation Range
    result = validator.validate(new DvDuration("PT-20H45M45S"), node);
    assertThat(result)
        .extracting(ConstraintViolation::getAqlPath, ConstraintViolation::getMessage)
        .containsExactlyInAnyOrder(
            new Tuple("/test/dv_duration_duration", "The value PT-19H-14M-15S must be >= PT0S"));
  }

  @Test
  void testValidate_Period() throws Exception {
    var node = parseNode("/webtemplate_nodes/dv_duration_period.json");

    // min
    assertThat(DurationHelper.getTotalComparisonSymbol(node, DurationHelper.MIN_MAX.MIN))
        .isPresent()
        .get()
        .isEqualTo(WebTemplateComparisonSymbol.GT);
    assertThat(DurationHelper.buildTotalRange(node, DurationHelper.MIN_MAX.MIN))
        .isPresent()
        .get()
        .hasToString("P1M");
    // max
    assertThat(DurationHelper.getTotalComparisonSymbol(node, DurationHelper.MIN_MAX.MAX)).isEmpty();
    assertThat(DurationHelper.buildTotalRange(node, DurationHelper.MIN_MAX.MAX)).isEmpty();

    // in Validation Range
    var result = validator.validate(new DvDuration("P10Y5M3D"), node);
    assertThat(result)
        .extracting(ConstraintViolation::getAqlPath, ConstraintViolation::getMessage)
        .containsExactlyInAnyOrder();

    result = validator.validate(new DvDuration("P5M3D"), node);
    assertThat(result)
        .extracting(ConstraintViolation::getAqlPath, ConstraintViolation::getMessage)
        .containsExactlyInAnyOrder();

    result = validator.validate(new DvDuration("P1Y3D"), node);
    assertThat(result)
        .extracting(ConstraintViolation::getAqlPath, ConstraintViolation::getMessage)
        .containsExactlyInAnyOrder();

    // less than Validation Range
    result = validator.validate(new DvDuration("PT2H"), node);
    assertThat(result)
        .extracting(ConstraintViolation::getAqlPath, ConstraintViolation::getMessage)
        .containsExactlyInAnyOrder(
            new Tuple("/test/dv_duration_period", "The value PT2H must be > P1M"));
  }

  @Test
  void testValidate_PeriodDuration() throws Exception {
    var node = parseNode("/webtemplate_nodes/dv_duration_period_duration.json");


    // min
    assertThat(DurationHelper.getTotalComparisonSymbol(node, DurationHelper.MIN_MAX.MIN))
        .isPresent()
        .get()
        .isEqualTo(WebTemplateComparisonSymbol.GT_EQ);
    assertThat(DurationHelper.buildTotalRange(node, DurationHelper.MIN_MAX.MIN))
        .isPresent()
        .get()
        .hasToString("P5Y4M15D");
    // max
    assertThat(DurationHelper.getTotalComparisonSymbol(node, DurationHelper.MIN_MAX.MAX))
        .isPresent()
        .get()
        .isEqualTo(WebTemplateComparisonSymbol.LT);
    assertThat(DurationHelper.buildTotalRange(node, DurationHelper.MIN_MAX.MAX))
        .isPresent()
        .get()
        .hasToString("P20YT10H30M15S");

    // in Validation Range
    var result = validator.validate(new DvDuration("P6Y5M2W3DT5H30M5S"), node);
    assertThat(result)
        .extracting(ConstraintViolation::getAqlPath, ConstraintViolation::getMessage)
        .containsExactlyInAnyOrder(
           );

    // greater than Validation Range
     result = validator.validate(new DvDuration("P33Y5M2W3DT5H30M5S"), node);
    assertThat(result)
        .extracting(ConstraintViolation::getAqlPath, ConstraintViolation::getMessage)
        .containsExactlyInAnyOrder(
            new Tuple(
                "/test/dv_duration_duration",
                "The value P33Y5M17DT5H30M5S must be < P20YT10H30M15S"));
    // less than Validation Range
    result = validator.validate(new DvDuration("PT10H30M15S"), node);
    assertThat(result)
        .extracting(ConstraintViolation::getAqlPath, ConstraintViolation::getMessage)
        .containsExactlyInAnyOrder(
            new Tuple(
                "/test/dv_duration_duration",
                "The value PT10H30M15S must be >= P5Y4M15D"));
  }
}
