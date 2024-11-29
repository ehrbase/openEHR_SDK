/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient;

import java.net.URI;
import java.util.UUID;
import org.apache.http.HttpResponse;
import org.ehrbase.openehr.sdk.client.openehrclient.AdminEhrEndpoint;

/**
 * Requires that the Admin endpoint is active.
 *
 * Parameters in .yml configuration matching the runtime context as:
 *
 * admin-api:
 *   active: true
 *   allowDeleteAll: true
 */
public class DefaultRestAdminEhrEndpoint implements AdminEhrEndpoint {

    public static final String ADMIN_EHR_PATH = "rest/admin/ehr/";

    private final DefaultRestClient defaultRestClient;

    public DefaultRestAdminEhrEndpoint(DefaultRestClient defaultRestClient) {
        this.defaultRestClient = defaultRestClient;
    }

    @Override
    public int delete(UUID ehrId) {
        if (ehrId == null) return 0;

        URI uri = defaultRestClient.getConfig().getBaseUri().resolve(ADMIN_EHR_PATH + ehrId.toString());

        HttpResponse response = defaultRestClient.internalDelete(uri, null);

        return response.getStatusLine().getStatusCode();
    }
}
