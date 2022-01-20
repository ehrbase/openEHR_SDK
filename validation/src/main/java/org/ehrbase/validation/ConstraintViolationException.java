package org.ehrbase.validation;

import java.util.List;
import java.util.stream.Collectors;

public class ConstraintViolationException extends ValidationException {

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

  public List<ConstraintViolation> getConstraintViolations() {
    return constraintViolations;
  }

  private static String toString(List<ConstraintViolation> errors) {
    return errors.stream()
        .map(error -> error.getAqlPath() + ": " + error.getMessage())
        .collect(Collectors.joining(", "));
  }
}
