/*
 * Copyright (c) 2020 Christian Chevalley (Hannover Medical School) and Vitasystems GmbH
 *
 * This file is part of project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package org.ehrbase.client.openehrclient.defaultrestclient;

import org.apache.http.HttpResponse;
import org.ehrbase.client.openehrclient.AdminTemplateEndpoint;

import java.net.URI;

/**
 * Requires that the Admin endpoint is active.
 *
 * Parameters in .yml configuration matching the runtime context as:
 *
 * admin-api:
 *   active: true
 *   allowDeleteAll: true
 */
public class DefaultRestAdminTemplateEndpoint implements AdminTemplateEndpoint {

    public static final String ADMIN_TEMPLATE_PATH = "rest/admin/template/";

    private final DefaultRestClient defaultRestClient;

    public DefaultRestAdminTemplateEndpoint(DefaultRestClient defaultRestClient) {
        this.defaultRestClient = defaultRestClient;
    }


    @Override
    public int delete(String templateId) {
        if (templateId == null)
            return 0;

        URI uri = defaultRestClient.getConfig().getBaseUri().resolve(ADMIN_TEMPLATE_PATH + templateId);

        HttpResponse response =
                defaultRestClient.internalDelete(
                        uri,
                        null);

        return response.getStatusLine().getStatusCode();
    }
}
