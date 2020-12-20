/*
 * Copyright (c) 2019 Vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project EHRbase
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
 */
package org.ehrbase.response.openehr.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Admin API response data for successful PUT responses. Returns the number of updated elements as a
 * response field. TODO: Return updated resource data instead of number. Should be generic for all
 * use cases, i.e. EHR, directory, etc.
 */
@JacksonXmlRootElement
public class AdminUpdateResponseData {

  @JsonProperty(value = "updated")
  private int updated;

  public AdminUpdateResponseData(int updated) {
    this.updated = updated;
  }

  public void setUpdated(int updated) {
    this.updated = updated;
  }

  public int getUpdated() {
    return this.updated;
  }
}
