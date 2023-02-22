/*
 * Copyright (c) 2019 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.client.openehrclient.defaultrestclient;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestEhrEndpoint.EHR_PATH;

import com.nedap.archie.rm.changecontrol.Contribution;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.openehrclient.ContributionEndpoint;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.response.openehr.ContributionCreateDto;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;

/**
 * The type Default rest contribution endpoint.
 */
public class DefaultRestContributionEndpoint implements ContributionEndpoint {

    public static final String CONTRIBUTION_PATH = "/contribution/";
    private final DefaultRestClient defaultRestClient;
    private final UUID ehrId;

    /**
     * Instantiates a new Default rest contribution endpoint.
     *
     * @param defaultRestClient the default rest client
     * @param ehrId             the ehr id
     */
    public DefaultRestContributionEndpoint(DefaultRestClient defaultRestClient, UUID ehrId) {
        this.defaultRestClient = defaultRestClient;
        this.ehrId = ehrId;
    }

    @Override
    public VersionUid saveContribution(ContributionCreateDto contribution) {
        URI baseUri = defaultRestClient.getConfig().getBaseUri();
        return defaultRestClient.httpPost(
                baseUri.resolve(EHR_PATH + ehrId.toString() + CONTRIBUTION_PATH), contribution);
    }

    @Override
    public Optional<Contribution> find(UUID contributionId) {

        URI uri = defaultRestClient
                .getConfig()
                .getBaseUri()
                .resolve(EHR_PATH + ehrId.toString() + CONTRIBUTION_PATH + contributionId.toString());

        HttpResponse response = defaultRestClient.internalGet(uri, null, ContentType.APPLICATION_JSON.getMimeType());

        try {
            String body = IOUtils.toString(response.getEntity().getContent(), UTF_8);

            return Optional.of(new CanonicalJson().unmarshal(body, Contribution.class));
        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }
}
