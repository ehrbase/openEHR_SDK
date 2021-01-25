/*
 *
 *  *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.webtemplate.model;

import java.util.Objects;

public class WebTemplateInterval<T> {

  private T min;
  private WebTemplateComparisonSymbol minOp;
  private T max;
  private WebTemplateComparisonSymbol maxOp;

  public WebTemplateInterval() {}

  public WebTemplateInterval(WebTemplateInterval<T> other) {
    this.min = other.min;
    this.minOp = other.minOp;
    this.max = other.max;
    this.maxOp = other.maxOp;
  }

  public T getMin() {
    return min;
  }

  public void setMin(T min) {
    this.min = min;
  }

  public WebTemplateComparisonSymbol getMinOp() {
    return minOp;
  }

  public void setMinOp(WebTemplateComparisonSymbol minOp) {
    this.minOp = minOp;
  }

  public T getMax() {
    return max;
  }

  public void setMax(T max) {
    this.max = max;
  }

  public WebTemplateComparisonSymbol getMaxOp() {
    return maxOp;
  }

  public void setMaxOp(WebTemplateComparisonSymbol maxOp) {
    this.maxOp = maxOp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WebTemplateInterval<?> that = (WebTemplateInterval<?>) o;
    return Objects.equals(min, that.min)
        && minOp == that.minOp
        && Objects.equals(max, that.max)
        && maxOp == that.maxOp;
  }

  @Override
  public int hashCode() {
    return Objects.hash(min, minOp, max, maxOp);
  }

  @Override
  public String toString() {
    return "WebTemplateValidationInterval{"
        + "min="
        + min
        + ", minOp="
        + minOp
        + ", max="
        + max
        + ", maxOp="
        + maxOp
        + '}';
  }
}
