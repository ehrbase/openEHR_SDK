/*
 *
 *  *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
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

package org.ehrbase.client.openehrclient;

import java.util.UUID;

public class VersionUid {
  private final UUID uuid;
  private final String system;
  private final long version;

  public VersionUid(String versionString) {
    String[] split = versionString.split("::");
    uuid = UUID.fromString(split[0]);
    if (split.length > 1) {
      system = split[1];
      version = Long.parseLong(split[2]);
    } else {
      system = "";
      version = 1L;
    }
  }

  public VersionUid(UUID uuid, String system, long version) {
    this.uuid = uuid;
    this.system = system;
    this.version = version;
  }

  public UUID getUuid() {
    return uuid;
  }

  public String getSystem() {
    return system;
  }

  public long getVersion() {
    return version;
  }

  @Override
  public String toString() {
    return uuid.toString() + "::" + system + "::" + version;
  }
}
