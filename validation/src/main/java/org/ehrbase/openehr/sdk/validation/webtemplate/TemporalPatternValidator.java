/*
 * Copyright (c) 2026 vitasystems GmbH and Hannover Medical School.
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

import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Map;
import org.ehrbase.openehr.sdk.validation.ConstraintViolation;
import org.ehrbase.openehr.sdk.webtemplate.model.TemporalPattern;
import org.ehrbase.openehr.sdk.webtemplate.model.TemporalPattern.TemporalPatternField;
import org.ehrbase.openehr.sdk.webtemplate.model.TemporalPattern.TemporalPatternValidity;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateInput;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateValidation;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validates DV_DATE, DV_TIME and DV_DATE_TIME values against the {@link TemporalPattern} of their input.
 * <p>Values are checked by the fields they carry, so both compact (dashless) and extended forms are accepted:
 * a mandatory field must be present, a prohibited one must not, optional fields are not checked; a timezone suffix
 * requires an offset, Z a zero offset, ±hh a whole number of hours.</p>
 */
final class TemporalPatternValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemporalPatternValidator.class);

    private TemporalPatternValidator() {}

    // Validates the value against the pattern of the input (if any)
    // Returns the violation with the value and the pattern, or null when the value conforms or nothing is to check
    public static @Nullable ConstraintViolation validate(
            String aqlPath, TemporalAccessor value, WebTemplateInput input) {
        if (value == null || input == null || !WebTemplateValidationUtils.hasValidationPattern(input)) {
            return null;
        }
        WebTemplateValidation validation = input.getValidation();
        TemporalPattern temporalPattern = validation.getTemporalPattern();
        if (temporalPattern == null) {
            LOGGER.debug("Ignoring unsupported date/time pattern '{}' at {}", validation.getPattern(), aqlPath);
            return null;
        }
        if (conforms(temporalPattern, value)) {
            return null;
        }
        return new ConstraintViolation(
                aqlPath, "The value %s does not match the pattern %s".formatted(value, validation.getPattern()));
    }

    private static boolean conforms(TemporalPattern pattern, TemporalAccessor value) {
        for (Map.Entry<TemporalPatternField, TemporalPatternValidity> entry :
                pattern.validities().entrySet()) {
            ChronoField field = entry.getKey().chronoField();
            boolean present = value.isSupported(field);
            boolean valid =
                    switch (entry.getValue()) {
                        case MANDATORY -> present;
                        case PROHIBITED -> !present;
                        case MANDATORY_UTC -> present && value.getLong(field) == 0;
                        case MANDATORY_HOURS -> present && value.getLong(field) % 3600 == 0;
                        case OPTIONAL -> true;
                    };
            if (!valid) {
                return false;
            }
        }
        return true;
    }
}
