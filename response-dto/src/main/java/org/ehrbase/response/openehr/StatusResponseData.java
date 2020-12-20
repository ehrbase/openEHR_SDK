/*
 * Copyright (c) 2019 Axel Siebert (Vitasystems GmbH) and Hannover Medical School.
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
package org.ehrbase.response.openehr;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Response data definition for EHRbase status response. Contains version information about runtime
 * of EHRbase.
 */
@JacksonXmlRootElement(localName = "status")
public class StatusResponseData {
  @JsonProperty(value = "ehrbase_version")
  private String ehrbaseVersion;

  @JsonProperty(value = "openehr_sdk_version")
  private String openEhrSdkVersion;

  @JsonProperty(value = "archie_version")
  private String archieVersion;

  @JsonProperty(value = "jvm_version")
  private String jvmVersion;

  @JsonProperty(value = "os_version")
  private String osVersion;

  @JsonProperty(value = "postgres_version")
  private String postgresVersion;

  public String getEhrbaseVersion() {
    return ehrbaseVersion;
  }

  public void setEhrbaseVersion(String ehrbaseVersion) {
    this.ehrbaseVersion = ehrbaseVersion;
  }

  public String getOpenEhrSdkVersion() {
    return openEhrSdkVersion;
  }

  public void setOpenEhrSdkVersion(String openEhrSdkVersion) {
    this.openEhrSdkVersion = openEhrSdkVersion;
  }

  public String getArchieVersion() {
    return archieVersion;
  }

  public void setArchieVersion(String archieVersion) {
    this.archieVersion = archieVersion;
  }

  public String getJvmVersion() {
    return jvmVersion;
  }

  public void setJvmVersion(String jvmVersion) {
    this.jvmVersion = jvmVersion;
  }

  public String getOsVersion() {
    return osVersion;
  }

  public void setOsVersion(String osVersion) {
    this.osVersion = osVersion;
  }

  public String getPostgresVersion() {
    return postgresVersion;
  }

  public void setPostgresVersion(String postgresVersion) {
    this.postgresVersion = postgresVersion;
  }
}
