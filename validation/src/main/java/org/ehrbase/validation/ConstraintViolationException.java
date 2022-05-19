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

package org.ehrbase.validation;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Validation exception that contains a list of constraint violations.
 *
 * @since 1.7
 */
public class ConstraintViolationException extends ValidationException {
  private static final long serialVersionUID = -3467256436138419008L;
  private final List<ConstraintViolation> constraintViolations;

  public ConstraintViolationException(String message,
      List<ConstraintViolation> constraintViolations) {
    super(message);
    this.constraintViolations = constraintViolations;
  }

  public ConstraintViolationException(List<ConstraintViolation> constraintViolations) {
    this(constraintViolations != null ? toString(constraintViolations) : null,
        constraintViolations);
  }

  /**
   * Returns the constraint violations.
   *
   * @return the list of constraint violations
   */
  public List<ConstraintViolation> getConstraintViolations() {
    return constraintViolations;
  }

  private static String toString(List<ConstraintViolation> errors) {
    return errors.stream()
        .map(error -> error.getAqlPath() + ": " + error.getMessage())
        .collect(Collectors.joining(", "));
  }
}
