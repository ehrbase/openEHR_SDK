/*
 *
 * Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 * This file is part of Project EHRbase
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.ehrbase.aql.dto.containment;

public class ContainmentDto implements ContainmentExpresionDto {
  private int id;
  private String archetypeId;
  private ContainmentExpresionDto contains;

  public ContainmentDto() {}

  public int getId() {
    return this.id;
  }

  public String getArchetypeId() {
    return this.archetypeId;
  }

  public ContainmentExpresionDto getContains() {
    return this.contains;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setArchetypeId(String archetypeId) {
    this.archetypeId = archetypeId;
  }

  public void setContains(ContainmentExpresionDto contains) {
    this.contains = contains;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof ContainmentDto)) return false;
    final ContainmentDto other = (ContainmentDto) o;
    if (!other.canEqual((Object) this)) return false;
    if (this.getId() != other.getId()) return false;
    final Object this$archetypeId = this.getArchetypeId();
    final Object other$archetypeId = other.getArchetypeId();
    if (this$archetypeId == null
        ? other$archetypeId != null
        : !this$archetypeId.equals(other$archetypeId)) return false;
    final Object this$contains = this.getContains();
    final Object other$contains = other.getContains();
    if (this$contains == null ? other$contains != null : !this$contains.equals(other$contains))
      return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof ContainmentDto;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    result = result * PRIME + this.getId();
    final Object $archetypeId = this.getArchetypeId();
    result = result * PRIME + ($archetypeId == null ? 43 : $archetypeId.hashCode());
    final Object $contains = this.getContains();
    result = result * PRIME + ($contains == null ? 43 : $contains.hashCode());
    return result;
  }

  public String toString() {
    return "ContainmentDto(id="
        + this.getId()
        + ", archetypeId="
        + this.getArchetypeId()
        + ", contains="
        + this.getContains()
        + ")";
  }
}
