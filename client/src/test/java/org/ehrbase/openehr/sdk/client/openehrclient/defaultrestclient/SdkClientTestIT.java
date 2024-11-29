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
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;
import org.ehrbase.openehr.sdk.client.openehrclient.OpenEhrClient;
import org.ehrbase.openehr.sdk.client.openehrclient.OpenEhrClientConfig;
import org.ehrbase.openehr.sdk.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.openehr.sdk.serialisation.dto.DefaultValuesProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;

/**
 * @author Stefan Spiska
 */
public class SdkClientTestIT {

    protected static OpenEhrClient openEhrClient;
    protected UUID ehr;

    protected static GenericContainer postgres;

    protected static GenericContainer ehrbase;

    static {
        try (Network network = Network.newNetwork()) {

            postgres = new EHRbasePostgresContainer(network);
            postgres.start();

            ehrbase = new EHRbaseContainer(network, List.of(postgres));
            ehrbase.start();
        }
    }

    protected static URI ehrBaseAPIEndpoint() {
        return URI.create(
                "http://%s:%d/ehrbase/".formatted(ehrbase.getHost(), ehrbase.getMappedPort(EHRbaseContainer.PORT)));
    }

    @BeforeAll
    public static void setup() throws URISyntaxException {
        openEhrClient = setupDefaultRestClient();
    }

    public static DefaultRestClient setupDefaultRestClient() throws URISyntaxException {
        TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();
        DefaultRestClient client =
                new DefaultRestClient(new OpenEhrClientConfig(ehrBaseAPIEndpoint()), templateProvider);
        templateProvider.listTemplateIds().stream()
                .forEach(t -> client.templateEndpoint().ensureExistence(t));
        return client;
    }

    public static DefaultRestClient setupRestClientWithDefaultTemplateProvider() throws URISyntaxException {
        return new DefaultRestClient(new OpenEhrClientConfig(ehrBaseAPIEndpoint()));
    }

    public static DefaultRestClient setupDefaultRestClientWithDefaultProvider(
            DefaultValuesProvider defaultValuesProvider) {
        TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();
        OpenEhrClientConfig config = new OpenEhrClientConfig(ehrBaseAPIEndpoint());
        config.setDefaultValuesProvider(defaultValuesProvider);
        DefaultRestClient client = new DefaultRestClient(config, templateProvider);
        templateProvider.listTemplateIds().stream()
                .forEach(t -> client.templateEndpoint().ensureExistence(t));
        return client;
    }

    @AfterEach
    public void tearDown() {
        // delete the created EHR using the admin endpoint
        if (ehr != null) {
            openEhrClient.adminEhrEndpoint().delete(ehr);
            ehr = null;
        }
    }
}
