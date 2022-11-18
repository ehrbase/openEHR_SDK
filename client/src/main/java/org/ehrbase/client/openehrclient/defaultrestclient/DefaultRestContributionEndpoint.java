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
import com.nedap.archie.rm.changecontrol.OriginalVersion;
import com.nedap.archie.rm.composition.Composition;
import java.io.IOException;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.UUID;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.flattener.Unflattener;
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
    public VersionUid mergeContributionEntity(ContributionCreateDto contribution) {
        URI baseUri = defaultRestClient.getConfig().getBaseUri();
        return defaultRestClient.httpPost(
                baseUri.resolve(EHR_PATH + ehrId.toString() + CONTRIBUTION_PATH), contribution);
    }

    //    TODO:: NEED to clarify requirement. The method below just some test that make sure all is map
    //     This method should looks like mergeContributionEntity(Composition composition)
    //     or  mergeContributionEntity(ContributionWrapper wrapper)
    //     for now need to clarify what INITIAL info should be set toContribution in case if I hav only Composition
    @Override
    public VersionUid mergeContributionEntity(ContributionCreateDto contribution, Object entity) {
        if (entity != null) {
            // TODO:: This can be wrong method it works but need to check requirements and
            //  ContributionCreateDto should be inherited from RmObject
            OriginalVersion<LinkedHashMap<String, Object>> originalVersion =
                    convertRmObjectToLinkedHashMapOriginalVersion(contribution, entity);
            contribution.getVersions().add(originalVersion);
        }

        URI baseUri = defaultRestClient.getConfig().getBaseUri();
        return defaultRestClient.httpPost(
                baseUri.resolve(EHR_PATH + ehrId.toString() + CONTRIBUTION_PATH), contribution);
    }

    @SuppressWarnings("unchecked")
    private OriginalVersion<LinkedHashMap<String, Object>> convertRmObjectToLinkedHashMapOriginalVersion(
            ContributionCreateDto contribution, Object entity) {
        Composition composition = (Composition)
                new Unflattener(defaultRestClient.getTemplateProvider(), defaultRestClient.getDefaultValuesProvider())
                        .unflatten(entity);

        //      TODO:: how it should be --> new CanonicalJson().unmarshal(new CanonicalJson().marshal(composition),
        // Composition.class)
        String compositionRmObjectString = new CanonicalJson().marshal(composition);
        LinkedHashMap<String, Object> secondComposition =
                (LinkedHashMap<String, Object>) new CanonicalJson().unmarshalToMap(compositionRmObjectString);
        OriginalVersion<LinkedHashMap<String, Object>> originalVersion =
                (OriginalVersion<LinkedHashMap<String, Object>>)
                        contribution.getVersions().get(0);
        originalVersion.setData(secondComposition);
        return originalVersion;
    }

    @Override
    public Optional<Contribution> find(UUID ehrId, UUID contributionId) {

        URI uri = defaultRestClient
                .getConfig()
                .getBaseUri()
                .resolve(EHR_PATH + ehrId.toString() + CONTRIBUTION_PATH + contributionId.toString());

        HttpResponse response = defaultRestClient.internalGet(uri, null, ContentType.APPLICATION_JSON.getMimeType());

        try {
            String body = IOUtils.toString(response.getEntity().getContent(), UTF_8);

            // TODO:: This replacement is wrong. need to clarify why we have CONTRIBUTION on type on json
            return Optional.of(new CanonicalJson()
                    .unmarshal(body.replace("\"_type\": \"CONTRIBUTION\",", ""), Contribution.class));
        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    @Override
    public VersionUid mergeContributionCompositionEntity(Object entity) {
        throw new ClientException("Still not implemented");
    }

    @Override
    public VersionUid mergeContributionCompositionsEntity(Object entity) {
        throw new ClientException("Still implemented");
    }
}
