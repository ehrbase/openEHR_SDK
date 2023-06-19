/*
 * Copyright (c) 2021 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient;

import static org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient.DefaultRestEhrEndpoint.EHR_PATH;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nedap.archie.rm.changecontrol.OriginalVersion;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.ehr.VersionedComposition;
import com.nedap.archie.rm.generic.RevisionHistoryItem;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.ehrbase.openehr.sdk.client.openehrclient.VersionedCompositionEndpoint;
import org.ehrbase.openehr.sdk.response.dto.OriginalVersionResponseData;
import org.ehrbase.openehr.sdk.response.dto.RevisionHistoryResponseData;
import org.ehrbase.openehr.sdk.serialisation.dto.RmToGeneratedDtoConverter;
import org.ehrbase.openehr.sdk.util.exception.ClientException;
import org.ehrbase.openehr.sdk.webtemplate.templateprovider.TemplateProvider;

@SuppressWarnings({"java:S6212", "java:S1075"})
public class DefaultRestVersionedCompositionEndpoint implements VersionedCompositionEndpoint {

    public static final String VERSIONED_COMPOSITION_PATH = "/versioned_composition/";

    public static final String REVISION_HISTORY_PATH = "/revision_history";

    public static final String VERSION_PATH = "/version";

    private final DefaultRestClient defaultRestClient;

    private final UUID ehrId;

    public DefaultRestVersionedCompositionEndpoint(DefaultRestClient defaultRestClient, UUID ehrId) {
        this.defaultRestClient = defaultRestClient;
        this.ehrId = ehrId;
    }

    @Override
    public Optional<VersionedComposition> find(UUID versionedObjectUid) {
        URI uri = defaultRestClient
                .getConfig()
                .getBaseUri()
                .resolve(EHR_PATH + ehrId + VERSIONED_COMPOSITION_PATH + versionedObjectUid);
        return defaultRestClient.httpGet(uri, VersionedComposition.class);
    }

    @Override
    public List<RevisionHistoryItem> findRevisionHistory(UUID versionedObjectUid) {
        URI uri = defaultRestClient
                .getConfig()
                .getBaseUri()
                .resolve(EHR_PATH + ehrId + VERSIONED_COMPOSITION_PATH + versionedObjectUid + REVISION_HISTORY_PATH);
        Optional<RevisionHistoryResponseData> result =
                defaultRestClient.httpGet(uri, RevisionHistoryResponseData.class);
        if (result.isEmpty()) {
            return new ArrayList<>();
        } else {
            return result.get().getRevisionHistoryItems();
        }
    }

    @Override
    public <T> Optional<OriginalVersion<T>> findVersionById(
            UUID versionedObjectUid, ObjectVersionId versionUid, Class<T> clazz) {
        URI uri = defaultRestClient
                .getConfig()
                .getBaseUri()
                .resolve(EHR_PATH + ehrId + VERSIONED_COMPOSITION_PATH + versionedObjectUid + VERSION_PATH + "/"
                        + versionUid.getValue());

        return internalFindVersion(uri, clazz);
    }

    @Override
    public <T> Optional<OriginalVersion<T>> findVersionAtTime(
            UUID versionedObjectUid, @Nullable LocalDateTime versionAtTime, Class<T> clazz) {
        try {
            URIBuilder uriBuilder = new URIBuilder(defaultRestClient
                    .getConfig()
                    .getBaseUri()
                    .resolve(EHR_PATH + ehrId + VERSIONED_COMPOSITION_PATH + versionedObjectUid + VERSION_PATH));

            if (versionAtTime != null) {
                uriBuilder.addParameter("version_at_time", versionAtTime.format(DateTimeFormatter.ISO_DATE_TIME));
            }

            return internalFindVersion(uriBuilder.build(), clazz);
        } catch (URISyntaxException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    public <T> Optional<OriginalVersion<T>> internalFindVersion(URI uri, Class<T> clazz) {
        HttpResponse response = defaultRestClient.internalGet(uri, null, ContentType.APPLICATION_JSON.getMimeType());
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
            return Optional.empty();
        }

        try {
            TypeReference<OriginalVersionResponseData<Composition>> valueTypeRef = new TypeReference<>() {};
            return Optional.of(DefaultRestClient.OBJECT_MAPPER.readValue(
                            response.getEntity().getContent(), valueTypeRef))
                    .map(originalVersion -> this.convert(originalVersion, clazz));
        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    /**
     * Converts an {@link OriginalVersionResponseData} into an {@link OriginalVersion}.
     *
     * @param originalVersion response data to convert
     * @param clazz           expected class
     * @param <T>             composition class
     * @return converted object
     */
    private <T> OriginalVersion<T> convert(OriginalVersionResponseData<Composition> originalVersion, Class<T> clazz) {
        OriginalVersion<T> result = new OriginalVersion<>();
        result.setUid(originalVersion.getVersionId());
        result.setPrecedingVersionUid(originalVersion.getPrecedingVersionUid());
        result.setLifecycleState(originalVersion.getLifecycleState());
        result.setCommitAudit(originalVersion.getAuditDetails());
        result.setSignature(originalVersion.getSignature());
        result.setOtherInputVersionUids(originalVersion.getOtherInputVersionUids());
        result.setAttestations(originalVersion.getAttestations());

        T composition = createFlattener(defaultRestClient.getTemplateProvider())
                .toGeneratedDto(originalVersion.getData(), clazz);
        result.setData(composition);

        return result;
    }

    protected RmToGeneratedDtoConverter createFlattener(TemplateProvider templateProvider) {
        return new RmToGeneratedDtoConverter(templateProvider);
    }
}
