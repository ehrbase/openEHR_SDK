/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
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

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WebTemplateAnnotation implements Serializable {

  private String comment;

  private final Map<String, String> other = new HashMap<>();

  public WebTemplateAnnotation() {}

  public WebTemplateAnnotation(WebTemplateAnnotation other) {
    this.comment = other.comment;
    this.other.putAll(other.other);
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  @JsonAnySetter
  @JsonAnyGetter
  public Map<String, String> getOther() {
    return other;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WebTemplateAnnotation that = (WebTemplateAnnotation) o;
    return Objects.equals(comment, that.comment) && Objects.equals(other, that.other);
  }

  @Override
  public int hashCode() {
    return Objects.hash(comment, other);
  }

  @Override
  public String toString() {
    return "WebTemplateAnnotation{" + "comment='" + comment + '\'' + ", other=" + other + '}';
  }
}
