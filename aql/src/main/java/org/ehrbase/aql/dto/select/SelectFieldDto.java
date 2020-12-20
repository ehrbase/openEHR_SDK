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

package org.ehrbase.aql.dto.select;

public class SelectFieldDto implements SelectStatementDto {

  private String name;
  private String aqlPath;
  private int containmentId;

  public SelectFieldDto() {}

  public String getName() {
    return this.name;
  }

  public String getAqlPath() {
    return this.aqlPath;
  }

  public int getContainmentId() {
    return this.containmentId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAqlPath(String aqlPath) {
    this.aqlPath = aqlPath;
  }

  public void setContainmentId(int containmentId) {
    this.containmentId = containmentId;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof SelectFieldDto)) return false;
    final SelectFieldDto other = (SelectFieldDto) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$name = this.getName();
    final Object other$name = other.getName();
    if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
    final Object this$aqlPath = this.getAqlPath();
    final Object other$aqlPath = other.getAqlPath();
    if (this$aqlPath == null ? other$aqlPath != null : !this$aqlPath.equals(other$aqlPath))
      return false;
    if (this.getContainmentId() != other.getContainmentId()) return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof SelectFieldDto;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $name = this.getName();
    result = result * PRIME + ($name == null ? 43 : $name.hashCode());
    final Object $aqlPath = this.getAqlPath();
    result = result * PRIME + ($aqlPath == null ? 43 : $aqlPath.hashCode());
    result = result * PRIME + this.getContainmentId();
    return result;
  }

  public String toString() {
    return "SelectFieldDto(name="
        + this.getName()
        + ", aqlPath="
        + this.getAqlPath()
        + ", containmentId="
        + this.getContainmentId()
        + ")";
  }
}
