/*
 *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  This file is part of Project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.client.openehrclient.defaultrestclient;

import com.nedap.archie.rm.ehr.EhrStatus;
import java.util.Optional;
import java.util.UUID;
import org.ehrbase.client.openehrclient.EhrEndpoint;
import org.ehrbase.client.openehrclient.VersionUid;

public class DefaultRestEhrEndpoint implements EhrEndpoint {
  public static final String EHR_PATH = "ehr/";
  public static final String EHR_STATUS_PATH = "/ehr_status";
  private final DefaultRestClient defaultRestClient;

  public DefaultRestEhrEndpoint(DefaultRestClient defaultRestClient) {
    this.defaultRestClient = defaultRestClient;
  }

  @Override
  public UUID createEhr() {
    return defaultRestClient
        .httpPost(defaultRestClient.getConfig().getBaseUri().resolve(EHR_PATH), null)
        .getUuid();
  }

  @Override
  public UUID createEhr(EhrStatus ehrStatus) {
    return defaultRestClient
        .httpPost(defaultRestClient.getConfig().getBaseUri().resolve(EHR_PATH), ehrStatus)
        .getUuid();
  }

  @Override
  public Optional<EhrStatus> getEhrStatus(UUID ehrId) {
    return defaultRestClient.httpGet(
        defaultRestClient
            .getConfig()
            .getBaseUri()
            .resolve(EHR_PATH + ehrId.toString() + EHR_STATUS_PATH),
        EhrStatus.class);
  }

  @Override
  public void updateEhrStatus(UUID ehrId, EhrStatus ehrStatus) {
    defaultRestClient.httpPut(
        defaultRestClient.getConfig().getBaseUri().resolve(EHR_PATH + ehrId + EHR_STATUS_PATH),
        ehrStatus,
        new VersionUid(ehrStatus.getUid().getValue()));
  }
}
