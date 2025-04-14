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

import static org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient.DefaultRestEhrEndpoint.EHR_PATH;

import com.nedap.archie.rm.directory.Folder;
import com.nedap.archie.rm.support.identification.ObjectId;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.util.Optional;
import java.util.UUID;
import org.ehrbase.openehr.sdk.client.openehrclient.DirectoryCrudEndpoint;
import org.ehrbase.openehr.sdk.response.dto.DirectoryResponseData;
import org.ehrbase.openehr.sdk.util.exception.SdkException;

/**
 * @author Stefan Spiska
 */
public class DefaultCrudEndpoint implements DirectoryCrudEndpoint {

    static final String DIRECTORY_PATH = "/directory/";
    private final DefaultRestClient defaultRestClient;

    private final UUID ehrId;

    public DefaultCrudEndpoint(DefaultRestClient restClient, UUID ehrId) {
        this.defaultRestClient = restClient;
        this.ehrId = ehrId;
    }

    @Override
    public ObjectVersionId createDirectory(Folder folder) {
        return defaultRestClient.httpPost(
                defaultRestClient.getConfig().getBaseUri().resolve(EHR_PATH + ehrId.toString() + DIRECTORY_PATH),
                folder);
    }

    @Override
    public ObjectVersionId updateDirectory(Folder folder) {

        ObjectVersionId versionUid = Optional.ofNullable(folder.getUid())
                .map(ObjectId::toString)
                .map(ObjectVersionId::new)
                .orElseThrow(() -> new SdkException("Folder.uuid not set"));
        return defaultRestClient.httpPut(
                defaultRestClient.getConfig().getBaseUri().resolve(EHR_PATH + ehrId.toString() + DIRECTORY_PATH),
                folder,
                versionUid);
    }

    @Override
    public Optional<Folder> getDirectory() {

        Optional<DirectoryResponseData> directoryResponseData = defaultRestClient.httpGet(
                defaultRestClient.getConfig().getBaseUri().resolve(EHR_PATH + ehrId.toString() + DIRECTORY_PATH),
                DirectoryResponseData.class);

        if (directoryResponseData.isEmpty()) {
            return Optional.empty();
        }

        Folder folder = new Folder();
        DirectoryResponseData responseData = directoryResponseData.get();
        folder.setUid(responseData.getUid());
        folder.setArchetypeNodeId("openEHR-EHR-FOLDER.generic.v1");
        folder.setName(responseData.getName());
        folder.setFolders(responseData.getFolders());
        folder.setItems(responseData.getItems());
        folder.setDetails(responseData.getDetails());

        return Optional.of(folder);
    }
}
