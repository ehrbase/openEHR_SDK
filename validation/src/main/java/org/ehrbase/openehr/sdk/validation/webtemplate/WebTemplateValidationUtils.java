/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.validation.webtemplate;

import com.nedap.archie.base.MultiplicityInterval;
import java.util.Objects;
import java.util.Optional;
import org.apache.commons.lang3.BooleanUtils;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateInput;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateInputValue;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;

/**
 * Convenience methods for working with Web Templates.
 *
 * @see org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateInput
 * @see org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode
 * @since 1.7
 */
public class WebTemplateValidationUtils {

    private WebTemplateValidationUtils() {}

    /**
     * Return whether the node has validation inputs.
     *
     * @param node the node to check
     * @return <code>true</code> if the node has inputs; <code>false</code>
     * otherwise
     */
    public static boolean hasInputs(WebTemplateNode node) {
        return node != null && !node.getInputs().isEmpty();
    }

    /**
     * Return the first input with the specified type.
     *
     * @param node the node to check
     * @param type the input suffix
     * @return the input element
     */
    public static Optional<WebTemplateInput> findInputWithType(WebTemplateNode node, String type) {
        return node.getInputs().stream()
                .filter(input -> Objects.equals(input.getType(), type))
                .findFirst();
    }

    /**
     * Return the first input with the specified type.
     *
     * @param node the node to check
     * @param type the input type
     * @return the input element
     * @throws java.util.NoSuchElementException if the node does not contain input with given type
     */
    public static WebTemplateInput getInputWithType(WebTemplateNode node, String type) {
        return findInputWithType(node, type).orElseThrow();
    }

    /**
     * Return whether the node has an input with the given type.
     *
     * @param node the node to check
     * @param type the input type
     * @return <code>true</code> if the node has an input; <code>false</code>
     * otherwise
     */
    public static boolean hasInputWithType(WebTemplateNode node, String type) {
        return findInputWithType(node, type).isPresent();
    }

    /**
     * Return the first input with the specified suffix.
     *
     * @param node   the node to check
     * @param suffix the input suffix
     * @return the input element
     */
    public static Optional<WebTemplateInput> findInputWithSuffix(WebTemplateNode node, String suffix) {
        return node.getInputs().stream()
                .filter(input -> Objects.equals(input.getSuffix(), suffix))
                .findFirst();
    }

    /**
     * Return the first input with the specified suffix.
     *
     * @param node   the node to check
     * @param suffix the input suffix
     * @return the input element
     * @throws java.util.NoSuchElementException if the node does not contain input with given suffix
     */
    public static WebTemplateInput getInputWithSuffix(WebTemplateNode node, String suffix) {
        return findInputWithSuffix(node, suffix).orElseThrow();
    }

    /**
     * Return the first input with the specified suffix.
     *
     * @param input the input to check
     * @param value the value
     * @return the input value element
     */
    public static Optional<WebTemplateInputValue> findInputValue(WebTemplateInput input, String value) {
        return input.getList().stream()
                .filter(inputValue -> Objects.equals(inputValue.getValue(), value))
                .findFirst();
    }

    /**
     * Return whether the provided input has a validation pattern.
     *
     * @param input the input to check
     * @return <code>true</code> if the provided input has a pattern defined; <code>false</code>
     * otherwise
     */
    public static boolean hasValidationPattern(WebTemplateInput input) {
        return input.getValidation() != null && input.getValidation().getPattern() != null;
    }

    /**
     * Return whether the provided input has a validation range.
     *
     * @param input the input to check
     * @return <code>true</code> if the provided input has a range; <code>false</code>
     * otherwise
     */
    public static boolean hasValidationRange(WebTemplateInput input) {
        return input.getValidation() != null && input.getValidation().getRange() != null;
    }

    /**
     * Return whether the provided input has a list that contains all available options.
     *
     * @param input the input to check
     * @return <code>true</code> if the provided input has a list; <code>false</code>
     * otherwise
     */
    public static boolean hasList(WebTemplateInput input) {
        return !input.getList().isEmpty() && BooleanUtils.isNotTrue(input.getListOpen());
    }

    /**
     * Return whether the provided input value has a validation range.
     *
     * @param inputValue the input to check
     * @return <code>true</code> if the provided input has a range; <code>false</code>
     * otherwise
     */
    public static boolean hasValidationRange(WebTemplateInputValue inputValue) {
        return inputValue.getValidation() != null && inputValue.getValidation().getRange() != null;
    }

    /**
     * Return whether the provided input value has a validation precision.
     *
     * @param inputValue the input to check
     * @return <code>true</code> if the provided input has a precision; <code>false</code>
     * otherwise
     */
    public static boolean hasValidationPrecision(WebTemplateInputValue inputValue) {
        return inputValue.getValidation() != null && inputValue.getValidation().getPrecision() != null;
    }

    public static MultiplicityInterval getMultiplicityInterval(WebTemplateNode node) {
        var interval = new MultiplicityInterval();
        if (node.getMin() != -1) {
            interval.setLower(node.getMin());
        } else {
            interval.setLowerUnbounded(true);
        }
        if (node.getMax() != -1) {
            interval.setUpper(node.getMax());
        } else {
            interval.setUpperUnbounded(true);
        }
        return interval;
    }
}
