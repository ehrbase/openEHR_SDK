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

import java.io.Serializable;
import java.util.Objects;

/**
 * Describes a constraint violation based on template definition.
 *
 * @since 1.7
 */
public class ConstraintViolation implements Serializable {
  private static final long serialVersionUID = 4271592302120107558L;

  private final String aqlPath;

  private final String message;

  public ConstraintViolation(String aqlPath, String message) {
    this.aqlPath = aqlPath;
    this.message = message;
  }
  
  public ConstraintViolation(String message) {
    this("", message);
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
