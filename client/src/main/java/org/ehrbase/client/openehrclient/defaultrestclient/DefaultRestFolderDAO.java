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

package org.ehrbase.client.openehrclient.defaultrestclient;


import com.google.common.net.HttpHeaders;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.directory.Folder;
import com.nedap.archie.rm.support.identification.ObjectRef;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.client.openehrclient.FolderDAO;

import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClient.*;

public class DefaultRestFolderDAO implements FolderDAO {

    private final DefaultRestDirectoryEndpoint directoryEndpoint;
    private final String path;

    public DefaultRestFolderDAO(DefaultRestDirectoryEndpoint directoryEndpoint, String path) {
        this.directoryEndpoint = directoryEndpoint;
        this.path = path;
    }


    @Override
    public String getName() {
        directoryEndpoint.syncFromDb();
        return getFolder().getName().getValue();
    }

    @Override
    public void setName(String name) {
        directoryEndpoint.syncFromDb();
        getFolder().setName(new DvText(name));
        directoryEndpoint.saveToDb();
    }

    @Override
    public Set<String> listSubFolderNames() {
        directoryEndpoint.syncFromDb();
        return Optional.of(getFolder())
                .stream()
                .map(Folder::getFolders)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .map(Folder::getName)
                .map(DvText::getValue)
                .collect(Collectors.toSet());
    }

    private Folder getFolder() {
        return directoryEndpoint.find(path);
    }

    @Override
    public FolderDAO getSubFolder(String path) {
        DefaultRestFolderDAO folderDAO = new DefaultRestFolderDAO(directoryEndpoint, Stream.of(this.path, path).filter(s -> StringUtils.isNotBlank(s)).collect(Collectors.joining("//")));
        folderDAO.sync();
        return folderDAO;
    }

    @Override
    public <T> T addCompositionEntity(T entity) {
        T updatedEntity = directoryEndpoint.getCompositionEndpoint().mergeCompositionEntity(entity);
        UUID uuid = DefaultRestCompositionEndpoint.extractVersionUid(updatedEntity)
                .orElseThrow(() -> new ClientException(String.format("No Id Element for %s", entity.getClass())))
                .getUuid();
        Folder folder = getFolder();
        if (folder.getItems() == null) {
            folder.setItems(new ArrayList<>());
        }
        folder.getItems().add(new ObjectRef(new ObjectVersionId(uuid.toString()), "dffddfd", "VERSIONED_COMPOSITION"));
        directoryEndpoint.saveToDb();
        return updatedEntity;
    }

    @Override
    public <T> List<T> find(Class<T> clazz) {
        List<T> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(getFolder().getItems())) {
            return result;
        }
        Map<String, String> qMap = new HashMap<>();
        String aqlString = "select a/uid/value, a/template_id, a from EHR e [ehr_id/value = '$EHRID$'] contains COMPOSITION a [$COMPOSITIONID$] where a/uid/value matches {$MATCHES$} and a/template_id='$TEMPLATEID$'";
        aqlString = aqlString.replace("$MATCHES$", buildMatches());
        aqlString = aqlString.replace("$EHRID$", directoryEndpoint.getEhrId().toString());
        aqlString = aqlString.replace("$TEMPLATEID$", extractTemplateId(clazz));
        aqlString = aqlString.replace("$COMPOSITIONID$", extractCompositionId(clazz));
        qMap.put("q", aqlString);
        URI uri = directoryEndpoint.getDefaultRestClient().getConfig().getBaseUri().resolve("query/aql");
        try {
            HttpResponse response = Request.Post(uri)
                    .addHeader(HttpHeaders.ACCEPT, ACCEPT_APPLICATION_JSON)
                    .bodyString(OBJECT_MAPPER.writeValueAsString(qMap), ContentType.APPLICATION_JSON)
                    .execute().returnResponse();
            checkStatus(response, HttpStatus.SC_OK, HttpStatus.SC_CREATED, HttpStatus.SC_NO_CONTENT);
            String value = EntityUtils.toString(response.getEntity());
            JsonObject asJsonObject = JsonParser.parseString(value).getAsJsonObject();
            JsonArray rows = asJsonObject.get("rows").getAsJsonArray();
            for (JsonElement jresult : rows) {
                String valueAsString = ((JsonArray) jresult).get(2).toString();
                Composition composition = OBJECT_MAPPER.readValue(valueAsString, Composition.class);
                T flatten = new Flattener().flatten(composition, clazz);
                result.add(flatten);
            }

        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
        return result;
    }

    void sync() {
        getFolder();
        directoryEndpoint.saveToDb();
    }

    private String buildMatches() {
        return getFolder().getItems().stream().map(ObjectRef::getId).map(Object::toString).map(s -> "'" + s + "'").collect(Collectors.joining(","));
    }

    private String extractTemplateId(Class clazz) {
        Template annotation = (Template) clazz.getAnnotation(Template.class);
        return annotation.value();
    }

    private String extractCompositionId(Class clazz) {
        Archetype annotation = (Archetype) clazz.getAnnotation(Archetype.class);
        return annotation.value();
    }
}
