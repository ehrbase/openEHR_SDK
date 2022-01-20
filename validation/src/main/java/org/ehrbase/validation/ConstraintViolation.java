package org.ehrbase.validation;

import java.io.Serializable;
import java.util.Objects;

public class ConstraintViolation implements Serializable {

  private final String aqlPath;

  private final String message;

  public ConstraintViolation(String aqlPath, String message) {
    this.aqlPath = aqlPath;
    this.message = message;
  }

  public String getAqlPath() {
    return aqlPath;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConstraintViolation that = (ConstraintViolation) o;
    return Objects.equals(aqlPath, that.aqlPath) && Objects.equals(message,
        that.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(aqlPath, message);
  }

  @Override
  public String toString() {
    return "ConstraintViolation{" +
        "aqlPath='" + aqlPath + '\'' +
        ", message='" + message + '\'' +
        '}';
  }
}
